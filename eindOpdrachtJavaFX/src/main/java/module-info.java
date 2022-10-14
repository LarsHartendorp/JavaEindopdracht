module inholland.nl.eindopdrachtjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens inholland.nl.eindopdrachtjavafx to javafx.fxml;
    exports inholland.nl.eindopdrachtjavafx;
    exports inholland.nl.eindopdrachtjavafx.Controllers;
    opens inholland.nl.eindopdrachtjavafx.Controllers to javafx.fxml;

    exports inholland.nl.eindopdrachtjavafx.Models;
    exports inholland.nl.eindopdrachtjavafx.DAL;
    opens inholland.nl.eindopdrachtjavafx.Models to javafx.base;
}