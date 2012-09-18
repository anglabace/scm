package com.genscript.gsscm.manufacture.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.xwork.StringUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.common.constant.DocumentType;
import com.genscript.gsscm.common.constant.DsPlateStatusType;
import com.genscript.gsscm.common.constant.WoOperStatus;
import com.genscript.gsscm.common.constant.WorkOrderStatus;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.StringUtil;
import com.genscript.gsscm.manufacture.dao.DsPlateItemsDao;
import com.genscript.gsscm.manufacture.dao.DsPlatesDao;
import com.genscript.gsscm.manufacture.dao.ManuDocumentDao;
import com.genscript.gsscm.manufacture.dao.OperationDao;
import com.genscript.gsscm.manufacture.dao.WorkOrderDao;
import com.genscript.gsscm.manufacture.dao.WorkOrderOperationDao;
import com.genscript.gsscm.manufacture.entity.DsPlateItems;
import com.genscript.gsscm.manufacture.entity.DsPlates;
import com.genscript.gsscm.manufacture.entity.ManuDocument;
import com.genscript.gsscm.manufacture.entity.Operation;
import com.genscript.gsscm.manufacture.entity.WorkOrder;
import com.genscript.gsscm.manufacture.entity.WorkOrderOperation;
import com.genscript.gsscm.order.dao.MfgOrderDao;
import com.genscript.gsscm.order.dao.OrderDao;
import com.genscript.gsscm.order.dao.OrderDnaSequencingDao;
import com.genscript.gsscm.order.dao.OrderItemDao;
import com.genscript.gsscm.order.entity.MfgOrder;
import com.genscript.gsscm.order.entity.OrderDnaSequencing;
import com.genscript.gsscm.order.entity.OrderItem;
import com.genscript.gsscm.order.entity.OrderMain;

@Service
@Transactional
public class DsPlateService {
	@Autowired
	private DozerBeanMapper dozer;
	@Autowired
	private DsPlatesDao dsPlatesDao;
	@Autowired
	private DsPlateItemsDao dsPlateItemsDao;
	@Autowired
	private WorkOrderDao workOrderDao;
	@Autowired
	private OrderDnaSequencingDao orderDnaSequencingDao;
	@Autowired
	private MfgOrderDao mfgOrderDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderItemDao orderItemDao;
	@Autowired
	private OperationDao operationDao;
	@Autowired
	private WorkOrderOperationDao workOrderOperationDao;
	@Autowired
	private ManuDocumentDao manuDocumentDao;
	
	public DsPlates findById(Integer id) {
		return this.dsPlatesDao.getById(id);
	}
	
	public Page<DsPlates> searchDsPlates(Page<DsPlates> page,List<PropertyFilter> filters) {
		if(filters == null || filters.isEmpty()) {
			return this.dsPlatesDao.getAll(page);
		} else {
			return this.dsPlatesDao.findPage(page, filters);
		}
	}
	
	public DsPlates findReceivingPlate() {
		List<DsPlates> list = this.dsPlatesDao.findBy("status", DsPlateStatusType.Receiving.value());
		return list!=null&&list.size()>0?list.get(0):null;
	}
	
	
	public void saveOrUpdatePlate(List<WorkOrder> workOrderList,boolean newPlateFlag) throws Exception{
		if(workOrderList==null||workOrderList.size()==0) {
			return;
		}
		if(newPlateFlag) {
			createDsPlate(workOrderList,newPlateFlag);
		} else {
			DsPlates dsPlates = this.findReceivingPlate();
			if(dsPlates!=null) {
				int nullWell = 96-dsPlates.getNums();
				if(nullWell>=workOrderList.size()) {
					for(int i =0;i<workOrderList.size();i++) {
						createDsPlateItems(dsPlates.getNums()+i,dsPlates,workOrderList.get(i));
					}
					dsPlates.setNums(dsPlates.getNums()+workOrderList.size());
					if(dsPlates.getNums().intValue()==96) {
						if(plateHasConcItem(dsPlates.getPlateNo())) {
							dsPlates.setStatus(DsPlateStatusType.Concentration.value());
						} else {
							dsPlates.setStatus(DsPlateStatusType.AddPrimer.value());
						}
					} else {
						dsPlates.setStatus(DsPlateStatusType.Receiving.value());
					}
					this.dsPlatesDao.save(dsPlates);
				} else {
					for(int i =0;i<nullWell;i++) {
						createDsPlateItems(i,dsPlates,workOrderList.get(i));
					}
					createDsPlate(workOrderList.subList(nullWell, workOrderList.size()),newPlateFlag);
				}
			} else {
				createDsPlate(workOrderList,newPlateFlag);
			}
		}
	}
	
