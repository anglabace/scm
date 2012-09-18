package com.genscript.gsscm.order.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.order.entity.MfgOrderDTO;
import com.genscript.gsscm.order.entity.MfgOrderDTO2;
import com.genscript.gsscm.order.entity.OrderItemCenterBean;

@Repository
public class OrderItemCenterBeanDao extends HibernateDao<OrderItemCenterBean, Integer>{
	
	/**
	 * 通过creationDate和centerId查找所有item
	 * @param fromDate 开始时间
	 * @param toDate 结束时间
	 * @centerId 工作中心Id
	 */
	public List<OrderItemCenterBean> findOrderItemByDate(Date fromDate,Date toDate,Integer orderNo,Integer itemNo,Integer centerId) {
		String hql = "from OrderItemCenterBean  where  creationDate>=? and creationDate<? and workCenterId=?";
		if(orderNo!=null) {
			hql +=" and orderNo="+orderNo;
		}
		if(itemNo!=null) {
			hql +=" and itemNo="+itemNo;
		}
		return this.find(hql, fromDate,toDate,centerId);
	}
	
	@SuppressWarnings("unchecked")
	public List<OrderItemCenterBean> searchMfgOrderDTO(List<PropertyFilter> filters){
		List<OrderItemCenterBean> list = null;
		String fromSql = "from order.mfg_order_items t right join manufacturing.work_center_assigned t2 on ((t.type = t2.item_type)  and  (t.cls_id = t2.cls_id)) join  (select tt.order_no,tt.src_so_no  from order.mfg_order tt,order.order tt1  where tt.src_so_no=tt1.order_no) t3 on t.order_no = t3.order_no where 1=1 "
						+"and t.status in('CM','BO','PB','CC','VC')";
		String selectSql="select distinct t.order_item_id AS order_item_id,t.order_no AS order_no,t.item_no AS item_no,t.status AS STATUS,t.catalog_no AS catalog_no,t.creation_date AS creation_date,t2.work_center_id AS work_center_id,  t3.src_so_no as  src_so_no ";
		
		
		String querySql = "";
		if (filters != null && filters.size() > 0) {
			for (int i = 0; i < filters.size(); i++) {
				PropertyFilter propertyFilter = (PropertyFilter) filters.get(i);
				if(propertyFilter.getPropertyName().equals("centerId")) {
					fromSql+=" and work_center_id="+String.valueOf(propertyFilter.getPropertyValue());
				}
				if (propertyFilter.getPropertyName().equals("srcSoNo")) {
					fromSql+=" and t3.src_so_no="+String.valueOf(propertyFilter.getPropertyValue());
                }
				if (propertyFilter.getPropertyName().equals("orderNo")) {
					fromSql+=" and t.order_no="+String.valueOf(propertyFilter.getPropertyValue());
                }
				if(propertyFilter.getPropertyName().equals("itemNo")) {
					fromSql+=" and t.item_no="+String.valueOf(propertyFilter.getPropertyValue());
				}
				if(propertyFilter.getPropertyName().equals("fromDate")) {
					fromSql+=" and t.creation_date>='"+String.valueOf(propertyFilter.getPropertyValue())+"'";
				}
				if(propertyFilter.getPropertyName().equals("toDate")) {
					fromSql+=" and t.creation_date<'"+String.valueOf(propertyFilter.getPropertyValue())+"'";
				}
			}
		}
		list = this.getSession().createSQLQuery(selectSql+fromSql+ querySql +" order by order_item_id")
								.addEntity(OrderItemCenterBean.class).list();
		return list;
	} 

}
