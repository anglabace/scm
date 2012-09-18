package com.genscript.gsscm.basedata.dao;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.customer.entity.DepartmentFunction;

@Repository
public class DeptFuncDao extends HibernateDao<DepartmentFunction, Integer> {

}
