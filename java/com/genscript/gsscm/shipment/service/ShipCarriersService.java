package com.genscript.gsscm.shipment.service;

import java.text.ParseException;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.privilege.dao.UserDao;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.shipment.dao.ShipCarriersBillingDao;
import com.genscript.gsscm.shipment.dao.ShipCarriersDao;
import com.genscript.gsscm.shipment.dto.ShipCarriersDTO;
import com.genscript.gsscm.shipment.entity.ShipCarrierBilling;
import com.genscript.gsscm.shipment.entity.ShipCarriers;

@Service
@Transactional
public class ShipCarriersService {
	@Autowired
	private ShipCarriersBillingDao shipCarriersBillingDao;
	@Autowired
	private ShipCarriersDao shipCarriersDao;
	@Autowired
	private DozerBeanMapper dozer;
	@Autowired
	private UserDao userDao;

	@Transactional(readOnly = true)
	public Page<ShipCarriers> searchShipCarriers(Page<ShipCarriers> page) {
		return shipCarriersDao.findPage(page);
	}

	@Transactional(readOnly = true)
	public Page<ShipCarriers> searchShipCarriers(Page<ShipCarriers> page,
			List<PropertyFilter> filters) {
		return shipCarriersDao.findPage(page, filters);
	}

	public void delShipCarrier(Integer CarrierId) {
		shipCarriersDao.delete(CarrierId);
	}

	public void deleshipCarriers(Integer CarrierId) {
		shipCarriersDao.logicDeleteOne(CarrierId);
	}

	public void delShipCarriers(List<Integer> list) {
		for (Integer carrierId : list) {
			delShipCarrier(carrierId);
		}
	}

	public User getUser(Integer clerkId) {
		return userDao.getById(clerkId);
	}

	public String getEmployeeName(int clerkId) {
		User user = userDao.getById(clerkId);
		if (user != null && user.getEmployee() != null) {
			return user.getEmployee().getEmployeeName();
		} else {
			return "";
		}

	}

	public ShipCarriers saveShipCarriers(final ShipCarriersDTO shipCarriers,
			String state, String billid) throws ParseException {
		ShipCarriers shipCarrierbean = null;
		ShipCarrierBilling shipCarriersBilling = new ShipCarrierBilling();
		shipCarrierbean = dozer.map(shipCarriers, ShipCarriers.class); 
		this.shipCarriersDao.save(shipCarrierbean); 
		shipCarriersBilling.setState(state); 
		if (billid != null && !"".equals(billid)) {
			shipCarriersBilling.setId(Integer.parseInt(billid));
		}
		shipCarriersBilling.setAddrLine1(shipCarriers.getAddrLine1());
		shipCarriersBilling.setAddrLine2(shipCarriers.getAddrLine2());
		shipCarriersBilling.setAddrLine3(shipCarriers.getAddrLine3());
		shipCarriersBilling.setAccountNo(shipCarriers.getAccountNo());
		shipCarriersBilling.setAccountPwd(shipCarriers.getAccountPwd());
		shipCarriersBilling.setBillStatus(shipCarriers.getBillStatus());
		shipCarriersBilling.setBillType(shipCarriers.getBillType());
		shipCarriersBilling.setCarrierId(shipCarriers.getCarrierId());
		shipCarriersBilling.setCity(shipCarriers.getCity());
		shipCarriersBilling.setCountry(shipCarriers.getCountry());
		shipCarriersBilling.setPhone(shipCarriers.getPhone());
		shipCarriersBilling.setPhoneExt(shipCarriers.getPhoneExt());
		shipCarriersBilling.setZipCode(shipCarriers.getZipCode());
		shipCarriersBilling.setModifiedBy(shipCarriers.getModifiedBy());
		shipCarriersBilling.setCreationDate(shipCarriers.getCreationDate());
		shipCarriersBilling.setModifyDate(shipCarriers.getModifyDate());
		shipCarriersBilling.setCreatedBy(shipCarrierbean.getCreatedBy());
		shipCarriersBilling.setCarrierId(shipCarrierbean.getCarrierId());
		this.shipCarriersBillingDao.save(shipCarriersBilling);
		return shipCarrierbean;
	}

	public ShipCarriersDTO getCarriersDetail(Integer carrierId) {
		System.out.println(carrierId);
		ShipCarrierBilling shipbilling = null;
		ShipCarriersDTO shipdto = null;
		ShipCarriers shipcarriers = shipCarriersDao.getById(carrierId);

		List<ShipCarrierBilling> shipbillinglist = shipCarriersBillingDao
				.getBycarrierId(carrierId);
		if (shipcarriers != null) {
			System.out.println(">>>>>>>>>" + shipcarriers.getCarrierId());
			shipdto = dozer.map(shipcarriers, ShipCarriersDTO.class);
		}
		if (shipbillinglist != null) {
			System.out.println(">>>>>>>>>>>>" + shipbillinglist.size());
			if (shipbillinglist.size() > 0) {
				shipbilling = shipbillinglist.get(0);
				if (shipbilling.getBillType() != null) {
					shipdto.setBillType(shipbilling.getBillType());
				}
				if (shipbilling.getZipCode() != null) {
					shipdto.setZipCode(shipbilling.getZipCode());
				}
				if (shipbilling.getId() != null) {
					shipdto.setBillid(shipbilling.getId());
				}
				if (shipbilling.getAccountNo() != null) {
					shipdto.setAccountNo(shipbilling.getAccountNo());
				}
				if (shipbilling.getAccountPwd() != null) {
					shipdto.setAccountPwd(shipbilling.getAccountPwd());
				}
				if (shipbilling.getBillStatus() != null) {
					shipdto.setBillStatus(shipbilling.getBillStatus());
				}
				if (shipbilling.getCity() != null) {
					shipdto.setCity(shipbilling.getCity());
				}
				if (shipbilling.getCountry() != null) {
					shipdto.setCountry(shipbilling.getCountry());
				}
				if (shipbilling.getAddrLine1() != null) {
					shipdto.setAddrLine1(shipbilling.getAddrLine1());

				}
				if (shipbilling.getAddrLine3() != null) {
					shipdto.setAddrLine2(shipbilling.getAddrLine3());
				}

				if (shipbilling.getAddrLine3() != null) {
					shipdto.setAddrLine3(shipbilling.getAddrLine3());
				}

				if (shipbilling.getPhone() != null) {
					shipdto.setPhone(shipbilling.getPhone());
				}

				if (shipbilling.getState() != null) {
					shipdto.setState(shipbilling.getState());
				}
				if (shipbilling.getPhoneExt() != null) {
					shipdto.setPhoneExt(shipbilling.getPhoneExt());
				}
			}
		}
		return shipdto;
	}

}
