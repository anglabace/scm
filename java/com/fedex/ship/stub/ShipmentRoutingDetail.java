/**
 * ShipmentRoutingDetail.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.fedex.ship.stub;


/**
 * Information about the routing, origin, destination and delivery
 * of a shipment.
 */
public class ShipmentRoutingDetail  implements java.io.Serializable {
    /* The prefix portion of the URSA (Universal Routing and Sort
     * Aid) code. */
    private java.lang.String ursaPrefixCode;

    /* The suffix portion of the URSA code. */
    private java.lang.String ursaSuffixCode;

    /* The identifier of the origin location of the shipment. Express
     * only. */
    private java.lang.String originLocationId;

    private java.lang.String originServiceArea;

    /* The identifier of the destination location of the shipment.
     * Express only. */
    private java.lang.String destinationLocationId;

    private java.lang.String destinationServiceArea;

    /* The state or province code of the destination of the shipment. */
    private java.lang.String destinationLocationStateOrProvinceCode;

    /* The date the shipment is expected to be delivered. */
    private java.util.Date deliveryDate;

    /* The day of the week the shipment is expected to be delivered.
     * Express only. */
    private com.fedex.ship.stub.DayOfWeekType deliveryDay;

    /* The date the shipment is committed to be delivered. */
    private java.util.Date commitDate;

    /* The day of the week the shipment is committed to be delivered.
     * Express only. */
    private com.fedex.ship.stub.DayOfWeekType commitDay;

    /* Currently not supported. */
    private com.fedex.ship.stub.TransitTimeType transitTime;

    /* Text describing planned delivery. */
    private java.lang.String astraPlannedServiceLevel;

    /* Currently not supported. */
    private java.lang.String astraDescription;

    /* The postal code of the destination of the shipment. */
    private java.lang.String postalCode;

    /* The state or province code of the destination of the shipment. */
    private java.lang.String stateOrProvinceCode;

    /* The country code of the destination of the shipment. */
    private java.lang.String countryCode;

    /* The identifier for the airport of the destination of the shipment. */
    private java.lang.String airportId;

    public ShipmentRoutingDetail() {
    }

    public ShipmentRoutingDetail(
           java.lang.String ursaPrefixCode,
           java.lang.String ursaSuffixCode,
           java.lang.String originLocationId,
           java.lang.String originServiceArea,
           java.lang.String destinationLocationId,
           java.lang.String destinationServiceArea,
           java.lang.String destinationLocationStateOrProvinceCode,
           java.util.Date deliveryDate,
           com.fedex.ship.stub.DayOfWeekType deliveryDay,
           java.util.Date commitDate,
           com.fedex.ship.stub.DayOfWeekType commitDay,
           com.fedex.ship.stub.TransitTimeType transitTime,
           java.lang.String astraPlannedServiceLevel,
           java.lang.String astraDescription,
           java.lang.String postalCode,
           java.lang.String stateOrProvinceCode,
           java.lang.String countryCode,
           java.lang.String airportId) {
           this.ursaPrefixCode = ursaPrefixCode;
           this.ursaSuffixCode = ursaSuffixCode;
           this.originLocationId = originLocationId;
           this.originServiceArea = originServiceArea;
           this.destinationLocationId = destinationLocationId;
           this.destinationServiceArea = destinationServiceArea;
           this.destinationLocationStateOrProvinceCode = destinationLocationStateOrProvinceCode;
           this.deliveryDate = deliveryDate;
           this.deliveryDay = deliveryDay;
           this.commitDate = commitDate;
           this.commitDay = commitDay;
           this.transitTime = transitTime;
           this.astraPlannedServiceLevel = astraPlannedServiceLevel;
           this.astraDescription = astraDescription;
           this.postalCode = postalCode;
           this.stateOrProvinceCode = stateOrProvinceCode;
           this.countryCode = countryCode;
           this.airportId = airportId;
    }


    /**
     * Gets the ursaPrefixCode value for this ShipmentRoutingDetail.
     * 
     * @return ursaPrefixCode   * The prefix portion of the URSA (Universal Routing and Sort
     * Aid) code.
     */
    public java.lang.String getUrsaPrefixCode() {
        return ursaPrefixCode;
    }


