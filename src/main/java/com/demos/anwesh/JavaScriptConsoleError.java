package com.demos.anwesh;
public class JavaScriptConsoleError {
    private String fileName, errorMessage;
    private Long lineNumber, colNumber;
    public JavaScriptConsoleError () {

    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    public void setLineNumber(long lineNumber) {
        this.lineNumber = lineNumber;
    }
    public void setColNumber(long colNumber) {
        this.colNumber = colNumber;
    }
    public String getFileName() {
        return fileName;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
    public Long getLineNumber() {
        return lineNumber;
    }
    public Long getColNumber() {
        return colNumber;
    }
}
