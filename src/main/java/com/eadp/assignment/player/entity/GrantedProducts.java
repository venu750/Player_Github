package com.eadp.assignment.player.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
public class GrantedProducts {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @ApiModelProperty(value = "Id of the Granted Products table")
    private Integer id;
    @ApiModelProperty(value = "Product Id")
    private Integer productId;
    @ApiModelProperty(value = "Player Id")
    private Integer playerId;
    @ApiModelProperty(value = "present Date and Time")
    private LocalDateTime date = LocalDateTime.now();

    public GrantedProducts(Integer productId, Integer playerId, LocalDateTime date) {
        this.productId = productId;
        this.playerId = playerId;
        this.date = date;
    }

    public GrantedProducts() {

    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
