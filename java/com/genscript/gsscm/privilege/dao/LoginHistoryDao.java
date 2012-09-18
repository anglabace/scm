package com.genscript.gsscm.privilege.dao;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.privilege.entity.LoginHistory;

@Repository
public class LoginHistoryDao extends HibernateDao<LoginHistory, Integer>{

}
