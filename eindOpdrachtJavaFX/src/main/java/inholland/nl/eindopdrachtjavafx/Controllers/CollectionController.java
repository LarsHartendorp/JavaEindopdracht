package inholland.nl.eindopdrachtjavafx.Controllers;
import inholland.nl.eindopdrachtjavafx.DAL.Database;
import inholland.nl.eindopdrachtjavafx.Models.Item;
import inholland.nl.eindopdrachtjavafx.Models.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class CollectionController implements Initializable {

    // user wordt nooit gebruikt

    private final User user;
    private final Database database;
    @FXML private Label errorLabel;
    @FXML private TextField textFieldTitle;
    @FXML private TextField textFieldAuthor;
    @FXML private TableView<Item> tableViewCollection;
    @FXML AnchorPane rootCollection;
    public CollectionController(User user, Database database) {
        this.user = user;
        this.database = database;
    }
    // alle items in de tabelView
    private void setItemInTable(List<Item> items){
        tableViewCollection.setItems(FXCollections.observableArrayList(items));
    }
    // herladen van de tabelView
    private void reloadTable() {
        setItemInTable(this.database.getAllItemsCSV());
        this.tableViewCollection.refresh();
    }

    // toevoegen van een item
    public void addItem() {
        try {
            String title = textFieldTitle.getText();
            String author = textFieldAuthor.getText();
            LocalDate lendingDate = LocalDate.now();
            if (title.isEmpty() || author.isEmpty()) {
                throw new Exception("Please fill in all fields to add new item");
            }
            Item item = new Item(true, title, author, lendingDate);
            this.database.addItem(item);
            reloadTable();
        } catch (Exception e) {
            errorLabel.setText(e.getMessage());
        }
    }

    // bewerken van een item
    public void editItem() {
        try {
            String title = textFieldTitle.getText();
            String author = textFieldAuthor.getText();
            Item item = tableViewCollection.getSelectionModel().getSelectedItem();

            if (item == null) throw new Exception("Please select an item to edit");

            item.setTitle(title);
            item.setAuthor(author);
            this.database.editItem(item);
            reloadTable();
        } catch (Exception e) {
            errorLabel.setText(e.getMessage());
        }
    }

    // verwijderen van een item
    public void deleteItem() {
        try {
            Item item = tableViewCollection.getSelectionModel().getSelectedItem();
            // alert als waarschuwing.
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            if (item == null) {
                throw new Exception("Please select an item to delete");
            }
            alert.setHeaderText("Are you sure you want to delete this item? " + item.getTitle());
            alert.showAndWait().ifPresent(response -> {
                if (response == alert.getButtonTypes().get(0)) {
                    this.database.deleteItem(item);
                    reloadTable();
                }
            });
        } catch (Exception e) {
            errorLabel.setText(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setItemInTable(this.database.getAllItemsCSV());
        this.tableViewCollection.setOnMouseClicked(MouseEvent -> {
            // 1x klikken om rij te selecteren en tekstvelden te vullen
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
                // 2x klikken om de beschikbaarheid van het item te veranderen
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


    // make method to calculate the expected return date
    public void calculateReturnDate() {
        Item item = tableViewCollection.getSelectionModel().getSelectedItem();
        if (item == null) {
            return;
        }
        if (!item.getAvailability()) {
            // set the expected return date from the lending date + 21 days in the tablecolumn
            LocalDate expectedReturnDate = item.getLendingDate().plusDays(21);
            item.setExpectedReturnDate(expectedReturnDate);
        }
    }


    // reading from a csv file and adding the items to the database, only .csv files are allowed
    @FXML
    public void readFromCSV() {
        try {
           // User input om een .csv file te kiezen.
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Please select a .csv file");
            // alleen .csv files zijn toegestaan
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open CSV File", "*.csv"));
            File selectedCSVFile = fileChooser.showOpenDialog(rootCollection.getScene().getWindow());
            if (selectedCSVFile != null) {
                List<String> lines = Files.readAllLines(Paths.get(selectedCSVFile.getAbsolutePath())).stream().skip(1).toList();
                for (String line : lines) {
                    String[] item = line.split(";");
                    Item newItem = new Item(item[0], true, item[1], item[2]);
                    this.database.addItemCSV(newItem);
                    this.setItemInTable(this.database.getAllItemsCSV());
                }
                reloadTable();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
