package com.genscript.gsscm.manufacture.service;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.dto.DropDownDTO;
import com.genscript.gsscm.common.MimeMailService;
import com.genscript.gsscm.common.UrlUtil;
import com.genscript.gsscm.common.constant.Constants;
import com.genscript.gsscm.common.constant.DocumentType;
import com.genscript.gsscm.common.constant.OrderStatusType;
import com.genscript.gsscm.common.constant.QuoteItemType;
import com.genscript.gsscm.common.constant.StrutsActionContant;
import com.genscript.gsscm.common.constant.WoOperStatus;
import com.genscript.gsscm.common.constant.WorkOrderStatus;
import com.genscript.gsscm.common.util.DateUtils;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.StringUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.customer.dao.CustomerDao;
import com.genscript.gsscm.customer.dao.SalesRepDao;
import com.genscript.gsscm.customer.entity.Customer;
import com.genscript.gsscm.customer.entity.SalesRep;
import com.genscript.gsscm.manufacture.dao.AntibodyOprExperimentDatasDao;
import com.genscript.gsscm.manufacture.dao.AntibodyOprPurificationResultsDao;
import com.genscript.gsscm.manufacture.dao.DsPlateItemsDao;
import com.genscript.gsscm.manufacture.dao.ManuDocumentDao;
import com.genscript.gsscm.manufacture.dao.ManufacturingClerksDao;
import com.genscript.gsscm.manufacture.dao.OperationDao;
import com.genscript.gsscm.manufacture.dao.QaClerkDao;
import com.genscript.gsscm.manufacture.dao.QaGroupAssignedDao;
import com.genscript.gsscm.manufacture.dao.QaGroupDao;
import com.genscript.gsscm.manufacture.dao.RouteDao;
import com.genscript.gsscm.manufacture.dao.RouteOperationDao;
import com.genscript.gsscm.manufacture.dao.WoBatchDetailDao;
import com.genscript.gsscm.manufacture.dao.WoBatcheDao;
import com.genscript.gsscm.manufacture.dao.WoOperationComponentDao;
import com.genscript.gsscm.manufacture.dao.WoOperationResourceDao;
import com.genscript.gsscm.manufacture.dao.WoStatusHistoryDao;
import com.genscript.gsscm.manufacture.dao.WorkCenterAssignedDao;
import com.genscript.gsscm.manufacture.dao.WorkCenterDao;
import com.genscript.gsscm.manufacture.dao.WorkGroupDao;
import com.genscript.gsscm.manufacture.dao.WorkOrderDao;
import com.genscript.gsscm.manufacture.dao.WorkOrderGroupDao;
import com.genscript.gsscm.manufacture.dao.WorkOrderLotDao;
import com.genscript.gsscm.manufacture.dao.WorkOrderOperationDao;
import com.genscript.gsscm.manufacture.dto.PeptideTemplateDTO;
import com.genscript.gsscm.manufacture.dto.WorkOrderDTO;
import com.genscript.gsscm.manufacture.dto.WorkOrderExcelDTO;
import com.genscript.gsscm.manufacture.dto.WorkOrderOperationBean;
import com.genscript.gsscm.manufacture.entity.AntibodyOprExperimentDatas;
import com.genscript.gsscm.manufacture.entity.AntibodyOprPurificationResults;
import com.genscript.gsscm.manufacture.entity.ManuDocument;
import com.genscript.gsscm.manufacture.entity.ManufacturingClerks;
import com.genscript.gsscm.manufacture.entity.Operation;
import com.genscript.gsscm.manufacture.entity.QaClerk;
import com.genscript.gsscm.manufacture.entity.QaGroup;
import com.genscript.gsscm.manufacture.entity.Resource;
import com.genscript.gsscm.manufacture.entity.Route;
import com.genscript.gsscm.manufacture.entity.RouteOperation;
import com.genscript.gsscm.manufacture.entity.WoBatche;
import com.genscript.gsscm.manufacture.entity.WoOperationComponent;
import com.genscript.gsscm.manufacture.entity.WoOperationResource;
import com.genscript.gsscm.manufacture.entity.WoStatusHistory;
import com.genscript.gsscm.manufacture.entity.WorkCenter;
import com.genscript.gsscm.manufacture.entity.WorkCenterAssigned;
import com.genscript.gsscm.manufacture.entity.WorkGroup;
import com.genscript.gsscm.manufacture.entity.WorkOrder;
import com.genscript.gsscm.manufacture.entity.WorkOrderGroup;
import com.genscript.gsscm.manufacture.entity.WorkOrderLot;
import com.genscript.gsscm.manufacture.entity.WorkOrderOperation;
import com.genscript.gsscm.order.dao.MfgOrderDao;
import com.genscript.gsscm.order.dao.MfgOrderItemDao;
import com.genscript.gsscm.order.dao.OrderAddressDao;
import com.genscript.gsscm.order.dao.OrderAntibodyDao;
import com.genscript.gsscm.order.dao.OrderCustCloningDao;
import com.genscript.gsscm.order.dao.OrderDao;
import com.genscript.gsscm.order.dao.OrderDnaSequencingDao;
import com.genscript.gsscm.order.dao.OrderGeneSynthesisDao;
import com.genscript.gsscm.order.dao.OrderItemCenterBeanDao;
import com.genscript.gsscm.order.dao.OrderItemDao;
import com.genscript.gsscm.order.dao.OrderMutagenesisDao;
import com.genscript.gsscm.order.dao.OrderOligoDao;
import com.genscript.gsscm.order.dao.OrderPeptideDao;
import com.genscript.gsscm.order.dao.OrderPkgServiceDao;
import com.genscript.gsscm.order.dao.OrderPlasmidPreparationDao;
import com.genscript.gsscm.order.dao.OrderServiceDao;
import com.genscript.gsscm.order.dao.OrderSirnaAndMirnaDao;
import com.genscript.gsscm.order.dto.OrderMainDTO;
import com.genscript.gsscm.order.dto.OrderServiceDTO;
import com.genscript.gsscm.order.entity.Document;
import com.genscript.gsscm.order.entity.MfgOrder;
import com.genscript.gsscm.order.entity.MfgOrderItem;
import com.genscript.gsscm.order.entity.OrderAddress;
import com.genscript.gsscm.order.entity.OrderAntibody;
import com.genscript.gsscm.order.entity.OrderCustCloning;
import com.genscript.gsscm.order.entity.OrderDnaSequencing;
import com.genscript.gsscm.order.entity.OrderGeneSynthesis;
import com.genscript.gsscm.order.entity.OrderItem;
import com.genscript.gsscm.order.entity.OrderItemCenterBean;
import com.genscript.gsscm.order.entity.OrderMain;
import com.genscript.gsscm.order.entity.OrderMutagenesis;
import com.genscript.gsscm.order.entity.OrderOligo;
import com.genscript.gsscm.order.entity.OrderPeptide;
import com.genscript.gsscm.order.entity.OrderPkgService;
import com.genscript.gsscm.order.entity.OrderPlasmidPreparation;
import com.genscript.gsscm.order.entity.OrderSirnaAndMirna;
import com.genscript.gsscm.privilege.dao.EmployeeDao;
import com.genscript.gsscm.privilege.dao.UserDao;
import com.genscript.gsscm.privilege.dao.UserRoleDao;
import com.genscript.gsscm.privilege.entity.EmailGroup;
import com.genscript.gsscm.privilege.entity.Employee;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.product.dao.ProductClassDao;
import com.genscript.gsscm.product.dao.ProductDao;
import com.genscript.gsscm.product.entity.Product;
import com.genscript.gsscm.product.entity.ProductClass;
import com.genscript.gsscm.quoteorder.dto.OrderCustCloningDTO;
import com.genscript.gsscm.quoteorder.dto.OrderGeneSynthesisDTO;
import com.genscript.gsscm.quoteorder.dto.OrderPlasmidPreparationDTO;
import com.genscript.gsscm.serv.dao.ServiceClassDao;
import com.genscript.gsscm.serv.dao.ServiceClassificationDao;
import com.genscript.gsscm.serv.dao.ServiceDao;
import com.genscript.gsscm.serv.entity.ServiceClass;
import com.genscript.gsscm.serv.entity.ServiceClassification;
import com.genscript.gsscm.system.dao.UpdateRequestLogDao;
import com.genscript.gsscm.system.dto.MailTemplatesDTO;
import com.genscript.gsscm.system.entity.UpdateRequestLog;
import com.genscript.gsscm.systemsetting.dao.EmailGroupDao;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.rtf.RtfWriter2;
import com.opensymphony.xwork2.ActionContext;

@Service
@Transactional
public class WorkOrderEntryService {
	/**
	 * spring注入workOrderDao对象
	 */
	@Autowired
	private WorkOrderDao workOrderDao;
	@Autowired
	private SalesRepDao salesRepDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private WorkOrderOperationDao workOrderOperationDao;
	@Autowired
	private WoOperationResourceDao woOperationResourceDao;
	@Autowired
	private WoOperationComponentDao woOperationComponentDao;
	@Autowired
	private QaClerkDao qaClerkDao;
	@Autowired
	private QaGroupDao qaGroupDao;
	@Autowired
	private ManuDocumentDao manuDocumentDao;
	@Autowired
	private WoBatcheDao woBatcheDao;
	@Autowired
	private WoBatchDetailDao woBatchDetailDao;
	@Autowired
	private WorkGroupDao workGroupDao;
	@Autowired
	private DozerBeanMapper dozer;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ServiceDao serviceDao;
	@Autowired
	private OrderItemDao orderItemDao;
	@Autowired
	private ServiceClassificationDao serviceClassificationDao;
	@Autowired
	private ProductClassDao productClassDao;
	@Autowired
	private WoStatusHistoryDao woStatusHistoryDao;
	@Autowired
	private UserRoleDao userRoleDao;
	@Autowired
	private OrderItemCenterBeanDao orderItemCenterBeanDao;
	@Autowired
	private WorkOrderGroupDao workOrderGroupDao;
	@Autowired
	private WorkCenterDao workCenterDao;
	@Autowired
	private MimeMailService mimeMailService;
	@Autowired
	private ManufacturingClerksDao manufacturingClerksDao;
	@Autowired
	private MfgOrderDao mfgOrderDao;
	@Autowired
	private MfgOrderItemDao mfgOrderItemDao;
	@Autowired
	private WorkCenterAssignedDao workCenterAssignedDao;
	@Autowired
	private OperationDao operationDao;
	@Autowired
	private AntibodyOprExperimentDatasDao antibodyOprExperimentDatasDao;
	@Autowired
	private AntibodyOprPurificationResultsDao antibodyOprPurificationResultsDao;
	@Autowired
	private ServiceClassDao serviceClassDao;
	@Autowired
	private OrderGeneSynthesisDao orderGeneSynthesisDao;
	@Autowired
	private OrderCustCloningDao orderCustCloningDao;
	@Autowired
	private OrderPlasmidPreparationDao orderPlasmidPreparationDao;
	@Autowired
	private OrderMutagenesisDao orderMutagenesisDao;
	@Autowired
	private OrderAntibodyDao orderAntibodyDao;
	@Autowired
	private OrderPeptideDao orderPeptideDao;
	@Autowired
	private OrderOligoDao orderOligoDao;
	@Autowired
	private OrderSirnaAndMirnaDao orderSirnaAndMirnaDao;
	@Autowired
	private OrderServiceDao orderServiceDao;
	@Autowired
	private OrderPkgServiceDao orderPkgServiceDao;
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private OrderAddressDao orderAddressDao;
	@Autowired
	private QaGroupAssignedDao qaGroupAssignedDao;
	@Autowired
	private RouteDao routeDao;
	@Autowired
	private RouteOperationDao routeOperationDao;
	@Autowired
	private EmailGroupDao emailGroupDao;
	@Autowired
	private WorkOrderLotDao workOrderLotDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private UpdateRequestLogDao updateRequestLogDao;
	@Autowired
	private OrderDnaSequencingDao orderDnaSequencingDao;
	@Autowired
	private DsPlateItemsDao dsPlateItemsDao;


	/**
	 * 根据条件查找并分页显示, 如果查找条件为null, 则返回所有结果并分页显示. 很复杂的一个查询,
	 * 会有两个or查询，一个in查询(框架默认不支持)
	 * 
	 * @param page
	 * @param filters
	 * @param batchFunction
	 *            打包类型(QA,QC)
	 * @return
	 */
	public Page<WorkOrder> searchWorkOrderPage(Page<WorkOrder> page,
			List<PropertyFilter> filters, String batchFunction,String roleName,boolean groupFilter)
			throws Exception {
		List<Integer> orderNoList = null;
		String batchNo = Struts2Util.getParameter("batchNo");
		if (batchNo != null && StringUtils.isNotBlank(batchNo)) {
			orderNoList = new ArrayList<Integer>();
			orderNoList = woBatchDetailDao.getOrderNoListByBatchNo(batchNo);
			if(orderNoList==null) {
				return page;
			}
		}
		String workGroupId = Struts2Util.getParameter("workGroupId");
		String workCenterId = Struts2Util.getParameter("filter_EQI_workCenterId");
		if("0".equals(workGroupId)) {
			List<Integer> orderNoList2 = workOrderDao.getWONoGroup();
			if(orderNoList2==null||orderNoList2.size()==0) {
				return page;
			}
			if(orderNoList!=null) {
				orderNoList.retainAll(orderNoList2);
			} else{
				orderNoList = orderNoList2;
			} 
		}else if(StringUtils.isNotEmpty(workGroupId)) {
			List<Integer> orderNoList2 = workOrderGroupDao.getOrderNoListByGroup(Integer.parseInt(workGroupId));
			if(orderNoList2==null||orderNoList2.size()==0) {
				return page;
			}
			if(orderNoList!=null) {
				orderNoList.retainAll(orderNoList2);
			} else{
				orderNoList = orderNoList2;
			} 
		} else if(batchFunction!=null&&"QC".equals(batchFunction)&&groupFilter){
			List<Integer> orderNoList2 = workOrderGroupDao.getOrderNoListByGroup(null);
			if(orderNoList2==null) {
				return page;
			}
			if(orderNoList!=null) {
				orderNoList.retainAll(orderNoList2);
			} else {
				orderNoList = orderNoList2;
			} 
			if(orderNoList==null||orderNoList.size()==0) {
				return page;
			}
		}
		if (filters == null) {
			filters = new ArrayList<PropertyFilter>();
		}
		List<Integer> workOrderNoList = this.dsPlateItemsDao.getAllWorkOrderNos();
		List<Integer> qGroupIdList = new ArrayList<Integer>();
		int len = filters.size();
		int i = 0;
		Set<Integer> soNoSet = new HashSet<Integer>();
		for (; i < len; i++) {
			PropertyFilter pf = filters.get(i);
			if (pf.getPropertyName().equals("qcGroup")) {
				qGroupIdList.add(Integer.parseInt(pf.getPropertyValue().toString()));
				PropertyFilter qGroupFilter = new PropertyFilter(
						"EQI_qcGroup",qGroupIdList);
				filters.remove(i);
				filters.add(i,qGroupFilter);
			} else if(pf.getPropertyName().equals("srcSoNo")) {
				if(pf.getPropertyValue()!=null&&StringUtil.isNumeric(pf.getPropertyValue().toString())) {
					List<Integer> soNoList = this.mfgOrderDao.findBySrcSoNo(Integer.parseInt(pf.getPropertyValue().toString()));
					if(soNoList!=null&&soNoList.size()>0) {
						soNoSet.addAll(soNoList);
					} else {
						soNoSet.add(Integer.parseInt(pf.getPropertyValue().toString()));
					}
				}
			} 
		}
		Map<String, Object> session = ActionContext.getContext().getSession();
		Object userName = session.get(StrutsActionContant.USER_NAME);
		if (!Constants.USERNAME_ADMIN.equals(userName)) {
			// 判断当前用户是否含有生产部经理角色
			boolean productionManager = userRoleDao
					.checkIsContainsManagerRole(roleName);
			if (!productionManager) {
				Object userId = session.get(StrutsActionContant.USER_ID);
				if(Constants.ROLE_QC_MANAGER.equals(roleName)) {
					List<Integer> groupIdList = new ArrayList<Integer>();
					List<QaClerk> qaclerkList = qaClerkDao.findBy("userId", userId);
					if (qaclerkList == null || qaclerkList.size()==0) {
						return page;
					}
					for(QaClerk qaclerk:qaclerkList) {
						Integer groupId = qaclerk.getGroupId();
						groupIdList.add(groupId);
					}
					if(qGroupIdList==null||qGroupIdList.size()==0) {
						qGroupIdList = groupIdList;
					} else if(groupIdList==null||groupIdList.size()==0) {
						return page;
					} else {
						qGroupIdList.retainAll(groupIdList);
					}
					if(qGroupIdList==null||qGroupIdList.size()==0) {
						return page;
					}
					PropertyFilter qGroupFilter = new PropertyFilter(
							"EQI_qcGroup",qGroupIdList);
					if(i>0) {
						filters.remove(i-1);
					}
					filters.add(qGroupFilter);
				} else if(StringUtils.isEmpty(workGroupId)){
					List<ManufacturingClerks> manufacturingClerksList = this.manufacturingClerksDao.findBy("clerkId", userId);
					if(manufacturingClerksList==null||manufacturingClerksList.size()==0) {
						return page;
					}
					List<Integer> orderNoList2 = new ArrayList<Integer>();
					for(ManufacturingClerks manufacturingClerks:manufacturingClerksList) {
						if(manufacturingClerks.getWorkGroupId()!=null) {
							List<Integer> list = workOrderGroupDao.getOrderNoListByGroup(manufacturingClerks.getWorkGroupId());
							if(list!=null&&list.size()>0){
								orderNoList2.addAll(list);
							}
						}
					}
					if(orderNoList2==null||orderNoList2.size()==0) {
						return page;
					}
					if(orderNoList!=null) {
						orderNoList.retainAll(orderNoList2);
					} else {
						orderNoList = orderNoList2;
					} 
					if(orderNoList==null||orderNoList.size()==0) {
						return page;
					}
				}
				page = this.workOrderDao.findPageByQC(page, filters,
						orderNoList,null,soNoSet,workOrderNoList,batchFunction);
			} else if(StringUtils.isEmpty(workCenterId)){
				List<WorkCenter> centerList = this.workCenterDao.findBy("supervisor", session.get(StrutsActionContant.USER_ID));
				List<Integer> centerIdList = new ArrayList<Integer>();
				if(centerList!=null&&centerList.size()>0) {
					for(WorkCenter workCenter:centerList) {
						centerIdList.add(workCenter.getId());
					}
				} else {
					List<ManufacturingClerks> manufacturingClerksList = this.manufacturingClerksDao.findBy("clerkId", session.get(StrutsActionContant.USER_ID));
					Set<Integer> centerIdSet = new HashSet<Integer>();
					if(manufacturingClerksList==null||manufacturingClerksList.size()==0) {
						return null;
					}
					for(ManufacturingClerks manufacturingClerks:manufacturingClerksList) {
						if (manufacturingClerks.getWorkCenterId() == null) {
							continue;
						}
						centerIdSet.add(manufacturingClerks.getWorkCenterId());
					}
					if(centerIdSet.isEmpty()) {
						return page;
					}
					centerIdList.addAll(centerIdSet);
				}
				page = this.workOrderDao.findPageByQC(page, filters,
						orderNoList,centerIdList,soNoSet,workOrderNoList,batchFunction);
			} else {
				page = this.workOrderDao.findPageByQC(page, filters,
						orderNoList,null,soNoSet,workOrderNoList,batchFunction);
			}
		} else {
			page = this.workOrderDao.findPageByQC(page, filters,
					orderNoList,null,soNoSet,workOrderNoList,batchFunction);
		}
		for (WorkOrder workOrder : page.getResult()) {
			if(workOrder.getWorkCenterId()!=null) {
				WorkCenter workCenter = this.workCenterDao.getById(workOrder.getWorkCenterId());
				workOrder.setWorkCenterName(workCenter!=null?workCenter.getName():null);
			}
			String woBatchNo = woBatchDetailDao.getBatchNoByOrderNo(workOrder
					.getOrderNo(), "QC");
			String woBatchQaNo = woBatchDetailDao.getBatchNoByOrderNo(workOrder
					.getOrderNo(), "QA");
			workOrder.setWoBatchQaNo(woBatchQaNo);
			workOrder.setWoBatchNo(woBatchNo);
			List<WorkOrderGroup> workOrderGroupList = this.workOrderGroupDao.findBy("orderNo", workOrder.getOrderNo());
			if(workOrderGroupList!=null&&workOrderGroupList.size()>0) {
				StringBuffer workGroupNames = null;
				for(WorkOrderGroup workOrderGroup:workOrderGroupList) {
					if (workOrderGroup.getWorkGroupId() != null) {
						WorkGroup workGroup = workGroupDao.getById(workOrderGroup
								.getWorkGroupId());
						if (workGroup != null&&workGroupNames==null) {
							workGroupNames = new StringBuffer();
							workGroupNames.append(workGroup.getName());
						} else if(workGroup!=null){
							workGroupNames.append(",").append(workGroup.getName());
						}
					}
				}
				workOrder.setWorkGroupName(workGroupNames!=null?workGroupNames.toString():"");
			}
			if(StringUtils.isNotEmpty(workOrder.getItemType())&&StringUtils.isNotEmpty(workOrder.getCatalogNo())) {
				String catalogNoDesc = "";
				String shippable = "";
				if (workOrder.getItemType().toLowerCase().equals("service")) {
					com.genscript.gsscm.serv.entity.Service serv = this.serviceDao.findUniqueBy("catalogNo", workOrder.getCatalogNo());
					catalogNoDesc = serv!=null?((serv.getCatalogNo()!=null?serv.getCatalogNo():"")+"-"+(serv.getName()!=null?serv.getName():"")):"";
					shippable=serv!=null?serv.getShippable():"";
				} else {
					Product product = this.productDao.findUniqueBy("catalogNo", workOrder.getCatalogNo());
					catalogNoDesc = product!=null?((product.getCatalogNo()!=null?product.getCatalogNo():"")+"-"+(product.getName()!=null?product.getName():"")):"";
					shippable = product!=null?product.getShippable():"";
				}
				workOrder.setShippable(shippable);
				workOrder.setCatalogNoDesc(catalogNoDesc);
			}
			MfgOrder mfgOrder = this.mfgOrderDao.getById(workOrder.getSoNo());
			workOrder.setSrcSoNo(mfgOrder!=null?mfgOrder.getSrcSoNo():workOrder.getSoNo());
			
		}
		return page;
	}

