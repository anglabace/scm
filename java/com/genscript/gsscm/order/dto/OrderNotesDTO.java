package com.genscript.gsscm.order.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.order.entity.OrderMain;
import com.genscript.gsscm.privilege.entity.User;

@XmlType(name = "OrderNotesDTO", namespace = WsConstants.NS)
public class OrderNotesDTO {

	private Integer noteId;
	private OrderMain order;
	private Date noteDate;
	private String type;
	private String source;
	private String description;
	private String docFlag;
	private Date creationDate;
	private User createdBy;
	private Date modifyDate;
	private User modifiedBy;
	private Integer orderNo;
    private String orderNote;
    private String customerNote;
    private String departmentNote;
    private String divistionNote;
    private String orgNote;
    private Integer orgId;
    private Integer divisionId;
    private Integer deptId;
    //add by zhanghuibin
    private String noteIds;
    //mingrs 用于session key
    private String noteKey;

    public String getNoteIds() {
        return noteIds;
    }

    public void setNoteIds(String noteIds) {
        this.noteIds = noteIds;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(Integer divisionId) {
        this.divisionId = divisionId;
    }

    public String getOrderNote() {
        return orderNote;
    }

    public void setOrderNote(String orderNote) {
        this.orderNote = orderNote;
    }

    public String getCustomerNote() {
        return customerNote;
    }

    public void setCustomerNote(String customerNote) {
        this.customerNote = customerNote;
    }

    public String getDepartmentNote() {
        return departmentNote;
    }

    public void setDepartmentNote(String departmentNote) {
        this.departmentNote = departmentNote;
    }

    public String getDivistionNote() {
        return divistionNote;
    }

    public void setDivistionNote(String divistionNote) {
        this.divistionNote = divistionNote;
    }

    public String getOrgNote() {
        return orgNote;
    }

    public void setOrgNote(String orgNote) {
        this.orgNote = orgNote;
    }

    public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getNoteId() {
		return noteId;
	}
	public void setNoteId(Integer noteId) {
		this.noteId = noteId;
	}
	public OrderMain getOrder() {
		return order;
	}
	public void setOrder(OrderMain order) {
		this.order = order;
	}
	public Date getNoteDate() {
		return noteDate;
	}
	public void setNoteDate(Date noteDate) {
		this.noteDate = noteDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDocFlag() {
		return docFlag;
	}
	public void setDocFlag(String docFlag) {
		this.docFlag = docFlag;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public User getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public User getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(User modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getNoteKey() {
		return noteKey;
	}

	public void setNoteKey(String noteKey) {
		this.noteKey = noteKey;
	}
	
	
}
