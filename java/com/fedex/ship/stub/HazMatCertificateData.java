/**
 * HazMatCertificateData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.fedex.ship.stub;


/**
 * to be included in the OP-950 (Hazardous Materials Certificate)
 * returned in Close reply
 */
public class HazMatCertificateData  implements java.io.Serializable {
    /* which broad class (as established by the United States DOT)
     * the contents of this shipment falls into; The user should be aware
     * that these (up to three) 50-character elements will actually be formatted
     * on the certificate in a 25-character-wide column on up to six lines;
     * Up to 25 characters of the first element will appear on the first
     * line, and any additional characters starting with the 26th will appear
     * on a second line. The first 25 of the second element, if it exists,
     * will appear on a third line, and any additional characters starting
     * with the 26th will appear on the fourth line. The first 25 characters
     * of the third element will appear on a fifth line, and any additional
     * characters starting with the 26th will appear on a sixth line. */
    private java.lang.String[] dotProperShippingName;

    /* which broad class (as established by the United States Department
     * of Transportation) the contents of this shipment falls into. */
    private java.lang.String dotHazardClassOrDivision;

    /* ID Number (UN or NA number), including prefix. */
    private java.lang.String dotIdNumber;

    /* Type of D.O.T. diamond hazard label, or "Ltd. Qty.", or Exemption
     * Number. */
    private java.lang.String dotLabelType;

    /* materials in certain classes, the Packing Group signifies the
     * degree of hazard. */
    private java.lang.String packingGroup;

    /* Quantity (in the given unites) of dangerous goods in shipment. */
    private java.math.BigDecimal quantity;

    /* Units in which the Quantity is expressed. */
    private java.lang.String units;

    /* 24-hour emergency response contact phone number. */
    private java.lang.String twentyFourHourEmergencyResponseContactNumber;

    /* 24-hour emergency response contact name. */
    private java.lang.String twentyFourHourEmergencyResponseContactName;

    public HazMatCertificateData() {
    }

    public HazMatCertificateData(
           java.lang.String[] dotProperShippingName,
           java.lang.String dotHazardClassOrDivision,
           java.lang.String dotIdNumber,
           java.lang.String dotLabelType,
           java.lang.String packingGroup,
           java.math.BigDecimal quantity,
           java.lang.String units,
           java.lang.String twentyFourHourEmergencyResponseContactNumber,
           java.lang.String twentyFourHourEmergencyResponseContactName) {
           this.dotProperShippingName = dotProperShippingName;
           this.dotHazardClassOrDivision = dotHazardClassOrDivision;
           this.dotIdNumber = dotIdNumber;
           this.dotLabelType = dotLabelType;
           this.packingGroup = packingGroup;
           this.quantity = quantity;
           this.units = units;
           this.twentyFourHourEmergencyResponseContactNumber = twentyFourHourEmergencyResponseContactNumber;
           this.twentyFourHourEmergencyResponseContactName = twentyFourHourEmergencyResponseContactName;
    }


    /**
     * Gets the dotProperShippingName value for this HazMatCertificateData.
     * 
     * @return dotProperShippingName   * which broad class (as established by the United States DOT)
     * the contents of this shipment falls into; The user should be aware
     * that these (up to three) 50-character elements will actually be formatted
     * on the certificate in a 25-character-wide column on up to six lines;
     * Up to 25 characters of the first element will appear on the first
     * line, and any additional characters starting with the 26th will appear
     * on a second line. The first 25 of the second element, if it exists,
     * will appear on a third line, and any additional characters starting
     * with the 26th will appear on the fourth line. The first 25 characters
     * of the third element will appear on a fifth line, and any additional
     * characters starting with the 26th will appear on a sixth line.
     */
    public java.lang.String[] getDotProperShippingName() {
        return dotProperShippingName;
    }


    /**
     * Sets the dotProperShippingName value for this HazMatCertificateData.
     * 
     * @param dotProperShippingName   * which broad class (as established by the United States DOT)
     * the contents of this shipment falls into; The user should be aware
     * that these (up to three) 50-character elements will actually be formatted
     * on the certificate in a 25-character-wide column on up to six lines;
     * Up to 25 characters of the first element will appear on the first
     * line, and any additional characters starting with the 26th will appear
     * on a second line. The first 25 of the second element, if it exists,
     * will appear on a third line, and any additional characters starting
     * with the 26th will appear on the fourth line. The first 25 characters
     * of the third element will appear on a fifth line, and any additional
     * characters starting with the 26th will appear on a sixth line.
     */
    public void setDotProperShippingName(java.lang.String[] dotProperShippingName) {
        this.dotProperShippingName = dotProperShippingName;
    }

