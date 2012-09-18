package com.genscript.gsscm.customer.dto;

import com.genscript.gsscm.common.SessionBaseDTO;

public class SampleRequestDTO extends SessionBaseDTO {


    private static final long serialVersionUID = 3527764406426699022L;

    private Integer seqNo;
    private String sampleName;
    private String url;
    private String requestDate;
    private String deliveryDate;
    private String note;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
