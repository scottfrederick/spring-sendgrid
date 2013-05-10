package org.cloudfoundry.samples.email.domain;

public class Status {

    private String message;

    public Status() {
        this.message = "";
    }

    public Status(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
