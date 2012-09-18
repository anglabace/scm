package com.genscript.gsscm.common.util;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.genscript.gsscm.common.FileUtils;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

/**
 * Reads the pages of an existing PDF file, adds pagenumbers and a watermark
 * etc.
 * 
 * @version 1.0
 * @author wangsf
 */
public class PdfUtils {
	private static Logger logger = Logger.getLogger(PdfUtils.class);
	private String readerURL = null;// 源pdf路径
	private String waterURL = null;
	private String tagURL = null;// 目标pdf路径
	private String headURL = null;
	private String footURL = null;
	boolean bExtraSame = false;// 扩展操作的源文件与目标文件是否同名.

	/**
	 * Construct of PdfUtil.
	 * 
	 * @param srcURL
	 *            源pdf路径
	 * @param tagURL
	 *            目标pdf路径
	 */
	public PdfUtils(String srcURL, String tagURL) {
		this.readerURL = srcURL;
		this.tagURL = tagURL;
		if (srcURL.equals(tagURL)) {
			initSame(srcURL);
		}
	}

	PdfUtils() {
	}

	/**
	 * 对已存在的pdf文档进行加工，修改. 注： 页眉与页脚的位置应与已存在pdf document的topmargin,
	 * bottommargin相配合， 以防被覆盖.
	 * 
	 * @param moreInfo
	 *            文档meta信息
	 * @throws IOException
	 * @throws DocumentException
	 */
	public void extraPdf(HashMap<String, String> moreInfo, String backImgUrl,
			String headImgUrl, String footImgUrl) throws Exception {
		headURL = headImgUrl;
		footURL = footImgUrl;
		waterURL = backImgUrl;
		logger.debug("headURL: " + headURL);
		logger.debug("footURL: " + footURL);
		logger.debug("waterURL: " + waterURL);
		logger.debug("tagURL: " + tagURL);
		PdfReader reader = null;
		PdfStamper stamp = null;
		Image img = null;
		// Image head = null;
		// Image foot = null;
		try {
			// we create a reader for a certain document
			// 预创建新文档， 获得原文档的document相关信息.
			reader = new PdfReader(readerURL);
			int n = reader.getNumberOfPages();
			stamp = new PdfStamper(reader, new FileOutputStream(tagURL));
			stamp.setMoreInfo(moreInfo);// 设置文档meta信息.
			// float width = 768;
			// float height = 768;
			// if (n > 0) {
			// width = stamp.getUnderContent(1).getPdfDocument().getPageSize()
			// .getWidth();
			// height = stamp.getUnderContent(1).getPdfDocument()
			// .getPageSize().getHeight();
			// logger.debug("document's width: " + width);
			// logger.debug("document's height: " + height);
			// }
			if (waterURL != null) {
				// 设置水印|页眉|页脚 图片的合适大小与绝对位置(每一页)
				img = Image.getInstance(waterURL);
				img.setAbsolutePosition(64, 110);
				img.scaleAbsoluteHeight(600);
				img.scaleAbsoluteWidth(493);

			}
			// 对每一页进行处理， 加入水印， 页眉， 页脚.
			// adding content to each page
			int i = 1;
			PdfContentByte under;
			// PdfContentByte over;
			while (i <= n) {

				// width =
				// stamp.getUnderContent(i).getPdfDocument().getPageSize().getWidth();
				// height =
				// stamp.getUnderContent(i).getPdfDocument().getPageSize().getHeight();
				// over = stamp.getOverContent(i);
				// over.getPdfDocument().setMargins(10, 10, 50,50);
				// over.getPdfDocument().setPageSize(new
				// Rectangle(width,height));

				if (waterURL != null) {
					// 设置水印|页眉|页脚 图片的合适大小与绝对位置(每一页)
					//
				}
				// if (headURL != null) {
				// head = Image.getInstance(headURL);
				// head.setAbsolutePosition(52, height-29);// 头部位置, 数值大往顶端移
				// head.scaleAbsoluteHeight(29);
				// head.scaleAbsoluteWidth(493);
				// over.addImage(head);
				// }
				// if (footURL != null) {
				// foot = Image.getInstance(footURL);
				// foot.setAbsolutePosition(52, 1);// 底部位置
				// foot.scaleAbsoluteHeight(29);
				// foot.scaleAbsoluteWidth(493);
				// over.addImage(foot);

				i++;
			}
		} catch (Exception ex) {
			Logger.getLogger(PdfUtils.class).error(ex.getMessage());
			ex.printStackTrace();
			throw ex;
		} finally {
			reader.close();
			stamp.close();
		}
		logger.debug("extra water, head and foot successful!");
		callBackExtra();
	}

