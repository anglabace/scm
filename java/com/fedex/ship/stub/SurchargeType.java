/**
 * SurchargeType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.fedex.ship.stub;

public class SurchargeType implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected SurchargeType(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _ADDITIONAL_HANDLING = "ADDITIONAL_HANDLING";
    public static final java.lang.String _APPOINTMENT_DELIVERY = "APPOINTMENT_DELIVERY";
    public static final java.lang.String _BROKER_SELECT_OPTION = "BROKER_SELECT_OPTION";
    public static final java.lang.String _CANADIAN_DESTINATION = "CANADIAN_DESTINATION";
    public static final java.lang.String _CLEARANCE_ENTRY_FEE = "CLEARANCE_ENTRY_FEE";
    public static final java.lang.String _COD = "COD";
    public static final java.lang.String _CUT_FLOWERS = "CUT_FLOWERS";
    public static final java.lang.String _DANGEROUS_GOODS = "DANGEROUS_GOODS";
    public static final java.lang.String _DELIVERY_AREA = "DELIVERY_AREA";
    public static final java.lang.String _DELIVERY_SIGNATURE_OPTIONS = "DELIVERY_SIGNATURE_OPTIONS";
    public static final java.lang.String _DRY_ICE = "DRY_ICE";
    public static final java.lang.String _EMAIL_LABEL = "EMAIL_LABEL";
    public static final java.lang.String _EUROPE_FIRST = "EUROPE_FIRST";
    public static final java.lang.String _EXPORT = "EXPORT";
    public static final java.lang.String _FEDEX_TAG = "FEDEX_TAG";
    public static final java.lang.String _FICE = "FICE";
    public static final java.lang.String _FUEL = "FUEL";
    public static final java.lang.String _HOME_DELIVERY_APPOINTMENT = "HOME_DELIVERY_APPOINTMENT";
    public static final java.lang.String _HOME_DELIVERY_DATE_CERTAIN = "HOME_DELIVERY_DATE_CERTAIN";
    public static final java.lang.String _HOME_DELIVERY_EVENING = "HOME_DELIVERY_EVENING";
    public static final java.lang.String _INSIDE_DELIVERY = "INSIDE_DELIVERY";
    public static final java.lang.String _INSIDE_PICKUP = "INSIDE_PICKUP";
    public static final java.lang.String _INSURED_VALUE = "INSURED_VALUE";
    public static final java.lang.String _INTERHAWAII = "INTERHAWAII";
    public static final java.lang.String _OFFSHORE = "OFFSHORE";
    public static final java.lang.String _ON_CALL_PICKUP = "ON_CALL_PICKUP";
    public static final java.lang.String _OTHER = "OTHER";
    public static final java.lang.String _OUT_OF_DELIVERY_AREA = "OUT_OF_DELIVERY_AREA";
    public static final java.lang.String _OUT_OF_PICKUP_AREA = "OUT_OF_PICKUP_AREA";
    public static final java.lang.String _OVERSIZE = "OVERSIZE";
    public static final java.lang.String _PIECE_COUNT_VERIFICATION = "PIECE_COUNT_VERIFICATION";
    public static final java.lang.String _PRIORITY_ALERT = "PRIORITY_ALERT";
    public static final java.lang.String _RESIDENTIAL_DELIVERY = "RESIDENTIAL_DELIVERY";
    public static final java.lang.String _RESIDENTIAL_PICKUP = "RESIDENTIAL_PICKUP";
    public static final java.lang.String _RETURN_LABEL = "RETURN_LABEL";
    public static final java.lang.String _SATURDAY_DELIVERY = "SATURDAY_DELIVERY";
    public static final java.lang.String _SATURDAY_PICKUP = "SATURDAY_PICKUP";
    public static final java.lang.String _SIGNATURE_OPTION = "SIGNATURE_OPTION";
    public static final java.lang.String _THIRD_PARTY_CONSIGNEE = "THIRD_PARTY_CONSIGNEE";
    public static final java.lang.String _TRANSMART_SERVICE_FEE = "TRANSMART_SERVICE_FEE";
    public static final SurchargeType ADDITIONAL_HANDLING = new SurchargeType(_ADDITIONAL_HANDLING);
    public static final SurchargeType APPOINTMENT_DELIVERY = new SurchargeType(_APPOINTMENT_DELIVERY);
    public static final SurchargeType BROKER_SELECT_OPTION = new SurchargeType(_BROKER_SELECT_OPTION);
    public static final SurchargeType CANADIAN_DESTINATION = new SurchargeType(_CANADIAN_DESTINATION);
    public static final SurchargeType CLEARANCE_ENTRY_FEE = new SurchargeType(_CLEARANCE_ENTRY_FEE);
    public static final SurchargeType COD = new SurchargeType(_COD);
    public static final SurchargeType CUT_FLOWERS = new SurchargeType(_CUT_FLOWERS);
    public static final SurchargeType DANGEROUS_GOODS = new SurchargeType(_DANGEROUS_GOODS);
    public static final SurchargeType DELIVERY_AREA = new SurchargeType(_DELIVERY_AREA);
    public static final SurchargeType DELIVERY_SIGNATURE_OPTIONS = new SurchargeType(_DELIVERY_SIGNATURE_OPTIONS);
    public static final SurchargeType DRY_ICE = new SurchargeType(_DRY_ICE);
    public static final SurchargeType EMAIL_LABEL = new SurchargeType(_EMAIL_LABEL);
    public static final SurchargeType EUROPE_FIRST = new SurchargeType(_EUROPE_FIRST);
    public static final SurchargeType EXPORT = new SurchargeType(_EXPORT);
    public static final SurchargeType FEDEX_TAG = new SurchargeType(_FEDEX_TAG);
    public static final SurchargeType FICE = new SurchargeType(_FICE);
    public static final SurchargeType FUEL = new SurchargeType(_FUEL);
    public static final SurchargeType HOME_DELIVERY_APPOINTMENT = new SurchargeType(_HOME_DELIVERY_APPOINTMENT);
    public static final SurchargeType HOME_DELIVERY_DATE_CERTAIN = new SurchargeType(_HOME_DELIVERY_DATE_CERTAIN);
    public static final SurchargeType HOME_DELIVERY_EVENING = new SurchargeType(_HOME_DELIVERY_EVENING);
    public static final SurchargeType INSIDE_DELIVERY = new SurchargeType(_INSIDE_DELIVERY);
    public static final SurchargeType INSIDE_PICKUP = new SurchargeType(_INSIDE_PICKUP);
    public static final SurchargeType INSURED_VALUE = new SurchargeType(_INSURED_VALUE);
    public static final SurchargeType INTERHAWAII = new SurchargeType(_INTERHAWAII);
    public static final SurchargeType OFFSHORE = new SurchargeType(_OFFSHORE);
    public static final SurchargeType ON_CALL_PICKUP = new SurchargeType(_ON_CALL_PICKUP);
    public static final SurchargeType OTHER = new SurchargeType(_OTHER);
    public static final SurchargeType OUT_OF_DELIVERY_AREA = new SurchargeType(_OUT_OF_DELIVERY_AREA);
    public static final SurchargeType OUT_OF_PICKUP_AREA = new SurchargeType(_OUT_OF_PICKUP_AREA);
    public static final SurchargeType OVERSIZE = new SurchargeType(_OVERSIZE);
    public static final SurchargeType PIECE_COUNT_VERIFICATION = new SurchargeType(_PIECE_COUNT_VERIFICATION);
    public static final SurchargeType PRIORITY_ALERT = new SurchargeType(_PRIORITY_ALERT);
    public static final SurchargeType RESIDENTIAL_DELIVERY = new SurchargeType(_RESIDENTIAL_DELIVERY);
    public static final SurchargeType RESIDENTIAL_PICKUP = new SurchargeType(_RESIDENTIAL_PICKUP);
    public static final SurchargeType RETURN_LABEL = new SurchargeType(_RETURN_LABEL);
    public static final SurchargeType SATURDAY_DELIVERY = new SurchargeType(_SATURDAY_DELIVERY);
    public static final SurchargeType SATURDAY_PICKUP = new SurchargeType(_SATURDAY_PICKUP);
    public static final SurchargeType SIGNATURE_OPTION = new SurchargeType(_SIGNATURE_OPTION);
    public static final SurchargeType THIRD_PARTY_CONSIGNEE = new SurchargeType(_THIRD_PARTY_CONSIGNEE);
    public static final SurchargeType TRANSMART_SERVICE_FEE = new SurchargeType(_TRANSMART_SERVICE_FEE);
    public java.lang.String getValue() { return _value_;}
    public static SurchargeType fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        SurchargeType enumeration = (SurchargeType)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static SurchargeType fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(SurchargeType.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "SurchargeType"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
