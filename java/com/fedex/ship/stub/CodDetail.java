/**
 * CodDetail.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.fedex.ship.stub;


/**
 * Descriptive data required for a FedEx COD (Collect-On-Delivery)
 * shipment.
 */
public class CodDetail  implements java.io.Serializable {
    /* Identifies if freight charges are to be added to the COD amount.
     * This element determines which freight charges should be added to the
     * COD collect amount. See CodAddTransportationChargesType for the list
     * of valid enumerated values. */
    private com.fedex.ship.stub.CodAddTransportationChargesType addTransportationCharges;

    /* Identifies the type of funds FedEx should collect upon package
     * delivery. See CodCollectionType for the list of valid enumerated values. */
    private com.fedex.ship.stub.CodCollectionType collectionType;

    /* Descriptive data about the recipient of the COD shipment. */
    private com.fedex.ship.stub.Party codRecipient;

    /* Indicates which type of reference information to include on
     * the COD return shipping label. */
    private com.fedex.ship.stub.CodReturnReferenceIndicatorType referenceIndicator;

    public CodDetail() {
    }

    public CodDetail(
           com.fedex.ship.stub.CodAddTransportationChargesType addTransportationCharges,
           com.fedex.ship.stub.CodCollectionType collectionType,
           com.fedex.ship.stub.Party codRecipient,
           com.fedex.ship.stub.CodReturnReferenceIndicatorType referenceIndicator) {
           this.addTransportationCharges = addTransportationCharges;
           this.collectionType = collectionType;
           this.codRecipient = codRecipient;
           this.referenceIndicator = referenceIndicator;
    }


    /**
     * Gets the addTransportationCharges value for this CodDetail.
     * 
     * @return addTransportationCharges   * Identifies if freight charges are to be added to the COD amount.
     * This element determines which freight charges should be added to the
     * COD collect amount. See CodAddTransportationChargesType for the list
     * of valid enumerated values.
     */
    public com.fedex.ship.stub.CodAddTransportationChargesType getAddTransportationCharges() {
        return addTransportationCharges;
    }


    /**
     * Sets the addTransportationCharges value for this CodDetail.
     * 
     * @param addTransportationCharges   * Identifies if freight charges are to be added to the COD amount.
     * This element determines which freight charges should be added to the
     * COD collect amount. See CodAddTransportationChargesType for the list
     * of valid enumerated values.
     */
    public void setAddTransportationCharges(com.fedex.ship.stub.CodAddTransportationChargesType addTransportationCharges) {
        this.addTransportationCharges = addTransportationCharges;
    }


    /**
     * Gets the collectionType value for this CodDetail.
     * 
     * @return collectionType   * Identifies the type of funds FedEx should collect upon package
     * delivery. See CodCollectionType for the list of valid enumerated values.
     */
    public com.fedex.ship.stub.CodCollectionType getCollectionType() {
        return collectionType;
    }


    /**
     * Sets the collectionType value for this CodDetail.
     * 
     * @param collectionType   * Identifies the type of funds FedEx should collect upon package
     * delivery. See CodCollectionType for the list of valid enumerated values.
     */
    public void setCollectionType(com.fedex.ship.stub.CodCollectionType collectionType) {
        this.collectionType = collectionType;
    }


    /**
     * Gets the codRecipient value for this CodDetail.
     * 
     * @return codRecipient   * Descriptive data about the recipient of the COD shipment.
     */
    public com.fedex.ship.stub.Party getCodRecipient() {
        return codRecipient;
    }


    /**
     * Sets the codRecipient value for this CodDetail.
     * 
     * @param codRecipient   * Descriptive data about the recipient of the COD shipment.
     */
    public void setCodRecipient(com.fedex.ship.stub.Party codRecipient) {
        this.codRecipient = codRecipient;
    }


    /**
     * Gets the referenceIndicator value for this CodDetail.
     * 
     * @return referenceIndicator   * Indicates which type of reference information to include on
     * the COD return shipping label.
     */
    public com.fedex.ship.stub.CodReturnReferenceIndicatorType getReferenceIndicator() {
        return referenceIndicator;
    }


    /**
     * Sets the referenceIndicator value for this CodDetail.
     * 
     * @param referenceIndicator   * Indicates which type of reference information to include on
     * the COD return shipping label.
     */
    public void setReferenceIndicator(com.fedex.ship.stub.CodReturnReferenceIndicatorType referenceIndicator) {
        this.referenceIndicator = referenceIndicator;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CodDetail)) return false;
        CodDetail other = (CodDetail) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.addTransportationCharges==null && other.getAddTransportationCharges()==null) || 
             (this.addTransportationCharges!=null &&
              this.addTransportationCharges.equals(other.getAddTransportationCharges()))) &&
            ((this.collectionType==null && other.getCollectionType()==null) || 
             (this.collectionType!=null &&
              this.collectionType.equals(other.getCollectionType()))) &&
            ((this.codRecipient==null && other.getCodRecipient()==null) || 
             (this.codRecipient!=null &&
              this.codRecipient.equals(other.getCodRecipient()))) &&
            ((this.referenceIndicator==null && other.getReferenceIndicator()==null) || 
             (this.referenceIndicator!=null &&
              this.referenceIndicator.equals(other.getReferenceIndicator())));
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
        if (getAddTransportationCharges() != null) {
            _hashCode += getAddTransportationCharges().hashCode();
        }
        if (getCollectionType() != null) {
            _hashCode += getCollectionType().hashCode();
        }
        if (getCodRecipient() != null) {
            _hashCode += getCodRecipient().hashCode();
        }
        if (getReferenceIndicator() != null) {
            _hashCode += getReferenceIndicator().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CodDetail.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "CodDetail"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addTransportationCharges");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "AddTransportationCharges"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "CodAddTransportationChargesType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("collectionType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "CollectionType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "CodCollectionType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codRecipient");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "CodRecipient"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "Party"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("referenceIndicator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "ReferenceIndicator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "CodReturnReferenceIndicatorType"));
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
