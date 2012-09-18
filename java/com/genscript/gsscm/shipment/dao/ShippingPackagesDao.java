package com.genscript.gsscm.shipment.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.common.util.Arith;
import com.genscript.gsscm.order.entity.OrderAddress;
import com.genscript.gsscm.shipment.dto.BankCardDTO;
import com.genscript.gsscm.shipment.dto.PrintPickListDTO;
import com.genscript.gsscm.shipment.dto.ShipPackageDTO;
import com.genscript.gsscm.shipment.dto.ViewPackingSlipDTO;
import com.genscript.gsscm.shipment.entity.ShipMethod;
import com.genscript.gsscm.shipment.entity.ShipPackage;
import com.genscript.gsscm.shipment.entity.ShipPackageLines;

@Repository
public class ShippingPackagesDao extends HibernateDao<ShipPackage,Integer>{
	
	
	/**
	 * 中国仓库的shipping列表
	 */
	@SuppressWarnings("unchecked")
	public Page<ShipPackageDTO> getShippackagesList(Page<ShipPackage> page, ShipPackageDTO srch) {
		if (srch == null) {
			srch = new ShipPackageDTO();
		}
		Page<ShipPackageDTO> pageShipPackageDto = new Page<ShipPackageDTO>();
		pageShipPackageDto.setPageSize(page.getPageSize());
		pageShipPackageDto.setPageNo(page.getPageNo());
		Map<String, Object> map = new HashMap<String, Object>();
//		String hql = "select distinct(ord.orderNo),ord.status,ord.orderType,ord.priority,item.shiptoAddrId,package.packageId,package.warehouseId,item.shipMethod" 
//				+" from OrderMain ord,OrderItem item,ShipPackageLines line,ShipPackage package";
//		if(srch.getShippingClerk() != null && !srch.getShippingClerk().equals("0")){
//			hql += ",ShipClerkAssigned assign,ShipClerk clerk";
//		}
//				hql += " where ord.orderNo = item.orderNo"
//				+" and item.orderNo = line.orderNo"
//				+" and item.itemNo = line.itemNo"
//				+" and line.shipPackages.packageId = package.packageId"
//				+" and package.warehouseId = 2"
//				+" and package.status <> 'Shipped' "
//				+" and (" +
//						"exists (select 1 from Reservation r where r.orderNo = ord.orderNo )"
//		      +" or " +
//		      		"exists (select 1 from ShipPackageLines spl where spl.orderNo =ord.orderNo and spl.status in ('Drafted', 'Picked', 'Packed'))" +
//		      		")";
		
		String hql = "select distinct(ord.orderNo),ord.status,ord.orderType,ord.priority,item.shiptoAddrId," +
		"ord.warehouseId,item.shipMethod " 
		+" from OrderMain ord,OrderItem item ";
		if(srch.getShippingClerk() != null && !srch.getShippingClerk().equals("0")){
			hql += ",ShipClerkAssigned assign,ShipClerk clerk";
		}
		hql += " where ord.orderNo = item.orderNo"
		+" and ord.warehouseId = 2"
		+" and (" 
		+"exists (select 1 from Reservation r where r.orderNo = ord.orderNo )"
		+" or " +
  		"exists (select 1 from ShipPackageLines spl where spl.orderNo =ord.orderNo and spl.status in ('Drafted', 'Picked', 'Packed'))" +
  		")";

		
		
		// shippingClerk
		if (srch.getShippingClerk()!=null && !srch.getShippingClerk().equals("0")) {
//			hql += " and package.shippingClerk = "+srch.getShippingClerk();
			hql += " and assign.shippingClerk = clerk.clerkId and clerk.clerkFunction in ('Picker', 'Packer', 'Picker/Packer', 'All')"
					+" and assign.shippingClerk = package.shippingClerk and item.clsId = assign.clsId and item.type = assign.itemType"
					+" and package.shippingClerk = " + srch.getShippingClerk();
		}
		// status
		if (srch.getStatus()!=null && srch.getStatus().trim().length() > 0) {
			hql += " and ord.status= '"+srch.getStatus()+"'";
		}
		// priority
		if(srch.getPriority() != null && srch.getPriority().trim().length() > 0){
			hql += " and ord.priority = '"+srch.getPriority()+"'";
		}
		// type
		if(srch.getOrderType() != null && srch.getOrderType().trim().length() > 0){
			hql += " and ord.orderType = '"+srch.getOrderType()+"'";
		}
		if(srch.getOrderNo() != null && srch.getOrderNo() > 0)
			hql += " and ord.orderNo = '"+srch.getOrderNo()+"'";
		if (page.getOrder() != null && page.getOrder().trim().length() > 0) {
			hql += " order by " + page.getOrderBy() + ",ord.priority " + " "+ page.getOrder();
		}
		page = this.findPage(page, hql, map);
		List list = page.getResult();
		List<ShipPackageDTO> lists = new ArrayList<ShipPackageDTO>();
		ShipPackageDTO dto = null;
		for(int i=0;i<list.size();i++){
			Object[] obj = (Object[])list.get(i);
			dto = new ShipPackageDTO();
			dto.setOrderNo(Integer.parseInt(obj[0]+""));
			dto.setStatus(obj[1]+"");
			dto.setOrderType(obj[2]+"");
			dto.setPriority(obj[3]+"");
			String addressId = obj[4]+"";
			List listAddress = this.getAddressList(addressId);
			String addressName = "";
			for(int k=0;k<listAddress.size();k++){
				OrderAddress address = (OrderAddress)listAddress.get(k);
				addressName += address.getFirstName()+" "+address.getLastName()+","+address.getAddrLine1()+","
								+address.getCity()+","+address.getState()+" "+address.getZipCode()+","+address.getCountry();
			}
			if(addressName.length() > 1)
				dto.setShipTo(addressName.substring(0,addressName.length()-1));
			
			ShipPackage packages = this.getShipPackageLineByOrderNo(dto.getOrderNo()).getShipPackages();
			if(packages != null)
				dto.setPackageNo(packages.getPackageId());
			dto.setWarehouseName(obj[5]+"");
			String methodId = obj[6]+"";
			List listMethod = this.getShipMethod(methodId);
			String methodName = "";
			
			for(int j=0;j<listMethod.size();j++){
				ShipMethod method = (ShipMethod)listMethod.get(j);
				methodName += method.getName()+",";
			}
			if(methodName.length() > 1)
				dto.setShipVia(methodName.substring(0,methodName.length()-1));
			lists.add(dto);
		}
		List list2 = this.getSession().createQuery(hql).list();
		Long count2 = 0L;
		if(list2!=null){
			count2 = (long)list2.size();
		}
		pageShipPackageDto.setResult(lists);
		pageShipPackageDto.setTotalCount(count2);
		
		return pageShipPackageDto;
	}

