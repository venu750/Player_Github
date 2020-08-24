package com.eadp.assignment.player.controller;

import com.eadp.assignment.player.model.PlayerDto;
import com.eadp.assignment.player.entity.Player;
import com.eadp.assignment.player.model.Product;
import com.eadp.assignment.player.service.ConvertorService;
import com.eadp.assignment.player.service.PlayerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@RestController
@Api(value = "Api for Player-Store")
public class PlayerController {
    @Autowired
    private PlayerService playerService;
    @Autowired
    ConvertorService convertorService;

    @Autowired
    private RestTemplate restTemplate;

    @ApiOperation(value = "Api returns all players")
    @GetMapping("players")
    public ResponseEntity<List<PlayerDto>> getAllPlayersDetails(){
        List<PlayerDto> playerDtos=playerService.getAllPlayers();
        if(playerDtos.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        else
            return new ResponseEntity(playerDtos,HttpStatus.OK);
    }
    @ApiOperation(value = "Api returns player based on Id")
    @GetMapping("player/{id}")
    public ResponseEntity<PlayerDto> getPlayer(@PathVariable("id") Integer id) {
        if(playerService.playerCheckById(id)){
            return new ResponseEntity(playerService.getPlayerById(id),HttpStatus.OK);
        }
        return new ResponseEntity("player with that id not exists",HttpStatus.NOT_FOUND);
    }
    @ApiOperation(value = "Api adds player to DB")
    @PostMapping("player/add")
    public ResponseEntity saveProduct(@RequestBody Player player) {
        playerService.addPlayer(player);
        return new ResponseEntity("player added successfully",HttpStatus.OK);
    }
    @ApiOperation(value = "Api updates particular Player")
    @PutMapping("player/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Integer id,
                                           @RequestBody Player player) {

        if(playerService.playerCheckById(id)==true){
            playerService.updatePlayer(id, player);
            return new ResponseEntity("player updated successfully", HttpStatus.OK);
        }
        else {
            return new ResponseEntity("player is not present",HttpStatus.NOT_FOUND);
        }
    }
    @ApiOperation(value = "Api deletes player based on ID")
    @DeleteMapping("player/delete/{id}")
    public ResponseEntity<Player> deleteProduct(@PathVariable Integer id) {
        //  return playerService.deleteProduct(id);
        if(playerService.playerCheckById(id)){
            playerService.deletePlayerById(id);
            return new ResponseEntity("player deleted successfully",HttpStatus.OK);
        }
        return new ResponseEntity("player with that id not exists",HttpStatus.NOT_FOUND);
    }
    @ApiOperation(value = "Api granting Product to a Player")
    @PostMapping("/grant/{productId}&{playerId}")
    public ResponseEntity grantProductToPlayer(@PathVariable("productId") Integer productId,
                                               @PathVariable("playerId") Integer playerId){
        boolean playerExists = playerService.playerCheckById(playerId);
        System.out.println("player check before");
        if(playerExists==false){
            return new ResponseEntity("player is not present in DB" +
                    " so granting product is not possible", HttpStatus.NOT_FOUND);
        }
        System.out.println("player check after");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        try{
            System.out.println("the value of httpStatus before");
            HttpStatus httpStatus;
            httpStatus=restTemplate.exchange("http://localhost:8080/product/"+productId+"/grant",
                    HttpMethod.POST,entity,String.class).getStatusCode();
            System.out.println("the value of httpStatus is"+httpStatus);
            if(httpStatus==HttpStatus.OK)
                playerService.addProduct(playerId,productId);
            return new ResponseEntity("product granted successfully",HttpStatus.OK);
        }catch (HttpClientErrorException e){
            e.printStackTrace();
        }
        System.out.println("outside of exception block");
        return new ResponseEntity("Product is not available " +
                "in database to grant",HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @ApiOperation(value = "Api returns Products which are granted to a Player")
    @GetMapping("/player/{playerId}/products")
    public ResponseEntity<Product[]> getTheProductsOfPlayer(@PathVariable("playerId") Integer playerId)
    {
        if(playerService.playerCheckById(playerId)==false)
            return new ResponseEntity("player is not present", HttpStatus.NOT_FOUND);
        return playerService.getProductsOfPlayer(playerId);

    }


}
