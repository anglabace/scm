package com.genscript.gsscm.product.entity;

import java.util.List;

import javax.persistence.*;




import com.genscript.core.orm.hibernate.BaseEntity;
/**
 * DOCUMENTS.
 * 
 * 
 * @author mingrs
 */

@Entity
@Table(name = "documents", catalog="product")
public class DocumentPro extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "doc_id")
	private Integer docId;
	private String docName;
	private String version;
	private String docType;
	private String docFileType;
	private String docFileName;
	private String docFilePath;
	private String imageFileType;
	private String imageFileName;
	private String imageFilePath;
	private String description;
	private String note;
	private String internalFlag;
	private String validateFlag;
	private String dispProp;
	public String getOldFlag() {
		return oldFlag;
	}
	public void setOldFlag(String oldFlag) {
		this.oldFlag = oldFlag;
	}
	private String oldFlag;
	
	
	public Integer getDocId() {
		return docId;
	}
	public void setDocId(Integer docId) {
		this.docId = docId;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDispProp() {
		return dispProp;
	}
	public void setDispProp(String dispProp) {
		this.dispProp = dispProp;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getDocFilePath() {
		return docFilePath;
	}
	public void setDocFilePath(String docFilePath) {
		this.docFilePath = docFilePath;
	}
	public String getImageFilePath() {
		return imageFilePath;
	}
	public void setImageFilePath(String imageFilePath) {
		this.imageFilePath = imageFilePath;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getInternalFlag() {
		return internalFlag;
	}
	public void setInternalFlag(String internalFlag) {
		this.internalFlag = internalFlag;
	}
	public String getValidateFlag() {
		return validateFlag;
	}
	public void setValidateFlag(String validateFlag) {
		this.validateFlag = validateFlag;
	}
	public String getDocFileName() {
		return docFileName;
	}
	public void setDocFileName(String docFileName) {
		this.docFileName = docFileName;
	}
	public String getImageFileType() {
		return imageFileType;
	}
	public void setImageFileType(String imageFileType) {
		this.imageFileType = imageFileType;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	public String getDocFileType() {
		return docFileType;
	}
	public void setDocFileType(String docFileType) {
		this.docFileType = docFileType;
	}
	

}
