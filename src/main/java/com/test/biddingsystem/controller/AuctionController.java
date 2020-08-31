package com.test.biddingsystem.controller;

import com.test.biddingsystem.entity.AuctionEntity;
import com.test.biddingsystem.model.BiddingRequest;
import com.test.biddingsystem.service.AuctionServiceImpl;
import com.test.biddingsystem.service.BiddingService;
import com.test.biddingsystem.status.BiddingStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("bidding_system/v1")
public class AuctionController {

    private static final Logger logger = LoggerFactory.getLogger(AuctionController.class);

    private final AuctionServiceImpl auctionService;
    private final BiddingService biddingService;

    public AuctionController(AuctionServiceImpl auctionService,
                             BiddingService biddingService) {
        this.auctionService = auctionService;
        this.biddingService = biddingService;
    }

    @GetMapping("/auctions")
    public ResponseEntity<List<AuctionEntity>> getAuctions() {
        List<AuctionEntity> auctions = auctionService.fetchAllAuctions();
        return new ResponseEntity<>(auctions, HttpStatus.OK);
    }
    @PostMapping("/placeBid")
    public ResponseEntity<String> placeBid(@RequestBody BiddingRequest biddingRequest) {
        logger.info(biddingRequest.toString());
        BiddingStatus biddingStatus = biddingService.bidProcessing(biddingRequest);
        return new ResponseEntity<>(biddingStatus.getReason(), biddingStatus.getCode());
    }
}
