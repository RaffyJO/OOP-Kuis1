public class Campus {
    private ReservationManager reservationManager;
    private EquipmentManager equipmentManager;
    private String campusName;

    public Campus(String campusName) {
        reservationManager = new ReservationManager();
        equipmentManager = new EquipmentManager();
        this.campusName = campusName;
    }

    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    public ReservationManager getReservationManager() {
        return reservationManager;
    }

    public EquipmentManager getEquipmentManager() {
        return equipmentManager;
    }
}
