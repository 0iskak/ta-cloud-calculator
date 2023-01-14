package me.iskak.calculator.page.email;

import me.iskak.calculator.model.Price;
import me.iskak.calculator.page.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class EmailPage extends Page {
    @FindBy(css = "#wmmailmain iframe")
    private WebElement frameElement;

    @FindBy(id = "geny")
    private WebElement emailElement;

    @FindBy(xpath = "//*[text()='Check Inbox']/..")
    private WebElement checkButton;

    public EmailPage(WebDriver driver) {
        super(driver);
    }

    public String getAddress() {
        return emailElement.getText();
    }

    public Price getPrice() {
        checkButton.click();
        switchFrame();

        var estimatedCost = fluentWait(Duration.ofSeconds(10), Duration.ofSeconds(1))
                .until(ignored -> {
                    driver.navigate().refresh();
                    switchFrame();
                    return driver.findElement(By.xpath("//*[contains(text(), 'Estimated Monthly Cost:')]"));
                }).getText();

        return Price.parsePrice(estimatedCost);
    }

    private void switchFrame() {
        driver.switchTo().frame(frameElement);
    }
}
