/**
 * CompletedEtdDetail.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.fedex.ship.stub;

public class CompletedEtdDetail  implements java.io.Serializable {
    private com.fedex.ship.stub.UploadDocumentReferenceDetail[] uploadDocumentReferenceDetails;

    public CompletedEtdDetail() {
    }

    public CompletedEtdDetail(
           com.fedex.ship.stub.UploadDocumentReferenceDetail[] uploadDocumentReferenceDetails) {
           this.uploadDocumentReferenceDetails = uploadDocumentReferenceDetails;
    }


    /**
     * Gets the uploadDocumentReferenceDetails value for this CompletedEtdDetail.
     * 
     * @return uploadDocumentReferenceDetails
     */
    public com.fedex.ship.stub.UploadDocumentReferenceDetail[] getUploadDocumentReferenceDetails() {
        return uploadDocumentReferenceDetails;
    }


    /**
     * Sets the uploadDocumentReferenceDetails value for this CompletedEtdDetail.
     * 
     * @param uploadDocumentReferenceDetails
     */
    public void setUploadDocumentReferenceDetails(com.fedex.ship.stub.UploadDocumentReferenceDetail[] uploadDocumentReferenceDetails) {
        this.uploadDocumentReferenceDetails = uploadDocumentReferenceDetails;
    }

    public com.fedex.ship.stub.UploadDocumentReferenceDetail getUploadDocumentReferenceDetails(int i) {
        return this.uploadDocumentReferenceDetails[i];
    }

    public void setUploadDocumentReferenceDetails(int i, com.fedex.ship.stub.UploadDocumentReferenceDetail _value) {
        this.uploadDocumentReferenceDetails[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CompletedEtdDetail)) return false;
        CompletedEtdDetail other = (CompletedEtdDetail) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.uploadDocumentReferenceDetails==null && other.getUploadDocumentReferenceDetails()==null) || 
             (this.uploadDocumentReferenceDetails!=null &&
              java.util.Arrays.equals(this.uploadDocumentReferenceDetails, other.getUploadDocumentReferenceDetails())));
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
        if (getUploadDocumentReferenceDetails() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUploadDocumentReferenceDetails());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUploadDocumentReferenceDetails(), i);
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
        new org.apache.axis.description.TypeDesc(CompletedEtdDetail.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "CompletedEtdDetail"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uploadDocumentReferenceDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "UploadDocumentReferenceDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v7", "UploadDocumentReferenceDetail"));
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
