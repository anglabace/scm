package com.genscript.gsscm.serv.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.privilege.dao.UserDao;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.serv.dao.PriceFormulaCriteriasDao;
import com.genscript.gsscm.serv.dao.PriceFormulaItemsDao;
import com.genscript.gsscm.serv.dao.PriceFormulasDao;
import com.genscript.gsscm.serv.dao.PriceRuleAttrValueMappingDao;
import com.genscript.gsscm.serv.dao.PriceRuleGroupsDao;
import com.genscript.gsscm.serv.dao.PriceRulesDao;
import com.genscript.gsscm.serv.dao.ServiceAttributesDao;
import com.genscript.gsscm.serv.dto.PriceFormulaCriteriasDTO;
import com.genscript.gsscm.serv.dto.PriceFormulaDTO;
import com.genscript.gsscm.serv.dto.PriceFormulaItemDTO;
import com.genscript.gsscm.serv.dto.PriceRuleAttrValueMappingDTO;
import com.genscript.gsscm.serv.dto.PriceRuleGroupsDTO;
import com.genscript.gsscm.serv.dto.PriceRulesDTO;
import com.genscript.gsscm.serv.entity.PriceFormulaCriterias;
import com.genscript.gsscm.serv.entity.PriceFormulaItems;
import com.genscript.gsscm.serv.entity.PriceFormulas;
import com.genscript.gsscm.serv.entity.PriceRuleAttrValueMapping;
import com.genscript.gsscm.serv.entity.PriceRuleGroups;
import com.genscript.gsscm.serv.entity.PriceRules;
import com.genscript.gsscm.serv.entity.ServiceAttributes;

