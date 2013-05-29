package org.cloudfoundry.samples.email.domain;

public class Status {

    private String message;
    private boolean isError;

    public Status() {
        isError = false;
        this.message = "";
    }

    public Status(boolean isError, String message) {
        this.isError = isError;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        this.isError = error;
    }
}
