package com.genscript.gsscm.product.web;
/*
 *2010-8-10 13:32:42
 *by mingrs
*/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.dto.DropDownDTO;
import com.genscript.gsscm.basedata.entity.PbDropdownListOptions;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.constant.RequestApproveType;
import com.genscript.gsscm.common.constant.SessionPdtServ;
import com.genscript.gsscm.common.constant.SpecDropDownListName;
import com.genscript.gsscm.common.util.OrderLockRelease;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.privilege.web.LoginAction;
import com.genscript.gsscm.product.entity.Catalog;
import com.genscript.gsscm.product.entity.ProductCategory;
import com.genscript.gsscm.product.service.ProductService;
import com.genscript.gsscm.productservice.dto.CatalogDTO;
import com.genscript.gsscm.serv.entity.ServiceCategory;
import com.genscript.gsscm.ws.WSException;

@Results( {
	@Result(name = LoginAction.SUCCESS, location = "product/catalogSrch.jsp"),
	@Result(name = "catlogCopyNew", location = "product/catalog_copyNew.jsp"),
	@Result(name = "catalogCreationForm", location = "product/catalog_creetion_from.jsp"),
	@Result(name = "catalogApprove", location = "product/manager_creetion/catalog_creetion_from.jsp")})
public class CatalogAction extends BaseAction<CatalogDTO> {

	private static final long serialVersionUID = -8121497063553807193L;
	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired
	private ProductService productService;
	@Autowired
	private PublicService publicService;
	
	/*
	 * 页面数据；
	 */
	private CatalogDTO entity;
	private CatalogDTO catalogDTO;
	private Page<Catalog> page;
	private Integer id;
	private String sessionCatalogId;
	private String callBackName;
	private List<PbDropdownListOptions> catalogDropdownList;
	private List<DropDownDTO> currencyDropdownList;
	private String categoryId;
	private String ctgAstGrp;
	private String ctgDescription;
	private String pdtCtgDel;
	private String servCategoryId;
	private String servCtgAstGrp;
	private String servCtgDescription;
	private String servCtgDel;
	private String defaultTab;
	
	private Integer requestId;
	private String approvedName;
	private String approvedStatus;
	private String approved;
	private String approvedType;
	private String approvedReason;
	private Boolean nameAppr;
	private Boolean statusAppr;
	private String approvedStatusList;//del product 批量修改status 为INACTIVE
	private List<Integer> statusOfapproved;//
	
	//获取从approved 列表页面点来的请求
	private String approvedMethod;
	//获取从非Catalog列表页面点过来的请求--Zhang Yong
	private String operation_method;
	
	//-- ModelDriven 与 Preparable函数 --//
	public void setId(Integer id) {
		/**
		 * id是prepareModel()二次绑定的参数;
		 */
		this.id = id;
	}
	
	public void setCallBackName(String callBackName){
		/**
		 * callBackName 是解决相同的action请求，不同的return;
		 * 在本对象内为inputAction请求，return 有catlogCopyNew与catalogCreationForm;
		 */
		this.callBackName=callBackName;
	}
	
	public CatalogDTO getModel() {
		/**
		 * 向跳转页面绑定catalogDTO类型的输出数据。
		 */
		return entity;
	}
	
	@Override
	protected void prepareModel() throws Exception {
		/**
		 * 对请求进行二次绑定，获取对应的catalogDTO数据;
		 */
		if (id != null) {
			entity = this.productService.getCatalogDetail(id);
		} else {
			entity = new CatalogDTO();
		}
	}
	
