/**
 * ShippingDocument.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.fedex.ship.stub;


/**
 * All package-level shipping documents (other than labels and barcodes).
 */
public class ShippingDocument  implements java.io.Serializable {
    /* Shipping Document Type */
    private com.fedex.ship.stub.ReturnedShippingDocumentType type;

    /* Number of copies to print. */
    private org.apache.axis.types.PositiveInteger copiesToPrint;

    /* One or more document parts which make up a single logical document,
     * such as
     * 												multiple pages of a single form. */
    private com.fedex.ship.stub.ShippingDocumentPart[] parts;

    public ShippingDocument() {
    }

    public ShippingDocument(
           com.fedex.ship.stub.ReturnedShippingDocumentType type,
           org.apache.axis.types.PositiveInteger copiesToPrint,
           com.fedex.ship.stub.ShippingDocumentPart[] parts) {
           this.type = type;
           this.copiesToPrint = copiesToPrint;
           this.parts = parts;
    }


    /**
     * Gets the type value for this ShippingDocument.
     * 
     * @return type   * Shipping Document Type
     */
    public com.fedex.ship.stub.ReturnedShippingDocumentType getType() {
        return type;
    }


    /**
     * Sets the type value for this ShippingDocument.
     * 
     * @param type   * Shipping Document Type
     */
    public void setType(com.fedex.ship.stub.ReturnedShippingDocumentType type) {
        this.type = type;
    }


    /**
     * Gets the copiesToPrint value for this ShippingDocument.
     * 
     * @return copiesToPrint   * Number of copies to print.
     */
    public org.apache.axis.types.PositiveInteger getCopiesToPrint() {
        return copiesToPrint;
    }


    /**
     * Sets the copiesToPrint value for this ShippingDocument.
     * 
     * @param copiesToPrint   * Number of copies to print.
     */
    public void setCopiesToPrint(org.apache.axis.types.PositiveInteger copiesToPrint) {
        this.copiesToPrint = copiesToPrint;
    }


    /**
     * Gets the parts value for this ShippingDocument.
     * 
     * @return parts   * One or more document parts which make up a single logical document,
     * such as
     * 												multiple pages of a single form.
     */
    public com.fedex.ship.stub.ShippingDocumentPart[] getParts() {
        return parts;
    }


    /**
     * Sets the parts value for this ShippingDocument.
     * 
     * @param parts   * One or more document parts which make up a single logical document,
     * such as
     * 												multiple pages of a single form.
     */
    public void setParts(com.fedex.ship.stub.ShippingDocumentPart[] parts) {
        this.parts = parts;
    }

    public com.fedex.ship.stub.ShippingDocumentPart getParts(int i) {
        return this.parts[i];
    }

    public void setParts(int i, com.fedex.ship.stub.ShippingDocumentPart _value) {
        this.parts[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ShippingDocument)) return false;
        ShippingDocument other = (ShippingDocument) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              this.type.equals(other.getType()))) &&
            ((this.copiesToPrint==null && other.getCopiesToPrint()==null) || 
             (this.copiesToPrint!=null &&
              this.copiesToPrint.equals(other.getCopiesToPrint()))) &&
            ((this.parts==null && other.getParts()==null) || 
             (this.parts!=null &&
              java.util.Arrays.equals(this.parts, other.getParts())));
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
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        if (getCopiesToPrint() != null) {
            _hashCode += getCopiesToPrint().hashCode();
        }
        if (getParts() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getParts());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getParts(), i);
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
        new org.apache.axis.description.TypeDesc(ShippingDocument.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "ShippingDocument"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "Type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "ReturnedShippingDocumentType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("copiesToPrint");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "CopiesToPrint"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "positiveInteger"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parts");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "Parts"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "ShippingDocumentPart"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
