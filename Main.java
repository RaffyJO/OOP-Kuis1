import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        int reservationListNumber = 1;

        Campus campus = new Campus("Polinema");
        List<Reservation> reservations = campus.getReservationManager().getReservations();

        while (true) {
            System.out.println("+-------------------------------+");
            System.out.println(" Menu Peminjaman Kampus " + campus.getCampusName());
            System.out.println("+-------------------------------+");
            System.out.println("1. Peminjaman Ruang");
            System.out.println("2. Persetujuan Admin dan Security");
            System.out.println("3. Daftar Reservasi");
            System.out.println("4. Hapus Reservasi");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu (1/2/3/4/5): ");
            int choice = scanner.nextInt();

            scanner.nextLine(); // Membaca newline setelah input angka

            switch (choice) {
                case 1:
                    System.out.println("-------------------------------");
                    System.out.println("Form Reservasi peminjaman Ruang");
                    System.out.println("-------------------------------");
                    System.out.print("Nama Peminjam: ");
                    String name = scanner.nextLine();
                    System.out.print("ID Peminjam: ");
                    String id = scanner.nextLine();
                    System.out.print("Nomor Mahasiswa (jika peminjam adalah mahasiswa, jika bukan kosongkan): ");
                    int studentId = scanner.nextInt();
                    scanner.nextLine(); // Membaca newline setelah input angka

                    Peminjam peminjam = new Peminjam(name, id, studentId);

                    System.out.print("Nomor Ruang: ");
                    String roomId = scanner.nextLine();
                    Room room = new Room(roomId);

                    System.out.print("Waktu Mulai (dd/MM/yyyy HH:mm): ");
                    String startTimeStr = scanner.nextLine();
                    Date startTime = dateFormat.parse(startTimeStr);

                    System.out.print("Waktu Selesai (dd/MM/yyyy HH:mm): ");
                    String endTimeStr = scanner.nextLine();
                    Date endTime = dateFormat.parse(endTimeStr);

                    Reservation newReservation = new Reservation(peminjam, room, startTime, endTime);
                    if (campus.getReservationManager().isReservationColliding(newReservation)) {
                        System.out.println(
                                "Reservasi bertabrakan dengan reservasi yang sudah ada. Silakan pilih waktu atau ruangan lain.");
                    } else {
                        newReservation.setNoReservation(reservationListNumber);
                        campus.getReservationManager().addReservation(newReservation);
                        System.out.println("Peminjaman berhasil ditambahkan.");
                        System.out.println("Nomor reservasi anda adalah : " + newReservation.getNoReservation());
                        reservationListNumber++;
                    }
                    System.out.println();
                    break;
                case 2:
                    // meminta persetujuan dari admin
                    Admin admin = new Admin("Susi", "001", "JTI Admin");
                    Security security = new Security("Budi", "01", "Pamdal");
                    System.out.println("----------------------------------");
                    System.out.println("Form Persetujuan Reservasi Ruangan");
                    System.out.println("----------------------------------");
                    System.out.println("Security      |    Admin");
                    System.out.println("1. " + security.getName() + "       |    1. " + admin.getName());
                    System.out.print("Nomor Peminjaman yang akan disetujui: ");
                    int reservationNumber = scanner.nextInt();

                    if (campus.getReservationManager().getReservations().isEmpty()) {
                        System.out.println("Data reservasi masih kosong!");
                        break;
                    }

                    Reservation selectedReservation = campus.getReservationManager().getReservations()
                            .get(reservationNumber - 1);

                    System.out.print("Nama Security: ");
                    String securityName = scanner.next();
                    System.out.print("Nama Admin: ");
                    String adminName = scanner.next();

                    if (adminName.equalsIgnoreCase(admin.getName())
                            && securityName.equalsIgnoreCase(security.getName())) {
                        selectedReservation.setAdmin(admin);
                        admin.approveReservation(selectedReservation);

                        selectedReservation.setSecurity(security);
                        security.approveReservation(selectedReservation);

                        System.out.println("Peminjaman berhasil disetujui oleh Admin dan Security.");
                    } else {
                        System.out.println("Nama admin atau security yang diinputkan salah!");
                    }
                    System.out.println();
                    break;
                case 3:
                    // Menampilkan daftar reservasi yang ada
                    System.out.println("------------------");
                    System.out.println("-Daftar Reservasi-");
                    System.out.println("------------------");
                    for (int i = 0; i < reservations.size(); i++) {
                        Reservation reservationList = reservations.get(i);
                        String namaAdmin = "-";
                        String namaSecurity = "-";
                        if (reservationList.getAdmin() != null && !reservationList.getAdmin().getName().isEmpty()) {
                            namaAdmin = reservationList.getAdmin().getName();
                        }

                        if (reservationList.getSecurity() != null
                                && !reservationList.getSecurity().getName().isEmpty()) {
                            namaSecurity = reservationList.getSecurity().getName();
                        }
                        System.out.println("Reservation number: " + reservationList.getNoReservation());
                        System.out.println("Room              : " + reservationList.getRoom().getRoomId());
                        System.out.println("Peminjam          : " + reservationList.getPeminjam().getName());
                        System.out.println("Start Time        : " + reservationList.getStartTime());
                        System.out.println("End Time          : " + reservationList.getEndTime());
                        System.out.println("Admin             : " + namaAdmin);
                        System.out.println("Security          : " + namaSecurity);
                        System.out.println("Approved          : " + reservationList.isApproved());
                        System.out.println();
                    }
                    System.out.println();
                    break;
                case 4:
                    System.out.print("Masukkan nomor reservasi: ");
                    int deleteReservasi = scanner.nextInt();

                    for (int i = 0; i < reservationListNumber; i++) {
                        Reservation reservationList = reservations.get(i);
                        if (reservationList.getNoReservation() == deleteReservasi) {

                            campus.getReservationManager()
                                    .removeReservation(reservationList);
                            System.out.println(
                                    "Nomor reservasi " + reservationList.getNoReservation() + " berhasil dihapus");
                            break;
                        } else {
                            System.out.println("Nomor reservasi tidak sesuai!");
                        }
                    }
                    System.out.println();
                    break;
                case 5:
                    System.out.println("Terima kasih. Program selesai.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                    break;
            }
        }
    }
}
