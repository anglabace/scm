package com.genscript.gsscm.biolib.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.gsscm.biolib.dao.PeptideCodeDao;
import com.genscript.gsscm.biolib.entity.PeptideCode;

@Service
@Transactional
public class BiolibService {
	@Autowired
	private PeptideCodeDao peptideCodeDao;
	
	@Transactional(readOnly = true)
	public List<PeptideCode> getPeptideCodeList(){
		return peptideCodeDao.getAll();
	}
	
	@Transactional(readOnly = true)
	public String getPeptideCode(String code){
		List<PeptideCode> peptideCodeList = peptideCodeDao.findBy("code1", code);
		if(peptideCodeList != null && peptideCodeList.size() > 0){
			if(StringUtils.isEmpty(peptideCodeList.get(0).getDcode())){
				return "";
			}
			return peptideCodeList.get(0).getDcode();
		}
		return "";
	}
}
