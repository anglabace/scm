package com.genscript.gsscm.purchase.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.common.ExceptionOut;
import com.genscript.gsscm.common.util.Arith;
import com.genscript.gsscm.common.util.FtpClient;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.StringToXMLUtil;
import com.genscript.gsscm.common.zip.FileUtil;
import com.genscript.gsscm.customer.dao.CustomerDao;
import com.genscript.gsscm.customer.entity.Customer;
import com.genscript.gsscm.manufacture.dao.WorkOrderDao;
import com.genscript.gsscm.manufacture.entity.WorkOrder;
import com.genscript.gsscm.order.dao.OrderAddressDao;
import com.genscript.gsscm.order.dao.OrderDao;
import com.genscript.gsscm.order.dao.OrderItemDao;
import com.genscript.gsscm.order.dao.OrderProcessLogDao;
import com.genscript.gsscm.order.entity.OrderAddress;
import com.genscript.gsscm.order.entity.OrderItem;
import com.genscript.gsscm.order.entity.OrderMain;
import com.genscript.gsscm.order.entity.OrderProcessLog;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.purchase.dao.PoReceiveingTmpDao;
import com.genscript.gsscm.purchase.entity.PoReceivingTmp;
import com.genscript.gsscm.purchase.entity.PurchaseOrder;
import com.genscript.gsscm.purchase.entity.PurchaseOrderItem;
import com.genscript.gsscm.serv.dao.ServiceDao;
import com.genscript.gsscm.shipment.dao.ShipPackageDao;
import com.genscript.gsscm.shipment.dao.ShipPackageLineDao;
import com.genscript.gsscm.shipment.dao.ShipmentLinesDao;
import com.genscript.gsscm.shipment.dao.ShipmentsDao;
import com.genscript.gsscm.shipment.entity.ShipMethod;
import com.genscript.gsscm.shipment.entity.ShipPackage;
import com.genscript.gsscm.shipment.entity.ShipPackageLines;
import com.genscript.gsscm.shipment.entity.Shipment;
import com.genscript.gsscm.shipment.entity.ShipmentLine;
import com.genscript.gsscm.shipment.service.ShipmentsService;
import com.genscript.gsscm.shipment.service.ShippingService;
import com.genscript.gsscm.systemsetting.dao.BillTerritoryDao;
import com.genscript.gsscm.ws.WSException;

@Service
@Transactional
public class ReadReceiveXmlService {
	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired
	private PurchaseOrderService purchaseOrderService;
	@Autowired
	private PurchaseOrderItemService purchaseOrderItemService;
	@Autowired
	private PoReceiveingTmpDao poReceiveingTmpDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private ShipmentLinesDao shipmentLinesDao;
	@Autowired
	private OrderItemDao orderItemDao;
	@Autowired
	private ShipmentsDao shipmentsDao;
	@Autowired
	private CustomerDao customerDao;
    @Autowired
    private OrderProcessLogDao orderProcessLogDao;
    @Autowired
    private OrderAddressDao orderAddressDao;
	@Autowired
	private BillTerritoryDao billTerritoryDao;
	@Autowired
	private ShipPackageDao shipPackageDao;
	@Autowired
	private ShipPackageLineDao shipPackageLineDao;
	@Autowired
	private WorkOrderDao workOrderDao;
	@Autowired
	private ShippingService shippingService;
	@Autowired
	private ShipmentsService shipmentsService;
	@Autowired
	private ServiceDao servDao;
	
	
	private File file;
	
