package com.eadp.assignment.player.entity;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;


@Entity
public class Player {
     @Id
             @ApiModelProperty(value = "Player Id")
     Integer playerId;
     @ApiModelProperty(value = "Player First Name")
     String firstName;
     @ApiModelProperty(value ="Player Last Name")
     String lastName;
     @ApiModelProperty(value = "Player DateOfBirth")
     String dob;
     @ApiModelProperty(value = "Player email")
     String email;


    public Player(Integer playerId, String firstName, String lastName, String dob, String email) {
        this.playerId = playerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.email = email;
    }

    public Player() {

    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
