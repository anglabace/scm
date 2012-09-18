package com.genscript.gsscm.application.dao;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.application.entity.Application;

@Repository
public class ApplicationDao extends HibernateDao<Application, Integer> {

}
