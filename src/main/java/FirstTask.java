import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ui.detailsPage;
import ui.homePage;
import ui.resultsPage;

import java.util.concurrent.TimeUnit;

public class FirstTask {

    WebDriver driver;

    @BeforeTest
    public void init() {
        driver = new ChromeDriver();
        /* System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver"); */
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://www.phptravels.net/");
    }

    @Test
    public void testTitle() {
        homePage homePage = new homePage(driver);
        homePage.selectLocation("Toronto");
        homePage.selectAdults("3");
        homePage.selectChild("2");

        resultsPage resultsPage = homePage.performSearch();
        String numberOfResults = resultsPage.countResults();
        System.out.println(numberOfResults);

        if (numberOfResults.equals(String.valueOf(0))) {
            driver.navigate().back();
            homePage homePageNew = new homePage(driver);
            homePageNew.selectAdults("3");
            homePageNew.selectChild("2");
            resultsPage resultsPageNew = homePage.performSearch();
            System.out.println(resultsPageNew.countResults());
            detailsPage detailsPage = resultsPage.goToDetails();
            String details = detailsPage.getDetails();
            System.out.println(details);
        }
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}

