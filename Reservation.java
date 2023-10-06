import java.util.Date;

public class Reservation {
    private int noReservation;
    private Peminjam peminjam;
    private Admin admin;
    private Security security;
    private Room room;
    private Date startTime;
    private Date endTime;
    private boolean approved;
    private EquipmentManager equipmentManager;

    public Reservation(Peminjam peminjam, Room room, Date startTime, Date endTime, EquipmentManager equipmentManager) {
        this.peminjam = peminjam;
        this.room = room;
        this.startTime = startTime;
        this.endTime = endTime;
        this.equipmentManager = equipmentManager;
        this.approved = false;
    }

    public void setNoReservation(int noReservation) {
        this.noReservation = noReservation;
    }

    public int getNoReservation() {
        return noReservation;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Peminjam getPeminjam() {
        return peminjam;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }

    public Room getRoom() {
        return room;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public EquipmentManager getEquipmentManager() {
        return equipmentManager;
    }

    public void setEquipmentManager(EquipmentManager equipmentManager) {
        this.equipmentManager = equipmentManager;
    }
}
