package inholland.nl.eindopdrachtjavafx.Controllers;
import inholland.nl.eindopdrachtjavafx.DAL.Database;
import inholland.nl.eindopdrachtjavafx.Models.Item;
import inholland.nl.eindopdrachtjavafx.Models.Member;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.util.List;

public class CollectionController {
    private Member member;
    private Database database;

    @FXML private TableView<Item> tableViewCollection;
    public CollectionController(Member member, Database database) {
        this.member = member;
        this.database = database;
    }

    // fill tableview with items from database
    @FXML public void initialize() {
        setItemInTable(this.database.getAllItems());
    }

    private void setItemInTable(List<Item> items){
        tableViewCollection.setItems(FXCollections.observableArrayList(items));
    }


}
