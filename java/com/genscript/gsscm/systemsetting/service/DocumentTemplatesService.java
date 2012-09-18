package com.genscript.gsscm.systemsetting.service;
import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.common.constant.FilePathConstant;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.serv.dao.ServiceClassificationDao;
import com.genscript.gsscm.serv.dao.ServiceDocTemplateDao;
import com.genscript.gsscm.serv.dao.ServiceDocumentsDao;
import com.genscript.gsscm.serv.entity.ServiceClassification;
import com.genscript.gsscm.serv.entity.ServiceDocTemplate;
import com.genscript.gsscm.serv.entity.ServiceDocuments;
/**
 * 
 * @Description:文档模板管理业务类
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
@Service
@Transactional
public class DocumentTemplatesService {
	@Autowired
	private ServiceDocTemplateDao serviceDocTemplateDao;
	@Autowired
	private ServiceClassificationDao serviceClassificationDao;
	@Autowired
	private ServiceDocumentsDao serviceDocumentsDao;
	/**
	 * 根据条件查找ServiceDocTemplate并分页显示
	 * @param serviceDocTemplatePage 分页对象
	 * @return
	 */
	public Page<ServiceDocTemplate> searchServiceDocTemplatePage(Page<ServiceDocTemplate> serviceDocTemplatePage) {
		// 获得分页请求相关数据：如第几页
		PagerUtil<ServiceDocTemplate> pagerUtil = new PagerUtil<ServiceDocTemplate>();
		serviceDocTemplatePage = pagerUtil.getRequestPage();
		// 设置默认排序
		if (!serviceDocTemplatePage.isOrderBySetted()) {
			serviceDocTemplatePage.setOrderBy("id");
			serviceDocTemplatePage.setOrder(Page.DESC);
		}
		// 设置默认每页显示记录条数
		serviceDocTemplatePage.setPageSize(15);
		// 获得查询条件
		List<PropertyFilter> filters = WebUtil
				.buildPropertyFilters(ServletActionContext.getRequest());
		if (filters == null || filters.isEmpty()) {// 没有搜索条件
			serviceDocTemplatePage = this.serviceDocTemplateDao.getAll(serviceDocTemplatePage);
		} else {
			serviceDocTemplatePage = this.serviceDocTemplateDao.findPageByFilter(serviceDocTemplatePage, filters);
		}
		if(serviceDocTemplatePage.getResult()!=null) {
			for(ServiceDocTemplate serviceDocTemplate:serviceDocTemplatePage.getResult()) {
				/**添加service category属性值**/
				Integer serviceClsId = serviceDocTemplate.getServiceClsId();
				ServiceClassification serviceClassification = this.serviceClassificationDao.getById(serviceClsId);
				if(serviceClassification!=null) {
					serviceDocTemplate.setServiceCategory(serviceClassification.getName());
				}
				/**添加docName属性值**/
				Integer docId = serviceDocTemplate.getDocId();
				if(docId!=null) {
					ServiceDocuments serviceDocuments = this.serviceDocumentsDao.getById(docId);
					if(serviceDocuments!=null) {
						serviceDocTemplate.setDocName(serviceDocuments.getDocName());
						serviceDocTemplate.setFilePath(serviceDocuments.getFilePath());
					}
				}
				
			}
		}
		return serviceDocTemplatePage;
	}
	/**
	 * 保存serviceDocTemplate
	 * @param serviceDocTemplate
	 * @throws Exception
	 */
	public void saveServiceDocTemplate(ServiceDocTemplate serviceDocTemplate) throws Exception{
		if(serviceDocTemplate.getTemplateId()==null) {
			serviceDocTemplate.setCreatedBy(SessionUtil.getUserId());
		}
		this.serviceDocTemplateDao.save(serviceDocTemplate);
	}
	
	/**
	 * 根据id删除serviceDocTemplate
	 * @param allChoiceVal 所有被选中的template
	 */
	public void deleteServiceDocTemplate(String allChoiceVal) throws Exception{
		
		if(allChoiceVal!=null) {
			for(String templateId:allChoiceVal.split("-")) {
				this.serviceDocTemplateDao.delete(Integer.parseInt(templateId));
			}
		}
	}
	
	/**
	 * 根据id查找serviceDocTemplate对象
	 * @param templateId 主键
	 */
	public ServiceDocTemplate findById(Integer templateId) {
		return this.serviceDocTemplateDao.get(templateId);
	}
	
	/**
	 * 查询所有serviceClassification对象
	 */
	public List<ServiceClassification> findAllSC() {
		return this.serviceClassificationDao.findAll();
	}
	
	/**
	 * 根据docId查找serviceDocuments对象
	 */
	public ServiceDocuments findServiceDocuments(String docId) {
		return this.serviceDocumentsDao.getById(Integer.parseInt(docId));
	}
	
	/**
	 * 删除serviceDocuments对象以及文件
	 * @param docId 附件Id
	 * @param rootPath 附件所在的根目录
	 */
	public void deleteServiceDocuments(String docId,String rootPath) throws Exception{
		ServiceDocuments serviceDocuments = this.findServiceDocuments(docId);
		if(serviceDocuments!=null) {
			File file = new File(rootPath+serviceDocuments.getFilePath());
			if(file.exists()) {
				file.delete();
			}
			this.serviceDocumentsDao.delete(serviceDocuments);
		}
	}
	
	/**
	 * 保存serviceDocuments对象
	 * @author lizhang
	 * @param doc 文件
	 * @param docFileName 文件名
	 * @param newFileName 新文件名
	 */
	public ServiceDocuments saveServiceDocuments(File doc,String docFileName,String newFileName) {
		ServiceDocuments serviceDocuments = new ServiceDocuments();
		serviceDocuments.setCreatedBy(SessionUtil.getUserId());
		serviceDocuments.setModifiedBy(SessionUtil.getUserId());
		serviceDocuments.setModifyDate(new Date());
		serviceDocuments.setDocName(docFileName);
		serviceDocuments.setFileType(docFileName.substring(docFileName.lastIndexOf(".") + 1));
		serviceDocuments.setDocType("document template");
		serviceDocuments.setFilePath(FilePathConstant.document_template.value()+"/"+newFileName);
		this.serviceDocumentsDao.save(serviceDocuments);
		return serviceDocuments;
	}

}
