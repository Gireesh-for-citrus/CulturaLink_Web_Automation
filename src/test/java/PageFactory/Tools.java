package PageFactory;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class Tools {
    WebDriver driver;
    WebDriverWait wait;
    @FindBy(xpath = "//*[local-name()='a']")
    List<WebElement> navPanelElements;
    @FindBy(xpath = "//*[local-name()='th']")
    List<WebElement> TableHeader;
    @FindBy(xpath = "//*[local-name()='label']")
    List<WebElement> Application_Settings;
    @FindBy(xpath = "//strong[@class='widget-name']")
    List<WebElement> toolElements;
    @FindBy(xpath = "//*[local-name()='h1']")
    WebElement title;
    @FindBy(className = "file-upload-label")
    WebElement Choose_file;
    @FindBy(className = "text-content")
    WebElement Import_Button;
    @FindBy(xpath = "//button[@type=\"submit\"]")
    WebElement Save;
    @FindBy(xpath = "//input[@placeholder='Choose Start Date']")
    WebElement start_date;
    @FindBy(xpath = "//input[@placeholder='Choose End Date']")
    WebElement end_date;
    @FindBy(xpath = "(//strong[@class='label'])[1]")
    WebElement Demo_data_from;
    @FindBy(xpath = "(//strong[@class=\"label\"])[2]")
    WebElement Demo_data_to;
    @FindBy(xpath = "(//img[@class='input-checkbox-icon unchecked'])[1]")
    WebElement interaction_checkbox;
    @FindBy(xpath = "(//img[@class='input-checkbox-icon unchecked'])[2]")
    WebElement interaction_checkbox1;
    @FindBy(xpath = "(//img[@class='input-checkbox-icon unchecked'])[3]")
    WebElement interaction_checkbox2;
    @FindBy(xpath = "(//strong[@class='label'])[3]")
    WebElement Entities;
    @FindBy(xpath = "//span[text()='Add New Role']")
    WebElement Add_role;
    @FindBy(xpath = "//span[text()='Clean Reports']")
    WebElement Clean_Reports;
    @FindBy(xpath = "//span[text()='Run processes']")
    WebElement Run_Processes;
    @FindBy(xpath = "//span[text()='Cancel']")
    WebElement Cancel;
    @FindBy(xpath = "//span[text()='Replace existing Public IDs']")
    WebElement Replace_ID_Checkbox;
    @FindBy(name = "name")
    WebElement Role_Name;
    @FindBy(xpath = "(//span[@class=\"checkbox-label\"])[33]")
    WebElement Role_type1;
    @FindBy(xpath = "(//span[@class=\"checkbox-label\"])[34]")
    WebElement Role_type2;
    @FindBy(xpath = "//span[contains(text(),'Save Settings')]")
    WebElement Save_settings;
    @FindBy(xpath = "//input[@type='text']")
    WebElement textbox;
    @FindBy(xpath = "//span[text()='HIDE FILTER']")
    WebElement hide_filter;
    @FindBy(xpath = "//span[text()='Remove']")
    WebElement Remove;
    @FindBy(xpath = "//span[text()='Mark All Void']")
    WebElement mark_void;
    @FindBy(xpath = "//span[text()='Download Report']")
    WebElement download_Report;
    @FindBy(id = "columnName-0")
    WebElement Date_column;
    @FindBy(id = "equals-0")
    WebElement Range;
    @FindBy(xpath = "//span[text()='Add Additional Filter']")
    WebElement Add_Additional_Filter;
    @FindBy(xpath = "//span[text()='Apply Filter']")
    WebElement Apply_Filter;
    @FindBy(id = "columnName-1")
    WebElement Additional_col;
    @FindBy(id = "equals-1")
    WebElement Range1;
    @FindBy(id = "value-1")
    WebElement Enter_Value;
    @FindBy(xpath ="//span[@class='page-number']")
    List<WebElement> Pagenumber;


    public Tools(WebDriver driver) throws InterruptedException {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void ClickOnTools() {
        wait = new WebDriverWait(driver, 50);
        navPanelElements.get(11).click();
    }
    public void URL_verification() throws InterruptedException {
        String currentUrl = driver.getCurrentUrl();
        String baseUrl = currentUrl.replace("/tools", "");
        System.out.println(baseUrl);
        for (int i = 0; i < 18; i++) {
            toolElements.get(i).click();
            String Title = title.getText();
            String[] titles = {"OPI Import", "OSI Import", "Demo Setup", "Roles", "Application Settings", "Client Import",
                    "Strip Client Name Special Characters", "Add Public ID to Interactions", "Fix Billed Amount", "Fix OSI Billing Report",
                    "All Transactions", "All Sessions Report", "All Failed OPI Imports", "All Transactions", "Retract Import", "Disputed Transactions",
                    "Fluency Names Import", "Clean Downloaded Reports"};
            System.out.println("Title " + Title);
            String[] expectedHref = {
                    baseUrl + "/tools/opi-import",
                    baseUrl + "/tools/osi-import",
                    baseUrl + "/tools/demo-setup",
                    baseUrl + "/tools/role-management",
                    baseUrl + "/tools/application-settings",
                    baseUrl + "/tools/client-import",
                    baseUrl + "/tools/client-strip",
                    baseUrl + "/tools/publicId-add",
                    baseUrl + "/tools/fix-billed-amount",
                    baseUrl + "/tools/fix-osi-billing-report",
                    baseUrl + "/reports/void-transactions",
                    baseUrl + "/reports/edit-interactions",
                    baseUrl + "/reports/fix-opi-import",
                    baseUrl + "/reports/dispute-transactions",
                    baseUrl + "/tools/retract-import",
                    baseUrl + "/reports/disputed-transactions-list",
                    baseUrl + "/tools/import-client-fluency",
                    baseUrl + "/tools/clean-dl-reports"};
            String actualHref = driver.getCurrentUrl();
            System.out.println("ExpectedURL " + expectedHref[i]);
            Assert.assertEquals(actualHref, expectedHref[i]);
            Assert.assertEquals(Title, titles[i]);
            driver.navigate().back();
        }
    }
    public void Choose_a_File() throws InterruptedException {
        String Choose = Choose_file.getText();
        String expText = "Choose a file";
        Assert.assertEquals(expText, Choose);
    }
    public void Import() {
        String Import = String.valueOf(Import_Button.getText());
        Assert.assertEquals(Import, "IMPORT");
    }
    public void opi_import() throws InterruptedException {
        toolElements.get(0).click();
    }
    public void osi_import() throws InterruptedException {
        toolElements.get(1).click();
    }
    public void demo_setup() throws InterruptedException {
        toolElements.get(2).click();
        Thread.sleep(3000);
        String[] expAttribute = {"Choose Start Date", "Choose End Date",
                "Select Client To Create Demo Data From",
                "Select Client To Send Demo Data To", "Select Which Entities To Create", "SUBMIT"};
        String StartDate = start_date.getAttribute("placeholder");
        String EndDate = end_date.getAttribute("placeholder");
        String demo_data_from = Demo_data_from.getText();
        String demo_data_to = Demo_data_to.getText();
        String entities = Entities.getText();
        String Submit = Save.getText();
        String[] Values = {StartDate, EndDate, demo_data_from, demo_data_to, entities, Submit};
        Assert.assertEquals(expAttribute, Values);
    }
    public void role_management() throws InterruptedException {
        toolElements.get(3).click();
        Thread.sleep(3000);
        Add_role.click();
        String Role = String.valueOf(Role_Name.isDisplayed());
        Thread.sleep(5000);
        for (int i = 0; i < 2; i++) {
            String tableHeader = TableHeader.get(i).getText();
            String[] HeaderValues = {"Permission Name", "Enabled", "Navigation Menu"};
            Assert.assertEquals(tableHeader, HeaderValues[i]);
            String RoleType1 = Role_type1.getText();
            String RoleType2 = Role_type2.getText();
            String[] ExpRoles = {"Culturalink", "Location"};
            String[] Roles = {RoleType1, RoleType2};
            Assert.assertEquals(ExpRoles, Roles);
            try {
                Save.isDisplayed();
            } catch (NoSuchElementException e) {
                e.printStackTrace();
            }
        }
    }
    public void Application_Settings() throws InterruptedException {
        toolElements.get(4).click();
        Thread.sleep(2000);
        for (int i = 0; i < 4; i++) {
            String App_Setting_Attributes = Application_Settings.get(i).getText();
            String[] expAttributes = {"VRI Timeout", "General OPI Destination Number",
                    "OPI Import Start/End Time Search Fuzz", "OPI Import Duration Time Search Fuzz"};
            Assert.assertEquals(App_Setting_Attributes, expAttributes[i]);
        }
        String Save = Save_settings.getText();
        Assert.assertEquals(Save, "SAVE SETTINGS");
        System.out.println("Done");
    }
    public void Client_import() throws InterruptedException {
        toolElements.get(5).click();
        String expAttribute = "Find top level client...";
        String text = textbox.getAttribute("placeholder");
        Assert.assertEquals(expAttribute, text);
    }
    public void Client_Strip() {
        toolElements.get(6).click();
    }
    public void public_ID_Add() {
        toolElements.get(7).click();
        try {
            Replace_ID_Checkbox.isDisplayed();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    public void Fix_Billed_Amount() {
        toolElements.get(8).click();
    }
    public void Fix_OSI_Billing_Amount() {
        toolElements.get(9).click();
    }

    public void Run_Processes() {
        Assert.assertEquals(Run_Processes.getText(), "RUN PROCESSES");
    }

    public void Retract_import() {
        toolElements.get(14).click();
    }

    public void Import_Fluency_Names() {
        toolElements.get(16).click();
    }

    public void clean_Downloads() {
        toolElements.get(17).click();
        try {
            Clean_Reports.isDisplayed();
            Cancel.click();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }
    public void Validating_Attributes() {
        Add_Additional_Filter.click();
        for (int i = 0; i < 9; i++) {
            String[] Attributes = {hide_filter.getText(), Date_column.getAttribute("id"),
                    Range.getAttribute("id"), start_date.getAttribute("placeholder"),
                    end_date.getAttribute("placeholder"), Additional_col.getAttribute("id"), Range1.getAttribute("id"),
                    Enter_Value.getAttribute("id"), Remove.getText()};
            String[] expAttributes = {"HIDE FILTER",
                    "columnName-0", "equals-0", "Choose Start Date", "Choose End Date",
                    "columnName-1", "equals-1", "value-1", "REMOVE"};
            Assert.assertEquals(Attributes, expAttributes);
        }}
    public void  Void_Transactions() throws InterruptedException {
        toolElements.get(10).click();
        try {
            mark_void.isDisplayed();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        Thread.sleep(5000);
        for (int i = 0; i < 15; i++) {
            String[] expValues ={"Action","Start Date & Time","End Date & Time","Duration (Minutes)",
                    "Language","Modality","Client","Facility","Department","Requester's Name",
                    "Patient ID","Interpreter ID","AAID","Device ID","Public ID"};
            Assert.assertEquals(expValues[i],(TableHeader.get(i).getText()));
        }}
    public void Edit_Transactions() throws InterruptedException {
        toolElements.get(11).click();
        Assert.assertEquals(download_Report.getText(),"DOWNLOAD REPORT");
        Thread.sleep(3000);
        for (int i = 0; i < 15; i++) {
            String[] expValues = {"Public ID","Start Date & Time","End Date & Time","Duration (Minutes)",
                    "Language","Modality","Billed Amount","Client","Facility","Department","Requester's Name",
                    "Patient ID","Interpreter ID","AAID","Device ID"};
            Assert.assertEquals(expValues[i], (TableHeader.get(i).getText()));
        }
    }
    public void  Fix_OPI_Imports(){
        toolElements.get(12).click();
    }
    public void Dispute_Transactions() throws InterruptedException {
        toolElements.get(13).click();
    }
    public void Fix_Dispute_Transactions() throws InterruptedException {
        toolElements.get(15).click();
    }
    public void Validating_HeaderValues(){
        for (int i = 0; i < 9; i++) {
            String[] expValues = {"Session ID","Client","Start Date & Time","End Date & Time",
                    "Duration (Minutes)","Language","Interpreter ID","Patient","AAID"};
            Assert.assertEquals(expValues[i], (TableHeader.get(i).getText()));
        }
    }
    public void Validating_Pagenumber(){
        Pagenumber.get(1).click();
    }
}
