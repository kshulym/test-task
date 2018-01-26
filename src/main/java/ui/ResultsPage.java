package ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResultsPage {

    private WebDriver driver;

    public ResultsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(css = "strong.go-text-right")
    WebElement countResults;

    @FindBy(xpath = "//button[contains(text(),'Details')]")
    WebElement detailsButton;

    @FindBy(id = "preloader")
    WebElement preloader;

    public int countResults() {
        return Integer.parseInt(countResults.getText());
    }

    public DetailsPage goToDetails(){
        detailsButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.invisibilityOf(preloader));
        return new DetailsPage(driver);
    }
}
