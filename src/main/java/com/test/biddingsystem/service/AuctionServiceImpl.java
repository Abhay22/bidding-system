package com.test.biddingsystem.service;

import com.test.biddingsystem.entity.AuctionEntity;
import com.test.biddingsystem.model.AuctionDetail;
import com.test.biddingsystem.repository.AuctionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuctionServiceImpl implements AuctionService {
    private static final Logger logger = LoggerFactory.getLogger(AuctionServiceImpl.class);

//    private final AuctionRepository auctionRepository;
//
//    public AuctionServiceImpl(AuctionRepository auctionRepository) {
//        this.auctionRepository = auctionRepository;
//    }
@Autowired
private AuctionRepository auctionRepository;
    @Override
    public List<AuctionEntity> fetchAllAuctions() {
        List<AuctionEntity> auctionList = new ArrayList<>();
        auctionRepository.findAll().forEach(auctionList::add);
        if (!auctionList.isEmpty()) {
            return auctionList;
        } else {
            logger.info("No auction found");
            return new ArrayList<>();
        }
    }
}
