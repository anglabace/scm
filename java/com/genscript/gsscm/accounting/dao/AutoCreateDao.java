package com.genscript.gsscm.accounting.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;


@Repository
public class AutoCreateDao extends HibernateDao  {
	
	
	/**
	 * @function 自动生成
	 * @version  1.0
	 * @auther   陈文擎
	 * @date     2010-11-11
	 *  
	 */
    
	
	/**
	 * @function 获取符合条件的shipment 的id号 
	 * @verSion 1.0
	 */
	public List getshipment()
	{
		List result=null;
		StringBuffer sql=new StringBuffer();
		sql.append("select   distinct(  ifnull( a.shipment_id   ,0  )   )   as shipment_id    from   shipping.ship_packages a where  a.status='Shipped'  ");
		sql.append("  and  a.invoiced_flag='N' ");
		result=this.getSession().createSQLQuery(sql.toString()).list();
		System.out.println("qqqq:"+result.size());
		return result;
	}
	
	/**
	 * 
	 * 
	 */
	public void updateStatus( String sql)
	{
		this.getSession().createSQLQuery(sql).executeUpdate();
		this.getSession().flush();
	}
	
	

}
