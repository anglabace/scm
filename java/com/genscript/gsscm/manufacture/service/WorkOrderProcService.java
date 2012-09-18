package com.genscript.gsscm.manufacture.service;

/**
 * @Description: 订单处理业务类
 * @Copyright: Copyright genscrpit (c)2010
 * @author: lizhang
 * @version: 1.0
 * @date: 2010-11-22上午09:56:34    
 * 
 * Modification History:
 * Date			Author			Version			Description
 * -------------------------------------------------------------
 *
 */
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.gsscm.common.constant.Constants;
import com.genscript.gsscm.common.constant.QuoteItemType;
import com.genscript.gsscm.common.constant.WorkOrderStatus;
import com.genscript.gsscm.common.util.DateUtils;
import com.genscript.gsscm.common.util.PdfUtils;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.customer.dao.CustomerDao;
import com.genscript.gsscm.customer.entity.Customer;
import com.genscript.gsscm.manufacture.dao.OperationDao;
import com.genscript.gsscm.manufacture.dao.ResourceDao;
import com.genscript.gsscm.manufacture.dao.WoBatchDetailDao;
import com.genscript.gsscm.manufacture.dao.WoBatcheDao;
import com.genscript.gsscm.manufacture.dao.WoOperationResourceDao;
import com.genscript.gsscm.manufacture.dao.WoStatusHistoryDao;
import com.genscript.gsscm.manufacture.dao.WorkGroupDao;
import com.genscript.gsscm.manufacture.dao.WorkOrderDao;
import com.genscript.gsscm.manufacture.dao.WorkOrderGroupDao;
import com.genscript.gsscm.manufacture.dao.WorkOrderLotDao;
import com.genscript.gsscm.manufacture.dao.WorkOrderOperationDao;
import com.genscript.gsscm.manufacture.dto.PeptideTemplateDTO;
import com.genscript.gsscm.manufacture.dto.ProteinLabelsDTO;
import com.genscript.gsscm.manufacture.dto.WoBatcheDTO;
import com.genscript.gsscm.manufacture.dto.WorkOrderDTO;
import com.genscript.gsscm.manufacture.entity.Resource;
import com.genscript.gsscm.manufacture.entity.WoBatchDetail;
import com.genscript.gsscm.manufacture.entity.WoBatche;
import com.genscript.gsscm.manufacture.entity.WoOperationResource;
import com.genscript.gsscm.manufacture.entity.WoStatusHistory;
import com.genscript.gsscm.manufacture.entity.WorkOrder;
import com.genscript.gsscm.manufacture.entity.WorkOrderLot;
import com.genscript.gsscm.manufacture.entity.WorkOrderOperation;
import com.genscript.gsscm.order.dao.MfgOrderDao;
import com.genscript.gsscm.order.dao.MfgOrderItemDao;
import com.genscript.gsscm.order.dao.OrderAntibodyDao;
import com.genscript.gsscm.order.dao.OrderCustCloningDao;
import com.genscript.gsscm.order.dao.OrderGeneSynthesisDao;
import com.genscript.gsscm.order.dao.OrderItemDao;
import com.genscript.gsscm.order.dao.OrderOligoDao;
import com.genscript.gsscm.order.dao.OrderPeptideDao;
import com.genscript.gsscm.order.dao.OrderPlasmidPreparationDao;
import com.genscript.gsscm.order.dao.OrderProteinDao;
import com.genscript.gsscm.order.entity.Document;
import com.genscript.gsscm.order.entity.MfgOrder;
import com.genscript.gsscm.order.entity.OrderAntibody;
import com.genscript.gsscm.order.entity.OrderCustCloning;
import com.genscript.gsscm.order.entity.OrderGeneSynthesis;
import com.genscript.gsscm.order.entity.OrderItem;
import com.genscript.gsscm.order.entity.OrderOligo;
import com.genscript.gsscm.order.entity.OrderPeptide;
import com.genscript.gsscm.order.entity.OrderPlasmidPreparation;
import com.genscript.gsscm.order.entity.OrderProtein;
import com.genscript.gsscm.privilege.dao.UserDao;
import com.genscript.gsscm.privilege.dao.UserRoleDao;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.product.dao.ProductClassDao;
import com.genscript.gsscm.product.dao.ProductDao;
import com.genscript.gsscm.product.entity.Product;
import com.genscript.gsscm.product.entity.ProductClass;
import com.genscript.gsscm.serv.dao.ServiceClassDao;
import com.genscript.gsscm.serv.dao.ServiceClassificationDao;
import com.genscript.gsscm.serv.entity.ServiceClass;

@Service
@Transactional
public class WorkOrderProcService {
	@Autowired(required = false)
	private DozerBeanMapper dozer;
	@Autowired
	private WoBatcheDao woBatcheDao;
	@Autowired
	private WoBatchDetailDao woBatchDetailDao;
	@Autowired
	private WorkOrderDao workOrderDao;
	@Autowired
	private ProductClassDao productClassDao;
	@Autowired
	private ServiceClassDao serviceClassDao;
	@Autowired
	private OrderPeptideDao orderPeptideDao;
	@Autowired
	private OrderGeneSynthesisDao orderGeneSynthesisDao;
	@Autowired
	private OrderItemDao orderItemDao;
	@Autowired
	private MfgOrderItemDao mfgOrderItemDao;
	@Autowired
	private OrderCustCloningDao orderCustCloningDao;
	@Autowired
	private OrderPlasmidPreparationDao orderPlasmidPreparationDao;
	@Autowired
	private OrderAntibodyDao orderAntibodyDao;
	@Autowired
	private WorkOrderGroupDao workOrderGroupDao;
	@Autowired
	private WorkGroupDao workGroupDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ServiceClassificationDao serviceClassificationDao;
	@Autowired
	private MfgOrderDao mfgOrderDao;
	@Autowired
	private OrderProteinDao orderProteinDao;
	@Autowired
	private UserRoleDao userRoleDao;
	@Autowired
	private WoStatusHistoryDao woStatusHistoryDao;
	@Autowired
	private WorkOrderOperationDao workOrderOperationDao;
	@Autowired
	private WoOperationResourceDao woOperationResourceDao;
	@Autowired
	private ResourceDao resourceDao;
	@Autowired
	private OrderOligoDao orderOligoDao;
	@Autowired
	private OperationDao operationDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private WorkOrderLotDao workOrderLotDao;
	/**
	 * 订单打包
	 * @param woBatcheDTO 订单包
	 * @author lizhang
	 * @return
	 */
	public String saveWoBatche(WoBatcheDTO woBatcheDTO) throws Exception{
		String flg = null;
		WoBatche woBatche1 = findByBatchNo(woBatcheDTO.getBatchNo());
		if(woBatche1!=null) {
			flg = "The batchNo has been used.";
			return flg;
		}
		Integer loginUserId = SessionUtil.getUserId();
		List<WoBatchDetail> woBatchDetailList = new ArrayList<WoBatchDetail>();
		woBatcheDTO.setCreatedBy(loginUserId);
		WoBatche woBatche = this.dozer.map(woBatcheDTO, WoBatche.class);
		this.woBatcheDao.save(woBatche);
		for(String orderNo:woBatcheDTO.getOrderNoStr().split(",")) {
			if(this.woBatchDetailDao.isHasThisNo(Integer.parseInt(orderNo),woBatcheDTO.getBatchFunction())) {
				flg=orderNo+" has been packged!";
				this.woBatcheDao.delete(woBatche);
				return flg;
			} else {
				WoBatchDetail woBatcheDetail = new WoBatchDetail();
				WorkOrder workOrder = this.workOrderDao.getById(Integer.parseInt(orderNo));
				if(workOrder!=null) {
					woBatcheDetail.setWoBatches(woBatche);
					woBatcheDetail.setWorkOrders(workOrder);
					woBatchDetailList.add(woBatcheDetail);
				}
			}
			
		}
		if(woBatchDetailList.size()>0) {
			for(WoBatchDetail woBatcheDetail:woBatchDetailList) {
				this.woBatchDetailDao.save(woBatcheDetail);
			}
		}
		flg = "Success";
		
		return flg;
	}
	
	/**
	 * 根据batchNo查找WoBatche
	 * @author lizhang
	 * @param batchNo 打包号
	 */
	public WoBatche findByBatchNo(String batchNo) {
		WoBatche woBatche = woBatcheDao.findUniqueBy("batchNo", batchNo);
		return woBatche;
	}
	
	/**
	 * 校验传入的一组workOrder的状态是否统一
	 * @param orderNoStrs
	 * @return
	 * @author zhangyong
	 */
	public Map<String,List<WorkOrder>> checkWorkOrderNos (String orderNoStrs) throws Exception {
		if(orderNoStrs!=null&&!("").equals(orderNoStrs)) {
			String[] orderNoArr = orderNoStrs.split(",");
			Map<String,List<WorkOrder>> workOrderStatusMap = new HashMap<String,List<WorkOrder>>();
			List<WorkOrder> workOrderList = new ArrayList<WorkOrder>();
			for(String orderNo:orderNoArr) {
				WorkOrder workOrder = this.workOrderDao.get(Integer.parseInt(orderNo));
				if(workOrder != null && workOrder.getStatus() != null) {
					workOrderList.add(workOrder);
					workOrderStatusMap.put(workOrder.getStatus(), workOrderList);
				} else {
					return null;
				}
			}
			if (workOrderStatusMap.size() == 1) {
				return workOrderStatusMap;
			}
		}
		return null;
	}
	
	/**
	 * 校验传入的一组workOrder的work center是否统一
	 * @param orderNoStrs
	 * @return
	 * @author lizhang
	 */
	public String checkWoWorkCenter (String orderNoStrs) throws Exception {
		if(orderNoStrs!=null&&!("").equals(orderNoStrs)) {
			String[] orderNoArr = orderNoStrs.split(",");
			Integer workCenterId = null;
			for(String orderNo:orderNoArr) {
				WorkOrder workOrder = this.workOrderDao.get(Integer.parseInt(orderNo));
				if(workOrder.getWorkCenterId()==null) {
					return "fail";
				}
				if(workCenterId==null) {
					workCenterId = workOrder.getWorkCenterId();
				} else if(workCenterId.intValue()!=workOrder.getWorkCenterId().intValue()){
					return "fail";
				}
				
			}
			return workCenterId!=null?workCenterId.toString():"fail";
		}
		return "fail";
	}
	
	public boolean saveBatchOrderProcess (String orderNoStrs, 
			String workOrderStatus, String selectWorkOrderStatus, 
			String comment) throws Exception {
		if (orderNoStrs == null || ("").equals(orderNoStrs.trim())) {
			return false;
		}
		if (workOrderStatus == null || ("").equals(workOrderStatus.trim())) {
			return false;
		}
		if (selectWorkOrderStatus == null || ("").equals(selectWorkOrderStatus.trim())) {
			return false;
		}
		Map<String,List<WorkOrder>> workOrderStatusMap = checkWorkOrderNos(orderNoStrs);
		if (workOrderStatusMap != null && workOrderStatusMap.size() == 1) {
			for (String oldStatus : workOrderStatusMap.keySet()) {
				if (oldStatus == null || !workOrderStatus.equals(oldStatus)) {
					return false;
				}
				if (comment != null && !("").equals(comment.trim())) {
					byte[] b = comment.trim().getBytes(); 
					if (b.length > 250) {
						return false;
					}
				}
				List<WorkOrder> workOrderList = workOrderStatusMap.get(oldStatus);
				if (selectWorkOrderStatus.equals(oldStatus)) {
					//更新workOrder
					if (comment != null && !("").equals(comment.trim())) {
						for (WorkOrder workOrder : workOrderList) {
							workOrder.setComment(comment.trim());
							workOrderDao.save(workOrder);
						}
					}
				} else {
					for (WorkOrder workOrder : workOrderList) {
						//更新workOrder
						if (comment != null && !("").equals(comment.trim())) {
							workOrder.setComment(comment.trim());
						}
						workOrder.setStatus(selectWorkOrderStatus);
						workOrderDao.save(workOrder);
					}
				}
				return true;
			}
		}
		return false;
	}
	/**
	 * 通过wo的Id构造proteinLabelsDTO对象并返回
	 */
	public ProteinLabelsDTO createProteinLabelsDTO(Integer workOrderId) throws Exception{
		ProteinLabelsDTO proteinLabelsDTO =  new ProteinLabelsDTO();
		WorkOrder workOrder = this.workOrderDao.getById(workOrderId);
		if(workOrder==null) {
			return proteinLabelsDTO;
		}
		List<WorkOrderLot> workOrderLotList = workOrderLotDao.getWorkOrderLotByWo(workOrderId);
		StringBuffer lotNoBuff = new StringBuffer("");
		for(WorkOrderLot lot:workOrderLotList) {
			if(lotNoBuff.toString().equals("")) {
				lotNoBuff.append(lot.getLotNo());
			} else {
				lotNoBuff.append(",").append(lot.getLotNo());
			}
			
		}
		workOrder.setLotNo(lotNoBuff.toString());
		String proteinName = "";
		if(workOrder.getSoNo()!=null) {
			MfgOrder mfgOrder = this.mfgOrderDao.getById(workOrder.getSoNo());
			workOrder.setSrcSoNo(mfgOrder!=null?mfgOrder.getSrcSoNo():workOrder.getSoNo());
			OrderItem orderItem = this.orderItemDao.searchOrderItemByOrderNoAndItemNo(mfgOrder!=null?mfgOrder.getSrcSoNo():workOrder.getSoNo(), workOrder.getSoItemNo());
			OrderProtein orderProtein = orderItem!=null&&orderItem.getOrderItemId()!=null?this.orderProteinDao.getById(orderItem.getOrderItemId()):null;
			proteinName = orderProtein!=null?orderProtein.getName():"";
		}
		proteinLabelsDTO.setOrderNo(String.valueOf(workOrder.getSrcSoNo()));
		proteinLabelsDTO.setItemNo(String.valueOf(workOrder.getSoItemNo()));
		String lotNo = workOrder.getLotNo();
		proteinLabelsDTO.setLotNo(lotNo);
		proteinLabelsDTO.setProteinName(proteinName);
		if(proteinLabelsDTO.getLotNo()!=null&&StringUtils.isNotEmpty(proteinLabelsDTO.getLotNo())) {
			String str = proteinLabelsDTO.getLotNo().substring(proteinLabelsDTO.getLotNo().indexOf("P")+1,proteinLabelsDTO.getLotNo().length()-4);
			str = String.valueOf(Integer.parseInt(str)+1);
			StringBuffer plasmid6 = new StringBuffer(proteinLabelsDTO.getLotNo().substring(proteinLabelsDTO.getLotNo().indexOf("/"),proteinLabelsDTO.getLotNo().indexOf("/")+3));
			plasmid6.append(str).append(proteinLabelsDTO.getLotNo().substring(proteinLabelsDTO.getLotNo().length()-4));
			proteinLabelsDTO.setPlasmid6(plasmid6.toString());
		}
		return proteinLabelsDTO;
	}
	
