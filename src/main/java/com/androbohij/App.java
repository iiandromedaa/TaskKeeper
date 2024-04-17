package com.androbohij;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;
    private static User user;
    public static final String VERSION = "";

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("main"), 640, 480);
        stage.setScene(scene);
        stage.setTitle("TaskKeeper");
        stage.setResizable(true);
        stage.setMaxWidth(Screen.getPrimary().getBounds().getMaxX());
        stage.setMaxHeight(Screen.getPrimary().getBounds().getMaxY());
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void save() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

}