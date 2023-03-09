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

/* This class covers the functionalities in CLIQ Login page. It includes visibility of elements, input field validation
 and login functionality */

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
    @FindBy(css = "a[class='forgot-password']")
    WebElement forgotPasswordLink;
    @FindBy(css = "div[class='ModalityWrapper__container osi performance-statistics sub-container']")
    WebElement financialPerfEle;


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
            e.printStackTrace();
        }

        String pageTitle = driver.getTitle();

        if (pageTitle.equals("Login")) {
            logger.info("Page title verification success");
        } else logger.info("Page title verification failed. Current page title is - " + pageTitle);
    }

    public void verifyForgotPassword() {
        String expForgotPasswordURL = "https://culturalink.onelogin.com/password/forgot_password";
        String actForgotPasswordURL = forgotPasswordLink.getAttribute("href");
        Assert.assertEquals(actForgotPasswordURL, expForgotPasswordURL);
    }

    public void loginValidationCheck() throws IOException {

        wait = new WebDriverWait(driver, 20);
        prop = new Properties();

        String propFilePath = "src/test/resources/Config/Config.properties";
        FileInputStream configPropFile = new FileInputStream(propFilePath);
        prop.load(configPropFile);

        String userName = prop.getProperty("User");
        String password = prop.getProperty("Password");
        String[][] cred = {{"", ""}, {"Test", "test"}, {userName, "123"}, {"testing@testmail.com", password}};

        for (String[] strings : cred) {
            inputEmail.clear();
            inputEmail.sendKeys(strings[0]);

            inputPassword.clear();
            inputPassword.sendKeys(strings[1]);

            btnLogin.click();

            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOf(wrongCredAlert));

            String alert = wrongCredAlert.getText();

            if (alert.equals("Your email/password is incorrect")) {
                logger.info("Alert verification success....");
                wait.until(ExpectedConditions.invisibilityOf(wrongCredAlert));
            } else logger.info("No alert displayed, Alert verification failed");
        }
    }

    public void loginWithValidCredentials() throws IOException {
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

        inputPassword.clear();
        inputPassword.sendKeys(password);

        btnLogin.click();
        logger.info("Logged in to the Application....");

    }

    public void verifyDashboardLoaded() {
        wait = new WebDriverWait(driver, 70);
        wait.until(ExpectedConditions.visibilityOf(financialPerfEle));
        String currentTitle = driver.getTitle();
        String expectedTitle = "CLIQ - Dashboard";
        Assert.assertEquals(currentTitle, expectedTitle);
    }

}
