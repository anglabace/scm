package com.genscript.gsscm.customer.dto;

import com.genscript.gsscm.common.SessionBaseDTO;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.Date;

/**
 * Created zhougang
 * User: zhougang
 * Date: 4/26/11
 * Time: 11:07 AM
 * show CustomerActivity on customer_view_activity.jsp
 */
public class CustomerActivityDTO extends SessionBaseDTO {
    private  Integer customNo;

    private Integer orderNo;
    private String status;
    private Date orderDate;
    private String soldby;
    private String enteredby;

    private Date dateTime;
    private String contactPerson;
    private String contactBy;

    private String lastEmailSent;
    private String lastEmailSubject;

    private String lastFaxSent;
    private String lasFaxSubject;
    private String lastMailingSent;
    private String lastMailingSubject;

    private Date lastModifiedDate;
    private String lastModifiedBy;



    public Integer getCustomNo() {
        return customNo;
    }

    public void setCustomNo(Integer customNo) {
        this.customNo = customNo;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getSoldby() {
        return soldby;
    }

    public void setSoldby(String soldby) {
        this.soldby = soldby;
    }

    public String getEnteredby() {
        return enteredby;
    }

    public void setEnteredby(String enteredby) {
        this.enteredby = enteredby;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactBy() {
        return contactBy;
    }

    public void setContactBy(String contactBy) {
        this.contactBy = contactBy;
    }

    public String getLastEmailSent() {
        return lastEmailSent;
    }

    public void setLastEmailSent(String lastEmailSent) {
        this.lastEmailSent = lastEmailSent;
    }

    public String getLastEmailSubject() {
        return lastEmailSubject;
    }

    public void setLastEmailSubject(String lastEmailSubject) {
        this.lastEmailSubject = lastEmailSubject;
    }

    public String getLastFaxSent() {
        return lastFaxSent;
    }

    public void setLastFaxSent(String lastFaxSent) {
        this.lastFaxSent = lastFaxSent;
    }

    public String getLasFaxSubject() {
        return lasFaxSubject;
    }

    public void setLasFaxSubject(String lasFaxSubject) {
        this.lasFaxSubject = lasFaxSubject;
    }

    public String getLastMailingSent() {
        return lastMailingSent;
    }

    public void setLastMailingSent(String lastMailingSent) {
        this.lastMailingSent = lastMailingSent;
    }

    public String getLastMailingSubject() {
        return lastMailingSubject;
    }

    public void setLastMailingSubject(String lastMailingSubject) {
        this.lastMailingSubject = lastMailingSubject;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
