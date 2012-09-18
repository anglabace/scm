package com.genscript.gsscm.tools.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.common.util.DateUtils;
import com.genscript.gsscm.order.entity.OrderMain;
import com.genscript.gsscm.tools.dao.OrderFollowupDao;
import com.genscript.gsscm.tools.dto.OrderFollowupDTO;

@Service
@Transactional
public class OrderFollowupService {

	@Autowired
	private OrderFollowupDao orderFollowupdao;

	/*
	 * 根据页面的参数 和选择的显示的 栏目 组装dto 
	 */
	public OrderFollowupDTO getOrderFollowupDate(Page<OrderFollowupDTO> page,
			OrderFollowupDTO dto, boolean isExport, String sortBy,
			ArrayList<String> columnlist) {
		ArrayList<String> columnList = new ArrayList<String>();

		@SuppressWarnings("rawtypes")
		List countList = new ArrayList();
		@SuppressWarnings("rawtypes")
		List sql_shiping_dateList = new ArrayList();
		@SuppressWarnings("rawtypes")
		List locationList = new ArrayList();
		@SuppressWarnings("rawtypes")
		List target_dateList = new ArrayList();
		@SuppressWarnings("rawtypes")
		List reduNumList = new ArrayList();
		@SuppressWarnings("rawtypes")
		List mainList = new ArrayList();
		@SuppressWarnings("rawtypes")
		List followups = new ArrayList();

		OrderFollowupDTO orderFollowupDTO = null;
		List<OrderFollowupDTO> allList = new ArrayList<OrderFollowupDTO>();
		String dateSql = "";
		String StatusSql = "";
		Long counts = 0L;
		String getallCount = "";
		String OrderSql = "";
		String sql_location = "";
		String orderNo = "";
		String sql_count = "";
		String Sql_numOfitems = "";
		String sql_target_date = "";
		String CustomerSql = "";
		String CountrySql = "";
		String pmanagerSql = "";
		String techsupportSql = "";
		String SmanagerSql = "";
		String sql = "";
		String EmailSql = "";
		String PrioritySql = "";
		String sql_shiping_date = "";
		String sql_follow1 = "";
		orderNo = dto.getOrderNo();
		String organizationSql = "";
		String criSql = "";

		for (int i = 0; i < columnlist.size(); i++) {
			String columnName = (String) columnlist.get(i);
			if ("".equals(columnName))
				continue;
			columnList.add(columnName.split(":")[1]);
		}
		dto.setColumnName(columnList);
		if (!"".equals(dto.getConfirmationDate())
				&& dto.getConfirmationDate() != null) {
			dateSql = getDateSql(dto,
					" `order`.`v_all_orders`.`customer_confirm_date`");
		}
		if (!"".equals(dto.getOrderStatus()) && dto.getOrderStatus() != null) {
			StatusSql = " and `order`.`v_all_orders`.`status` like '%"
					+ dto.getOrderStatus() + "%'";
		}
		if (!"".equals(orderNo) && orderNo != null) {
			OrderSql = " and `order`.`v_all_orders`.`order_no` = '" + orderNo
					+ "'";
		}

		if (!"".equals(dto.getOrgName()) && dto.getOrgName() != null) {
			organizationSql = " and `order`.`v_all_orders`.`organization` ="
					+ dto.getOrgName();
		}

		if (!"".equals(dto.getPriority()) && dto.getPriority() != null) {
			PrioritySql = " and `order`.`v_all_orders`.`priority` ='"
					+ dto.getPriority() + "'";
		}

		if (!"".equals(dto.getCustNo()) && dto.getCustNo() != null) {
			CustomerSql = " and `order`.`v_all_orders`.`cust_no` = '"
					+ dto.getCustNo() + "'";
		}

		if (!"".equals(dto.getEmail()) && dto.getEmail() != null) {
			EmailSql = " and `order`.`v_all_orders`.`email like` '%"
					+ dto.getEmail() + "%'";
		}

		if (!"".equals(dto.getCountry()) && dto.getCountry() != null) {
			EmailSql = " and `order`.`v_all_orders`.`country` = '"
					+ dto.getCountry() + "'";
		}

		if (!"".equals(dto.getProjectManager())
				&& dto.getProjectManager() != null) {
			pmanagerSql = " and `order`.`v_all_orders`.`project_manager_id` ='"
					+ dto.getProjectManager() + "'";
		}

		if (!"".equals(dto.getAccountManager())
				&& dto.getAccountManager() != null) {
			techsupportSql = " and `order`.`v_all_orders`.`tech_support_id` ='"
					+ dto.getAccountManager() + "'";
		}

		if (!"".equals(dto.getSalesManager()) && dto.getSalesManager() != null) {
			SmanagerSql = "and `order`.`v_all_orders`.`sales_contact_id` ='"
					+ dto.getSalesManager() + "'";
		}

		String followstatus = "";
		if (!"".equals(dto.getFollowupStatus())
				&& dto.getFollowupStatus() != null) {
			followstatus = "   	OR foll.status = '" + dto.getFollowupStatus()
					+ "'";
		}

		criSql = StatusSql + OrderSql + dateSql + organizationSql + PrioritySql
				+ CustomerSql + EmailSql + CountrySql + SmanagerSql
				+ techsupportSql + pmanagerSql;
		String[] str = null;
		String pstype = "";
		String pstypesql = "";
		pstype = dto.getProductServiceType();
		if (dto.getProductServiceType() != null
				&& !"".equals(dto.getProductServiceType())) {
			str = pstype.split(":");
			if (str != null) {
				pstypesql = "   and oi.type = '" + str[2].toString()
						+ "' and oi.name = '" + str[0].toString() + "'";
			}
		}

		sql = " select   `order`.`v_all_orders`.`order_no` ,`order`.`v_all_orders`.`customer_confirm_date`  ,"
				+ "`order`.`v_all_orders`.`status` ,`order`.`v_all_orders`.`email`  ,`order`.`v_all_orders`.`cust_no` ,`order`.`v_all_orders`.`priority` ,`order`.`v_all_orders`.`organization`  , CONCAT(`order`.`v_all_orders`.`first_name`,'',`order`.`v_all_orders`.`last_name`) as customerName,`order`.`v_all_orders`.`amount` ,`order`.`v_all_orders`.`tech_support`  , `order`.`v_all_orders`.`sales_contact`  ,`order`.`v_all_orders`.`project_manager`  ,   `order`.`payment_voucher`.`po_number`"
				+ "FROM `order`.`v_all_orders` "
				+ "JOIN "
				+ "(SELECT oi.order_no,oi.type,oi.cls_id    FROM `order`.`order_items` oi "
				+ " WHERE oi.order_item_id = (SELECT MIN(oii.order_item_id)  FROM `order`.`order_items` oii WHERE oi.order_no = oii.order_no  )"
				+ pstypesql
				+ " ) AS oiii "
				+ " ON (v_all_orders.order_no = oiii.order_no) "
				+ " LEFT JOIN"
				+ "  (SELECT t1.order_no,t1.status    FROM `order`.`order_followup` t1   "
				+ "WHERE id = (SELECT MAX(id) FROM `order`.`order_followup` t2 WHERE t1.order_no = t2.order_no) ) AS foll"
				+ " ON (v_all_orders.order_no = foll.order_no) "
				+ " LEFT JOIN  `order`.`payment_voucher` 	"
				+ " ON(`v_all_orders`.`order_no` = `payment_voucher`.`order_no`)     WHERE 1=1  "
				+ followstatus
				+ "  AND 1=1 "
				+ criSql
				+ " and `order`.`v_all_orders`.`status` IN('CC','VC','IP','PS')  ";

		if (sortBy != null && !"".equals(sortBy)) {
			sql += "order by " + sortBy + " desc ";
		}

		sql += "  LIMIT  " + page.getPageSize() * (page.getPageNo() - 1) + ", "
				+ page.getPageSize() * (page.getPageNo()) + "";

		System.out.println("sql====" + sql);
		mainList = orderFollowupdao.ExcuteSql(sql);
		if (mainList != null && mainList.size() > 0) {
			int n1 = mainList.size();
			for (int i = 0; i < n1; i++) {
				Object[] o1 = (Object[]) mainList.get(i);
				orderFollowupDTO = new OrderFollowupDTO();
				if (o1[0] != null && o1[0].toString() != null
						&& !"".equals(o1[0].toString())) {
					orderFollowupDTO.setOrderNo(o1[0].toString());
					String orderNo_now = o1[0].toString();
					sql_count = " select  count(item_no) as totalItems from order.order_items oii"
							+ " where oii.status <> 'CN' and oii.order_no  = '"
							+ orderNo_now + "'";

					sql_shiping_date = "select  sl.ship_date from shipping.shipment_lines sl  "
							+ "where  sl.line_id =(select max(ss.line_id)"
							+ " from shipping.shipment_lines ss  where  ss.order_no = '"
							+ orderNo_now + "') ";

					sql_target_date = " select   Max(oi.target_date) from order.order_items oi where   "
							+ "oi.status <>'CN' and   oi.order_no ='"
							+ orderNo_now + "'";

					Sql_numOfitems = " select  count(*) from  order.order_items where target_date < sysdate()"
							+ " and status not in('CN','SH')   and  order_no ='"
							+ orderNo_now + "'";

					sql_follow1 = "SELECT  STATUS, LEFT(message,10) ,MAX(followup_date) "
							+ "FROM order.order_followup WHERE order_no = "
							+ orderNo_now + " ";

					countList = orderFollowupdao.ExcuteSql(sql_count);
					sql_shiping_dateList = orderFollowupdao
							.ExcuteSql(sql_shiping_date);
					target_dateList = orderFollowupdao
							.ExcuteSql(sql_target_date);
					reduNumList = orderFollowupdao.ExcuteSql(Sql_numOfitems);
					followups = orderFollowupdao.ExcuteSql(sql_follow1);

					if (countList != null) {
						Long counts2 = Long.parseLong(countList.get(0)
								.toString());
						orderFollowupDTO.setNumofItems(counts2.toString());
					} else {
						orderFollowupDTO.setNumofItems("");
					}

					if (str != null) {
						if (str[2] != null && !"".equals(str[2])) {
							orderFollowupDTO.setProductServiceType(str[2]
									.toString());
						}
					}

					if (sql_shiping_dateList != null
							&& !"".equals(sql_shiping_dateList)) {
						if (sql_shiping_dateList.size() > 0) {
							if (sql_shiping_dateList.get(0) != null
									&& !"".equals(sql_shiping_dateList.get(0))) {
								orderFollowupDTO
										.setDeliveryDate(sql_shiping_dateList
												.get(0).toString());
							} else {
								orderFollowupDTO.setDeliveryDate("");
							}
						}

					}

					if (target_dateList != null) {
						if (target_dateList.get(0) != null
								&& !"".equals(target_dateList.get(0))) {
							orderFollowupDTO
									.setProductionTargetDate(target_dateList
											.get(0).toString());
							orderFollowupDTO
									.setGuaranteedDeliveryDate(target_dateList
											.get(0).toString());
						} else {
							orderFollowupDTO.setGuaranteedDeliveryDate("");
						}

					}
					if (reduNumList != null) {
						if (reduNumList.get(0) != null
								&& !"".equals(reduNumList.get(0))) {
							Long counts2 = Long.parseLong(reduNumList.get(0)
									.toString());
							orderFollowupDTO.setOverdueNumOfitems(counts2
									.toString());
						} else {
							orderFollowupDTO.setOverdueNumOfitems("");
						}
					}

					if (followups != null) {
						if (followups.get(0) != null) {
							Object[] o2 = (Object[]) followups.get(0);
							if (o2[1] != null && o2[2] != null && o2[0] != null) {
								String targets = o2[0].toString();
								String targets2 = o2[1].toString();
								String targets3 = o2[2].toString();
								orderFollowupDTO.setFollowupStatus(targets);
								orderFollowupDTO.setFollowupMessage(targets2);
								orderFollowupDTO.setFollowupDate(targets3);
							} else {
								orderFollowupDTO.setFollowupStatus("");
								orderFollowupDTO.setFollowupMessage("");
								orderFollowupDTO.setFollowupDate("");
							}
						}
					}
				}

				if (o1[1] != null && o1[1].toString() != null
						&& !"".equals(o1[1].toString())) {
					orderFollowupDTO.setConfirmationDate(o1[1].toString());
				} else {
					orderFollowupDTO.setConfirmationDate("");
				}

				if (o1[2] != null && o1[2].toString() != null
						&& !"".equals(o1[2].toString())) {
					orderFollowupDTO.setOrderStatus(o1[2].toString());
				} else {
					orderFollowupDTO.setOrderStatus("");
				}

				if (o1[3] != null && o1[3].toString() != null
						&& !"".equals(o1[3].toString())) {
					orderFollowupDTO.setMail(o1[3].toString());
				} else {
					orderFollowupDTO.setMail("");

				}

				if (o1[4] != null && o1[4].toString() != null
						&& !"".equals(o1[4].toString())) {
					orderFollowupDTO.setCustNo(o1[4].toString());
					sql_location = " select CONCAT(cut.country,'',cut.state) as Location from "
							+ "customer.customer cut where  cut.cust_no  = '"
							+ o1[4].toString() + "' ";
					locationList = orderFollowupdao.ExcuteSql(sql_location);
					if (locationList != null) {
						if (locationList.get(0) != null) {
							String locations = locationList.get(0).toString();
							orderFollowupDTO.setLocation(locations);
						} else {
							orderFollowupDTO.setLocation("");
						}
					}

				}

				if (o1[5] != null && o1[5].toString() != null
						&& !"".equals(o1[5].toString())) {
					orderFollowupDTO.setPriority(o1[5].toString());
				} else {
					orderFollowupDTO.setPriority("");

				}

				if (o1[6] != null && o1[6].toString() != null
						&& !"".equals(o1[6].toString())) {
					orderFollowupDTO.setOrganization(o1[6].toString());
				} else {
					orderFollowupDTO.setOrganization("");
				}

				if (o1[7] != null && o1[7].toString() != null
						&& !"".equals(o1[7].toString())) {
					orderFollowupDTO.setCustName(o1[7].toString());
				} else {
					orderFollowupDTO.setCustName("");
				}

				if (o1[8] != null && o1[8].toString() != null
						&& !"".equals(o1[8].toString())) {
					orderFollowupDTO.setOrderTotal(o1[8].toString());
				}

				if (o1[9] != null && o1[9].toString() != null
						&& !"".equals(o1[9].toString())) {
					orderFollowupDTO.setTam(o1[9].toString());
				}

				if (o1[10] != null && o1[10].toString() != null
						&& !"".equals(o1[10].toString())) {
					orderFollowupDTO.setSalesManage(o1[10].toString());
				}

				if (o1[11] != null && o1[11].toString() != null
						&& !"".equals(o1[11].toString())) {
					orderFollowupDTO.setProjectManager(o1[11].toString());
				}

				if (o1[12] != null && o1[12].toString() != null
						&& !"".equals(o1[12].toString())) {
					orderFollowupDTO.setPo(o1[12].toString());
				}

				allList.add(orderFollowupDTO);

			}

		}

		if (page.isAutoCount()) {
			getallCount = " select count(`order`.`v_all_orders`.`order_no`) "
					+ "   FROM `order`.`v_all_orders` "
					+ "JOIN "
					+ "(SELECT oi.order_no,oi.type,oi.cls_id    FROM `order`.`order_items` oi "
					+ " WHERE oi.order_item_id = (SELECT MIN(oii.order_item_id)  FROM `order`.`order_items` "
					+ "oii WHERE oi.order_no = oii.order_no  )"
					+ pstypesql
					+ " ) AS oiii "
					+ " ON (v_all_orders.order_no = oiii.order_no) "
					+ " LEFT JOIN"
					+ "  (SELECT t1.order_no,t1.status    FROM `order`.`order_followup` t1   "
					+ "WHERE id = (SELECT MAX(id) FROM `order`.`order_followup` t2 WHERE t1.order_no = t2.order_no) ) AS foll"
					+ " ON (v_all_orders.order_no = foll.order_no) "
					+ " LEFT JOIN  `order`.`payment_voucher` 	"
					+ " ON(`v_all_orders`.`order_no` = `payment_voucher`.`order_no`)     WHERE 1=1  "
					+ followstatus
					+ "  AND 1=1 "
					+ criSql
					+ " and `order`.`v_all_orders`.`status` IN('CC','VC','IP','PS')  ";

		}

		@SuppressWarnings("rawtypes")
		List countlist2 = orderFollowupdao.ExcuteSql(getallCount);
		if (countlist2 != null) {
			counts = Long.parseLong(countlist2.get(0).toString());
		}
		System.out.println(counts);
		page.setResult(allList);
		page.setPageNo(page.getPageNo());
		page.setTotalCount(Long.parseLong(counts + ""));
		page.setPageSize(15);

		dto.setReportData(allList);
		return dto;

	}

