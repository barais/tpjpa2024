package Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="slot")
public class Slot extends Rdv {

    @Column(name="time_slot")
    public Integer timeSlot;

    @Column(name="titled_slot")
    public String titledSlot;

    @Column(name="professional")
    private Professional pro;

    public Slot() {
    }

    public Slot(Integer timeSlot, String titledSlot, Professional pro) {
        this.timeSlot = timeSlot;
        this.titledSlot = titledSlot;
        this.pro = pro;
    }

    public Slot(Integer timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Integer getSlot() { return timeSlot; }

    public void setSlot(Integer timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getTitled() { return titledSlot; }

    public void setTitledSlot(String titledSlot) {
        this.titledSlot = titledSlot;
    }

    @ManyToOne
    public Professional getProfessional() {
        return pro;
    }

    public void setProfessional(Professional pro) {
        this.pro = pro;
    }

    @Override
    public String toString() {
        return "Slot [timeSlot=" + timeSlot + ", titledSlot=" + titledSlot + "]";
    }
}
