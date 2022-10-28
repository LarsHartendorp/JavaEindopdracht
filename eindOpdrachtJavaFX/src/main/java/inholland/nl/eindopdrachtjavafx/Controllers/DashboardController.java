package inholland.nl.eindopdrachtjavafx.Controllers;

import inholland.nl.eindopdrachtjavafx.DAL.Database;
import inholland.nl.eindopdrachtjavafx.JavaApplication;
import inholland.nl.eindopdrachtjavafx.Models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

// bemiddelaar tussen View en Model.
// clickevents worden hier afgehandeld
// praat met de logica laag
public class DashboardController implements Initializable {
    private Database database;
    @FXML private AnchorPane contentForAllViews;
    private User user;
    public DashboardController(User user, Database database) {
        this.user = user;
        this.database = database;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        try {
            this.openPageLending();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void pageLending() throws IOException {
        openPageLending();
    }

    public void pageCollection() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(JavaApplication.class.getResource("collection-view.fxml"));
        CollectionController collectionController = new CollectionController(user, database);
        fxmlLoader.setController(collectionController);
        this.contentForAllViews.getChildren().setAll((AnchorPane)fxmlLoader.load());
    }

    public void pageMember() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(JavaApplication.class.getResource("member-view.fxml"));
        MemberController memberController = new MemberController(user, database);
        fxmlLoader.setController(memberController);
        this.contentForAllViews.getChildren().setAll((AnchorPane)fxmlLoader.load());
    }

    public void openPageLending() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(JavaApplication.class.getResource("lending-receiving-view.fxml"));
        LendingReceivingController lendingReceivingController = new LendingReceivingController(user, database);
        fxmlLoader.setController(lendingReceivingController);
        this.contentForAllViews.getChildren().setAll((AnchorPane)fxmlLoader.load());
    }
}
