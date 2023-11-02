package Model;

import jakarta.persistence.*;
import java.util.List;

public class Student extends User {

    private List<Rdv> RdvList;

    public Student() {
    }

    public Student(String name, String password, List<Rdv> RdvList) {
        super(name, password);
        this.RdvList = RdvList;
    }

    @OneToMany(mappedBy="user", cascade= CascadeType.ALL)
    public List<Rdv> getRdvList() {
        return RdvList;
    }

    public void setRdvList(List<Rdv> RdvList) {
        this.RdvList = RdvList;
    }

    public void addRdv(Rdv rdv) { this.RdvList.add(rdv); }

    public void removeRdv(Rdv rdv) { this.RdvList.remove(rdv); }

    @Override
    public String toString() {
        return "Student [RdvList=" + RdvList + "]";
    }
}
