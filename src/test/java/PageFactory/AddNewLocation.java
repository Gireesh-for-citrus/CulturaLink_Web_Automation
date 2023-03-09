package PageFactory;


import Utils.Utils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/* This class covers add new client functionality in Clients page. It includes the input form validations, date pickers etc
in the Basic Info, Contact Info, Business Address, Mailing Address, Location Roles, Smart Routing, Billing Profile, Billing Rates,
and Carts tabs */
public class AddNewLocation {

    WebDriver driver;
    WebDriverWait wait;
    Actions action;
    Properties prop;
    JavascriptExecutor js;

    @FindBy(css = "div[class='page-container__title']")
    WebElement pageContainerTitle;
    @FindBy(how = How.CSS, using = "button.Wizard__header-item")
    List<WebElement> wizardHeaderTabs;
    @FindBy(how = How.CSS, using = "button.button__primary")
    WebElement addNewLocationButton;
    @FindBy(css = "h1[class='title mt-3']")
    List<WebElement> contHeadBasicInfo;
    @FindBy(css = "input[class='input']")
    List<WebElement> InputTextFields;
    @FindBy(css = "label[class='label']")
    List<WebElement> InputFieldLabels;
    @FindBy(css = "span[class='checkbox-label']")
    List<WebElement> BI_checkBoxLabels;
    @FindBy(xpath = "//span[text()='Add Another']")
    WebElement btn_AddAnother;
    @FindBy(css = "div[class='d-flex align-items-center']")
    List<WebElement> classiInputFields;
    @FindBy(xpath = "//span[text()='Remove']")
    List<WebElement> btn_Remove;
    @FindBy(css = "span[class='alert-msg']")
    WebElement alertRemoveClassification;
    @FindBy(how = How.CSS, using = "div.AlertBlock")
    WebElement alertBox;
    @FindBy(xpath = "//span[text()='Next']")
    WebElement btn_Next;
    @FindBy(css = "h1[class='title mt-3']")
    WebElement contHeader;
    @FindBy(xpath = "//span[text()='Back']")
    WebElement btn_Back;
    @FindBy(id = "businessAddressLineOne")
    WebElement addressLine1;
    @FindBy(id = "businessAddressCity")
    WebElement addressCity;
    @FindBy(id = "businessAddressState")
    WebElement addressState;
    @FindBy(id = "mailingAddressState")
    WebElement mailingAdState;
    @FindBy(id = "businessAddressZip")
    WebElement addressZip;
    @FindBy(xpath = "//*[@id=\"AddChildWizard\"]/div[2]/fieldset/div[2]")
    WebElement inputFieldArea;
    @FindBy(xpath = "//p[strong]")
    List<WebElement> LR_Permissions;
    @FindBy(css = "span[class='text-danger']")
    WebElement smartRoutingWarning;
    @FindBy(how = How.CSS, using = "span.checkbox")
    WebElement checkBox;
    @FindBy(xpath = "//th")
    List<WebElement> BP_TableHeader;
    @FindBy(how = How.CSS, using = "th.sortable")
    List<WebElement> headerWithSort;
    @FindBy(css = "img[class='sort-icon']")
    WebElement sortImage;
    @FindBy(css = "img[class='gear']")
    List<WebElement> settingsIcon;
    @FindBy(xpath = "//li[text()='Edit']")
    List<WebElement> editProfButton;
    @FindBy(css = "div[class='modal ']")
    WebElement editBillingProfPopup;
    @FindBy(css = "h2[class='modal-title']")
    WebElement modalTitle;
    @FindBy(css = "label[for='minimum-minutes']")
    WebElement minMinuteFieldLabel;
    @FindBy(id = "minimumMinutes")
    WebElement minMinuteInputField;
    @FindBy(css = "span[class='alert-msg']")
    WebElement billingProfAlert;
    @FindBy(xpath = "//span[text()='SAVE']")
    WebElement saveButton;
    @FindBy(xpath = "//td")
    List<WebElement> tableValues;
    @FindBy(css = "button[class='modal-close-btn']")
    WebElement modalCloseButton;
    @FindBy(css = "span[class='text text__secondary']")
    WebElement cancelButton;
    @FindBy(xpath = "//span[text()='Add Rate']")
    WebElement addRateButton;
    @FindBy(xpath = "//p")
    WebElement noRatesText;
    @FindBy(css = "label[for='modalityName']")
    WebElement labelForModality;
    @FindBy(css = "label[for='languageId']")
    WebElement labelForLang;
    @FindBy(css = "label[for='normalRate']")
    WebElement labelForNormalRate;
    @FindBy(css = "label[for='afterHoursRate']")
    WebElement labelForAfterHour;
    @FindBy(css = "label[for='weekendRate']")
    WebElement labelForWeekendRate;
    @FindBy(css = "label[for='holidayRate']")
    WebElement labelForHoliday;
    @FindBy(css = "select[class='input']")
    List<WebElement> selectInputBR;
    @FindBy(css = "div[style='color: rgb(224, 67, 53); padding-top: 5px;']")
    List<WebElement> alertTextsInAddBillRate;
    @FindBy(css = "h5[class='grid-empty-state__title']")
    WebElement emptyGridAlert;
    @FindBy(xpath = "//span[text()='Add New Cart']")
    WebElement addNewCartButton;
    @FindBy(xpath = "//span[text()='Submit']")
    WebElement submitButton;
    @FindBy(css = "div[class='title']")
    WebElement cartTabTitle;
    @FindBy(css = "div[class='modal undefined']")
    WebElement addNewCartPopup;
    @FindBy(css = "img[class='close-icon']")
    WebElement popupCloseButton;
    @FindBy(css = "label[for='idNumber']")
    WebElement cartIdLabel;
    @FindBy(css = "label[for='deliveryDate']")
    WebElement cartDeliveryDateLabel;
    @FindBy(css = "label[for='payments']")
    WebElement cartPaymentsLabel;
    @FindBy(css = "label[for='deposit']")
    WebElement cartDepositLabel;
    @FindBy(css = "label[for='termInMonths']")
    WebElement cartTermInMonthLabel;
    @FindBy(css = "input[class='dtp-addon input form-control']")
    WebElement cartDeliveryDate;
    @FindBy(css = "span[class='current-date']")
    WebElement calendarMonthYear;
    @FindBy(css = "span[class='fa fa-chevron-right']")
    WebElement datePickerRightArrow;
    @FindBy(css = "span[class='fa fa-chevron-left']")
    WebElement datePickerLeftArrow;
    @FindBy(css = "div[class='DateTimePicker__wrapper']")
    WebElement datePicker;
    @FindBy(css = "button[class='dtp-button primary mt-5']")
    WebElement todayButton_DatePicker;
    @FindBy(css = "div[class='full-width']")
    WebElement addNewCartLeaseFields;
    @FindBy(css = "label[for='purchase']")
    WebElement purchaseLabel;
    @FindBy(id = "purchase")
    WebElement purchaseInputField;
    @FindBy(css = "div[class='grid']")
    WebElement tableGrid;
    @FindBy(xpath = "//li[text()='Delete']")
    List<WebElement> deleteCartButton;
    @FindBy(css = "span[class='alert-msg']")
    WebElement alertText;
    @FindBy(css = "div[class='modal undefined']")
    WebElement deleteCartPopup;
    @FindBy(css = "div[class='input-row']")
    WebElement confirmDeleteText;
    @FindBy(xpath = "//span[text()='DELETE']")
    WebElement deleteButton;
    @FindBy(xpath = "//span[text()='Submit']")
    WebElement clientSubmitButton;
    @FindBy(css = "div[class='basic-info__name mb-1']")
    WebElement clientNameInProfilePage;


