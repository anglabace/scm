/**
 * DangerousGoodsDetail.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.fedex.ship.stub;


/**
 * The descriptive data required for a FedEx shipment containing dangerous
 * goods (hazardous materials).
 */
public class DangerousGoodsDetail  implements java.io.Serializable {
    /* Identifies whether or not the products being shipped are required
     * to be accessible during delivery. */
    private com.fedex.ship.stub.DangerousGoodsAccessibilityType accessibility;

    /* Shipment is packaged/documented for movement ONLY on cargo
     * aircraft. */
    private java.lang.Boolean cargoAircraftOnly;

    private com.fedex.ship.stub.HazMatCertificateData hazMatCertificateData;

    public DangerousGoodsDetail() {
    }

    public DangerousGoodsDetail(
           com.fedex.ship.stub.DangerousGoodsAccessibilityType accessibility,
           java.lang.Boolean cargoAircraftOnly,
           com.fedex.ship.stub.HazMatCertificateData hazMatCertificateData) {
           this.accessibility = accessibility;
           this.cargoAircraftOnly = cargoAircraftOnly;
           this.hazMatCertificateData = hazMatCertificateData;
    }


    /**
     * Gets the accessibility value for this DangerousGoodsDetail.
     * 
     * @return accessibility   * Identifies whether or not the products being shipped are required
     * to be accessible during delivery.
     */
    public com.fedex.ship.stub.DangerousGoodsAccessibilityType getAccessibility() {
        return accessibility;
    }


    /**
     * Sets the accessibility value for this DangerousGoodsDetail.
     * 
     * @param accessibility   * Identifies whether or not the products being shipped are required
     * to be accessible during delivery.
     */
    public void setAccessibility(com.fedex.ship.stub.DangerousGoodsAccessibilityType accessibility) {
        this.accessibility = accessibility;
    }


    /**
     * Gets the cargoAircraftOnly value for this DangerousGoodsDetail.
     * 
     * @return cargoAircraftOnly   * Shipment is packaged/documented for movement ONLY on cargo
     * aircraft.
     */
    public java.lang.Boolean getCargoAircraftOnly() {
        return cargoAircraftOnly;
    }


    /**
     * Sets the cargoAircraftOnly value for this DangerousGoodsDetail.
     * 
     * @param cargoAircraftOnly   * Shipment is packaged/documented for movement ONLY on cargo
     * aircraft.
     */
    public void setCargoAircraftOnly(java.lang.Boolean cargoAircraftOnly) {
        this.cargoAircraftOnly = cargoAircraftOnly;
    }


    /**
     * Gets the hazMatCertificateData value for this DangerousGoodsDetail.
     * 
     * @return hazMatCertificateData
     */
    public com.fedex.ship.stub.HazMatCertificateData getHazMatCertificateData() {
        return hazMatCertificateData;
    }


    /**
     * Sets the hazMatCertificateData value for this DangerousGoodsDetail.
     * 
     * @param hazMatCertificateData
     */
    public void setHazMatCertificateData(com.fedex.ship.stub.HazMatCertificateData hazMatCertificateData) {
        this.hazMatCertificateData = hazMatCertificateData;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DangerousGoodsDetail)) return false;
        DangerousGoodsDetail other = (DangerousGoodsDetail) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.accessibility==null && other.getAccessibility()==null) || 
             (this.accessibility!=null &&
              this.accessibility.equals(other.getAccessibility()))) &&
            ((this.cargoAircraftOnly==null && other.getCargoAircraftOnly()==null) || 
             (this.cargoAircraftOnly!=null &&
              this.cargoAircraftOnly.equals(other.getCargoAircraftOnly()))) &&
            ((this.hazMatCertificateData==null && other.getHazMatCertificateData()==null) || 
             (this.hazMatCertificateData!=null &&
              this.hazMatCertificateData.equals(other.getHazMatCertificateData())));
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
        if (getAccessibility() != null) {
            _hashCode += getAccessibility().hashCode();
        }
        if (getCargoAircraftOnly() != null) {
            _hashCode += getCargoAircraftOnly().hashCode();
        }
        if (getHazMatCertificateData() != null) {
            _hashCode += getHazMatCertificateData().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DangerousGoodsDetail.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "DangerousGoodsDetail"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accessibility");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "Accessibility"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "DangerousGoodsAccessibilityType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cargoAircraftOnly");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "CargoAircraftOnly"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hazMatCertificateData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "HazMatCertificateData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "HazMatCertificateData"));
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
