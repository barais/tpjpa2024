package DTO;

import java.io.Serializable;

public class RdvDTO extends SlotDTO implements Serializable {

    private Long id;
    private String titled;

    private StudentDTO stu;

    public RdvDTO() {
    }

    public RdvDTO(Long timeStart, Long timeEnd, ProfessionalDTO pro, String titled, StudentDTO stu) {
        super(timeStart, timeEnd, pro);
        this.titled = titled;
        this.stu = stu;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitled() {
        return titled;
    }

    public void setTitled(String titled) {
        this.titled = titled;
    }

    public StudentDTO getStudent() {
        return stu;
    }

    public void setStudent(StudentDTO stu) {
        this.stu = stu;
    }
}
