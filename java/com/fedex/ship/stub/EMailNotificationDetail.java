/**
 * EMailNotificationDetail.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.fedex.ship.stub;


/**
 * The descriptive data required for FedEx to provide email notification
 * to the customer regarding the shipment.
 */
public class EMailNotificationDetail  implements java.io.Serializable {
    /* Identifies the message text to be sent in the email notification. */
    private java.lang.String personalMessage;

    /* The descriptive data element for the collection of email recipients. */
    private com.fedex.ship.stub.EMailNotificationRecipient[] recipients;

    public EMailNotificationDetail() {
    }

    public EMailNotificationDetail(
           java.lang.String personalMessage,
           com.fedex.ship.stub.EMailNotificationRecipient[] recipients) {
           this.personalMessage = personalMessage;
           this.recipients = recipients;
    }


    /**
     * Gets the personalMessage value for this EMailNotificationDetail.
     * 
     * @return personalMessage   * Identifies the message text to be sent in the email notification.
     */
    public java.lang.String getPersonalMessage() {
        return personalMessage;
    }


    /**
     * Sets the personalMessage value for this EMailNotificationDetail.
     * 
     * @param personalMessage   * Identifies the message text to be sent in the email notification.
     */
    public void setPersonalMessage(java.lang.String personalMessage) {
        this.personalMessage = personalMessage;
    }


    /**
     * Gets the recipients value for this EMailNotificationDetail.
     * 
     * @return recipients   * The descriptive data element for the collection of email recipients.
     */
    public com.fedex.ship.stub.EMailNotificationRecipient[] getRecipients() {
        return recipients;
    }


    /**
     * Sets the recipients value for this EMailNotificationDetail.
     * 
     * @param recipients   * The descriptive data element for the collection of email recipients.
     */
    public void setRecipients(com.fedex.ship.stub.EMailNotificationRecipient[] recipients) {
        this.recipients = recipients;
    }

    public com.fedex.ship.stub.EMailNotificationRecipient getRecipients(int i) {
        return this.recipients[i];
    }

    public void setRecipients(int i, com.fedex.ship.stub.EMailNotificationRecipient _value) {
        this.recipients[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EMailNotificationDetail)) return false;
        EMailNotificationDetail other = (EMailNotificationDetail) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.personalMessage==null && other.getPersonalMessage()==null) || 
             (this.personalMessage!=null &&
              this.personalMessage.equals(other.getPersonalMessage()))) &&
            ((this.recipients==null && other.getRecipients()==null) || 
             (this.recipients!=null &&
              java.util.Arrays.equals(this.recipients, other.getRecipients())));
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
        if (getPersonalMessage() != null) {
            _hashCode += getPersonalMessage().hashCode();
        }
        if (getRecipients() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRecipients());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRecipients(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EMailNotificationDetail.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "EMailNotificationDetail"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("personalMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "PersonalMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recipients");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "Recipients"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "EMailNotificationRecipient"));
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