	/**
	 * 通过wo的Id构造oligo标签
	 */
	public String createOligoLabels(Integer workOrderId) {
		WorkOrder workOrder = this.workOrderDao.getById(workOrderId);
		if(workOrder==null) {
			return "";
		}
		String oligoName = "";
		String sequence = "";
		String purity = "";
		Integer aliquoting_into = 1;
		if(workOrder.getSoNo()!=null) {
			MfgOrder mfgOrder = this.mfgOrderDao.getById(workOrder.getSoNo());
			workOrder.setSrcSoNo(mfgOrder!=null?mfgOrder.getSrcSoNo():workOrder.getSoNo());
			OrderItem orderItem = this.orderItemDao.searchOrderItemByOrderNoAndItemNo(mfgOrder!=null?mfgOrder.getSrcSoNo():workOrder.getSoNo(), workOrder.getSoItemNo());
			OrderOligo orderOligo = orderItem!=null&&orderItem.getOrderItemId()!=null?this.orderOligoDao.getById(orderItem.getOrderItemId()):null;
			oligoName = orderOligo!=null?orderOligo.getOligoName():"";
			sequence = orderOligo!=null?orderOligo.getSequence():"";
			purity = orderOligo!=null?orderOligo.getPurification():"";
			aliquoting_into = orderOligo!=null&&orderOligo.getAliquotingInto()!=null?orderOligo.getAliquotingInto():1;
		}
		sequence = sequence!=null?sequence:"";
		Pattern p = Pattern.compile("[/]+");
		String[] result = p.split(sequence);
		Double res= 0.0;
		for(String str:result) {
			if(StringUtils.isNotEmpty(str)) {
				if(str.startsWith("5'")||str.startsWith("3'")||str.startsWith("i")) {
					res = res+getModficationValue(str);
				} else {
					res = res+getNearestNeighborAndIndividual(str);
				}
			}
		}
		Double args = new BigDecimal((1.0/res)*10*10*10*10*10*10).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		StringBuffer oligoLabels = new StringBuffer("");
		oligoLabels .append("<input type='hidden' name='args' id='args' value='").append(args).append("'></input>")
					.append("<input type='hidden' name='aliquotingInto' id='aliquotingInto' value='").append(aliquoting_into).append("'></input>")
					.append("<span>Oligo Name:<input type='text' id='oligo' class='NText' size=14 name='oligoName' id='oligoName' value='").append(oligoName).append("'></input><br>")
				    .append("Purity:").append("<span><input type='text' id='purity' class='NText' size=14 value='").append(purity).append("'></input></span><br>")
				    .append("OD:").append("<input type='text' id='OD' class='NText' size=14></input>OD</span><br>")
				    .append("Cat.No.").append("<span><input type='text' id='catalog' class='NText' value='").append(workOrder.getSrcSoNo()).append("-").append(workOrder.getSoItemNo()).append("' size=14></input></span><br></span>")
				    .append("Aliquoting into:").append(aliquoting_into).append("Tubes");
		return oligoLabels.toString();
	}
	
	/**
	 * 
	 */
	private Long getNearestNeighborAndIndividual(String seq) {
		char[] seqCharArray = seq.toCharArray();
		Long result = 0l;
		for(int i = 0;i<seqCharArray.length-1;i++) {
			result = result + getSeqValue(seqCharArray[i],seqCharArray[i+1]);
			result = result - getSeqValue(seqCharArray[i+1]);
		}
		return result;
	}
	
	/**
	 * 
	 */
	private Integer getSeqValue(char args1,char args2) {
		int[][] table = new int[][]{
											{27400,21200,25000,22800,22800,25400,24100,25067,24533,23000,23800,25100,23100,23900,24300,22000,26200},
											{21200,14600,18000,15200,15200,18600,17250,18133,17933,15933,17000,18200,16300,16600,17900,14900,19600},
											{25200,17600,21600,20000,20000,22600,21100,22267,21467,19733,20933,22600,19600,20800,21400,18800,23400},
											{23400,16200,19000,16800,16800,20600,18850,19733,19533,17333,18800,20100,17600,17900,19800,16500,21200},
											{25400,18600,22400,20600,20600,23000,21750,22800,22133,20533,21533,23000,20500,21500,22000,19600,23900}, 
											{24300,17400,20900,18700,18700,21800,20325,21300,20867,19000,20133,21500,19150,19800,20850,18050,22600}, 
											{23400,16200,19000,16800,16800,16200,18850,19733,19533,17333,18800,20100,17600,17900,19800,16500,21200}, 
											{25333,18333,21867,19867,19867,22867,21350,22356,21844,20022,21178,22600,20100,20867,21833,19100,23600}, 
											{24600,17800,21533,19333,19333,22200,20817,21822,21311,19556,20578,21967,19667,20433,21200,18567,23067}, 
											{23267,16133,19533,17333,17333,20600,19067,20044,19644,17667,18911,20300,17833,18433,19700,16733,21400}, 
											{24000,17333,20667,18267,18267,21533,20067,20978,20667,18756,19867,21133,19000,19467,20667,17800,22333}, 
											{25400,18700,22000,19800,19800,23000,21475,22400,22033,20167,21300,22600,20350,20900,22050,19250,23700}, 
											{23200,16100,19800,17600,17600,20600,19175,20200,19700,17833,18967,20400,17950,18700,19650,16850,21500}, 
											{48600,33800,40600,36800,36800,43200,39950,42000,41000,37067,39733,42700,37200,38700,41200,35300,44600}, 
											{24300,17900,21500,19000,19000,22000,20675,21600,21233,19467,20400,21650,19700,20250,21100,18450,22900}, 
											{22300,15400,18500,16000,16000,19600,18050,18933,18733,16633,17900,19150,16950,17250,18850,15700,20400}, 
											{26300,19400,23300,21400,21400,24000,22600,23667,23000,21367,22367,23850,21350,22350,22850,20400,24800}
									};
		int row = 0;
		int col = 0;
		switch(args1) {
		   case 'A':row = 0;break;
		   case 'C':row = 1;break;
		   case 'G':row = 2;break;
		   case 'T':row = 3;break;
		   case 'I':row = 4;break;
		   case 'N':row = 5;break;
		   case 'U':row = 6;break;
		   case 'D':row = 7;break;
		   case 'V':row = 8;break;
		   case 'B':row = 9;break;
		   case 'H':row = 10;break;
		   case 'W':row = 11;break;
		   case 'S':row = 12;break;
		   case 'K':row = 13;break;
		   case 'M':row = 14;break;
		   case 'Y':row = 15;break;
		   case 'R':row = 16;break;
		   default:break;
		}
		switch(args2) {
		   case 'A':col = 0;break;
		   case 'C':col = 1;break;
		   case 'G':col = 2;break;
		   case 'T':col = 3;break;
		   case 'U':col = 4;break;
		   case 'I':col = 5;break;
		   case 'N':col = 6;break;
		   case 'D':col = 7;break;
		   case 'V':col = 8;break;
		   case 'B':col = 9;break;
		   case 'H':col = 10;break;
		   case 'W':col = 11;break;
		   case 'S':col = 12;break;
		   case 'K':col = 13;break;
		   case 'M':col = 14;break;
		   case 'Y':col = 15;break;
		   case 'R':col = 16;break;
		   default:break;
		}
		return table[row][col];
	}
	
	/**
	 * 
	 */
	private Integer getSeqValue(char args1) {
		int[] table = new int[]{15400,7400,11500,8700,10000,12350,10750,11867,11433,9200,10500,12050,9450,10100,11400,8050,13450};
		int row = 0;
		switch(args1) {
		   case 'A':row = 0;break;
		   case 'C':row = 1;break;
		   case 'G':row = 2;break;
		   case 'T':row = 3;break;
		   case 'I':row = 4;break;
		   case 'N':row = 5;break;
		   case 'U':row = 6;break;
		   case 'D':row = 7;break;
		   case 'V':row = 8;break;
		   case 'B':row = 9;break;
		   case 'H':row = 10;break;
		   case 'W':row = 11;break;
		   case 'S':row = 12;break;
		   case 'K':row = 13;break;
		   case 'M':row = 14;break;
		   case 'Y':row = 15;break;
		   case 'R':row = 16;break;
		   default:break;
		}
		return table[row];
	}
	
	/**
	 * 
	 */
	private Integer getModficationValue(String mod) {
		int[][] table=new int[][]{
									{20960,16300,31600,29100,20100,10000,4900,22600,13000,0,0,0},
									{21000,0,0,36900,20100,10000,4900,0,13000,8000,8000,12783},
									{13700,0,0,29100,0,0,10000,4900,0,0,0,0,0}
								 };
		int row = 0;
		int col = 0;
		if(mod.startsWith("3'")) {
			row =1;
		} else if(mod.startsWith("i")) {
			row = 2;
		} else if(mod.startsWith("5'")) {
			row = 0;
		} else {
			return 0;
		}
		if(mod.endsWith("6-FAM")) {
			col = 0;
		} else if(mod.endsWith("TET")) {
			col = 1;
		} else if(mod.endsWith("HEX")) {
			col = 2;
		} else if(mod.endsWith("TAMRA")) {
			col = 3;
		} else if(mod.endsWith("JOE")){
			col = 4;
		} else if(mod.endsWith("CY5")) {
			col = 5;
		} else if(mod.endsWith("CY3")) {
			col = 6;
		} else if(mod.endsWith("ROX")) {
			col = 7;
		} else if(mod.endsWith("DIG")) {
			col = 8;
		} else if(mod.endsWith("BHQ-1")) {
			col = 9;
		} else if(mod.endsWith("BHQ-2")) {
			col = 10;
		} else if(mod.endsWith("DABCYL")){
			col = 11;
		} else {
			return 0;
		}
		return table[row][col];
	}
		
