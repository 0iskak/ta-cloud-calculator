package me.iskak.calculator.util;

import me.iskak.calculator.driver.Driver;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtil {
    private static String getFormattedTimestamp() {
        return LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern(
                        "uuuu-MM-dd_HH-mm-ss"
                ));
    }

    public static void saveScreenshot() {
        var bytes = ((TakesScreenshot) Driver.getDriver())
                .getScreenshotAs(OutputType.BYTES);

        try {
            var path = Path.of(
                    "target", "screenshots", getFormattedTimestamp() + ".png"
            );
            Files.createDirectories(path.getParent());
            Files.write(path, bytes);
        } catch (IOException e) {
            LogManager.getLogger()
                    .warn("Failed to save screenshot: " + e.getLocalizedMessage());
        }
    }
}
