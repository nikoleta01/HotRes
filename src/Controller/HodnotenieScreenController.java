package Controller;

import Model.Hotel;
import Model.Review;
import View.HodnotenieScreenView;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

public class HodnotenieScreenController {
    public static void openHodnotenieScreenWindow(Hotel chosenHotel, ObservableList<Hotel> data, int prihlaseny, int number_of_pages, String inserted_string, ObservableList<Review> reviews, int id_user)  throws SQLException
    {
        final Stage primaryStage = new Stage();
        primaryStage.setTitle("HotRes.sk");
        HodnotenieScreenView hodnotenieScreenView = new HodnotenieScreenView();
        primaryStage.setScene(new Scene(hodnotenieScreenView.showHodnotenieScreenView(primaryStage, chosenHotel, data, prihlaseny, number_of_pages, inserted_string, reviews, id_user), 612, 295));
        primaryStage.show();
    }

    public static void closeHodnotenieScreenWindow(final Stage primaryStage)
    {
        primaryStage.close();
    }
}
