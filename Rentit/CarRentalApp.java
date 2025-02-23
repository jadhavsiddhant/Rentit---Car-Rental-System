import java.util.Scanner;
import java.util.Date;

public class CarRentalApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LoginService loginService = new LoginService();
        CarService carService = new CarService();
        BookingService bookingService = new BookingService();

        System.out.println("Enter username: ");
        String username = scanner.next();
        System.out.println("Enter password: ");
        String password = scanner.next();

        if (!loginService.authenticate(username, password)) {
            System.out.println("Invalid credentials.");
            return;
        }

        System.out.println("Login successful!");

        while (true) {
            System.out.println("\n1. View Available Cars");
            System.out.println("2. Book a Car");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();

            if (choice == 1) {
                for (Car car : carService.getAvailableCars()) {
                    System.out.println(car.getId() + " - " + car.getBrand() + " " + car.getModel());
                }
            } else if (choice == 2) {
                System.out.println("Enter Car ID: ");
                int carId = scanner.nextInt();
                System.out.println("Enter Customer Name: ");
                String name = scanner.next();
                Booking booking = bookingService.bookCar(carId, name, new Date(), new Date());
                System.out.println("Booked! Total bill: " + booking.getTotalBill());
            } else {
                break;
            }
        }
    }
}
