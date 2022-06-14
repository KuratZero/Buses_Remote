package Buses;

public record Bus(int id, String name, String plate) {

    @Override
    public String toString() {
        return "\nНомер маршрута: " + id + ", ФИО водителя: " + name + ", Номер маршрута: " + plate + ";";
    }
}
