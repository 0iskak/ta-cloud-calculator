package me.iskak.calculator.test;

import me.iskak.calculator.driver.Driver;
import me.iskak.calculator.page.cloud.SearchPage;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

public class SearchTest extends CommonConditions {
    @Test
    public void testSearch() {
        var driver = Driver.getDriver();
        driver.get("https://cloud.google.com/");
        var resultsCount = new SearchPage(Driver.getDriver())
                .search("Google Cloud Platform Pricing Calculator")
                .getResultsCount();
        assertThat(resultsCount, is(greaterThan(0)));
    }
}
