package userservice.userauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import userservice.userauth.entity.User;
import userservice.userauth.feignClients.BikeFeignClient;
import userservice.userauth.feignClients.CarFeignClient;
import userservice.userauth.models.Bike;
import userservice.userauth.models.Car;
import userservice.userauth.repositories.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarFeignClient carFeignClient;

    @Autowired
    private BikeFeignClient bikeFeignClient;





    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getUserById(int id){
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user){
        return userRepository.save(user);
    }

    // RestTemplate
    public List<Car> getCars(Integer userId){
        List<Car> cars = restTemplate.getForObject("http://car-service/car/user/"+userId, List.class);
        return  cars;
    }

    public List<Bike> getBikes(Integer userId){
        List<Bike> bikes = restTemplate.getForObject("http://bike-service/bike/user/"+userId, List.class);
        return  bikes;
    }

    // FeignClients

    public Car saveCar(Integer userId, Car car){
        car.setUserId(userId);
        return carFeignClient.save(car);
    }

    public Bike saveBike(Integer id, Bike bike){
        bike.setUserId(id);
        return bikeFeignClient.save(bike);
    }

    public Map<String, Object> listBikesByUserId(Integer id){
        Map<String, Object> results = new HashMap<>();
        User user = userRepository.findById(id).orElse(null);
        if (user == null){
            results.put("Message", "User not found");
            return results;
        }
        results.put("User", user);
        List<Car> cars = carFeignClient.listCarsByUserId(user.getId());
        List<Bike> bikes = bikeFeignClient.listBikesByUserId(user.getId());
        results.put("Cars", cars);
        results.put("Bikes", bikes);
        return results;
    }

}
