package org.cloudfoundry.samples.email.domain;

import java.io.Serializable;
import java.util.List;

public class EmailMessage implements Serializable {
    private List<String> toAddresses;
    private List<String> ccAddresses;
    private List<String> bccAddresses;
    private String fromAddress;
    private String subject;
    private String body;

    public EmailMessage() {
        super();
    }

    public List<String> getToAddresses() {
        return toAddresses;
    }

    public void setToAddresses(List<String> toAddresses) {
        this.toAddresses = toAddresses;
    }

    public List<String> getCcAddresses() {
        return ccAddresses;
    }

    public void setCcAddresses(List<String> ccAddresses) {
        this.ccAddresses = ccAddresses;
    }

    public List<String> getBccAddresses() {
        return bccAddresses;
    }

    public void setBccAddresses(List<String> bccAddresses) {
        this.bccAddresses = bccAddresses;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "EmailMessage{" +
                "toAddresses='" + toAddresses + '\'' +
                ", ccAddresses='" + ccAddresses + '\'' +
                ", bccAddresses='" + bccAddresses + '\'' +
                ", fromAddress='" + fromAddress + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}


