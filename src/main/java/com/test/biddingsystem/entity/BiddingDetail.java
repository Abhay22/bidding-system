package com.test.biddingsystem.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Builder
@Table(name = "BIDDING_DETAIL")
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class BiddingDetail {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "bid_price")
    private int bidPrice;
}