	/**
	 * 通过Wo的Id获取多抗的标签内容
	 */
	public String getLabelToMonoclonal(Integer workOrderId) {
		StringBuffer result = new StringBuffer("");
		WorkOrder workOrder = this.workOrderDao.getById(workOrderId);
		if(workOrder==null) {
			return null;
		}
		MfgOrder mfgOrder = this.mfgOrderDao.getById(workOrder.getSoNo());
		workOrder.setSrcSoNo(mfgOrder!=null?mfgOrder.getSrcSoNo():null);
		Integer usOrderNo = workOrder.getSrcSoNo()!=null?workOrder.getSrcSoNo():workOrder.getSoNo();
		OrderItem item = this.orderItemDao.searchOrderItemByOrderNoAndItemNo(usOrderNo, workOrder.getSoItemNo());
		List<OrderItem> preItemList = this.orderItemDao.searchOrderItemByOrderNoAndCatalogNo(usOrderNo,"SC1088");
		List<OrderItem> peptideChildItemList = null;
		String peptideName = "";
		String sequence = "";
		if(item!=null) {
			peptideChildItemList = this.orderItemDao.getPeptideByParent(item.getOrderItemId());
			if(peptideChildItemList!=null&&peptideChildItemList.size()>0) {
				OrderPeptide orderPeptide = this.orderPeptideDao.getById(peptideChildItemList.get(0).getOrderItemId());
				peptideName = orderPeptide!=null?orderPeptide.getName():"";
				sequence = orderPeptide!=null?orderPeptide.getSequence():"";
			}
		}
		List<String> hostNoList =  new ArrayList<String>();
		List<WorkOrderOperation> list = this.workOrderOperationDao.getAllList(workOrderId);
		if(list!=null&&list.size()>0) {
			List<WoOperationResource> woOperationResourceList = this.woOperationResourceDao.getAllList(list.get(0).getId());
			if(woOperationResourceList!=null) {
				for(WoOperationResource woOperationResource:woOperationResourceList) {
					Resource resource = this.resourceDao.getById(woOperationResource.getResource().getResourceId());
					hostNoList.add(resource!=null?resource.getResourceNo():null);
					if(hostNoList.size()==2) {
						break;
					}
				}
			}
		}
		if(hostNoList.isEmpty()) {
			hostNoList.add("");
		}
		String catalogNo = item!=null?item.getCatalogNo():"";
		int size = 0;
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		String lotNoPre = String.valueOf(year%100)+(month < 10 ? ("0" + month) : (String.valueOf(month)));
		//##### Liquid S3 ######
		StringBuffer l_s3 = new StringBuffer();
		String volume = "1";
		boolean l_s3_flg = false;
		if(("SC1015".equals(catalogNo)&&(peptideChildItemList==null||peptideChildItemList.size()<=1))||
		    "SC1030".equals(catalogNo)||"SC1179".equals(catalogNo)||
			"SC1178".equals(catalogNo)||"SC1180".equals(catalogNo)||
			("SC1031".equals(catalogNo)&&(peptideChildItemList==null||peptideChildItemList.size()<=1))||
			"SC1045".equals(catalogNo)||"SC1247".equals(catalogNo)||"SC1248".equals(catalogNo)||
			"SC1044".equals(catalogNo)||"SC1137".equals(catalogNo)||"SC1055".equals(catalogNo)||
			"SC1056".equals(catalogNo)||"SC1390".equals(catalogNo)||
			("SC1015".equals(catalogNo)&&peptideChildItemList!=null&&peptideChildItemList.size()>1)||
			("SC1031".equals(catalogNo)&&peptideChildItemList!=null&&peptideChildItemList.size()>1)) {
			l_s3_flg = true;
		} else if("SC1050".equals(catalogNo)||"SC1051".equals(catalogNo)||"SC1052".equals(catalogNo)) {
			l_s3_flg = true;
			volume = "2";
		}
		if(l_s3_flg) {
			for(String hostNo:hostNoList) {
				size++;
				l_s3.append(this.getCheckBox("Liquid_S3_1_"+size))
					.append("Pre-immune serum of#").append(hostNo).append(" ,Volume: ").append(volume)
					.append(" ml,Order ID: ")
					.append(usOrderNo).append("-").append(preItemList!=null&&preItemList.size()>0?preItemList.get(0).getItemNo():"")
					.append("  Lot No.A3").append(lotNoPre)
					.append(this.getInput("span_Liquid_S3_1_"+size+"_1", true));
			}
		}
		
		//##### Liquid M3 ######
		StringBuffer l_m3 = new StringBuffer();
		size = 0;
		boolean l_m3_flg = false;
		volume = "";
		if("SC1015".equals(catalogNo)||"SC1030".equals(catalogNo)) {
			l_m3_flg = true;
			volume = "40";
		} else if("SC1150".equals(catalogNo)||"SC1151".equals(catalogNo)||"SC1152".equals(catalogNo)) {
			l_m3_flg = true;
			volume = "250";
		} else if("SC1055".equals(catalogNo)||"SC1056".equals(catalogNo)) {
			l_m3_flg = true;
			volume = "2";
		} 
		if(l_m3_flg) {
			for(String hostNo:hostNoList) {
				size++;
				l_m3.append(this.getCheckBox("Liquid_M3_1_"+size))
					.append("Antiserum of #").append(hostNo).append("   Volume: ").append(volume)
					.append(" ml,Antigen name: ")
					.append(peptideName)
					.append(",Order ID:").append(workOrder.getSrcSoNo()).append("-").append(workOrder.getSoItemNo()).append("  Lot No.A3")
					.append(lotNoPre)
					.append(this.getInput("span_Liquid_M3_1_"+size+"_1", true));
			}
		}
			
	   if("SC1060".equals(catalogNo)) {
			l_m3.append(this.getCheckBox("Liquid_M3_1_1"))
					.append("F(ab')2 fragment of Antibody,Quantity: ").append(this.getInput("span_Liquid_M3_1_1_1", false)).append(" mg/ml @ ").append(this.getInput("span_Liquid_M3_1_1_2", false))
					.append(" ml,Order ID: ")
					.append(workOrder.getSrcSoNo()).append("-").append(workOrder.getSoItemNo()).append("  Lot No.A3")
					.append(this.getInput("span_Liquid_M3_1_1_3", true));
		} else if("SC1061".equals(catalogNo)) {
			l_m3.append(this.getCheckBox("Liquid_M3_1_1"))
				.append("Biotin Conjugated Antibody,Quantity: ").append(this.getInput("span_Liquid_M3_1_1_1", false)).append(" mg/ml @ ").append(this.getInput("span_Liquid_M3_1_1_2", false))
				.append(" ml,Order ID: ")
				.append(workOrder.getSrcSoNo()).append("-").append(workOrder.getSoItemNo()).append("  Lot No.A3")
				.append(this.getInput("span_Liquid_M3_1_1_3", true));
		} else if("SC1062".equals(catalogNo)) {
			l_m3.append(this.getCheckBox("Liquid_M3_1_1"))
				.append("HRP Conjugated Antibody,Quantity: ").append(this.getInput("span_Liquid_M3_1_1_1", false)).append(" mg/ml @ ").append(this.getInput("span_Liquid_M3_1_1_2", false))
				.append(" ml,Order ID: ")
				.append(workOrder.getSrcSoNo()).append("-").append(workOrder.getSoItemNo()).append("  Lot No.A3")
				.append(this.getInput("span_Liquid_M3_1_1_3", true));
		} else if("SC1063".equals(catalogNo)) {
			l_m3.append(this.getCheckBox("Liquid_M3_1_1"))
				.append("FITC Conjugated Antibody,Quantity: ").append(this.getInput("span_Liquid_M3_1_1_1", false)).append(" mg/ml @ ").append(this.getInput("span_Liquid_M3_1_1_2", false))
				.append(" ml,Order ID: ")
				.append(workOrder.getSrcSoNo()).append("-").append(workOrder.getSoItemNo()).append("  Lot No.A3")
				.append(this.getInput("span_Liquid_M3_1_1_3", true));
		} else if("SC1315".equals(catalogNo)) {
			l_m3.append(this.getCheckBox("Liquid_M3_1_1"))
				.append("Fab fragment of Antibody,Quantity: ").append(this.getInput("span_Liquid_M3_1_1_1", false)).append(" mg/ml @ ").append(this.getInput("span_Liquid_M3_1_1_2", false))
				.append(" ml,Order ID: ")
				.append(workOrder.getSrcSoNo()).append("-").append(workOrder.getSoItemNo()).append("  Lot No.A3")
				.append(this.getInput("span_Liquid_M3_1_1_3", true));
		} else if("SC1348".equals(catalogNo)) {
			l_m3.append(this.getCheckBox("Liquid_M3_1_1"))
				.append("Texas Red Conjugated Antibody,Quantity: ").append(this.getInput("span_Liquid_M3_1_1_1", false)).append(" mg/ml @ ").append(this.getInput("span_Liquid_M3_1_1_2", false))
				.append(" ml,Order ID: ")
				.append(workOrder.getSrcSoNo()).append("-").append(workOrder.getSoItemNo()).append("  Lot No.A3")
				.append(this.getInput("span_Liquid_M3_1_1_3", true));
		} else if("SC1035".equals(catalogNo)) {
			l_m3.append(this.getCheckBox("Liquid_M3_1_1")).append(peptideName).append("-KLH,10mg,").append(workOrder.getSrcSoNo())
				.append("-").append(workOrder.getSoItemNo()).append("-KLH<br/>").append(peptideName).append("-BSA,10mg,")
				.append(workOrder.getSrcSoNo()).append("-").append(workOrder.getSoItemNo()).append("-BSA<br/>");
		} else if("SC1015".equals(catalogNo)&&peptideChildItemList!=null&&peptideChildItemList.size()>1) {
			StringBuffer antigen = new StringBuffer("");
			if(peptideChildItemList!=null&&peptideChildItemList.size()>1) {
				antigen = new StringBuffer("");
				for(int j = 0; j < peptideChildItemList.size(); j++) {
					if (j!=0) {
						antigen.append(",");
					}
					OrderPeptide orderPeptide2 = this.orderPeptideDao.getById(peptideChildItemList.get(j).getOrderItemId());
					antigen.append("Antigen ").append(j+1).append(" name:").append(orderPeptide2!=null?orderPeptide2.getName():"");
				}
			}
			for(String hostNo:hostNoList) {
				size++;
				l_m3.append(this.getCheckBox("Liquid_M3_1_"+size))
					.append("Antiserum of #").append(hostNo).append("   Volume: ").append(this.getInput("span_Liquid_M3_1_"+size+"_1", false))
					.append(" ml,Antigen ").append(antigen)
					.append(",After the 3rd immunization from rabbit ").append(hostNo).append(",Post 3rd immunization,,Order ID:")
					.append(workOrder.getSrcSoNo()).append("-").append(workOrder.getSoItemNo()).append("  Lot No.A3")
					.append(this.getInput("span_Liquid_M3_1_"+size+"_2", true));
			}
		}
		//##### Liquid M4 ######
		StringBuffer l_m4 = new StringBuffer();
		size = 0;
		boolean l_m4_flg = false;
		volume = "";
		boolean isHasPost = false;
		if("SC1015".equals(catalogNo)||"SC1030".equals(catalogNo)) {
			l_m4_flg = true;
			isHasPost = true;
			volume = "14";
		} else if("SC1179".equals(catalogNo)||"SC1178".equals(catalogNo)) {
			l_m4_flg = true;
			volume = "40";
		} else if("SC1050".equals(catalogNo)||"SC1052".equals(catalogNo)) {
			l_m4_flg = true;
			isHasPost = true;
			volume = "130";
		}
		if(l_m4_flg) {
			for(String hostNo:hostNoList) {
				size++;
				l_m4.append(this.getCheckBox("Liquid_M4_1_"+size))
					.append("Antiserum of #").append(hostNo).append("   Volume: ").append(volume)
					.append(" ml,Antigen name: ")
					.append(peptideName)
					.append(",");
				if(isHasPost) {
					l_m4.append("Post 3rd immunization,");
				}
				l_m4.append("Order ID:").append(workOrder.getSrcSoNo()).append("-").append(workOrder.getSoItemNo()).append("  Lot No.A3")
					.append(lotNoPre)
					.append(this.getInput("span_Liquid_M4_1_"+size+"_1", true));
			}
		}
		if("SC1051".equals(catalogNo)) {
			StringBuffer l_m4_1 = new StringBuffer();
			StringBuffer l_m4_2 = new StringBuffer();
			StringBuffer antigen = new StringBuffer(peptideName);
			if(peptideChildItemList!=null&&peptideChildItemList.size()>1) {
				antigen = new StringBuffer("");
				for(int j = 0; j < peptideChildItemList.size(); j++) {
					if (j!=0) {
						antigen.append(",");
					}
					OrderPeptide orderPeptide2 = this.orderPeptideDao.getById(peptideChildItemList.get(j).getOrderItemId());
					antigen.append("Antigen ").append(j+1).append(" name:").append(orderPeptide2!=null?orderPeptide2.getName():"");
				}
			}
			for(String hostNo:hostNoList) {
				size++;
				l_m4_1.append(this.getCheckBox("Liquid_M4_1_"+size))
					.append("Protein G Purified Antibody,")
					.append(" ml,Antigen name: ").append(antigen)
					.append(",Quantity: ").append(this.getInput("span_Liquid_M4_1_"+size+"_1", false)).append(" mg/ml @ ")
					.append(this.getInput("span_Liquid_M4_1_"+size+"_2", false))
					.append(" ml,Order ID:").append(workOrder.getSrcSoNo()).append("-").append(workOrder.getSoItemNo())
					.append("(#").append(hostNo).append(")")
					.append("  Lot No.A3").append(lotNoPre)
					.append(this.getInput("span_Liquid_M4_1_"+size+"_3", true));
				l_m4_2.append(this.getCheckBox("Liquid_M4_2_"+size))
					.append("Antiserum of #").append(hostNo).append("   Volume: ").append(this.getInput("span_Liquid_M4_2_"+size+"_1", false))
					.append(" ml,Antigen name: ")
					.append(peptideName)
					.append(",Post 3rd immunization,Order ID:").append(workOrder.getSrcSoNo()).append("-").append(workOrder.getSoItemNo())
					.append("  Lot No.A3").append(lotNoPre)
					.append(this.getInput("span_Liquid_M4_2_"+size+"_2", true));
			}
			l_m4.append(l_m4_1).append(l_m4_2);
		} else if("SC1137".equals(catalogNo)) {
			for(String hostNo:hostNoList) {
				size++;
				l_m4.append(this.getCheckBox("Liquid_M4_1_"+size))
					.append("Test Bleed of #").append(hostNo)
					.append(",Antigen name: ")
					.append(peptideName)
					.append(",Post 2nd immunization  Volume: 1 ml")
					.append(",Order ID:").append(workOrder.getSrcSoNo()).append("-").append(workOrder.getSoItemNo()).append("  Lot No.A3")
					.append(lotNoPre)
					.append(this.getInput("span_Liquid_M4_1_"+size+"_1", true));
			}
		}
		
		//##### Liquid M7 ######
		StringBuffer l_m7 = new StringBuffer();
		size = 0;
		if("SC1015".equals(catalogNo)&&peptideChildItemList!=null&&peptideChildItemList.size()>1) {
			StringBuffer antigen = new StringBuffer("");
			if(peptideChildItemList!=null&&peptideChildItemList.size()>1) {
				antigen = new StringBuffer("");
				for(int j = 0; j < peptideChildItemList.size(); j++) {
					if (j!=0) {
						antigen.append(",");
					}
					OrderPeptide orderPeptide2 = this.orderPeptideDao.getById(peptideChildItemList.get(j).getOrderItemId());
					antigen.append("Antigen ").append(j+1).append(" name:").append(orderPeptide2!=null?orderPeptide2.getName():"");
				}
			}
			for(String hostNo:hostNoList) {
				size++;
				l_m7.append(this.getCheckBox("Liquid_M7_1_"+size))
					.append("Antiserum of #").append(hostNo).append("   Volume: ").append(this.getInput("span_Liquid_M7_1_"+size+"_1", false))
					.append(" ml,Antigen ").append(antigen)
					.append(",After the 3rd immunization from rabbit ").append(hostNo).append(",Post 3rd immunization,,Order ID:")
					.append(workOrder.getSrcSoNo()).append("-").append(workOrder.getSoItemNo())
					.append("  Lot No.A3").append(lotNoPre)
					.append(this.getInput("span_Liquid_M7_1_"+size+"_2", true));
			}
		}
		//##### Liquid S4 ######
		StringBuffer l_s4 = new StringBuffer();
		String header = "";
		boolean l_s4_flg = false;
		boolean hostNoFlg = false;
		size = 0;
		if(("SC1031".equals(catalogNo)&&(peptideChildItemList==null||peptideChildItemList.size()<=1))||"SC1247".equals(catalogNo)||
				"SC1390".equals(catalogNo)||"SC1039".equals(catalogNo)||("SC1031".equals(catalogNo)&&peptideChildItemList!=null&&peptideChildItemList.size()>1)||
				"SC1180".equals(catalogNo)||"SC1056".equals(catalogNo)) {
			l_s4_flg = true;
			header = "Affinity Purified Antibody ,";
		} else if("SC1045".equals(catalogNo)||"SC1248".equals(catalogNo)||"SC1038".equals(catalogNo)) {
			l_s4_flg = true;
			header = "Protein A Purified Antibody,";
		} else if("SC1055".equals(catalogNo)) {
			l_s4_flg = true;
			hostNoFlg = true;
			header = "IgY-Purified Antibody ,";
		} 
		if("SC1180".equals(catalogNo)||"SC1056".equals(catalogNo)) {
			hostNoFlg = true;
		}
		if(l_s4_flg) {
			boolean breakFlg = false;
			for(String hostNo:hostNoList) {
				if(breakFlg) {
					break;
				}
				size++;
				l_s4.append(this.getCheckBox("Liquid_S4_1_"+size))
					.append(header).append("Antigen name:").append(peptideName).append(",Quantity:")
					.append(this.getInput("span_Liquid_S4_1_"+size+"_1", false))
					.append("mg/ml @")
					.append(this.getInput("span_Liquid_S4_1_"+size+"_2", false))
					.append("ml,Order ID:").append(workOrder.getSrcSoNo()).append("-").append(workOrder.getSoItemNo());
				if(hostNoFlg) {
					l_s4.append("(#").append(hostNo).append(")");
				} else {
					breakFlg = true;
				}
				l_s4.append("  Lot No.A3")
					.append(lotNoPre)
					.append(this.getInput("span_Liquid_S4_1_"+size+"_3", true));
			}
		}
		
		//##### Liquid S5 ######
		StringBuffer l_s5 = new StringBuffer();
		size=0;
		if("SC1044".equals(catalogNo)) {
			StringBuffer antigen = new StringBuffer("");
			if(peptideChildItemList!=null&&peptideChildItemList.size()>1) {
				antigen = new StringBuffer("");
				for(int j = 0; j < peptideChildItemList.size(); j++) {
					if (j!=0) {
						antigen.append(",");
					}
					OrderPeptide orderPeptide2 = this.orderPeptideDao.getById(peptideChildItemList.get(j).getOrderItemId());
					antigen.append("Antigen ").append(j+1).append(" name:").append(orderPeptide2!=null?orderPeptide2.getName():"");
				}
			}

			l_s5.append(this.getCheckBox("Liquid_S5_1_"+size))
				.append("Phospho-specific Antibody,").append(antigen).append(",Quantity:")
				.append(this.getInput("span_Liquid_S5_1_"+size+"_1", false))
				.append("mg/ml @")
				.append(this.getInput("span_Liquid_S5_1_"+size+"_2", false))
				.append("ml,Order ID:").append(workOrder.getSrcSoNo()).append("-").append(workOrder.getSoItemNo())
				.append("  Lot No.A3").append(lotNoPre)
				.append(this.getInput("span_Liquid_S5_1_"+size+"_3", true));
		}
		
		//##### Solid S3 ######
		StringBuffer s_s3 = new StringBuffer();
		size = 0;
		boolean s_s3_flg = false;
		boolean lyophilizedFlg = true;
		volume = "";
		if("SC1050".equals(catalogNo)||"SC1051".equals(catalogNo)||"SC1052".equals(catalogNo)) {
			s_s3_flg = true;
			volume = "2";
		} else if("SC1055".equals(catalogNo)||"SC1056".equals(catalogNo)||
				("SC1015".equals(catalogNo)&&(peptideChildItemList==null||peptideChildItemList.size()<=1))||
				("SC1030".equals(catalogNo)&&(peptideChildItemList==null||peptideChildItemList.size()<=1))||
				"SC1179".equals(catalogNo)||"SC1178".equals(catalogNo)||
				"(SC1031".equals(catalogNo)||"SC1045".equals(catalogNo)||"SC1247".equals(catalogNo)||
				"SC1248".equals(catalogNo)||"SC1044".equals(catalogNo)||"SC1180".equals(catalogNo)||
				"SC1390".equals(catalogNo)||("SC1015".equals(catalogNo)&&peptideChildItemList!=null&&peptideChildItemList.size()>1)
				||("SC1031".equals(catalogNo)&&peptideChildItemList!=null&&peptideChildItemList.size()>1)) {
			s_s3_flg = true;
			volume = "1";
		} else if("SC1137".equals(catalogNo)) {
			lyophilizedFlg = false;
			s_s3_flg = true;
			volume = "1";
		}
		if(s_s3_flg) {
			for(String hostNo:hostNoList) {
				size++;
				s_s3.append(this.getCheckBox("Solid_S3_1_"+size));
				if(lyophilizedFlg) {
					s_s3.append("Lyophilized ");
				}
				s_s3.append("Pre-immune serum of#").append(hostNo).append(" ,Volume: ").append(volume)
					.append(" ml,Order ID: ")
					.append(usOrderNo).append("-").append(preItemList!=null&&preItemList.size()>0?preItemList.get(0).getItemNo():"")
					.append("  Lot No.A3").append(lotNoPre)
					.append(this.getInput("span_Solid_S3_1_"+size+"_1", true));
			}
		}
		//##### Solid M3 ######
		StringBuffer s_m3 = new StringBuffer();
		boolean s_m3_flg = false;
		lyophilizedFlg = true;
		volume = "";
		size=0;
		if("SC1015".equals(catalogNo)||"SC1030".equals(catalogNo)||"SC1179".equals(catalogNo)||
				"SC1178".equals(catalogNo)) {
			s_m3_flg = true;
			volume = "20";
		} else if("SC1050".equals(catalogNo)||"SC1051".equals(catalogNo)||"SC1052".equals(catalogNo)) {
			s_m3_flg = true;
			volume = "250";
		} else if("SC1055".equals(catalogNo)||"SC1056".equals(catalogNo)) {
			s_m3_flg = true;
			lyophilizedFlg = false;
			volume = "2";
		}
		if(s_m3_flg) {
			for(String hostNo:hostNoList) {
				size++;
				s_m3.append(this.getCheckBox("Solid_M3_1_"+size));
				if(lyophilizedFlg) {
					s_m3.append("Lyophilized ");
				}
				s_m3.append("Antiserum of #").append(hostNo).append(" ,Volume: ").append(volume)
					.append(" ml,Antigen name:").append(peptideName).append(",Order ID: ")
					.append(usOrderNo).append("-").append(workOrder.getSoItemNo())
					.append("  Lot No.A3")
					.append(lotNoPre)
					.append(this.getInput("span_Solid_M3_1_"+size+"_1", true));
			}
		}
		if("SC1060".equals(catalogNo)) {
			s_m3.append(this.getCheckBox("Solid_M3_1_1"))
				.append("Lyophilized F(ab')2 fragment of Antibody,Quantity: ").append(this.getInput("span_Solid_M3_1_1_1", false)).append(" mg  Volume: ").append(this.getInput("span_Solid_M3_1_1_2", false))
				.append(" ml,Order ID: ")
				.append(usOrderNo).append("-").append(workOrder.getSoItemNo()).append("  Lot No.A3")
				.append(this.getInput("span_Solid_M3_1_1_3", true));
		} else if("SC1061".equals(catalogNo)) {
			s_m3.append(this.getCheckBox("Solid_M3_1_1"))
				.append("Lyophilized Biotin Conjugated Antibody,Quantity: ").append(this.getInput("span_Solid_M3_1_1_1", false)).append(" mg  Volume: ").append(this.getInput("span_Solid_M3_1_1_2", false))
				.append(" ml,Order ID: ")
				.append(usOrderNo).append("-").append(workOrder.getSoItemNo()).append("  Lot No.A3")
				.append(this.getInput("span_Solid_M3_1_1_3", true));
		} else if("SC1062".equals(catalogNo)) {
			s_m3.append(this.getCheckBox("Solid_M3_1_1"))
				.append("Lyophilized HRP Conjugated Antibody,Quantity: ").append(this.getInput("span_Solid_M3_1_1_1", false)).append(" mg  Volume: ").append(this.getInput("span_Solid_M3_1_1_2", false))
				.append(" ml,Order ID: ")
				.append(usOrderNo).append("-").append(workOrder.getSoItemNo()).append("  Lot No.A3")
				.append(this.getInput("span_Solid_M3_1_1_3", true));
		} else if("SC1063".equals(catalogNo)) {
			s_m3.append(this.getCheckBox("Solid_M3_1_1"))
				.append("Lyophilized FITC Conjugated Antibody,Quantity: ").append(this.getInput("span_Solid_M3_1_1_1", false)).append(" mg  Volume: ").append(this.getInput("span_Solid_M3_1_1_2", false))
				.append(" ml,Order ID: ")
				.append(usOrderNo).append("-").append(workOrder.getSoItemNo()).append("  Lot No.A3")
				.append(this.getInput("span_Solid_M3_1_1_3", true));
		} else if("SC1315".equals(catalogNo)) {
			s_m3.append(this.getCheckBox("Solid_M3_1_1"))
				.append("Lyophilized Fab fragment of Antibody,Quantity: ").append(this.getInput("span_Solid_M3_1_1_1", false)).append(" mg/ml @ ").append(this.getInput("span_Solid_M3_1_1_2", false))
				.append(" ml,Order ID: ")
				.append(usOrderNo).append("-").append(workOrder.getSoItemNo()).append("  Lot No.A3")
				.append(this.getInput("span_Solid_M3_1_1_3", true));
		} else if("SC1348".equals(catalogNo)) {
			s_m3.append(this.getCheckBox("Solid_M3_1_1"))
				.append("Lyophilized Texas Red Conjugated Antibody,Quantity: ").append(this.getInput("span_Solid_M3_1_1_1", false)).append(" mg/ml @ ").append(this.getInput("span_Solid_M3_1_1_2", false))
				.append(" ml,Order ID: ")
				.append(usOrderNo).append("-").append(workOrder.getSoItemNo()).append("  Lot No.A3")
				.append(this.getInput("span_Solid_M3_1_1_3", true));
		} else if("SC1035".equals(catalogNo)) {
			s_m3.append(this.getCheckBox("Solid_M3_1_1")).append(peptideName).append("-KLH,10mg,")
				.append(usOrderNo).append("-").append(workOrder.getSoItemNo()).append("-KLH<br/>")
				.append(peptideName).append("-BSA,10mg,").append(workOrder.getSrcSoNo()).append("-").append(workOrder.getSoItemNo()).append("-BSA<br/>");
		}
		//##### Solid M4 ######
		StringBuffer s_m4 = new StringBuffer();
		size=0;
		header = "";
		boolean s_m4_flg = false;
		hostNoFlg = false;
		size = 0;
		if(("SC1031".equals(catalogNo)&&(peptideChildItemList==null||peptideChildItemList.size()<=1))||"SC1247".equals(catalogNo)||
				"SC1390".equals(catalogNo)||"SC1039".equals(catalogNo)||
				"SC1180".equals(catalogNo)) {
			s_m4_flg = true;
			header = "Lyophilized  Affinity Purified Antibody ,";
		} else if("SC1045".equals(catalogNo)||"SC1248".equals(catalogNo)||"SC1038".equals(catalogNo)) {
			s_m4_flg = true;
			header = "Lyophilized  Protein A Purified Antibody,";
		} 
		if("SC1180".equals(catalogNo)) {
			hostNoFlg = true;
		}
		if(s_m4_flg) {
			boolean breakFlg = false;
			for(String hostNo:hostNoList) {
				if(breakFlg) {
					break;
				}
				size++;
				s_m4.append(this.getCheckBox("Solid_M4_1_"+size))
					.append(header).append("Antigen name:").append(peptideName).append(",Quantity:")
					.append(this.getInput("span_Solid_M4_1_"+size+"_1", false))
					.append("mg  Volume: ")
					.append(this.getInput("span_Solid_M4_1_"+size+"_2", false))
					.append("ml,Order ID:").append(workOrder.getSrcSoNo()).append("-").append(workOrder.getSoItemNo());
				if(hostNoFlg) {
					s_m4.append("(#").append(hostNo).append(")");
				} else {
					breakFlg = true;
				}
				s_m4.append("  Lot No.A3")
					.append(lotNoPre)
					.append(this.getInput("span_Solid_M4_1_"+size+"_3", true));
			}
		}
		if("SC1015".equals(catalogNo)&&peptideChildItemList!=null&&peptideChildItemList.size()>1) {
			StringBuffer antigen = new StringBuffer("");
			if(peptideChildItemList!=null&&peptideChildItemList.size()>1) {
				for(int j = 0; j < peptideChildItemList.size(); j++) {
					if (j!=0) {
						antigen.append(",");
					}
					OrderPeptide orderPeptide2 = this.orderPeptideDao.getById(peptideChildItemList.get(j).getOrderItemId());
					antigen.append("Antigen ").append(j+1).append(" name:").append(orderPeptide2!=null?orderPeptide2.getName():"")
							.append(")");
				}
			}
			for(String hostNo:hostNoList) {
				size++;
				s_m4.append(this.getCheckBox("Solid_M4_1_"+size))
					.append("Lyophilized antiserum of#").append(hostNo)
					.append("  Volume: 20 ml,").append(antigen).append(",Order ID: ")
					.append(workOrder.getSrcSoNo()).append("-").append(workOrder.getSoItemNo())
					.append("  Lot No.A3").append(lotNoPre)
					.append(this.getInput("span_Solid_M4_1_"+size+"_1", true));
			}
		} else if("SC1031".equals(catalogNo)&&peptideChildItemList!=null&&peptideChildItemList.size()>1) {
			StringBuffer antigen = new StringBuffer("");
			if(peptideChildItemList!=null&&peptideChildItemList.size()>1) {
				for(int j = 0; j < peptideChildItemList.size(); j++) {
					if (j!=0) {
						antigen.append(",");
					}
					OrderPeptide orderPeptide2 = this.orderPeptideDao.getById(peptideChildItemList.get(j).getOrderItemId());
					antigen.append("Antigen ").append(j+1).append(" name:").append(orderPeptide2!=null?orderPeptide2.getName():"")
							.append(")");
				}
			}

			s_m4.append(this.getCheckBox("Solid_M4_1_"+size))
				.append("Lyophilized Affinity Purified Antibody,").append(antigen)
				.append(",Quantity: ").append(this.getInput("span_Solid_M4_1_"+size+"_1", false)).append("mg    Volume: ")
				.append(this.getInput("span_Solid_M4_1_"+size+"_2", false))
				.append(",Order ID: ")
				.append(workOrder.getSrcSoNo()).append("-").append(workOrder.getSoItemNo())
				.append("  Lot No.A3").append(lotNoPre)
				.append(this.getInput("span_Solid_M4_1_"+size+"_3", true));
		} else if("SC1015".equals(catalogNo)||"SC1030".equals(catalogNo)) {
			for(String hostNo:hostNoList) {
				s_m4.append(this.getCheckBox("Solid_M4_1_"+size))
					.append("Lyophilized Antiserum of #").append(hostNo).append(" ,Volume: ").append("14")
					.append(" ml,Antigen name:").append(peptideName).append(",Post 3rd immunization,Order ID: ")
					.append(usOrderNo).append("-").append(workOrder.getSoItemNo())
					.append("  Lot No.A3")
					.append(lotNoPre)
					.append(this.getInput("span_Solid_M4_1_"+size+"_1", true));
			}
		} else if("SC1050".equals(catalogNo)||"SC1052".equals(catalogNo)) {
			for(String hostNo:hostNoList) {
				s_m4.append(this.getCheckBox("Solid_M4_1_"+size))
					.append("Lyophilized Antiserum of #").append(hostNo).append(" ,Volume: ").append("130")
					.append(" ml,Antigen name:").append(peptideName).append(",Post 3rd immunization,Order ID: ")
					.append(usOrderNo).append("-").append(workOrder.getSoItemNo())
					.append("  Lot No.A3")
					.append(lotNoPre)
					.append(this.getInput("span_Solid_M4_1_"+size+"_1", true));
			}
		} else if("SC1051".equals(catalogNo)) {
			StringBuffer s_m4_1 = new StringBuffer();
			StringBuffer s_m4_2 = new StringBuffer();
			StringBuffer antigen = new StringBuffer(peptideName);
			if(peptideChildItemList!=null&&peptideChildItemList.size()>1) {
				antigen = new StringBuffer("");
				for(int j = 0; j < peptideChildItemList.size(); j++) {
					if (j!=0) {
						antigen.append(",");
					}
					OrderPeptide orderPeptide2 = this.orderPeptideDao.getById(peptideChildItemList.get(j).getOrderItemId());
					antigen.append("Antigen ").append(j+1).append(" name:").append(orderPeptide2!=null?orderPeptide2.getName():"");
				}
			}
			for(String hostNo:hostNoList) {
				size++;
				s_m4_1.append(this.getCheckBox("Solid_M4_1_"+size))
					  .append("Lyophilized Protein G Purified Antibody,")
					  .append(" ml,Antigen name: ").append(antigen)
					  .append(",Quantity: ").append(this.getInput("span_Solid_M4_1_"+size+"_1", false)).append(" mg/ml @ ")
					  .append(this.getInput("span_Solid_M4_1_"+size+"_2", false))
					  .append(" ml,Order ID:").append(workOrder.getSrcSoNo()).append("-").append(workOrder.getSoItemNo())
					  .append("(#").append(hostNo).append(")")
					  .append("  Lot No.A3")
					  .append(this.getInput("span_Solid_M4_1_"+size+"_3", true));
				s_m4_2.append(this.getCheckBox("Solid_M4_2_"+size))
					.append("Lyophilized Antiserum of #").append(hostNo).append("   Volume: ").append(this.getInput("span_Solid_M4_2_"+size+"_1", false))
					.append(" ml,Antigen name: ")
					.append(peptideName)
					.append(",Post 3rd immunization,Order ID:").append(workOrder.getSrcSoNo()).append("-").append(workOrder.getSoItemNo()).append("  Lot No.A3")
					.append(this.getInput("span_Solid_M4_2_"+size+"_2", true));
			}
			s_m4.append(s_m4_1).append(s_m4_2);
		} else if("SC1137".equals(catalogNo)) {
			for(String hostNo:hostNoList) {
				size++;
				s_m4.append(this.getCheckBox("Solid_M4_1_"+size))
					.append("Test Bleed of #").append(hostNo)
					.append(",Antigen name: ")
					.append(peptideName)
					.append(",Post 2nd immunization  Volume: 1 ml")
					.append(",Order ID:").append(workOrder.getSrcSoNo()).append("-").append(workOrder.getSoItemNo()).append("  Lot No.A3")
					.append(lotNoPre)
					.append(this.getInput("span_Solid_M4_1_"+size+"_1", true));
			}
		} 
		
		//##### Solid M5 ######
		StringBuffer s_m5 = new StringBuffer();
		size=0;
		if("SC1044".equals(catalogNo)) {
			StringBuffer antigen = new StringBuffer("");
			if(peptideChildItemList!=null&&peptideChildItemList.size()>1) {
				for(int j = 0; j < peptideChildItemList.size(); j++) {
					if (j!=0) {
						antigen.append(",");
					}
					OrderPeptide orderPeptide2 = this.orderPeptideDao.getById(peptideChildItemList.get(j).getOrderItemId());
					antigen.append("Antigen ").append(j+1).append(" name:").append(orderPeptide2!=null?orderPeptide2.getName():"")
							.append(")");
				}
			}

			s_m5.append(this.getCheckBox("Solid_M5_1_"+size))
				.append("Lyophilized Phospho-specific Antibody,").append(antigen)
				.append(",Quantity:")
				.append(this.getInput("span_Solid_M5_1_"+size+"_1", false))
				.append(" mg   Volume: ")
				.append(this.getInput("span_Solid_M5_1_"+size+"_2", false))
				.append(" ml,Order ID:").append(antigen).append(",Order ID: ")
				.append(workOrder.getSrcSoNo()).append("-").append(workOrder.getSoItemNo())
				.append(")  Lot No.A3").append(lotNoPre)
				.append(this.getInput("span_Solid_M5_1_"+size+"_3", true));
		} else if("SC1015".equals(catalogNo)&&peptideChildItemList!=null&&peptideChildItemList.size()>1) {
			StringBuffer antigen = new StringBuffer("");
			if(peptideChildItemList!=null&&peptideChildItemList.size()>1) {
				for(int j = 0; j < peptideChildItemList.size(); j++) {
					if (j!=0) {
						antigen.append(",");
					}
					OrderPeptide orderPeptide2 = this.orderPeptideDao.getById(peptideChildItemList.get(j).getOrderItemId());
					antigen.append("Antigen ").append(j+1).append(" name:").append(orderPeptide2!=null?orderPeptide2.getName():"")
							.append(")");
				}
			}
			for(String hostNo:hostNoList) {
				size++;
				s_m4.append(this.getCheckBox("Solid_M5_1_"+size))
					.append("Lyophilized antiserum of#").append(hostNo)
					.append("  Volume: 14 ml,").append(antigen).append(",Post 3rd immunization,Order ID: ")
					.append(workOrder.getSrcSoNo()).append("-").append(workOrder.getSoItemNo())
					.append("  Lot No.A3")
					.append(this.getInput("span_Solid_M5_1_"+size+"_1", true));
			}
		}
		//##### Solid S4 ######
		StringBuffer s_s4 = new StringBuffer();
		boolean s_s4_flg = false;
		size = 0;
		header = "";
		if("SC1055".equals(catalogNo)) {
			s_s4_flg = true;
			header = "gY-Purified Antibody ,";
		} else if("SC1056".equals(catalogNo)) {
			s_s4_flg = true;
			header = "Affinity Purified Antibody ,";
		}
		if(s_s4_flg) {
			for(String hostNo:hostNoList) {
				size++;
				s_s4.append(this.getCheckBox("Solid_S4_1_"+size))
					.append(header).append("Antigen name:").append(peptideName).append(",Quantity:")
					.append(this.getInput("span_Solid_S4_1_"+size+"_1", false))
					.append("mg/ml @")
					.append(this.getInput("span_Solid_S4_1_"+size+"_2", false))
					.append("ml,Order ID:").append(workOrder.getSrcSoNo()).append("-").append(workOrder.getSoItemNo())
					.append("(#").append(hostNo).append(")")
					.append("  Lot No.A3")
					.append(lotNoPre)
					.append(this.getInput("span_Solid_S4_1_"+size+"_3", true));
			}
		}
		result.append("Liquid<br/>");
		if(StringUtils.isNotEmpty(l_s3.toString())) {
			result.append("<br/>S3<br/>").append(l_s3);
		}
		if(StringUtils.isNotEmpty(l_m3.toString())) {
			result.append("<br/>M3<br/>").append(l_m3);
		}
		if(StringUtils.isNotEmpty(l_m4.toString())) {
			result.append("<br/>M4<br/>").append(l_m4);
		}
		if(StringUtils.isNotEmpty(l_m7.toString())) {
			result.append("<br/>M7<br/>").append(l_m7);
		}
		if(StringUtils.isNotEmpty(l_s4.toString())) {
			result.append("<br/>S4<br/>").append(l_s4);
		}
		if(StringUtils.isNotEmpty(l_s5.toString())) {
			result.append("<br/>S5<br/>").append(l_s5);
		}
		result.append("<br/>Solid<br/>");
		if(StringUtils.isNotEmpty(s_s3.toString())) {
			result.append("<br/>S3<br/>").append(s_s3);
		}
		if(StringUtils.isNotEmpty(s_m3.toString())) {
			result.append("<br/>M3<br/>").append(s_m3);
		}
		if(StringUtils.isNotEmpty(s_m4.toString())) {
			result.append("<br/>M4<br/>").append(s_m4);
		}
		if(StringUtils.isNotEmpty(s_m5.toString())) {
			result.append("<br/>M5<br/>").append(s_m5);
		}
		if(StringUtils.isNotEmpty(s_s4.toString())) {
			result.append("<br/>S4<br/>").append(s_s4);
		}
		return result.toString();
	}
	
	
	private String getCheckBox(String id) {
		return "<input type=checkbox id="+id+" name=antibody checked></input><span id=span_"+id+">";
	}
	
