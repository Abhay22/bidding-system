package com.test.biddingsystem.service;

import com.test.biddingsystem.biddingrules.BidValidationRules;
import com.test.biddingsystem.entity.AuctionEntity;
import com.test.biddingsystem.entity.BiddingDetail;
import com.test.biddingsystem.model.BiddingRequest;
import com.test.biddingsystem.repository.AuctionRepository;
import com.test.biddingsystem.repository.BiddingDetailRepository;
import com.test.biddingsystem.status.BiddingStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.OptimisticLockException;

@Service
public class BiddingService {
    private static final Logger logger = LoggerFactory.getLogger(BiddingService.class);
    private final BidValidationRules bidValidationRules;
    private final AuctionRepository auctionRepository;
    private final BiddingDetailRepository biddingDetailRepository;

    public BiddingService(BidValidationRules bidValidationRules,
                          AuctionRepository auctionRepository,
                          BiddingDetailRepository biddingDetailRepository) {
        this.bidValidationRules = bidValidationRules;
        this.auctionRepository = auctionRepository;
        this.biddingDetailRepository = biddingDetailRepository;
    }

    public BiddingStatus bidProcessing(BiddingRequest biddingRequest) {
        try {
            AuctionEntity auctionEntity = auctionRepository.findByItemCode(biddingRequest.getItemCode());
            if (null == auctionEntity) {
                return BiddingStatus.AUCTION_NOT_FOUND;
            }
            // bidding entry
            biddingDetailRepository.save(BiddingDetail.builder().itemId(auctionEntity.getItemId()).
                    bidPrice(biddingRequest.getBidAmount()).userName("admin").build());
            // bidding validation
            if (!bidValidationRules.apply(auctionEntity, biddingRequest)) {
                return BiddingStatus.BID_REJECTED;
            }
            // persisting bid
            auctionEntity.setBidPrice(biddingRequest.getBidAmount());
            auctionRepository.save(auctionEntity);

            return BiddingStatus.BID_ACCEPTED;

        } catch (OptimisticLockException e) {
            return BiddingStatus.BID_REJECTED;
        } catch (Exception e) {
            logger.error("Exception while bid processing: {}", e.getMessage());
            return BiddingStatus.PROCESSING_ERROR;
        }
    }
}