	/**
	 * 根据OrderNo查询ShipPackageLines对象
	 * @param orderNo
	 * @return ShipPackageLines
	 */
	@SuppressWarnings("unchecked")
	public ShipPackageLines getShipPackageLineByOrderNo(Integer orderNo){
		String hql = "from ShipPackageLines where orderNo =:orderNo";
		List<ShipPackageLines> list = this.getSession().createQuery(hql).setInteger("orderNo", orderNo).list();
		if(list == null || list.size() == 0)
			return new ShipPackageLines();
		return list.get(0);
	}
	
	/**
	 * 得到ShipMethod的list
	 * @param methodId
	 * @return List
	 */
	private List<ShipMethod> getShipMethod(String methodId){
		try {
			return this.find("from ShipMethod s where s.methodId = "+methodId);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ArrayList<ShipMethod>();
	}
	
	/**
	 * 得到OrderAddresses的list
	 * @param addressId
	 * @return List
	 */
	private List<OrderAddress> getAddressList(String addressId){
		try {
			return this.find("from OrderAddress a where a.addrId = "+addressId);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ArrayList<OrderAddress>();
	}

	/**
	 * 保存shipping信息
	 * @param packages
	 * @return
	 * @throws Exception
	 */
	public String saveShipping(ShipPackage packages)throws Exception{
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

    public OrderAddress getOrderAddress(String packageId){
        OrderAddress order = new  OrderAddress();
		try {
		  String hql = "select oad.country, oad.busEmail from OrderAddress oad,OrderMain orm,ShipmentLine sl,ShipPackage sp " +
                  " where oad.addrId=orm.shiptoAddrId and orm.orderNo=sl.order.orderNo and sl.shipments.shipmentId=sp.shipments.shipmentId and sp.packageId=:packageId";
		  Map<String, Object> map =new HashMap<String,Object>();
		    map.put("packageId", new Integer(packageId));
		    List list=this.find(hql, map);
            if(list.size() != 0){
                Object[] obj=(Object[])list.get(0);
                order.setCountry(obj[0] == null ? "" : obj[0].toString());
                order.setBusEmail(obj[1] == null ? "" : obj[1].toString());
            }
        } catch (Exception ex) {
			ex.printStackTrace();
		}
		return order;
	}
	
	/**************************************************************************************************
	 * dingtf
	 * 
	 **************************************************************************************************/
	/**
	 * 更新ShipPackages中信息
	 * @param shipPackage
	 */
	public void updatePackId(ShipPackage shipPackage){
		this.save(shipPackage);
	}
	
	/**
	 * 根据packageId查询详细信息
	 * @param  packageId    参数
	 * @return ShipPackage 实体
	 */
	@SuppressWarnings("unchecked")
	public ShipPackage findByPackageId(Integer packageId){
		String hql = "from ShipPackage where packageId = ?";
		List list = this.find(hql,packageId);
		ShipPackage shipPackages = null;
		if(list.size()>0){
			shipPackages = (ShipPackage)list.get(0);
		}
		return shipPackages;
	}
	
	/**
	 * 根据orderNo 查询PackageId
	 * @param  orderNo 参数
	 * @return List    集合
	 */
	@SuppressWarnings("unchecked")
	public List<ShipPackage> getPackageTn(String orderNo,String status) {
		String hql = "select distinct(package.packageId) from ShipPackage package,ShipPackageLines line where line.shipPackages.packageId = package.packageId and package.status in('"+status+"') and line.orderNo in ("+orderNo+")";
		List<ShipPackage> list = this.getSession().createQuery(hql).list();
		return list;
	}
	
	/**
	 * 根据orderNo 查询PackageId
	 * @param  orderNo 参数
	 * @return List    集合
	 */
	@SuppressWarnings("unchecked")
	public List<ShipPackage> getPackageTn1(String orderNo,String status) {
		String hql = "select distinct(package) from ShipPackage package,ShipPackageLines line where line.shipPackages.packageId = package.packageId and package.status in('"+status+"') and line.orderNo in ("+orderNo+")";
		List<ShipPackage> list = this.getSession().createQuery(hql).list();
		return list;
	}
	
	/**
	 * 根据packageId查询
	 * @param  packageId
	 * @return List
	 */
	public List<ShipPackage> getPackageId(String packageId) {
		List<ShipPackage> list = this
					.find("select distinct(package) from ShipPackage package,ShipPackageLines line where line.shipPackages.packageId = package.packageId and package.packageId="
							+ packageId);
		return list;
	}
	
	/**
	 * 更新ShipPackages,ShipPackageLines状态(第二个按钮)
	 * @param orderNo
	 */
	public void updateStatus(String orderNo)
	{
		if(orderNo==null)
			orderNo="";
		String hql="update ShipPackage p  set p.status='Packed' where p.packageId in (select pl.shipPackages.packageId from ShipPackageLines pl where pl.orderNo in ("+orderNo+")) and p.status='Picked'";
		String hql1="update ShipPackageLines pl set pl.status='Packed' where  pl.orderNo in ("+orderNo+") and pl.status='Picked'" ;
		this.getSession().createQuery(hql).executeUpdate();
		this.getSession().createQuery(hql1).executeUpdate();
	}
	
	/**
	 * 根据orderNo查状态(第一个按钮和第二个按钮共用)
	 * @param orderNo
	 * @param status
	 * @return
	 */
	public Boolean SelectPackageIdAndStatus(String orderNo,String status)
	{
		Boolean selectStatus=false;
		String hql="select count(*) from ShipPackage package,ShipPackageLines line where line.shipPackages.packageId = package.packageId  and line.orderNo in ("+orderNo+") and package.status='"+status+"'";
		Long count=(Long)(super.getSession().createQuery(hql).uniqueResult());
		if(count!=0)
			selectStatus=true;
		return selectStatus;	
	}
	
	/**
	 * printPickList打印包的信息(DAO)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PrintPickListDTO> printPickList(String lineIds)
	{
			/*String str_ = "";
			List ls = getPackageTn(OrderNo,"Picked','Packed");
			for(int i = 0 ;ls!=null && i<ls.size();i++){
				String str = ls.get(i).toString();
				str_ += str+",";
			}
			if(str_.length()==0){
				return new ArrayList<PrintPickListDTO>(); 
			}
			str_ = str_.substring(0,str_.length()-1);*/
     		String hql="select oi.catalogNo,oi.orderItemId,oi.name,oi.quantity,oi.comment,spl.quantity,oi.size,spl.size, spl.pkgLineId,spl.status,oi.type" +
			" ,oi.qtyUom,oi.sizeUom from ShipPackageLines as spl,OrderItem as oi,Warehouse as wh" +
			" where spl.orderNo=oi.orderNo  and spl.itemNo=oi.itemNo " +
			
            " and spl.status  in ('Drafted','Picked','Packed') and spl.pkgLineId in ("+lineIds+") group by oi.orderNo,oi.itemNo";
     		
     		/*String hql="select distinct(pl.packageId),oi.catalogNo,o.billtoAddrId,o.shiptoAddrId,p.shipMethod,p.billableWeight,p.packer,concat(concat(wh.name,' - '),sto.name),oi.name,oi.quantity,oi.comment,wh.name,spl.quantity " +
			" from ShipPackageLines as spl,OrderItem as oi,PickingLogs as pl,ShipPackage as p,OrderMain as o ,Warehouse as wh,Storage sto  " +
			" where spl.orderNo=oi.orderNo and pl.pkgLineId = spl.pkgLineId  and spl.itemNo=oi.itemNo " +
			" and p.packageId=spl.shipPackages.packageId and spl.itemNo=oi.itemNo and oi.orderNo=o.orderNo " +
            " and wh.warehouseId = sto.warehouse "+
			" and wh.warehouseId=p.warehouseId and p.status not in ('Ready To Ship','Shipped') and p.packageId in ("+str_+") group by p.packageId";
			*/
     		Query q = createQuery(hql);
			List l = q.list();
			List<PrintPickListDTO> list=new ArrayList<PrintPickListDTO>();
				for(int i=0;i<l.size();i++)
				{
					PrintPickListDTO ppldto=new PrintPickListDTO();
					Object[] o=(Object[])l.get(i);
					//up
					
					ppldto.setCatNo((String)o[0]);
					
					//un
					//ppldto.setStorageLocation(o[1]==null?"":o[1].toString());
					if((((String)o[2]).length()) >30)
					{
						ppldto.setName(((String)o[2]).substring(0, 29)+"...");
					}
					else
						ppldto.setName((String)o[2]);
					Integer oiQty = (Integer)o[3];
					ppldto.setQtyToPick((Integer)o[3]);
					ppldto.setQtypicked(o[5].toString());
					ppldto.setComment(o[4]==null?"":o[4].toString());
					Double oiSize = (Double)o[6];
					if(oiSize==null){
						oiSize = 0.0;
					}
					ppldto.setSizeToPick((Arith.mul(oiSize, oiQty))+"");
					ppldto.setSizePick(o[7]+"");
					ppldto.setLineId(o[8]+"");
					ppldto.setStatus(o[9]+"");
					ppldto.setType(o[10]+"");
					ppldto.setQtyUom(o[11]+"");
					if(ppldto.getQtyUom()==null||ppldto.getQtyUom().equals("null")){
						ppldto.setQtyUom("");
					}
					ppldto.setSizeUom(o[12]+"");
					if(ppldto.getSizeUom()==null||ppldto.getSizeUom().equals("null")){
						ppldto.setSizeUom("");
					}
					list.add(ppldto);
				}

			return list;
	}
	
	/**
	 * 第二个按钮的查询
	 * @param orderNo
	 * @return list
	 */
	@SuppressWarnings("unchecked")
	public List<ViewPackingSlipDTO> ViewPackingSlip(String orderNo)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		//List ls = this.getPackageTn(orderNo,"Packed','Ready to ship");
		String hql=" select o.billtoAddrId,o.shiptoAddrId,o.custNo,o.orderNo,o.orderDate,oi.shipMethod,p.shipmentDate,p.billableWeight,p.packageId,oi.quantity," +
				" oi.itemNo,oi.shortDesc,oi.unitPrice,oi.discount,oi.amount " +
				" from ShipPackageLines as spl,ShipPackage as p,OrderMain as o,OrderItem oi" +
				" where p.status='Packed' and spl.orderNo=o.orderNo and spl.itemNo=oi.itemNo and oi.orderNo=o.orderNo and p.packageId=spl.shipPackages.packageId and p.packageId in (:packageId)";

		map.put("packageId", getPackageTn(orderNo,"Packed"));
		Query q=createQuery(hql,map);
		List l=q.list();
		List<ViewPackingSlipDTO> listView =new ArrayList<ViewPackingSlipDTO>();
		for(int i=0;i<l.size();i++)
		{
			Object[] o=(Object[])l.get(i);
			ViewPackingSlipDTO view =new ViewPackingSlipDTO();
			view.setBillTo(o[0]==null?"":o[0].toString());
			view.setShipTO(o[1]==null?"":o[1].toString());
			view.setCustomerNo((Integer)(o[2]));
			view.setOrder((Integer)o[3]);
			view.setOrderDate(o[4]==null?"":o[4].toString());
			view.setShipVia(o[5]==null?"":o[5].toString());
			view.setShippingDate(o[6]==null?"":o[6].toString());
			view.setTotalWeight(o[7]==null?"":o[7]+"");
			//view.setOfPackages((Integer)o[8]);
			view.setQtyOrdered((Integer)o[9]);
			view.setItem(Integer.parseInt(o[10]+""));
			view.setDescription(o[11]==null?"":o[11].toString());
			view.setUnitPrice((Double)o[12]);
			view.setDisc((Double)o[13]);
			view.setExtendedPrice((Double)o[14]);
//			for(int j=0;j<ls.size();j++){
//				Integer packageId_ = Integer.parseInt(ls.get(j)+"");
//				if(packageId_ != null){
//					Long qty_sum = this.getQtysum(packageId_);
//					view.setQtyShipped(qty_sum+"");
//				}
//			}
			Integer packageId_ = Integer.parseInt(o[8]+"");
			if(packageId_ != null){
				Long qty_sum = this.getQtysum(packageId_);
				view.setQtyShipped(qty_sum+"");
			}
			if(view.getQtyShipped()!=null&&view.getUnitPrice()!=null){
				view.setSubtotal(Arith.mul(view.getUnitPrice(),Double.parseDouble(view.getQtyShipped())));
			}
			
			listView.add(view);		
		}
		return listView;
	}
	
	/**
	 * 判断并获取lines的qtysum
	 * @param packageId
	 * @return Integer
	 */
	public Long getQtysum(Integer packageId){
		try {
			List<Long> temp= this.find("select sum(spl.quantity) from ShipPackageLines as spl,ShipPackage as p,OrderMain as o,OrderItem oi " +
					"where spl.orderNo=o.orderNo and spl.itemNo=oi.itemNo and oi.orderNo=o.orderNo and p.packageId=spl.shipPackages.packageId and p.packageId ="+packageId );
			return temp.get(0);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0L;
	}
	
//	/**
//	 * 第二个按钮的查询
//	 * @param orderNo
//	 * @return list
//	 */
//	@SuppressWarnings("unchecked")
//	 public List<ViewPackingSlipDTO> ViewPackingSlip(String orderNo)
//	{
//		List<ViewPackingSlipDTO> listView =null;
//		Map<String, Object> map = new HashMap<String, Object>();
//		
//		List ls = this.getPackageTn(orderNo,"Packed','Ready to ship");
//		for(int j=0;j<ls.size();j++){
//			Integer packageId_ = Integer.parseInt(ls.get(j)+"");
//			String hql="select o.billtoAddrId,o.shiptoAddrId,o.custNo,o.orderNo,o.orderDate,oi.shipMethod,p.shipmentDate,p.billableWeight,p.packageId,oi.quantity," +
//					"(select sum(spl.quantity) from ShipPackageLines as spl,ShipPackage as p,OrderMain as o,OrderItem oi " +
//					"where spl.orderNo=o.orderNo and spl.itemNo=oi.itemNo and oi.orderNo=o.orderNo and p.packageId=spl.shipPackages.packageId and p.packageId in (:packageId_))," +
//					"oi.itemNo,oi.shortDesc,oi.unitPrice,oi.discount,oi.amount " +
//					" from ShipPackageLines as spl,ShipPackage as p,OrderMain as o,OrderItem oi" +
//					" where spl.orderNo=o.orderNo and spl.itemNo=oi.itemNo and oi.orderNo=o.orderNo and p.packageId=spl.shipPackages.packageId and p.packageId in (:packageId_)";
//	
//			map.put("packageId_",packageId_);
//			Query q=createQuery(hql,map);
//			
//			List l=q.list();
//			listView =new ArrayList<ViewPackingSlipDTO>();
//			for(int i=0;i<l.size();i++)
//			{
//				Object[] o=(Object[])l.get(i);
//				ViewPackingSlipDTO view =new ViewPackingSlipDTO();
//				view.setBillTo(o[0]==null?"":o[0].toString());
//				view.setShipTO(o[1]==null?"":o[1].toString());
//				view.setCustomerNo((Integer)(o[2]));
//				view.setOrder((Integer)o[3]);
//				view.setOrderDate(o[4]==null?"":o[4].toString());
//				view.setShipVia(o[5]==null?"":o[5].toString());
//				view.setShippingDate(o[6]==null?"":o[6].toString());
//				view.setTotalWeight(o[7]==null?"":o[7]+"");
//				view.setOfPackages((Integer)o[8]);
//				view.setQtyOrdered((Integer)o[9]);
//				view.setQtyShipped(o[10]+"");
//				view.setItem((Integer)o[11]);
//				view.setDescription(o[12]==null?"":o[12].toString());
//				view.setUnitPrice((Double)o[13]);
//				view.setDisc((Double)o[14]);
//				view.setExtendedPrice((Double)o[15]);
//				listView.add(view);		
//			}
//		}
//		return listView;
//		
//	}
	/**
	 * ShipTO BillTo
	 * @param id
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String selectAddr(String id,String type)
	{
		String address="";
		if(id==null || id.trim().equals(""))
			return address;
		String hql="from OrderAddress where addrId='"+id+"' and addrType='"+type+"'";
		Query q=createQuery(hql);
		List<OrderAddress> list=(List<OrderAddress>)q.list();
		for(OrderAddress addr :list)
		{
			address=addr.getFirstName()+"<br />"+addr.getLastName()+"<br />"+addr.getAddrLine1()+addr.getCity()+addr.getState();
		}
		if(address=="")
			address="<br /><br /><br />";
		return address;
	}
	/**
	 * 从ShipMethod表中取出name值(ship Via)
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String selectShipVia(String id)
	{
		String shipVia="";
		if(id==null || id.trim().equals(""))
			return shipVia;
		String hql="from  ShipMethod where methodId='"+id+"'";
		Query q=createQuery(hql);
		List<ShipMethod> list=(List<ShipMethod>)q.list();
		for(ShipMethod sm : list)
		{
			shipVia=shipVia+sm.getName();
			
		}
		return shipVia;
	}

	public String saveShipPackage(ShipPackage packages)throws Exception{
		String flag = "0";
		try {
			//this.save(packages);
			this.getSession().save(packages);
			flag = "1";
			this.getSession().flush();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return flag;
	}
	
	/**
	 * 根据id返回ShipPackages对象
	 * @param id
	 * @return ShipPackage
	 */
	public ShipPackage getShipPackageById(Serializable id){
		return this.getById((Integer)id);
	}
	
	/**
	 * 取消装运功能:更新ShipPackages表信息
	 * @param packageId
	 * @param note
	 * @return
	 */
	public Serializable updateShipPackagesByPackageId(Serializable packageId,Serializable note){
		String hql="update ShipPackage set status='Packed',trackingNo='',note='Cancel Reason: "+note+"' ,sendBy=null  where packageId=?";
		return this.batchExecute(hql, packageId);
	}
	
	/**
	 * 查询customer_credit_card表中的信息(银行接口)
	 * @param packageID
	 * @param bankCardDTO
	 * @return
	 */	@SuppressWarnings("unchecked")
	public BankCardDTO selectCustomerCreditCards(List packageID,BankCardDTO bankCardDTO)
	{																		             
		String hql="select c.cardHolder,c.cardNo,c.cvc,c.expr_date,s.custNo,s.currency,c.cardType from Shipment s,CustomerCreditCards c,ShipPackage p where s.custNo=c.custNo and s.shipmentId=p.shipments.shipmentId and p.packageId in (:packageId)";
		Map<String, Object> map =new HashMap<String,Object>();
		map.put("packageId", packageID);
		List l=this.find(hql, map);
		if(l.size()!=0)
		{
			Object[] o=(Object[])l.get(0);
			bankCardDTO.setCardHolder(o[0]!=null?o[0].toString():"");
			bankCardDTO.setCardNo(o[1]!=null?o[1].toString():null);
			bankCardDTO.setCvc(o[2]!=null?o[2].toString():"");
			String year="";
			String month="";
			if(o[3]!=null)
			{
				year=(o[3].toString()).substring(0, 4);
				month=(o[3].toString()).substring(5, 7);
			}
			bankCardDTO.setCusNo(o[4]!=null?Integer.valueOf(o[4].toString()):0);
			bankCardDTO.setCurrency(o[5]!=null?o[5].toString():"0");
			bankCardDTO.setType(o[6]!=null?o[6].toString():"0");
			bankCardDTO.setExprYear(year);
			bankCardDTO.setExprMonth(month);
			
		}
		return bankCardDTO;
	}
	/**
	 * 查询order_address表中的信息(银行接口)
	 * @param packageID
	 * @param bankCardDTO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public BankCardDTO selectOrderAddress(List packageID,BankCardDTO bankCardDTO)
	{
		String hql="select oa.addrLine1,oa.addrLine2,oa.addrLine3,oa.city,oa.state,oa.country,oa.zipCode,spl.orderNo from ShipPackageLines spl,OrderMain o,OrderAddress oa where spl.orderNo=o.orderNo and o.billtoAddrId=oa.addrId and spl.shipPackages.packageId in (:packageId)";
		Map<String, Object> map =new HashMap<String,Object>();
		map.put("packageId", packageID);
		List l=this.find(hql, map);
		if(l.size()!=0)
		{
			Object[] o=(Object[])l.get(0);
			String billTo="";
			if(o[0]!=null&&!o[0].toString().equals(""))
				billTo+=","+o[0].toString();
			if(o[1]!=null&&!o[0].toString().equals(""))
				billTo+=","+o[1].toString();
			if(o[2]!=null&&!o[0].toString().equals(""))
				billTo+=","+o[2].toString();
			bankCardDTO.setBillTo(billTo.substring(1));
			bankCardDTO.setCity(o[3]!=null?o[3].toString():"");
			bankCardDTO.setState(o[4]!=null?o[4].toString():"");
			bankCardDTO.setCountry(o[5]!=null?o[5].toString():"");
			bankCardDTO.setZipCode(o[6]!=null?o[6].toString():"");
			bankCardDTO.setOrderNo(o[7]!=null?o[6].toString():"");
		}
		return bankCardDTO;
	}
	/**
	 * 查询ShipPackages表中customer_charge的信息(银行接口)
	 * @param packageID
	 * @param bankCardDTO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public BankCardDTO selectPackagesCustomerCharge(List packageID,BankCardDTO bankCardDTO)
	{
		String hql="select customerCharge from ShipPackage p where p.packageId in (:packageId)";
		Map<String, Object> map =new HashMap<String,Object>();
		map.put("packageId", packageID);
		List l=this.find(hql, map);
		bankCardDTO.setCustomerCharge(0.0);
		if(l.size()!=0 && l!=null)
		{
			for(int i=0;i<l.size();i++){
				if(l.get(i)!=null)
					bankCardDTO.setCustomerCharge(Arith.add(bankCardDTO.getCustomerCharge(), ((BigDecimal)l.get(0)).doubleValue()));
				//else
					//bankCardDTO.setCustomerCharge(1.00);
			}
		}
		return bankCardDTO;
	}
	
}