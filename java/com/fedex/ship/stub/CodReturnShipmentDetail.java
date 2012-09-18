/**
 * CodReturnShipmentDetail.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.fedex.ship.stub;

public class CodReturnShipmentDetail  implements java.io.Serializable {
    /* The COD amount (after any accumulations) that must be collected
     * upon delivery of a package shipped using the COD special service. */
    private com.fedex.ship.stub.Money collectionAmount;

    /* Currently not supported. */
    private java.lang.String handling;

    /* The description of the FedEx service type used for the COD
     * return shipment. Currently not supported. */
    private java.lang.String serviceTypeDescription;

    /* The description of the packaging used for the COD return shipment. */
    private java.lang.String packagingDescription;

    /* Currently not supported. */
    private java.lang.String securedDescription;

    /* Currently not supported. */
    private com.fedex.ship.stub.Party remitter;

    /* Currently not supported. */
    private com.fedex.ship.stub.Party codRecipient;

    /* The CodRoutingDetail element will contain the COD return tracking
     * number and form id. In the case of a COD multiple piece shipment these
     * will need to be inserted in the request for the last piece of the
     * multiple piece shipment.
     * 												The service commitment is the only other element of the
     * RoutingDetail that is used for a CodRoutingDetail. */
    private com.fedex.ship.stub.RoutingDetail codRoutingDetail;

    /* Contains the data which form the Astra and 2DCommon barcodes
     * that print on the COD return label. */
    private com.fedex.ship.stub.PackageBarcodes barcodes;

    /* The label image or printer commands to print the label. */
    private com.fedex.ship.stub.ShippingDocument label;

    public CodReturnShipmentDetail() {
    }

    public CodReturnShipmentDetail(
           com.fedex.ship.stub.Money collectionAmount,
           java.lang.String handling,
           java.lang.String serviceTypeDescription,
           java.lang.String packagingDescription,
           java.lang.String securedDescription,
           com.fedex.ship.stub.Party remitter,
           com.fedex.ship.stub.Party codRecipient,
           com.fedex.ship.stub.RoutingDetail codRoutingDetail,
           com.fedex.ship.stub.PackageBarcodes barcodes,
           com.fedex.ship.stub.ShippingDocument label) {
           this.collectionAmount = collectionAmount;
           this.handling = handling;
           this.serviceTypeDescription = serviceTypeDescription;
           this.packagingDescription = packagingDescription;
           this.securedDescription = securedDescription;
           this.remitter = remitter;
           this.codRecipient = codRecipient;
           this.codRoutingDetail = codRoutingDetail;
           this.barcodes = barcodes;
           this.label = label;
    }


    /**
     * Gets the collectionAmount value for this CodReturnShipmentDetail.
     * 
     * @return collectionAmount   * The COD amount (after any accumulations) that must be collected
     * upon delivery of a package shipped using the COD special service.
     */
    public com.fedex.ship.stub.Money getCollectionAmount() {
        return collectionAmount;
    }


    /**
     * Sets the collectionAmount value for this CodReturnShipmentDetail.
     * 
     * @param collectionAmount   * The COD amount (after any accumulations) that must be collected
     * upon delivery of a package shipped using the COD special service.
     */
    public void setCollectionAmount(com.fedex.ship.stub.Money collectionAmount) {
        this.collectionAmount = collectionAmount;
    }


    /**
     * Gets the handling value for this CodReturnShipmentDetail.
     * 
     * @return handling   * Currently not supported.
     */
    public java.lang.String getHandling() {
        return handling;
    }


    /**
     * Sets the handling value for this CodReturnShipmentDetail.
     * 
     * @param handling   * Currently not supported.
     */
    public void setHandling(java.lang.String handling) {
        this.handling = handling;
    }


    /**
     * Gets the serviceTypeDescription value for this CodReturnShipmentDetail.
     * 
     * @return serviceTypeDescription   * The description of the FedEx service type used for the COD
     * return shipment. Currently not supported.
     */
    public java.lang.String getServiceTypeDescription() {
        return serviceTypeDescription;
    }


    /**
     * Sets the serviceTypeDescription value for this CodReturnShipmentDetail.
     * 
     * @param serviceTypeDescription   * The description of the FedEx service type used for the COD
     * return shipment. Currently not supported.
     */
    public void setServiceTypeDescription(java.lang.String serviceTypeDescription) {
        this.serviceTypeDescription = serviceTypeDescription;
    }


    /**
     * Gets the packagingDescription value for this CodReturnShipmentDetail.
     * 
     * @return packagingDescription   * The description of the packaging used for the COD return shipment.
     */
    public java.lang.String getPackagingDescription() {
        return packagingDescription;
    }


    /**
     * Sets the packagingDescription value for this CodReturnShipmentDetail.
     * 
     * @param packagingDescription   * The description of the packaging used for the COD return shipment.
     */
    public void setPackagingDescription(java.lang.String packagingDescription) {
        this.packagingDescription = packagingDescription;
    }


    /**
     * Gets the securedDescription value for this CodReturnShipmentDetail.
     * 
     * @return securedDescription   * Currently not supported.
     */
    public java.lang.String getSecuredDescription() {
        return securedDescription;
    }


    /**
     * Sets the securedDescription value for this CodReturnShipmentDetail.
     * 
     * @param securedDescription   * Currently not supported.
     */
    public void setSecuredDescription(java.lang.String securedDescription) {
        this.securedDescription = securedDescription;
    }


    /**
     * Gets the remitter value for this CodReturnShipmentDetail.
     * 
     * @return remitter   * Currently not supported.
     */
    public com.fedex.ship.stub.Party getRemitter() {
        return remitter;
    }


    /**
     * Sets the remitter value for this CodReturnShipmentDetail.
     * 
     * @param remitter   * Currently not supported.
     */
    public void setRemitter(com.fedex.ship.stub.Party remitter) {
        this.remitter = remitter;
    }


    /**
     * Gets the codRecipient value for this CodReturnShipmentDetail.
     * 
     * @return codRecipient   * Currently not supported.
     */
    public com.fedex.ship.stub.Party getCodRecipient() {
        return codRecipient;
    }


    /**
     * Sets the codRecipient value for this CodReturnShipmentDetail.
     * 
     * @param codRecipient   * Currently not supported.
     */
    public void setCodRecipient(com.fedex.ship.stub.Party codRecipient) {
        this.codRecipient = codRecipient;
    }


    /**
     * Gets the codRoutingDetail value for this CodReturnShipmentDetail.
     * 
     * @return codRoutingDetail   * The CodRoutingDetail element will contain the COD return tracking
     * number and form id. In the case of a COD multiple piece shipment these
     * will need to be inserted in the request for the last piece of the
     * multiple piece shipment.
     * 												The service commitment is the only other element of the
     * RoutingDetail that is used for a CodRoutingDetail.
     */
    public com.fedex.ship.stub.RoutingDetail getCodRoutingDetail() {
        return codRoutingDetail;
    }


    /**
     * Sets the codRoutingDetail value for this CodReturnShipmentDetail.
     * 
     * @param codRoutingDetail   * The CodRoutingDetail element will contain the COD return tracking
     * number and form id. In the case of a COD multiple piece shipment these
     * will need to be inserted in the request for the last piece of the
     * multiple piece shipment.
     * 												The service commitment is the only other element of the
     * RoutingDetail that is used for a CodRoutingDetail.
     */
    public void setCodRoutingDetail(com.fedex.ship.stub.RoutingDetail codRoutingDetail) {
        this.codRoutingDetail = codRoutingDetail;
    }


    /**
     * Gets the barcodes value for this CodReturnShipmentDetail.
     * 
     * @return barcodes   * Contains the data which form the Astra and 2DCommon barcodes
     * that print on the COD return label.
     */
    public com.fedex.ship.stub.PackageBarcodes getBarcodes() {
        return barcodes;
    }


    /**
     * Sets the barcodes value for this CodReturnShipmentDetail.
     * 
     * @param barcodes   * Contains the data which form the Astra and 2DCommon barcodes
     * that print on the COD return label.
     */
    public void setBarcodes(com.fedex.ship.stub.PackageBarcodes barcodes) {
        this.barcodes = barcodes;
    }


    /**
     * Gets the label value for this CodReturnShipmentDetail.
     * 
     * @return label   * The label image or printer commands to print the label.
     */
    public com.fedex.ship.stub.ShippingDocument getLabel() {
        return label;
    }


    /**
     * Sets the label value for this CodReturnShipmentDetail.
     * 
     * @param label   * The label image or printer commands to print the label.
     */
    public void setLabel(com.fedex.ship.stub.ShippingDocument label) {
        this.label = label;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CodReturnShipmentDetail)) return false;
        CodReturnShipmentDetail other = (CodReturnShipmentDetail) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.collectionAmount==null && other.getCollectionAmount()==null) || 
             (this.collectionAmount!=null &&
              this.collectionAmount.equals(other.getCollectionAmount()))) &&
            ((this.handling==null && other.getHandling()==null) || 
             (this.handling!=null &&
              this.handling.equals(other.getHandling()))) &&
            ((this.serviceTypeDescription==null && other.getServiceTypeDescription()==null) || 
             (this.serviceTypeDescription!=null &&
              this.serviceTypeDescription.equals(other.getServiceTypeDescription()))) &&
            ((this.packagingDescription==null && other.getPackagingDescription()==null) || 
             (this.packagingDescription!=null &&
              this.packagingDescription.equals(other.getPackagingDescription()))) &&
            ((this.securedDescription==null && other.getSecuredDescription()==null) || 
             (this.securedDescription!=null &&
              this.securedDescription.equals(other.getSecuredDescription()))) &&
            ((this.remitter==null && other.getRemitter()==null) || 
             (this.remitter!=null &&
              this.remitter.equals(other.getRemitter()))) &&
            ((this.codRecipient==null && other.getCodRecipient()==null) || 
             (this.codRecipient!=null &&
              this.codRecipient.equals(other.getCodRecipient()))) &&
            ((this.codRoutingDetail==null && other.getCodRoutingDetail()==null) || 
             (this.codRoutingDetail!=null &&
              this.codRoutingDetail.equals(other.getCodRoutingDetail()))) &&
            ((this.barcodes==null && other.getBarcodes()==null) || 
             (this.barcodes!=null &&
              this.barcodes.equals(other.getBarcodes()))) &&
            ((this.label==null && other.getLabel()==null) || 
             (this.label!=null &&
              this.label.equals(other.getLabel())));
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
        if (getCollectionAmount() != null) {
            _hashCode += getCollectionAmount().hashCode();
        }
        if (getHandling() != null) {
            _hashCode += getHandling().hashCode();
        }
        if (getServiceTypeDescription() != null) {
            _hashCode += getServiceTypeDescription().hashCode();
        }
        if (getPackagingDescription() != null) {
            _hashCode += getPackagingDescription().hashCode();
        }
        if (getSecuredDescription() != null) {
            _hashCode += getSecuredDescription().hashCode();
        }
        if (getRemitter() != null) {
            _hashCode += getRemitter().hashCode();
        }
        if (getCodRecipient() != null) {
            _hashCode += getCodRecipient().hashCode();
        }
        if (getCodRoutingDetail() != null) {
            _hashCode += getCodRoutingDetail().hashCode();
        }
        if (getBarcodes() != null) {
            _hashCode += getBarcodes().hashCode();
        }
        if (getLabel() != null) {
            _hashCode += getLabel().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CodReturnShipmentDetail.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "CodReturnShipmentDetail"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("collectionAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "CollectionAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "Money"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("handling");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "Handling"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceTypeDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "ServiceTypeDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("packagingDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "PackagingDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("securedDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "SecuredDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("remitter");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "Remitter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "Party"));
        elemField.setMinOccurs(0);
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
        elemField.setFieldName("codRoutingDetail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "CodRoutingDetail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "RoutingDetail"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("barcodes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "Barcodes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "PackageBarcodes"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("label");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "Label"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "ShippingDocument"));
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
