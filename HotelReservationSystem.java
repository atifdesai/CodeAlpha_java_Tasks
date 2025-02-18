import java.util.ArrayList;
import java.util.Scanner;

class Reservation {
    String name;
    int roomNumber;
    int nights;

    Reservation(String name, int roomNumber, int nights) {
        this.name = name;
        this.roomNumber = roomNumber;
        this.nights = nights;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " | Guest: " + name + " | Nights: " + nights;
    }
}

public class HotelReservationSystem {
    static ArrayList<Reservation> reservations = new ArrayList<>();
    static boolean[] rooms = new boolean[11]; // Rooms 1 to 10

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Hotel Reservation System ---");
            System.out.println("1. Book a Room");
            System.out.println("2. View Reservations");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    bookRoom(scanner);
                    break;
                case 2:
                    viewReservations();
                    break;
                case 3:
                    cancelReservation(scanner);
                    break;
                case 4:
                    System.out.println("Thank you for using the Hotel Reservation System!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void bookRoom(Scanner scanner) {
        System.out.print("Enter guest name: ");
        String name = scanner.nextLine();

        System.out.print("Enter number of nights: ");
        int nights = scanner.nextInt();

        int roomNumber = findAvailableRoom();
        if (roomNumber == -1) {
            System.out.println("Sorry, no rooms available.");
        } else {
            rooms[roomNumber] = true;
            Reservation reservation = new Reservation(name, roomNumber, nights);
            reservations.add(reservation);
            System.out.println("Room " + roomNumber + " booked successfully for " + name + ".");
        }
    }

    public static void viewReservations() {
        if (reservations.isEmpty()) {
            System.out.println("No current reservations.");
        } else {
            System.out.println("\n--- Current Reservations ---");
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        }
    }

    public static void cancelReservation(Scanner scanner) {
        System.out.print("Enter room number to cancel: ");
        int roomNumber = scanner.nextInt();

        Reservation toRemove = null;
        for (Reservation reservation : reservations) {
            if (reservation.roomNumber == roomNumber) {
                toRemove = reservation;
                break;
            }
        }

        if (toRemove != null) {
            reservations.remove(toRemove);
            rooms[roomNumber] = false;
            System.out.println("Reservation for Room " + roomNumber + " has been canceled.");
        } else {
            System.out.println("Room not found or no reservation exists.");
        }
    }

    public static int findAvailableRoom() {
        for (int i = 1; i <= 10; i++) {
            if (!rooms[i]) {
                return i;
            }
        }
        return -1; // No available room
    }
}
