package com.coachmovecustomer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApplyPromoCodeResponse {
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("applyCouponAmount")
    @Expose
    private Integer applyCouponAmount;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private String status;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getApplyCouponAmount() {
        return applyCouponAmount;
    }


    public void setApplyCouponAmount(Integer applyCouponAmount) {
        this.applyCouponAmount = applyCouponAmount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
