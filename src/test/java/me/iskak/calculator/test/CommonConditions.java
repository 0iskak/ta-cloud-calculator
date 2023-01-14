package me.iskak.calculator.test;

import me.iskak.calculator.driver.Driver;
import me.iskak.calculator.util.TestListener;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(TestListener.class)
public abstract class CommonConditions {
    @BeforeAll
    public static void init() {
        var driver = Driver.getDriver();
        driver.get("https://cloud.google.com/");
    }

    @AfterAll
    public static void closeDriver() {
        Driver.closeDriver();
    }
}
