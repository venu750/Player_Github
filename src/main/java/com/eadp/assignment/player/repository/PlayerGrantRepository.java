package com.eadp.assignment.player.repository;

import com.eadp.assignment.player.entity.GrantedProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlayerGrantRepository extends JpaRepository<GrantedProducts,Integer> {
    @Query(value="select distinct product_id from playerdb.granted_products where player_id=?1",nativeQuery = true)
    List<Integer>getPlayerProductIds(Integer playerId);
}
