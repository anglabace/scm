package com.genscript.gsscm.product.dao;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.product.entity.Peptide;

@Repository
public class PeptideDao extends HibernateDao<Peptide, Integer>{

}
