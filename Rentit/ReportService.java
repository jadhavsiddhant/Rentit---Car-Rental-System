import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;

public class ReportService {
    public List<Car> generateReportByVehicleType(List<Car> cars, String type) {
        return cars.stream().filter(car -> car.brand.equalsIgnoreCase(type)).collect(Collectors.toList());
    }

    public List<Booking> generateBookingReport(List<Booking> bookings, Date startDate, Date endDate) {
        return bookings.stream().filter(b -> b.startDate.after(startDate) && b.endDate.before(endDate)).collect(Collectors.toList());
    }

    public double generateSalesReport(List<Booking> bookings) {
        return bookings.stream().mapToDouble(b -> b.totalBill).sum();
    }
}

