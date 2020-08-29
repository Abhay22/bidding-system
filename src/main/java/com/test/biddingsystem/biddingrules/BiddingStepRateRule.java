package com.test.biddingsystem.biddingrules;

import com.test.biddingsystem.entity.AuctionEntity;
import com.test.biddingsystem.model.BiddingRequest;

public class BiddingStepRateRule implements BiddingRules<AuctionEntity, BiddingRequest> {
    @Override
    public boolean apply(AuctionEntity auctionEntity,BiddingRequest biddingRequest) {
        //initial bid
        if(auctionEntity.getBidPrice() == 0){
            return biddingRequest.getBidAmount() > auctionEntity.getBasePrice()+auctionEntity.getStepRate();
        }
        return biddingRequest.getBidAmount() > auctionEntity.getBidPrice()+auctionEntity.getStepRate();
    }
}
