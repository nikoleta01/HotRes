package View;

import Controller.DetailHotelScreenController;
import Controller.HodnotenieScreenController;
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


import java.io.IOException;
import java.sql.SQLException;

public class HodnotenieScreenView {
    public AnchorPane showHodnotenieScreenView(final Stage primaryStage, Hotel chosenHotel,  ObservableList<Hotel> data, int prihlaseny, int number_of_pages, String inserted_string, ObservableList<Review> reviews, int id_user) throws SQLException {

        DatabaseManagement execute = new DatabaseManagement();

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(612, 295);

        Label label = new Label("Hodnotenie");
        label.setAlignment(Pos.TOP_CENTER);
        label.setContentDisplay(ContentDisplay.CENTER);
        label.setLayoutX(246);
        label.setLayoutY(35);
        label.setFont(new Font("System Bold Italic", 24));

        Label text = new Label("Napíšte krátke hodnotenie hotela. Pomôžete tak ďalším zákazníkom pri rozhodovaní.");
        text.setAlignment(Pos.TOP_CENTER);
        text.setContentDisplay(ContentDisplay.CENTER);
        text.setLayoutX(28);
        text.setLayoutY(80);
        text.setFont(new Font(15));

        TextField pole = new TextField();
        pole.setLayoutX(39);
        pole.setLayoutY(128);
        pole.setPrefSize(537, 46);

        Slider slider = new Slider();
        slider.setLayoutX(86);
        slider.setLayoutY(188);
        slider.setMajorTickUnit(10);
        slider.setPrefSize(423, 18);
        slider.setShowTickLabels(true);

        Button ohodnot = new Button("PRIDAŤ HODNOTENIE");
        ohodnot.setLayoutX(409);
        ohodnot.setLayoutY(240);
        ohodnot.setMnemonicParsing(false);

        ohodnot.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String review = pole.getText();
                double rating = slider.getValue();
                rating = Math.round((rating/100)*10.0)/10.0;
                int hotelik_id = chosenHotel.getId();

                ObservableList<Review> reviewsList = null;

                Review newReview = new Review(0, review, rating);

                //do listu vlozim novu recenziu
                //reviews.add(newReview);

                //tu sa vlozi do databazy
                System.out.println("id user: " + id_user);
                try {
                    execute.insertToReviews(newReview, id_user, hotelik_id);

                    reviewsList = execute.selectedReviewForHotel(chosenHotel);
                } catch (SQLException e) {
                    e.printStackTrace();
                }


                //a potom sa otvori nove detail screen
                try{
                    DetailHotelScreenController.openDetailHotelsScreenWindow(primaryStage, data, chosenHotel, prihlaseny, number_of_pages, inserted_string, reviewsList, id_user);
                } catch(SQLException e){
                    e.printStackTrace();

                }



                System.out.println("recenzia: " + review + "\nhodnotenie: " + rating + "\nid_hotel: " + hotelik_id);



                HodnotenieScreenController.closeHodnotenieScreenWindow(primaryStage);
            }
        });

        anchorPane.getChildren().add(label);
        anchorPane.getChildren().add(text);
        anchorPane.getChildren().add(pole);
        anchorPane.getChildren().add(slider);
        anchorPane.getChildren().add(ohodnot);

        return anchorPane;
    }

}