	/**
	 * 
	 * @return the instance of Document
	 */
	public Document getDOM() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		Document document = null;
		try {
			db = dbf.newDocumentBuilder();
			document = db.parse(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return document;
	}
	
	public boolean download(String localPath, String remotePath)
			throws Exception {
		try{
			FtpClient.getInstance().setWorkDirectory(remotePath);
			String[] fileNames = FtpClient.getInstance().listNames();
			//FtpClient.getInstance().download(localPath, "2.xml");
			FileUtil fu= new FileUtil() ;
			File folder = new File(localPath);
			if(!folder.exists()){
				
				fu.createFolder(localPath);
			}
			if (fileNames != null && fileNames.length > 0) {
				for (String fileName : fileNames) {
					System.out.println("<<<<<<<<<<<<<<"+fileName);
					FtpClient.getInstance().setWorkDirectory(remotePath);
					
					boolean b = FtpClient.getInstance().download(localPath, fileName);
					if(b){
						
						System.out.println("DELE " + remotePath + File.separator + fileName);
						FtpClient.getInstance().setWorkDirectory(remotePath);
						FtpClient.getInstance().removeFile(remotePath, fileName);
					}
					
				}
				for (String fileName : fileNames){
					this.getInfoByTagName(localPath, "ShipDtl", 33 , fileName);
				}
			}
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
		

		return true;
	}
	
	public static void main(String[] args){
		ReadReceiveXmlService s = new ReadReceiveXmlService();
		String filename = "d:/1111.xml";
		System.out.println(s.getInfoByTrackingNo(filename));
	/*	try {*/
			/*for(int i = 0;i<10;i++){
				
			
			String localPath = "c:/tmp";
			String remotePath = "shipped/";
			DocumentBuilderFactory factory2 = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder2 = factory2.newDocumentBuilder();
			Document doc = builder2.newDocument();
			Element rootElement = doc.createElement("Received");
			Element soNoChildElement = doc.createElement("soNo");
			Text soNoTextNode = doc.createTextNode("111111");
			Element statusChildElement = doc.createElement("status");
			Text statusTextNode = doc.createTextNode("32323232");
			Element companyChildElement = doc.createElement("company");
			Text companyTextNode = doc.createTextNode("fdsafsadfdsa");
			doc.appendChild(rootElement);
			rootElement.appendChild(soNoChildElement);
			soNoChildElement.appendChild(soNoTextNode);
			rootElement.appendChild(statusChildElement);
			statusChildElement.appendChild(statusTextNode);
			rootElement.appendChild(companyChildElement);
			companyChildElement.appendChild(companyTextNode);
			// rootElement.setAttribute("font", "12");
			*//**
			 * 目前DOM API并不支持把DOM树写到输出流，我们借助于XML格式页转换（XSLT）
			 *//*
			Transformer t = TransformerFactory.newInstance().newTransformer();
			t.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "......");
			t.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "**********");
			t.setOutputProperty(OutputKeys.INDENT, "yes");
			String xmlName = SessionUtil.generateTempId();
			t.transform(new DOMSource(doc), new StreamResult(
					new FileOutputStream(new File(localPath + "/" + xmlName
							+ ".xml"))));
			System.out.println("localPath="+localPath+"/"+xmlName);
			//this.upload(remotePath, localPath + "/" + xmlName + ".xml");
			
			boolean b = false;
			while(!b){
				FtpClient.getInstance().setWorkDirectory(remotePath);
				b = FtpClient.getInstance().upload(localPath+"/", xmlName + ".xml", xmlName + ".xml");
				if(!b){
					System.out.println("updae false.");
				}
			}
			}
		}catch (Exception ex) {
				System.out.println("1111");
				//ExceptionOut.printException(exDTO);
			}*/
	}
	/*
	 * 获取trackingNo
	 */
	public String getInfoByTrackingNo(String filename){
		try {
			// DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			File file = new File(filename);
			this.file = file;
			String info = "";
			Document document = this.getDOM();
			String tagName = "ShipHead";
			// 获取NodeName 为tagName的节点组
			NodeList nl = document.getElementsByTagName(tagName);
			for (int i = 0; i < nl.getLength(); i++) {
				Map<String, String> map = new HashMap<String, String>();
				info += tagName + "[+" + i + "+]" + "\n";
				Node node = nl.item(i);

				// 如果当前节点有子节点（这里 只考虑还有一级子节点的情况）
				if (node.hasChildNodes()) {

					NodeList list = node.getChildNodes();

					for (int j = 0; j < list.getLength(); j++) {

						Node childNode = list.item(j);
						
						/* * 不加这个If语句会抛出 Exception in thread "main"
						 * java.lang.NullPointerException at
						 * ReadXML.getInfoByTagName(ReadXML.java:59) at
						 * Test.main(Test.java:17)*/
						 
						if (childNode.getFirstChild() != null) {
							if(childNode.getNodeName().equals("trackingNo")){
								return childNode.getFirstChild().getNodeValue();
							}
						}
					}
				} 
			}
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
		}
		return null;
	}
	
    private boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("^(-?//d+)(//.//d+)?$");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }


	
	/*
	 * 删除TMP 如果tracking no 存在，且已经有received
	 */
	public void delTmpByTrNO(String tNo){
		this.poReceiveingTmpDao.delPoTmp(tNo);
		this.poReceiveingTmpDao.flush();
	}
	/**
	 * 
	 * @param tagName
	 * @return
	 */
	public String getInfoByTagName(String path, String tagName,
			Integer userId ,String fileName) {
		try {
			// DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			String oldFile = path+fileName;
			//File file = new File(oldFile);
			String newFile =path + "new"+fileName;
			System.out.println("begain="+1);
			String con1 =  FileUtil.getContent(oldFile, "UTF-16");  
	        con1 = con1.replace("utf-16", "utf-8");
	        System.out.println("con1="+con1);
	        if(con1==null||con1.equals("")){
	        	con1 = " ";
	        }
	        FileUtil.createFile(newFile, con1, "UTF-8");
			this.file = new File(newFile);
			String info = "";
			Document document = this.getDOM();
			String tNo = this.getInfoByTrackingNo(oldFile);
			this.delTmpByTrNO(tNo);
			// 获取NodeName 为tagName的节点组
			NodeList nl = document.getElementsByTagName(tagName);
			List<PurchaseOrder> purchaseOrderList = new ArrayList<PurchaseOrder>();
			List<PurchaseOrderItem> itemList = new ArrayList<PurchaseOrderItem>();
			System.out.println("mapStart");
			String xmlStr1 ="<?xml version=\"1.0\" encoding=\"utf-8\" ?><Receive><Package>"; 
			String xmlStr2 = "<?xml version=\"1.0\" encoding=\"utf-8\" ?><Ship><Package><head>";
			xmlStr2 += "<trNo>"+tNo+"</trNo>";
			xmlStr2 += "</head>";
			String xmlStr2Is = xmlStr2;
			for (int i = 0; i < nl.getLength(); i++) {
				System.out.println("map");
				Map<String, String> map = new HashMap<String, String>();
				info += tagName + "[+" + i + "+]" + "\n";
				Node node = nl.item(i);

				// 如果当前节点有子节点（这里 只考虑还有一级子节点的情况）
				if (node.hasChildNodes()) {

					NodeList list = node.getChildNodes();

					for (int j = 0; j < list.getLength(); j++) {

						Node childNode = list.item(j);
						
						/* * 不加这个If语句会抛出 Exception in thread "main"
						 * java.lang.NullPointerException at
						 * ReadXML.getInfoByTagName(ReadXML.java:59) at
						 * Test.main(Test.java:17)*/
						 
						if (childNode.getFirstChild() != null) {
							System.out.println("mapLIst");
							map.put(childNode.getNodeName(), childNode
									.getFirstChild().getNodeValue());
							info += childNode.getNodeName() + ":"
									+ childNode.getFirstChild().getNodeValue()
									+ "\n";
						}
						
						/* * 对getNodeValue()的过程彻底无语 调试这个地方的时候，在网上很容易找到了
						 * 在得到Value的时候必须在节点对象后面先调用getFirstChild
						 * ()或者getChildNodes().item(0) 原因很简单，但是不知道设计者问什么要这么设计
						 * 最近在看《Beyond JAVA》（本人很推荐）,确实，JAVA很多地方应该简化，API等，生产力很重要
						 * 当然不是只给“语法甜头”，而是从底层实现的角度来满足简化需要*/
						 

					}
					PoReceivingTmp tmp = new PoReceivingTmp();
					System.out.println("start");
					String orderNo = map.get("number01");
					String uom = map.get("UOM");
					String itemNO = map.get("number02");
					String partNum  = map.get("PartNum");
					String qty = map.get("qty");
					System.out.println("itemNO:  "+itemNO);
					//String size = "0";//map.get("size");
					if(orderNo != null && itemNO != null
							&& qty != null
							&& tNo != null&&partNum !=null&&!partNum.equals("Service")){
					OrderMain order  = this.orderDao.getById(Integer.valueOf(orderNo));
					if(order!=null){
						//Customer customer = this.customerDao.getById(order.getCustNo());
						//OrderAddress orderAddress = this.orderAddressDao.get(order.getBilltoAddrId());
						OrderItem orderItem = this.orderItemDao.getOrderItem(order.getOrderNo(), Integer.valueOf(itemNO));
						String company = null;
						if(orderItem!=null){
							//company = billTerritoryDao.getAccountCode(orderAddress.getCountry(), orderAddress.getState(), orderAddress.getZipCode());
							company = orderItem.getShippingRoute();
						}
						if(company==null){
							company="US";
						}
						if("Customer".equals(company)||"CN".equals(company)){
							List<ShipmentLine> shipmentLineList  = this.shipmentLinesDao.getShipmentLine(orderItem.getItemNo(), orderItem.getOrderNo());
							if(shipmentLineList!=null&&!shipmentLineList.isEmpty()){
								for(ShipmentLine shipmentLine : shipmentLineList){
									if(!shipmentLine.getShipments().getStatus().equals("Invalid")){
										List<ShipPackage> shipPackageList = this.shipPackageDao.findBy("trackingNo", tNo);
										ShipPackage shipPackage = new ShipPackage();
										if(shipPackageList!=null&&!shipPackageList.isEmpty()){
											shipPackage = shipPackageList.get(0);
										}else{
											//ShipMethod shipMethod = this.shippingService.getShipVia(null, null, null,orderItem);
											//shipPackage.setship(shipMethod.getName());
											//shipPackage.setShipMethod(shipMethod.getMethodId());
											//shipPackage.setActualWeight(orderItem.getw)
											shipPackage.setStatus("Shipped");
											shipPackage.setSendBy(-33);
											shipPackage.setShipmentDate(new Date());
											shipPackage.setShippedTime(new Date());
											//shipPackage.setPackageNo("receive"+orderNo);
											shipPackage.setTrackingNo(tNo);
											shipPackage.setShipments(shipmentLine.getShipments());
											shipPackage.setWarehouseId(order.getWarehouseId());
											shipPackage.setInvoicedFlag("N");
											shipPackage.setCompanyId(order.getGsCoId());
											shipPackage.setCreationDate(new Date());
											shipPackage.setShipinfoSentFlag("Y");
											shipPackage.setCreatedBy("-33");
											shipPackage.setModifiedBy("-33");
											shipPackage.setModifyDate(new Date());
											this.shipPackageDao.save(shipPackage);
											this.shipPackageDao.flush();
										}
										ShipPackageLines shipPackageLine = new ShipPackageLines();
										
										shipPackageLine.setOrderNo(shipmentLine.getOrder().getOrderNo());
										shipPackageLine.setItemNo(shipmentLine.getItemNo());
										if(uom!=null&&uom.equals("BP")){
											//OrderItem orderItem = this.orderItemDao.getOrderItem(Integer.valueOf(orderNo), Integer.valueOf(itemNO));
											//tmp.setSize(orderItem.getSize());
											shipPackageLine.setQuantity(orderItem.getQuantity());
										}else{
											shipPackageLine.setQuantity(Integer.valueOf(qty));
										}
										shipPackageLine.setQtyUom(orderItem.getQtyUom());
										shipPackageLine.setSize(orderItem.getSize());
										if(shipPackageLine.getSize()==null||shipPackageLine.getSize()==0){
											shipPackageLine.setSize(1.0);
										}
										shipPackageLine.setSizeUom(orderItem.getSizeUom());
										shipPackageLine.setShipmentLines(shipmentLine);
										shipPackageLine.setShipPackages(shipPackage);
										shipPackageLine.setStatus("Shipped");
										shipPackageLine.setReservationId(0);
										shipPackageLine.setCreatedBy(-33);
										shipPackageLine.setCreationDate(new Date());
										shipPackageLine.setModifiedBy(-33);
										shipPackageLine.setModifyDate(new Date());
										this.shipPackageLineDao.save(shipPackageLine);
										shipmentLine.getShipments().setStatus("Partial Shipped");
										shipmentLine.getShipments().setShipDate(new Date());
										if(shipmentLine.getShipments()==null||shipmentLine.getShipments().equals("")){
											shipmentLine.getShipments().setTrackingNo(tNo);
										}else{
											
											if(shipmentLine.getShipments().getTrackingNo()==null||shipmentLine.getShipments().getTrackingNo().equals("")){
												shipmentLine.getShipments().setTrackingNo(tNo);
											}else{
												String isAddTrNo = "1";
												
												String[] trNos = shipmentLine.getShipments().getTrackingNo().split(",");
												for(int ns=0;ns<trNos.length;ns++){
													if(trNos[ns].equals(tNo)){
														isAddTrNo = "0";
													}
												}
												
												if(isAddTrNo.equals("1")){
													shipmentLine.getShipments().setTrackingNo(shipmentLine.getShipments().getTrackingNo()+","+tNo);
												}
											}
											
										}
										
										this.shipmentsDao.save(shipmentLine.getShipments());
										
										
										xmlStr1 +="<line><soNo>"+orderNo +"</soNo>";
										xmlStr1 +="<itemNo>"+itemNO+"</itemNo>";
										xmlStr1 +="<qty>"+qty+"</qty>";
										String company1 = "";
										if(order.getGsCoId()==1){
											company1 = "GSUS";
										}
										if(order.getGsCoId()==2){
											company1 = "GSNJ";
										}
										if(order.getGsCoId()==3){
											company1 = "GSPK";
										}
										if(order.getGsCoId()==4){
											company1 = "GSHK";
										}
										xmlStr1 +="<company>"+company1+"</company></line>"; 
										
										xmlStr2 += "<line>";
										xmlStr2 += "<soNo>"+orderNo+"</soNo>"; 
										String po = this.shipmentsService.getPOBySO(shipPackageLine.getOrderNo());
										xmlStr2 +="<po>"+po+"</po>";
										xmlStr2 += "<itemNo>"+itemNO+"</itemNo>"; 
										xmlStr2 += "<size>"+orderItem.getSize()+"</size>"; 
										xmlStr2 += "<qty>"+qty+"</qty>"; 
										xmlStr2 += "<lotNum></lotNum>"; 
										xmlStr2 += "<location></location>"; 
										xmlStr2 += "<company>"+company1+"</company>"; 
										xmlStr2 += "</line>";
										
										
										
										
										break;
									}
								}
							}
							continue;
						}
					}
					if (orderNo != null && itemNO != null
							&& qty != null
							&& tNo != null) {
						PurchaseOrder purchaseOrder = this.purchaseOrderService
								.getPurchaseOrderBySoId(Integer.valueOf(orderNo));
						if (purchaseOrder != null) {
							Integer poNo = purchaseOrder.getOrderNo();
							//List<PoReceivingTmp> tmpList = this.poReceiveingTmpDao
									//.searchPoTmp(poNo, Integer.valueOf(itemNO));
							
							tmp.setQty(1);
							OrderItem orderItem = this.orderItemDao.getOrderItem(Integer.valueOf(orderNo), Integer.valueOf(itemNO));
							if(uom!=null&&uom.equals("BP")){
								tmp.setSize(orderItem.getSize());
								if(tmp.getSize()==null||tmp.getSize()==0.0){
									tmp.setSize(1.0);
								}
								tmp.setSize(Arith.mul(tmp.getSize(), orderItem.getQuantity()));
								tmp.setQty(1);
							}else{
								
								
									Double sizeD = 0.0;
									if (qty!= null
											&& !qty.equals("")) {
										tmp.setQty(Integer.valueOf(qty));
										if(orderItem!=null&&orderItem.getSize()!=null){
											if(orderItem.getSize()==0){
												orderItem.setSize(1.0);
											}
											sizeD = Arith.mul(orderItem.getSize(), Double.valueOf(qty));
										}
										tmp.setSize(sizeD);
									} else {
										tmp.setQty(1);
										tmp.setSize(0.0);
									}
								
								
								if (tmp.getSize().equals(0.0)) {
									tmp.setSize(1.0);
								}
							}
							tmp.setPoNo(poNo);
							tmp.setPoLineNo(Integer.valueOf(itemNO));
							tmp.setReceivingTime(new Date());
							tmp.setTrackingNo(tNo);
							PurchaseOrderItem item = this.purchaseOrderItemService
									.findByNoAndItemNo(tmp.getPoNo(), tmp
											.getPoLineNo());
							if (item != null) {
								/*if (item.getQuantity().equals(1)) {
									tmp.setQty(1);
								}*/
								List<WorkOrder> workOrderList = this.workOrderDao.findByPO(Integer.valueOf(orderNo),tmp.getPoLineNo());
								if(workOrderList==null) {
									workOrderList = this.workOrderDao.findBySNAndSIN(Integer.valueOf(orderNo),tmp.getPoLineNo());
								}
								if(workOrderList!=null){
									for(WorkOrder workOrder : workOrderList){
										this.workOrderDao.getSession().evict(workOrder);
										workOrder.setInterfaceShipFlag("1");
										this.workOrderDao.save(workOrder);
									}
								}
								
								com.genscript.gsscm.serv.entity.Service service = this.servDao
								.findUniqueBy("catalogNo", orderItem
										.getCatalogNo());
								if(service.getShippable()==null||"Y".equals(service.getShippable().toUpperCase())){
									tmp.setQty(1);
									if(tmp.getSize()==null||tmp.getSize()==0){
										tmp.setSize(1.0);
									}
									this.poReceiveingTmpDao.save(tmp);
								}else{
									System.out.println("11111111111111111111111");
									User processUser = new User();
						            processUser.setUserId(order.getModifiedBy());
									List<ShipmentLine> shipmentLineList  = this.shipmentLinesDao.getShipmentLine1(orderItem.getOrderNo());
									if(shipmentLineList==null||shipmentLineList.isEmpty()){
										if(order.getStatus()!=null&&order.getStatus().equals("CC")){
											OrderProcessLog log = new OrderProcessLog();
								            log.setOrderNo(order.getOrderNo());
								            //log.setOrderItemId(item.getOrderItemId());
								            log.setCurrentStat(order.getStatus());
								            log.setPriorStat("SH");
								            log.setReason("Fedix return shipped.");
								            //log.setNote(dto.getStatusNote());
								            log.setProcessDate(new Date());
								            
								            log.setProcessedBy(processUser);
								            this.orderProcessLogDao.save(log);
											this.orderDao.updateOrderStatus("SH", order.getOrderNo().toString());
											
											
											System.out.println("222222222222222222222222");
										}
									}
									OrderProcessLog logItem = new OrderProcessLog();
									logItem.setOrderNo(order.getOrderNo());
									logItem.setOrderItemId(orderItem.getOrderItemId());
									logItem.setCurrentStat("SH");
									logItem.setReason("Fedix return shipped.");
									logItem.setPriorStat(orderItem.getStatus());
						            //log.setNote(dto.getStatusNote());
									logItem.setProcessDate(new Date());
						            logItem.setProcessedBy(processUser);
						            this.orderProcessLogDao.save(logItem);
									this.orderItemDao.updateOrderItemStatusByOrder1("SH", "CC",orderItem.getOrderItemId() );
							    }
								String isAdd = "1";
								if(purchaseOrderList != null && !purchaseOrderList.isEmpty()){
									for (PurchaseOrder porder : purchaseOrderList) {
										if(porder.getOrderNo().equals(purchaseOrder.getOrderNo())){
											isAdd = "0";
										}
									}
								}
								if(isAdd.equals("1")){
									purchaseOrder.setStatus("OP");
									this.purchaseOrderService.savePO(purchaseOrder);
									purchaseOrderList.add(purchaseOrder);
								}
								item.setFileDownloaded("1");
								this.purchaseOrderItemService.savePOitem(item);
							}
							// System.out.println("save");
						}
					}
				  }
				} else
					info += node.getNodeName() + ":"
							+ node.getChildNodes().item(0).getNodeValue()
							+ "\n";
				info += "\n";
			}
			if(!xmlStr1.equals("<?xml version=\"1.0\" encoding=\"utf-8\" ?><Receive><Package>")){
				xmlStr1 += "</Package></Receive>";
				this.setInfoStringToXML(xmlStr1, "/tmp/received", "received/");
			}  
			  
			if(!xmlStr2.equals(xmlStr2Is)){
				xmlStr2 +="</Package>";
				xmlStr2 +="</Ship>";
				this.setInfoStringToXML(xmlStr2, "/tmp/shipped", "shipped/");
			}
			/*if (purchaseOrderList != null && !purchaseOrderList.isEmpty()) {
				for (PurchaseOrder order : purchaseOrderList) {
					if (order.getOrderNo() != null
							&& order.getSrcSoNo() != null) {
						//Integer orderNo = order.getOrderNo();
						// order.setOrderNo(null);
						this.purchaseOrderService.savePO(order);
						
						 * if(itemList!=null&&!itemList.isEmpty()){
						 * for(PurchaseOrderItem item:itemList){
						 * if(item.getOrderNo().equals(orderNo)){
						 * item.setOrderNo(order.getOrderNo());
						 * //this.purchaseOrderItemService.savePOitem(item); }
						 * 
						 * } }
						 
					}

				}
			}*/
			if (itemList != null && !itemList.isEmpty()) {
				for (PurchaseOrderItem item : itemList) {
					if (item.getOrderNo() != null && item.getItemNo() != null) {
						this.purchaseOrderItemService.savePOitem(item);
					}

				}
			}
			return info;
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
		}
		return null;
	}
	
	public boolean setInfoStringToXML(String xmlStr,String localPath, String remotePath){
		String xmlName;
		try {
			FileUtil fu= new FileUtil() ;
			File folder = new File(localPath);
			if(!folder.exists()){
				
				fu.createFolder(localPath);
			}
			xmlName = SessionUtil.generateTempId();
			File file = new File(localPath+"/"+xmlName+".xml");
			
			StringToXMLUtil.strChangeXML(xmlStr, localPath, xmlName+".xml");
			file = new File(localPath+"/"+xmlName+".xml");
			if(!file.exists()){
				return false;
			}
			boolean b = false;
			Integer i = 0;
			while(!b){
				FtpClient.getInstance().setWorkDirectory(remotePath);
				b = FtpClient.getInstance().upload(localPath+"/", xmlName + ".xml", xmlName + ".xml");
				if(!b){
					System.out.println("updae false.");
					i++;
					if(i>1){
						b=true;
						StringToXMLUtil.strChangeXML(xmlStr, localPath, xmlName+"bak.xml");
					}
				}
			}
			
		}  catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
			return false;
		}
		return true;
	}
	
