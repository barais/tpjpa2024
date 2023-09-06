package domain;

import jakarta.persistence.Entity;

@Entity
public class Doll extends Toy{
    private String name;

    public Doll() {
    }

    public Doll(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "Doll [id=" + id + ", name=" + name + "]";
    }
}