	private boolean createDsPlateItems(int index,DsPlates dsPlate,WorkOrder workOrder) throws Exception{
		String[] rows = new String[]{"A","B","C","D","E","F","G","H"};
		MfgOrder mfgOrder = this.mfgOrderDao.getById(workOrder.getSoNo());
		OrderItem orderItem  = this.orderItemDao.getOrderItem(mfgOrder!=null?mfgOrder.getSrcSoNo():workOrder.getSoNo(), workOrder.getSoItemNo());
		if(orderItem==null) {
			throw new NullPointerException();
		}
		OrderDnaSequencing orderDnaSequencing = this.orderDnaSequencingDao.getById(orderItem.getOrderItemId());
		DsPlateItems dsPlateItems = new DsPlateItems();
		dsPlateItems.setWorkOrderNo(workOrder!=null?workOrder.getOrderNo():null);
		dsPlateItems.setSampleBarcode(orderDnaSequencing!=null?orderDnaSequencing.getCode():null);
		dsPlateItems.setReceivedDate(new java.sql.Date(new Date().getTime()));
		String row = "A";
		String col = "12";
		if((index+1)%12==0) {
			row = rows[((index+1)/12-1)%12];
		} else {
			row = rows[((index+1)/12)%12];
			col = String.format("%02d", (index+1)%12);
		}
		dsPlateItems.setPlatePosition(row+col);
		dsPlateItems.setPlateNo(dsPlate.getPlateNo());
		dsPlateItems.setCreatedBy(SessionUtil.getUserId());
		dsPlateItems.setModifiedBy(SessionUtil.getUserId());
		dsPlateItems.setCreationDate(new Date());
		dsPlateItems.setModifyDate(new Date());
		this.dsPlateItemsDao.save(dsPlateItems);
		if(orderDnaSequencing.getFlagConcMeas()!=null&&orderDnaSequencing.getFlagConcMeas()==1) {
			return true;
		} 
		return false;
	}
	
	private void createDsPlate(List<WorkOrder> workOrderList,boolean flag) throws Exception{
		int size = workOrderList.size();
		int postion = 0;
		int index = 0;
		Long num = this.dsPlatesDao.findPlateNum();
		num = num!=null?num:0;
		while(true) {
			DsPlates dsPlates = new DsPlates();
			dsPlates.setCreatedBy(SessionUtil.getUserId());
			dsPlates.setCreationDate(new Date());
			dsPlates.setModifiedBy(SessionUtil.getUserId());
			dsPlates.setCreationDate(new Date());
			dsPlates.setNums(size>96?96:size);
			num ++;
			dsPlates.setPlateNo("GS"+String.format("%05d", num));
			dsPlates.setName(dsPlates.getPlateNo());
			postion = postion + dsPlates.getNums();
			this.dsPlatesDao.save(dsPlates);
			boolean conFlag = false;
			for(;index<postion;index++) {
				if(!conFlag) {
					conFlag = createDsPlateItems(index,dsPlates,workOrderList.get(index));
				} else {
					createDsPlateItems(index,dsPlates,workOrderList.get(index));
				}
			}
			if(dsPlates.getNums().intValue()==96||flag) {
				if(conFlag) {
					dsPlates.setStatus(DsPlateStatusType.Concentration.value());
				} else {
					dsPlates.setStatus(DsPlateStatusType.AddPrimer.value());
				}
			} else {
				dsPlates.setStatus(DsPlateStatusType.Receiving.value());
			}
			this.dsPlatesDao.save(dsPlates);
			size = size - 96;
			if(size<0) {
				break;
			}
		}
	}
	