	public void setInfoXML(Integer soNo,String status,String localPath, String remotePath) {
		/**
		 * @param args
		 *            the command line arguments
		 */
		try {
			OrderMain order = this.orderDao.getById(soNo);
			String company = "";
			if(order!=null){
				Customer customer = this.customerDao.getById(order.getCustNo());
				if(customer!=null){
					company = billTerritoryDao.getAccountCode(customer.getCountry(), customer.getState(), customer.getZipCode());
				}
			}
			if(company!=null&&!company.equals("")){
				company = "GS"+company;
			}else{
				company = "GSUS";
			}
			DocumentBuilderFactory factory2 = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder2 = factory2.newDocumentBuilder();
			Document doc = builder2.newDocument();
			Element rootElement = doc.createElement("Received");
			Element soNoChildElement = doc.createElement("soNo");
			Text soNoTextNode = doc.createTextNode(soNo.toString());
			Element statusChildElement = doc.createElement("status");
			Text statusTextNode = doc.createTextNode(status);
			Element companyChildElement = doc.createElement("company");
			Text companyTextNode = doc.createTextNode(company);
			doc.appendChild(rootElement);
			rootElement.appendChild(soNoChildElement);
			soNoChildElement.appendChild(soNoTextNode);
			rootElement.appendChild(statusChildElement);
			statusChildElement.appendChild(statusTextNode);
			rootElement.appendChild(companyChildElement);
			companyChildElement.appendChild(companyTextNode);
			// rootElement.setAttribute("font", "12");
			/**
			 * 目前DOM API并不支持把DOM树写到输出流，我们借助于XML格式页转换（XSLT）
			 */
			Transformer t = TransformerFactory.newInstance().newTransformer();
			/*t.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "......");
			t.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "**********");
			t.setOutputProperty(OutputKeys.INDENT, "yes");*/
			String xmlName = SessionUtil.generateTempId();
			t.transform(new DOMSource(doc), new StreamResult(
					new FileOutputStream(new File(localPath + "/" + xmlName
							+ ".xml"))));
			System.out.println("localPath="+localPath+"/"+xmlName);
			//this.upload(remotePath, localPath + "/" + xmlName + ".xml");
			
			boolean b = false;
			while(!b){
				FtpClient.getInstance().setWorkDirectory(remotePath);
				b = FtpClient.getInstance().upload(localPath+"/", xmlName + ".xml", xmlName + ".xml");
				if(!b){
					System.out.println("updae false.");
				}
			}
		}  catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}

				
				// TODO code application logic here
			

