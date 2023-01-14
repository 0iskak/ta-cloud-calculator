package me.iskak.calculator.page.cloud;

import me.iskak.calculator.model.Price;
import me.iskak.calculator.page.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.List;

public class CalculatorPage extends Page {
    @FindBy(tagName = "iframe")
    private WebElement frameElement;

    @FindBy(xpath = "//button[contains(text(), 'Add to Estimate')]")
    private WebElement addToEstimateButton;

    @FindBy(css = "#compute > md-list")
    private List<WebElement> estimateElements;

    @FindBy(css = ".cpc-cart-total")
    private WebElement totalCostElement;

    @FindBy(css = "button[title='Email Estimate']")
    private WebElement emailButton;

    @FindBy(css = "form[name='emailForm']")
    private WebElement emailForm;

    @FindBy(xpath = "//button[normalize-space()='Send Email']")
    private WebElement sendEmailButton;

    public CalculatorPage(WebDriver driver) {
        super(driver);

        switchFrame();
    }

    public void switchFrame() {
        Runnable switchFrame = () -> driver.switchTo()
                .frame(frameElement);

        switchFrame.run();
        switchFrame.run();
    }

    private WebElement getProductTab(String productName) {
        return findDisplayed("//md-tabs//md-tab-item//*[text()='%s']/ancestor::md-tab-item"
                .formatted(productName));
    }

    public CalculatorPage setProduct(String productName) {
        getProductTab(productName).click();

        return this;
    }

    private WebElement getInput(String inputName) {
        return findDisplayed("//md-input-container/*[contains(text(), '%s')]/following-sibling::input"
                .formatted(inputName));
    }

    public CalculatorPage setInput(String inputName, String inputContent) {
        getInput(inputName).sendKeys(inputContent);

        return this;
    }

    public WebElement getSelect(String selectName) {
        return findDisplayed("//md-input-container/*[text()='%s']/following-sibling::md-select"
                .formatted(selectName));
    }

    public WebElement getOption(String optionName) {
        return findDisplayed("//md-option/*[normalize-space()='%s']/.."
                .formatted(optionName));
    }

    public CalculatorPage setOption(String selectName, String optionName) {
        getSelect(selectName).click();
        getOption(optionName).click();

        return this;
    }

    public WebElement getCheckbox(String checkboxName) {
        return findDisplayed("//md-checkbox/*[contains(text(), '%s')]//.."
                .formatted(checkboxName));
    }

    public CalculatorPage setCheckbox(String checkboxName, boolean toCheck) {
        var checkbox = getCheckbox(checkboxName);
        var checked = checkbox.getAttribute("aria-checked").equals("true");

        if (checked && !toCheck || !checked && toCheck)
            checkbox.click();

        return this;
    }

    public CalculatorPage addEstimate() {
        var estimateCount = estimateElements.size() + 1;
        addToEstimateButton.click();
        fluentWait(Duration.ofSeconds(3))
                .until(ignored -> getEstimatesCount() == estimateCount);

        return this;
    }

    public int getEstimatesCount() {
        return estimateElements.size();
    }

    public Price getPrice() {
        return Price.parsePrice(totalCostElement.getText());
    }

    public void sendEmail(String address) {
        emailButton.click();
        findDisplayed("//input[@type='email']").sendKeys(address);
        sendEmailButton.click();
    }
}
