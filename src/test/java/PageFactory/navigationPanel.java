package PageFactory;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import java.util.List;

/* This class covers the functionalities of Navigation panel in CLIQ Dashboard page. It includes visibility of elements,
link redirection etc. */

public class navigationPanel {


    WebDriver driver;
    WebDriverWait wait;
    public static Logger logger;

    @FindBy(css = "img[class='SidebarHeader__cl-logo']")
    WebElement dashboardLogo;
    @FindBy(css = "img[class='SidebarHeader__collapse-icon']")
    WebElement expandOrCollapse;
    @FindBy(css = "div[class='Sidebar']")
    WebElement sideBar;
    @FindBy(css = "img[class='AppHeader__logo']")
    WebElement headerLogo;
    @FindBy(css = "div[class='modal-body']")
    WebElement notificationPopupBody;
    @FindBy(css = "img[class='SidebarHeader__sub-footer__container__icon__notification']")
    WebElement btn_Notification;
    @FindBy(css = "h2[class='modal-title']")
    WebElement modalTitle;
    @FindBy(css = "button[class='modal-close-btn']")
    WebElement btn_modalClose;
    @FindBy(xpath = "//*[local-name()='a']")
    List<WebElement> navPanelElements;
    @FindBy(css = "button[class='cl-avatar']")
    WebElement editProfButton;
    @FindBy(css = "div[class='modal ']")
    WebElement editProfilePopup;
    @FindBy(css="h2[class='modal-title']")
    WebElement profileModalTitle;
    @FindBy(css = "button[class='modal-close-btn']")
    WebElement editProfClose;
    @FindBy(css = "div[class='down-arrow']")
    WebElement downArrow_Profile;
    @FindBy(css = "div[class='logout-container']")
    WebElement logoutButton;
    @FindBy(css = "img[class='login-background']")
    WebElement loginBackground;


    public navigationPanel(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        logger = Logger.getLogger(Dashboard.class.getName().getClass());
    }

    public void visibilityOfLogo() {


        String currentUrl = driver.getCurrentUrl();
        String baseUrl = currentUrl.replace("/dashboard", "");

        String actAttribute = dashboardLogo.getAttribute("src");
        String expAttribute = baseUrl + "/assets/img/CulturaLink_logo.9a799bfe28b33aa673d52c8e700c62f7.png";
        Assert.assertEquals(actAttribute, expAttribute);

    }

    public void expandOrCollapseIcon() throws InterruptedException {
        String sideBarStyle = sideBar.getAttribute("style");
        String exp_sideBarStyle = "width: 210px;";
        String expStyleAfterClick = "width: 70px;";

        if (sideBarStyle.equals(exp_sideBarStyle)) {
            expandOrCollapse.click();
            Thread.sleep(1000);
            String updatedSideBarStyle = sideBar.getAttribute("style");
            Assert.assertEquals(updatedSideBarStyle, expStyleAfterClick);


        } else {
            logger.info("Side bar is in collapsed state");
        }

    }

    public void headerLogoVisibility() {
        try {
            headerLogo.isDisplayed();
            expandOrCollapse.click();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    public void verifyNotificationButton() {
        btn_Notification.click();
        if (!notificationPopupBody.isDisplayed()) {
            logger.info("Notification popup is not displayed");
        } else {
            String expectedModalTitle = modalTitle.getText();
            Assert.assertEquals(expectedModalTitle, "Notifications");
            btn_modalClose.click();
        }

    }

    public void checkRedirection() throws InterruptedException {
        String currentUrl = driver.getCurrentUrl();
        String baseUrl = currentUrl.replace("/dashboard", "");
        for (int i = 0; i < 16; i++) {
            String[] expectedHref = {baseUrl+"/cart",
                    baseUrl+"/cart/testCall",
                    baseUrl+"/dashboard",
                    baseUrl+"/clients",
                    baseUrl+"/users",
                    baseUrl+"/languages",
                    baseUrl+"/lite-schedule",
                    baseUrl+"/lite-schedule/scheduling-admin",
                    baseUrl+"/translation/documents",
                    baseUrl+"/translation-admin",
                    baseUrl+"/reports",
                    baseUrl+"/tools",
                    baseUrl+"/interpreter",
                    baseUrl+"/analytics-reports",
                    baseUrl+"/help",
                    baseUrl+"/reports"};
            String actualHref = navPanelElements.get(i).getAttribute("href");
            Thread.sleep(500);
            Assert.assertEquals(actualHref, expectedHref[i]);
        }
    }
    public void verifyEditProfilePopup(){
        editProfButton.click();
        if (editProfilePopup.isDisplayed()){
            String expectedTitle = "Edit Profile Photo";
            String actTitle = profileModalTitle.getText();
            Assert.assertEquals(actTitle,expectedTitle);
            editProfClose.click();
        }else{
            logger.info("Edit profile popup is not displayed");
        }

    }
    public void verifyLogoutFunctionality(){
        String currentUrl = driver.getCurrentUrl();
        String baseUrl = currentUrl.replace("/dashboard", "");
        wait = new WebDriverWait(driver,50);
        downArrow_Profile.click();
        wait.until(ExpectedConditions.visibilityOf(logoutButton));
        logoutButton.click();
        wait.until(ExpectedConditions.visibilityOf(loginBackground));
        String expectedURL = baseUrl+"/login";
        Assert.assertEquals(expectedURL,driver.getCurrentUrl());

    }
}
