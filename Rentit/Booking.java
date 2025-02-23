import java.util.Date;

public class Booking {
    int bookingId;
    int carId;
    String customerName;
    Date startDate;
    Date endDate;
    double totalBill;

    public Booking(int bookingId, int carId, String customerName, Date startDate, Date endDate, double totalBill) {
        this.bookingId = bookingId;
        this.carId = carId;
        this.customerName = customerName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalBill = totalBill;
    }
}
