package com.genscript.gsscm.customer.dao;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.customer.entity.CustomerContactHistory;

@Repository
public class CustContactHistDao extends HibernateDao<CustomerContactHistory, Integer> {

    private static final String DEL_CONTACT_HISTORYS = "delete from CustomerContactHistory c where c.contactId in (:ids)";
    private static final String QUERY_MAIL = "from CustomerContactHistory c where c.status = 'INCOMPLETE' and c.scheduleDate >=? and c.scheduleDate < ?";

    public void delContactsList(Object ids) {
        Map<String, Object> map = Collections.singletonMap("ids", ids);
        batchExecute(DEL_CONTACT_HISTORYS, map);
    }

    public List<CustomerContactHistory> getListByCust(Integer custNo) {
        String hql = "from CustomerContactHistory where custNo=?";
        return this.find(hql, custNo);
    }

    public int getMethodCount(String contactMethod, Integer custNo) {
        int count = 0;
        String hql = "from CustomerContactHistory where contactMethod=? and custNo=?";
        List<CustomerContactHistory> list = this.find(hql, contactMethod, custNo);
        if (list != null) {
            count = list.size();
        }
        return count;
    }

    public CustomerContactHistory getLastContact(Integer custNo) {
        CustomerContactHistory contactHistory = null;
        String hql = "from CustomerContactHistory where custNo=:custNo order by modifyDate desc";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("custNo", custNo);
        Page<CustomerContactHistory> page = new Page<CustomerContactHistory>();
        page.setPageNo(1);
        page.setPageSize(1);
        page = this.findPage(page, hql, map);
        if (page.getResult() != null && !page.getResult().isEmpty()) {
            contactHistory = page.getResult().get(0);
        }
        return contactHistory;
    }

    /**
     * ×××××××××××××××××××××××××××××××××××××××××/
     * 定义了获取当前最新的联系人信息
     * 根据custNo 获取
     * zhougang
     * 2011  4-27
     */

    // private static final String getLastContactIDByCustNO = "select Max(contactId) from CustomerContactHistory where custNo=? ";   //根据custNo 从  CustomerContactHistory表 中 获取当前最先的联系人信息

    private static final String getContactInfoBycustNo = "from CustomerContactHistory where contactId=?";


    private static final String GetLastEmailContactInfoBycustNo = "select Max(contactId) from CustomerContactHistory where custNo=? and contact_method ='Email'";
    private static final String GetLastfaxContactInfoBycustNo = "select Max(contactId) from CustomerContactHistory where custNo=? and contact_method ='Fax'";
    private static final String GetLastMailingContactInfoBycustNo = "select Max(contactId) from CustomerContactHistory where custNo=? and contact_method ='Mail'";
    private static final String GetLastPhoneContactInfoBycustNo = "select Max(contactId) from CustomerContactHistory where custNo=? and contact_method ='Phone'";
    private static final String GetLastMeetingContactInfoBycustNo = "select Max(contactId) from CustomerContactHistory where custNo=? and contact_method ='Meeting'";


    public Integer getLastContactHistoryIDByCustNO(Integer custNo, String method) {
        String sqlByMethod = "";
        if (method.equals("Email")) {
            sqlByMethod = GetLastEmailContactInfoBycustNo;
        }
        if (method.equals("Fax")) {
            sqlByMethod = GetLastfaxContactInfoBycustNo;
        }
        if (method.equals("Mail")) {
            sqlByMethod = GetLastMailingContactInfoBycustNo;
        }
        if(method.equals("Phone")){
            sqlByMethod=GetLastPhoneContactInfoBycustNo;
        }
        if(method.equals("Meeting")){
            sqlByMethod=GetLastMeetingContactInfoBycustNo;
        }
        return this.findUnique(sqlByMethod, custNo);
    }

    public CustomerContactHistory getCustomerContactHistoryByCustNo(Integer custNo,String method) {
        Integer Id = getLastContactHistoryIDByCustNO(custNo,method);
        if (Id != null) {
            return this.findUnique(getContactInfoBycustNo, Id);
        } else {
            return null;
        }
    }
    ///×××××××××××××××××××××××××××××××××××××××/

    public CustomerContactHistory getLastContactByMethod(Integer custNo, String contactMethod) {
        CustomerContactHistory contactHistory = null;
        String hql = "from CustomerContactHistory where custNo=:custNo AND contactMethod=:contactMethod order by modifyDate desc";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("custNo", custNo);
        map.put("contactMethod", contactMethod);
        Page<CustomerContactHistory> page = new Page<CustomerContactHistory>();
        page.setPageNo(1);
        page.setPageSize(1);
        page = this.findPage(page, hql, map);
        if (page.getResult() != null && !page.getResult().isEmpty()) {
            contactHistory = page.getResult().get(0);
        }
        return contactHistory;
    }

    public CustomerContactHistory getLastContactByContentType(Integer custNo, String contentType) {
        CustomerContactHistory contactHistory = null;
        String hql = "from CustomerContactHistory where custNo=:custNo AND contentType=:contentType order by modifyDate desc";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("custNo", custNo);
        map.put("contentType", contentType);
        Page<CustomerContactHistory> page = new Page<CustomerContactHistory>();
        page.setPageNo(1);
        page.setPageSize(1);
        page = this.findPage(page, hql, map);
        if (page.getResult() != null && !page.getResult().isEmpty()) {
            contactHistory = page.getResult().get(0);
        }
        return contactHistory;
    }


    @SuppressWarnings("unchecked")
    public List<CustomerContactHistory> queryMailSend() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date now = calendar.getTime();
        System.out.println(now);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date tomorrow = calendar.getTime();
        System.out.println(tomorrow);
        List<CustomerContactHistory> list = createQuery(QUERY_MAIL, now, tomorrow).list();
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }
}
