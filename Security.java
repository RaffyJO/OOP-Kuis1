public class Security extends Person {
    private String badgeId;

    public Security(String name, String id, String badgeId) {
        super(name, id);
        this.badgeId = badgeId;
    }

    public void approveReservation(Reservation reservation) {
        reservation.setApproved(true);
    }

    public String getBadgeId() {
        return badgeId;
    }
}