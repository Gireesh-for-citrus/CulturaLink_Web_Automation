package PageFactory;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class Report {
    WebDriver driver;
    WebDriverWait wait;
    @FindBy(xpath = "//*[local-name()='a']")
    List<WebElement> navPanelElements;
    @FindBy(className = "graph-dark")
    WebElement ToggleButton;
    @FindBy(xpath = "//*[local-name()='h1']")
    List<WebElement> ReportTitles;
    @FindBy(xpath = "//*[local-name()='g']")
    List<WebElement> GraphElements;
    @FindBy(xpath = "//*[local-name()='strong']")
    List<WebElement> ReportElements;
    @FindBy(xpath = "//span[text()='Add Additional Filter']")
    WebElement Add_Additional_Filter;
    @FindBy(xpath = "//input[@placeholder='Choose Start Date']")
    WebElement start_date;
    @FindBy(xpath = "//span[text()='HIDE FILTER']")
    WebElement hide_filter;
    @FindBy(xpath = "//input[@placeholder='Choose End Date']")
    WebElement end_date;

    @FindBy(xpath = "//span[text()='Apply Filter']")
    WebElement Apply_Filter;
    @FindBy(className = "download")
    WebElement download_Report;
    @FindBy(xpath = "//span[text()='Remove']")
    WebElement Remove;
    @FindBy(id = "columnName-0")
    WebElement Date_column;
    @FindBy(id = "equals-0")
    WebElement Range;
    @FindBy(id = "columnName-1")
    WebElement Additional_col;
    @FindBy(id = "equals-1")
    WebElement Range1;
    @FindBy(id = "value-1")
    WebElement Enter_Value;

    @FindBy(xpath = "//*[local-name()='th']")
    List<WebElement> TableHeader;
    @FindBy(xpath = "//div[text()='Duration (in min)']")
    WebElement Duration_button;

    public Report(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void ClickOnReport() {
        wait = new WebDriverWait(driver, 50);
        navPanelElements.get(10).click();
    }
    public void Reports_title() throws InterruptedException {
        Thread.sleep(5000);
        for(int i=0;i<3;i++) {
            String Title = ReportTitles.get(i).getText();
            String  Titles[]={"Usage Reports", "Billing Reports","Admin Reports"};
            Assert.assertEquals(Title,Titles[i]);
        }
    }
    public void URL_verification() throws InterruptedException {
        String currentUrl = driver.getCurrentUrl();
        String baseUrl = currentUrl.replace("/reports", "");
        for (int i = 1; i <9 ; i++) {
            ReportElements.get(i).click();
            String[] expectedHref = {baseUrl+"/reports",
                    baseUrl + "/reports/encounter-quality",
                    baseUrl + "/reports/encounter-density",
                    baseUrl + "/reports/language-usage",
                    baseUrl + "/reports/device-usage",
                    baseUrl + "/reports/usage-by-modality",
                    baseUrl + "/reports/usage-by-origin",
                    baseUrl + "/reports/all-billing",
                    baseUrl + "/reports/interpreter-pay",};
            String  Actual  = driver.getCurrentUrl();
            System.out.println(Actual);
            Assert.assertEquals(Actual, expectedHref[i]);
            driver.navigate().back();
        }       }

    public void Validating_Attributes() {
        Add_Additional_Filter.click();
        Assert.assertEquals(download_Report.getAttribute("class"),"download");
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
    public void Encounter_Quality() throws InterruptedException {
        ReportElements.get(1).click();
        Thread.sleep(3000);
        for (int i = 0; i < 16; i++) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView();",TableHeader.get(i));
            String ActualValues =TableHeader.get(i).getText();
            String[] Expectedvalues ={"Session ID","Start Date & Time", "End Date & Time",
                    "Language", "Modality","Wait Time", "Duration (Minutes)", "Requester",
                    "Patient ID","Client Rating", "Interpreter ID", "Interpreter Rating",
                    "Client", "Facility","Department","Device ID"};
            Assert.assertEquals(ActualValues,Expectedvalues[i]);
        }
    }
    public void Encounter_Density() throws InterruptedException {
        ReportElements.get(2).click();
        Thread.sleep(3000);
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
    public void Usage_By_Language() throws InterruptedException {
        ReportElements.get(3).click();
        Thread.sleep(3000);
        for (int i = 0; i < 5; i++) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView();",TableHeader.get(i));
            String ActualValues =TableHeader.get(i).getText();
            String[] Expectedvalues={"Language","Modality","Client","Encounters",
                    "% of Total Encounters", "Device ID"};
            Assert.assertEquals(ActualValues,Expectedvalues[i]);
        }
    }
    public void Device_Usage() throws InterruptedException {
        ReportElements.get(4).click();
        Thread.sleep(3000);
        for (int i = 0; i < 13; i++) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView();",TableHeader.get(i));
            String ActualValues =TableHeader.get(i).getText();
            String[] Expectedvalues={"Device ID","Modality", "Client",
                    "Region", "Facility", "Department", "Encounters", "Encounter / Day",
                    "Duration (Minutes)","Minutes / Day","Install Date","Last Used", "Active Days"};
            Assert.assertEquals(ActualValues,Expectedvalues[i]);
        }
    }
    public void Usage_By_Modality() throws InterruptedException {
        ReportElements.get(5).click();
        Thread.sleep(3000);
        for (int i = 0; i < 6; i++) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView();", TableHeader.get(i));
            String ActualValues = TableHeader.get(i).getText();
            String[] Expectedvalues = {"Modality", "Origin", "Language",
                    "Encounters (sum over period)", "Percentage of Total Encounters",
                    "Minutes (sum over period)"};
            Assert.assertEquals(ActualValues, Expectedvalues[i]);
        }
    }
    public void Usage_By_Origin() throws InterruptedException {
        ReportElements.get(6).click();
        Thread.sleep(3000);
        for (int i = 0; i < 7; i++) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView();",TableHeader.get(i));
            String[] ExpectedValues ={"Origin", "Modality", "Language",
                    "Encounters (sum over period)", "Percentage of Total Encounters",
                    "Minutes (sum over period)", "Client"};
            String ActualValues =TableHeader.get(i).getText();
            Assert.assertEquals(ActualValues,ExpectedValues[i]);
        }
    }
    public void All_Sessions() throws InterruptedException {
        ReportElements.get(7).click();
        Thread.sleep(3000);
        for (int i = 0; i < 15; i++) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView();",TableHeader.get(i));
            String ActualValues =TableHeader.get(i).getText();
            String[] ExpectedValues= {"Start Date & Time", "End Date & Time","Duration (Minutes)",
                    "Language", "Modality","Billed Amount", "Client","Facility",
                    "Department", "Requester's Name", "Patient ID",
                    "Interpreter ID","AAID", "Device ID", "Session Id"};
            Assert.assertEquals(ActualValues,ExpectedValues[i]);
        }
    }
    public void Interpreter_Pay() throws InterruptedException {
        ReportElements.get(8).click();
        Thread.sleep(3000);
        for (int i = 0; i < 12; i++) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView();",TableHeader.get(i));
            String ActualValues =TableHeader.get(i).getText();
            String[] ExpectedValues ={"Date", "Start Time", "Language", "Duration (Minutes)",
                    "Paid Amount", "Interpreter Name", "Interpreter ID", "Facility",
                    "Department", "Modality", "Device ID","Public ID"};
            Assert.assertEquals(ActualValues,ExpectedValues[i]);
        }
    }
    public void Toggle_button() throws InterruptedException {
        ToggleButton.click();
        Thread.sleep(3000);
    }
    public void Encounter_Quality_Graph() throws InterruptedException { int j=0;
        for(int i=0;i<25;i++)
        {
            wait = new WebDriverWait(driver,50);
            GraphElements.get(i).isEnabled();
        } while (j == 27)
        {
            wait.until(ExpectedConditions.visibilityOf(GraphElements.get(j)));
            GraphElements.get(j).click();
            System.out.println("True");
        }j++;
        driver.navigate().to("https://www.culturalinksync.com/reports");
    }
    public void Usage_By_Language__Graph() throws InterruptedException {
        Duration_button.click();
        for(int i=5;i<11;i++)
        {
            GraphElements.get(i).click();
        }
    }
    public void Device_Usage_Graph() throws InterruptedException {
        Duration_button.click();
        for(int i=0;i<10;i++)
        {
            GraphElements.get(i).isEnabled();
        }
    }
    public void Usage_By_Modality_Graph() throws InterruptedException {
        Duration_button.click();
        for(int i=13;i<16;i++)
        {
            GraphElements.get(i).click();
        }
    }
    public void Usage_By_Origin_Graph() throws InterruptedException {
        for(int i=14;i<15;i++)
        {
            GraphElements.get(i).click();
        }
    }
    public void All_Session_Graph() throws InterruptedException {
        for(int i=13;i<16;i++)
        {
            GraphElements.get(i).click();
        }
    }}
