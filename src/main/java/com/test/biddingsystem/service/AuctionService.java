package com.test.biddingsystem.service;

import com.test.biddingsystem.entity.AuctionEntity;

import java.util.List;

public interface AuctionService {
    List<AuctionEntity> fetchAllAuctions(String status);
}
