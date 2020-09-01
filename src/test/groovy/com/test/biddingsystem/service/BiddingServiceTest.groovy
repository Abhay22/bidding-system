package com.test.biddingsystem.service

import com.test.biddingsystem.biddingrules.BidValidationRules
import com.test.biddingsystem.entity.AuctionEntity
import com.test.biddingsystem.model.BiddingRequest
import com.test.biddingsystem.repository.AuctionRepository
import com.test.biddingsystem.repository.BiddingDetailRepository
import com.test.biddingsystem.status.BiddingStatus
import spock.lang.Specification
import spock.lang.Unroll

import javax.persistence.OptimisticLockException

import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

class BiddingServiceTest extends Specification {

    def biddingRule = mock(BidValidationRules.class)
    def auctionRepo = mock(AuctionRepository.class)
    def biddingRepo = mock(BiddingDetailRepository.class)

    def biddingService = new BiddingService(biddingRule, auctionRepo, biddingRepo)

    @Unroll
    def 'process bid request #label'() {
        given:

        def bidRequest = BiddingRequest.builder().itemCode("ABC123").bidAmount(1200).build()
        when(auctionRepo.findByItemCode("ABC123")).thenReturn(aucEntity)
        when(biddingRule.apply(aucEntity, bidRequest)).thenReturn(isValidBid)

        when:

        def resp = biddingService.bidProcessing(bidRequest)
        then:

        resp.getReason() == expected

        where:
        label           | isValidBid | expected                                    | aucEntity
        "BID ACCEPTED"  | true       | BiddingStatus.BID_ACCEPTED.getReason()      | AuctionEntity.builder().itemId(1001).itemCode("ABC123").basePrice(1000).bidPrice(0).stepRate(100).status("RUNNING").build()
        "BID REJECTED"  | false      | BiddingStatus.BID_REJECTED.getReason()      | AuctionEntity.builder().itemId(1001).itemCode("ABC123").basePrice(1000).bidPrice(0).stepRate(100).status("RUNNING").build()
        "BID NOT FOUND" | false      | BiddingStatus.AUCTION_NOT_FOUND.getReason() | null

    }

    @Unroll
    def 'exception while processing bid request'() {
        given:

        List<AuctionEntity> auctionEntityList = new ArrayList<>()
        def auctionEntity = AuctionEntity.builder().itemId(1001).itemCode("ABC123").basePrice(1000).bidPrice(0).stepRate(100).status("RUNNING").build()
        auctionEntityList.add(AuctionEntity.builder().itemId(1001).itemCode("ABC123").basePrice(1000).bidPrice(0).stepRate(100).status("RUNNING").build())
        def bidRequest = BiddingRequest.builder().itemCode("ABC123").bidAmount(1200).build()
        when(auctionRepo.findByItemCode("ABC123")).thenReturn(auctionEntity)
        when(biddingRule.apply(auctionEntity, bidRequest)).thenReturn(true)
        when(auctionRepo.save(auctionEntity)).thenThrow(exceptionName)

        when:

        def resp = biddingService.bidProcessing(bidRequest)
        then:

        resp.getReason() == expected
        where:
        label              | exceptionName                 | expected
        "PROCESSING ERROR" | new NullPointerException()    | BiddingStatus.PROCESSING_ERROR.getReason()
        "BID REJECTED"     | new OptimisticLockException() | BiddingStatus.BID_REJECTED.getReason()

    }
}