	@SuppressWarnings("rawtypes")
	public String getDateSql(OrderFollowupDTO dto, String dateCoulumn) {
		HashMap map = getDateFromTo(dto);
		if (map.get("fromDate") == null)
			return "";
		String sql = " and date(" + dateCoulumn + ") >= '"
				+ DateUtils.formatDate2Str((Date) map.get("fromDate"))
				+ "' and date(" + dateCoulumn + ") <= '"
				+ DateUtils.formatDate2Str((Date) map.get("toDate")) + "' ";
		return sql;
	}

	public HashMap<String, Date> getDateFromTo(OrderFollowupDTO dto) {
		String dataRange = dto.getConfirmationDate();
		Date fromDate = null;// 寮�鏃堕棿
		Date toDate = null;// 缁撴潫鏃堕棿
		if ("lWeek".equals(dataRange)) {
			fromDate = DateUtils.formatStr2Date(DateUtils.getPreviousMonday(),
					DateUtils.C_DATE_PATTON_DEFAULT);
			toDate = DateUtils.formatStr2Date(DateUtils.getPreviousSunday(),
					DateUtils.C_DATE_PATTON_DEFAULT);
		} else if ("week".equals(dataRange)) {
			fromDate = DateUtils.formatStr2Date(DateUtils.getCurrentMonday(),
					DateUtils.C_DATE_PATTON_DEFAULT);
			toDate = DateUtils.getWeekEndDay(new Date());
		} else if ("lMonth".equals(dataRange)) {
			fromDate = DateUtils.formatStr2Date(DateUtils.getLastMonth(),
					DateUtils.C_DATE_PATTON_DEFAULT);
			toDate = DateUtils.formatStr2Date(
					DateUtils.getLastDayOfMonth(fromDate),
					DateUtils.C_DATE_PATTON_DEFAULT);
		} else if ("month".equals(dataRange)) {
			fromDate = DateUtils.formatStr2Date(
					DateUtils.getFirstDayOfMonth(new Date()),
					DateUtils.C_DATE_PATTON_DEFAULT);
			toDate = DateUtils.formatStr2Date(
					DateUtils.getLastDayOfMonth(new Date()),
					DateUtils.C_DATE_PATTON_DEFAULT);
		} else if ("lQuarter".equals(dataRange)) {
			fromDate = DateUtils.getQuarterStartDay(DateUtils
					.defineMonthBefore2Date(new Date(), -3));
			toDate = DateUtils.getQuarterEndDay(DateUtils
					.defineMonthBefore2Date(new Date(), -3));
		} else if ("quarter".equals(dataRange)) {
			fromDate = DateUtils.getQuarterStartDay(new Date());
			toDate = DateUtils.getQuarterEndDay(new Date());
		} else if ("lYear".equals(dataRange)) {
			fromDate = DateUtils.getYearStartDay(DateUtils
					.defineMonthBefore2Date(new Date(), -12));
			toDate = DateUtils.getYearEndDay(DateUtils.defineMonthBefore2Date(
					new Date(), -12));
		} else if ("year".equals(dataRange)) {
			fromDate = DateUtils.getYearStartDay(new Date());
			toDate = DateUtils.getYearEndDay(new Date());
		} else if ("custom".equals(dataRange)) { // custom date
			fromDate = DateUtils
					.formatStr2Date(dto.getDataFrom(), "yyyy-MM-dd");
			toDate = DateUtils.formatStr2Date(dto.getDataTo(), "yyyy-MM-dd");
		}
		HashMap<String, Date> map = new HashMap<String, Date>();
		map.put("fromDate", fromDate);
		map.put("toDate", toDate);
		return map;
	}
 