    //add by zhanghuibin
    public ArrayList<WorkOrderExcelDTO> searchWorkOrderPageForExcel(Page<WorkOrder> page, List<PropertyFilter> filters)
			throws Exception {
        ArrayList<WorkOrderExcelDTO> workOrderList = new ArrayList<WorkOrderExcelDTO>();
		List<Integer> orderNoList = null;
		String workGroupId = Struts2Util.getParameter("workGroupId");
		if("0".equals(workGroupId)) {
			List<Integer> orderNoList2 = workOrderDao.getWONoGroup();
			if(orderNoList2==null||orderNoList2.size()==0) {
				return workOrderList;
			}
			if(orderNoList!=null) {
				orderNoList.retainAll(orderNoList2);
			} else{
				orderNoList = orderNoList2;
			}
		}else if(StringUtils.isNotEmpty(workGroupId)) {
			List<Integer> orderNoList2 = workOrderGroupDao.getOrderNoListByGroup(Integer.parseInt(workGroupId));
			if(orderNoList2==null||orderNoList2.size()==0) {
				return workOrderList;
			}
			if(orderNoList!=null) {
				orderNoList.retainAll(orderNoList2);
			} else{
				orderNoList = orderNoList2;
			}
		}
		if (filters == null) {
			filters = new ArrayList<PropertyFilter>();
		}
		List<Integer> qGroupIdList = new ArrayList<Integer>();
		int i = 0;
		Set<Integer> soNoSet = new HashSet<Integer>();
		for (; i < filters.size(); i++) {
			PropertyFilter pf = filters.get(i);
			if(pf.getPropertyName().equals("srcSoNo")) {
				if(pf.getPropertyValue()!=null&&StringUtil.isNumeric(pf.getPropertyValue().toString())) {
					List<Integer> soNoList = this.mfgOrderDao.findBySrcSoNo(Integer.parseInt(pf.getPropertyValue().toString()));
					if(soNoList!=null&&soNoList.size()>0) {
						soNoSet.addAll(soNoList);
					} else {
						soNoSet.add(Integer.parseInt(pf.getPropertyValue().toString()));
					}
				}
			}
		}
		Map<String, Object> session = ActionContext.getContext().getSession();
		Object userName = session.get(StrutsActionContant.USER_NAME);
		if (!Constants.USERNAME_ADMIN.equals(userName)) {
            String roleName = Constants.ROLE_WOASSIGN_MANAGER;
			//判断当前用户是否含有生产部经理角色
			boolean productionManager = userRoleDao.checkIsContainsManagerRole(roleName);
			if (!productionManager) {
				Object userId = session.get(StrutsActionContant.USER_ID);
				if(Constants.ROLE_QC_MANAGER.equals(roleName)) {
					List<Integer> groupIdList = new ArrayList<Integer>();
					List<QaClerk> qaclerkList = qaClerkDao.findBy("userId", userId);
					if (qaclerkList == null || qaclerkList.size()==0) {
						return workOrderList;
					}
					for(QaClerk qaclerk:qaclerkList) {
						Integer groupId = qaclerk.getGroupId();
						groupIdList.add(groupId);
					}
					if(qGroupIdList==null||qGroupIdList.size()==0) {
						qGroupIdList = groupIdList;
					} else if(groupIdList==null||groupIdList.size()==0) {
						return workOrderList;
					} else {
						qGroupIdList.retainAll(groupIdList);
					}
					if(qGroupIdList==null||qGroupIdList.size()==0) {
						return workOrderList;
					}
					PropertyFilter qGroupFilter = new PropertyFilter("EQI_qcGroup",qGroupIdList);
					if(i>0) {
						filters.remove(i-1);
					}
					filters.add(qGroupFilter);
				} else {
					List<ManufacturingClerks> manufacturingClerksList = this.manufacturingClerksDao.findBy("clerkId", userId);
					if(manufacturingClerksList==null||manufacturingClerksList.size()==0) {
						return workOrderList;
					}
					List<Integer> orderNoList2 = new ArrayList<Integer>();
					for(ManufacturingClerks manufacturingClerks:manufacturingClerksList) {
						if(manufacturingClerks.getWorkGroupId()!=null) {
							List<Integer> list = workOrderGroupDao.getOrderNoListByGroup(manufacturingClerks.getWorkGroupId());
							if(list!=null&&list.size()>0){
								orderNoList2.addAll(list);
							}
						}
					}
					if(orderNoList2==null||orderNoList2.size()==0) {
						return workOrderList;
					}
					if(orderNoList!=null) {
						orderNoList.retainAll(orderNoList2);
					} else {
						orderNoList = orderNoList2;
					}
					if(orderNoList==null||orderNoList.size()==0) {
						return workOrderList;
					}
				}
				page = this.workOrderDao.findPageByQC(page, filters, orderNoList,null,soNoSet,null,"QD");
			} else {
				List<WorkCenter> centerList = this.workCenterDao.findBy("supervisor", session.get(StrutsActionContant.USER_ID));
				List<Integer> centerIdList = new ArrayList<Integer>();
				if(centerList!=null&&centerList.size()>0) {
					for(WorkCenter workCenter:centerList) {
						centerIdList.add(workCenter.getId());
					}
				} else {
					List<ManufacturingClerks> manufacturingClerksList = this.manufacturingClerksDao.findBy("clerkId", session.get(StrutsActionContant.USER_ID));
					Set<Integer> centerIdSet = new HashSet<Integer>();
					if(manufacturingClerksList==null||manufacturingClerksList.size()==0) {
						return null;
					}
					for(ManufacturingClerks manufacturingClerks:manufacturingClerksList) {
						if (manufacturingClerks.getWorkCenterId() == null) {
							continue;
						}
						centerIdSet.add(manufacturingClerks.getWorkCenterId());
					}
					if(centerIdSet.isEmpty()) {
						return workOrderList;
					}
					centerIdList.addAll(centerIdSet);
				}
				page = this.workOrderDao.findPageByQC(page, filters, orderNoList,centerIdList,soNoSet,null,"QD");
			}
		} else {
			page = this.workOrderDao.findPageByQC(page, filters, orderNoList,null,soNoSet,null,"QD");
		}
		for (WorkOrder workOrder : page.getResult()) {
            WorkOrderExcelDTO workOrderExcelDTO = new WorkOrderExcelDTO();
			List<WorkOrderGroup> workOrderGroupList = this.workOrderGroupDao.findBy("orderNo", workOrder.getOrderNo());
			if(workOrderGroupList!=null&&workOrderGroupList.size()>0) {
				StringBuffer workGroupNames = null;
				for(WorkOrderGroup workOrderGroup:workOrderGroupList) {
					if (workOrderGroup.getWorkGroupId() != null) {
						WorkGroup workGroup = workGroupDao.getById(workOrderGroup.getWorkGroupId());
						if (workGroup != null&&workGroupNames==null) {
							workGroupNames = new StringBuffer();
							workGroupNames.append(workGroup.getName());
						} else if(workGroup!=null){
							workGroupNames.append(",").append(workGroup.getName());
						}
					}
				}
				workOrderExcelDTO.setWorkGroupName(workGroupNames != null ? workGroupNames.toString() : "");
			}
            //add by zhanghuibin
            workOrderExcelDTO.setTargetDate(DateUtils.formatDate2Str(workOrder.getScheduleEnd(), "MM/dd/yyyy"));
			MfgOrder mfgOrder = this.mfgOrderDao.getById(workOrder.getSoNo());
            Object[] objects = null;
			if(mfgOrder==null) {
				objects = this.orderItemDao.getGeneDetailForExcel(workOrder.getSoNo(), workOrder.getSoItemNo());
			} else {
				objects = this.orderItemDao.getGeneDetailForExcel(mfgOrder.getSrcSoNo(), workOrder.getSoItemNo());
			}
            if(objects == null ) continue;
            OrderItem orderItem = (OrderItem)objects[0];
			if(orderItem==null) {
				workOrderExcelDTO.setItemDesc("");
				continue;
			}
            workOrderExcelDTO.setOrderNo(orderItem.getOrderNo() + "_" + workOrder.getSoItemNo());
            workOrderExcelDTO.setCost("" + orderItem.getCost());
            String itemType = getOrderItemType(orderItem);
            if (itemType.toLowerCase().startsWith("gene")) {
                Map<String, String> geneDetailMap = getGeneItemDescForExcel(orderItem);
                workOrderExcelDTO.setItemDesc(geneDetailMap.get("desc") == null ? "" : geneDetailMap.get("desc"));
                workOrderExcelDTO.setSquenceLength(geneDetailMap.get("length") == null ? "" : geneDetailMap.get("length"));
            }else{
               //不是gene不计入
               continue;
            }
            workOrderExcelDTO.setConfirmDate(objects[1] == null ? "" : objects[1].toString());
            workOrderExcelDTO.setTam(objects[2] == null ? "" : objects[2].toString());
            workOrderList.add(workOrderExcelDTO);
		}
		return workOrderList;
	}

	/**
	 * 获得QC页面中需要的其它信息
	 * 
	 * @param page
	 */
	public Page<WorkOrder> qcViewOtherInfo(Page<WorkOrder> page) {
		for (WorkOrder workOrder : page.getResult()) {
			if (workOrder.getQcGroup() != null) {
				QaGroup qcGroup = this.qaGroupDao.getById(workOrder.getQcGroup());
				workOrder.setQcGroupName(qcGroup!=null?qcGroup.getGroupName():"");
			}
			if (workOrder.getQcClerk() != null) {
				QaClerk qcClerk = this.qaClerkDao.getById(workOrder.getQcClerk());
				User superUser = (qcClerk!=null&&qcClerk.getUserId()!=null)?this.userDao.getById(qcClerk.getUserId()):null;
				if (superUser != null) {
					workOrder.setQcClerkName(superUser.getLoginName());
				}
			}
			if (workOrder.getQaGroup() != null) {
				QaGroup qaGroup = this.qaGroupDao.getById(workOrder.getQaGroup());
				workOrder.setQaGroupName(qaGroup!=null?qaGroup.getGroupName():"");
			}
			if (workOrder.getQaClerk() != null) {
				QaClerk qaClerk = this.qaClerkDao.getById(workOrder.getQaClerk());
				User superUser = (qaClerk!=null&&qaClerk.getUserId()!=null)?this.userDao.getById(qaClerk.getUserId()):null;
				if (superUser != null) {
					workOrder.setQaClerkName(superUser.getLoginName());
				}
			}
		}
		return page;
	}

	/**
	 * 获得WorkOrder
	 * 
	 * @param id
	 * @return
	 */
	public WorkOrderDTO getWorkOrder(Integer id){
		WorkOrderDTO dto = null;
		WorkOrder workOrder = this.workOrderDao.getById(id);
		dto = dozer.map(workOrder, WorkOrderDTO.class);
		User temp = this.userDao.getById(dto.getModifiedBy());
		if (temp != null) {
			dto.setModifyUser(temp.getLoginName());
		}
		if(dto.getSoNo()!=null) {
			MfgOrder mfgOrder = this.mfgOrderDao.getById(dto.getSoNo());
			dto.setSrcSoNo(mfgOrder!=null?mfgOrder.getSrcSoNo():null);
		}
		if (dto.getSrcSoNo() != null) {
			OrderMain order = this.orderDao.getById(dto.getSrcSoNo());
			if (order!=null&&order.getSalesContact() != null) {
				SalesRep salesContactUser = this.salesRepDao.getById(order
						.getSalesContact());
				if (salesContactUser != null) {
					User user = this.userDao.getById(salesContactUser.getSalesId());
					dto.setSalesContact(user!=null?((user.getFirstName()!=null?user.getFirstName():"")+(" "+user.getLastName()!=null?user.getLastName():"")):"");
				}
			}
			if (order!=null&&order.getProjectManager() != null) {
				SalesRep projectManagerUser = this.salesRepDao.getById(order.getProjectManager());
				if (projectManagerUser != null) {
					User user = this.userDao.getById(projectManagerUser.getSalesId());
					dto.setProjectSupport(user!=null?((user.getFirstName()!=null?user.getFirstName():"")+(" "+user.getLastName()!=null?user.getLastName():"")):"");
				}
			}
			if(order!=null&&order.getTechSupport()!=null) {
				SalesRep techSupportUser = this.salesRepDao.getById(order.getTechSupport());
				if(techSupportUser!=null) {
					User user = this.userDao.getById(techSupportUser.getSalesId());
					dto.setTechSupport(user!=null?((user.getFirstName()!=null?user.getFirstName():"")+(" "+user.getLastName()!=null?user.getLastName():"")):"");
				}
			}
		}
		// 获得DocumentList
		List<ManuDocument> manuDocumentList = manuDocumentDao.getDocumentList(id,
				DocumentType.MANU_WORKORDER);
		if(manuDocumentList!=null&&manuDocumentList.size()>0) {
			for(ManuDocument manuDocument:manuDocumentList) {
				User user = this.userDao.getById(manuDocument.getModifiedBy());
                if (user == null) {
                    manuDocument.setModifyUser("");
                } else {
                    manuDocument.setModifyUser(user.getLoginName());
                }
			}
		}
		dto.setDocumentList(manuDocumentList);
		//获得workOrderLotList
		List<WorkOrderLot> workOrderLotList = workOrderLotDao.getWorkOrderLotByWo(id);
		for(WorkOrderLot lot:workOrderLotList) {
			User user = this.userDao.getById(lot.getCreatedBy());
			lot.setCreatedByName(user!=null?user.getLoginName():"");
		}
		dto.setWorkOrderLotList(workOrderLotList);
		return dto;
	}

	/**
	 * 对WorkOrder的基本信息进行扩展.
	 * 
	 * @param dto
	 */
	public void getWorkOrderExtraInfo(WorkOrderDTO dto) {
		// 设置WorkCenter super的可选项.
		List<DropDownDTO> workCenterSuperList = new ArrayList<DropDownDTO>();
		if (dto.getWorkCenterSpvs() != null) {
			User superUser = this.userDao.getById(dto.getWorkCenterSpvs());
			if (superUser != null) {
				DropDownDTO centerSuper = new DropDownDTO();
				centerSuper.setName(superUser.getFirstName()!=null?superUser.getFirstName():""+" "+superUser.getLastName()!=null?superUser.getLastName():"");
				centerSuper.setValue(dto.getWorkCenterSpvs() + "");
				workCenterSuperList.add(centerSuper);
			}
		}
		dto.setWorkCenterSuperList(workCenterSuperList);
		List<WorkOrderGroup> workOrderGroupList = workOrderGroupDao.findBy("orderNo", dto.getOrderNo());
		StringBuffer workGroupNameBuf = null;
		StringBuffer workGroupIdBuf = null;
		if(workOrderGroupList!=null&&workOrderGroupList.size()>0) {
			for(WorkOrderGroup workOrderGroup:workOrderGroupList) {
				WorkGroup workGroup = workGroupDao.getById(workOrderGroup.getWorkGroupId());
				if(workGroupNameBuf==null&&workGroup!=null) {
					workGroupNameBuf = new StringBuffer(workGroup.getName());
					workGroupIdBuf = new StringBuffer(String.valueOf(workGroup.getId()));
				} else if(workGroup!=null) {
					workGroupNameBuf.append(",").append(workGroup.getName());
					workGroupIdBuf.append(",").append(String.valueOf(workGroup.getId()));
				}
			}
			dto.setWorkGroupNames(workGroupNameBuf!=null?workGroupNameBuf.toString():"");
			dto.setWorkGroupIds(workGroupIdBuf!=null?workGroupIdBuf.toString():"");
		}
		if(StringUtils.isNotEmpty(dto.getItemType())&&StringUtils.isNotEmpty(dto.getCatalogNo())) {
			String catalogNoDesc = "";
			if (dto.getItemType().toLowerCase().equals("service")) {
				com.genscript.gsscm.serv.entity.Service serv = this.serviceDao.findUniqueBy("catalogNo", dto.getCatalogNo());
				catalogNoDesc = serv!=null?((serv.getCatalogNo()!=null?serv.getCatalogNo():"")+"-"+(serv.getName()!=null?serv.getName():"")):"";
			} else {
				Product product = this.productDao.findUniqueBy("catalogNo", dto.getCatalogNo());
				catalogNoDesc = product!=null?((product.getCatalogNo()!=null?product.getCatalogNo():"")+"-"+(product.getName()!=null?product.getName():"")):"";
			}
			dto.setCatalogNoDesc(catalogNoDesc);
		}
	}

