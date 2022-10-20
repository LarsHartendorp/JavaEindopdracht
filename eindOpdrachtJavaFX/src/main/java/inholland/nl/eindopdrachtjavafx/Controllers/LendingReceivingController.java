package inholland.nl.eindopdrachtjavafx.Controllers;

import inholland.nl.eindopdrachtjavafx.DAL.Database;
import inholland.nl.eindopdrachtjavafx.Models.Item;
import inholland.nl.eindopdrachtjavafx.Models.Member;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class LendingReceivingController implements Initializable {
    private Database database;
    private Member member;
    @FXML
    public Label nameOfUserLabel;
    @FXML
    public TextField ItemCodeLending;
    @FXML
    public TextField MemberIdentifier;
    @FXML
    public Button LendButton;
    @FXML
    public Label errorHandling;
    @FXML
    public Label errorHandlingReceiving;
    @FXML
    public TextField ItemCodeReceiving;
    @FXML
    public Button ReceiveButton;


    public LendingReceivingController(Member member, Database database) {
        this.member = member;
        this.database = database;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameOfUserLabel.setText("Welcome " + member.getFullname());
    }

    public void lendingItem(ActionEvent event) {
        try {
            // itemcode vervangen
            if (this.ItemCodeLending.getText().isEmpty() || this.ItemCodeLending.getText() == null || this.MemberIdentifier.getText().isEmpty() || this.MemberIdentifier.getText() == null) {
                throw new Exception("Please check if all fields are filled in and are correct");
            }
            int itemCode = Integer.parseInt(ItemCodeLending.getText());
            int memberID = Integer.parseInt(MemberIdentifier.getText());
            if (itemCode < 1 || memberID < 1) {
                throw new Exception("Item code or member ID can't be lower than 1");
            } else if (this.database.checkIfItemIsAlreadyLent(itemCode, memberID)) {
                throw new Exception("Item is already lent");
            } else {
                this.database.lentItem(itemCode, memberID);
                throw new Exception("Item " + ItemCodeLending.getText() + " has been lent.");
            }
        } catch (Exception e) {
            errorHandling.setText(e.getMessage());
        }
    }

    public void receivingItem(ActionEvent actionEvent) {
        try {
            if (this.ItemCodeReceiving.getText().isEmpty() || this.ItemCodeReceiving.getText() == null) {
                throw new Exception("Please check if all fields are filled in and are correct");
            }
            int itemCode = Integer.parseInt(ItemCodeReceiving.getText());
            final int DAYS_OVERDUE = 21;
            if (itemCode < 1) {
                throw new Exception("Item code can't be lower than 1");
            }
            if (this.database.checkIfItemIsAlreadyReceived(itemCode)) {
                throw new Exception("Item can't be received because it's not lent");
            } else {
                Item item = this.database.getItem(itemCode);
                if (item.getLendingDate().plusDays(DAYS_OVERDUE).isBefore(LocalDate.now())) {
                    this.database.receivedItem(itemCode); // instead of received item, add a fine. (Will do this later if necessary)
                    throw new Exception("Item is overdue by " + this.database.calculateOverdueDays(itemCode) + " days. There will be a fine.");
                } else {
                    this.database.receivedItem(itemCode);
                    throw new Exception("Item " + ItemCodeReceiving.getText() + " has been received.");
                }
            }
        }catch(Exception e){
                    errorHandlingReceiving.setText(e.getMessage());
        }
    }
}