	public void setFromToDate(OrderMain orderMain, int year, String perType,
			Object quote_dateObj) { 
		if ("day".equals(perType)) {
			String dateTemp = DateUtils.formatDate2Str((Date) quote_dateObj,
					"yyyy-MM-dd");
			orderMain.setFromDate(dateTemp);
			orderMain.setToDate(dateTemp);
			return;
		}
		// week, month, quarter
		Integer quote_date = (Integer) quote_dateObj;
		if ("week".equals(perType)) {
			Calendar ca = weekCalendar(year, quote_date);
			orderMain.setFromDate(DateUtils.formatDate2Str(
					DateUtils.calendar2Date(ca), "yyyy-MM-dd"));
			ca.add(Calendar.DAY_OF_WEEK, 6);
			orderMain.setToDate(DateUtils.formatDate2Str(ca.getTime(),
					"yyyy-MM-dd"));
		} else if ("month".equals(perType)) {
			String dateFromTemp = year + "-" + quote_date + "-01";
			String dateToTemp = year + "-" + quote_date + "-"
					+ DateUtils.findMaxDayInMonth(quote_date);
			orderMain.setFromDate(dateFromTemp);
			orderMain.setToDate(dateToTemp);
		} else if ("quarter".equals(perType)) {
			String fromDateTemp = year + "-" + 3 * quote_date + 2 + "-01";
			String toDateTemp = "";
			if (quote_date == 1 || quote_date == 4) {
				toDateTemp = year + "-" + 3 * quote_date + "-31";
			} else {
				toDateTemp = year + "-" + 3 * quote_date + "-01";
			}
			orderMain.setFromDate(fromDateTemp);
			orderMain.setToDate(toDateTemp);
		} else if ("year".equals(perType)) {
			orderMain.setFromDate(year + "-01-31");
			orderMain.setToDate(year + "-12-31");
		}
	}

