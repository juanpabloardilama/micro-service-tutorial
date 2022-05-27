package car.server.services;

import car.server.entities.Car;
import car.server.reposotories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public List<Car> getAll(){
        return carRepository.findAll();
    }

    public Car getCarById(int id){
        return carRepository.findById(id).orElse(null);
    }

    public Car save(Car user){
        return carRepository.save(user);
    }

    public List<Car> byUserId(Integer id){
        return carRepository.findByUserId(id);
    }
}
