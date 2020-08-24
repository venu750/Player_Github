package com.eadp.assignment.player.service;

import com.eadp.assignment.player.model.PlayerDto;
import com.eadp.assignment.player.entity.Player;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConvertorService {
    @Autowired
    private ModelMapper modelMapper;
    public PlayerDto convertDto(Player player)
    {
        return modelMapper.map(player,PlayerDto.class);
    }
}
