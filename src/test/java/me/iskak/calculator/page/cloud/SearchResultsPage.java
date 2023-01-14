package me.iskak.calculator.page.cloud;

import me.iskak.calculator.page.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class SearchResultsPage extends Page {
    @FindBy(css = ".gsc-expansionArea > .gsc-webResult.gsc-result a")
    private List<WebElement> resultElements;

    protected SearchResultsPage(WebDriver driver) {
        super(driver);
        fluentWait(Duration.ofSeconds(5), Duration.ofSeconds(1))
                .until(ignored -> getResultsCount() > 0);
    }

    public int getResultsCount() {
        return resultElements.size();
    }

    public <T extends Page> T get(int index, Function<WebDriver, T> pageFunction) {
        resultElements.get(index).click();

        return pageFunction.apply(driver);
    }
}
