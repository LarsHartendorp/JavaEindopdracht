package inholland.nl.eindopdrachtjavafx.Controllers;

import inholland.nl.eindopdrachtjavafx.DAL.Database;
import inholland.nl.eindopdrachtjavafx.JavaApplication;
import inholland.nl.eindopdrachtjavafx.Models.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    private Database database;
    @FXML private TextField usernameTextfield;
    @FXML private PasswordField passwordTextfield;
    @FXML private Label errorLabel;

    public LoginController() {
        this.database = new Database();
    }

    public void login(ActionEvent event) {
        try {
            for (Member member : database.getMember()) {
                if (member.getUsername().equalsIgnoreCase(usernameTextfield.getText()) && member.getPassword().equals(passwordTextfield.getText())) {
                    FXMLLoader fxmlLoader = new FXMLLoader(JavaApplication.class.getResource("dashboard-view.fxml"));
                    DashboardController dashboardController = new DashboardController(member, database);
                    fxmlLoader.setController(dashboardController);
                    Scene scene = new Scene(fxmlLoader.load());
                    scene.getStylesheets().add("file:src/main/resources/inholland/nl/eindopdrachtjavafx/style.css");
                    Stage window = new Stage();
                    window.setTitle("Library Management System");
                    window.setScene(scene);
                    window.show();
                    Stage s = (Stage)((Node)(event.getSource())).getScene().getWindow();
                    s.close();
                    window.setOnCloseRequest(e -> this.database.saveData());
                } else {
                    throw new Exception("Username or password is incorrect");

                }}} catch (Exception e) {
            errorLabel.setText(e.getMessage());
        }
    }
}