	//-- CRUD Action 函数 --//
	
	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * @param delStr 
	 * 使用Struts2Util.getParameter()获取delStr;
	 * delStr 是“<;>”分割的String类型；
	 * @return 
	 * 为AJAX请求，所以return 为null;
	 * 使用Struts2Util.renderText()方法返回给AJAX信息。
	 */
	public String delCatalogList(){
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			String dataStr = ServletActionContext.getRequest().getParameter("delStr");
			List<String> idList = Arrays.asList(dataStr.split("<;>"));
			this.productService.delCatalogListStr(idList);
			rt.put("message", SUCCESS);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0203",
					SessionUtil.getUserId());
			rt.put("hasException", "Y");
			rt.put("exception", exDTO);			
		}
		Struts2Util.renderJson(rt);
		return null;
	}
	

	
	/**
	 * 
	 * @param
	 * 新建和修改catalog时通过ID把数据读取出来。
	 * CatalogDTO 由prepareModel()进行获取。
	 * sessionCatalogId 用于session save 使用，当ID为空时获取随机数，否则获取该catalog id;
	 * @retun
	 * 
	 */
	@Override
	public String input() throws Exception {
		if(this.id==null){
			sessionCatalogId = SessionUtil.generateTempId();
			//*********** Add By Zhang Yong Start *****************************//
			//释放application中的订单锁
			OrderLockRelease realeseOrderLock = new OrderLockRelease();
			realeseOrderLock.releaseOrderLock(); 
			//*********** Add By Zhang Yong End *****************************//
		}else{
			//*********** Add By Zhang Yong Start *****************************//
			//判断将要修改的单号是否正在被操作
			String editUrl = "product/catalog!input.action?id="+id;
			OrderLockRelease orderLockRelease = new OrderLockRelease();
			String byUser = orderLockRelease.checkOrderStatus(editUrl);
			if (byUser != null) {
				operation_method = byUser;
			}
			//*********** Add By Zhang Yong End *****************************//
			sessionCatalogId = this.entity.getId().toString();
		}
		SessionUtil.deleteRow(SessionPdtServ.CatalogProductCategory.value(), sessionCatalogId);
		SessionUtil.deleteRow(SessionPdtServ.DelCatalogProductCategory.value(), sessionCatalogId);
		SessionUtil.deleteRow(SessionPdtServ.CatalogServiceCategory.value(), sessionCatalogId);
		SessionUtil.deleteRow(SessionPdtServ.DelCatalogServiceCategory.value(), sessionCatalogId);
		SessionUtil.deleteRow(SessionPdtServ.CatalogApprovedName.value(), sessionCatalogId);
		SessionUtil.deleteRow(SessionPdtServ.CatalogApprovedNameReason.value(), sessionCatalogId);
		SessionUtil.deleteRow(SessionPdtServ.CatalogApprovedStatus.value(), sessionCatalogId);
		SessionUtil.deleteRow(SessionPdtServ.CatalogApprovedStatusReason.value(), sessionCatalogId);
		currencyDropdownList = publicService.getSpecDropDownList(SpecDropDownListName.CURRENCY);
		catalogDropdownList = publicService.getDropdownList("CATALOG_PUBLISH_ZONE");
		this.nameAppr = productService.checkPropertyApproved(entity.getId(), RequestApproveType.catalogName.name(),RequestApproveType.Catalog.name());
		this.statusAppr = productService.checkPropertyApproved(entity.getId(),RequestApproveType.status.name(),RequestApproveType.Catalog.name());
		if("".equals(this.approvedName)){
			this.approvedName = null;
		}
		if("".equals(this.approvedStatus)){
			this.approvedStatus = null;
		}
		/*if(callBackName==null){
			Struts2Util.renderJson("");
		}*/
		return callBackName;
	}

	/*
	 * (non-Javadoc)
	 * @see com.genscript.gsscm.common.web.BaseAction#list()
	 * 按条件查询catalog方法;
	 * 输入类型参考PropertyFilter类;
	 */
	@Override
	public String list() throws Exception {
		// 获得分页请求相关数据：如第几页
		PagerUtil<Catalog> pagerUtil = new PagerUtil<Catalog>();
		page = pagerUtil.getRequestPage();
		List<PropertyFilter> filters = WebUtil.buildPropertyFilters(ServletActionContext.getRequest());
		// 设置默认每页显示记录条数
		if (page.getPageSize() == null
				|| page.getPageSize().intValue() < 1) {
			page.setPageSize(20);
		}
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.DESC);
		}
		page = this.productService.searchCatalogList(page, filters);
		this.statusOfapproved = this.productService.getApprovedRequestListByTableTypeStatus(RequestApproveType.Catalog.name());
		// 把结果集中的分页信息转化为PageDTO并保存在request的pagerInfo里
		PageDTO pagerInfo = pagerUtil.formPage(page);
		ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
		return SUCCESS;
	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/*
	 * save new catalog
	 */
	public String saveNewCatalog(){
		Map<String, Object> rt = new HashMap<String, Object>();
		Integer loginUserId = SessionUtil.getUserId();
		try{
			Catalog catalog = this.productService.copyNewCatalog(catalogDTO, loginUserId);
			rt.put("message", "The catalog #"+catalog.getCatalogName()+" is saved successfully!");
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0203",
					SessionUtil.getUserId());
			rt.put("hasException", "Y");
			rt.put("exception", exDTO);
			rt.put("message", ERROR);
		}
		Struts2Util.renderJson(rt);
		return null;
	}
	
	
	/*
	 * 保存catalog
	 * obj 为获取catalog save 和  del 信息；
	 */
	@SuppressWarnings("unchecked")
	public String SaveCopyCtlgAct() throws Exception{
		Integer loginUserId = SessionUtil.getUserId();
		Map<String, Object> rt = new HashMap<String, Object>();
		//*********** Add By Zhang Yong Start *****************************//
		//校验当前对象是否正被其他人先编辑，有则不保存，跳转到编辑页面，防止用户通过URL方式保存订单
		if (sessionCatalogId != null && !("").equals(sessionCatalogId)) {
			String editUrl = "product/catalog!input.action?id="+sessionCatalogId;
			OrderLockRelease orderLockRelease = new OrderLockRelease();
			String byUser = orderLockRelease.checkOrderStatus(editUrl);
			if (byUser != null) {
				operation_method = byUser;
				rt.put("message", "Save catalog fail,the catalog is editing by "+operation_method);
				rt.put("id", sessionCatalogId);
				Struts2Util.renderJson(rt);
				// 清除Session
				return null;
			}
		}
		//*********** Add By Zhang Yong End *****************************//	
		try{
			if(catalogDTO.getDefaultFlag()==null||catalogDTO.getDefaultFlag().equals("")){
				catalogDTO.setDefaultFlag("N");
			}
			Object obj = SessionUtil.getRow(SessionPdtServ.CatalogProductCategory.value(), sessionCatalogId);
			if(obj!=null){
				Map<String,ProductCategory> productCategoryMap = (Map<String, ProductCategory>) obj;
				List<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();
				for(Map.Entry<String, ProductCategory> entry: productCategoryMap.entrySet()){
					ProductCategory productCategory = productCategoryMap.get(entry.getKey());
					if(productCategory!=null){
						productCategoryList.add(productCategory);
						//this.productService.saveOrUpdateProductCategory(productCategory, loginUserId);
					}
				}
				SessionUtil.deleteRow(SessionPdtServ.CatalogProductCategory.value(), sessionCatalogId);
				if(!productCategoryList.isEmpty()){
					catalogDTO.setPdtCatList(productCategoryList);
				}
			}
			
			obj = SessionUtil.getRow(SessionPdtServ.DelCatalogProductCategory.value(), sessionCatalogId);
			if(obj !=null){
				List<Integer> delPdtList = (List<Integer>)obj;
				if(!delPdtList.isEmpty()){
					catalogDTO.setDelPdtCatIdList(delPdtList);
				}
				SessionUtil.deleteRow(SessionPdtServ.DelCatalogProductCategory.value(), sessionCatalogId);
			}
			
			obj = SessionUtil.getRow(SessionPdtServ.CatalogServiceCategory.value(), sessionCatalogId);
			if(obj!=null){
				Map<String,ServiceCategory> serviceCategoryMap = (Map<String, ServiceCategory>) obj;
				List<ServiceCategory> serviceCategoryList = new ArrayList<ServiceCategory>();
				for(Map.Entry<String, ServiceCategory> entry: serviceCategoryMap.entrySet()){
					ServiceCategory serviceCategory = serviceCategoryMap.get(entry.getKey());
					if(serviceCategory!=null){
						serviceCategoryList.add(serviceCategory);
						//this.servService.saveOrUpdateServCategory(serviceCategory, loginUserId);
					}
				}
				SessionUtil.deleteRow(SessionPdtServ.CatalogServiceCategory.value(), sessionCatalogId);
				if(!serviceCategoryList.isEmpty()){
					catalogDTO.setServiceCatList(serviceCategoryList);
				}
			}
			
			obj = SessionUtil.getRow(SessionPdtServ.DelCatalogServiceCategory.value(), sessionCatalogId);
			if(obj !=null){
				List<Integer> delServList = (List<Integer>)obj;
				if(!delServList.isEmpty()){
					catalogDTO.setDelServiceCatIdList(delServList);
				}
				SessionUtil.deleteRow(SessionPdtServ.DelCatalogServiceCategory.value(), sessionCatalogId);
			}
			/*if(categoryId!=null){
				List<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();
				String[] categoryIdData = categoryId.split("<;;>");
				String[] ctgDescriptionData = ctgDescription.split("<;;>");
				String[] ctgAstGrpData = ctgAstGrp.split("<;;>");
				if(categoryIdData.length>1){
					for(int i = 1;i<categoryIdData.length;i++){
						if(!(categoryIdData[i].equals("-1"))){
							ProductCategory category = this.productService.getProductCategoryDetail(Integer.valueOf(categoryIdData[i]));
							if(category!=null){
								category.setAssetGroup(ctgAstGrpData[i]);
								if(ctgDescriptionData.length>i-1){
									category.setDescription(ctgDescriptionData[i]);
								}
								productCategoryList.add(category);
							}
						}
					}
					if(!(productCategoryList.isEmpty())){
						catalogDTO.setPdtCatList(productCategoryList);
					}
				}
			}
			
			if(servCategoryId!=null){
				List<ServiceCategory> serviceCategoryList = new ArrayList<ServiceCategory>();
				String[] serviceCatalogIdData = servCategoryId.split("<;;>");
				String[] ServiceCtgDescriptionData = servCtgDescription.split("<;;>");
				String[] ServiceCtgAstGrpData = servCtgAstGrp.split("<;;>");
				if(serviceCatalogIdData.length>1){
					for(int i = 1;i<serviceCatalogIdData.length;i++){
						if(!(serviceCatalogIdData[i].equals("-1"))){
							ServiceCategory category = this.serviceService.getServiceCategoryDetail(Integer.valueOf(serviceCatalogIdData[i]));
							if(category!=null){
								if(ServiceCtgDescriptionData.length>i-1){
									category.setAssetGroup(ServiceCtgDescriptionData[i]);
								}
								category.setDescription(ServiceCtgAstGrpData[i]);
								serviceCategoryList.add(category);
							}
						}
					}
					if(!(serviceCategoryList.isEmpty())){
						catalogDTO.setServiceCatList(serviceCategoryList);
					}	
				}
			}
			if(pdtCtgDel!=null&&!(pdtCtgDel.equals(""))){
				List<Integer> delProduct = new ArrayList<Integer>();
				String[] del_product = pdtCtgDel.split("<;;>");
				if(del_product.length>1){
					for(int i =1;i<del_product.length;i++){
						delProduct.add(Integer.valueOf(del_product[i]));
					}
				}
				if(delProduct!=null){
					catalogDTO.setDelPdtCatIdList(delProduct);
				}
			}
			if(servCtgDel!=null&&!(servCtgDel.equals(""))){
				List<Integer> delServ = new ArrayList<Integer>();
				String[] del_service = servCtgDel.split("<;;>");
				if(del_service.length>1){
					for(int i =1;i<del_service.length;i++){
						delServ.add(Integer.valueOf(del_service[i]));
					}
				}
				if(delServ!=null){
					catalogDTO.setDelServiceCatIdList(delServ);
				}
			}*/
			//获取catalog approved;
			obj = SessionUtil.getRow(SessionPdtServ.CatalogApprovedName.value(), sessionCatalogId);
			if(obj !=null){
			    catalogDTO.setNameApprove(obj.toString());
				SessionUtil.deleteRow(SessionPdtServ.CatalogApprovedName.value(), sessionCatalogId);
			}
			obj = SessionUtil.getRow(SessionPdtServ.CatalogApprovedNameReason.value(), sessionCatalogId);
			if(obj !=null){
			    catalogDTO.setNameReason(obj.toString());
				SessionUtil.deleteRow(SessionPdtServ.CatalogApprovedNameReason.value(), sessionCatalogId);
			}
			obj = SessionUtil.getRow(SessionPdtServ.CatalogApprovedStatus.value(), sessionCatalogId);
			if(obj !=null){
			    catalogDTO.setStatusApprove(obj.toString());
				SessionUtil.deleteRow(SessionPdtServ.CatalogApprovedStatus.value(), sessionCatalogId);
			}
			obj = SessionUtil.getRow(SessionPdtServ.CatalogApprovedStatusReason.value(), sessionCatalogId);
			if(obj !=null){
			    catalogDTO.setStatusReason(obj.toString());
				SessionUtil.deleteRow(SessionPdtServ.CatalogApprovedStatusReason.value(), sessionCatalogId);
			}
			
			Catalog catalog = this.productService.saveCatalog(catalogDTO, loginUserId);
			rt.put("message", "The catalog #"+catalog.getCatalogName()+" is saved successfully!");
			rt.put("id",catalog.getId());
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0203",
					SessionUtil.getUserId());
			rt.put("hasException", "Y");
			rt.put("exception", exDTO);			
		}
		Struts2Util.renderJson(rt);
		return null;
	}
	
	public String delCatalogListApproved(){
		Map<String, Object> rt = new HashMap<String, Object>();
		if(this.approvedStatusList==null||"".equals(this.approvedStatusList)){
			rt.put("message","You have not chosen any valid data!!!");
		}else{
			String[] statustList = this.approvedStatusList.split(",");
			Integer userId = SessionUtil.getUserId();
			this.productService.delCatalogApproved(statustList,userId,approved,approvedReason);
			rt.put("message", SUCCESS);
		}
		Struts2Util.renderJson(rt);
		return null;
	}
	
	/*
	 * catalog Approved session save 
	 * @param type , name ,reason
	 */
	public String catalogApprovedSaveSession(){
		Map<String, Object> rt = new HashMap<String, Object>();
		if(SessionPdtServ.CatalogApprovedName.value().equals(approvedType)){
			if(SessionUtil.getRow(SessionPdtServ.CatalogApprovedName.value(), sessionCatalogId)==null){
				SessionUtil.insertRow(SessionPdtServ.CatalogApprovedName.value(), sessionCatalogId, approved);
			}else{
				SessionUtil.updateRow(SessionPdtServ.CatalogApprovedName.value(), sessionCatalogId, approved);
			}
			if(SessionUtil.getRow(SessionPdtServ.CatalogApprovedNameReason.value(), sessionCatalogId)==null){
				SessionUtil.insertRow(SessionPdtServ.CatalogApprovedNameReason.value(), sessionCatalogId, approvedReason);
			}else{
				SessionUtil.updateRow(SessionPdtServ.CatalogApprovedNameReason.value(), sessionCatalogId, approvedReason);
			}
			
		}else if(SessionPdtServ.CatalogApprovedStatus.value().equals(approvedType)){
			if(SessionUtil.getRow(SessionPdtServ.CatalogApprovedStatus.value(), sessionCatalogId)==null){
				SessionUtil.insertRow(SessionPdtServ.CatalogApprovedStatus.value(), sessionCatalogId, approved);
			}else{
				SessionUtil.updateRow(SessionPdtServ.CatalogApprovedStatus.value(), sessionCatalogId, approved);
			}
			if(SessionUtil.getRow(SessionPdtServ.CatalogApprovedStatusReason.value(), sessionCatalogId)==null){
				SessionUtil.insertRow(SessionPdtServ.CatalogApprovedStatusReason.value(), sessionCatalogId,approvedReason);
			}else{
				SessionUtil.updateRow(SessionPdtServ.CatalogApprovedStatusReason.value(), sessionCatalogId, approvedReason);
			}
		}
		rt.put("message", SUCCESS);
		Struts2Util.renderJson(rt);
		return null;
	}
	
	//-- 页面属性访问函数 --//
	/**
	 * list页面显示Catalog分页列表.
	*/
	public Page<Catalog> getPage() {
		return page;
	}
	/*
	 * 分页起始。
	 
	public Integer getPageFirst(){
		if(page.getPageNo()>5&&page.getTotalPages()>10){
			if(page.getPageNo()+5>page.getTotalPages()){
				return page.getTotalPages().intValue()-9;
			}
			return page.getPageNo()-4;
		}
		return 1;
	}
	
	 * 分页结束。
	 
	public Integer getPageLast(){
		if(page.getTotalPages()<10||(page.getPageNo()+5)>page.getTotalPages()){
			return page.getTotalPages().intValue();
		}else if(page.getPageNo()<5){
			return 10;
		}else{
			return page.getPageNo()+5;
		}
	}*/
	/**
	 * 页面显示.
	 */

	public List<PbDropdownListOptions> getCatalogDropdownList() {
		return catalogDropdownList;
	}
	
	public List<DropDownDTO> getCurrencyDropdownList() {
		return currencyDropdownList;
	}

	public CatalogDTO getCatalogDTO() {
		return catalogDTO;
	}
	
	/*
	 * 页面获取 
	 */
	
	public void setCurrencyDropdownList(List<DropDownDTO> currencyDropdownList) {
		this.currencyDropdownList = currencyDropdownList;
	}

	public void setCatalogDTO(CatalogDTO catalogDTO) {
		this.catalogDTO = catalogDTO;
	}

	public void setCatalogDropdownList(
			List<PbDropdownListOptions> catalogDropdownList) {
		this.catalogDropdownList = catalogDropdownList;
	}
	
	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCtgAstGrp() {
		return ctgAstGrp;
	}

	public void setCtgAstGrp(String ctgAstGrp) {
		this.ctgAstGrp = ctgAstGrp;
	}

	public String getCtgDescription() {
		return ctgDescription;
	}

	public void setCtgDescription(String ctgDescription) {
		this.ctgDescription = ctgDescription;
	}

	public String getServCategoryId() {
		return servCategoryId;
	}

	public void setServCategoryId(String servCategoryId) {
		this.servCategoryId = servCategoryId;
	}

	public String getServCtgAstGrp() {
		return servCtgAstGrp;
	}

	public void setServCtgAstGrp(String servCtgAstGrp) {
		this.servCtgAstGrp = servCtgAstGrp;
	}

	public String getServCtgDescription() {
		return servCtgDescription;
	}

	public void setServCtgDescription(String servCtgDescription) {
		this.servCtgDescription = servCtgDescription;
	}

	public String getPdtCtgDel() {
		return pdtCtgDel;
	}

	public void setPdtCtgDel(String pdtCtgDel) {
		this.pdtCtgDel = pdtCtgDel;
	}

	public String getServCtgDel() {
		return servCtgDel;
	}

	public void setServCtgDel(String servCtgDel) {
		this.servCtgDel = servCtgDel;
	}

	public String getSessionCatalogId() {
		return sessionCatalogId;
	}

	public void setSessionCatalogId(String sessionCatalogId) {
		this.sessionCatalogId = sessionCatalogId;
	}

	public String getApproved() {
		return approved;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}

	public String getApprovedType() {
		return approvedType;
	}

	public void setApprovedType(String approvedType) {
		this.approvedType = approvedType;
	}

	public String getApprovedReason() {
		return approvedReason;
	}

	public void setApprovedReason(String approvedReason) {
		this.approvedReason = approvedReason;
	}

	public Boolean getNameAppr() {
		return nameAppr;
	}

	public void setNameAppr(Boolean nameAppr) {
		this.nameAppr = nameAppr;
	}

	public Boolean getStatusAppr() {
		return statusAppr;
	}

	public void setStatusAppr(Boolean statusAppr) {
		this.statusAppr = statusAppr;
	}

	public String getOperation_method() {
		return operation_method;
	}

	public void setOperation_method(String operationMethod) {
		operation_method = operationMethod;
	}

	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public String getApprovedName() {
		return approvedName;
	}

	public void setApprovedName(String approvedName) {
		this.approvedName = approvedName;
	}

	public String getApprovedStatus() {
		return approvedStatus;
	}

	public void setApprovedStatus(String approvedStatus) {
		this.approvedStatus = approvedStatus;
	}

	public String getApprovedMethod() {
		return approvedMethod;
	}

	public void setApprovedMethod(String approvedMethod) {
		this.approvedMethod = approvedMethod;
	}

	public String getApprovedStatusList() {
		return approvedStatusList;
	}

	public void setApprovedStatusList(String approvedStatusList) {
		this.approvedStatusList = approvedStatusList;
	}

	public List<Integer> getStatusOfapproved() {
		return statusOfapproved;
	}

	public void setStatusOfapproved(List<Integer> statusOfapproved) {
		this.statusOfapproved = statusOfapproved;
	}

	public String getDefaultTab() {
		return defaultTab;
	}

	public void setDefaultTab(String defaultTab) {
		this.defaultTab = defaultTab;
	}
	
	
}
