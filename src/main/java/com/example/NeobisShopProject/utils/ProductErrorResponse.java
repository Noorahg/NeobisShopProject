package com.example.NeobisShopProject.utils;

public class ProductErrorResponse {
    private String messsage;
    private long timestamp;

    public ProductErrorResponse(String messsage, long timestamp) {
        this.messsage = messsage;
        this.timestamp = timestamp;
    }

    public String getMesssage() {
        return messsage;
    }

    public void setMesssage(String messsage) {
        this.messsage = messsage;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
