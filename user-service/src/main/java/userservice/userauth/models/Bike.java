package userservice.userauth.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Bike {
    private String brand;
    private String model;
    private Integer userId;
}
