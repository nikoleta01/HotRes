package View;

import Controller.DetailHotelScreenController;
import Controller.HotelsScreenController;
import Controller.LoginScreenController;
import Controller.WelcomeScreenController;
import Model.DatabaseManagement;
import Model.Hotel;
import Model.Review;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.SQLException;

import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;

public class HotelsScreenView
{
    public AnchorPane showHotelsScreenView(final Stage primaryStage, ObservableList<Hotel> data, int number_of_pages, String inserted_string, int prihlaseny, int id_user) throws SQLException
    {
        DatabaseManagement database = new DatabaseManagement();

        ObservableList<Hotel> data2 = data;
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(1000,750);



//        pagination.setPageFactory(this::createPage);
//
//        int fromIndex = pageIndex * 10;
//        int toIndex = Math.min(fromIndex + 10, data.size());


        TableView<Hotel> hotelTableView;


        TableColumn<Hotel, String> columnName = new TableColumn<>("Názov hotela");
        columnName.setMinWidth(300);
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Hotel, String> columnAddress = new TableColumn<>("Adresa hotela");
        columnAddress.setMinWidth(270);
        columnAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        TableColumn<Hotel, String> columnStars = new TableColumn<>("Počet hviezdičiek");
        columnStars.setMinWidth(200);
        columnStars.setCellValueFactory(new PropertyValueFactory<>("stars"));

        hotelTableView = new TableView<>();

        hotelTableView.setEditable(true);
        hotelTableView.setLayoutX(200);
        hotelTableView.setLayoutY(100);
        hotelTableView.setVisible(true);
        hotelTableView.setItems(data2);
        hotelTableView.setPrefHeight(515);

        //nastavime pagination, pocet podla toho, kolko hotelov sa nachadza v zadanej krajine
        Pagination pagination = new Pagination();

        //ak pocet stran je 0, nie je nutne mat dlhe pagination, staci 1 strana, kde sa zmestia vsetky mesta
        if (number_of_pages == 0)
        {
            pagination.setPageCount(1);
            pagination.setLayoutY(670);
            pagination.setLayoutX(530);
        }
        else if(number_of_pages % 20 == 0)
        {
            pagination.setPageCount(number_of_pages / 20);
            pagination.setLayoutX(370);
            pagination.setLayoutY(670);
        }
        else
        {
            pagination.setPageCount((number_of_pages / 20) + 1);
            pagination.setLayoutX(370);
            pagination.setLayoutY(670);
        }

//        pagination.currentPageIndexProperty().addListener(ChangeLi);

        pagination.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) ->
        {
            try {
                ObservableList<Hotel> hotels;
                hotels = database.pagination(newIndex.intValue(), inserted_string);
                //TODO
                hotelTableView.setItems(hotels);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });




        hotelTableView.getColumns().addAll(columnName, columnAddress, columnStars);


