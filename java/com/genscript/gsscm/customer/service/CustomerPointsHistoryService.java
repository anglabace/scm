package com.genscript.gsscm.customer.service;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang.xwork.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.InputSource;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.common.ExceptionOut;
import com.genscript.gsscm.common.util.FtpClient;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.StringUtil;
import com.genscript.gsscm.common.zip.FileUtil;
import com.genscript.gsscm.customer.dao.CustomerDao;
import com.genscript.gsscm.customer.dao.CustomerPointsHistoryDao;
import com.genscript.gsscm.customer.dao.GiftCardDao;
import com.genscript.gsscm.customer.dto.CustPointHistoryDTO;
import com.genscript.gsscm.customer.dto.PaymentDTO;
import com.genscript.gsscm.customer.entity.Customer;
import com.genscript.gsscm.customer.entity.CustomerBean;
import com.genscript.gsscm.customer.entity.CustomerPointsHistory;
import com.genscript.gsscm.customer.entity.GiftCard;
import com.genscript.gsscm.order.dao.ExchRateByDateDao;
import com.genscript.gsscm.order.dao.OrderDao;
import com.genscript.gsscm.order.entity.OrderMain;
import com.genscript.gsscm.ws.WSException;

/**
 * 
 * @author zhangyong
 * 
 */
@Service
@Transactional
public class CustomerPointsHistoryService {
	@Autowired
	private CustomerPointsHistoryDao customerPointsHistoryDao;
	@Autowired
	private ExchRateByDateDao exchRateByDateDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private GiftCardDao giftCardDao;

	/**
	 * 货币兑换积分
	 * 
	 * @author zhangyong
	 * @param custNo
	 * @param transactionId
	 * @param currency
	 * @param paymentamount
	 */
	public void paymentToPoint(Integer custNo, Integer transactionId,
			String currency, Double paymentamount) throws Exception {
		Double exchCurrency = exchRateByDateDao.getCurrencyRate(currency,
				"USD", new Date());
		CustomerPointsHistory customerPointsHisory = new CustomerPointsHistory();
		customerPointsHisory.setCustNo(custNo);
		customerPointsHisory.setArTransactionId(transactionId);
		customerPointsHisory.setIssueDate(new Date());
		customerPointsHisory.setPoints((int) (exchCurrency * paymentamount));
		customerPointsHistoryDao.save(customerPointsHisory);
	}

	/**
	 * 客户付过款的是否兑换成积分(没有就兑换)
	 */
	public boolean paymentToPoint(List<PaymentDTO> paymentList) {
		for (PaymentDTO payment : paymentList) {
			// Customer customer = customerDao.getById(payment.getCustNo());
			// #rule1
			// next if($position[0][0] !~ /End User|P\.I\./i);
			// next if($position[0][2] =~ /vwr|funakoshi|Genecode/i);
			// next if($position[0][3] =~ /funakoshi|Genecode/i);
			if (payment.getPayment() >= 1) {
				Integer points = payment.getPayment().intValue();
				CustomerPointsHistory customerPointsHisory = new CustomerPointsHistory();
				customerPointsHisory.setCustNo(payment.getCustNo());
				customerPointsHisory
						.setOrderNo(payment.getOrderNo() != null ? payment
								.getOrderNo() : null);
				customerPointsHisory.setIssueDate(new Date());
				customerPointsHisory.setPoints(points);
				customerPointsHistoryDao.save(customerPointsHisory);
			}

			// #rule2
			// #pay date between 2009-11-20 and 2009-12-31
			// #product order > 500 and service order > 2000
		}

		return true;

	}

	/**
	 * 给客户添加积分
	 */
	public void addPointsToCust(Integer points, Integer custNo, Integer orderNo) {
		CustomerPointsHistory customerPointsHistory = new CustomerPointsHistory();
		customerPointsHistory.setCustNo(custNo);
		customerPointsHistory.setOrderNo(orderNo);
		customerPointsHistory.setPoints(points);
		customerPointsHistory.setIssueDate(new Date());
		customerPointsHistoryDao.save(customerPointsHistory);
	}

	/**
	 * 获得最大的payment_id
	 */
	public Integer getMaxTransactionId() {
		return customerPointsHistoryDao.getMaxTransactionId();
	}

