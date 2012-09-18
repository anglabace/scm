package com.genscript.gsscm.epicorwebservice.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import com.genscript.gsscm.accounting.dao.ArInvoicePaymentDao;
import com.genscript.gsscm.accounting.entity.ArInvoicePayment;
import com.genscript.gsscm.accounting.service.ArInvoicePaymentService;
import com.genscript.gsscm.customer.dao.CustomerDao;
import com.genscript.gsscm.customer.entity.Customer;
import com.genscript.gsscm.epicorwebservice.stub.invoiceService.Payment;
import com.genscript.gsscm.epicorwebservice.stub.invoiceService.Service;
import com.genscript.gsscm.epicorwebservice.stub.invoiceService.ServiceSoap;
import com.genscript.gsscm.order.dao.OrderDao;
import com.genscript.gsscm.order.entity.OrderMain;

public class GetOrderPaymentListJob {
	@Autowired
	private ArInvoicePaymentService arInvoicePaymentService;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private CustomerDao customerDao;

	public void execute() {
		try {

			Service ss = new Service();
			ServiceSoap port = ss.getServiceSoap();
			// 日期类型转变 传空值 表示接受当天的数据、
			com.genscript.gsscm.epicorwebservice.stub.invoiceService.ArrayOfPayment getPaymentList_return = port.getPaymentList("");
			ArInvoicePayment arInvoicePayment = null;
			
			if (getPaymentList_return != null&& getPaymentList_return.getPayment().size() > 0) {
				Log.debug(" 有新的付款信息："+ getPaymentList_return.getPayment().size() + "");
				System.out.println(getPaymentList_return.getPayment());
				List<Payment> al = new ArrayList<Payment>();
				al = getPaymentList_return.getPayment();
				for (int i = 0; i < al.size(); i++) {
					arInvoicePayment = new ArInvoicePayment();
					Payment pp = (Payment) al.get(i);
					//判断custNo和orderNo是否存在
					OrderMain orderMain = orderDao.findByOrderNo(Integer.parseInt(pp.getCmsOrderNum().toString()));
					Customer customer = customerDao.findByCustNo(Integer.parseInt(pp.getCustId()));
					if (orderMain != null && customer != null) {
						//存入custNo
						arInvoicePayment.setCustNo(Integer.parseInt(pp.getCustId()));	
						arInvoicePayment.setBalance(String.valueOf(0f));
						arInvoicePayment.setAmount(pp.getTranAmt().toString());
						arInvoicePayment.setTransactionNo(String.valueOf(pp.getInvoiceNum()));
						arInvoicePayment.setTransactionType(pp.getTranType());
						arInvoicePayment.setOrderNo(String.valueOf(pp.getCmsOrderNum()));
						if (orderMain.getOrderCurrency() == null || "".equals(orderMain.getOrderCurrency())) {
								arInvoicePayment.setCurrency("USD");
							} else {
								arInvoicePayment.setCurrency(orderMain.getOrderCurrency());
							}					
						arInvoicePayment.setTransactionDate(GetInvoicePaymentListJob.DateUitl(pp.getTranDate()));					
						arInvoicePayment.setPaymentType("Standard");
						arInvoicePayment.setStatus("Completed");
						arInvoicePayment.setCreationDate(new Date());
						arInvoicePayment.setModifiedBy(-1);
						arInvoicePayment.setModifyDate(new Date());
						arInvoicePayment.setCreatedBy(-1);
						arInvoicePaymentService.AddOrUpdate(arInvoicePayment);
						Log.debug("insert into arInvoicePayment:"+ pp.getInvoiceNum()+ "------"+ pp.getCmsOrderNum());
						}
					}

			} else {
				Log.debug("没有付款信息");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("end for insert ...");
	}
}
