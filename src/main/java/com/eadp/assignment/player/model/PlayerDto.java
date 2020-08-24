package com.eadp.assignment.player.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerDto {
    @JsonIgnore
    Integer playerId;
    String firstName;
    String lastName;
    String dob;
    String email;
}
