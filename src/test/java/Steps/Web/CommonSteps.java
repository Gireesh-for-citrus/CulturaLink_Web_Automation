package Steps.Web;
import PageElement.PagesWithLocators;
import PageFactory.*;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.BeforeTest;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class CommonSteps {
    WebDriver driver = null;
    public static Logger logger = null;
    Properties Prop;
    Login login;
    Help help;
    NavigationPanel navPanel;
    Tools tools;
    Report reports;
    Analytics_Report AnalyticReport;
    RequestAppointment requestAppointment;


    @BeforeTest
    public static void loadLog4J() {
        String log4Jpath = System.getProperty("src/test/resources/Config/log4j2.properties");
        PropertyConfigurator.configure(log4Jpath);
    }
//    @After
//    public void closeBrowser() {
//        driver.close();
//    }

    @Given("I Load the URL {string}")
    public void iLoadTheUrl(String urlName) throws IOException {
        Prop = new Properties();
        String propFilePath = "src/test/resources/Config/Config.properties";
        FileInputStream configPropFile = new FileInputStream(propFilePath);
        Prop.load(configPropFile);

        String br = Prop.getProperty("browser");
        if (br.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        if (br.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        if (br.equals("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        if (br.equals("safari")) {
            WebDriverManager.safaridriver().setup();
            driver = new SafariDriver();
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        PagesWithLocators pages = PagesWithLocators.valueOf(urlName);
        driver.get(pages.getPage());
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @And("I check whether the background image and page title are displayed")
    public void iCheckWhetherTheBackgroundImageAndPageTitleAreDisplayed() {
        login = new Login(driver);
        login.visibilityOfElementAndTitle();
    }

    @Then("I check whether the validation is correct for valid and invalid login credentials")
    public void iCheckWhetherTheValidationIsCorrectForValidAndInvalidLoginCredentials() throws InterruptedException {
        login.loginValidationCheck();
    }

    @Then("I Login to dashboard with valid credentials")
    public void iLoginToDashboardWithValidCredentials() throws IOException, InterruptedException {

        login = new Login(driver);
        login.loginWithValidCredentials();
        Thread.sleep(5000);
        System.out.println("Logged In");
        Thread.sleep(10000);
    }

    @And("I check whether the dashboard page is loaded")
    public void iCheckWhetherTheDashboardPageIsLoaded() throws InterruptedException {
        login.verifyDashboardLoaded();
    }

    @Then("I check the logo is displayed")
    public void iCheckTheLogoIsDisplayed() {
        try {
            navPanel = new NavigationPanel(driver);
            navPanel.visibilityOfLogo();
            System.out.println("Logo verification passed...");
            // logger.info("Logo verification passes...");
        } catch (Exception e) {
//            logger.error("Logo is missing from Dashboard");
            System.out.println("Logo verification failed...");
        }
    }

    @And("I check whether the Expand or Collapse button is working fine")
    public void iCheckWhetherTheExpandOrCollapseButtonIsWorkingFine() throws InterruptedException {
        navPanel = new NavigationPanel(driver);
        navPanel.expandOrCollapseIcon();
        navPanel.headerLogoVisibility();
    }

    @And("I verify the notification button showing the notification popup")
    public void iVerifyTheNotificationButtonShowingTheNotificationPopup() {
        navPanel = new NavigationPanel(driver);
        navPanel.verifyNotificationButton();
    }

    @Then("I verify the redirection URLs for other links on the Navigation panel")
    public void iVerifyTheRedirectionURLsForOtherLinksOnTheNavigationPanel() throws InterruptedException {
        navPanel = new NavigationPanel(driver);
        navPanel.checkRedirection();
    }

    @And("I check whether the Edit profile picture popup displayed when clicking on it")
    public void iCheckWhetherTheEditProfilePicturePopupDisplayedWhenClickingOnIt() {
        navPanel = new NavigationPanel(driver);
        navPanel.verifyEditProfilePopup();

    }

    @And("I verify the logout button is working fine")
    public void iVerifyTheLogoutButtonIsWorkingFine() {
        navPanel = new NavigationPanel(driver);
        navPanel.verifyLogoutFunctionality();
    }

    @Then("I click on help")
    public void iClickOnHelp() {
        help = new Help(driver);
        help.ClickOnHelp();
    }

    @Then("I click on Analytics Report")
    public void iClickOnAnalyticsReport() {
        AnalyticReport = new Analytics_Report(driver);
        AnalyticReport.ClickOnAnalyticsReport();
    }

    @And("I validate the link")
    public void iValidateTheLink() throws InterruptedException {
        help.Validate_link();
        help.Validate_link1();
        help.validate_Phone_Number();
    }

    @And("I validate the header")
    public void iValidateTheHeader() {
        AnalyticReport.ValidateHeader();
    }

    @Then("I click on Tools")
    public void iClickOnTools() throws InterruptedException {
        tools = new Tools(driver);
        tools.ClickOnTools();
    }

    @And("I validate the OPI_Import")
    public void iValidateTheOPI_Import() throws InterruptedException {
        tools.opi_import();
        tools.Choose_a_File();
        tools.Import();
        driver.navigate().back();
    }

    @Then("I validate the OSI_Import")
    public void iValidateTheOSI_Import() throws InterruptedException {
        tools.osi_import();
        tools.Choose_a_File();
        tools.Import();
        driver.navigate().back();
    }

    @Then("I validate the demo-setup")
    public void iValidateTheDemoSetup() throws InterruptedException {
        tools.demo_setup();
        driver.navigate().back();
    }

    @Then("I validate the role_management")
    public void iValidateTheRole_management() throws InterruptedException {
        tools.role_management();
        driver.navigate().to("https://www.culturalinksync.com/tools");
    }

    @Then("I validate the Application Settings")
    public void iValidateTheApplicationSettings() throws InterruptedException {
        tools.Application_Settings();
        driver.navigate().back();
    }

    @Then("I validate Client Import")
    public void iValidateClientImport() throws InterruptedException {
        tools.Client_import();
        tools.Choose_a_File();
        tools.Import();
        driver.navigate().back();
    }

    @Then("I validate the URL and Title")
    public void iValidateTheURL() throws InterruptedException {
        tools.URL_verification();
    }

    @Then("I validate Retract Import")
    public void iValidateRetractImport() throws InterruptedException {
        tools.Retract_import();
        tools.Choose_a_File();
        tools.Import();
        driver.navigate().back();
    }

    @Then("I validate  Import Fluency Names")
    public void iValidateImportFluencyNames() throws InterruptedException {
        tools.Import_Fluency_Names();
        tools.Choose_a_File();
        tools.Import();
        driver.navigate().back();
    }

    @Then("I validate Client Strip")
    public void iValidateClientStrip() {
        tools.Client_Strip();
        tools.Run_Processes();
        driver.navigate().back();
    }

    @Then("I validate Public ID  add")
    public void iValidatePublicIDAdd() {
        tools.public_ID_Add();
        tools.Run_Processes();
        driver.navigate().back();
    }

    @Then("I validate Fix Billing Amount")
    public void iValidateFixBillingAmount() {
        tools.Fix_Billed_Amount();
        tools.Run_Processes();
        driver.navigate().back();
    }

    @Then("I validate Fix OSI billing Amount")
    public void iValidateFixOSIBillingAmount() {
        tools.Fix_OSI_Billing_Amount();
        tools.Run_Processes();
        driver.navigate().back();
    }

    @Then("I validate Clear_Reports")
    public void iValidateClear_Reports() {
        tools.clean_Downloads();
    }

    @Then("I validate Void_Transactions")
    public void iValidateVoid_Transactions() throws InterruptedException {
        tools.Void_Transactions();
        tools.Validating_Attributes();
        tools.Validating_Pagenumber();
        driver.navigate().back();
    }

    @Then("I validate Edit_Transactions")
    public void iValidateEdit_Transactions() throws InterruptedException {
        tools.Edit_Transactions();
        tools.Validating_Attributes();
        tools.Validating_Pagenumber();
        driver.navigate().back();
    }

    @Then("I validate Fix_OPI_import")
    public void iValidateFix_OPI_import() {
        tools.Fix_OPI_Imports();
        tools.Validating_Attributes();
        driver.navigate().back();
    }

    @Then("I validate Dispute Transaction")
    public void iValidateDisputeTransaction() throws InterruptedException {
        tools.Dispute_Transactions();
        tools.Validating_Attributes();
        tools.Validating_HeaderValues();
        driver.navigate().back();
    }

    @Then("I validate Fix Dispute Transaction")
    public void iValidateFixDisputeTransaction() throws InterruptedException {
        tools.Fix_Dispute_Transactions();
        tools.Validating_Attributes();
        tools.Validating_HeaderValues();
        driver.navigate().back();
    }

    @And("I validate Tool tip")
    public void iValidateToolTip() throws InterruptedException {
        AnalyticReport.Tool_tip();
    }

    @Then("I click on Request Appointment")
    public void iClickOnRequestAppointment() {
        requestAppointment = new RequestAppointment(driver);
        requestAppointment.ClickOnRequestAppointment();

    }

    @Then("I validate CustomerInfo")
    public void iValidateCustomerInfo() throws InterruptedException {
        requestAppointment.Customer_Information();
    }

    @Then("I validate PatientInfo")
    public void iValidatePatientInfo() throws InterruptedException {
        requestAppointment.Patient_Information();
    }

    @Then("I validate AppointmentInfo")
    public void iValidateAppointmentInfo() throws InterruptedException {
        requestAppointment.Appointment_Information();
    }

    @Then("I validate headers")
    public void iValidateHeaders() throws InterruptedException {
        requestAppointment.Header_Validation();
    }
    @And("I validate the links")
    public void iValidateTheLinks() throws InterruptedException {
        reports.URL_verification();

    }

    @And("I validate the titles")
    public void iValidateTheTitles() throws InterruptedException {
        reports.Reports_title();
    }

    @Then("I validate Encounter quality report")
    public void iValidateEncounterQualityReport() throws InterruptedException {
        reports.Encounter_Quality();
        reports.Validating_Attributes();
        reports.Toggle_button();
        reports.Encounter_Quality_Graph();
        driver.navigate().to("https://www.culturalinksync.com/reports");
    }


    @Then("I validate Usage_By_Language")
    public void iValidateUsage_By_Language() throws InterruptedException {
        reports.Usage_By_Language();
        reports.Validating_Attributes();
        reports.Toggle_button();
        reports.Usage_By_Language__Graph();
        driver.navigate().to("https://www.culturalinksync.com/reports");
    }

    @Then("I validate Device_Usage")
    public void iValidateDevice_Usage() throws InterruptedException {
        reports.Device_Usage();
        reports.Validating_Attributes();
        reports.Toggle_button();
        reports.Device_Usage_Graph();
        driver.navigate().to("https://www.culturalinksync.com/reports");
    }

    @Then("I validate Usage_By_Modality")
    public void iValidateUsage_By_Modality() throws InterruptedException {
        reports.Usage_By_Modality();
        reports.Validating_Attributes();
        reports.Toggle_button();
        reports.Usage_By_Modality_Graph();
        driver.navigate().to("https://www.culturalinksync.com/reports");
    }

    @Then("I validate Usage_By_Origin")
    public void iValidateUsage_By_Origin() throws InterruptedException {
        reports.Usage_By_Origin();
        reports.Validating_Attributes();
        reports.Toggle_button();
        reports.Usage_By_Origin_Graph();
        driver.navigate().to("https://www.culturalinksync.com/reports");
    }

    @Then("I validate All_Session Report")
    public void iValidateAll_SessionReport() throws InterruptedException {
        reports.All_Sessions();
        reports.Validating_Attributes();
        reports.Toggle_button();
        reports.All_Session_Graph();
        driver.navigate().to("https://www.culturalinksync.com/reports");
    }

    @Then("I validate Interpreter Pay")
    public void iValidateInterpreterPay() throws InterruptedException {
        reports.Interpreter_Pay();
        reports.Validating_Attributes();
        reports.Toggle_button();
        driver.navigate().to("https://www.culturalinksync.com/reports");
    }

    @Then("I validate Encounter Density report")
    public void iValidateEncounterDensityReport() throws InterruptedException {
        reports.Encounter_Density();
        driver.navigate().to("https://www.culturalinksync.com/reports");
    }

    @Then("I click on Report")
    public void iClickOnReport() {
        reports = new Report(driver);
        reports.ClickOnReport();
    }

    @Then("I validate InputFields")
    public void iValidateInputFields() throws InterruptedException {
        requestAppointment.Validating_InputFields();

    }
}


