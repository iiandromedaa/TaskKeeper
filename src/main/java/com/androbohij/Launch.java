package com.androbohij;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * new main class so i can like export a jar without it complaining
 */
public class Launch {
    public static String VERSION;
    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException {
        Properties properties = new Properties();
        properties.load(App.class.getResourceAsStream(".properties"));
        VERSION = properties.getProperty("VERSION");

        App.run(args);
    }
}
