package jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Acount {
    private Long id;

    private String email;

    private String pw;

    public Acount(String email, String pw){
        this.email = email;
        this.pw = pw;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }
    public void setPw(String pw){
        this.pw = pw;
    }

    public String getPw(){
        return this.pw;
    }

    @Id
    @GeneratedValue
    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id=id;
    }


}
