package com.genscript.gsscm.manufacture.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * ManuDocument.
 * 
 * 
 * @author Wangsf
 */

@Entity
@Table(name = "documents", catalog="manufacturing")
public class ManuDocument extends BaseEntity implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5508621628356009401L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer docId;
	private String refType;
	private Integer refId;
	private String docName;
	private String docType;
	private String fileType;
	private String filePath;
	private String description;
	@Transient
	private String modifyUser;
	@Transient
	private String isOldProdctFile;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}	
	
	public Integer getDocId() {
		return docId;
	}
	public void setDocId(Integer docId) {
		this.docId = docId;
	}
	public String getRefType() {
		return refType;
	}
	public void setRefType(String refType) {
		this.refType = refType;
	}
	public Integer getRefId() {
		return refId;
	}
	public void setRefId(Integer refId) {
		this.refId = refId;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public String getIsOldProdctFile() {
		return isOldProdctFile;
	}

	public void setIsOldProdctFile(String isOldProdctFile) {
		this.isOldProdctFile = isOldProdctFile;
	}
	
	
}
