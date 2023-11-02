package Model;

import jakarta.persistence.*;

@Entity
@Table(name="slot")
public class Slot {

    private Long id;

    protected Long timeStart;

    protected Long timeEnd;

    private Professional pro;

    public Slot() {
    }

    public Slot(Long timeStart, Long timeEnd, Professional pro) {
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.pro = pro;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="time_start")
    public Long getTimeStart() { return timeStart; }

    public void setTimeStart(Long timeStart) {
        this.timeStart = timeStart;
    }

    @Column(name="time_end")
    public Long getTimeEnd() { return timeEnd; }

    public void setTimeEnd(Long timeEnd) {
        this.timeEnd = timeEnd;
    }

    @ManyToOne
    public Professional getProfessional() {
        return pro;
    }

    public void setProfessional(Professional pro) {
        this.pro = pro;
    }

    public boolean contain(Slot slot) {
        return this.timeStart <= slot.timeStart & this.timeEnd <= slot.timeEnd;
    }

    @Override
    public String toString() {
        return "Slot [id=" + id + ", timeStart=" + timeStart + ", timeEnd=" + timeEnd + ", professional=" + pro + "]";
    }
}
