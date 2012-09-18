package com.genscript.gsscm.manufacture.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.util.OrderLockRelease;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.inventory.dao.WarehouseDao;
import com.genscript.gsscm.inventory.entity.Warehouse;
import com.genscript.gsscm.manufacture.dto.RouteClassDTO;
import com.genscript.gsscm.manufacture.entity.Operation;
import com.genscript.gsscm.manufacture.entity.Route;
import com.genscript.gsscm.manufacture.entity.RouteOperation;
import com.genscript.gsscm.manufacture.service.SetupService;
import com.genscript.gsscm.product.entity.ProductClass;
import com.genscript.gsscm.product.service.ProductService;
import com.genscript.gsscm.serv.entity.ServiceClass;
import com.genscript.gsscm.serv.service.ServService;
import com.genscript.gsscm.ws.WSException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Results( {
		@Result(name = "search_from", location = "manufacture/route_search_form.jsp"),
		@Result(name = "search_result", location = "manufacture/route_search_result.jsp"),
		@Result(name = "route_add", location = "manufacture/route_add.jsp"),
		@Result(name = "route_edit", location = "manufacture/route_edit.jsp"),
		@Result(name = "route_operation_list", location = "manufacture/route_operation_list.jsp") })
public class RouteAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6534283335416418911L;
	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired
	private SetupService setupService;
	@Autowired
	private WarehouseDao warehouseDao;
	@Autowired
	private ServService servService;
	@Autowired
	private ProductService productService;
	private Route route;
	private Integer id;
	private Page<Route> routePage;
	private String[] delRouteId;
	private List<Integer> groupIdList;
	private List<Warehouse> warehouseList;
	private List<String[]> productionList;
	private List<RouteClassDTO> classList;
	// 关联Operation
	private Map<String, RouteOperation> roMap;
	private String roKey;
	private String sessRouteId;
	private List<String> delROIdList;
	private List<Integer> operationIdList;
	// 用于批量更新seqNo.
	private List<Integer> seqNoList;
	private List<String> keyList;

	private String operation_method;
	/**
	 * 进入Route的主页面
	 * 
	 * @return
	 */
	public String search() {
		this.warehouseList = this.warehouseDao.findAll(Page.ASC,"name");
		productionList = this.setupService.getProductionList();
		return "search_from";
	}

	/**
	 * 进入新增页面
	 */
	public String add() {
		this.doInput();
		route = new Route();
		route.setModifiedBy(SessionUtil.getUserId());
		route.setModifyUser(SessionUtil.getUserName());
		if(!this.setupService.isHasOtherDefaultRoute(null)) {
			route.setDefaultFlag("Y");
		}
		return "route_add";
	}

	/**
	 * 进入修改页面.
	 * 
	 * @return
	 */
	public String edit() {
		//*********** Add By Zhang Yong Start *****************************//
		if (this.id != null && !("").equals(this.id)) {
			//判断将要修改的单号是否正在被操作
			String editUrl = "route!edit.action?id="+id;
			OrderLockRelease orderLockRelease = new OrderLockRelease();
			String byUser = orderLockRelease.checkOrderStatus(editUrl);
			if (byUser != null) {
				operation_method = byUser;
			}
		} else {
			//释放application中的订单锁
			OrderLockRelease realeseOrderLock = new OrderLockRelease();
			realeseOrderLock.releaseOrderLock(); 
		}
		//*********** Add By Zhang Yong End *****************************//
		this.doInput();
		route = this.setupService.getRoute(this.id);
		this.sessRouteId = Integer.toString(this.id);
		route.setProduction(route.getItemType() + "-" + route.getClsId());
		return "route_edit";
	}

	public void doInput() {
		this.classList = new ArrayList<RouteClassDTO>();
		List<ProductClass> pdtClassList = this.productService
				.getAllProductClass();
		for (ProductClass pdtClass : pdtClassList) {
			RouteClassDTO dto = new RouteClassDTO();
			dto.setType("Product - " + pdtClass.getName());
			dto.setValue("PRODUCT-" + pdtClass.getClsId());
			this.classList.add(dto);
		}
		List<ServiceClass> servClassList = this.servService
				.getAllServiceClass();
		for (ServiceClass servClass : servClassList) {
			RouteClassDTO dto = new RouteClassDTO();
			dto.setType("Service - " + servClass.getName());
			dto.setValue("SERVICE-" + servClass.getClsId());
			this.classList.add(dto);
		}
		this.warehouseList = this.warehouseDao.findAll(Page.ASC,"name");

	}

	/**
	 * 分页查找
	 */
	public String list() {
		try {
			// 获得分页请求相关数据：如第几页
			PagerUtil<Route> pagerUtil = new PagerUtil<Route>();
			routePage = pagerUtil.getRequestPage();
			// 设置默认排序
			if (!routePage.isOrderBySetted()) {
				routePage.setOrderBy("name");
				routePage.setOrder(Page.ASC);
			}
			// 设置默认每页显示记录条数
			routePage.setPageSize(20);
			// 获得查询条件
			List<PropertyFilter> filters = WebUtil
					.buildPropertyFilters(ServletActionContext.getRequest());
			if (filters == null || filters.isEmpty()) {
				// 默认列表的结果集(含分页信息)
				routePage = setupService.searchRoutePage(routePage, null);
			} else {
				// 有查询条件的结果集(含分页信息)
				routePage = setupService.searchRoutePage(routePage, filters);
			}
			ServletActionContext.getRequest().setAttribute("pagerInfo", routePage);
			Map<String, Object> session = ActionContext.getContext().getSession();
			session.remove(SessionConstant.RouteOperation.value());
		} catch (Exception ex) {
			return "search_result";
		}
		return "search_result";
	}

	/**
	 * 新增或修改一个Route
	 */
	public String save() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			//*********** Add By Zhang Yong Start *****************************//
			//校验当前对象是否正被其他人先编辑，有则不保存，跳转到编辑页面，防止用户通过URL方式保存订单
			if (this.route!= null && this.route.getId() != null) {
				String editUrl = "route!edit.action?id="+this.route.getId();
				OrderLockRelease orderLockRelease = new OrderLockRelease();
				String byUser = orderLockRelease.checkOrderStatus(editUrl);
				if (byUser != null) {
					operation_method = byUser;
					rt.put("message", "Save fail,the route is editing by "+operation_method);
					Struts2Util.renderJson(rt);
					return null;
				}
			}
			//*********** Add By Zhang Yong End *****************************//	
			this.attachOperation();
			if (route.getProduction() != null) {
				String[] typeAndClsId = route.getProduction().split("-");
				route.setItemType(typeAndClsId[0]);
				route.setClsId(Integer.parseInt(typeAndClsId[1]));

			}
			this.setupService.saveRoute(route, SessionUtil.getUserId());
			SessionUtil.deleteRow(SessionConstant.RouteOperation.value(),
					this.route.getId() + "");
			rt.put("message", "Save routing sucessfully.");
			//*********** Add By Zhang Yong Start *****************************//
			//释放同步锁
			OrderLockRelease realeseOrderLock = new OrderLockRelease();
			realeseOrderLock.releaseOrderLock(); 
			//*********** Add By Zhang Yong End *****************************//
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

	/**
	 * 
	 * 批量删除Route.
	 */
	public String delete() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			List<Integer> idList = new ArrayList<Integer>();
			for (String temp : this.delRouteId) {
				idList.add(Integer.valueOf(temp));
			}
			this.setupService.delRoute(idList);
			rt.put("message", "Delete routing sucessfully !");
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

	public String getOperationList() {
		Map<String, RouteOperation> dbMap = null;
		Map<String, RouteOperation> sessMap = (Map<String, RouteOperation>) SessionUtil
		.getRow(SessionConstant.RouteOperation.value(),
				String.valueOf(this.sessRouteId));
		if(sessMap==null) {
			// 如果sessOperationId为数字则取得数据中的, 否则数据库中没有记录不用去查询
			if (StringUtils.isNumeric(this.sessRouteId)) {
				List<RouteOperation> dbList = this.setupService
						.getAllRouteOperation(Integer.parseInt(this.sessRouteId));
				dbMap = new LinkedHashMap<String, RouteOperation>();
				for (RouteOperation dto : dbList) {
					dbMap.put(dto.getId() + "", dto);
				}
			}
			SessionUtil.insertRow(SessionConstant.RouteOperation.value(),
					this.sessRouteId, dbMap);
			
		} else {
			dbMap = sessMap;
		}
		this.roMap = dbMap;
		System.out.println("sessMap: "
				+ SessionUtil.getRow(SessionConstant.RouteOperation.value(),
						this.sessRouteId));

		return "route_operation_list";
	}

	/**
	 * Session保存
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveOperation() throws Exception {
		Map<Integer, String> retMap = new HashMap<Integer, String>();
		try {
			int trCount = Integer.parseInt(Struts2Util.getParameter("trCount"));
			if(operationIdList!=null&&operationIdList.size()>0) {
				for (int i = 0; i < this.operationIdList.size(); i++) {
					String tempId = SessionUtil.generateTempId();
					Operation operation = this.setupService
							.getOperation(this.operationIdList.get(i));
					RouteOperation ro = new RouteOperation();
					ro.setCreatedBy(SessionUtil.getUserId());
					ro.setModifiedBy(SessionUtil.getUserId());
					ro.setOperation(operation);
					ro.setSeqNo(trCount + i + 1);
					ro.setRoutingId(Integer.parseInt(this.sessRouteId));
					SessionUtil.updateOneRow(
							SessionConstant.RouteOperation.value(),
							this.sessRouteId, tempId, ro);
					retMap.put(this.operationIdList.get(i), tempId);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Struts2Util.renderJson(retMap);
		return null;
	}

	@SuppressWarnings("unchecked")
	public String deleteOperation() {
		this.roMap = (Map<String, RouteOperation>) SessionUtil.getRow(
				SessionConstant.RouteOperation.value(), this.sessRouteId);
		for (String key : this.delROIdList) {
			if (StringUtils.isNumeric(key)) {
				roMap.put(key, null);
			} else {
				roMap.remove(key);
			}

		}
		Struts2Util.renderText("success");
		return NONE;
	}

	@SuppressWarnings("unchecked")
	private void attachOperation() {
		List<RouteOperation> roList = new ArrayList<RouteOperation>();
		List<Integer> delIdList = new ArrayList<Integer>();
		Map<String, RouteOperation> sessMap = (Map<String, RouteOperation>) SessionUtil
				.getRow(SessionConstant.RouteOperation.value(),
						this.sessRouteId);
		if(sessMap!=null) {
			for (Entry<String, RouteOperation> entry : sessMap.entrySet()) {
				RouteOperation groupRes = (RouteOperation) entry.getValue();
				// 本例中对session中的数据只有新增和删除操作
				if (StringUtils.isNumeric(entry.getKey())) {// 数据库已有的
					if (groupRes == null) {// 执行了临时的删除操作
						delIdList.add(Integer.parseInt(entry.getKey()));
					} else {
						// TODO 修改部分的处理暂不考虑
						roList.add(groupRes);// 修改的
					}
				} else {
					groupRes.setId(null);
					roList.add(groupRes);// 新增的
				}
			}
		}
		
		this.route.setDelRouteOperationIdList(delIdList);
		this.route.setRouteOperationList(roList);
	}

	@SuppressWarnings("unchecked")
	public String updateSeqNo() {
		// 取得session中的
		Map<String, RouteOperation> sessMap = (Map<String, RouteOperation>) SessionUtil
				.getRow(SessionConstant.RouteOperation.value(),
						this.sessRouteId);
		for (int k = 0; k < this.keyList.size(); k++) {
			RouteOperation ors = sessMap.get(keyList.get(k));
			ors.setSeqNo(this.seqNoList.get(k));
			SessionUtil.updateOneRow(SessionConstant.RouteOperation.value(),
					this.sessRouteId, keyList.get(k), ors);
		}
		Struts2Util.renderText("success");
		return NONE;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Page<Route> getRoutePage() {
		return routePage;
	}

	public void setRoutePage(Page<Route> routePage) {
		this.routePage = routePage;
	}

	public String[] getDelRouteId() {
		return delRouteId;
	}

	public void setDelRouteId(String[] delRouteId) {
		this.delRouteId = delRouteId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Integer> getGroupIdList() {
		return groupIdList;
	}

	public void setGroupIdList(List<Integer> groupIdList) {
		this.groupIdList = groupIdList;
	}

	public List<Warehouse> getWarehouseList() {
		return warehouseList;
	}

	public void setWarehouseList(List<Warehouse> warehouseList) {
		this.warehouseList = warehouseList;
	}

	public List<RouteClassDTO> getClassList() {
		return classList;
	}

	public void setClassList(List<RouteClassDTO> classList) {
		this.classList = classList;
	}

	public List<Integer> getOperationIdList() {
		return operationIdList;
	}

	public void setOperationIdList(List<Integer> operationIdList) {
		this.operationIdList = operationIdList;
	}

	public List<Integer> getSeqNoList() {
		return seqNoList;
	}

	public void setSeqNoList(List<Integer> seqNoList) {
		this.seqNoList = seqNoList;
	}

	public List<String> getKeyList() {
		return keyList;
	}

	public void setKeyList(List<String> keyList) {
		this.keyList = keyList;
	}

	public String getRoKey() {
		return roKey;
	}

	public void setRoKey(String roKey) {
		this.roKey = roKey;
	}

	public String getSessRouteId() {
		return sessRouteId;
	}

	public void setSessRouteId(String sessRouteId) {
		this.sessRouteId = sessRouteId;
	}

	public Map<String, RouteOperation> getRoMap() {
		return roMap;
	}

	public void setRoMap(Map<String, RouteOperation> roMap) {
		this.roMap = roMap;
	}

	public List<String> getDelROIdList() {
		return delROIdList;
	}

	public void setDelROIdList(List<String> delROIdList) {
		this.delROIdList = delROIdList;
	}

	public String getOperation_method() {
		return operation_method;
	}

	public void setOperation_method(String operation_method) {
		this.operation_method = operation_method;
	}

	public List<String[]> getProductionList() {
		return productionList;
	}

	public void setProductionList(List<String[]> productionList) {
		this.productionList = productionList;
	}

}
