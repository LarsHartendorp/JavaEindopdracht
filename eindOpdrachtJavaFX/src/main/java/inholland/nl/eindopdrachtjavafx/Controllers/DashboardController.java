package inholland.nl.eindopdrachtjavafx.Controllers;
import inholland.nl.eindopdrachtjavafx.DAL.Database;
import inholland.nl.eindopdrachtjavafx.JavaApplication;
import inholland.nl.eindopdrachtjavafx.Models.Item;
import inholland.nl.eindopdrachtjavafx.Models.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ResourceBundle;

// bemiddelaar tussen View en Model.
// clickevents worden hier afgehandeld
// praat met de logica laag
public class DashboardController implements Initializable {
    private Database database;
    @FXML private AnchorPane contentForAllViews;
    private Member member;
    public DashboardController(Member member, Database database) {
        this.member = member;
        this.database = database;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        try {
            this.openPageLending();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void pageLending(ActionEvent actionEvent) throws IOException {
        openPageLending();
    }

    public void pageCollection(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(JavaApplication.class.getResource("collection-view.fxml"));
        CollectionController collectionController = new CollectionController(member, database);
        fxmlLoader.setController(collectionController);
        this.contentForAllViews.getChildren().setAll((AnchorPane)fxmlLoader.load());
    }

    public void pageMember(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(JavaApplication.class.getResource("member-view.fxml"));
        MemberController memberController = new MemberController(member, database);
        fxmlLoader.setController(memberController);
        this.contentForAllViews.getChildren().setAll((AnchorPane)fxmlLoader.load());
    }

    public void openPageLending() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(JavaApplication.class.getResource("lending-receiving-view.fxml"));
        LendingReceivingController lendingReceivingController = new LendingReceivingController(member, database);
        fxmlLoader.setController(lendingReceivingController);
        this.contentForAllViews.getChildren().setAll((AnchorPane)fxmlLoader.load());
    }
}