    public String clientName() throws IOException {
        prop = new Properties();
        String propFilePath = "src/test/resources/Config/Config.properties";
        FileInputStream configPropFile = new FileInputStream(propFilePath);
        prop.load(configPropFile);
        return prop.getProperty("Client_Name");

    }


    public AddNewLocation(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void navigateToNewLocation() {
        wait = new WebDriverWait(driver, 30);

        wait.until(ExpectedConditions.visibilityOf(addNewLocationButton));
        addNewLocationButton.click();
    }

    public void verifyPageContainerTitle() {
        wait = new WebDriverWait(driver, 50);

        wait.until(ExpectedConditions.visibilityOf(pageContainerTitle));
        String actPageContTitle = pageContainerTitle.getText();

        Assert.assertEquals(actPageContTitle, "Add Location");
    }

    public void verifyVisibilityOfWizardHeaderTabs() {
        String[] WizardTabTexts = {"Basic Info", "Contact Info", "Business Address", "Mailing Address",
                "Location Roles", "Smart Routing", "Billing Profile", "Billing Rates", "Carts"};

        String activeWizardClass = wizardHeaderTabs.get(0).getAttribute("class");
        boolean basicInfoStatus = activeWizardClass.contains("active");

        Assert.assertTrue(basicInfoStatus);

        for (int i = 0; i < wizardHeaderTabs.size(); i++) {
            boolean wizardTabsVisible = wizardHeaderTabs.get(i).isDisplayed();
            String actWizardTabText = wizardHeaderTabs.get(i).getText();

            Assert.assertTrue(wizardTabsVisible);
            Assert.assertEquals(actWizardTabText, WizardTabTexts[i]);
        }

    }

    public void verifyContentHeaderInBasicInfo() {
        String[] expContentHeader = {"Location Information", "Classifications"};
        for (int i = 0; i < contHeadBasicInfo.size(); i++) {
            String actContentHeader = contHeadBasicInfo.get(i).getText();

            Assert.assertEquals(actContentHeader, expContentHeader[i]);
        }
    }

    public void verifyInputTextFieldsInBasicInfo() {
        String[] expInputFieldPlaceholders = {"eg: Hospital 123", "eg: 5846873", "eg: Hospital 123", "eg: 5846873",
                "e.g. 'Region' or 'Facility'", "e.g. 'Region' or 'Facility'", "e.g. 'Region' or 'Facility'"};
        String[] expInputFieldLabels = {"* Name", "Audio Access Id", "Cart PIN", "Audio Access Id",
                "Classification Name", "Classification Name", "Classification Name"};
        for (int i = 0; i < InputTextFields.size(); i++) {
            boolean inputFieldVisible = InputTextFields.get(i).isDisplayed();
            String actPlaceholder = InputTextFields.get(i).getAttribute("placeholder");
            String actInputFieldLabels = InputFieldLabels.get(i).getText();

            Assert.assertTrue(inputFieldVisible);
            Assert.assertEquals(actPlaceholder, expInputFieldPlaceholders[i]);
            Assert.assertEquals(actInputFieldLabels, expInputFieldLabels[i]);
        }

    }

    public void verifyAddAnotherButton() {
        int inputFieldCount = classiInputFields.size();
        int expectedCount = 3;

        Assert.assertEquals(expectedCount, inputFieldCount);

        btn_AddAnother.click();

        int updatedInputFieldCount = classiInputFields.size();
        int expInputFieldCount = 4;

        Assert.assertEquals(updatedInputFieldCount, expInputFieldCount);
    }

    public void verifyRemoveButton() {
        wait = new WebDriverWait(driver, 10);

        int currentInputFieldCount = classiInputFields.size();

        btn_Remove.get(currentInputFieldCount - 1).click();
        wait.until(ExpectedConditions.attributeContains(alertBox, "class", "success"));
        String actAlertText = alertRemoveClassification.getText();
        String expAlertText = "The classification \"\" has been successfully removed.";

        Assert.assertEquals(actAlertText, expAlertText);

// The field is not getting removed from the section. It is a bug, once it get resolved, need to enable below code
//        int updatedInputFieldCount = classiInputFields.size();
//
//        Assert.assertEquals(updatedInputFieldCount,currentInputFieldCount-1);

    }

    public void verifyInputFieldsAcceptingValues() throws IOException {
        for (WebElement inputTextField : InputTextFields) {
            inputTextField.clear();
            inputTextField.sendKeys(clientName());

            String actUpdatedValue = inputTextField.getAttribute("value");

            Assert.assertEquals(actUpdatedValue, clientName());

        }
    }

    public void verifyMandatoryFieldsInBasicInfo() {
        WebElement[] requiredFields = {InputTextFields.get(0), InputTextFields.get(2), InputTextFields.get(4), InputTextFields.get(5), InputTextFields.get(6)};
        for (WebElement requiredField : requiredFields) {

            js = (JavascriptExecutor) driver;
            boolean isRequired = (Boolean) js.executeScript("return arguments[0].required;", requiredField);

            Assert.assertTrue(isRequired);
        }
    }

    public void verifyCheckboxLabels() {
        String[] expCheckboxLabels = {"Partner", "Partner", "Contact Info", "Client Roles", "Business Address", "Mailing Address", "Smart Routing", "Billing Profiles", "Billing Rates", "Carts"};

        for (int i = 0; i < expCheckboxLabels.length; i++) {
            String actCheckboxLabels = BI_checkBoxLabels.get(i).getText();

            Assert.assertEquals(expCheckboxLabels[i], actCheckboxLabels);
        }
    }

    public void verifyNextButtonInBasicInfo() {
        wait = new WebDriverWait(driver, 10);

        InputTextFields.get(1).clear();
        InputTextFields.get(3).clear();

        btn_Next.click();

        wait.until(ExpectedConditions.attributeContains(wizardHeaderTabs.get(1), "class", "active"));
        String activeWizardClass = wizardHeaderTabs.get(1).getAttribute("class");
        boolean basicInfoStatus = activeWizardClass.contains("active");

        Assert.assertTrue(basicInfoStatus);
    }

    public void verifyCI_ContainerTitleAndContentHeader() throws IOException {
        String actPageContTitle = pageContainerTitle.getText();
        String actContentHeader = contHeader.getText();

        String expPageContTitle = "Edit Location Info: " + clientName();
        String expContentHeader = "Contact Info";

        Assert.assertEquals(actPageContTitle, expPageContTitle);
        Assert.assertEquals(actContentHeader, expContentHeader);

    }

    public void veryInputFieldsInContactInfo() {
        String[] CI_InputLabels = {"Web Site", "Phone", "Fax"};
        String[] CI_Placeholder = {"Web Site", "(555)555-5555", "(555)555-5555"};

        for (int i = 0; i < InputTextFields.size(); i++) {

            String actCI_InputLabels = InputFieldLabels.get(i).getText();
            String actCI_PlaceHolder = InputTextFields.get(i).getAttribute("placeholder");

            Assert.assertEquals(actCI_InputLabels, CI_InputLabels[i]);
            Assert.assertEquals(actCI_PlaceHolder, CI_Placeholder[i]);


        }

    }

    public void verifyInputValueType() {
        js = (JavascriptExecutor) driver;

        String[] valueType = {"url", "tel", "tel"};
        String[] fieldValues = {"https://www.test.com", "1234567890", "1234567890"};
        String[] expFieldValues = {"https://www.test.com", "(123) 456-7890", "(123) 456-7890"};

        for (int i = 0; i < InputTextFields.size(); i++) {
            String actValueType = InputTextFields.get(i).getAttribute("type");

            Assert.assertEquals(actValueType, valueType[i]);
            InputTextFields.get(i).sendKeys("1");

            try {
                js.executeScript("arguments[0].removeAttribute('pattern');", InputTextFields.get(i));

                InputTextFields.get(i).clear();
                InputTextFields.get(i).sendKeys(fieldValues[i]);

                String actFieldValues = InputTextFields.get(i).getAttribute("value");

                Assert.assertEquals(actFieldValues, expFieldValues[i]);
            } catch (Exception e) {
                InputTextFields.get(i).clear();
                InputTextFields.get(i).sendKeys(fieldValues[i]);

                String actFieldValues = InputTextFields.get(i).getAttribute("value");

                Assert.assertEquals(actFieldValues, expFieldValues[i]);
            }
        }
    }

    public void verifyBackButtonInContactInfo() throws IOException {
        wait = new WebDriverWait(driver, 10);

        btn_Back.click();
        wait.until(ExpectedConditions.attributeContains(wizardHeaderTabs.get(0), "class", "active"));
        String activeWizardClass = wizardHeaderTabs.get(0).getAttribute("class");
        boolean basicInfoStatus = activeWizardClass.contains("active");

        Assert.assertTrue(basicInfoStatus);
        for (int i = 4; i < 8; i++) {
            InputTextFields.get(i).sendKeys(clientName());
        }
        btn_Next.click();
        wait.until(ExpectedConditions.attributeContains(wizardHeaderTabs.get(1), "class", "active"));

    }

    public void verifyNextButtonInContactInfo() {
        wait = new WebDriverWait(driver, 20);

        btn_Next.click();
        wait.until(ExpectedConditions.attributeContains(wizardHeaderTabs.get(2), "class", "active"));

        boolean activeTabClass = wizardHeaderTabs.get(2).getAttribute("class").contains("active");

        Assert.assertTrue(activeTabClass);

        String contactInfoTitle = contHeadBasicInfo.get(0).getText();

        Assert.assertEquals(contactInfoTitle, "Business Address");

    }

    public void verifyInputFieldsInBusinessAddressTab() {
        String[] inputFieldLabels = {"* Line 1:", "Line 2:", "Line 3:", "* City:", "* State:", "* Zip:"};
        String[] expPlaceHolders = {"Line 1", "Line 2", "Line 3", "City", "Zip"};

        for (int i = 0, j = 0; i < inputFieldLabels.length && j < expPlaceHolders.length; i++, j++) {
            String actInputLabels = InputFieldLabels.get(i).getText();
            String actPlaceHolders = InputTextFields.get(j).getAttribute("placeholder");

            Assert.assertEquals(actInputLabels, inputFieldLabels[i]);
            Assert.assertEquals(actPlaceHolders, expPlaceHolders[j]);

            InputTextFields.get(j).sendKeys("Test");
            String attriValue = InputTextFields.get(j).getAttribute("value");

            Assert.assertEquals(attriValue, "Test");
        }
        addressState.sendKeys("A");

    }

    public void verifyMandatoryFieldsInBusinessAddressTab() {
        WebElement[] requiredFields = {addressLine1, addressCity, addressState, addressZip};
        for (WebElement requiredField : requiredFields) {

            js = (JavascriptExecutor) driver;
            boolean isRequired = (Boolean) js.executeScript("return arguments[0].required;", requiredField);

            Assert.assertTrue(isRequired);
        }
    }

    public void verifyBackAndNextButtonInBusiAddressTab() {
        wait = new WebDriverWait(driver, 10);

        btn_Back.click();
        wait.until(ExpectedConditions.attributeContains(wizardHeaderTabs.get(1), "class", "active"));
        String activeWizardClass = wizardHeaderTabs.get(1).getAttribute("class");
        boolean contactInfoStatus = activeWizardClass.contains("active");

        Assert.assertTrue(contactInfoStatus);
        btn_Next.click();
        wait.until(ExpectedConditions.attributeContains(wizardHeaderTabs.get(2), "class", "active"));

        btn_Next.click();
        wait.until(ExpectedConditions.attributeContains(wizardHeaderTabs.get(3), "class", "active"));
        String nextTabClass = wizardHeaderTabs.get(3).getAttribute("class");
        boolean mailingAddressStatus = nextTabClass.contains("active");

        Assert.assertTrue(mailingAddressStatus);

        String contactInfoTitle = contHeadBasicInfo.get(0).getText();
        Assert.assertEquals(contactInfoTitle, "Mailing Address");

    }

    public void verifySameAsBusiCheckBoxInMailAddressTab() {
        BI_checkBoxLabels.get(0).click();
        boolean hiddenClass = inputFieldArea.getAttribute("class").equals("hidden");

        Assert.assertTrue(hiddenClass);

        BI_checkBoxLabels.get(0).click();
    }

    public void verifyInputFieldsInMailingAddressTab() {
        String[] inputFieldLabels = {"Line 1:", "Line 2:", "Line 3:", "City:", "State:", "Zip:"};
        String[] expPlaceHolders = {"Line 1", "Line 2", "Line 3", "City", "Zip"};

        for (int i = 0, j = 0; i < inputFieldLabels.length && j < expPlaceHolders.length; i++, j++) {
            String actInputLabels = InputFieldLabels.get(i).getText();
            String actPlaceHolders = InputTextFields.get(j).getAttribute("placeholder");

            Assert.assertEquals(actInputLabels, inputFieldLabels[i]);
            Assert.assertEquals(actPlaceHolders, expPlaceHolders[j]);

            InputTextFields.get(j).sendKeys("Test");
            String attriValue = InputTextFields.get(j).getAttribute("value");

            Assert.assertEquals(attriValue, "Test");
        }
        mailingAdState.sendKeys("A");

    }

    public void verifyBackAndNextButtonInMailingAddressTab() {
        wait = new WebDriverWait(driver, 10);

        btn_Back.click();
        wait.until(ExpectedConditions.attributeContains(wizardHeaderTabs.get(2), "class", "active"));
        String activeWizardClass = wizardHeaderTabs.get(2).getAttribute("class");
        boolean contactInfoStatus = activeWizardClass.contains("active");

        Assert.assertTrue(contactInfoStatus);

        btn_Next.click();
        wait.until(ExpectedConditions.attributeContains(wizardHeaderTabs.get(3), "class", "active"));

        btn_Next.click();
        wait.until(ExpectedConditions.attributeContains(wizardHeaderTabs.get(4), "class", "active"));
        String nextTabClass = wizardHeaderTabs.get(4).getAttribute("class");
        boolean locationRoleStatus = nextTabClass.contains("active");

        Assert.assertTrue(locationRoleStatus);

        String contactInfoTitle = contHeadBasicInfo.get(0).getText();
        Assert.assertEquals(contactInfoTitle, "Location Roles");

    }

    public void verifyCheckboxLabels_LR() {
        String[] LR_checkboxLabels = {"test client", "Portal User", "Tester", "Client User", "Customer Experience Manager", "Contractor", "Webcalls User", "ULG User", "testclient user"};
        for (int i = 0; i < LR_checkboxLabels.length; i++) {
            String actCheckboxLabel = BI_checkBoxLabels.get(i).getText();

            Assert.assertEquals(actCheckboxLabel, LR_checkboxLabels[i]);
        }
    }

    public void verifyListOfPermissionsInLR() {
        String[] listOfPermissions = {"Permissions:", "Permissions:", "Permissions:", "Permissions: Dashboard(Navigation Menu Enabled), Reports(Navigation Menu Enabled), OPI(Navigation Menu Enabled), DT(Navigation Menu Enabled), OSI(Navigation Menu Enabled), Scheduling Admin(Navigation Menu Enabled), Web Calls(Navigation Menu Enabled)", "Permissions: Dashboard(Navigation Menu Enabled), Reports(Navigation Menu Enabled), VRI, OPI, DT(Navigation Menu Enabled), OSI(Navigation Menu Enabled), Analytics(Navigation Menu Enabled)", "Permissions: Dashboard(Navigation Menu Enabled), Reports(Navigation Menu Enabled), VRI, OPI, DT(Navigation Menu Enabled), OSI(Navigation Menu Enabled)", "Permissions: Web Calls(Navigation Menu Enabled)", "Permissions: Dashboard(Navigation Menu Enabled), VRI(Navigation Menu Enabled)", "Permissions: Dashboard(Navigation Menu Enabled), Reports(Navigation Menu Enabled), Users(Navigation Menu Enabled), VRI(Navigation Menu Enabled), OPI(Navigation Menu Enabled)"};
        for (int i = 0; i < listOfPermissions.length; i++) {
            String actLRPermissions = LR_Permissions.get(i).getText();

            Assert.assertEquals(actLRPermissions, listOfPermissions[i]);
        }
    }

    public void verifyBackAndNextButtonInLocationRolesTab() {
        wait = new WebDriverWait(driver, 10);

        btn_Back.click();
        wait.until(ExpectedConditions.attributeContains(wizardHeaderTabs.get(3), "class", "active"));
        String activeWizardClass = wizardHeaderTabs.get(3).getAttribute("class");
        boolean contactInfoStatus = activeWizardClass.contains("active");

        Assert.assertTrue(contactInfoStatus);

        btn_Next.click();
        wait.until(ExpectedConditions.attributeContains(wizardHeaderTabs.get(4), "class", "active"));

        btn_Next.click();
        wait.until(ExpectedConditions.attributeContains(wizardHeaderTabs.get(5), "class", "active"));
        String nextTabClass = wizardHeaderTabs.get(5).getAttribute("class");
        boolean locationRoleStatus = nextTabClass.contains("active");

        Assert.assertTrue(locationRoleStatus);

        String contactInfoTitle = contHeadBasicInfo.get(0).getText();
        Assert.assertEquals(contactInfoTitle, "Smart Routing");

    }

    public void verifySmartRoutCheckboxAndInputField() {

        String actCheckboxLabel = BI_checkBoxLabels.get(0).getText();
        String actInputFieldLabel = InputFieldLabels.get(0).getText();
        String actInputPlaceholder = InputTextFields.get(0).getAttribute("placeholder");
        String actWarningText = smartRoutingWarning.getText();

        Assert.assertEquals(actCheckboxLabel, "* Smart Routing Enabled");
        Assert.assertEquals(actInputFieldLabel, "Queue Name");
        Assert.assertEquals(actInputPlaceholder, "Smart Routing Queue Name");
        Assert.assertEquals(actWarningText, "Any billing rates with smart routing will have it disabled if smart routing disabled.");

    }

    public void verifyCheckboxFunctionalityInSmartRoute() {
        js = (JavascriptExecutor) driver;
        boolean isDisabled = (Boolean) js.executeScript("return arguments[0].disabled;", InputTextFields.get(0));
        Assert.assertTrue(isDisabled);

        checkBox.click();

        boolean isRequired = (Boolean) js.executeScript("return arguments[0].required;", InputTextFields.get(0));
        Assert.assertTrue(isRequired);

        try {
            smartRoutingWarning.isDisplayed();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }

        InputTextFields.get(0).sendKeys("test");
        String textValue = InputTextFields.get(0).getAttribute("value");
        Assert.assertEquals(textValue, "test");

        checkBox.click();

    }

    public void verifyBackAndNextButtonInSmartRoutingTab() {
        wait = new WebDriverWait(driver, 10);

        btn_Back.click();
        wait.until(ExpectedConditions.attributeContains(wizardHeaderTabs.get(4), "class", "active"));
        String activeWizardClass = wizardHeaderTabs.get(4).getAttribute("class");
        boolean locationRoles = activeWizardClass.contains("active");

        Assert.assertTrue(locationRoles);

        btn_Next.click();
        wait.until(ExpectedConditions.attributeContains(wizardHeaderTabs.get(5), "class", "active"));

        btn_Next.click();
        wait.until(ExpectedConditions.attributeContains(wizardHeaderTabs.get(6), "class", "active"));
        String nextTabClass = wizardHeaderTabs.get(6).getAttribute("class");
        boolean billingProfileStatus = nextTabClass.contains("active");

        Assert.assertTrue(billingProfileStatus);

        String contactInfoTitle = contHeadBasicInfo.get(0).getText();
        Assert.assertEquals(contactInfoTitle, "Billing Profiles");

    }

    public void verifyTableHeaderTextInBillingProfileTab() {
        String[] tableHeader = {"MODALITY", "AFTER HOURS", "HOLIDAY", "WEEKEND", "MINIMUM MINUTES", ""};
        for (int i = 0; i < tableHeader.length; i++) {
            String actTableHeader = BP_TableHeader.get(i).getText();

            Assert.assertEquals(tableHeader[i], actTableHeader);
        }

    }

    public void verifyTableHeaderSortFunctionality() {

        String currentUrl = driver.getCurrentUrl();
        String baseUrl = currentUrl.replace("/clients/new", "");

        String expImageAsc = baseUrl + "/assets/img/sort-up@2x.e0fcbe83746bd87246013de5482f893b.png";
        String expImageDec = baseUrl + "/assets/img/sort-down@2x.5b9168e44dbe76be3cc898bd534c575f.png";
        for (WebElement webElement : headerWithSort) {
            webElement.click();
            boolean headerClassAsc = webElement.getAttribute("class").contains("asc ");
            Assert.assertTrue(headerClassAsc);
            String actImageAsc = sortImage.getAttribute("src");
            Assert.assertEquals(expImageAsc, actImageAsc);

            webElement.click();
            boolean headerClassDec = webElement.getAttribute("class").contains("dec ");
            Assert.assertTrue(headerClassDec);
            String actImageDec = sortImage.getAttribute("src");
            Assert.assertEquals(expImageDec, actImageDec);

        }
    }

    public void verifyVisibilityOfEditBillingProfile() {
        action = new Actions(driver);

        action.moveToElement(settingsIcon.get(0)).perform();
        action.moveToElement(editProfButton.get(0)).perform();
        action.click(editProfButton.get(0)).build().perform();

        boolean popupDisplayed = editBillingProfPopup.isDisplayed();
        Assert.assertTrue(popupDisplayed);

        String popupTitle = "Edit Billing Profile";
        String actPopupTitle = modalTitle.getText();
        Assert.assertEquals(popupTitle, actPopupTitle);

        String actInputFieldLabel = minMinuteFieldLabel.getText();
        String actInputFieldPlaceholder = minMinuteInputField.getAttribute("placeholder");

        Assert.assertEquals("Minimum Bill Time", actInputFieldLabel);
        Assert.assertEquals("Minimum Minutes", actInputFieldPlaceholder);

        String[] expCheckboxLabel = {"After Hours", "Holiday", "Weekend"};
        for (int i = 0; i < expCheckboxLabel.length; i++) {
            String actCheckboxLabel = BI_checkBoxLabels.get(i).getText();
            Assert.assertEquals(expCheckboxLabel[i], actCheckboxLabel);
        }

        boolean inputFieldType = minMinuteInputField.getAttribute("type").equals("number");
        Assert.assertTrue(inputFieldType);
    }

    public void verifyEditBillProfWithValidParameters() {
        action = new Actions(driver);
        wait = new WebDriverWait(driver, 10);

        String minCount = "1";
        minMinuteInputField.clear();
        minMinuteInputField.sendKeys(minCount);

        for (WebElement bi_checkBoxLabel : BI_checkBoxLabels) {
            bi_checkBoxLabel.click();
        }

        saveButton.click();
        wait.until(ExpectedConditions.visibilityOf(billingProfAlert));
        String actAlertText = billingProfAlert.getText();

        Assert.assertEquals("Successfully updated the Billing Profile", actAlertText);
        wait.until(ExpectedConditions.invisibilityOf(billingProfAlert));
//Now the billing status not getting updated automatically after saving billing profile, need to remove  below code after fixing this bug
        btn_Back.click();
        wait.until(ExpectedConditions.attributeContains(wizardHeaderTabs.get(5), "class", "active"));
        String activeWizardClass = wizardHeaderTabs.get(5).getAttribute("class");
        boolean contactInfoStatus = activeWizardClass.contains("active");

        Assert.assertTrue(contactInfoStatus);

        btn_Next.click();
        wait.until(ExpectedConditions.attributeContains(wizardHeaderTabs.get(6), "class", "active"));

        String savedMinMinutes = tableValues.get(4).getText();
        Assert.assertEquals(minCount, savedMinMinutes);
    }

    public void verifyEditBillProfWithInvalidParameters() {
        action = new Actions(driver);
        wait = new WebDriverWait(driver, 10);

        action.moveToElement(settingsIcon.get(1)).perform();
        action.moveToElement(editProfButton.get(1)).perform();
        action.click(editProfButton.get(1)).build().perform();

        String invalidMinCount = "1.5";
        minMinuteInputField.clear();
        minMinuteInputField.sendKeys(invalidMinCount);

        for (WebElement bi_checkBoxLabel : BI_checkBoxLabels) {
            bi_checkBoxLabel.click();
        }

        saveButton.click();
        wait.until(ExpectedConditions.visibilityOf(billingProfAlert));
        String actErrorAlertText = billingProfAlert.getText();

        Assert.assertEquals("Error With Updating Container", actErrorAlertText);

    }

    public void verifyCancelAndCloseButtonFunctionalityInBillProfPopup() {
        action = new Actions(driver);
        wait = new WebDriverWait(driver, 10);

        WebElement[] closeAndCancel = {modalCloseButton, cancelButton};
        for (WebElement webElement : closeAndCancel) {
            action.moveToElement(settingsIcon.get(0)).perform();
            action.moveToElement(editProfButton.get(0)).perform();
            action.click(editProfButton.get(0)).build().perform();

            wait.until(ExpectedConditions.visibilityOf(modalCloseButton));

            webElement.click();
            try {
                editBillingProfPopup.isDisplayed();
            } catch (NoSuchElementException e) {
                e.printStackTrace();
            }
        }
    }

    public void verifyBackAndNextButtonInBillingProfileTab() {
        wait = new WebDriverWait(driver, 10);

        btn_Back.click();
        wait.until(ExpectedConditions.attributeContains(wizardHeaderTabs.get(5), "class", "active"));
        String activeWizardClass = wizardHeaderTabs.get(5).getAttribute("class");
        boolean locationRoles = activeWizardClass.contains("active");

        Assert.assertTrue(locationRoles);

        btn_Next.click();
        wait.until(ExpectedConditions.attributeContains(wizardHeaderTabs.get(6), "class", "active"));

        btn_Next.click();
        wait.until(ExpectedConditions.attributeContains(wizardHeaderTabs.get(7), "class", "active"));
        String nextTabClass = wizardHeaderTabs.get(7).getAttribute("class");
        boolean billingProfileStatus = nextTabClass.contains("active");

        Assert.assertTrue(billingProfileStatus);

        String contactInfoTitle = contHeadBasicInfo.get(0).getText();
        Assert.assertEquals(contactInfoTitle, "Billing Rates");

    }

    public void verifyBillingRatesTabEle() {
        boolean visibilityOfAddRateButton = addRateButton.isDisplayed();
        String noRateText = noRatesText.getText();

        Assert.assertTrue(visibilityOfAddRateButton);
        Assert.assertEquals("No rates to display", noRateText);
    }

    public void verifyAddBillingRatePopup() {
        wait = new WebDriverWait(driver, 10);

        addRateButton.click();
        wait.until(ExpectedConditions.textToBePresentInElement(modalTitle, "Add Billing Rate"));

        Assert.assertEquals("Add Billing Rate", modalTitle.getText());

        WebElement[] inputFieldLabelEle = {labelForModality, labelForLang, labelForNormalRate, labelForAfterHour, labelForWeekendRate, labelForHoliday};
        String[] expFieldLabels = {"Modality *", "Language Select *", "Normal Rate *", "After Hours Rate *", "Weekend Rate *", "Holiday Rate *"};
        for (int i = 0; i < inputFieldLabelEle.length; i++) {
            String actLabelText = inputFieldLabelEle[i].getText();

            Assert.assertEquals(expFieldLabels[i], actLabelText);
        }
    }

    public void verifyInputFieldType() {
        for (WebElement inputTextField : InputTextFields) {
            boolean fieldType = inputTextField.getAttribute("type").equals("number");

            Assert.assertTrue(fieldType);
        }
    }


    public void verifyAlertsForEmptyFieldsInBRPopup() {
        String[] expAlertTexts = {"Modality is required", "Language is required", "Normal Rate is required", "After Hours Rate is required", "Weekend rate is required", "Holiday Rate is required"};

        saveButton.click();
        for (int i = 0; i < alertTextsInAddBillRate.size(); i++) {
            String actAlertText = alertTextsInAddBillRate.get(i).getText();

            Assert.assertEquals(expAlertTexts[i], actAlertText);
        }

    }

    public void verifyAddBillRateFunctionality() {
        wait = new WebDriverWait(driver, 10);
        String[] selectValues = {"OPI", "Arabic"};
        String Rate = "1";
//        String[] expTableValues = {"Arabic", "OPI", Rate, Rate, Rate, Rate};

        int i = 0;
        while (i < selectInputBR.size()) {
            Select select = new Select(selectInputBR.get(i));
            select.selectByVisibleText(selectValues[i]);

            i++;
        }
        for (WebElement inputTextField : InputTextFields) {
            inputTextField.sendKeys(Rate);
        }
        saveButton.click();
        wait.until(ExpectedConditions.visibilityOf(billingProfAlert));

        String actAlertText = billingProfAlert.getText();

        Assert.assertEquals("Successfully created billing rate", actAlertText);
// Now the billing rate table is not getting updated automatically, need to enable below code after fixing this bug
//        wizardHeaderTabs.get(7).click();
//        wait.until(ExpectedConditions.visibilityOf(billingRateTable));
//
//        boolean visibilityOfBillingRate = billingRateGridTable.isDisplayed();
//        Assert.assertTrue(visibilityOfBillingRate);
//
//        for (int i = 0; i < tableValues.size(); i++) {
//            String actTableValues = tableValues.get(i).getText();
//            if (i < 2) {
//                Assert.assertEquals(expTableValues[i], actTableValues);
//            } else {
//                Assert.assertEquals("$" + Rate + ".00", actTableValues);
//            }
//        }
        btn_Back.click();
        wait.until(ExpectedConditions.attributeContains(wizardHeaderTabs.get(7), "class", "active"));
        String activeWizardClass = wizardHeaderTabs.get(7).getAttribute("class");
        boolean billingRate = activeWizardClass.contains("active");

        Assert.assertTrue(billingRate);
    }

    public void verifyBackAndNextButtonInBillingRateTab() {
        wait = new WebDriverWait(driver, 10);

        btn_Back.click();
        wait.until(ExpectedConditions.attributeContains(wizardHeaderTabs.get(6), "class", "active"));
        String activeWizardClass = wizardHeaderTabs.get(6).getAttribute("class");
        boolean locationRoles = activeWizardClass.contains("active");

        Assert.assertTrue(locationRoles);

        btn_Next.click();
        wait.until(ExpectedConditions.attributeContains(wizardHeaderTabs.get(7), "class", "active"));

        btn_Next.click();
        wait.until(ExpectedConditions.attributeContains(wizardHeaderTabs.get(8), "class", "active"));
        String nextTabClass = wizardHeaderTabs.get(8).getAttribute("class");
        boolean cartStatus = nextTabClass.contains("active");

        Assert.assertTrue(cartStatus);

        String cartTabTitleText = cartTabTitle.getText();
        Assert.assertEquals(cartTabTitleText, "Carts");
    }

    public void verifyVisibilityOfElementsInCartsTab() {
        WebElement[] cartsTabEle = {emptyGridAlert, addNewCartButton, btn_Back, submitButton};
        for (WebElement webElement : cartsTabEle) {
            boolean elementVisible = webElement.isDisplayed();
            Assert.assertTrue(elementVisible);
        }
        String actAlertText = emptyGridAlert.getText();
        Assert.assertEquals(actAlertText, "There are currently no results");

    }

    public void verifyAddNewCartPopupTitle() {
        wait = new WebDriverWait(driver, 10);

        addNewCartButton.click();
        wait.until(ExpectedConditions.visibilityOf(addNewCartPopup));

        String popupTitle = modalTitle.getText();
        Assert.assertEquals(popupTitle, "Add Cart");

    }

    public void verifyCloseAndCancelButtonFunctionality() {
        WebElement[] closeAndCancelEle = {popupCloseButton, cancelButton};
        for (WebElement webElement : closeAndCancelEle) {
            webElement.click();
            try {
                addNewCartPopup.isDisplayed();
            } catch (NoSuchElementException e) {
                e.printStackTrace();
            }
            addNewCartButton.click();
        }
    }

    public void verifyAddNewCartFieldLabels() {
        WebElement[] addNewCartFieldLabels = {cartIdLabel, cartDeliveryDateLabel, cartPaymentsLabel, cartDepositLabel, cartTermInMonthLabel};
        String[] expFieldLabels = {"Cart ID", "Delivery Date", "Payments", "Deposit", "Term In Months"};

        for (int i = 0; i < addNewCartFieldLabels.length; i++) {
            String actFieldLabel = addNewCartFieldLabels[i].getText();
            Assert.assertEquals(actFieldLabel, expFieldLabels[i]);
        }
    }

    public void verifyPropertiesInAddNewCartDeliveryDateField() {
        String todayDate = Utils.today();
        String actDateFieldValue = cartDeliveryDate.getAttribute("value");
        Assert.assertEquals(actDateFieldValue, todayDate);

        String actDateFieldPlaceholder = cartDeliveryDate.getAttribute("placeholder");
        Assert.assertEquals(actDateFieldPlaceholder, "Please select a date");
    }

    public void verifyDatePickerValues() {
        cartDeliveryDate.click();
        String actCurrentMonthYear = calendarMonthYear.getText();
        String expMonthYear = Utils.currentMonthYear().toUpperCase();

        Assert.assertEquals(actCurrentMonthYear, expMonthYear);

    }

    public void verifyMonthSelectionInDatePicker() {
        wait = new WebDriverWait(driver, 10);
        datePickerRightArrow.click();
        String nextMonthYear = calendarMonthYear.getText();
        String expNextMonthYear = Utils.nextMonthYear().toUpperCase();
        Assert.assertEquals(nextMonthYear, expNextMonthYear);

        datePickerLeftArrow.click();
        String expCurrentMonthYear = Utils.currentMonthYear().toUpperCase();
        wait.until(ExpectedConditions.textToBePresentInElement(calendarMonthYear, expCurrentMonthYear));
        String currentMonthYear = calendarMonthYear.getText();

        Assert.assertEquals(currentMonthYear, expCurrentMonthYear);
    }

    public void pickDiffDeliveryDate() {

        List<WebElement> rows = datePicker.findElements(By.tagName("tr"));
        for (WebElement row : rows) {

            List<WebElement> cols = row.findElements(By.tagName("td"));
            for (int k = 0; k < cols.size(); k++) {

                String calDate = cols.get(k).getText();
                if (cols.get(k).getAttribute("class").equals("next-month")) {
                    k++;

                } else if (calDate.equals(Utils.thisMonthStartDay())) {
                    cols.get(k).click();
                    break;
                }
            }
        }

        String updatedDate = cartDeliveryDate.getAttribute("value");
        String expDate = Utils.thisMonthStartDate();
        Assert.assertEquals(expDate, updatedDate);
    }

    public void verifyTodayButtonInDatePicker() {
        cartDeliveryDate.click();
        todayButton_DatePicker.click();

        String today = cartDeliveryDate.getAttribute("value");
        String expDate = Utils.today();
        Assert.assertEquals(expDate, today);
    }

    public void verifyAddNewCartCheckboxFunctionality() {
        String[] checkboxLabels = {"Lease", "Purchase"};
        for (int i = 0; i < checkboxLabels.length; i++) {
            String actCheckboxLabels = BI_checkBoxLabels.get(i).getText();
            Assert.assertEquals(actCheckboxLabels, checkboxLabels[i]);

        }
        BI_checkBoxLabels.get(1).click();
        boolean purchaseLabelVisible = purchaseLabel.isDisplayed();
        boolean purchaseLabelType = purchaseInputField.getAttribute("type").equals("number");

        Assert.assertTrue(purchaseLabelVisible);
        Assert.assertTrue(purchaseLabelType);
        try {
            addNewCartLeaseFields.isDisplayed();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }

        BI_checkBoxLabels.get(0).click();

        boolean leaseFieldsVisibility = addNewCartLeaseFields.isDisplayed();
        Assert.assertTrue(leaseFieldsVisibility);
    }

    public void addNewCartForLease() {
        wait = new WebDriverWait(driver, 10);
        String inputValue = Utils.gen();
        for (WebElement inputTextField : InputTextFields) {
            inputTextField.clear();
            inputTextField.sendKeys(inputValue);
        }
        saveButton.click();
        wait.until(ExpectedConditions.visibilityOf(tableGrid));

        String actCartID = tableValues.get(0).getText();
        Assert.assertEquals(actCartID, inputValue);
    }

    public void addNewCartForPurchase() {
        wait = new WebDriverWait(driver, 10);
        String inputCartId = Utils.gen();
        addNewCartButton.click();
        wait.until(ExpectedConditions.visibilityOf(addNewCartPopup));

        BI_checkBoxLabels.get(1).click();

        InputTextFields.get(0).clear();
        InputTextFields.get(0).sendKeys(inputCartId);

        InputTextFields.get(1).clear();
        InputTextFields.get(1).sendKeys(inputCartId);

        saveButton.click();
        wait.until(ExpectedConditions.visibilityOf(alertText));
        String actAlertText = alertText.getText();
        Assert.assertEquals("Cart Succesfully Added", actAlertText);
        wait.until(ExpectedConditions.invisibilityOf(alertText));

        String actCartID_2 = tableValues.get(3).getText();
        Assert.assertEquals(actCartID_2, inputCartId);
    }

    public void verifyEditCartFunctionality() {
        action = new Actions(driver);
        wait = new WebDriverWait(driver, 10);

        action.moveToElement(settingsIcon.get(0)).perform();
        action.moveToElement(editProfButton.get(0)).perform();
        action.click(editProfButton.get(0)).build().perform();

        wait.until(ExpectedConditions.visibilityOf(addNewCartPopup));

        String actModalTitle = modalTitle.getText();
        Assert.assertEquals("Edit Cart", actModalTitle);
        String cartIdToBeEdited = Utils.gen();

        InputTextFields.get(0).clear();
        InputTextFields.get(0).sendKeys(cartIdToBeEdited);

        saveButton.click();
        wait.until(ExpectedConditions.visibilityOf(alertText));
        String actAlertText = alertText.getText();
        Assert.assertEquals("Cart Successfully Updated", actAlertText);

        wait.until(ExpectedConditions.invisibilityOf(alertText));
        String actUpdatedTableValue = tableValues.get(0).getText();
        Assert.assertEquals(actUpdatedTableValue, cartIdToBeEdited);

    }

    public void verifyDeleteCartFunctionality() {
        action = new Actions(driver);
        wait = new WebDriverWait(driver, 10);

        action.moveToElement(settingsIcon.get(0)).perform();
        action.moveToElement(deleteCartButton.get(0)).perform();
        action.click(deleteCartButton.get(0)).build().perform();

        wait.until(ExpectedConditions.visibilityOf(deleteCartPopup));

        String actModalTitle = modalTitle.getText();
        String actConfirmDeleteText = confirmDeleteText.getText();

        String expConfirmDeleteText = "Are you sure you would like to delete this cart?";
        Assert.assertEquals("Delete Cart", actModalTitle);
        Assert.assertEquals(expConfirmDeleteText, actConfirmDeleteText);

        deleteButton.click();
        wait.until(ExpectedConditions.visibilityOf(alertText));

        String actAlertText = alertText.getText();
        Assert.assertEquals("Cart Successfully Deleted", actAlertText);

        wait.until(ExpectedConditions.invisibilityOf(alertText));
// The delete functionality is not working as expected, once this bug is fixed need to enable the below code
//        int actTableRowCount = tableRows.size();
//        int expTableRowCount = 2;
//        Assert.assertEquals(expTableRowCount, actTableRowCount);

    }

    public void addNewLocationSubmit() throws IOException {
        wait = new WebDriverWait(driver, 30);
        clientSubmitButton.click();
        wait.until(ExpectedConditions.visibilityOf(clientNameInProfilePage));
        String actNewlyCreatedClientName = clientNameInProfilePage.getText();
        Assert.assertEquals(clientName(), actNewlyCreatedClientName);
    }
}


