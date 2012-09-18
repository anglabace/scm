package com.genscript.gsscm.manufacture.web;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.entity.PbDropdownListOptions;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.FileService;
import com.genscript.gsscm.common.constant.DsPlateStatusType;
import com.genscript.gsscm.common.constant.FilePathConstant;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.manufacture.entity.DsPlateItems;
import com.genscript.gsscm.manufacture.entity.DsPlates;
import com.genscript.gsscm.manufacture.entity.ManuDocument;
import com.genscript.gsscm.manufacture.service.DsPlateService;
import com.genscript.gsscm.ws.WSException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 
 * @Description:DNA Sequencing Plate manage
 * @Copyright: Copyright genscrpit (c)2010
 * @author: lizhang
 * @version: 1.0
 * @date:   2011-12-13
 * 
 * Modification History:
 * Date			Author			Version			Description
 * -------------------------------------------------------------
 *
 */
@Results( {
	@Result(name = "plate_search", location = "manufacture/plate_search.jsp"),
	@Result(name = "plate_list", location = "manufacture/plate_list.jsp"),
	@Result(name = "plate_edit", location = "manufacture/plate_items.jsp"),
	@Result(name = "data_analyis_item_list", location = "manufacture/data_analyis_item_list.jsp"),
	@Result(name = "production_reports", location = "manufacture/production_reports.jsp"),
	@Result(name = "plate_edit", location = "manufacture/plate_items.jsp"),
	@Result(name="execl_plateMap_list",location="manufacture/execl_plateMap_list.jsp"),
	@Result(name="execl_seqyencerFile_list",location="manufacture/execl_seqyencerFile_list.jsp")
})
public class DsPlateAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = -87462131389324227L;
	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired
	private PublicService publicService;
	@Autowired
	private DsPlateService dsPlateService;
	private Integer id;
	private String plateItemId;
	private DsPlates dsPlates;
	private DsPlateItems dsPlateItems;
	private Page<DsPlates> dsPlatesPage;
	private List<DsPlateItems> dsPlateItemsList;
	private Map<String,DsPlateItems> dsPlateItemsMap;
	private List<PbDropdownListOptions> plateStausList;
	private String concerntrationValues;
	private String dsPlateItemsIds;
	private String controlValue;
	private String status;
	private String hasConcentrationItem;
	
	// 上传附件相关
	@Autowired
	private FileService fileService;
	private List<File> upload;
	private List<String> uploadContentType;
	private List<String> uploadFileName;
	private String refType;
	private String delFilePath;
	private String filePath;
	private String fileName;
	
	/**
	 * 显示plate列表页面
	 * @author lizhang
	 * @return
	 */
	public String plateSearch() {
		this.plateStausList = this.publicService.getDropdownList("PLATE_STATUS");
		//清空保存在session中关于plate的数据
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.remove(SessionConstant.DsPlateItemsList.value());
		return "plate_search";
	}
	
	/**
	 * 获取plate列表并分页
	 * @author lizhang
	 * @return
	 */
	public String plateList() {
		try {
			// 获得分页请求相关数据：如第几页
			PagerUtil<DsPlates> pagerUtil = new PagerUtil<DsPlates>();
			dsPlatesPage = pagerUtil.getRequestPage();
			// 设置默认排序
			if (!dsPlatesPage.isOrderBySetted()) {
				dsPlatesPage.setOrderBy("name");
				dsPlatesPage.setOrder(Page.ASC);
			}
			// 设置默认每页显示记录条数
			dsPlatesPage.setPageSize(20);
			// 获得查询条件
			List<PropertyFilter> filters = WebUtil
					.buildPropertyFilters(ServletActionContext.getRequest());
			dsPlatesPage = dsPlateService.searchDsPlates(dsPlatesPage,filters);
			ServletActionContext.getRequest().setAttribute("pagerInfo",
					dsPlatesPage);
		} catch (Exception ex) {
			return "plate_list";
		}
		return "plate_list";
	}
	
	/**
	 * 显示plate详细页面
	 * @author lizhang
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String plateEdit() {
		dsPlates = dsPlateService.findById(id);
		if(dsPlates!=null) {
			Map<String,Object> map = dsPlateService.findDsPlateItemsByPlateNo(dsPlates.getPlateNo());
			dsPlateItemsList =  (LinkedList<DsPlateItems>)map.get("dsPlateItemsList");
			hasConcentrationItem = (String)map.get("hasConcentrationItem");
		}
		if(dsPlateItemsList==null) {
			dsPlateItemsList = new ArrayList<DsPlateItems>();
		}
		int nullWell = 96-dsPlateItemsList.size();
		if(nullWell!=0) {
			for(int i =0;i<nullWell;i++) {
				DsPlateItems dsPlateItems = new DsPlateItems();
				dsPlateItemsList.add(dsPlateItems);
			}
		}
		SessionUtil.insertRow(SessionConstant.DsPlateItemsList.value(), String.valueOf(id), dsPlateItemsList);   
		return "plate_edit";
	}
	
	/**
	 * fangquan
	 * Download Plate Map
	 * 生成execl
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public String plateMap() throws Exception{
		dsPlateItemsList = (List<DsPlateItems>)SessionUtil.getRow(SessionConstant.DsPlateItemsList.value(), String.valueOf(id));  
		String plateNo=Struts2Util.getParameter("plateNo");
		ServletActionContext.getRequest().setAttribute("dsPlateItemsList", dsPlateItemsList);
		ServletActionContext.getRequest().setAttribute("plateNo", plateNo);
		return "execl_plateMap_list";
	}
	
	/**
	 * fangquan
	 * Download Sequencer File Names
	 * 生成execl
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public String seqyencerFile(){
		dsPlateItemsList = (List<DsPlateItems>)SessionUtil.getRow(SessionConstant.DsPlateItemsList.value(), String.valueOf(id));  
		String plateNo=Struts2Util.getParameter("plateNo");
		ServletActionContext.getRequest().setAttribute("dsPlateItemsList", dsPlateItemsList);
		ServletActionContext.getRequest().setAttribute("plateNo", plateNo);
		return "execl_seqyencerFile_list";
	}
	
	/**
	 * 更新plate的status
	 * @author lizhang
	 * @return
	 */
	public String updatePlateStatus() {
		Map<String,Object> rt = new HashMap<String, Object>();
		try {
			dsPlates = this.dsPlateService.findById(id);
			dsPlates.setStatus(status);
		    if(DsPlateStatusType.Concentration.value().equals(status)) {
				dsPlateService.updateConcentration(dsPlateItemsIds,concerntrationValues);
			} else if(DsPlateStatusType.Sequencing.value().equals(status)) {
				dsPlates.setControlValue(controlValue!=null?Integer.parseInt(controlValue):null);
			}
		    dsPlateService.updateDsPlatesStatus(dsPlates, hasConcentrationItem,concerntrationValues);
		} catch(Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			rt.put("hasException", "Y");
			rt.put("exception", exDTO);
		}
		rt.put("message", "Success");
		Struts2Util.renderJson(rt);
		return null;
	}
	
	/**
	 * 显示data Analysis页面
	 * @author lizhang
	 * @return
	 */
	public String dataAnalysis() {
		SessionUtil.deleteRow(SessionConstant.DataAnalysisMap.value(), String.valueOf(id));
		return "production_reports";
	}
	
	/**
	 * 获取需要data analysis的dsPlateItems
	 * @author lizhang
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String dataAnalyisList() {
		dsPlateItemsMap = (Map<String,DsPlateItems>)SessionUtil.getRow(SessionConstant.DataAnalysisMap.value(), String.valueOf(id));
		dsPlates = dsPlateService.findById(id);
		if(dsPlates!=null&&dsPlateItemsMap==null) {
			dsPlateItemsMap = SessionUtil.convertList2Map(dsPlateService.findDataAnlyisDsPlateItemsByPlateNo(dsPlates.getPlateNo()), "id");
		}
		if(dsPlateItemsMap==null) {
			dsPlateItemsMap = new HashMap<String,DsPlateItems>();
		}
		SessionUtil.updateRow(SessionConstant.DataAnalysisMap.value(), String.valueOf(id), dsPlateItemsMap);
		return "data_analyis_item_list";
	}
	
	/**
	 * 显示需要data analysis的dsPlateItems上传的文件
	 * @author lizhang
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String plateItemEdit() {
		dsPlateItemsMap = (Map<String,DsPlateItems>)SessionUtil.getRow(SessionConstant.DataAnalysisMap.value(), String.valueOf(id));
		if(dsPlateItemsMap!=null) {
			dsPlateItems = dsPlateItemsMap.get(plateItemId);
		}
		Map<String,ManuDocument> map = new HashMap<String,ManuDocument>();
		map.putAll(dsPlateItems.getAlignmentFileMap());
		map.putAll(dsPlateItems.getQvDataMap());
		map.putAll(dsPlateItems.getTraceFileMap());
		map.putAll(dsPlateItems.getSequenceFileMap());
		String html = Struts2Util.conventJavaObjToJson(map);
		Struts2Util.renderHtml(html);
		return null;
	}
	
	/**
	 * 上传文件
	 * @author lizhang
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String uploadFile() {
		try {
			dsPlateItemsMap = (Map<String,DsPlateItems>)SessionUtil.getRow(SessionConstant.DataAnalysisMap.value(), String.valueOf(id));
			if(dsPlateItemsMap==null||dsPlateItemsMap.size()==0) {
				return null;
			}
			// 这次请求上传的文件， 要返回文件信息给客户端.
			Map<String, ManuDocument> uploadMap = new LinkedHashMap<String, ManuDocument>();
			String[] dsPlateItemIdArray = dsPlateItemsIds.split(",");
			if(dsPlateItemIdArray==null||dsPlateItemIdArray.length==0) {
				return null;
			}
			String docType = Struts2Util.getParameter("docType");
			// 目前页面上只能一次上传一个文件.
			for (int i = 0; i < upload.size(); i++) {
				String srcFileName = uploadFileName.get(i);
				ManuDocument doc = new ManuDocument();
				doc.setDocName(srcFileName);
				uploadFileName.set(i, SessionUtil.generateTempId() + "_"
						+ srcFileName);
				doc.setFilePath(FilePathConstant.Manu_WorkOrder.value() + "/"
						+ uploadFileName.get(i));
				doc.setDocType(docType);
				doc.setModifyUser(SessionUtil.getUserName());
				String tempId = SessionUtil.generateTempId();
				uploadMap.put(tempId, doc);
				for(String dsPlateItemId : dsPlateItemIdArray) {
					DsPlateItems dsPlateItems = dsPlateItemsMap.get(dsPlateItemId);
					if(docType.equals("Sequencing File")) {
						dsPlateItems.getSequenceFileMap().put(tempId,doc);
					} else if(docType.equals("Trace File")) {
						dsPlateItems.getTraceFileMap().put(tempId,doc);
					} else if(docType.equals("QV Data")) {
						dsPlateItems.getQvDataMap().put(tempId,doc);
					} else if(docType.equals("Alignment File")) {
						dsPlateItems.getAlignmentFileMap().put(tempId,doc);
					}
				}
			}
			fileService.uploadFile(upload, uploadContentType, uploadFileName,
					FilePathConstant.Manu_WorkOrder.value());
			String html = Struts2Util.conventJavaObjToJson(uploadMap);
			Struts2Util.renderHtml(html);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 删除文件
	 * @author lizhang
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String deleteFile() {
		dsPlateItemsMap = (Map<String,DsPlateItems>)SessionUtil.getRow(SessionConstant.DataAnalysisMap.value(), String.valueOf(id));
		if(dsPlateItemsMap==null||dsPlateItemsMap.size()==0) {
			return null;
		}
		DsPlateItems dsPlateItems = dsPlateItemsMap.get(plateItemId);
		String docIds = Struts2Util.getParameter("docIds");
		String docTypes = Struts2Util.getParameter("docTypes");
		String[] docIdArray = docIds.split(",");
		String[] docTypeArray = docTypes.split(",");
		for (int i=0;i<docIdArray.length;i++) {
			if (StringUtils.isNumeric(docIdArray[i])) {
				if(docTypeArray[i].equals("Sequencing File")) {
					dsPlateItems.getSequenceFileMap().put(docIdArray[i], null);
				} else if(docTypeArray[i].equals("Trace File")) {
					dsPlateItems.getTraceFileMap().put(docIdArray[i], null);
				} else if(docTypeArray[i].equals("QV Data")) {
					dsPlateItems.getQvDataMap().put(docIdArray[i], null);
				} else if(docTypeArray[i].equals("Alignment File")) {
					dsPlateItems.getAlignmentFileMap().put(docIdArray[i], null);
				}
			} else {
				if(docTypeArray[i].equals("Sequencing File")) {
					dsPlateItems.getSequenceFileMap().remove(docIdArray[i]);
				} else if(docTypeArray[i].equals("Trace File")) {
					dsPlateItems.getTraceFileMap().remove(docIdArray[i]);
				} else if(docTypeArray[i].equals("QV Data")) {
					dsPlateItems.getQvDataMap().remove(docIdArray[i]);
				} else if(docTypeArray[i].equals("Alignment File")) {
					dsPlateItems.getAlignmentFileMap().remove(docIdArray[i]);
				}
			}
		}
		Struts2Util.renderText("Success");
		return null;
	}
	
	/**
	 * 保存data analysis
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String saveUploadData() {
		Map<String,Object> rt = new HashMap<String,Object>();
		try {
			dsPlateItemsMap = (Map<String,DsPlateItems>)SessionUtil.getRow(SessionConstant.DataAnalysisMap.value(), String.valueOf(id));
			if(dsPlateItemsMap==null||dsPlateItemsMap.size()==0) {
				return null;
			}
			this.dsPlateService.saveUploadData(dsPlateItemsMap);
			SessionUtil.deleteRow(SessionConstant.DataAnalysisMap.value(), String.valueOf(id));
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		rt.put("message", "Success.");
		Struts2Util.renderJson(rt);
		return null;
	}
	
	/***************************************getter(),setter()*****************************************************************/

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DsPlates getDsPlates() {
		return dsPlates;
	}

	public void setDsPlates(DsPlates dsPlates) {
		this.dsPlates = dsPlates;
	}

	public Page<DsPlates> getDsPlatesPage() {
		return dsPlatesPage;
	}

	public void setDsPlatesPage(Page<DsPlates> dsPlatesPage) {
		this.dsPlatesPage = dsPlatesPage;
	}

	public List<DsPlateItems> getDsPlateItemsList() {
		return dsPlateItemsList;
	}

	public void setDsPlateItemsList(List<DsPlateItems> dsPlateItemsList) {
		this.dsPlateItemsList = dsPlateItemsList;
	}

	public String getConcerntrationValues() {
		return concerntrationValues;
	}

	public void setConcerntrationValues(String concerntrationValues) {
		this.concerntrationValues = concerntrationValues;
	}
	

	public String getDsPlateItemsIds() {
		return dsPlateItemsIds;
	}

	public void setDsPlateItemsIds(String dsPlateItemsIds) {
		this.dsPlateItemsIds = dsPlateItemsIds;
	}

	public String getControlValue() {
		return controlValue;
	}

	public void setControlValue(String controlValue) {
		this.controlValue = controlValue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getHasConcentrationItem() {
		return hasConcentrationItem;
	}

	public void setHasConcentrationItem(String hasConcentrationItem) {
		this.hasConcentrationItem = hasConcentrationItem;
	}

	public List<File> getUpload() {
		return upload;
	}

	public void setUpload(List<File> upload) {
		this.upload = upload;
	}

	public List<String> getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(List<String> uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public List<String> getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(List<String> uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getRefType() {
		return refType;
	}

	public void setRefType(String refType) {
		this.refType = refType;
	}

	public String getDelFilePath() {
		return delFilePath;
	}

	public void setDelFilePath(String delFilePath) {
		this.delFilePath = delFilePath;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Map<String, DsPlateItems> getDsPlateItemsMap() {
		return dsPlateItemsMap;
	}
	
	public void setDsPlateItemsMap(Map<String, DsPlateItems> dsPlateItemsMap) {
		this.dsPlateItemsMap = dsPlateItemsMap;
	}

	public String getPlateItemId() {
		return plateItemId;
	}

	public void setPlateItemId(String plateItemId) {
		this.plateItemId = plateItemId;
	}

	public DsPlateItems getDsPlateItems() {
		return dsPlateItems;
	}

	public void setDsPlateItems(DsPlateItems dsPlateItems) {
		this.dsPlateItems = dsPlateItems;
	}

	public List<PbDropdownListOptions> getPlateStausList() {
		return plateStausList;
	}

	public void setPlateStausList(List<PbDropdownListOptions> plateStausList) {
		this.plateStausList = plateStausList;
	}



}
