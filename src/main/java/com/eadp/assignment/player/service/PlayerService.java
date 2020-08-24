package com.eadp.assignment.player.service;

import com.eadp.assignment.player.model.PlayerDto;
import com.eadp.assignment.player.entity.GrantedProducts;
import com.eadp.assignment.player.entity.Player;
import com.eadp.assignment.player.model.Product;
import com.eadp.assignment.player.repository.PlayerGrantRepository;
import com.eadp.assignment.player.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private ConvertorService convertorService;
    @Autowired
    PlayerGrantRepository playerGrantRepository;
    @Autowired
    private RestTemplate restTemplate;

    public boolean playerCheckById(Integer id) {
        return playerRepository.existsById(id);
    }

    public List<PlayerDto> getAllPlayers() {
        List<Player> players = playerRepository.findAll();
        return players.stream().map(convertorService::convertDto).collect(Collectors.toList());
    }

    public PlayerDto getPlayerById(Integer id) {
        Player player = playerRepository.findById(id).orElse(null);
        return convertorService.convertDto(player);
    }

    public Player addPlayer(Player player) {
        return playerRepository.save(player);
    }

    public Player updatePlayer(Integer id, Player player) {
        playerRepository.deleteById(id);
        playerRepository.flush();
        return playerRepository.save(player);
    }

    public void deletePlayerById(Integer id) {
        if (playerRepository.existsById(id)) {
            playerRepository.deleteById(id);
        }
    }

    public void addProduct(Integer playerId, Integer productId) {
        LocalDateTime now = LocalDateTime.now();
        playerGrantRepository.save(new GrantedProducts(productId, playerId, now));
    }

    public List<Integer> getPlayerProductIds(Integer playerId) {
        List<Integer> productIds = playerGrantRepository.getPlayerProductIds(playerId);
        return productIds;
    }

    public ResponseEntity<Product[]> getProductsOfPlayer(Integer playerId) {
        List<Integer> productIds = playerGrantRepository.getPlayerProductIds(playerId);
        for (int i = 0; i < productIds.size(); i++) {
            System.out.println(productIds.get(i));
        }
        System.out.println("values after printing");
        List<String> ids = new ArrayList<>();
        String productUrl = "http://localhost:8080/";
        for (Object o : productIds) {
            ids.add(String.valueOf(o));
        }
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(productUrl + "productByIds");
        //System.out.println("builder is"+builder);
        builder.queryParam("productIds", String.join(",", ids));
        URI url = builder.build().encode().toUri();
        System.out.println("url is"+url);
        return restTemplate.getForEntity(url, Product[].class);
    }
}
