package inholland.nl.eindopdrachtjavafx.Controllers;
import inholland.nl.eindopdrachtjavafx.DAL.Database;
import inholland.nl.eindopdrachtjavafx.Models.Item;
import inholland.nl.eindopdrachtjavafx.Models.Member;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class CollectionController implements Initializable {
    private Member member;
    private Database database;
    @FXML public Label errorLabel;
    @FXML public TextField textFieldTitle;
    @FXML public TextField textFieldAuthor;
    @FXML private TableView<Item> tableViewCollection;
    public CollectionController(Member member, Database database) {
        this.member = member;
        this.database = database;
    }
    private void setItemInTable(List<Item> items){
        tableViewCollection.setItems(FXCollections.observableArrayList(items));
    }
    private void reloadTable() {
        setItemInTable(this.database.getAllItems());
        this.tableViewCollection.refresh();
    }

    public void addItem(ActionEvent actionEvent) {
        try {
            String title = textFieldTitle.getText();
            String author = textFieldAuthor.getText();
            LocalDate lendingDate = LocalDate.now();
            if (title.isEmpty() || author.isEmpty()) {
                throw new Exception("Please fill in all fields to add new item");
            } else {
                Item item = new Item(true, title, author, lendingDate);
                this.database.addItem(item);
                reloadTable();
            }
        } catch (Exception e) {
            errorLabel.setText(e.getMessage());
        }
    }

    public void editItem(ActionEvent actionEvent) {
        try {
            String title = textFieldTitle.getText();
            String author = textFieldAuthor.getText();
            Item item = tableViewCollection.getSelectionModel().getSelectedItem();
            if (item == null) {
                throw new Exception("Please select an item to edit");
            } else {
                item.setTitle(title);
                item.setAuthor(author);
                this.database.editItem(item);
                reloadTable();
            }
        } catch (Exception e) {
            errorLabel.setText(e.getMessage());
        }
    }

    public void deleteItem(ActionEvent actionEvent) {
        try {
            Item item = tableViewCollection.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            if (item == null) {
                throw new Exception("Please select an item to delete");
            } else {
                alert.setHeaderText("Are you sure you want to delete this item? " + item.getTitle());
                alert.showAndWait().ifPresent(response -> {
                    if (response == alert.getButtonTypes().get(0)) {
                        this.database.deleteItem(item);
                        reloadTable();
                    }
                });
            }
        } catch (Exception e) {
            errorLabel.setText(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setItemInTable(this.database.getAllItems());
        this.tableViewCollection.setOnMouseClicked((MouseEvent) -> {
            if (MouseEvent.getClickCount() == 1) {
                try {
                    Item item = tableViewCollection.getSelectionModel().getSelectedItem();
                    if (item == null) {
                        return;
                    }
                    textFieldTitle.setText(item.getTitle());
                    textFieldAuthor.setText(item.getAuthor());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (MouseEvent.getClickCount() == 2) {
                // double click
                try {
                    Item item = tableViewCollection.getSelectionModel().getSelectedItem();
                    item.setAvailability(!item.getAvailability());
                    this.database.editItem(item);
                    this.reloadTable();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
