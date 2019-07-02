package View;

import Controller.*;
import Model.DatabaseManagement;
import Model.Hotel;
import Model.Review;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.SQLException;

public class DetailHotelScreenView
{
    public AnchorPane showDetailHotelsScreenView(Stage previousStage, final Stage primaryStage, Hotel chosenHotel, int prihlaseny, ObservableList<Hotel> data, int number_of_pages, String inserted_string, ObservableList<Review> reviews, int id_user) throws SQLException
    {
        System.out.println("prihlasenie = " +  prihlaseny);
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(800,830);

        ObservableList<Review> dataReview = reviews;

//        Label name = new Label("HotRes.sk");
//        name.setAlignment(Pos.TOP_CENTER);
//        name.setContentDisplay(ContentDisplay.CENTER);
//        name.setLayoutX(510);
//        name.setLayoutY(26);
//        name.setFont(new Font(24));

        Label nazov = new Label(chosenHotel.getName());
        nazov.setAlignment(Pos.TOP_CENTER);
        nazov.setContentDisplay(ContentDisplay.CENTER);
        nazov.setLayoutX(35);
        nazov.setLayoutY(20);
        nazov.setFont(new Font("System Bold", 34));

        Label info = new Label();
        info.setAlignment(Pos.TOP_CENTER);
        info.setContentDisplay(ContentDisplay.CENTER);
        info.setLayoutX(35);
        info.setLayoutY(440);
        info.setText("Recenzie, ktoré vám môžu napomôcť lepšie sa informovať o hoteli.");

        Label recenzie = new Label("Pridané hodnotenia: ");
        recenzie.setAlignment(Pos.TOP_CENTER);
        recenzie.setContentDisplay(ContentDisplay.CENTER);
        recenzie.setLayoutX(35);
        recenzie.setLayoutY(470);
        recenzie.setFont(new Font ("System Bold", 18));


        TableView<Review> reviewTableView;


        TableColumn<Review, String> columnReview = new TableColumn<>("Recenzia");
        columnReview.setMinWidth(630);
        columnReview.setCellValueFactory(new PropertyValueFactory<>("text"));

        TableColumn<Review, String> columnHodnotenie = new TableColumn<>("Hodnotenie");
        columnHodnotenie.setMinWidth(100);
        columnHodnotenie.setCellValueFactory(new PropertyValueFactory<>("rating"));



        reviewTableView = new TableView<>();

        reviewTableView.setEditable(true);
        reviewTableView.setLayoutX(35);
        reviewTableView.setLayoutY(500);
        reviewTableView.setVisible(true);
        reviewTableView.setPrefHeight(320);
        //reviewTableView.setItems(data2);
        reviewTableView.setItems(dataReview);


        DatabaseManagement database = new DatabaseManagement();

        reviewTableView.getColumns().addAll(columnReview, columnHodnotenie);

        reviewTableView.setOnMouseClicked(event-> {
            if (event.getClickCount() == 2)
            {
                Review selectedItem = reviewTableView.getSelectionModel().getSelectedItem();
                System.out.println(selectedItem.getText() + " " + selectedItem.getRating());

                System.out.println("ahoj");
            }
        });

        ContextMenu delete = new ContextMenu();
        MenuItem textDelete = new MenuItem("zmaž");
        delete.getItems().add(textDelete);

        reviewTableView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                if(event.getButton() == MouseButton.SECONDARY)
                {
                    delete.show(reviewTableView, event.getScreenX(), event.getScreenY());

                    textDelete.setOnAction(new EventHandler(){
                        public void handle(Event event) {

                            //zmaze oznaceny zaznam
                            Review selectedItem = reviewTableView.getSelectionModel().getSelectedItem();
                            System.out.println(selectedItem.getText() + " id: " + selectedItem.getId());

                            try{
                                database.deleteToReviews(selectedItem.getId());

                                ObservableList<Review> reviews;
                                reviews = database.selectedReviewForHotel(chosenHotel);
                                reviewTableView.setItems(reviews);

                            }catch (SQLException e)
                            {
                                e.printStackTrace();
                            }


                        }
                    });
                }
            }
        });





//        ScrollBar scrollBar = new ScrollBar();
//        scrollBar.setLayoutX(749);
//        scrollBar.setLayoutY(105);
//        scrollBar.setOrientation(Orientation.VERTICAL);
//        scrollBar.setPrefSize(18, 415);

        Button recenziaButton = new Button("OHODNOTIŤ HOTEL");
        recenziaButton.setLayoutX(435);
        recenziaButton.setLayoutY(350);
        recenziaButton.setPrefSize(330,34);
        recenziaButton.setMnemonicParsing(false);

        recenziaButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    if(prihlaseny == 1){
                        System.out.println("prihlaseny, takze moze pridat hodnotenie");
                        DetailHotelScreenController.closeDetailHotelsScreenWindow(primaryStage);
                        HodnotenieScreenController.openHodnotenieScreenWindow(chosenHotel, data, prihlaseny, number_of_pages, inserted_string, reviews, id_user);
                    }
                    else if(prihlaseny == 0){
                        System.out.println("neprihlaseny, takze nemoze pridat hodnotenie");
                        DetailHotelScreenController.closeDetailHotelsScreenWindow(primaryStage);
                        HlaskaScreenController.openHlaskaScreenWindow(previousStage, chosenHotel, data, 2, prihlaseny, number_of_pages, inserted_string, reviews, id_user);
                    }

                  System.out.println("pridanie recenzie");
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        });

        Button dostupnostButton = new Button("CHCEM SI ZAREZERVOVAŤ POBYT");
        dostupnostButton.setLayoutX(35);
        dostupnostButton.setLayoutY(350);
        dostupnostButton.setPrefSize(330,34);

        dostupnostButton.setMnemonicParsing(true);

        dostupnostButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    if(prihlaseny == 1){
                        System.out.println("prihlaseny, takze moze pridat rezervaciu");
                        ReservationScreenController.openRezervaciaScreenWindow();
                    }
                    else if(prihlaseny == 0){
                        System.out.println("neprihlaseny, takze nemoze pridat rezervaciu");

                        DetailHotelScreenController.closeDetailHotelsScreenWindow(primaryStage);
                        HlaskaScreenController.openHlaskaScreenWindow(previousStage, chosenHotel, data, 1, prihlaseny, number_of_pages, inserted_string, reviews, id_user);
                    }
                    System.out.println("pridanie rezervacie");


