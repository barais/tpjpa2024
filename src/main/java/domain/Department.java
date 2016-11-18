package domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@NamedQueries({
	@NamedQuery(name="touslesdeps",query="select e from Departement e"),
	@NamedQuery(name="touslesdepscdcd",query="select e from Departement e"),
	@NamedQuery(name="touslesdepscdd",query="select e from Departement e"),
	@NamedQuery(name="touslesdepccdcds",query="select e from Departement e.."),
	@NamedQuery(name="touslesdepscdad",query="select e from Departement e..")
})
public class Department {

    private Long id;

    private String n;

    private List<Employee> employees = new ArrayList<Employee>();

    public Department() {
        super();
    }

    public Department(String name) {
        this.n = name;
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
        return n;
    }

    public void setName(String name) {
        this.n = name;
    }

    @OneToMany(mappedBy = "department", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
