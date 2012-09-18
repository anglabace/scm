/**
 * ShipmentSpecialServicesRequested.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.fedex.ship.stub;


/**
 * Descriptive data regarding special services requested by the shipper
 * for a shipment. If the shipper is requesting a special service which
 * requires additional data (e.g. COD), the special service type must
 * be present in the specialServiceTypes collection, and the supporting
 * detail must be provided in the appropriate sub-object. For example,
 * to request COD, "COD" must be included in the SpecialServiceTypes
 * collection and the CodDetail object must contain the required data.
 */
public class ShipmentSpecialServicesRequested  implements java.io.Serializable {
    /* Identifies the collection of special service types requested
     * by the shipper. See SpecialServiceTypes for the list of valid enumerated
     * types. */
    private com.fedex.ship.stub.ShipmentSpecialServiceType[] specialServiceTypes;

    /* Descriptive data required for a FedEx COD (Collect-On-Delivery)
     * shipment. This element is required when SpecialServiceType.COD is
     * present in the SpecialServiceTypes collection. */
    private com.fedex.ship.stub.CodDetail codDetail;

    /* The COD amount (after any accumulations) that must be collected
     * upon delivery of a shipment shipped using the COD special service. */
    private com.fedex.ship.stub.Money codCollectionAmount;

    /* Descriptive data required for a FedEx shipment that is to be
     * held at the destination FedEx location for pickup by the recipient.
     * This element is required when SpecialServiceType.HOLD_AT_LOCATION
     * is present in the SpecialServiceTypes collection. */
    private com.fedex.ship.stub.HoldAtLocationDetail holdAtLocationDetail;

    /* Descriptive data required for FedEx to provide email notification
     * to the customer regarding the shipment. This element is required when
     * SpecialServiceType.EMAIL_NOTIFICATION is present in the SpecialServiceTypes
     * collection. */
    private com.fedex.ship.stub.EMailNotificationDetail EMailNotificationDetail;

    /* The descriptive data required for FedEx Printed Return Label.
     * This element is required when SpecialServiceType.PRINTED_RETURN_LABEL
     * is present in the SpecialServiceTypes collection */
    private com.fedex.ship.stub.ReturnShipmentDetail returnShipmentDetail;

    /* The descriptive data required for FedEx Pending shipment. This
     * element is required when SpecialServiceType.PENDING_SHIPMENT is present
     * in the SpecialServiceTypes collection */
    private com.fedex.ship.stub.PendingShipmentDetail pendingShipmentDetail;

    /* Number of packages in this shipment which contain dry ice and
     * the total weight of the dry ice for this shipment. */
    private com.fedex.ship.stub.ShipmentDryIceDetail shipmentDryIceDetail;

    /* The descriptive data required for FedEx Home Delivery options.
     * This element is required when SpecialServiceType.HOME_DELIVERY_PREMIUM
     * is present in the SpecialServiceTypes collection */
    private com.fedex.ship.stub.HomeDeliveryPremiumDetail homeDeliveryPremiumDetail;

    private com.fedex.ship.stub.EtdDetail etdDetail;

    public ShipmentSpecialServicesRequested() {
    }

    public ShipmentSpecialServicesRequested(
           com.fedex.ship.stub.ShipmentSpecialServiceType[] specialServiceTypes,
           com.fedex.ship.stub.CodDetail codDetail,
           com.fedex.ship.stub.Money codCollectionAmount,
           com.fedex.ship.stub.HoldAtLocationDetail holdAtLocationDetail,
           com.fedex.ship.stub.EMailNotificationDetail EMailNotificationDetail,
           com.fedex.ship.stub.ReturnShipmentDetail returnShipmentDetail,
           com.fedex.ship.stub.PendingShipmentDetail pendingShipmentDetail,
           com.fedex.ship.stub.ShipmentDryIceDetail shipmentDryIceDetail,
           com.fedex.ship.stub.HomeDeliveryPremiumDetail homeDeliveryPremiumDetail,
           com.fedex.ship.stub.EtdDetail etdDetail) {
           this.specialServiceTypes = specialServiceTypes;
           this.codDetail = codDetail;
           this.codCollectionAmount = codCollectionAmount;
           this.holdAtLocationDetail = holdAtLocationDetail;
           this.EMailNotificationDetail = EMailNotificationDetail;
           this.returnShipmentDetail = returnShipmentDetail;
           this.pendingShipmentDetail = pendingShipmentDetail;
           this.shipmentDryIceDetail = shipmentDryIceDetail;
           this.homeDeliveryPremiumDetail = homeDeliveryPremiumDetail;
           this.etdDetail = etdDetail;
    }


