package me.iskak.calculator.page.cloud;

import me.iskak.calculator.page.Page;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends Page {
    @FindBy(css = "input.devsite-search-field.devsite-search-query")
    private WebElement searchElement;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public SearchResultsPage search(String query) {
        searchElement.click();
        searchElement.sendKeys(query);
        searchElement.sendKeys(Keys.ENTER);

        return new SearchResultsPage(driver);
    }

}
