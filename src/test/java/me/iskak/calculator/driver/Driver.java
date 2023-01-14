package me.iskak.calculator.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.WebDriverManagerException;
import org.openqa.selenium.WebDriver;


public class Driver {
    private final static String DEFAULT_BROWSER_NAME = "firefox";

    private static WebDriver driver = null;

    public static WebDriver getDriver() {
        if (driver == null) driver = createDriver();

        return driver;
    }

    public static void closeDriver() {
        if (driver == null) return;

        driver.quit();
        driver = null;
    }

    private static WebDriver createDriver() {
        WebDriverManager driverManager;

        try {
            driverManager = WebDriverManager.getInstance(System.getProperty("browser"));
        } catch (NullPointerException | WebDriverManagerException ignored) {
            driverManager = WebDriverManager.getInstance(DEFAULT_BROWSER_NAME);
        }
        driverManager.setup();

        return driverManager.create();
    }
}
