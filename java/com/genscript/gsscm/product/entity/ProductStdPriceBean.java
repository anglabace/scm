package com.genscript.gsscm.product.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * ProductStdPriceBean.
 * 
 * 
 * @author Wangsf
 */
@Entity
@Table(name = "v_products_in_category", catalog = "product")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class ProductStdPriceBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2970007910385075955L;
	@Id
	@Column(name = "product_id")
	private Integer productId;
	private String catalogNo;
        private String type;
	private String name;
	private Integer size;
        private String uom;
        private String qtyUom;
        private String description;
        private Integer categoryId;
	private String catalogId;
        private String catalogStatus;
        private String baseCatalog;
        private Double unitPrice;
	private String symbol;
	private Double pricePrecision;
	private String currencyCode;
	private String status;
	private Date creationDate;
	private Date modifyDate;
         private Integer leadTime;
        
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	/**
	 * @return the productId
	 */
	public Integer getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	/**
	 * @return the catalogNo
	 */
	public String getCatalogNo() {
		return catalogNo;
	}
	/**
	 * @param catalogNo the catalogNo to set
	 */
	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}
	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}
	/**
	 * @param symbol the symbol to set
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}


	/**
	 * @return the standardPrice
	 */
//	public Double getStandardPrice() {
//		return standardPrice;
//	}
	/**
	 * @param standardPrice the standardPrice to set
	 */
//	public void setStandardPrice(Double standardPrice) {
//		this.standardPrice = standardPrice;
//	}
	/**
	 * @return the catalogId
	 */
	public String getCatalogId() {
		return catalogId;
	}
	/**
	 * @param catalogId the catalogId to set
	 */
	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the leadTime
	 */
	public Integer getLeadTime() {
		return leadTime;
	}
	/**
	 * @param leadTime the leadTime to set
	 */
	public void setLeadTime(Integer leadTime) {
		this.leadTime = leadTime;
	}
	//public String getShortDesc() {
	//	return shortDesc;
	//}
//	public void setShortDesc(String shortDesc) {
//		this.shortDesc = shortDesc;
//	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the uom
     */
    public String getUom() {
        return uom;
    }

    /**
     * @param uom the uom to set
     */
    public void setUom(String uom) {
        this.uom = uom;
    }

    /**
     * @return the qtyUom
     */
    public String getQtyUom() {
        return qtyUom;
    }

    /**
     * @param qtyUom the qtyUom to set
     */
    public void setQtyUom(String qtyUom) {
        this.qtyUom = qtyUom;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the categoryId
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * @return the catalogStatus
     */
    public String getCatalogStatus() {
        return catalogStatus;
    }

    /**
     * @param catalogStatus the catalogStatus to set
     */
    public void setCatalogStatus(String catalogStatus) {
        this.catalogStatus = catalogStatus;
    }

    /**
     * @return the baseCatalog
     */
    public String getBaseCatalog() {
        return baseCatalog;
    }

    /**
     * @param baseCatalog the baseCatalog to set
     */
    public void setBaseCatalog(String baseCatalog) {
        this.baseCatalog = baseCatalog;
    }

    /**
     * @return the unitPrice
     */
    public Double getUnitPrice() {
        return unitPrice;
    }

    /**
     * @param unitPrice the unitPrice to set
     */
    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * @return the pricePrecision
     */
    public Double getPricePrecision() {
        return pricePrecision;
    }

    /**
     * @param pricePrecision the pricePrecision to set
     */
    public void setPricePrecision(Double pricePrecision) {
        this.pricePrecision = pricePrecision;
    }

    /**
     * @return the currencyCode
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * @param currencyCode the currencyCode to set
     */
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
