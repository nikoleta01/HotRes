package Controller;

import Model.Hotel;
import Model.Review;
import View.LoginScreenView;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

public class LoginScreenController {


    //pouzivane pri hotel screen
    public static void openPrihlasenieScreenWindow(ObservableList<Hotel> data, int number_of_pages, String inserted_string, int id_user) throws SQLException
    {
        final Stage primaryStage = new Stage();
        primaryStage.setTitle("HotRes.sk");
        LoginScreenView loginScreenView = new LoginScreenView();
        primaryStage.setScene(new Scene(loginScreenView.showPrihlasenieScreenView(primaryStage, data, number_of_pages, inserted_string, id_user), 400, 250));
        primaryStage.show();
    }

    //pouzivany pri welcome screen
    public static void openPrihlasenieScreenWindow(int screen, int id_user) throws SQLException
    {
        final Stage primaryStage = new Stage();
        primaryStage.setTitle("HotRes.sk");
        LoginScreenView loginScreenView = new LoginScreenView();
        primaryStage.setScene(new Scene(loginScreenView.showPrihlasenieScreenView(primaryStage, screen, id_user), 400, 250));
        primaryStage.show();
    }

    public static void openPrihlasenieScreenWindow(Stage previousStage, int screen, Hotel chosenHotel, ObservableList<Hotel> data, int number_of_pages, String inserted_string, ObservableList<Review> reviews, int prihlaseny, int id_user) throws SQLException
    {
        final Stage primaryStage = new Stage();
        primaryStage.setTitle("HotRes.sk");
        LoginScreenView loginScreenView = new LoginScreenView();
        primaryStage.setScene(new Scene(loginScreenView.showPrihlasenieScreenView(previousStage, primaryStage, screen, chosenHotel, data, number_of_pages, inserted_string, reviews, prihlaseny, id_user), 400, 250));
        primaryStage.show();
    }

    public static void closePrihlasenieScreenWindow(final Stage primaryStage)
    {
        primaryStage.close();
    }
}
