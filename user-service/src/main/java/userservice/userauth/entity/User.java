package userservice.userauth.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "users")
@Table
@Getter
@Setter
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = -2228784815938588107L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String email;

    public User(Integer id, String name, String email) {
        super();
        this.id = id;
            this.name = name;
        this.email = email;
    }
}
