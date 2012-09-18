package com.genscript.gsscm.product.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.product.entity.Documents;
import com.genscript.gsscm.product.entity.Product;

@XmlType(name = "DocumentsDTO", namespace = WsConstants.NS)
public class DocumentsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 45733841252818432L;
	
	private Documents documents;
	private List<Product> productId;
	private String modifiedByName;
	
	public DocumentsDTO(){
		documents = new Documents();
		productId = new ArrayList<Product>();
	}
	public Documents getDocuments() {
		return documents;
	}
	public void setDocuments(Documents documents) {
		this.documents = documents;
	}
	public List<Product> getProductId() {
		return productId;
	}
	public void setProductId(List<Product> productId) {
		this.productId = productId;
	}
	public String getModifiedByName() {
		return modifiedByName;
	}
	public void setModifiedByName(String modifiedByName) {
		this.modifiedByName = modifiedByName;
	}
	
}
