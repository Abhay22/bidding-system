package com.test.biddingsystem.status;

public enum AuctionStatus {
    NEW("NEW"),
    RUNNING("RUNNING"),
    OVER("OVER");

    private String status;
    AuctionStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
}
