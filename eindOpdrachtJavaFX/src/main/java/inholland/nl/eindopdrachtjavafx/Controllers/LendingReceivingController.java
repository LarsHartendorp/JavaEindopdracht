package inholland.nl.eindopdrachtjavafx.Controllers;

import inholland.nl.eindopdrachtjavafx.DAL.Database;
import inholland.nl.eindopdrachtjavafx.Models.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.awt.*;

public class LendingReceivingController {

    private Database database;
    private Member member;
    @FXML public Label WelcomeLabel;
    @FXML public TextField ItemCodeLending;
    @FXML public TextField MemberIdentifier;
    @FXML public Button LendButton;
    @FXML public TextField ItemCodeReceiving;
    @FXML public Button ReceiveButton;

    public LendingReceivingController(Member member, Database database) {
        this.member = member;
        this.database = database;
    }

    @FXML
    public void initialize() {
        //nameOfUserLabel.setText("Welcome " + member.getFullname());
    }

public void test(){
    System.out.println("test");
}
    public void receivingItem(ActionEvent actionEvent) {
        // item status should be changed to available
        // lending date should be cleared
        System.out.println("Test receiving item");
    }
}
