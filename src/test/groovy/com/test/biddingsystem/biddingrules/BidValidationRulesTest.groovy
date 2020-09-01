package com.test.biddingsystem.biddingrules

import com.test.biddingsystem.entity.AuctionEntity
import com.test.biddingsystem.model.BiddingRequest
import spock.lang.Specification
import spock.lang.Unroll

class BidValidationRulesTest extends Specification {

    @Unroll
    def 'initial invalid bid'() {
        given:
        def biddingRule = new BidValidationRules()
        def bidRequest = BiddingRequest.builder().itemCode("ABC123").bidAmount(100).build()
        def aucEntity = AuctionEntity.builder().itemId(1001).itemCode("ABC123").basePrice(1000).bidPrice(0).stepRate(100).status("RUNNING").build()

        when:
        def resp = biddingRule.apply(aucEntity, bidRequest)
        then:
        !resp
    }

    @Unroll
    def 'initial valid bid'() {
        given:
        def biddingRule = new BidValidationRules()
        def bidRequest = BiddingRequest.builder().itemCode("ABC123").bidAmount(1100).build()
        def aucEntity = AuctionEntity.builder().itemId(1001).itemCode("ABC123").basePrice(1000).bidPrice(0).stepRate(100).status("RUNNING").build()

        when:
        def resp = biddingRule.apply(aucEntity, bidRequest)
        then:
        resp
    }
    @Unroll
    def 'valid bid'() {
        given:
        def biddingRule = new BidValidationRules()
        def bidRequest = BiddingRequest.builder().itemCode("ABC123").bidAmount(1200).build()
        def aucEntity = AuctionEntity.builder().itemId(1001).itemCode("ABC123").basePrice(1000).bidPrice(1100).stepRate(100).status("RUNNING").build()

        when:
        def resp = biddingRule.apply(aucEntity, bidRequest)
        then:
        resp
    }
}
