package ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class resultsPage {

    private WebDriver driver;

    public resultsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(css = "strong.go-text-right")
    WebElement countResults;

    @FindBy(xpath = "//button[contains(text(),'Details')]")
    WebElement detailsButton;

    @FindBy(id = "preloader")
    WebElement preloader;

    public String countResults() {
        return this.countResults.getText();
    }

    public detailsPage goToDetails(){
        this.detailsButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.invisibilityOf(preloader));
        return new detailsPage(driver);
    }
}
