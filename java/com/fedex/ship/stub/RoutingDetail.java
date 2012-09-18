/**
 * RoutingDetail.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.fedex.ship.stub;


/**
 * Information about the routing, origin, destination and delivery
 * of a shipment.
 */
public class RoutingDetail  implements java.io.Serializable {
    /* The routing information detail for this shipment. */
    private com.fedex.ship.stub.ShipmentRoutingDetail shipmentRoutingDetail;

    /* The tracking number information and the data to form the Astra
     * barcode for the label. */
    private com.fedex.ship.stub.RoutingAstraDetail[] astraDetails;

    public RoutingDetail() {
    }

    public RoutingDetail(
           com.fedex.ship.stub.ShipmentRoutingDetail shipmentRoutingDetail,
           com.fedex.ship.stub.RoutingAstraDetail[] astraDetails) {
           this.shipmentRoutingDetail = shipmentRoutingDetail;
           this.astraDetails = astraDetails;
    }


    /**
     * Gets the shipmentRoutingDetail value for this RoutingDetail.
     * 
     * @return shipmentRoutingDetail   * The routing information detail for this shipment.
     */
    public com.fedex.ship.stub.ShipmentRoutingDetail getShipmentRoutingDetail() {
        return shipmentRoutingDetail;
    }


    /**
     * Sets the shipmentRoutingDetail value for this RoutingDetail.
     * 
     * @param shipmentRoutingDetail   * The routing information detail for this shipment.
     */
    public void setShipmentRoutingDetail(com.fedex.ship.stub.ShipmentRoutingDetail shipmentRoutingDetail) {
        this.shipmentRoutingDetail = shipmentRoutingDetail;
    }


    /**
     * Gets the astraDetails value for this RoutingDetail.
     * 
     * @return astraDetails   * The tracking number information and the data to form the Astra
     * barcode for the label.
     */
    public com.fedex.ship.stub.RoutingAstraDetail[] getAstraDetails() {
        return astraDetails;
    }


    /**
     * Sets the astraDetails value for this RoutingDetail.
     * 
     * @param astraDetails   * The tracking number information and the data to form the Astra
     * barcode for the label.
     */
    public void setAstraDetails(com.fedex.ship.stub.RoutingAstraDetail[] astraDetails) {
        this.astraDetails = astraDetails;
    }

    public com.fedex.ship.stub.RoutingAstraDetail getAstraDetails(int i) {
        return this.astraDetails[i];
    }

    public void setAstraDetails(int i, com.fedex.ship.stub.RoutingAstraDetail _value) {
        this.astraDetails[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RoutingDetail)) return false;
        RoutingDetail other = (RoutingDetail) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.shipmentRoutingDetail==null && other.getShipmentRoutingDetail()==null) || 
             (this.shipmentRoutingDetail!=null &&
              this.shipmentRoutingDetail.equals(other.getShipmentRoutingDetail()))) &&
            ((this.astraDetails==null && other.getAstraDetails()==null) || 
             (this.astraDetails!=null &&
              java.util.Arrays.equals(this.astraDetails, other.getAstraDetails())));
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
        if (getShipmentRoutingDetail() != null) {
            _hashCode += getShipmentRoutingDetail().hashCode();
        }
        if (getAstraDetails() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAstraDetails());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAstraDetails(), i);
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
        new org.apache.axis.description.TypeDesc(RoutingDetail.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "RoutingDetail"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shipmentRoutingDetail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "ShipmentRoutingDetail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "ShipmentRoutingDetail"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("astraDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "AstraDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "RoutingAstraDetail"));
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
