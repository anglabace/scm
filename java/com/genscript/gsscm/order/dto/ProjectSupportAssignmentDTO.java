package com.genscript.gsscm.order.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.common.constant.WsConstants;

/**
 * @author zhangyong
 */
@XmlType(name = "ProjectSupportAssignmentDTO", namespace = WsConstants.NS)
public class ProjectSupportAssignmentDTO implements Serializable {

	private static final long serialVersionUID = -4177944214383739878L;
	private Integer id;
	private String serviceTypeName;
	private String projectSupport;
	private String comment;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getServiceTypeName() {
		return serviceTypeName;
	}
	public void setServiceTypeName(String serviceTypeName) {
		this.serviceTypeName = serviceTypeName;
	}
	public String getProjectSupport() {
		return projectSupport;
	}
	public void setProjectSupport(String projectSupport) {
		this.projectSupport = projectSupport;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
