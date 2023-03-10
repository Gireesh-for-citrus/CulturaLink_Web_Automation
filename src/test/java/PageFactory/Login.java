package PageFactory;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Login {
    WebDriver driver;
    WebDriverWait wait;
    Properties prop;

    public static Logger logger;


    @FindBy(css = "img[class='login-background']")
    WebElement loginBackground;
    @FindBy(id = "email")
    WebElement inputEmail;
    @FindBy(id = "password")
    WebElement inputPassword;
    @FindBy(css = "div[class='text text__primary']")
    WebElement btnLogin;
    @FindBy(css = "span[class='alert-msg']")
    WebElement wrongCredAlert;


    public Login(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        logger = Logger.getLogger(Login.class.getName());
    }


    public void visibilityOfElementAndTitle() {

        try {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            loginBackground.getAttribute("src").equals("/assets/img/login-background.eaf3d2eae77e6f050718b0da18ef5839.png");
        } catch (NoSuchElementException e) {
            System.out.println("Element is not displayed - " + e);
        }

        String pageTitle = driver.getTitle();

        if (pageTitle.equals("Login")) {
            System.out.println("Page title verification success");
        } else System.out.println("Page title verification failed. Current page title is - " + pageTitle);
    }

    public void loginValidationCheck() throws InterruptedException {

        String[][] cred = {{"", ""}, {"Test", "test"}, {"gkrishnankutty@theculturalink.com", "123"}, {"testing@testmail.com", "Diversity2022!"}};

        for (String[] strings : cred) {
            inputEmail.clear();
            inputEmail.sendKeys(strings[0]);
            Thread.sleep(1000);

            inputPassword.clear();
            inputPassword.sendKeys(strings[1]);
            Thread.sleep(1000);

            btnLogin.click();
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOf(wrongCredAlert));
            String alert = wrongCredAlert.getText();
            if (alert.equals("Your email/password is incorrect")) {
                System.out.println("Alert verification success");
                logger.info("Alert verification success....");
                Thread.sleep(3000);
            } else System.out.println("No alert displayed, Alert verification failed");
        }
    }
    public void loginWithValidCredentials() throws IOException, InterruptedException {
        prop = new Properties();
        String propFilePath = "src/test/resources/Config/Config.properties";
        FileInputStream configPropFile = new FileInputStream(propFilePath);
        prop.load(configPropFile);

        String userName = prop.getProperty("User");
        String password = prop.getProperty("Password");

        wait = new WebDriverWait(driver, 30);

        driver.navigate().refresh();
        wait.until(ExpectedConditions.visibilityOf(inputEmail));

        inputEmail.clear();
        inputEmail.sendKeys(userName);
        Thread.sleep(1000);
        inputPassword.clear();
        inputPassword.sendKeys(password);
        Thread.sleep(1000);
        btnLogin.click();
        logger.info("Logged in to the Application....");
    }
    public void verifyDashboardLoaded() throws InterruptedException {
        Thread.sleep(60000);
        String currentTitle = driver.getTitle();
        String expectedTitle = "CLIQ - Dashboard";
        Assert.assertEquals(currentTitle, expectedTitle);
    }
}
