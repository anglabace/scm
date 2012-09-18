package com.genscript.gsscm.order.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * ORDER ADDRESSES.
 * 
 * 
 * @author Golf
 */

@Entity
@Table(name = "order_addresses", catalog = "order")
public class OrderAddress implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1387713315770654681L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "addr_id")
	private Integer addrId;
	private Integer orderNo;
	private String addrType;
	private String namePfx;
	private String firstName;
	private String midName;
	private String lastName;
	private String nameSfx;
	private String title;
	private Integer deptId;
	private String busPhone;
	private String busPhoneExt;
	private String altPhone;
	private String altPhoneExt;
	private String mobile;
	private String altMobile;
	private String homePhone;
	private String fax;
	private String faxExt;
	private String busEmail;
	private String altBusEmail;
	private String personalEmail;
	private String altPersonalEmail;
	private String addrLine1;
	private String addrLine2;
	private String addrLine3;
	private String city;
	private String state;
	private String zipCode;
	private String country;
	@Column(name = "gift_message")
	private String message;
	private String addrClass;
	@Column(updatable = false)
	protected Date creationDate = new Date();
	@Column(updatable = false)
	protected Integer createdBy;
	protected Date modifyDate = new Date();
	protected Integer modifiedBy;
	
	 //新增
    private String orgName;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof OrderAddress) {
			OrderAddress anotherAddr = (OrderAddress) obj;
			if (StringUtils.isBlank(this.namePfx) && StringUtils.isBlank(anotherAddr.getNamePfx())) {
			} else if (!StringUtils.equals(this.namePfx, anotherAddr.getNamePfx())) {
				return false;
			}
			if (StringUtils.isBlank(this.firstName) && StringUtils.isBlank(anotherAddr.getFirstName())) {
			} else if (!StringUtils.equals(this.firstName, anotherAddr.getFirstName())) {
				return false;
			}
			if (StringUtils.isBlank(this.midName) && StringUtils.isBlank(anotherAddr.getMidName())) {
			} else if (!StringUtils.equals(this.midName, anotherAddr.getMidName())) {
				return false;
			}
			if (StringUtils.isBlank(this.lastName) && StringUtils.isBlank(anotherAddr.getLastName())) {
			} else if (!StringUtils.equals(this.lastName, anotherAddr.getLastName())) {
				return false;
			}
			if (StringUtils.isBlank(this.nameSfx) && StringUtils.isBlank(anotherAddr.getNameSfx())) {
			} else if (!StringUtils.equals(this.nameSfx, anotherAddr.getNameSfx())) {
				return false;
			}
			if (StringUtils.isBlank(this.title) && StringUtils.isBlank(anotherAddr.getTitle())) {
			} else if (!StringUtils.equals(this.title, anotherAddr.getTitle())) {
				return false;
			}
			if (StringUtils.isBlank(this.orgName) && StringUtils.isBlank(anotherAddr.getOrgName())) {
			} else if (!StringUtils.equals(this.orgName, anotherAddr.getOrgName())) {
				return false;
			}
			if (StringUtils.isBlank(this.busPhone) && StringUtils.isBlank(anotherAddr.getBusPhone())) {
			} else if (!StringUtils.equals(this.busPhone, anotherAddr.getBusPhone())) {
				return false;
			}
			if (StringUtils.isBlank(this.busPhoneExt) && StringUtils.isBlank(anotherAddr.getBusPhoneExt())) {
			} else if (!StringUtils.equals(this.busPhoneExt, anotherAddr.getBusPhoneExt())) {
				return false;
			}
			if (StringUtils.isBlank(this.altPhone) && StringUtils.isBlank(anotherAddr.getAltPhone())) {
			} else if (!StringUtils.equals(this.altPhone, anotherAddr.getAltPhone())) {
				return false;
			}
			if (StringUtils.isBlank(this.altPhoneExt) && StringUtils.isBlank(anotherAddr.getAltPhoneExt())) {
			} else if (!StringUtils.equals(this.altPhoneExt, anotherAddr.getAltPhoneExt())) {
				return false;
			}
			if (StringUtils.isBlank(this.mobile) && StringUtils.isBlank(anotherAddr.getMobile())) {
			} else if (!StringUtils.equals(this.mobile, anotherAddr.getMobile())) {
				return false;
			}
			if (StringUtils.isBlank(this.altMobile) && StringUtils.isBlank(anotherAddr.getAltMobile())) {
			} else if (!StringUtils.equals(this.altMobile, anotherAddr.getAltMobile())) {
				return false;
			}
			if (StringUtils.isBlank(this.homePhone) && StringUtils.isBlank(anotherAddr.getHomePhone())) {
			} else if (!StringUtils.equals(this.homePhone, anotherAddr.getHomePhone())) {
				return false;
			}
			if (StringUtils.isBlank(this.fax) && StringUtils.isBlank(anotherAddr.getFax())) {
			} else if (!StringUtils.equals(this.fax, anotherAddr.getFax())) {
				return false;
			}
			if (StringUtils.isBlank(this.faxExt) && StringUtils.isBlank(anotherAddr.getFaxExt())) {
			} else if (!StringUtils.equals(this.faxExt, anotherAddr.getFaxExt())) {
				return false;
			}
			if (StringUtils.isBlank(this.busEmail) && StringUtils.isBlank(anotherAddr.getBusEmail())) {
			} else if (!StringUtils.equals(this.busEmail, anotherAddr.getBusEmail())) {
				return false;
			}
			if (StringUtils.isBlank(this.altBusEmail) && StringUtils.isBlank(anotherAddr.getAltBusEmail())) {
			} else if (!StringUtils.equals(this.altBusEmail, anotherAddr.getAltBusEmail())) {
				return false;
			}
			if (StringUtils.isBlank(this.personalEmail) && StringUtils.isBlank(anotherAddr.getPersonalEmail())) {
			} else if (!StringUtils.equals(this.personalEmail, anotherAddr.getPersonalEmail())) {
				return false;
			}
			if (StringUtils.isBlank(this.altPersonalEmail) && StringUtils.isBlank(anotherAddr.getAltPersonalEmail())) {
			} else if (!StringUtils.equals(this.altPersonalEmail, anotherAddr.getAltPersonalEmail())) {
				return false;
			}
			if (StringUtils.isBlank(this.addrLine1) && StringUtils.isBlank(anotherAddr.getAddrLine1())) {
			} else if (!StringUtils.equals(this.addrLine1, anotherAddr.getAddrLine1())) {
				return false;
			}
			if (StringUtils.isBlank(this.addrLine2) && StringUtils.isBlank(anotherAddr.getAddrLine2())) {
			} else if (!StringUtils.equals(this.addrLine2, anotherAddr.getAddrLine2())) {
				return false;
			}
			if (StringUtils.isBlank(this.addrLine3) && StringUtils.isBlank(anotherAddr.getAddrLine3())) {
			} else if (!StringUtils.equals(this.addrLine3, anotherAddr.getAddrLine3())) {
				return false;
			}
			if (StringUtils.isBlank(this.city) && StringUtils.isBlank(anotherAddr.getCity())) {
			} else if (!StringUtils.equals(this.city, anotherAddr.getCity())) {
				return false;
			}
			if (StringUtils.isBlank(this.state) && StringUtils.isBlank(anotherAddr.getState())) {
			} else if (!StringUtils.equals(this.state, anotherAddr.getState())) {
				return false;
			}
			if (StringUtils.isBlank(this.zipCode) && StringUtils.isBlank(anotherAddr.getZipCode())) {
			} else if (!StringUtils.equals(this.zipCode, anotherAddr.getZipCode())) {
				return false;
			}
			if (StringUtils.isBlank(this.country) && StringUtils.isBlank(anotherAddr.getCountry())) {
			} else if (!StringUtils.equals(this.country, anotherAddr.getCountry())) {
				return false;
			}
			if (StringUtils.isBlank(this.message) && StringUtils.isBlank(anotherAddr.getMessage())) {
			} else if (!StringUtils.equals(this.message, anotherAddr.getMessage())) {
				return false;
			}
			if (StringUtils.isBlank(this.addrClass) && StringUtils.isBlank(anotherAddr.getAddrClass())) {
			} else if (!StringUtils.equals(this.addrClass, anotherAddr.getAddrClass())) {
				return false;
			}
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 17;
		if (this.firstName != null) {
			result = this.firstName.length() * 31;
		}
		if (this.midName != null) {
			result = this.midName.length() * 31;
		}
		if (this.lastName != null) {
			result = this.lastName.length() * 31;
		}
		if (this.state != null) {
			result = this.state.length() * 31;
		}
		if (this.country != null) {
			result = this.country.length() * 31;
		}
		return result;
	}

	public Integer getAddrId() {
		return addrId;
	}

	public void setAddrId(Integer addrId) {
		this.addrId = addrId;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public String getAddrType() {
		return addrType;
	}

	public void setAddrType(String addrType) {
		this.addrType = addrType;
	}

	public String getNamePfx() {
		return namePfx;
	}

	public void setNamePfx(String namePfx) {
		this.namePfx = namePfx;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMidName() {
		return midName;
	}

	public void setMidName(String midName) {
		this.midName = midName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNameSfx() {
		return nameSfx;
	}

	public void setNameSfx(String nameSfx) {
		this.nameSfx = nameSfx;
	}

	public String getBusPhone() {
		return busPhone;
	}

	public void setBusPhone(String busPhone) {
		this.busPhone = busPhone;
	}

	public String getBusPhoneExt() {
		return busPhoneExt;
	}

	public void setBusPhoneExt(String busPhoneExt) {
		this.busPhoneExt = busPhoneExt;
	}

	public String getAltPhone() {
		return altPhone;
	}

	public void setAltPhone(String altPhone) {
		this.altPhone = altPhone;
	}

	public String getAltPhoneExt() {
		return altPhoneExt;
	}

	public void setAltPhoneExt(String altPhoneExt) {
		this.altPhoneExt = altPhoneExt;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAltMobile() {
		return altMobile;
	}

	public void setAltMobile(String altMobile) {
		this.altMobile = altMobile;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getFaxExt() {
		return faxExt;
	}

	public void setFaxExt(String faxExt) {
		this.faxExt = faxExt;
	}

	public String getBusEmail() {
		return busEmail;
	}

	public void setBusEmail(String busEmail) {
		this.busEmail = busEmail;
	}

	public String getAltBusEmail() {
		return altBusEmail;
	}

	public void setAltBusEmail(String altBusEmail) {
		this.altBusEmail = altBusEmail;
	}

	public String getPersonalEmail() {
		return personalEmail;
	}

	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}

	public String getAltPersonalEmail() {
		return altPersonalEmail;
	}

	public void setAltPersonalEmail(String altPersonalEmail) {
		this.altPersonalEmail = altPersonalEmail;
	}

	public String getAddrLine1() {
		return addrLine1;
	}

	public void setAddrLine1(String addrLine1) {
		this.addrLine1 = addrLine1;
	}

	public String getAddrLine2() {
		return addrLine2;
	}

	public void setAddrLine2(String addrLine2) {
		this.addrLine2 = addrLine2;
	}

	public String getAddrLine3() {
		return addrLine3;
	}

	public void setAddrLine3(String addrLine3) {
		this.addrLine3 = addrLine3;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate
	 *            the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the createdBy
	 */
	public Integer getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the modifyDate
	 */
	public Date getModifyDate() {
		return modifyDate;
	}

	/**
	 * @param modifyDate
	 *            the modifyDate to set
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	/**
	 * @return the modifiedBy
	 */
	public Integer getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy
	 *            the modifiedBy to set
	 */
	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * @return the addrClass
	 */
	public String getAddrClass() {
		return addrClass;
	}

	/**
	 * @param addrClass
	 *            the addrClass to set
	 */
	public void setAddrClass(String addrClass) {
		this.addrClass = addrClass;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	
	
}