	/**
	 * 从服务器上下载客户付款记录文件并分析
	 */
	public List<PaymentDTO> getPaymentList(String localPath, String remotePath) {
		List<PaymentDTO> list = new ArrayList<PaymentDTO>();
		try {
			FtpClient.getInstance().setWorkDirectory(remotePath);
			String[] fileNames = FtpClient.getInstance().listNames();
			FileUtil fu = new FileUtil();
			File folder = new File(localPath);
			if (!folder.exists()) {
				fu.createFolder(localPath);
			}
			if (fileNames != null && fileNames.length > 0) {
				for (String fileName : fileNames) {
					FtpClient.getInstance().setWorkDirectory(remotePath);
					if (FtpClient.getInstance().download(localPath, fileName)) {
						File file = new File(localPath + fileName);
						Map<String, String> map = parseString(localPath
								+ fileName);
						if (map != null) {
							Set<Entry<String, String>> set = map.entrySet();
							List<String> custNoList = new LinkedList<String>();
							List<String> paymentList = new LinkedList<String>();
							List<String> orderNoList = new LinkedList<String>();
							for (Entry<String, String> entry : set) {
								if (entry.getKey().startsWith("custNo")) {
									custNoList.add(entry.getValue());
								}
								if (entry.getKey().startsWith("payment")) {
									paymentList.add(entry.getValue());
								}
								if (entry.getKey().startsWith("orderNo")) {
									orderNoList.add(entry.getValue());
								}
							}
							for (int i = 0; i < custNoList.size(); i++) {
								if (StringUtils.isEmpty(custNoList.get(i))
										|| !StringUtil.isNumeric(custNoList
												.get(i))
										|| StringUtils.isEmpty(paymentList
												.get(i))) {
									continue;
								}
								PaymentDTO paymentDTO = new PaymentDTO();
								paymentDTO.setCustNo(Integer
										.parseInt(custNoList.get(i)));
								paymentDTO.setPayment(Double
										.parseDouble(paymentList.get(i)));
								paymentDTO
										.setOrderNo(StringUtils
												.isNotEmpty(orderNoList.get(i)) ? Integer
												.parseInt(orderNoList.get(i))
												: null);
								list.add(paymentDTO);
							}

						}
						FtpClient.getInstance().setWorkDirectory(remotePath);
						FtpClient.getInstance()
								.removeFile(remotePath, fileName);
						file.deleteOnExit();
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	private Map<String, String> parseString(String fileName) {
		File inputXml = new File(fileName);
		SAXReader saxReader = new SAXReader();
		Map<String, String> map = new LinkedHashMap<String, String>();
		try {
			Document document = saxReader.read(inputXml);
			Element root = document.getRootElement();
			Iterator it = root.elementIterator();
			int i = 0;
			while (it.hasNext()) {
				Element employee = (Element) it.next();
				recursion(map, employee, i);
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	private void recursion(Map<String, String> map, Element node, int i) {
		Iterator it = node.elementIterator();
		if (!it.hasNext()) {
			map.put(node.getName() + i, node.getText());
		} else {
			while (it.hasNext()) {
				Element employee = (Element) it.next();
				recursion(map, employee, i);
			}
		}

	}

	public Page<CustomerPointsHistory> searchpointhistorylist(
			Page<CustomerPointsHistory> page, List<PropertyFilter> filters) {
		Map<String, Object> filterMap = new HashMap<String, Object>();
		if (filters != null && filters.size() > 0) {
			for (int i = 0; i < filters.size(); i++) {
				String filterName = filters.get(i).getPropertyName();
				Object filterValue = filters.get(i).getPropertyValue();
				if (("custNo").equals(filterName)) {
					filterMap.put("custNo", filterValue);
				} else {
					filterMap.put(filterName, filterValue);
				}
			}
		}
		Page<CustomerPointsHistory> customerPointsHistoryList = this.customerPointsHistoryDao
				.searchpointhistoryList(page, filterMap);
		return customerPointsHistoryList;
	}

	public Boolean updateByCustNoandcardId(Integer id, String custNo) {
		String sql = "update `customer`.`customer_points_history` set gift_card_id="
				+ id + " where id= " + id + " and cust_no= '" + custNo + "'";
		System.out.println(sql);
		int a = this.customerPointsHistoryDao.getSession().createSQLQuery(sql)
				.executeUpdate();
		if (a != -1) {
			return true;
		}
		return false;

	}
}