		//PrintWriter pw = new PrintWriter(System.out);
		

	}
	/*private String ip = "10.168.2.245";// ftp服务器的IP地址
	private String username = "Test";// 用户名
	private String password = "test";// 密码
	private int port = 21;// 默认端口
	private String localfilefullname = "";// 需要上传的目录,带绝对路径
	FtpClient ftpclient = null;
	OutputStream os = null;
	FileInputStream is = null;

	public ReadReceiveXmlService() {

	}

	public ReadReceiveXmlService(String ip, String username, String password,
			String localfilefullname) {
		this.ip = ip;
		this.username = username;
		this.password = password;
		this.localfilefullname = localfilefullname;
	}

	*//**
	 * 创建文件夹
	 * 
	 * @param dir
	 * @param ftpclient
	 * @throws exception
	 *//*
	private void createdir(String dir, FtpClient ftpclient) throws Exception {
		ftpclient.ascii();
		StringTokenizer s = new StringTokenizer(dir, "/"); // 
		s.countTokens();
		String pathname = "";
		while (s.hasMoreElements()) {
			pathname = pathname + "/" + (String) s.nextElement();
			try {
				ftpclient.sendServer("mkd " + pathname + "\r\n");// 如果服务器上有该目录，不会被创建
			} catch (Exception e) {
				e = null;
			}
			ftpclient.readServerResponse();
		}
		ftpclient.binary();
	}

	*//**
	 * 检查文件夹是否存在
	 * 
	 * @param dir
	 * @param ftpclient
	 * @return
	 *//*
	private boolean isdirexist(String dir, FtpClient ftpclient)
			throws Exception {
		try {
			ftpclient.cd(dir);
		} catch (Exception e) {
			// todo 自动生成 catch 块
			return false;
		}
		return true;
	}

	*//**
	 * ftp上传
	 * 
	 * @param localfilefullname
	 *            上传的源文件夹
	 * @return
	 *//*
	public boolean upload(String prefix, String localfilefullname)
			throws Exception {
		this.localfilefullname = localfilefullname;
		try {
			String savefilename = localfilefullname;
			// 新建一个ftp客户端连
			ftpclient = new FtpClient();
			ftpclient.openServer(this.ip, this.port);
			ftpclient.login(this.username, this.password);
			// 打开本地待长传的文件
			File file_in = new File(savefilename);
			processfile(prefix, file_in, ftpclient);
			if (is != null) {
				is.close();
			}
			if (os != null) {
				os.close();
			}
			if (ftpclient != null) {
				ftpclient.closeServer();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("exception e in ftp upload(): " + e.toString());
			return false;
		} finally {
			if (is != null) {
				is.close();
			}
			if (os != null) {
				os.close();
			}
			if (ftpclient != null) {
				ftpclient.closeServer();
			}
		}
	}

	*//**
	 * 上传文件,递归算法，如果是目录且服务器上不存在该目录则在服务器上创建该目录，一级一级往下找，找到目录下的文件则读取文件内容，上传到服务器上
	 * 
	 * @param source
	 * @param ftpclient
	 * @throws exception
	 *//*
	private void processfile(String prefix, File source, FtpClient ftpclient)
			throws Exception {
		if (source.exists()) {
			if (source.isDirectory()) {
				String path = prefix
						+ source.getPath()
								.substring(localfilefullname.length()).replace(
										"\\", "/");
				if (!isdirexist(path, ftpclient)) {
					createdir(path, ftpclient);
				}
				File sourcefile[] = source.listFiles();
				for (int i = 0; i < sourcefile.length; i++) {
					if (sourcefile[i].exists()) {
						if (sourcefile[i].isDirectory()) {
							this.processfile(prefix, sourcefile[i], ftpclient);
						} else {
							ftpclient.cd(cheangpath(prefix, sourcefile[i]
									.getPath()));
							ftpclient.binary();
							os = ftpclient.put(sourcefile[i].getName());
							byte[] bytes = new byte[1024];
							is = new FileInputStream(sourcefile[i]);
							// 开始复制
							int c;
							// 暂未考虑中途终止的情况
							while ((c = is.read(bytes)) != -1) {
								os.write(bytes, 0, c);
							}
							is.close();
							os.close();
						}
					}
				}
			} else {
				ftpclient.cd(cheangpath(prefix, source.getPath()));
				ftpclient.binary();
				os = ftpclient.put(source.getName());
				byte[] bytes = new byte[1024];
				is = new FileInputStream(source);
				// 开始复制
				int c;
				// 暂未考虑中途终止的情况
				while ((c = is.read(bytes)) != -1) {
					os.write(bytes, 0, c);
				}
				is.close();
				os.close();
			}
		} else {
			throw new Exception("此文件或文件夹[" + source.getName() + "]有误或不存在!");
		}
	}

	*//**
	 * 获取当前的ftp路径
	 * 
	 * @param path
	 * @return
	 *//*
	private String cheangpath(String prefix, String path) throws Exception {
		path = path.substring(localfilefullname.length()).replace("\\", "/");
		if ("".equals(path)) {
			path = "/";
		} else {
			path = path.substring(0, path.lastIndexOf("/") + 1);
		}
		path = prefix + path;
		return path;
	}

	*//**
	 * 递归算法，取得文件的字节大小
	 * 
	 * @param strName
	 *            文件名带绝对路径
	 * @return
	 *//*
	public Long getSize(String strName) {
		Long TotalSize = 0L;
		File f = new File(strName);
		if (f.isFile())
			return f.length();
		else {
			if (f.isDirectory()) {
				File[] contents = f.listFiles();
				for (int i = 0; i < contents.length; i++) {
					if (contents[i].isFile())
						TotalSize += contents[i].length();
					else {
						if (contents[i].isDirectory())
							TotalSize += getSize(contents[i].getPath());
					}
				}
			}
		}
		return TotalSize;
	}

	*//**
	 * 递归下载文件
	 * 
	 * @param localPath
	 * @param remotePath
	 *//*
	public void processdownload(String localPath, String remotePath) {
		FileOutputStream outStream = null;
		ArrayList list = null;
		try {
			list = getFileList(remotePath);
			ftpclient.binary();
			File temp = null;
			for (int i = 0; i < list.size(); i++) {
				// 如果是文件，则直接执行下载
				if (isFile(list.get(i).toString())) {
					ftpclient.cd(remotePath);
					ArrayList listfileName = getNameList(remotePath);
					for (int j = 0; j < listfileName.size(); j++) {
						temp = new File(localPath + File.separator
								+ listfileName.get(j).toString());
						outStream = new FileOutputStream(temp);
						TelnetInputStream is = ftpclient.get(listfileName
								.get(j).toString());
						byte[] bytes = new byte[1024];
						int c;
						// 暂未考虑中途终止的情况
						while ((c = is.read(bytes)) != -1) {
							outStream.write(bytes, 0, c);
						}
						is.close();
						outStream.close();
						System.out.println("成功下载文件：" + remotePath
								+ File.separator
								+ listfileName.get(j).toString());
						this
								.getInfoByTagName(localPath
										+ listfileName.get(j).toString(),
										"ShipDtl", 33);
						System.out.println("DELE " + remotePath
								+ File.separator
								+ listfileName.get(j).toString());
						ftpclient.sendServer("DELE " + remotePath
								+ File.separator
								+ listfileName.get(j).toString() + "\r\n ");
					}
				} else if (isDir(list.get(i).toString()))// 是目录
				{
					temp = new File(localPath + File.separator
							+ getFileName(list.get(i).toString()));
					temp.mkdirs();
					String newRemote = remotePath + File.separator
							+ getFileName(list.get(i).toString());
					processdownload(localPath + File.separator
							+ getFileName(list.get(i).toString()), newRemote);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (outStream != null) {
					outStream.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	*//**
	 * 返回文件夹或者文件的名称
	 * 
	 * @param line
	 * @return
	 *//*
	public String getFileName(String line) {
		String filename = (String) parseLine(line).get(8);
		return filename;
	}

	*//**
	 * 返回当前目录的文件名称
	 * 
	 * @return
	 * @throws IOException
	 *//*
	public ArrayList getNameList(String remotePath) throws IOException {
		BufferedReader dr = new BufferedReader(new InputStreamReader(ftpclient
				.nameList(remotePath)));
		ArrayList al = new ArrayList();
		String s = "";
		while ((s = dr.readLine()) != null) {
			System.out.println("filename:" + s);
			al.add(s);
		}
		return al;
	}

	*//**
	 * 返回当前目录的所有文件及文件夹
	 * 
	 * @return
	 * @throws IOException
	 *//*
	public ArrayList getFileList(String remotePath) throws IOException {
		ftpclient.cd(remotePath);
		BufferedReader dr = new BufferedReader(new InputStreamReader(ftpclient
				.list()));
		ArrayList al = new ArrayList();
		String s = "";
		while ((s = dr.readLine()) != null) {
			System.out.println("readLine:" + s);
			if ((!((String) parseLine(s).get(8)).equals("."))
					&& (!((String) parseLine(s).get(8)).equals(".."))) {
				al.add(s);
				System.out.println("s:" + s);
			}
		}
		return al;
	}

	*//**
	 * 判断一行文件信息是否为目录
	 * 
	 * @param line
	 * @return
	 *//*
	public boolean isDir(String line) {
		return ((String) parseLine(line).get(0)).indexOf("d") != -1;
	}

	public boolean isFile(String line) {
		return !isDir(line);
	}

	*//**
	 * 处理getFileList取得的行信息
	 * 
	 * @param line
	 * @return
	 *//*
	private ArrayList parseLine(String line) {
		ArrayList s1 = new ArrayList();
		StringTokenizer st = new StringTokenizer(line, " ");
		while (st.hasMoreTokens()) {
			s1.add(st.nextToken());
		}
		return s1;
	}

	*//**
	 * ftp下载文件
	 * 
	 * @param localPath
	 * @param remotePath
	 * @return
	 * @throws Exception
	 *//*
	public boolean download(String localPath, String remotePath)
			throws Exception {
		// this.localfilefullname = localfilefullname;
		try {
			// String savefilename = localfilefullname;
			// 新建一个ftp客户端连
			ftpclient = new FtpClient();
			ftpclient.openServer(this.ip, this.port);
			ftpclient.login(this.username, this.password);
			if (remotePath != null) {
				ftpclient.cd(remotePath);
			}
			processdownload(localPath, remotePath);
			if (is != null) {
				is.close();
			}
			if (os != null) {
				os.close();
			}
			if (ftpclient != null) {
				ftpclient.closeServer();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("exception e in ftp upload(): " + e.toString());
			return false;
		} finally {
			if (is != null) {
				is.close();
			}
			if (os != null) {
				os.close();
			}
			if (ftpclient != null) {
				ftpclient.closeServer();
			}
		}
	}

	public ReadReceiveXmlService(String filename) {
		File file = new File(filename);
		this.file = file;
	}

	*//**
	 * 
	 * @return the instance of Document
	 *//*
	public Document getDOM() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		Document document = null;
		try {
			db = dbf.newDocumentBuilder();
			document = db.parse(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return document;
	}

	*//**
	 * 
	 * @param tagName
	 * @return
	 *//*
	public String getInfoByTagName(String filename, String tagName,
			Integer userId) {
		try {
			// DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			File file = new File(filename);
			this.file = file;
			String info = "";
			Document document = this.getDOM();

			// 获取NodeName 为tagName的节点组
			NodeList nl = document.getElementsByTagName(tagName);
			List<PurchaseOrder> purchaseOrderList = new ArrayList<PurchaseOrder>();
			List<PurchaseOrderItem> itemList = new ArrayList<PurchaseOrderItem>();
			System.out.println("mapStart");
			for (int i = 0; i < nl.getLength(); i++) {
				System.out.println("map");
				Map<String, String> map = new HashMap<String, String>();
				info += tagName + "[+" + i + "+]" + "\n";
				Node node = nl.item(i);

				// 如果当前节点有子节点（这里 只考虑还有一级子节点的情况）
				if (node.hasChildNodes()) {

					NodeList list = node.getChildNodes();

					for (int j = 0; j < list.getLength(); j++) {

						Node childNode = list.item(j);
						
						 * 不加这个If语句会抛出 Exception in thread "main"
						 * java.lang.NullPointerException at
						 * ReadXML.getInfoByTagName(ReadXML.java:59) at
						 * Test.main(Test.java:17)
						 
						if (childNode.getFirstChild() != null) {
							System.out.println("mapLIst");
							map.put(childNode.getNodeName(), childNode
									.getFirstChild().getNodeValue());
							info += childNode.getNodeName() + ":"
									+ childNode.getFirstChild().getNodeValue()
									+ "\n";
						}
						
						 * 对getNodeValue()的过程彻底无语 调试这个地方的时候，在网上很容易找到了
						 * 在得到Value的时候必须在节点对象后面先调用getFirstChild
						 * ()或者getChildNodes().item(0) 原因很简单，但是不知道设计者问什么要这么设计
						 * 最近在看《Beyond JAVA》（本人很推荐）,确实，JAVA很多地方应该简化，API等，生产力很重要
						 * 当然不是只给“语法甜头”，而是从底层实现的角度来满足简化需要
						 

					}
					PoReceivingTmp tmp = new PoReceivingTmp();
					System.out.println("start");
					if (map.get("soNo") != null && map.get("soItemNo") != null
							&& map.get("qty") != null
							&& map.get("trackingNo") != null) {
						PurchaseOrder purchaseOrder = this.purchaseOrderService
								.getPurchaseOrderBySoId(Integer.valueOf(map
										.get("soNo")));
						if (purchaseOrder != null) {
							Integer poNo = purchaseOrder.getOrderNo();
							List<PoReceivingTmp> tmpList = this.poReceiveingTmpDao
									.searchPoTmp(poNo, Integer.valueOf(map
											.get("soItemNo")));
							tmp.setSize(Double.valueOf(0.0));
							if (tmpList != null && !tmpList.isEmpty()) {
								tmp = tmpList.get(0);
								tmp.setQty(tmp.getQty()
										+ Integer.valueOf(map.get("qty")));
							} else {
								tmp.setQty(Integer.valueOf(map.get("qty")));
							}
							if (tmp.getQty().equals(1)) {
								if (map.get("size") != null
										&& !map.get("size").equals("")) {
									tmp.setSize(Arith.add(Double.valueOf(map
											.get("size")), tmp.getSize()));
								} else {
									tmp.setSize(0.0);
								}
							}
							tmp.setPoNo(poNo);
							if (tmp.getSize().equals(0.0)) {
								tmp.setSize(1.0);
							}
							tmp.setPoLineNo(Integer
									.valueOf(map.get("soItemNo")));
							tmp.setReceivingTime(new Date());
							tmp.setTrackingNo(map.get("trackingNo"));
							PurchaseOrderItem item = this.purchaseOrderItemService
									.findByNoAndItemNo(tmp.getPoNo(), tmp
											.getPoLineNo());
							if (item != null) {
								if (item.getQuantity().equals(1)) {
									tmp.setQty(1);
								}
								this.poReceiveingTmpDao.save(tmp);
								purchaseOrder.setStatus("OP");
								this.purchaseOrderService.savePO(purchaseOrder);
							}
							// System.out.println("save");
						}
					}
					
					 * PurchaseOrderItem item = new PurchaseOrderItem();
					 * PurchaseOrder purchaseOrder = new PurchaseOrder();
					 * OrderMain orderMain = new OrderMain(); OrderItem
					 * orderItem = new OrderItem();
					 * if(map.get("Number01")!=null){
					 * 
					 * 
					 * 
					 * purchaseOrder.setSrcSoNo(Integer.valueOf(map.get("Number01"
					 * )));
					 * purchaseOrder.setOrderNo(Integer.valueOf(map.get("OrderNum"
					 * ))); if(map.get("Number02")!=null){
					 * item.setItemNo(Integer.valueOf(map.get("Number02")));
					 * System
					 * .out.println(purchaseOrder.getSrcSoNo()+"   "+item.
					 * getItemNo ()); orderItem =
					 * this.orderItemService.getOrderItem(purchaseOrder
					 * .getSrcSoNo(), item.getItemNo());
					 * item.setCreatedBy(userId); item.setCreationDate(new
					 * Date()); item.setModifiedBy(userId);
					 * item.setModifyDate(new Date()); if(orderItem!=null){
					 * item.setCatalogNo(orderItem.getCatalogNo());
					 * item.setName(orderItem.getName());
					 * item.setClsId(orderItem.getClsId());
					 * item.setCost(orderItem.getCost());
					 * item.setQtyUom(orderItem.getQtyUom());
					 * item.setQuantity(orderItem.getQuantity());
					 * item.setSize(orderItem.getSize());
					 * item.setType(orderItem.getType());
					 * item.setStatus(orderItem.getStatus());
					 * item.setSizeUom(orderItem.getSizeUom());
					 * item.setOrderNo(purchaseOrder.getOrderNo());
					 * itemList.add(item); } } orderMain =
					 * this.orderService.getOrder(purchaseOrder.getSrcSoNo());
					 * if(orderMain !=null){
					 * purchaseOrder.setAltOrderNo(orderMain.getAltOrderNo());
					 * purchaseOrder.setCompanyId(orderMain.getGsCoId());
					 * purchaseOrder.setExprDate(orderMain.getExprDate());
					 * purchaseOrder.setOrderDate(orderMain.getOrderDate());
					 * purchaseOrder.setOrderType(orderMain.getOrderType());
					 * purchaseOrder.setPriority(orderMain.getPriority());
					 * purchaseOrder
					 * .setPurchaseContact(orderMain.getSalesContact());
					 * purchaseOrder.setStatus(orderMain.getStatus());
					 * purchaseOrder
					 * .setSubTotal(orderMain.getSubTotal().doubleValue());
					 * purchaseOrder.setVendorNo(1);
					 * purchaseOrder.setWarehouseId(orderMain.getWarehouseId());
					 * purchaseOrder.setCurrency(orderMain.getOrderCurrency());
					 * } } purchaseOrder.setComment(null);
					 * purchaseOrder.setCreatedBy(userId);
					 * purchaseOrder.setCreationDate(new Date());
					 * purchaseOrder.setModifiedBy(userId);
					 * purchaseOrder.setModifyDate(new Date());
					 * 
					 * purchaseOrder.setExpectedDate(null);
					 * 
					 * 
					 * 
					 * if(purchaseOrderList!=null&&!purchaseOrderList.isEmpty()){
					 * int is = 0; for(PurchaseOrder porder:purchaseOrderList){
					 * if
					 * (porder.getOrderNo().equals(purchaseOrder.getOrderNo())){
					 * is = 1; } } if(is==0){
					 * purchaseOrderList.add(purchaseOrder); } }else{
					 * purchaseOrderList.add(purchaseOrder); }
					 

				} else
					info += node.getNodeName() + ":"
							+ node.getChildNodes().item(0).getNodeValue()
							+ "\n";
				info += "\n";
			}
			if (purchaseOrderList != null && !purchaseOrderList.isEmpty()) {
				for (PurchaseOrder order : purchaseOrderList) {
					if (order.getOrderNo() != null
							&& order.getSrcSoNo() != null) {
						Integer orderNo = order.getOrderNo();
						// order.setOrderNo(null);
						this.purchaseOrderService.savePO(order);
						
						 * if(itemList!=null&&!itemList.isEmpty()){
						 * for(PurchaseOrderItem item:itemList){
						 * if(item.getOrderNo().equals(orderNo)){
						 * item.setOrderNo(order.getOrderNo());
						 * //this.purchaseOrderItemService.savePOitem(item); }
						 * 
						 * } }
						 
					}

				}
			}
			if (itemList != null && !itemList.isEmpty()) {
				for (PurchaseOrderItem item : itemList) {
					if (item.getOrderNo() != null && item.getItemNo() != null) {
						this.purchaseOrderItemService.savePOitem(item);
					}

				}
			}
			return info;
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
		}
		return null;
	}

	public void setInfoXML(Integer soNo,String status,String localPath, String remotePath) {
		*//**
		 * @param args
		 *            the command line arguments
		 *//*
		try {
			DocumentBuilderFactory factory2 = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder2 = factory2.newDocumentBuilder();
			Document doc= builder2.newDocument();
			Element rootElement = doc.createElement("Received");
			   Element soNoChildElement = doc.createElement("soNo");
			   Text soNoTextNode= doc.createTextNode(soNo.toString());
			   Element statusChildElement = doc.createElement("status");
			   Text statusTextNode= doc.createTextNode(status);
			   doc.appendChild(rootElement);
			   rootElement.appendChild(soNoChildElement);
			   soNoChildElement.appendChild(soNoTextNode);
			   rootElement.appendChild(statusChildElement);
			   statusChildElement.appendChild(statusTextNode);
			   //rootElement.setAttribute("font", "12");
			   *//**
			    * 目前DOM API并不支持把DOM树写到输出流，我们借助于XML格式页转换（XSLT）
			    *//*
			   Transformer t = TransformerFactory.newInstance().newTransformer();
			   t.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "......");
			   t.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "**********");
			   t.setOutputProperty(OutputKeys.INDENT, "yes");
			   String xmlName = SessionUtil.generateTempId();
			   t.transform(new DOMSource(doc), new StreamResult(new FileOutputStream(new File(localPath+"/"+xmlName+".xml"))));
			   this.upload(remotePath, localPath+"/"+xmlName+".xml");
			  }catch(Exception e){
			   e.printStackTrace();
			  }

				
				// TODO code application logic here
			
		
		

		//PrintWriter pw = new PrintWriter(System.out);
		

	}
*/
}