    /**
     * Gets the specialServiceTypes value for this ShipmentSpecialServicesRequested.
     * 
     * @return specialServiceTypes   * Identifies the collection of special service types requested
     * by the shipper. See SpecialServiceTypes for the list of valid enumerated
     * types.
     */
    public com.fedex.ship.stub.ShipmentSpecialServiceType[] getSpecialServiceTypes() {
        return specialServiceTypes;
    }


    /**
     * Sets the specialServiceTypes value for this ShipmentSpecialServicesRequested.
     * 
     * @param specialServiceTypes   * Identifies the collection of special service types requested
     * by the shipper. See SpecialServiceTypes for the list of valid enumerated
     * types.
     */
    public void setSpecialServiceTypes(com.fedex.ship.stub.ShipmentSpecialServiceType[] specialServiceTypes) {
        this.specialServiceTypes = specialServiceTypes;
    }

    public com.fedex.ship.stub.ShipmentSpecialServiceType getSpecialServiceTypes(int i) {
        return this.specialServiceTypes[i];
    }

    public void setSpecialServiceTypes(int i, com.fedex.ship.stub.ShipmentSpecialServiceType _value) {
        this.specialServiceTypes[i] = _value;
    }


    /**
     * Gets the codDetail value for this ShipmentSpecialServicesRequested.
     * 
     * @return codDetail   * Descriptive data required for a FedEx COD (Collect-On-Delivery)
     * shipment. This element is required when SpecialServiceType.COD is
     * present in the SpecialServiceTypes collection.
     */
    public com.fedex.ship.stub.CodDetail getCodDetail() {
        return codDetail;
    }


    /**
     * Sets the codDetail value for this ShipmentSpecialServicesRequested.
     * 
     * @param codDetail   * Descriptive data required for a FedEx COD (Collect-On-Delivery)
     * shipment. This element is required when SpecialServiceType.COD is
     * present in the SpecialServiceTypes collection.
     */
    public void setCodDetail(com.fedex.ship.stub.CodDetail codDetail) {
        this.codDetail = codDetail;
    }


    /**
     * Gets the codCollectionAmount value for this ShipmentSpecialServicesRequested.
     * 
     * @return codCollectionAmount   * The COD amount (after any accumulations) that must be collected
     * upon delivery of a shipment shipped using the COD special service.
     */
    public com.fedex.ship.stub.Money getCodCollectionAmount() {
        return codCollectionAmount;
    }


    /**
     * Sets the codCollectionAmount value for this ShipmentSpecialServicesRequested.
     * 
     * @param codCollectionAmount   * The COD amount (after any accumulations) that must be collected
     * upon delivery of a shipment shipped using the COD special service.
     */
    public void setCodCollectionAmount(com.fedex.ship.stub.Money codCollectionAmount) {
        this.codCollectionAmount = codCollectionAmount;
    }


    /**
     * Gets the holdAtLocationDetail value for this ShipmentSpecialServicesRequested.
     * 
     * @return holdAtLocationDetail   * Descriptive data required for a FedEx shipment that is to be
     * held at the destination FedEx location for pickup by the recipient.
     * This element is required when SpecialServiceType.HOLD_AT_LOCATION
     * is present in the SpecialServiceTypes collection.
     */
    public com.fedex.ship.stub.HoldAtLocationDetail getHoldAtLocationDetail() {
        return holdAtLocationDetail;
    }


    /**
     * Sets the holdAtLocationDetail value for this ShipmentSpecialServicesRequested.
     * 
     * @param holdAtLocationDetail   * Descriptive data required for a FedEx shipment that is to be
     * held at the destination FedEx location for pickup by the recipient.
     * This element is required when SpecialServiceType.HOLD_AT_LOCATION
     * is present in the SpecialServiceTypes collection.
     */
    public void setHoldAtLocationDetail(com.fedex.ship.stub.HoldAtLocationDetail holdAtLocationDetail) {
        this.holdAtLocationDetail = holdAtLocationDetail;
    }


    /**
     * Gets the EMailNotificationDetail value for this ShipmentSpecialServicesRequested.
     * 
     * @return EMailNotificationDetail   * Descriptive data required for FedEx to provide email notification
     * to the customer regarding the shipment. This element is required when
     * SpecialServiceType.EMAIL_NOTIFICATION is present in the SpecialServiceTypes
     * collection.
     */
    public com.fedex.ship.stub.EMailNotificationDetail getEMailNotificationDetail() {
        return EMailNotificationDetail;
    }


