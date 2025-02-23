import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarService {
    public void addCar(Car car) {
        String sql = "INSERT INTO cars (brand, model, fuelType, capacity, pricePerKm, available) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, car.brand);
            stmt.setString(2, car.model);
            stmt.setString(3, car.fuelType);
            stmt.setInt(4, car.capacity);
            stmt.setDouble(5, car.pricePerKm);
            stmt.setBoolean(6, car.available);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCar(int id) {
        String sql = "DELETE FROM cars WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Car> loadCarDetails() {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM cars";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                cars.add(new Car(
                        rs.getInt("id"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getString("fuelType"),
                        rs.getInt("capacity"),
                        rs.getDouble("pricePerKm"),
                        rs.getBoolean("available")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }
}
