/**
 * EMailNotificationRecipient.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.fedex.ship.stub;


/**
 * The descriptive data for a FedEx email notification recipient.
 */
public class EMailNotificationRecipient  implements java.io.Serializable {
    /* Identifies the email notification recipient type. See EMailNotificationRecipientType
     * for a list of valid enumerated values. */
    private com.fedex.ship.stub.EMailNotificationRecipientType EMailNotificationRecipientType;

    /* Identifies the email address of the notification recipient. */
    private java.lang.String EMailAddress;

    /* Identifies if an email notification should be sent to the recipient
     * when the package is shipped. */
    private java.lang.Boolean notifyOnShipment;

    /* Identifies if an email notification should be sent to the recipient
     * when an exception occurs during package movement from origin to destination. */
    private java.lang.Boolean notifyOnException;

    /* Identifies if an email notification should be sent to the recipient
     * when the package is delivered. */
    private java.lang.Boolean notifyOnDelivery;

    /* A unique format can be specified for each email address indicated.
     * The format will apply to notification emails sent to a particular
     * email address.. */
    private com.fedex.ship.stub.EMailNotificationFormatType format;

    /* Indicates the language the notification is expressed in. */
    private com.fedex.ship.stub.Localization localization;

    public EMailNotificationRecipient() {
    }

    public EMailNotificationRecipient(
           com.fedex.ship.stub.EMailNotificationRecipientType EMailNotificationRecipientType,
           java.lang.String EMailAddress,
           java.lang.Boolean notifyOnShipment,
           java.lang.Boolean notifyOnException,
           java.lang.Boolean notifyOnDelivery,
           com.fedex.ship.stub.EMailNotificationFormatType format,
           com.fedex.ship.stub.Localization localization) {
           this.EMailNotificationRecipientType = EMailNotificationRecipientType;
           this.EMailAddress = EMailAddress;
           this.notifyOnShipment = notifyOnShipment;
           this.notifyOnException = notifyOnException;
           this.notifyOnDelivery = notifyOnDelivery;
           this.format = format;
           this.localization = localization;
    }


    /**
     * Gets the EMailNotificationRecipientType value for this EMailNotificationRecipient.
     * 
     * @return EMailNotificationRecipientType   * Identifies the email notification recipient type. See EMailNotificationRecipientType
     * for a list of valid enumerated values.
     */
    public com.fedex.ship.stub.EMailNotificationRecipientType getEMailNotificationRecipientType() {
        return EMailNotificationRecipientType;
    }


    /**
     * Sets the EMailNotificationRecipientType value for this EMailNotificationRecipient.
     * 
     * @param EMailNotificationRecipientType   * Identifies the email notification recipient type. See EMailNotificationRecipientType
     * for a list of valid enumerated values.
     */
    public void setEMailNotificationRecipientType(com.fedex.ship.stub.EMailNotificationRecipientType EMailNotificationRecipientType) {
        this.EMailNotificationRecipientType = EMailNotificationRecipientType;
    }


    /**
     * Gets the EMailAddress value for this EMailNotificationRecipient.
     * 
     * @return EMailAddress   * Identifies the email address of the notification recipient.
     */
    public java.lang.String getEMailAddress() {
        return EMailAddress;
    }


    /**
     * Sets the EMailAddress value for this EMailNotificationRecipient.
     * 
     * @param EMailAddress   * Identifies the email address of the notification recipient.
     */
    public void setEMailAddress(java.lang.String EMailAddress) {
        this.EMailAddress = EMailAddress;
    }


    /**
     * Gets the notifyOnShipment value for this EMailNotificationRecipient.
     * 
     * @return notifyOnShipment   * Identifies if an email notification should be sent to the recipient
     * when the package is shipped.
     */
    public java.lang.Boolean getNotifyOnShipment() {
        return notifyOnShipment;
    }


    /**
     * Sets the notifyOnShipment value for this EMailNotificationRecipient.
     * 
     * @param notifyOnShipment   * Identifies if an email notification should be sent to the recipient
     * when the package is shipped.
     */
    public void setNotifyOnShipment(java.lang.Boolean notifyOnShipment) {
        this.notifyOnShipment = notifyOnShipment;
    }


    /**
     * Gets the notifyOnException value for this EMailNotificationRecipient.
     * 
     * @return notifyOnException   * Identifies if an email notification should be sent to the recipient
     * when an exception occurs during package movement from origin to destination.
     */
    public java.lang.Boolean getNotifyOnException() {
        return notifyOnException;
    }


