package car.server.controller;

import car.server.entities.Car;
import car.server.services.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {
    private final Logger logger = LoggerFactory.getLogger(CarController.class);
    @Autowired
    private CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> listCars(){
        List<Car> cars = carService.getAll();
        if(cars.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCar(@PathVariable Integer id){
        logger.info("Aca");
        Car car = carService.getCarById(id);
        if(car == null)
            return ResponseEntity.noContent().build();
        return  ResponseEntity.ok(car);
    }

    @PostMapping
    public ResponseEntity<Car> saveCar(@RequestBody Car car){
        Car newCar = carService.save(car);
        return ResponseEntity.ok(newCar);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Car>> listCarsByUserId(@PathVariable Integer userId){
        List<Car> cars = carService.byUserId(userId);
        if(cars.isEmpty())
            return ResponseEntity.noContent().build();
        return  ResponseEntity.ok(cars);
    }
}
