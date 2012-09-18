/**
 * CommercialInvoice.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.fedex.ship.stub;


/**
 * CommercialInvoice element is required for electronic upload of
 * CI data. It will serve to create/transmit an Electronic Commercial
 * Invoice through FedEx System. Customers are responsible for printing
 * their own Commercial Invoice. Commercial Invoice support consists
 * of a maximum of 99 commodity line items.
 */
public class CommercialInvoice  implements java.io.Serializable {
    /* Commercial Invoice comments to be uploaded to customs. */
    private java.lang.String[] comments;

    /* Format: Two explicit decimal positions max length 19 including
     * decimal.
     * 												Required if Terms Of Sale is CFR or CIF.
     * 												This charge should be added to the total customs value
     * amount. */
    private com.fedex.ship.stub.Money freightCharge;

    /* Format: Two explicit decimal positions max length 19 including
     * decimal.
     * 												This charge should be added to the total customs value
     * amount. */
    private com.fedex.ship.stub.Money taxesOrMiscellaneousCharge;

    /* Test for the Commercial Invoice. Note that Sold is not a valid
     * Purpose for a Proforma Invoice. */
    private com.fedex.ship.stub.PurposeOfShipmentType purpose;

    /* Customer assigned invoice number. */
    private java.lang.String customerInvoiceNumber;

    /* Defines the terms of the sale. */
    private com.fedex.ship.stub.TermsOfSaleType termsOfSale;

    public CommercialInvoice() {
    }

    public CommercialInvoice(
           java.lang.String[] comments,
           com.fedex.ship.stub.Money freightCharge,
           com.fedex.ship.stub.Money taxesOrMiscellaneousCharge,
           com.fedex.ship.stub.PurposeOfShipmentType purpose,
           java.lang.String customerInvoiceNumber,
           com.fedex.ship.stub.TermsOfSaleType termsOfSale) {
           this.comments = comments;
           this.freightCharge = freightCharge;
           this.taxesOrMiscellaneousCharge = taxesOrMiscellaneousCharge;
           this.purpose = purpose;
           this.customerInvoiceNumber = customerInvoiceNumber;
           this.termsOfSale = termsOfSale;
    }


    /**
     * Gets the comments value for this CommercialInvoice.
     * 
     * @return comments   * Commercial Invoice comments to be uploaded to customs.
     */
    public java.lang.String[] getComments() {
        return comments;
    }


    /**
     * Sets the comments value for this CommercialInvoice.
     * 
     * @param comments   * Commercial Invoice comments to be uploaded to customs.
     */
    public void setComments(java.lang.String[] comments) {
        this.comments = comments;
    }

    public java.lang.String getComments(int i) {
        return this.comments[i];
    }

    public void setComments(int i, java.lang.String _value) {
        this.comments[i] = _value;
    }


    /**
     * Gets the freightCharge value for this CommercialInvoice.
     * 
     * @return freightCharge   * Format: Two explicit decimal positions max length 19 including
     * decimal.
     * 												Required if Terms Of Sale is CFR or CIF.
     * 												This charge should be added to the total customs value
     * amount.
     */
    public com.fedex.ship.stub.Money getFreightCharge() {
        return freightCharge;
    }


    /**
     * Sets the freightCharge value for this CommercialInvoice.
     * 
     * @param freightCharge   * Format: Two explicit decimal positions max length 19 including
     * decimal.
     * 												Required if Terms Of Sale is CFR or CIF.
     * 												This charge should be added to the total customs value
     * amount.
     */
    public void setFreightCharge(com.fedex.ship.stub.Money freightCharge) {
        this.freightCharge = freightCharge;
    }


    /**
     * Gets the taxesOrMiscellaneousCharge value for this CommercialInvoice.
     * 
     * @return taxesOrMiscellaneousCharge   * Format: Two explicit decimal positions max length 19 including
     * decimal.
     * 												This charge should be added to the total customs value
     * amount.
     */
    public com.fedex.ship.stub.Money getTaxesOrMiscellaneousCharge() {
        return taxesOrMiscellaneousCharge;
    }


    /**
     * Sets the taxesOrMiscellaneousCharge value for this CommercialInvoice.
     * 
     * @param taxesOrMiscellaneousCharge   * Format: Two explicit decimal positions max length 19 including
     * decimal.
     * 												This charge should be added to the total customs value
     * amount.
     */
    public void setTaxesOrMiscellaneousCharge(com.fedex.ship.stub.Money taxesOrMiscellaneousCharge) {
        this.taxesOrMiscellaneousCharge = taxesOrMiscellaneousCharge;
    }


