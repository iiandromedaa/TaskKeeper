package com.androbohij;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;

import com.androbohij.controllers.Controller;
import com.androbohij.controllers.MainController;

public class App extends Application {

    private static Scene scene;
    private static User user;
    private static MainController mc;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("main", stage), 640, 480);
        stage.setScene(scene);
        stage.setTitle("TaskKeeper");
        stage.setResizable(true);
        //size of the scrollbars makes this wacky so im adding 38 pixels to account
        stage.setMinWidth(640+38);
        stage.setMinHeight(480+38);
        stage.setMaxWidth(Screen.getPrimary().getBounds().getMaxX());
        stage.setMaxHeight(Screen.getPrimary().getBounds().getMaxY());
        stage.setOnHidden(event -> Platform.exit());
        SaveLoad.loadToGUI(user.getTodoList(), mc);
        stage.show();
    }

    @Override
    public void stop() {
        try {
            SaveLoad.save();
        } catch (IOException e) {
            System.out.println("cant save :(");
        }
    }

    private static Parent loadFXML(String fxml, Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        Parent par = fxmlLoader.load();
        ((Controller)fxmlLoader.getController()).setStage(stage);
        if (fxml.equals("main"))
            mc = (MainController)fxmlLoader.getController();
        return par;
    }

    public static void run(String[] args) throws IOException {
        user = new User();
        try {
            user.setTodoList(SaveLoad.load());
            // user.displayTodoList();
        } catch (FileNotFoundException | InvalidClassException e) {
            System.out.println("no saves found, starting fresh");
        } catch (ClassNotFoundException e) {
            System.out.println("we cant find the TodoList class????");
        }
        launch();
    }

    public static User getUser() {
        return user;
    }

}