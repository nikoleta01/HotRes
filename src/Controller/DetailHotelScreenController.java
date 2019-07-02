package Controller;

import Model.Hotel;
import Model.Review;
import View.DetailHotelScreenView;
import View.HotelsScreenView;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

public class DetailHotelScreenController {


    public static void openDetailHotelsScreenWindow(Stage previousStage, ObservableList<Hotel> data, Hotel chosenHotel, int prihlaseny, int number_of_pages, String inserted_string, ObservableList<Review> reviews, int id_user) throws SQLException
    {
        // Hotel hotel = null;
        //hotel = chosenHotel;
        final Stage primaryStage = new Stage();
        primaryStage.setTitle("HotRes.sk");
        DetailHotelScreenView detailHotelsScreen = new DetailHotelScreenView();
        primaryStage.setScene(new Scene(detailHotelsScreen.showDetailHotelsScreenView(previousStage, primaryStage, chosenHotel, prihlaseny, data, number_of_pages, inserted_string, reviews, id_user), 800, 830));
        primaryStage.show();
    }

    public static void closeDetailHotelsScreenWindow(final Stage primaryStage)
    {
        primaryStage.close();
    }
}
