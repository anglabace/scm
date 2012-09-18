package com.genscript.gsscm.order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.common.constant.OrderInstructionType;
import com.genscript.gsscm.customer.entity.CustomerNote;
import com.genscript.gsscm.order.dto.OrderNotesDTO;
import com.genscript.gsscm.order.entity.OrderNote;

@Repository
public class OrderNoteDao extends HibernateDao<OrderNote, Integer>{
	/**
	 * 根据orderNo查找相关OrderNote.
	 * @param orderNo
	 * @param type
	 * @return
	 */
	public List<OrderNote> getOrderNoteList(Integer orderNo, OrderInstructionType type) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter quoteFilter = new PropertyFilter("EQI_orderNo", orderNo);
		filterList.add(quoteFilter);
		if (type != null) {
			PropertyFilter typeFilter = new PropertyFilter("EQS_type", type);
			filterList.add(typeFilter);
		}
		List<OrderNote> noteList = this.find(filterList);
		return noteList;
	}
	
	/**
	 * 根据orderNos查询OrderNotes和User
	 * @param  orderNos 参数
	 * @return List     集合
	 */
	@SuppressWarnings("rawtypes")
	public HashMap<String, Object> getOrderNotesByOrderNo(String orderNos) throws SQLException{
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        HashMap<String, Object> notes = new HashMap<String, Object>();
        try{
        if(orderNos==null||orderNos.equals(""))
			return null;
//		String hql="select user,orderNotes from OrderNote orderNotes,User user where user.userId=orderNotes.createdBy and orderNotes.orderNo in ("+orderNos+") and orderNotes.type='SHIPMENT'";
//		List list=this.find(hql);
        String hql = "select distinct id,description,orderNo from OrderNote where  type='SHIPMENT' and orderNo in (" + orderNos + ")";
        List<OrderNotesDTO> orderNotesDTOs = new ArrayList<OrderNotesDTO>();
        List orderNoteList = this.getSession().createQuery(hql).list();
        for (Object obj : orderNoteList) {
            OrderNotesDTO dto = new OrderNotesDTO();
            Object[] objTemp = (Object[]) obj;
            dto.setNoteIds("" + (Integer) objTemp[0]);
            dto.setOrderNote("" + objTemp[1]);
            dto.setOrderNo((Integer)(objTemp[2]));
            orderNotesDTOs.add(dto);
        }
        System.out.println("dto:"+orderNotesDTOs.size());
        notes.put("orderNotes", orderNotesDTOs);
        //Customer Notes
        String orgNoteIds = "";
        String diviNoteIds = "";
        String deptNoteIds = "";
        List<CustomerNote> customerNoteList = new ArrayList<CustomerNote>();
        connection = SessionFactoryUtils.getDataSource(this.sessionFactory).getConnection();
        String custSql = "select distinct cust.org_id,cust.dept_id,cust.division_id,cun.description as cust_description,cun.note_id as cust_note_id from customer.customer cust,customer.customer_notes cun,order.order ord " +
                "where cun.cust_no=cust.cust_no and cun.note_type='SHIPMENT' and cun.cust_no=ord.cust_no and ord.order_no in (" + orderNos + ")";
        ps = connection.prepareStatement(custSql);
        rs = ps.executeQuery();
        while (rs.next()) {
            CustomerNote custNote = new CustomerNote();
            orgNoteIds = orgNoteIds + "," + rs.getInt("org_id");
            deptNoteIds = deptNoteIds + "," + rs.getInt("dept_id");
            diviNoteIds = diviNoteIds + "," + rs.getInt("division_id");
            custNote.setId(rs.getInt("cust_note_id"));
            custNote.setDescription("" + rs.getString("cust_description"));
            customerNoteList.add(custNote);
        }
            notes.put("custNote", customerNoteList);
            List orgnotes = new ArrayList();
        if (!"".equals(orgNoteIds)) {
            orgnotes = this.getSession().createQuery("select distinct id,description from OrganizationNote where orgId in (" + orgNoteIds.substring(1, orgNoteIds.length()) + ") and type='SHIPMENT'").list();
        }
            notes.put("orgNote", orgnotes);
             List divisionNotes = new ArrayList();
        if (!"".equals(diviNoteIds)) {
            divisionNotes = this.getSession().createQuery("select distinct id,description from OrganizationNote where divisionId in (" + diviNoteIds.substring(1, diviNoteIds.length()) + ") and type='SHIPMENT'").list();
        }
            notes.put("diviNote", divisionNotes);
            List deptnotes = new ArrayList();
        if (!"".equals(deptNoteIds)) {
            deptnotes = this.getSession().createQuery("select distinct id,description from OrganizationNote where deptId in (" + deptNoteIds.substring(1, deptNoteIds.length()) + ") and type='SHIPMENT'").list();
        }
            notes.put("deptNote", deptnotes);
        }catch (SQLException ex){
            throw ex;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return notes;
	}
	
	/**
	 * 查询OrderNote中是否含有指定的note
	 * @author Zhang Yong
	 * add date 2011-11-04
	 * @param orderNo
	 * @param type
	 * @param description
	 * @return
	 */
	public boolean checkIsConteinNote (Integer orderNo, String type, String description) {
		boolean result = false;
		String hql = "FROM OrderNote WHERE orderNo=:orderNo AND type=:type AND description like:description";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNo", orderNo);
		map.put("type", type);
		map.put("description", "%"+description+"%");
		List<OrderNote> noteList = this.find(hql, map);
		if (noteList != null && !noteList.isEmpty()) {
			result = true;
		}
		return result;
	}
}
