package org.cloudfoundry.samples.email.domain;

import java.io.Serializable;

public class EmailMessage implements Serializable {
    private String toAddress;
    private String ccAddress;
    private String bccAddress;
    private String fromAddress;
    private String subject;
    private String body;

    public EmailMessage() {
        super();
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getCcAddress() {
        return ccAddress;
    }

    public void setCcAddress(String ccAddress) {
        this.ccAddress = ccAddress;
    }

    public String getBccAddress() {
        return bccAddress;
    }

    public void setBccAddress(String bccAddress) {
        this.bccAddress = bccAddress;
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
                "toAddress='" + toAddress + '\'' +
                ", ccAddress='" + ccAddress + '\'' +
                ", bccAddress='" + bccAddress + '\'' +
                ", fromAddress='" + fromAddress + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}


