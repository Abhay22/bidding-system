package com.test.biddingsystem.controller

import com.test.biddingsystem.entity.AuctionEntity
import com.test.biddingsystem.model.BiddingRequest
import com.test.biddingsystem.service.AuctionServiceImpl
import com.test.biddingsystem.service.BiddingService
import com.test.biddingsystem.status.BiddingStatus
import spock.lang.Specification
import spock.lang.Unroll

import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

class AuctionControllerTest extends Specification{

    def auctionService = mock(AuctionServiceImpl.class)
    def biddingService = mock(BiddingService.class)
    def controller = new AuctionController(auctionService,biddingService)

    @Unroll
    def 'fetch all auctions'() {
        given:

        List<AuctionEntity> auctionEntityList= new ArrayList<>()
        auctionEntityList.add(AuctionEntity.builder().itemId(1001).itemCode("ABC123").basePrice(1000).bidPrice(0).stepRate(100).status("RUNNING").build())
        when(auctionService.fetchAllAuctions("RUNNING")).thenReturn(auctionEntityList)
        when:
        def resp = controller.getAuctions("RUNNING")
        then:
         resp.statusCodeValue == 200
    }

    @Unroll
    def 'place a bid'() {
        given:

        List<AuctionEntity> auctionEntityList= new ArrayList<>()
        auctionEntityList.add(AuctionEntity.builder().itemId(1001).itemCode("ABC123").basePrice(1000).bidPrice(0).stepRate(100).status("RUNNING").build())
        def bidRequest = BiddingRequest.builder().itemCode("ABC123").bidAmount(1200).build()
        when(biddingService.bidProcessing(bidRequest)).thenReturn(BiddingStatus.BID_ACCEPTED)
        when:
        def resp = controller.placeBid("ABC123",bidRequest)
        then:
        resp.statusCodeValue == 201
    }

    @Unroll
    def 'bad request'() {
        given:

        List<AuctionEntity> auctionEntityList= new ArrayList<>()
        auctionEntityList.add(AuctionEntity.builder().itemId(1001).itemCode("ABC123").basePrice(1000).bidPrice(0).stepRate(100).status("RUNNING").build())
        def bidRequest = BiddingRequest.builder().itemCode("ABC123").bidAmount(1200).build()
        when(biddingService.bidProcessing(bidRequest)).thenReturn(BiddingStatus.BID_ACCEPTED)
        when:
        def resp = controller.placeBid("",bidRequest)
        then:
        resp.statusCodeValue == 400
    }
}
