package com.genscript.gsscm.olddb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "marker", catalog = "olddb")
public class Marker implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5421068115886169042L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String code;
	private String size1;
	private String size2;
	private Float price;
	private Float comp;
	private String document;
	private String title;
	private String category;
	private String gcode;
	private Integer listId;
	private Float vendorPrice;
	private String description;
	private String seq;
	private String map;
	private String storage;
	private Integer gsonly;
	private String dnaSeq;
	private String regine;
	private String protocol;
	private String coordinator;
	@Column(name = "re5_name")
	private String re5Name;
	@Column(name = "re5_seq")
	private String re5Seq;
	@Column(name = "re3_name")
	private String re3Name;
	@Column(name = "re3_seq")
	private String re3Seq;
	private String relatedProducts;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSize1() {
		return size1;
	}
	public void setSize1(String size1) {
		this.size1 = size1;
	}
	public String getSize2() {
		return size2;
	}
	public void setSize2(String size2) {
		this.size2 = size2;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Float getComp() {
		return comp;
	}
	public void setComp(Float comp) {
		this.comp = comp;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getGcode() {
		return gcode;
	}
	public void setGcode(String gcode) {
		this.gcode = gcode;
	}
	public Integer getListId() {
		return listId;
	}
	public void setListId(Integer listId) {
		this.listId = listId;
	}
	public Float getVendorPrice() {
		return vendorPrice;
	}
	public void setVendorPrice(Float vendorPrice) {
		this.vendorPrice = vendorPrice;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getMap() {
		return map;
	}
	public void setMap(String map) {
		this.map = map;
	}
	public String getStorage() {
		return storage;
	}
	public void setStorage(String storage) {
		this.storage = storage;
	}
	public Integer getGsonly() {
		return gsonly;
	}
	public void setGsonly(Integer gsonly) {
		this.gsonly = gsonly;
	}
	public String getDnaSeq() {
		return dnaSeq;
	}
	public void setDnaSeq(String dnaSeq) {
		this.dnaSeq = dnaSeq;
	}
	public String getRegine() {
		return regine;
	}
	public void setRegine(String regine) {
		this.regine = regine;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getCoordinator() {
		return coordinator;
	}
	public void setCoordinator(String coordinator) {
		this.coordinator = coordinator;
	}
	public String getRe5Name() {
		return re5Name;
	}
	public void setRe5Name(String re5Name) {
		this.re5Name = re5Name;
	}
	public String getRe5Seq() {
		return re5Seq;
	}
	public void setRe5Seq(String re5Seq) {
		this.re5Seq = re5Seq;
	}
	public String getRe3Name() {
		return re3Name;
	}
	public void setRe3Name(String re3Name) {
		this.re3Name = re3Name;
	}
	public String getRe3Seq() {
		return re3Seq;
	}
	public void setRe3Seq(String re3Seq) {
		this.re3Seq = re3Seq;
	}
	public String getRelatedProducts() {
		return relatedProducts;
	}
	public void setRelatedProducts(String relatedProducts) {
		this.relatedProducts = relatedProducts;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