    /**
     * Sets the ursaPrefixCode value for this ShipmentRoutingDetail.
     * 
     * @param ursaPrefixCode   * The prefix portion of the URSA (Universal Routing and Sort
     * Aid) code.
     */
    public void setUrsaPrefixCode(java.lang.String ursaPrefixCode) {
        this.ursaPrefixCode = ursaPrefixCode;
    }


    /**
     * Gets the ursaSuffixCode value for this ShipmentRoutingDetail.
     * 
     * @return ursaSuffixCode   * The suffix portion of the URSA code.
     */
    public java.lang.String getUrsaSuffixCode() {
        return ursaSuffixCode;
    }


    /**
     * Sets the ursaSuffixCode value for this ShipmentRoutingDetail.
     * 
     * @param ursaSuffixCode   * The suffix portion of the URSA code.
     */
    public void setUrsaSuffixCode(java.lang.String ursaSuffixCode) {
        this.ursaSuffixCode = ursaSuffixCode;
    }


    /**
     * Gets the originLocationId value for this ShipmentRoutingDetail.
     * 
     * @return originLocationId   * The identifier of the origin location of the shipment. Express
     * only.
     */
    public java.lang.String getOriginLocationId() {
        return originLocationId;
    }


    /**
     * Sets the originLocationId value for this ShipmentRoutingDetail.
     * 
     * @param originLocationId   * The identifier of the origin location of the shipment. Express
     * only.
     */
    public void setOriginLocationId(java.lang.String originLocationId) {
        this.originLocationId = originLocationId;
    }


    /**
     * Gets the originServiceArea value for this ShipmentRoutingDetail.
     * 
     * @return originServiceArea
     */
    public java.lang.String getOriginServiceArea() {
        return originServiceArea;
    }


    /**
     * Sets the originServiceArea value for this ShipmentRoutingDetail.
     * 
     * @param originServiceArea
     */
    public void setOriginServiceArea(java.lang.String originServiceArea) {
        this.originServiceArea = originServiceArea;
    }


    /**
     * Gets the destinationLocationId value for this ShipmentRoutingDetail.
     * 
     * @return destinationLocationId   * The identifier of the destination location of the shipment.
     * Express only.
     */
    public java.lang.String getDestinationLocationId() {
        return destinationLocationId;
    }


    /**
     * Sets the destinationLocationId value for this ShipmentRoutingDetail.
     * 
     * @param destinationLocationId   * The identifier of the destination location of the shipment.
     * Express only.
     */
    public void setDestinationLocationId(java.lang.String destinationLocationId) {
        this.destinationLocationId = destinationLocationId;
    }


    /**
     * Gets the destinationServiceArea value for this ShipmentRoutingDetail.
     * 
     * @return destinationServiceArea
     */
    public java.lang.String getDestinationServiceArea() {
        return destinationServiceArea;
    }


    /**
     * Sets the destinationServiceArea value for this ShipmentRoutingDetail.
     * 
     * @param destinationServiceArea
     */
    public void setDestinationServiceArea(java.lang.String destinationServiceArea) {
        this.destinationServiceArea = destinationServiceArea;
    }


    /**
     * Gets the destinationLocationStateOrProvinceCode value for this ShipmentRoutingDetail.
     * 
     * @return destinationLocationStateOrProvinceCode   * The state or province code of the destination of the shipment.
     */
    public java.lang.String getDestinationLocationStateOrProvinceCode() {
        return destinationLocationStateOrProvinceCode;
    }


    /**
     * Sets the destinationLocationStateOrProvinceCode value for this ShipmentRoutingDetail.
     * 
     * @param destinationLocationStateOrProvinceCode   * The state or province code of the destination of the shipment.
     */
    public void setDestinationLocationStateOrProvinceCode(java.lang.String destinationLocationStateOrProvinceCode) {
        this.destinationLocationStateOrProvinceCode = destinationLocationStateOrProvinceCode;
    }


    /**
     * Gets the deliveryDate value for this ShipmentRoutingDetail.
     * 
     * @return deliveryDate   * The date the shipment is expected to be delivered.
     */
    public java.util.Date getDeliveryDate() {
        return deliveryDate;
    }


