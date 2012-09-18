package com.genscript.gsscm.systemsetting.web;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.basedata.entity.PbDropdownListOptions;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.FileService;
import com.genscript.gsscm.common.constant.DropDownListName;
import com.genscript.gsscm.common.constant.FilePathConstant;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.StringUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.serv.entity.ServiceClassification;
import com.genscript.gsscm.serv.entity.ServiceDocTemplate;
import com.genscript.gsscm.serv.entity.ServiceDocuments;
import com.genscript.gsscm.systemsetting.service.DocumentTemplatesService;
import com.genscript.gsscm.ws.WSException;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @Description:文档模板管理控制类
 * @Copyright: Copyright genscrpit (c)2010
 * @author: lizhang
 * @version: 1.0
 * @date:   2010-12-1
 * 
 * Modification History:
 * Date			Author			Version			Description
 * -------------------------------------------------------------
 *
 */
@Results({
	@Result(name = "document_temp_list", location = "systemsetting/document_temp_list.jsp"),
	@Result(name = "document_temp_result", location = "systemsetting/document_temp_result.jsp"),
	@Result(name = "saveSuccess", location="list",type="chain"),
	@Result(name = "document_temp_detail", location = "systemsetting/document_temp_detail.jsp")
})
public class DocumentTemplatesAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	/**自动装载实例**/
	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired
	private DocumentTemplatesService documentTemplatesService;
	@Autowired
	private PublicService publicService;
	@Autowired
	private FileService fileService;
	/**action变量**/
	private Page<ServiceDocTemplate> serviceDocTemplatePage;
	private List<ServiceClassification> serviceClassificationList;//service category
	private Map<String, List<PbDropdownListOptions>> dropDownMap;//work function
	private ServiceDocTemplate serviceDocTemplate;
	private Integer templateId;
	private String docId;//附件ID
	private String allChoiceVal;//所有被选中的
	
	/**上传文件属性**/
	private File doc;
	private String fileDir;
	private String docFileName;
	private String docContentType;
	
	
	/*****************************action方法**************************************/
	/**
	 * 查询页
	 */
	public String list() {
		this.dropDownList();
		return "document_temp_list";
	}
	/**
	 * 列表页
	 * @return
	 */
	public String result() {
		serviceDocTemplatePage = this.documentTemplatesService.searchServiceDocTemplatePage(serviceDocTemplatePage);
		ServletActionContext.getRequest().setAttribute("pagerInfo",
				serviceDocTemplatePage);
		return "document_temp_result";
	}
	
	public String load() {
		this.dropDownList();
		if(templateId!=null) {
			/**修改**/
			serviceDocTemplate = this.documentTemplatesService.findById(templateId);
			if(serviceDocTemplate!=null&&serviceDocTemplate.getDocId()!=null) {
				ServiceDocuments serviceDocuments = this.documentTemplatesService.findServiceDocuments(String.valueOf(serviceDocTemplate.getDocId()));
				if(serviceDocuments!=null) {
					serviceDocTemplate.setDocName(serviceDocuments.getDocName());
				}
			}
		}
		if(serviceDocTemplate==null) {
			/**添加**/
			serviceDocTemplate = new ServiceDocTemplate();
			serviceDocTemplate.setCreatedBy(SessionUtil.getUserId());
		}
		serviceDocTemplate.setModifyDate(new Date());
		serviceDocTemplate.setModifiedBy(SessionUtil.getUserId());
		serviceDocTemplate.setModifyName(SessionUtil.getUserName());
		return "document_temp_detail";
	}
	public String save() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			this.documentTemplatesService.saveServiceDocTemplate(serviceDocTemplate);
			rt.put("message", "save success!");
		}  catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			rt.put("hasException", "Y");
			rt.put("exception", exDTO);
		}
		Struts2Util.renderJson(rt);
		return null;
	}
	public String delete() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			this.documentTemplatesService.deleteServiceDocTemplate(allChoiceVal);
			rt.put("message", "delete success!");
		}  catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			rt.put("hasException", "Y");
			rt.put("exception", exDTO);
		}
		Struts2Util.renderJson(rt);
		return null;
	}
	
	/**
	 * 附件上传
	 * @throws Exception 
	 */
	public String upload() throws Exception {
		try {
			String fileName = SessionUtil.generateTempId()+"."+this.fileService.getExtension(docFileName);
			this.fileService.uploadFile(doc, docContentType, fileName,FilePathConstant.document_template.value());
			ServiceDocuments serviceDocuments =  this.documentTemplatesService.saveServiceDocuments(doc, docFileName,fileName);
			docId = serviceDocuments!=null?String.valueOf(serviceDocuments.getDocId()):null;
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
		}
//		Struts2Util.renderJson(rt);
		return tohtml(docId,docFileName);
	}
	
	/**
	 * 附件删除
	 */
	public String deleteFile() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			this.documentTemplatesService.deleteServiceDocuments(docId,this.fileService.getUploadPath());
			rt.put("message", "delete file success!");
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			rt.put("hasException", "Y");
			rt.put("exception", exDTO);
		}
		Struts2Util.renderJson(rt);
		return null;
	}
	
	/*************************private方法*****************************************/
	/**
	 * 给页面下拉列表框赋值
	 */
	private void dropDownList() {
		serviceClassificationList = this.documentTemplatesService.findAllSC();
		List<DropDownListName> listName = new ArrayList<DropDownListName>();
		listName.add(DropDownListName.TEMPLATE_WORK_FUNCTION);
		dropDownMap = publicService.getDropDownMap(listName);
	}
	
	protected String tohtml(String docId ,String fileName ) {
		HttpServletResponse r = ServletActionContext.getResponse();
		r.setHeader("Cache-Control", "no-cache, must-revalidate");
		r.setHeader("Pragma", "no-cache");
		r.setContentType("text/html;charset=utf-8");
		try{
		r.getOutputStream().write(docId.getBytes("utf-8"));
		r.getOutputStream().write("@".getBytes());
		r.getOutputStream().write(fileName.getBytes("utf-8"));
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/*************************getter setter****************************************/
	public Page<ServiceDocTemplate> getServiceDocTemplatePage() {
		return serviceDocTemplatePage;
	}
	public void setServiceDocTemplatePage(
			Page<ServiceDocTemplate> serviceDocTemplatePage) {
		this.serviceDocTemplatePage = serviceDocTemplatePage;
	}
	public List<ServiceClassification> getServiceClassificationList() {
		return serviceClassificationList;
	}
	public void setServiceClassificationList(
			List<ServiceClassification> serviceClassificationList) {
		this.serviceClassificationList = serviceClassificationList;
	}
	
	public Map<String, List<PbDropdownListOptions>> getDropDownMap() {
		return dropDownMap;
	}

	public void setDropDownMap(
			Map<String, List<PbDropdownListOptions>> dropDownMap) {
		this.dropDownMap = dropDownMap;
	}
	public ServiceDocTemplate getServiceDocTemplate() {
		return serviceDocTemplate;
	}
	public void setServiceDocTemplate(ServiceDocTemplate serviceDocTemplate) {
		this.serviceDocTemplate = serviceDocTemplate;
	}
	public Integer getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}
	public File getDoc() {
		return doc;
	}
	public void setDoc(File doc) {
		this.doc = doc;
	}
	public String getFileDir() {
		return fileDir;
	}
	public void setFileDir(String fileDir) {
		this.fileDir = fileDir;
	}
	public String getDocFileName() {
		return docFileName;
	}
	public void setDocFileName(String docFileName) {
		this.docFileName = docFileName;
	}
	public String getDocContentType() {
		return docContentType;
	}
	public void setDocContentType(String docContentType) {
		this.docContentType = docContentType;
	}
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	
	public String getAllChoiceVal() {
		return allChoiceVal;
	}
	public void setAllChoiceVal(String allChoiceVal) {
		this.allChoiceVal = allChoiceVal;
	}

	

}
