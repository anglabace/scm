package com.genscript.gsscm.tools.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.tools.dao.FollowUpDao;
import com.genscript.gsscm.tools.dto.FollowUpDTO;
import com.genscript.gsscm.tools.entity.FollowUp;

@Service
@Transactional
public class FollowUpService {

	@Autowired
	private FollowUpDao followUpDao;
	@Autowired
	private DozerBeanMapper dozer;

	@Transactional(readOnly = true)
	public Page<FollowUp> searchfollowUps(Page<FollowUp> page) {
		return followUpDao.findPage(page);
	}

	@Transactional(readOnly = true)
	public Page<FollowUp> searchfollowUps(Page<FollowUp> page,
			List<PropertyFilter> filters) {
		return followUpDao.findPage(page, filters);
	}

	public FollowUp saveFollowUp(final FollowUp followUp)
			throws ParseException {
		FollowUp followUps = null;
		followUps = dozer.map(followUp, FollowUp.class);
		this.followUpDao.save(followUps);
		return followUps;
	}

 
	public List<FollowUp> getallFollowups(Integer orderNo) {
		List<FollowUp> fllist=new ArrayList<FollowUp>();
		fllist=followUpDao.getAllByorderNo(orderNo);
		return fllist;
	}

	public FollowUp getLastOne1(Integer orderNo) { 

		return followUpDao.getLastOnByOrderNo1(orderNo);
	}

	public List getLastOne2(Integer orderNo) { 

		return followUpDao.getLastOnByOrderNo2(orderNo);
	}

	public String getptdate(Integer orderNo) {
		 
		return followUpDao.getptdateStr(orderNo);
	}
}
