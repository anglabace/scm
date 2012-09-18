package com.genscript.gsscm.serv.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.quoteorder.dto.AntibodyDTO;
import com.genscript.gsscm.quoteorder.dto.OrderCustCloningDTO;
import com.genscript.gsscm.quoteorder.dto.OrderGeneSynthesisDTO;
import com.genscript.gsscm.quoteorder.dto.OrderMutagenesisDTO;
import com.genscript.gsscm.quoteorder.dto.OrderOligoDTO;
import com.genscript.gsscm.quoteorder.dto.OrderOrfCloneDTO;
import com.genscript.gsscm.quoteorder.dto.OrderPlasmidPreparationDTO;
import com.genscript.gsscm.quoteorder.dto.PeptideDTO;
import com.genscript.gsscm.quoteorder.dto.PkgServiceDTO;
import com.genscript.gsscm.quoteorder.dto.RnaDTO;

@XmlType(name = "OrderServiceItemPiceDTO", namespace = WsConstants.NS)
public class ServiceItemPiceDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6868322122188067067L;
	private String catalogNo;
	private String servicePriceType;
	private Integer serviceClsId;
	private String catalogId;
	private OrderGeneSynthesisDTO geneSynthesisDTO;
	private OrderCustCloningDTO custCloningDTO;
	private PeptideDTO peptideDTO;
	private OrderMutagenesisDTO mutagenesisDTO;
	private OrderPlasmidPreparationDTO plasmidPreparationDTO;
	private PkgServiceDTO pkgServiceDTO;
	private AntibodyDTO antibodyDTO;
	private RnaDTO rnaDTO;
	private OrderOrfCloneDTO orfCloneDTO;
	private OrderOligoDTO oligoDTO;
	private BigDecimal cost;
	private BigDecimal price;
	private Integer quantity;
	private Integer qty;
	private String TBDFlag;
	private String priceStr;
	private String costStr;
	private Integer parentClsId;
	private BigDecimal amount;
	private Double discount;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getCatalogNo() {
		return catalogNo;
	}

	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}

	public OrderGeneSynthesisDTO getGeneSynthesisDTO() {
		return geneSynthesisDTO;
	}

	public void setGeneSynthesisDTO(OrderGeneSynthesisDTO geneSynthesisDTO) {
		this.geneSynthesisDTO = geneSynthesisDTO;
	}

	public BigDecimal getCost() {
		if(cost == null)
			return null;
		return cost.setScale(2,
				BigDecimal.ROUND_HALF_UP);
	}

	public void setCost(BigDecimal cost) {
		if(cost == null)
			this.cost = null;
		this.cost = cost.setScale(2,
				BigDecimal.ROUND_HALF_UP);
	}

	public BigDecimal getPrice() {
		if(price == null)
			return null;
		return price.setScale(2,
				BigDecimal.ROUND_HALF_UP);
	}

	public void setPrice(BigDecimal price) {
		if(price == null)
			this.price = null;
		this.price = price.setScale(2,
				BigDecimal.ROUND_HALF_UP);
	}

	public String getTBDFlag() {
		return TBDFlag;
	}

	public void setTBDFlag(String tBDFlag) {
		TBDFlag = tBDFlag;
	}

	public OrderCustCloningDTO getCustCloningDTO() {
		return custCloningDTO;
	}

	public void setCustCloningDTO(OrderCustCloningDTO custCloningDTO) {
		this.custCloningDTO = custCloningDTO;
	}

	public PeptideDTO getPeptideDTO() {
		return peptideDTO;
	}

	public void setPeptideDTO(PeptideDTO peptideDTO) {
		this.peptideDTO = peptideDTO;
	}

	public OrderMutagenesisDTO getMutagenesisDTO() {
		return mutagenesisDTO;
	}

	public void setMutagenesisDTO(OrderMutagenesisDTO mutagenesisDTO) {
		this.mutagenesisDTO = mutagenesisDTO;
	}

	public OrderPlasmidPreparationDTO getPlasmidPreparationDTO() {
		return plasmidPreparationDTO;
	}

	public void setPlasmidPreparationDTO(
			OrderPlasmidPreparationDTO plasmidPreparationDTO) {
		this.plasmidPreparationDTO = plasmidPreparationDTO;
	}

	public String getServicePriceType() {
		return servicePriceType;
	}

	public void setServicePriceType(String servicePriceType) {
		this.servicePriceType = servicePriceType;
	}

	public Integer getServiceClsId() {
		return serviceClsId;
	}

	public void setServiceClsId(Integer serviceClsId) {
		this.serviceClsId = serviceClsId;
	}

	public String getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}

	public PkgServiceDTO getPkgServiceDTO() {
		return pkgServiceDTO;
	}

	public void setPkgServiceDTO(PkgServiceDTO pkgServiceDTO) {
		this.pkgServiceDTO = pkgServiceDTO;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public AntibodyDTO getAntibodyDTO() {
		return antibodyDTO;
	}

	public void setAntibodyDTO(AntibodyDTO antibodyDTO) {
		this.antibodyDTO = antibodyDTO;
	}

	public String getPriceStr() {
		return priceStr;
	}

	public void setPriceStr(String priceStr) {
		this.priceStr = priceStr;
	}

	public String getCostStr() {
		return costStr;
	}

	public void setCostStr(String costStr) {
		this.costStr = costStr;
	}

	public RnaDTO getRnaDTO() {
		return rnaDTO;
	}

	public void setRnaDTO(RnaDTO rnaDTO) {
		this.rnaDTO = rnaDTO;
	}

	public OrderOrfCloneDTO getOrfCloneDTO() {
		return orfCloneDTO;
	}

	public void setOrfCloneDTO(OrderOrfCloneDTO orfCloneDTO) {
		this.orfCloneDTO = orfCloneDTO;
	}

	public Integer getParentClsId() {
		return parentClsId;
	}

	public void setParentClsId(Integer parentClsId) {
		this.parentClsId = parentClsId;
	}

	public OrderOligoDTO getOligoDTO() {
		return oligoDTO;
	}

	public void setOligoDTO(OrderOligoDTO oligoDTO) {
		this.oligoDTO = oligoDTO;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

}
