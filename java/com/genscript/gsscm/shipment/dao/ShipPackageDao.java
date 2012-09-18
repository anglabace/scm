package com.genscript.gsscm.shipment.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.inventory.entity.Warehouse;
import com.genscript.gsscm.order.entity.OrderAddress;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.shipment.dto.ShipPackageDTO;
import com.genscript.gsscm.shipment.dto.ShipPackagesSrchDTO;
import com.genscript.gsscm.shipment.dto.ViewPackingSlipDTO;
import com.genscript.gsscm.shipment.entity.ShipMethod;
import com.genscript.gsscm.shipment.entity.ShipPackage;

@Repository
public class ShipPackageDao extends HibernateDao<ShipPackage, Integer> {
	/**
	 * 根据orderNo获得shipPackage list.
	 * 
	 * @param orderNo
	 * @return
	 */
	public List<ShipPackage> getShipPackageList(Integer orderNo) {
		String hql = "from ShipPackage where orderNo=:orderNo order by packageId ASC";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNo", orderNo);
		return this.find(hql, map);
	}

	/**
	 * 获得一个Order所有Package的数量.
	 * 
	 * @param orderNo
	 * @return
	 */
	public Long getOrderPackageCount(Integer orderNo) {
		String hql = "select count(*) from ShipPackage where orderNo=?";
		return this.findUnique(hql, orderNo);
	}

	/**
	 * 获得一个Order所有Package中zone的类别数.
	 * 
	 * @param orderNo
	 * @return
	 */
	public Long getOrderPackageZoneCount(Integer orderNo) {
		String hql = "select count(distinct zone) from ShipPackage where orderNo=?";
		return this.findUnique(hql, orderNo);
	}

	/**
	 * 获得一个Order所有Package中ship method的类别数.
	 * 
	 * @param orderNo
	 * @return
	 */
	public Long getOrderPackageShipMethodCount(Integer orderNo) {
		String hql = "select count(distinct shipMethod) from ShipPackage where orderNo=?";
		return this.findUnique(hql, orderNo);
	}

	/**
	 * 获得一个Order所有Package的ship method list.
	 * 
	 * @param orderNo
	 * @return
	 */
	public List<Integer> getOrderShipMethodList(Integer orderNo) {
		String hql = "select shipMethod from ShipPackage where orderNo=?";
		return this.find(hql, orderNo);
	}