	private String getInput(String id,boolean isBr) {
		return (isBr?"":" ")+"<span id="+id+"><input type=text name=antibody size=14 class=NFText></input></span>"+(isBr?"<br/></span>":" ");
	}
	private String getInput(boolean isBr,String value) {
		if("".equals(value)) {
			return (isBr?"":" ")+"<span><input type=text  name=antibody size=14 class=NFText></input></span>"+(isBr?"<br/>":" ");
		}
		return (isBr?"":" ")+"<span><input type=text value="+value+" name=antibody size=14 class=NFText></input></span>"+(isBr?"<br/>":" ");
	}
	
	/**
	 * 根据wo id和页面传过来的cloneIds生成Monoclonal antibody标签
	 */
	public String getInfoToAnti(Integer workOrderId,String cloneIds) {
		StringBuffer result = new StringBuffer("");
		WorkOrder workOrder = this.workOrderDao.getById(workOrderId);
		if(workOrder.getSoNo()!=null) {
			MfgOrder mfgOrder = this.mfgOrderDao.getById(workOrder.getSoNo());
			workOrder.setSrcSoNo(mfgOrder!=null?mfgOrder.getSrcSoNo():null);
		}
		Integer orderNo = workOrder.getSrcSoNo()!=null?workOrder.getSrcSoNo():workOrder.getSoNo();
		OrderItem item = this.orderItemDao.searchOrderItemByOrderNoAndItemNo(orderNo, workOrder.getSoItemNo());
		
		String catalogNo = item!=null?item.getCatalogNo():"";
		List<OrderItem> peptideChildItemList = this.orderItemDao.getPeptideByParent(item.getOrderItemId());
		OrderPeptide orderPeptide = null;
		String peptideName= "";
		StringBuffer ag = new StringBuffer("");
		String[] cloneIdArray = null;
		if(StringUtils.isNotEmpty(cloneIds)) {
			cloneIdArray = cloneIds.split(",");
		}
		OrderAntibody orderAntibody = null;
		if(item!=null) {
			orderAntibody = this.orderAntibodyDao.getById(item.getOrderItemId());
		}
		if(peptideChildItemList!=null&&peptideChildItemList.size()>0) {
			orderPeptide = this.orderPeptideDao.getById(peptideChildItemList.get(0).getOrderItemId());
			for(OrderItem orderItem:peptideChildItemList) {
				OrderPeptide orderPeptide2 = this.orderPeptideDao.getById(orderItem.getOrderItemId());
				if(StringUtils.isNotEmpty(ag.toString())) {
					ag.append(" ");
				} else {
					ag.append(orderPeptide2!=null?orderPeptide2.getName():"");
				}
			}
		} else {
			peptideName = orderAntibody!=null?orderAntibody.getAntibodyName():"";
			ag.append(peptideName);
		}
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		String lotNoPre = String.valueOf(year%100)+(month < 10 ? ("0" + month) : (String.valueOf(month)));
		StringBuffer strBuff = new StringBuffer("");
		if("SC1040".equals(catalogNo)||"SC1041".equals(catalogNo)||"SC1184".equals(catalogNo)||
				"SC1185".equals(catalogNo)||"SC1220".equals(catalogNo)||"SC1301".equals(catalogNo)||
				"SC1398".equals(catalogNo)||"SC1342".equals(catalogNo)||"SC1343".equals(catalogNo)||
				"SC1344".equals(catalogNo)||"SC1345".equals(catalogNo)||"SC1391".equals(catalogNo)||
				"SC1392".equals(catalogNo)||"SC1057".equals(catalogNo)) {
			strBuff.append(this.getCheckBox("Liquid")).append("Liquid<br/>");
			StringBuffer m1_1 = new StringBuffer("");
			StringBuffer m1_2 = new StringBuffer("");
			StringBuffer m1_3 = new StringBuffer("");
			StringBuffer m1_4 = new StringBuffer("");
			StringBuffer m1_5 = new StringBuffer("");
			if(cloneIdArray!=null) {
				int i =0;
				for(String cloneId:cloneIdArray) {
					i++;
					m1_1.append("Positive Parental Supernatant,Clone ID: ")
						   .append(cloneId).append(" Ag: ").append(ag)
						   .append("   Volume:").append(this.getInput(false,"1")).append("ml")
						   .append(",Order ID: ")
						   .append(orderNo).append("_").append(workOrder.getSoItemNo())
						   .append("  Lot No.A2").append(lotNoPre)
						   .append(this.getInput(true,""));
					StringBuffer _m1_3 = new StringBuffer("");
					_m1_3.append("Positive Supernatant,Clone ID: ")
					   .append(cloneId).append(" Ag: ").append(ag)
					   .append("   Volume:").append(this.getInput(false,"5")).append("ml")
					   .append(",Order ID: ")
					   .append(orderNo).append("_").append(workOrder.getSoItemNo())
					   .append("  Lot No.A2").append(lotNoPre)
					   .append(this.getInput(true,""));
					m1_3.append(_m1_3);
					m1_5.append("Lyophilized ").append(_m1_3);
					m1_2.append("Clone ID: ").append(cloneId).append("  Order ID: ")
				    	.append(orderNo).append("_").append(workOrder.getSoItemNo())
				    	.append(",Ag: ").append(ag)
				    	.append(",Lot No.A2").append(lotNoPre).append(this.getInput(false,""))
				    	.append(" Store at: -196℃<br>");
					m1_4.append("Clone ID: ").append(cloneId).append("  Order ID: ")
				    	.append(orderNo).append("_").append(workOrder.getSoItemNo())
				    	.append(",Ag: ").append(ag)
				    	.append(",Lot No.A2").append(lotNoPre).append(this.getInput(false,""))
				    	.append(" Store at: 37℃<br>");
				}
			}
			strBuff.append(this.getCheckBox("Liquid_1")).append(m1_1).append("</span>")
				   .append(this.getCheckBox("Liquid_2")).append(m1_3).append("</span>")
				   .append(this.getCheckBox("Liquid_3")).append(m1_2).append("</span>")
				   .append(this.getCheckBox("Liquid_4")).append(m1_4).append("</span>");
			strBuff.append("</span>").append("<br/>").append(this.getCheckBox("Solid")).append("Solid<br/>");
			strBuff.append(this.getCheckBox("Solid_1")).append(m1_5).append("</span>")
				   .append(this.getCheckBox("Solid_2")).append(m1_2).append("</span>")
				   .append(this.getCheckBox("Solid_3")).append(m1_4).append("</span></span>");
			
		} else if("SC1043".equals(catalogNo)||"SC1124".equals(catalogNo)||"SC1125".equals(catalogNo)||
				"SC1126".equals(catalogNo)||"SC1127".equals(catalogNo)||"SC1128".equals(catalogNo)||
				"SC1129".equals(catalogNo)||"SC1130".equals(catalogNo)||"SC1131".equals(catalogNo)||
				"SC1132".equals(catalogNo)||"SC1133".equals(catalogNo)||"SC1134".equals(catalogNo)) {
			strBuff.append(this.getCheckBox("Liquid")).append("Liquid<br/>");
			StringBuffer m2_1 = new StringBuffer("");
			StringBuffer m2_2 = new StringBuffer("");
			StringBuffer m2_3 = new StringBuffer("");
			StringBuffer m2_4 = new StringBuffer("");
			StringBuffer m2_5 = new StringBuffer("");
			StringBuffer _m2_1 = new StringBuffer("");
			StringBuffer _m2_2 = new StringBuffer("");
			StringBuffer _m2_3 = new StringBuffer("");
			StringBuffer _m2_4 = new StringBuffer("");
			StringBuffer _m2_5 = new StringBuffer("");
			
			if(cloneIdArray!=null) {
				String m2_1_str = "Protein A Purified Ascites,";
				String _m2_1_str = "Lyophilized Protein A Purified Ascites,";
				String m2_2_str = "Protein G Purified Ascites,";
				String _m2_2_str ="Lyophilized Protein G Purified Ascites,";
				String m2_3_str = "Ion-exchange Purified Ascites,";
				String _m2_3_str = "Lyophilized Ion-exchange Purified Ascites,";
				for(String cloneId:cloneIdArray) {
					StringBuffer same1 = new StringBuffer("");
					StringBuffer same3 = new StringBuffer("");
					same1.append("Clone ID:").append(cloneId).append(",Quantity: ").append(this.getInput(false,""))
						 .append("mg/ml @ ").append(this.getInput(false,"")).append("ml,Order ID: ")
						 .append(orderNo).append("_").append(workOrder.getSoItemNo())
						 .append("  Lot No. A2").append(lotNoPre).append(this.getInput(true,""));
					same3.append("Clone ID:").append(cloneId).append(",Quantity: ").append(this.getInput(false,""))
					 	.append("mg   Volume:").append(this.getInput(false,"")).append("ml,Order ID: ")
					 	.append(orderNo).append("_").append(workOrder.getSoItemNo())
					 	.append("  Lot No. A2").append(lotNoPre).append(this.getInput(true,""));
					m2_1.append(m2_1_str).append(same1);
					_m2_1.append(_m2_1_str).append(same3);
					m2_2.append(m2_2_str).append(same1);
					_m2_2.append(_m2_2_str).append(same3);
					m2_3.append(m2_3_str).append(same1);
					_m2_3.append(_m2_3_str).append(same3);
				}
			}
			String m2_4_str = "<br>Low EU Protein A Purified Ascites,";
			String _m2_4_str = "<br>Lyophilized Low EU Protein A Purified Ascites,";
			String m2_5_str = "Low EU Protein G Purified Ascites,";
			String _m2_5_str = "Lyophilized Low EU Protein G Purified Ascites,";
			StringBuffer same2 = new StringBuffer("");
			StringBuffer same4 = new StringBuffer("");
			same2.append("Cell line name: ").append(this.getInput(false, ""))
					.append(",Quantity: ").append(this.getInput(false, ""))
					.append("mg/ml @ ").append(this.getInput(false, ""))
					.append("ml,Order ID: ").append(orderNo).append("_")
					.append(workOrder.getSoItemNo()).append("  Lot No. A2")
					.append(lotNoPre).append(this.getInput(true, ""));
			same4.append("Cell line name: ").append(this.getInput(false, ""))
					.append(",Quantity: ").append(this.getInput(false, ""))
					.append("mg    Volume:").append(this.getInput(false, ""))
					.append("ml,Order ID: ").append(orderNo).append("_")
					.append(workOrder.getSoItemNo()).append("  Lot No. A2")
					.append(lotNoPre).append(this.getInput(true, ""));
			m2_4.append(m2_4_str).append(same2);
			_m2_4.append(_m2_4_str).append(same4);
			m2_5.append(m2_5_str).append(same2);
			_m2_5.append(_m2_5_str).append(same4);
			strBuff.append(this.getCheckBox("Liquid_1")).append(m2_1).append("</span>")
				   .append(this.getCheckBox("Liquid_2")).append(m2_2).append("</span>")
				   .append(this.getCheckBox("Liquid_3")).append(m2_3).append("</span>")
				   .append(this.getCheckBox("Liquid_4")).append(m2_4).append("</span>")
				   .append(this.getCheckBox("Liquid_5")).append(m2_5).append("</span>");
			strBuff.append("</span>").append("<br/>").append(this.getCheckBox("Solid")).append("Solid<br/>");
			strBuff.append(this.getCheckBox("Solid_1")).append(_m2_1).append("</span>")
			   	   .append(this.getCheckBox("Solid_2")).append(_m2_2).append("</span>")
			   	   .append(this.getCheckBox("Solid_3")).append(_m2_3).append("</span>")
			   	   .append(this.getCheckBox("Solid_4")).append(_m2_4).append("</span>")
			   	   .append(this.getCheckBox("Solid_5")).append(_m2_5).append("</span></span>");
		} else if("SC1046".equals(catalogNo)||"SC1047".equals(catalogNo)||"SC1355".equals(catalogNo)||"SC1356".equals(catalogNo)) {
			strBuff.append(this.getCheckBox("Liquid")).append("Liquid<br/>");
			StringBuffer m3_1 = new StringBuffer("");
			StringBuffer m3_2 = new StringBuffer("");
			StringBuffer m3_3 = new StringBuffer("");
			StringBuffer m3_4 = new StringBuffer("");
			for(int i=1;i<6;i++) {
				StringBuffer _m3_1 = new StringBuffer("");
				_m3_1.append("Pre-immune Serum of #").append(i).append(",Volume: ").append(this.getInput(false,"")).append("ul,Order ID:  ")
					.append(orderNo).append("_").append(workOrder.getSoItemNo())
					.append(" Lot No. A2").append(lotNoPre).append(this.getInput(true,""));
				m3_1.append(_m3_1);
				StringBuffer _m3_2 = new StringBuffer("");
				_m3_2.append("Antiserum of #").append(i).append(",Antigen name: ").append(ag).append(",Volume: ").append(this.getInput(false,""))
				    .append("ul,Order ID:  ")
				   	.append(orderNo).append("_").append(workOrder.getSoItemNo())
				    .append(" Lot No. A2").append(lotNoPre).append(this.getInput(true,""));
				m3_2.append(_m3_2);
				m3_3.append("Lyophilized ").append(_m3_1);
				m3_4.append("Lyophilized ").append(_m3_2);
			}
			
			strBuff.append(this.getCheckBox("Liquid_1")).append(m3_1).append("</span>")
				   .append(this.getCheckBox("Liquid_2")).append(m3_2).append("</span>");
			strBuff.append("</span>").append("<br/>").append(this.getCheckBox("Solid")).append("Solid<br/>");
			strBuff.append(this.getCheckBox("Solid_1")).append(m3_1).append("</span>")
			   	   .append(this.getCheckBox("Solid_2")).append(m3_2).append("</span></span>");
		} else if("SC1049".equals(catalogNo)||"SC1250".equals(catalogNo)||"SC1311".equals(catalogNo)||"SC1313".equals(catalogNo)||
					"SC1312".equals(catalogNo)||"SC1314".equals(catalogNo)) {
			strBuff.append(this.getCheckBox("Liquid")).append("Liquid<br/>");
			StringBuffer m4_1 = new StringBuffer("");
			StringBuffer m4_2 = new StringBuffer("");
			StringBuffer m4_3 = new StringBuffer("");
			StringBuffer m4_4 = new StringBuffer("");
			StringBuffer m4_5 = new StringBuffer("");
			StringBuffer m4_6 = new StringBuffer("");
			for(int i=1;i<6;i++) {
				StringBuffer _m4_1 = new StringBuffer("");
				_m4_1.append("Pre-immune Serum of #").append(i).append(",Volume: ").append(this.getInput(false,"")).append(" ul,Order ID: ")
					.append(orderNo).append("_").append(workOrder.getSoItemNo())
					.append(" Lot No. A2").append(lotNoPre).append(this.getInput(true,""));
				m4_1.append(_m4_1);
				m4_6.append("Lyophilized ").append(_m4_1);
			}
			m4_2.append("Protein A Purified Antiserum,Antigen name: ").append(ag).append(",Quantity: ").append(this.getInput(false,""))
			    .append(" mg/ml @ ").append(this.getInput(false,"")).append(" ml,Order ID: ")
			    .append(orderNo).append("_").append(workOrder.getSoItemNo())
				.append(" Lot No. A2").append(lotNoPre).append(this.getInput(true,""));
			m4_4.append("Protein A Purified Antiserum,Antigen name: ").append(ag).append(",Quantity: ").append(this.getInput(false,""))
		    	.append(" mg   Volume: ").append(this.getInput(false,"")).append(" ml,Order ID: ")
		    	.append(orderNo).append("_").append(workOrder.getSoItemNo())
		    	.append(" Lot No. A2").append(lotNoPre).append(this.getInput(true,""));
			m4_3.append("Protein G Purified Antiserum,Antigen name: ").append(ag).append(",Quantity: ").append(this.getInput(false,""))
		    	.append(" mg/ml @ ").append(this.getInput(false,"")).append(" ml,Order ID: ")
		    	.append(orderNo).append("_").append(workOrder.getSoItemNo())
		    	.append(" Lot No. A2").append(lotNoPre).append(this.getInput(true,""));
			m4_5.append("Protein G Purified Antiserum,Antigen name: ").append(ag).append(",Quantity: ").append(this.getInput(false,""))
	    		.append(" mg   Volume: ").append(this.getInput(false,"")).append(" ml,Order ID: ")
	    		.append(orderNo).append("_").append(workOrder.getSoItemNo())
	    		.append(" Lot No. A2").append(lotNoPre).append(this.getInput(true,""));
			strBuff.append(this.getCheckBox("Liquid_1")).append(m4_1).append("</span>")
				   .append(this.getCheckBox("Liquid_2")).append(m4_2).append("</span>")
				   .append(this.getCheckBox("Liquid_3")).append(m4_3).append("</span>");
			strBuff.append("</span>").append("<br/>").append(this.getCheckBox("Solid")).append("Solid<br/>");
			strBuff.append(this.getCheckBox("Solid_1")).append(m4_6).append("</span>")
			   	   .append(this.getCheckBox("Solid_2")).append("Lyophilized ").append(m4_4).append("</span>")
			       .append(this.getCheckBox("Solid_3")).append("Lyophilized ").append(m4_5).append("</span></span>");
		} else if("SC1110".equals(catalogNo)||"SC1111".equals(catalogNo)||"SC1112".equals(catalogNo)
				||"SC1113".equals(catalogNo)||"SC1114".equals(catalogNo)||"SC1115".equals(catalogNo)
				||"SC1116".equals(catalogNo)||"SC1302".equals(catalogNo)||"SC1303".equals(catalogNo)
				||"SC1304".equals(catalogNo)||"SC1305".equals(catalogNo)||"SC1306".equals(catalogNo)
				||"SC1307".equals(catalogNo)||"SC1308".equals(catalogNo)||"SC1255".equals(catalogNo)) {
			strBuff.append(this.getCheckBox("Liquid")).append("Liquid<br/>");
			StringBuffer m5_1 = new StringBuffer("");
			StringBuffer m5_2 = new StringBuffer("");
			if(cloneIdArray!=null) {
				for(String cloneId:cloneIdArray) {
					m5_1.append("Ascites   Volume: ").append(this.getInput(false,"")).append(" ml,Clone ID: ").append(cloneId)
						.append(",Order ID: ")
						.append(orderNo).append("_").append(workOrder.getSoItemNo())
				    	.append(" Lot No. A2").append(lotNoPre).append(this.getInput(true,""));
					m5_2.append("Lyophilized Ascites   Volume: ").append(this.getInput(false,"")).append(" ml,Clone ID: ").append(cloneId)
						.append(",Order ID: ")
						.append(orderNo).append("_").append(workOrder.getSoItemNo())
						.append(" Lot No. A2").append(lotNoPre).append(this.getInput(true,""));
				}
			}
			strBuff.append(this.getCheckBox("Liquid_1")).append(m5_1).append("</span>");
			strBuff.append("</span>").append("<br/>").append(this.getCheckBox("Solid")).append("Solid<br/>");	
			strBuff.append(this.getCheckBox("Solid_1")).append(m5_2).append("</span></span>");
		} else if("SC1227".equals(catalogNo)||"SC1228".equals(catalogNo)||"SC1381".equals(catalogNo)||"SC1382".equals(catalogNo)) {
			strBuff.append(this.getCheckBox("Liquid")).append("Liquid<br/>");
			StringBuffer m6_1 = new StringBuffer("");
			StringBuffer _m6_1 = new StringBuffer("");
			StringBuffer m6_2 = new StringBuffer("");
			StringBuffer _m6_2 = new StringBuffer("");
			StringBuffer m6_3 = new StringBuffer("");
			StringBuffer _m6_3 = new StringBuffer("");
			StringBuffer m6_4 = new StringBuffer("");
			StringBuffer _m6_4 = new StringBuffer("");
			
			if(cloneIdArray!=null) {
				String m6_1_str = "Protein A Purified Positive Supernatant,";
				String _m6_1_str = "Lyophilized Protein A Purified Positive Supernatant,";
				String m6_2_str = "Protein G Purified Positive Supernatant,";
				String _m6_2_str ="Lyophilized Protein G Purified Positive Supernatant,";
				String m6_3_str = "<br>Low EU Protein A Purified Positive Supernatant,";
				String _m6_3_str ="Lyophilized Low EU Protein A Purified Positive Supernatant,";
				String m6_4_str = "Low EU Protein G Purified Positive Supernatant,";
				String _m6_4_str = "Lyophilized Low EU Protein G Purified Positive Supernatant,";
				for(String cloneId:cloneIdArray) {
					StringBuffer same1 = new StringBuffer("");
					StringBuffer same2 = new StringBuffer("");
					same1.append("Clone ID: ").append(cloneId).append(",Quantity: ").append(this.getInput(false,"")).append(" mg/ml @ ")
						 .append(this.getInput(false,"")).append(" ml,Order ID: ")
						 .append(orderNo).append("_").append(workOrder.getSoItemNo())
						 .append(" Lot No. A2").append(lotNoPre).append(this.getInput(true,""));
					same2.append("Clone ID: ").append(cloneId).append(",Quantity: ").append(this.getInput(false,"")).append(" mg   Volume: ")
					 	 .append(this.getInput(false,"")).append(" ml,Order ID: ")
					 	 .append(orderNo).append("_").append(workOrder.getSoItemNo())
					 	 .append(" Lot No. A2").append(lotNoPre).append(this.getInput(true,""));
					m6_1.append(m6_1_str);
					_m6_1.append(_m6_1_str);
					m6_1.append(same1);
					_m6_1.append(same2);
					m6_2.append(m6_2_str);
					_m6_2.append(_m6_2_str);
					m6_2.append(same1);
					_m6_2.append(same2);
					m6_3.append(m6_3_str);
					_m6_3.append(_m6_3_str);
					m6_3.append(same1);
					_m6_3.append(same2);
					m6_4.append(m6_4_str);
					_m6_4.append(_m6_4_str);
					m6_4.append(same1);
					_m6_4.append(same2);
					
				}
			}
			strBuff.append(this.getCheckBox("Liquid_1")).append(m6_1).append("</span>")
				   .append(this.getCheckBox("Liquid_2")).append(m6_2).append("</span>")
				   .append(this.getCheckBox("Liquid_2")).append(m6_3).append("</span>")
				   .append(this.getCheckBox("Liquid_2")).append(m6_4).append("</span>");
			strBuff.append("</span>").append("<br/>").append(this.getCheckBox("Solid")).append("Solid<br/>");	
			strBuff.append(this.getCheckBox("Solid_1")).append(_m6_1).append("</span>")
			   	   .append(this.getCheckBox("Solid_2")).append(_m6_2).append("</span>")
			   	   .append(this.getCheckBox("Solid_2")).append(_m6_3).append("</span>")
			   	   .append(this.getCheckBox("Solid_2")).append(_m6_4).append("</span>");
		} else if("SC1197".equals(catalogNo)||"SC1216".equals(catalogNo)||"SC1217".equals(catalogNo)
				||"SC1384".equals(catalogNo)||"SC1385".equals(catalogNo)) {
			strBuff.append(this.getCheckBox("Liquid")).append("Liquid<br/>");
			StringBuffer m7_1 = new StringBuffer("");
			StringBuffer _m7_1 = new StringBuffer("");
			StringBuffer m7_2 = new StringBuffer("");
			StringBuffer m7_3 = new StringBuffer("");
			StringBuffer m7_5 = new StringBuffer("");
			StringBuffer m7_6 = new StringBuffer("");
			StringBuffer _m7_2 = new StringBuffer("");
			StringBuffer _m7_3 = new StringBuffer("");
			StringBuffer _m7_5 = new StringBuffer("");
			StringBuffer _m7_6 = new StringBuffer("");
			for(int i =1;i<6;i++) {
				StringBuffer m7_1_buf = new StringBuffer("");
				m7_1_buf.append("Pre-immune Serum of #").append(i).append(",Volume: ").append(this.getInput(false,"")).append(" ul,Order ID: ")
					.append(orderNo).append("_").append(workOrder.getSoItemNo())
					.append(" Lot No. A2").append(lotNoPre).append(this.getInput(true,""));
				m7_1.append(m7_1_buf);
				_m7_1.append("Lyophilized ").append(m7_1_buf);
				StringBuffer m7_2_buf = new StringBuffer("");
				m7_2_buf.append("Test Bleed of #").append(i).append(",Antigen name: ").append(ag).append(",Post 2nd").append(" immunization   Volume: ")
					.append(this.getInput(false,"")).append(" ul,").append(",Order ID: ")
					.append(orderNo).append("_").append(workOrder.getSoItemNo())
					.append(" Lot No. A2").append(lotNoPre).append(this.getInput(true,""));
				m7_2.append(m7_2_buf);
				_m7_2.append("Lyophilized ").append(m7_2_buf);
				StringBuffer m7_3_buf = new StringBuffer("");
				m7_3_buf.append("Test Bleed of #").append(i).append(",Antigen name: ").append(ag).append(",Post 3nd").append(" immunization   Volume: ")
					.append(this.getInput(false,"")).append(" ul,").append(",Order ID: ")
					.append(orderNo).append("_").append(workOrder.getSoItemNo())
					.append(" Lot No. A2").append(lotNoPre).append(this.getInput(true,""));
				m7_3.append(m7_3_buf);
				_m7_3.append("Lyophilized ").append(m7_3_buf);
				StringBuffer m7_5_buf = new StringBuffer("");
				m7_5_buf.append("Test Bleed of #").append(i).append(",Antigen name: ").append(ag).append(",Post 4nd").append(" immunization   Volume: ")
					.append(this.getInput(false,"")).append(" ul,").append(",Order ID: ")
					.append(orderNo).append("_").append(workOrder.getSoItemNo())
					.append(" Lot No. A2").append(lotNoPre).append(this.getInput(true,""));
				m7_5.append(m7_5_buf);
				_m7_5.append("Lyophilized ").append(m7_5_buf);
				StringBuffer m7_6_buf = new StringBuffer("");
				m7_6_buf.append("Test Bleed of #").append(i).append(",Antigen name: ").append(ag).append(",Post 5nd").append(" immunization   Volume: ")
					.append(this.getInput(false,"")).append(" ul,").append(",Order ID: ")
					.append(orderNo).append("_").append(workOrder.getSoItemNo())
					.append(" Lot No. A2").append(lotNoPre).append(this.getInput(true,""));
				m7_6.append(m7_6_buf);
				_m7_6.append("Lyophilized ").append(m7_6_buf);
			}
			strBuff.append(this.getCheckBox("Liquid_1")).append(m7_1).append("</span>")
				   .append(this.getCheckBox("Liquid_2")).append(m7_2).append("</span>")
				   .append(this.getCheckBox("Liquid_3")).append(m7_3).append("</span>")
				   .append(this.getCheckBox("Liquid_4")).append(m7_5).append("</span>")
				   .append(this.getCheckBox("Liquid_5")).append(m7_6).append("</span>");
			strBuff.append("</span>").append("<br/>").append(this.getCheckBox("Solid")).append("Solid<br/>");	
			strBuff.append(this.getCheckBox("Solid_1")).append(_m7_1).append("</span>")
			   	   .append(this.getCheckBox("Solid_2")).append(_m7_2).append("</span>")
			   	   .append(this.getCheckBox("Solid_3")).append(_m7_3).append("</span>")
			   	   .append(this.getCheckBox("Solid_4")).append(_m7_5).append("</span>")
			   	   .append(this.getCheckBox("Solid_5")).append(_m7_6).append("</span></span>");
		} else if("SC1198".equals(catalogNo)||"SC1219".equals(catalogNo)) {
			StringBuffer m12_1 = new StringBuffer("");
			strBuff.append(this.getCheckBox("Liquid")).append("Liquid<br/>");
			if(cloneIdArray!=null) {
				for(String cloneId:cloneIdArray) {
					m12_1.append("Positive Parental Supernatant,Clone ID: ").append(cloneId).append(",Ag: ").append(ag)
						 .append("      Volume: ").append(this.getInput(false,"")).append(",Order ID: ")
						 .append(orderNo).append("_").append(workOrder.getSoItemNo())
						 .append(" Lot No. A2").append(lotNoPre)
						 .append(this.getInput(true,""));
				}
			}
			strBuff.append(this.getCheckBox("Liquid_1")).append(m12_1).append("</span></span>");
		} else if("SC1060".equals(catalogNo)) {
			strBuff.append(this.getCheckBox("Liquid")).append("Liquid<br/>");
			StringBuffer m8_1 = new StringBuffer("");
			StringBuffer m8_2 = new StringBuffer("");
			m8_1.append("Lyophilized Fab Fragment of antibody,Quantity: ").append(this.getInput(false,""))
				   .append(" mg/ml @ ").append(this.getInput(false,"")).append(" ml,Order ID: ")
				   .append(orderNo).append("_").append(workOrder.getSoItemNo())
				   .append(" Lot No. A2").append(lotNoPre).append(this.getInput(true,""));
			m8_2.append("Lyophilized Fab Fragment of antibody,Quantity: ").append(this.getInput(false,""))
			    .append(" mg   Volume: ").append(this.getInput(false,"")).append(" ml,Order ID: ")
			    .append(orderNo).append("_").append(workOrder.getSoItemNo())
			    .append(" Lot No. A2").append(lotNoPre).append(this.getInput(true,""));
			strBuff.append(this.getCheckBox("Liquid_1")).append(m8_1).append("</span></span>");
			strBuff.append("<br/>").append(this.getCheckBox("Solid")).append("Solid<br/>");
			strBuff.append(this.getCheckBox("Solid_1")).append(m8_2).append("</span></span>");
		} else if("SC1061".equals(catalogNo)) {
			strBuff.append(this.getCheckBox("Liquid")).append("Liquid<br/>");
			StringBuffer m9_1 = new StringBuffer("");
			StringBuffer m9_2 = new StringBuffer("");
			m9_1.append("Biotin Conjugated Antibody,Quantity: ").append(this.getInput(false,""))
				   .append(" mg/ml @ ").append(this.getInput(false,"")).append(" ml,Order ID: ")
				   .append(orderNo).append("_").append(workOrder.getSoItemNo())
				   .append(" Lot No. A2").append(lotNoPre).append(this.getInput(true,""));
			m9_2.append("Biotin Conjugated Antibody,Quantity: ").append(this.getInput(false,""))
			    .append(" mg   Volume: ").append(this.getInput(false,"")).append(" ml,Order ID: ")
			    .append(orderNo).append("_").append(workOrder.getSoItemNo())
			    .append(" Lot No. A2").append(lotNoPre).append(this.getInput(true,""));
			strBuff.append(this.getCheckBox("Liquid_1")).append(m9_1).append("</span></span>");
			strBuff.append("<br/>").append(this.getCheckBox("Solid")).append("Solid<br/>");
			strBuff.append(this.getCheckBox("Solid_1")).append("Lyophilized ").append(m9_2).append("</span></span>");
		} else if("SC1062".equals(catalogNo)) {
			strBuff.append(this.getCheckBox("Liquid")).append("Liquid<br/>");
			StringBuffer m10_1 = new StringBuffer("");
			StringBuffer m10_2 = new StringBuffer("");
			m10_1.append("HRP Conjugated Antibody,Quantity: ").append(this.getInput(false,""))
				   .append(" mg/ml @ ").append(this.getInput(false,"")).append(" ml,Order ID: ")
				   .append(orderNo).append("_").append(workOrder.getSoItemNo())
				   .append(" Lot No. A2").append(lotNoPre).append(this.getInput(true,""));
			m10_2.append("HRP Conjugated Antibody,Quantity: ").append(this.getInput(false,""))
			    .append(" mg   Volume: ").append(this.getInput(false,"")).append(" ml,Order ID: ")
			    .append(orderNo).append("_").append(workOrder.getSoItemNo())
			    .append(" Lot No. A2").append(lotNoPre).append(this.getInput(true,""));
			strBuff.append(this.getCheckBox("Liquid_1")).append(m10_1).append("</span></span>");
			strBuff.append("<br/>").append(this.getCheckBox("Solid")).append("Solid<br/>");
			strBuff.append(this.getCheckBox("Solid_1")).append("Lyophilized ").append(m10_2).append("</span></span>");
		} else if("SC1063".equals(catalogNo)) {
			strBuff.append(this.getCheckBox("Liquid")).append("Liquid<br/>");
			StringBuffer m11_1 = new StringBuffer("");
			StringBuffer m11_2 = new StringBuffer("");
			m11_1.append("FITC Conjugated Antibody,Quantity:").append(this.getInput(false,""))
				   .append(" mg/ml @ ").append(this.getInput(false,"")).append(" ml,Order ID: ")
				   .append(orderNo).append("_").append(workOrder.getSoItemNo())
				   .append(" Lot No. A2").append(lotNoPre).append(this.getInput(true,""));
			m11_2.append("FITC Conjugated Antibody,Quantity:").append(this.getInput(false,""))
			    .append(" mg   Volume: ").append(this.getInput(false,"")).append(" ml,Order ID: ")
			    .append(orderNo).append("_").append(workOrder.getSoItemNo())
			    .append(" Lot No. A2").append(lotNoPre).append(this.getInput(true,""));
			strBuff.append(this.getCheckBox("Liquid_1")).append(m11_2).append("</span></span>");
			strBuff.append("<br/>").append(this.getCheckBox("Solid")).append("Solid<br/>");
			strBuff.append(this.getCheckBox("Solid_1")).append("Lyophilized ").append(m11_2).append("</span></span>");
		}
		result.append(strBuff);
		return result.toString();
	}
	
