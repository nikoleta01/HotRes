import Controller.WelcomeScreenController;
import Model.DatabaseManagement;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Main extends Application
{
    @Override
    public void start(final Stage primaryStage) throws SQLException {
        WelcomeScreenController.openWelcomeScreenWindow();
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