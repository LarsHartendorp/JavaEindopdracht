package inholland.nl.eindopdrachtjavafx.Controllers;

import inholland.nl.eindopdrachtjavafx.DAL.Database;
import inholland.nl.eindopdrachtjavafx.Models.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LendingReceivingController {
    private Database database;
    private Member member;
    @FXML public Label nameOfUserLabel;
    @FXML public TextField ItemCodeLending;
    @FXML public TextField MemberIdentifier;
    @FXML public Button LendButton;
    @FXML public Label errorHandling;
    @FXML public Label errorHandlingReceiving;
    @FXML public TextField ItemCodeReceiving;
    @FXML public Button ReceiveButton;

    public LendingReceivingController(Member member, Database database) {
        this.member = member;
        this.database = database;
    }

    @FXML public void initialize() {
        nameOfUserLabel.setText("Welcome " + member.getFullname());
    }

    public void lendingItem(ActionEvent event) {
        int itemCode = Integer.parseInt(ItemCodeLending.getText());
        int memberID = Integer.parseInt(MemberIdentifier.getText());
        if (this.ItemCodeLending.getText().isEmpty()  || this.ItemCodeLending.getText() == null || this.MemberIdentifier.getText().isEmpty() || this.MemberIdentifier.getText() == null) {
            errorHandling.setText("Please check if all fields are filled in and are correct");
        } else if(itemCode < 1 || memberID < 1){
            errorHandling.setText("Item code or member ID can't be lower than 1");
        } else {
            this.database.lentItem(itemCode, member.getMembername());
            errorHandling.setText("Item " + ItemCodeLending.getText() + " has been lent to " + member.getMembername() + ", status has been changed to lent");
        }

    }
    public void receivingItem(ActionEvent actionEvent) {
        int itemCode = Integer.parseInt(ItemCodeReceiving.getText());
        // if lendingdate is more than 3 weeks in past, message should be shown with number of days overdue
        // when receive button is pressed, item status should be changed and lending date should be cleared
        if (this.ItemCodeReceiving.getText().isEmpty()) {
            errorHandlingReceiving.setText("Please check if all fields are filled in and are correct");
        } else if(itemCode < 1){
            errorHandlingReceiving.setText("Item code can't be lower than 1");
        } if(this.database.calculateOverdueDays(itemCode) > 21) {
            // if overdue days > 21, show message with number of days overdue
            errorHandlingReceiving.setText("Item " + ItemCodeReceiving.getText() + " is overdue by " + this.database.calculateOverdueDays(itemCode) + " days");
        }else {
            this.database.receivedItem(itemCode);
            errorHandlingReceiving.setText("Item " + ItemCodeReceiving.getText() + " has been received, status has been changed to available and lending date has been cleared");
        }
    }
}