    /**
     * Gets the purpose value for this CommercialInvoice.
     * 
     * @return purpose   * Test for the Commercial Invoice. Note that Sold is not a valid
     * Purpose for a Proforma Invoice.
     */
    public com.fedex.ship.stub.PurposeOfShipmentType getPurpose() {
        return purpose;
    }


    /**
     * Sets the purpose value for this CommercialInvoice.
     * 
     * @param purpose   * Test for the Commercial Invoice. Note that Sold is not a valid
     * Purpose for a Proforma Invoice.
     */
    public void setPurpose(com.fedex.ship.stub.PurposeOfShipmentType purpose) {
        this.purpose = purpose;
    }


    /**
     * Gets the customerInvoiceNumber value for this CommercialInvoice.
     * 
     * @return customerInvoiceNumber   * Customer assigned invoice number.
     */
    public java.lang.String getCustomerInvoiceNumber() {
        return customerInvoiceNumber;
    }


    /**
     * Sets the customerInvoiceNumber value for this CommercialInvoice.
     * 
     * @param customerInvoiceNumber   * Customer assigned invoice number.
     */
    public void setCustomerInvoiceNumber(java.lang.String customerInvoiceNumber) {
        this.customerInvoiceNumber = customerInvoiceNumber;
    }


    /**
     * Gets the termsOfSale value for this CommercialInvoice.
     * 
     * @return termsOfSale   * Defines the terms of the sale.
     */
    public com.fedex.ship.stub.TermsOfSaleType getTermsOfSale() {
        return termsOfSale;
    }


    /**
     * Sets the termsOfSale value for this CommercialInvoice.
     * 
     * @param termsOfSale   * Defines the terms of the sale.
     */
    public void setTermsOfSale(com.fedex.ship.stub.TermsOfSaleType termsOfSale) {
        this.termsOfSale = termsOfSale;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CommercialInvoice)) return false;
        CommercialInvoice other = (CommercialInvoice) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.comments==null && other.getComments()==null) || 
             (this.comments!=null &&
              java.util.Arrays.equals(this.comments, other.getComments()))) &&
            ((this.freightCharge==null && other.getFreightCharge()==null) || 
             (this.freightCharge!=null &&
              this.freightCharge.equals(other.getFreightCharge()))) &&
            ((this.taxesOrMiscellaneousCharge==null && other.getTaxesOrMiscellaneousCharge()==null) || 
             (this.taxesOrMiscellaneousCharge!=null &&
              this.taxesOrMiscellaneousCharge.equals(other.getTaxesOrMiscellaneousCharge()))) &&
            ((this.purpose==null && other.getPurpose()==null) || 
             (this.purpose!=null &&
              this.purpose.equals(other.getPurpose()))) &&
            ((this.customerInvoiceNumber==null && other.getCustomerInvoiceNumber()==null) || 
             (this.customerInvoiceNumber!=null &&
              this.customerInvoiceNumber.equals(other.getCustomerInvoiceNumber()))) &&
            ((this.termsOfSale==null && other.getTermsOfSale()==null) || 
             (this.termsOfSale!=null &&
              this.termsOfSale.equals(other.getTermsOfSale())));
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
        if (getComments() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getComments());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getComments(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getFreightCharge() != null) {
            _hashCode += getFreightCharge().hashCode();
        }
        if (getTaxesOrMiscellaneousCharge() != null) {
            _hashCode += getTaxesOrMiscellaneousCharge().hashCode();
        }
        if (getPurpose() != null) {
            _hashCode += getPurpose().hashCode();
        }
        if (getCustomerInvoiceNumber() != null) {
            _hashCode += getCustomerInvoiceNumber().hashCode();
        }
        if (getTermsOfSale() != null) {
            _hashCode += getTermsOfSale().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CommercialInvoice.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "CommercialInvoice"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comments");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "Comments"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("freightCharge");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "FreightCharge"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "Money"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxesOrMiscellaneousCharge");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "TaxesOrMiscellaneousCharge"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "Money"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("purpose");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "Purpose"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "PurposeOfShipmentType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerInvoiceNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "CustomerInvoiceNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("termsOfSale");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "TermsOfSale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "TermsOfSaleType"));
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
