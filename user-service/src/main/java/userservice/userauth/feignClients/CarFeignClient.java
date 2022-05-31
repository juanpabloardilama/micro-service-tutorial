package userservice.userauth.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import userservice.userauth.models.Car;

import java.util.List;

@FeignClient(name = "car-service", path = "/car")
public interface CarFeignClient {
    @PostMapping
    public Car save(@RequestBody Car ca);

    @GetMapping("/user/{userId}")
    List<Car> listCarsByUserId(@PathVariable Integer userId);
}
