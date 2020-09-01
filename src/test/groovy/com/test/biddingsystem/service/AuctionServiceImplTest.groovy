package com.test.biddingsystem.service

import com.test.biddingsystem.entity.AuctionEntity
import com.test.biddingsystem.repository.AuctionRepository
import spock.lang.Specification
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

class AuctionServiceImplTest extends Specification{
//"[{\"item_id\":1001,\"item_code\":\"iHpTrbC3wl\",\"base_price\":4000,\"bid_price\":1,\"step_rate\":200,\"status\":\"RUNNING\",\"version\":1},{\"item_id\":1002,\"item_code\":\"RbXq9aArFC\",\"base_price\":2000,\"bid_price\":1,\"step_rate\":100,\"status\":\"RUNNING\",\"version\":1},{\"item_id\":1003,\"item_code\":\"2KIADt74uI\",\"base_price\":3500,\"bid_price\":1,\"step_rate\":300,\"status\":\"RUNNING\",\"version\":1}]"
    def auctionRepo = mock(AuctionRepository.class)

    def 'get auction'() {
        given:
        def auctionService = new AuctionServiceImpl(auctionRepo)
        List<AuctionEntity> auctionEntityList= new ArrayList<>()
        AuctionEntity.builder().itemId(1001).itemCode("ABC123").basePrice(1000).bidPrice(0).stepRate(100).status("RUNNING").build()
        auctionEntityList.add(AuctionEntity.builder().itemId(1001).itemCode("ABC123").basePrice(1000).bidPrice(0).stepRate(100).status("RUNNING").build())
        when(auctionRepo.findAll("RUNNING")).thenReturn(auctionEntityList)
        when:
        def resp = auctionService.fetchAllAuctions("RUNNING")
        then:
        resp.get(0).itemCode == "ABC123"
    }
}
