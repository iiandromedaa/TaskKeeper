module com.androbohij {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.androbohij to javafx.fxml;
    exports com.androbohij;
}
