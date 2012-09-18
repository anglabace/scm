/**
 * Party.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.fedex.ship.stub;


/**
 * The descriptive data for a person or company entitiy doing business
 * with FedEx.
 */
public class Party  implements java.io.Serializable {
    /* Identifies the FedEx account number assigned to the customer. */
    private java.lang.String accountNumber;

    /* Descriptive data for taxpayer identification information. */
    private com.fedex.ship.stub.TaxpayerIdentification tin;

    /* Descriptive data identifying the point-of-contact person. */
    private com.fedex.ship.stub.Contact contact;

    /* The descriptive data for a physical location. */
    private com.fedex.ship.stub.Address address;

    public Party() {
    }

    public Party(
           java.lang.String accountNumber,
           com.fedex.ship.stub.TaxpayerIdentification tin,
           com.fedex.ship.stub.Contact contact,
           com.fedex.ship.stub.Address address) {
           this.accountNumber = accountNumber;
           this.tin = tin;
           this.contact = contact;
           this.address = address;
    }


    /**
     * Gets the accountNumber value for this Party.
     * 
     * @return accountNumber   * Identifies the FedEx account number assigned to the customer.
     */
    public java.lang.String getAccountNumber() {
        return accountNumber;
    }


    /**
     * Sets the accountNumber value for this Party.
     * 
     * @param accountNumber   * Identifies the FedEx account number assigned to the customer.
     */
    public void setAccountNumber(java.lang.String accountNumber) {
        this.accountNumber = accountNumber;
    }


    /**
     * Gets the tin value for this Party.
     * 
     * @return tin   * Descriptive data for taxpayer identification information.
     */
    public com.fedex.ship.stub.TaxpayerIdentification getTin() {
        return tin;
    }


    /**
     * Sets the tin value for this Party.
     * 
     * @param tin   * Descriptive data for taxpayer identification information.
     */
    public void setTin(com.fedex.ship.stub.TaxpayerIdentification tin) {
        this.tin = tin;
    }


    /**
     * Gets the contact value for this Party.
     * 
     * @return contact   * Descriptive data identifying the point-of-contact person.
     */
    public com.fedex.ship.stub.Contact getContact() {
        return contact;
    }


    /**
     * Sets the contact value for this Party.
     * 
     * @param contact   * Descriptive data identifying the point-of-contact person.
     */
    public void setContact(com.fedex.ship.stub.Contact contact) {
        this.contact = contact;
    }


    /**
     * Gets the address value for this Party.
     * 
     * @return address   * The descriptive data for a physical location.
     */
    public com.fedex.ship.stub.Address getAddress() {
        return address;
    }


    /**
     * Sets the address value for this Party.
     * 
     * @param address   * The descriptive data for a physical location.
     */
    public void setAddress(com.fedex.ship.stub.Address address) {
        this.address = address;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Party)) return false;
        Party other = (Party) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.accountNumber==null && other.getAccountNumber()==null) || 
             (this.accountNumber!=null &&
              this.accountNumber.equals(other.getAccountNumber()))) &&
            ((this.tin==null && other.getTin()==null) || 
             (this.tin!=null &&
              this.tin.equals(other.getTin()))) &&
            ((this.contact==null && other.getContact()==null) || 
             (this.contact!=null &&
              this.contact.equals(other.getContact()))) &&
            ((this.address==null && other.getAddress()==null) || 
             (this.address!=null &&
              this.address.equals(other.getAddress())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getAccountNumber() != null) {
            _hashCode += getAccountNumber().hashCode();
        }
        if (getTin() != null) {
            _hashCode += getTin().hashCode();
        }
        if (getContact() != null) {
            _hashCode += getContact().hashCode();
        }
        if (getAddress() != null) {
            _hashCode += getAddress().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Party.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "Party"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "AccountNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tin");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "Tin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "TaxpayerIdentification"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contact");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "Contact"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "Contact"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("address");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "Address"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "Address"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
