package View;

import Controller.HlaskaScreenController;
import Controller.LoginScreenController;
import Model.Hotel;
import Model.Review;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.SQLException;

public class HlaskaScreenView {
    public AnchorPane showHlaskaScreenView(Stage previousStage, final Stage primaryStage, Hotel chosenHotel, ObservableList<Hotel> data, int screen, int prihlaseny, int number_of_pages, String inserted_string, ObservableList<Review> reviews, int id_user){
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(600,160);

        Label label = new Label("Akciu je možné vykonať až po prihlásení.");
        label.setAlignment(Pos.TOP_CENTER);
        label.setContentDisplay(ContentDisplay.CENTER);
        label.setLayoutX(84);
        label.setLayoutY(52);
        label.setFont(new Font(24));

        Button prihlas = new Button("Prihlásiť sa");
        prihlas.setLayoutX(462);
        prihlas.setLayoutY(103);
        prihlas.setMnemonicParsing(false);

        prihlas.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    HlaskaScreenController.closeHlaskaScreenWindow(primaryStage);
                    LoginScreenController.openPrihlasenieScreenWindow(previousStage, screen, chosenHotel, data, number_of_pages, inserted_string, reviews, prihlaseny, id_user);
                }catch(SQLException e){
                    e.printStackTrace();
                }

            }
        });

        anchorPane.getChildren().add(prihlas);
        anchorPane.getChildren().add(label);


        return anchorPane;
    }
}
