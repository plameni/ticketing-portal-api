package com.ticketing.portal.response;

public class DBOperationResponse {
    private boolean success;
    private int id;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DBOperationResponse(boolean success, int id, String message) {
        this.success = success;
        this.id = id;
        this.message = message;
    }
}
