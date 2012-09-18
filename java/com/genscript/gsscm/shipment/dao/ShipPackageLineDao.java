package com.genscript.gsscm.shipment.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.common.util.Arith;
import com.genscript.gsscm.inventory.entity.PickingLogs;
import com.genscript.gsscm.order.entity.OrderItem;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.shipment.dto.PrintPickListDTO;
import com.genscript.gsscm.shipment.dto.ShipPackageLineDTO;
import com.genscript.gsscm.shipment.entity.ShipPackage;
import com.genscript.gsscm.shipment.entity.ShipPackageLines;


@Repository
public class ShipPackageLineDao extends HibernateDao<ShipPackageLines, Integer> {

	/**
	 * 美国，根据Package No查询，返回一个list集合
	 * @param packageId
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public Page<ShipPackageLineDTO> getListSpl(Page<ShipPackageLines> page,
			Integer packageId) {
		List<ShipPackageLineDTO> dtoList = new ArrayList<ShipPackageLineDTO>();
		Page<ShipPackageLineDTO> shipDto = new Page<ShipPackageLineDTO>();
		String sql = "select a.pkg_line_id,a.order_no,a.item_no,a.quantity,a.size,b.name,c.missing_qty,c.missing_size from shipping.ship_package_lines a join order.order_items b on (a.order_no = b.order_no and a.item_no = b.item_no) left join shipping.ship_package_errors c on (a.package_id = c.package_id and a.pkg_line_id = c.pkg_line_id)";
		if (packageId != null && packageId.intValue() != 0) {
			sql += "  where a.package_id=" + packageId + "";
		}
		if (page.getOrder() != null && page.getOrder().trim().length() > 0) {
			sql += " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		Query query = this.getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst()-1);
		query.setMaxResults(page.getPageSize());
		List pageR = query.list();
		if (pageR != null) {
			for (int i = 0; i < pageR.size(); i++) {
				Object[] obj = (Object[]) pageR.get(i);
				ShipPackageLineDTO dto2 = new ShipPackageLineDTO();
				dto2.setOrderNo(Integer.parseInt(obj[1] + ""));
				dto2.setItemNo(Integer.parseInt(obj[2] + ""));
				dto2.setPkgLineId(Integer.parseInt(obj[0] + ""));
				dto2.setQuantity(Integer.parseInt(obj[3] + ""));
				dto2.setSize(Double.parseDouble(obj[4] + ""));
				dto2.setName(obj[5] + "");
				Integer qty = 0;
				Double size = 0.0;
				if (obj[6] == null) {
					dto2.setMissingQty(qty);
				} else {
					dto2.setMissingQty(Integer.parseInt(obj[6] + ""));
				}
				if (obj[7] == null) {
					dto2.setMissingSize(size);
				} else {
					dto2.setMissingSize(Double.parseDouble(obj[7] + ""));
				}
				dtoList.add(dto2);
			}
			shipDto.setResult(dtoList);
			shipDto.setPageNo(page.getPageNo());
			shipDto.setPageSize(10);
			shipDto.setTotalCount((long)this.getSession().createSQLQuery(sql).list().size());
		}
		return shipDto;
	}
	
	/**
	 * 根据trackingNo和itemNo和orderNo查询shipPackageLines中的qty和size以及ReceivingLogs中的qty和size比较
	 * 
	 * @param trackingNo
	 * @param itemNo
	 * @param orderNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ShipPackageLineDTO> findShipPickUp(Integer packageId) {
			String hql = "select oi.orderNo,oi.itemNo,oi.name,oi.quantity,oi.qtyUom,pl.quantity from " +
					"OrderItem oi," +
					"ShipPackageLines pl" +
					" where oi.orderNo = pl.orderNo and oi.itemNo = pl.itemNo and pl.shipPackages.packageId = "+ packageId;
			List list = this.find(hql);
			List<ShipPackageLineDTO> shipPackageLineDTOList = new ArrayList<ShipPackageLineDTO>();
			if(list!=null&&!list.isEmpty()){
				for(int i = 0 ; i<list.size();i++){
					Object[] obj = (Object[]) list.get(i);
					ShipPackageLineDTO dto = new ShipPackageLineDTO();
					dto.setOrderNoFrm(obj[0]+"");
					dto.setItemNoFrm(obj[1]+"");
					dto.setName(obj[2]+"");
					dto.setQuantity(Integer.valueOf(obj[3]+""));
					if(obj[4]==null){
						dto.setQtyUom("");
					}else{
						dto.setQtyUom(obj[4]+"");
					}
					if(obj[5]==null){
						dto.setQtyShipped(null);
					}else{
						dto.setQtyShipped(Integer.valueOf(obj[5]+""));
					}
					dto.setLen("");
					dto.setVector("");
					shipPackageLineDTOList.add(dto);
				}
			}
			
			return shipPackageLineDTOList;
	}
	
	/**
	 * 根据itemNo查询并返回ShipPackage对象
	 * @param itemNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ShipPackage getShippageById(Integer itemNo) {
		String hql = "select s from ShipPackage s ,ShipPackageLines sl where s.packageId=sl.shipPackages.packageId and sl.itemNo=?";
		List retList = this.find(hql, itemNo);
		ShipPackage sps = null;
//		if (retList != null && retList.size() > 0) {
//			Object[] obj = (Object[])retList.get(0);
//			Integer packageId = Integer.parseInt(obj[0]+"");
//			sps.setPackageId(packageId);
//		}
		if (retList != null && retList.size() > 0) {
			
			sps = (ShipPackage)retList.get(0);
		}
		return sps;
	}

	/**
	 * 根据trackingNo和itemNo和orderNo查询shipPackageLines中的qty和size以及ReceivingLogs中的qty和size比较
	 * 
	 * @param trackingNo
	 * @param itemNo
	 * @param orderNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findShipReceiveLine(String trackingNo, Integer itemNo,
			Integer orderNo) {
			String sql = "select line.order_no,line.item_no from shipping.ship_package_lines line,inventory.receiving_logs log,order.order ord,"
					+ " (select sum(qty_received) as quantity,sum(size_received) as size from order.order ord,inventory.receiving_logs log,"
					+ " shipping.ship_package_lines line where ord.src_po_no = log.order_no and ord.order_no = line.order_no and line.item_no = log.item_no"
					+ " and log.tracking_no='"
					+ trackingNo
					+ "' and log.item_no = "
					+ itemNo
					+ " and log.order_no = "
					+ orderNo
					+ ") as log_ where ord.src_po_no = log.order_no"
					+ " and ord.order_no = line.order_no and line.item_no = log.item_no and ((line.quantity - log_.quantity)<=0 and (line.size - log_.size)<=0"
					+ " ) and log.order_no = "
					+ orderNo
					+ " and log.item_no = "
					+ itemNo
					+ " group by line.pkg_line_id";
			List list = this.getSession().createSQLQuery(sql).list();
			return list;
	}
	/**
	 * 更新ShipPackageLines状态
	 * 
	 * @param orderNo
	 * @param itemNo
	 */
	public void updateShipReceive(String orderNo, Integer itemNo) {
			String hql = "update ShipPackageLines set status = 'Received' where orderNo =:orderNo and itemNo =:itemNo";
			this.getSession().createQuery(hql).setString("orderNo", orderNo)
					.setInteger("itemNo", itemNo).executeUpdate();
	}

