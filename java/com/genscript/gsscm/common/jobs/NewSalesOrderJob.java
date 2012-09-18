package com.genscript.gsscm.common.jobs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.genscript.gsscm.common.FileService;
import com.genscript.gsscm.common.MimeMailService;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.events.CancelOrderEvent;
import com.genscript.gsscm.common.events.NewSalesOrderEvent;
import com.genscript.gsscm.common.util.FtpClient;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.epicorwebservice.service.SalesOrderService;
import com.genscript.gsscm.order.dao.MfgOrderDao;
import com.genscript.gsscm.order.dto.OrderItemDTO;
import com.genscript.gsscm.order.entity.MfgOrder;
import com.genscript.gsscm.order.entity.OrderMain;
import com.genscript.gsscm.order.service.OrderService;
import com.genscript.gsscm.purchase.dao.PurchaseOrderDao;
import com.genscript.gsscm.purchase.entity.PurchaseOrder;
import com.genscript.gsscm.purchase.service.PurchaseOrderService;
import com.genscript.gsscm.ws.WSException;

@Component
public class NewSalesOrderJob implements ApplicationListener<ApplicationEvent>{

	private static Logger logger = LoggerFactory.getLogger(NewSalesOrderJob.class);
	@Autowired
	private OrderService orderService;
//	@Autowired
//	private ErpSalesOrderService erpSalesOrderService;
	@Autowired
	private FileService fileService;
	@Autowired
	private SalesOrderService salesOrderService;
	@Autowired
	private MimeMailService mimeMailService;
	@Autowired
	private PurchaseOrderService purchaseOrderService;
	@Autowired
	private PurchaseOrderDao purchaseOrderDao;
	@Autowired
	private MfgOrderDao mfgOrderDao;
	
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		logger.debug("Received a NewSalesOrderEvent");
		if(event instanceof NewSalesOrderEvent){
			this.execute((NewSalesOrderEvent)event);
		} else
		if(event instanceof CancelOrderEvent){
			this.execute((CancelOrderEvent)event);
		}
		
	}
	@SuppressWarnings("unchecked")
	public void execute(NewSalesOrderEvent event){
		OrderMain orderMain = event.getOrderMain();
		Integer orderNo = orderMain.getOrderNo();
		String sessOrderNo = orderNo.toString();
		Map<String, OrderItemDTO> itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(
				SessionConstant.OrderItemList.value(), sessOrderNo);
		String poNumber = orderService.getOrderPoNumber(orderNo);
		long start = System.nanoTime();
		String path = generateOrderFileName().get(0);
		String name = generateOrderFileName().get(1);
		//erpSalesOrderService.createSaleOrder(source.getOrder(), itemMap, poNumber);
		salesOrderService.createXMLFile(path+name, orderMain, itemMap, poNumber, false);
		try {
			FtpClient.getInstance().openHandSwitch();
			if(!FtpClient.getInstance().ready()){
				FtpClient.getInstance().close();
			}else {
				FtpClient.getInstance().setWorkDirectory("sales_order/");
				FtpClient.getInstance().upload(path, name, name);
			}
			FtpClient.getInstance().close();
			FtpClient.getInstance().closeHandSwitch();
			
		} catch (IOException e) {
			e.printStackTrace();
			mimeMailService.sendMail("lifeng.gu@genscript.com,hong.jin@genscript.com,jianxian.wu@genscript.com,golfinux@gmail.com", "FTP upload XML file Error for Customer Confirm, Order No " + orderNo, WSException.getException(e), null, null);
		}
		long end = System.nanoTime();
		System.out.println("Invoke get Epicor service took " + (end - start) + " nanoseconds");
	}
	
	@SuppressWarnings("unchecked")
	public void execute(CancelOrderEvent event){
		OrderMain orderMain = event.getOrderMain();
		Integer orderNo = orderMain.getOrderNo();
		String sessOrderNo = orderNo.toString();
		Map<String, OrderItemDTO> itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(
				SessionConstant.OrderItemList.value(), sessOrderNo);
		String poNumber = orderService.getOrderPoNumber(orderNo);
		
		PurchaseOrder purchaseOrder = purchaseOrderService.getPurchaseOrderBySoId(orderNo);
		if(purchaseOrder != null){
			purchaseOrder.setStatus("CN");
			purchaseOrderDao.save(purchaseOrder);
		}
		List<MfgOrder> mfgList = mfgOrderDao.findBy("srcSoNo", orderNo);
		if(mfgList != null && mfgList.size() > 0){
			for(MfgOrder mfgOrder : mfgList){
				mfgOrder.setStatus("CN");
				mfgOrderDao.save(mfgOrder);
			}
		}
		long start = System.nanoTime();
		String path = generateOrderFileName().get(0);
		String name = generateOrderFileName().get(1);
		//erpSalesOrderService.createSaleOrder(source.getOrder(), itemMap, poNumber);
		salesOrderService.createXMLFile(path+name, orderMain, itemMap, poNumber, true);
		try {
			FtpClient.getInstance().setWorkDirectory("sales_order/");
			FtpClient.getInstance().upload(path, name, name);
		} catch (IOException e) {
			e.printStackTrace();
			mimeMailService.sendMail("lifeng.gu@genscript.com,hong.jin@genscript.com,jianxian.wu@genscript.com,golfinux@gmail.com", "FTP upload XML file Error for Cancel Order, Order No " + orderNo, WSException.getException(e), null, null);
		}
		long end = System.nanoTime();
		System.out.println("Invoke get Epicor service took " + (end - start) + " nanoseconds");
	}
	
	private List<String> generateOrderFileName() {
		List<String> list = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		String baseTmpDir = fileService.getUploadTmpPath();
		list.add(baseTmpDir);
		list.add(sb.append(System.currentTimeMillis())
				.append(".").append("xml").toString());
		return list;
	}
}
