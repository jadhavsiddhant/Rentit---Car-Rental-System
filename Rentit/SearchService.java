import java.util.List;
import java.util.stream.Collectors;

public class SearchService {
    public List<Car> getAvailableCars(List<Car> cars) {
        return cars.stream().filter(car -> car.available).collect(Collectors.toList());
    }

    public List<Car> getCarsByPrice(List<Car> cars, double maxPrice) {
        return cars.stream().filter(car -> car.pricePerKm <= maxPrice).collect(Collectors.toList());
    }

    public List<Car> getCarsByCapacity(List<Car> cars, int requiredCapacity) {
        return cars.stream().filter(car -> car.capacity >= requiredCapacity).collect(Collectors.toList());
    }

    public List<Car> getCarsByFuelType(List<Car> cars, String fuelType) {
        return cars.stream().filter(car -> car.fuelType.equalsIgnoreCase(fuelType)).collect(Collectors.toList());
    }
}
