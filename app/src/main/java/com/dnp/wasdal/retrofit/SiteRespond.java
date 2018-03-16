package com.dnp.wasdal.retrofit;

/**
 * Created by dimasnurpanca on 2/1/2018.
 */

public class SiteRespond {

    private String message;
    private Boolean error;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

}