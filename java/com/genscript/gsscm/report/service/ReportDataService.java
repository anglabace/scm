package com.genscript.gsscm.report.service;

import com.fedex.ship.stub.StringBarcode;
import com.genscript.core.orm.Page;
import com.genscript.gsscm.common.util.DateUtils;
import com.genscript.gsscm.order.entity.OrderMain;
import com.genscript.gsscm.quote.entity.QuoteMain;
import com.genscript.gsscm.report.dao.ReportDao;
import com.genscript.gsscm.report.entity.*;
import com.genscript.gsscm.report.dto.ReportDataDto;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by zhanghuibin
 * User: Administrator
 * Date: 7/1/11
 * Time: 9:13 AM
 * To change this template use File | Settings | File Templates.
 */

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class ReportDataService {

    @Autowired
    private ReportDao reportDao;

    //quotation Summary 查询
    public ReportDataDto getQuotationReportDate(Page<ReportDataDto> reportDataDtoPage, ReportDataDto dto, Boolean isExport) throws Exception{
        //解析 column
        ArrayList<String> columnList = new ArrayList<String>();
        for(int i=0; i < dto.getColumn().size(); i++){
            String columnName = dto.getColumn().get(i);
            if("".equals(columnName)) continue;
            if("status".equals(columnName.split(":")[0]) && dto.getStatus().size() > 1){
                dto.getColumn().remove(i);
                i--;
                continue;
            }
            dto.getColumn().set(i, columnName.split(":")[0]);
            columnList.add(columnName.split(":")[1]);
        }
        dto.setColumnName(columnList);
        //Date Range
        String dateSql = "";
        if(!"".equals(dto.getDataRange())){
            dateSql = getDateSql(dto, "qu.quote_Date");
        }
        //Period Type
        String perTypeSql = "";
        String perType = "";
        if(!"".equals(dto.getPeriodType())){
            if ("year".equals(dto.getPeriodType())) {
                perTypeSql = " group by " + dto.getPeriodType() + "(qu.quote_Date)";
                perType = dto.getPeriodType() + "(qu.quote_Date) ";
            }else if("day".equals(dto.getPeriodType())){
                perTypeSql = " group by date(qu.quote_Date)";
                perType = " qu.quote_Date ";
            }else{
                perTypeSql = " group by year(qu.quote_Date)," + dto.getPeriodType() + "(qu.quote_Date)";
                perType = dto.getPeriodType() + "(qu.quote_Date) ";
            }
        }
        List<String> statusList = dto.getStatus();
        String statuss = "";
        String statusSql = "";
        for(String status : statusList){
            if("".equals(status)) continue;
           statuss = statuss + "'" + status + "',";
        }
        if(!"".equals(statuss)){
           statusSql = " and qu.status in (" + statuss.substring(0, statuss.length() -1) + ")";
        }
        String currencySql = "";
        if(!"".equals(dto.getCurrency())){
           currencySql = " and qu.quote_Currency='" + dto.getCurrency() + "'";
        }
        String criSql = statusSql + currencySql + dateSql;
        /*countSql += "select qu.quote_date,qu.status_count as status,qu.tax,qu.subTotal,qu.shipAmt,qu.total,case status_count1 when 0 then 0 else status_count2/status_count1 end as closeRation,qu.quote_year from ";
        countSql += " (select year(qu.quote_date) as quote_year," + perType + " as quote_date ,qu.status as status_count, sum(qu.tax) as tax,sum(qu.sub_Total) as subTotal,sum(qu.ship_amt) as shipAmt, sum(ifnull(qu.amount,0)-ifnull(qu.discount,0)-ifnull(qu.coupon_Value,0)+ ifnull(qu.ship_Amt,0) + ifnull(qu.tax,0)) as total";
        countSql += " from order.quote qu where 1=1 " + criSql + ") as qu ";
        countSql += " left join (select year(qu.quote_date) as quote_year1," + perType + " as quote_date1,count(qu.status) as status_count1 from order.quote qu where qu.status in ('OP','CW','CO') "  + criSql + ") as al on quote_date=quote_date1 and quote_year=quote_year1";
        countSql += " left join (select year(qu.quote_date) as quote_year2," + perType + " as quote_date2,count(qu.status) as status_count2 from order.quote qu where qu.status='Closed' "  + criSql + ") as a2 on quote_date=quote_date2 and quote_year=quote_year2";
        countSql += " ) as tempQuote order by quote_year,quote_date";*/
        String sql = "select  "+perType+" as per_date,qu.status as status, sum(qu.tax) as tax,sum(qu.sub_Total) as subTotal,sum(qu.ship_Amt) as shipAmt,sum(ifnull(qu.amount,0)-ifnull(qu.discount,0)-ifnull(qu.coupon_Value,0)+ ifnull(qu.ship_Amt,0) + ifnull(qu.tax,0)) as total,year(qu.quote_date) as intYear from order.quote qu where 1=1 "  + statusSql + currencySql + dateSql + perTypeSql;
        String countSql = " select case status_count1 when 0 then 0 else status_count2/status_count1 end as closeRation from (";
        countSql += "select (select count(qu.status) as status_count2 from order.quote qu where qu.status='CN' "  + criSql + ")as status_count2 ,(select count(qu.status) as status_count1 from order.quote qu where qu.status in ('OP','CW','CO') "  + criSql + " ) as status_count1 from dual) as quoteTemp";
        sql = datePerSqlAppend(dto, sql);
        if (isExport) {
            dto.setReportData(reportDao.getReportByHQL(sql));
            dto.setCloseRatio((BigDecimal)reportDao.getUniqueByHQL(countSql));
        } else {
            dto.setReportData(reportDao.getQuoteByHQL(reportDataDtoPage, sql));
            dto.setCloseRatio((BigDecimal)reportDao.getUniqueByHQL(countSql));
        }
        //手动映射到bean
        //from date , to date

        /*String dataRange = dto.getDataRange();
        Date fromDate = null;// 开始时间
	    Date toDate = null;// 结束时间
        HashMap<String, Date> sp = getDateFromTo(dto);
        if(sp.get("fromDate") != null){
            fromDate = sp.get("fromDate");
        }else{
            fromDate = reportDao.getReportFromDateByHQL("select min(qu.quote_date) from order.quote qu where 1=1 " + statusSql + currencySql + dateSql);
        }
        toDate = DateUtils.defineDayBefore2Date(toDate, 1);*/

        for(int i= 0; i < dto.getReportData().size();i++){
            Object[] dataTemp = (Object[])dto.getReportData().get(i);
            QuoteMain quoteMain = new QuoteMain();
            //year
            Integer dateYearInt = (Integer)dataTemp[1];
            HashMap<String, String> map = setFromToDate(dateYearInt, dto.getPeriodType(), dataTemp[0]);
            quoteMain.setFromDate(map.get("fromDate"));
            quoteMain.setToDate(map.get("toDate"));
            //quoteMain.setQuoteDate((Date)dataTemp[0]);
            quoteMain.setDateInt(dataTemp[0]);
            quoteMain.setStatus(convertStatus("" + dataTemp[3]));
            quoteMain.setTax(BigDecimal2Dto(dataTemp[4]));
            quoteMain.setSubTotal(BigDecimal2Dto(dataTemp[5]));
            quoteMain.setShipAmt((Double)(BigDecimal2Dto(dataTemp[6]).doubleValue()));
            quoteMain.setTotal((Double)(BigDecimal2Dto(dataTemp[7]).doubleValue()));
            dto.getReportData().set(i, quoteMain);
        }
        modifyFstAndLstDate(dto);
        return dto;
    }

    //Quotation Detail 查询
    public ReportDataDto getQuotationDetailReportDate(Page<ReportDataDto> reportDataDtoPage, ReportDataDto dto, boolean isExport){
        setReportColumns(dto);
        //Date Range
        String dateSql = "";
        if(!"".equals(dto.getDataRange())){
            dateSql = getDateSql(dto, "qu.quote_Date");
        }
        //customer 查询
        String customerSql = "";
        if(dto.getCustomer() != null && !"".equals(dto.getCustomer())){
            customerSql = " and qu.cust_No=" + dto.getCustomer();
        }
        String cirSql = "";
        String cirLeftColumn = ",territory.territory_code as salesTerritoryName,contact.login_name as salesContactName,tech.login_name as techSupportName,project.login_name as projectManagerName, org.name as orgName, '1' as tempFiled";
        String cirLeftJoin = " left join system.users contact on qu.sales_contact=contact.user_id " +
                " left join system.users tech on qu.tech_support=tech.user_id " +
                " left join system.users project on qu.project_manager=project.user_id ";
        String customerLeft = " LEFT JOIN customer.organization org ON org.org_id=cus.org_id \n" +
                "LEFT JOIN sales_territory territory ON cus.sales_territory=territory.territory_id ";
        if(dto.getTerritory() != null && !"".equals(dto.getTerritory())){
            //cirSql += " and qu.cust_no in (select cus.cust_no from customer.customer cus where cus.cust_no=qu.cust_no and cus.sales_territory=" + dto.getTerritory() + ")";
            cirSql += " and cus.sales_territory=" + dto.getTerritory();
        }
        if(dto.getSalesManager() != null && !"".equals(dto.getSalesManager())){
            cirSql += " and qu.sales_contact=" + dto.getSalesManager();
        }
        if(dto.getTechManager() != null && !"".equals(dto.getTechManager())){
            cirSql += " and qu.tech_support=" + dto.getTechManager();
        }
        if(dto.getProjectManager() != null && !"".equals(dto.getProjectManager())){
            cirSql += " and qu.project_manager=" + dto.getProjectManager();
        }
        if(dto.getOrgType() != null && !"".equals(dto.getOrgType())){
            //cirSql += " and qu.cust_no in  (select cus.cust_no from customer.customer cus,customer.organization org where org.org_id=cus.org_id and org.org_type_id="+dto.getOrgType()+")";
            cirSql += " and org.org_type_id="+dto.getOrgType();
        }
        if(dto.getOrgId() != null && !"".equals(dto.getOrgId())){
            //cirSql += " and qu.cust_no in (select cus.cust_no from customer.customer cus where cus.org_id=" + dto.getOrgId() + ")";
            cirSql += " and cus.org_id=" + dto.getOrgId();
        }
        List<String> statusList = dto.getStatus();
        String statuss = "";
        String statusSql = "";
        for(String status : statusList){
            if("".equals(status)) continue;
           statuss = statuss + "'" + status + "',";
        }
        if(!"".equals(statuss)){
           statusSql = " and qu.status in (" + statuss.substring(0, statuss.length() -1) + ")";
        }
        String currencySql = "";
        if(!"".equals(dto.getCurrency())){
           currencySql = " and qu.quote_Currency='" + dto.getCurrency()+"'";
        }
        String sortSql = "";
        if(dto.getSortBy1()!=null && !"".equals(dto.getSortBy1())){
             sortSql += " order by qu." + dto.getSortBy1();
        }
        if(dto.getSortBy2()!=null && !"".equals(dto.getSortBy2())){
             sortSql += ", qu." + dto.getSortBy2();
        }
        if(dto.getSortBy3()!=null && !"".equals(dto.getSortBy3())){
             sortSql += ", qu." + dto.getSortBy3();
        }
        List dataList = null;
        List dataList2 = null;
        String sql = "SELECT distinct QUP.update_date,ord.state,ord.country,ifnull(qu.amount,0)-ifnull(qu.discount,0)-ifnull(qu.coupon_Value,0)+ ifnull(qu.ship_Amt,0) + ifnull(qu.tax,0) as total,qu.quote_no from order.quote qu left join order.order_addresses ord  on qu.shipto_addr_id=ord.addr_Id " + cirLeftJoin + ",order.quote_status_history QUP,customer.customer cus " + customerLeft + " where 1=1 and cus.cust_no=qu.cust_no and QUP.quote_No=qu.quote_No " + dateSql + customerSql+ cirSql +  statusSql + currencySql + sortSql;
        String hql2 = "SELECT distinct {qu.*}" + cirLeftColumn + " from order.quote qu left join order.order_addresses ord  on qu.shipto_addr_id=ord.addr_Id " + cirLeftJoin + ",order.quote_status_history QUP,customer.customer cus " + customerLeft + " where 1=1 and cus.cust_no=qu.cust_no  and QUP.quote_No=qu.quote_No " + dateSql + customerSql + cirSql +  statusSql + currencySql + sortSql;
        String sqlCount = "SELECT distinct qu.quote_no from order.quote qu left join order.order_addresses ord  on qu.shipto_addr_id=ord.addr_Id " + cirLeftJoin + ",order.quote_status_history QUP,customer.customer cus " + customerLeft + " where 1=1 and cus.cust_no=qu.cust_no  and QUP.quote_No=qu.quote_No " + dateSql + customerSql + cirSql +  statusSql + currencySql + sortSql;
        if (isExport) {
            dataList = reportDao.getQuoteDetailBySQL1(sql);
            dataList2 = reportDao.getQuoteDetailBySQL(hql2);
//            dto.setReportData(reportDao.getQuoteDetailBySQL(hql));
        } else {
            dataList = reportDao.getQuoteDetailBySQL1(reportDataDtoPage,sql);
            dataList2 = reportDao.getQuoteDetailBySQL(reportDataDtoPage, hql2, sqlCount);
//            dto.setReportData(reportDao.getQuoteDetailBySQL(reportDataDtoPage, hql));
        }
        List reportDataList = new ArrayList();
        for (int i = 0; i < dataList2.size(); i++) {
            Object[] data = (Object[]) dataList2.get(i);
            QutationDetail quoteDetail = (QutationDetail) data[0];
            quoteDetail.setStatus(convertStatus(quoteDetail.getStatus()));
            Object[] dataObjTemp = (Object[]) dataList.get(i);
            String status = dataObjTemp[1] == null ? "" : dataObjTemp[1].toString() + ",";
            String country = dataObjTemp[2] == null ? "" : dataObjTemp[2].toString();
            quoteDetail.setLocation(status + country);
            quoteDetail.setQuotationCloseDate((Date) dataObjTemp[0]);
            quoteDetail.setTotal((Double) ((BigDecimal) dataObjTemp[3]).doubleValue());
            quoteDetail.setSalesTerritoryName(String2Dto(data[1]));
            quoteDetail.setSalesContactName(String2Dto(data[2]));
            quoteDetail.setTechSupportName(String2Dto(data[3]));
            quoteDetail.setProjectManagerName(String2Dto(data[4]));
            quoteDetail.setOrgName(String2Dto(data[5]));
            reportDataList.add(quoteDetail);
        }
        dto.setReportData(reportDataList);
        return dto;
    }

     //Sales Order Summary 查询
    public ReportDataDto getSalesSummaryReportDate(Page<ReportDataDto> reportDataDtoPage, ReportDataDto dto, Boolean isExport)  throws Exception{
        //解析 column
        ArrayList<String> columnList = new ArrayList<String>();
        for(int i=0; i < dto.getColumn().size(); i++){
            String columnName = dto.getColumn().get(i);
            if("".equals(columnName)) continue;
            if("status".equals(columnName.split(":")[0]) && dto.getStatus().size() > 1){
                dto.getColumn().remove(i);
                i--;
                continue;
            }
            dto.getColumn().set(i, columnName.split(":")[0]);
            columnList.add(columnName.split(":")[1]);
        }
        dto.setColumnName(columnList);
        //Date Range
        String dateSql = "";
        if(!"".equals(dto.getDataRange())){
            dateSql = getDateSql(dto, "qu.order_Date");
        }
        //Period Type
        String perTypeSql = "";
        String perType = "";
        if(!"".equals(dto.getPeriodType())){
            if ("year".equals(dto.getPeriodType())) {
                perTypeSql = " group by " + dto.getPeriodType() + "(qu.order_Date)";
                 perType = dto.getPeriodType() + "(qu.order_Date) as per_date";
            }else if("day".equals(dto.getPeriodType())){
                perTypeSql = " group by date(qu.order_Date)";
                 perType = " date(qu.order_Date) as per_date";
            }else{
                perTypeSql = " group by year(qu.order_Date)," + dto.getPeriodType() + "(qu.order_Date)";
                 perType = dto.getPeriodType() + "(qu.order_Date) as per_date";
            }
        }
        List<String> statusList = dto.getStatus();
        String statuss = "";
        String statusSql = "";
        for(String status : statusList){
            if("".equals(status)) continue;
           statuss = statuss + "'" + status + "',";
        }
        if(!"".equals(statuss)){
           statusSql = " and qu.status in (" + statuss.substring(0, statuss.length() -1) + ")";
        }
        String currencySql = "";
        if(!"".equals(dto.getCurrency())){
           currencySql = " and qu.order_currency='" + dto.getCurrency() + "'";
        }
        String hql = "select "+perType+",qu.status as status, sum(qu.tax) as tax,sum(qu.sub_Total) as subTotal,sum(qu.ship_Amt) as shipAmt,sum(ifnull(qu.amount,0)-ifnull(qu.discount,0)-ifnull(qu.coupon_Value,0)+ ifnull(qu.ship_Amt,0) + ifnull(qu.tax,0)) as total,year(qu.order_date) as intYear from order.order qu where 1=1 "  + statusSql + currencySql + dateSql + perTypeSql;
        hql = datePerSqlAppend(dto, hql);
        if (isExport) {
            dto.setReportData(reportDao.getReportByHQL(hql));
        } else {
            dto.setReportData(reportDao.getQuoteByHQL(reportDataDtoPage, hql));
        }

        for(int i= 0; i < dto.getReportData().size();i++){
            Object[] dataTemp = (Object[])dto.getReportData().get(i);
            OrderDetail orderMain = new OrderDetail();
            //year
            Integer dateYearInt = (Integer)dataTemp[1];
            HashMap<String, String> map = setFromToDate(dateYearInt, dto.getPeriodType(), dataTemp[0]);
            orderMain.setDateInt(dataTemp[0]);
            orderMain.setFromDate(map.get("fromDate"));
            orderMain.setToDate(map.get("toDate"));
            orderMain.setStatus(convertStatus1("" + dataTemp[3]));
            orderMain.setTax(BigDecimal2Dto(dataTemp[4]));
            orderMain.setSubTotal(BigDecimal2Dto(dataTemp[5]));
            orderMain.setShipAmt((Double)(BigDecimal2Dto(dataTemp[6]).doubleValue()));
            orderMain.setAmount(BigDecimal2Dto(dataTemp[7]));
            dto.getReportData().set(i, orderMain);
        }
        modifyFstAndLstDate(dto);
        return dto;
    }

    //Sales Order Detail 查询
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public ReportDataDto getSalesDetailReportDate(Page<ReportDataDto> reportDataDtoPage, ReportDataDto dto, Boolean isExport) throws Exception{
        //解析 column
        ArrayList<String> columnList = new ArrayList<String>();
        for(int i=0; i < dto.getColumn().size(); i++){
            String columnName = dto.getColumn().get(i);
            if("".equals(columnName)) continue;
            if("status".equals(columnName.split(":")[0]) && dto.getStatus().size() > 1){
                dto.getColumn().remove(i);
                i--;
                continue;
            }
            dto.getColumn().set(i, columnName.split(":")[0]);
            columnList.add(columnName.split(":")[1]);
        }
        dto.setColumnName(columnList);
        //Date Range
        String dateSql = "";
        if(!"".equals(dto.getDataRange())){
            dateSql = getDateSql(dto, "qu.order_Date");
        }
        //customer 查询
        String customerSql = "";
        if(dto.getCustomer() != null && !"".equals(dto.getCustomer())){
            customerSql = " and qu.cust_No=" + dto.getCustomer();
        }
        String cirSql = "";
        String cirLeftColumn = ",territory.territory_code as salesTerritoryName,contact.login_name as salesContactName,tech.login_name as techSupportName,project.login_name as projectManagerName, org.name as orgName ";
        String cirLeftJoin = " left join system.users contact on qu.sales_contact=contact.user_id " +
                " left join system.users tech on qu.tech_support=tech.user_id " +
                " left join system.users project on qu.project_manager=project.user_id ";
        String customerLeft = " LEFT JOIN customer.organization org ON org.org_id=cus.org_id \n" +
                "LEFT JOIN sales_territory territory ON cus.sales_territory=territory.territory_id ";
        if(dto.getTerritory() != null && !"".equals(dto.getTerritory())){
            //cirSql += " and qu.cust_no in (select cus.cust_no from customer.customer cus where cus.cust_no=qu.cust_no and cus.sales_territory=" + dto.getTerritory() + ")";
            cirSql += " and cus.sales_territory=" + dto.getTerritory();
        }
        if(dto.getSalesManager() != null && !"".equals(dto.getSalesManager())){
            cirSql += " and qu.sales_contact=" + dto.getSalesManager();
        }
        if(dto.getTechManager() != null && !"".equals(dto.getTechManager())){
            cirSql += " and qu.tech_support=" + dto.getTechManager();
        }
        if(dto.getProjectManager() != null && !"".equals(dto.getProjectManager())){
            cirSql += " and qu.project_manager=" + dto.getProjectManager();
        }
        if(dto.getOrgType() != null && !"".equals(dto.getOrgType())){
            //cirSql += " and qu.cust_no in  (select cus.cust_no from customer.customer cus,customer.organization org where org.org_id=cus.org_id and org.org_type_id="+dto.getOrgType()+")";
            cirSql += " and org.org_type_id="+dto.getOrgType();
        }
        if(dto.getOrgId() != null && !"".equals(dto.getOrgId())){
            //cirSql += " and qu.cust_no in (select cus.cust_no from customer.customer cus where cus.org_id=" + dto.getOrgId() + ")";
            cirSql += " and cus.org_id=" + dto.getOrgId();
        }
        List<String> statusList = dto.getStatus();
        String statuss = "";
        String statusSql = "";
        for(String status : statusList){
            if("".equals(status)) continue;
           statuss = statuss + "'" + status + "',";
        }
        if(!"".equals(statuss)){
           statusSql = " and qu.status in (" + statuss.substring(0, statuss.length() -1) + ")";
        }
        String currencySql = "";
        if(!"".equals(dto.getCurrency())){
           currencySql = " and qu.order_currency='" + dto.getCurrency()+"'";
        }
        String sortSql = "";
        if(dto.getSortBy1()!=null && !"".equals(dto.getSortBy1())){
             sortSql += " order by qu." + dto.getSortBy1();
        }
        if(dto.getSortBy2()!=null && !"".equals(dto.getSortBy2())){
             sortSql += ", qu." + dto.getSortBy2();
        }
        if(dto.getSortBy3()!=null && !"".equals(dto.getSortBy3())){
             sortSql += ", qu." + dto.getSortBy3();
        }
        List dataList = null;
        List dataList2 = null;
        String sql = "SELECT distinct QUP.update_date,ord.state,ord.country,ifnull(qu.amount,0)-ifnull(qu.discount,0)-ifnull(qu.coupon_Value,0)+ ifnull(qu.ship_Amt,0) + ifnull(qu.tax,0) as total,qu.order_no from order.order qu " + cirLeftJoin + " left join order.order_addresses ord  on qu.shipto_addr_id=ord.addr_Id,order.order_status_history QUP,customer.customer cus " + customerLeft + " where 1=1 and cus.cust_no=qu.cust_no and QUP.order_No=qu.order_No " + dateSql + customerSql + cirSql + cirSql +  statusSql + currencySql + sortSql;
        String hql2 = "SELECT  distinct {qu.*}" + cirLeftColumn + " from order.order qu " + cirLeftJoin + " left join order.order_addresses ord  on qu.shipto_addr_id=ord.addr_Id,order.order_status_history QUP,customer.customer cus " + customerLeft + " where 1=1 and cus.cust_no=qu.cust_no and QUP.order_No=qu.order_No " + dateSql + customerSql  + cirSql  + statusSql + currencySql + sortSql;
        String sqlCount = "SELECT distinct qu.order_no from order.order qu " + cirLeftJoin + " left join order.order_addresses ord  on qu.shipto_addr_id=ord.addr_Id,order.order_status_history QUP,customer.customer cus " + customerLeft + " where 1=1 and cus.cust_no=qu.cust_no and QUP.order_No=qu.order_No " + dateSql + customerSql + cirSql +  statusSql + currencySql + sortSql;
        if (isExport) {
            dataList = reportDao.getQuoteDetailBySQL1(sql);
            dataList2 = reportDao.getOrderDetailBySQL(hql2);
        } else {
            dataList = reportDao.getQuoteDetailBySQL1(reportDataDtoPage,sql);
            dataList2 = reportDao.getOrderDetailBySQL(reportDataDtoPage,hql2, sqlCount);
        }
        List reportDataList = new ArrayList();
        for(int i = 0; i< dataList2.size(); i++){
            Object[] data = (Object[])dataList2.get(i);
            OrderDetail orderDetail = (OrderDetail) data[0];
            orderDetail.setStatus(convertStatus1(orderDetail.getStatus()));
            Object[] dataObjTemp = (Object[])dataList.get(i);
            String status = dataObjTemp[1]==null ? "" : dataObjTemp[1].toString() + ",";
            String country = dataObjTemp[2]==null ? "" : dataObjTemp[2].toString();
            orderDetail.setLocation(status + "," + country);
            orderDetail.setTotal((Double)((BigDecimal)dataObjTemp[3]).doubleValue());
            if (data[1] != null) orderDetail.setSalesTerritoryName(data[1].toString());
            if (data[2] != null) orderDetail.setSalesContactName(data[2].toString());
            if (data[3] != null) orderDetail.setTechSupportName(data[3].toString());
            if (data[4] != null) orderDetail.setProjectManagerName(data[4].toString());
            if (data[5] != null) orderDetail.setOrgName(data[5].toString());
            reportDataList.add(orderDetail);
        }
        dto.setReportData(reportDataList);
        return dto;
    }

      //Sales Tax & Profit 查询
    public ReportDataDto getSalesProfitReportDate(Page<ReportDataDto> reportDataDtoPage, ReportDataDto dto, Boolean isExport){
        setReportColumns(dto);
        //Date Range
        String dateSql = "";
        if(!"".equals(dto.getDataRange())){
            dateSql = getDateSql(dto, "qu.order_Date");
        }
        String statusSql = "";
        String currencySql = "";
        if(!"".equals(dto.getCurrency())){
           currencySql = " and qu.order_currency='" + dto.getCurrency() + "'";
        }
        String hql = "select sum(item.cost) as cost,qu.order_no,qu.status,sum(item.amount) as amount,itemTemp.Profit,itemTemp.Profit/itemTemp.total as rate from order.order_items item,\n" +
                "order.order qu\n" +
                "left join (select item.order_no,sum((item.base_price-item.cost)*item.quantity) as Profit,sum(item.base_price*item.quantity) as total\n" +
                " from order.order_items item group by item.order_no) as itemTemp on itemTemp.order_no=qu.order_no \n" +
                " where qu.order_no=item.order_no and itemTemp.order_no=qu.order_no " +
                "group by qu.order_no " + currencySql + dateSql ;
        if (isExport) {
            dto.setReportData(reportDao.getReportByHQL(hql));
        } else {
            dto.setReportData(reportDao.getQuoteByHQL(reportDataDtoPage, hql));
        }

        for(int i= 0; i < dto.getReportData().size();i++){
            Object[] dataTemp = (Object[])dto.getReportData().get(i);
            OrderMain orderMain = new OrderMain();
            //year
//            Integer dateYearInt = (Integer)dataTemp[7];
//            HashMap<String, String> map = setFromToDate(dateYearInt, dto.getPeriodType(), dataTemp[0]);
//            orderMain.setFromDate(map.get("fromDate"));
//            orderMain.setToDate(map.get("toDate"));
            orderMain.setCost((BigDecimal) dataTemp[0]);
            orderMain.setStatus(convertStatus1("" + dataTemp[2]));
            orderMain.setAmount((BigDecimal) dataTemp[3]);
            orderMain.setProfit((BigDecimal) dataTemp[4]);
            orderMain.setRate(((BigDecimal) dataTemp[5]));
            dto.getReportData().set(i, orderMain);
        }
//        modifyFstAndLstDate(dto);
        return dto;
    }

    //Product/Service Sales Summary 查询
    public ReportDataDto getPSSalesSummaryReportDate(Page<ReportDataDto> reportDataDtoPage, ReportDataDto dto, Boolean isExport){
        setReportColumns(dto);
        //Date Range
        String dateSql = "";
        if(!"".equals(dto.getDataRange())){
            dateSql = getDateSql(dto, "qu.order_Date");
        }
        String table = "product.v_product_structure";
        String sqlType = "product_type";
        Class entityClass = ProudctServiceReport.class;
        String id = "product_id";
        if("service".equals(dto.getSegmentType())){
            table = "product.v_service_structure";
            entityClass = ServiceReport.class;
            id = "service_id";
            sqlType = "service_type";
        }
        String criSql = "";
        if(dto.getPsId()!=null && !"".equals(dto.getPsId())){
            if("service".equals(dto.getSegmentType())){
                criSql += " and service.service_id="+dto.getPsId();
            }else{
                criSql += " and service.product_id="+dto.getPsId();
            }
        }
        if(dto.getPsName()!=null && !"".equals(dto.getPsName().trim())){
            criSql += " and service.name like '%"+dto.getPsName() + "%'";
        }
        criSql += " and service.business_unit_id=" + dto.getBusUnitId() + " and service.category_id=" + dto.getCategoryId() + " and service.product_line_id=" + dto.getPsLineId();
        String hql = "select {sac.*} from (select service."+id+",service.business_unit,service.business_unit_id,service.catalog_id,service.catalog_no,service.category,\n" +
                "        service.category_id,service.name,service.product_line,service.product_line_id,service."+sqlType+",service.segment,\n" +
                "        service.uom,item.qty_uom,item.quantity,item.amount  " +
                "from "+table+" service,order.order_items item,order.order qu where item.catalog_no=service.catalog_no and service.catalog_id=item.catalog_id and item.order_no=qu.order_no \n" +
                "and item.type='"+dto.getSegmentType().toUpperCase()+"'"  + dateSql + criSql + ") as sac ";
        if (isExport) {
            dto.setReportData(reportDao.getPSSS(hql, entityClass));
        } else {
            dto.setReportData(reportDao.getPSSS(reportDataDtoPage, hql, entityClass));
        }
        return dto;
    }

    //Customer Payment 查询
    public ReportDataDto getCusPaymentReportDate(Page<ReportDataDto> reportDataDtoPage, ReportDataDto dto, Boolean isExport){
        //Date Range
        String dateSql = "";
        if(!"".equals(dto.getDataRange())){
            dateSql = getDateSql(dto, "qu.quote_Date");
        }
        String currencySql = "";
        if(!"".equals(dto.getCurrency())){
           currencySql = " and qu.quote_Currency='" + dto.getCurrency() + "'";
        }
        String hql = "select qu.order_Date,qu.status as status, sum(qu.tax) as tax,sum(qu.sub_Total) as subTotal,sum(qu.ship_Amt) as shipAmt,sum(ifnull(qu.amount,0)-ifnull(qu.discount,0)-ifnull(qu.coupon_Value,0)+ ifnull(qu.ship_Amt,0) + ifnull(qu.tax,0)) as total from order.order qu where 1=1 "  + currencySql + dateSql ;
        if (isExport) {
            dto.setReportData(reportDao.getReportByHQL(hql));
        } else {
            dto.setReportData(reportDao.getQuoteByHQL(reportDataDtoPage, hql));
        }

        for(int i= 0; i < dto.getReportData().size();i++){
            Object[] dataTemp = (Object[])dto.getReportData().get(i);
            OrderMain orderMain = new OrderMain();
            //year
//            Integer dateYearInt = (Integer)dataTemp[7];
//            HashMap<String, String> map = setFromToDate(dateYearInt, dto.getPeriodType(), dataTemp[0]);
//            orderMain.setFromDate(map.get("fromDate"));
//            orderMain.setToDate(map.get("toDate"));
            orderMain.setStatus(convertStatus1("" + dataTemp[1]));
            orderMain.setTax((BigDecimal)dataTemp[2]);
            orderMain.setSubTotal((BigDecimal)dataTemp[3]);
            orderMain.setShipAmt((Double)((BigDecimal)dataTemp[4]).doubleValue());
            orderMain.setAmount((BigDecimal)dataTemp[5]);
            dto.getReportData().set(i, orderMain);
        }
//        modifyFstAndLstDate(dto);
        return dto;
    }

    public void setReportColumns(ReportDataDto dto){
       //解析 column
        ArrayList<String> columnList = new ArrayList<String>();
        for(int i=0; i < dto.getColumn().size(); i++){
            String columnName = dto.getColumn().get(i);
            if("".equals(columnName)) continue;
            dto.getColumn().set(i, columnName.split(":")[0]);
            columnList.add(columnName.split(":")[1]);
        }
        dto.setColumnName(columnList);
    }

    //Sales Points Summary 查询
    public ReportDataDto getSalePointReportDate(Page<ReportDataDto> reportDataDtoPage, ReportDataDto dto, Boolean isExport){
        setReportColumns(dto);
        //Date Range
        String dateSql = "";
        if(!"".equals(dto.getDataRange())){
            dateSql = getDateSql(dto, "history.issue_date");
        }
        //Period Type
        String perTypeSql = "";
        String perType = "";
        String orderByPerType = "";
        if(!"".equals(dto.getPeriodType())){
            if ("year".equals(dto.getPeriodType())) {
                perTypeSql =  dto.getPeriodType() + "(history.issue_date)";
                 perType = dto.getPeriodType() + "(history.issue_date) as per_date";
                 orderByPerType = dto.getPeriodType() + "(history.issue_date)";
            }else if("day".equals(dto.getPeriodType())){
                perTypeSql = "  date(history.issue_date)";
                 perType = " date(history.issue_date) as per_date";
                 orderByPerType = " date(history.issue_date)";
            }else{
                perTypeSql = "  year(history.issue_date)," + dto.getPeriodType() + "(history.issue_date)";
                 perType = dto.getPeriodType() + "(history.issue_date) as per_date";
                 orderByPerType = dto.getPeriodType() + "(history.issue_date)";
            }
        }
        String criSql = "";
        if(dto.getOrgId() != null && !"".equals(dto.getOrgId())){
            criSql +=" and type.org_id=" + dto.getOrgId();
        }

        if(dto.getCustomer() != null && !"".equals(dto.getCustomer())){
            criSql +=" and history.cust_no=" + dto.getCustomer();
        }
        String hql = "select "+ perType + ",type.org_type_name,type.cust_name,history.order_no,ifnull(sum(red.points),0) as redPoints,ifnull(sum(ea.points),0) as eaPoints,year(history.issue_date) as intYear   \n" +
                " from customer.customer_points_history history \n" +
                "left join customer.customer_points_history red on  red.points<0 and history.cust_no=red.cust_no and history.order_no=red.order_no \n" +
                "left join customer.customer_points_history ea on  ea.points>0 and history.cust_no=ea.cust_no and history.order_no=ea.order_no, \n" +
                "(select cust.cust_no,cusType.name as org_type_name,cusType.org_id,concat(cust.first_name, ' ', cust.last_name) as cust_name from customer.customer cust,\n" +
                "(select type.name,org.org_id from customer.organization org left join customer.organization_type type on type.org_type_id=org.org_type_id) cusType\n" +
                " where cusType.org_id=cust.org_id ) type \n" +
                " where history.cust_no=type.cust_no " + criSql + dateSql +
                " group by history.cust_no,history.order_no,"  + perTypeSql + " order by " +orderByPerType;
        hql = datePerSqlAppend(dto, hql);
        if (isExport) {
            dto.setReportData(reportDao.getReportByHQL(hql));
        } else {
            dto.setReportData(reportDao.getQuoteByHQL(reportDataDtoPage, hql));
        }
        for(int i= 0; i < dto.getReportData().size();i++){
            Object[] dataTemp = (Object[])dto.getReportData().get(i);
            SalesPoints salePoint = new SalesPoints();
            //year
            salePoint.setDateInt(dataTemp[0]);
            Integer dateYearInt = (Integer)dataTemp[1];
            HashMap<String, String> map = setFromToDate(dateYearInt, dto.getPeriodType(), dataTemp[0]);
            salePoint.setFromDate(map.get("fromDate"));
            salePoint.setToDate(map.get("toDate"));
            salePoint.setOrgTypeName(String2Dto(dataTemp[3]));
            salePoint.setCustName(String2Dto(dataTemp[4]));
            salePoint.setOrderNo(String2Dto(dataTemp[5]));
            salePoint.setRedPoints(Integer2Dto(dataTemp[6]));
            salePoint.setEaPoints(Integer2Dto(dataTemp[7]));
            Integer balance = Integer2Dto(dataTemp[6]) + Integer2Dto(dataTemp[7]);
            salePoint.setBalancePoints(balance);
            dto.getReportData().set(i, salePoint);
        }
        //修正第一条数据的fromDate日期 和 最后一条数据的toDate
        if (!(!"week".equals(dto.getPeriodType()) || "".equals(dto.getDataRange()) || "today".equals(dto.getDataRange()) || "yesterday".equals(dto.getDataRange()) || "week".equals(dto.getDataRange()) || "lWeek".equals(dto.getDataRange()))) {
            HashMap<String, Date> map = getDateFromTo(dto);
            if (dto.getReportData().size() > 0) {
                SalesPoints fstDate = (SalesPoints) dto.getReportData().get(0);
                SalesPoints lstDate = (SalesPoints) dto.getReportData().get(dto.getReportData().size() - 1);
                if (map.get("fromDate").after(DateUtils.formatStr2Date(fstDate.getFromDate(), "yyyy-MM-dd"))) {
                    lstDate.setFromDate(DateUtils.formatDate2Str(map.get("fromDate"), "yyyy-MM-dd"));
                }
                if (map.get("toDate").before(DateUtils.formatStr2Date(lstDate.getToDate(), "yyyy-MM-dd"))) {
                    lstDate.setToDate(DateUtils.formatDate2Str(map.get("toDate"), "yyyy-MM-dd"));
                }
            }
        }
        return dto;
    }

    //Purchase Order Detail 查询
    public ReportDataDto getPurchaseOrderReportDate(Page<ReportDataDto> reportDataDtoPage, ReportDataDto dto, Boolean isExport){
        setReportColumns(dto);
        //Date Range
        String dateSql = "";
        if(!"".equals(dto.getDataRange())){
            dateSql = getDateSql(dto, "qu.order_date");
        }
        //Vendor
        String vendorSql = "";
        if(dto.getVendorId()!=null && dto.getVendorId() !=-1){
           vendorSql = " and qu.vendor_no='" + dto.getVendorId() + "'";
        }
        List<String> statusList = dto.getStatus();
        String statuss = "";
        String statusSql = "";
        for(String status : statusList){
            if("".equals(status)) continue;
           statuss = statuss + "'" + status + "',";
        }
        if(!"".equals(statuss)){
           statusSql = " and qu.status in (" + statuss.substring(0, statuss.length() -1) + ")";
        }
         Class entityClass = PurchaseOrderReport.class;
        String hql = "select {sac.*} from (";
        hql += "select qu.order_Date,qu.status as status, qu.src_so_no,qu.sub_Total,qu.order_no,qu.sub_Total as total,vend.vendor_name,vend.vendor_no from purchase.purchase_orders qu left join purchase.vendors vend on qu.vendor_no=vend.vendor_no where 1=1  "  + vendorSql + statusSql + dateSql +") as sac";
        if (isExport) {
            dto.setReportData(reportDao.getPSSS(hql, entityClass));
        } else {
            dto.setReportData(reportDao.getPSSS(reportDataDtoPage, hql, entityClass));
        }
        return dto;
    }

    //Work Order Summary 查询
    public ReportDataDto getWorlOrderReportDate(Page<ReportDataDto> reportDataDtoPage, ReportDataDto dto, Boolean isExport) throws Exception{
        //解析 column
        ArrayList<String> columnList = new ArrayList<String>();
        boolean delayFlag = false;
        for(int i=0; i < dto.getColumn().size(); i++){
            String columnName = dto.getColumn().get(i);
            if("".equals(columnName)) continue;
            if("status".equals(columnName.split(":")[0]) && dto.getStatus().size() > 1){
                dto.getColumn().remove(i);
                i--;
                continue;
            }
            dto.getColumn().set(i, columnName.split(":")[0]);
            columnList.add(columnName.split(":")[1]);
        }
        dto.setColumnName(columnList);
        //Date Range
        String dateSql = "";
        if(!"".equals(dto.getDataRange())){
            dateSql = getDateSql(dto, "qu.order_date");
        }
        //Period Type
        String perTypeSql = "";
        String perType = "";
        if(!"".equals(dto.getPeriodType())){
            if ("year".equals(dto.getPeriodType())) {
                perTypeSql = " group by " + dto.getPeriodType() + "(qu.order_date)";
                 perType = dto.getPeriodType() + "(qu.order_date) as per_date";
            }else if("day".equals(dto.getPeriodType())){
                perTypeSql = " group by date(qu.order_date)";
                 perType = " date(qu.order_date) as per_date ";
            }else{
                perTypeSql = " group by year(qu.order_date)," + dto.getPeriodType() + "(qu.order_date)";
                 perType = dto.getPeriodType() + "(qu.order_date)  as per_date";
            }
        }
        StringBuffer criSql = new StringBuffer("");
        List<String> statusList = dto.getStatus();
        String statuss = "";
        for(String status : statusList){
            if("".equals(status)) continue;
            if("delayed".equals(status)){
                delayFlag = true;
                continue;
            }
           statuss = statuss + "'" + status + "',";
        }
        if(!"".equals(statuss)){
           criSql.append(" and qu.status in (" + statuss.substring(0, statuss.length() -1) + ")");
        }
        //Production Department
        criSql.append(" and qu.work_center=" + dto.getWorkCenterId());
        if(dto.getDelayFrom()!=null && dto.getDelayTo() != null){
            criSql.append(" and qu.schedule_end between date_sub(current_date, interval "+dto.getDelayTo() + " " + dto.getDelayType()+") and date_sub(current_date, interval " + dto.getDelayFrom() + " "+  dto.getDelayType() +") ");
        }
        //Work Group
        if(null != dto.getWorkGroupId() &&  -1 != dto.getWorkGroupId()){
            criSql.append(" and qu.order_no in (select order_no from manufacturing.work_order_group wog where wog.work_group_id= " + dto.getWorkGroupId() + ")");
        }
        if(delayFlag){
            criSql.append(" and current_date > qu.schedule_end ");
        }
        //Product/Service Type
        if(null != dto.getPsType() && !"".equals(dto.getPsType())){
            criSql.append(" and qu.cls_id=" + dto.getPsType().split("@@")[0] + " and qu.item_type='" + dto.getPsType().split("@@")[1] + "' ");
        }
        if(null != dto.getSource() && !"".equals(dto.getSource())){
            criSql.append(" and qu.source='" + dto.getSource()+"'");
        }
        // Target Date
        if(null != dto.getTargetDateFrom() && !"".equals(dto.getTargetDateFrom())){
            criSql.append(" and date(qu.schedule_end) >='" + dto.getTargetDateFrom()+"'");
        }
        if(null != dto.getTargetDateTo() && !"".equals(dto.getTargetDateTo())){
            criSql.append(" and date(qu.schedule_end) <= '" + dto.getTargetDateTo()+"'");
        }
        String successRatioSql = "";
        String delayedRatioSql = "";
        String closedRatioSql = "";
        String hql = "";
        String workGroupSql = "(SELECT GROUP_CONCAT(DISTINCT wp.name SEPARATOR ',') FROM manufacturing.work_order_group wog,manufacturing.work_groups wp WHERE qu.order_no=wog.order_no AND wog.work_group_id=wp.work_group_id) as work_group_name ";
        if ("Y".equals(dto.getStepFlag())) {
            successRatioSql = "select " + perType + ",count(qu.order_no) as success_num,year(qu.order_date) as intYear from manufacturing.work_orders qu \n" +
                    "where qu.status in ('Closed', 'Production Terminated', 'Conditional Released', 'Product QC Failed','Document QC Failed') " + dateSql + criSql + perTypeSql;
            delayedRatioSql = "select " + perType + ", count(qu.order_no) as delayed_num,year(qu.order_date) as intYear from manufacturing.work_orders qu where qu.schedule_end < curdate() " + dateSql + criSql + perTypeSql;
            closedRatioSql = "select " + perType + ", count(qu.order_no) as closed_num,year(qu.order_date) as intYear from manufacturing.work_orders qu where qu.status='Closed' " + dateSql + criSql + perTypeSql;
            hql = "select dataTemp.per_date,dataTemp.status,dataTemp.total,dataTemp.order_num,dataTemp.work_center,wc.name as work_center_name,dataTemp.intYear,dataTemp.work_group_name from( select " + perType + ",qu.status,sum(res.cost) as total,count(qu.order_no) as order_num,qu.work_center,year(qu.order_date) as intYear," + workGroupSql + " from manufacturing.work_orders qu,manufacturing.wo_operations woo,manufacturing.wo_operation_resources wor,manufacturing.resources res \n" +
                    "where qu.order_no=woo.order_no and woo.id=wor.id and wor.id=res.resource_id " + dateSql + criSql.toString() + perTypeSql + ") as dataTemp left join manufacturing.work_centers wc on dataTemp.work_center = wc.work_center_id";
        } else {
            String coreDataCirSql = " and group_temp.catalog_no_alt=qu.catalog_no and group_temp.so_no = qu.so_no ";
            StringBuffer coreDataSql = new StringBuffer("").append("(select sum(res.cost) as cost,qu.so_no ,if(locate('-', qu.catalog_no) > 0, left(qu.catalog_no, locate('-', qu.catalog_no)-1), qu.catalog_no) as catalog_no_alt  \n" +
                    " from  manufacturing.work_orders qu, manufacturing.wo_operations woo, manufacturing.wo_operation_resources wor,manufacturing.resources res  \n" +
                    " WHERE qu.order_no=woo.order_no AND woo.id=wor.id AND wor.id=res.resource_id ").append(dateSql).append(criSql).append(" GROUP BY qu.so_no,catalog_no_alt) AS group_temp ");

            successRatioSql = "select " + perType + ",count(qu.order_no) as success_num,year(qu.order_date) as intYear from " + coreDataSql.toString() + ", manufacturing.work_orders qu \n" +
                    "where qu.status in ('Closed', 'Production Terminated', 'Conditional Released', 'Product QC Failed','Document QC Failed') " + coreDataCirSql + perTypeSql;
            delayedRatioSql = "select " + perType + ", count(qu.order_no) as delayed_num,year(qu.order_date) as intYear from " + coreDataSql.toString() + ", manufacturing.work_orders qu where qu.schedule_end < curdate() " + coreDataCirSql + perTypeSql;
            closedRatioSql = "select " + perType + ", count(qu.order_no) as closed_num,year(qu.order_date) as intYear from " + coreDataSql.toString() + ", manufacturing.work_orders qu where qu.status='Closed' " + coreDataCirSql + perTypeSql;
            hql = "select dataTemp.per_date,dataTemp.status,dataTemp.total,dataTemp.order_num,dataTemp.work_center,wc.name as work_center_name,dataTemp.intYear,dataTemp.work_group_name from( select " + perType + ",qu.status,sum(group_temp.cost) as total,count(qu.order_no) as order_num,qu.work_center,year(qu.order_date) as intYear," + workGroupSql + " from " + coreDataSql.toString() + ", manufacturing.work_orders qu " +
                    "where 1=1 " + coreDataCirSql + perTypeSql + ") as dataTemp left join manufacturing.work_centers wc on dataTemp.work_center = wc.work_center_id";
        }
        hql = datePerSqlAppend(dto, hql);
        successRatioSql = datePerSqlAppend(dto, successRatioSql);
        delayedRatioSql = datePerSqlAppend(dto, delayedRatioSql);
        closedRatioSql = datePerSqlAppend(dto, closedRatioSql);
        List successRatioList = null;
        List delayedRatioList = null;
        List closedRatioList = null;
        if (isExport) {
            dto.setReportData(reportDao.getReportByHQL(hql));
            successRatioList = reportDao.getReportByHQL(successRatioSql);
            delayedRatioList = reportDao.getReportByHQL(delayedRatioSql);
            closedRatioList = reportDao.getReportByHQL(closedRatioSql);
        } else {
            dto.setReportData(reportDao.getQuoteByHQL(reportDataDtoPage, hql));
            successRatioList = reportDao.getQuoteByHQL(reportDataDtoPage, successRatioSql);
            delayedRatioList = reportDao.getQuoteByHQL(reportDataDtoPage, delayedRatioSql);
            closedRatioList = reportDao.getQuoteByHQL(reportDataDtoPage, closedRatioSql);
        }

        for(int i= 0; i < dto.getReportData().size();i++){
            Object[] dataTemp = (Object[])dto.getReportData().get(i);
            WorkOrderSummary workOrderSummary = new WorkOrderSummary();
            //year
            workOrderSummary.setDateInt(dataTemp[0]);
            Integer dateYearInt = (Integer)dataTemp[1];
            HashMap<String, String> map = setFromToDate(dateYearInt, dto.getPeriodType(), dataTemp[0]);
            workOrderSummary.setFromDate(map.get("fromDate"));
            workOrderSummary.setToDate(map.get("toDate"));
            workOrderSummary.setStatus(convertStatusForWO("" + dataTemp[3]));
            workOrderSummary.setTotal(BigDecimal2Dto(dataTemp[4]));
            workOrderSummary.setOrderNum(Integer2Dto(dataTemp[5]));
            workOrderSummary.setWorkCenterName(String2Dto(dataTemp[6]));
            workOrderSummary.setWorkGroupName(String2Dto(dataTemp[7]));
            Double success = Object2DoubleDto(((Object[])successRatioList.get(i))[3]);
            Double delayed = Object2DoubleDto(((Object[])delayedRatioList.get(i))[3]);
            Double closed = Object2DoubleDto(((Object[])closedRatioList.get(i))[3]);
            workOrderSummary.setDelieryNum((int)closed.doubleValue());
            workOrderSummary.setDelayedNum((int)delayed.doubleValue());
            Double successRatio = success == 0 ? 0 : closed/success;
            Double delayedRatio = closed == 0 ? 0 : delayed/closed;
            Double failedRatio = 1 - successRatio;
            workOrderSummary.setSuccessRatio(successRatio);
            workOrderSummary.setDelayedRatio(delayedRatio);
            workOrderSummary.setFailedRatio(failedRatio);
            dto.getReportData().set(i, workOrderSummary);
        }
        modifyFstAndLstDate(dto);
        return dto;
    }

    //Work Order Detail 查询
    public ReportDataDto getWorlOrderDetailReportDate(Page<ReportDataDto> reportDataDtoPage, ReportDataDto dto, Boolean isExport){
        setReportColumns(dto);
        //Date Range
        String dateSql = "";
        if(!"".equals(dto.getDataRange())){
            dateSql = getDateSql(dto, "qu.order_Date");
        }
        StringBuffer criSql = new StringBuffer("");
        List<String> statusList = dto.getStatus();
        String statuss = "";
        for(String status : statusList){
            if("".equals(status)) continue;
           statuss = statuss + "'" + status + "',";
        }
        if(!"".equals(statuss)){
           criSql.append(" and qu.status in (" + statuss.substring(0, statuss.length() -1) + ")");
        }
        //delayed状态处理
        if(statuss.indexOf("delayed") >= 0){
            criSql = new StringBuffer(criSql.toString().replace(",'delayed'", ""));
            criSql.append(" and qu.schedule_end < curdate() ");
        }
        //Production Department
        criSql.append(" and qu.work_center=" + dto.getWorkCenterId());
        //Work Group
        if(null != dto.getWorkGroupId() &&  -1 != dto.getWorkGroupId()){
            criSql.append(" and qu.order_no in (select order_no from manufacturing.work_order_group wog where wog.work_group_id= " + dto.getWorkGroupId() + ")");
        }
        //Order Source
        if(null != dto.getSource() && !"".equals(dto.getSource())){
            criSql.append(" and qu.source='" + dto.getSource()+"'");
        }

        if ("Y".equals(dto.getStepFlag())) {
            criSql.append(" and LOCATE('-',qu.catalog_no)<=0");
        }

        String workGroupSql = "(SELECT GROUP_CONCAT(DISTINCT wp.name SEPARATOR ',') FROM manufacturing.work_order_group wog,manufacturing.work_groups wp WHERE dataTemp.order_no=wog.order_no AND wog.work_group_id=wp.work_group_id) as work_group_name ";

        String operationSql = "(SELECT mo.name as operation_name,wo_op.type_id,wo_op.order_no FROM (SELECT wo.order_no,wo.src_operation_id,IF(wo.status='New',0, 1) AS type_id  \n" +
                "FROM manufacturing.wo_operations wo WHERE wo.status!='Completed' ORDER BY wo.seq_no LIMIT 1) AS wo_op,manufacturing.operations mo \n" +
                "WHERE wo_op.order_no =581  AND wo_op.src_operation_id=mo.operation_id) as operation";
        StringBuffer hql = new StringBuffer("select {sac.*} from (");
        hql.append("select dataTemp.order_no, wc.name work_center_name," + workGroupSql + ",dataTemp.item_type,dataTemp.status,dataTemp.schedule_start,dataTemp.schedule_end,dataTemp.target_date as mod_com_date,woh.update_date as scd_date,woh.update_reason as scd_reason,dataTemp.actual_start,dataTemp.actual_end,\n" +
                "case when dataTemp.delay_days > 0 then  dataTemp.delay_days else 0 end as delay_days,log.request_date as cancelled_date,log.reason as cancelled_reason,operation.operation_name,operation.type_id \n" +
                " from(select  qu.order_no,qu.work_center,qu.item_type,qu.status,qu.schedule_start,qu.schedule_end,ordi.target_date,qu.actual_start,qu.actual_end,current_date - date(qu.schedule_end) as delay_days\n" +
                " from manufacturing.work_orders qu,order.mfg_order mo,order.order ord,order.order_items ordi\n" +
                "where qu.so_no=mo.order_no and mo.src_so_no=ord.order_no and qu.so_item_no=ordi.item_no and ordi.order_no=ord.order_no " + dateSql + criSql.toString() + ") as dataTemp \n" +
                "left join manufacturing.wo_status_history woh on woh.status_type='WORK_ORDER' and woh.status='Canceled' and woh.order_no=dataTemp.order_no \n" +
                "left join system.update_request_log log on log.object='OrderItem' and log.field='ScheduledDelivery' \n" +
                "left join manufacturing.work_centers wc on dataTemp.work_center = wc.work_center_id ");
        hql.append(" left join " + operationSql + " on operation.order_no=dataTemp.order_no ");
        hql.append(" ) as sac ");
        if (isExport) {
            dto.setReportData(reportDao.getPSSS(hql.toString(), WorkOrderDetail.class));
        } else {
            dto.setReportData(reportDao.getPSSS(reportDataDtoPage, hql.toString(), WorkOrderDetail.class));
        }
        for(Object workObj: dto.getReportData()){
            WorkOrderDetail workOrderDetail = (WorkOrderDetail)workObj;
            workOrderDetail.setStatus(convertStatusForWO(workOrderDetail.getStatus()));
            // 处理 operation name问题，type_id为0的是New，1 为除Completed的其他状态
            String opName = workOrderDetail.getOperationName();
            Integer type_id = workOrderDetail.getTypeId();
            if(type_id != null && type_id == 0){
                String nameTemp = opName.split(" ", 2)[1];
                opName = nameTemp.split("|", 2)[1];
                workOrderDetail.setOperationName(opName);
            }
        }
        return dto;
    }

     //Sales By .. 查询
    public ReportDataDto getSalesOrderByReportDate(Page<ReportDataDto> reportDataDtoPage, ReportDataDto dto, Boolean isExport) throws Exception{
        //解析 column
        ArrayList<String> columnList = new ArrayList<String>();
        for(int i=0; i < dto.getColumn().size(); i++){
            String columnName = dto.getColumn().get(i);
            if("".equals(columnName)) continue;
            if("status".equals(columnName.split(":")[0]) && dto.getStatus().size() > 1){
                dto.getColumn().remove(i);
                i--;
                continue;
            }
            dto.getColumn().set(i, columnName.split(":")[0]);
            columnList.add(columnName.split(":")[1]);
        }
        dto.setColumnName(columnList);
        //quotation or order
        String dateColumn = "";
        String table = "";
        if("quotation".equals(dto.getType())){
            dateColumn = "qu.quote_Date";
            table = " order.quote ";
        }else{
            dateColumn = "qu.order_Date";
            table=" order.order ";
        }
        //customer 查询
        String customerSql = "";
        if(dto.getCustomer() != null && !"".equals(dto.getCustomer())){
            customerSql = " and qu.cust_No=" + dto.getCustomer();
        }
        //Date Range
        String dateSql = "";
        if(!"".equals(dto.getDataRange())){
            dateSql = getDateSql(dto, dateColumn);
        }
        //Period Type
        String perTypeSql = "";
        String perType = "";
        if(!"".equals(dto.getPeriodType())){
            if ("year".equals(dto.getPeriodType())) {
                perTypeSql = " group by " + dto.getPeriodType() + "("+dateColumn+")";
                 perType = dto.getPeriodType() + "("+dateColumn+") as per_date";
            }else if("day".equals(dto.getPeriodType())){
                perTypeSql = " group by date(" + dateColumn+")";
                 perType = "date(" + dateColumn +") as per_date";
            }else{
                perTypeSql = " group by year("+dateColumn+")," + dto.getPeriodType() + "(" + dateColumn + ")";
                 perType = dto.getPeriodType() + "("+dateColumn+") as per_date";
            }
        }
        List<String> statusList = dto.getStatus();
        String statuss = "";
        String statusSql = "";
        for(String status : statusList){
            if("".equals(status)) continue;
           statuss = statuss + "'" + status + "',";
        }
        if(!"".equals(statuss)){
           statusSql = " and qu.status in (" + statuss.substring(0, statuss.length() -1) + ")";
        }
        String currencySql = "";
        if(!"".equals(dto.getCurrency()) && "order".equals(dto.getType())){
           currencySql = " and qu.order_currency='" + dto.getCurrency() + "'";
        }else if(!"".equals(dto.getCurrency()) && "quotation".equals(dto.getType())){
           currencySql = " and qu.quote_currency='" + dto.getCurrency() + "'";
        }
        String sql = "";
        String criSql = "";
        //Territory 查询
        if("territory".equals(dto.getReportType())){
            sql = "select "+perType+",count(qu.status) as status, sum(qu.tax) as tax,sum(qu.sub_Total) as subTotal,sum(qu.ship_Amt) as shipAmt,sum(ifnull(qu.amount,0)-ifnull(qu.discount,0)-ifnull(qu.coupon_Value,0)+ ifnull(qu.ship_Amt,0) + ifnull(qu.tax,0)) as total,year("+ dateColumn +") as intYear from "+table+" qu,customer.customer cus where cus.cust_no=qu.cust_no ";
            if(null != dto.getTerritory() && !"".equals(dto.getTerritory())){
                criSql += "and cus.sales_territory="+dto.getTerritory();
            }
        }else if ("rep".equals(dto.getReportType())) { //Sales Representative 查询
            sql = "select " + perType + ",count(qu.status) as status, sum(qu.tax) as tax,sum(qu.sub_Total) as subTotal,sum(qu.ship_Amt) as shipAmt,sum(ifnull(qu.amount,0)-ifnull(qu.discount,0)-ifnull(qu.coupon_Value,0)+ ifnull(qu.ship_Amt,0) + ifnull(qu.tax,0)) as total,year(" + dateColumn + ") as intYear from " + table + " qu,system.users us,system.department dep where  qu.sales_contact=us.user_id and dep.dept_id=us.dept_id ";
            if (null != dto.getSalesDepartment() && !"-1".equals(dto.getSalesDepartment()) && !"".equals(dto.getSalesDepartment())) {
                criSql += " and us.dept_id=" + dto.getSalesDepartment();
            }
            if (null != dto.getSalesRepresentative() && !"-1".equals(dto.getSalesRepresentative())) {
                criSql += " and us.user_id=" + dto.getSalesRepresentative();
            }
        } else if ("org".equals(dto.getReportType())) {  //Organization Type 查询
            String cirLeftColumn = ",territory.territory_code as salesTerritoryName,contact.login_name as salesContactName,tech.login_name as techSupportName,project.login_name as projectManagerName, org.name as orgName";
            String cirLeftJoin = " left join system.users contact on dataTemp.sales_contact=contact.user_id " +
                    " left join system.users tech on dataTemp.tech_support=tech.user_id " +
                    " left join system.users project on dataTemp.project_manager=project.user_id ";
            String leftOrgJoinCirSql = "";
            if (dto.getTerritory() != null && !"".equals(dto.getTerritory())) {
                criSql += " and cus.sales_territory=" + dto.getTerritory();
            }
            if (dto.getSalesManager() != null && !"".equals(dto.getSalesManager())) {
                criSql += " and qu.sales_contact=" + dto.getSalesManager();
            }
            if (dto.getTechManager() != null && !"".equals(dto.getTechManager())) {
                criSql += " and qu.tech_support=" + dto.getTechManager();
            }
            if (dto.getProjectManager() != null && !"".equals(dto.getProjectManager())) {
                criSql += " and qu.project_manager=" + dto.getProjectManager();
            }
            if (dto.getOrgType() != null && !"".equals(dto.getOrgType())) {
                leftOrgJoinCirSql += " and o.org_type_id=" + dto.getOrgType();
            }
            if (dto.getOrgId() != null && !"".equals(dto.getOrgId())) {
                criSql += " and cus.org_id=" + dto.getOrgId();
            }
            if (null != dto.getOrgName() && !"".equals(dto.getOrgName())) { //Organization Name 查询
                leftOrgJoinCirSql += " and o.org_id=" + dto.getOrgName();
            }
            String customerLeft = " LEFT JOIN (select o.org_id,o.org_type_id,o.name,type.name as org_type_name from customer.organization o,organization_type type where type.org_type_id=o.org_type_id " + leftOrgJoinCirSql + ") as org ON org.org_id=dataTemp.org_id \n" +
                    "LEFT JOIN sales_territory territory ON dataTemp.sales_territory=territory.territory_id ";
            sql = "select dataTemp.per_date,dataTemp.status,dataTemp.tax,dataTemp.subTotal,dataTemp.shipAmt,dataTemp.total" + cirLeftColumn + ",org.org_type_name,dataTemp.cust_no,dataTemp.tempFiled,dataTemp.intYear from (";
            sql += "select " + perType + ",count(qu.status) as status, sum(qu.tax) as tax,sum(qu.sub_Total) as subTotal,sum(qu.ship_Amt) as shipAmt,sum(ifnull(qu.amount,0)-ifnull(qu.discount,0)-ifnull(qu.coupon_Value,0)+ ifnull(qu.ship_Amt,0) + ifnull(qu.tax,0)) as total" +
                    ",qu.sales_contact,qu.tech_support,qu.project_manager,cus.org_id,cus.sales_territory" + ",cus.cust_no, '1' as tempFiled,year(" + dateColumn + ") as intYear " +
                    " from " + table + " qu,customer.customer cus where qu.cust_no=cus.cust_no " +  statusSql + currencySql + dateSql + customerSql + criSql + perTypeSql + ") as dataTemp " +
                    cirLeftJoin + customerLeft + " where 1=1 ";
        }
        //Country 查询
        if(null != dto.getCountry() && !"".equals(dto.getCountry())){
            sql = "select "+perType+",qu.status as status, sum(qu.tax) as tax,sum(qu.sub_Total) as subTotal,sum(qu.ship_Amt) as shipAmt,sum(ifnull(qu.amount,0)-ifnull(qu.discount,0)-ifnull(qu.coupon_Value,0)+ ifnull(qu.ship_Amt,0) + ifnull(qu.tax,0)) as total,year("+ dateColumn +") as intYear from "+ table +" qu,customer.customer cus  where qu.cust_no=cus.cust_no ";
            if(!"-1".equals(dto.getCountry())){
                criSql += " and cus.country='"+ dto.getCountry() +"'";
            }
        }
        String hql = "";
        if ("org".equals(dto.getReportType())) {
           hql = sql;
        }else{
           hql = sql + statusSql + currencySql + dateSql + customerSql + criSql + perTypeSql;
        }
        hql = datePerSqlAppend(dto, hql);
        if (isExport) {
            dto.setReportData(reportDao.getReportByHQL(hql));
        } else {
            dto.setReportData(reportDao.getQuoteByHQL(reportDataDtoPage, hql));
        }
        if ("org".equals(dto.getReportType())) {
            for (int i = 0; i < dto.getReportData().size(); i++) {
                Object[] dataTemp = (Object[]) dto.getReportData().get(i);
                QutationDetail qutationDetail = new QutationDetail();
                //year
                qutationDetail.setDateInt(dataTemp[0]);
                Integer dateYearInt = (Integer) dataTemp[1];
                HashMap<String, String> map = setFromToDate(dateYearInt, dto.getPeriodType(), dataTemp[0]);
                qutationDetail.setFromDate(map.get("fromDate"));
                qutationDetail.setToDate(map.get("toDate"));
                qutationDetail.setStatus(convertStatus1("" + dataTemp[3]));
                qutationDetail.setTax(BigDecimal2Dto(dataTemp[4]));
                qutationDetail.setSubTotal(BigDecimal2Dto(dataTemp[5]));
                qutationDetail.setShipAmt((Double) (BigDecimal2Dto(dataTemp[6])).doubleValue());
                qutationDetail.setAmount(BigDecimal2Dto(dataTemp[7]));
                qutationDetail.setSalesTerritoryName(String2Dto(dataTemp[8]));
                qutationDetail.setSalesContactName(String2Dto(dataTemp[9]));
                qutationDetail.setTechSupportName(String2Dto(dataTemp[10]));
                qutationDetail.setProjectManagerName(String2Dto(dataTemp[11]));
                qutationDetail.setOrgName(String2Dto(dataTemp[12]));
                qutationDetail.setOrg_type_name(String2Dto(dataTemp[13]));
                qutationDetail.setCustNo(Integer2Dto(dataTemp[14]));
                dto.getReportData().set(i, qutationDetail);
            }
        } else {
            for (int i = 0; i < dto.getReportData().size(); i++) {
                Object[] dataTemp = (Object[]) dto.getReportData().get(i);
                QutationDetail qutationDetail = new QutationDetail();
                //year
                qutationDetail.setDateInt(dataTemp[0]);
                Integer dateYearInt = (Integer) dataTemp[1];
                HashMap<String, String> map = setFromToDate(dateYearInt, dto.getPeriodType(), dataTemp[0]);
                qutationDetail.setFromDate(map.get("fromDate"));
                qutationDetail.setToDate(map.get("toDate"));
                qutationDetail.setStatus(convertStatus1("" + dataTemp[3]));
                qutationDetail.setTax(BigDecimal2Dto(dataTemp[4]));
                qutationDetail.setSubTotal(BigDecimal2Dto(dataTemp[5]));
                qutationDetail.setShipAmt((Double) (BigDecimal2Dto(dataTemp[6])).doubleValue());
                qutationDetail.setAmount(BigDecimal2Dto(dataTemp[7]));
                dto.getReportData().set(i, qutationDetail);
            }
        }
        modifyFstAndLstDate(dto);
        return dto;
    }

    //Receipt Detail
    public ReportDataDto getReceiptDetailReportDate(Page<ReportDataDto> reportDataDtoPage, ReportDataDto dto, Boolean isExport){
         setReportColumns(dto);
        //Date Range
        String dateSql = "";
        if(!"".equals(dto.getDataRange())){
            dateSql = getDateSql(dto, "log.reveiving_date");
        }
        String criSql = "";
        //Vendor
        if(dto.getVendorId()!=null && dto.getVendorId() !=-1){
           criSql += " and por.vendor_no='" + dto.getVendorId() + "'";
        }
        //Receiving Clerk
        if(dto.getReceivingClerk() != null && -1 != dto.getReceivingClerk()){
            criSql += " and log.received_by=" + dto.getReceivingClerk();
        }
        //Purchase Order #
        if(dto.getPurchaseOrderNo() != null && !"".equals(dto.getPurchaseOrderNo().trim())){
            criSql += " and por.order_no=" + dto.getPurchaseOrderNo();
        }
        //Sales Order Ref
        if(dto.getSalesOrderNo() != null && !"".equals(dto.getSalesOrderNo().trim())){
            criSql += " and por.src_so_no=" + dto.getSalesOrderNo();
        }
        String hql = "select {sac.*} from (";
        hql += "select log.id,por.order_no as purchase_order_no,por.order_date as purchase_order_date,por.src_so_no as sales_order_no,ord.order_date as sales_order_date,vend.vendor_name,stor.name as storage_name, \n" +
                "log.item_no,log.qty_received,log.size_received,log.storage_id,log.tracking_no,log.reveiving_date as receiving_date,us.login_name as receiving_clerk  \n" +
                "from inventory.receiving_logs log,purchase.purchase_orders por,order.order ord,shipping.shipping_clerks cl,system.users us,purchase.vendors vend,inventory.storages stor\n" +
                "where por.order_no=log.order_no and log.order_type='Purchase Order' and ord.order_no=por.src_so_no\n" +
                "and  cl.clerk_id=us.user_id and cl.clerk_function in ('ALL', 'Receiver') and cl.clerk_id = log.received_by and vend.vendor_no=por.vendor_no and stor.storage_id=log.storage_id "  + criSql + dateSql ;
        hql += ") as sac ";
        if (isExport) {
            dto.setReportData(reportDao.getPSSS(hql, ReceiptDetail.class));
        } else {
            dto.setReportData(reportDao.getPSSS(reportDataDtoPage, hql, ReceiptDetail.class));
        }
        return dto;
    }

    //Shipment Detail
    public ReportDataDto getShipmentDetailReportDate(Page<ReportDataDto> reportDataDtoPage, ReportDataDto dto, Boolean isExport){
         setReportColumns(dto);
        //Date Range
        String dateSql = "";
        if(!"".equals(dto.getDataRange())){
            dateSql = getDateSql(dto, "pak.shipment_date");
        }
        StringBuffer criSql = new StringBuffer("");
        //Customer
        if(dto.getCustomer()!=null && !"".equals(dto.getCustomer())){
           criSql.append(" and cust.cust_no=" + dto.getCustomer());
        }
        //Organization
        if(dto.getOrgId() != null && !"".equals(dto.getOrgId())){
            criSql.append(" and cust.org_id=" + dto.getOrgId());
        }
        //Tracking#
        if(dto.getTrackingNo() != null && !"".equals(dto.getTrackingNo())){
            criSql.append(" and pak.tracking_no like '%" + dto.getTrackingNo() + "%'");
        }
        //Sales Order #
        if(dto.getSalesOrderNo() != null && !"".equals(dto.getSalesOrderNo().trim())){
            criSql.append(" and pak_line.order_no like '%" + dto.getSalesOrderNo() + "%'");
        }
        //Shipping Carrier
        if(dto.getShippingCarrierId() != null && -1 != dto.getShippingCarrierId()){
            criSql.append(" and sm.carrier in (select carrier_code from shipping.ship_carriers where carrier_id=" + dto.getShippingCarrierId() + ") ");
        }else {
            criSql.append("  and sm.carrier in (select carrier_code from shipping.ship_carriers) ");
        }
        //Shipping Clerk
        if(dto.getReceivingClerk() != null && -1 != dto.getReceivingClerk()){
            criSql.append(" and pak.shipping_clerk=" + dto.getReceivingClerk());
        }
        //Shipper Clerk
        if(dto.getShipperClerk() != null && -1 != dto.getShipperClerk()){
            criSql.append(" and pak.send_by=" + dto.getShipperClerk());
        }
        String hql = "select {sac.*},pl.item_no from (";
        hql += "select leftTable.package_id,leftTable.order_no as sales_order_no,leftTable.order_date as sales_order_date,leftTable.item_no as item_no,leftTable.qty_shipped,leftTable.size_shipped,leftTable.tracking_no,leftTable.shipment_date,leftTable.shipping_carrier,leftTable.shipping_clerk,leftTable.customer_name,leftTable.organization_name,leftTable.ship_to,clerk.login_name as ship_clerk,leftTable.shipper \n" +
                "from (select pak.package_id,pak_line.order_no,ord.order_date,ord.cust_no,pak_line.item_no,pak_line.quantity as qty_shipped,pak_line.size as size_shipped,pak.tracking_no,pak.shipment_date,sm.name as shipping_carrier,pak.shipping_clerk,pak.rcp_country as ship_to,concat(cust.first_name,' ',cust.mid_name,' ', cust.last_name) as customer_name,org.name as organization_name, \n" +
                " (select employee_name from system.users us,system.employee ep where us.user_id=pak.send_by and us.employee_id=ep.employee_id) as shipper " +
                "from shipping.ship_packages pak,shipping.ship_package_lines pak_line,order.order ord,shipping.ship_method sm,customer.customer cust,customer.organization org \n" +
                "where pak.package_id=pak_line.package_id and pak_line.order_no=ord.order_no and pak.ship_method=sm.method_id and cust.cust_no=ord.cust_no and cust.org_id=org.org_id \n" + criSql.toString() + dateSql +
                ") as leftTable \n" +
                "left join (select cl.clerk_id,us.login_name from shipping.shipping_clerks cl,system.users us where cl.clerk_id=us.user_id and cl.clerk_function in ('ALL', 'Receiver')) clerk on clerk.clerk_id=leftTable.shipping_clerk "   ;
        hql += ") as sac,shipping.ship_package_lines pl where pl.order_no=sac.sales_order_no and sac.package_id=pl.package_id and sac.item_no=pl.item_no ";
        if (isExport) {
            dto.setReportData(reportDao.getShipmentDetail(hql));
        } else {
            dto.setReportData(reportDao.getShipmentDetail(reportDataDtoPage, hql));
        }
        return dto;
    }

    //Coupon & Gift Card Summary 查询
    //@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public ReportDataDto getCouponCardReportDate(Page<ReportDataDto> reportDataDtoPage, ReportDataDto dto, Boolean isExport) throws Exception{
        setReportColumns(dto);
        //Date Range
        String dateSql = "";
        if(!"".equals(dto.getDataRange())){
            dateSql = getDateSql(dto, "qu.order_date");
        }
        String criSql = "";
        List<String> statusList = dto.getStatus();
        String statuss = "";
        for(String status : statusList){
            if("".equals(status)) continue;
           statuss = statuss + "'" + status + "',";
        }
        if(!"".equals(statuss)){
           criSql += " and qu.status in (" + statuss.substring(0, statuss.length() -1) + ")";
        }
        if(!"".equals(dto.getCurrency())){
           criSql += " and qu.order_currency='" + dto.getCurrency() + "'";
        }
         String hql = "select {sac.*} from (";
        if(dto.getType() == null) dto.setType("");
        if("".equals(dto.getType()) || "coupon".equals(dto.getType())){
            hql += "select qu.order_no as id,'Coupon' as discount_type,qu.status,sum(ifnull(qu.amount,0)-ifnull(qu.discount,0)-ifnull(qu.coupon_Value,0)+ ifnull(qu.ship_Amt,0) + ifnull(qu.tax,0)) as total,sum(discount) as discount_total from order.order qu,customer.coupon co where qu.coupon_id=co.id\n " + criSql + dateSql +
                    "group by qu.order_no ";
        }
        if("".equals(dto.getType())){
            hql += " Union ";
        }
        if("".equals(dto.getType()) || "gift".equals(dto.getType())){
            hql += " select qu.order_no as id,'Gift Card' as discount_type,qu.status,sum(ifnull(qu.amount,0)-ifnull(qu.discount,0)-ifnull(qu.coupon_Value,0)+ ifnull(qu.ship_Amt,0) + ifnull(qu.tax,0)) as total,sum(discount) as discount_total from order.order qu,customer.gift_card gc where qu.order_no=gc.order_no \n" + criSql + dateSql +
                    "group by qu.order_no ";
        }
        hql += " ) as sac ";
        //hql = datePerSqlAppend(dto, hql);
        if (isExport) {
            dto.setReportData(reportDao.getPSSS(hql, CouponGift.class));
        } else {
            dto.setReportData(reportDao.getPSSS(reportDataDtoPage, hql, CouponGift.class));
        }
        for(Object obj : dto.getReportData()){
            CouponGift couponGift = (CouponGift)obj;
            couponGift.setStatus(convertStatus(couponGift.getStatus()));
        }
        //modifyFstAndLstDate(dto);
        return dto;
    }

    //Sales Promotions
    public ReportDataDto getSalesPromotionReportDate(Page<ReportDataDto> reportDataDtoPage, ReportDataDto dto, Boolean isExport){
         setReportColumns(dto);
        //Date Range
        String dateSql = "";
        if(!"".equals(dto.getDataRange())){
            dateSql = getDateSql(dto, "log.reveiving_date");
        }
        String criSql = "";
        //Vendor
        if(dto.getVendorId()!=null && dto.getVendorId() !=-1){
           criSql += " and por.vendor_no='" + dto.getVendorId() + "'";
        }
        //Receiving Clerk
        if(dto.getReceivingClerk() != null && -1 != dto.getReceivingClerk()){
            criSql += " and log.received_by=" + dto.getReceivingClerk();
        }
        //Purchase Order #
        if(dto.getPurchaseOrderNo() != null && !"".equals(dto.getPurchaseOrderNo().trim())){
            criSql += " and por.order_no=" + dto.getPurchaseOrderNo();
        }
        //Sales Order Ref
        if(dto.getSalesOrderNo() != null && !"".equals(dto.getSalesOrderNo().trim())){
            criSql += " and por.src_so_no=" + dto.getSalesOrderNo();
        }
        String hql = "select {sac.*} from (";
        hql += "select log.id,por.order_no as purchase_order_no,por.order_date as purchase_order_date,por.src_so_no as sales_order_no,ord.order_date as sales_order_date,vend.vendor_name,stor.name as storage_name, \n" +
                "log.item_no,log.qty_received,log.size_received,log.storage_id,log.tracking_no,log.reveiving_date as receiving_date,us.login_name as receiving_clerk  \n" +
                "from inventory.receiving_logs log,purchase.purchase_orders por,order.order ord,shipping.shipping_clerks cl,system.users us,purchase.vendors vend,inventory.storages stor\n" +
                "where por.order_no=log.order_no and log.order_type='Purchase Order' and ord.order_no=por.src_so_no\n" +
                "and  cl.clerk_id=us.user_id and cl.clerk_function in ('ALL', 'Receiver') and cl.clerk_id = log.received_by and vend.vendor_no=por.vendor_no and stor.storage_id=log.storage_id "  + criSql + dateSql ;
        hql += ") as sac ";
        if (isExport) {
            dto.setReportData(reportDao.getPSSS(hql, ReceiptDetail.class));
        } else {
            dto.setReportData(reportDao.getPSSS(reportDataDtoPage, hql, ReceiptDetail.class));
        }
        return dto;
    }

    /*String getDateSql(ReportDataDto dto, String dateCoulumn){
        String type = dto.getDataRange();
        String dateSql = "";
        if("year".equals(type)){ //this year
            dateSql = " and year(" + dateCoulumn + ")=year(current_date) ";
        }else if("quarter".equals(type)){//this quarter
            dateSql = " and year(" + dateCoulumn + ")=year(current_date) and quarter(" + dateCoulumn + ")=quarter(current_date)";
        }else if("month".equals(type)){//this month
            dateSql = " and year(" + dateCoulumn + ")=year(current_date) and month(" + dateCoulumn + ")=month(current_date)";
        }else if("week".equals(type)){ //this week
            dateSql = " and year(" + dateCoulumn + ")=year(current_date) and month(" + dateCoulumn + ")=month(current_date) and week(" + dateCoulumn + ")=week(current_date)";
        }else if("today".equals(type)){ //today
            dateSql = " and date(" + dateCoulumn + ") = current_date";
        }else if("yesterday".equals(type)){ //yesterday
            dateSql = " and date(" + dateCoulumn + ")=date_sub(current_date, interval 1 day)";
        }else if("lWeek".equals(type)){ //last week
            dateSql = " and date(" + dateCoulumn + ")=date_sub(current_date, interval 1 week)";
        }else if("lMonth".equals(type)){ //last month
            dateSql = " and date(" + dateCoulumn + ")=date_sub(current_date, interval 1 month)";
        }else if("lQuarter".equals(type)){ //last quarter
            dateSql = " and date(" + dateCoulumn + ")=date_sub(current_date, interval 1 quarter)";
        }else if("lYear".equals(type)){ //last year
            dateSql = " and date(" + dateCoulumn + ")=date_sub(current_date, interval 1 year)";
        }else if("custom".equals(type)){ //custom date
            dateSql = " and date(" + dateCoulumn + ") between '" + dto.getDataFrom() + "' and '" + dto.getDataTo() + "'";
        }
        return dateSql;
    }*/
    public String getDateSql(ReportDataDto dto, String dateCoulumn){
        HashMap map = getDateFromTo(dto);
        if(map.get("fromDate")==null) return "";
        dto.setDataFrom(DateUtils.formatDate2Str((Date)map.get("fromDate")));
        dto.setDataTo(DateUtils.formatDate2Str((Date) map.get("toDate")));
        String sql = " and date(" + dateCoulumn + ") >= '" + dto.getDataFrom() + "' and date(" + dateCoulumn + ") <= '" + dto.getDataTo() + "' ";
        return sql;
    }

    public HashMap<String, Date> getDateFromTo(ReportDataDto dto){
        String dataRange = dto.getDataRange();
        Date fromDate = null;// 开始时间
	    Date toDate = null;// 结束时间
        if ("yesterday".equals(dataRange)) {
            Calendar cal = Calendar.getInstance();
            cal.add(cal.DATE, -1);
            fromDate = cal.getTime();
            toDate = Calendar.getInstance().getTime();
        }else if ("today".equals(dataRange)) {
            Calendar cal = Calendar.getInstance();
            fromDate = DateUtils.formatStr2Date(DateUtils.formatDate2Str(cal.getTime(), DateUtils.C_DATE_PATTON_DEFAULT) , DateUtils.C_DATE_PATTON_DEFAULT);
            toDate = DateUtils.formatStr2Date(DateUtils.formatDate2Str(Calendar.getInstance().getTime(), DateUtils.C_DATE_PATTON_DEFAULT), DateUtils.C_DATE_PATTON_DEFAULT);
        } else if ("lWeek".equals(dataRange)) {
            fromDate = DateUtils.formatStr2Date(DateUtils.getPreviousMonday(), DateUtils.C_DATE_PATTON_DEFAULT);
            toDate = DateUtils.formatStr2Date(DateUtils.getPreviousSunday(), DateUtils.C_DATE_PATTON_DEFAULT);
        } else if ("week".equals(dataRange)) {
            fromDate = DateUtils.formatStr2Date(DateUtils.getCurrentMonday(), DateUtils.C_DATE_PATTON_DEFAULT);
            toDate = DateUtils.getWeekEndDay(new Date());
        } else if ("lMonth".equals(dataRange)) {
            fromDate = DateUtils.formatStr2Date(DateUtils.getLastMonth(), DateUtils.C_DATE_PATTON_DEFAULT);
            toDate = DateUtils.formatStr2Date(DateUtils.getLastDayOfMonth(fromDate), DateUtils.C_DATE_PATTON_DEFAULT);
        } else if ("month".equals(dataRange)) {
            fromDate = DateUtils.formatStr2Date(DateUtils.getFirstDayOfMonth(new Date()), DateUtils.C_DATE_PATTON_DEFAULT);
            toDate = DateUtils.formatStr2Date(DateUtils.getLastDayOfMonth(new Date()), DateUtils.C_DATE_PATTON_DEFAULT);
        } else if ("lQuarter".equals(dataRange)) {
            fromDate = DateUtils.getQuarterStartDay(DateUtils.defineMonthBefore2Date(new Date(), -3));
            toDate = DateUtils.getQuarterEndDay(DateUtils.defineMonthBefore2Date(new Date(), -3));
        } else if ("quarter".equals(dataRange)) {
            fromDate = DateUtils.getQuarterStartDay(new Date());
            toDate = DateUtils.getQuarterEndDay(new Date());
        } else if ("lYear".equals(dataRange)) {
            fromDate = DateUtils.getYearStartDay(DateUtils.defineMonthBefore2Date(new Date(), -12));
            toDate = DateUtils.getYearEndDay(DateUtils.defineMonthBefore2Date(new Date(), -12));
        } else if ("year".equals(dataRange)) {
            fromDate = DateUtils.getYearStartDay(new Date());
            toDate = DateUtils.getYearEndDay(new Date());
        }else if("custom".equals(dataRange)){ //custom date
            fromDate = DateUtils.formatStr2Date(dto.getDataFrom(), "yyyy-MM-dd");
            toDate = DateUtils.formatStr2Date(dto.getDataTo(), "yyyy-MM-dd");
        }
        HashMap<String, Date> map = new HashMap<String, Date>();
        map.put("fromDate", fromDate);
        map.put("toDate", toDate);
        return map;
    }

    //summary 赋值 fromDate toDate
    public HashMap<String, String> setFromToDate(int year, String perType, Object quote_dateObj){
//        Calendar cal = DateUtils.date2Calendar(fromDate);
//        int year = cal.get(Calendar.YEAR);
        HashMap<String, String> map = new HashMap<String, String>();
        //对于 day 而言，quote_date为Date类型
        if("day".equals(perType)){
            String dateTemp = DateUtils.formatDate2Str((Date)quote_dateObj, "yyyy-MM-dd");
            map.put("fromDate", dateTemp);
            map.put("toDate", dateTemp);
            return map;
        }
        // week, month, quarter
        Integer quote_date = (Integer)quote_dateObj;
        if("week".equals(perType)){
            Calendar ca = weekCalendar(year, quote_date);
            map.put("fromDate", DateUtils.formatDate2Str(DateUtils.calendar2Date(ca), "yyyy-MM-dd"));
            ca.add(Calendar.DAY_OF_WEEK, 6);
            map.put("toDate", DateUtils.formatDate2Str(ca.getTime(), "yyyy-MM-dd"));
        }else if("month".equals(perType)){
            String dateFromTemp = year + "-" + quote_date + "-01";
            String dateToTemp = year + "-" + quote_date + "-" + DateUtils.findMaxDayInMonth(quote_date);
            map.put("fromDate", dateFromTemp);
            map.put("toDate", dateToTemp);
        }else if("quarter".equals(perType)){
            String fromDateTemp = year + "-" + 3 * quote_date + 2 + "-01";
            String toDateTemp = "";
            if(quote_date == 1 || quote_date == 4){
                toDateTemp = year + "-" + 3 * quote_date + "-31";
            }else{
                toDateTemp = year + "-" + 3 * quote_date + "-01";
            }
            map.put("fromDate", fromDateTemp);
            map.put("toDate", toDateTemp);
        }else if("year".equals(perType)){
            map.put("fromDate", year + "-01-31");
            map.put("toDate", year + "-12-31");
        }
        return map;
    }

    //取得某周第一天时间
    public Calendar weekCalendar(int year, int week){
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DATE, 1);
        Calendar c1 = (GregorianCalendar)cal.clone();
        c1.add(Calendar.DATE, week*7);
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(c1.getTime());
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
        return c;
    }

    //status 状态转换
    String convertStatus(String status){
        if("OP".equals(status)){
            return "Open";
        }else if("NW".equals(status)){
            return "Open";
        }else if("CW".equals(status)){
            return "Closed w/ Order";
        }else if("CO".equals(status)){
            return "Closed w/o Order";
        }else if("Closed".equals(status)){
            return "Closed";
        }else if("CC".equals(status)){
            return "Customer Confirmed";
        }else if("CO".equals(status)){
            return "Vendor Confirmed";
        }else if("SH".equals(status)){
            return "Fully Shipped";
        }else if("CN".equals(status)){
            return "Cancelled";
        }else if("VC".equals(status)){
            return "Vendor Confirmed";
        }
        return "";
    }

    //Work Order Summary status 转换
    String convertStatusForWO(String status){
        if("New".equals(status)){
            return "New";
        }else if("In Production".equals(status)){
            return "In Production";
        }else if("Production Completed".equals(status)){
            return "Production Completed";
        }else if("Closed".equals(status)){
            return "Closed";
        }else if("Closed w/PT".equals(status)){
            return "Production Terminated";
        }else if("Closed w/CR".equals(status)){
            return "Conditional Released";
        }else if("Product QC Failed".equals(status)){
            return "Product QC Failed";
        }else if("Document QC Failed".equals(status)){
            return "Document QC Failed";
        }else if("Canceled".equals(status)){
            return "Canceled";
        }
        return "";
    }

    String convertStatus1(String status){
        if("OP".equals(status)){
            return "Open";
        }else if("CC".equals(status)){
            return "Customer Confirmed";
        }else if("CO".equals(status)){
            return "Vendor Confirmed";
        }else if("SH".equals(status)){
            return "Fully Shipped";
        }else if("CN".equals(status)){
            return "Cancelled";
        }else if("VC".equals(status)){
            return "Vendor Confirmed";
        }
        return "";
    }

    public void modifyFstAndLstDate(ReportDataDto dto) throws Exception{
       //修正第一条数据的fromDate日期 和 最后一条数据的toDate
        if(null == dto.getDataRange() || !"week".equals(dto.getPeriodType()) || "".equals( dto.getDataRange()) || "today".equals( dto.getDataRange()) || "yesterday".equals( dto.getDataRange()) || "week".equals( dto.getDataRange()) || "lWeek".equals( dto.getDataRange()))
            return;
        HashMap<String, Date> map = getDateFromTo(dto);
        if(dto.getReportData().size() > 0){
            Object fstDate = dto.getReportData().get(0);
            Object lstDate = dto.getReportData().get(dto.getReportData().size() - 1);
            if (map.get("fromDate").after(DateUtils.formatStr2Date(BeanUtils.getProperty(fstDate, "fromDate"), "yyyy-MM-dd"))) {
                BeanUtils.setProperty(fstDate, "fromDate", DateUtils.formatDate2Str(map.get("fromDate"), "yyyy-MM-dd"));
            }
            if (map.get("toDate").before(DateUtils.formatStr2Date(BeanUtils.getProperty(lstDate, "toDate"), "yyyy-MM-dd"))) {
                BeanUtils.setProperty(lstDate, "toDate", DateUtils.formatDate2Str(map.get("toDate"), "yyyy-MM-dd"));
            }
        }
    }

    //创建没有数据记录的日期区间
    public String datePerSqlAppend(ReportDataDto dto, String sql){
        String allSql = "";
        StringBuffer appendSql = new StringBuffer("select distinct * from (select ");
        if("day".equals(dto.getPeriodType())){
            appendSql.append("date_list.date ");
        }else{
            appendSql.append(dto.getPeriodType()+"(date_list.date) ");
        }
        appendSql.append("as per_date1,year(date_list.date) as year_date from common.date_list ");
        if (!"".equals(dto.getDataFrom())) {
            appendSql.append("where date>='" + dto.getDataFrom() + "' and date<='" + dto.getDataTo() + "'") ;
        }
        appendSql.append( " ) as date_list left join ( ");
        appendSql.append(sql);
        appendSql.append(")as date_temp on date_list.per_date1=date_temp.per_date and date_list.year_date=date_temp.intYear");
        allSql = appendSql.toString();
        return allSql;
    }

    //-------------------------------数据值转换 ---------------------------
    public BigDecimal BigDecimal2Dto(Object data){
        if(data == null){
            return new BigDecimal(0);
        }else{
            return (BigDecimal)data;
        }
    }

    public Integer Integer2Dto(Object data){
        if(data == null){
            return 0;
        }else if(data instanceof Integer){
            return (Integer)data;
        }else{
            return Integer.parseInt(data.toString());
        }
    }

    public Double Double2Dto(Object data){
        if(data == null){
            return 0.0;
        }else{
            return (Double)data;
        }
    }

    public Double Object2DoubleDto(Object data){
        if(data == null){
            return 0.0;
        }else{
            return Double.parseDouble(data.toString());
        }
    }

    public String String2Dto(Object data){
        if(data == null){
            return "";
        }else{
            return data.toString();
        }
    }
}
