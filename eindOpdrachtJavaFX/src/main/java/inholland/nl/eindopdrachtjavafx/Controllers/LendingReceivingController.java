package inholland.nl.eindopdrachtjavafx.Controllers;

import inholland.nl.eindopdrachtjavafx.DAL.Database;
import inholland.nl.eindopdrachtjavafx.Models.Item;
import inholland.nl.eindopdrachtjavafx.Models.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class LendingReceivingController implements Initializable {
    private Database database;
    private User user;
    @FXML private Label nameOfUserLabel;
    @FXML private TextField itemCodeLending;
    @FXML private TextField memberIdentifier;
    @FXML private Button lendButton;
    @FXML private Label errorHandling;
    @FXML private Label errorHandlingReceiving;
    @FXML private TextField itemCodeReceiving;
    @FXML public Button receiveButton;


    // Constructor
    public LendingReceivingController(User user, Database database) {
        this.user = user;
        this.database = database;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameOfUserLabel.setText("Welcome " + this.user.getUsername());
    }

    public void lendingItem() {
        try {
            if (this.itemCodeLending.getText().isEmpty() || this.itemCodeLending.getText() == null || this.memberIdentifier.getText().isEmpty() || this.memberIdentifier.getText() == null) {
                throw new Exception("Please check if all fields are filled in and are correct");
            }
            // check if input are both digits
            checkIfInputIsString(itemCodeLending.getText(), "Item code can't be a string");
            checkIfInputIsString(memberIdentifier.getText(), "Member ID can't be a string");
            int itemCode = Integer.parseInt(itemCodeLending.getText());
            int memberID = Integer.parseInt(memberIdentifier.getText());
            if (itemCode < 1 || memberID < 1) {
                throw new Exception("Item code or member ID can't be lower than 1");
            } else if(!this.database.checkItemCodeAndMember(itemCode, memberID)){
                throw new Exception("Item code or member ID does not exist in current database");
            } else if (this.database.checkIfItemIsAlreadyLent(itemCode)) {
                throw new Exception("Item is already lent");
            } else {
                this.database.lentItem(itemCode, memberID);
                throw new Exception("Item " + itemCodeLending.getText() + " has been lent.");
            }
        } catch (Exception e) {
            errorHandling.setText(e.getMessage());
        }
    }

    public void receivingItem() {
        try {
            // if empty or null
            if (this.itemCodeReceiving.getText().isEmpty() || this.itemCodeReceiving.getText() == null) {
                throw new Exception("Please check if all fields are filled in and are correct");
            }
            // check if input is digit
            checkIfInputIsString(itemCodeReceiving.getText(), "item code can't be a string");
            int itemCode = Integer.parseInt(itemCodeReceiving.getText());
            final int DAYS_OVERDUE = 21;
            if (itemCode < 1) {
                throw new NumberFormatException("Item code can't be lower than 1");
            }
            // check if item is already lent
            if (this.database.checkIfItemIsAlreadyReceived(itemCode)) {
                throw new Exception("Item can't be received because it's not lent");
            }
            // check if item exists
            else if(!this.database.checkItemCode(itemCode)){
                throw new Exception("Item code or member ID does not exist in current database");
            }else {
                Item item = this.database.getItem(itemCode);
                if (item.getLendingDate().plusDays(DAYS_OVERDUE).isBefore(LocalDate.now())) {
                    this.database.receivedItem(itemCode); // instead of received item, add a fine. (Will do this later if necessary)
                    throw new Exception("Item is overdue by " + this.database.calculateOverdueDays(itemCode) + " days. There will be a fine.");
                } else {
                    this.database.receivedItem(itemCode);
                    throw new Exception("Item " + itemCodeReceiving.getText() + " has been received.");
                }
            }
        }catch(Exception e){
            this.errorHandlingReceiving.setText(e.getMessage());
        }
    }
    // check if input is String instead of int
    private void checkIfInputIsString(String input, String exceptionMessage) {
        if (input.matches("[a-zA-Z]+")) {
            throw new NumberFormatException(exceptionMessage);
        }
    }
}



