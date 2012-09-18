package com.genscript.gsscm.quote.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * QUOTATION ADDRESSES.
 * 
 * 
 * @author Golf
 */

@Entity
@Table(name = "quote_addresses", catalog="order")
public class QuoteAddress extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "addr_id")
	private Integer addrId;
	private Integer quoteNo;
	private String addrType;
	private String namePfx;
	private String firstName;
	private String midName;
	private String lastName;
	private String nameSfx;
	private String title;
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
	 //新增
    private String orgName;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof QuoteAddress) {
			QuoteAddress anotherAddr = (QuoteAddress) obj;
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
	
	/**
	 * @return the addrId
	 */
	public Integer getAddrId() {
		return addrId;
	}
	/**
	 * @param addrId the addrId to set
	 */
	public void setAddrId(Integer addrId) {
		this.addrId = addrId;
	}
	/**
	 * @return the addrType
	 */
	public String getAddrType() {
		return addrType;
	}
	/**
	 * @param addrType the addrType to set
	 */
	public void setAddrType(String addrType) {
		this.addrType = addrType;
	}
	/**
	 * @return the namePfx
	 */
	public String getNamePfx() {
		return namePfx;
	}
	/**
	 * @param namePfx the namePfx to set
	 */
	public void setNamePfx(String namePfx) {
		this.namePfx = namePfx;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the midName
	 */
	public String getMidName() {
		return midName;
	}
	/**
	 * @param midName the midName to set
	 */
	public void setMidName(String midName) {
		this.midName = midName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the nameSfx
	 */
	public String getNameSfx() {
		return nameSfx;
	}
	/**
	 * @param nameSfx the nameSfx to set
	 */
	public void setNameSfx(String nameSfx) {
		this.nameSfx = nameSfx;
	}
	/**
	 * @return the busPhone
	 */
	public String getBusPhone() {
		return busPhone;
	}
	/**
	 * @param busPhone the busPhone to set
	 */
	public void setBusPhone(String busPhone) {
		this.busPhone = busPhone;
	}
	/**
	 * @return the busPhoneExt
	 */
	public String getBusPhoneExt() {
		return busPhoneExt;
	}
	/**
	 * @param busPhoneExt the busPhoneExt to set
	 */
	public void setBusPhoneExt(String busPhoneExt) {
		this.busPhoneExt = busPhoneExt;
	}
	/**
	 * @return the altPhone
	 */
	public String getAltPhone() {
		return altPhone;
	}
	/**
	 * @param altPhone the altPhone to set
	 */
	public void setAltPhone(String altPhone) {
		this.altPhone = altPhone;
	}
	/**
	 * @return the altPhoneExt
	 */
	public String getAltPhoneExt() {
		return altPhoneExt;
	}
	/**
	 * @param altPhoneExt the altPhoneExt to set
	 */
	public void setAltPhoneExt(String altPhoneExt) {
		this.altPhoneExt = altPhoneExt;
	}
	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * @return the altMobile
	 */
	public String getAltMobile() {
		return altMobile;
	}
	/**
	 * @param altMobile the altMobile to set
	 */
	public void setAltMobile(String altMobile) {
		this.altMobile = altMobile;
	}
	/**
	 * @return the homePhone
	 */
	public String getHomePhone() {
		return homePhone;
	}
	/**
	 * @param homePhone the homePhone to set
	 */
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}
	/**
	 * @param fax the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}
	/**
	 * @return the faxExt
	 */
	public String getFaxExt() {
		return faxExt;
	}
	/**
	 * @param faxExt the faxExt to set
	 */
	public void setFaxExt(String faxExt) {
		this.faxExt = faxExt;
	}
	/**
	 * @return the busEmail
	 */
	public String getBusEmail() {
		return busEmail;
	}
	/**
	 * @param busEmail the busEmail to set
	 */
	public void setBusEmail(String busEmail) {
		this.busEmail = busEmail;
	}
	/**
	 * @return the altBusEmail
	 */
	public String getAltBusEmail() {
		return altBusEmail;
	}
	/**
	 * @param altBusEmail the altBusEmail to set
	 */
	public void setAltBusEmail(String altBusEmail) {
		this.altBusEmail = altBusEmail;
	}
	/**
	 * @return the personalEmail
	 */
	public String getPersonalEmail() {
		return personalEmail;
	}
	/**
	 * @param personalEmail the personalEmail to set
	 */
	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}
	/**
	 * @return the altPersonalEmail
	 */
	public String getAltPersonalEmail() {
		return altPersonalEmail;
	}
	/**
	 * @param altPersonalEmail the altPersonalEmail to set
	 */
	public void setAltPersonalEmail(String altPersonalEmail) {
		this.altPersonalEmail = altPersonalEmail;
	}
	/**
	 * @return the addrLine1
	 */
	public String getAddrLine1() {
		return addrLine1;
	}
	/**
	 * @param addrLine1 the addrLine1 to set
	 */
	public void setAddrLine1(String addrLine1) {
		this.addrLine1 = addrLine1;
	}
	/**
	 * @return the addrLine2
	 */
	public String getAddrLine2() {
		return addrLine2;
	}
	/**
	 * @param addrLine2 the addrLine2 to set
	 */
	public void setAddrLine2(String addrLine2) {
		this.addrLine2 = addrLine2;
	}
	/**
	 * @return the addrLine3
	 */
	public String getAddrLine3() {
		return addrLine3;
	}
	/**
	 * @param addrLine3 the addrLine3 to set
	 */
	public void setAddrLine3(String addrLine3) {
		this.addrLine3 = addrLine3;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}
	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	/**
	 * @return the organization
	 */
 
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the quoteNo
	 */
	public Integer getQuoteNo() {
		return quoteNo;
	}
	/**
	 * @param quoteNo the quoteNo to set
	 */
	public void setQuoteNo(Integer quoteNo) {
		this.quoteNo = quoteNo;
	}
	/**
	 * @return the addrClass
	 */
	public String getAddrClass() {
		return addrClass;
	}
	/**
	 * @param addrClass the addrClass to set
	 */
	public void setAddrClass(String addrClass) {
		this.addrClass = addrClass;
	}
	
}
