package DTO;

import java.io.Serializable;

public class SlotDTO implements Serializable {

    private Long id;
    protected Long timeStart;
    protected Long timeEnd;
    private ProfessionalDTO pro;

    public SlotDTO() {
    }

    public SlotDTO(Long timeStart, Long timeEnd, ProfessionalDTO pro) {
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.pro = pro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTimeStart() { return timeStart; }

    public void setTimeStart(Long timeStart) {
        this.timeStart = timeStart;
    }

    public Long getTimeEnd() { return timeEnd; }

    public void setTimeEnd(Long timeEnd) {
        this.timeEnd = timeEnd;
    }

    public ProfessionalDTO getProfessional() {
        return pro;
    }

    public void setProfessional(ProfessionalDTO pro) {
        this.pro = pro;
    }
}
