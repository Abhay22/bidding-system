package com.test.biddingsystem.repository;

import com.test.biddingsystem.entity.AuctionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AuctionRepository extends CrudRepository<AuctionEntity, Long> {

    @Query(value = "select * from auction a where a.item_code= ?1", nativeQuery = true)
    AuctionEntity findByItemCode(String itemCode);
}
