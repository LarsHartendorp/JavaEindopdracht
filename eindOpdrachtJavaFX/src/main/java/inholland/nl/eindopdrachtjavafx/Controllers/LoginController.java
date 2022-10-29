package inholland.nl.eindopdrachtjavafx.Controllers;

import inholland.nl.eindopdrachtjavafx.DAL.Database;
import inholland.nl.eindopdrachtjavafx.JavaApplication;
import inholland.nl.eindopdrachtjavafx.Models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginController {
    private Database database;
    @FXML private TextField usernameTextfield;
    @FXML private PasswordField passwordTextfield;
    @FXML private Label errorLabel;

    public LoginController() {
        this.database = new Database();
    }

    public void login(ActionEvent event) throws IOException {
       User user = this.database.getUserByUsername(usernameTextfield.getText());
       if (user.getUsername().equalsIgnoreCase(usernameTextfield.getText()) && user.getPassword().equals(passwordTextfield.getText())) {
            mainView(user, event);
       } else {
           errorLabel.setText("Username or password is incorrect");
       }
    }

    private void mainView(User user, ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(JavaApplication.class.getResource("dashboard-view.fxml"));
        DashboardController dashboardController = new DashboardController(user, database);
        fxmlLoader.setController(dashboardController);
        // maak een Scene aan
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add("file:src/main/resources/inholland/nl/eindopdrachtjavafx/style.css");
        // maak een Stage aan
        Stage window = new Stage();
        window.setTitle("Library Management System");
        window.setScene(scene);
        window.show();
        Stage s = (Stage)((Node)(event.getSource())).getScene().getWindow();
        s.close();
        // Data wordt opgeslagen in de database als de applicatie wordt afgesloten
        window.setOnCloseRequest(e -> this.database.saveData());
    }
}