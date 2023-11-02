package DTO;

import java.io.Serializable;
import java.util.List;

public class StudentDTO extends UserDTO implements Serializable {

    private List<RdvDTO> RdvList;

    public StudentDTO() {
    }

    public StudentDTO(String name, String password, List<RdvDTO> RdvList) {
        super(name, password);
        this.RdvList = RdvList;
    }

    public List<RdvDTO> getRdvList() {
        return RdvList;
    }

    public void setRdvList(List<RdvDTO> RdvList) {
        this.RdvList = RdvList;
    }

    public void addRdv(RdvDTO rdv) { this.RdvList.add(rdv); }

    public void removeRdv(RdvDTO rdv) { this.RdvList.remove(rdv); }
}
