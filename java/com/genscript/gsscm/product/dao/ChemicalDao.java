package com.genscript.gsscm.product.dao;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.product.entity.Chemical;

@Repository
public class ChemicalDao extends HibernateDao<Chemical, Integer> {

}
