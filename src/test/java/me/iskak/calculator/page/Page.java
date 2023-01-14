package me.iskak.calculator.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.function.Predicate;

public abstract class Page {
    protected final WebDriver driver;

    protected Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public FluentWait<WebDriver> fluentWait(Duration timeout, Duration polling) {
        return new FluentWait<>(driver)
                .ignoring(NoSuchElementException.class)
                .withTimeout(timeout)
                .pollingEvery(polling);
    }

    public FluentWait<WebDriver> fluentWait(Duration timeout) {
        return fluentWait(timeout, Duration.ofMillis(100));
    }

    public FluentWait<WebDriver> fluentWait() {
        return fluentWait(Duration.ofSeconds(1));
    }

    protected WebElement findDisplayed(String xpath) {
        return fluentWait().until(ignored -> driver.findElements(By.xpath(xpath))
                .stream()
                .dropWhile(Predicate.not(WebElement::isDisplayed))
                .findFirst()
                .orElse(null));
    }
}