    public java.lang.String getDotProperShippingName(int i) {
        return this.dotProperShippingName[i];
    }

    public void setDotProperShippingName(int i, java.lang.String _value) {
        this.dotProperShippingName[i] = _value;
    }


    /**
     * Gets the dotHazardClassOrDivision value for this HazMatCertificateData.
     * 
     * @return dotHazardClassOrDivision   * which broad class (as established by the United States Department
     * of Transportation) the contents of this shipment falls into.
     */
    public java.lang.String getDotHazardClassOrDivision() {
        return dotHazardClassOrDivision;
    }


    /**
     * Sets the dotHazardClassOrDivision value for this HazMatCertificateData.
     * 
     * @param dotHazardClassOrDivision   * which broad class (as established by the United States Department
     * of Transportation) the contents of this shipment falls into.
     */
    public void setDotHazardClassOrDivision(java.lang.String dotHazardClassOrDivision) {
        this.dotHazardClassOrDivision = dotHazardClassOrDivision;
    }


    /**
     * Gets the dotIdNumber value for this HazMatCertificateData.
     * 
     * @return dotIdNumber   * ID Number (UN or NA number), including prefix.
     */
    public java.lang.String getDotIdNumber() {
        return dotIdNumber;
    }


    /**
     * Sets the dotIdNumber value for this HazMatCertificateData.
     * 
     * @param dotIdNumber   * ID Number (UN or NA number), including prefix.
     */
    public void setDotIdNumber(java.lang.String dotIdNumber) {
        this.dotIdNumber = dotIdNumber;
    }


    /**
     * Gets the dotLabelType value for this HazMatCertificateData.
     * 
     * @return dotLabelType   * Type of D.O.T. diamond hazard label, or "Ltd. Qty.", or Exemption
     * Number.
     */
    public java.lang.String getDotLabelType() {
        return dotLabelType;
    }


    /**
     * Sets the dotLabelType value for this HazMatCertificateData.
     * 
     * @param dotLabelType   * Type of D.O.T. diamond hazard label, or "Ltd. Qty.", or Exemption
     * Number.
     */
    public void setDotLabelType(java.lang.String dotLabelType) {
        this.dotLabelType = dotLabelType;
    }


    /**
     * Gets the packingGroup value for this HazMatCertificateData.
     * 
     * @return packingGroup   * materials in certain classes, the Packing Group signifies the
     * degree of hazard.
     */
    public java.lang.String getPackingGroup() {
        return packingGroup;
    }


    /**
     * Sets the packingGroup value for this HazMatCertificateData.
     * 
     * @param packingGroup   * materials in certain classes, the Packing Group signifies the
     * degree of hazard.
     */
    public void setPackingGroup(java.lang.String packingGroup) {
        this.packingGroup = packingGroup;
    }


    /**
     * Gets the quantity value for this HazMatCertificateData.
     * 
     * @return quantity   * Quantity (in the given unites) of dangerous goods in shipment.
     */
    public java.math.BigDecimal getQuantity() {
        return quantity;
    }


    /**
     * Sets the quantity value for this HazMatCertificateData.
     * 
     * @param quantity   * Quantity (in the given unites) of dangerous goods in shipment.
     */
    public void setQuantity(java.math.BigDecimal quantity) {
        this.quantity = quantity;
    }


    /**
     * Gets the units value for this HazMatCertificateData.
     * 
     * @return units   * Units in which the Quantity is expressed.
     */
    public java.lang.String getUnits() {
        return units;
    }


    /**
     * Sets the units value for this HazMatCertificateData.
     * 
     * @param units   * Units in which the Quantity is expressed.
     */
    public void setUnits(java.lang.String units) {
        this.units = units;
    }


    /**
     * Gets the twentyFourHourEmergencyResponseContactNumber value for this HazMatCertificateData.
     * 
     * @return twentyFourHourEmergencyResponseContactNumber   * 24-hour emergency response contact phone number.
     */
    public java.lang.String getTwentyFourHourEmergencyResponseContactNumber() {
        return twentyFourHourEmergencyResponseContactNumber;
    }


    /**
     * Sets the twentyFourHourEmergencyResponseContactNumber value for this HazMatCertificateData.
     * 
     * @param twentyFourHourEmergencyResponseContactNumber   * 24-hour emergency response contact phone number.
     */
    public void setTwentyFourHourEmergencyResponseContactNumber(java.lang.String twentyFourHourEmergencyResponseContactNumber) {
        this.twentyFourHourEmergencyResponseContactNumber = twentyFourHourEmergencyResponseContactNumber;
    }


