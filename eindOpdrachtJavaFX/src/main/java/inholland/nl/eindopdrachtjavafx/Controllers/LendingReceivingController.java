package inholland.nl.eindopdrachtjavafx.Controllers;

import inholland.nl.eindopdrachtjavafx.DAL.Database;
import inholland.nl.eindopdrachtjavafx.Models.Item;
import inholland.nl.eindopdrachtjavafx.Models.Member;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

import java.time.LocalDate;

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
        }if(itemCode < 1 || memberID < 1){
            errorHandling.setText("Item code or member ID can't be lower than 1");
        }else if (this.database.checkIfItemIsAlreadyLent(itemCode, memberID)){
            errorHandling.setText("Item is already lent");}
        else {this.database.lentItem(itemCode, memberID);
            errorHandling.setText("Item " + ItemCodeLending.getText() + " has been lent.");
        }
    }


    public void receivingItem(ActionEvent actionEvent) {
        int itemCode = Integer.parseInt(ItemCodeReceiving.getText());
        final int DAYS_OVERDUE = 21;
        if (this.ItemCodeReceiving.getText().isEmpty() || this.ItemCodeReceiving.getText() == null) {
            errorHandlingReceiving.setText("Please check if all fields are filled in and are correct");
        }else if(itemCode < 1){
            errorHandlingReceiving.setText("Item code can't be lower than 1");
        }if (this.database.checkIfItemIsAlreadyReceived(itemCode)){
            errorHandlingReceiving.setText("Item is already received");
        }else {
            Item item = this.database.getItem(itemCode);
            if (item.getLendingDate().plusDays(DAYS_OVERDUE).isBefore(LocalDate.now())) {
                errorHandlingReceiving.setText("Item is overdue by " + this.database.calculateOverdueDays(itemCode) + " days");
                this.database.receivedItem(itemCode); // later omzetten in een geldboete.
            } else {
                this.database.receivedItem(itemCode);
                errorHandlingReceiving.setText("Item " + ItemCodeReceiving.getText() + " has been received.");
            }
        }
    }
}




