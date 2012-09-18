package com.genscript.gsscm.serv.entity;

import javax.persistence.*;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;
import org.springframework.transaction.annotation.Transactional;

/**
 * PRODUCT ProductClass.
 * 
 * 
 * @author Wangsf
 */
@Entity
@Table(name = "service_classification", catalog="product")
public class ServiceClass extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer clsId;
	private String name;
	private String description;
    @Transient
    private String showPriceType;
    @Transient
    private String lineSymbol;

    public String getLineSymbol() {
        return lineSymbol;
    }

    public void setLineSymbol(String lineSymbol) {
        this.lineSymbol = lineSymbol;
    }

    public String getShowPriceType() {
        return showPriceType;
    }

    public void setShowPriceType(String showPriceType) {
        this.showPriceType = showPriceType;
    }

    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	/**
	 * @return the clsId
	 */
	public Integer getClsId() {
		return clsId;
	}
	/**
	 * @param clsId the clsId to set
	 */
	public void setClsId(Integer clsId) {
		this.clsId = clsId;
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
}
