import Controller.WelcomeScreenController;
import Model.DatabaseManagement;
import View.WelcomeScreenView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application
{
    @Override
    public void start(final Stage primaryStage) throws SQLException {

        int id_user = 0;

        WelcomeScreenController.openWelcomeScreenWindow(0, id_user);

    }

    public static void main(String[] args)
    {
        launch(args);

        DatabaseManagement conn = new DatabaseManagement();
        try
        {
            conn.getConnection();
        }
        catch (SQLException e)
        {
            System.out.println("Error while connecting to database because " + e.getMessage());
        }
    }
}