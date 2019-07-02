package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseManagement
{
    Connection connection = null;

    //pripojenie k databaze
    public Connection getConnection() throws SQLException
    {
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/HotRes", "postgres", "paradajka");

        return connection;
    }

    ArrayList<String> cities = new ArrayList<String>();
    String query;

    //autocomplete opraveny
    public ArrayList<String> selectedCities(String insertedString) throws SQLException
    {
        query = "SELECT mesto FROM lokalita WHERE mesto LIKE ?";

        PreparedStatement pst = getConnection().prepareStatement(query);
        pst.setString(1,  insertedString + "%");
        ResultSet result = pst.executeQuery();
        while(result.next())
        {
            cities.add(result.getString("mesto"));
        }
        return cities;
    }

    ObservableList<Hotel> hotels = FXCollections.observableArrayList();
    ObservableList<Review> reviews = FXCollections.observableArrayList();

    public ObservableList<Hotel> selectedHotelsInCity(String insertedString) throws SQLException
    {
        query = "Select hotel_id, nazov, adresa, hviezdicky from hotel join lokalita on hotel.lokalita_id=lokalita.id where lokalita.mesto=?";

        PreparedStatement pst = getConnection().prepareStatement(query);
        pst.setString(1, insertedString );
        ResultSet result = pst.executeQuery();
        while(result.next())
        {
            int hotel_id = result.getInt("hotel_id");
            String nazov = result.getString("nazov");
            String adresa = result.getString("adresa");
            String hviezdicky = result.getString("hviezdicky");

            hotels.add(new Hotel(hotel_id, nazov, adresa, hviezdicky));
        }
        return hotels;
    }

    public Hotel selectDetails(String name, String address, String stars) throws SQLException
    {
        query = "select hotel_id, nazov, adresa, hviezdicky, email, telefonne_cislo, mesto, krajina from hotel join lokalita on hotel.lokalita_id = lokalita.id where hotel.nazov=? AND hotel.adresa=? AND hotel.hviezdicky=?";

        PreparedStatement pst = getConnection().prepareStatement(query);

        pst.setString(1, name);
        pst.setString(2, address);
        pst.setString(3, stars);

        ResultSet result = pst.executeQuery();

        Hotel chosenHotel = null;

        while(result.next())
        {
            int hote_id = result.getInt("hotel_id");
            String nazov = result.getString("nazov");
            String adresa = result.getString("adresa");
            String hviezdicky = result.getString("hviezdicky");
            String email = result.getString("email");
            String telefonne_cislo = result.getString("telefonne_cislo");
            String mesto = result.getString("mesto");
            String krajina = result.getString("krajina");

            chosenHotel = new Hotel(hote_id, nazov, adresa, hviezdicky, telefonne_cislo, email, mesto, krajina);
            //hotels.add(new Hotel(nazov, adresa, hviezdicky));
        }

        return chosenHotel;
    }

//    public ObservableList<Hotel> selectedHotelsInCountry(String insertedString) throws SQLException
//    {
//        query = "select nazov, adresa, hviezdicky, email, telefonne_cislo, mesto, krajina from hotel join lokalita on hotel.lokalita_id = lokalita.id where hotel.nazov=? AND hotel.adresa=? AND hotel.hviezdicky=?";
//
//        PreparedStatement pst = getConnection().prepareStatement(query);
//
//        pst.setString(1, name);
//        pst.setString(2, address);
//        pst.setString(3, stars);
//
//        ResultSet result = pst.executeQuery();
//
//        Hotel chosenHotel = null;
//
//        while(result.next())
//        {
//            String nazov = result.getString("nazov");
//            String adresa = result.getString("adresa");
//            String hviezdicky = result.getString("hviezdicky");
//            String email = result.getString("email");
//            String telefonne_cislo = result.getString("telefonne_cislo");
//            String mesto = result.getString("mesto");
//            String krajina = result.getString("krajina");
//
//            chosenHotel = new Hotel(nazov, adresa, hviezdicky, telefonne_cislo, email, mesto, krajina);
//            //hotels.add(new Hotel(nazov, adresa, hviezdicky));
//        }
//
//        return chosenHotel;
//    }

//    public ObservableList<Hotel> selectedHotelsInCountry(String insertedString) throws SQLException
//    {
//        query = "Select nazov, adresa, hviezdicky from hotel join lokalita on hotel.lokalita_id=lokalita.id where lokalita.krajina=?";
//
//
//        PreparedStatement pst = getConnection().prepareStatement(query);
//        pst.setString(1, insertedString);
//        ResultSet result = pst.executeQuery();
//        while(result.next())
//        {
//            String nazov = result.getString("nazov");
//            String adresa = result.getString("adresa");
//            String hviezdicky = result.getString("hviezdicky");
//
//            hotels.add(new Hotel(nazov, adresa, hviezdicky));
//        }
//        return hotels;
//    }

    //spocita, kolko hotelov bolo v danej krajine, vyuziva sa pri pagination
    public int countNumberOfHotelsInCountry(String insertedString) throws SQLException
    {
        query = "Select count(*) from hotel join lokalita on hotel.lokalita_id=lokalita.id where lokalita.krajina=?";
        PreparedStatement pst = getConnection().prepareStatement(query);
        pst.setString(1, insertedString);
        ResultSet result = pst.executeQuery();

        int number_of_pages = 0;
        while(result.next())
        {
            number_of_pages = result.getInt("count");
        }
        System.out.println("number of pages is " + number_of_pages);
        return number_of_pages;
    }

    public ObservableList<Hotel> pagination(int selected_index, String insertedString) throws SQLException
    {
        //please work
        hotels.removeAll(hotels);

        query = "Select nazov, adresa, hviezdicky, hotel_id from hotel join lokalita on hotel.lokalita_id=lokalita.id where lokalita.krajina=? order by nazov asc offset ? fetch first 20 rows only";
        PreparedStatement pst = getConnection().prepareStatement(query);
        pst.setString(1, insertedString);
        pst.setInt(2, selected_index * 20);

        ResultSet result = pst.executeQuery();
        while(result.next())
        {
            int hotel_id = result.getInt("hotel_id");
            String name = result.getString("nazov");
            String address = result.getString("adresa");
            String stars = result.getString("hviezdicky");

            hotels.add(new Hotel(hotel_id, name, address, stars));
        }
        return hotels;
    }

    public ObservableList<Review> selectedReviewForHotel(Hotel chosenHotel) throws SQLException
    {
        reviews.removeAll(reviews);

//        query = "select recenzia, hodnotenie from ohodnotenie join (Select * from hotel join lokalita on hotel.lokalita_id=lokalita.id where lokalita.krajina=? order by nazov asc offset ? fetch first 20 rows only) as kiwi on ohodnotenie.hotel_id=kiwi.hotel_id where kiwi.hotel_id = ?;";

        query = "select ohodnotenie_id, recenzia, hodnotenie from ohodnotenie join (select * from hotel join lokalita on hotel.lokalita_id = lokalita.id where lokalita.mesto=?) as kiwi on ohodnotenie.hotel_id=kiwi.hotel_id where kiwi.hotel_id=?;";
        PreparedStatement pst = getConnection().prepareStatement(query);
        pst.setString(1, chosenHotel.getCity());
        pst.setInt(2, chosenHotel.getId());

//        pst.setString(chosenHotel.getCountry());
        ResultSet result = pst.executeQuery();
        while(result.next())
        {
            int id = result.getInt("ohodnotenie_id");
            String review = result.getString("recenzia");
            double rating = result.getDouble("hodnotenie");

            reviews.add(new Review(id, review, rating));
        }
        return reviews;
    }

    public boolean checkPassword(String password, String username) throws SQLException
    {
        query = "select * from pouzivatel where pouzivatelske_meno=? AND heslo=crypt(?, heslo);";
        PreparedStatement pst = getConnection().prepareStatement(query);
        pst.setString(1, username);
        pst.setString(2, password);

        boolean correct = false;
        ResultSet result = pst.executeQuery();
        while(result.next())
        {
            correct = true;
        }
        return correct;
    }

    public int getIDUsers(String username) throws SQLException
    {
        query = "select * from pouzivatel where pouzivatelske_meno=?";
        PreparedStatement pst = getConnection().prepareStatement(query);
        pst.setString(1, username);
        int id_users = 0;
        ResultSet result = pst.executeQuery();
        while (result.next())
        {
            id_users = result.getInt("pouzivatel_id");

        }



        return id_users;
    }

    //funkcia, ktora prida recenziu
    public void insertToReviews(Review review, int id_user, int id_hotel) throws SQLException
    {
        query="INSERT INTO ohodnotenie(ohodnotenie_id, hodnotenie, recenzia, hotel_id, pouzivatel_id) VALUES(DEFAULT, ?, ?, ?, ?)";

        PreparedStatement pst = getConnection().prepareStatement(query);
        pst.setDouble(1, review.getRating());
        pst.setString(2, review.getText());
        pst.setInt(3, id_hotel);
        pst.setInt(4, id_user);

        pst.addBatch();
        pst.executeBatch();
    }

    public void deleteToReviews(int id_review) throws SQLException
    {
        query="delete from ohodnotenie as kiwi where kiwi.ohodnotenie_id=?";

        PreparedStatement pst = getConnection().prepareStatement(query);
        pst.setDouble(1, id_review);

        pst.executeUpdate();

    }

}


//connection na databazu z https://docs.oracle.com/javase/tutorial/jdbc/basics/connecting.html#db_connection_url