	/**
	 * 扩展操作之后回调.
	 */
	private void callBackExtra() {
		if (bExtraSame) {
			FileUtils.delFile(this.readerURL);
			FileUtils.renameFile(this.tagURL, this.readerURL);
		}
	}

	/**
	 * 当源文件与目标文件一致时执行.
	 * 
	 * @param srcRUL
	 */
	void initSame(final String srcRUL) {
		this.bExtraSame = true;
		// 获得pdf临时文件名称
		String dir = srcRUL.substring(0,
				srcRUL.lastIndexOf(System.getProperty("file.separator")) + 1);
		Date now = new Date();
		Format formatter = new SimpleDateFormat("yyMMddHHmmssSS");
		this.tagURL = dir + "stamp_" + formatter.format(now) + ".pdf";
		System.out.println("srcPDF is same with tagPDF !");
	}

	/**
	 * 获得pdf文件的相关属性.
	 * 
	 * @return
	 */
	public static HashMap<String, String> getFileInfo() {
		HashMap<String, String> moreInfo = new HashMap<String, String>();
		moreInfo.put("Author", "wangsifa");
		moreInfo.put("Creator", "genscript");
		return moreInfo;
	}

	/**
	 * 把多个PDF文件合并到一个PDF中
	 * 
	 * @author wangsf
	 * @serialData 2010-12-24
	 * @param srcFullNames
	 *            多个源PDF文件名
	 * @param tagFullName
	 *            目标文件名
	 */
	public static void mergePdfFiles(String[] srcFullNames, String tagFullName) {
		String separator = System.getProperty("file.separator");
		if (separator.equals("\\")) {
			try {
				Document document = new Document(
						new PdfReader(srcFullNames[0]).getPageSize(1));
				PdfCopy copy = new PdfCopy(document, new FileOutputStream(
						tagFullName));
				document.open();
				for (int i = 0; i < srcFullNames.length; i++) {
					PdfReader reader = new PdfReader(srcFullNames[i]);
					int n = reader.getNumberOfPages();
					for (int j = 1; j <= n; j++) {
						document.newPage();
						PdfImportedPage page = copy.getImportedPage(reader, j);
						copy.addPage(page);
					}
				}
				document.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append("gs -dNOPAUSE -sDEVICE=pdfwrite -sOUTPUTFILE=")
					.append(tagFullName).append(" -dBATCH ");
			for (int i = 0; i < srcFullNames.length; i++) {
				sb.append(srcFullNames[i]).append(" ");
			}
			try

			{
				System.out.println(sb.toString());
				Process process = Runtime.getRuntime().exec(sb.toString());
				InputStreamReader ir = new InputStreamReader(
						process.getInputStream());
				LineNumberReader input = new LineNumberReader(ir);

				String line;
				while ((line = input.readLine()) != null) {
					System.out.println(line);
				}
			} catch (java.io.IOException e) {
				System.err.println("IOException " + e.getMessage());
			}
		}

	}

	/**
	 * 把服务器上的文件以可靠流的形式传给客户端， 客户端可选择'保存'或'打开', 如果取消则抛出异常(只在服务端)
	 * 
	 * @author wangsf
	 * @serialData 2010-12-24
	 * @param fileFullName
	 *            responsePdfName
	 */
	public static void downloadFile(String fileFullName, String responsePdfName) {
		try {
			// 提示下载
			HttpServletResponse response = Struts2Util.getResponse();
			response.setContentType("APPLICATION/DOWNLOAD");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ responsePdfName);
			java.io.OutputStream os = response.getOutputStream();
			java.io.FileInputStream fis = new java.io.FileInputStream(
					fileFullName);
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
			e.printStackTrace();
			throw new RuntimeException(e);

		}
	}

