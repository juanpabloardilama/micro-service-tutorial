package userservice.userauth.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import userservice.userauth.models.Bike;

import java.util.List;

@FeignClient(name = "bike-service", url = "http://localhost:8083", path = "/bike")
public interface BikeFeignClient {
    @PostMapping
    Bike save(@RequestBody Bike bike);

    @GetMapping("/user/{userId}")
    List<Bike> listBikesByUserId(@PathVariable Integer userId);
}