	public void save(WorkOrderDTO dto, Integer loginUserId) {
		WorkOrder workOrder = dozer.map(dto, WorkOrder.class);
		workOrder.setModifiedBy(loginUserId);
		workOrder.setModifyDate(new Date());
		// 保存WorkOrder之前为WorkTime进行预处理.
		this.prevSaveForWorkTime(workOrder,false);
		
		String mailTo = null;
		if(workOrder.getOrderNo()==null&&workOrder.getSoNo()!=null&& workOrder.getSoItemNo()!=null) {
			
			if(workOrder.getWorkCenterSpvs()!=null) {
				User user = this.userDao.getById(workOrder.getWorkCenterSpvs());
				if(user!=null&&user.getEmail()!=null) {
					mailTo = user.getEmail();
				}
			}
			
			MfgOrder order = this.mfgOrderDao.getById(workOrder.getSoNo());
			OrderMain orderMain = this.orderDao.getById(order!=null?order.getSrcSoNo():workOrder.getSoNo());
			if(order!=null&&(OrderStatusType.NW.value().equals(order.getStatus())||OrderStatusType.OP.value().equals(order.getStatus()))) {
				order.setStatus(OrderStatusType.IP.value());
				this.mfgOrderDao.save(order);
			}
			if(orderMain!=null&&(OrderStatusType.NW.value().equals(orderMain.getStatus())||OrderStatusType.OP.value().equals(orderMain.getStatus()))) {
				orderMain.setStatus(OrderStatusType.IP.value());
				this.orderDao.save(orderMain);
			}
		}
		if(workOrder.getOrderNo()==null) {
			Integer seqNo = this.workOrderDao.getTodayMaxSeqNo();
			workOrder.setSeqNo(seqNo!=null?seqNo++:1);
		}
		if(StringUtils.isNotEmpty(dto.getParentOrderNo())) {
			workOrder.setAltOrderNo(dto.getParentOrderNo());
		}
		this.workOrderDao.save(workOrder);
		if(StringUtils.isEmpty(workOrder.getAltOrderNo())) {
			workOrder.setAltOrderNo(String.valueOf(workOrder.getOrderNo()));
			this.workOrderDao.save(workOrder);
		}
		//发送邮件
		if(mailTo!=null) {
			String content = "Dear manager:<br><br>&nbsp;&nbsp;your work center  get a work order:#"+workOrder.getOrderNo();
			String subject = "Create work order success";
			mimeMailService.sendMail(mailTo, subject, content,"scm_admin@genscriptcorp.com");
		}
		dto.setOrderNo(workOrder.getOrderNo());
//		if(StringUtils.isNotEmpty(dto.getWorkGroupIds())) {
//			String[] workGroupIdArray =  dto.getWorkGroupIds().split(",");
//			for(String workGroupId:workGroupIdArray) {
//				WorkGroup workGroup = this.workGroupDao.getById(Integer.parseInt(workGroupId));
//				WorkOrderGroup workOrderGroup = new WorkOrderGroup();
//				workOrderGroup.setCreatedBy(SessionUtil.getUserId());
//				workOrderGroup.setModifiedBy(SessionUtil.getUserId());
//				workOrderGroup.setWorkGroupId(workGroup.getId());
//				workOrderGroup.setWorkGroupSpvs(workGroup.getSupervisor());
//				workOrderGroup.setOrderNo(dto.getOrderNo());
//				this.workOrderGroupDao.save(workOrderGroup);
//			}
//		}
		this.attachSaveOperation(dto, workOrder, loginUserId);
//		if (workOrder.getStatus().equals("Completed")) {
//			this.workOrderOperationDao.batchCompleted(workOrder.getOrderNo());
//		}

		// 保存Document.
		{
			List<Integer> delDocIdList = dto.getDelDocIdList();
			if (delDocIdList != null && !delDocIdList.isEmpty()) {
				this.manuDocumentDao.delDocumentList(delDocIdList);
			}
			String docType = DocumentType.MANU_WORKORDER.value();
			for (ManuDocument ors : dto.getDocumentList()) {
				ors.setCreatedBy(loginUserId);
				ors.setModifiedBy(loginUserId);
				ors.setCreationDate(new Date());
				ors.setModifyDate(new Date());
				ors.setRefType(docType);
				ors.setRefId(workOrder.getOrderNo());
				this.manuDocumentDao.save(ors);
			}
		}
		
		List<Integer> delLotIdList = dto.getDelWorkOrderLotList();
		if(delLotIdList!=null&&delLotIdList.size()>0) {
			this.workOrderLotDao.updateLotList(delLotIdList);
		}
		for(WorkOrderLot lot:dto.getWorkOrderLotList()) {
			lot.setModifiedBy(loginUserId);
			lot.setModifyDate(new Date());
			lot.setWorkOrderNo(dto.getOrderNo());
			this.workOrderLotDao.save(lot);
		}
		if(StringUtils.isNotEmpty(dto.getReason())) {
			WoStatusHistory woStatus = new WoStatusHistory();
			woStatus.setOrderNo(dto.getOrderNo());
			woStatus.setUpdateDate(new Date());
			woStatus.setUpdatedBy(SessionUtil.getUserId());
			woStatus.setStatusType("WORK_ORDER");
			woStatus.setStatus(dto.getStatus());
			woStatus.setUpdateReason(dto.getReason());
			this.woStatusHistoryDao.save(woStatus);
		}
	}
	
	public WorkOrder refreshTime(Integer workOrderNo) {
		WorkOrder workOrder = this.workOrderDao.getById(workOrderNo);
		this.prevSaveForWorkTime(workOrder,true);
		return workOrder;
	}

    /*
    保存document
     */

    public void saveDocument(ManuDocument source, Integer orderNo, Integer loginUserId) {
        //这里传人的是work_order_no
        ManuDocument ors = dozer.map(source, ManuDocument.class);
        String docType = DocumentType.MANU_WORKORDER.value();
        ors.setCreatedBy(loginUserId);
        ors.setModifiedBy(loginUserId);
        ors.setCreationDate(new Date());
        ors.setModifyDate(new Date());
        ors.setRefType(docType);
        ors.setRefId(orderNo);
        this.manuDocumentDao.save(ors);
    }

	/**
	 * 保存WorkOrder之前为WorkTime进行预处理.
	 * 
	 * @author wangsf
	 * @serialData 2010-12-21
	 * @param workOrder
	 */
	private void prevSaveForWorkTime(WorkOrder workOrder,boolean isRefresh) {
		if (workOrder.getScheduleStart() == null) {
			workOrder.setScheduleStart(new java.sql.Date(new java.util.Date().getTime()));
		}
		if(workOrder.getScheduleEnd()==null||isRefresh) {
			Integer leadTime = null;
			OrderItem orderItem = this.orderItemDao.getOrderItem(workOrder.getSrcSoNo()!=null?workOrder.getSrcSoNo():workOrder.getSoNo(), workOrder.getSoItemNo());
			if(orderItem!=null) {
				leadTime = orderItem.getShipSchedule();
			}
			if(leadTime==null||leadTime.intValue()==0) {
				if ("SERVICE".equals(workOrder.getItemType())) {
					com.genscript.gsscm.serv.entity.Service server = serviceDao
							.getServiceByCatalogNo(workOrder.getCatalogNo());
					leadTime = server.getLeadTime();
				} else {
					Product product = this.productDao
							.getProductByCatalogNo(workOrder.getCatalogNo());
					leadTime = product.getLeadTime();
				}
			}
			if(leadTime==null) {
				leadTime = new Integer(0);
			}
			
			workOrder.setScheduleEnd(new java.sql.Date(DateUtils.dayBefore2Date(workOrder.getScheduleStart(), leadTime).getTime()));
		}
		if(workOrder.getCustomStart()==null) {
			workOrder.setCustomStart(workOrder.getScheduleStart());
		}
		if(workOrder.getCustomEnd()==null) {
			workOrder.setCustomEnd(workOrder.getScheduleEnd());
		}
		// 有的文档写成 In Production.
		if (workOrder.getActualStart() == null
				&& WorkOrderStatus.Inprogress.value().equals(workOrder.getStatus())) {
			workOrder.setActualStart(new java.sql.Date(new java.util.Date().getTime()));
		}
		if(workOrder.getStatus()!=null&&WorkOrderStatus.Completed.value().equals(workOrder.getStatus())) {
			workOrder.setActualEnd(new java.sql.Date(new java.util.Date().getTime()));
		}
	}

	public WorkOrder saveTaskWorkOrderEntry(WorkOrderDTO dto, Integer loginUserId) throws Exception{
		WorkOrder workOrder =dozer.map(dto, WorkOrder.class);
//		if (dto.getWorkGroupId() != null && dto.getWorkGroupId() > 0
//				&& dto.getWorkGroupSpvs() != null && dto.getWorkGroupSpvs() > 0) {
//			workOrder = this.workOrderDao.get(dto.getOrderNo());
//			workOrder.setWorkGroupId(dto.getWorkGroupId());
//			workOrder.setWorkGroupSpvs(dto.getWorkGroupSpvs());
//			workOrder.setModifiedBy(loginUserId);
//			workOrder.setModifyDate(new Date());
			/***生成lot**/
//			if(WorkOrderType.SERVICE.value().equals(workOrder.getItemType())) {
//				ServiceDTO serviceDto = servService.getServDetail(workOrder.getClsId());
//				type = serviceDto==null?null:serviceDto.getName();
//			}
//			if(!StringUtils.isNotBlank(workOrder.getLotNo())) {
//				workOrder.setLotNo(this.getLotNo(workOrder.getOrderNo(), orderNo, itemNo));
//			}
//			this.workOrderDao.save(workOrder);
//		}
		if(StringUtils.isNotEmpty(dto.getWorkGroupIds())) {
			workOrderGroupDao.deleteByOrderNo(dto.getOrderNo());
			String[] workGroupIdArray =  dto.getWorkGroupIds().split(",");
			for(String workGroupId:workGroupIdArray) {
				WorkGroup workGroup = this.workGroupDao.getById(Integer.parseInt(workGroupId));
				WorkOrderGroup workOrderGroup = new WorkOrderGroup();
				workOrderGroup.setCreatedBy(SessionUtil.getUserId());
				workOrderGroup.setModifiedBy(SessionUtil.getUserId());
				workOrderGroup.setWorkGroupId(workGroup.getId());
				workOrderGroup.setWorkGroupSpvs(workGroup.getSupervisor());
				workOrderGroup.setOrderNo(dto.getOrderNo());
				this.workOrderGroupDao.save(workOrderGroup);
			}
		}
		return workOrder;
	}
	
	/**
	 * 批量给work order分配组
	 * @param orderNos
	 * @param workGroupIds
	 * @param processFlag，判断该work order是否进入生产流程(0:进入   1:不进入)
	 * @return
	 */
	public Map<String,List<WorkOrder>> batchAssignGroup(String orderNos,String workGroupIds,String processFlag) {
		Map<String,List<WorkOrder>> resultMap = new HashMap<String,List<WorkOrder>>();
		List<WorkOrder> tubeWorkOrderList = new ArrayList<WorkOrder>();
		List<WorkOrder> plateWorkOrderList = new ArrayList<WorkOrder>();
		List<WorkOrder> workOrderList = new ArrayList<WorkOrder>();
		if(StringUtils.isEmpty(orderNos)) {
			resultMap.put("workOrderList", workOrderList);
			resultMap.put("tubeWorkOrderList", tubeWorkOrderList);
			resultMap.put("plateWorkOrderList", plateWorkOrderList);
			return resultMap;
		}
		for(String orderNo:orderNos.split(",")) {
			WorkOrder workOrder = this.workOrderDao.getById(Integer.parseInt(orderNo));
			if(workOrder==null) {
				continue;
			}
			MfgOrder mfgOrder = this.mfgOrderDao.getById(workOrder.getSoNo());
			OrderItem item = this.orderItemDao.getOrderItem(mfgOrder!=null?mfgOrder.getSrcSoNo():workOrder.getSoNo(), workOrder.getSoItemNo());
			if(item==null) {
				continue;
			}
			if (QuoteItemType.SERVICE.value().equals(item.getType())&&item.getClsId().intValue()==40){
				OrderDnaSequencing OrderDnaSequencing = orderDnaSequencingDao.getById(item.getOrderItemId());
				if(OrderDnaSequencing!=null) {
					if(OrderDnaSequencing.getCode().toLowerCase().startsWith("t")) {
						tubeWorkOrderList.add(workOrder);
					}
					if(OrderDnaSequencing.getCode().toLowerCase().startsWith("p")) {
						plateWorkOrderList.add(workOrder);
					}
				}
			}
			if(processFlag!=null&&"1".equals(processFlag)) {
				workOrder.setActualStart(new java.sql.Date(new Date().getTime()));
				workOrder.setActualEnd(new java.sql.Date(new Date().getTime()));
				workOrder.setStatus(WorkOrderStatus.Completed.value());
				this.workOrderDao.save(workOrder);
				UpdateRequestLog updateRequestLog = new UpdateRequestLog();
				updateRequestLog.setObjectEntity("Work Order");
				updateRequestLog.setField("NO Productive Process");
				updateRequestLog.setObjectId(Integer.parseInt(orderNo));
				updateRequestLog.setRequestDate(new Date());
				updateRequestLog.setRequestedBy(SessionUtil.getUserId());
				this.updateRequestLogDao.save(updateRequestLog);
			}
			workOrderList.add(workOrder);
			if(StringUtils.isNotEmpty(workGroupIds)) {
				workOrderGroupDao.deleteByOrderNo(workOrder.getOrderNo());
				String[] workGroupIdArray =  workGroupIds.split(",");
				for(String workGroupId:workGroupIdArray) {
					WorkGroup workGroup = this.workGroupDao.getById(Integer.parseInt(workGroupId));
					WorkOrderGroup workOrderGroup = new WorkOrderGroup();
					workOrderGroup.setCreatedBy(SessionUtil.getUserId());
					workOrderGroup.setModifiedBy(SessionUtil.getUserId());
					workOrderGroup.setWorkGroupId(workGroup.getId());
					workOrderGroup.setWorkGroupSpvs(workGroup.getSupervisor());
					workOrderGroup.setOrderNo(workOrder.getOrderNo());
					this.workOrderGroupDao.save(workOrderGroup);
				}
			}
		}
		resultMap.put("workOrderList", workOrderList);
		resultMap.put("tubeWorkOrderList", tubeWorkOrderList);
		resultMap.put("plateWorkOrderList", plateWorkOrderList);
		return resultMap;
	}

	/**
	 * 保存Work Order的同时保存Operation(包括 Resource, Component);
	 * 
	 * @param dto
	 * @param workOrder
	 * @param loginUserId
	 */
	private void attachSaveOperation(WorkOrderDTO dto, WorkOrder workOrder,
			Integer loginUserId) {
		// 保存WorkOrder Operation.
		List<Integer> delIdList = dto.getDelOperationIdList();
		// 如果Operation删除了， 则先删除它们的WoResource, WoComponent.
		if (delIdList != null && !delIdList.isEmpty()) {
			// 先删除Wo Resource
			woOperationResourceDao.delWoResourceByWoOPerationList(delIdList);
			woOperationComponentDao.delWoComponentByWoOPerationList(delIdList);
			// 再删除Wo Operation
			workOrderOperationDao.delWorkOrderOperationList(delIdList);
			antibodyOprExperimentDatasDao.delAntibodyOprExperimentDatasByWoOPerationList(delIdList);
			this.antibodyOprPurificationResultsDao.delAntibodyOprPurificationResultsByWoOPerationList(delIdList);
		}
		List<Integer> toStartSeqNoList = new ArrayList<Integer>();
		List<BigDecimal> runtimeList = new ArrayList<BigDecimal>();
		boolean changeStatus = false;
		if(StringUtils.isNotEmpty(dto.getReason())&&WorkOrderStatus.Completed.value().equals(dto.getStatus())) {
			changeStatus = true;
		}
		if(dto!=null&&dto.getWorkOrderOperationList()!=null) {
			for (WorkOrderOperation ors : dto.getWorkOrderOperationList()) {
				ors.setCreatedBy(loginUserId);
				ors.setModifiedBy(loginUserId);
				ors.setCreationDate(new Date());
				ors.setModifyDate(new Date());
				ors.setWorkOrderNo(workOrder.getOrderNo());
				if(changeStatus) {
					ors.setStatus(WoOperStatus.Completed.value());
					if(ors.getActualStartDate()==null) {
						ors.setActualStartDate(new java.sql.Date(new Date().getTime()));
					}
					if(ors.getActualEndDate()==null) {
						ors.setActualEndDate(new java.sql.Date(new Date().getTime()));
					}
				}
				// 对Wo operation进行一些预处理
				this.prevSaveWoOperation(ors, toStartSeqNoList, runtimeList);
				if (ors.getId() == null) {
					this.workOrderOperationDao.save(ors);
				} else {
					this.workOrderOperationDao.getSession().merge(ors);
				}

				if (ors.getDelWoResIdList() != null
						&& ors.getDelWoResIdList().size() > 0) {
					woOperationResourceDao.delOperationResourceList(ors
							.getDelWoResIdList());
				}
				// 级联保存Wo Resource
				for (WoOperationResource woRes : ors.getWoResourceList()) {
					woRes.setWoOperationId(ors.getId());
					woRes.setCreatedBy(loginUserId);
					woRes.setModifiedBy(loginUserId);
					woRes.setCreationDate(new Date());
					woRes.setModifyDate(new Date());
					woOperationResourceDao.save(woRes);
				}

				if (ors.getDelWoComIdList() != null
						&& ors.getDelWoComIdList().size() > 0) {
					woOperationComponentDao.delOperationComponentList(ors
							.getDelWoComIdList());
				}
				// 级联保存Wo Component
				for (WoOperationComponent woRes : ors.getWoComponentList()) {
					woRes.setWoOperationId(ors.getId());
					woRes.setCreatedBy(loginUserId);
					woRes.setModifiedBy(loginUserId);
					woRes.setCreationDate(new Date());
					woRes.setModifyDate(new Date());
					woOperationComponentDao.save(woRes);
				}
				if(ors.getAntibodyOprExperimentDatasMap()!=null&&ors.getAntibodyOprExperimentDatasMap().size()>0) {
					Set<Entry<String,AntibodyOprExperimentDatas>> set = ors.getAntibodyOprExperimentDatasMap().entrySet();
					for(Entry<String,AntibodyOprExperimentDatas> entry:set) {
						AntibodyOprExperimentDatas antibodyOprExperimentDatas = entry.getValue();
						antibodyOprExperimentDatas.setWoOperationId(ors.getId());
						this.antibodyOprExperimentDatasDao.save(antibodyOprExperimentDatas);
					}
				}
				if(StringUtils.isNotEmpty(ors.getDelResultIds())) {
					List<Integer> list = new ArrayList<Integer>();
					for(String id:ors.getDelResultIds().split(",")) {
						if(StringUtil.isNumeric(id)) {
							list.add(Integer.parseInt(id));
						}
					}
					this.antibodyOprPurificationResultsDao.delAntibodyOprPurificationResultsById(list);
				}
				if(ors.getAntibodyOprPurificationResultsMap()!=null&&ors.getAntibodyOprPurificationResultsMap().size()>0) {
					Set<Entry<String,AntibodyOprPurificationResults>> set = ors.getAntibodyOprPurificationResultsMap().entrySet();
					for(Entry<String,AntibodyOprPurificationResults> entry:set) {
						AntibodyOprPurificationResults antibodyOprPurificationResults = entry.getValue();
						antibodyOprPurificationResults.setRefId(ors.getId());
						this.antibodyOprPurificationResultsDao.save(antibodyOprPurificationResults);
					}
				}
				
			}
		}
		//对各个需要计划开始的wo operation, 更新它的(start/end) schedule date.
		this.startScheduleOperateion(workOrder.getOrderNo(), toStartSeqNoList,
				runtimeList);
	}

	/**
	 * 对Wo operation保存进行一些预处理
	 * @author wangsf
	 * @serialData 2010-12-24
	 * @param ors
	 * @param toStartSeqNoList
	 */
	private void prevSaveWoOperation(WorkOrderOperation ors,
			List<Integer> toStartSeqNoList, List<BigDecimal> runtimeList) {
		if (ors.getSeqNo() == 1 && ors.getExptdStartDate() == null) {
			ors.setExptdStartDate(new java.sql.Date(new Date().getTime()));// 第一条设置Schedule Start
		}
		if (ors.getSeqNo() == 1 && ors.getCustomStartDate() == null) {
			ors.setCustomStartDate(new java.sql.Date(new Date().getTime()));// 第一条设置Custom Start
		}
		if ("In Production".equals(ors.getStatus())) {
			ors.setActualStartDate(new java.sql.Date(new Date().getTime()));
		} else if ("Completed".equals(ors.getStatus())) {
			// 如果当前wo operation为Completed, 则它的下一下wo operation才计划开始
			toStartSeqNoList.add(ors.getSeqNo() + 1);
			runtimeList.add(ors.getOperation().getRunTime());
		}
	}

	/**
	 * 对各个需要计划开始的wo operation, 更新它的(start/end) schedule date.
	 * @author wangsf
	 * @serialData 2010-12-24
	 * @param workOrderNo
	 * @param toStartSeqNoList
	 * @param runtimeList
	 */
	private void startScheduleOperateion(Integer workOrderNo,
			List<Integer> toStartSeqNoList, List<BigDecimal> runtimeList) {
		if (toStartSeqNoList == null || toStartSeqNoList.isEmpty()) {
			return;
		}
		// 循环对各个需要计划开始的wo operation, 更新它的(start/end) schedule date.
		for (int i = 0; i < toStartSeqNoList.size(); i++) {
			workOrderOperationDao.startScheduleOperateion(workOrderNo,
					toStartSeqNoList.get(i), new Date(), DateUtils
							.dayBefore2Date(new Date(), runtimeList.get(i).intValue()));
		}
	}

	@Transactional(readOnly = true)
	public List<WorkOrderOperation> getAllWOOperation(Integer workOrderNo) {
		List<WorkOrderOperation> list = this.workOrderOperationDao
				.getAllList(workOrderNo);
		for (WorkOrderOperation ro : list) {
			Operation operation = ro.getOperation();
			if (operation != null) {// 可能为内部订单
				if (operation.getSupervisor() != null) {
					User superUser = this.userDao.getById(operation
							.getSupervisor());
					if (superUser != null) {
						operation.setSuperName(superUser.getLoginName());
					}
				}
				if (operation.getModifiedBy() != null) {
					User temp = this.userDao.getById(operation.getModifiedBy());
					if (temp != null) {
						operation.setModifyUser(temp.getLoginName());
					}
				}
			}
		}
		return list;
	}

