package com.genscript.gsscm.manufacture.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * OperationResource.
 * 
 * cascade表示级联操作   
 * CascadeType.MERGE级联更新：若items属性修改了那么order对象保存时同时修改items里的对象。对应EntityManager的merge方法   
 * CascadeType.PERSIST级联刷新：获取order对象里也同时也重新获取最新的items时的对象。对应EntityManager的refresh(object)方法有效。即会重新查询数据库里的最新数据   
 * CascadeType.REFRESH级联保存：对order对象保存时也对items里的对象也会保存。对应EntityManager的presist方法   
 * CascadeType.REMOVE级联删除：对order对象删除也对items里的对象也会删除。对应EntityManager的remove方法   
 * FetchType.LAZY表示懒加载。对于xxxtoMany时即获得多的一方fetch的默认值是FetchType.LAZY懒加载。对于xxxtoOne时即获得一的一方fetch的默认值是FetchType.EAGER立即加载   
 * 
 * @author Wangsf.
 */

@Entity
@Table(name = "operation_resources", catalog = "manufacturing")
public class OperationResource extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2016643701390627231L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JoinColumn(name = "resourceId", referencedColumnName = "resourceId")
	@OneToOne(cascade = CascadeType.PERSIST, fetch=FetchType.LAZY)
	private Resource resource;
	private Integer operationId;
	private Integer seqNo;
	private BigDecimal quantity;
	private Integer workGroupId;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public Integer getOperationId() {
		return operationId;
	}

	public void setOperationId(Integer operationId) {
		this.operationId = operationId;
	}

	public Integer getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public Integer getWorkGroupId() {
		return workGroupId;
	}

	public void setWorkGroupId(Integer workGroupId) {
		this.workGroupId = workGroupId;
	}
}
