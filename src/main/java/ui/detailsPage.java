package ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class detailsPage {

    private WebDriver driver;

    public detailsPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
        driver.manage().window().maximize();
    }

    @FindBy(xpath="//p[@class=\"main-title go-right\"]/../div/following-sibling::p")
    WebElement details;

    public String getDetails(){
        return details.getText();
    }

}
