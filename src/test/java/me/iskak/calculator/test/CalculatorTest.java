package me.iskak.calculator.test;

import me.iskak.calculator.driver.Driver;
import me.iskak.calculator.model.Price;
import me.iskak.calculator.page.cloud.CalculatorPage;
import me.iskak.calculator.page.cloud.SearchPage;
import me.iskak.calculator.page.email.EmailPage;
import me.iskak.calculator.service.DataReader;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WindowType;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


public class CalculatorTest extends CommonConditions {
    private static CalculatorPage calculatorPage;

    @Test
    @Order(1)
    public void testSets() {
        calculatorPage = new SearchPage(Driver.getDriver())
                .search("Google Cloud Platform Pricing Calculator")
                .get(0, CalculatorPage::new);

        calculatorPage.setProduct("Compute Engine")
                .setInput("Number of instances", "4")
                .setInput("What are these instances for?", "")
                .setOption(
                        "Operating System / Software",
                        "Free: Debian, CentOS, CoreOS, Ubuntu or BYOL (Bring Your Own License)"
                )
                .setOption("Provisioning model", "Regular")
                .setOption("Series", "N1")
                .setOption("Machine type", "n1-standard-8 (vCPUs: 8, RAM: 30GB)")
                .setCheckbox("Add GPUs.", true)
                .setOption("GPU type", "NVIDIA Tesla V100")
                .setOption("Number of GPUs", "1")
                .setOption("Local SSD", "2x375 GB")
                .setOption("Datacenter location", "Frankfurt (europe-west3)")
                .setOption("Committed usage", "1 Year");
    }

    @Test
    @Order(2)
    public void testAddEstimate() {
        calculatorPage.addEstimate();
        assertThat(
                calculatorPage.getEstimatesCount(),
                is(equalTo(1))
        );
    }

    @Test
    @Order(3)
    public void testEmail() {
        var driver = Driver.getDriver();

        var calculatorHandle = driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.TAB);
        driver.navigate().to("https://yopmail.com/en/email-generator");
        var emailHandle = driver.getWindowHandle();

        var emailPage = new EmailPage(driver);
        var address = emailPage.getAddress();

        driver.switchTo().window(calculatorHandle);
        calculatorPage.switchFrame();
        calculatorPage.sendEmail(address);

        driver.switchTo().window(emailHandle);

        var price = new Price(
                DataReader.getData("price.currency"),
                Double.parseDouble(DataReader.getData("price.value"))
        );
        assertThat(emailPage.getPrice(), is(equalTo(price)));
    }
}
