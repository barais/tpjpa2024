package DTO;

import java.io.Serializable;
import java.util.List;

public class ProfessionalDTO extends UserDTO implements Serializable {

    private List<SlotDTO> SlotList;

    public ProfessionalDTO() {

    }

    public ProfessionalDTO(String name, String password, List<SlotDTO> SlotList) {
        super(name, password);
        this.SlotList = SlotList;
    }

    public List<SlotDTO> getSlotList() {
        return SlotList;
    }

    public void setSlotList(List<SlotDTO> SlotList) {
        this.SlotList = SlotList;
    }

    public void addSlot(SlotDTO slot) { this.SlotList.add(slot); }

    public void removeSlot(SlotDTO slot) { this.SlotList.remove(slot); }
}
