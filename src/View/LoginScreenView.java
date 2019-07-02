package View;

import Controller.*;
import Model.DatabaseManagement;
import Model.Hotel;
import Model.Review;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.SQLException;

public class LoginScreenView {

    public AnchorPane showPrihlasenieScreenView(final Stage primaryStage, ObservableList<Hotel> data, int number_of_pages, String inserted_string, int id_user) throws SQLException{

        DatabaseManagement execute = new DatabaseManagement();
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(400,250);

        Label login = new Label("Login: ");
        login.setAlignment(Pos.TOP_CENTER);
        login.setContentDisplay(ContentDisplay.CENTER);
        login.setLayoutX(65);
        login.setLayoutY(73);
        login.setFont(new Font(12));

        Label password = new Label("Heslo: ");
        password.setAlignment(Pos.TOP_CENTER);
        password.setContentDisplay(ContentDisplay.CENTER);
        password.setLayoutX(65);
        password.setLayoutY(120);
        password.setFont(new Font(12));

        TextField loginT = new TextField();
        loginT.setLayoutX(123);
        loginT.setLayoutY(68);

        PasswordField passwordT = new PasswordField();
        passwordT.setLayoutX(123);
        passwordT.setLayoutY(115);

        Button prihlas = new Button("Prihlásiť sa");
        prihlas.setLayoutX(142);
        prihlas.setLayoutY(169);
        prihlas.setMnemonicParsing(false);


        prihlas.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    //DetailHotelScreenController.openDetailHotelsScreenWindow();
                    if (execute.checkPassword(passwordT.getText(), loginT.getText()) == true)
                    {
                        int newIDuser = execute.getIDUsers(loginT.getText());
                        LoginScreenController.closePrihlasenieScreenWindow(primaryStage);
                        HotelsScreenController.openHotelsScreenWindow(data, number_of_pages, inserted_string, 1, newIDuser);

                    }
                    else
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error.");
                        alert.setContentText("Incorrect password.");
                        alert.showAndWait();
                    }







                }catch(SQLException e){
                    e.printStackTrace();
                }

            }
        });


        anchorPane.getChildren().add(login);
        anchorPane.getChildren().add(password);
        anchorPane.getChildren().add(loginT);
        anchorPane.getChildren().add(passwordT);
        anchorPane.getChildren().add(prihlas);

        return anchorPane;
    }

    public AnchorPane showPrihlasenieScreenView(final Stage primaryStage, int screen, int id_user) throws SQLException
    {


        DatabaseManagement execute = new DatabaseManagement();
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(400,250);

        Label login = new Label("Login: ");
        login.setAlignment(Pos.TOP_CENTER);
        login.setContentDisplay(ContentDisplay.CENTER);
        login.setLayoutX(65);
        login.setLayoutY(73);
        login.setFont(new Font(12));

        Label password = new Label("Heslo: ");
        password.setAlignment(Pos.TOP_CENTER);
        password.setContentDisplay(ContentDisplay.CENTER);
        password.setLayoutX(65);
        password.setLayoutY(120);
        password.setFont(new Font(12));

        TextField loginT = new TextField();
        loginT.setLayoutX(123);
        loginT.setLayoutY(68);

        PasswordField passwordT = new PasswordField();
        passwordT.setLayoutX(123);
        passwordT.setLayoutY(115);

        Button prihlas = new Button("Prihlásiť sa");
        prihlas.setLayoutX(142);
        prihlas.setLayoutY(169);
        prihlas.setMnemonicParsing(false);


        prihlas.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    //DetailHotelScreenController.openDetailHotelsScreenWindow();

                    if (execute.checkPassword(passwordT.getText(), loginT.getText()) == true)
                    {
                        int newIDuser = execute.getIDUsers(loginT.getText());
                        LoginScreenController.closePrihlasenieScreenWindow(primaryStage);
                        WelcomeScreenController.openWelcomeScreenWindow(1, newIDuser);
                    }
                    else
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error.");
                        alert.setContentText("Incorrect password.");
                        alert.showAndWait();
                    }




                }catch(SQLException e){
                    e.printStackTrace();
                }

            }
        });

        anchorPane.getChildren().add(login);
        anchorPane.getChildren().add(password);
        anchorPane.getChildren().add(loginT);
        anchorPane.getChildren().add(passwordT);
        anchorPane.getChildren().add(prihlas);

        return anchorPane;
    }

    public AnchorPane showPrihlasenieScreenView(Stage previousStage, final Stage primaryStage, int screen, Hotel chosenHotel, ObservableList<Hotel> data, int number_of_pages, String inserted_string, ObservableList<Review> reviews, int prihlaseny, int id_user) throws SQLException{

        DatabaseManagement execute = new DatabaseManagement();
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(400,250);

        Label login = new Label("Login: ");
        login.setAlignment(Pos.TOP_CENTER);
        login.setContentDisplay(ContentDisplay.CENTER);
        login.setLayoutX(65);
        login.setLayoutY(73);
        login.setFont(new Font(12));

        Label password = new Label("Heslo: ");
        password.setAlignment(Pos.TOP_CENTER);
        password.setContentDisplay(ContentDisplay.CENTER);
        password.setLayoutX(65);
        password.setLayoutY(120);
        password.setFont(new Font(12));

        TextField loginT = new TextField();
        loginT.setLayoutX(123);
        loginT.setLayoutY(68);

        PasswordField passwordT = new PasswordField();
        passwordT.setLayoutX(123);
        passwordT.setLayoutY(115);

        Button prihlas = new Button("Prihlásiť sa");
        prihlas.setLayoutX(142);
        prihlas.setLayoutY(169);
        prihlas.setMnemonicParsing(false);


        prihlas.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    //DetailHotelScreenController.openDetailHotelsScreenWindow();

                    if (execute.checkPassword(passwordT.getText(), loginT.getText()) == true)
                    {
                        int newIDuser = execute.getIDUsers(loginT.getText());



                        LoginScreenController.closePrihlasenieScreenWindow(primaryStage);

                        if(screen == 1){
                            DetailHotelScreenController.closeDetailHotelsScreenWindow(primaryStage);
                            HotelsScreenController.closeHotelsScreenWindow(previousStage);
                            HotelsScreenController.openHotelsScreenWindow(data, number_of_pages, inserted_string, 1, newIDuser);
                            //DetailHotelScreenController.openDetailHotelsScreenWindow(previousStage, data, chosenHotel, 1, number_of_pages, inserted_string, reviews);
                            ReservationScreenController.openRezervaciaScreenWindow();
                        }else if(screen == 2){

                            DetailHotelScreenController.closeDetailHotelsScreenWindow(primaryStage);
                            HotelsScreenController.closeHotelsScreenWindow(previousStage);
                            HotelsScreenController.openHotelsScreenWindow(data, number_of_pages, inserted_string, 1, newIDuser);
                            //DetailHotelScreenController.openDetailHotelsScreenWindow(previousStage, data, chosenHotel, 1, number_of_pages, inserted_string, reviews);
                            HodnotenieScreenController.openHodnotenieScreenWindow(chosenHotel, data, prihlaseny, number_of_pages, inserted_string, reviews, newIDuser);

                    }
                    else
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error.");
                        alert.setContentText("Incorrect password.");
                        alert.showAndWait();
                    }


                    }
                }catch(SQLException e){
                    e.printStackTrace();
                }

            }
        });

        anchorPane.getChildren().add(login);
        anchorPane.getChildren().add(password);
        anchorPane.getChildren().add(loginT);
        anchorPane.getChildren().add(passwordT);
        anchorPane.getChildren().add(prihlas);

        return anchorPane;
    }
}