	@Transactional(readOnly = true)
	public WorkOrderOperation getWoOperation(Integer woOperationId) {
		WorkOrderOperation woOperation = this.workOrderOperationDao
				.get(woOperationId);
		return woOperation;
	}

	@Transactional(readOnly = true)
	public List<QaGroup> getAllQaGroup() {
		return this.qaGroupDao.getAll();
	}
	
	@Transactional(readOnly = true)
	public List<QaGroup> getAllQaGroup(String roleName){
		List<QaGroup> result = new ArrayList<QaGroup>();
		Map<String, Object> session = ActionContext.getContext().getSession();
		Object userName = session.get(StrutsActionContant.USER_NAME);
		if (!Constants.USERNAME_ADMIN.equals(userName)) {
			boolean productionManager = userRoleDao.checkIsContainsManagerRole(roleName);
			if (!productionManager) {
				Object userId = session.get(StrutsActionContant.USER_ID);
				// 查询用户所在组Id
				List<QaClerk> qaclerkList = qaClerkDao.findBy("userId", userId);
				if (qaclerkList == null || qaclerkList.size()==0) {
					return result;
				}
				for(QaClerk qaclerk:qaclerkList) {
					Integer groupId = qaclerk.getGroupId();
					QaGroup qaGroup = this.qaGroupDao.getById(groupId);
					if(!result.contains(qaGroup)) {
						result.add(qaGroup);
					}
				}
				
			} else {
				List<QaGroup> centerList = this.qaGroupDao.findBy("supervisor", session.get(StrutsActionContant.USER_ID),Page.ASC,"groupName");
				result =  centerList;
			}
			return result;
		} 
		return  this.qaGroupDao.findAll(Page.ASC,"groupName");
	}
	
	public List<QaGroup> getQaGroup(WorkOrderDTO workOrderDTO) {
		List<String> itemTypeList = new LinkedList<String>();
		List<Integer> clsIdList = new LinkedList<Integer>();
		List<WorkCenterAssigned> list = this.workCenterAssignedDao.findBy("workCenter.id", workOrderDTO.getWorkCenterId());
		if(list==null) {
			return null;
		}
		for(WorkCenterAssigned workCenterAssigned:list) {
			itemTypeList.add(workCenterAssigned.getItemType());
			clsIdList.add(workCenterAssigned.getClsId());
		}
		if(itemTypeList.size()==0||clsIdList.size()==0) {
			itemTypeList.add(workOrderDTO.getItemType());
			clsIdList.add(workOrderDTO.getClsId());
		}
		return this.qaGroupDao.findByAssigned(clsIdList,itemTypeList);
	}

	@Transactional(readOnly = true)
	public List<WoBatche> getAllWoBatche() {
		return this.woBatcheDao.getAll();
	}

	public List<QaClerk> getClerkListByQa(Integer qaGroupId,String roleName) {
		List<QaClerk> result = new ArrayList<QaClerk>();
		if(qaGroupId==null) {
			return result;
		}
		Map<String, Object> session = ActionContext.getContext().getSession();
		Object userName = session.get(StrutsActionContant.USER_NAME);
		if (!Constants.USERNAME_ADMIN.equals(userName)) {
			boolean productionManager = true;
			if(roleName!=null&&StringUtils.isNotEmpty(roleName)) {
				productionManager = userRoleDao.checkIsContainsManagerRole(roleName);
			}
			if (!productionManager) {
				Object userId = session.get(StrutsActionContant.USER_ID);
				// 查询用户所在组Id
				result = qaClerkDao.getGroupList((Integer)userId,qaGroupId);
				
			}  else {
				result = this.qaClerkDao.getAllList(qaGroupId);
			}
		} else {
			result = this.qaClerkDao.getAllList(qaGroupId);
		}
		for(QaClerk qaClerk:result) {
			if(qaClerk.getUserId()!=null) {
				User user = this.userDao.getById(qaClerk.getUserId());
				qaClerk.setSuperName(user!=null?((user.getFirstName()!=null?user.getFirstName():"")+(" "+user.getLastName()!=null?user.getLastName():"")):"");
			}
		}
		return result;
	}
	
	public List<QaClerk> getClerkList(Integer qaGroupId) {
		List<QaClerk> result = new ArrayList<QaClerk>();
		if(qaGroupId==null) {
			return result;
		}
		result = this.qaClerkDao.findBy("groupId", qaGroupId);
		for(QaClerk qaClerk:result) {
			if(qaClerk.getUserId()!=null) {
				User user = this.userDao.getById(qaClerk.getUserId());
				qaClerk.setSuperName(user!=null?((user.getFirstName()!=null?user.getFirstName():"")+(" "+user.getLastName()!=null?user.getLastName():"")):"");
			}
		}
		return result;
	}

	/**
	 * 根据woOperationId获得Wo Resource list.
	 * 
	 * @param woOperationId
	 * @return
	 */
	public List<WoOperationResource> getWoResourceList(Integer woOperationId) {
		List<WoOperationResource> list = this.woOperationResourceDao
				.getAllList(woOperationId);
		for (WoOperationResource ro : list) {
			Resource res = ro.getResource();
			if (res != null) {
				User temp = this.userDao.getById(res.getModifiedBy());
				if (temp != null) {
					res.setModifyUser(temp.getLoginName());
				}
			}
		}
		return list;
	}

	/**
	 * 根据woOperationId获得WoOperationComponent list.
	 * 
	 * @param woOperationId
	 * @return
	 */
	public List<WoOperationComponent> getWoComponentList(Integer woOperationId) {
		List<WoOperationComponent> list = this.woOperationComponentDao
				.getAllList(woOperationId);
		for (WoOperationComponent ro : list) {
			User temp = this.userDao.getById(ro.getModifiedBy());
			if (temp != null) {
				ro.setModifyUser(temp.getLoginName());
			}
		}
		return list;
	}

	/**
	 * 通过WorkOrder表主键查询WorkOrder对象
	 * @author zhangyong
	 * @param workOrderNo
	 * @param orderNo
	 * @param itemNo
	 * @return
	 */
	public Map<String, String> getLotNo(Integer workOrderNo, Integer orderNo, Integer itemNo)
			throws Exception {
		Map<String, String> rtnMap = new HashMap<String, String>();
		String rtnMessage = "rtnMessage";
		if (workOrderNo == null || workOrderNo <= 0) {
			rtnMap.put(rtnMessage, "workOrderNo can not be null.");
			return rtnMap;
		}
		if (orderNo == null) {
			rtnMap.put(rtnMessage, "orderNo can not be null.");
			return rtnMap;
		}
		if (itemNo == null) {
			rtnMap.put(rtnMessage, "itemNo can not be null.");
			return rtnMap;
		}
		WorkOrder workOrder = workOrderDao.getById(workOrderNo);
		if (workOrder == null) {
			rtnMap.put(rtnMessage, "WorkOrder can not be found by workOrderNo:"+workOrderNo);
			return rtnMap;
		}
		if (StringUtils.isBlank(workOrder.getCatalogNo())) {
			rtnMap.put(rtnMessage, "In workOrder catalogNo can not be null.");
			return rtnMap;
		}
		if (workOrder.getOrderNo() == null) {
			rtnMap.put(rtnMessage, "In workOrder OrderNo can not be null.");
			return rtnMap;
		}
		List<WorkOrderGroup> workOrderGroupList = this.workOrderGroupDao.findBy("orderNo", workOrder.getOrderNo());
		if (workOrderGroupList == null || workOrderGroupList.isEmpty()) {
			rtnMap.put(rtnMessage, "WorkOrderGroup can not be found by workOrder'OrderNo:"+workOrder.getOrderNo());
			return rtnMap;
		}
		WorkGroup workGroup = workGroupDao.get(workOrderGroupList.get(0).getWorkGroupId());
		if (workGroup == null) {
			rtnMap.put(rtnMessage, "workGroup can not be found by WorkGroupId:"+workOrderGroupList.get(0).getWorkGroupId());
			return rtnMap;
		}
		OrderItem orderItem = orderItemDao.getOrderItem(orderNo, itemNo,
				workOrder.getCatalogNo());
		if (orderItem == null) {
			rtnMap.put(rtnMessage, "OrderItem can not be found by orderNo:"+orderNo+",itemNo:"+itemNo+",CatalogNo:"+workOrder.getCatalogNo());
			return rtnMap;
		}
		Integer clsId = null;
		String type = orderItem.getType();
		if (QuoteItemType.PRODUCT.value().equals(type)) {
			Product product = productDao.getProductByCatalogNo(workOrder.getCatalogNo());
			if (product == null) {
				rtnMap.put(rtnMessage, "Product can not be found by workOrder CatalogNo:"+workOrder.getCatalogNo());
				return rtnMap;
			}
			clsId = product.getProductClsId();
			if (clsId == null) {
				rtnMap.put(rtnMessage, "Product Id:"+product.getProductId()+" clsId is null.");
				return rtnMap;
			}
			ProductClass productClass = productClassDao.get(clsId);
			if (productClass == null) {
				rtnMap.put(rtnMessage, "ProductClass can not be found by clsId:"+clsId+".");
				return rtnMap;
			} else if (productClass.getName() == null) {
				rtnMap.put(rtnMessage, "ProductClass name is null, clsId:"+clsId+".");
				return rtnMap;
			}
		} else if (QuoteItemType.SERVICE.value().equals(type)) {
			com.genscript.gsscm.serv.entity.Service servuce = serviceDao.getServiceByCatalogNo(workOrder.getCatalogNo());
			if (servuce == null) {
				rtnMap.put(rtnMessage, "Service can not be found by workOrder CatalogNo:"+workOrder.getCatalogNo());
				return rtnMap;
			}
			clsId = servuce.getServiceClsId();
			if (clsId == null || clsId <= 0) {
				rtnMap.put(rtnMessage, "Service Id:"+servuce.getServiceId()+" clsId is null.");
				return rtnMap;
			}
			ServiceClassification serviceClassification = serviceClassificationDao
					.get(clsId);
			if (serviceClassification == null) {
				rtnMap.put(rtnMessage, "ServiceClassification can not be found by clsId:"+clsId+".");
				return rtnMap;
			} else if (serviceClassification.getName() == null) {
				rtnMap.put(rtnMessage, "ServiceClassification name is null, clsId:"+clsId+".");
				return rtnMap;
			}
		}
		String lotNo = null;
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		int day = Calendar.getInstance().get(Calendar.DATE);
		if ((clsId == 2 && QuoteItemType.PRODUCT.value().equals(type)) 
				|| (clsId == 1 && QuoteItemType.SERVICE.value().equals(type))) {
			String abrCode = workGroup.getAbrCode();
			if (abrCode == null || ("").equals(abrCode)) {
				rtnMap.put(rtnMessage, "workGroup abrCode is null, workGroup Id:"+workGroup.getId()+".");
				return rtnMap;
			}
			String lotNoItemNo = itemNo < 10 ? ("00" + itemNo)
					: (itemNo < 100 ? ("0" + itemNo) : itemNo.toString()
							.substring(itemNo.toString().length() - 3,
									itemNo.toString().length()));
			String lotNoMonth = month < 10 ? ("0" + month) : (String
					.valueOf(month));
			String lotNoDay = day < 10 ? ("0" + day) : (String.valueOf(day));
			lotNo = orderNo + lotNoItemNo + lotNoMonth + lotNoDay + year
					+ abrCode;
		} else if ((clsId == 3 && QuoteItemType.PRODUCT.value().equals(type)) 
				|| (clsId == 2 && QuoteItemType.SERVICE.value().equals(type))) {
			Integer abrNo = workGroup.getAbrNo();
			if (abrNo == null) {
				rtnMap.put(rtnMessage, "workGroup abrNo is null, workGroup Id:"+workGroup.getId()+".");
				return rtnMap;
			}
			Integer seqNo = workOrder.getSeqNo();
			if (seqNo == null) {
				rtnMap.put(rtnMessage, "workOrder seqNo is null, workOrder OrderNo:"+workOrder.getOrderNo()+".");
				return rtnMap;
			}
			String lotNoseqNo = seqNo < 10 ? ("00" + seqNo)
					: (seqNo < 100 ? ("0" + seqNo) : seqNo.toString());
			String lotNoItemNo = itemNo < 10 ? ("00" + itemNo)
					: (itemNo < 100 ? ("0" + itemNo) : itemNo.toString());
			String lotNoMonth = month < 10 ? ("0" + month) : (String
					.valueOf(month));
			lotNo = orderNo + "S" + lotNoItemNo + "/P" + abrNo + lotNoseqNo
					+ lotNoMonth + String.valueOf(year).substring(2, 4);
		} else if ((clsId == 1 && QuoteItemType.PRODUCT.value().equals(type)) 
				|| (clsId == 3 && QuoteItemType.SERVICE.value().equals(type))) {
			Date thatTime = orderItem.getTargetDate();
			if (thatTime == null) {
				rtnMap.put(rtnMessage, "orderItem TargetDate is null, orderItem orderItemId:"+orderItem.getOrderItemId()+".");
				return rtnMap;
			}
			Calendar c = Calendar.getInstance();
			c.setTime(thatTime);
			String thatDay = new SimpleDateFormat("EEEE", Locale.ENGLISH)
					.format(c.getTime());
			String thatDayCode = "";
			if (("Monday").equals(thatDay)) {
				thatDayCode = "A";
			} else if (("Tuesday").equals(thatDay)) {
				thatDayCode = "B";
			} else if (("Wednesday").equals(thatDay)) {
				thatDayCode = "C";
			} else if (("Thursday").equals(thatDay)) {
				thatDayCode = "D";
			} else if (("Friday").equals(thatDay)) {
				thatDayCode = "E";
			} else if (("Saturday").equals(thatDay)) {
				thatDayCode = "F";
			} else {
				thatDayCode = "G";
			}
			String lotNoItemNo = itemNo < 10 ? itemNo.toString() : itemNo
					.toString().substring(itemNo.toString().length() - 1,
							itemNo.toString().length());
			lotNo = orderNo + "S-" + lotNoItemNo + "/" + thatDayCode
					+ "000" + lotNoItemNo;
		} else {
			rtnMap.put(rtnMessage, "type:"+type+" clsId:"+clsId+" can not create lotNo.");
			return rtnMap;
		}
		rtnMap.put("lotNo", lotNo);
		return rtnMap;
	}

	/**
	 * 对WorkOrder进行批量的QC处理.
	 * 
	 * @param workOrderNoList
	 * @param goodsQcStatus
	 * @param docQcStatus
	 * @param goodsQcRejectReason
	 * @param docQcRejectReason
	 * @param loginUserId
	 */
	public void processOrderByQc(List<Integer> workOrderNoList,
			String goodsQcStatus, String docQcStatus,
			String goodsQcRejectReason, String docQcRejectReason,
			Integer loginUserId) {
		String passed = "Passed";
		String failed = "Failed";
		String partial = "Partial";
		String status = null;
		// 都为Passed
		if (passed.equals(goodsQcStatus) && passed.equals(docQcStatus)) {
			status = WorkOrderStatus.Closed.value();
		} else if (failed.equals(goodsQcStatus) && failed.equals(docQcStatus)) {// 都为Failed.
			status = WorkOrderStatus.QCFailed.value();
		} else if(failed.equals(goodsQcStatus) && !failed.equals(docQcStatus)){
			status = WorkOrderStatus.ProductQCFailed.value();
		} else if(!failed.equals(goodsQcStatus) && failed.equals(docQcStatus)){
			status = WorkOrderStatus.DocumentQCFailed.value();
		} else if(passed.equals(goodsQcStatus)&&"".equals(docQcStatus)) {
			status = WorkOrderStatus.ProductQCPassed.value();
		} else if(passed.equals(docQcStatus)&&"".equals(goodsQcStatus)) {
			status = WorkOrderStatus.DocumentQCPassed.value();
		} else if(partial.equals(docQcStatus)&&partial.equals(goodsQcStatus)) {
			status = WorkOrderStatus.QCPartial.value();
		} else if(partial.equals(docQcStatus)&&!failed.equals(goodsQcStatus)) {
			status = WorkOrderStatus.DocumentQCPartial.value();
		} else if(partial.equals(goodsQcStatus)&&!failed.equals(docQcStatus)) {
			status = WorkOrderStatus.ProductQCPartial.value();
		}
		if (status != null) {
			// 批量更新WorkOrder的状态.
			this.workOrderDao.batchUpdateStatus(workOrderNoList, status);
		}
		// 如果Product/Service QC Status为Failed, 保存Product/Service的状态变化
		Map<Integer,LinkedHashSet<Integer>> map = new HashMap<Integer,LinkedHashSet<Integer>>();
		if (failed.equals(goodsQcStatus)) {
			for (Integer workOrderNo : workOrderNoList) {
				
				WoStatusHistory woStatus = this.genWoStatus(workOrderNo,
						loginUserId);
				woStatus.setStatusType("PRODUCT_QC");
				woStatus.setStatus(goodsQcStatus);
				woStatus.setUpdateReason(goodsQcRejectReason);
				this.woStatusHistoryDao.save(woStatus);
				// 本身状态也发生变化, 也需要保存
				if (status != null) {
					WoStatusHistory woSelfStatus = this.genWoStatus(
							workOrderNo, loginUserId);
					woSelfStatus.setStatusType("WORK_ORDER");
					woSelfStatus.setStatus(status);
					woSelfStatus.setUpdateReason(goodsQcRejectReason);
					this.woStatusHistoryDao.save(woSelfStatus);
				}
			}
		}
		// 如果Product/Service Documents QC Status为Failed, 保存Product/Service
		// Documents的状态变化
		if (failed.equals(docQcStatus)) {
			for (Integer workOrderNo : workOrderNoList) {
				if(failed.equals(goodsQcStatus)) {
					List<WorkOrderGroup> list = this.workOrderGroupDao.findBy("orderNo", workOrderNo);
					for(WorkOrderGroup workOrderGroup:list) {
						if(map.containsKey(workOrderGroup.getWorkGroupSpvs())) {
							LinkedHashSet<Integer> orderNoSet = map.get(workOrderGroup.getWorkGroupSpvs());
							orderNoSet.add(workOrderNo);
						} else {
							LinkedHashSet<Integer> orderNoSet = new LinkedHashSet<Integer>();
							orderNoSet.add(workOrderNo);
							map.put(workOrderGroup.getWorkGroupSpvs(), orderNoSet);
						}
					}
				}
				WoStatusHistory woStatus = this.genWoStatus(workOrderNo,
						loginUserId);
				woStatus.setStatusType("DOCUMENT_QC");
				woStatus.setStatus(docQcStatus);
				woStatus.setUpdateReason(docQcRejectReason);
				this.woStatusHistoryDao.save(woStatus);
				// 本身状态也发生变化
				if (status != null) {
					WoStatusHistory woSelfStatus = this.genWoStatus(
							workOrderNo, loginUserId);
					woSelfStatus.setStatusType("WORK_ORDER");
					woSelfStatus.setStatus(status);
					woSelfStatus.setUpdateReason(docQcRejectReason);
					this.woStatusHistoryDao.save(woSelfStatus);
				}
			}
		}
		
		this.workOrderDao.batchUpdateQCStatus(workOrderNoList, goodsQcStatus, docQcStatus);
		if(status!=null&&status.equals(WorkOrderStatus.QCFailed.value())) {
			String subject="Work Order QC Result";
			String from = "scm_admin@genscriptcorp.com";
			for(Entry<Integer, LinkedHashSet<Integer>> entity : map.entrySet()) {
				StringBuffer content = new StringBuffer("");
				String to = "";
				User user = this.userDao.getById(entity.getKey());
				if(user!=null&&user.getEmployee()!=null) {
					Employee employee = employeeDao.getById(user.getEmployee().getEmployeeId());
					if(employee!=null) {
						to = employee.getEmail();
						content.append("Dear ").append(user.getFirstName()).append(" ").append(user.getLastName()).append(":<br>");
					}
				}
				content.append("   All the following work orders come from your team are failed in the QC process.")
						.append("Format is as follows: the first column is work order no, and the second column is US order no, and the third column is item no. <br><br>");
				Iterator<Integer> it = entity.getValue().iterator();
				while(it.hasNext()) {
					Integer orderNo = it.next();
					WorkOrder workorder = this.workOrderDao.getById(orderNo);
					MfgOrder mfgOrder = this.mfgOrderDao.getById(workorder.getSoNo());
					content.append(orderNo).append("   ").append(mfgOrder.getSrcSoNo()).append("   ").append(workorder.getSoItemNo()).append("<br>");
				}
				this.mimeMailService.sendMail(to, subject, content.toString(), from);
			}
		}
		
	}

