/**
 * RoutingAstraDetail.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.fedex.ship.stub;


/**
 * The tracking number information and the data to form the Astra
 * barcode for the label.
 */
public class RoutingAstraDetail  implements java.io.Serializable {
    /* The tracking number information for the shipment. */
    private com.fedex.ship.stub.TrackingId trackingId;

    private java.lang.String astraBarcode;

    /* The textual description of the special service applied to the
     * package. */
    private java.lang.String astraHandlingText;

    public RoutingAstraDetail() {
    }

    public RoutingAstraDetail(
           com.fedex.ship.stub.TrackingId trackingId,
           java.lang.String astraBarcode,
           java.lang.String astraHandlingText) {
           this.trackingId = trackingId;
           this.astraBarcode = astraBarcode;
           this.astraHandlingText = astraHandlingText;
    }


    /**
     * Gets the trackingId value for this RoutingAstraDetail.
     * 
     * @return trackingId   * The tracking number information for the shipment.
     */
    public com.fedex.ship.stub.TrackingId getTrackingId() {
        return trackingId;
    }


    /**
     * Sets the trackingId value for this RoutingAstraDetail.
     * 
     * @param trackingId   * The tracking number information for the shipment.
     */
    public void setTrackingId(com.fedex.ship.stub.TrackingId trackingId) {
        this.trackingId = trackingId;
    }


    /**
     * Gets the astraBarcode value for this RoutingAstraDetail.
     * 
     * @return astraBarcode
     */
    public java.lang.String getAstraBarcode() {
        return astraBarcode;
    }


    /**
     * Sets the astraBarcode value for this RoutingAstraDetail.
     * 
     * @param astraBarcode
     */
    public void setAstraBarcode(java.lang.String astraBarcode) {
        this.astraBarcode = astraBarcode;
    }


    /**
     * Gets the astraHandlingText value for this RoutingAstraDetail.
     * 
     * @return astraHandlingText   * The textual description of the special service applied to the
     * package.
     */
    public java.lang.String getAstraHandlingText() {
        return astraHandlingText;
    }


    /**
     * Sets the astraHandlingText value for this RoutingAstraDetail.
     * 
     * @param astraHandlingText   * The textual description of the special service applied to the
     * package.
     */
    public void setAstraHandlingText(java.lang.String astraHandlingText) {
        this.astraHandlingText = astraHandlingText;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RoutingAstraDetail)) return false;
        RoutingAstraDetail other = (RoutingAstraDetail) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.trackingId==null && other.getTrackingId()==null) || 
             (this.trackingId!=null &&
              this.trackingId.equals(other.getTrackingId()))) &&
            ((this.astraBarcode==null && other.getAstraBarcode()==null) || 
             (this.astraBarcode!=null &&
              this.astraBarcode.equals(other.getAstraBarcode()))) &&
            ((this.astraHandlingText==null && other.getAstraHandlingText()==null) || 
             (this.astraHandlingText!=null &&
              this.astraHandlingText.equals(other.getAstraHandlingText())));
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
        if (getTrackingId() != null) {
            _hashCode += getTrackingId().hashCode();
        }
        if (getAstraBarcode() != null) {
            _hashCode += getAstraBarcode().hashCode();
        }
        if (getAstraHandlingText() != null) {
            _hashCode += getAstraHandlingText().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RoutingAstraDetail.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "RoutingAstraDetail"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trackingId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "TrackingId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "TrackingId"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("astraBarcode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "AstraBarcode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("astraHandlingText");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "AstraHandlingText"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
