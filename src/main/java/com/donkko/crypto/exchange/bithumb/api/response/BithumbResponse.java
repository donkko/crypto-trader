package com.donkko.crypto.exchange.bithumb.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BithumbResponse<T> {

    public BithumbResponse(String status, String message, String orderId, T data) {
        this.status = status;
        this.message = message;
        this.orderId = orderId;
        this.data = data;
    }

    private String status;
    private String message;
    @JsonProperty("order_id") private String orderId;
    private T data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