	public Calendar weekCalendar(int year, int week) {
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DATE, 1);
		Calendar c1 = (GregorianCalendar) cal.clone();
		c1.add(Calendar.DATE, week * 7);
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(c1.getTime());
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
		return c;
	}

	public OrderFollowupDTO getOrderFollowupDate2Pdf(
			Page<OrderFollowupDTO> page, OrderFollowupDTO dto,
			boolean isExport, String sortBy, ArrayList<String> columnlist) {

		ArrayList<String> columnList = new ArrayList<String>(); 
		@SuppressWarnings("rawtypes")
		List countList = new ArrayList();
		@SuppressWarnings("rawtypes")
		List sql_shiping_dateList = new ArrayList();
		@SuppressWarnings("rawtypes")
		List locationList = new ArrayList();
		@SuppressWarnings("rawtypes")
		List target_dateList = new ArrayList();
		@SuppressWarnings("rawtypes")
		List reduNumList = new ArrayList();
		@SuppressWarnings("rawtypes")
		List mainList = new ArrayList();
		@SuppressWarnings("rawtypes")
		List followups = new ArrayList();

		OrderFollowupDTO orderFollowupDTO = null;
		List<OrderFollowupDTO> allList = new ArrayList<OrderFollowupDTO>();
		String dateSql = "";
		String StatusSql = ""; 
		String OrderSql = "";
		String sql_location = "";
		String orderNo = "";
		String sql_count = "";
		String Sql_numOfitems = "";
		String sql_target_date = "";
		String CustomerSql = "";
		String CountrySql = "";
		String pmanagerSql = "";
		String techsupportSql = "";
		String SmanagerSql = "";
		String sql = "";
		String EmailSql = "";
		String PrioritySql = "";
		String sql_shiping_date = "";
		String sql_follow1 = "";
		orderNo = dto.getOrderNo();
		String organizationSql = "";
		String criSql = "";

		for (int i = 0; i < columnlist.size(); i++) {
			String columnName = (String) columnlist.get(i);
			if ("".equals(columnName))
				continue;

			columnList.add(columnName.split(":")[1]);
		}
		dto.setColumnName(columnList);
		if (!"".equals(dto.getConfirmationDate())
				&& dto.getConfirmationDate() != null) {
			dateSql = getDateSql(dto,
					" `order`.`v_all_orders`.`customer_confirm_date`");
		}
		if (!"".equals(dto.getOrderStatus()) && dto.getOrderStatus() != null) {
			StatusSql = " and `order`.`v_all_orders`.`status` like '%"
					+ dto.getOrderStatus() + "%'";
		}
		if (!"".equals(orderNo) && orderNo != null) {
			OrderSql = " and `order`.`v_all_orders`.`order_no` = '" + orderNo
					+ "'";
		}

		if (!"".equals(dto.getOrgName()) && dto.getOrgName() != null) {
			organizationSql = " and `order`.`v_all_orders`.`organization` ="
					+ dto.getOrgName();
		}

		if (!"".equals(dto.getPriority()) && dto.getPriority() != null) {
			PrioritySql = " and `order`.`v_all_orders`.`priority` ='"
					+ dto.getPriority() + "'";
		}

		if (!"".equals(dto.getCustNo()) && dto.getCustNo() != null) {
			CustomerSql = " and `order`.`v_all_orders`.`cust_no` = '"
					+ dto.getCustNo() + "'";
		}

		if (!"".equals(dto.getEmail()) && dto.getEmail() != null) {
			EmailSql = " and `order`.`v_all_orders`.`email like` '%"
					+ dto.getEmail() + "%'";
		}

		if (!"".equals(dto.getCountry()) && dto.getCountry() != null) {
			EmailSql = " and `order`.`v_all_orders`.`country` = '"
					+ dto.getCountry() + "'";
		}

		if (!"".equals(dto.getProjectManager())
				&& dto.getProjectManager() != null) {
			pmanagerSql = " and `order`.`v_all_orders`.`project_manager_id` ='"
					+ dto.getProjectManager() + "'";
		}

		if (!"".equals(dto.getAccountManager())
				&& dto.getAccountManager() != null) {
			techsupportSql = " and `order`.`v_all_orders`.`tech_support_id` ='"
					+ dto.getAccountManager() + "'";
		}

		if (!"".equals(dto.getSalesManager()) && dto.getSalesManager() != null) {
			SmanagerSql = "and `order`.`v_all_orders`.`sales_contact_id` ='"
					+ dto.getSalesManager() + "'";
		}

		String followstatus = "";
		if (!"".equals(dto.getFollowupStatus())
				&& dto.getFollowupStatus() != null) {
			followstatus = "   	OR foll.status = '" + dto.getFollowupStatus()
					+ "'";
		}

		criSql = StatusSql + OrderSql + dateSql + organizationSql + PrioritySql
				+ CustomerSql + EmailSql + CountrySql + SmanagerSql
				+ techsupportSql + pmanagerSql;
		String[] str = null;
		String pstype = "";
		String pstypesql = "";
		pstype = dto.getProductServiceType();
		if (dto.getProductServiceType() != null
				&& !"".equals(dto.getProductServiceType())) {
			str = pstype.split(":");
			if (str != null) {
				pstypesql = "   and oi.type = '" + str[2].toString()
						+ "' and oi.name = '" + str[0].toString() + "'";
			}
		}

		sql = " select   `order`.`v_all_orders`.`order_no` ,`order`.`v_all_orders`.`customer_confirm_date`  ,"
				+ "`order`.`v_all_orders`.`status` ,`order`.`v_all_orders`.`email`  ,`order`.`v_all_orders`.`cust_no` ,`order`.`v_all_orders`.`priority` ,`order`.`v_all_orders`.`organization`  , CONCAT(`order`.`v_all_orders`.`first_name`,'',`order`.`v_all_orders`.`last_name`) as customerName,`order`.`v_all_orders`.`amount` ,`order`.`v_all_orders`.`tech_support`  , `order`.`v_all_orders`.`sales_contact`  ,`order`.`v_all_orders`.`project_manager`  ,   `order`.`payment_voucher`.`po_number`"
				+ "FROM `order`.`v_all_orders` "
				+ "JOIN "
				+ "(SELECT oi.order_no,oi.type,oi.cls_id    FROM `order`.`order_items` oi "
				+ " WHERE oi.order_item_id = (SELECT MIN(oii.order_item_id)  FROM `order`.`order_items` oii WHERE oi.order_no = oii.order_no  )"
				+ pstypesql
				+ " ) AS oiii "
				+ " ON (v_all_orders.order_no = oiii.order_no) "
				+ " LEFT JOIN"
				+ "  (SELECT t1.order_no,t1.status    FROM `order`.`order_followup` t1   "
				+ "WHERE id = (SELECT MAX(id) FROM `order`.`order_followup` t2 WHERE t1.order_no = t2.order_no) ) AS foll"
				+ " ON (v_all_orders.order_no = foll.order_no) "
				+ " LEFT JOIN  `order`.`payment_voucher` 	"
				+ " ON(`v_all_orders`.`order_no` = `payment_voucher`.`order_no`)     WHERE 1=1  "
				+ followstatus
				+ "  AND 1=1 "
				+ criSql
				+ " and `order`.`v_all_orders`.`status` IN('CC','VC','IP','PS')  ";

		if (sortBy != null && !"".equals(sortBy)) {
			sql += "order by " + sortBy + " desc ";
		}

		System.out.println("sql====" + sql);
		mainList = orderFollowupdao.ExcuteSql(sql);
		if (mainList != null && mainList.size() > 0) {
			int n1 = mainList.size();
			for (int i = 0; i < n1; i++) {
				Object[] o1 = (Object[]) mainList.get(i);
				orderFollowupDTO = new OrderFollowupDTO();
				if (o1[0] != null && o1[0].toString() != null
						&& !"".equals(o1[0].toString())) {
					orderFollowupDTO.setOrderNo(o1[0].toString());
					String orderNo_now = o1[0].toString();
					sql_count = " select  count(item_no) as totalItems from order.order_items oii"
							+ " where oii.status <> 'CN' and oii.order_no  = '"
							+ orderNo_now + "'";

					sql_shiping_date = "select  sl.ship_date from shipping.shipment_lines sl  "
							+ "where  sl.line_id =(select max(ss.line_id)"
							+ " from shipping.shipment_lines ss  where  ss.order_no = '"
							+ orderNo_now + "') ";

					sql_target_date = " select   Max(oi.target_date) from order.order_items oi where   "
							+ "oi.status <>'CN' and   oi.order_no ='"
							+ orderNo_now + "'";

					Sql_numOfitems = " select  count(*) from  order.order_items where target_date < sysdate()"
							+ " and status not in('CN','SH')   and  order_no ='"
							+ orderNo_now + "'";

					sql_follow1 = "SELECT  STATUS, LEFT(message,10) ,MAX(followup_date) "
							+ "FROM order.order_followup WHERE order_no = "
							+ orderNo_now + " ";

					countList = orderFollowupdao.ExcuteSql(sql_count);
					sql_shiping_dateList = orderFollowupdao
							.ExcuteSql(sql_shiping_date);
					target_dateList = orderFollowupdao
							.ExcuteSql(sql_target_date);
					reduNumList = orderFollowupdao.ExcuteSql(Sql_numOfitems);
					followups = orderFollowupdao.ExcuteSql(sql_follow1);

					if (countList != null) {
						Long counts2 = Long.parseLong(countList.get(0)
								.toString());
						orderFollowupDTO.setNumofItems(counts2.toString());
					} else {
						orderFollowupDTO.setNumofItems("");
					}

					if (str != null) {
						if (str[2] != null && !"".equals(str[2])) {
							orderFollowupDTO.setProductServiceType(str[2]
									.toString());
						}
					}

					if (sql_shiping_dateList != null
							&& !"".equals(sql_shiping_dateList)) {
						if (sql_shiping_dateList.size() > 0) {
							if (sql_shiping_dateList.get(0) != null
									&& !"".equals(sql_shiping_dateList.get(0))) {
								orderFollowupDTO
										.setDeliveryDate(sql_shiping_dateList
												.get(0).toString());
							} else {
								orderFollowupDTO.setDeliveryDate("");
							}
						}

					}

					if (target_dateList != null) {
						if (target_dateList.get(0) != null
								&& !"".equals(target_dateList.get(0))) {
							orderFollowupDTO
									.setProductionTargetDate(target_dateList
											.get(0).toString());
							orderFollowupDTO
									.setGuaranteedDeliveryDate(target_dateList
											.get(0).toString());
						} else {
							orderFollowupDTO.setGuaranteedDeliveryDate("");
						}

					}
					if (reduNumList != null) {
						if (reduNumList.get(0) != null
								&& !"".equals(reduNumList.get(0))) {
							Long counts2 = Long.parseLong(reduNumList.get(0)
									.toString());
							orderFollowupDTO.setOverdueNumOfitems(counts2
									.toString());
						} else {
							orderFollowupDTO.setOverdueNumOfitems("");
						}
					}

					if (followups != null) {
						if (followups.get(0) != null) {
							Object[] o2 = (Object[]) followups.get(0);
							if (o2[1] != null && o2[2] != null && o2[0] != null) {
								String targets = o2[0].toString();
								String targets2 = o2[1].toString();
								String targets3 = o2[2].toString();
								orderFollowupDTO.setFollowupStatus(targets);
								orderFollowupDTO.setFollowupMessage(targets2);
								orderFollowupDTO.setFollowupDate(targets3);
							} else {
								orderFollowupDTO.setFollowupStatus("");
								orderFollowupDTO.setFollowupMessage("");
								orderFollowupDTO.setFollowupDate("");
							}
						}
					}
				}

				if (o1[1] != null && o1[1].toString() != null
						&& !"".equals(o1[1].toString())) {
					orderFollowupDTO.setConfirmationDate(o1[1].toString());
				} else {
					orderFollowupDTO.setConfirmationDate("");
				}

				if (o1[2] != null && o1[2].toString() != null
						&& !"".equals(o1[2].toString())) {
					orderFollowupDTO.setOrderStatus(o1[2].toString());
				} else {
					orderFollowupDTO.setOrderStatus("");
				}

				if (o1[3] != null && o1[3].toString() != null
						&& !"".equals(o1[3].toString())) {
					orderFollowupDTO.setMail(o1[3].toString());
				} else {
					orderFollowupDTO.setMail("");

				}

				if (o1[4] != null && o1[4].toString() != null
						&& !"".equals(o1[4].toString())) {
					orderFollowupDTO.setCustNo(o1[4].toString());
					sql_location = " select CONCAT(cut.country,'',cut.state) as Location from "
							+ "customer.customer cut where  cut.cust_no  = '"
							+ o1[4].toString() + "' ";
					locationList = orderFollowupdao.ExcuteSql(sql_location);
					if (locationList != null) {
						if (locationList.get(0) != null) {
							String locations = locationList.get(0).toString();
							orderFollowupDTO.setLocation(locations);
						} else {
							orderFollowupDTO.setLocation("");
						}
					}
				}

				if (o1[5] != null && o1[5].toString() != null
						&& !"".equals(o1[5].toString())) {
					orderFollowupDTO.setPriority(o1[5].toString());
				} else {
					orderFollowupDTO.setPriority("");

				}

				if (o1[6] != null && o1[6].toString() != null
						&& !"".equals(o1[6].toString())) {
					orderFollowupDTO.setOrganization(o1[6].toString());
				} else {
					orderFollowupDTO.setOrganization("");
				}

				if (o1[7] != null && o1[7].toString() != null
						&& !"".equals(o1[7].toString())) {
					orderFollowupDTO.setCustName(o1[7].toString());
				} else {
					orderFollowupDTO.setCustName("");
				}

				if (o1[8] != null && o1[8].toString() != null
						&& !"".equals(o1[8].toString())) {
					orderFollowupDTO.setOrderTotal(o1[8].toString());
				}

				if (o1[9] != null && o1[9].toString() != null
						&& !"".equals(o1[9].toString())) {
					orderFollowupDTO.setTam(o1[9].toString());
				}

				if (o1[10] != null && o1[10].toString() != null
						&& !"".equals(o1[10].toString())) {
					orderFollowupDTO.setSalesManage(o1[10].toString());
				}

				if (o1[11] != null && o1[11].toString() != null
						&& !"".equals(o1[11].toString())) {
					orderFollowupDTO.setProjectManager(o1[11].toString());
				}

				if (o1[12] != null && o1[12].toString() != null
						&& !"".equals(o1[12].toString())) {
					orderFollowupDTO.setPo(o1[12].toString());
				}

				allList.add(orderFollowupDTO);

			}

		}
		dto.setReportData(allList);
		return dto;
	}

}
