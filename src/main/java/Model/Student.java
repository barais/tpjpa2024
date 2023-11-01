package Model;

import java.util.List;

public class Student extends User {

    private List<Rdv> RdvList;

    public Student() {
    }

    public Student(List<Rdv> RdvList) {
        this.RdvList = RdvList;
    }

    public List<Rdv> getRdvList() {
        return RdvList;
    }

    public void setRdvList(List<Rdv> RdvList) {
        this.RdvList = RdvList;
    }

    public void addRdv(Rdv rdv) { this.RdvList.add(rdv); }

    public void removeRdv(Rdv rdv) { this.RdvList.remove(rdv); }
}
