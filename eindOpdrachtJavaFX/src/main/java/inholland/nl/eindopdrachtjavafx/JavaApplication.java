package inholland.nl.eindopdrachtjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


// deze class is de main class van de applicatie en hierin runt de applicatie.
public class JavaApplication extends Application {
    @Override
    // stage is de hele 'form' van de applicatie
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(JavaApplication.class.getResource("login-view.fxml"));
        // Scene is de inhoud van de applicatie
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add("file:src/main/resources/inholland/nl/eindopdrachtjavafx/style.css");
        stage.setResizable(false);
        stage.setTitle("Library system");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}