	/**
	 * Qa只能对一个WorkOrder进行处理
	 * 
	 * @param workOrderNo
	 * @param goodsQcStatus
	 * @param docQcStatus
	 * @param goodsQcRejectReason
	 * @param docQcRejectReason
	 * @param loginUserId
	 */
	public void processOrderByQa(Integer workOrderNo, String goodsQcStatus,
			String docQcStatus, String goodsQcRejectReason,
			String docQcRejectReason, Integer loginUserId) {
		String passed = "Passed";
		String failed = "Failed";
		String status = null;
		// 都为Passed
		if (passed.equals(goodsQcStatus) && passed.equals(docQcStatus)) {
			status = "Closed";
		} else if (failed.equals(goodsQcStatus) && failed.equals(docQcStatus)) {// 都为Failed.
			status = "Voided";
		} else {// 一个为Passed, 一个为Failed
			// 不作任何处理.
		}
		if (status != null) {
			// 批量更新WorkOrder的状态.
			this.workOrderDao.updateWorkOrderStatus(workOrderNo, status);
			if (status.equals("Closed")) {
				this.workOrderDao.closeWorkOrder(workOrderNo);// 更新actualEnd
			}
		}
		// 如果Product/Service QC Status为Failed, 保存Product/Service的状态变化
		if (failed.equals(goodsQcStatus)) {
			WoStatusHistory woStatus = this.genWoStatus(workOrderNo,
					loginUserId);
			woStatus.setStatusType("PRODUCT_QA");
			woStatus.setStatus(goodsQcStatus);
			woStatus.setUpdateReason(goodsQcRejectReason);
			this.woStatusHistoryDao.save(woStatus);
			// 本身状态也发生变化, 也需要保存
			if (status != null) {
				WoStatusHistory woSelfStatus = this.genWoStatus(workOrderNo,
						loginUserId);
				woSelfStatus.setStatusType("WORK_ORDER");
				woSelfStatus.setStatus(status);
				woSelfStatus.setUpdateReason(goodsQcRejectReason);
				this.woStatusHistoryDao.save(woSelfStatus);
			}
		}
		// 如果Product/Service Documents QC Status为Failed, 保存Product/Service
		// Documents的状态变化
		if (failed.equals(docQcStatus)) {
			WoStatusHistory woStatus = this.genWoStatus(workOrderNo,
					loginUserId);
			woStatus.setStatusType("DOCUMENT_QA");
			woStatus.setStatus(docQcStatus);
			woStatus.setUpdateReason(docQcRejectReason);
			this.woStatusHistoryDao.save(woStatus);
			// 本身状态也发生变化
			if (status != null) {
				WoStatusHistory woSelfStatus = this.genWoStatus(workOrderNo,
						loginUserId);
				woSelfStatus.setStatusType("WORK_ORDER");
				woSelfStatus.setStatus(status);
				woSelfStatus.setUpdateReason(docQcRejectReason);
				this.woStatusHistoryDao.save(woSelfStatus);
			}
		}
	}

	/**
	 * 提供公共的创建WoStatusHistory的方法.
	 * 
	 * @param workOrderNo
	 * @param loginUserId
	 * @return
	 */
	private WoStatusHistory genWoStatus(Integer workOrderNo, Integer loginUserId) {
		WoStatusHistory woStatus = new WoStatusHistory();
		woStatus.setOrderNo(workOrderNo);
		woStatus.setUpdateDate(new Date());
		woStatus.setUpdatedBy(loginUserId);
		return woStatus;
	}
	
	/**
	 * 获取workGroupSupervisor的Email
	 */
	public String getWGSupervisorEmail(String workGroupIds) {
		StringBuffer email = new StringBuffer();
		if(StringUtils.isNotEmpty(workGroupIds)) {
			Integer centerId = null;
			String[] workGroupIdArray = workGroupIds.split(",");
			for(String workGroupId:workGroupIdArray) {
				if(StringUtils.isNotEmpty(workGroupId)) {
					WorkGroup workGroup  = this.workGroupDao.getById(Integer.parseInt(workGroupId));
					if(centerId==null) {
						centerId = workGroup.getWorkCenterId();
					}
					if(workGroup!=null&&workGroup.getSupervisor()!=null) {
						User user = this.userDao.getById(workGroup.getSupervisor());
						String strEmail = null;
						if(user!=null&&user.getEmployee()!=null) {
							Employee employee = employeeDao.getById(user.getEmployee().getEmployeeId());
							if(employee!=null) {
								strEmail = employee.getEmail();
							}
						}
						email.append(strEmail==null?"":strEmail).append(",");
					}
				}
				
			}
			if(centerId!=null) {
				WorkCenter workCenter = this.workCenterDao.getById(centerId);
				if(workCenter==null) {
					return StringUtils.isNotEmpty(email.toString())?email.toString().substring(0,email.toString().length()-1):null;
				}
				if("Protein Department".equals(workCenter.getName())) {
					EmailGroup emailGroup = this.emailGroupDao.getEmailGroupByNameAndFunction("Protein Dept WO Notification","WO Assignment");
					if(emailGroup!=null) {
						email.append(emailGroup.getGroupAddress()).append(",");
					}
				} else if("Gene Department".equals(workCenter.getName())) {
					EmailGroup emailGroup = this.emailGroupDao.getEmailGroupByNameAndFunction("Gene Dept WO Notification","WO Creation");
					if(emailGroup!=null) {
						email.append(emailGroup.getGroupAddress()).append(",");
					}
				}
			}
		}
		return StringUtils.isNotEmpty(email.toString())?email.toString().substring(0,email.toString().length()-1):null;
	}
	/**
	 * 获取某段时间内创建的status为(CM,BO,PB,CC,VC),work_center_id为centerId的所有order_item
	 * @param fromDate 开始时间
	 * @param toDate 结束时间
	 * @param centerId
	 */
	public List<OrderItemCenterBean> searchItem(Date fromDate,Date toDate,Integer srcSoNo,Integer orderNo,Integer itemNo,Integer centerId) {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		if(srcSoNo!=null) {
			PropertyFilter srcSoNoFilter = new PropertyFilter("EQI_srcSoNo",srcSoNo);
			filters.add(srcSoNoFilter);
		}
		if(orderNo!=null) {
			PropertyFilter orderNoFilter = new PropertyFilter("EQI_orderNo",orderNo);
			filters.add(orderNoFilter);
		}
		if(itemNo!=null) {
			PropertyFilter itemNoFilter = new PropertyFilter("EQI_itemNo",itemNo);
			filters.add(itemNoFilter);
		}
		if(fromDate!=null) {
			PropertyFilter formDateFilter = new PropertyFilter("EQS_fromDate",DateUtils.formatDate2Str(fromDate,"yyyy-MM-dd"));
			filters.add(formDateFilter);
		}
		if(toDate!=null) {
			PropertyFilter toDateFilter = new PropertyFilter("EQS_toDate",DateUtils.formatDate2Str(toDate,"yyyy-MM-dd"));
			filters.add(toDateFilter);
		}
		if(centerId!=null) {
			PropertyFilter centerFilter = new PropertyFilter("EQI_centerId",centerId);
			filters.add(centerFilter);
		}
		
		return this.orderItemCenterBeanDao.searchMfgOrderDTO(filters);
	}
	
	/**
	 * 
	 */
	public List<WorkOrderOperationBean> searchWoOperation(WorkOrderOperationBean workOrderOperationDTO,Integer customFlag) {
		if(workOrderOperationDTO ==null) {
			workOrderOperationDTO = new WorkOrderOperationBean();
		}
		String workCenterId = Struts2Util.getParameter("workCenterId");
		String workGroupId = Struts2Util.getParameter("workGroupId");
		Set<Integer> orderNoSet = new HashSet<Integer>();
		if(StringUtils.isNotEmpty(workGroupId)) {
			List<Integer> orderNoList = workOrderGroupDao.getOrderNoListByGroup(Integer.parseInt(workGroupId));
			orderNoSet.addAll(orderNoList);
		}
		if(StringUtils.isNotEmpty(workCenterId)) {
			List<Integer> list = this.workOrderDao.findByCenterId(Integer.parseInt(workCenterId));
			if(orderNoSet.size()>0){
				orderNoSet.retainAll(list);
			} else if(list!=null&&list.size()>0){
				orderNoSet.addAll(list);
			}
		}
		if((StringUtils.isNotEmpty(workGroupId)||StringUtils.isNotEmpty(workCenterId))&&orderNoSet.size()==0) {
			return null;
		}
		if(orderNoSet.size()==0) {
			if (!Constants.USERNAME_ADMIN.equals(SessionUtil.getUserName())) {
				// 判断当前用户是否含有生产部经理角色
				boolean productionManager = userRoleDao
						.checkIsContainsManagerRole(Constants.ROLE_WOPROCESS_MANAGER);
				if (!productionManager) {
					List<ManufacturingClerks> manufacturingClerksList = this.manufacturingClerksDao.findBy("clerkId", SessionUtil.getUserId());
					if(manufacturingClerksList==null||manufacturingClerksList.size()==0) {
						return null;
					}
					
					for(ManufacturingClerks manufacturingClerks:manufacturingClerksList) {
						if(manufacturingClerks.getWorkGroupId()!=null) {
							List<Integer> list = workOrderGroupDao.getOrderNoListByGroup(manufacturingClerks.getWorkGroupId());
							if(list!=null&&list.size()>0){
								orderNoSet.addAll(list);
							}
						}
					}
				} else {
					List<WorkCenter> centerList = this.workCenterDao.findBy("supervisor", SessionUtil.getUserId());
					List<Integer> centerIdList = new ArrayList<Integer>();
					if(centerList!=null&&centerList.size()>0) {
						for(WorkCenter workCenter:centerList) {
							centerIdList.add(workCenter.getId());
						}
					} else {
						List<ManufacturingClerks> manufacturingClerksList = this.manufacturingClerksDao.findBy("clerkId", SessionUtil.getUserId());
						Set<Integer> centerIdSet = new HashSet<Integer>();
						if(manufacturingClerksList==null||manufacturingClerksList.size()==0) {
							return null;
						}
						for(ManufacturingClerks manufacturingClerks:manufacturingClerksList) {
							if (manufacturingClerks.getWorkCenterId() == null) {
								continue;
							}
							centerIdSet.add(manufacturingClerks.getWorkCenterId());
						}
						if(centerIdSet.isEmpty()) {
							return null;
						}
						centerIdList.addAll(centerIdSet);
					}
					for(Integer centerId:centerIdList) {
						List<Integer> list = this.workOrderDao.findByCenterId(centerId);
						if(list!=null&&list.size()>0){
							orderNoSet.addAll(list);
						}
					}
				}
			} else {
				List<Integer> orderNoList = workOrderGroupDao.getOrderNoListByGroup(null);
				orderNoSet.addAll(orderNoList);
			}
		}
		if(StringUtils.isEmpty(workGroupId)) {
			List<Integer> orderNoList2 = workOrderGroupDao.getOrderNoListByGroup(null);
			if(orderNoList2==null) {
				return null;
			}
			if(orderNoSet.size()>0) {
				orderNoSet.retainAll(orderNoList2);
			} 
		}
		Set<Integer> soNoSet = null;
		if(workOrderOperationDTO!=null&&workOrderOperationDTO.getSrcSoNo()!=null) {
			soNoSet = new HashSet<Integer>();
			List<Integer> soNoList = this.mfgOrderDao.findBySrcSoNo(workOrderOperationDTO.getSrcSoNo());
			if(soNoList!=null&&soNoList.size()>0) {
				soNoSet.addAll(soNoList);
			} else {
				soNoSet.add(workOrderOperationDTO.getSrcSoNo());
			}
		}
		List<WorkOrderOperation> woOperationList =  this.workOrderOperationDao.getWorkOrderOPerationList(workOrderOperationDTO,customFlag,orderNoSet,soNoSet);
		if(woOperationList==null) {
			return null;
		}
		List<WorkOrderOperationBean> result = new ArrayList<WorkOrderOperationBean>();
		for(WorkOrderOperation workOrderOperation:woOperationList) {
			if(customFlag==null&&workOrderOperation.getSeqNo()>1) {
				WorkOrderOperation preWorkOrderOperation = this.workOrderOperationDao.findWoOperation(workOrderOperation.getSeqNo()-1,workOrderOperation.getWorkOrderNo());
				if(preWorkOrderOperation!=null&&!WoOperStatus.Completed.value().equals(preWorkOrderOperation.getStatus())) {
					continue;
				}
			}
			Operation operation = this.operationDao.getById(workOrderOperation.getOperation().getId());
			WorkOrder workOrder = this.workOrderDao.getById(workOrderOperation.getWorkOrderNo());
			MfgOrder mfgOrder = this.mfgOrderDao.getById(workOrder.getSoNo());
			workOrder.setSrcSoNo(mfgOrder!=null?mfgOrder.getSrcSoNo():workOrder.getSoNo());
			WorkOrderOperationBean workOrderOperationBean = new WorkOrderOperationBean();
			workOrderOperationBean.setId(workOrderOperation.getId());
			workOrderOperationBean.setAltOrderNo(workOrder!=null?workOrder.getAltOrderNo():"");
			workOrderOperationBean.setOperationName(operation!=null?operation.getName():"");
			workOrderOperationBean.setSoItemNo(workOrder!=null?workOrder.getSoItemNo():null);
			workOrderOperationBean.setSoNo(workOrder!=null?workOrder.getSoNo():null);
			workOrderOperationBean.setSrcSoNo(workOrder!=null?workOrder.getSrcSoNo():null);
			workOrderOperationBean.setStatus(workOrderOperation.getStatus());
			workOrderOperationBean.setExptdEndDate(DateUtils.formatDate2Str(workOrderOperation.getExptdEndDate(),"yyyy-MM-dd HH:mm"));
			workOrderOperationBean.setExptdStartDate(DateUtils.formatDate2Str(workOrderOperation.getExptdStartDate(),"yyyy-MM-dd HH:mm"));
			workOrderOperationBean.setCustomEndDate(DateUtils.formatDate2Str(workOrderOperation.getCustomEndDate(),"yyyy-MM-dd HH:mm"));
			workOrderOperationBean.setCustomStartDate(DateUtils.formatDate2Str(workOrderOperation.getCustomStartDate(),"yyyy-MM-dd HH:mm"));
			List<WorkOrderGroup> workOrderGroupList = this.workOrderGroupDao.findBy("orderNo", workOrder.getOrderNo());
			if(workOrderGroupList!=null&&workOrderGroupList.size()>0) {
				StringBuffer workGroupNames = null;
				for(WorkOrderGroup workOrderGroup:workOrderGroupList) {
					if (workOrderGroup.getWorkGroupId() != null) {
						WorkGroup workGroup = workGroupDao.getById(workOrderGroup
								.getWorkGroupId());
						if (workGroup != null&&workGroupNames==null) {
							workGroupNames = new StringBuffer();
							workGroupNames.append(workGroup.getName());
						} else if(workGroup!=null){
							workGroupNames.append(",").append(workGroup.getName());
						}
					}
				}
				workOrderOperationBean.setWorkGroupNames(workGroupNames!=null?workGroupNames.toString():"");
			}
			result.add(workOrderOperationBean);
		}
		return result;
	}
	
	public List<WorkOrderOperationBean> searchWoOperationByUSOrder(Integer srcSoNo) {
		Set<Integer> soNoSet = new HashSet<Integer>();
		List<Integer> soNoList = this.mfgOrderDao.findBySrcSoNo(srcSoNo);
		if(soNoList!=null&&soNoList.size()>0) {
			soNoSet.addAll(soNoList);
		} else {
			soNoSet.add(srcSoNo);
		}
		List<WorkOrderOperation> woOperationList =  this.workOrderOperationDao.getWorkOrderOPerationList(soNoSet);
		if(woOperationList==null) {
			return null;
		}
		List<WorkOrderOperationBean> result = new ArrayList<WorkOrderOperationBean>();
		for(WorkOrderOperation workOrderOperation:woOperationList) {
			Operation operation = this.operationDao.getById(workOrderOperation.getOperation().getId());
			WorkOrder workOrder = this.workOrderDao.getById(workOrderOperation.getWorkOrderNo());
			WorkOrderOperationBean workOrderOperationBean = new WorkOrderOperationBean();
			workOrderOperationBean.setId(workOrderOperation.getId());
			workOrderOperationBean.setAltOrderNo(workOrder!=null?workOrder.getAltOrderNo():"");
			workOrderOperationBean.setOperationName(operation!=null?operation.getName():"");
			workOrderOperationBean.setSoItemNo(workOrder!=null?workOrder.getSoItemNo():null);
			workOrderOperationBean.setSoNo(workOrder!=null?workOrder.getSoNo():null);
			workOrderOperationBean.setSrcSoNo(srcSoNo);
			workOrderOperationBean.setStatus(workOrderOperation.getStatus());
			workOrderOperationBean.setExptdEndDate(DateUtils.formatDate2Str(workOrderOperation.getExptdEndDate(),"yyyy-MM-dd HH:mm"));
			workOrderOperationBean.setExptdStartDate(DateUtils.formatDate2Str(workOrderOperation.getExptdStartDate(),"yyyy-MM-dd HH:mm"));
			workOrderOperationBean.setCustomEndDate(DateUtils.formatDate2Str(workOrderOperation.getCustomEndDate(),"yyyy-MM-dd HH:mm"));
			workOrderOperationBean.setCustomStartDate(DateUtils.formatDate2Str(workOrderOperation.getCustomStartDate(),"yyyy-MM-dd HH:mm"));
			List<WorkOrderGroup> workOrderGroupList = this.workOrderGroupDao.findBy("orderNo", workOrder.getOrderNo());
			if(workOrderGroupList!=null&&workOrderGroupList.size()>0) {
				StringBuffer workGroupNames = null;
				for(WorkOrderGroup workOrderGroup:workOrderGroupList) {
					if (workOrderGroup.getWorkGroupId() != null) {
						WorkGroup workGroup = workGroupDao.getById(workOrderGroup
								.getWorkGroupId());
						if (workGroup != null&&workGroupNames==null) {
							workGroupNames = new StringBuffer();
							workGroupNames.append(workGroup.getName());
						} else if(workGroup!=null){
							workGroupNames.append(",").append(workGroup.getName());
						}
					}
				}
				workOrderOperationBean.setWorkGroupNames(workGroupNames!=null?workGroupNames.toString():"");
			}
			result.add(workOrderOperationBean);
		}
		return result;
	}
	public boolean orderToCCCreateWorkOrders(OrderMainDTO order) {
		if(order==null||order.getOrderNo()==null) {
			return false;
		}
		Customer cust = this.customerDao.getById(order.getCustNo());
		OrderMain orderMain = this.orderDao.getById(order.getOrderNo());
		String status = order.getStatus();
		if (Constants.INTERNAL_TYPE_CUSTOMER.equalsIgnoreCase(cust.getCustType())&&!status.equals(orderMain.getStatus())
				&&status.equals(OrderStatusType.CC.name())&& orderMain.getKeyInfoChanged() != 1) {
			return true;
		
		}
		return false;
	}
	
