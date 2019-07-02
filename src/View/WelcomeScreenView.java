package View;

import Controller.HotelsScreenController;
import Controller.LoginScreenController;
import Controller.WelcomeScreenController;
import Model.DatabaseManagement;
import Model.Hotel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class WelcomeScreenView implements KeyListener
{
    public AnchorPane showWelcomeScreenView(final Stage primaryStage, int prihlaseny, int id_user) throws SQLException
    {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(515,317);

        Hyperlink login = new Hyperlink();
        login.setLayoutX(410);
        login.setLayoutY(20);
        if(prihlaseny == 0){
            login.setText("prihlásiť sa");
        }else{
            login.setText("prihlásený");
        }

        login.setOnMouseClicked(event-> {
            if (event.getClickCount() == 1)
            {
                try{
                    WelcomeScreenController.closeWelcomeScreenWindow(primaryStage);
                    LoginScreenController.openPrihlasenieScreenWindow(1, id_user);
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
        });

        //zadajte lokalitu
        TextField locality = new TextField();
        locality.setPrefWidth(230);
        locality.setPrefHeight(26);
        locality.setLayoutX(59);
        locality.setLayoutY(163);
        locality.setPromptText("Zadajte mesto");

        DatabaseManagement select = new DatabaseManagement();

        locality.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                try
                {
                    ArrayList<String> suggestions = select.selectedCities(newValue);
                    TextFields.bindAutoCompletion(locality, suggestions);
                    suggestions.clear();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        });

        RadioButton countrySearch = new RadioButton();
        countrySearch.setLayoutX(280);
        countrySearch.setLayoutY(205);
        countrySearch.setMnemonicParsing(false);
        countrySearch.setText("hľadať podľa krajiny");

        RadioButton citySearch = new RadioButton();
        citySearch.setLayoutX(80);
        citySearch.setLayoutY(205);
        citySearch.setMnemonicParsing(false);
        citySearch.setText("hľadať podľa mesta");



        //aby bola vybrana len jedna radio button v istej chvili
        ToggleGroup group = new ToggleGroup();
        countrySearch.setToggleGroup(group);
        citySearch.setToggleGroup(group);

//        RadioButton selectedRadioButton =
//                (RadioButton) group.getSelectedToggle();

        final Boolean[] foundCountry = {false};
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle)
            {
                if (group.getSelectedToggle() != null)
                {
                    String selected = group.getSelectedToggle().toString();
                    String country = "krajiny";

                    foundCountry[0] = selected.contains(country);
                    //ak vybraty sposob hladanie predstavuje hladanie podla mesta, v databaze sa vyhladaju hotely prisluchajuce zadanemu mestu
//                    if (selected.contains(country) == true)
//                    {
//                        System.out.println("city");
//                    }
                }
            }
        });

//        RadioButton selectedRadioButton = (RadioButton)group.getSelectedToggle();
//        String selectedValue = selectedRadioButton.getText();
//        System.out.println(selectedValue);

//        locality.setOnKeyTyped(new EventHandler<javafx.scene.input.KeyEvent>() {
//            @Override
//            public void handle(javafx.scene.input.KeyEvent event)
//            {
//                try
//                {
//                    ArrayList<String> suggestions = select.selectedCities(event.getCharacter());
//                    TextFields.bindAutoCompletion(locality, suggestions);
//                    suggestions.clear();
//                }
//                catch (SQLException e)
//                {
//                    e.printStackTrace();
//                }
//            }
//        });
//
////
//        //navrhy miest, ktore su vyselectovane z tabulky lokalita, zo stlpec miest
//        ArrayList<String> suggestions = select.selectedCities();
//        //autocompletion, pouzitie externej kniznice controlsfx, ktore umoznuje takuto funkcionalitu
//        TextFields.bindAutoCompletion(locality, suggestions);
//
        //vitajte label


        Label headline = new Label("HotRes.sk");
        headline.setAlignment(Pos.TOP_CENTER);
        headline.setContentDisplay(ContentDisplay.CENTER);
        headline.setLayoutX(25);
        headline.setLayoutY(20);
        headline.setFont(new Font("System Bold", 30));


        Label welcome = new Label("Vitajte v rezervačnom systéme HotRes!");
        welcome.setAlignment(Pos.TOP_CENTER);
        welcome.setContentDisplay(ContentDisplay.CENTER);
        welcome.setLayoutX(98);
        welcome.setLayoutY(83);
        welcome.setFont(new Font(16));

        //kam idete label
        Label whereTo = new Label("Kam cestujete?");
        whereTo.setLayoutX(59);
        whereTo.setLayoutY(136);
        whereTo.setFont(new Font(15));

        //button na vyhladanie lokality hotela
        Button search = new Button();
        search.setLayoutX(214);
        search.setLayoutY(250);
        search.setPrefSize(87,34);
        search.setText("Vyhľadaj");
        search.setMnemonicParsing(false);

        search.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    //DetailHotelScreenController.openDetailHotelsScreenWindow();

                    if (foundCountry[0] == true)
                    {
//                        System.out.println("chcem krajinu");

                        //TODO aj autocomplete nech robi
                        ObservableList<Hotel> data;
                        data = select.pagination(0, locality.getText());
//                        data = select.selectedHotelsInCountry(locality.getText());
                        int number_of_pages = select.countNumberOfHotelsInCountry(locality.getText());
                        HotelsScreenController.openHotelsScreenWindow(data, number_of_pages, locality.getText(), prihlaseny, id_user);
                        WelcomeScreenController.closeWelcomeScreenWindow(primaryStage);
                    }
                    else
                    {
                        ObservableList<Hotel> data;
                        data = select.selectedHotelsInCity(locality.getText());
                        HotelsScreenController.openHotelsScreenWindow(data, 0, locality.getText(), prihlaseny, id_user);
                        WelcomeScreenController.closeWelcomeScreenWindow(primaryStage);
                    }
                }catch(SQLException e){
                    e.printStackTrace();
                }

            }
        });


        //date picker od
        DatePicker from = new DatePicker();
        from.setLayoutX(289);
        from.setLayoutY(163);
        from.setPrefSize(87,26);
        from.setPromptText("Od");

        //date picker do
        DatePicker until = new DatePicker();
        until.setLayoutX(376);
        until.setLayoutY(163);
        until.setPrefSize(87,26);
        until.setPromptText("Do");

        //adding children
        anchorPane.getChildren().add(locality);
        anchorPane.getChildren().add(welcome);
        anchorPane.getChildren().add(search);
        anchorPane.getChildren().add(whereTo);
        anchorPane.getChildren().add(from);
        anchorPane.getChildren().add(until);
        anchorPane.getChildren().add(login);
        anchorPane.getChildren().add(headline);
        anchorPane.getChildren().add(citySearch);
        anchorPane.getChildren().add(countrySearch);


        return anchorPane;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