    /**
     * Sets the deliveryDate value for this ShipmentRoutingDetail.
     * 
     * @param deliveryDate   * The date the shipment is expected to be delivered.
     */
    public void setDeliveryDate(java.util.Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }


    /**
     * Gets the deliveryDay value for this ShipmentRoutingDetail.
     * 
     * @return deliveryDay   * The day of the week the shipment is expected to be delivered.
     * Express only.
     */
    public com.fedex.ship.stub.DayOfWeekType getDeliveryDay() {
        return deliveryDay;
    }


    /**
     * Sets the deliveryDay value for this ShipmentRoutingDetail.
     * 
     * @param deliveryDay   * The day of the week the shipment is expected to be delivered.
     * Express only.
     */
    public void setDeliveryDay(com.fedex.ship.stub.DayOfWeekType deliveryDay) {
        this.deliveryDay = deliveryDay;
    }


    /**
     * Gets the commitDate value for this ShipmentRoutingDetail.
     * 
     * @return commitDate   * The date the shipment is committed to be delivered.
     */
    public java.util.Date getCommitDate() {
        return commitDate;
    }


    /**
     * Sets the commitDate value for this ShipmentRoutingDetail.
     * 
     * @param commitDate   * The date the shipment is committed to be delivered.
     */
    public void setCommitDate(java.util.Date commitDate) {
        this.commitDate = commitDate;
    }


    /**
     * Gets the commitDay value for this ShipmentRoutingDetail.
     * 
     * @return commitDay   * The day of the week the shipment is committed to be delivered.
     * Express only.
     */
    public com.fedex.ship.stub.DayOfWeekType getCommitDay() {
        return commitDay;
    }


    /**
     * Sets the commitDay value for this ShipmentRoutingDetail.
     * 
     * @param commitDay   * The day of the week the shipment is committed to be delivered.
     * Express only.
     */
    public void setCommitDay(com.fedex.ship.stub.DayOfWeekType commitDay) {
        this.commitDay = commitDay;
    }


    /**
     * Gets the transitTime value for this ShipmentRoutingDetail.
     * 
     * @return transitTime   * Currently not supported.
     */
    public com.fedex.ship.stub.TransitTimeType getTransitTime() {
        return transitTime;
    }


    /**
     * Sets the transitTime value for this ShipmentRoutingDetail.
     * 
     * @param transitTime   * Currently not supported.
     */
    public void setTransitTime(com.fedex.ship.stub.TransitTimeType transitTime) {
        this.transitTime = transitTime;
    }


    /**
     * Gets the astraPlannedServiceLevel value for this ShipmentRoutingDetail.
     * 
     * @return astraPlannedServiceLevel   * Text describing planned delivery.
     */
    public java.lang.String getAstraPlannedServiceLevel() {
        return astraPlannedServiceLevel;
    }


    /**
     * Sets the astraPlannedServiceLevel value for this ShipmentRoutingDetail.
     * 
     * @param astraPlannedServiceLevel   * Text describing planned delivery.
     */
    public void setAstraPlannedServiceLevel(java.lang.String astraPlannedServiceLevel) {
        this.astraPlannedServiceLevel = astraPlannedServiceLevel;
    }


    /**
     * Gets the astraDescription value for this ShipmentRoutingDetail.
     * 
     * @return astraDescription   * Currently not supported.
     */
    public java.lang.String getAstraDescription() {
        return astraDescription;
    }


    /**
     * Sets the astraDescription value for this ShipmentRoutingDetail.
     * 
     * @param astraDescription   * Currently not supported.
     */
    public void setAstraDescription(java.lang.String astraDescription) {
        this.astraDescription = astraDescription;
    }


    /**
     * Gets the postalCode value for this ShipmentRoutingDetail.
     * 
     * @return postalCode   * The postal code of the destination of the shipment.
     */
    public java.lang.String getPostalCode() {
        return postalCode;
    }


    /**
     * Sets the postalCode value for this ShipmentRoutingDetail.
     * 
     * @param postalCode   * The postal code of the destination of the shipment.
     */
    public void setPostalCode(java.lang.String postalCode) {
        this.postalCode = postalCode;
    }


