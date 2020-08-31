package com.test.biddingsystem.status;

import org.springframework.http.HttpStatus;

public enum BiddingStatus {
    BID_ACCEPTED("Bid is accepted", HttpStatus.CREATED),
    BID_NOT_FOUND("Auction not found", HttpStatus.NOT_FOUND),
    BID_REJECTED("Bid is rejected", HttpStatus.NOT_ACCEPTABLE),
    BAD_REQUEST("BAD REQUEST", HttpStatus.BAD_REQUEST),
    PROCESSING_ERROR("Error in processing Bid", HttpStatus.INTERNAL_SERVER_ERROR);

    private String reason;
    private HttpStatus code;

    BiddingStatus(String reason, HttpStatus code) {
        this.reason = reason;
        this.code = code;
    }
    public String getReason() {
        return reason;
    }
    public HttpStatus getCode() {
        return code;
    }
}
