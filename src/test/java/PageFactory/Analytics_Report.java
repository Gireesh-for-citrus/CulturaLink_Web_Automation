package PageFactory;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class Analytics_Report {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//*[local-name()='a']")
    List<WebElement> navPanelElements;
    @FindBy(xpath = "//h1[contains(text(),'Most Missed')]")
    WebElement header1;
    @FindBy(xpath = "//h1[contains(text(),'Most Requested')]")
    WebElement header2;

    @FindBy(xpath="//*[local-name()='g']")
    List<WebElement> GraphElements;

    public Analytics_Report(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void ClickOnAnalyticsReport() {
        wait = new WebDriverWait(driver, 50);
        navPanelElements.get(13).click();
    }
    public void ValidateHeader(){
        String actTitle1 = header1.getText();
        String actTitle2 = header2.getText();
        Assert.assertEquals(actTitle1,"Top 10 Most Missed Requested Languages");
        Assert.assertEquals(actTitle2,"Top 10 Most Requested Languages");
    }
    public void Tool_tip() {
        try {
            for(int i=0; i < 18; i++){
                wait = new WebDriverWait(driver,50);
                GraphElements.get(i).isDisplayed();}
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }System.out.println("Done");
    }
}
