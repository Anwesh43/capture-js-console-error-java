package com.demos.anwesh;
public class JavascriptNetworkError {
    private String errorString, statusText, url;
    private int id, status;
    public JavascriptNetworkError() {

    }
    public void setErrorString(String errorString) {
        this.errorString = errorString;
    }
    public String getErrorString() {
        return errorString;
    }
    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getStatusText() {
        return statusText;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
}
