package inholland.nl.eindopdrachtjavafx.Controllers;
import inholland.nl.eindopdrachtjavafx.DAL.Database;
import inholland.nl.eindopdrachtjavafx.Models.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class CollectionController {
    private Member member;
    private Database database;

    @FXML private TableView tableViewCollection;
    public CollectionController(Member member, Database database) {
        this.member = member;
        this.database = database;
    }

    // fill tableview with items from database
    @FXML public void initialize() {
        tableViewCollection.setItems(database.getAllItems());
    }

}
