package com.genscript.gsscm.report.dao;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.basedata.dto.DropDownDTO;
import com.genscript.gsscm.order.entity.OrderMain;
import com.genscript.gsscm.quote.entity.QuoteMain;
import com.genscript.gsscm.quote.entity.QuoteProcessLog;
import com.genscript.gsscm.report.dto.ReportDataDto;
import com.genscript.gsscm.report.entity.OrderDetail;
import com.genscript.gsscm.report.entity.ProudctServiceReport;
import com.genscript.gsscm.report.entity.QutationDetail;
import com.genscript.gsscm.report.entity.ShipmentDetail;
import org.apache.struts2.ServletActionContext;
import org.dozer.DozerBeanMapper;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.hql.FilterTranslator;
import org.hibernate.hql.QueryTranslator;
import org.hibernate.hql.classic.QueryTranslatorImpl;
import org.hibernate.impl.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by zhanghuibin
 * User: Administrator
 * Date: 7/5/11
 * Time: 5:32 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class ReportDao extends HibernateDao<Object, Integer> {
    @Autowired
    private DozerBeanMapper dozer;

    //add by zhanghuibin ,Report查询
    public List getQuoteByHQL(Page<ReportDataDto> reportDataDtoPage, String hql){
        int pageIndex = reportDataDtoPage.getPageNo();
        int pageSize = reportDataDtoPage.getPageSize();
        //pageIndex 为当前页数，pageSize为页显示数
        final int items = (pageIndex-1) * pageSize;
        Query query = this.getSession().createSQLQuery(hql);
        //定义从第几条开始查询
        query.setFirstResult(items);
        //定义返回的纪录数
        query.setMaxResults(pageSize);

        String totalCount = this.getSession().createSQLQuery("select count(*) from (" + hql + " ) tmp_count_t ").uniqueResult().toString();
        reportDataDtoPage.setTotalCount(Long.parseLong(totalCount));
        query.setMaxResults(pageSize);
        List quoteList = query.list();
        //逐出数据
        this.getSession().flush();
        this.getSession().clear();
        return quoteList;
    }

    //add by zhanghuibin Report导出查询
    public List getReportByHQL(String hql){
        Query query = this.getSession().createSQLQuery(hql);
        List reportData = query.list();
        //逐出数据
        this.getSession().flush();
        this.getSession().clear();
        return reportData;
    }

    //add by zhanghuibin 取得fromDate
     public Date getReportFromDateByHQL(String hql){
        return (Date)this.getSession().createSQLQuery(hql).uniqueResult();
    }

    //add by zhanghuibin
     public Object getUniqueByHQL(String sql){
        return this.getSession().createSQLQuery(sql).uniqueResult();
    }

    //add by zhanghuibin summmary



    //add by zhanghuibin
    public String getCountSql(String originalHql){
        SessionFactoryImpl sessionFactoryImp = (SessionFactoryImpl)this.getSessionFactory();
        FilterTranslator filterTrans = sessionFactoryImp.getSettings().getQueryTranslatorFactory().createFilterTranslator(originalHql, originalHql, Collections.EMPTY_MAP, sessionFactoryImp);
        filterTrans.compile(Collections.EMPTY_MAP, false);
//        QueryTranslatorImpl queryTranslator = new QueryTranslatorImpl(originalHql, originalHql, Collections.EMPTY_MAP, (org.hibernate.engine.SessionFactoryImplementor)sessionFactory);
//        queryTranslator.compile(Collections.EMPTY_MAP, false);
        return "select count(*) from (" + filterTrans.getSQLString() + ") tmp_count_t";
    }

     //add by zhanghuibin ,Report SQL查询
    public List getQuoteBySQL(Page<ReportDataDto> reportDataDtoPage, String hql, String entityClassName, Class entityClass){
        int pageIndex = reportDataDtoPage.getPageNo();
        int pageSize = reportDataDtoPage.getPageSize();
        //pageIndex 为当前页数，pageSize为页显示数
        final int items = (pageIndex-1) * pageSize;
        Query query = this.getSession().createSQLQuery(hql).addEntity(entityClassName, entityClass);
        //定义从第几条开始查询
        query.setFirstResult(items);
        //定义返回的纪录数
        query.setMaxResults(pageSize);

        String totalCount = this.getSession().createSQLQuery(getCountSql(hql)).uniqueResult().toString();
        reportDataDtoPage.setTotalCount(Long.parseLong(totalCount));
        query.setMaxResults(pageSize);
        List quoteList = query.list();
        //逐出数据
        this.getSession().flush();
        this.getSession().clear();
        return quoteList;
    }


     //add by zhanghuibin ,Quotation Detail Report SQL查询
    public List getQuoteDetailBySQL(Page<ReportDataDto> reportDataDtoPage, String hql, String sql){
        int pageIndex = reportDataDtoPage.getPageNo();
        int pageSize = reportDataDtoPage.getPageSize();
        //pageIndex 为当前页数，pageSize为页显示数
        final int items = (pageIndex-1) * pageSize;
        Query query = this.getSession().createSQLQuery(hql).addEntity("qu", QutationDetail.class).addScalar("salesTerritoryName").addScalar("salesContactName", Hibernate.STRING).addScalar("techSupportName")
                .addScalar("projectManagerName").addScalar("orgName");
        //定义从第几条开始查询
        query.setFirstResult(items);
        //定义返回的纪录数
        query.setMaxResults(pageSize);

        String totalCount = this.getSession().createSQLQuery("select count(*) from ( " +sql + ") tmp_count_t").uniqueResult().toString();
        reportDataDtoPage.setTotalCount(Long.parseLong(totalCount));
        query.setMaxResults(pageSize);
        List quoteList = query.list();
        //逐出数据
        this.getSession().flush();
        this.getSession().clear();
        return quoteList;
    }

    public List getOrderDetailBySQL(Page<ReportDataDto> reportDataDtoPage, String hql, String sql){
        //this.getSession().getTransaction().isActive();
        int pageIndex = reportDataDtoPage.getPageNo();
        int pageSize = reportDataDtoPage.getPageSize();
        //pageIndex 为当前页数，pageSize为页显示数
        final int items = (pageIndex-1) * pageSize;
        Query query = this.getSession().createSQLQuery(hql).addEntity("qu", OrderDetail.class).addScalar("salesTerritoryName").addScalar("salesContactName", Hibernate.STRING).addScalar("techSupportName")
                .addScalar("projectManagerName").addScalar("orgName");
        //定义从第几条开始查询
        query.setFirstResult(items);
        //定义返回的纪录数
        query.setMaxResults(pageSize);

        String totalCount = this.getSession().createSQLQuery("select count(*) from ( " +sql + ") tmp_count_t").uniqueResult().toString();
        reportDataDtoPage.setTotalCount(Long.parseLong(totalCount));
        query.setMaxResults(pageSize);
        List quoteList = query.list();
        //逐出数据
        this.getSession().flush();
        this.getSession().clear();
        return quoteList;
    }

    public List getQuoteDetailBySQL1(Page<ReportDataDto> reportDataDtoPage, String sql){
        int pageIndex = reportDataDtoPage.getPageNo();
        int pageSize = reportDataDtoPage.getPageSize();
        //pageIndex 为当前页数，pageSize为页显示数
        final int items = (pageIndex-1) * pageSize;
        Query query = this.getSession().createSQLQuery(sql);
        //定义从第几条开始查询
        query.setFirstResult(items);
        //定义返回的纪录数
        query.setMaxResults(pageSize);

        String totalCount = this.getSession().createSQLQuery("select count(*) from ( " + sql + ") tmp_count_t").uniqueResult().toString();
        reportDataDtoPage.setTotalCount(Long.parseLong(totalCount));
        query.setMaxResults(pageSize);
        List quoteList = query.list();
        //逐出数据
        this.getSession().flush();
        this.getSession().clear();
        return quoteList;
    }

    //add by zhanghuibin Quotation Detail Report 导出查询
    public List getQuoteDetailBySQL(String hql){
        Query query = this.getSession().createSQLQuery(hql).addEntity("qu", QutationDetail.class).addScalar("salesTerritoryName").addScalar("salesContactName", Hibernate.STRING).addScalar("techSupportName")
                .addScalar("projectManagerName").addScalar("orgName");
        List reportData = query.list();
        //逐出数据
        this.getSession().flush();
        this.getSession().clear();
        return reportData;
    }

    public List getOrderDetailBySQL(String hql){
        Query query = this.getSession().createSQLQuery(hql).addEntity("qu", OrderDetail.class).addScalar("salesTerritoryName").addScalar("salesContactName", Hibernate.STRING).addScalar("techSupportName")
                .addScalar("projectManagerName").addScalar("orgName");
        List reportData = query.list();
        //逐出数据
        this.getSession().flush();
        this.getSession().clear();
        return reportData;
    }

    public List getQuoteDetailBySQL1(String sql){
        Query query = this.getSession().createSQLQuery(sql);
        List reportData = query.list();
        //逐出数据
        this.getSession().flush();
        this.getSession().clear();
        return reportData;
    }

    public List getPSSS(String sql, Class entityClass){
        Query query = this.getSession().createSQLQuery(sql).addEntity("sac", entityClass);
        List reportData = query.list();
        //逐出数据
        this.getSession().flush();
        this.getSession().clear();
        return reportData;
    }

    //shipment detail
    public List getShipmentDetail(String sql){
        Query query = this.getSession().createSQLQuery(sql).addEntity("sac", ShipmentDetail.class).addScalar("item_no", Hibernate.STRING);
        List reportData = query.list();
        //逐出数据
        int i = 0;
        for (Object obj : reportData) {
            Object[] objTemp = (Object[])obj;
            ShipmentDetail sd = dozer.map(objTemp[0], ShipmentDetail.class);
            sd.setItemNo("" + objTemp[1]);
            reportData.set(i, sd);
            i++;
        }
        return reportData;
    }

    public List getShipmentDetail(Page<ReportDataDto> page, String sql){
        //this.getSession().getTransaction().isActive()
        int pageIndex = page.getPageNo();
        int pageSize = page.getPageSize();
        //pageIndex 为当前页数，pageSize为页显示数
        final int items = (pageIndex-1) * pageSize;
        Query query = this.getSession().createSQLQuery(sql).addEntity("sac", ShipmentDetail.class).addScalar("item_no", Hibernate.STRING);
        //定义从第几条开始查询
        query.setFirstResult(items);
        //定义返回的纪录数
        query.setMaxResults(pageSize);
        String totalCount = ((Object[])this.getSession().createSQLQuery(getCountSqlByString(sql)).uniqueResult())[0].toString();
        page.setTotalCount(Long.parseLong(totalCount));
        query.setMaxResults(pageSize);
        List reportData = query.list();
        //逐出数据
        int i = 0;
        for (Object obj : reportData) {
            Object[] objTemp = (Object[])obj;
            ShipmentDetail sd = dozer.map(objTemp[0], ShipmentDetail.class);
            sd.setItemNo("" + objTemp[1]);
            reportData.set(i, sd);
            i++;
        }
        return reportData;
    }

    public List getPSSS(Page<ReportDataDto> page, String sql, Class entityClass){
        //this.getSession().getTransaction().isActive()
        int pageIndex = page.getPageNo();
        int pageSize = page.getPageSize();
        //pageIndex 为当前页数，pageSize为页显示数
        final int items = (pageIndex-1) * pageSize;
        Query query = this.getSession().createSQLQuery(sql).addEntity("sac", entityClass);
        //定义从第几条开始查询
        query.setFirstResult(items);
        //定义返回的纪录数
        query.setMaxResults(pageSize);
        String totalCount = this.getSession().createSQLQuery(getCountSqlByString(sql)).uniqueResult().toString();
        page.setTotalCount(Long.parseLong(totalCount));
        query.setMaxResults(pageSize);
        List reportData = query.list();
        //逐出数据
        this.getSession().flush();
        this.getSession().clear();
        return reportData;
    }

    String getCountSqlByString(String sql){
        String countSql = "";
        countSql = sql.replaceAll("\\{[^}]*\\}", "count(*)");
        return countSql;
    }

    //add by zhanghuibin
    public List<DropDownDTO> getVendorList(){
		String hql = "select vendorNo,vendorName from Vendor";
        List serviceCategories = this.find(hql);
        List<DropDownDTO> dropDownDTOList = new ArrayList<DropDownDTO>();
        for (Object ser : serviceCategories) {
            DropDownDTO downDTO = new DropDownDTO();
            Object[] obj = (Object[]) ser;
            downDTO.setId(obj[0].toString());
            downDTO.setName((String) obj[1]);
            dropDownDTOList.add(downDTO);
        }
		return dropDownDTOList;
	}

    public List<DropDownDTO> getCommonList(String hql){
        List serviceCategories = this.find(hql);
        List<DropDownDTO> dropDownDTOList = new ArrayList<DropDownDTO>();
        for (Object ser : serviceCategories) {
            DropDownDTO downDTO = new DropDownDTO();
            Object[] obj = (Object[]) ser;
            downDTO.setId(obj[0].toString());
            downDTO.setName((String) obj[1]);
            dropDownDTOList.add(downDTO);
        }
		return dropDownDTOList;
	}

    public List<DropDownDTO> getCommonListBySql(String sql){
        List serviceCategories = this.getSession().createSQLQuery(sql).list();
        List<DropDownDTO> dropDownDTOList = new ArrayList<DropDownDTO>();
        for (Object ser : serviceCategories) {
            DropDownDTO downDTO = new DropDownDTO();
            Object[] obj = (Object[]) ser;
            downDTO.setId(obj[0].toString());
            downDTO.setName((String) obj[1]);
            dropDownDTOList.add(downDTO);
        }
		return dropDownDTOList;
	}

}
