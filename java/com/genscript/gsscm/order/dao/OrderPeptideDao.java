package com.genscript.gsscm.order.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.order.entity.OrderPeptide;

@Repository
public class OrderPeptideDao extends HibernateDao<OrderPeptide, Integer>{

	/**
	 * 通过主服务查找子服务
	 * @author Zhang Yong
	 * @param peptideType
	 * @param parentOrderItemId
	 * @return
	 */
	public List<OrderPeptide> findByOrderItemIds (String peptideType, Integer parentOrderItemId) {
		String hql = "select op from OrderPeptide op, OrderItem oi, ServiceClass sc "
			+ "where op.orderItemId = oi.orderItemId and oi.clsId = sc.clsId and sc.name = upper(?) "
			+ "and oi.parentId = ?";
		return this.find(hql, peptideType, parentOrderItemId);
	}
	
	/**
	 * 批量删除orderItemId的order peptide.
	 * @param ids
	 */
	public void delOrderItemList(Object ids){
		String hql = "delete from OrderPeptide c where c.orderItemId in (:ids)";
		Map<String,Object> map = Collections.singletonMap("ids", ids);
		batchExecute(hql, map);
	}

    //add by zhanghuibin
    public  OrderPeptide getOrderPeptideByNo(String order_no, String item_no){
        String sql = "select {pep.*} from order.order_peptide pep " +
                "where pep.order_item_id=(select item.order_item_id from order.order_items item where item.order_no=:order_no and item.item_no=:item_no)";

        Query sqlQuery = this.getSession().createSQLQuery(sql).addEntity("pep", OrderPeptide.class).setParameter("order_no", order_no).setParameter("item_no", item_no);
        List<OrderPeptide> peptides = sqlQuery.list();
        if(peptides.size() > 0){
            return peptides.get(0);
        }else{
            return null;
        }
    }

    public void save(OrderPeptide peptide){
        peptide.setRealPurity(peptide.getPurity());
        peptide.setRealQuantity(peptide.getQuantity());
        super.save(peptide);
    }
}
