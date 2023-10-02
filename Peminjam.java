public class Peminjam extends Person {
    private int studentId;

    public Peminjam(String name, String id, int studentId) {
        super(name, id);
        this.studentId = studentId;
    }

    public int getStudentId() {
        return studentId;
    }
}
