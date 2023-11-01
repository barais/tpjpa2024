package Model;

import jakarta.persistence.*;

import java.util.List;

public class Professional extends User {

    private List<Slot> SlotList;

    public Professional() {
    }

    public Professional(List<Slot> SlotList) {
        this.SlotList = SlotList;
    }

    @OneToMany(mappedBy="professional", cascade= CascadeType.ALL)
    public List<Slot> getSlotList() {
        return SlotList;
    }

    public void setSlotList(List<Slot> SlotList) {
        this.SlotList = SlotList;
    }

    public void addSlot(Slot slot) { this.SlotList.add(slot); }

    public void removeSlot(Slot slot) { this.SlotList.remove(slot); }
}
