package com.github.osvaldsoza.smart.receipts.aws.exception;

/**
 * Global exception handler advice for REST API
 */
public class ApiError {
    private int status;
    private String message;
    private String timestamp;

    public ApiError(int status, String message, String timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}

