package com.genscript.gsscm.customer.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.common.exception.BussinessException;
import com.genscript.gsscm.customer.entity.Organization;

@Repository
public class OrganizationDao extends HibernateDao<Organization, Integer> {

 
    /**
     * check this org if it is in the lists
     *
     * @param orgName String
     * @author zhougang 2011 06 16
     */
    public boolean checkOrgName(Integer orgId) {
        boolean flag;
        String sql = "from Organization where  orgId = ?";
        List<Organization> orgList = this.find(sql, orgId);
        flag = orgList.size() != 0;
        System.out.println(flag);
        return flag;
    }


    /*
      * (non-Javadoc)
      *
      * @see com.genscript.core.orm.hibernate.SimpleHibernateDao#save(java.lang.Object )
      */
    @Override
    public void save(Organization entity) {
        // TODO Auto-generated method stub
        String name = entity.getName().trim();
        List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
        PropertyFilter quoteFilter = new PropertyFilter("EQS_name", name);
        filterList.add(quoteFilter);
        if (entity.getOrgId() == null || entity.getOrgId().intValue() == 0) {
            entity.setOrgId(null);
        } else {
            PropertyFilter idFilter = new PropertyFilter("NEI_orgId", entity
                    .getOrgId());
            filterList.add(idFilter);
        }
        List<Organization> orgList = this.find(filterList);
        if (orgList != null && !orgList.isEmpty()) {
            throw new BussinessException(BussinessException.ORG_REPEATNAME_CODE);
        }
        entity.setName(name);
        super.save(entity);
    }

    /**
     * 根据orgGroupId获得所有关联的Organization
     *
     * @param page
     * @param orgGroupId
     * @return
     */
    public Page<Organization> searchOrganization(Page<Organization> page,
                                                 Integer orgGroupId) {
        String hql = "from Organization where orgGroupId=?";
        return this.findPage(page, hql, orgGroupId);
    }

    /**
     * 分页查找一个Organization的子级列表
     *
     * @param page
     * @param orgId
     * @return
     */
    public Page<Organization> searchSubList(Page<Organization> page,
                                            Integer orgId) {
        String hql = "from Organization where parentOrgId=?";
        return this.findPage(page, hql, orgId);
    }

    public Page<Organization> searchUnSubList(Page<Organization> page,
                                              Integer orgId) {
        String hql = "from Organization where (parentOrgId=null OR parentOrgId<>?) and orgId<>?";
        if (page.isOrderBySetted()) {
            hql += " order by " + page.getOrderBy() + " " + page.getOrder();
        }
        return this.findPage(page, hql, orgId, orgId);
    }

    /**
     * 根据Organization的name, 或OrganizationGroup的name属性查找Organization
     *
     * @param page
     * @param orgName
     * @param
     * @return
     */
    public Page<Organization> searchOrganization(Page<Organization> page, String orgName, String orgGroupId) {
        boolean bInIdList = false;
        String hql = "from Organization where activeFlag='Y'";
        if (orgName != null && orgName.trim().length() > 0) {
            hql += " and name like '%" + orgName + "%'";
        }
        Map<String, Object> values = new HashMap<String, Object>();
        if (orgGroupId != null && !"".equals(orgGroupId)) {
            hql += " and orgGroupId =:groupId";
            values.put("groupId", Integer.parseInt(orgGroupId));
            bInIdList = true;
        }
        if (page.isOrderBySetted()) {
            hql += " order by " + page.getOrderBy() + " " + page.getOrder();
        }
        if (bInIdList) {
            return this.findPage(page, hql, values);
        }
        return this.findPage(page, hql);
    }

    /**
     * 批量移除Organization与其Group的对应关系.
     *
     * @param detachOrgIdList
     */
    public void detachOrgGroup(List<Integer> detachOrgIdList) {
        String hql = "update Organization o set o.orgGroupId=null where o.orgId in (:idList)";
        Map<String, List<Integer>> map = Collections.singletonMap("idList",
                detachOrgIdList);
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("idList", map.get("idList"));
        batchExecute(hql, values);
    }

    /**
     * 指更新organization的状态
     *
     * @param orgIdList
     * @param status
     */
    public void batchUpdateOrganization(List<Integer> orgIdList, String status, String note) {
        String hql = "update Organization o set o.activeFlag=:status,o.updateStatusReason=:updateStatusReason where o.orgId in (:idList)";
        Map<String, List<Integer>> map = Collections.singletonMap("idList",
                orgIdList);
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("status", status);
        values.put("updateStatusReason", note);
        values.put("idList", map.get("idList"));
        batchExecute(hql, values);
    }

    /**
     * 批量更新parent organization
     *
     * @param parentOrgId
     * @param subIdList
     * @author wangsf
     * @serialData 2011-03-14
     */
    public void batchUpdateParent(Integer parentOrgId, List<Integer> subIdList) {
        String hql = "update Organization o set o.parentOrgId=:parentOrgId where o.orgId in (:subIdList)";
        Map<String, List<Integer>> map = Collections.singletonMap("subIdList",
                subIdList);
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("subIdList", map.get("subIdList"));
        values.put("parentOrgId", parentOrgId);
        batchExecute(hql, values);
    }

    /**
     * 批量去除关联parent organization
     *
     * @param parentOrgId
     * @param subIdList
     * @author wangsf
     * @serialData 2011-03-14
     */
    public void batchDetachParent(Integer parentOrgId, List<Integer> subIdList) {
        String hql = "update Organization o set o.parentOrgId=null where o.orgId in (:subIdList)";
        Map<String, List<Integer>> map = Collections.singletonMap("subIdList",
                subIdList);
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("subIdList", map.get("subIdList"));
        batchExecute(hql, values);
    }
}