    /**
     * Gets the stateOrProvinceCode value for this ShipmentRoutingDetail.
     * 
     * @return stateOrProvinceCode   * The state or province code of the destination of the shipment.
     */
    public java.lang.String getStateOrProvinceCode() {
        return stateOrProvinceCode;
    }


    /**
     * Sets the stateOrProvinceCode value for this ShipmentRoutingDetail.
     * 
     * @param stateOrProvinceCode   * The state or province code of the destination of the shipment.
     */
    public void setStateOrProvinceCode(java.lang.String stateOrProvinceCode) {
        this.stateOrProvinceCode = stateOrProvinceCode;
    }


    /**
     * Gets the countryCode value for this ShipmentRoutingDetail.
     * 
     * @return countryCode   * The country code of the destination of the shipment.
     */
    public java.lang.String getCountryCode() {
        return countryCode;
    }


    /**
     * Sets the countryCode value for this ShipmentRoutingDetail.
     * 
     * @param countryCode   * The country code of the destination of the shipment.
     */
    public void setCountryCode(java.lang.String countryCode) {
        this.countryCode = countryCode;
    }


    /**
     * Gets the airportId value for this ShipmentRoutingDetail.
     * 
     * @return airportId   * The identifier for the airport of the destination of the shipment.
     */
    public java.lang.String getAirportId() {
        return airportId;
    }


