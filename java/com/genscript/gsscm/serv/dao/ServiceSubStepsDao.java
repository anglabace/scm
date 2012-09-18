package com.genscript.gsscm.serv.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.serv.entity.ServiceSubSteps;

@Repository
public class ServiceSubStepsDao extends HibernateDao<ServiceSubSteps, Integer> {
    public void delSubStepsById(Object ids) {
        String del_subSteps = "delete from ServiceSubSteps c where c.stepId in (:ids)";
        Map<String, Object> map = Collections.singletonMap("ids", ids);
        batchExecute(del_subSteps, map);
    }

    public List<ServiceSubSteps> getSubStepListBySerivceId(Integer id) {
        String hql = "from ServiceSubSteps c where c.serviceId = ? ORDER BY c.stepNo asc";
        return this.find(hql, id);
    }

    //add by zhougang 2011 05 26
    public ServiceSubSteps getServiceSubStepBySteps(Integer stepid) {
        String hql = " from ServiceSubSteps c where c.stepId= ?";
        return this.findUnique(hql, stepid);

    }
}
