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
    public LoginController() {
        this.database = new Database();
    }

    @FXML
    private TextField usernameTextfield;
    @FXML
    private PasswordField passwordTextfield;
    @FXML
    private Label errorLabel;
    public void login(ActionEvent event) throws Exception {
        //stappenplan
        // check if username and password are in database and are correct
        // if correct, go to next page
        // if not, show error message in label

        for (Member member : database.getUsers()) {
            if (member.getMembername().equals(usernameTextfield.getText()) && member.getPassword().equals(passwordTextfield.getText())) {
                 FXMLLoader fxmlLoader = new FXMLLoader(JavaApplication.class.getResource("dashboard-view.fxml"));
                 DashboardController dashboardController = new DashboardController(member, database);
                 fxmlLoader.setController(dashboardController);
                 Scene scene = new Scene(fxmlLoader.load());
                 Stage window = new Stage();
                 window.setTitle("Login");
                 window.setScene(scene);
                 window.show();
                 Stage s = (Stage)((Node)(event.getSource())).getScene().getWindow();
                 s.close();
            } else {
                errorLabel.setText("Username or password is incorrect");
            }
        }
    }
}

