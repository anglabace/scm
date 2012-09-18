package com.genscript.gsscm.systemsetting.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.xwork.StringUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.dao.PbCountryDao;
import com.genscript.gsscm.basedata.entity.PbCountry;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.customer.dao.BankDao;
import com.genscript.gsscm.privilege.dao.UserDao;
import com.genscript.gsscm.privilege.entity.Employee;
import com.genscript.gsscm.systemsetting.dao.BillTerritoryBillingDao;
import com.genscript.gsscm.systemsetting.dao.BillTerritoryDao;
import com.genscript.gsscm.systemsetting.dao.BillTerritoryZoneDao;
import com.genscript.gsscm.systemsetting.dto.BillTerritoryDTO;
import com.genscript.gsscm.systemsetting.dto.BillTerritoryZoneDTO;
import com.genscript.gsscm.systemsetting.entity.BillTerritory;
import com.genscript.gsscm.systemsetting.entity.BillTerritoryBilling;
import com.genscript.gsscm.systemsetting.entity.BillTerritoryZone;

@Service
@Transactional
public class BillTerritoryService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private BillTerritoryDao billTerritoryDao;
	@Autowired(required = false)
	private DozerBeanMapper dozer;
	@Autowired
	private BillTerritoryZoneDao billTerritoryZoneDao;
	@Autowired
	private BillTerritoryBillingDao billTerritoryBillingDao;
	@Autowired
	private BankDao bankDao;
	@Autowired
	private PbCountryDao pbCountryDao;

	/**
	 * 批量更新territory
	 * @author Zhang Yong
	 * @param territoryIds
	 */
	public void delBillTerritory(String territoryIds) {
		if (StringUtils.isNotBlank(territoryIds)) {
			String[] territoryIdArr = territoryIds.split(",");
			List<Integer> btIds = new ArrayList<Integer>();
			for (String territoryId : territoryIdArr) {
				if (StringUtils.isNotBlank(territoryId)
						&& StringUtils.isNumeric(territoryId)) {
					btIds.add(Integer.parseInt(territoryId));
				}
			}
			List<BillTerritory> btList = billTerritoryDao.findByIds(btIds);
			if (btList != null && !btList.isEmpty()) {
				List<Integer> toUpdateBtIds = new ArrayList<Integer>();
				Iterator<BillTerritory> item = btList.iterator();
				while (item.hasNext()) {
					toUpdateBtIds.add(item.next().getTerritoryId());
				}
				billTerritoryDao.updateBillTerritorys(toUpdateBtIds,
						SessionUtil.getUserId());
			}
		}
	}

	/**
	 * 批量更新Zone
	 * @author Zhang Yong
	 * @param zoneIds
	 */
	@Transactional(readOnly = true)
	public void delBillTerritoryZones(String zoneIds, String sessTerritoryId) {
		if (StringUtils.isBlank(zoneIds)
				|| StringUtils.isBlank(sessTerritoryId)) {
			return;
		}
		@SuppressWarnings("unchecked")
		List<BillTerritoryZoneDTO> btzDTOList = (List<BillTerritoryZoneDTO>) SessionUtil
				.getRow(SessionConstant.BillTerritoryZoneList.value(),
						sessTerritoryId);
		if (btzDTOList == null || btzDTOList.isEmpty()) {
			return;
		}
		String[] zoneIdArr = zoneIds.split(",");
		Map<String, String> delIdMap = new HashMap<String, String>();
		for (String zoneId : zoneIdArr) {
			if (StringUtils.isNotBlank(zoneId)) {
				delIdMap.put(zoneId, zoneId);
			}
		}
		if (delIdMap.isEmpty()) {
			return;
		}
		List<BillTerritoryZoneDTO> newBtzDtoList = new ArrayList<BillTerritoryZoneDTO>();
		Iterator<BillTerritoryZoneDTO> item = btzDTOList.iterator();
		while (item.hasNext()) {
			BillTerritoryZoneDTO btz = item.next();
			if (!delIdMap.containsKey(btz.getSessZoneId())) {
				newBtzDtoList.add(btz);
			}
		}
		SessionUtil.insertRow(SessionConstant.BillTerritoryZoneList.value(),
				sessTerritoryId, newBtzDtoList);
	}

	/**
	 * @author Zhang Yong 查询BillTerritory列表
	 * @param page
	 * @param filters
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<BillTerritory> searchBillTerritory(Page<BillTerritory> page,
			List<PropertyFilter> filters) {
		if (filters == null || filters.isEmpty()) {
			return billTerritoryDao.findPage(page);
		} else {
			return billTerritoryDao.findPage(page, filters);
		}
	}

	/**
	 * 分页查询zone
	 * @author Zhang Yong
	 * @param zonePage
	 * @param sessTerritoryId
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<BillTerritoryZoneDTO> searchBillTerritoryZone(
			Page<BillTerritoryZoneDTO> zonePage, String sessTerritoryId) {
		if (StringUtils.isNotBlank(sessTerritoryId)) {
			@SuppressWarnings("unchecked")
			List<BillTerritoryZoneDTO> btzDTOList = (List<BillTerritoryZoneDTO>) SessionUtil
					.getRow(SessionConstant.BillTerritoryZoneList.value(),
							sessTerritoryId);
			if (btzDTOList == null || btzDTOList.isEmpty()) {
				return zonePage;
			}
			zonePage.setTotalCount(Long.valueOf(btzDTOList.size()));
			List<BillTerritoryZoneDTO> resultlist = new ArrayList<BillTerritoryZoneDTO>();
			int num = 1;
			for (int i = (zonePage.getFirst() - 1); i < btzDTOList.size(); i++) {
				resultlist.add(btzDTOList.get(i));
				if (num == zonePage.getPageSize().intValue()) {
					break;
				}
				num++;
			}
			zonePage.setResult(resultlist);
		}
		return zonePage;
	}

	/**
	 * 获取BillTerritory的详细信息
	 * @author Zhang Yong
	 * @param sessTerritoryId
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public BillTerritoryDTO getBillTerritoryById(String sessTerritoryId)
			throws Exception {
		BillTerritoryDTO sessTerritory = (BillTerritoryDTO) SessionUtil.getRow(
					SessionConstant.BillTerritory.value(), sessTerritoryId);
		if (sessTerritory != null) {
			return sessTerritory;
		}
		if (sessTerritory == null && StringUtils.isNumeric(sessTerritoryId)) {
			BillTerritory billTerritory = billTerritoryDao.getById(Integer
					.parseInt(sessTerritoryId));
			sessTerritory = dozer
					.map(billTerritory, BillTerritoryDTO.class);
			// 获取BillTerritoryBilling
			BillTerritoryBilling btb = billTerritoryBillingDao
					.getByTerrId(sessTerritory.getTerritoryId());
			if (btb != null) {
				sessTerritory.setBillingId(btb.getBillingId());
				sessTerritory.setAccountUsage(btb.getAccountUsage());
				sessTerritory.setAccountType(btb.getAccountType());
				sessTerritory.setBank(btb.getBank());
				sessTerritory.setPhone(btb.getPhone());
				sessTerritory.setAccountNo(btb.getAccountNo());
				sessTerritory.setRoutingNo(btb.getRoutingNo());
				sessTerritory.setIban(btb.getIban());
				sessTerritory.setBban(btb.getBban());
				sessTerritory.setPhoneExt(btb.getPhoneExt());
				sessTerritory.setAddrLine1(btb.getAddrLine1());
				sessTerritory.setAddrLine2(btb.getAddrLine2());
				sessTerritory.setAddrLine3(btb.getAddrLine3());
				sessTerritory.setCountry(btb.getCountry());
				sessTerritory.setCity(btb.getCity());
				sessTerritory.setZipCode(btb.getZipCode());
				sessTerritory.setState(btb.getState());
			}
			// 设置modifiedByUser，去的name是employee name
			Employee employee = userDao.getById(SessionUtil.getUserId())
					.getEmployee();
			sessTerritory.setModifiedByUser(employee != null ? employee
					.getEmployeeName() : null);
			// 获取BillTerritoryZone
			List<BillTerritoryZone> billTerrList = billTerritoryZoneDao
					.findByTerrId(billTerritory.getTerritoryId());
			if (billTerrList != null && !billTerrList.isEmpty()) {
				List<BillTerritoryZoneDTO> btzDTOList = new ArrayList<BillTerritoryZoneDTO>();
				for (Iterator<BillTerritoryZone> item = billTerrList
						.iterator(); item.hasNext();) {
					BillTerritoryZoneDTO btzDTO = dozer.map(item.next(),
							BillTerritoryZoneDTO.class);
					btzDTO.setSessZoneId(btzDTO.getZoneId().toString());
					btzDTO.setModifiedByUser(employee != null ? employee
							.getEmployeeName() : null);
					btzDTOList.add(btzDTO);
				}
				SessionUtil.insertRow(
						SessionConstant.BillTerritoryZoneList.value(),
						sessTerritoryId, btzDTOList);
			}
		} else {
			sessTerritory = new BillTerritoryDTO();
		}
		sessTerritory.setBankList(bankDao.getAll());
		SessionUtil.insertRow(SessionConstant.BillTerritory.value(),
				sessTerritoryId, sessTerritory);
		return sessTerritory;
	}

	/**
	 * 查询Zone
	 * @author Zhang Yong
	 * @param sessTerritoryId
	 * @param sessZoneId
	 * @return
	 */
	@Transactional(readOnly = true)
	public BillTerritoryZoneDTO getBillTerritoryZoneById(
			String sessTerritoryId, String sessZoneId) {
		BillTerritoryZoneDTO billTerritoryZoneDTO = null;
		if (StringUtils.isBlank(sessZoneId)
				|| !StringUtils.isNumeric(sessZoneId)
				|| StringUtils.isBlank(sessTerritoryId)) {
			return billTerritoryZoneDTO;
		}
		@SuppressWarnings("unchecked")
		List<BillTerritoryZoneDTO> billTerrList = (List<BillTerritoryZoneDTO>) SessionUtil
				.getRow(SessionConstant.BillTerritoryZoneList.value(),
						sessTerritoryId);
		if (billTerrList != null && !billTerrList.isEmpty()) {
			Iterator<BillTerritoryZoneDTO> item = billTerrList.iterator();
			while (item.hasNext()) {
				BillTerritoryZoneDTO btzDto = item.next();
				if (btzDto.getZoneId() != null
						&& btzDto.getZoneId().intValue() == Integer
								.parseInt(sessZoneId)) {
					billTerritoryZoneDTO = dozer.map(btzDto,
							BillTerritoryZoneDTO.class);
					break;
				}
			}
		}
		return billTerritoryZoneDTO;
	}

	/**
	 * 通过continent升序查询Continent所有记录
	 * @author Zhang Yong
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<PbCountry> getContinent() {
		List<PbCountry> pclist = new ArrayList<PbCountry>();
		List<String> continentlist = pbCountryDao.getAllPbCountry();
		if (continentlist != null && !continentlist.isEmpty()) {
			for (Iterator<String> item = continentlist.iterator();item.hasNext();) {
				PbCountry pc = new PbCountry();
				pc.setContinent(item.next());
				pclist.add(pc);
			}
		}
		return pclist;
	}

	/**
	 * 将zone信息保存到session中
	 * @author Zhang Yong
	 * @param btzDto
	 * @param sessTerritoryId
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	@Transactional(readOnly = true)
	public String saveZone(BillTerritoryZoneDTO btzDto, String sessTerritoryId)
			throws Exception {
		String rtnMessage = null;
		@SuppressWarnings("unchecked")
		List<BillTerritoryZoneDTO> billTerrList = (List<BillTerritoryZoneDTO>) SessionUtil
				.getRow(SessionConstant.BillTerritoryZoneList.value(),
						sessTerritoryId);
		if (billTerrList == null) {
			billTerrList = new ArrayList<BillTerritoryZoneDTO>();
		}
		if (StringUtils.isNotBlank(btzDto.getSessZoneId())) {
			if (!billTerrList.isEmpty()) {
				boolean hasSame = checkZone(btzDto, billTerrList);
				if (hasSame) {
					rtnMessage = "There have the same record in database, please fill in another infomation.";
					return rtnMessage;
				}
				//更新数据到session中
				Iterator<BillTerritoryZoneDTO> item = billTerrList.iterator();
				while (item.hasNext()) {
					BillTerritoryZoneDTO sessBtzDto = item.next();
					if (StringUtils.isNotBlank(sessBtzDto.getSessZoneId())
							&& sessBtzDto.getSessZoneId().equals(
									btzDto.getSessZoneId())) {
						if (sessBtzDto.getZoneId() != null) {
							btzDto.setZoneId(sessBtzDto.getZoneId());
							btzDto.setTerritoryId(sessBtzDto.getTerritoryId());
							btzDto.setModifiedByUser(sessBtzDto
									.getModifiedByUser());
							btzDto.setModifyDate(sessBtzDto.getModifyDate());
							billTerrList.remove(sessBtzDto);
							if (billTerrList != null) {
								billTerrList.add(btzDto);
							} else {
								billTerrList = new ArrayList<BillTerritoryZoneDTO>();
								billTerrList.add(btzDto);
							}
						}
						break;
					}
				}
			}
		} else {
			String sessZoneId = SessionUtil.generateTempId();
			btzDto.setSessZoneId(sessZoneId);
			boolean hasSame = checkZone(btzDto, billTerrList);
			if (hasSame) {
				rtnMessage = "There have the same record in database, please fill in another infomation.";
				return rtnMessage;
			}
			billTerrList.add(btzDto);
		}
		SessionUtil.insertRow(SessionConstant.BillTerritoryZoneList.value(),
				sessTerritoryId, billTerrList);
		return rtnMessage;
	}
	
	/**
	 * 检查数据是否重复
	 * @author Zhang Yong
	 * @param btzDto
	 * @param billTerrList
	 * @return
	 */
	private boolean checkZone (BillTerritoryZoneDTO btzDto, List<BillTerritoryZoneDTO> billTerrList) {
		boolean hasSame = false;
		String paramContinent = btzDto.getContinent()==null?"":btzDto.getContinent();
		String paramCountry = btzDto.getCountry()==null?"":btzDto.getCountry();
		String paramState = btzDto.getState()==null?"":btzDto.getState();
		String paramZipFrom = btzDto.getZipFrom()==null?"":btzDto.getZipFrom();
		String paramZipTo = btzDto.getZipTo()==null?"":btzDto.getZipTo();
		List<BillTerritoryZone> dBBtzList = billTerritoryZoneDao.getTerritoryZone(paramContinent, 
				paramCountry, paramState, paramZipFrom, paramZipTo);
		if (dBBtzList != null && !dBBtzList.isEmpty()) {
			if (dBBtzList.size() >1) {
				hasSame = true;
				return hasSame;
			}
			Iterator<BillTerritoryZone> dbBtzItem = dBBtzList.iterator();
			while (dbBtzItem.hasNext()) {
				BillTerritoryZone dbBtz = dbBtzItem.next();
				if (btzDto.getZoneId() != null && btzDto.getZoneId() != dbBtz.getZoneId()) {
					hasSame = true;
					return hasSame;
				}
				if (StringUtils.isNumeric(btzDto.getSessZoneId()) && dbBtz.getZoneId() != Integer.parseInt(btzDto.getSessZoneId())) {
					hasSame = true;
					return hasSame;
				}
			}
		}
		//检查是否有相同的数据
		Iterator<BillTerritoryZoneDTO> checkItem = billTerrList.iterator();
		while (checkItem.hasNext()) {
			BillTerritoryZoneDTO sessBtzDto = checkItem.next();
			if (StringUtils.isNotBlank(sessBtzDto.getSessZoneId())
					&& !sessBtzDto.getSessZoneId().equals(btzDto.getSessZoneId())) {
				String sessContinent = sessBtzDto.getContinent()==null?"":sessBtzDto.getContinent();
				String sessCountry = sessBtzDto.getCountry()==null?"":sessBtzDto.getCountry();
				String sessState = sessBtzDto.getState()==null?"":sessBtzDto.getState();
				String sessZipFrom = sessBtzDto.getZipFrom()==null?"":sessBtzDto.getZipFrom();
				String sessZipTo = sessBtzDto.getZipTo()==null?"":sessBtzDto.getZipTo();
				if (paramContinent.equals(sessContinent) && paramCountry.equals(sessCountry) 
						&& paramState.equals(sessState) && paramZipFrom.equals(sessZipFrom)
						&& paramZipTo.equals(sessZipTo)) {
					hasSame = true;
					break;
				}
			}
		}
		return hasSame;
	}
	
	/**
	 * 保存BillTerritory
	 * @author Zhang Yong
	 * @param sessTerritoryId
	 * @param btDto
	 */
	public String saveBillTerritory(String sessTerritoryId, BillTerritoryDTO btDto) {
		String rtnMessage = null;
		if (StringUtils.isBlank(sessTerritoryId) || btDto == null) {
			return rtnMessage;
		}
		if (StringUtils.isBlank(btDto.getTerritoryCode())) {
			rtnMessage = "Billing Territories Code can not be null.";
			return rtnMessage;
		}
		List<BillTerritory> billTerritoryList = this.billTerritoryDao.findByTerriCode(btDto.getTerritoryCode());
		if (billTerritoryList != null && !billTerritoryList.isEmpty()) {
			if (billTerritoryList.size() == 1) {
				if (!StringUtils.isNumeric(sessTerritoryId) || Integer.parseInt(sessTerritoryId) 
						!= billTerritoryList.get(0).getTerritoryId().intValue()) {
					rtnMessage = "There have the same Billing Territories Code in database, please fill out anothers.";
					return rtnMessage;
				} 
			} else {
				rtnMessage = "There have the same Billing Territories Code in database, please fill out anothers.";
				return rtnMessage;
			}
		}
		BillTerritoryDTO sessBillTerr = (BillTerritoryDTO) SessionUtil.getRow(
				SessionConstant.BillTerritory.value(), sessTerritoryId);
		if (sessBillTerr == null) {
			return rtnMessage;
		}
		Date now = new Date();
		Integer userId = SessionUtil.getUserId();
		BillTerritory billTerritory = null;
		// 保存billTerritory和billTerritoryBilling
		if (StringUtils.isNumeric(sessTerritoryId)) {
			BillTerritory dbBillTerritory = billTerritoryDao.getById(Integer
					.parseInt(sessTerritoryId));
			dbBillTerritory.setTerritoryName(btDto.getTerritoryName());
			dbBillTerritory.setDescription(btDto.getDescription());
			dbBillTerritory.setStatus(btDto.getStatus());
			dbBillTerritory.setAccountCode(btDto.getAccountCode());
			dbBillTerritory.setModifiedBy(userId);
			dbBillTerritory.setModifyDate(now);
			billTerritoryDao.save(dbBillTerritory);
			billTerritory = dbBillTerritory;
			BillTerritoryBilling dbBillTerritoryBilling = billTerritoryBillingDao
					.getByTerrId(dbBillTerritory.getTerritoryId());
			dbBillTerritoryBilling.setAccountUsage(btDto.getAccountUsage());
			dbBillTerritoryBilling.setAccountType(btDto.getAccountType());
			dbBillTerritoryBilling.setBank(btDto.getBank());
			dbBillTerritoryBilling.setAccountNo(btDto.getAccountNo());
			dbBillTerritoryBilling.setRoutingNo(btDto.getRoutingNo());
			dbBillTerritoryBilling.setIban(btDto.getIban());
			dbBillTerritoryBilling.setBban(btDto.getBban());
			dbBillTerritoryBilling.setPhone(btDto.getPhone());
			dbBillTerritoryBilling.setPhoneExt(btDto.getPhoneExt());
			dbBillTerritoryBilling.setAddrLine1(btDto.getAddrLine1());
			dbBillTerritoryBilling.setAddrLine2(btDto.getAddrLine2());
			dbBillTerritoryBilling.setAddrLine3(btDto.getAddrLine3());
			dbBillTerritoryBilling.setCity(btDto.getCity());
			dbBillTerritoryBilling.setZipCode(btDto.getZipCode());
			dbBillTerritoryBilling.setState(btDto.getState());
			dbBillTerritoryBilling.setCountry(btDto.getCountry());
			dbBillTerritoryBilling.setModifiedBy(userId);
			dbBillTerritoryBilling.setModifyDate(now);
			billTerritoryBillingDao.save(dbBillTerritoryBilling);
		} else {
			billTerritory = dozer.map(btDto, BillTerritory.class);
			billTerritory.setTerritoryId(null);
			billTerritory.setCreatedBy(userId);
			billTerritory.setCreationDate(now);
			billTerritory.setModifiedBy(userId);
			billTerritory.setModifyDate(now);
			billTerritoryDao.save(billTerritory);
			BillTerritoryBilling billTerritoryBilling = dozer.map(btDto,
					BillTerritoryBilling.class);
			billTerritoryBilling.setCreatedBy(userId);
			billTerritoryBilling.setCreationDate(now);
			billTerritoryBilling.setModifiedBy(userId);
			billTerritoryBilling.setModifyDate(now);
			billTerritoryBilling.setTerritoryId(billTerritory.getTerritoryId());
			billTerritoryBillingDao.save(billTerritoryBilling);
		}
		// 保存BillTerritoryZone
		List<BillTerritoryZone> dbBtzList = billTerritoryZoneDao
				.findByTerrId(billTerritory.getTerritoryId());
		@SuppressWarnings("unchecked")
		List<BillTerritoryZoneDTO> billTerrList = (List<BillTerritoryZoneDTO>) SessionUtil
				.getRow(SessionConstant.BillTerritoryZoneList.value(),
						sessTerritoryId);
		if (billTerrList == null || billTerrList.isEmpty()) {
			if (dbBtzList != null && !dbBtzList.isEmpty()) {
				billTerritoryZoneDao.deleteByTerrId(billTerritory
						.getTerritoryId());
				return "The new Territory Zone can not be update.";
			}
		} else {
			Map<String, BillTerritoryZone> dbMap = new HashMap<String, BillTerritoryZone>();
			if (dbBtzList != null && !dbBtzList.isEmpty()) {
				Iterator<BillTerritoryZone> dbBtzItem = dbBtzList.iterator();
				while (dbBtzItem.hasNext()) {
					BillTerritoryZone dbBtz = dbBtzItem.next();
					dbMap.put(dbBtz.getZoneId().toString(), dbBtz);
				}
			}
			Iterator<BillTerritoryZoneDTO> sessBtzItem = billTerrList
					.iterator();
			while (sessBtzItem.hasNext()) {
				BillTerritoryZoneDTO sessBtz = sessBtzItem.next();
				BillTerritoryZone btz = dozer.map(sessBtz,
						BillTerritoryZone.class);
				if (sessBtz.getZoneId() != null && dbMap != null
						&& dbMap.containsKey(sessBtz.getZoneId().toString())) {
					dbMap.remove(sessBtz.getZoneId().toString());
					BillTerritoryZone dbBtz = billTerritoryZoneDao.getById(btz.getZoneId());
					dbBtz.setContinent(btz.getContinent());
					dbBtz.setCountry(btz.getCountry());
					dbBtz.setState(btz.getState());
					dbBtz.setZipFrom(btz.getZipFrom());
					dbBtz.setZipTo(btz.getZipTo());
					dbBtz.setModifiedBy(userId);
					dbBtz.setModifyDate(now);
					billTerritoryZoneDao.save(dbBtz);
				} else {
					btz.setZoneId(null);
					btz.setCreatedBy(userId);
					btz.setCreationDate(now);
					btz.setTerritoryId(billTerritory.getTerritoryId());
					btz.setModifiedBy(userId);
					btz.setModifyDate(now);
					billTerritoryZoneDao.save(btz);
				}
			}
			// 删除Zone
			if (dbMap != null && !dbMap.isEmpty()) {
				Iterator<Entry<String, BillTerritoryZone>> delItem = dbMap
						.entrySet().iterator();
				while (delItem.hasNext()) {
					Entry<String, BillTerritoryZone> entry = delItem.next();
					String delZoneId = entry.getKey();
					billTerritoryZoneDao.delete(Integer.parseInt(delZoneId));
				}
			}
		}
		return rtnMessage;
	}
}
