package Controller;

import View.ReservationScreenView;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ReservationScreenController {

    public static void openRezervaciaScreenWindow() throws SQLException
    {
        final Stage primaryStage = new Stage();
        primaryStage.setTitle("HotRes.sk");
        ReservationScreenView reservationScreenView = new ReservationScreenView();
        primaryStage.setScene(new Scene(reservationScreenView.showRezervaciaScreenView(primaryStage), 350, 450));
        primaryStage.show();
    }

    public static void closeRezervaciaScreenWindow(final Stage primaryStage)
    {
        primaryStage.close();
    }

}
