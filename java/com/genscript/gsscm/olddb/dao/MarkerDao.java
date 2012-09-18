package com.genscript.gsscm.olddb.dao;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.olddb.entity.Marker;

@Repository
public class MarkerDao extends HibernateDao<Marker, Integer> {

}
