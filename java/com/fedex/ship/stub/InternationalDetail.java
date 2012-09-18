/**
 * InternationalDetail.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.fedex.ship.stub;

public class InternationalDetail  implements java.io.Serializable {
    /* Descriptive data identifying the Broker responsible for the
     * shipment.
     * 														Required if BROKER_SELECT_OPTION is requested in Special
     * Services. */
    private com.fedex.ship.stub.Party broker;

    /* Applicable only for Commercial Invoice. If the consignee and
     * importer are not the same, the Following importer fields are required.
     * 														Importer/Contact/PersonName
     * 														Importer/Contact/CompanyName
     * 														Importer/Contact/PhoneNumber
     * 														Importer/Address/StreetLine[0]
     * 														Importer/Address/City
     * 														Importer/Address/StateOrProvinceCode - if Importer Country
     * Code is US or CA
     * 														Importer/Address/PostalCode - if Importer Country Code
     * is US or CA
     * 														Importer/Address/CountryCode */
    private com.fedex.ship.stub.Party importerOfRecord;

    /* Type of Brazilian taxpayer identifier provided in Recipient/TaxPayerIdentification/Number.
     * For shipments bound for Brazil this overrides the value in Recipient/TaxPayerIdentification/TinType */
    private com.fedex.ship.stub.RecipientCustomsIdType recipientCustomsIdType;

    /* Indicates how payment of duties for the shipment will be made. */
    private com.fedex.ship.stub.Payment dutiesPayment;

    /* Indicates whether this shipment contains documents only or
     * non-documents. */
    private com.fedex.ship.stub.InternationalDocumentContentType documentContent;

    /* The total customs value for the shipment. This total will rrepresent
     * th esum of the values of all commodities, and may include freight,
     * miscellaneous, and insurance charges. Must contain 2 explicit decimal
     * positions with a max length of 17 including the decimal. For Express
     * International MPS, the Total Customs Value is in the master transaction
     * and all child transactions */
    private com.fedex.ship.stub.Money customsValue;

    /* Documents amount paid to third party for coverage of shipment
     * content. */
    private com.fedex.ship.stub.Money insuranceCharges;

    /* CommercialInvoice element is required for electronic upload
     * of CI data. It will serve to create/transmit an Electronic Commercial
     * Invoice through FedEx System. Customers are responsible for printing
     * their own Commercial Invoice. Commercial Invoice support consists
     * of a maximum of 20 commodity line items. */
    private com.fedex.ship.stub.CommercialInvoice commercialInvoice;

    /* For international multiple piece shipments, commodity information
     * must be passed in the Master and on each child transaction.
     * 										If this shipment cotains more than four commodities line
     * items, the four highest valued should be included in the first 4 occurances
     * for this request. */
    private com.fedex.ship.stub.Commodity[] commodities;

    /* Country specific details of an International shipment. */
    private com.fedex.ship.stub.ExportDetail exportDetail;

    /* FOOD_OR_PERISHABLE is required by FDA/BTA; must be true for
     * food/perishable items coming to US or PR from non-US/non-PR origin. */
    private com.fedex.ship.stub.RegulatoryControlType[] regulatoryControls;

    public InternationalDetail() {
    }

    public InternationalDetail(
           com.fedex.ship.stub.Party broker,
           com.fedex.ship.stub.Party importerOfRecord,
           com.fedex.ship.stub.RecipientCustomsIdType recipientCustomsIdType,
           com.fedex.ship.stub.Payment dutiesPayment,
           com.fedex.ship.stub.InternationalDocumentContentType documentContent,
           com.fedex.ship.stub.Money customsValue,
           com.fedex.ship.stub.Money insuranceCharges,
           com.fedex.ship.stub.CommercialInvoice commercialInvoice,
           com.fedex.ship.stub.Commodity[] commodities,
           com.fedex.ship.stub.ExportDetail exportDetail,
           com.fedex.ship.stub.RegulatoryControlType[] regulatoryControls) {
           this.broker = broker;
           this.importerOfRecord = importerOfRecord;
           this.recipientCustomsIdType = recipientCustomsIdType;
           this.dutiesPayment = dutiesPayment;
           this.documentContent = documentContent;
           this.customsValue = customsValue;
           this.insuranceCharges = insuranceCharges;
           this.commercialInvoice = commercialInvoice;
           this.commodities = commodities;
           this.exportDetail = exportDetail;
           this.regulatoryControls = regulatoryControls;
    }


    /**
     * Gets the broker value for this InternationalDetail.
     * 
     * @return broker   * Descriptive data identifying the Broker responsible for the
     * shipment.
     * 														Required if BROKER_SELECT_OPTION is requested in Special
     * Services.
     */
    public com.fedex.ship.stub.Party getBroker() {
        return broker;
    }


    /**
     * Sets the broker value for this InternationalDetail.
     * 
     * @param broker   * Descriptive data identifying the Broker responsible for the
     * shipment.
     * 														Required if BROKER_SELECT_OPTION is requested in Special
     * Services.
     */
    public void setBroker(com.fedex.ship.stub.Party broker) {
        this.broker = broker;
    }


    /**
     * Gets the importerOfRecord value for this InternationalDetail.
     * 
     * @return importerOfRecord   * Applicable only for Commercial Invoice. If the consignee and
     * importer are not the same, the Following importer fields are required.
     * 														Importer/Contact/PersonName
     * 														Importer/Contact/CompanyName
     * 														Importer/Contact/PhoneNumber
     * 														Importer/Address/StreetLine[0]
     * 														Importer/Address/City
     * 														Importer/Address/StateOrProvinceCode - if Importer Country
     * Code is US or CA
     * 														Importer/Address/PostalCode - if Importer Country Code
     * is US or CA
     * 														Importer/Address/CountryCode
     */
    public com.fedex.ship.stub.Party getImporterOfRecord() {
        return importerOfRecord;
    }


    /**
     * Sets the importerOfRecord value for this InternationalDetail.
     * 
     * @param importerOfRecord   * Applicable only for Commercial Invoice. If the consignee and
     * importer are not the same, the Following importer fields are required.
     * 														Importer/Contact/PersonName
     * 														Importer/Contact/CompanyName
     * 														Importer/Contact/PhoneNumber
     * 														Importer/Address/StreetLine[0]
     * 														Importer/Address/City
     * 														Importer/Address/StateOrProvinceCode - if Importer Country
     * Code is US or CA
     * 														Importer/Address/PostalCode - if Importer Country Code
     * is US or CA
     * 														Importer/Address/CountryCode
     */
    public void setImporterOfRecord(com.fedex.ship.stub.Party importerOfRecord) {
        this.importerOfRecord = importerOfRecord;
    }


    /**
     * Gets the recipientCustomsIdType value for this InternationalDetail.
     * 
     * @return recipientCustomsIdType   * Type of Brazilian taxpayer identifier provided in Recipient/TaxPayerIdentification/Number.
     * For shipments bound for Brazil this overrides the value in Recipient/TaxPayerIdentification/TinType
     */
    public com.fedex.ship.stub.RecipientCustomsIdType getRecipientCustomsIdType() {
        return recipientCustomsIdType;
    }


    /**
     * Sets the recipientCustomsIdType value for this InternationalDetail.
     * 
     * @param recipientCustomsIdType   * Type of Brazilian taxpayer identifier provided in Recipient/TaxPayerIdentification/Number.
     * For shipments bound for Brazil this overrides the value in Recipient/TaxPayerIdentification/TinType
     */
    public void setRecipientCustomsIdType(com.fedex.ship.stub.RecipientCustomsIdType recipientCustomsIdType) {
        this.recipientCustomsIdType = recipientCustomsIdType;
    }


    /**
     * Gets the dutiesPayment value for this InternationalDetail.
     * 
     * @return dutiesPayment   * Indicates how payment of duties for the shipment will be made.
     */
    public com.fedex.ship.stub.Payment getDutiesPayment() {
        return dutiesPayment;
    }


    /**
     * Sets the dutiesPayment value for this InternationalDetail.
     * 
     * @param dutiesPayment   * Indicates how payment of duties for the shipment will be made.
     */
    public void setDutiesPayment(com.fedex.ship.stub.Payment dutiesPayment) {
        this.dutiesPayment = dutiesPayment;
    }


    /**
     * Gets the documentContent value for this InternationalDetail.
     * 
     * @return documentContent   * Indicates whether this shipment contains documents only or
     * non-documents.
     */
    public com.fedex.ship.stub.InternationalDocumentContentType getDocumentContent() {
        return documentContent;
    }


    /**
     * Sets the documentContent value for this InternationalDetail.
     * 
     * @param documentContent   * Indicates whether this shipment contains documents only or
     * non-documents.
     */
    public void setDocumentContent(com.fedex.ship.stub.InternationalDocumentContentType documentContent) {
        this.documentContent = documentContent;
    }


    /**
     * Gets the customsValue value for this InternationalDetail.
     * 
     * @return customsValue   * The total customs value for the shipment. This total will rrepresent
     * th esum of the values of all commodities, and may include freight,
     * miscellaneous, and insurance charges. Must contain 2 explicit decimal
     * positions with a max length of 17 including the decimal. For Express
     * International MPS, the Total Customs Value is in the master transaction
     * and all child transactions
     */
    public com.fedex.ship.stub.Money getCustomsValue() {
        return customsValue;
    }


    /**
     * Sets the customsValue value for this InternationalDetail.
     * 
     * @param customsValue   * The total customs value for the shipment. This total will rrepresent
     * th esum of the values of all commodities, and may include freight,
     * miscellaneous, and insurance charges. Must contain 2 explicit decimal
     * positions with a max length of 17 including the decimal. For Express
     * International MPS, the Total Customs Value is in the master transaction
     * and all child transactions
     */
    public void setCustomsValue(com.fedex.ship.stub.Money customsValue) {
        this.customsValue = customsValue;
    }


    /**
     * Gets the insuranceCharges value for this InternationalDetail.
     * 
     * @return insuranceCharges   * Documents amount paid to third party for coverage of shipment
     * content.
     */
    public com.fedex.ship.stub.Money getInsuranceCharges() {
        return insuranceCharges;
    }


    /**
     * Sets the insuranceCharges value for this InternationalDetail.
     * 
     * @param insuranceCharges   * Documents amount paid to third party for coverage of shipment
     * content.
     */
    public void setInsuranceCharges(com.fedex.ship.stub.Money insuranceCharges) {
        this.insuranceCharges = insuranceCharges;
    }


    /**
     * Gets the commercialInvoice value for this InternationalDetail.
     * 
     * @return commercialInvoice   * CommercialInvoice element is required for electronic upload
     * of CI data. It will serve to create/transmit an Electronic Commercial
     * Invoice through FedEx System. Customers are responsible for printing
     * their own Commercial Invoice. Commercial Invoice support consists
     * of a maximum of 20 commodity line items.
     */
    public com.fedex.ship.stub.CommercialInvoice getCommercialInvoice() {
        return commercialInvoice;
    }


    /**
     * Sets the commercialInvoice value for this InternationalDetail.
     * 
     * @param commercialInvoice   * CommercialInvoice element is required for electronic upload
     * of CI data. It will serve to create/transmit an Electronic Commercial
     * Invoice through FedEx System. Customers are responsible for printing
     * their own Commercial Invoice. Commercial Invoice support consists
     * of a maximum of 20 commodity line items.
     */
    public void setCommercialInvoice(com.fedex.ship.stub.CommercialInvoice commercialInvoice) {
        this.commercialInvoice = commercialInvoice;
    }


    /**
     * Gets the commodities value for this InternationalDetail.
     * 
     * @return commodities   * For international multiple piece shipments, commodity information
     * must be passed in the Master and on each child transaction.
     * 										If this shipment cotains more than four commodities line
     * items, the four highest valued should be included in the first 4 occurances
     * for this request.
     */
    public com.fedex.ship.stub.Commodity[] getCommodities() {
        return commodities;
    }


    /**
     * Sets the commodities value for this InternationalDetail.
     * 
     * @param commodities   * For international multiple piece shipments, commodity information
     * must be passed in the Master and on each child transaction.
     * 										If this shipment cotains more than four commodities line
     * items, the four highest valued should be included in the first 4 occurances
     * for this request.
     */
    public void setCommodities(com.fedex.ship.stub.Commodity[] commodities) {
        this.commodities = commodities;
    }

    public com.fedex.ship.stub.Commodity getCommodities(int i) {
        return this.commodities[i];
    }

    public void setCommodities(int i, com.fedex.ship.stub.Commodity _value) {
        this.commodities[i] = _value;
    }


    /**
     * Gets the exportDetail value for this InternationalDetail.
     * 
     * @return exportDetail   * Country specific details of an International shipment.
     */
    public com.fedex.ship.stub.ExportDetail getExportDetail() {
        return exportDetail;
    }


    /**
     * Sets the exportDetail value for this InternationalDetail.
     * 
     * @param exportDetail   * Country specific details of an International shipment.
     */
    public void setExportDetail(com.fedex.ship.stub.ExportDetail exportDetail) {
        this.exportDetail = exportDetail;
    }


    /**
     * Gets the regulatoryControls value for this InternationalDetail.
     * 
     * @return regulatoryControls   * FOOD_OR_PERISHABLE is required by FDA/BTA; must be true for
     * food/perishable items coming to US or PR from non-US/non-PR origin.
     */
    public com.fedex.ship.stub.RegulatoryControlType[] getRegulatoryControls() {
        return regulatoryControls;
    }


    /**
     * Sets the regulatoryControls value for this InternationalDetail.
     * 
     * @param regulatoryControls   * FOOD_OR_PERISHABLE is required by FDA/BTA; must be true for
     * food/perishable items coming to US or PR from non-US/non-PR origin.
     */
    public void setRegulatoryControls(com.fedex.ship.stub.RegulatoryControlType[] regulatoryControls) {
        this.regulatoryControls = regulatoryControls;
    }

    public com.fedex.ship.stub.RegulatoryControlType getRegulatoryControls(int i) {
        return this.regulatoryControls[i];
    }

    public void setRegulatoryControls(int i, com.fedex.ship.stub.RegulatoryControlType _value) {
        this.regulatoryControls[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InternationalDetail)) return false;
        InternationalDetail other = (InternationalDetail) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.broker==null && other.getBroker()==null) || 
             (this.broker!=null &&
              this.broker.equals(other.getBroker()))) &&
            ((this.importerOfRecord==null && other.getImporterOfRecord()==null) || 
             (this.importerOfRecord!=null &&
              this.importerOfRecord.equals(other.getImporterOfRecord()))) &&
            ((this.recipientCustomsIdType==null && other.getRecipientCustomsIdType()==null) || 
             (this.recipientCustomsIdType!=null &&
              this.recipientCustomsIdType.equals(other.getRecipientCustomsIdType()))) &&
            ((this.dutiesPayment==null && other.getDutiesPayment()==null) || 
             (this.dutiesPayment!=null &&
              this.dutiesPayment.equals(other.getDutiesPayment()))) &&
            ((this.documentContent==null && other.getDocumentContent()==null) || 
             (this.documentContent!=null &&
              this.documentContent.equals(other.getDocumentContent()))) &&
            ((this.customsValue==null && other.getCustomsValue()==null) || 
             (this.customsValue!=null &&
              this.customsValue.equals(other.getCustomsValue()))) &&
            ((this.insuranceCharges==null && other.getInsuranceCharges()==null) || 
             (this.insuranceCharges!=null &&
              this.insuranceCharges.equals(other.getInsuranceCharges()))) &&
            ((this.commercialInvoice==null && other.getCommercialInvoice()==null) || 
             (this.commercialInvoice!=null &&
              this.commercialInvoice.equals(other.getCommercialInvoice()))) &&
            ((this.commodities==null && other.getCommodities()==null) || 
             (this.commodities!=null &&
              java.util.Arrays.equals(this.commodities, other.getCommodities()))) &&
            ((this.exportDetail==null && other.getExportDetail()==null) || 
             (this.exportDetail!=null &&
              this.exportDetail.equals(other.getExportDetail()))) &&
            ((this.regulatoryControls==null && other.getRegulatoryControls()==null) || 
             (this.regulatoryControls!=null &&
              java.util.Arrays.equals(this.regulatoryControls, other.getRegulatoryControls())));
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
        if (getBroker() != null) {
            _hashCode += getBroker().hashCode();
        }
        if (getImporterOfRecord() != null) {
            _hashCode += getImporterOfRecord().hashCode();
        }
        if (getRecipientCustomsIdType() != null) {
            _hashCode += getRecipientCustomsIdType().hashCode();
        }
        if (getDutiesPayment() != null) {
            _hashCode += getDutiesPayment().hashCode();
        }
        if (getDocumentContent() != null) {
            _hashCode += getDocumentContent().hashCode();
        }
        if (getCustomsValue() != null) {
            _hashCode += getCustomsValue().hashCode();
        }
        if (getInsuranceCharges() != null) {
            _hashCode += getInsuranceCharges().hashCode();
        }
        if (getCommercialInvoice() != null) {
            _hashCode += getCommercialInvoice().hashCode();
        }
        if (getCommodities() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCommodities());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCommodities(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getExportDetail() != null) {
            _hashCode += getExportDetail().hashCode();
        }
        if (getRegulatoryControls() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRegulatoryControls());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRegulatoryControls(), i);
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
        new org.apache.axis.description.TypeDesc(InternationalDetail.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "InternationalDetail"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("broker");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "Broker"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "Party"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("importerOfRecord");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "ImporterOfRecord"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "Party"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recipientCustomsIdType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "RecipientCustomsIdType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "RecipientCustomsIdType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dutiesPayment");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "DutiesPayment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "Payment"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentContent");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "DocumentContent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "InternationalDocumentContentType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customsValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "CustomsValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "Money"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("insuranceCharges");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "InsuranceCharges"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "Money"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("commercialInvoice");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "CommercialInvoice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "CommercialInvoice"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("commodities");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "Commodities"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "Commodity"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("exportDetail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "ExportDetail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "ExportDetail"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("regulatoryControls");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "RegulatoryControls"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "RegulatoryControlType"));
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
