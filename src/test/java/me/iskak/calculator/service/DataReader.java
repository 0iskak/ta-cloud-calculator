package me.iskak.calculator.service;

import java.util.ResourceBundle;

public class DataReader {
    private final static ResourceBundle reasource = ResourceBundle
            .getBundle(System.getProperty("environment"));

    public static String getData(String key) {
        return reasource.getString(key);
    }
}
