package com.genscript.gsscm.customer.dao;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.customer.entity.Bank;

@Repository
public class BankDao extends HibernateDao<Bank, Integer> {
}
