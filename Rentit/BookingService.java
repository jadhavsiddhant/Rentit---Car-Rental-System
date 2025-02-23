import java.sql.*;
import java.util.Date;

public class BookingService {
    public boolean checkAvailability(int carId) {
        String sql = "SELECT available FROM cars WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, carId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("available");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void bookCar(int carId, String customerName, Date startDate, Date endDate) {
        String updateCarSql = "UPDATE cars SET available = FALSE WHERE id = ?";
        String insertBookingSql = "INSERT INTO bookings (carId, customerName, startDate, endDate, totalBill) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement updateCarStmt = conn.prepareStatement(updateCarSql);
             PreparedStatement insertBookingStmt = conn.prepareStatement(insertBookingSql)) {
            conn.setAutoCommit(false);

            updateCarStmt.setInt(1, carId);
            updateCarStmt.executeUpdate();

            double totalBill = calculateBill(carId, startDate, endDate);

            insertBookingStmt.setInt(1, carId);
            insertBookingStmt.setString(2, customerName);
            insertBookingStmt.setDate(3, new java.sql.Date(startDate.getTime()));
            insertBookingStmt.setDate(4, new java.sql.Date(endDate.getTime()));
            insertBookingStmt.setDouble(5, totalBill);
            insertBookingStmt.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double calculateBill(int carId, Date startDate, Date endDate) {
        String sql = "SELECT pricePerKm FROM cars WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, carId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                double pricePerKm = rs.getDouble("pricePerKm");
                long days = (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);
                return days * pricePerKm * 100; // Assuming 100 km per day
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
