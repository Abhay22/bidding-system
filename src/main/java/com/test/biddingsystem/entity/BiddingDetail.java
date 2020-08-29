package com.test.biddingsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "BIDDING_INFO")
@NoArgsConstructor
@AllArgsConstructor
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
