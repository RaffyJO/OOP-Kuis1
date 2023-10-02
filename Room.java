public class Room {
    private String roomId;
    private boolean isAvailable;

    public Room(String roomId) {
        this.roomId = roomId;
        this.isAvailable = true;
    }

    public String getRoomId() {
        return roomId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
