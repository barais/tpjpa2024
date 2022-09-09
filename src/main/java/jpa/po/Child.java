package jpa.po;

import javax.persistence.Entity;

@Entity
public class Child extends Patient {
    String parentName;
    Integer age;

    public Child() {}

    public Child(String firstName, String lastName, String parentName, Integer age) {
        super(firstName, lastName);
        this.parentName = parentName;
        this.age = age;
    }


    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
