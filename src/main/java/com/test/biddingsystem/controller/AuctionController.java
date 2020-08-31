package com.test.biddingsystem.controller;

import com.test.biddingsystem.entity.AuctionEntity;
import com.test.biddingsystem.service.AuctionServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("bidding_system/v1")
public class AuctionController {

    private static final Logger logger = LoggerFactory.getLogger(AuctionController.class);

    private final AuctionServiceImpl auctionService;

    public AuctionController(AuctionServiceImpl auctionService) {
        this.auctionService = auctionService;
    }

    @GetMapping("/auctions")
    public ResponseEntity<List<AuctionEntity>> getAuctions() {
        List<AuctionEntity> auctions = auctionService.fetchAllAuctions();
        return new ResponseEntity<>(auctions, new HttpHeaders(), HttpStatus.OK);
    }
}
