package com.genscript.gsscm.shipment.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.shipment.entity.ShipPackageErrors;

@Repository
public class ShipPackageErrorsDao extends HibernateDao<ShipPackageErrors,Integer>  {
	

	/**
	 * 更新ShipPackageErrors数据
	 * @param errors
	 */
	public void updteShipPackageErrors(ShipPackageErrors errors)
	{
		String hql="update ShipPackageErrors set ";
		if(errors.getMissingQty()!=null)
			hql=hql+",missingQty='"+errors.getMissingQty()+"'";
		if(errors.getMissingSize()!=null)
			hql=hql+",missingSize='"+errors.getMissingSize()+"'";
		if(errors.getReason()!=null)
			hql=hql+",reason='"+errors.getReason()+"'";
		hql=hql+" where id="+errors.getId();
		if(hql.substring(hql.indexOf("set")+4, hql.indexOf("set")+5).equals(","))
		{
			hql=hql.substring(0, hql.indexOf("set")+4)+hql.substring(hql.indexOf("set")+5);
		}
			this.getSession().createQuery(hql).executeUpdate();
	}
	
	public List<ShipPackageErrors> getPackageError(String packageId,String pkgLineId){
			String hql = "from ShipPackageErrors where packageId ="+packageId+" and pkgLineId ="+pkgLineId;
			List<ShipPackageErrors> error = this.find(hql);/*this.getSession()
				.createQuery("from ShipPackageErrors where packageId =:packageId and pkgLineId =:pkgLineId")
					.setString("packageId", packageId).setString("pkgLineId", pkgLineId).list();*/
			return error;
	}
	
	public List<ShipPackageErrors> getPackageErrorByShipmentId(Integer shipmentId){
		String hql = "select se from ShipPackageErrors se,ShipmentLine sl where se.packageId =sl.order.orderNo and se.pkgLineId =sl.itemNo and sl.shipments.shipmentId="+shipmentId;
		List<ShipPackageErrors> error = this.find(hql);
		/*this.getSession()
			.createQuery("from ShipPackageErrors where packageId =:packageId and pkgLineId =:pkgLineId")
				.setString("packageId", packageId).setString("pkgLineId", pkgLineId).list();*/
		return error;
}
	
	public String updateError(ShipPackageErrors error)throws Exception{
		String flag = "1";
			int count = this.getSession().createSQLQuery("update ship_package_errors set missing_qty =:missingQty " +
					"and missing_size =:missingSize and reason =:reason where id =:id")
					.setInteger("missingQty", error.getMissingQty()).setDouble("missingSize", error.getMissingSize())
					.setString("reason", error.getReason()).setInteger("id", error.getId()).executeUpdate();
			if(count == 0)
				flag = "0";
			return flag;
	}

}
