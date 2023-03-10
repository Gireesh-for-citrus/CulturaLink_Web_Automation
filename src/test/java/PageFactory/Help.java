package PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class Help {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//*[local-name()='a']")
    List<WebElement> navPanelElements;
    @FindBy(xpath = "//a[text()='Support Account']")
    WebElement href;
    @FindBy(linkText = "our Support Page")
    WebElement href1;
    @FindBy(xpath = "//*[local-name()='h3']")
    List<WebElement> htags;
    public Help(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void ClickOnHelp() {
        wait = new WebDriverWait(driver, 50);
        navPanelElements.get(14).click();
    }

    public void Validate_link() throws InterruptedException {
        String expectedHref = "mailto:support@theculturalink.com?Online%20Support";
        String actualHref = href.getAttribute("Href");
        Thread.sleep(500);
        System.out.println(actualHref);
        Assert.assertEquals(actualHref, expectedHref);
    }

    public void Validate_link1() throws InterruptedException {
        String expectedHref = "https://culturalink.force.com/support/login?ec=302&startURL=%2Fsupport%2Fs%2F";
        String actualHref = href1.getAttribute("Href");
        Thread.sleep(500);
        System.out.println(actualHref);
        Assert.assertEquals(actualHref, expectedHref);
        href1.click();
    }
    public void validate_Phone_Number() throws InterruptedException {
        String expectedNumber = "(888)495-0060";
        String actual = htags.get(2).getText();
        Thread.sleep(500);
        System.out.println(expectedNumber);
        System.out.println(actual);
    }
}
