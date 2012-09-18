package com.genscript.gsscm.serv.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.product.entity.ProductOfPdtcategoryBean;
import com.genscript.gsscm.serv.entity.ServiceOfServcategoryBean;

@Repository
public class ServiceOfServcategoryBeanDao extends HibernateDao<ServiceOfServcategoryBean, Integer> {

    /**
     * 根据catalogNo和catalogId查找视图
     *
     * @param catalogNo
     * @param catalogId
     * @return
     * @author lizhang
     */
    public ServiceOfServcategoryBean getBeanByCatalog(String catalogNo, String catalogId) {
        Criterion criterion1 = Restrictions.eq("catalogId", catalogId);
        Criterion criterion2 = Restrictions.eq("catalogNo", catalogNo);
        List<ServiceOfServcategoryBean> list = find(criterion1, criterion2);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }
    //------------add by zhou gang 2011 5 18 -------


    public ServiceOfServcategoryBean getBeanByCatalog2(String catalogId, Integer serviceID) {
        System.out.println("catalogId>>>>>>>>"+catalogId);
        System.out.println("serviceID>>>>>>>>>>>"+serviceID);
    	Criterion criterion1 = Restrictions.eq("catalogId", catalogId);
        Criterion criterion2 = Restrictions.eq("serviceId", serviceID);
         return findUnique(criterion1,criterion2);
    }
    //------------add by zhou gang 2011 5 18 -------


    public Page<ServiceOfServcategoryBean> getCatalogNoListByCategory(Page<ServiceOfServcategoryBean> page, String categoryNo) {
        Criterion criterion1 = Restrictions.eq("categoryNo", categoryNo);
        return findPage(page, criterion1);
    }

    /**
     * @param page
     * @param filterList
     * @param catalogNoList
     * @return
     * @author zhang yong
     */
    public Page<ServiceOfServcategoryBean> findPageExceptCatalogNo(final Page<ServiceOfServcategoryBean> page, final List<PropertyFilter> filterList, List<String> catalogNoList) {
        Criterion criterion1;
        List<Criterion> criterionList = new ArrayList<Criterion>();
        for (PropertyFilter filter : filterList) {
            if (!filter.isMultiProperty()) {
                Criterion criterion = buildPropertyFilterCriterion(filter.getPropertyName(), filter.getPropertyValue(),
                        filter.getPropertyType(), filter.getMatchType());
                criterionList.add(criterion);
            } else {
                Disjunction disjunction = Restrictions.disjunction();
                for (String param : filter.getPropertyNames()) {
                    Criterion criterion = buildPropertyFilterCriterion(param, filter.getPropertyValue(), filter
                            .getPropertyType(), filter.getMatchType());
                    disjunction.add(criterion);
                }
                criterionList.add(disjunction);
            }
        }
        if (catalogNoList != null && catalogNoList.size() > 0) {
            criterion1 = Restrictions.not(Restrictions.in("catalogNo", catalogNoList));
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(criterion1);
            criterionList.add(disjunction);
        }
        Criterion[] criterions = criterionList.toArray(new Criterion[criterionList.size()]);
        return findPage(page, criterions);
    }
    public List<ServiceOfServcategoryBean> getAllcategorylist(Integer sessionCategoryId) {
		String hql="from  ServiceOfServcategoryBean where categoryId= ?";		
		return this.find(hql,sessionCategoryId);
	}
}
