/**
 * CompletedPackageDetail.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.fedex.ship.stub;

public class CompletedPackageDetail  implements java.io.Serializable {
    /* The package sequence number of this package in a multiple piece
     * shipment. */
    private org.apache.axis.types.PositiveInteger sequenceNumber;

    /* The Tracking number and form id for this package. */
    private com.fedex.ship.stub.TrackingId[] trackingIds;

    /* Used with request containing PACKAGE_GROUPS, to identify which
     * group of identical packages was used to produce a reply item. */
    private org.apache.axis.types.NonNegativeInteger groupNumber;

    /* Oversize class for this package. */
    private com.fedex.ship.stub.OversizeClassType oversizeClass;

    /* The rating information for this package. Note, this is a courtesy
     * rate and may be different from what is invoiced. */
    private com.fedex.ship.stub.PackageRating packageRating;

    /* The data that is used to from the Astra and 2DCommon barcodes
     * for the label.. */
    private com.fedex.ship.stub.PackageBarcodes barcodes;

    /* The textual description of the special service applied to the
     * package. */
    private java.lang.String astraHandlingText;

    /* The label image or printer commands to print the label. */
    private com.fedex.ship.stub.ShippingDocument label;

    /* All package-level shipping documents (other than labels and
     * barcodes). */
    private com.fedex.ship.stub.ShippingDocument[] packageDocuments;

    /* Information about the COD return shipment. */
    private com.fedex.ship.stub.CodReturnPackageDetail codReturnDetail;

    /* The actual signature option applied to this shipment. This
     * could be different than the signature option requested if a conflict
     * occured with other service features in the shipment. */
    private com.fedex.ship.stub.SignatureOptionType signatureOption;

    public CompletedPackageDetail() {
    }

    public CompletedPackageDetail(
           org.apache.axis.types.PositiveInteger sequenceNumber,
           com.fedex.ship.stub.TrackingId[] trackingIds,
           org.apache.axis.types.NonNegativeInteger groupNumber,
           com.fedex.ship.stub.OversizeClassType oversizeClass,
           com.fedex.ship.stub.PackageRating packageRating,
           com.fedex.ship.stub.PackageBarcodes barcodes,
           java.lang.String astraHandlingText,
           com.fedex.ship.stub.ShippingDocument label,
           com.fedex.ship.stub.ShippingDocument[] packageDocuments,
           com.fedex.ship.stub.CodReturnPackageDetail codReturnDetail,
           com.fedex.ship.stub.SignatureOptionType signatureOption) {
           this.sequenceNumber = sequenceNumber;
           this.trackingIds = trackingIds;
           this.groupNumber = groupNumber;
           this.oversizeClass = oversizeClass;
           this.packageRating = packageRating;
           this.barcodes = barcodes;
           this.astraHandlingText = astraHandlingText;
           this.label = label;
           this.packageDocuments = packageDocuments;
           this.codReturnDetail = codReturnDetail;
           this.signatureOption = signatureOption;
    }


    /**
     * Gets the sequenceNumber value for this CompletedPackageDetail.
     * 
     * @return sequenceNumber   * The package sequence number of this package in a multiple piece
     * shipment.
     */
    public org.apache.axis.types.PositiveInteger getSequenceNumber() {
        return sequenceNumber;
    }


    /**
     * Sets the sequenceNumber value for this CompletedPackageDetail.
     * 
     * @param sequenceNumber   * The package sequence number of this package in a multiple piece
     * shipment.
     */
    public void setSequenceNumber(org.apache.axis.types.PositiveInteger sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }


    /**
     * Gets the trackingIds value for this CompletedPackageDetail.
     * 
     * @return trackingIds   * The Tracking number and form id for this package.
     */
    public com.fedex.ship.stub.TrackingId[] getTrackingIds() {
        return trackingIds;
    }


    /**
     * Sets the trackingIds value for this CompletedPackageDetail.
     * 
     * @param trackingIds   * The Tracking number and form id for this package.
     */
    public void setTrackingIds(com.fedex.ship.stub.TrackingId[] trackingIds) {
        this.trackingIds = trackingIds;
    }

    public com.fedex.ship.stub.TrackingId getTrackingIds(int i) {
        return this.trackingIds[i];
    }

    public void setTrackingIds(int i, com.fedex.ship.stub.TrackingId _value) {
        this.trackingIds[i] = _value;
    }


    /**
     * Gets the groupNumber value for this CompletedPackageDetail.
     * 
     * @return groupNumber   * Used with request containing PACKAGE_GROUPS, to identify which
     * group of identical packages was used to produce a reply item.
     */
    public org.apache.axis.types.NonNegativeInteger getGroupNumber() {
        return groupNumber;
    }


    /**
     * Sets the groupNumber value for this CompletedPackageDetail.
     * 
     * @param groupNumber   * Used with request containing PACKAGE_GROUPS, to identify which
     * group of identical packages was used to produce a reply item.
     */
    public void setGroupNumber(org.apache.axis.types.NonNegativeInteger groupNumber) {
        this.groupNumber = groupNumber;
    }


    /**
     * Gets the oversizeClass value for this CompletedPackageDetail.
     * 
     * @return oversizeClass   * Oversize class for this package.
     */
    public com.fedex.ship.stub.OversizeClassType getOversizeClass() {
        return oversizeClass;
    }


    /**
     * Sets the oversizeClass value for this CompletedPackageDetail.
     * 
     * @param oversizeClass   * Oversize class for this package.
     */
    public void setOversizeClass(com.fedex.ship.stub.OversizeClassType oversizeClass) {
        this.oversizeClass = oversizeClass;
    }


    /**
     * Gets the packageRating value for this CompletedPackageDetail.
     * 
     * @return packageRating   * The rating information for this package. Note, this is a courtesy
     * rate and may be different from what is invoiced.
     */
    public com.fedex.ship.stub.PackageRating getPackageRating() {
        return packageRating;
    }


    /**
     * Sets the packageRating value for this CompletedPackageDetail.
     * 
     * @param packageRating   * The rating information for this package. Note, this is a courtesy
     * rate and may be different from what is invoiced.
     */
    public void setPackageRating(com.fedex.ship.stub.PackageRating packageRating) {
        this.packageRating = packageRating;
    }


    /**
     * Gets the barcodes value for this CompletedPackageDetail.
     * 
     * @return barcodes   * The data that is used to from the Astra and 2DCommon barcodes
     * for the label..
     */
    public com.fedex.ship.stub.PackageBarcodes getBarcodes() {
        return barcodes;
    }


    /**
     * Sets the barcodes value for this CompletedPackageDetail.
     * 
     * @param barcodes   * The data that is used to from the Astra and 2DCommon barcodes
     * for the label..
     */
    public void setBarcodes(com.fedex.ship.stub.PackageBarcodes barcodes) {
        this.barcodes = barcodes;
    }


    /**
     * Gets the astraHandlingText value for this CompletedPackageDetail.
     * 
     * @return astraHandlingText   * The textual description of the special service applied to the
     * package.
     */
    public java.lang.String getAstraHandlingText() {
        return astraHandlingText;
    }


    /**
     * Sets the astraHandlingText value for this CompletedPackageDetail.
     * 
     * @param astraHandlingText   * The textual description of the special service applied to the
     * package.
     */
    public void setAstraHandlingText(java.lang.String astraHandlingText) {
        this.astraHandlingText = astraHandlingText;
    }


    /**
     * Gets the label value for this CompletedPackageDetail.
     * 
     * @return label   * The label image or printer commands to print the label.
     */
    public com.fedex.ship.stub.ShippingDocument getLabel() {
        return label;
    }


    /**
     * Sets the label value for this CompletedPackageDetail.
     * 
     * @param label   * The label image or printer commands to print the label.
     */
    public void setLabel(com.fedex.ship.stub.ShippingDocument label) {
        this.label = label;
    }


    /**
     * Gets the packageDocuments value for this CompletedPackageDetail.
     * 
     * @return packageDocuments   * All package-level shipping documents (other than labels and
     * barcodes).
     */
    public com.fedex.ship.stub.ShippingDocument[] getPackageDocuments() {
        return packageDocuments;
    }


    /**
     * Sets the packageDocuments value for this CompletedPackageDetail.
     * 
     * @param packageDocuments   * All package-level shipping documents (other than labels and
     * barcodes).
     */
    public void setPackageDocuments(com.fedex.ship.stub.ShippingDocument[] packageDocuments) {
        this.packageDocuments = packageDocuments;
    }

    public com.fedex.ship.stub.ShippingDocument getPackageDocuments(int i) {
        return this.packageDocuments[i];
    }

    public void setPackageDocuments(int i, com.fedex.ship.stub.ShippingDocument _value) {
        this.packageDocuments[i] = _value;
    }


    /**
     * Gets the codReturnDetail value for this CompletedPackageDetail.
     * 
     * @return codReturnDetail   * Information about the COD return shipment.
     */
    public com.fedex.ship.stub.CodReturnPackageDetail getCodReturnDetail() {
        return codReturnDetail;
    }


    /**
     * Sets the codReturnDetail value for this CompletedPackageDetail.
     * 
     * @param codReturnDetail   * Information about the COD return shipment.
     */
    public void setCodReturnDetail(com.fedex.ship.stub.CodReturnPackageDetail codReturnDetail) {
        this.codReturnDetail = codReturnDetail;
    }


    /**
     * Gets the signatureOption value for this CompletedPackageDetail.
     * 
     * @return signatureOption   * The actual signature option applied to this shipment. This
     * could be different than the signature option requested if a conflict
     * occured with other service features in the shipment.
     */
    public com.fedex.ship.stub.SignatureOptionType getSignatureOption() {
        return signatureOption;
    }


    /**
     * Sets the signatureOption value for this CompletedPackageDetail.
     * 
     * @param signatureOption   * The actual signature option applied to this shipment. This
     * could be different than the signature option requested if a conflict
     * occured with other service features in the shipment.
     */
    public void setSignatureOption(com.fedex.ship.stub.SignatureOptionType signatureOption) {
        this.signatureOption = signatureOption;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CompletedPackageDetail)) return false;
        CompletedPackageDetail other = (CompletedPackageDetail) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.sequenceNumber==null && other.getSequenceNumber()==null) || 
             (this.sequenceNumber!=null &&
              this.sequenceNumber.equals(other.getSequenceNumber()))) &&
            ((this.trackingIds==null && other.getTrackingIds()==null) || 
             (this.trackingIds!=null &&
              java.util.Arrays.equals(this.trackingIds, other.getTrackingIds()))) &&
            ((this.groupNumber==null && other.getGroupNumber()==null) || 
             (this.groupNumber!=null &&
              this.groupNumber.equals(other.getGroupNumber()))) &&
            ((this.oversizeClass==null && other.getOversizeClass()==null) || 
             (this.oversizeClass!=null &&
              this.oversizeClass.equals(other.getOversizeClass()))) &&
            ((this.packageRating==null && other.getPackageRating()==null) || 
             (this.packageRating!=null &&
              this.packageRating.equals(other.getPackageRating()))) &&
            ((this.barcodes==null && other.getBarcodes()==null) || 
             (this.barcodes!=null &&
              this.barcodes.equals(other.getBarcodes()))) &&
            ((this.astraHandlingText==null && other.getAstraHandlingText()==null) || 
             (this.astraHandlingText!=null &&
              this.astraHandlingText.equals(other.getAstraHandlingText()))) &&
            ((this.label==null && other.getLabel()==null) || 
             (this.label!=null &&
              this.label.equals(other.getLabel()))) &&
            ((this.packageDocuments==null && other.getPackageDocuments()==null) || 
             (this.packageDocuments!=null &&
              java.util.Arrays.equals(this.packageDocuments, other.getPackageDocuments()))) &&
            ((this.codReturnDetail==null && other.getCodReturnDetail()==null) || 
             (this.codReturnDetail!=null &&
              this.codReturnDetail.equals(other.getCodReturnDetail()))) &&
            ((this.signatureOption==null && other.getSignatureOption()==null) || 
             (this.signatureOption!=null &&
              this.signatureOption.equals(other.getSignatureOption())));
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
        if (getSequenceNumber() != null) {
            _hashCode += getSequenceNumber().hashCode();
        }
        if (getTrackingIds() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTrackingIds());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTrackingIds(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getGroupNumber() != null) {
            _hashCode += getGroupNumber().hashCode();
        }
        if (getOversizeClass() != null) {
            _hashCode += getOversizeClass().hashCode();
        }
        if (getPackageRating() != null) {
            _hashCode += getPackageRating().hashCode();
        }
        if (getBarcodes() != null) {
            _hashCode += getBarcodes().hashCode();
        }
        if (getAstraHandlingText() != null) {
            _hashCode += getAstraHandlingText().hashCode();
        }
        if (getLabel() != null) {
            _hashCode += getLabel().hashCode();
        }
        if (getPackageDocuments() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPackageDocuments());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPackageDocuments(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCodReturnDetail() != null) {
            _hashCode += getCodReturnDetail().hashCode();
        }
        if (getSignatureOption() != null) {
            _hashCode += getSignatureOption().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CompletedPackageDetail.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "CompletedPackageDetail"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sequenceNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "SequenceNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "positiveInteger"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trackingIds");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "TrackingIds"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "TrackingId"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("groupNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "GroupNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "nonNegativeInteger"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("oversizeClass");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "OversizeClass"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "OversizeClassType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("packageRating");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "PackageRating"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "PackageRating"));
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
        elemField.setFieldName("astraHandlingText");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "AstraHandlingText"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("packageDocuments");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "PackageDocuments"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "ShippingDocument"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codReturnDetail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "CodReturnDetail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "CodReturnPackageDetail"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("signatureOption");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "SignatureOption"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "SignatureOptionType"));
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