	/**
	 * 批量生产WO
	 * @param itemIds
	 * @param workOrder 
	 * @return
	 */
	public Map<String,List<OrderItem>> createWorkOrders(String itemIds,Integer centerId) throws Exception{
		Map<String,List<OrderItem>> resultMap = new HashMap<String,List<OrderItem>>();
		String[] itemId = itemIds.split(",");
		Integer userId = 0;
		Map<String,Object> session = (ActionContext.getContext()!=null)?ActionContext.getContext().getSession():null;
		if(session==null) {
			User user = this.userDao.findByLoginName(Constants.USERNAME_ADMIN);
			userId = user!=null?user.getUserId():0;
		} else {
			userId = SessionUtil.getUserId();
		}
		List<String> newSortList = new LinkedList<String>();
		List<String> filterItemList = new LinkedList<String>();
		List<Route> routeList = null;
		Integer standardRoutine = null;
		if(centerId!=null) {
			routeList = this.routeDao.findRoute(centerId,2);
		} 
		
		List<WorkOrderOperation> dnaWoOperationList = null;
		Route dnaRoute = this.routeDao.findUniqueBy("name", "SQ DNA Sequencing Routing");//DNA Route
		if(dnaRoute!=null) {
			List<RouteOperation> list = this.routeOperationDao.getAllList(dnaRoute.getId());
			Date preExptdEndDate = new Date();
			int seqNo = 1;
			if(list!=null) {
				dnaWoOperationList = new ArrayList<WorkOrderOperation>();
				for (RouteOperation dto : list) {
					WorkOrderOperation wo = new WorkOrderOperation();
					wo.setOperation(dto.getOperation());
					wo.setStatus(WoOperStatus.New.value());
					if (seqNo == 1) {
						wo.setExptdStartDate(new java.sql.Date(new Date().getTime()));// 第一条设置Schedule Start Date.
						preExptdEndDate = wo.getExptdEndDate();
						wo.setCustomStartDate(wo.getExptdStartDate());
						wo.setCustomEndDate(wo.getExptdEndDate());
					} else {
						wo.setExptdStartDate(new java.sql.Date(preExptdEndDate.getTime()));// 第一条设置Schedule Start Date.
						preExptdEndDate = wo.getExptdEndDate();
						wo.setCustomStartDate(wo.getExptdStartDate());
						wo.setCustomEndDate(wo.getExptdEndDate());
					}
					wo.setSeqNo(seqNo);
					dnaWoOperationList.add(wo);
					seqNo++;
				}
			}
		}
		if(itemId!=null&&itemId.length>0) {
			Map<Integer,HashSet<Integer>> map = new HashMap<Integer,HashSet<Integer>>();
			List<OrderItem> trubleOrderItemList = new ArrayList<OrderItem>();
			List<OrderItem> plateOrderItemList = new ArrayList<OrderItem>();
			for(String id:itemId) {
				OrderItem item = this.returnOrderItem(Integer.parseInt(id));
				if(item==null) {
					continue;
				}
				if (QuoteItemType.SERVICE.value().equals(item.getType())&&item.getClsId().intValue()==40){
					OrderDnaSequencing OrderDnaSequencing = orderDnaSequencingDao.getById(item.getOrderItemId());
					if(OrderDnaSequencing==null) {
						continue;
					}
					if(map.containsKey(item.getOrderNo())) {
						map.get(item.getOrderNo()).add(item.getOrderItemId());
					} else {
						HashSet<Integer> itemIdSet = new HashSet<Integer>();
						itemIdSet.add(item.getOrderItemId());
						map.put(item.getOrderNo(), itemIdSet);
					}
					if(OrderDnaSequencing.getCode().toLowerCase().startsWith("t")) {
						trubleOrderItemList.add(item);
					}
					if(OrderDnaSequencing.getCode().toLowerCase().startsWith("p")) {
						plateOrderItemList.add(item);
					}
					filterItemList.add(id);
					continue;
	            }
				if(item.getParentId()==null||item.getParentId()==0) {
					newSortList.add(id);
				}
			}
			resultMap.put("TUBE", trubleOrderItemList);
			resultMap.put("PLATE", plateOrderItemList);
			for(String id:filterItemList) {
				WorkOrder workOrder = createWorkOrder(Integer.parseInt(id),centerId,userId);
				String giftFlag = "N";
				if(QuoteItemType.PRODUCT.value().equals(workOrder.getItemType())) {
					Product product = this.productDao.getProductByCatalogNo(workOrder.getCatalogNo());
					giftFlag = product!=null?product.getGiftFlag():"N";
				} else {
					com.genscript.gsscm.serv.entity.Service service = this.serviceDao.getServiceByCatalogNo(workOrder.getCatalogNo());
					giftFlag = service!=null?service.getGiftFlag():"N";
				}
				if("Y".equals(giftFlag)) {
					workOrder.setActualStart(new java.sql.Date(new Date().getTime()));
					workOrder.setActualEnd(new java.sql.Date(new Date().getTime()));
					workOrder.setStatus(WorkOrderStatus.Completed.value());
					this.workOrderDao.save(workOrder);
					if(StringUtils.isEmpty(workOrder.getAltOrderNo())) {
						workOrder.setAltOrderNo(String.valueOf(workOrder.getOrderNo()));
					}
				} else {
					workOrder.setStandardRoutine(dnaRoute!=null?dnaRoute.getId():null);
					this.workOrderDao.save(workOrder);
					if(StringUtils.isEmpty(workOrder.getAltOrderNo())) {
						workOrder.setAltOrderNo(String.valueOf(workOrder.getOrderNo()));
					}
					if(dnaWoOperationList!=null) {
						for(WorkOrderOperation woOperation:dnaWoOperationList) {
							WorkOrderOperation wo = this.dozer.map(woOperation, WorkOrderOperation.class);
							wo.setCreatedBy(userId);
							wo.setCreationDate(new Date());
							wo.setModifiedBy(userId);
							wo.setModifyDate(new Date());
							wo.setWorkOrderNo(workOrder.getOrderNo());
							this.workOrderOperationDao.save(wo);
						}
					}
				}
			}
			for(String id:newSortList) {
				WorkOrder workOrder = createWorkOrder(Integer.parseInt(id),centerId,userId);
				if(routeList==null) {
					routeList = this.routeDao.findRoute(workOrder.getWorkCenterId(), 2);
				}
				if(routeList!=null&&routeList.size()>0) {
					for(Route route:routeList) {
						if(!StringUtils.isNotEmpty(route.getName())&&route.getName().contains(workOrder.getCatalogNo())) {
							standardRoutine = route.getId();
							break;
						}
					}
					if(standardRoutine==null) {
						for(Route route:routeList) {
							if(route.getDefaultFlag()!=null&&route.getDefaultFlag().toLowerCase().equals("y")) {
								standardRoutine = route.getId();
								break;
							}
						}
					}
					if(standardRoutine==null) {
						standardRoutine = routeList.get(0).getId();
					}
				}
				List<WorkOrderOperation> woOperationList = null;
				if(standardRoutine!=null) {
					List<RouteOperation> list = this.routeOperationDao.getAllList(standardRoutine);
					Date preExptdEndDate = new Date();
					int seqNo = 1;
					if(list!=null) {
						woOperationList = new ArrayList<WorkOrderOperation>();
						for (RouteOperation dto : list) {
							WorkOrderOperation wo = new WorkOrderOperation();
							wo.setOperation(dto.getOperation());
							wo.setStatus(WoOperStatus.New.value());
							if (seqNo == 1) {
								wo.setExptdStartDate(new java.sql.Date(new Date().getTime()));// 第一条设置Schedule Start Date.
								preExptdEndDate = wo.getExptdEndDate();
								wo.setCustomStartDate(wo.getExptdStartDate());
								wo.setCustomEndDate(wo.getExptdEndDate());
							} else {
								wo.setExptdStartDate(new java.sql.Date(preExptdEndDate.getTime()));// 第一条设置Schedule Start Date.
								preExptdEndDate = wo.getExptdEndDate();
								wo.setCustomStartDate(wo.getExptdStartDate());
								wo.setCustomEndDate(wo.getExptdEndDate());
							}
							wo.setSeqNo(seqNo);
							woOperationList.add(wo);
							seqNo++;
						}
					}
					
				}
				String giftFlag = "N";
				if(QuoteItemType.PRODUCT.value().equals(workOrder.getItemType())) {
					Product product = this.productDao.getProductByCatalogNo(workOrder.getCatalogNo());
					giftFlag = product!=null?product.getGiftFlag():"N";
				} else {
					com.genscript.gsscm.serv.entity.Service service = this.serviceDao.getServiceByCatalogNo(workOrder.getCatalogNo());
					giftFlag = service!=null?service.getGiftFlag():"N";
				}
				if("Y".equals(giftFlag)) {
					workOrder.setActualStart(new java.sql.Date(new Date().getTime()));
					workOrder.setActualEnd(new java.sql.Date(new Date().getTime()));
					workOrder.setStatus(WorkOrderStatus.Completed.value());
					this.workOrderDao.save(workOrder);
					if(StringUtils.isEmpty(workOrder.getAltOrderNo())) {
						workOrder.setAltOrderNo(String.valueOf(workOrder.getOrderNo()));
					}
				} else {
					workOrder.setStandardRoutine(standardRoutine);
					this.workOrderDao.save(workOrder);
					if(StringUtils.isEmpty(workOrder.getAltOrderNo())) {
						workOrder.setAltOrderNo(String.valueOf(workOrder.getOrderNo()));
					}
					if(woOperationList!=null) {
						for(WorkOrderOperation woOperation:woOperationList) {
							WorkOrderOperation wo = this.dozer.map(woOperation, WorkOrderOperation.class);
							wo.setCreatedBy(userId);
							wo.setCreationDate(new Date());
							wo.setModifiedBy(userId);
							wo.setModifyDate(new Date());
							wo.setWorkOrderNo(workOrder.getOrderNo());
							this.workOrderOperationDao.save(wo);
						}
					}
				}
				
			}
			for(String id:itemId) {
				if(newSortList.contains(id)||filterItemList.contains(id)) {
					continue;
				}
				WorkOrder workOrder = createWorkOrder(Integer.parseInt(id),centerId,userId);
				String giftFlag = "N";
				if(QuoteItemType.PRODUCT.value().equals(workOrder.getItemType())) {
					Product product = this.productDao.getProductByCatalogNo(workOrder.getCatalogNo());
					giftFlag = product!=null?product.getGiftFlag():"N";
				} else {
					com.genscript.gsscm.serv.entity.Service service = this.serviceDao.getServiceByCatalogNo(workOrder.getCatalogNo());
					giftFlag = service!=null?service.getGiftFlag():"N";
				}
				if(routeList==null) {
					routeList = this.routeDao.findRoute(workOrder.getWorkCenterId(), 2);
				}
				if(routeList!=null&&routeList.size()>0) {
					for(Route route:routeList) {
						if(!StringUtils.isNotEmpty(route.getName())&&route.getName().contains(workOrder.getCatalogNo())) {
							standardRoutine = route.getId();
							break;
						}
					}
					if(standardRoutine==null) {
						for(Route route:routeList) {
							if(route.getDefaultFlag()!=null&&route.getDefaultFlag().toLowerCase().equals("y")) {
								standardRoutine = route.getId();
								break;
							}
						}
					}
					if(standardRoutine==null) {
						standardRoutine = routeList.get(0).getId();
					}
				}
				List<WorkOrderOperation> woOperationList = null;
				if(standardRoutine!=null) {
					List<RouteOperation> list = this.routeOperationDao.getAllList(standardRoutine);
					Date preExptdEndDate = new Date();
					int seqNo = 1;
					if(list!=null) {
						woOperationList = new ArrayList<WorkOrderOperation>();
						for (RouteOperation dto : list) {
							WorkOrderOperation wo = new WorkOrderOperation();
							wo.setOperation(dto.getOperation());
							wo.setStatus(WoOperStatus.New.value());
							if (seqNo == 1) {
								wo.setExptdStartDate(new java.sql.Date(new Date().getTime()));// 第一条设置Schedule Start Date.
								preExptdEndDate = wo.getExptdEndDate();
								wo.setCustomStartDate(wo.getExptdStartDate());
								wo.setCustomEndDate(wo.getExptdEndDate());
							} else {
								wo.setExptdStartDate(new java.sql.Date(preExptdEndDate.getTime()));// 第一条设置Schedule Start Date.
								preExptdEndDate = wo.getExptdEndDate();
								wo.setCustomStartDate(wo.getExptdStartDate());
								wo.setCustomEndDate(wo.getExptdEndDate());
							}
							wo.setSeqNo(seqNo);
							woOperationList.add(wo);
							seqNo++;
						}
					}
					
				}
				if("Y".equals(giftFlag)) {
					workOrder.setActualStart(new java.sql.Date(new Date().getTime()));
					workOrder.setActualEnd(new java.sql.Date(new Date().getTime()));
					workOrder.setStatus(WorkOrderStatus.Completed.value());
					this.workOrderDao.save(workOrder);
					if(StringUtils.isEmpty(workOrder.getAltOrderNo())) {
						workOrder.setAltOrderNo(String.valueOf(workOrder.getOrderNo()));
					}
				} else {
					workOrder.setStandardRoutine(standardRoutine);
					this.workOrderDao.save(workOrder);
					if(StringUtils.isEmpty(workOrder.getAltOrderNo())) {
						workOrder.setAltOrderNo(String.valueOf(workOrder.getOrderNo()));
					}
					if(woOperationList!=null) {
						for(WorkOrderOperation woOperation:woOperationList) {
							WorkOrderOperation wo = this.dozer.map(woOperation, WorkOrderOperation.class);
							wo.setCreatedBy(userId);
							wo.setCreationDate(new Date());
							wo.setModifiedBy(userId);
							wo.setModifyDate(new Date());
							wo.setWorkOrderNo(workOrder.getOrderNo());
							this.workOrderOperationDao.save(wo);
						}
					}
				}
			}
		}
		return resultMap;
	}
	
	private OrderItem returnOrderItem(Integer itemId) {
		OrderItem orderItem = null;
		MfgOrderItem mfgOrderItem = this.mfgOrderItemDao.getById(itemId);
		if(mfgOrderItem==null) {
			orderItem = this.orderItemDao.getById(itemId);
			return orderItem;
		}
		MfgOrder mfgOrder = this.mfgOrderDao.getById(mfgOrderItem.getOrderNo());
		if(mfgOrder==null) {
			orderItem = this.orderItemDao.getById(itemId);
			return orderItem;
		}
		orderItem = this.orderItemDao.getOrderItem(mfgOrder.getSrcSoNo(), mfgOrderItem.getItemNo());
		orderItem.setMfgOrderNo(mfgOrder.getOrderNo());
		return orderItem;
	}
	
	private WorkOrder createWorkOrder(Integer itemId,Integer centerId,Integer userId) {
		OrderItem item = this.returnOrderItem(itemId);
		if(item==null) {
			return null;
		}
		if(centerId==null&&StringUtils.isNotEmpty(item.getType())&&item.getClsId()!=null) {
			List<WorkCenterAssigned> workCenterAssignedList = this.workCenterAssignedDao.findByTypeAndCId(item.getType(), item.getClsId());
			centerId = workCenterAssignedList!=null&&workCenterAssignedList.size()>0?workCenterAssignedList.get(0).getWorkCenter().getId():0;
		}
		OrderMain order = this.orderDao.getById(item.getOrderNo());
		WorkOrder workOrder = new WorkOrder();
		workOrder.setType("Default");
		workOrder.setStatus("New");
		workOrder.setSource("SALES ORDER");
		workOrder.setSoNo(item.getMfgOrderNo()!=null?item.getMfgOrderNo():item.getOrderNo());
		workOrder.setSoItemNo(item.getItemNo());
		workOrder.setWorkCenterId(centerId);
		WorkCenter workCenter = this.workCenterDao.getById(centerId);
		workOrder.setWorkCenterSpvs(workCenter!=null?workCenter.getSupervisor():null);
		workOrder.setWarehouseId(2);//genscript Nj 
		workOrder.setItemType(item.getType());
		workOrder.setClsId(item.getClsId());
		workOrder.setOrderDate(new java.sql.Date(new Date().getTime()));
		workOrder.setExprDate(new java.sql.Date(new Date().getTime()));
		workOrder.setPriority(order.getPriority());
		workOrder.setCatalogNo(item.getCatalogNo());
		workOrder.setItemName(item.getName());
		workOrder.setQuantity(item.getQuantity());
		workOrder.setQtyUom(item.getQtyUom());
		workOrder.setSize(item.getSize());
		workOrder.setSizeUom(item.getSizeUom());
		if (order!=null&&order.getGsCoId() != null) {
			workOrder.setCompanyId(Short.parseShort(String
					.valueOf(order.getGsCoId())));
		} else {
			workOrder.setCompanyId(Short.parseShort("1"));
		}
		workOrder.setCreatedBy(userId);
		Integer seqNo = this.workOrderDao.getTodayMaxSeqNo();
		workOrder.setSeqNo(seqNo!=null?seqNo++:1);
		workOrder.setModifyDate(new Date());
		workOrder.setModifiedBy(userId);
		this.prevSaveForWorkTime(workOrder,false);
		
		WorkOrderDTO workOrderDTO = this.dozer.map(workOrder, WorkOrderDTO.class);
		List<QaGroup> qaGroupList = this.getQaGroup(workOrderDTO);
		// 为null时要给其赋值， 否则struts2标签访问时会出错.
		
		if(qaGroupList!=null&&qaGroupList.size()>0) {
			workOrder.setQcGroup(qaGroupList.get(0).getId());
			List<QaClerk> qcClerkList  = this.getClerkList(qaGroupList.get(0).getId());
			workOrder.setQcClerk(qcClerkList!=null&&qcClerkList.size()>0?qcClerkList.get(0).getId():null);
		}
		return workOrder;
	}
	
	/**
	 * 邮件模板变量替换并返回
	 */
	public List<MailTemplatesDTO> convertParam(List<MailTemplatesDTO> mailTmplList,String orderNoStrs) {
		if(mailTmplList!=null&&StringUtils.isNotEmpty(orderNoStrs)) {
			StringBuffer orderNoStr = new StringBuffer("");
			StringBuffer subjectBuf = new StringBuffer("");
			Set<Integer> soNoSet = new HashSet<Integer>();
			String workCenterName = null;
			for(String orderNo:orderNoStrs.split(",")) {
				WorkOrder workOrder = this.workOrderDao.getById(Integer.parseInt(orderNo));
				if(StringUtils.isEmpty(workCenterName)) {
					WorkCenter workCenter = this.workCenterDao.getById(workOrder.getWorkCenterId());
					workCenterName = workCenter!=null?workCenter.getName():null;
				}
				MfgOrder mfgOrder = this.mfgOrderDao.getById(workOrder.getSoNo());
				soNoSet.add(mfgOrder!=null?mfgOrder.getSrcSoNo():workOrder.getSoNo());
//				if (QuoteItemType.PRODUCT.value().equals(workOrder.getItemType())) {
//	                ProductClass pdtClass = this.productClassDao.getById(workOrder
//	                        .getClsId());
//	                workOrderType = pdtClass == null ? null : pdtClass.getName();
//	            } else {
//	                ServiceClass servClass = this.serviceClassDao.getById(workOrder
//	                        .getClsId());
//	                workOrderType = servClass == null ? null : servClass.getName();
//	            }
//				if(workOrderType==null) {
//					continue;
//				}
//				subjectBuf.append(workOrderType).append(" Supply Order: ").append(soNo).append(";");
			}
			StringBuffer contact = new StringBuffer("");
			Iterator<Integer> it = soNoSet.iterator();
			while(it.hasNext()) {
				Integer soNo = it.next();
				OrderMain order = this.orderDao.getById(soNo);
				Integer custNo = order!=null?order.getCustNo():0;
				Customer customer = this.customerDao.getById(custNo);
				if(customer==null||customer.getSalesContact()==null) {
					continue;
				}
				User user = this.userDao.getById(customer.getSalesContact());
				String userName = "";
				String email = "";
				if(user==null||user.getEmployee()==null) {
					continue;
				}
				Employee employee = this.employeeDao.getById(user.getEmployee().getEmployeeId());
				if(employee==null) {
					continue;
				}
				userName = user.getFirstName()+" "+user.getLastName();
				email = employee.getEmail();
				if(StringUtils.isEmpty(contact.toString())) {
					contact.append(userName).append(" at ").append(email)
					.append(" about order#").append(soNo);
				} else {
					contact.append(" and ").append(userName).append(" at ").append(email)
					.append(" about order#").append(soNo);
				}
				if(StringUtils.isEmpty(orderNoStr.toString())) {
					orderNoStr.append(soNo);
				} else {
					orderNoStr.append(",").append(soNo);
				}
				
			}
			subjectBuf.append(workCenterName.substring(0,workCenterName.lastIndexOf(" "))).append(" Supply Order:").append(orderNoStr);
			User user = this.userDao.getById(SessionUtil.getUserId());
			for(MailTemplatesDTO mailTemplatesDTO:mailTmplList) {
				//String convertSubject = mailTemplatesDTO.getSubject().replace("&order_no", orderNoStr.toString());
				String convertContent = mailTemplatesDTO.getContent().replace("&order_no", orderNoStr.toString())
																		.replace("&contact",contact.toString())
																		.replace("&signature", user.getSignature()!=null?user.getSignature():"");
				mailTemplatesDTO.setSubject_content(subjectBuf+"::"+convertContent);
			}
		}
		return mailTmplList;
	}
	