@Service
@Transactional
public class ServicePriceRuleService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private DozerBeanMapper dozer;
	@Autowired
	private PriceFormulaCriteriasDao priceFormulaCriteriasDao;
	@Autowired
	private PriceFormulaItemsDao priceFormulsItemsDao;
	@Autowired
	private PriceFormulasDao priceFormulasDao;
	@Autowired
	private PriceRuleAttrValueMappingDao priceRuleAttrValueMappingDao;
	@Autowired
	private PriceRulesDao priceRulesDao;
	@Autowired
	private PriceRuleGroupsDao priceRuleGroupsDao;
	@Autowired
	private ServiceAttributesDao serviceAttributesDao;
	
	
	public Page<PriceRules> searchPriceRuleList(Page<PriceRules> page, List<PropertyFilter> filters){
		return this.priceRulesDao.findPage(page, filters);
	}
	
	public Page<PriceRules> searchPriceRuleListByGroupId(Page<PriceRules> page, Integer clsId,String groupId){
		return this.priceRulesDao.findPageByGroupId(page,clsId,groupId);
	}
	
	public List<PriceRuleGroups> searchPriceRuleGroupListByClsId(Integer clsId){
		return this.priceRuleGroupsDao.findBy("clsId", clsId);
	} 
	
	public PriceRulesDTO getPriceRuleById(Integer id){
		PriceRulesDTO pr = new PriceRulesDTO();
		pr.setPriceRules(this.priceRulesDao.get(id));
		//pr.setPriceAttrValMapList(this.priceRuleAttrValueMappingDao.findBy("ruleId", id));
		List<PriceRuleAttrValueMapping> prmList = this.priceRuleAttrValueMappingDao.findBy("ruleId", id);
		List<PriceRuleAttrValueMappingDTO> prmDTOList = new ArrayList<PriceRuleAttrValueMappingDTO>();
		for(PriceRuleAttrValueMapping prm :prmList){
			PriceRuleAttrValueMappingDTO dto = new PriceRuleAttrValueMappingDTO();
			dto.setPriceRuleValue(prm);
			ServiceAttributes attributes =this.serviceAttributesDao.getById(prm.getAttributeId());
			if(attributes!=null){
				dto.setAttributerName(attributes.getAttributeName());
			}
			prmDTOList.add(dto);
		}
		pr.setPriceAttrValMapDTOList(prmDTOList);
		
		List<PriceFormulas> pfList = this.priceFormulasDao.findBy("ruleId", id);
		List<PriceFormulaDTO> pfListDTO = new ArrayList<PriceFormulaDTO>();
		for(PriceFormulas pf:pfList){
			PriceFormulaDTO dto = new PriceFormulaDTO();
			dto.setPriceFormulas(pf);
			User createUser = this.userDao.getById(pf.getCreatedBy());
			if (createUser != null) {
				dto.setCreatedByText(createUser.getLoginName());
			}
			User modUser = this.userDao.getById(pf.getModifiedBy());
			if (modUser != null) {
				dto.setModifiedByText(modUser.getLoginName());
			}
			pfListDTO.add(dto);
		}
		pr.setPriceFormulasDTOList(pfListDTO);
		return pr;
	}
	
	public void savePriceRule(PriceRulesDTO entity,Integer userId){
		Date now = new Date();
		if(entity.getPriceRules().getCreatedBy()==null){
			entity.getPriceRules().setCreatedBy(userId);
			entity.getPriceRules().setCreationDate(now);
		}
		entity.getPriceRules().setModifiedBy(userId);
		entity.getPriceRules().setModifyDate(now);
		this.priceRulesDao.save(entity.getPriceRules());
		Integer ruleId = entity.getPriceRules().getId();
		if(entity.getPriceAttrValMapDTOList()!=null&&!entity.getPriceAttrValMapDTOList().isEmpty()){
			for(PriceRuleAttrValueMappingDTO prvm : entity.getPriceAttrValMapDTOList()){
				if(prvm.getPriceRuleValue()!=null){
					prvm.getPriceRuleValue().setRuleId(ruleId);
					this.priceRuleAttrValueMappingDao.save(prvm.getPriceRuleValue());
				}
			}
		}
		if(entity.getPriceFormulasDTOList()!=null&&!entity.getPriceFormulasDTOList().isEmpty()){
			for(PriceFormulaDTO pf : entity.getPriceFormulasDTOList()){
				if(pf.getPriceFormulas()!=null){
					if(pf.getPriceFormulas().getCreatedBy()==null){
						pf.getPriceFormulas().setCreatedBy(userId);
						pf.getPriceFormulas().setCreationDate(now);
					}
					pf.getPriceFormulas().setModifiedBy(userId);
					pf.getPriceFormulas().setModifyDate(now);
					pf.getPriceFormulas().setRuleId(ruleId);
					this.priceFormulasDao.save(pf.getPriceFormulas());
					Integer formulaId = pf.getPriceFormulas().getId();
					if(pf.getPriceFormulaItems()!=null){
						for(PriceFormulaItems pfi : pf.getPriceFormulaItems()){
							if(pfi!=null){
								pfi.setFormulaId(formulaId);
								this.priceFormulsItemsDao.save(pfi);
							}
						}
					}
					if(pf.getDelPriceFormulaItems()!=null&&!pf.getDelPriceFormulaItems().isEmpty()){
						this.priceFormulsItemsDao.delPriceFormulaItem(pf.getDelPriceFormulaItems());
					}
					if(pf.getPriceFormlaCriteriasDTO()!=null){
						for(PriceFormulaCriteriasDTO pfc : pf.getPriceFormlaCriteriasDTO()){
							
							if(pfc.getPriceFormulasCriterias().getCreatedBy()==null){
								pfc.getPriceFormulasCriterias().setCreatedBy(userId);
								pfc.getPriceFormulasCriterias().setCreationDate(now);
							}
							pfc.getPriceFormulasCriterias().setModifiedBy(userId);
							pfc.getPriceFormulasCriterias().setModifyDate(now);
							pfc.getPriceFormulasCriterias().setFormulaId(formulaId);
							this.priceFormulaCriteriasDao.save(pfc.getPriceFormulasCriterias());
						}
					}
					if(pf.getDelPriceFormlaCriterias()!=null&&!pf.getDelPriceFormlaCriterias().isEmpty()){
						this.priceFormulaCriteriasDao.delPriceFormulaCriterias(pf.getDelPriceFormlaCriterias());
					}
				}
			}
		}
		if(entity.getDelPriceAttr()!=null&&!entity.getDelPriceAttr().isEmpty()){
			this.priceRuleAttrValueMappingDao.delPriceRuleAttrValueMapping(entity.getDelPriceAttr());
		}
		if(entity.getDelPriceForms()!=null&&!entity.getDelPriceForms().isEmpty()){
			this.delPrieFormulaService(entity.getDelPriceForms());
		}
	}
	
	private void delPrieFormulaService(List<Integer> delPriceForms){
		for(Integer formsId : delPriceForms){
			this.priceFormulsItemsDao.delPriceFormulaItemByFormulaId(formsId);
			this.priceFormulaCriteriasDao.delPriceFormulaCriteriasBy(formsId);
		}
		this.priceFormulasDao.delPriceFormulas(delPriceForms);
	}
	
	public void delPriceRules(List<String> ids){
		List<Integer> idList = new ArrayList<Integer>();
		for(String id :ids){
			idList.add(Integer.valueOf(id));
		}
		this.delPriceRulesFormula(idList);
		this.priceRuleAttrValueMappingDao.delPriceRuleAttrValueMappingByRuleId(idList);
		this.priceRulesDao.delPriceRules(idList);
	}
	
	private void delPriceRulesFormula(List<Integer> delPriceRule){
		for(Integer ruleId : delPriceRule){
			List<PriceFormulas> priceFormulas = this.priceFormulasDao.findBy("ruleId", ruleId);
			List<Integer> ids=new ArrayList<Integer>();
			for(PriceFormulas priceFormula:priceFormulas){
				ids.add(priceFormula.getId());
			}
			if(!ids.isEmpty()){
				this.delPrieFormulaService(ids);
			}
			this.priceFormulasDao.getSession().evict(priceFormulas);
		}
	}
	
	public PriceFormulaDTO getFormulaDetail(Integer id){
		PriceFormulas pf = this.priceFormulasDao.getById(id);
		PriceFormulaDTO dto = new PriceFormulaDTO();
		if(pf==null){
			return dto;
		}
		dto.setPriceFormulas(pf);
		User createUser = this.userDao.getById(pf.getCreatedBy());
		if (createUser != null) {
			dto.setCreatedByText(createUser.getLoginName());
		}
		User modyUser = this.userDao.getById(pf.getModifiedBy());
		if (modyUser != null) {
			dto.setModifiedByText(modyUser.getLoginName());
		}
		List<PriceFormulaCriterias> prvmList = priceFormulaCriteriasDao.findBy("formulaId",id);
		List<PriceFormulaCriteriasDTO> prvDTOList = new ArrayList<PriceFormulaCriteriasDTO>();
		for(PriceFormulaCriterias prvm : prvmList){
			PriceFormulaCriteriasDTO prvmdto = new PriceFormulaCriteriasDTO();
			prvmdto.setPriceFormulasCriterias(prvm);
			User prvmUser = this.userDao.getById(pf.getCreatedBy());
			if (prvmUser != null) {
				prvmdto.setCreatedByText(prvmUser.getLoginName());
			}
			ServiceAttributes attributes =this.serviceAttributesDao.getById(prvm.getAttributeId());
			prvmdto.setAttributerName(attributes.getAttributeName());
			prvDTOList.add(prvmdto);
		}
		dto.setPriceFormlaCriteriasDTO(prvDTOList);
		return dto;
	}
	
	public List<PriceFormulaItemDTO> searchPriceFormulaItemsByFormulaId(Integer id){
		List<PriceFormulaItems> pfi = this.priceFormulsItemsDao.findByFormulaId(id);
		List<PriceFormulaItemDTO> dtoList = new ArrayList<PriceFormulaItemDTO>(); 
		for(int i =0;i<pfi.size();i++){
			PriceFormulaItemDTO dto = new PriceFormulaItemDTO();
			dto.setPriceFormulaItem(pfi.get(i));
			if(pfi.get(i).getType().equals("Value")){
				ServiceAttributes att = this.serviceAttributesDao.getById(Integer.valueOf(pfi.get(i).getValue()));
				if(att!=null){
					dto.setValueName(att.getAttributeName());
				}
			}
			dtoList.add(dto);
		}
		return dtoList;
	}
	
	public List<PriceRuleAttrValueMapping> searchPriceRuleAttrValueMappingByAttributeIdAndRuleId(Integer ruleId,Integer attributeId){
		return this.priceRuleAttrValueMappingDao.searchPriceRuleAttrValueMappingByAttributeIdAndRuleId(ruleId, attributeId);
	}
	
	public Page<PriceRuleGroups> searchPriceRuleGroups(Page<PriceRuleGroups> page, List<PropertyFilter> filters){
		return this.priceRuleGroupsDao.findPage(page, filters);
	}
	
	public void delPriceRuleGroups(List<String> groupIds,Integer userId){
		//List<Integer> groupId = new ArrayList<Integer>();
		for(String groupIdss:groupIds){
			List<PriceRules> priceRuleList = this.priceRulesDao.findBy("groupId", Integer.valueOf(groupIdss));
			//groupId.add(Integer.valueOf(groupIdss));
			this.priceRulesDao.getSession().evict(priceRuleList);
			for(PriceRules priceRule:priceRuleList){
				this.priceRulesDao.getSession().evict(priceRule);
				PriceRules pr = this.dozer.map(priceRule, PriceRules.class);
				pr.setGroupId(null);
				pr.setModifiedBy(userId);
				this.savePrice(pr);
			}
			//this.priceRulesDao.getSession().evict(priceRuleList);
			this.priceRuleGroupsDao.delete(Integer.valueOf(groupIdss));
		}
		//this.priceRuleGroupsDao.delPriceRuleGroups(groupId);
	}
	
	public void savePrice(PriceRules priceRules){
		this.priceRulesDao.save(priceRules);
	}
	
	public PriceRuleGroupsDTO getPriceRuleGroupsDetail(Integer groupId){
		PriceRuleGroupsDTO dto = new PriceRuleGroupsDTO();
		dto.setPriceRuleGropus(this.priceRuleGroupsDao.getById(groupId));
		User createUser = this.userDao.getById(dto.getPriceRuleGropus().getCreatedBy());
		if (createUser != null) {
			dto.setCreatedByText(createUser.getLoginName());
		}
		User modyUser = this.userDao.getById(dto.getPriceRuleGropus().getModifiedBy());
		if (modyUser != null) {
			dto.setModifiedByText(modyUser.getLoginName());
		}
		dto.setPriceRuleList(this.priceRulesDao.findBy("groupId", dto.getPriceRuleGropus().getGroupId()));
		return dto;
	}
	
	private void savePriceRulesOfGroupId(List<Integer> ids,Integer userId){
		for(Integer id : ids){
			PriceRules priceRules = this.priceRulesDao.getById(id);
			this.priceRulesDao.getSession().evict(priceRules);
			priceRules.setGroupId(null);
			priceRules.setModifiedBy(userId);
			this.priceRulesDao.save(priceRules);
		}
	}
	
	public void savePriceRulesGroup(PriceRuleGroupsDTO dto , Integer userId){
		Date now = new Date();
		dto.getPriceRuleGropus().setModifiedBy(userId);
		dto.getPriceRuleGropus().setModifyDate(now);
		if(dto.getPriceRuleGropus().getCreatedBy()==null){
			dto.getPriceRuleGropus().setCreatedBy(userId);
			dto.getPriceRuleGropus().setCreationDate(now);
		}
		this.priceRuleGroupsDao.save(dto.getPriceRuleGropus());
		for(Integer priceRuleId : dto.getAddPriceRuleList()){
			if(priceRuleId!=null){
				PriceRules priceRule = this.priceRulesDao.getById(priceRuleId);
				this.priceRulesDao.getSession().evict(priceRule);
				if(priceRule!=null){
					priceRule.setGroupId(dto.getPriceRuleGropus().getGroupId());
					this.priceRulesDao.save(priceRule);
				}
			}
		}
		this.savePriceRulesOfGroupId(dto.getDelPriceRuleList(),userId);
	}
}
