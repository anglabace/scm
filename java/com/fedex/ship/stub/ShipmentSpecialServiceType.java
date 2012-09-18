/**
 * ShipmentSpecialServiceType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.fedex.ship.stub;

public class ShipmentSpecialServiceType implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected ShipmentSpecialServiceType(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _BROKER_SELECT_OPTION = "BROKER_SELECT_OPTION";
    public static final java.lang.String _COD = "COD";
    public static final java.lang.String _DRY_ICE = "DRY_ICE";
    public static final java.lang.String _ELECTRONIC_TRADE_DOCUMENTS = "ELECTRONIC_TRADE_DOCUMENTS";
    public static final java.lang.String _EMAIL_NOTIFICATION = "EMAIL_NOTIFICATION";
    public static final java.lang.String _FUTURE_DAY_SHIPMENT = "FUTURE_DAY_SHIPMENT";
    public static final java.lang.String _HOLD_AT_LOCATION = "HOLD_AT_LOCATION";
    public static final java.lang.String _HOME_DELIVERY_PREMIUM = "HOME_DELIVERY_PREMIUM";
    public static final java.lang.String _INSIDE_DELIVERY = "INSIDE_DELIVERY";
    public static final java.lang.String _INSIDE_PICKUP = "INSIDE_PICKUP";
    public static final java.lang.String _PENDING_SHIPMENT = "PENDING_SHIPMENT";
    public static final java.lang.String _RETURN_SHIPMENT = "RETURN_SHIPMENT";
    public static final java.lang.String _SATURDAY_DELIVERY = "SATURDAY_DELIVERY";
    public static final java.lang.String _SATURDAY_PICKUP = "SATURDAY_PICKUP";
    public static final ShipmentSpecialServiceType BROKER_SELECT_OPTION = new ShipmentSpecialServiceType(_BROKER_SELECT_OPTION);
    public static final ShipmentSpecialServiceType COD = new ShipmentSpecialServiceType(_COD);
    public static final ShipmentSpecialServiceType DRY_ICE = new ShipmentSpecialServiceType(_DRY_ICE);
    public static final ShipmentSpecialServiceType ELECTRONIC_TRADE_DOCUMENTS = new ShipmentSpecialServiceType(_ELECTRONIC_TRADE_DOCUMENTS);
    public static final ShipmentSpecialServiceType EMAIL_NOTIFICATION = new ShipmentSpecialServiceType(_EMAIL_NOTIFICATION);
    public static final ShipmentSpecialServiceType FUTURE_DAY_SHIPMENT = new ShipmentSpecialServiceType(_FUTURE_DAY_SHIPMENT);
    public static final ShipmentSpecialServiceType HOLD_AT_LOCATION = new ShipmentSpecialServiceType(_HOLD_AT_LOCATION);
    public static final ShipmentSpecialServiceType HOME_DELIVERY_PREMIUM = new ShipmentSpecialServiceType(_HOME_DELIVERY_PREMIUM);
    public static final ShipmentSpecialServiceType INSIDE_DELIVERY = new ShipmentSpecialServiceType(_INSIDE_DELIVERY);
    public static final ShipmentSpecialServiceType INSIDE_PICKUP = new ShipmentSpecialServiceType(_INSIDE_PICKUP);
    public static final ShipmentSpecialServiceType PENDING_SHIPMENT = new ShipmentSpecialServiceType(_PENDING_SHIPMENT);
    public static final ShipmentSpecialServiceType RETURN_SHIPMENT = new ShipmentSpecialServiceType(_RETURN_SHIPMENT);
    public static final ShipmentSpecialServiceType SATURDAY_DELIVERY = new ShipmentSpecialServiceType(_SATURDAY_DELIVERY);
    public static final ShipmentSpecialServiceType SATURDAY_PICKUP = new ShipmentSpecialServiceType(_SATURDAY_PICKUP);
    public java.lang.String getValue() { return _value_;}
    public static ShipmentSpecialServiceType fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        ShipmentSpecialServiceType enumeration = (ShipmentSpecialServiceType)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static ShipmentSpecialServiceType fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(ShipmentSpecialServiceType.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "ShipmentSpecialServiceType"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
