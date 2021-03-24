package com.graduate.hospital_manage.model.constant;

public enum PayStatus {
    UN_PAY(0),                //未缴费
    PRE_PAY(1),               //预缴费
    PAY(9);                   //已缴费

    int status ;
    PayStatus(int status) {
        this.status = status ;
    }
    public int status() {
        return this.status ;
    }
}
