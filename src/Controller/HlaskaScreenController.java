package Controller;

import Model.Hotel;
import Model.Review;
import View.HlaskaScreenView;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

public class HlaskaScreenController {
    public static void openHlaskaScreenWindow(Stage previousStage, Hotel chosenHotel, ObservableList<Hotel> data, int screen, int prihlaseny, int number_of_pages, String inserted_string, ObservableList<Review> reviews, int id_user) throws SQLException
    {
        final Stage primaryStage = new Stage();
        primaryStage.setTitle("HotRes.sk");
        HlaskaScreenView hlaskaScreenView = new HlaskaScreenView();
        primaryStage.setScene(new Scene(hlaskaScreenView.showHlaskaScreenView(previousStage, primaryStage, chosenHotel, data, screen, prihlaseny, number_of_pages, inserted_string, reviews, id_user), 600, 160));
        primaryStage.show();
    }

    public static void closeHlaskaScreenWindow(final Stage primaryStage)
    {
        primaryStage.close();
    }
}
