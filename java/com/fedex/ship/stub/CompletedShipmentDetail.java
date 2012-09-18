/**
 * CompletedShipmentDetail.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.fedex.ship.stub;

public class CompletedShipmentDetail  implements java.io.Serializable {
    /* Indicates whether or not this is a US Domestic shipment. */
    private java.lang.Boolean usDomestic;

    /* Indicates the carrier that will be used to deliver this shipment. */
    private com.fedex.ship.stub.CarrierCodeType carrierCode;

    /* The master tracking number and form id of this multiple piece
     * shipment. This information is to be provided for each subsequent of
     * a multiple piece shipment. */
    private com.fedex.ship.stub.TrackingId masterTrackingId;

    /* Description of the FedEx service used for this shipment. Currently
     * not supported. */
    private java.lang.String serviceTypeDescription;

    /* Description of the packaging used for this shipment. Currently
     * not supported. */
    private java.lang.String packagingDescription;

    /* Information about the routing, origin, destination and delivery
     * of a shipment. */
    private com.fedex.ship.stub.ShipmentRoutingDetail routingDetail;

    /* The code indicating the Ground service type and any special
     * services associated with this shipment. Currently not supported. */
    private java.lang.String groundServiceCode;

    /* Access Details about PendingShipment information. */
    private com.fedex.ship.stub.PendingShipmentAccessDetail accessDetail;

    /* Only used in the reply to tag requests. */
    private com.fedex.ship.stub.CompletedTagDetail tagDetail;

    /* Provides reply information specific to SmartPost shipments. */
    private com.fedex.ship.stub.CompletedSmartPostDetail smartPostDetail;

    /* The rating information for this shipment. Note, this is a courtesy
     * rate and may be different from what is invoiced. */
    private com.fedex.ship.stub.ShipmentRating shipmentRating;

    /* Information about the COD return shipment. */
    private com.fedex.ship.stub.CodReturnShipmentDetail codReturnDetail;

    /* Indicates whether or not this shipment is eligible for a money
     * back guarantee. */
    private java.lang.Boolean ineligibleForMoneyBackGuarantee;

    /* General field for exporting-country-specific export data (e.g.
     * B13A for CA, FTSR Exemption or AES Citation for US. */
    private java.lang.String exportComplianceStatement;

    private com.fedex.ship.stub.CompletedEtdDetail completedEtdDetail;

    /* All shipment-level shipping documents (other than labels and
     * barcodes). */
    private com.fedex.ship.stub.ShippingDocument[] shipmentDocuments;

    /* Package level details about this package. */
    private com.fedex.ship.stub.CompletedPackageDetail[] completedPackageDetails;

    public CompletedShipmentDetail() {
    }

    public CompletedShipmentDetail(
           java.lang.Boolean usDomestic,
           com.fedex.ship.stub.CarrierCodeType carrierCode,
           com.fedex.ship.stub.TrackingId masterTrackingId,
           java.lang.String serviceTypeDescription,
           java.lang.String packagingDescription,
           com.fedex.ship.stub.ShipmentRoutingDetail routingDetail,
           java.lang.String groundServiceCode,
           com.fedex.ship.stub.PendingShipmentAccessDetail accessDetail,
           com.fedex.ship.stub.CompletedTagDetail tagDetail,
           com.fedex.ship.stub.CompletedSmartPostDetail smartPostDetail,
           com.fedex.ship.stub.ShipmentRating shipmentRating,
           com.fedex.ship.stub.CodReturnShipmentDetail codReturnDetail,
           java.lang.Boolean ineligibleForMoneyBackGuarantee,
           java.lang.String exportComplianceStatement,
           com.fedex.ship.stub.CompletedEtdDetail completedEtdDetail,
           com.fedex.ship.stub.ShippingDocument[] shipmentDocuments,
           com.fedex.ship.stub.CompletedPackageDetail[] completedPackageDetails) {
           this.usDomestic = usDomestic;
           this.carrierCode = carrierCode;
           this.masterTrackingId = masterTrackingId;
           this.serviceTypeDescription = serviceTypeDescription;
           this.packagingDescription = packagingDescription;
           this.routingDetail = routingDetail;
           this.groundServiceCode = groundServiceCode;
           this.accessDetail = accessDetail;
           this.tagDetail = tagDetail;
           this.smartPostDetail = smartPostDetail;
           this.shipmentRating = shipmentRating;
           this.codReturnDetail = codReturnDetail;
           this.ineligibleForMoneyBackGuarantee = ineligibleForMoneyBackGuarantee;
           this.exportComplianceStatement = exportComplianceStatement;
           this.completedEtdDetail = completedEtdDetail;
           this.shipmentDocuments = shipmentDocuments;
           this.completedPackageDetails = completedPackageDetails;
    }


    /**
     * Gets the usDomestic value for this CompletedShipmentDetail.
     * 
     * @return usDomestic   * Indicates whether or not this is a US Domestic shipment.
     */
    public java.lang.Boolean getUsDomestic() {
        return usDomestic;
    }


    /**
     * Sets the usDomestic value for this CompletedShipmentDetail.
     * 
     * @param usDomestic   * Indicates whether or not this is a US Domestic shipment.
     */
    public void setUsDomestic(java.lang.Boolean usDomestic) {
        this.usDomestic = usDomestic;
    }


    /**
     * Gets the carrierCode value for this CompletedShipmentDetail.
     * 
     * @return carrierCode   * Indicates the carrier that will be used to deliver this shipment.
     */
    public com.fedex.ship.stub.CarrierCodeType getCarrierCode() {
        return carrierCode;
    }


    /**
     * Sets the carrierCode value for this CompletedShipmentDetail.
     * 
     * @param carrierCode   * Indicates the carrier that will be used to deliver this shipment.
     */
    public void setCarrierCode(com.fedex.ship.stub.CarrierCodeType carrierCode) {
        this.carrierCode = carrierCode;
    }


    /**
     * Gets the masterTrackingId value for this CompletedShipmentDetail.
     * 
     * @return masterTrackingId   * The master tracking number and form id of this multiple piece
     * shipment. This information is to be provided for each subsequent of
     * a multiple piece shipment.
     */
    public com.fedex.ship.stub.TrackingId getMasterTrackingId() {
        return masterTrackingId;
    }


    /**
     * Sets the masterTrackingId value for this CompletedShipmentDetail.
     * 
     * @param masterTrackingId   * The master tracking number and form id of this multiple piece
     * shipment. This information is to be provided for each subsequent of
     * a multiple piece shipment.
     */
    public void setMasterTrackingId(com.fedex.ship.stub.TrackingId masterTrackingId) {
        this.masterTrackingId = masterTrackingId;
    }


    /**
     * Gets the serviceTypeDescription value for this CompletedShipmentDetail.
     * 
     * @return serviceTypeDescription   * Description of the FedEx service used for this shipment. Currently
     * not supported.
     */
    public java.lang.String getServiceTypeDescription() {
        return serviceTypeDescription;
    }


    /**
     * Sets the serviceTypeDescription value for this CompletedShipmentDetail.
     * 
     * @param serviceTypeDescription   * Description of the FedEx service used for this shipment. Currently
     * not supported.
     */
    public void setServiceTypeDescription(java.lang.String serviceTypeDescription) {
        this.serviceTypeDescription = serviceTypeDescription;
    }


    /**
     * Gets the packagingDescription value for this CompletedShipmentDetail.
     * 
     * @return packagingDescription   * Description of the packaging used for this shipment. Currently
     * not supported.
     */
    public java.lang.String getPackagingDescription() {
        return packagingDescription;
    }


    /**
     * Sets the packagingDescription value for this CompletedShipmentDetail.
     * 
     * @param packagingDescription   * Description of the packaging used for this shipment. Currently
     * not supported.
     */
    public void setPackagingDescription(java.lang.String packagingDescription) {
        this.packagingDescription = packagingDescription;
    }


    /**
     * Gets the routingDetail value for this CompletedShipmentDetail.
     * 
     * @return routingDetail   * Information about the routing, origin, destination and delivery
     * of a shipment.
     */
    public com.fedex.ship.stub.ShipmentRoutingDetail getRoutingDetail() {
        return routingDetail;
    }


    /**
     * Sets the routingDetail value for this CompletedShipmentDetail.
     * 
     * @param routingDetail   * Information about the routing, origin, destination and delivery
     * of a shipment.
     */
    public void setRoutingDetail(com.fedex.ship.stub.ShipmentRoutingDetail routingDetail) {
        this.routingDetail = routingDetail;
    }


    /**
     * Gets the groundServiceCode value for this CompletedShipmentDetail.
     * 
     * @return groundServiceCode   * The code indicating the Ground service type and any special
     * services associated with this shipment. Currently not supported.
     */
    public java.lang.String getGroundServiceCode() {
        return groundServiceCode;
    }


    /**
     * Sets the groundServiceCode value for this CompletedShipmentDetail.
     * 
     * @param groundServiceCode   * The code indicating the Ground service type and any special
     * services associated with this shipment. Currently not supported.
     */
    public void setGroundServiceCode(java.lang.String groundServiceCode) {
        this.groundServiceCode = groundServiceCode;
    }


    /**
     * Gets the accessDetail value for this CompletedShipmentDetail.
     * 
     * @return accessDetail   * Access Details about PendingShipment information.
     */
    public com.fedex.ship.stub.PendingShipmentAccessDetail getAccessDetail() {
        return accessDetail;
    }


    /**
     * Sets the accessDetail value for this CompletedShipmentDetail.
     * 
     * @param accessDetail   * Access Details about PendingShipment information.
     */
    public void setAccessDetail(com.fedex.ship.stub.PendingShipmentAccessDetail accessDetail) {
        this.accessDetail = accessDetail;
    }


    /**
     * Gets the tagDetail value for this CompletedShipmentDetail.
     * 
     * @return tagDetail   * Only used in the reply to tag requests.
     */
    public com.fedex.ship.stub.CompletedTagDetail getTagDetail() {
        return tagDetail;
    }


    /**
     * Sets the tagDetail value for this CompletedShipmentDetail.
     * 
     * @param tagDetail   * Only used in the reply to tag requests.
     */
    public void setTagDetail(com.fedex.ship.stub.CompletedTagDetail tagDetail) {
        this.tagDetail = tagDetail;
    }


    /**
     * Gets the smartPostDetail value for this CompletedShipmentDetail.
     * 
     * @return smartPostDetail   * Provides reply information specific to SmartPost shipments.
     */
    public com.fedex.ship.stub.CompletedSmartPostDetail getSmartPostDetail() {
        return smartPostDetail;
    }


    /**
     * Sets the smartPostDetail value for this CompletedShipmentDetail.
     * 
     * @param smartPostDetail   * Provides reply information specific to SmartPost shipments.
     */
    public void setSmartPostDetail(com.fedex.ship.stub.CompletedSmartPostDetail smartPostDetail) {
        this.smartPostDetail = smartPostDetail;
    }


    /**
     * Gets the shipmentRating value for this CompletedShipmentDetail.
     * 
     * @return shipmentRating   * The rating information for this shipment. Note, this is a courtesy
     * rate and may be different from what is invoiced.
     */
    public com.fedex.ship.stub.ShipmentRating getShipmentRating() {
        return shipmentRating;
    }


    /**
     * Sets the shipmentRating value for this CompletedShipmentDetail.
     * 
     * @param shipmentRating   * The rating information for this shipment. Note, this is a courtesy
     * rate and may be different from what is invoiced.
     */
    public void setShipmentRating(com.fedex.ship.stub.ShipmentRating shipmentRating) {
        this.shipmentRating = shipmentRating;
    }


    /**
     * Gets the codReturnDetail value for this CompletedShipmentDetail.
     * 
     * @return codReturnDetail   * Information about the COD return shipment.
     */
    public com.fedex.ship.stub.CodReturnShipmentDetail getCodReturnDetail() {
        return codReturnDetail;
    }


    /**
     * Sets the codReturnDetail value for this CompletedShipmentDetail.
     * 
     * @param codReturnDetail   * Information about the COD return shipment.
     */
    public void setCodReturnDetail(com.fedex.ship.stub.CodReturnShipmentDetail codReturnDetail) {
        this.codReturnDetail = codReturnDetail;
    }


    /**
     * Gets the ineligibleForMoneyBackGuarantee value for this CompletedShipmentDetail.
     * 
     * @return ineligibleForMoneyBackGuarantee   * Indicates whether or not this shipment is eligible for a money
     * back guarantee.
     */
    public java.lang.Boolean getIneligibleForMoneyBackGuarantee() {
        return ineligibleForMoneyBackGuarantee;
    }


    /**
     * Sets the ineligibleForMoneyBackGuarantee value for this CompletedShipmentDetail.
     * 
     * @param ineligibleForMoneyBackGuarantee   * Indicates whether or not this shipment is eligible for a money
     * back guarantee.
     */
    public void setIneligibleForMoneyBackGuarantee(java.lang.Boolean ineligibleForMoneyBackGuarantee) {
        this.ineligibleForMoneyBackGuarantee = ineligibleForMoneyBackGuarantee;
    }


    /**
     * Gets the exportComplianceStatement value for this CompletedShipmentDetail.
     * 
     * @return exportComplianceStatement   * General field for exporting-country-specific export data (e.g.
     * B13A for CA, FTSR Exemption or AES Citation for US.
     */
    public java.lang.String getExportComplianceStatement() {
        return exportComplianceStatement;
    }


    /**
     * Sets the exportComplianceStatement value for this CompletedShipmentDetail.
     * 
     * @param exportComplianceStatement   * General field for exporting-country-specific export data (e.g.
     * B13A for CA, FTSR Exemption or AES Citation for US.
     */
    public void setExportComplianceStatement(java.lang.String exportComplianceStatement) {
        this.exportComplianceStatement = exportComplianceStatement;
    }


    /**
     * Gets the completedEtdDetail value for this CompletedShipmentDetail.
     * 
     * @return completedEtdDetail
     */
    public com.fedex.ship.stub.CompletedEtdDetail getCompletedEtdDetail() {
        return completedEtdDetail;
    }


    /**
     * Sets the completedEtdDetail value for this CompletedShipmentDetail.
     * 
     * @param completedEtdDetail
     */
    public void setCompletedEtdDetail(com.fedex.ship.stub.CompletedEtdDetail completedEtdDetail) {
        this.completedEtdDetail = completedEtdDetail;
    }


    /**
     * Gets the shipmentDocuments value for this CompletedShipmentDetail.
     * 
     * @return shipmentDocuments   * All shipment-level shipping documents (other than labels and
     * barcodes).
     */
    public com.fedex.ship.stub.ShippingDocument[] getShipmentDocuments() {
        return shipmentDocuments;
    }


    /**
     * Sets the shipmentDocuments value for this CompletedShipmentDetail.
     * 
     * @param shipmentDocuments   * All shipment-level shipping documents (other than labels and
     * barcodes).
     */
    public void setShipmentDocuments(com.fedex.ship.stub.ShippingDocument[] shipmentDocuments) {
        this.shipmentDocuments = shipmentDocuments;
    }

    public com.fedex.ship.stub.ShippingDocument getShipmentDocuments(int i) {
        return this.shipmentDocuments[i];
    }

    public void setShipmentDocuments(int i, com.fedex.ship.stub.ShippingDocument _value) {
        this.shipmentDocuments[i] = _value;
    }


    /**
     * Gets the completedPackageDetails value for this CompletedShipmentDetail.
     * 
     * @return completedPackageDetails   * Package level details about this package.
     */
    public com.fedex.ship.stub.CompletedPackageDetail[] getCompletedPackageDetails() {
        return completedPackageDetails;
    }


    /**
     * Sets the completedPackageDetails value for this CompletedShipmentDetail.
     * 
     * @param completedPackageDetails   * Package level details about this package.
     */
    public void setCompletedPackageDetails(com.fedex.ship.stub.CompletedPackageDetail[] completedPackageDetails) {
        this.completedPackageDetails = completedPackageDetails;
    }

    public com.fedex.ship.stub.CompletedPackageDetail getCompletedPackageDetails(int i) {
        return this.completedPackageDetails[i];
    }

    public void setCompletedPackageDetails(int i, com.fedex.ship.stub.CompletedPackageDetail _value) {
        this.completedPackageDetails[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CompletedShipmentDetail)) return false;
        CompletedShipmentDetail other = (CompletedShipmentDetail) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.usDomestic==null && other.getUsDomestic()==null) || 
             (this.usDomestic!=null &&
              this.usDomestic.equals(other.getUsDomestic()))) &&
            ((this.carrierCode==null && other.getCarrierCode()==null) || 
             (this.carrierCode!=null &&
              this.carrierCode.equals(other.getCarrierCode()))) &&
            ((this.masterTrackingId==null && other.getMasterTrackingId()==null) || 
             (this.masterTrackingId!=null &&
              this.masterTrackingId.equals(other.getMasterTrackingId()))) &&
            ((this.serviceTypeDescription==null && other.getServiceTypeDescription()==null) || 
             (this.serviceTypeDescription!=null &&
              this.serviceTypeDescription.equals(other.getServiceTypeDescription()))) &&
            ((this.packagingDescription==null && other.getPackagingDescription()==null) || 
             (this.packagingDescription!=null &&
              this.packagingDescription.equals(other.getPackagingDescription()))) &&
            ((this.routingDetail==null && other.getRoutingDetail()==null) || 
             (this.routingDetail!=null &&
              this.routingDetail.equals(other.getRoutingDetail()))) &&
            ((this.groundServiceCode==null && other.getGroundServiceCode()==null) || 
             (this.groundServiceCode!=null &&
              this.groundServiceCode.equals(other.getGroundServiceCode()))) &&
            ((this.accessDetail==null && other.getAccessDetail()==null) || 
             (this.accessDetail!=null &&
              this.accessDetail.equals(other.getAccessDetail()))) &&
            ((this.tagDetail==null && other.getTagDetail()==null) || 
             (this.tagDetail!=null &&
              this.tagDetail.equals(other.getTagDetail()))) &&
            ((this.smartPostDetail==null && other.getSmartPostDetail()==null) || 
             (this.smartPostDetail!=null &&
              this.smartPostDetail.equals(other.getSmartPostDetail()))) &&
            ((this.shipmentRating==null && other.getShipmentRating()==null) || 
             (this.shipmentRating!=null &&
              this.shipmentRating.equals(other.getShipmentRating()))) &&
            ((this.codReturnDetail==null && other.getCodReturnDetail()==null) || 
             (this.codReturnDetail!=null &&
              this.codReturnDetail.equals(other.getCodReturnDetail()))) &&
            ((this.ineligibleForMoneyBackGuarantee==null && other.getIneligibleForMoneyBackGuarantee()==null) || 
             (this.ineligibleForMoneyBackGuarantee!=null &&
              this.ineligibleForMoneyBackGuarantee.equals(other.getIneligibleForMoneyBackGuarantee()))) &&
            ((this.exportComplianceStatement==null && other.getExportComplianceStatement()==null) || 
             (this.exportComplianceStatement!=null &&
              this.exportComplianceStatement.equals(other.getExportComplianceStatement()))) &&
            ((this.completedEtdDetail==null && other.getCompletedEtdDetail()==null) || 
             (this.completedEtdDetail!=null &&
              this.completedEtdDetail.equals(other.getCompletedEtdDetail()))) &&
            ((this.shipmentDocuments==null && other.getShipmentDocuments()==null) || 
             (this.shipmentDocuments!=null &&
              java.util.Arrays.equals(this.shipmentDocuments, other.getShipmentDocuments()))) &&
            ((this.completedPackageDetails==null && other.getCompletedPackageDetails()==null) || 
             (this.completedPackageDetails!=null &&
              java.util.Arrays.equals(this.completedPackageDetails, other.getCompletedPackageDetails())));
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
        if (getUsDomestic() != null) {
            _hashCode += getUsDomestic().hashCode();
        }
        if (getCarrierCode() != null) {
            _hashCode += getCarrierCode().hashCode();
        }
        if (getMasterTrackingId() != null) {
            _hashCode += getMasterTrackingId().hashCode();
        }
        if (getServiceTypeDescription() != null) {
            _hashCode += getServiceTypeDescription().hashCode();
        }
        if (getPackagingDescription() != null) {
            _hashCode += getPackagingDescription().hashCode();
        }
        if (getRoutingDetail() != null) {
            _hashCode += getRoutingDetail().hashCode();
        }
        if (getGroundServiceCode() != null) {
            _hashCode += getGroundServiceCode().hashCode();
        }
        if (getAccessDetail() != null) {
            _hashCode += getAccessDetail().hashCode();
        }
        if (getTagDetail() != null) {
            _hashCode += getTagDetail().hashCode();
        }
        if (getSmartPostDetail() != null) {
            _hashCode += getSmartPostDetail().hashCode();
        }
        if (getShipmentRating() != null) {
            _hashCode += getShipmentRating().hashCode();
        }
        if (getCodReturnDetail() != null) {
            _hashCode += getCodReturnDetail().hashCode();
        }
        if (getIneligibleForMoneyBackGuarantee() != null) {
            _hashCode += getIneligibleForMoneyBackGuarantee().hashCode();
        }
        if (getExportComplianceStatement() != null) {
            _hashCode += getExportComplianceStatement().hashCode();
        }
        if (getCompletedEtdDetail() != null) {
            _hashCode += getCompletedEtdDetail().hashCode();
        }
        if (getShipmentDocuments() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getShipmentDocuments());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getShipmentDocuments(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCompletedPackageDetails() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCompletedPackageDetails());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCompletedPackageDetails(), i);
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
        new org.apache.axis.description.TypeDesc(CompletedShipmentDetail.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "CompletedShipmentDetail"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("usDomestic");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "UsDomestic"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("carrierCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "CarrierCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "CarrierCodeType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("masterTrackingId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "MasterTrackingId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "TrackingId"));
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
        elemField.setFieldName("routingDetail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "RoutingDetail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "ShipmentRoutingDetail"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("groundServiceCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "GroundServiceCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accessDetail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "AccessDetail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "PendingShipmentAccessDetail"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tagDetail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "TagDetail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "CompletedTagDetail"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("smartPostDetail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "SmartPostDetail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "CompletedSmartPostDetail"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shipmentRating");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "ShipmentRating"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "ShipmentRating"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codReturnDetail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "CodReturnDetail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "CodReturnShipmentDetail"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ineligibleForMoneyBackGuarantee");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "IneligibleForMoneyBackGuarantee"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("exportComplianceStatement");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "ExportComplianceStatement"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("completedEtdDetail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "CompletedEtdDetail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "CompletedEtdDetail"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shipmentDocuments");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "ShipmentDocuments"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "ShippingDocument"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("completedPackageDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "CompletedPackageDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "CompletedPackageDetail"));
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
