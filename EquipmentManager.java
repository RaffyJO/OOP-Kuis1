import java.util.List;
import java.util.ArrayList;

public class EquipmentManager {
    private List<Equipment> equipments;

    public EquipmentManager() {
        equipments = new ArrayList<>();
    }

    public void addEquipment(Equipment equipment) {
        equipments.add(equipment);
    }

    public void removeEquipment(Equipment equipment) {
        equipments.remove(equipment);
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }
}
