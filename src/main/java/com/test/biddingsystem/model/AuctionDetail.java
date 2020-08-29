package com.test.biddingsystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuctionDetail {
    private String itemCode;
    private int  basePrice;
    private String status;
    private int stepRate;
}
