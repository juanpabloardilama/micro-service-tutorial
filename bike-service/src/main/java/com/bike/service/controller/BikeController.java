package com.bike.service.controller;

import com.bike.service.entities.Bike;
import com.bike.service.service.BikeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bike")
public class BikeController {
    private final Logger logger = LoggerFactory.getLogger(BikeController.class);
    @Autowired
    private BikeService bikeService;

    @GetMapping
    public ResponseEntity<List<Bike>> listBikes(){
        List<Bike> bikes = bikeService.getAll();
        if(bikes.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(bikes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bike> getBike(@PathVariable Integer id){
        logger.info("Aca");
        Bike bike = bikeService.getBikeById(id);
        if(bike == null)
            return ResponseEntity.noContent().build();
        return  ResponseEntity.ok(bike);
    }

    @PostMapping
    public ResponseEntity<Bike> saveBike(@RequestBody Bike bike){
        Bike newBike = bikeService.save(bike);
        return ResponseEntity.ok(newBike);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Bike>> listBikesByUserId(@PathVariable Integer userId){
        List<Bike> bikes = bikeService.byUserId(userId);
        if(bikes.isEmpty())
            return ResponseEntity.noContent().build();
        return  ResponseEntity.ok(bikes);
    }
}
