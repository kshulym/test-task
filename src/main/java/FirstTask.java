import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ui.DetailsPage;
import ui.HomePage;
import ui.ResultsPage;

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
        HomePage homePage = new HomePage(driver);
        homePage.selectLocation("Toronto");
        homePage.setDates("27/02/2019", "28/03/2019");
        homePage.selectAdults("3");
        homePage.selectChild("2");

        ResultsPage resultsPage = homePage.performSearch();
        int numberOfResults = resultsPage.countResults();
        System.out.println("The Number of Results is: " + numberOfResults);

        if(numberOfResults == 0){
            driver.navigate().back();
            homePage.waitPreloader();
            homePage.selectAdults("3");
            homePage.selectChild("2");
            resultsPage = homePage.performSearch();

            numberOfResults = resultsPage.countResults();
            System.out.println("The Number of Results is: " + numberOfResults);

            DetailsPage detailsPage = resultsPage.goToDetails();
            String details = detailsPage.getDetails();
            System.out.println("The Details of hotel are: " + details);
        }
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}

