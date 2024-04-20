module com.androbohij {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;

    opens com.androbohij to javafx.fxml;
    exports com.androbohij;
}
