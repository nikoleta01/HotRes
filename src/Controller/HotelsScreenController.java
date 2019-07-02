package Controller;

import Model.Hotel;
import View.HotelsScreenView;
import View.WelcomeScreenView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

import java.sql.SQLException;

public class HotelsScreenController
{


    public static void openHotelsScreenWindow(ObservableList<Hotel> data, int number_of_pages, String inserted_string, int prihlaeny, int id_user) throws SQLException
    {
        ObservableList<Hotel> data1 = data;
        final Stage primaryStage = new Stage();
        primaryStage.setTitle("HotRes.sk");
        HotelsScreenView hotelsScreen = new HotelsScreenView();
        primaryStage.setScene(new Scene(hotelsScreen.showHotelsScreenView(primaryStage,  data1, number_of_pages, inserted_string, prihlaeny, id_user), 1000, 750));
        primaryStage.show();
    }

    public static void closeHotelsScreenWindow(final Stage primaryStage)
    {
        primaryStage.close();
    }
}
