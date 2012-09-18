package com.genscript.gsscm.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * product Detail. GENE.
 * 
 * 
 * @author Golf
 */
@Entity
@Table(name = "gene", catalog="product")
public class Gene extends BaseEntity {
	@Id
	@Column(name="product_id")
	private Integer productId;

	@Column(name="accession_no")
	private String accessionNo;

	private String affiliation;

    @Lob()
	@Column(name="cloning_site")
	private String cloningSite;

	@Column(name="flank_3")
	private String flank3;

	@Column(name="flank_5")
	private String flank5;

    @Lob()
	private String genemap;

	private String organism;

	private String refseqid;

    @Lob()
	private String sequence;

	private String species;

	private String vector;



	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getAccessionNo() {
		return accessionNo;
	}

	public void setAccessionNo(String accessionNo) {
		this.accessionNo = accessionNo;
	}

	public String getAffiliation() {
		return affiliation;
	}

	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}

	public String getCloningSite() {
		return cloningSite;
	}

	public void setCloningSite(String cloningSite) {
		this.cloningSite = cloningSite;
	}

	public String getFlank3() {
		return flank3;
	}

	public void setFlank3(String flank3) {
		this.flank3 = flank3;
	}

	public String getFlank5() {
		return flank5;
	}

	public void setFlank5(String flank5) {
		this.flank5 = flank5;
	}

	public String getGenemap() {
		return genemap;
	}

	public void setGenemap(String genemap) {
		this.genemap = genemap;
	}

	public String getOrganism() {
		return organism;
	}

	public void setOrganism(String organism) {
		this.organism = organism;
	}

	public String getRefseqid() {
		return refseqid;
	}

	public void setRefseqid(String refseqid) {
		this.refseqid = refseqid;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getVector() {
		return vector;
	}

	public void setVector(String vector) {
		this.vector = vector;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
