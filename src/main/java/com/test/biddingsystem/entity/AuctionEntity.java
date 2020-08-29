package com.test.biddingsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import java.io.Serializable;

@Data
@Entity
@Table(name = "AUCTION")
@NoArgsConstructor
@AllArgsConstructor
public class AuctionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @Column(name = "item_code")
    private String itemCode;

    @Column(name = "base_price")
    private int basePrice;

    @Column(name = "bid_price")
    private int bidPrice;

    @Column(name = "step_rate")
    private int stepRate;

    @Column(name = "status")
    private String status;

    @Version
    @Column(name = "VERSION")
    private Integer version;
}