        hotelTableView.setOnMouseClicked(event-> {
            if (event.getClickCount() == 2)
            {
                try
                {
                    //ziskame hotel, ktory pouzivatel vybral dvojkliknutim
                    Hotel selectedItem = hotelTableView.getSelectionModel().getSelectedItem();
                    Hotel chosenHotel = null;
                    chosenHotel = database.selectDetails(selectedItem.getName(), selectedItem.getAddress(), selectedItem.getStars());

                    //do listu sa vlozia vsetky recenzia na dany hotel
                    ObservableList<Review> reviews;
                    reviews = database.selectedReviewForHotel(chosenHotel);

                    //otvori sa nove okno, kde su podrobnejsie informacie o hoteli
                    //HotelsScreenController.closeHotelsScreenWindow(primaryStage);
                    DetailHotelScreenController.openDetailHotelsScreenWindow(primaryStage, data, chosenHotel, prihlaseny, number_of_pages, inserted_string, reviews, id_user);
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        });

        Hyperlink prihlasenie = new Hyperlink();
        prihlasenie.setLayoutX(894);
        prihlasenie.setLayoutY(10);
        if(prihlaseny == 0){
            prihlasenie.setText("prihlásiť sa");
        }else{
            prihlasenie.setText("prihlásený");
        }

        prihlasenie.setOnMouseClicked(event-> {
            if (event.getClickCount() == 1)
            {
                try{
                    HotelsScreenController.closeHotelsScreenWindow(primaryStage);
                    LoginScreenController.openPrihlasenieScreenWindow(data, number_of_pages, inserted_string, id_user);
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
        });

        Label classify = new Label("Zoradiť podľa:");
        classify.setLayoutX(680);
        classify.setLayoutY(50);
        classify.setFont(new Font(14));

        ComboBox order  = new ComboBox();
        order.setLayoutX(782);
        order.setLayoutY(46);
        order.getItems().addAll("najlacnejšie", "najdrahšie", "najviac hviezdičiek", "najvyššie hodnotenie");

        Label welcome = new Label("Cena za izbu:");
        welcome.setAlignment(Pos.TOP_CENTER);
        welcome.setContentDisplay(ContentDisplay.CENTER);
        welcome.setLayoutX(24);
        welcome.setLayoutY(100);
        welcome.setFont(new Font(14));

        CheckBox price1 = new CheckBox("€0 -  €50 za noc");
        price1.setLayoutX(24);
        price1.setLayoutY(130);
        price1.setFont(new Font(12));

        CheckBox price2 = new CheckBox("€50 -  €100 za noc");
        price2.setLayoutX(24);
        price2.setLayoutY(150);
        price2.setFont(new Font(12));

        CheckBox price3 = new CheckBox("€100 -  €150 za noc");
        price3.setLayoutX(24);
        price3.setLayoutY(170);
        price3.setFont(new Font(12));

        CheckBox price4 = new CheckBox("€150 -  €200 za noc");
        price4.setLayoutX(24);
        price4.setLayoutY(190);
        price4.setFont(new Font(12));

        CheckBox price5 = new CheckBox("€200 a viac za za noc");
        price5.setLayoutX(24);
        price5.setLayoutY(210);
        price5.setFont(new Font(12));

        Label starsLabel = new Label("Počet hviezdičiek:");
        starsLabel.setAlignment(Pos.TOP_CENTER);
        starsLabel.setContentDisplay(ContentDisplay.CENTER);
        starsLabel.setLayoutX(24);
        starsLabel.setLayoutY(250);
        starsLabel.setFont(new Font(14));

        CheckBox star1 = new CheckBox("*");
        star1.setLayoutX(24);
        star1.setLayoutY(280);
        star1.setFont(new Font(14));

        CheckBox star2 = new CheckBox("* *");
        star2.setLayoutX(24);
        star2.setLayoutY(300);
        star2.setFont(new Font(14));

        CheckBox star3 = new CheckBox("* * *");
        star3.setLayoutX(24);
        star3.setLayoutY(320);
        star3.setFont(new Font(14));

        CheckBox star4 = new CheckBox("* * * *");
        star4.setLayoutX(24);
        star4.setLayoutY(340);
        star4.setFont(new Font(14));

        CheckBox star5 = new CheckBox("* * * * *");
        star5.setLayoutX(24);
        star5.setLayoutY(360);
        star5.setFont(new Font(14));

        Label equipmentLabel = new Label("Vybavenie izby:");
        equipmentLabel.setAlignment(Pos.TOP_CENTER);
        equipmentLabel.setContentDisplay(ContentDisplay.CENTER);
        equipmentLabel.setLayoutX(24);
        equipmentLabel.setLayoutY(400);
        equipmentLabel.setFont(new Font(14));

        CheckBox equipment1 = new CheckBox("Kuchynka");
        equipment1.setLayoutX(24);
        equipment1.setLayoutY(430);
        equipment1.setFont(new Font(12));

        CheckBox equipment2 = new CheckBox("Balkón");
        equipment2.setLayoutX(24);
        equipment2.setLayoutY(450);
        equipment2.setFont(new Font(12));

        CheckBox equipment3 = new CheckBox("WiFi");
        equipment3.setLayoutX(24);
        equipment3.setLayoutY(470);
        equipment3.setFont(new Font(12));

        Label services = new Label("Služby hotela:");
        services.setAlignment(Pos.TOP_CENTER);
        services.setContentDisplay(ContentDisplay.CENTER);
        services.setLayoutX(24);
        services.setLayoutY(510);
        services.setFont(new Font(14));

        CheckBox service1 = new CheckBox("Reštaurácia");
        service1.setLayoutX(24);
        service1.setLayoutY(540);
        service1.setFont(new Font(12));

        CheckBox service2 = new CheckBox("Wellness");
        service2.setLayoutX(24);
        service2.setLayoutY(560);
        service2.setFont(new Font(12));

        CheckBox service3 = new CheckBox("Bazén");
        service3.setLayoutX(24);
        service3.setLayoutY(580);
        service3.setFont(new Font(12));

        CheckBox service4 = new CheckBox("Fitness centrum");
        service4.setLayoutX(24);
        service4.setLayoutY(600);
        service4.setFont(new Font(12));

        CheckBox service5 = new CheckBox("Parkovanie");
        service5.setLayoutX(24);
        service5.setLayoutY(620);
        service5.setFont(new Font(12));

        CheckBox service6 = new CheckBox("Bar");
        service6.setLayoutX(24);
        service6.setLayoutY(640);
        service6.setFont(new Font(12));

        Button search = new Button("Hľadať");
        search.setLayoutX(24);
        search.setLayoutY(680);
        search.setPrefSize(87,34);
        //search.setText("Vyhľadaj");
        search.setMnemonicParsing(false);



        Label filter = new Label("FILTROVAŤ PODĽA:");
        filter.setAlignment(Pos.TOP_CENTER);
        filter.setContentDisplay(ContentDisplay.CENTER);
        filter.setLayoutX(24);
        filter.setLayoutY(70);
        filter.setFont(new Font(16));

        Label name = new Label("HotRes.sk");
        name.setAlignment(Pos.TOP_CENTER);
        name.setContentDisplay(ContentDisplay.CENTER);
        name.setLayoutX(400);
        name.setLayoutY(8);
        name.setFont(new Font(36));

        Button back = new Button("Späť");
        back.setLayoutX(24);
        back.setLayoutY(18);
        back.setPrefSize(70, 30);
        back.setMnemonicParsing(false);

        //button spat, v pripade ze sa pouzivatel pomylil, moze sa vratit na pociatocne welcome screen okno a znova vyhladavat nove hotely, podla novej krajiny alebo mesta
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try
                {
                    HotelsScreenController.closeHotelsScreenWindow(primaryStage);
                    WelcomeScreenController.openWelcomeScreenWindow(prihlaseny, id_user);
                }
                catch(SQLException e)
                {
                    e.printStackTrace();
                }
            }
        });


        anchorPane.getChildren().add(welcome);
        anchorPane.getChildren().add(name);
        anchorPane.getChildren().add(filter);
        anchorPane.getChildren().add(back);
        anchorPane.getChildren().add(order);
        anchorPane.getChildren().add(price1);
        anchorPane.getChildren().add(price2);
        anchorPane.getChildren().add(price3);
        anchorPane.getChildren().add(price4);
        anchorPane.getChildren().add(price5);
        anchorPane.getChildren().add(equipmentLabel);
        anchorPane.getChildren().add(equipment1);
        anchorPane.getChildren().add(equipment2);
        anchorPane.getChildren().add(equipment3);
        anchorPane.getChildren().add(starsLabel);
        anchorPane.getChildren().add(star1);
        anchorPane.getChildren().add(star2);
        anchorPane.getChildren().add(star3);
        anchorPane.getChildren().add(star4);
        anchorPane.getChildren().add(star5);
        anchorPane.getChildren().add(services);
        anchorPane.getChildren().add(service1);
        anchorPane.getChildren().add(service2);
        anchorPane.getChildren().add(service3);
        anchorPane.getChildren().add(service4);
        anchorPane.getChildren().add(service5);
        anchorPane.getChildren().add(service6);
        anchorPane.getChildren().add(classify);
        anchorPane.getChildren().add(search);
        anchorPane.getChildren().add(hotelTableView);
        anchorPane.getChildren().add(pagination);
        anchorPane.getChildren().add(prihlasenie);

        return anchorPane;
    }
}
