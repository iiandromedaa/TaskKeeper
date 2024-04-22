package com.androbohij;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.androbohij.controllers.MainController;

public class SaveLoad {

    public static void save() throws IOException {
        String path = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "TaskKeeper";
        File folder = new File(path);
        if (!folder.exists()) {
            System.out.println("we made directories");
            folder.mkdirs();
        }
        File save = new File(folder, "save.ser");
        try (FileOutputStream fos = new FileOutputStream(save); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(App.getUser().getTodoList());
            oos.flush();
        }
        System.out.println("saved!! :]");
    }

    public static TodoList load() throws FileNotFoundException, IOException, ClassNotFoundException {
        String path = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "TaskKeeper";
        File folder = new File(path);
        File save = new File(folder, "save.ser");
        if (!folder.exists() || !save.exists())
            throw new FileNotFoundException();
        try (FileInputStream fis = new FileInputStream(save); ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (TodoList)ois.readObject();
        }
    }

    public static void loadToGUI(TodoList todoList, MainController mc) throws IOException {
        for (Task task : todoList.displayTasks()) {
            mc.loadTask(task);
        }
    }
}
