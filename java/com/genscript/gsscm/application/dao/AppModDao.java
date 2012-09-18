package com.genscript.gsscm.application.dao;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.application.entity.ApplicationModule;

/**
 * The generic DAO class of ApplicationModule.
 * 
 * @author Golf
 */

@Repository
public class AppModDao extends HibernateDao<ApplicationModule, Integer> {

}
