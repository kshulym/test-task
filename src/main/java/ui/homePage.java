package ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class homePage {

    private WebDriver driver;

    @FindBy(id = "adults")
    WebElement adultsDropdown;

    @FindBy(id = "child")
    WebElement childDropdown;

    @FindBy(id = "s2id_autogen10")
    WebElement searchField;

    @FindBy(css = "span.select2-match")
    WebElement locationResults;

    @FindBy(xpath = "//button[contains(text(),'Search')]")
    WebElement searchButton;

    @FindBy(id = "preloader")
    WebElement preloader;

    public homePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.invisibilityOf(preloader));
    }

    public void selectAdults(String number) {
        Select adDropdown = new Select(adultsDropdown);
        adDropdown.selectByValue(number);
    }

    public void selectChild(String number) {
        Select adDropdown = new Select(childDropdown);
        adDropdown.selectByValue(number);
    }

    public void selectLocation(String city){
        Actions actions = new Actions(driver);
        actions.moveToElement(searchField).click().sendKeys(city).build().perform();
        this.locationResults.click();
    }

    public resultsPage performSearch(){
        this.searchButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.invisibilityOf(preloader));
        return new resultsPage(driver);
    }

}
