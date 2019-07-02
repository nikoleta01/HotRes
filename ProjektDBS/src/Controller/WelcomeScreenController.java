package Controller;

import View.WelcomeScreenView;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

public class WelcomeScreenController
{
    public static void openWelcomeScreenWindow() throws SQLException {
        final Stage primaryStage = new Stage();
        primaryStage.setTitle("Vitajte!");
        WelcomeScreenView welcomeScreen = new WelcomeScreenView();
        primaryStage.setScene(new Scene(welcomeScreen.showWelcomeScreenView(primaryStage), 515, 317));
        primaryStage.show();
    }

    public static void closeWelcomeScreenWindow(final Stage primaryStage)
    {
        primaryStage.close();
    }
}