	/**
	 * 生成word文档作为附件
	 */
	public List<Document> createWordDoc(String orderNoStrs,String path) throws Exception{
		List<Document> docList = new ArrayList<Document>();
		Map<Integer,List<Integer>> soNoMap = new HashMap<Integer,List<Integer>>();
		if(StringUtils.isNotEmpty(orderNoStrs)) {
			for(String orderNo:orderNoStrs.split(",")) {
				WorkOrder workOrder = this.workOrderDao.getById(Integer.parseInt(orderNo));
				if(workOrder==null) {
					continue;
				}
				Integer srcSoNo = workOrder.getSoNo();
				Integer soItemNo = workOrder.getSoItemNo();
				MfgOrder mfgOrder = this.mfgOrderDao.getById(workOrder.getSoNo());
				if(mfgOrder!=null) {
					srcSoNo = mfgOrder.getSrcSoNo();
				}
				if(soNoMap.containsKey(srcSoNo)) {
					soNoMap.get(srcSoNo).add(soItemNo);
				} else {
					List<Integer> soItemNoList = new ArrayList<Integer>();
					soItemNoList.add(soItemNo);
					soNoMap.put(srcSoNo, soItemNoList);
				}
			}
		}
		for(Entry<Integer,List<Integer>> entry:soNoMap.entrySet()) {
			StringBuffer content = new StringBuffer("");
			Integer srcSoNo = entry.getKey();
			OrderMain order = this.orderDao.getById(srcSoNo);
			OrderAddress billToAddr = this.orderAddressDao.getById(order.getBilltoAddrId());
			OrderAddress shipToAddr = this.orderAddressDao.getById(order.getShiptoAddrId());
			content.append("Purchase Order:  ").append(srcSoNo).append("\n");
			content.append("1.	Shipping Address:\n\n").append("Ship To:\n");
			if(shipToAddr!=null) {
				content.append("\t").append(this.getAddressDisplay(shipToAddr)).append("\n");
			}
			content.append("2.	Billing Address:\n\n").append("Bill To:\n");
			if(billToAddr!=null) {
				content.append("\t").append(this.getAddressDisplay(billToAddr)).append("\n");
			}
			content.append("3.	Order date:").append(order.getOrderDate()).append("\n");
			content.append("4. 	Sequence and Instruction:\n\n");

			List<Integer> soItemNoList = entry.getValue();
			Double totalCost = 0.0;
			for(Integer itemNo:soItemNoList) {
				OrderItem orderItem = this.orderItemDao.getOrderItem(srcSoNo, itemNo);
				if(orderItem==null) {
					continue;
				}
				content.append(srcSoNo).append("-").append(itemNo).append("\n");
				content.append(getItemDetail(orderItem).replace("<br>", "\n").replace("&nbsp;", " "));
				content.append("Guaranteed delivery date:").append(orderItem.getTargetDate()).append("\n");
				content.append("Cost:").append(orderItem.getCost()).append("\n\n");
				totalCost = totalCost + orderItem.getCost();
			}
			content.append("5:	Total Cost:	").append(totalCost).append("\n\n");
//			byte b[] = content.toString().getBytes();
//
//			ByteArrayInputStream bais = new ByteArrayInputStream(b);
//
//			POIFSFileSystem fs = new POIFSFileSystem();
//			DirectoryEntry directory = fs.getRoot();
//			DocumentEntry de = directory.createDocument("WordDocument", bais);
			String attchmentFileName = "workOrderDetail_"+srcSoNo+".doc";
			String fileName = "workOrderDetail_"+srcSoNo+"_"+SessionUtil.generateTempId()+".doc";
			FileOutputStream ostream = new FileOutputStream(path+fileName);
//			fs.writeFilesystem(ostream);
//			bais.close();
//			ostream.close();
			com.lowagie.text.Document document = new com.lowagie.text.Document(PageSize.A4);
		    RtfWriter2.getInstance(document,ostream); 
	        document.open(); 
	        Paragraph p = new Paragraph(content.toString());
	        document.add(p);
	        document.close(); 
			Document doc = new Document();
			doc.setDocName(attchmentFileName);
			doc.setFilePath("quote_notes/"+fileName);
			docList.add(doc);
			
			String fileUrl = "/app_data/database/GeneDept/analyzed_orders/"+srcSoNo.toString().substring(0,3)+"/"+srcSoNo.toString()+".tar.gz";
			String seqAnalyzFileName = srcSoNo.toString()+".tar.gz";
			File srcFile = new File(fileUrl);
			if(srcFile.exists()) {
				File target = new File(path+seqAnalyzFileName);
				FileUtils.copyFile(srcFile, target);
				Document seqAnalyzDoc = new Document();
				seqAnalyzDoc.setDocName(seqAnalyzFileName);
				seqAnalyzDoc.setFilePath("quote_notes/"+seqAnalyzFileName);
				docList.add(doc);
			}
		}
		return docList;
	}
	
	/**
	 * 根据OrderAddress获得也没所需字符串组合。
	 * 
	 * @param orderAddress
	 * @return
	 */
	private String getAddressDisplay(OrderAddress orderAddress) {
		List<String> tmpList = new ArrayList<String>();
		String addrStr = "";
		if (!StringUtils.isEmpty(orderAddress.getAddrLine1())) {
			addrStr = orderAddress.getAddrLine1();
		}
		if (!StringUtils.isEmpty(orderAddress.getAddrLine2())) {
			addrStr = addrStr + "\n" + orderAddress.getAddrLine2();
		}
		if (!StringUtils.isEmpty(orderAddress.getAddrLine3())) {
			addrStr = addrStr + "\n" + orderAddress.getAddrLine3();
		}
		tmpList.add(addrStr);
		String cityStr = "";
		if (!StringUtils.isEmpty(orderAddress.getCity())) {
			cityStr = orderAddress.getCity();
		}
		if (!StringUtils.isEmpty(orderAddress.getState())) {
			if (cityStr.equalsIgnoreCase("")) {
				cityStr = orderAddress.getState() + " "
						+ orderAddress.getZipCode();
			} else {
				cityStr = cityStr + ", " + orderAddress.getState() + " "
						+ orderAddress.getZipCode();
			}
		}
		if (!StringUtils.isEmpty(orderAddress.getCountry())) {
			if (cityStr.equalsIgnoreCase("")) {
				cityStr = orderAddress.getCountry();
			} else {
				cityStr = cityStr + ", " + orderAddress.getCountry();
			}
		}
		tmpList.add(cityStr);
		return StringUtils.join(tmpList.toArray(), "\n");
	}

    //add by zhanghuibin 取得gene的Detail信息
    private OrderServiceDTO getOrderGeneSynthesis(OrderItem orderItem) {
        OrderGeneSynthesis orderGeneSynthesis = orderGeneSynthesisDao.getById(orderItem.getOrderItemId());
        if (orderGeneSynthesis == null) {
            return null;
        }
        OrderServiceDTO orderServiceDTO = new OrderServiceDTO();
        orderServiceDTO.setGeneSynthesis(dozer.map(orderGeneSynthesis, OrderGeneSynthesisDTO.class));
        if (!"N".equals(orderGeneSynthesis.getCloningFlag())) {
            List<OrderItem> itemList = this.orderItemDao.findBy("parentId", orderItem.getOrderItemId());
            OrderCustCloning childOrderCustCloning = null;
            for (OrderItem item : itemList) {
                childOrderCustCloning = orderCustCloningDao.getById(item.getOrderItemId());
                if (childOrderCustCloning != null) {
                    orderServiceDTO.setCustCloning(dozer.map(childOrderCustCloning, OrderCustCloningDTO.class));
                    break;
                }
            }
        }
        OrderPlasmidPreparation childOrderPlasmidPreparation = null;
        if (!"N".equals(orderGeneSynthesis.getPlasmidPrepFlag())) {
            List<OrderItem> itemList = this.orderItemDao.findBy("parentId", orderItem.getOrderItemId());
            for (OrderItem item : itemList) {
                childOrderPlasmidPreparation = orderPlasmidPreparationDao.getById(item.getOrderItemId());
                if (childOrderPlasmidPreparation != null) {
                    orderServiceDTO.setPlasmidPreparation(dozer.map(childOrderPlasmidPreparation, OrderPlasmidPreparationDTO.class));
                    break;
                }
            }
        }
        return orderServiceDTO;
    }

    //add by zhanghuibin gene Item detail  for Excel
    private Map<String, String> getGeneItemDescForExcel(OrderItem orderItem) {
        Map<String, String> resultMap = new HashMap<String, String>();
        StringBuffer result = new StringBuffer();
        result.append("\tGene Synthesis:\r");
        OrderServiceDTO orderServiceDTO = getOrderGeneSynthesis(orderItem);
        if (orderServiceDTO == null) {
            return null;
        }
        OrderGeneSynthesisDTO orderGeneSynthesis = orderServiceDTO.getGeneSynthesis();
        OrderCustCloningDTO childOrderCustCloning = orderServiceDTO.getCustCloning();
        result.append("Gene name:").append(orderGeneSynthesis.getGeneName());
        String vectorName = orderGeneSynthesis.getStdVectorName();
        if (!"N".equals(orderGeneSynthesis.getPlasmidPrepFlag())) {
            vectorName = childOrderCustCloning != null ? childOrderCustCloning.getTgtVector() : "";
        }
        result.append(",Vector name:").append(vectorName);
        result.append("\r");
        result.append("Cloning strategy:").append(vectorName);
        result.append(",Cloning site:").append(orderGeneSynthesis.getCloningSite() != null ? orderGeneSynthesis.getCloningSite() : "");
        result.append(",Plasmid preparation: Standard delivery:");
        if ("N".equals(orderGeneSynthesis.getPlasmidPrepFlag())) {
            result.append("4 ug (Free of charge)\r");
        } else {
            OrderPlasmidPreparationDTO childOrderPlasmidPreparation = orderServiceDTO.getPlasmidPreparation();
            result.append(childOrderPlasmidPreparation != null ?
                    childOrderPlasmidPreparation.getPrepWeight() + childOrderPlasmidPreparation.getPrepWtUom() : "").append("\r");
        }
        resultMap.put("desc", result.toString());
        String len = orderGeneSynthesis.getSeqLength() != null ? "" + orderGeneSynthesis.getSeqLength() : "";
        resultMap.put("length", len);
        return resultMap;
    }

    //add by zhanghuibin
    private String getOrderItemType(OrderItem orderItem){
        String orderItemType = null;
		if (QuoteItemType.PRODUCT.value().equals(orderItem.getType())) {
			ProductClass pdtClass = this.productClassDao.getById(orderItem.getClsId());
			orderItemType = pdtClass == null ? null : pdtClass.getName();
		} else {
			ServiceClass servClass = this.serviceClassDao.getById(orderItem.getClsId());
			orderItemType = servClass == null ? null : servClass.getName();
		}
        return orderItemType;
    }
	
	/**
	 * 
	 */
	private String getItemDetail(OrderItem orderItem) {
		StringBuffer result = new StringBuffer();
		String orderItemType = getOrderItemType(orderItem);
		if (orderItemType.toLowerCase().startsWith("gene")) {
			result.append("&nbsp;&nbsp;Gene Synthesis:<br>");
			OrderServiceDTO orderServiceDTO = getOrderGeneSynthesis(orderItem);
			if(orderServiceDTO == null) {
				return "";
			}
            OrderGeneSynthesisDTO orderGeneSynthesis = orderServiceDTO.getGeneSynthesis();
            OrderCustCloningDTO childOrderCustCloning = orderServiceDTO.getCustCloning();
			result.append("Gene name:").append(orderGeneSynthesis.getGeneName());
			result.append(",Length: ").append(orderGeneSynthesis.getSeqLength() != null ? orderGeneSynthesis.getSeqLength() : "");
			String vectorName = orderGeneSynthesis.getStdVectorName();
            if (!"N".equals(orderGeneSynthesis.getPlasmidPrepFlag())) {
                vectorName = childOrderCustCloning != null ? childOrderCustCloning.getTgtVector() : "";
            }
			result.append(",Vector name:").append(vectorName);
			result.append("<br>");
			result.append("Cloning strategy:").append(vectorName);
			result.append(",Cloning site:").append(orderGeneSynthesis.getCloningSite()!=null?orderGeneSynthesis.getCloningSite():"");
			result.append(",Plasmid preparation: Standard delivery:");
			if ("N".equals(orderGeneSynthesis.getPlasmidPrepFlag())) {
				result.append("4 ug (Free of charge)");
			} else {
				OrderPlasmidPreparationDTO childOrderPlasmidPreparation = orderServiceDTO.getPlasmidPreparation();
				result.append(childOrderPlasmidPreparation!=null?
										childOrderPlasmidPreparation.getPrepWeight()+ childOrderPlasmidPreparation.getPrepWtUom():"");
			}
			result.append(",Delivery time:").append(orderItem.getShipSchedule()).append("days<br>");
			result.append("Sequence:<br>");
			result.append(orderGeneSynthesis.getSequence()).append("<br>");
		} else if(orderItemType.toLowerCase().startsWith("custom cloning")) {
			result.append("&nbsp;&nbsp;Custom Cloning:<br>");
			OrderCustCloning orderCustCloning = orderCustCloningDao.getById(orderItem.getOrderItemId());
			if(orderCustCloning==null) {
				return "";
			}
			result.append("Custom cloning name:").append(orderCustCloning.getTgtInsertName());
			if ("N".equals(orderCustCloning.getCloningFlag())) {
				result.append(",Vector name:").append(orderCustCloning.getTgtVector() != null ? orderCustCloning.getTgtVector(): "");
			} else {
				List<OrderItem> itemList = this.orderItemDao
						.findBy("parentId",
								orderItem.getOrderItemId());
				OrderCustCloning childOrderCustCloning = null;
				for (OrderItem item : itemList) {
					childOrderCustCloning = orderCustCloningDao
							.getById(item.getOrderItemId());
					if (childOrderCustCloning != null) {
						break;
					}
				}
				result.append(",Vector name:").append(childOrderCustCloning != null ?childOrderCustCloning.getTgtVector(): "");
			}
			if ("N".equals(orderCustCloning.getPlasmidPrepFlag())) {
				result.append(",Standard delivery: 4 ug (Free of charge)");
			} else {
				List<OrderItem> itemList = this.orderItemDao
						.findBy("parentId",
								orderItem.getOrderItemId());
				OrderPlasmidPreparation childOrderPlasmidPreparation = null;
				for (OrderItem item : itemList) {
					childOrderPlasmidPreparation = orderPlasmidPreparationDao
							.getById(item.getOrderItemId());
					if (childOrderPlasmidPreparation != null) {
						break;
					}
				}
				if (childOrderPlasmidPreparation != null) {
					result.append(",Standard delivery:")
						   .append(childOrderPlasmidPreparation!=null?childOrderPlasmidPreparation.getPrepWeight()+ childOrderPlasmidPreparation.getPrepWtUom():"");
				}
			}
			result.append(",Delivery time:").append(orderItem.getShipSchedule()).append("days<br>");
		} else if (orderItemType.toLowerCase().startsWith("plasmid preparation")) {
			OrderPlasmidPreparation orderPlasmidPreparation = orderPlasmidPreparationDao.getById(orderItem.getOrderItemId());
			if(orderPlasmidPreparation==null) {
				return "";
			}
			result.append("&nbsp;&nbsp;Plasmid Preparation:<br>");
			result.append("Plasmid name:").append(orderPlasmidPreparation.getPlasmidName());
			result.append(",").append("Plasmid Size:").append(orderPlasmidPreparation.getPlasmidSize());
			result.append(",").append("Antibiotic Resistance:").append(orderPlasmidPreparation.getAntibioticResistance());
			result.append(",").append("Delivery time:").append(orderItem.getShipSchedule()).append("days");
			result.append(",").append("Comment:").append(orderPlasmidPreparation.getComments());
		} else if (orderItemType.toLowerCase().startsWith("mutagenesis")) {
			OrderMutagenesis orderMutagenesis = orderMutagenesisDao.getById(orderItem.getOrderItemId());
			if (orderMutagenesis == null) {
				return "";
			}
			result.append("&nbsp;&nbsp;Mutagenesis:<br>");
			result.append("Mutagenesis name:").append(orderMutagenesis.getVariantName());
			if ("N".equals(orderMutagenesis.getCloningFlag())) {
				result.append(",Cloning Site:").append(orderMutagenesis.getTmplCloningSite());
			} else {
				List<OrderItem> itemList = this.orderItemDao
						.findBy("parentId",
								orderItem.getOrderItemId());
				OrderCustCloning orderCustCloning = null;
				for (OrderItem item : itemList) {
					orderCustCloning = orderCustCloningDao
							.getById(item.getOrderItemId());
					if (orderCustCloning != null) {
						break;
					}
				}
				result.append(",Cloning Site:").append(orderCustCloning != null ? orderCustCloning.getTgtCloningSite() : "");
			}
			if ("N".equals(orderMutagenesis.getPlasmidPrepFlag())) {
				result.append(",Standard delivery: Miniprep 4 ug");
			} else {
				List<OrderItem> itemList = this.orderItemDao
						.findBy("parentId",
								orderItem.getOrderItemId());
				OrderPlasmidPreparation orderPlasmidPreparation = null;
				for (OrderItem item : itemList) {
					orderPlasmidPreparation = orderPlasmidPreparationDao
							.getById(item.getOrderItemId());
					if (orderPlasmidPreparation != null) {
						break;
					}
				}
				result.append(",Standard delivery: Miniprep ")
						.append(orderPlasmidPreparation != null ? (orderPlasmidPreparation
								.getPrepWeight() + orderPlasmidPreparation
								.getPrepWtUom())
								: "");
			}
			result.append(",Delivery time:").append(orderItem.getShipSchedule()).append("days<br>");
		} else if (orderItemType != null
				&& orderItemType.toLowerCase().indexOf("antibody") != -1
				&& orderItemType.toLowerCase().indexOf("antibody drug") == -1) {
			OrderAntibody orderAntibody = this.orderAntibodyDao.getById(orderItem.getOrderItemId());
			if(orderAntibody==null) {
				return "";
			}
			result.append("&nbsp;&nbsp;Antibody:<br>");
			result.append("antibody name:").append(orderAntibody.getAntibodyName());
			if(orderItem.getShipSchedule()!=null) {
				result.append(",Delivery time:").append(orderItem.getShipSchedule()).append("days");
			}
			result.append("<br>");
			result.append("peptide list:<br>");
			List<OrderItem> childItemList = this.orderItemDao.findBy("parentId", orderItem.getOrderItemId());
			for (int j = 0; j < childItemList.size(); j++) {
				OrderItem child = childItemList.get(j);
				String childItemType = null;
				// 获取orderItem类型
				if (QuoteItemType.PRODUCT.value().equals(
						child.getType())) {
					ProductClass pdtClass = this.productClassDao
							.getById(child.getClsId());
					childItemType = pdtClass == null ? null : pdtClass
							.getName();
				} else {
					ServiceClass servClass = this.serviceClassDao
							.getById(child.getClsId());
					childItemType = servClass == null ? null
							: servClass.getName();
				}
				if (StringUtils.isNotEmpty(childItemType)&& childItemType.toLowerCase().startsWith("peptide")) {
					OrderPeptide orderPeptide = orderPeptideDao.getById(child.getOrderItemId());
					if (orderPeptide == null) {
						orderPeptide = new OrderPeptide();
					}
					result.append("peptide name").append(orderPeptide.getName()).append(": ");
					if (StringUtils.isNotEmpty(orderPeptide.getQuantity())) {
						result.append(",Quantity:").append(orderPeptide.getQuantity());
					}
					if (StringUtils.isNotEmpty(orderPeptide.getPurity())) {
						result.append(",Purity:").append(orderPeptide.getPurity());
					}
					if (orderPeptide.getSeqLength() != null) {
						result.append(",Sequence length:").append(orderPeptide.getSeqLength()).append("aa");
					}
					if (StringUtils.isNotEmpty(orderPeptide.getNTerminal())) {
						result.append(",").append(orderPeptide.getNTerminal())
								.append(";");
					}
					if (StringUtils.isNotEmpty(orderPeptide
							.getCTerminal())) {
						result.append(orderPeptide.getCTerminal())
								.append(";");
					}
					if (StringUtils.isNotEmpty(orderPeptide
							.getModification())) {
						result.append(orderPeptide.getModification())
								.append(";");
					}
					if (StringUtils.isNotEmpty(orderPeptide
							.getDisulfideBridge())) {
						result.append(";Disulfide Bridge:")
								.append(orderPeptide
										.getDisulfideBridge())
								.append(";");
					}
					if (orderPeptide.getAliquoteVialQty() != null) {
						result.append(
								orderPeptide.getAliquoteVialQty())
								.append("vials");
					}

					result.append(orderPeptide == null ? null
							: orderPeptide.getSequence()).append("<br>");
				}
			}
		} else if (orderItemType != null&& orderItemType.toLowerCase().startsWith("peptide")) {
				OrderPeptide orderPeptide = orderPeptideDao.getById(orderItem.getOrderItemId());
				if(orderPeptide==null) {
					return "";
				}
				result.append("&nbsp;&nbsp;Peptide:<br>");
				result.append("peptide name:").append(orderPeptide.getName());
				if(orderItem.getShipSchedule()!=null) {
					result.append(",Delivery time:").append(orderItem.getShipSchedule()).append("days");
				}
				if (orderPeptide.getSeqLength() != null) {
					result.append(",Sequence length:").append(orderPeptide.getSeqLength()).append("aa");
				}
				if (StringUtils.isNotEmpty(orderPeptide.getQuantity())) {
					result.append(",Quantity:").append(orderPeptide.getQuantity());
				}
				if (StringUtils.isNotEmpty(orderPeptide.getPurity())) {
					result.append(",Purity:").append(orderPeptide.getPurity());
				}
				if (StringUtils.isNotEmpty(orderPeptide.getNTerminal())) {
					result.append(",").append(orderPeptide.getNTerminal())
							.append(";");
				}
				if (StringUtils.isNotEmpty(orderPeptide.getCTerminal())) {
					result.append(orderPeptide.getCTerminal())
							.append(";");
				}
				if (StringUtils.isNotEmpty(orderPeptide.getModification())) {
					result.append(orderPeptide.getModification()).append(
							";");
				}
				if (StringUtils.isNotEmpty(orderPeptide
						.getDisulfideBridge())) {
					result.append("Disulfide Bridge:")
							.append(orderPeptide.getDisulfideBridge())
							.append(";");
				}
				if (orderPeptide.getAliquoteVialQty() != null) {
					result.append(orderPeptide.getAliquoteVialQty())
							.append("vials");
				}
				if(StringUtils.isNotEmpty(orderPeptide.getComments())) {
					result.append("<br>").append(orderPeptide.getComments());
				}
				if(StringUtils.isNotEmpty(orderPeptide.getSequence())) {
					result.append("<br>").append(orderPeptide == null ? null : orderPeptide.getSequence()).append("<br>");
				}
			} else if (orderItemType.toLowerCase().startsWith("oligo")) {
				OrderOligo orderOligo = this.orderOligoDao.getById(orderItem.getOrderItemId());
				if(orderOligo==null) {
					return "";
				}
				result.append("&nbsp;&nbsp;Oligo:<br>");
				result.append("oligo name:").append(orderOligo.getOligoName());
				if(orderItem.getShipSchedule()!=null) {
					result.append(",Delivery time:").append(orderItem.getShipSchedule()).append("days");
				}
				result.append(",").append("Aliquoting into:")
					  .append(orderOligo.getAliquotingInto())
					  .append(",Backbone:")
					  .append(orderOligo.getBackbone())
					  .append(",Purification:")
					  .append(orderOligo.getPurification())
					  .append(",Synthesis Scales:")
					  .append(orderOligo.getSynthesisScales()).append("<br>")
					  .append("Sequence:").append(orderOligo.getSequence());

			} else if (orderItemType.toLowerCase().startsWith("sirna")
					|| orderItemType.toLowerCase().startsWith("mirna")) {
				OrderSirnaAndMirna orderSirnaAndMirna = orderSirnaAndMirnaDao.getById(orderItem.getOrderItemId());
				if (orderSirnaAndMirna == null) {
					return "";
				}
				result.append("&nbsp;&nbsp;Sirna Or Mirna:<br>");
				result.append("Sirna Or Mirna name:").append(orderSirnaAndMirna.getGeneName());
				if(orderItem.getShipSchedule()!=null) {
					result.append(",Delivery time:").append(orderItem.getShipSchedule()).append("days");
				}
				result.append(",Quantity:").append(orderSirnaAndMirna.getQuantity()).append(",Vector name:")
					  .append(orderSirnaAndMirna.getVectorName());
			} else if (orderItemType.toLowerCase().startsWith("custom services")) {
				result.append("&nbsp;&nbsp;Custom Services:<br>");
				result.append("name:").append(orderItem.getName());
				if(orderItem.getShipSchedule()!=null) {
					result.append(",Delivery time:").append(orderItem.getShipSchedule()).append("days");
				}
				com.genscript.gsscm.order.entity.OrderService orderService = this.orderServiceDao.getById(orderItem.getOrderItemId());
				if(orderService==null) {
					return "";
				}
				result.append(";").append(orderService.getCustomDesc());
			} else {
				OrderPkgService orderPkgService = this.orderPkgServiceDao.getById(orderItem.getOrderItemId());
				if(orderPkgService==null) {
					return "";
				}
				result.append("&nbsp;&nbsp;").append(orderItemType).append(":<br>");
				result.append("name:").append(orderItem.getName());
				if(orderItem.getShipSchedule()!=null) {
					result.append(",Delivery time:").append(orderItem.getShipSchedule()).append("days");
				}
				result.append("<br>").append(orderPkgService.getDescription());
			}
		result.append("<br>");
		return result.toString();
	}
	
