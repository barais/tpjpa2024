package DTO;

import java.io.Serializable;

public class RdvDTO extends SlotDTO implements Serializable {
    private String titled;

    private StudentDTO stu;

    public RdvDTO() {
    }

    public RdvDTO(Long timeStart, Long timeEnd, ProfessionalDTO pro, String titled, StudentDTO stu) {
        super(timeStart, timeEnd, pro);
        this.titled = titled;
        this.stu = stu;
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
