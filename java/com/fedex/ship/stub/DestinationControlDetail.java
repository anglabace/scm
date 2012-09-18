/**
 * DestinationControlDetail.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.fedex.ship.stub;


/**
 * Department of Commerce/Department of State information about this
 * shipment.
 */
public class DestinationControlDetail  implements java.io.Serializable {
    /* List of applicable Statment types. */
    private com.fedex.ship.stub.DestinationControlStatementType[] statementTypes;

    /* Countries this shipment is destined for. */
    private java.lang.String destinationCountries;

    /* Department of State End User. */
    private java.lang.String endUser;

    public DestinationControlDetail() {
    }

    public DestinationControlDetail(
           com.fedex.ship.stub.DestinationControlStatementType[] statementTypes,
           java.lang.String destinationCountries,
           java.lang.String endUser) {
           this.statementTypes = statementTypes;
           this.destinationCountries = destinationCountries;
           this.endUser = endUser;
    }


    /**
     * Gets the statementTypes value for this DestinationControlDetail.
     * 
     * @return statementTypes   * List of applicable Statment types.
     */
    public com.fedex.ship.stub.DestinationControlStatementType[] getStatementTypes() {
        return statementTypes;
    }


    /**
     * Sets the statementTypes value for this DestinationControlDetail.
     * 
     * @param statementTypes   * List of applicable Statment types.
     */
    public void setStatementTypes(com.fedex.ship.stub.DestinationControlStatementType[] statementTypes) {
        this.statementTypes = statementTypes;
    }

    public com.fedex.ship.stub.DestinationControlStatementType getStatementTypes(int i) {
        return this.statementTypes[i];
    }

    public void setStatementTypes(int i, com.fedex.ship.stub.DestinationControlStatementType _value) {
        this.statementTypes[i] = _value;
    }


    /**
     * Gets the destinationCountries value for this DestinationControlDetail.
     * 
     * @return destinationCountries   * Countries this shipment is destined for.
     */
    public java.lang.String getDestinationCountries() {
        return destinationCountries;
    }


    /**
     * Sets the destinationCountries value for this DestinationControlDetail.
     * 
     * @param destinationCountries   * Countries this shipment is destined for.
     */
    public void setDestinationCountries(java.lang.String destinationCountries) {
        this.destinationCountries = destinationCountries;
    }


    /**
     * Gets the endUser value for this DestinationControlDetail.
     * 
     * @return endUser   * Department of State End User.
     */
    public java.lang.String getEndUser() {
        return endUser;
    }


    /**
     * Sets the endUser value for this DestinationControlDetail.
     * 
     * @param endUser   * Department of State End User.
     */
    public void setEndUser(java.lang.String endUser) {
        this.endUser = endUser;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DestinationControlDetail)) return false;
        DestinationControlDetail other = (DestinationControlDetail) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.statementTypes==null && other.getStatementTypes()==null) || 
             (this.statementTypes!=null &&
              java.util.Arrays.equals(this.statementTypes, other.getStatementTypes()))) &&
            ((this.destinationCountries==null && other.getDestinationCountries()==null) || 
             (this.destinationCountries!=null &&
              this.destinationCountries.equals(other.getDestinationCountries()))) &&
            ((this.endUser==null && other.getEndUser()==null) || 
             (this.endUser!=null &&
              this.endUser.equals(other.getEndUser())));
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
        if (getStatementTypes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getStatementTypes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getStatementTypes(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDestinationCountries() != null) {
            _hashCode += getDestinationCountries().hashCode();
        }
        if (getEndUser() != null) {
            _hashCode += getEndUser().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DestinationControlDetail.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "DestinationControlDetail"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statementTypes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "StatementTypes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "DestinationControlStatementType"));
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destinationCountries");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "DestinationCountries"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endUser");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "EndUser"));
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
