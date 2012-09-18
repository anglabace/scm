/**
 * CertificateOfOriginDetail.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.fedex.ship.stub;


/**
 * ?
 */
public class CertificateOfOriginDetail  implements java.io.Serializable {
    /* ? */
    private com.fedex.ship.stub.ShippingDocumentFormat documentFormat;

    /* ? */
    private com.fedex.ship.stub.CustomerImageUsage customerImageUsages;

    public CertificateOfOriginDetail() {
    }

    public CertificateOfOriginDetail(
           com.fedex.ship.stub.ShippingDocumentFormat documentFormat,
           com.fedex.ship.stub.CustomerImageUsage customerImageUsages) {
           this.documentFormat = documentFormat;
           this.customerImageUsages = customerImageUsages;
    }


    /**
     * Gets the documentFormat value for this CertificateOfOriginDetail.
     * 
     * @return documentFormat   * ?
     */
    public com.fedex.ship.stub.ShippingDocumentFormat getDocumentFormat() {
        return documentFormat;
    }


    /**
     * Sets the documentFormat value for this CertificateOfOriginDetail.
     * 
     * @param documentFormat   * ?
     */
    public void setDocumentFormat(com.fedex.ship.stub.ShippingDocumentFormat documentFormat) {
        this.documentFormat = documentFormat;
    }


    /**
     * Gets the customerImageUsages value for this CertificateOfOriginDetail.
     * 
     * @return customerImageUsages   * ?
     */
    public com.fedex.ship.stub.CustomerImageUsage getCustomerImageUsages() {
        return customerImageUsages;
    }


    /**
     * Sets the customerImageUsages value for this CertificateOfOriginDetail.
     * 
     * @param customerImageUsages   * ?
     */
    public void setCustomerImageUsages(com.fedex.ship.stub.CustomerImageUsage customerImageUsages) {
        this.customerImageUsages = customerImageUsages;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CertificateOfOriginDetail)) return false;
        CertificateOfOriginDetail other = (CertificateOfOriginDetail) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.documentFormat==null && other.getDocumentFormat()==null) || 
             (this.documentFormat!=null &&
              this.documentFormat.equals(other.getDocumentFormat()))) &&
            ((this.customerImageUsages==null && other.getCustomerImageUsages()==null) || 
             (this.customerImageUsages!=null &&
              this.customerImageUsages.equals(other.getCustomerImageUsages())));
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
        if (getDocumentFormat() != null) {
            _hashCode += getDocumentFormat().hashCode();
        }
        if (getCustomerImageUsages() != null) {
            _hashCode += getCustomerImageUsages().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CertificateOfOriginDetail.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "CertificateOfOriginDetail"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentFormat");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "DocumentFormat"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "ShippingDocumentFormat"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerImageUsages");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "CustomerImageUsages"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "CustomerImageUsage"));
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
