package com.genscript.gsscm.basedata.dao;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.basedata.entity.PbDropdownListOptions;

@Repository
public class DropdownOptionDao extends HibernateDao<PbDropdownListOptions, Integer> {

}
