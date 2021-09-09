package model;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Users {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastname;

    private String Login;

    public Users() {
    }




//    @Override
//    public String toString() {
//        return "Users [id=" + id + ", name=" + name + ", agenda="
//                + agenda.getName() + "]";
//    }


}