    /**
     * Sets the EMailNotificationDetail value for this ShipmentSpecialServicesRequested.
     * 
     * @param EMailNotificationDetail   * Descriptive data required for FedEx to provide email notification
     * to the customer regarding the shipment. This element is required when
     * SpecialServiceType.EMAIL_NOTIFICATION is present in the SpecialServiceTypes
     * collection.
     */
    public void setEMailNotificationDetail(com.fedex.ship.stub.EMailNotificationDetail EMailNotificationDetail) {
        this.EMailNotificationDetail = EMailNotificationDetail;
    }


    /**
     * Gets the returnShipmentDetail value for this ShipmentSpecialServicesRequested.
     * 
     * @return returnShipmentDetail   * The descriptive data required for FedEx Printed Return Label.
     * This element is required when SpecialServiceType.PRINTED_RETURN_LABEL
     * is present in the SpecialServiceTypes collection
     */
    public com.fedex.ship.stub.ReturnShipmentDetail getReturnShipmentDetail() {
        return returnShipmentDetail;
    }


    /**
     * Sets the returnShipmentDetail value for this ShipmentSpecialServicesRequested.
     * 
     * @param returnShipmentDetail   * The descriptive data required for FedEx Printed Return Label.
     * This element is required when SpecialServiceType.PRINTED_RETURN_LABEL
     * is present in the SpecialServiceTypes collection
     */
    public void setReturnShipmentDetail(com.fedex.ship.stub.ReturnShipmentDetail returnShipmentDetail) {
        this.returnShipmentDetail = returnShipmentDetail;
    }


    /**
     * Gets the pendingShipmentDetail value for this ShipmentSpecialServicesRequested.
     * 
     * @return pendingShipmentDetail   * The descriptive data required for FedEx Pending shipment. This
     * element is required when SpecialServiceType.PENDING_SHIPMENT is present
     * in the SpecialServiceTypes collection
     */
    public com.fedex.ship.stub.PendingShipmentDetail getPendingShipmentDetail() {
        return pendingShipmentDetail;
    }


    /**
     * Sets the pendingShipmentDetail value for this ShipmentSpecialServicesRequested.
     * 
     * @param pendingShipmentDetail   * The descriptive data required for FedEx Pending shipment. This
     * element is required when SpecialServiceType.PENDING_SHIPMENT is present
     * in the SpecialServiceTypes collection
     */
    public void setPendingShipmentDetail(com.fedex.ship.stub.PendingShipmentDetail pendingShipmentDetail) {
        this.pendingShipmentDetail = pendingShipmentDetail;
    }


    /**
     * Gets the shipmentDryIceDetail value for this ShipmentSpecialServicesRequested.
     * 
     * @return shipmentDryIceDetail   * Number of packages in this shipment which contain dry ice and
     * the total weight of the dry ice for this shipment.
     */
    public com.fedex.ship.stub.ShipmentDryIceDetail getShipmentDryIceDetail() {
        return shipmentDryIceDetail;
    }


    /**
     * Sets the shipmentDryIceDetail value for this ShipmentSpecialServicesRequested.
     * 
     * @param shipmentDryIceDetail   * Number of packages in this shipment which contain dry ice and
     * the total weight of the dry ice for this shipment.
     */
    public void setShipmentDryIceDetail(com.fedex.ship.stub.ShipmentDryIceDetail shipmentDryIceDetail) {
        this.shipmentDryIceDetail = shipmentDryIceDetail;
    }


    /**
     * Gets the homeDeliveryPremiumDetail value for this ShipmentSpecialServicesRequested.
     * 
     * @return homeDeliveryPremiumDetail   * The descriptive data required for FedEx Home Delivery options.
     * This element is required when SpecialServiceType.HOME_DELIVERY_PREMIUM
     * is present in the SpecialServiceTypes collection
     */
    public com.fedex.ship.stub.HomeDeliveryPremiumDetail getHomeDeliveryPremiumDetail() {
        return homeDeliveryPremiumDetail;
    }


    /**
     * Sets the homeDeliveryPremiumDetail value for this ShipmentSpecialServicesRequested.
     * 
     * @param homeDeliveryPremiumDetail   * The descriptive data required for FedEx Home Delivery options.
     * This element is required when SpecialServiceType.HOME_DELIVERY_PREMIUM
     * is present in the SpecialServiceTypes collection
     */
    public void setHomeDeliveryPremiumDetail(com.fedex.ship.stub.HomeDeliveryPremiumDetail homeDeliveryPremiumDetail) {
        this.homeDeliveryPremiumDetail = homeDeliveryPremiumDetail;
    }


    /**
     * Gets the etdDetail value for this ShipmentSpecialServicesRequested.
     * 
     * @return etdDetail
     */
    public com.fedex.ship.stub.EtdDetail getEtdDetail() {
        return etdDetail;
    }