	/**
	 * 给所有被选择的wo添加label信息并返回
	 */
	public List<WorkOrderDTO> getSelWOLabel(String allChoiceVal) {
		List<WorkOrderDTO> list = new ArrayList<WorkOrderDTO>();
		String[] workOrderIdArray = allChoiceVal.split(",");
		for(String id:workOrderIdArray) {
			WorkOrder workOrder = this.workOrderDao.getById(Integer.parseInt(id));
			if(workOrder==null) {
				continue;
			}
			List<WorkOrderLot> workOrderLotList = workOrderLotDao.getWorkOrderLotByWo(Integer.parseInt(id));
			StringBuffer lotNoBuff = new StringBuffer("");
			for(WorkOrderLot lot:workOrderLotList) {
				if(lotNoBuff.toString().equals("")) {
					lotNoBuff.append(lot.getLotNo());
				} else {
					lotNoBuff.append(",").append(lot.getLotNo());
				}
				
			}
			workOrder.setLotNo(lotNoBuff.toString());
			String workOrderType = null;
			StringBuffer labels = new StringBuffer("");
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
			if(workOrderType.toLowerCase().contains("peptide")&&QuoteItemType.SERVICE.value().equals(workOrder.getItemType())) {
                OrderPeptide orderPeptide = orderPeptideDao.getById(orderItem.getOrderItemId());
				labels.append(workOrder.getSrcSoNo()).append("-").append(workOrder.getSoItemNo()).append("&nbsp;&nbsp;&nbsp;&nbsp;");
				String quantity = "";
				String sequence = "";
				String purity = "";
				BigDecimal molecularWeight = null;
				if(orderPeptide!=null) {
					quantity = orderPeptide.getRealQuantity();
					sequence = orderPeptide.getSequence();
					purity = orderPeptide.getRealPurity();
					molecularWeight = orderPeptide.getMolecularWeight()!=null?orderPeptide.getMolecularWeight().setScale(2, BigDecimal.ROUND_HALF_UP):null;
				}
				if(StringUtils.isEmpty(workOrder.getLotNo())) {
					continue;
				}
				labels.append(quantity);
				labels.append("|Peptide Order ID: ").append(workOrder.getSrcSoNo()).append("-").append(workOrder.getSoItemNo()).append("|");
				labels.append(sequence).append("| Purity:  ").append(purity).append("| MW: ").append(molecularWeight!=null?molecularWeight:"");
				labels.append("| Lot No:").append(workOrder.getLotNo()!=null?workOrder.getLotNo():"");
			} else if(workOrderType.toLowerCase().contains("peptide")&&QuoteItemType.PRODUCT.value().equals(workOrder.getItemType())) {
				OrderPeptide orderPeptide = orderPeptideDao.getById(orderItem.getOrderItemId());
				String quantity = "";
				String purity = "";
				if(orderPeptide!=null) {
					quantity = orderPeptide.getQuantity();
					purity = orderPeptide.getPurity();
				}
				if(StringUtils.isEmpty(workOrder.getLotNo())) {
					continue;
				}
				Product product = workOrder.getCatalogNo()!=null?this.productDao.getProductByCatalogNo(workOrder.getCatalogNo()):null;
				labels.append(product!=null?product.getName():"").append("|").append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;").append(" Purity: ").append(purity)
					  .append(" ").append(quantity).append("| Cat.NO.: ").append(workOrder.getCatalogNo()!=null?workOrder.getCatalogNo():"").append("| Lot NO:")
					  .append(workOrder.getLotNo()!=null?workOrder.getLotNo():"");
			} else if(workOrderType.toLowerCase().contains("oligo")){
				labels.append("<a href=\"#\" onclick=\"edit("+workOrder.getOrderNo()+",'oligo')\">Create DNA Oligos label</a>");
			} else if(workOrderType.toLowerCase().contains("gene")) {
				OrderGeneSynthesis orderGeneSynthesis = orderGeneSynthesisDao.getById(orderItem.getOrderItemId());
				String vector = "";
				String quanitity = "";
				if(StringUtils.isEmpty(workOrder.getLotNo())) {
					continue;
				}
				if(orderGeneSynthesis!=null&&orderGeneSynthesis.getCloningFlag()!=null&&"Y".equals(orderGeneSynthesis.getCloningFlag())) {
					List<OrderItem> itemList = this.orderItemDao.findBy("parentId", orderItem.getOrderItemId());
					OrderCustCloning orderCustCloning = null;
					for(OrderItem item:itemList) {
						orderCustCloning = orderCustCloningDao.getById(item.getOrderItemId());
						if(orderCustCloning!=null) {
							break;
						}
					}
					vector = orderCustCloning!=null?orderCustCloning.getTgtVector():"";
				} else if(orderGeneSynthesis!=null){
					vector = orderGeneSynthesis.getStdVectorName();
				} else {
					WorkOrderDTO workOrderDTO = new WorkOrderDTO();
					workOrderDTO.setOrderNo(workOrder.getOrderNo());
					workOrderDTO.setLabels(labels.toString());
					list.add(workOrderDTO);
					continue;
				}
				labels.append(orderGeneSynthesis.getGeneName()).append(",in ").append(vector);
				if(orderGeneSynthesis!=null&&orderGeneSynthesis.getPlasmidPrepFlag()!=null&&"Y".equals(orderGeneSynthesis.getPlasmidPrepFlag())) {
					List<OrderItem> itemList = this.orderItemDao.findBy("parentId", orderItem.getOrderItemId());
					OrderPlasmidPreparation orderPlasmidPreparation = null;
					for(OrderItem item:itemList) {
						orderPlasmidPreparation = orderPlasmidPreparationDao.getById(item.getOrderItemId());
						if(orderPlasmidPreparation!=null) {
							break;
						}
					}
					quanitity = orderPlasmidPreparation!=null?orderPlasmidPreparation.getPrepWeight().intValue()+orderPlasmidPreparation.getPrepWtUom():"";
				} else if(orderGeneSynthesis!=null) {
					quanitity = orderGeneSynthesis.getStdPlasmidWt()+orderGeneSynthesis.getStdPlasmidWtUom();
				} 
				labels.append("&nbsp;&nbsp;&nbsp;&nbsp;");
				if(quanitity!=null&&"4ug".equals(quanitity)) {
					labels.append("&nbsp;");
					labels.append("Miniprep(\\~4ug)").append(",Lot:").append(workOrder.getLotNo()!=null?workOrder.getLotNo():"").append("<br>");
					labels.append("Top 10/").append(vector).append("-").append(orderGeneSynthesis.getGeneName()).append(",Order:")
							.append(workOrder.getSrcSoNo()).append("S-").append(workOrder.getSoItemNo());
				} else if(quanitity!=null&&"100ug".equals(quanitity)) {
					StringBuffer labels2 = new StringBuffer(); 
					labels2.append(labels);
					labels.append("Maxiprep(\\~50ug)").append(",Lot:").append(workOrder.getLotNo()!=null?workOrder.getLotNo():"").append("<br>");
					labels.append("Top 10/").append(vector).append("-").append(orderGeneSynthesis.getGeneName()).append(",Order:")
							.append(workOrder.getSrcSoNo()).append("S-").append(workOrder.getSoItemNo());
					labels.append("<br>");
					labels2.append("Miniprep(\\~4ug)").append(",Lot:").append(workOrder.getLotNo()!=null?workOrder.getLotNo():"").append("<br>");
					labels2.append("Top 10/").append(vector).append("-").append(orderGeneSynthesis.getGeneName()).append(",Order:")
							.append(workOrder.getSrcSoNo()).append("S-").append(workOrder.getSoItemNo());
					labels.append(labels2);
				} else {
					labels.append(quanitity).append(",Lot:").append(workOrder.getLotNo()!=null?workOrder.getLotNo():"").append("<br>");
					labels.append("Top 10/").append(vector).append("-").append(orderGeneSynthesis.getGeneName()).append(",Order:")
							.append(workOrder.getSrcSoNo()).append("S-").append(workOrder.getSoItemNo());
				}
				
			} else if(workOrderType.toLowerCase().contains("protein")) {
				if(StringUtils.isEmpty(workOrder.getLotNo())) {
					continue;
				}
				labels.append("<a href=\"#\" onclick=\"edit("+workOrder.getOrderNo()+",'protein')\">Create protein label</a>");
			} else if(workOrderType.toLowerCase().contains("monoclonal antibody") ) {
				labels.append("<a href=\"#\" onclick=\"edit("+workOrder.getOrderNo()+",'monoclonal antibody')\">Create monoclonal antibody label</a>");
			} else if(workOrderType.toLowerCase().contains("polyclonal antibody")) {
				labels.append("<a href=\"#\" onclick=\"edit("+workOrder.getOrderNo()+",'polyclonal antibody')\">Create polyclonal antibody label</a>");
			} else {
				continue;
			}
			WorkOrderDTO workOrderDTO = new WorkOrderDTO();
			workOrderDTO.setOrderNo(workOrder.getOrderNo());
			workOrderDTO.setLabels(labels.toString());
			list.add(workOrderDTO);
		}
		return list;
	}
	
