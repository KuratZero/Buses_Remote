package Menu;

import Buses.Bus;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Menu {
    private final Base park, route;
    private final Scanner scan;
    protected Menu(Statement statement) {
        this.park = new Base(statement, "buses_park");
        this.route = new Base(statement, "buses_route");
        this.scan = new Scanner(System.in);
    }
    protected boolean start() throws SQLException {
        System.out.print("""
                
                
                ------------------------------------------
                Меню:
                0. Выйти из программы.
                11/12. Вывести все автобусы в парке/на маршруте.
                21/22. Вставить автобус в парк/ на маршрут.
                3. Перевести автобус из парка на маршрут.
                4. Перевести автобус с маршрута в парк.
                Выберите действие, которое хотите сделать:\040""");
        int command = -1;
        if(scan.hasNextInt()) command = scan.nextInt();
        switch (command) {
            case 0 -> {
                return false;
            }
            case 11 -> System.out.println(park.getAll());
            case 12 -> System.out.println(route.getAll());
            case 21, 22 -> {
                System.out.println("Введите номер автобуса(число), ФИО водителя (строка в отдельной строке)" +
                        " и номер маршрута(строка без пробелов)");
                int id = scan.nextInt();
                scan.nextLine();
                String name = scan.nextLine(), plate = scan.next();
                if(command == 21) park.insert(new Bus(id, name, plate));
                else route.insert(new Bus(id, name, plate));
            }
            case 3, 4 -> {
                System.out.println("Ввеодите номер автобуса(число), который вы хотите перевести:");
                int id = scan.nextInt();
                if(command == 3) {
                    Bus tmp = park.delete(id);
                    if(tmp == null) {
                        System.out.println("Автобуса с таким id нет в парке.");
                        return true;
                    }
                    route.insert(tmp);
                } else {
                    Bus tmp = route.delete(id);
                    if(tmp == null) {
                        System.out.println("Автобуса с таким id нет на маршруте.");
                        return true;
                    }
                    park.insert(tmp);
                }
            }
            default -> System.out.println("Ошибка ввода или несуществующая команда.");
        }
        return true;
    }

}
