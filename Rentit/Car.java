public class Car {
    int id;
    String brand;
    String model;
    String fuelType;
    int capacity;
    double pricePerKm;
    boolean available;

    public Car(int id, String brand, String model, String fuelType, int capacity, double pricePerKm, boolean available) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.fuelType = fuelType;
        this.capacity = capacity;
        this.pricePerKm = pricePerKm;
        this.available = available;
    }
}

