/**
 * ReturnedRateType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.fedex.ship.stub;

public class ReturnedRateType implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected ReturnedRateType(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _PAYOR_ACCOUNT = "PAYOR_ACCOUNT";
    public static final java.lang.String _PAYOR_COUNTER = "PAYOR_COUNTER";
    public static final java.lang.String _PAYOR_LIST = "PAYOR_LIST";
    public static final java.lang.String _PAYOR_MULTIWEIGHT = "PAYOR_MULTIWEIGHT";
    public static final java.lang.String _RATED_ACCOUNT = "RATED_ACCOUNT";
    public static final java.lang.String _RATED_COUNTER = "RATED_COUNTER";
    public static final java.lang.String _RATED_LIST = "RATED_LIST";
    public static final ReturnedRateType PAYOR_ACCOUNT = new ReturnedRateType(_PAYOR_ACCOUNT);
    public static final ReturnedRateType PAYOR_COUNTER = new ReturnedRateType(_PAYOR_COUNTER);
    public static final ReturnedRateType PAYOR_LIST = new ReturnedRateType(_PAYOR_LIST);
    public static final ReturnedRateType PAYOR_MULTIWEIGHT = new ReturnedRateType(_PAYOR_MULTIWEIGHT);
    public static final ReturnedRateType RATED_ACCOUNT = new ReturnedRateType(_RATED_ACCOUNT);
    public static final ReturnedRateType RATED_COUNTER = new ReturnedRateType(_RATED_COUNTER);
    public static final ReturnedRateType RATED_LIST = new ReturnedRateType(_RATED_LIST);
    public java.lang.String getValue() { return _value_;}
    public static ReturnedRateType fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        ReturnedRateType enumeration = (ReturnedRateType)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static ReturnedRateType fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(ReturnedRateType.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "ReturnedRateType"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
