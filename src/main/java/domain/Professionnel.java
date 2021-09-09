package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Professionnel {
    private Long id;

    private String name;

    private Departement department;

    public Professionnel() {
    }

    public Professionnel(String name, Departement department) {
        this.name = name;
        this.department = department;
    }

    public Professionnel(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    public Departement getDepartment() {
        return department;
    }

    public void setDepartment(Departement department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Professionnel [id=" + id + ", name=" + name + ", departement="
                + department.getName() + "]";
    }

}

