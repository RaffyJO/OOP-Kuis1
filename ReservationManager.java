import java.util.List;
import java.util.ArrayList;

public class ReservationManager {
    private List<Reservation> reservations;

    public ReservationManager() {
        reservations = new ArrayList<>();
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public void removeReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public boolean isReservationColliding(Reservation newReservation) {
        for (Reservation existingReservation : reservations) {
            if (existingReservation.getRoom().getRoomId().equals(newReservation.getRoom().getRoomId())) {
                // Memeriksa apakah reservasi bertabrakan berdasarkan waktu
                if (existingReservation.getStartTime().before(newReservation.getEndTime()) &&
                        existingReservation.getEndTime().after(newReservation.getStartTime())) {
                    return true; // Ada tabrakan
                }
            }
        }
        return false; // Tidak ada tabrakan
    }
}