	/**
	 * 获取数据库中所有work order生成的lotNo数量
	 */
	public Long getTotalForLotNo() {
		return this.workOrderLotDao.getTotalCount();
	}
	
	/**
	 * 判断当前item能否生成work order
	 */
	public String judgeItem(Integer orderNo,Integer itemNo) {
		OrderItem item = this.orderItemDao.getOrderItem(orderNo, itemNo);
		if(QuoteItemType.PRODUCT.value().equals(item.getType())) {
			return "ok";
		}else if(item.getParentId()==null||item.getParentId()==0) {
			return "ok";
		}
		OrderItem parentOrderItem = this.orderItemDao.getById(item.getParentId());
		if(parentOrderItem==null) {
			return "ok";
		}
		if(parentOrderItem.getClsId()==2||parentOrderItem.getClsId()==14||parentOrderItem.getClsId()==16||
				parentOrderItem.getClsId()==18||parentOrderItem.getClsId()==32) {
			List<WorkOrder> workOrderList = this.workOrderDao.findByPO(parentOrderItem.getOrderNo(),parentOrderItem.getItemNo());
			if(workOrderList==null) {
				workOrderList = this.workOrderDao.findBySNAndSIN(parentOrderItem.getOrderNo(), parentOrderItem.getItemNo());
			}
			WorkOrder workOrder = workOrderList!=null&&workOrderList.size()>0?workOrderList.get(0):null;
			if(workOrder==null) {
				return "no";
			} else {
				String index = "";
				if(item.getCatalogNo().indexOf("-")!=-1) {
					index = item.getCatalogNo().split("-")[1];
				}
				return String.valueOf(workOrder.getOrderNo())+"-"+index;
			}
		}
		return "ok";
	}
	
	/**
	 * 通过WorkCenter主键查找
	 * @author Zhang Yong
	 * @param workCenterId
	 * @return
	 */
	public WorkCenter getWorkCenterById (Integer workCenterId) {
		return workCenterDao.getById(workCenterId);
	}
	
	/**
	 * 获取最大targetDate
	 */
	public Date getMaxTargetDate(Integer orderNo) {
		return this.orderItemDao.getMaxTargetDate(orderNo);
	}
	
	/**
	 * 获取最大leadtTime
	 */
	public Integer getMaxLeadtime(Integer orderNo) {
		return this.orderItemDao.getMaxLeadtime(orderNo);
	}
	
	public List<AntibodyOprPurificationResults> getAntibodyOprPurificationResultsList(Integer woOperationId) {
		return this.antibodyOprPurificationResultsDao.findBy("refId", woOperationId);
	}
	
	public List<AntibodyOprExperimentDatas> getAntibodyOprExperimentDatas(Integer woOperationId) {
		return this.antibodyOprExperimentDatasDao.findBy("woOperationId", woOperationId);
	}
	
	public Page<WorkOrder> getDescription(Page<WorkOrder> page) {
		for (WorkOrder workOrder : page.getResult()) {
			setWorkOrderDescription(workOrder);
		}
		return page;
	}

    //add by zhanghuibin 取得一个workOrder的description
    private void setWorkOrderDescription(WorkOrder workOrder){
        String workOrderType = null;
			if (QuoteItemType.PRODUCT.value().equals(workOrder.getItemType())) {
                ProductClass pdtClass = this.productClassDao.getById(workOrder.getClsId());
                workOrderType = pdtClass == null ? null : pdtClass.getName();
            } else {
                ServiceClass servClass = this.serviceClassDao.getById(workOrder.getClsId());
                workOrderType = servClass == null ? null : servClass.getName();
            }
			if(workOrderType==null) {
				workOrder.setItemDesc("");
				return;
			}
			OrderItem orderItem = null;
			MfgOrder mfgOrder = this.mfgOrderDao.getById(workOrder.getSoNo());
			if(mfgOrder==null) {
				orderItem = this.orderItemDao.getOrderItem(workOrder.getSoNo(), workOrder.getSoItemNo());
			} else {
				orderItem = this.orderItemDao.getOrderItem(mfgOrder.getSrcSoNo(), workOrder.getSoItemNo());
			}
			if(orderItem==null) {
				workOrder.setItemDesc("");
				return;
			}
			StringBuffer itemDesc =new StringBuffer("");
			itemDesc.append(getItemDetail(orderItem));
			itemDesc.append("<br>cost:").append(orderItem.getCost());
			workOrder.setItemDesc(itemDesc.toString());
    }

    /*
    * add by zhanghuibin
    * */
    PeptideTemplateDTO getWorkOrderData(String delivery_date, String work_order, String order_no, String so_item_no){
        PeptideTemplateDTO templateDTO = new PeptideTemplateDTO();
        OrderPeptide orderPeptide = orderPeptideDao.getOrderPeptideByNo(order_no, so_item_no);
        List<WorkOrderLot> lots = workOrderLotDao.getWorkOrderLotByWo(Integer.valueOf(work_order));
        StringBuffer lot = new StringBuffer("");
        for(WorkOrderLot wl : lots){
            lot.append(wl.getLotNo() + "<br>");
        }
        templateDTO.setAliq(orderPeptide.getAliquoteVialQty() == null ? "" : orderPeptide.getAliquoteVialQty().toString());
        templateDTO.setComments(orderPeptide.getComments());
        templateDTO.setDeliveryDate(delivery_date);
        templateDTO.setLotNo(lot.toString());
        templateDTO.setModification(orderPeptide.getModification());
        templateDTO.setName(orderPeptide.getName());
        templateDTO.setOrderId(order_no + "-" +so_item_no);
        String pi = orderPeptide.getPi() == null ? "" : orderPeptide.getPi().toString();
        String mw = orderPeptide.getMolecularWeight() == null ? "" : orderPeptide.getMolecularWeight().toString();
        templateDTO.setPm(pi + "/" + mw);
        templateDTO.setQuantity(orderPeptide.getQuantity());
        templateDTO.setSequence(orderPeptide.getSequence());
        templateDTO.setPurity(orderPeptide.getPurity());
        templateDTO.setWo(work_order);
        return templateDTO;
    }

    public PeptideTemplateDTO operationTableData(String delivery_date, String work_order, String order_no, String so_item_no){
        PeptideTemplateDTO templateDTO =  getWorkOrderData(delivery_date, work_order, order_no, so_item_no);
        templateDTO.setHyd(get_hydrophobic(templateDTO.getModification(), templateDTO.getSequence()));
        return templateDTO;
    }

    public PeptideTemplateDTO qcTableData(String delivery_date, String work_order, String order_no, String so_item_no){
        return getWorkOrderData(delivery_date, work_order, order_no, so_item_no);
    }

    String get_hydrophobic(String modifaction, String sequence){
        String[] modification_arr = modifaction.split(";");
        String cmod = "";
        String nmod = "";
        String bridge = "";
        String omod = "";
        String result = "";
        for (String modification : modification_arr) {
            if("C - Terminal".equals(modification.toUpperCase().trim()))
            {
                cmod = modification.replaceAll("^\\s*+|\\s+$", "");
            }
            else if("N - Terminal".equals(modification.toUpperCase().trim()))
            {
                nmod = modification.replaceAll("^\\s*+|\\s+$", "");
            }
            else if("Bridge".equals(modification.toUpperCase().trim()))
            {
                bridge = modification.replaceAll("^\\s*+|\\s+$", "");
            }else{
                omod = modification.replaceAll("^\\s*+|\\s+$", "") + ";";
            }
        }
        StringBuffer url = new StringBuffer("https://www.genscript.com/ssl-bin/site2/peptide_calculation.cgi");
        StringBuffer params = new StringBuffer("op=calculate");
        params.append("&subdata="+sequence);
        params.append("&nmod=" + nmod + "&cmod=" + cmod + "&omod=" + omod + "&bridge=" + bridge);
        try {
			String returnStr = UrlUtil.postReq(url.toString(), params.toString());
    			if (StringUtils.isNotEmpty(returnStr)) {
                  Pattern pattern = Pattern.compile("<hydrophobic value=\\\"(.*?)\\\">");
                  Matcher matcher = pattern.matcher(returnStr);
                  if(matcher.find()){
                      result = matcher.group(1);
                  }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    
    /**
	 * 获得工单对应的item的序列
	 */
	public Map<String,String> getSequenceByWO(String sessId) {
		Map<String,String> seqMap = new LinkedHashMap<String,String>();
		Map<String, Object> session = ActionContext.getContext().getSession();
		WorkOrderDTO workOrder = (WorkOrderDTO)session.get(sessId);
		if(workOrder==null) {
			return new LinkedHashMap<String,String>();
		}
		MfgOrder mfgOrder = this.mfgOrderDao.getById(workOrder.getSoNo());
		workOrder.setSrcSoNo(mfgOrder!=null?mfgOrder.getSrcSoNo():workOrder.getSoNo());
		OrderItem item = this.orderItemDao.getOrderItem(workOrder.getSrcSoNo(), workOrder.getSoItemNo());
		if(item==null) {
			return new LinkedHashMap<String,String>();
		}
		OrderGeneSynthesis  OrderGeneSynthesis = orderGeneSynthesisDao.getById(item.getOrderItemId());
		if(OrderGeneSynthesis==null) {
			return new LinkedHashMap<String,String>();
		}
		if(OrderGeneSynthesis.getSequence()!=null) {
			seqMap.put("sequence", OrderGeneSynthesis.getSequence());
		}
		if(OrderGeneSynthesis.getSequence3()!=null) {
			seqMap.put("sequence3", OrderGeneSynthesis.getSequence3());
		}
		if(OrderGeneSynthesis.getSequence5()!=null) {
			seqMap.put("sequence5", OrderGeneSynthesis.getSequence5());
		}
		seqMap.put("job_name", workOrder.getSrcSoNo()+"_"+workOrder.getSoItemNo());
		return seqMap;
	}
	
	/**
	 * 获得所有被选择的wo所对应的peptide信息并返回
	 */
	public List<PeptideTemplateDTO> getSelWOPeptideInfo(String allChoiceVal) {
		List<PeptideTemplateDTO> list = new ArrayList<PeptideTemplateDTO>();
		String[] workOrderIdArray = allChoiceVal.split(",");
		for(String id:workOrderIdArray) {
			WorkOrder workOrder = this.workOrderDao.getById(Integer.parseInt(id));
			if(workOrder==null) {
				continue;
			}
			List<WorkOrderLot> workOrderLotList = workOrderLotDao.getWorkOrderLotByWo(Integer.parseInt(id));
			StringBuffer lotNoBuff = new StringBuffer("");
			StringBuffer appearance = new StringBuffer("");
			for(WorkOrderLot lot:workOrderLotList) {
				if(lotNoBuff.toString().equals("")) {
					lotNoBuff.append(lot.getLotNo());
					appearance.append(lot.getAdtlInfo1());
				} else {
					lotNoBuff.append(",").append(lot.getLotNo());
					appearance.append(",").append(lot.getAdtlInfo1());
				}
				
			}
			workOrder.setLotNo(lotNoBuff.toString());
			String workOrderType = null;
			if (QuoteItemType.PRODUCT.value().equals(workOrder.getItemType())) {
                ProductClass pdtClass = this.productClassDao.getById(workOrder
                        .getClsId());
                workOrderType = pdtClass == null ? null : pdtClass.getName();
            } else {
                ServiceClass servClass = this.serviceClassDao.getById(workOrder
                        .getClsId());
                workOrderType = servClass == null ? null : servClass.getName();
            }
			if(workOrderType==null) {
				continue;
			}
			MfgOrder mfgOrder = this.mfgOrderDao.getById(workOrder.getSoNo());
			workOrder.setSrcSoNo(mfgOrder!=null?mfgOrder.getSrcSoNo():workOrder.getSoNo());
			OrderItem orderItem = null;
			if(mfgOrder==null) {
				orderItem = this.orderItemDao.getOrderItem(workOrder.getSoNo(), workOrder.getSoItemNo());
			} else {
				orderItem = this.orderItemDao.getOrderItem(mfgOrder.getSrcSoNo(), workOrder.getSoItemNo());
			}
			
			if(orderItem==null) {
				continue;
			}
			PeptideTemplateDTO peptideTemplateDTO = new PeptideTemplateDTO();
			peptideTemplateDTO.setWo(id);
			if(workOrderType.toLowerCase().contains("peptide")) {
                peptideTemplateDTO.setOrderId(workOrder.getSrcSoNo()+"-"+workOrder.getSoItemNo());
                peptideTemplateDTO.setLotNo(workOrder.getLotNo());
                peptideTemplateDTO.setAppearance(appearance.toString());
                OrderPeptide orderPeptide = orderPeptideDao.getById(orderItem.getOrderItemId());
                if(orderPeptide==null) {
                	list.add(peptideTemplateDTO);
                	continue;
                }
				if(orderPeptide!=null) {
					peptideTemplateDTO.setMw(orderPeptide.getMolecularWeight()!=null?orderPeptide.getMolecularWeight().setScale(2, BigDecimal.ROUND_HALF_UP):null);
					peptideTemplateDTO.setName(orderPeptide.getName());
					peptideTemplateDTO.setRealPurity(orderPeptide.getRealPurity());
					peptideTemplateDTO.setAliq(orderPeptide.getAliquoteVialQty()!=null?orderPeptide.getAliquoteVialQty()+"vials":"");
					peptideTemplateDTO.setRealQuantity(orderPeptide.getRealQuantity());
					
				}
				if(orderItem.getShippingRoute()!=null&&orderItem.getShippingRoute().startsWith("CN_Sales")) {
					peptideTemplateDTO.setDestination("CN_Sales");
				} else {
					peptideTemplateDTO.setDestination("USA");
				}
			}
			list.add(peptideTemplateDTO);
		}
		return list;
	}
	
	
	public boolean isCreate(Integer soNo,Integer soItemNo) {
		List<WorkOrder> list = this.workOrderDao.findByPO(soNo, soItemNo);
		if(list!=null&&list.size()>0) {
			return true;
		} 
		return false;
	}

    //add by zhanghuibin
    public List<WorkOrder> getWorkNos(Integer soNo,Integer soItemNo) {
		List<WorkOrder> list = this.workOrderDao.findByPO(soNo, soItemNo);
        return list;
	}
    
    /**
     * 过滤掉已经生成wo的item
     * @param itemIds
     * @return
     */
    public StringBuffer filterItems(String itemIds) {
    	StringBuffer result = new StringBuffer("");
    	String[] itemId = itemIds.split(",");
    	for(String id:itemId) {
    		if(StringUtils.isNumeric(id)) {
    			MfgOrderItem mfgOrderItem = this.mfgOrderItemDao.getById(Integer.parseInt(id));
    			MfgOrder mfgOrder = this.mfgOrderDao.getById(mfgOrderItem.getOrderNo());
    			if(mfgOrderItem==null||mfgOrder==null) {
    				continue;
    			}
    			if(this.workOrderDao.findLastByPo(mfgOrder.getSrcSoNo(), mfgOrderItem.getItemNo())==null) {
    				result.append(id).append(",");
    			}
    		}
		}
    	return result;
    }
}