	private boolean plateHasConcItem(String plateNo) {
		Map<String,Object> map = findDsPlateItemsByPlateNo(plateNo);
		if(map!=null&&map.get("hasConcentrationItem").equals("1")) {
			return true;
		} 
		return false;
	}
	
	public Map<String,Object> findDsPlateItemsByPlateNo(String plateNo) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		List<DsPlateItems> list = this.dsPlateItemsDao.findBy("plateNo", plateNo);
		List<DsPlateItems> resultList = new LinkedList<DsPlateItems>();
		String hasConcentrationItem = "";
		if(list!=null&&list.size()>0) {
			for(DsPlateItems dsPlateItems:list) {
				WorkOrder workOrder = this.workOrderDao.getById(dsPlateItems.getWorkOrderNo());
				if(workOrder==null) {
					continue;
				}
				MfgOrder mfgOrder = this.mfgOrderDao.getById(workOrder.getSoNo());
				if(mfgOrder==null) {
					continue;
				}
				OrderItem item = this.orderItemDao.getOrderItem(mfgOrder.getSrcSoNo(),workOrder.getSoItemNo());
				if(item==null) {
					continue;
				}
				OrderDnaSequencing orderDnaSequencing = this.orderDnaSequencingDao.getById(item.getOrderItemId());
				dsPlateItems.setOrderNo(mfgOrder.getSrcSoNo());
				dsPlateItems.setItemNo(item.getItemNo());
				dsPlateItems.setOrderDnaSequencing(orderDnaSequencing);
				resultList.add(dsPlateItems);
				if(orderDnaSequencing!=null&&orderDnaSequencing.getFlagConcMeas()!=null&&orderDnaSequencing.getFlagConcMeas().intValue()==1) {
					hasConcentrationItem="1"; 
				}
			}
		}
		resultMap.put("dsPlateItemsList", resultList);
		resultMap.put("hasConcentrationItem", hasConcentrationItem);
		return resultMap;
	}
	
	@Transactional(readOnly = false)
	public void updateConcentration(String dsPlateItemsIds,String concerntrationValues) {
		String[] dsPlateItemsIdArray = dsPlateItemsIds.split(",");
		String[] concerntrationValueArray = concerntrationValues.split(",");
		for(int i =0;i<dsPlateItemsIdArray.length;i++) {
			DsPlateItems dsPlateItems = this.dsPlateItemsDao.getById(Integer.parseInt(dsPlateItemsIdArray[i]));
			dsPlateItems.setConcerntrationValue(concerntrationValueArray[i]);
			this.dsPlateItemsDao.save(dsPlateItems);
			if(dsPlateItems!=null) {
				WorkOrderOperation workOrderOperation = this.workOrderOperationDao.findWoOperation("PSEQ Concentration Measurement",dsPlateItems.getWorkOrderNo());
				if(workOrderOperation!=null) {
					this.workOrderOperationDao.updateWorkOrderOperationsComment(workOrderOperation.getId(), concerntrationValueArray[i]);
				}
			}
		}
	}
	
	public void updateDsPlatesStatus(DsPlates dsPlates,String hasConcentrationItem,String concerntrationValues) {
		List<DsPlateItems> dsPlateItemsList = this.dsPlateItemsDao.findBy("plateNo", dsPlates.getPlateNo());
		List<Integer> workOrderNoList = new ArrayList<Integer>();
		List<Operation> operationList = null;
		if(dsPlateItemsList!=null&&dsPlateItemsList.size()>0) {
			for(DsPlateItems dsPlateItems:dsPlateItemsList) {
				workOrderNoList.add(dsPlateItems.getWorkOrderNo());
			}
		}
		if(DsPlateStatusType.Receiving.value().equals(dsPlates.getStatus())) {
			//operationList = operationDao.findBy("name", "");
			if(StringUtils.isNotEmpty(hasConcentrationItem)&&"1".equals(hasConcentrationItem)) {
				dsPlates.setStatus(DsPlateStatusType.Concentration.value());
			} else {
				dsPlates.setStatus(DsPlateStatusType.AddPrimer.value());
			}
		} else if(DsPlateStatusType.Concentration.value().equals(dsPlates.getStatus())) {
			operationList = operationDao.findBy("name", "PSEQ Concentration Measurement");
			dsPlates.setStatus(DsPlateStatusType.AddPrimer.value());
		} else if(DsPlateStatusType.AddPrimer.value().equals(dsPlates.getStatus())) {
			operationList = operationDao.findBy("name", "PSEQ Primer Adding");
			dsPlates.setStatus(DsPlateStatusType.PCR.value());
		} else if(DsPlateStatusType.PCR.value().equals(dsPlates.getStatus())) {
			operationList = operationDao.findBy("name", "PSEQ PCR Amplification");
			dsPlates.setStatus(DsPlateStatusType.Purification.value());
		} else if(DsPlateStatusType.Purification.value().equals(dsPlates.getStatus())) {
			operationList = operationDao.findBy("name", "PSEQ Purification");
			dsPlates.setStatus(DsPlateStatusType.Sequencing.value());
		} else if(DsPlateStatusType.Sequencing.value().equals(dsPlates.getStatus())) {
			operationList = operationDao.findBy("name", "PSEQ Sequencing (Electrophoresis)");
			dsPlates.setStatus(DsPlateStatusType.Finished.value());
		}
		if(operationList!=null&&operationList.size()>0) {
			this.workOrderOperationDao.batchWorkOrderOperation(workOrderNoList, operationList.get(0).getId());
			for(Integer workOrderNo : workOrderNoList) {
				List<WorkOrderOperation> woOperationList = this.workOrderOperationDao.findBy("workOrderNo", workOrderNo);
				if(woOperationList==null) {
					this.workOrderDao.updateWorkOrderStatus(workOrderNo, WorkOrderStatus.Completed.value());
				} else {
					boolean flg = true;
					for(WorkOrderOperation workOrderOperation:woOperationList) {
						if(workOrderOperation.getOperation().getId().intValue()==operationList.get(0).getId().intValue()) {
							continue;
						}
						if(!workOrderOperation.getStatus().equals(WoOperStatus.Completed.value())) {
							flg = false;
							break;
						}
					}
					if(flg) {
						this.workOrderDao.updateWorkOrderStatus(workOrderNo, WorkOrderStatus.Completed.value());
					} else {
						this.workOrderDao.updateWorkOrderStatus(workOrderNo, WorkOrderStatus.Inprogress.value());
					}
				}
			}
		}
		this.dsPlatesDao.save(dsPlates);
	}
	
	public List<DsPlateItems> findDataAnlyisDsPlateItemsByPlateNo(String plateNo) {
		List<DsPlateItems> list = this.dsPlateItemsDao.findBy("plateNo", plateNo);
		List<DsPlateItems> resultList = new LinkedList<DsPlateItems>();
		if(list!=null&&list.size()>0) {
			for(DsPlateItems dsPlateItems:list) {
				WorkOrder workOrder = this.workOrderDao.getById(dsPlateItems.getWorkOrderNo());
				if(workOrder==null) {
					continue;
				}
				MfgOrder mfgOrder = this.mfgOrderDao.getById(workOrder.getSoNo());
				if(mfgOrder==null) {
					continue;
				}
				OrderItem item = this.orderItemDao.getOrderItem(mfgOrder.getSrcSoNo(),workOrder.getSoItemNo());
				if(item==null) {
					continue;
				}
				OrderDnaSequencing orderDnaSequencing = this.orderDnaSequencingDao.getById(item.getOrderItemId());
				if(orderDnaSequencing!=null&&orderDnaSequencing.getFlagDataAnas()!=null&&orderDnaSequencing.getFlagDataAnas().intValue()==1) {
					dsPlateItems.setOrderNo(mfgOrder.getSrcSoNo());
					dsPlateItems.setItemNo(item.getItemNo());
					dsPlateItems.setOrderDnaSequencing(orderDnaSequencing);
					dsPlateItems.setAlignmentFileMap(new HashMap<String,ManuDocument>());
					dsPlateItems.setSequenceFileMap(new HashMap<String,ManuDocument>());
					dsPlateItems.setTraceFileMap(new HashMap<String,ManuDocument>());
					dsPlateItems.setQvDataMap(new HashMap<String,ManuDocument>());
					List<ManuDocument> manuDocumentList = this.manuDocumentDao.getDocumentList(workOrder.getOrderNo(), DocumentType.MANU_WORKORDER);
					if(manuDocumentList!=null) {
						for(ManuDocument manuDocument:manuDocumentList) {
							if("Sequencing File".equals(manuDocument.getDocType())) {
								dsPlateItems.getSequenceFileMap().put(manuDocument.getDocId().toString(),manuDocument);
							}
							if("Trace File".equals(manuDocument.getDocType())) {
								dsPlateItems.getTraceFileMap().put(manuDocument.getDocId().toString(),manuDocument);
							}
							if("QV Data".equals(manuDocument.getDocType())) {
								dsPlateItems.getQvDataMap().put(manuDocument.getDocId().toString(),manuDocument);
							}
							if("Alignment File".equals(manuDocument.getDocType())) {
								dsPlateItems.getAlignmentFileMap().put(manuDocument.getDocId().toString(),manuDocument);
							}
						}
					}
					resultList.add(dsPlateItems); 
				}
			}
		}
		return resultList;
	}
	
	public void saveUploadData(Map<String,DsPlateItems> map) {
		for(Entry<String, DsPlateItems> entry:map.entrySet()) {
			Map<String,ManuDocument> totalMap = new HashMap<String,ManuDocument>();
			totalMap.putAll(entry.getValue().getSequenceFileMap());
			totalMap.putAll(entry.getValue().getQvDataMap());
			totalMap.putAll(entry.getValue().getAlignmentFileMap());
			totalMap.putAll(entry.getValue().getTraceFileMap());
			for(Entry<String, ManuDocument> entryDoc:totalMap.entrySet()) {
				if(entryDoc.getValue()==null&&entryDoc.getKey()!=null&&StringUtil.isNumeric(entryDoc.getKey())) {
					this.manuDocumentDao.delete(Integer.parseInt(entryDoc.getKey()));
				} else if(entryDoc.getKey()!=null&&!StringUtil.isNumeric(entryDoc.getKey())&&entryDoc.getValue()!=null) {
					ManuDocument doc = dozer.map(entryDoc.getValue(), ManuDocument.class);
					doc.setRefId(entry.getValue().getWorkOrderNo());
					doc.setRefType(DocumentType.MANU_WORKORDER.value());
					doc.setCreatedBy(SessionUtil.getUserId());
					doc.setCreationDate(new Date());
					doc.setModifiedBy(SessionUtil.getUserId());
					doc.setModifyDate(new Date());
					this.manuDocumentDao.save(doc);
				}
			}
		}
	}
	
	
	@Transactional(readOnly=false)
	public void saveDsPlateItemsList(List<ManuDocument> docList,List<DsPlateItems> dsPlateItemsList) {
		for(ManuDocument doc:docList) {
			this.manuDocumentDao.save(doc);
		}
		for(DsPlateItems dsPlateItems:dsPlateItemsList) {
			if(dsPlateItems.getFileAnalysisFlag()==1) {
				WorkOrder workOrder = this.workOrderDao.getById(dsPlateItems.getWorkOrderNo());
				workOrder.setProductQc("Passed");
				workOrder.setProductQcDate(new java.sql.Date(new Date().getTime()));
				workOrder.setDocumentQc("Passed");
				workOrder.setDocumentQcDate(new java.sql.Date(new Date().getTime()));
				workOrder.setStatus(WorkOrderStatus.Closed.value());
				this.workOrderDao.save(workOrder);
			}
			dsPlateItems.setFileAnalysisFlag(1);
			this.dsPlateItemsDao.save(dsPlateItems);
		}
	}
}
