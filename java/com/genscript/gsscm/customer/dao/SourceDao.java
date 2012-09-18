package com.genscript.gsscm.customer.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.customer.entity.Source;

@Repository
public class SourceDao extends HibernateDao<Source, Integer> {

    private static final String DEL_SOURCES = "delete from Source s where s.sourceId in (:ids)";

    private static final String Get_all_ActiveSource = "from Source s where s.status ='ACTIVE' order by s.sourceId desc";


    public List<Source> getALLActiveSources() {
        return findUnique(Get_all_ActiveSource);
    }

    public int delSources(Object ids) {
        Map<String, Object> map = Collections.singletonMap("ids", ids);
        return batchExecute(DEL_SOURCES, map);
    }

    public Source getSourceDetail(Integer sourceId) {
        String sql = "from Source where sourceId=?";

        return this.findUnique(sql, sourceId);
    }
}
