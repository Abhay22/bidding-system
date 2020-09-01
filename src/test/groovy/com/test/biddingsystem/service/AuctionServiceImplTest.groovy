package com.test.biddingsystem.service

import com.test.biddingsystem.entity.AuctionEntity
import com.test.biddingsystem.repository.AuctionRepository
import spock.lang.Specification
import spock.lang.Unroll

import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

class AuctionServiceImplTest extends Specification{

    def auctionRepo = mock(AuctionRepository.class)

    @Unroll
    def 'get auction'() {
        given:
        def auctionService = new AuctionServiceImpl(auctionRepo)
        List<AuctionEntity> auctionEntityList= new ArrayList<>()
        auctionEntityList.add(AuctionEntity.builder().itemId(1001).itemCode("ABC123").basePrice(1000).bidPrice(0).stepRate(100).status("RUNNING").build())
        when(auctionRepo.findAll("RUNNING")).thenReturn(auctionEntityList)
        when:
        def resp = auctionService.fetchAllAuctions("RUNNING")
        then:
        resp.get(0).itemCode == "ABC123"
    }

    @Unroll
    def 'auction not found'() {
        given:
        def auctionService = new AuctionServiceImpl(auctionRepo)
        List<AuctionEntity> auctionEntityList= new ArrayList<>()
        when(auctionRepo.findAll("RUNNING")).thenReturn(auctionEntityList)
        when:
        def resp = auctionService.fetchAllAuctions("RUNNING")
        then:
        resp.size() == 0
    }
}
