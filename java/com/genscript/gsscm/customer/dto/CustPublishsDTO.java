package com.genscript.gsscm.customer.dto;

import com.genscript.gsscm.common.SessionBaseDTO;

import java.util.Date;

public class CustPublishsDTO extends SessionBaseDTO {

    /**
     *
     */
    private static final long serialVersionUID = 3527764406426699022L;
    private Integer grantId;
    private Integer custNo;
    private String title;
    private String publicationName;
    private Date issueDate;
    private String url;

    public Integer getGrantId() {
        return grantId;
    }

    public void setGrantId(Integer grantId) {
        this.grantId = grantId;
    }

    public Integer getCustNo() {
        return custNo;
    }

    public void setCustNo(Integer custNo) {
        this.custNo = custNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublicationName() {
        return publicationName;
    }

    public void setPublicationName(String publicationName) {
        this.publicationName = publicationName;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
