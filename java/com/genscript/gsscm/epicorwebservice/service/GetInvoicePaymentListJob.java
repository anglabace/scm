package com.genscript.gsscm.epicorwebservice.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.gsscm.accounting.entity.ArInvoice;
import com.genscript.gsscm.accounting.service.ArInvoiceMassageService;
import com.genscript.gsscm.customer.dao.CustomerDao;
import com.genscript.gsscm.customer.entity.Customer;
import com.genscript.gsscm.epicorwebservice.stub.invoiceService.ArrayOfInventoryQtyAdjItem;
import com.genscript.gsscm.epicorwebservice.stub.invoiceService.ArrayOfInvoice;
import com.genscript.gsscm.epicorwebservice.stub.invoiceService.ArrayOfPartItem;
import com.genscript.gsscm.epicorwebservice.stub.invoiceService.InventoryQtyAdjItem;
import com.genscript.gsscm.epicorwebservice.stub.invoiceService.Invoice;
import com.genscript.gsscm.epicorwebservice.stub.invoiceService.PartItem;
import com.genscript.gsscm.epicorwebservice.stub.invoiceService.Service;
import com.genscript.gsscm.epicorwebservice.stub.invoiceService.ServiceSoap;
import com.genscript.gsscm.order.dao.OrderDao;
import com.genscript.gsscm.order.entity.OrderMain;
import com.genscript.gsscm.system.dao.CompanyDao;
import com.genscript.gsscm.common.util.DateUtils;
public class GetInvoicePaymentListJob {

	@Autowired
	private ArInvoiceMassageService arInvoiceMassageService;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private OrderDao orderDao;
	
	private CompanyDao companyDao;
	public void execute(){
		try{
		Service ss = new Service();
		ServiceSoap port = ss.getServiceSoap();
		// Date d =new Date();
		ArrayOfInvoice getInvoiceList = port.getInvoiceList("");
		ArInvoice arInvoice = null;
//		System.out.println("getInvoiceList.size()=================="
//				+ getInvoiceList.getInvoice().size());
		if (getInvoiceList != null && getInvoiceList.getInvoice().size() > 0) {
			System.out.println("有开票信息");
			List<Invoice> al = new ArrayList<Invoice>();
			al = getInvoiceList.getInvoice();
			for (int i = 0; i < al.size(); i++) {
				// 获取对象
					Invoice invoice = (Invoice) al.get(i);
					// 创建对象
					arInvoice = new ArInvoice();
					if (invoice != null && !"".equals(invoice)) {
						//判断
						OrderMain orderMail=orderDao.findByOrderNo(invoice.getScmOrderNum());
						Customer customer=customerDao.findByCustNo(Integer.parseInt(invoice.getCustID()));
						if(orderMail!=null && customer!=null){							
//							System.out
//							.println("invoice.getInvoiceNum()============"
//									+ invoice.getInvoiceNum());
//					System.out
//							.println("invoice.getOrderNum()=============="
//									+ invoice.getOrderNum());
//					System.out
//							.println("invoice.getCompany()==============="
//									+ invoice.getCompany());
//					System.out
//							.println("invoice.getPONum()================="
//									+ invoice.getPONum());
//					System.out
//							.println("invoice.getTermsCode()============="
//									+ invoice.getTermsCode());
//					System.out
//							.println("invoice.getInvoiceDate()==========="
//									+ invoice.getInvoiceDate());
//					System.out
//							.println("invoice.getInvoiceAmt()============"
//									+ invoice.getInvoiceAmt());
//					System.out
//							.println("invoice.getInvoiceBal()============"
//									+ invoice.getInvoiceBal());
//					System.out
//							.println("invoice.getCurrencyCode()=========="
//									+ invoice.getCurrencyCode());
//					System.out.println("invoice.getScmOrderNum()=========="
//							+ invoice.getScmOrderNum());
//					System.out.println("invoice.getChangeDate()=========="
//							+ invoice.getChangeDate());
//					System.out.println("invoice.getDueDate()=========="
//							+ invoice.getDueDate());
//					System.out.println("invoice.getCustNum()=========="
//							+ invoice.getCustID());
							arInvoice.setInvoiceNo(String.valueOf(invoice.getInvoiceNum()));
							arInvoice.setOrderNo(invoice.getScmOrderNum());
							arInvoice.setInvoiceDate(DateUitl(invoice.getInvoiceDate()));
							arInvoice.setBalance(invoice.getInvoiceBal().floatValue());
							arInvoice.setExprDate(DateUitl(invoice.getDueDate()));
							arInvoice.setModifyDate(DateUitl(invoice.getChangeDate()));
							arInvoice.setSymbol(invoice.getCurrencyCode());
							arInvoice.setTotalAmount(invoice.getInvoiceAmt().floatValue());
							arInvoice.setCurrency(invoice.getCurrencyCode());
							Integer companyId=arInvoiceMassageService.getCompanyId(invoice.getCompany());
							if(companyId==null){
								arInvoice.setCompanyId(1);
							}else{
								arInvoice.setCompanyId(companyId);
							}
							arInvoice.setInvoiceType("");
							arInvoice.setCreatedBy(-1);
							arInvoice.setCreationDate(new Date());
							arInvoice.setModifiedBy(-1);							
							arInvoice.setSubTotal(0f);
							arInvoice.setTax(0f);
							arInvoice.setDiscount(0f);
							arInvoice.setShipping(0f);
							arInvoice.setPrintedFlag("N");
							arInvoice.setCustNo(Integer.parseInt(invoice.getCustID()));
							arInvoiceMassageService.getAddOrUpdate(arInvoice);
						}
					}
				}
		} else {
			System.out.println("没有开票信息");
		}
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	/**
	 * XMLGregorianCalendar转换Date
	 * 
	 * @param dateType
	 * @return
	 * @throws Exception
	 */
	
	public static Date DateUitl(XMLGregorianCalendar dateType) throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.set(dateType.getYear(), dateType.getMonth()-1, dateType.getDay(),0,0,0);
		Date birthday = calendar.getTime();
		return birthday;
	}
	
	public ArrayOfInventoryQtyAdjItem searchInventoryQtyAdjItem(List<PartItem> partItemList){
		ArrayOfPartItem partNumList = new ArrayOfPartItem();
		if(partItemList!=null&&!partItemList.isEmpty()){
			partNumList.setPartItem(partItemList);
		}else{
			return null;
		}
		Service erpService = new Service();
		ServiceSoap port = erpService.getServiceSoap();
		// Date d =new Date();
		ArrayOfInventoryQtyAdjItem searchInventoyQtyAdjItem = port.getInventoryQtyAdjList(partNumList);
		return searchInventoyQtyAdjItem;
	}

}
