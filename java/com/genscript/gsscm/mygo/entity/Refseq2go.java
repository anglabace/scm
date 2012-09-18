package com.genscript.gsscm.mygo.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "refseq2go", catalog = "mygo")
public class Refseq2go implements Serializable {
	private static final long serialVersionUID = 7202752969781171628L;
	@Id
	private String code;
	private String refseqid;
	private String gi;
	private String goname;
	private String goacc;
	private String gotype;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getRefseqid() {
		return refseqid;
	}
	public void setRefseqid(String refseqid) {
		this.refseqid = refseqid;
	}
	public String getGi() {
		return gi;
	}
	public void setGi(String gi) {
		this.gi = gi;
	}
	public String getGoname() {
		return goname;
	}
	public void setGoname(String goname) {
		this.goname = goname;
	}
	public String getGoacc() {
		return goacc;
	}
	public void setGoacc(String goacc) {
		this.goacc = goacc;
	}
	public String getGotype() {
		return gotype;
	}
	public void setGotype(String gotype) {
		this.gotype = gotype;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((gi == null) ? 0 : gi.hashCode());
		result = prime * result + ((goacc == null) ? 0 : goacc.hashCode());
		result = prime * result + ((goname == null) ? 0 : goname.hashCode());
		result = prime * result + ((gotype == null) ? 0 : gotype.hashCode());
		result = prime * result
				+ ((refseqid == null) ? 0 : refseqid.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Refseq2go other = (Refseq2go) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (gi == null) {
			if (other.gi != null)
				return false;
		} else if (!gi.equals(other.gi))
			return false;
		if (goacc == null) {
			if (other.goacc != null)
				return false;
		} else if (!goacc.equals(other.goacc))
			return false;
		if (goname == null) {
			if (other.goname != null)
				return false;
		} else if (!goname.equals(other.goname))
			return false;
		if (gotype == null) {
			if (other.gotype != null)
				return false;
		} else if (!gotype.equals(other.gotype))
			return false;
		if (refseqid == null) {
			if (other.refseqid != null)
				return false;
		} else if (!refseqid.equals(other.refseqid))
			return false;
		return true;
	}
}
