module com.androbohij {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens com.androbohij to javafx.fxml;
    opens com.androbohij.controllers to javafx.fxml;
    opens com.androbohij.controllers.insertcontrollers to javafx.fxml;

    exports com.androbohij;
    exports com.androbohij.controllers;
}
