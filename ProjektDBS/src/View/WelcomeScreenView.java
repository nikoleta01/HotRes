package View;

import Model.DatabaseManagement;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

import java.sql.SQLException;
import java.util.ArrayList;

public class WelcomeScreenView
{
    public AnchorPane showWelcomeScreenView(final Stage primaryStage) throws SQLException
    {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(515,317);

        //zadajte lokalitu
        TextField locality = new TextField();
        locality.setPrefWidth(230);
        locality.setPrefHeight(26);
        locality.setLayoutX(59);
        locality.setLayoutY(163);
        locality.setPromptText("Zadajte mesto");

        DatabaseManagement select = new DatabaseManagement();
        //navrhy miest, ktore su vyselectovane z tabulky lokalita, zo stlpec miest
        ArrayList<String> suggestions = select.selectedCities();
        //autocompletion, pouzitie externej kniznice controlsfx, ktore umoznuje takuto funkcionalitu
        TextFields.bindAutoCompletion(locality, suggestions);

        //vitajte label
        Label welcome = new Label("Vitajte v rezervačnom systéme HotRes!");
        welcome.setAlignment(Pos.TOP_CENTER);
        welcome.setContentDisplay(ContentDisplay.CENTER);
        welcome.setLayoutX(118);
        welcome.setLayoutY(63);
        welcome.setFont(new Font(14));

        //kam idete label
        Label whereTo = new Label("Kam cestujete?");
        whereTo.setLayoutX(59);
        whereTo.setLayoutY(136);
        whereTo.setFont(new Font(15));

        //button na vyhladanie lokality hotela
        //alt+enter resolves stuff
        Button search = new Button();
        search.setLayoutX(214);
        search.setLayoutY(236);
        search.setPrefSize(87,34);
        search.setText("Vyhľadaj");
        search.setMnemonicParsing(false);


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

        return anchorPane;
    }
}