	public static void downloadFile2(String fileFullName, String responsePdfName) {
		try {
			// 提示下载
			HttpServletResponse response = Struts2Util.getResponse();
			response.setContentType("APPLICATION/DOWNLOAD");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ responsePdfName);
			java.io.OutputStream os = response.getOutputStream();
			java.io.FileInputStream fis = new java.io.FileInputStream(
					fileFullName);
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
			e.printStackTrace();
			throw new RuntimeException(e);

		}
	}

	/**
	 * 根据服务器地址返回的html生成pdf文件.
	 * 
	 * @author wangsf
	 * @serialData 2010-12-24
	 * @param requestPath
	 *            http://10.168.2.181:8080/scm/quote_extra!print.action",
	 *            则传"quote_extra!print.action". 如："PrintQuote" + (new
	 *            Random()).nextInt() + ".pdf"
	 */
	public static String convertServerUrl2Pdf(String requestPath, String param) {
		String tempPdfFileName = null;
		HttpURLConnection con = null;
		HttpsURLConnection connection = null;
		URL url = null;
		Process process = null;
		String processName = getPdfProcessName();
		try {
			// 取得路径生成html;
			String tempHtmlFileName = getTempFileShortName() + ".html";
			tempPdfFileName = getTempFileShortName() + ".pdf";
			String sessionid = Struts2Util.getRequest().getSession().getId();
			String port = "";
			if (Struts2Util.getRequest().getServerPort() != 80) {
				port = ":" + Struts2Util.getRequest().getServerPort();
			}
			if (Struts2Util.getRequest().getServerPort() == 443) {
				port = ":8443";
			}
			System.out.println("#######################Order/Quote print Port"
					+ port);
			String basePath = Struts2Util.getRequest().getScheme() + "://"
					+ Struts2Util.getRequest().getServerName() + port
					+ Struts2Util.getRequest().getContextPath() + "/";
			System.out
					.println("#######################Order/Quote print baseurl:"
							+ basePath);
			url = new URL(basePath + requestPath);

			if (basePath.startsWith("https://")) {
				// 创建SSLContext对象，并使用我们指定的信任管理器初始化
				TrustManager[] tm = { new MyX509TrustManager() };
				SSLContext sslContext = SSLContext
						.getInstance("SSL", "SunJSSE");

				sslContext.init(null, tm, new java.security.SecureRandom());

				// 从上述SSLContext对象中得到SSLSocketFactory对象
				SSLSocketFactory ssf = sslContext.getSocketFactory();

				HostnameVerifier hv = new HostnameVerifier()

				{

					public boolean verify(String urlHostName, SSLSession session)

					{

						System.out.println("Warning: URL Host: " + urlHostName
								+ " vs. " + session.getPeerHost());

						return true;

					}

				};

				HttpsURLConnection.setDefaultHostnameVerifier(hv);
				connection = (HttpsURLConnection) url.openConnection();
				connection.setSSLSocketFactory(ssf);
				connection.setDoOutput(true);
				connection.setDoInput(true);
				connection.setRequestMethod("POST");
				connection.setUseCaches(false);
				connection.setInstanceFollowRedirects(true);
				connection.setRequestProperty(" Content-Type ",
						" application/x-www-form-urlencoded ");
				connection.setRequestProperty("charset", "utf-8");
				connection.setRequestProperty("Cookie", "JSESSIONID="
						+ sessionid);
				connection.connect();
			} else {
				con = (HttpURLConnection) url.openConnection();
				con.setDoOutput(true);
				con.setDoInput(true);
				con.setRequestMethod("POST");
				con.setUseCaches(false);

				con.setInstanceFollowRedirects(true);
				con.setRequestProperty(" Content-Type ",
						" application/x-www-form-urlencoded ");
				con.setRequestProperty("charset", "utf-8");
				con.setRequestProperty("Cookie", "JSESSIONID=" + sessionid);
				con.connect();
			}

			int size = 0;
			byte[] buf = new byte[1024];
			BufferedInputStream bis = null;
			System.out.println("--------------" + param);
			if (con != null) {
				PrintWriter out = new PrintWriter(new OutputStreamWriter(
						con.getOutputStream(), "utf-8"));
				out.write(param);
				out.flush();
				out.close();
				bis = new BufferedInputStream(con.getInputStream());
			} else {
				PrintWriter out = new PrintWriter(new OutputStreamWriter(
						connection.getOutputStream(), "utf-8"));
				out.write(param);
				out.flush();
				out.close();
				bis = new BufferedInputStream(connection.getInputStream());
			}
			StringBuffer strb = new StringBuffer();
			while ((size = bis.read(buf)) != -1) {
				strb.append(new String(buf, 0, size));
			}
			System.out.println("strb================" + strb);
			FileWriter writer = null;
			File file = new File(tempHtmlFileName);
			if (file.isFile() && !file.getParentFile().exists()) {
				if (!file.getParentFile().mkdirs()) {
					System.out.println("create parent directory fail");
				}
			}
			writer = new FileWriter(file);
			writer.write(strb.toString());
			writer.flush();
			writer.close();

			// Document document = new Document();
			// try {
			// StyleSheet st = new StyleSheet();
			// // st.loadTagStyle("body", "leading", "16,0");
			// PdfWriter.getInstance(document, new
			// FileOutputStream(tempPdfFileName));
			// document.open();
			// // BaseFont bfChinese = BaseFont.createFont("STSong-Light",
			// "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			// // Font FontChinese = new Font(bfChinese, 12, Font.NORMAL);
			// // Paragraph t = new Paragraph(tempHtmlFileName);
			// ArrayList p = HTMLWorker.parseToList(new
			// FileReader(tempHtmlFileName), st);
			// for(int k = 0; k < p.size(); ++k) {
			// document.add((Element)p.get(k));
			// }
			// document.close();
			// }catch(Exception e) {
			// e.printStackTrace();
			// }

			String headerPath = Struts2Util.getSession().getServletContext()
					.getRealPath("html/header.html");
			String footerPath = Struts2Util.getSession().getServletContext()
					.getRealPath("html/footer.html");

			// // 根据html生成pdf
			ProcessBuilder pb = new ProcessBuilder(processName, "-L", "10",
					"-R", "10", "-T", "30", "-B", "50", "--footer-html",
					footerPath, "--footer-spacing", "30", "--header-html",
					headerPath, "--header-spacing", "10", tempHtmlFileName,
					tempPdfFileName);
			pb.redirectErrorStream(true);

			process = pb.start();
			InputStreamReader ir = new InputStreamReader(
					process.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			String line = null;
			while ((line = input.readLine()) != null) {// 这句必须有
				System.out.println(line);
			}
			bis.close();
			if (con != null) {
				con.disconnect();
			} else {
				connection.disconnect();
			}
			file.delete();// 删除html文件
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);

		} finally {
			if (process != null) {
				process.destroy();
			}
		}
		return tempPdfFileName;
	}

	/**
	 * 获得用于生成Pdf的程序名称, windows系统和linux系统不一样
	 * 
	 * @author wangsf
	 * @serialData 2010-12-24
	 * @return
	 */
	public static String getPdfProcessName() {
		StringBuilder sb = new StringBuilder();
		String separator = System.getProperty("file.separator");
		if (separator.equals("\\")) {// windows

			// cmd = "C:/wkhtmltopdf/wkhtmltopdf.exe";
			sb.append("C:/wkhtmltopdf/wkhtmltopdf.exe");

		} else {// linux etc.
			sb.append("html2pdf");
		}
		// sb.append(" -L 10 -R 10 -T 20 -B 50  --footer-html ").append(footerPath).append(" --footer-spacing 30 --header-html ").append(headerPath).append(" --header-spacing 0");
		return sb.toString();
	}

	// /-------------add by zhou gang -------------
	public static String getPdfProcessName2() {
		String cmd = "html2pdf";
		String separator = System.getProperty("file.separator");
		if (separator.equals("\\")) {// windows
			// cmd = "c:/wkhtmltopdf/wkhtmltopdf.exe";
			cmd = "d:/wkhtmltopdf/wkhtmltopdf/wkhtmltopdf.exe";
		} else {// linux etc.

		}
		return cmd;
	}

	// =====================================================end ========
	/**
	 * 获得生成html和pdf临时文件的前缀， 不包含.pdf, .html部分, windows系统和linux系统不一样
	 * 
	 * @author wangsf
	 * @serialData 2010-12-24
	 * @return
	 * @throws Exception
	 */
	public static String getTempFileShortName() throws Exception {
		String random = SessionUtil.generateTempId();
		String shortName = "/tmp/genpdf_" + random;
		String separator = System.getProperty("file.separator");
		if (separator.equals("\\")) {// windows
			shortName = "C:" + shortName;
		} else {// linux etc.

		}
		return shortName;
	}

	public static void main(String[] args) {
		if (args != null && args.length > 0) {
			logger.debug("generic pdf starting...!");
			Date start = new Date();
			PdfUtils pdfUtils = new PdfUtils();
			initArgs(args, pdfUtils);
			pdfUtils.initSame(pdfUtils.readerURL);
			try {
				pdfUtils.extraPdf(getFileInfo(), pdfUtils.waterURL,
						pdfUtils.headURL, pdfUtils.footURL);
				// 计算消耗的时间.
				Date end = new Date();
				logger.info("extra time(ms): "
						+ (end.getTime() - start.getTime())
						+ " -- extra pdf: '" + pdfUtils.readerURL
						+ "' successful!");

			} catch (Exception e) {
				logger.error("Extra pdf error: " + pdfUtils.tagURL);
				e.printStackTrace();
			}
		}
	}

	private static void initArgs(String[] args, PdfUtils pdfUtils) {
		HashMap<String, String> moreInfo = new HashMap<String, String>();
		for (int i = 0; i < args.length; i++) {
			String[] keyValue = args[i].split("=");
			moreInfo.put(keyValue[0], keyValue[1]);
		}
		pdfUtils.readerURL = getMapValue(moreInfo, "PDF_URL");
		pdfUtils.waterURL = getMapValue(moreInfo, "WATER_URL");
		pdfUtils.headURL = getMapValue(moreInfo, "HEAD_URL");
		pdfUtils.footURL = getMapValue(moreInfo, "FOOT_URL");
	}

	public static String getMapValue(HashMap<String, String> moreInfo,
			String key) {
		String value = null;
		if (moreInfo.get(key) != null) {
			value = ((String) moreInfo.get(key)).trim();
		}
		return value;
	}

}
