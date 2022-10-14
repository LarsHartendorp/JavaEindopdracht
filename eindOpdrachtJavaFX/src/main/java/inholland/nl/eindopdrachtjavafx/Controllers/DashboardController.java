package inholland.nl.eindopdrachtjavafx.Controllers;
import inholland.nl.eindopdrachtjavafx.DAL.Database;
import inholland.nl.eindopdrachtjavafx.JavaApplication;
import inholland.nl.eindopdrachtjavafx.Models.Item;
import inholland.nl.eindopdrachtjavafx.Models.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

// bemiddelaar tussen View en Model.
// clickevents worden hier afgehandeld
// praat met de logica laag
public class DashboardController {
    private Database database;
    @FXML
    private AnchorPane contentForAllViews;
    private Member member;
    public DashboardController(Member member, Database database) {
        this.member = member;
        this.database = database;
    }
    public void pageLending(ActionEvent actionEvent) throws IOException {
        // anchorpane wordt hier aangeroepen
        FXMLLoader fxmlLoader = new FXMLLoader(JavaApplication.class.getResource("lending-receiving-view.fxml"));
        //DashboardController dashboardController = new DashboardController(member, database);
        LendingReceivingController lendingReceivingController = new LendingReceivingController(member, database);
        fxmlLoader.setController(lendingReceivingController);
        this.contentForAllViews.getChildren().setAll((AnchorPane)fxmlLoader.load());
    }
}
