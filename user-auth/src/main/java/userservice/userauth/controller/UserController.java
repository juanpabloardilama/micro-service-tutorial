package userservice.userauth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import userservice.userauth.entity.User;
import userservice.userauth.models.Bike;
import userservice.userauth.models.Car;
import userservice.userauth.service.UserService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> listUsers() {
        List<User> users = userService.getAll();
        if (users.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        logger.info("Aca");
        User user = userService.getUserById(id);
        if (user == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User newUser = userService.save(user);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping("/cars/{userId}")
    public ResponseEntity<List<Car>> getCars(@PathVariable Integer userId) {
        User user = userService.getUserById(userId);
        if (user == null)
            return ResponseEntity.noContent().build();
        List<Car> cars = userService.getCars(user.getId());
        if (cars.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/bikes/{userId}")
    public ResponseEntity<List<Bike>> getBikes(@PathVariable Integer userId) {
        User user = userService.getUserById(userId);
        if (user == null)
            return ResponseEntity.noContent().build();
        List<Bike> bikes = userService.getBikes(user.getId());
        if (bikes.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(bikes);
    }

    @PostMapping("/save-car/{userId}")
    public ResponseEntity<Car> saveCar(@PathVariable Integer userId, @RequestBody Car car) {
        Car carNew = userService.saveCar(userId, car);
        return ResponseEntity.ok(carNew);
    }

    @PostMapping("/save-bike/{userId}")
    public ResponseEntity<Bike> saveBike(@PathVariable Integer userId, @RequestBody Bike bike) {
        Bike bikeNew = userService.saveBike(userId, bike);
        return ResponseEntity.ok(bikeNew);
    }

    @GetMapping("/get-vehicles/{userId}")
    public ResponseEntity<Map<String, Object>> listAllVehicles(@PathVariable Integer userId) {
        return ResponseEntity.ok(userService.listBikesByUserId(userId));
    }
}