	/**
	 * 更新ShipPackage状态
	 * 
	 * @param orderNo
	 * @param itemNo
	 */
	public void updateShipPkReceive(String packageId) {
		String hql = "update ShipPackage set status = 'Received' where packageId =:packageId";
		this.getSession().createQuery(hql)
				.setString("packageId", packageId).executeUpdate();
	}

	/**
	 * 根据orderNo和itemNo查询，返回一个list集合
	 * 
	 * @param orderNo
	 * @param itemNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findSpk_lines(Integer orderNo, Integer itemNo) {
			String sql = "select li.status,pa.package_id from shipping.ship_package_lines li,shipping.ship_packages pa"
					+ " where pa.package_id = li.package_id and li.package_Id in (select l.package_Id from shipping.ship_package_lines l"
					+ ",order.order ord where ord.src_po_no =:orderNo and ord.order_No =l.order_no and l.item_No =:itemNo)";
			return this.getSession().createSQLQuery(sql).setInteger("orderNo",
					orderNo).setInteger("itemNo", itemNo).list();
	}
	
	/**
	 * 根据获取的packageId查询ShipPackageLines数据并返回Page对象
	 * @param page
	 * @param shipPackageLinesdto
	 * @return
	 */
	public Page<ShipPackageLines> searchShipPackageLines(Page<ShipPackageLines> page, ShipPackageLineDTO shipPackageLinesdto)
	{
		if (shipPackageLinesdto == null) {
			shipPackageLinesdto = new ShipPackageLineDTO();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "from ShipPackageLines s where s.shipPackages.packag" +
				"eId=:packageId";
		map.put("packageId", shipPackageLinesdto.getShipPackages().getPackageId());
		System.out.println("/"+shipPackageLinesdto.getShipPackages().getPackageId());
		page=this.findPage(page, hql,map);
		System.out.println("/page.size:"+page.getResult().size());
		
		return page;
	}
	
	/**
	 * 查询ShipPackageLines数据并返回Page对象
	 * @param page
	 * @param filters
	 * @return
	 */
	public Page<ShipPackageLines> searchShipPackageLines(Page<ShipPackageLines> page, List<PropertyFilter> filters) {
		return findPage(page, filters);
	}
	
	/**
	 * 根据orderNo和itemNo查询并返回ShipPackageLines对象
	 * @param orderNo
	 * @param itemNo
	 * @return
	 */
	public ShipPackageLines getShipPackageLinesByOrderNoAndItemNo(Integer orderNo,Integer itemNo){
		String hql = "from ShipPackageLines where orderNo="
			+ orderNo + " and itemNo="
			+ itemNo
			+ " and shipmentLines.lineId is not null";
		List<ShipPackageLines> list=this.find(hql);
		if(list==null||list.size()==0)
			return null;
		return list.get(0);
	}
	
	/**
	 * 更新ShipPackageLines对象信息
	 * @param line
	 * @return
	 * @throws Exception
	 */
	public String updateShipPackageLine(ShipPackageLines line)throws Exception{
		String flag = "1";
			this.getSession().createSQLQuery("").executeUpdate();
			return flag;
	}
	
	/**
	 * print pick list功能
	 * 更新ShipPackageLines状态为Picked
	 * @param id
	 * @return
	 */
	public boolean changePackageLinesStatus(Serializable id){
		String hql="update ShipPackageLines set status='Drafted' where shipPackages.packageId=?";
		int result=this.batchExecute(hql, id);
		if(result>=0)return true;
		return false;
	}
	
	/**
	 * 保存ShipPackageLines对象信息
	 * @param spl
	 */
	public void saveShipPackageLines(ShipPackageLines spl){
		this.save(spl);
	}

	/**
	 * 保存shipPackagelines数据
	 * @param spldto
	 */
	public void saveShipPackagelines(ShipPackageLineDTO splDto,Integer pkgLineId)
	{
		String hql="update ShipPackageLines set " ;
		if(splDto.getOrderNo()!=null && splDto.getOrderNo()!=0)
			hql=hql+",orderNo='"+splDto.getOrderNo()+"'" ;
		if(splDto.getItemNo()!=null && splDto.getItemNo()!=0)
			hql=hql+",itemNo='"+splDto.getItemNo()+"'" ;
		if(splDto.getQuantity()!=null && splDto.getQuantity()!=0)
			hql=hql+",quantity='"+splDto.getQuantity()+"'" ;
		if(splDto.getSize()!=null && splDto.getSize()!=0)
			hql=hql+",size='"+splDto.getSize()+"'" ;
		if(splDto.getQtyUom()!=null && splDto.getQtyUom().trim().length()>0)
			hql=hql+",qtyUom='"+splDto.getQtyUom()+"'" ;
		if(splDto.getSizeUom()!=null && splDto.getSizeUom().trim().length()>0)
			hql=hql+",sizeUom='"+splDto.getSizeUom()+"'";
		hql=hql+"  where pkgLineId="+pkgLineId;		
		if(hql.substring(hql.indexOf("set")+4, hql.indexOf("set")+5).equals(","))
		{
			hql=hql.substring(0, hql.indexOf("set")+4)+hql.substring(hql.indexOf("set")+5);
		}
		this.getSession().createQuery(hql).executeUpdate();	
	}
	
	/**
	 * 保存ShipPackageLines对象信息
	 * @param line
	 * @return
	 * @throws Exception
	 */
	public String saveShipPackageLine(ShipPackageLines line)throws Exception{
			String flag = "0";
			//this.save(line);
			this.getSession().save(line);
			flag = "1";
			this.getSession().flush();
			return flag;
	}
	
	/**
	 * Print pick list功能
	 * 根据lineId,更新ShipmentLines对象的状态为Shipping
	 * @param lineId
	 * @return
	 */
	public int modyfyShipmentLinesByLineId(Serializable lineId){
		String hql1="update ShipmentLine set status='Ready To Ship' where lineId="+lineId;
		return this.getSession().createQuery(hql1).executeUpdate();
	}
	
	/**
	 * 根据packageId查询，返回一个ShipPackageLines对象
	 * @param  packageId        参数
	 * @return ShipPackageLines 实体
	 */
	@SuppressWarnings("unchecked")
	public ShipPackageLines findPackagePackId(Integer packageId){
		String hql = "from ShipPackageLines where shipPackages.packageId in (?)";
		List list = this.getSession().createQuery(hql).setInteger("packageId", packageId).list();
		ShipPackageLines shipPackageLines = null;
		if(list.size()>0){
			shipPackageLines = (ShipPackageLines)list.get(0);
		}
		return shipPackageLines;
	}
	
	/**
	 * 根据packageId查询，返回一个ShipPackageLines对象
	 * @param  packageId        参数
	 * @return ShipPackageLines 实体
	 */
	@SuppressWarnings("unchecked")
	public List<ShipPackageLines> findPackageLineList(String packageId){
		String hql = "from ShipPackageLines where shipPackages.packageId in ("+packageId+")";
		List<ShipPackageLines> list = this.find(hql);
		return list;
	}
	
	/**
	 * 根据packageId查询，返回一个ShipPackageLines对象
	 * @param  packageId        参数   String
	 * @return ShipPackageLines 实体
	 */
	@SuppressWarnings("unchecked")
	public ShipPackageLines findPackagePackId(String packageId){
		String hql = "from ShipPackageLines where shipPackages.packageId in (?)";
		List list = this.getSession().createQuery(hql).setString("parckageId", packageId).list();
		ShipPackageLines shipPackageLines = null;
		if(list.size()>0){
			shipPackageLines = (ShipPackageLines)list.get(0);
		}
		return shipPackageLines;
	}
	/**
	 * 通过packageId查询
	 * @param packageId
	 * @return
	 */
	public List<ShipPackageLines> getShipPackageLineList (Integer packageId) {
		String hql = " FROM ShipPackageLines spk where spk.shipPackages.packageId = ? ";
		return this.find(hql, packageId);
	}
	
	/**
	 * 通过packageId查询
	 * @param packageId
	 * @return
	 */
	public List<ShipPackage> getShipPackageList (Integer packageId) {
		String hql = " FROM ShipPackage sp where sp.packageId = ? ";
		return this.find(hql, packageId);
	}
	
	public List<ShipPackage> searchShipPackageList (String packageIds) {
		String hql = " FROM ShipPackage sp where sp.packageId in ("+packageIds+") ";
		return this.find(hql);
	}
	
	/**
	 * 根据userId查询User
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public User getByUserId(Integer userId){
		String hql = "from User where userId = :userId";
		List<User> list = this.getSession().createQuery(hql).setInteger("userId", userId).list();
		User user = null;
		if(list !=null && list.size()>0){
			user = (User)list.get(0);
		}
		return user;
	}
	
	//modify bu zhanghuibin
	public List<ShipPackageLines> getLineQutByPackage(Integer packageId){
        List<ShipPackageLines> lines = this.findBy("shipPackages.packageId", packageId);
        return lines;
	}
	
	public List<ShipPackageLines> findLineByPackagetrNo(String trNo){
		String hql = "select sl from ShipPackageLines sl,ShipPackage s where sl.shipPackages.packageId = s.packageId and s.trackingNo = "+trNo;
		return this.find(hql);
	}
	
	/*
	 * 根据orderNo 返回packageline
	 
	public List<PrintPickListsDTO> findPackageByOrderNo(String orderNos ,String status){
		if(orderNos!=null&&!orderNos.equals("")&&status!=null&&!status.equals("")){
			String hql = "select item.name , item.catalogNo , pl.lotNo , pl.locationCode,item.quantity,item.size, from ShipPackageLines sl,OrderItem item,PickingLogs pl where sl.orderNo in("+orderNos+") and sl.status in ("+status+") and sl.orderNo = item.orderNo and sl.itemNo = item.itemNo " +
					"pl.pkgLineId = sl.pkgLineId";
			List list =  this.find(hql);
		}
		return null;
	}*/
	
	public void flush(){
		Session session = this.getSession();
		session.flush();
		session.clear();
	}
	
	/**
	 * 通过orderNo和Item查询
	 * @author Zhang Yong
	 * @param orderNo
	 * @param itemNo
	 * @return
	 */
	public List<ShipPackage> findByOrderNoAndItemNo (Integer orderNo, Integer itemNo) {
		String hql = "select spl.shipPackages from ShipPackageLines spl where spl.orderNo=:orderNo and spl.itemNo=:itemNo and spl.shipPackages.status = 'Shipped' order by spl.shipPackages.packageId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNo", orderNo);
		map.put("itemNo", itemNo);
		return this.find(hql, map);
	}
	
