package View;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ReservationScreenView {

    public AnchorPane showRezervaciaScreenView(final Stage primaryStage) throws SQLException {

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(350,450);

        Label rez = new Label("Rezervácia");
        rez.setAlignment(Pos.TOP_CENTER);
        rez.setContentDisplay(ContentDisplay.CENTER);
        rez.setLayoutX(137);
        rez.setLayoutY(40);
        rez.setFont(new Font("System Bold Italic", 18));

        Label name = new Label("Meno ");
        name.setLayoutX(50);
        name.setLayoutY(100);
        name.setFont(new Font(14));

        TextField nameT = new TextField();
        nameT.setLayoutX(113);
        nameT.setLayoutY(96);

        Label surname = new Label("Priezvisko ");
        surname.setLayoutX(36);
        surname.setLayoutY(142);
        surname.setFont(new Font(14));

        TextField surnameT = new TextField();
        surnameT.setLayoutX(113);
        surnameT.setLayoutY(135);

        Label mail = new Label("e-mail ");
        mail.setLayoutX(52);
        mail.setLayoutY(183);
        mail.setFont(new Font(14));

        TextField mailT = new TextField();
        mailT.setLayoutX(113);
        mailT.setLayoutY(178);

        Label phone = new Label("tel. číslo ");
        phone.setLayoutX(43);
        phone.setLayoutY(229);
        phone.setFont(new Font(14));

        TextField phoneT = new TextField();
        phoneT.setLayoutX(113);
        phoneT.setLayoutY(224);

        Label room = new Label("Izba ");
        room.setLayoutX(59);
        room.setLayoutY(272);
        room.setFont(new Font(14));

        ChoiceBox roomC  = new ChoiceBox();
        roomC.setLayoutX(113);
        roomC.setLayoutY(267);
        roomC.getItems().addAll("jednolôžková", "dvojlôžková", "trojlôžková", "štvorlôžková");

        Label adults = new Label("Dospelí ");
        adults.setLayoutX(48);
        adults.setLayoutY(314);
        adults.setFont(new Font(12));

        ChoiceBox adultsC  = new ChoiceBox();
        adultsC.setLayoutX(113);
        adultsC.setLayoutY(309);
        adultsC.getItems().addAll("-", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10");

        Label kids = new Label("Deti ");
        kids.setLayoutX(207);
        kids.setLayoutY(314);
        kids.setFont(new Font(14));

        ChoiceBox kidsC  = new ChoiceBox();
        kidsC.setLayoutX(246);
        kidsC.setLayoutY(309);
        kidsC.getItems().addAll("-", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10");

        Button rezervuj = new Button("Rezervuj");
        rezervuj.setLayoutX(45);
        rezervuj.setLayoutY(380);
        rezervuj.setMnemonicParsing(false);
        rezervuj.setPrefSize(261, 31);

        anchorPane.getChildren().add(rez);
        anchorPane.getChildren().add(name);
        anchorPane.getChildren().add(surname);
        anchorPane.getChildren().add(mail);
        anchorPane.getChildren().add(phone);
        anchorPane.getChildren().add(room);
        anchorPane.getChildren().add(adults);
        anchorPane.getChildren().add(kids);
        anchorPane.getChildren().add(nameT);
        anchorPane.getChildren().add(surnameT);
        anchorPane.getChildren().add(mailT);
        anchorPane.getChildren().add(phoneT);
        anchorPane.getChildren().add(roomC);
        anchorPane.getChildren().add(adultsC);
        anchorPane.getChildren().add(kidsC);
        anchorPane.getChildren().add(rezervuj);


        return anchorPane;
    }
}
