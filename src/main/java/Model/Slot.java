package Model;

public class Slot extends Rdv {
    public Integer timeSlot;

    public String titledSlot;

    public Slot() {
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

    @Override
    public String toString() {
        return "Slot [timeSlot=" + timeSlot + ", titledSlot=" + titledSlot + "]";
    }
}