//                    dostupnostButton.setStyle("-fx-background-color: #00ff00; ");
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setPrefSize(400, 250);
        gridPane.setLayoutX(35);
        gridPane.setLayoutY(100);

      //  anchorPane.getChildren().add(name);
        anchorPane.getChildren().add(nazov);
        anchorPane.getChildren().add(recenzie);
//        anchorPane.getChildren().add(scrollBar);
        anchorPane.getChildren().add(recenziaButton);
        anchorPane.getChildren().add(dostupnostButton);
        anchorPane.getChildren().add(info);
        anchorPane.getChildren().add(reviewTableView);


        gridPane.getColumnConstraints().add(new ColumnConstraints(180));
        gridPane.getColumnConstraints().add(new ColumnConstraints(260));
        gridPane.getColumnConstraints().add(new ColumnConstraints(130));
        gridPane.getColumnConstraints().add(new ColumnConstraints(50));

        Label hviezdickyLabel = new Label("Počet hviezdičiek: ");
        GridPane.setConstraints(hviezdickyLabel, 0, 0);
        hviezdickyLabel.setFont(new Font ("System Bold", 17));

        Label hviezdicky = new Label(chosenHotel.getStars());
        GridPane.setConstraints(hviezdicky, 1, 0);

        Label adresaLabel = new Label("Adresa: ");
        GridPane.setConstraints(adresaLabel, 0, 1);
        adresaLabel.setFont(new Font ("System Bold", 17));

        Label adresa = new Label(chosenHotel.getAddress());
        GridPane.setConstraints(adresa, 1, 1);

        Label mestoLabel = new Label("Mesto: ");
        GridPane.setConstraints(mestoLabel, 0, 2);
        mestoLabel.setFont(new Font ("System Bold", 17));

        Label mesto = new Label(chosenHotel.getCity());
        GridPane.setConstraints(mesto, 1, 2);

        Label krajinaLabel = new Label("Krajina: ");
        GridPane.setConstraints(krajinaLabel, 0, 3);
        krajinaLabel.setFont(new Font ("System Bold", 17));

        Label krajina = new Label(chosenHotel.getCountry());
        GridPane.setConstraints(krajina, 1, 3);

        Label medzera1 = new Label(" ");
        GridPane.setConstraints(medzera1, 0, 4);

        Label medzera3 = new Label(" ");
        GridPane.setConstraints(medzera3, 1, 4);

        Label medzera4 = new Label(" ");
        GridPane.setConstraints(medzera4, 1, 5);

        Label kontaktLabel = new Label("KONTAKT");
        GridPane.setConstraints(kontaktLabel, 0, 5);
        kontaktLabel.setFont(new Font ("System Bold", 17));

        Label mailLabel = new Label("E-mail: ");
        GridPane.setConstraints(mailLabel, 0, 6);
        mailLabel.setFont(new Font ("System Bold", 17));

        Label mail = new Label(chosenHotel.getEmail());
        GridPane.setConstraints(mail, 1, 6);

        Label mobilLabel = new Label("Telefónne číslo: ");
        GridPane.setConstraints(mobilLabel, 0,7);
        mobilLabel.setFont(new Font ("System Bold", 17));

        Label mobil = new Label(chosenHotel.getPhone_number());
        GridPane.setConstraints(mobil, 1,7);

        Label medzera2 = new Label(" ");
        GridPane.setConstraints(medzera2, 0, 8);

        Label sluzbyLabel = new Label("SLUŽBY: ");
        GridPane.setConstraints(sluzbyLabel, 2, 0);
        sluzbyLabel.setFont(new Font ("System Bold", 17));

        Label wellnessLabel = new Label("Wellness");
        GridPane.setConstraints(wellnessLabel, 2, 1);

        Label bazenLabel = new Label("Bázen");
        GridPane.setConstraints(bazenLabel, 2,2);

        Label fitnessLabel = new Label("Fitness centrum");
        GridPane.setConstraints(fitnessLabel, 2, 3);

        Label parkovanieLabel = new Label("Parkovanie");
        GridPane.setConstraints(parkovanieLabel, 2, 4);

        Label restauraciaLabel = new Label("Reštaurácia");
        GridPane.setConstraints(restauraciaLabel, 2,5);

        Label barLabel = new Label("Bar");
        GridPane.setConstraints(barLabel, 2, 6);

        Label tenisLabel = new Label("Tenis");
        GridPane.setConstraints(tenisLabel, 2, 7);

        Label eskortLabel = new Label("Eskort");
        GridPane.setConstraints(eskortLabel, 2, 8);

        gridPane.getChildren().addAll(medzera1, medzera2, hviezdickyLabel, adresaLabel, mestoLabel, krajinaLabel, kontaktLabel, mailLabel, mobilLabel, sluzbyLabel, wellnessLabel, bazenLabel, fitnessLabel, parkovanieLabel, restauraciaLabel, barLabel, tenisLabel, eskortLabel, mail, mobil, medzera3, medzera4, adresa, mesto, krajina, hviezdicky );


        anchorPane.getChildren().add(gridPane);

        return anchorPane;
    }
}