    /**
     * Sets the airportId value for this ShipmentRoutingDetail.
     * 
     * @param airportId   * The identifier for the airport of the destination of the shipment.
     */
    public void setAirportId(java.lang.String airportId) {
        this.airportId = airportId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ShipmentRoutingDetail)) return false;
        ShipmentRoutingDetail other = (ShipmentRoutingDetail) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ursaPrefixCode==null && other.getUrsaPrefixCode()==null) || 
             (this.ursaPrefixCode!=null &&
              this.ursaPrefixCode.equals(other.getUrsaPrefixCode()))) &&
            ((this.ursaSuffixCode==null && other.getUrsaSuffixCode()==null) || 
             (this.ursaSuffixCode!=null &&
              this.ursaSuffixCode.equals(other.getUrsaSuffixCode()))) &&
            ((this.originLocationId==null && other.getOriginLocationId()==null) || 
             (this.originLocationId!=null &&
              this.originLocationId.equals(other.getOriginLocationId()))) &&
            ((this.originServiceArea==null && other.getOriginServiceArea()==null) || 
             (this.originServiceArea!=null &&
              this.originServiceArea.equals(other.getOriginServiceArea()))) &&
            ((this.destinationLocationId==null && other.getDestinationLocationId()==null) || 
             (this.destinationLocationId!=null &&
              this.destinationLocationId.equals(other.getDestinationLocationId()))) &&
            ((this.destinationServiceArea==null && other.getDestinationServiceArea()==null) || 
             (this.destinationServiceArea!=null &&
              this.destinationServiceArea.equals(other.getDestinationServiceArea()))) &&
            ((this.destinationLocationStateOrProvinceCode==null && other.getDestinationLocationStateOrProvinceCode()==null) || 
             (this.destinationLocationStateOrProvinceCode!=null &&
              this.destinationLocationStateOrProvinceCode.equals(other.getDestinationLocationStateOrProvinceCode()))) &&
            ((this.deliveryDate==null && other.getDeliveryDate()==null) || 
             (this.deliveryDate!=null &&
              this.deliveryDate.equals(other.getDeliveryDate()))) &&
            ((this.deliveryDay==null && other.getDeliveryDay()==null) || 
             (this.deliveryDay!=null &&
              this.deliveryDay.equals(other.getDeliveryDay()))) &&
            ((this.commitDate==null && other.getCommitDate()==null) || 
             (this.commitDate!=null &&
              this.commitDate.equals(other.getCommitDate()))) &&
            ((this.commitDay==null && other.getCommitDay()==null) || 
             (this.commitDay!=null &&
              this.commitDay.equals(other.getCommitDay()))) &&
            ((this.transitTime==null && other.getTransitTime()==null) || 
             (this.transitTime!=null &&
              this.transitTime.equals(other.getTransitTime()))) &&
            ((this.astraPlannedServiceLevel==null && other.getAstraPlannedServiceLevel()==null) || 
             (this.astraPlannedServiceLevel!=null &&
              this.astraPlannedServiceLevel.equals(other.getAstraPlannedServiceLevel()))) &&
            ((this.astraDescription==null && other.getAstraDescription()==null) || 
             (this.astraDescription!=null &&
              this.astraDescription.equals(other.getAstraDescription()))) &&
            ((this.postalCode==null && other.getPostalCode()==null) || 
             (this.postalCode!=null &&
              this.postalCode.equals(other.getPostalCode()))) &&
            ((this.stateOrProvinceCode==null && other.getStateOrProvinceCode()==null) || 
             (this.stateOrProvinceCode!=null &&
              this.stateOrProvinceCode.equals(other.getStateOrProvinceCode()))) &&
            ((this.countryCode==null && other.getCountryCode()==null) || 
             (this.countryCode!=null &&
              this.countryCode.equals(other.getCountryCode()))) &&
            ((this.airportId==null && other.getAirportId()==null) || 
             (this.airportId!=null &&
              this.airportId.equals(other.getAirportId())));
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
        if (getUrsaPrefixCode() != null) {
            _hashCode += getUrsaPrefixCode().hashCode();
        }
        if (getUrsaSuffixCode() != null) {
            _hashCode += getUrsaSuffixCode().hashCode();
        }
        if (getOriginLocationId() != null) {
            _hashCode += getOriginLocationId().hashCode();
        }
        if (getOriginServiceArea() != null) {
            _hashCode += getOriginServiceArea().hashCode();
        }
        if (getDestinationLocationId() != null) {
            _hashCode += getDestinationLocationId().hashCode();
        }
        if (getDestinationServiceArea() != null) {
            _hashCode += getDestinationServiceArea().hashCode();
        }
        if (getDestinationLocationStateOrProvinceCode() != null) {
            _hashCode += getDestinationLocationStateOrProvinceCode().hashCode();
        }
        if (getDeliveryDate() != null) {
            _hashCode += getDeliveryDate().hashCode();
        }
        if (getDeliveryDay() != null) {
            _hashCode += getDeliveryDay().hashCode();
        }
        if (getCommitDate() != null) {
            _hashCode += getCommitDate().hashCode();
        }
        if (getCommitDay() != null) {
            _hashCode += getCommitDay().hashCode();
        }
        if (getTransitTime() != null) {
            _hashCode += getTransitTime().hashCode();
        }
        if (getAstraPlannedServiceLevel() != null) {
            _hashCode += getAstraPlannedServiceLevel().hashCode();
        }
        if (getAstraDescription() != null) {
            _hashCode += getAstraDescription().hashCode();
        }
        if (getPostalCode() != null) {
            _hashCode += getPostalCode().hashCode();
        }
        if (getStateOrProvinceCode() != null) {
            _hashCode += getStateOrProvinceCode().hashCode();
        }
        if (getCountryCode() != null) {
            _hashCode += getCountryCode().hashCode();
        }
        if (getAirportId() != null) {
            _hashCode += getAirportId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ShipmentRoutingDetail.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "ShipmentRoutingDetail"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ursaPrefixCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "UrsaPrefixCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ursaSuffixCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "UrsaSuffixCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("originLocationId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "OriginLocationId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("originServiceArea");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "OriginServiceArea"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destinationLocationId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "DestinationLocationId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destinationServiceArea");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "DestinationServiceArea"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destinationLocationStateOrProvinceCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "DestinationLocationStateOrProvinceCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deliveryDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "DeliveryDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deliveryDay");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "DeliveryDay"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "DayOfWeekType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("commitDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "CommitDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("commitDay");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "CommitDay"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "DayOfWeekType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transitTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "TransitTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "TransitTimeType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("astraPlannedServiceLevel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "AstraPlannedServiceLevel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("astraDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "AstraDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("postalCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "PostalCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stateOrProvinceCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "StateOrProvinceCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("countryCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "CountryCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("airportId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "AirportId"));
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