	/**
	 * 获得一个Order的第一个Package.
	 * 
	 * @param orderNo
	 * @return
	 */
	public ShipPackage getOrderFirstPackage(Integer orderNo) {
		Page<ShipPackage> page = new Page<ShipPackage>();
		page.setPageNo(1);
		page.setPageSize(1);
		String hql = "from ShipPackage where orderNo=? order by packageId asc";
		List<ShipPackage> list = this.findPage(page, hql, orderNo).getResult();
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 获得一个Order所有Package的weight之和.
	 * 
	 * @param orderNo
	 * @return
	 */
	public Double getOrderPackageWeight(Integer orderNo) {
		String hql = "select sum(billableWeight) from ShipPackage where orderNo=?";
		return this.findUnique(hql, orderNo);
	}

	/**
	 * 获得一个Order所有Package的Customer charge之和.
	 * 
	 * @param orderNo
	 * @return
	 */
	public Double getOrderPackageCost(Integer orderNo) {
		String hql = "select sum(customerCharge) from ShipPackage where orderNo=?";
		return this.findUnique(hql, orderNo);
	}

	/**
	 * 删除一个Order所有的Package list.
	 * 
	 * @param orderNo
	 */
	public void delShipPackageListByOrderNo(Integer orderNo) {
		String hql = "delete from ShipPackage where orderNo=:orderNo";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNo", orderNo);
		this.batchExecute(hql, map);
	}

	/**
	 * 删除一个Quote所有的Package list.
	 * 
	 * @param quoteNo
	 */
	public void delShipPackageListByQuoteNo(Integer quoteNo) {
		String hql = "delete from ShipPackage where quoteNo=:quoteNo";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quoteNo", quoteNo);
		this.batchExecute(hql, map);
	}

	/**
	 * 批量删除多个ShipPackage.
	 * 
	 * @param delPackageIdList
	 */
	public void delShipPackageList(List<Integer> delPackageIdList) {
		String hql = "delete from ShipPackage where packageId in (:delPackageIdList)";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("delPackageIdList", delPackageIdList);
		this.batchExecute(hql, map);
	}

	/**
	 * 获得一个package的总的quantity(所有packageItem quantity的总和).
	 * 
	 * @param packageId
	 * @return
	 */
	public Long getTotalQty(Integer packageId) {
		String hql = "select sum(quantity) from ShipPackageItem where packageId=?";
		return this.findUnique(hql, packageId);
	}

	/**
	 * 更新到shippackages数据
	 * 
	 * @param sps
	 */
	public void saveSps(ShipPackage sps) {
		this.save(sps);
	}

	/***************************************************************************
	 * ShipPackagesDao
	 **************************************************************************/
	/**
	 * 美国仓库:根据shipmentId查询shipPackages，返回一个list集合
	 * 
	 * @param shipmentId
	 * @return List
	 */
	public Page<ShipPackage> searchPkg(Page<ShipPackage> page,
			Integer shipmentId) {
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "from ShipPackage sp where 1=1";
		if (shipmentId != null && shipmentId.intValue() != 0) {
			hql += " and sp.shipmentId=:shipmentId";
			map.put("shipmentId", shipmentId);
		}
		if (page.getOrder() != null && page.getOrder().trim().length() > 0) {
			hql += " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		page = this.findPage(page, hql, map);
		return page;
	}

	/**
	 * 查询ShipPackages对象并返回Page对象
	 * 
	 * @param page
	 * @return Page
	 */
	@SuppressWarnings("unchecked")
	public Page<ShipPackage> getshipmentsList(Page<ShipPackage> page) {
		List<ShipPackage> packagesList = null;
		String hql = "from ShipPackage";
		Query q = createQuery(hql);
		setPageParameter(q, page);
		packagesList = q.list();
		page.setResult(packagesList);
		return page;
	}

	/**
	 * 根据shipmentId分页查询ShipPackages数据
	 * 
	 * @param page
	 * @param shipmentId
	 * @return Page
	 */
	public Page<ShipPackage> searchPackages(Page<ShipPackage> page,
			Serializable shipmentId) {
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "from ShipPackage where 1=1";
		if (shipmentId != null && (Integer) shipmentId != 0) {
			hql += " and shipments.shipmentId=:shipmentId order by creationDate";
			map.put("shipmentId", shipmentId);
		}
		page = this.findPage(page, hql, map);
		return page;
	}

	/**
	 * 按条件查询ShipPackages数据
	 * 
	 * @param page
	 * @param shipschdto
	 * @return Page
	 */
	public Page<ShipPackage> searchShipPackagess(Page<ShipPackage> page,
			ShipPackagesSrchDTO shipschdto) {
		if (shipschdto == null) {
			shipschdto = new ShipPackagesSrchDTO();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "from ShipPackage a where 1=1 ";

		if (shipschdto.getStatus() != null
				&& shipschdto.getStatus().trim().length() > 0) {
			hql += " and a.status=:status";
			map.put("status", shipschdto.getStatus());
		}
		if (page.getOrder() != null && page.getOrder().trim().length() > 0) {
			hql += " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		page = this.findPage(page, hql, map);
		return page;
	}

	/**
	 * 按条件查询ShipPackages数据
	 * 
	 * @param page
	 * @param filters
	 * @return
	 */
	public Page<ShipPackage> searchShipPackagess(Page<ShipPackage> page,
			List<PropertyFilter> filters) {
		return findPage(page, filters);
	}

	/**
	 * 查询ShipPackages对象并返回List
	 * 
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List getListByClert() {
		String hql = "from ShipPackage";
		return this.find(hql);
	}

	/**
	 * 查询Warehouse对象并返回List
	 * 
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List getListByWarehouse() {
		String hql = "from Warehouse";
		return this.find(hql);
	}

	/**
	 * 根据warehouseId查询并返回Warehouse对象
	 * 
	 * @param warehouseId
	 * @return Warehouse
	 */
	public Warehouse findById(Integer warehouseId) {
		String hql = "from Warehouse where warehouseId = ?";
		return this.findUnique(hql, warehouseId);
	}

	/**
	 * 针对中国仓库的查询列表,参数error,用于判断yes/no
	 * 
	 * @param page
	 * @param srch
	 * @param error
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<ShipPackageDTO> searchch(Page<ShipPackage> page,
			ShipPackageDTO srch, String error) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Page<ShipPackageDTO> packagesDTO = new Page<ShipPackageDTO>();
		if (srch == null) {
			srch = new ShipPackageDTO();
		}
		String type = "MANUFACTURING";
		String hql = "";
		if ("MANUFACTURING".equals(type)) {
			hql = " select sp.packageId,"
					+ " sp.trackingNo,"
					+ " sp.note,"
					+ " sp.status,"
					+ " sp.shipMethod,"
					+ " sp.shiptoAddress,"
					+ " sp.shipmentDate,"
					+ " sp.shippingClerk "
					+ " from ShipPackage sp where 1=1 ";
		}
		if (srch.getShippingClerk() != null && !srch.getShippingClerk().equals("0")) {
			hql = hql + "and sp.shippingClerk = " + srch.getShippingClerk();
		}
		if (srch.getStatus() != null && srch.getStatus().trim().length() > 0) {
			hql = hql + " and sp.status="+srch.getStatus();
		}
		if (srch.getShipmentDate() != null) {
			hql = hql + " and sp.shipmentDate='" + srch.getShipmentDate() + "'";
		}
		if (srch.getOrderNo() != null && srch.getOrderNo() != 0) {
			hql = hql
					+ " and sp.packageId in (select spl.pkgLineId from ShipPackageLines spl where spl.orderNo="+srch.getOrderNo()+")";
		}
		if (page.getOrder() != null && page.getOrder().trim().length() > 0) {
			hql += " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		// set object
		List list = this.getSession().createQuery(hql).list();
		List<ShipPackageDTO> lists = new ArrayList<ShipPackageDTO>();
		ShipPackageDTO dto = null;
		int countTemp = 0;
		List list_new = new ArrayList();
		for(int i=0;i<list.size();i++){
			Object[] obj = (Object[])list.get(i);
			String packageId = obj[0] + "";
			if(error != null && error.equals("YES")){
				String error_ = this.getError(packageId);// 是否有错误包标志
				if("YES".equals(error_)){
					list_new.add(obj);
				}
			}
			else if(error != null && error.equals("NO")){
				packageId = obj[0] + "";
				String error_ = this.getError(packageId);// 是否有错误包标志
				if("NO".equals(error_)){
					list_new.add(obj);
				}
			}
			else
				list_new.add(obj);
		}
		for (int i = page.getPageSize()*(page.getPageNo()-1); i < list_new.size(); i++) {
			Object[] obj = (Object[]) list_new.get(i);
			dto = new ShipPackageDTO();
			String packageId = obj[0] + "";
			if("0".equals(packageId)){
				dto.setError("NO");
			}else{
				String error_ = this.getError(packageId);                 // 是否有错误包标志
				dto.setError(error_);                                 // 是否为空
			}
			Integer packageIds = Integer.parseInt(packageId);
			dto.setPackageId(packageIds);
			if (obj[1] != null) {
				dto.setTrackingNo(obj[1] + "");
			}
			if (obj[2] != null) {
				dto.setDesc(obj[2] + "");
			}
			dto.setStatus(obj[3] + "");
			String methodId = obj[4] + "";
			List listMethod = this.getShipMethod(methodId);
			String methodName = "";
			for (int j = 0; j < listMethod.size(); j++) {
				ShipMethod method = (ShipMethod) listMethod.get(j);
				methodName += method.getName() + ",";
			}
			if (methodName.length() > 1)
				dto.setShipVia(methodName.substring(0,
								methodName.length() - 1));

			dto.setShiptoAddress(obj[5] + "");
			if (obj[6] != null) {
				dto.setShipmentDate(format.parse(obj[6] + ""));
			}
			// shippingClerk
			List<User> listShipClerk = this.getShipPackageListByPackageId(packageId);
			String shippingClerk = "";
			for(int j=0;listShipClerk != null && j<listShipClerk.size();j++){
				User u = listShipClerk.get(j);
				if(!"".equals(u.getLoginName()) && u.getLoginName() != null ){
					shippingClerk += ","+u.getLoginName();
				}
			}
			if(shippingClerk.trim().length() > 0)
				dto.setShippingClerk(shippingClerk.substring(1));
			dto.setWname("Genscript NJ Warehouse");
			lists.add(dto);
			countTemp++;
			if(countTemp == 10)
				break;
		}
		// 设置分页数据
		packagesDTO.setResult(lists);
		packagesDTO.setPageNo(page.getPageNo());
		packagesDTO.setTotalCount(Long.parseLong(list_new.size()+""));
		packagesDTO.setPageSize(10);
		return packagesDTO;
	}
	
	/**
	 * 得到ShipPackage的list
	 * @param packageId
	 * @return List
	 */
	private List<User> getShipPackageListByPackageId(String packageId){
		return this.find("select u from User u,ShipPackage s where u.userId=s.shippingClerk and s.packageId = "+packageId);
	}
	
	/**
	 * 得到ShipPackage的list
	 * @param packageId
	 * @return List
	 */
	public List<User> getShipPackageListByPackageId(Integer packageId){
		return this.find("select u from User u,ShipPackage s where u.userId=s.shippingClerk and s.packageId = "+packageId);
	}
	
	/**
	 * 得到ShipPackage的list
	 * @param packageId
	 * @return List
	 */
	public List<User> getModibyNameByPackageId(Integer packageId){
		return this.find("select u from User u,ShipPackage s where u.userId=s.modifiedBy and s.packageId = "+packageId);
	}

	/**
	 * 查询ShipPackages对象并返回List
	 * 
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List getListByTr() {
		String hql = "from ShipPackage";
		return this.find(hql);
	}

	/**
	 * 根据ids查询ShipPackages对象并返回List
	 * 
	 * @param ids
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List getListByTr(List<Integer> ids) {
		List list = new ArrayList();
		for (Integer id : ids) {

			String hql = "from ShipPackage where shipments.shipmentId=" + id;

			List<ShipPackage> list1 = this.find(hql);
			for (ShipPackage shipPackages : list1) {
				list.add(shipPackages);
			}
		}
		return list;
	}

	/**
	 * 根据id返回ShipPackages对象
	 * 
	 * @param id
	 * @return ShipPackage
	 */
	public ShipPackage getShipPackageById(Serializable id) {
		return this.getById((Integer) id);
	}

	/**
	 * 取消装运功能:更新ShipPackages表信息
	 * 
	 * @param packageId
	 * @param note
	 * @return
	 */
	public Serializable updateShipPackagesByPackageId(Serializable packageId,
			Serializable note) {
		String hql = "update ShipPackage set status='Drafted',trackingNo='',note='Cancel Reason: "
				+ note + "',sendBy=null where packageId=?";
		return this.batchExecute(hql, packageId);
	}
	
	public void updateShipPackagesByPackageIds(String packageIds) {
		String hql = "update ShipPackage set shipinfoSentFlag='Y' where packageId in("+packageIds+")";
		this.batchExecute(hql);
	}
	
	/**
	 * 装运功能:更新ShipPackages表信息
	 * 
	 * @param packageId
	 * @param note
	 * @return
	 */
	public Serializable updateShipPackagesByPackageIdNo(Serializable packageId,
			Serializable trackingNo) {
		String hql = "update ShipPackage set trackingNo='"+trackingNo+"' where packageId=?";
		return this.batchExecute(hql, packageId);
	}
	
	/**
	 * 卸载功能:更新ShipPackages表信息
	 * 
	 * @param packageId
	 * @param note
	 * @return
	 */
	public Serializable updateShipPackagesByrackingNo(Serializable trackingNo) {
		String hql = "update ShipPackage set status='Packed',trackingNo='' where trackingNo=?";
		return this.batchExecute(hql, trackingNo);
	}

	/**
	 * 得到ShipMethod的list
	 * 
	 * @param methodId
	 * @return List
	 */
	private List<ShipMethod> getShipMethod(String methodId) {
			return this
					.find("from ShipMethod s where s.methodId = " + methodId);
	}

	/**
	 * 得到PackageError的YES或NO
	 * 
	 * @param packageId
	 * @return String
	 */
	public String getError(String packageId) {
		try {
			List<String> temp= this.find("select (case count(*) when 0 then 'NO' else 'YES' end)as error from " +
					" ShipPackageErrors spe,ShipPackage sp " +
					" where spe.packageId = sp.packageId and spe.packageId ="+packageId );
			return temp.get(0);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "";
	}

	/**
	 * 保存ShipPackages数据
	 * 
	 * @param spdto
	 * @return void
	 */
	public void saveShipPackages(ShipPackageDTO spdto) {
		String hql = "update ShipPackage set ";
		if (spdto.getStatus() != null && spdto.getStatus().trim().length() > 0)
			hql = hql + ",status='" + spdto.getStatus() + "'";
		if (spdto.getShiptoAddress() != null
				&& spdto.getShiptoAddress().trim().length() > 0)
			hql = hql + ",shiptoAddress='" + spdto.getShiptoAddress() + "'";
		if (spdto.getShipMethod() != null)
			hql = hql + ",shipMethod='" + spdto.getShipMethod() + "'";
		if (spdto.getShipmentDate() != null)
			hql = hql + ",shipmentDate=:date";
		if (spdto.getTrackingNo() != null
				&& spdto.getTrackingNo().trim().length() > 0)
			hql = hql + ",trackingNo='" + spdto.getTrackingNo() + "'";
//		if (spdto.getShippingClerk() != null)
//			hql = hql + ",shippingClerk='" + spdto.getShippingClerk() + "'";
		// hql=hql+",creationDate='"+spdto.getCreationDate()+"'"+
//		hql = hql + ",modifiedBy='" + spdto.getModifiedBy() + "'"
		hql = hql + ",modifyDate=:modifyDate" + "  where packageId="
				+ spdto.getPackageId();
		if (hql.substring(hql.indexOf("set") + 4, hql.indexOf("set") + 5)
				.equals(",")) {
			hql = hql.substring(0, hql.indexOf("set") + 4)
					+ hql.substring(hql.indexOf("set") + 5);
		}
		@SuppressWarnings("unused")
		int boo = this.getSession().createQuery(hql).setDate("date",
				spdto.getShipmentDate()).setDate("modifyDate",
				spdto.getModifyDate()).executeUpdate();

	}

	/**
	 * 查询ViewPackingSlip数据细节
	 */
	@SuppressWarnings("unchecked")
	public Page<ViewPackingSlipDTO> ViewPackingSlip(
			Page<ViewPackingSlipDTO> page, String packageId) {

		Map<String, Object> map = new HashMap<String, Object>();
//		String hql = "select o.billtoAddrId,o.shiptoAddrId,o.custNo,o.orderNo,o.orderDate,oi.shipMethod,p.shipmentDate,p.billableWeight,p.packageId,oi.quantity,spl.quantity,oi.itemNo,oi.shortDesc,oi.unitPrice,oi.discount,oi.amount "
//				+ " from ShipPackageLines as spl,ShipPackage as p,OrderMain as o,OrderItem oi"
//				+ " where spl.orderNo=o.orderNo and spl.itemNo=oi.itemNo and oi.orderNo=o.orderNo and p.packageId = "
//				+ packageId;
        String hql="select distinct(spl.shipPackages.packageId),o.billtoAddrId,o.shiptoAddrId,o.custNo,spl.orderNo,o.orderDate,o.orderNo,spl.creationDate,o.subTotal,spl.quantity,spl.size,spl.itemNo,o.baseCurrency,o.shipAmt,o.discount,o.amount "+
        		" from ShipPackageLines as spl,OrderMain as o where o.orderNo = spl.orderNo and spl.shipPackages.packageId ="+packageId;
//        String hql="select distinct(spl.shipPackages.packageId),o.billtoAddrId,o.shiptoAddrId,o.custNo,spl.orderNo,o.orderDate,o.orderNo,spl.creationDate,o.subTotal,oi.quantity,spl.quantity,spl.itemNo,o.baseCurrency,o.shipAmt,o.discount,o.amount "+
//		" from ShipPackageLines as spl,OrderMain as o,OrderItem as oi where o.orderNo = spl.orderNo and o.orderNo = oi.orderNo and spl.shipPackages.packageId ="+packageId;
		map.put("packageId", packageId);
		Query q = createQuery(hql, map);

		List l = q.list();
		List<ViewPackingSlipDTO> listView = new ArrayList<ViewPackingSlipDTO>();
		if (l != null && l.size() > 0) {
			for (int i = 0; i < l.size(); i++) {
				Object[] o = (Object[]) l.get(i);
				ViewPackingSlipDTO view = new ViewPackingSlipDTO();
				view.setBillTo(o[1]==null ? "":o[1]+"");
				view.setShipTO(o[2]==null ? "":o[2]+"");
				view.setCustomerNo((Integer) (o[3]));
				view.setOrder((Integer) o[4]);
				view.setOrderDate(o[5] == null ? "" : o[5]+"");
				//view.setShipVia(o[6] == null ? "" : o[6]+"");
				view.setShipVia("Fedex");
				view.setShippingDate(o[7] == null ? "" : o[7]+"");
//				view.setTotalWeight(o[8]==null ? "":o[8]+"");
				view.setOfPackages(Integer.parseInt(o[0]+""));
				view.setQtyOrdered((Integer) o[9]);
				view.setQtyShipped(o[10]==null ? "":o[10]+"");
				view.setItem(Integer.parseInt(o[11]+""));
				view.setDescription(o[12] == null ? "" : o[12]+"");
				view.setUnitPrice(Double.parseDouble(o[13]+""));
				view.setDisc(Double.parseDouble(o[14]+""));
				view.setExtendedPrice(Double.parseDouble(o[15]+""));
				listView.add(view);
			}
			page.setResult(listView);
			return page;
		}
		return null;
	}


	/**
	 * 根据id和type查询ShipTO和BillTo两个属性值
	 * 
	 * @param id
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String selectAddr(String id, String type) {
		String address = "";
		if (id == null || id.trim().equals(""))
			return address;
		String hql = "from OrderAddress where addrId='" + id
				+ "' and addrType='" + type + "'";
		Query q = createQuery(hql);
		List<OrderAddress> list = (List<OrderAddress>) q.list();
		for (OrderAddress addr : list) {
			address = addr.getFirstName() + "<br />" + addr.getLastName()
					+ "<br />" + addr.getAddrLine1() + addr.getCity()
					+ addr.getState();
		}
		if (address == "")
			address = "<br /><br /><br />";
		return address;
	}

	/**
	 * 从ShipMethod表中取出name值(ship Via)
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String selectShipVia(String id) {
		String shipVia = "";
		if (id == null || id.trim().equals(""))
			return shipVia;
		String hql = "from  ShipMethod where methodId='" + id + "'";
		Query q = createQuery(hql);
		List<ShipMethod> list = (List<ShipMethod>) q.list();
		for (ShipMethod sm : list) {
			shipVia = shipVia + sm.getName();

		}
		return shipVia;
	}

	/**
	 * 根据orderNo获取packageId并返回list
	 * 
	 * @param orderNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getPackageTn(String orderNo) {
		String hql = "select distinct(package.packageId) from ShipPackage package,ShipPackageLines line where line.shipPackages.packageId = package.packageId and package.status = 'Packed' and line.orderNo in ("
				+ orderNo + ")";
		List list = this.find(hql, orderNo);
		return list;
	}

	/**
	 * 保存ShiPackages对象数据信息
	 * 
	 * @param packages
	 * @return String
	 * @throws Exception
	 */
	public String saveShipPackage(ShipPackage packages) throws Exception {
		String flag = "0";
		try {
			this.save(packages);
			flag = "1";
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return flag;
	}
	/*
	 * 根据status获取package
	 */
	public List<ShipPackage> searchShipPackageListByStatus(String status){
		String hql = "from ShipPackage where status = '"+status+"'";
		return this.find(hql);
	} 
	
	/*
	 * 把package变为SH状态
	 */
	public void SaveShipPackageToSH(String packageIds,String shipDate){
		
		String hql = "update ShipPackage set status='Shipped',shippedTime='"+shipDate+"',shipinfoSentFlag='Y' where packageId in ("+packageIds+")";
		this.batchExecute(hql);
	}
	
	public void flush(){
		Session session = this.getSession();
		session.flush();
		session.clear();
	}
}
