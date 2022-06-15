package Menu;

import java.io.IOException;
import java.sql.*;


public class Start {
    static private final String DB_URL = "jdbc:postgresql://localhost:5432/name_database";
    static private final String USER = "login";
    static private final String PASS = "password";

    public static void main(String[] argv) {
        Connection connection;
        try {
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                Menu man = new Menu(statement);
                System.out.println("Добро пожаловать!");
                while(man.start()) {
                    System.out.println("Нажмите Enter для продолжения.");
                    System.in.read();
                }
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            } finally {
                System.out.println("До новых встреч!");
            }
        } else {
            System.out.println("Failed to make connection to database");
        }
    }
}