    /**
     * Sets the notifyOnException value for this EMailNotificationRecipient.
     * 
     * @param notifyOnException   * Identifies if an email notification should be sent to the recipient
     * when an exception occurs during package movement from origin to destination.
     */
    public void setNotifyOnException(java.lang.Boolean notifyOnException) {
        this.notifyOnException = notifyOnException;
    }


    /**
     * Gets the notifyOnDelivery value for this EMailNotificationRecipient.
     * 
     * @return notifyOnDelivery   * Identifies if an email notification should be sent to the recipient
     * when the package is delivered.
     */
    public java.lang.Boolean getNotifyOnDelivery() {
        return notifyOnDelivery;
    }


    /**
     * Sets the notifyOnDelivery value for this EMailNotificationRecipient.
     * 
     * @param notifyOnDelivery   * Identifies if an email notification should be sent to the recipient
     * when the package is delivered.
     */
    public void setNotifyOnDelivery(java.lang.Boolean notifyOnDelivery) {
        this.notifyOnDelivery = notifyOnDelivery;
    }


    /**
     * Gets the format value for this EMailNotificationRecipient.
     * 
     * @return format   * A unique format can be specified for each email address indicated.
     * The format will apply to notification emails sent to a particular
     * email address..
     */
    public com.fedex.ship.stub.EMailNotificationFormatType getFormat() {
        return format;
    }


    /**
     * Sets the format value for this EMailNotificationRecipient.
     * 
     * @param format   * A unique format can be specified for each email address indicated.
     * The format will apply to notification emails sent to a particular
     * email address..
     */
    public void setFormat(com.fedex.ship.stub.EMailNotificationFormatType format) {
        this.format = format;
    }


    /**
     * Gets the localization value for this EMailNotificationRecipient.
     * 
     * @return localization   * Indicates the language the notification is expressed in.
     */
    public com.fedex.ship.stub.Localization getLocalization() {
        return localization;
    }


    /**
     * Sets the localization value for this EMailNotificationRecipient.
     * 
     * @param localization   * Indicates the language the notification is expressed in.
     */
    public void setLocalization(com.fedex.ship.stub.Localization localization) {
        this.localization = localization;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EMailNotificationRecipient)) return false;
        EMailNotificationRecipient other = (EMailNotificationRecipient) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.EMailNotificationRecipientType==null && other.getEMailNotificationRecipientType()==null) || 
             (this.EMailNotificationRecipientType!=null &&
              this.EMailNotificationRecipientType.equals(other.getEMailNotificationRecipientType()))) &&
            ((this.EMailAddress==null && other.getEMailAddress()==null) || 
             (this.EMailAddress!=null &&
              this.EMailAddress.equals(other.getEMailAddress()))) &&
            ((this.notifyOnShipment==null && other.getNotifyOnShipment()==null) || 
             (this.notifyOnShipment!=null &&
              this.notifyOnShipment.equals(other.getNotifyOnShipment()))) &&
            ((this.notifyOnException==null && other.getNotifyOnException()==null) || 
             (this.notifyOnException!=null &&
              this.notifyOnException.equals(other.getNotifyOnException()))) &&
            ((this.notifyOnDelivery==null && other.getNotifyOnDelivery()==null) || 
             (this.notifyOnDelivery!=null &&
              this.notifyOnDelivery.equals(other.getNotifyOnDelivery()))) &&
            ((this.format==null && other.getFormat()==null) || 
             (this.format!=null &&
              this.format.equals(other.getFormat()))) &&
            ((this.localization==null && other.getLocalization()==null) || 
             (this.localization!=null &&
              this.localization.equals(other.getLocalization())));
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
        if (getEMailNotificationRecipientType() != null) {
            _hashCode += getEMailNotificationRecipientType().hashCode();
        }
        if (getEMailAddress() != null) {
            _hashCode += getEMailAddress().hashCode();
        }
        if (getNotifyOnShipment() != null) {
            _hashCode += getNotifyOnShipment().hashCode();
        }
        if (getNotifyOnException() != null) {
            _hashCode += getNotifyOnException().hashCode();
        }
        if (getNotifyOnDelivery() != null) {
            _hashCode += getNotifyOnDelivery().hashCode();
        }
        if (getFormat() != null) {
            _hashCode += getFormat().hashCode();
        }
        if (getLocalization() != null) {
            _hashCode += getLocalization().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EMailNotificationRecipient.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "EMailNotificationRecipient"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EMailNotificationRecipientType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "EMailNotificationRecipientType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "EMailNotificationRecipientType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EMailAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "EMailAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("notifyOnShipment");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "NotifyOnShipment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("notifyOnException");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "NotifyOnException"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("notifyOnDelivery");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "NotifyOnDelivery"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("format");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "Format"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "EMailNotificationFormatType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("localization");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "Localization"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "Localization"));
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
