package com.genscript.gsscm.quote.web;

import static java.io.File.separator;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.common.constant.QuoteStatusType;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.util.PdfUtils;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.quote.dto.QuotationPrintDTO;
import com.genscript.gsscm.quote.dto.QuoteItemDTO;
import com.genscript.gsscm.quote.dto.QuoteMainDTO;
import com.genscript.gsscm.quote.entity.QuoteItem;
import com.genscript.gsscm.quote.entity.QuoteTemplate;
import com.genscript.gsscm.quote.service.QuoteItemService;
import com.genscript.gsscm.quote.service.QuotePrintService;
import com.genscript.gsscm.quote.service.QuoteService;
import com.genscript.gsscm.ws.WSException;

@Results({
		@Result(name = "quote_print", location = "quote/quote_print.jsp"),
		@Result(name = "quote_template", location = "quote/quote_template.jsp"),
		@Result(name = "quote_reopen", location = "quote/quote_reopen.jsp") })
public class QuoteExtraAction extends BaseAction<Object> implements
		ServletContextAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8029698034152652101L;
	@Autowired
	private QuoteService quoteService;
	@Autowired
	private QuotePrintService quotePrintSerice;
	private QuoteMainDTO quote;
	private String sessQuoteNo;
	private String quoteNo;
	// save template时是否覆盖已有同名的template.
	private String overrideFlag;
	private String tmplName;
	private List<QuoteTemplate> templateList;
	private Integer maxTemplate = 6;
	// reopen quote
	private String statusReason;
	private String quoteStatus;
	private String statusNote;
	@Autowired
	private ExceptionService exceptionUtil;

	// print
	private QuotationPrintDTO quotationPrintDTO;

	// servletContext
	private ServletContext context;
 

	private static String FILE_STORAGE = "documents";
	private static String FE = "txt";
	private Map<String, QuoteItemDTO> itemMap;

	public static String getFileStorage(HttpServletRequest request, String sss) {
		return request.getSession().getServletContext()
				.getRealPath(FILE_STORAGE + separator + sss);
	}

	public static String getDocumentItemStorage(HttpServletRequest request) {
		return getFileStorage(request, FE);
	}

	public Map<String, QuoteItemDTO> getItemMap() {
		return itemMap;
	}

	public void setItemMap(Map<String, QuoteItemDTO> itemMap) {
		this.itemMap = itemMap;
	}

	public void PrintTxt() {
		StringBuffer sb = new StringBuffer();
		String quoteNo = this.getSessQuoteNo();
		System.out.println(">>>>>>>~~~~~~~~~~~~~~~~~" + quoteNo);
		sb = quotePrintSerice.getStrByQuoteNo(quoteNo);
		System.out.println(">>>>>>>" + sb.toString());
		FileOutputStream fos = null;
		long r1 = System.currentTimeMillis();
		String fileName = "Quote-" + this.getSessQuoteNo() + r1 + ".txt";
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			File tempFile = new File(getDocumentItemStorage(request)
					+ separator + fileName);
			if (tempFile.exists()) {
				tempFile.delete();
			}
			FileUtils.forceMkdir(tempFile.getParentFile());
			fos = new FileOutputStream(tempFile, true); 
			fos.write((sb.toString()).getBytes());
			fos.flush();
			fos.close(); 
			// 下载
			HttpServletResponse response = Struts2Util.getResponse();
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ fileName);
			java.io.OutputStream os = response.getOutputStream();
			String file = getDocumentItemStorage(request) + separator
					+ fileName;
			java.io.FileInputStream fis = new java.io.FileInputStream(file);
			byte[] b = new byte[1024];
			int i = 0;
			while ((i = fis.read(b)) > 0) { 
				os.write(b, 0, i);
			}
			fis.close();
			os.flush();
			response.flushBuffer();
			os.close();
		} catch (Exception e) {
			System.out.println("Error exec!");
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			Struts2Util.renderText(exDTO.getMessageDetail() + "\n"
					+ exDTO.getAction());
			e.printStackTrace();
		}
	}

	 public void Printdocument(){

			StringBuffer sb = new StringBuffer();
			String quoteNo = this.getSessQuoteNo();
			sb = quotePrintSerice.getStrByQuoteNo(quoteNo);
			System.out.println(">>>>>>>" + sb.toString());
			FileOutputStream fos = null;
			long r1 = System.currentTimeMillis();
			String fileName = "Quote-" + this.getSessQuoteNo()+r1 + ".doc";
			try {
				HttpServletRequest request = ServletActionContext.getRequest();
				File tempFile = new File(getDocumentItemStorage(request)
						+ separator + fileName);
				 if (tempFile.exists()) {
					if (tempFile.delete()) {
						System.out.println("delete the ...." + tempFile.getName());
					}
				} 
				FileUtils.forceMkdir(tempFile.getParentFile());
				System.out.println(">>>>>>>" + sb.toString());
				fos = new FileOutputStream(tempFile, true); 
				fos.write((sb.toString()).getBytes());
				fos.flush();
				fos.close(); 
				sb = null; 
				// 下载
				HttpServletResponse response = Struts2Util.getResponse();
				response.setCharacterEncoding("utf-8");
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition", "attachment; filename="
						+ fileName);
				java.io.OutputStream os = response.getOutputStream();
				String file = getDocumentItemStorage(request) + separator
						+ fileName;
				java.io.FileInputStream fis = new java.io.FileInputStream(file);
				byte[] b = new byte[1024];
				int i = 0;
				while ((i = fis.read(b)) > 0) {
					os.write(b, 0, i);
				}
				fis.close();
				os.flush();
				response.flushBuffer();
				os.close();
			} catch (Exception e) {
				System.out.println("Error exec!");
				WSException exDTO = exceptionUtil.getExceptionDetails(e);
				exceptionUtil.logException(exDTO, this.getClass(), e,
						new Exception().getStackTrace()[0].getMethodName(),
						"INTF0203", SessionUtil.getUserId());
				Struts2Util.renderText(exDTO.getMessageDetail() + "\n"
						+ exDTO.getAction());
				e.printStackTrace();
			}
		
	 }
	/**
	 * Select Option下拉框中的Print Quote.
	 * 
	 * @author wangsf
	 * @serialData 2010-12-24
	 * @return
	 */
	public void print() {
		// String requestPath = "quote/quote!showPrintPage.action?quoteNo=" +
		// this.getSessQuoteNo();
		String responsePdfName = "Quote-" + this.getQuoteNo() + ".pdf";
		// 1. 通过JSP生成原始的PDF，没有合并子Item, 没有加水印
		// String pdfName = PdfUtils.convertServerUrl2Pdf(requestPath);

		// PdfUtils pdfUtil = new PdfUtils(pdfName, pdfName);
		// HashMap<String, String> moreInfo = new HashMap<String, String>();
		// try {
		// //加水印，头尾logo
		// // pdfUtil.extraPdf(moreInfo, null, "c:/tmp/baidulogo.gif", null);
		// //合并pdf
		// String names[] = {pdfName, "c:/tmp/OD.pdf"};
		// PdfUtils.mergePdfFiles(names, "c:/tmp/tag.pdf");
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// 3. 这是提供下载的
		String pdfName = this.quoteService
				.mergePdfFiles(Integer.parseInt(this.getQuoteNo()),
						quotationPrintDTO, context);
		if (pdfName != null) {
			PdfUtils.downloadFile(pdfName, responsePdfName);
		}

		/*
		 * quote = (QuoteMainDTO) SessionUtil.getRow(
		 * SessionConstant.Quote.value(), this.sessQuoteNo); return
		 * "quote_print";
		 */
	}

	// public void run() {
	// Map<String ,String> data=new HashMap<String ,String >();
	// try {
	// quote = (QuoteMainDTO) SessionUtil.getRow(SessionConstant.Quote
	// .value(), this.sessQuoteNo);
	// String filePath=fileService.getUploadPath();
	// File write = new File(filePath + this.sessQuoteNo + ".txt");
	// if (!write.exists()) {
	// write = new File(filePath + this.sessQuoteNo + ".txt");
	// }
	// BufferedWriter bw = new BufferedWriter(new FileWriter(write));
	// // FileInputStream in = new
	// // FileInputStream("F:/java-work/scm/WebRoot/images/doc_top.jpg");
	// // OutputStream outp = new FileOutputStream(write);
	// // int b=0;
	// // while((b=in.read())!=-1){
	// // outp.write(b);
	// // }
	// // in.close();
	// // outp.close();
	//
	// // 如果你有一个Image对象，想把它写入文件可以这样做：
	// // BufferedImage image = ImageIO.read(new File("d:\\source.gif"));
	// // 要想保存这个对象的话你要把image声明为BufferedImage 类型
	// // ImageIO.write(image, "png", new File("f:\\test.png"));
	// while (quote != null) {
	// bw.write("                                      \r\n");
	// bw.write("                                      \r\n");
	// bw.write("                                      \r\n");
	// bw
	// .write("                                                  Project Proposal\r\n");
	// bw.write("                                      \r\n");
	// bw.write("                                      \r\n");
	// bw.write("                                      Quote No:  "
	// + quote.getQuoteNo() + "\r\n");
	// bw.write("                                      Quote Date:  "
	// + quote.getQuoteDate() + "\r\n");
	// bw
	// .write("                                      Expire Date:   "
	// + quote.getQuoteDate() + "\r\n");
	// bw
	// .write("                                      Prepared Date:  "
	// + quote.getExchRate() + "\r\n");
	// bw.write("                                      \r\n");
	// bw
	// .write("                                      Prepared For:  Tusuya Taneda \r\n");
	// bw
	// .write("                                      taneda@tainestu.com \r\n");
	// bw.write("                                      \r\n");
	// bw
	// .write("                                      Thermostable Enzyme Laboratory Co.,Ltd \r\n");
	// bw.write("                                      Hyogo \r\n");
	// bw.write("                                      japan \r\n");
	// bw.write("                                      \r\n");
	// bw
	// .write("                                      ......     \r\n");
	// bw.close();
	// break;
	// }
	// String url = filePath + this.sessQuoteNo + ".txt ";
	// String text = "cmd /c C:/htmlToPdf/wkhtmltopdf/wkhtmltopdf.exe "
	// + url + filePath + this.sessQuoteNo + ".pdf";
	// Runtime.getRuntime().exec(text);
	// Thread.sleep(1000);
	// System.out.println(" ");
	// System.out.println(" ");
	// System.out.println(" ");
	// System.out.println(" ");
	// System.out.println(" ");
	// System.out.println(" ");
	// System.out.println(" ");
	// write.delete();
	// // Runtime.getRuntime().exec(
	// // "cmd /c  start  " + "/tmp/" + this.sessQuoteNo + ".pdf");
	// String fileName=this.sessQuoteNo + ".pdf";
	// data.put("filePath",filePath);
	// data.put("fileName",fileName);
	// data.put("No","1");
	// } catch (Exception e) {
	// e.printStackTrace();
	// data.put("No","0");
	// Struts2Util.renderJson(data);
	// }
	// Struts2Util.renderJson(data);
	// }

	public void convertFile() {
		HttpURLConnection con = null;
		URL url = null;
		Process p = null;
		String cmd = "html2pdf";
		try {
			String sessionid = Struts2Util.getRequest().getSession().getId();
			System.out.println("sessionid==" + sessionid);
			url = new URL(
					"  http://10.168.2.181:8080/scm/quote_extra!print.action");
			con = (HttpURLConnection) url.openConnection();
			con.setRequestProperty("Cookie", "JSESSIONID=" + sessionid);
			con.connect();
			int size = 0;
			byte[] buf = new byte[1024];
			BufferedInputStream bis = new BufferedInputStream(
					con.getInputStream());
			StringBuffer strb = new StringBuffer();
			while ((size = bis.read(buf)) != -1) {
				strb.append(new String(buf, 0, size));
			}
			FileWriter writer = null;
			File file = new File("/tmp/quote_print.html");
			writer = new FileWriter(file);
			writer.write(strb.toString());
			writer.flush();
			writer.close();
			ProcessBuilder pb = new ProcessBuilder(cmd,
					"/tmp/quote_print.html", "/tmp/quote_print.pdf");
			pb.redirectErrorStream(true);
			p = pb.start();
			InputStreamReader ir = new InputStreamReader(p.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			String line;
			while ((line = input.readLine()) != null) {
				System.out.println(line);
			}
			// 提示下载
			HttpServletResponse response = Struts2Util.getResponse();
			response.setContentType("APPLICATION/DOWNLOAD");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ "PrintQuote" + (new Random()).nextInt() + ".pdf");// PackingSlip是文件名
			java.io.OutputStream os = response.getOutputStream();
			java.io.FileInputStream fis = new java.io.FileInputStream(
					"/tmp/quote_print.pdf");
			byte[] b = new byte[1024];
			int i = 0;
			while ((i = fis.read(b)) > 0) {
				os.write(b, 0, i);
			}
			fis.close();
			os.flush();
			response.flushBuffer();
			os.close();
			bis.close();
			con.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);

		} finally {
			if (p != null) {
				p.destroy();
			}
		}
	}

	/**
	 * Select Option下拉框中的Repeat As New Quote, 产生一个新的Quote.
	 * 
	 * @return
	 */
	public String repeat() {
		try {
			Integer newQuoteNo = quoteService.repeatQuote(
					Integer.valueOf(this.sessQuoteNo), SessionUtil.getUserId());
			Struts2Util.renderJson(newQuoteNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Select Option下拉框中的save as my Template.进行的保存操作.
	 * 
	 * @return
	 */
	public String saveTemplate() {
		QuoteTemplate quoteTemplate = new QuoteTemplate();
		quoteTemplate.setTmplName(this.getTmplName());
		quoteTemplate.setOwner(SessionUtil.getUserId());
		quoteTemplate.setSrcQuoteNo(Integer.valueOf(this.sessQuoteNo));
		this.quoteService.saveQuoteTemplate(quoteTemplate,
				SessionUtil.getUserId(), overrideFlag);
		Struts2Util.renderJson("successful");
		return null;
	}

	/**
	 * Select Option下拉框中的save as my Template.
	 * 
	 * @return
	 */
	public String searchTemplate() {
		templateList = quoteService.getTemplateList(SessionUtil.getUserId());
		return "quote_template";
	}

	/**
	 * Order Template中删除一个或多个Template.
	 * 
	 * @return
	 */
	public String delTemplate() {
		List<Integer> tmplIdList = new ArrayList<Integer>();
		String[] delIdArray = Struts2Util.getRequest().getParameterValues(
				"tmplId");
		for (String id : delIdArray) {
			if (StringUtils.isNumeric(id)) {
				tmplIdList.add(Integer.valueOf(id));
			}
		}
		this.quoteService.delQuoteTemplate(tmplIdList);
		Struts2Util.renderJson("successful");
		return null;
	}

	/**
	 * Reopen一个已经Canceled Quote， 更新Quote的状态为OP(Quote On Open), Item状态不变.
	 * 如果statusReason为null, 则是进入页面.
	 * 
	 * @return
	 */
	public String reopen() {
		if (this.statusReason != null) {// 响应操作
			String status = QuoteStatusType.fromValue(this.quoteStatus).value();// 验证status
			this.quoteService.updateQuoteStatus(
					Integer.valueOf(this.sessQuoteNo), statusReason, status,
					SessionUtil.getUserId(), statusNote);
			Struts2Util.renderJson("successful");
			return null;
		}
		return "quote_reopen";// 进入页面
	}

	public String getOverrideFlag() {
		return overrideFlag;
	}

	public void setOverrideFlag(String overrideFlag) {
		this.overrideFlag = overrideFlag;
	}

	public String getTmplName() {
		return tmplName;
	}

	public void setTmplName(String tmplName) {
		this.tmplName = tmplName;
	}

	public Integer getMaxTemplate() {
		return maxTemplate;
	}

	public void setMaxTemplate(Integer maxTemplate) {
		this.maxTemplate = maxTemplate;
	}

	public String getStatusReason() {
		return statusReason;
	}

	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
	}

	public String getStatusNote() {
		return statusNote;
	}

	public void setStatusNote(String statusNote) {
		this.statusNote = statusNote;
	}

	public QuoteMainDTO getQuote() {
		return quote;
	}

	public void setQuote(QuoteMainDTO quote) {
		this.quote = quote;
	}

	public String getSessQuoteNo() {
		return sessQuoteNo;
	}

	public void setSessQuoteNo(String sessQuoteNo) {
		this.sessQuoteNo = sessQuoteNo;
	}

	public List<QuoteTemplate> getTemplateList() {
		return templateList;
	}

	public void setTemplateList(List<QuoteTemplate> templateList) {
		this.templateList = templateList;
	}

	public String getQuoteStatus() {
		return quoteStatus;
	}

	public void setQuoteStatus(String quoteStatus) {
		this.quoteStatus = quoteStatus;
	}

	public QuotationPrintDTO getQuotationPrintDTO() {
		return quotationPrintDTO;
	}

	public void setQuotationPrintDTO(QuotationPrintDTO quotationPrintDTO) {
		this.quotationPrintDTO = quotationPrintDTO;
	}

	public String getQuoteNo() {
		return quoteNo;
	}

	public void setQuoteNo(String quoteNo) {
		this.quoteNo = quoteNo;
	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		this.context = arg0;
	}

}
