package com.genscript.gsscm.product.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * product Detail.
 * 
 * 
 * @author Mingrs
 */
@Entity
@Table(name = "enzyme", catalog="product")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Enzyme extends BaseEntity {
	@Id
	private Integer productId;
	private String appearance;
	private String color;
	private String purity;
	private String concentration;
	private String specificActivity;
	private String reactionBuffer;
	private String substrate;
	private String qualityControl;
	private String stability;
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getAppearance() {
		return appearance;
	}
	public void setAppearance(String appearance) {
		this.appearance = appearance;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getPurity() {
		return purity;
	}
	public void setPurity(String purity) {
		this.purity = purity;
	}
	public String getConcentration() {
		return concentration;
	}
	public void setConcentration(String concentration) {
		this.concentration = concentration;
	}
	public String getSpecificActivity() {
		return specificActivity;
	}
	public void setSpecificActivity(String specificActivity) {
		this.specificActivity = specificActivity;
	}
	public String getReactionBuffer() {
		return reactionBuffer;
	}
	public void setReactionBuffer(String reactionBuffer) {
		this.reactionBuffer = reactionBuffer;
	}
	public String getSubstrate() {
		return substrate;
	}
	public void setSubstrate(String substrate) {
		this.substrate = substrate;
	}
	public String getQualityControl() {
		return qualityControl;
	}
	public void setQualityControl(String qualityControl) {
		this.qualityControl = qualityControl;
	}
	public String getStability() {
		return stability;
	}
	public void setStability(String stability) {
		this.stability = stability;
	}
	
}
