import java.util.Date;

public class Reservation {
    private Person person;
    private Room room;
    private Date startTime;
    private Date endTime;
    private boolean approved;

    public Reservation(Person person, Room room, Date startTime, Date endTime) {
        this.person = person;
        this.room = room;
        this.startTime = startTime;
        this.endTime = endTime;
        this.approved = false;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Person getPerson() {
        return person;
    }

    public Room getRoom() {
        return room;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }
}