	/**
	 * 生成pdf文件并返回路径
	 */
	public String createPdf(List<WorkOrderDTO> workOrderList){
		Process process = null;
		String processName = PdfUtils.getPdfProcessName();
		String tempPdfFileName =null;
		try {
			StringBuffer html = new StringBuffer("");
			html.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">")
				.append("<html><head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'></head><body>")
				.append("<table width='100%' border='1' cellspacing='0' cellpadding='0'>")
				.append("<tr><td width='10%' align='center'>Work Order No</td><td width='90%' align='center'>Label</td></tr>");
			if(workOrderList!=null) {	
				for(WorkOrderDTO workOrderDTO:workOrderList) {
					html.append("<tr><td>").append(workOrderDTO.getOrderNo()).append("</td>")
						.append("<td>").append(workOrderDTO.getLabels()).append("</td></tr>");
				}
			}
			html.append("</table></body></html>");
			String tempHtmlFileName = PdfUtils.getTempFileShortName() + ".html";
			File file = new File(tempHtmlFileName);
			if(file.isFile()&&!file.getParentFile().exists()) {
				if(!file.getParentFile().mkdirs()) {
					System.out.println("create parent directory fail");
				}
			}
			DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
			out.writeUTF(html.toString());
			out.flush();
			out.close();
			tempPdfFileName = PdfUtils.getTempFileShortName()+".pdf";
			ProcessBuilder pb = new ProcessBuilder(processName,
					tempHtmlFileName, tempPdfFileName);
			pb.redirectErrorStream(true);
			process = pb.start();
			InputStreamReader ir = new InputStreamReader(process.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			String line = null;
			while ((line = input.readLine()) != null) {// 这句必须有
				System.out.println(line);
			}
			file.delete();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);

		} finally {
			if (process != null) {
				process.destroy();
			}
		}
		return tempPdfFileName;
		    

		
		
//		Document document = new Document(PageSize.A4.rotate(),18f,18f,18f,10f);
//		String filePath = null;
//		try {
//			StyleSheet st = new StyleSheet();
//			st.loadTagStyle("body", "leading", "16,0");
//			filePath = PdfUtils.getTempFileShortName()+".pdf";
//			PdfWriter.getInstance(document, new FileOutputStream(filePath));
//			BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
//			Font fontChinese = new Font(bfChinese, 12, Font.NORMAL, Color.BLACK);
//			document.open(); 
//			int[] widths={96,400};
//			PdfPTable table = new PdfPTable(2);
//			table.setWidths(widths);
//			table.setWidthPercentage(100);
//			table.setSpacingBefore(3f);
//			table.getDefaultCell().setBorder(1);
//			PdfPCell cell00 = new PdfPCell(new Paragraph("Work Order No")); 
//			cell00.setHorizontalAlignment(Element.ALIGN_CENTER);
//			table.addCell(cell00);
//			PdfPCell cell01 = new PdfPCell(new Paragraph("Label")); 
//			cell01.setHorizontalAlignment(Element.ALIGN_CENTER); 
//			table.addCell(cell01);
//			if(workOrderList!=null) {	
//				for(WorkOrderDTO workOrderDTO:workOrderList) {
//					PdfPCell cell1 = new PdfPCell(new Paragraph(String.valueOf(workOrderDTO.getOrderNo()))); 
//					cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//					table.addCell(cell1);
//					PdfPCell cell2 = new PdfPCell(new Paragraph(workOrderDTO.getLabels().replace("&nbsp;", " ").replaceAll("<*?>", replacement),fontChinese)); 
//					cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
//					table.addCell(cell2);
//				}
//			}
//			document.add(table);
//		} catch(Exception e) {
//			e.printStackTrace();
//		} finally {
//			document.close();
//		}
//		return filePath;
	}
	
	/**
	 * 生成txt文件并返回路径
	 */
	public String createTxt(List<WorkOrderDTO> workOrderList){
		String tempTxtFileName =null;
		try {
			String str = "";
			if(workOrderList!=null) {	
				for(WorkOrderDTO workOrderDTO:workOrderList) {
					str = str +workOrderDTO.getLabels().toString().replace("&nbsp;", " ")
						   .replaceAll("</{0,1}(br|BR)/{0,1}>", "\r\n")
						   .replaceAll("<.+?>", "")
						   +"\r\n";
				}
			}
			tempTxtFileName = PdfUtils.getTempFileShortName() + ".txt";
			File file = new File(tempTxtFileName);
			if(!file.getParentFile().exists()) {
				if(!file.getParentFile().mkdirs()) {
					System.out.println("create parent directory fail");
				}
			}
			FileWriter out = new FileWriter(tempTxtFileName);
			out.write(str);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);

		} 
		return tempTxtFileName;
	}
	public String deleteWo(String allChoiceVal,String reason,String roleName) throws Exception{
		if (!Constants.USERNAME_ADMIN.equals(SessionUtil.getUserName())) {
			boolean productionManager = userRoleDao.checkIsContainsManagerRole(roleName);
			if(!productionManager) {
				return "fail";
			}
		}
		if(StringUtils.isNotEmpty(allChoiceVal)) {
			String[] woIdArray = allChoiceVal.split(",");
			List<Integer> idList = new ArrayList<Integer>();
			for(String id:woIdArray) {
				idList.add(Integer.parseInt(id));
				WoStatusHistory woStatus = new WoStatusHistory();
				woStatus.setOrderNo(Integer.parseInt(id));
				woStatus.setUpdateDate(new Date());
				woStatus.setUpdatedBy(SessionUtil.getUserId());
				woStatus.setStatusType("WORK_ORDER_CANCEL");
				woStatus.setStatus(WorkOrderStatus.Canceled.value());
				woStatus.setUpdateReason(reason);
				this.woStatusHistoryDao.save(woStatus);
			}
			this.workOrderDao.batchUpdateStatus(idList,WorkOrderStatus.Canceled.value());
		}
		return "success";
		
	}
	
	/**
	 * 生成邮件附件
	 */
	public Document createAttachment(Integer workOrderNo,String path) throws Exception{
		Document doc = new Document();
		WorkOrder workOrder = this.workOrderDao.getById(workOrderNo);
		List<WorkOrderOperation> woOperationList = this.workOrderOperationDao.findBy("workOrderNo", workOrderNo);
		String random = SessionUtil.generateTempId();
		File xmlFile = new File(path+"workOrderOperation_"+random+".xls");
		if(!xmlFile.getParentFile().exists()) {
			if(!xmlFile.getParentFile().mkdirs()) {
				System.out.println("create parent directory fail");
				return null;
			}
		}
		FileOutputStream out = new FileOutputStream(xmlFile);
		WritableWorkbook wbook = Workbook.createWorkbook(out);  
		WritableSheet wsheet = wbook.createSheet("Work order operation", 0); //工作表名称 
		wsheet.setColumnView(2, 50);
		//设置Excel字体 
		WritableFont wfont = new WritableFont(WritableFont.ARIAL, 14, 
		WritableFont.BOLD, false, 
		jxl.format.UnderlineStyle.NO_UNDERLINE, 
		jxl.format.Colour.BLACK); 
		WritableCellFormat titleFormat = new WritableCellFormat(wfont); 
		WritableCellFormat contentFormat = new WritableCellFormat(); 
		titleFormat.setShrinkToFit(true);
		contentFormat.setShrinkToFit(true);
		String[] title2 = {"Step","Host No.","Progress","Date"}; 
		//设置Excel表头 
		for (int i = 0; i < title2.length; i++) { 
			Label excelTitle = new Label(i, 0, title2[i], titleFormat); 
			wsheet.addCell(excelTitle); 
		} 
		int c = 1; //用于循环时Excel的行号 
		if(woOperationList!=null) {
			for(WorkOrderOperation workOrderOperation:woOperationList) {
				Label content1 = new Label(0, c, workOrderOperation.getSeqNo().toString(),contentFormat);
				Label content2 = new Label(1, c, workOrder.getHostNo(),contentFormat);
				String operationName = workOrderOperation.getOperation()!=null?workOrderOperation.getOperation().getName():null;
				operationName = operationName!=null?operationName:this.operationDao.getById(workOrderOperation.getOperation().getId()).getName();
				if(operationName!=null&&operationName.startsWith("PANT")&&operationName.indexOf("|")!=-1) {
					operationName = operationName.substring(4,operationName.indexOf("|"));
				}
				Label content3 = new Label(2, c,operationName,contentFormat);
				Label content4 = new Label(3, c, DateUtils.formatDate2Str(DateUtils.dayBefore2Date(workOrderOperation.getExptdEndDate(), -2),DateUtils.C_DATE_PATTON_DEFAULT),contentFormat);
				wsheet.addCell(content1);
				wsheet.addCell(content2);
				wsheet.addCell(content3);
				wsheet.addCell(content4);
				c++;
			}
		}
		wbook.write(); //写入文件 
		wbook.close(); 
		out.close(); 
		doc.setDocName("Work_order_operation.xls");
		doc.setFilePath("quote_notes/"+"workOrderOperation_"+random+".xls");
		return doc;
	}
	
	/**
	 * 生成Excel
	 */
	public Document createExcel(List<PeptideTemplateDTO> list,String path) throws Exception{
		Document doc = new Document();
		String random = SessionUtil.generateTempId();
		File xmlFile = new File(path+"workorder_peptide_info_"+random+".xls");
		if(!xmlFile.getParentFile().exists()) {
			if(!xmlFile.getParentFile().mkdirs()) {
				System.out.println("create parent directory fail");
				return null;
			}
		}
		FileOutputStream out = new FileOutputStream(xmlFile);
		WritableWorkbook wbook = Workbook.createWorkbook(out);  
		WritableSheet wsheet = wbook.createSheet("workorder_peptide_info", 0); //工作表名称 
		wsheet.setColumnView(0, 15);
		wsheet.setColumnView(1, 15);
		wsheet.setColumnView(2, 15);
		wsheet.setColumnView(3, 15);
		wsheet.setColumnView(4, 25);
		wsheet.setColumnView(5, 15);
		wsheet.setColumnView(6, 15);
		wsheet.setColumnView(7, 25);
		wsheet.setColumnView(8, 15);
		wsheet.setColumnView(9, 15);
		//设置Excel字体 
		WritableFont wfont = new WritableFont(WritableFont.ARIAL, 12, 
		WritableFont.BOLD, false, 
		jxl.format.UnderlineStyle.NO_UNDERLINE, 
		jxl.format.Colour.BLACK); 
		WritableCellFormat titleFormat = new WritableCellFormat(wfont); 
		WritableCellFormat contentFormat = new WritableCellFormat(); 
		titleFormat.setShrinkToFit(true);
		contentFormat.setShrinkToFit(true);
		String[] title2 = {"Order_Item","Work Order No","name","MW","Lot.No","Real Quantity","Aliqo","Appearance","Destination","Real Purity"}; 
		//设置Excel表头 
		for (int i = 0; i < title2.length; i++) { 
			Label excelTitle = new Label(i, 0, title2[i], titleFormat); 
			wsheet.addCell(excelTitle); 
		} 
		int c = 1; //用于循环时Excel的行号 
		if(list!=null) {
			for(PeptideTemplateDTO peptideTemplateDTO:list) {
				Label content1 = new Label(0, c, peptideTemplateDTO.getOrderId(),contentFormat);
				Label content2 = new Label(1, c,peptideTemplateDTO.getWo() ,contentFormat);
				Label content3 = new Label(2, c,peptideTemplateDTO.getName(),contentFormat);
				Label content4 = new Label(3, c, peptideTemplateDTO.getMw()!=null?peptideTemplateDTO.getMw().toString():"",contentFormat);
				Label content5 = new Label(4, c, peptideTemplateDTO.getLotNo(),contentFormat);
				Label content6 = new Label(5, c, peptideTemplateDTO.getRealQuantity(),contentFormat);
				Label content7 = new Label(6, c, peptideTemplateDTO.getAliq(),contentFormat);
				Label content8 = new Label(7, c, peptideTemplateDTO.getAppearance(),contentFormat);
				Label content9 = new Label(8, c, peptideTemplateDTO.getDestination(),contentFormat);
				Label content10 = new Label(9, c, peptideTemplateDTO.getRealPurity(),contentFormat);
				wsheet.addCell(content1);
				wsheet.addCell(content2);
				wsheet.addCell(content3);
				wsheet.addCell(content4);
				wsheet.addCell(content5);
				wsheet.addCell(content6);
				wsheet.addCell(content7);
				wsheet.addCell(content8);
				wsheet.addCell(content9);
				wsheet.addCell(content10);
				c++;
			}
		}
		wbook.write(); //写入文件 
		wbook.close(); 
		out.close(); 
		doc.setDocName("Workorder_peptide_info.xls");
		doc.setFilePath("quote_notes/workorder_peptide_info_"+random+".xls");
		return doc;
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
			Customer customer = this.customerDao.getById(mfgOrder.getCustNo());
			dto.setCustNo(customer!=null?customer.getCustNo():null);
			dto.setEmail(customer!=null?customer.getBusEmail():null);
			dto.setPassword(customer!=null?customer.getPasswd():null);
			dto.setCustName(customer!=null?customer.getFirstName()+" "+customer.getMidName()+" "+customer.getLastName():null);
		}
		
		return dto;
	}
	
}
