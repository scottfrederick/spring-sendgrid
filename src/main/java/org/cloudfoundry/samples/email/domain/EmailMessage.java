package org.cloudfoundry.samples.email.domain;

import org.springframework.core.style.ToStringCreator;

import java.io.Serializable;

public class EmailMessage implements Serializable {
    private String toName;
    private String toAddress;
    private String fromName;
    private String fromAddress;
    private String subject;
    private String body;

    public EmailMessage() {
        super();
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
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
        return new ToStringCreator(this).
                append(toName).
                append(toAddress).
                append(fromName).
                append(fromAddress).
                append(subject).
                append(body).
                toString();
    }
}