    /**
     * Sets the etdDetail value for this ShipmentSpecialServicesRequested.
     * 
     * @param etdDetail
     */
    public void setEtdDetail(com.fedex.ship.stub.EtdDetail etdDetail) {
        this.etdDetail = etdDetail;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ShipmentSpecialServicesRequested)) return false;
        ShipmentSpecialServicesRequested other = (ShipmentSpecialServicesRequested) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.specialServiceTypes==null && other.getSpecialServiceTypes()==null) || 
             (this.specialServiceTypes!=null &&
              java.util.Arrays.equals(this.specialServiceTypes, other.getSpecialServiceTypes()))) &&
            ((this.codDetail==null && other.getCodDetail()==null) || 
             (this.codDetail!=null &&
              this.codDetail.equals(other.getCodDetail()))) &&
            ((this.codCollectionAmount==null && other.getCodCollectionAmount()==null) || 
             (this.codCollectionAmount!=null &&
              this.codCollectionAmount.equals(other.getCodCollectionAmount()))) &&
            ((this.holdAtLocationDetail==null && other.getHoldAtLocationDetail()==null) || 
             (this.holdAtLocationDetail!=null &&
              this.holdAtLocationDetail.equals(other.getHoldAtLocationDetail()))) &&
            ((this.EMailNotificationDetail==null && other.getEMailNotificationDetail()==null) || 
             (this.EMailNotificationDetail!=null &&
              this.EMailNotificationDetail.equals(other.getEMailNotificationDetail()))) &&
            ((this.returnShipmentDetail==null && other.getReturnShipmentDetail()==null) || 
             (this.returnShipmentDetail!=null &&
              this.returnShipmentDetail.equals(other.getReturnShipmentDetail()))) &&
            ((this.pendingShipmentDetail==null && other.getPendingShipmentDetail()==null) || 
             (this.pendingShipmentDetail!=null &&
              this.pendingShipmentDetail.equals(other.getPendingShipmentDetail()))) &&
            ((this.shipmentDryIceDetail==null && other.getShipmentDryIceDetail()==null) || 
             (this.shipmentDryIceDetail!=null &&
              this.shipmentDryIceDetail.equals(other.getShipmentDryIceDetail()))) &&
            ((this.homeDeliveryPremiumDetail==null && other.getHomeDeliveryPremiumDetail()==null) || 
             (this.homeDeliveryPremiumDetail!=null &&
              this.homeDeliveryPremiumDetail.equals(other.getHomeDeliveryPremiumDetail()))) &&
            ((this.etdDetail==null && other.getEtdDetail()==null) || 
             (this.etdDetail!=null &&
              this.etdDetail.equals(other.getEtdDetail())));
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
        if (getSpecialServiceTypes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSpecialServiceTypes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSpecialServiceTypes(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCodDetail() != null) {
            _hashCode += getCodDetail().hashCode();
        }
        if (getCodCollectionAmount() != null) {
            _hashCode += getCodCollectionAmount().hashCode();
        }
        if (getHoldAtLocationDetail() != null) {
            _hashCode += getHoldAtLocationDetail().hashCode();
        }
        if (getEMailNotificationDetail() != null) {
            _hashCode += getEMailNotificationDetail().hashCode();
        }
        if (getReturnShipmentDetail() != null) {
            _hashCode += getReturnShipmentDetail().hashCode();
        }
        if (getPendingShipmentDetail() != null) {
            _hashCode += getPendingShipmentDetail().hashCode();
        }
        if (getShipmentDryIceDetail() != null) {
            _hashCode += getShipmentDryIceDetail().hashCode();
        }
        if (getHomeDeliveryPremiumDetail() != null) {
            _hashCode += getHomeDeliveryPremiumDetail().hashCode();
        }
        if (getEtdDetail() != null) {
            _hashCode += getEtdDetail().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ShipmentSpecialServicesRequested.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "ShipmentSpecialServicesRequested"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("specialServiceTypes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "SpecialServiceTypes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "ShipmentSpecialServiceType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codDetail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "CodDetail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "CodDetail"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codCollectionAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "CodCollectionAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "Money"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("holdAtLocationDetail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "HoldAtLocationDetail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "HoldAtLocationDetail"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EMailNotificationDetail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "EMailNotificationDetail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "EMailNotificationDetail"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returnShipmentDetail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "ReturnShipmentDetail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "ReturnShipmentDetail"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pendingShipmentDetail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "PendingShipmentDetail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "PendingShipmentDetail"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shipmentDryIceDetail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "ShipmentDryIceDetail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "ShipmentDryIceDetail"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("homeDeliveryPremiumDetail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "HomeDeliveryPremiumDetail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "HomeDeliveryPremiumDetail"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("etdDetail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "EtdDetail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "EtdDetail"));
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
