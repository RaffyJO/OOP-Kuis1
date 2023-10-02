public class Admin extends Person {
    private String position;

    public Admin(String name, String id, String position) {
        super(name, id);
        this.position = position;
    }

    public void approveReservation(Reservation reservation) {
        reservation.setApproved(true);
    }

    public String getPosition() {
        return position;
    }
}
