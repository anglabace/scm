package com.genscript.gsscm.shipment.dao;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.shipment.entity.CanceledShipPackages;

@Repository
public class CanceledShipPackageDao extends HibernateDao<CanceledShipPackages,Integer>{

}
