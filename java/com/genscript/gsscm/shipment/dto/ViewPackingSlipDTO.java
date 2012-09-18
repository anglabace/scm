package com.genscript.gsscm.shipment.dto;

import java.util.List;

import com.genscript.gsscm.manufacture.dto.WorkOrderDTO;
import com.genscript.gsscm.manufacture.entity.WorkOrder;
import com.genscript.gsscm.order.entity.OrderMain;
import com.genscript.gsscm.shipment.entity.ShipPackage;

public class ViewPackingSlipDTO implements Comparable<ViewPackingSlipDTO> {
	//up
	private String billTo;
	private String shipTO;
	//un
	private Integer customerNo;
	private Integer order;
	private String orderDate;
	private String shipVia;
	private String shippingDate;
	private String terms;
	private String totalWeight;
	private Double totalWeightDouble;
	private Integer ofPackages;
	private String ofPacakge;
	private Integer qtyOrdered;
	private String qtyShipped;
	private Integer item;
	private String description;
	private Double unitPrice;
	private Double disc;
	private Double extendedPrice;
	private Double subtotal;
	private Double discount;
	private Double tax;
	private Double shipping;
	private Double handing;
	private Double total;
	private OrderMain orderMain ;
	private ShipPackage shipPackage;
	private String po;
	private String type;
	private List<ShipPackageLineDTO> shipPackageLineDTO;
	private List<WorkOrderDTO> workOrderList;
	public String getBillTo() {
		return billTo;
	}
	public void setBillTo(String billTo) {
		this.billTo = billTo;
	}
	public Integer getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(Integer customerNo) {
		this.customerNo = customerNo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getDisc() {
		return disc;
	}
	public void setDisc(Double disc) {
		this.disc = disc;
	}
	public Double getExtendedPrice() {
		return extendedPrice;
	}
	public void setExtendedPrice(Double extendedPrice) {
		this.extendedPrice = extendedPrice;
	}
	public Integer getItem() {
		return item;
	}
	public void setItem(Integer item) {
		this.item = item;
	}
	public Integer getOfPackages() {
		return ofPackages;
	}
	public void setOfPackages(Integer ofPackages) {
		this.ofPackages = ofPackages;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public Integer getQtyOrdered() {
		return qtyOrdered;
	}
	public void setQtyOrdered(Integer qtyOrdered) {
		this.qtyOrdered = qtyOrdered;
	}
	public String getQtyShipped() {
		return qtyShipped;
	}
	public void setQtyShipped(String qtyShipped) {
		this.qtyShipped = qtyShipped;
	}
	public String getShippingDate() {
		return shippingDate;
	}
	public void setShippingDate(String shippingDate) {
		this.shippingDate = shippingDate;
	}
	public String getShipTO() {
		return shipTO;
	}
	public void setShipTO(String shipTO) {
		this.shipTO = shipTO;
	}
	public String getShipVia() {
		return shipVia;
	}
	public void setShipVia(String shipVia) {
		this.shipVia = shipVia;
	}
	public String getTotalWeight() {
		return totalWeight;
	}
	public void setTotalWeight(String totalWeight) {
		this.totalWeight = totalWeight;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int compareTo(ViewPackingSlipDTO o) {
		// TODO Auto-generated method stub
		return this.order.compareTo(o.getOrder());
	}
	public String getOfPacakge() {
		return ofPacakge;
	}
	public void setOfPacakge(String ofPacakge) {
		this.ofPacakge = ofPacakge;
	}
	public Double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Double getTax() {
		return tax;
	}
	public void setTax(Double tax) {
		this.tax = tax;
	}

	public Double getShipping() {
		return shipping;
	}
	public void setShipping(Double shipping) {
		this.shipping = shipping;
	}
	public Double getHanding() {
		return handing;
	}
	public void setHanding(Double handing) {
		this.handing = handing;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}

	public List<ShipPackageLineDTO> getShipPackageLineDTO() {
		return shipPackageLineDTO;
	}
	public void setShipPackageLineDTO(List<ShipPackageLineDTO> shipPackageLineDTO) {
		this.shipPackageLineDTO = shipPackageLineDTO;
	}
	public OrderMain getOrderMain() {
		return orderMain;
	}
	public void setOrderMain(OrderMain orderMain) {
		this.orderMain = orderMain;
	}
	public ShipPackage getShipPackage() {
		return shipPackage;
	}
	public void setShipPackage(ShipPackage shipPackage) {
		this.shipPackage = shipPackage;
	}
	public Double getTotalWeightDouble() {
		return totalWeightDouble;
	}
	public void setTotalWeightDouble(Double totalWeightDouble) {
		this.totalWeightDouble = totalWeightDouble;
	}
	public String getTerms() {
		return terms;
	}
	public void setTerms(String terms) {
		this.terms = terms;
	}
	public String getPo() {
		return po;
	}
	public void setPo(String po) {
		this.po = po;
	}
	public List<WorkOrderDTO> getWorkOrderList() {
		return workOrderList;
	}
	public void setWorkOrderList(List<WorkOrderDTO> workOrderList) {
		this.workOrderList = workOrderList;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}


}
