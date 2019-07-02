package Model;

import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseManagement
{
    Connection connect = null;

    //pripojenie k databaze
    public Connection getConnection() throws SQLException
    {
        //static final String JDBC_DRIVER = "org.postgresql.Driver";

        connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/rezervacny_system", "postgres", "paradajka");

        //System.out.println("Connection to database successful.");
        //statement.execute("INSERT INTO lokalita (id, mesto, krajina) VALUES (51, 'Dublin', 'Ireland')");

        return connect;
    }

    ArrayList<String> cities = new ArrayList<String>();

    //select miest z databazy
    public ArrayList<String> selectedCities() throws SQLException
    {
        getConnection();
        Statement sta = connect.createStatement();
        ResultSet result = sta.executeQuery("SELECT mesto FROM lokalita");
        while(result.next())
        {
            cities.add(result.getString("mesto"));
        }

        //vratime cely array list vyselectovanych miest
        return cities;
    }
     //TODO dat informaciu ze prihlasenie bolo splnene oki
    // TODO prerobit na error window alebo warning window

}
//connection na databazu z https://docs.oracle.com/javase/tutorial/jdbc/basics/connecting.html#db_connection_url