    /**
     * Gets the twentyFourHourEmergencyResponseContactName value for this HazMatCertificateData.
     * 
     * @return twentyFourHourEmergencyResponseContactName   * 24-hour emergency response contact name.
     */
    public java.lang.String getTwentyFourHourEmergencyResponseContactName() {
        return twentyFourHourEmergencyResponseContactName;
    }


    /**
     * Sets the twentyFourHourEmergencyResponseContactName value for this HazMatCertificateData.
     * 
     * @param twentyFourHourEmergencyResponseContactName   * 24-hour emergency response contact name.
     */
    public void setTwentyFourHourEmergencyResponseContactName(java.lang.String twentyFourHourEmergencyResponseContactName) {
        this.twentyFourHourEmergencyResponseContactName = twentyFourHourEmergencyResponseContactName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof HazMatCertificateData)) return false;
        HazMatCertificateData other = (HazMatCertificateData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.dotProperShippingName==null && other.getDotProperShippingName()==null) || 
             (this.dotProperShippingName!=null &&
              java.util.Arrays.equals(this.dotProperShippingName, other.getDotProperShippingName()))) &&
            ((this.dotHazardClassOrDivision==null && other.getDotHazardClassOrDivision()==null) || 
             (this.dotHazardClassOrDivision!=null &&
              this.dotHazardClassOrDivision.equals(other.getDotHazardClassOrDivision()))) &&
            ((this.dotIdNumber==null && other.getDotIdNumber()==null) || 
             (this.dotIdNumber!=null &&
              this.dotIdNumber.equals(other.getDotIdNumber()))) &&
            ((this.dotLabelType==null && other.getDotLabelType()==null) || 
             (this.dotLabelType!=null &&
              this.dotLabelType.equals(other.getDotLabelType()))) &&
            ((this.packingGroup==null && other.getPackingGroup()==null) || 
             (this.packingGroup!=null &&
              this.packingGroup.equals(other.getPackingGroup()))) &&
            ((this.quantity==null && other.getQuantity()==null) || 
             (this.quantity!=null &&
              this.quantity.equals(other.getQuantity()))) &&
            ((this.units==null && other.getUnits()==null) || 
             (this.units!=null &&
              this.units.equals(other.getUnits()))) &&
            ((this.twentyFourHourEmergencyResponseContactNumber==null && other.getTwentyFourHourEmergencyResponseContactNumber()==null) || 
             (this.twentyFourHourEmergencyResponseContactNumber!=null &&
              this.twentyFourHourEmergencyResponseContactNumber.equals(other.getTwentyFourHourEmergencyResponseContactNumber()))) &&
            ((this.twentyFourHourEmergencyResponseContactName==null && other.getTwentyFourHourEmergencyResponseContactName()==null) || 
             (this.twentyFourHourEmergencyResponseContactName!=null &&
              this.twentyFourHourEmergencyResponseContactName.equals(other.getTwentyFourHourEmergencyResponseContactName())));
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
        if (getDotProperShippingName() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDotProperShippingName());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDotProperShippingName(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDotHazardClassOrDivision() != null) {
            _hashCode += getDotHazardClassOrDivision().hashCode();
        }
        if (getDotIdNumber() != null) {
            _hashCode += getDotIdNumber().hashCode();
        }
        if (getDotLabelType() != null) {
            _hashCode += getDotLabelType().hashCode();
        }
        if (getPackingGroup() != null) {
            _hashCode += getPackingGroup().hashCode();
        }
        if (getQuantity() != null) {
            _hashCode += getQuantity().hashCode();
        }
        if (getUnits() != null) {
            _hashCode += getUnits().hashCode();
        }
        if (getTwentyFourHourEmergencyResponseContactNumber() != null) {
            _hashCode += getTwentyFourHourEmergencyResponseContactNumber().hashCode();
        }
        if (getTwentyFourHourEmergencyResponseContactName() != null) {
            _hashCode += getTwentyFourHourEmergencyResponseContactName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(HazMatCertificateData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "HazMatCertificateData"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dotProperShippingName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "DotProperShippingName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dotHazardClassOrDivision");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "DotHazardClassOrDivision"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dotIdNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "DotIdNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dotLabelType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "DotLabelType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("packingGroup");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "PackingGroup"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantity");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "Quantity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("units");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "Units"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("twentyFourHourEmergencyResponseContactNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "TwentyFourHourEmergencyResponseContactNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("twentyFourHourEmergencyResponseContactName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "TwentyFourHourEmergencyResponseContactName"));
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
