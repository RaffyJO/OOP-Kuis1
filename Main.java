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
            System.out.println("5. Edit Reservasi");
            System.out.println("6. Keluar");
            System.out.print("Pilih menu (1-6): ");
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

                    EquipmentManager equipmentManager = new EquipmentManager();
                    boolean addingEquipment = true;
                    System.out.println("Masukan nama peralatan yang ingin dipinjam\nKetik 'done' untuk berhenti: ");
                    while (addingEquipment) {
                        String newEquipment = scanner.nextLine();

                        if (newEquipment.equalsIgnoreCase("done")) {
                            addingEquipment = false;
                        } else {
                            equipmentManager.addEquipment(new Equipment(newEquipment));
                        }
                    }

                    Reservation newReservation = new Reservation(peminjam, room, startTime, endTime, equipmentManager);
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
                    Reservation selectedReservation = null;
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

                    if (reservationNumber <= 0
                            || reservationNumber > campus.getReservationManager().getReservations().size()) {
                        System.out.println("Nomor peminjaman tidak valid");
                        continue;
                    } else {
                        selectedReservation = campus.getReservationManager().getReservations()
                                .get(reservationNumber - 1);
                    }

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
                        continue;
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
                        System.out.println("Daftar peralatan  : ");
                        for (Equipment equipmentItem : reservationList.getEquipmentManager().getEquipments()) {
                            System.out.println("- " + equipmentItem.getEquipmentName());
                        }
                    }
                    System.out.println();
                    break;
                case 4:
                    System.out.print("Masukkan nomor reservasi: ");
                    int deleteReservasi = scanner.nextInt();

                    boolean reservationFound = false;

                    for (Reservation reservation : campus.getReservationManager().getReservations()) {
                        if (reservation.getNoReservation() == deleteReservasi) {
                            if (reservation.getNoReservation() == deleteReservasi) {
                                campus.getReservationManager().removeReservation(reservation);
                                System.out.println(
                                        "Nomor reservasi " + reservation.getNoReservation() + " berhasil dihapus");
                                reservationFound = true;
                                break;
                            }
                        }
                    }

                    if (!reservationFound) {
                        System.out.println("Reservasi dengan nomor tersebut tidak ditemukan.");
                    }
                    break;
                case 5:
                    System.out.print("Masukkan nomor reservasi untuk mengedit: ");
                    int numberReservasiToEdit = scanner.nextInt();
                    scanner.nextLine(); // Menangani newline yang tersisa setelah nextInt()

                    boolean reservationCheck = false;

                    for (Reservation reservation : campus.getReservationManager().getReservations()) {
                        if (reservation.getNoReservation() == numberReservasiToEdit) {
                            // Menampilkan data reservasi yang akan diedit
                            System.out.println("--------------");
                            System.out.println("Data Reservasi yang akan Diedit:");
                            System.out.println("Resrvation number: " + reservation.getNoReservation());
                            System.out.println("Room             : " + reservation.getRoom().getRoomId());
                            System.out.println("Peminjam         : " + reservation.getPeminjam().getName());
                            System.out.println("Start Time       : " + reservation.getStartTime());
                            System.out.println("End Time         : " + reservation.getEndTime());
                            System.out.println("--------------");

                            // Meminta input data baru
                            System.out.print("Masukkan nama peminjam baru: ");
                            String newName = scanner.nextLine();

                            System.out.print("Nomor Ruang: ");
                            String newRoomId = scanner.nextLine();

                            System.out.print("Waktu Mulai (dd/MM/yyyy HH:mm): ");
                            String newStartTimeStr = scanner.nextLine();
                            Date newStartTime = dateFormat.parse(newStartTimeStr);

                            System.out.print("Waktu Selesai (dd/MM/yyyy HH:mm): ");
                            String newEndTimeStr = scanner.nextLine();
                            Date newEndTime = dateFormat.parse(newEndTimeStr);

                            EquipmentManager newEquipmentManager = new EquipmentManager();
                            boolean newAddingEquipment = true;
                            System.out.println(
                                    "Masukan nama peralatan yang ingin dipinjam\nKetik 'done' untuk berhenti: ");
                            while (newAddingEquipment) {
                                String newEquipment = scanner.nextLine();

                                if (newEquipment.equalsIgnoreCase("done")) {
                                    newAddingEquipment = false;
                                } else {
                                    newEquipmentManager.addEquipment(new Equipment(newEquipment));
                                }
                            }

                            // Mengupdate data reservasi
                            reservation.getPeminjam().setName(newName);
                            reservation.getRoom().setRoomId(newRoomId);
                            reservation.setStartTime(newStartTime);
                            reservation.setEndTime(newEndTime);
                            reservation.setAdmin(null);
                            reservation.setSecurity(null);
                            reservation.setApproved(false);
                            reservation.setEquipmentManager(newEquipmentManager);

                            System.out.println("Data Reservasi berhasil diperbarui.");
                            reservationCheck = true;
                            break; // Keluar dari loop setelah reservasi ditemukan dan diupdate
                        }
                    }

                    if (!reservationCheck) {
                        System.out.println("Reservasi dengan nomor tersebut tidak ditemukan.");
                    }
                    break;
                case 6:
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
