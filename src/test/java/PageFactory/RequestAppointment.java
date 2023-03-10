package PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.List;

public class RequestAppointment {
    WebDriver driver;
    WebDriverWait wait;
    @FindBy(xpath="//input[@role=\"combobox\"]")
    WebElement   location;
    @FindBy(xpath = "//a[@class='dropdown-item']")
    List<WebElement> dropdown_items;
    @FindBy(xpath = "//*[local-name()='a']")
    List<WebElement> navPanelElements;
    @FindBy(xpath = "//input[@type='tel']")
    List<WebElement> TelInputFields;
    @FindBy(xpath="//input[@type='text']")
    List<WebElement> TextInputFields;
    @FindBy(xpath = "//*[local-name()='label']")
    List<WebElement> customerInfo;
    @FindBy(xpath = "//*[local-name()='h1']")
    List<WebElement>  Headers;
    @FindBy(xpath ="//select[@class='input']")
    List<WebElement> Dropdowns;
    @FindBy(xpath = "//input[@type='number']")
    WebElement Duration;
    @FindBy(id = "description")
    WebElement Description;
    @FindBy(xpath = "//span[@class='checkbox-label']")
    WebElement Checkbox;
    @FindBy(xpath = "//button[@type='submit']")
    List<WebElement> buttons;
    public RequestAppointment(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void ClickOnRequestAppointment() {
        wait = new WebDriverWait(driver, 50);
        navPanelElements.get(6).click();
    }
    public void Header_Validation() throws InterruptedException {
        Thread.sleep(2000);
        for(int  i=0;i<2;i++){
            String headers =  Headers .get(i).getText();
            String[]  expAttributes={"Customer Information",
                    "Appointment Information", "Patient Information"};
            Assert.assertEquals(headers,expAttributes[i]);
        }}
    public void Customer_Information() throws InterruptedException {
        Thread.sleep(2000);
        for (int i = 0; i < 5; i++) {
            String Customer_information =  customerInfo .get(i).getText();
            String[] expAttributes = {"Customer Name","", "Requester's Name",
                    "Requester's Email" ,"Requester's Phone"};
            Assert.assertEquals(Customer_information, expAttributes[i]);
        }}
    public void Appointment_Information() throws InterruptedException {
        Thread.sleep(2000);
        for (int i = 5; i < 25; i++) {
            String Appointment_information = customerInfo.get(i).getText();
            String[] expAttributes = {"Customer Name","", "Requester's Name",
                    "Requester's Email" ,"Requester's Phone","Home Visit", "", "Clinic/Venue Name", "Clinic/Venue Phone", "Address", "City", "State", "Zip", "Department", "Where to Meet Member", "Provider's Name", "Provider's Phone",
                    "Start Date and Time", "Timezone", "Appointment Duration (Minutes)", "Language Requested",
                    "Interpreter Gender", "Interpreter Requested", "", "Briefly Describe the Subject of This Appointment"};
           Assert.assertEquals(Appointment_information, expAttributes[i]);
        }}
    public void  Patient_Information() throws InterruptedException {
            Thread.sleep(2000);
        int i=25;
        {
            String Patient_information = customerInfo.get(i).getText();
            String expAttribute="Patient ID #/Ins. or Med #";
            Assert.assertEquals(Patient_information,expAttribute);
            }}
        public void  Validating_InputFields() throws InterruptedException {
        Thread.sleep(2000);
        //Text Input Fields
        TextInputFields.get(0).click();
        for(int i=2;i<13;i++) {
            TextInputFields.get(i).click();
        }
        TextInputFields.get(14).click();
        //Tele input fields
            for(int j=0;j<3;j++){
                TelInputFields.get(j).click();
            }
            //Dropdowns
            for(int k=0;k<4;k++){
                Dropdowns.get(k).click();
                Duration.click();
                Description.click();
            }
            Actions action = new Actions(driver);
            action.moveToElement(Checkbox).click().perform();
            Thread.sleep(3000);
            location.sendKeys("Test");
            dropdown_items.get(30).click();
            Thread.sleep(5000);
            buttons.get(0).click();
            buttons.get(1).click();
    }}
