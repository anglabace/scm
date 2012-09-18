/**
 * ReturnedShippingDocumentType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.fedex.ship.stub;

public class ReturnedShippingDocumentType implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected ReturnedShippingDocumentType(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _AUXILIARY_LABEL = "AUXILIARY_LABEL";
    public static final java.lang.String _COD_RETURN_LABEL = "COD_RETURN_LABEL";
    public static final java.lang.String _GENERAL_AGENCY_AGREEMENT_FORM = "GENERAL_AGENCY_AGREEMENT_FORM";
    public static final java.lang.String _GENERAL_AGENCY_AGREEMENT_INSTRUCTIONS = "GENERAL_AGENCY_AGREEMENT_INSTRUCTIONS";
    public static final java.lang.String _NAFTA_CERTIFICATE_OF_ORIGIN_FORM = "NAFTA_CERTIFICATE_OF_ORIGIN_FORM";
    public static final java.lang.String _NAFTA_CERTIFICATE_OF_ORIGIN_INSTRUCTIONS = "NAFTA_CERTIFICATE_OF_ORIGIN_INSTRUCTIONS";
    public static final java.lang.String _OUTBOUND_LABEL = "OUTBOUND_LABEL";
    public static final java.lang.String _TERMS_AND_CONDITIONS = "TERMS_AND_CONDITIONS";
    public static final java.lang.String _CERTIFICATE_OF_ORIGIN_FORM = "CERTIFICATE_OF_ORIGIN_FORM";
    public static final java.lang.String _COD_RETURN_2_D_BARCODE = "COD_RETURN_2_D_BARCODE";
    public static final java.lang.String _ETD_LABEL = "ETD_LABEL";
    public static final java.lang.String _GROUND_BARCODE = "GROUND_BARCODE";
    public static final java.lang.String _OUTBOUND_2_D_BARCODE = "OUTBOUND_2_D_BARCODE";
    public static final java.lang.String _PRO_FORMA_INVOICE_FORM = "PRO_FORMA_INVOICE_FORM";
    public static final java.lang.String _RETURN_INSTRUCTIONS = "RETURN_INSTRUCTIONS";
    public static final java.lang.String _USPS_BARCODE = "USPS_BARCODE";
    public static final ReturnedShippingDocumentType AUXILIARY_LABEL = new ReturnedShippingDocumentType(_AUXILIARY_LABEL);
    public static final ReturnedShippingDocumentType COD_RETURN_LABEL = new ReturnedShippingDocumentType(_COD_RETURN_LABEL);
    public static final ReturnedShippingDocumentType GENERAL_AGENCY_AGREEMENT_FORM = new ReturnedShippingDocumentType(_GENERAL_AGENCY_AGREEMENT_FORM);
    public static final ReturnedShippingDocumentType GENERAL_AGENCY_AGREEMENT_INSTRUCTIONS = new ReturnedShippingDocumentType(_GENERAL_AGENCY_AGREEMENT_INSTRUCTIONS);
    public static final ReturnedShippingDocumentType NAFTA_CERTIFICATE_OF_ORIGIN_FORM = new ReturnedShippingDocumentType(_NAFTA_CERTIFICATE_OF_ORIGIN_FORM);
    public static final ReturnedShippingDocumentType NAFTA_CERTIFICATE_OF_ORIGIN_INSTRUCTIONS = new ReturnedShippingDocumentType(_NAFTA_CERTIFICATE_OF_ORIGIN_INSTRUCTIONS);
    public static final ReturnedShippingDocumentType OUTBOUND_LABEL = new ReturnedShippingDocumentType(_OUTBOUND_LABEL);
    public static final ReturnedShippingDocumentType TERMS_AND_CONDITIONS = new ReturnedShippingDocumentType(_TERMS_AND_CONDITIONS);
    public static final ReturnedShippingDocumentType CERTIFICATE_OF_ORIGIN_FORM = new ReturnedShippingDocumentType(_CERTIFICATE_OF_ORIGIN_FORM);
    public static final ReturnedShippingDocumentType COD_RETURN_2_D_BARCODE = new ReturnedShippingDocumentType(_COD_RETURN_2_D_BARCODE);
    public static final ReturnedShippingDocumentType ETD_LABEL = new ReturnedShippingDocumentType(_ETD_LABEL);
    public static final ReturnedShippingDocumentType GROUND_BARCODE = new ReturnedShippingDocumentType(_GROUND_BARCODE);
    public static final ReturnedShippingDocumentType OUTBOUND_2_D_BARCODE = new ReturnedShippingDocumentType(_OUTBOUND_2_D_BARCODE);
    public static final ReturnedShippingDocumentType PRO_FORMA_INVOICE_FORM = new ReturnedShippingDocumentType(_PRO_FORMA_INVOICE_FORM);
    public static final ReturnedShippingDocumentType RETURN_INSTRUCTIONS = new ReturnedShippingDocumentType(_RETURN_INSTRUCTIONS);
    public static final ReturnedShippingDocumentType USPS_BARCODE = new ReturnedShippingDocumentType(_USPS_BARCODE);
    public java.lang.String getValue() { return _value_;}
    public static ReturnedShippingDocumentType fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        ReturnedShippingDocumentType enumeration = (ReturnedShippingDocumentType)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static ReturnedShippingDocumentType fromString(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        return fromValue(value);
    }
    public boolean equals(java.lang.Object obj) {return (obj == this);}
    public int hashCode() { return toString().hashCode();}
    public java.lang.String toString() { return _value_;}
    public java.lang.Object readResolve() throws java.io.ObjectStreamException { return fromValue(_value_);}
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumSerializer(
            _javaType, _xmlType);
    }
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumDeserializer(
            _javaType, _xmlType);
    }
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReturnedShippingDocumentType.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "ReturnedShippingDocumentType"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
