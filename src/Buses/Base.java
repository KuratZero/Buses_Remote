package Buses;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Base {
    Statement stat;
    String name;
    public Base(Statement statement, String name) {
        stat = statement;
        this.name = name;
    }
    public ArrayList<Bus> getAll() throws SQLException {
        ResultSet res = stat.executeQuery("Select * from " + name + ";");
        ArrayList<Bus> buses = new ArrayList<>();
        while(res.next()) {
            buses.add(new Bus(res.getInt(1), res.getString(2), res.getString(3)));
        }
        return buses;
    }

    public void insert(Bus bus) throws SQLException {
        stat.execute("INSERT INTO " + name + " (id, driver, plate) VALUES ('"
                + bus.id() + "', '" + bus.name() + "', '" + bus.plate() + "');");
    }

    public Bus get(int id) throws SQLException {
        ResultSet rset = stat.executeQuery("SELECT * FROM " + name + " WHERE id = " + id + ";");
        if(rset.next()) {
            return new Bus(rset.getInt(1), rset.getString(2), rset.getString(3));
        } else {
            return null;
        }
    }

    public Bus delete(int id) throws SQLException {
        Bus get = this.get(id);
        if(get == null) return null;
        stat.execute("DELETE FROM " + name + " WHERE id = " + id + ";");
        return get;
    }

}