	/*
	 * 根据shipment id 获取pacageline and picking_logs;
	 */
	public List<PrintPickListDTO> searchShipPackageLineAndPickingLog(Integer packageId){
		String hql = "SELECT spl,oi"+
		  " FROM ShipPackageLines spl"+
/*		  " LEFT JOIN"+
		    " (SELECT pl"+
		    " FROM PickingLogs pl"+
		    "  WHERE pl.id = (SELECT MIN(pls.id) FROM PickingLogs pls WHERE pl.pkgLineId = pls.pkgLineId)) AS picking"+
		    " ON(spl.pkgLineId = picking.pkgLineId)" +*/
		    ", OrderItem oi " +
		    "where spl.shipPackages.packageId = "+packageId+" and spl.orderNo=oi.orderNo  and spl.itemNo=oi.itemNo" +
		    " and spl.status  in ('Drafted','Picked','Packed') group by oi.orderNo,oi.itemNo";
		List list = this.find(hql);
		List<PrintPickListDTO> printPickListDTOList = new ArrayList<PrintPickListDTO>();
		for(int i=0;i<list.size();i++){
			Object[] obj = (Object[])list.get(i);
			ShipPackageLines lines = (ShipPackageLines)obj[0];
			//PickingLogs pl = (PickingLogs)obj[1];
			OrderItem oi = (OrderItem)obj[1];
			PrintPickListDTO dto = new PrintPickListDTO();
			dto.setCatNo(oi.getCatalogNo());
			dto.setComment(oi.getComment());
			//dto.setLocationCode(pl.getLocationCode());
			//dto.setLotNo(pl.getLotNo());
			if(oi.getName()!=null&&oi.getName().length()>30){
				dto.setName(oi.getName().substring(0,29)+"...");
			}else{
				dto.setName(oi.getName());
			}
			dto.setStatus(lines.getStatus());
			dto.setPackageId(lines.getShipPackages().getPackageId());
			dto.setQtypicked(lines.getQuantity()+"");
			dto.setQtyUom(oi.getQtyUom());
			dto.setQtyToPick(oi.getQuantity());
			dto.setOrderNo(oi.getOrderNo());
			if(lines.getSize()!=null){
				dto.setSizePick(lines.getSize()+"");
			}
			Double oiSize = oi.getSize();
			if(oiSize==null){
				oiSize = 0.0;
			}
			dto.setSizeToPick((Arith.mul(oiSize, oi.getQuantity()))+"");
			dto.setSizeUom(oi.getSizeUom());
			dto.setType(oi.getType());
			
			dto.setLineId(lines.getPkgLineId()+"");
			printPickListDTOList.add(dto);
			
		}
		
		return printPickListDTOList;
	}
	
	/*
	 * 获取是否有相同的数据
	 */
	public List<ShipPackageLines> searchShipPackageLinesOfPack(Integer packageId,Integer orderNo,Integer itemNo){
		String hql = "from ShipPackageLines where shipPackages.packageId = "+packageId+" and orderNo = "+orderNo +" and itemNo = "+itemNo;
		return this.find(hql);
	}
	
	/*
	 * 根据shipmentId
	 */
	public List<ShipPackageLines> searchShipPackageLinesByShipmentId(Integer shipmentId){
		String hql="select DISTINCT spl from ShipPackageLines spl where spl.shipPackages.shipments.shipmentId = "+shipmentId;
		return this.find(hql);
	}
}
