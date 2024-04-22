module com.androbohij {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires javafx.base;

    opens com.androbohij to javafx.fxml;
    opens com.androbohij.controllers to javafx.fxml;
    exports com.androbohij;
}
