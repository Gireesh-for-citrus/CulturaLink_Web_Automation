package PageFactory;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class AddNewUser {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    Actions action;

    public AddNewUser(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[text()='Add New User']")
    WebElement addNewUserButton;
    @FindBy(css = "div[class='page-container__title']")
    WebElement usersPageHeader;
    @FindBy(css = "div[class='title']")
    List<WebElement> ClUserTitle;
    @FindBy(how = How.CSS, using = "i.fa")
    List<WebElement> expandCollapseIcon;
    @FindBy(how = How.CSS, using = "div.collapse")
    List<WebElement> inputFieldContainers;
    @FindBy(css = "span[class='checkbox-label']")
    List<WebElement> checkboxLabelEle;
    @FindBy(css = "label[class='label']")
    List<WebElement> inputFieldLabels;
    @FindBy(css = "input[class='input']")
    List<WebElement> inputFields;
    @FindBy(css = "select[class='input']")
    List<WebElement> stateSelect;
    @FindBy(xpath = "//*[@id=\"main\"]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div/div/form/div[4]/div[2]/fieldset/div[2]")
    WebElement mailingAddressContainers;
    @FindBy(css = "div[class='Select-placeholder']")
    WebElement selectRole;
    @FindBy(xpath = "//div[text()='Administrator']")
    WebElement roleAdmin;
    @FindBy(css = "span[class='Select-value-label']")
    WebElement selectedRoleLabel;
    @FindBy(css = "span[class='Select-clear']")
    WebElement roleClearIcon;
    @FindBy(xpath = "//span[text()='Add Role']")
    WebElement addRoleButton;
    @FindBy(xpath = "//span[text()='Remove']")
    WebElement removeRoleButton;
    @FindBy(css = "table[class='table table-striped']")
    WebElement permissionTable;
    @FindBy(how = How.CSS, using = "div.Select-option")
    List<WebElement> roleDropdownList;
    @FindBy(xpath = "//th")
    List<WebElement> permissionTableHeader;
    @FindBy(xpath = "//td")
    List<WebElement> permissionTableValues;
    @FindBy(css = "span[class='checkbox no-pad']")
    List<WebElement> permissionCheckbox;


    public void redirectToAddNewUserPage() {
        wait = new WebDriverWait(driver, 50);

        String currentUrl = driver.getCurrentUrl();
        String baseUrl = currentUrl.replace("/users", "");

        addNewUserButton.click();
        wait.until(ExpectedConditions.textToBePresentInElement(usersPageHeader, "New User"));

        String currentURL = driver.getCurrentUrl();
        String expUrl = baseUrl + "/users/new";

        String actualTitle = driver.getTitle();

        Assert.assertEquals(currentURL, expUrl);
        Assert.assertEquals(actualTitle, "CLIQ - Users Add");
    }

    public void verifyTitlesOfClUser() {
        String[] ClUserTitles = {"User Association", "User Information", "Contact Information", "Home Address", "Mailing Address", "Roles"};
        for (int i = 0; i < ClUserTitle.size(); i++) {
            String actClUserTitle = ClUserTitle.get(i).getText();
            Assert.assertEquals(actClUserTitle, ClUserTitles[i]);
        }
    }

    public void verifyTitlesOfLocationUser() {
        String[] LocationUserTitles = {"User Association", "User Information", "Contact Information", "Home Address", "Mailing Address", "Linked Locations"};
        for (int i = 0; i < ClUserTitle.size(); i++) {
            String actLocationUserTitle = ClUserTitle.get(i).getText();
            Assert.assertEquals(actLocationUserTitle, LocationUserTitles[i]);
        }
    }

    public void verifyExpandCollapseFunctionality() throws InterruptedException {
        wait = new WebDriverWait(driver, 20);

        for (int i = 0; i < expandCollapseIcon.size(); i++) {
            String currentExpCollapseIconStat = expandCollapseIcon.get(i).getAttribute("class");
            if (i < 2) {
                Assert.assertEquals(currentExpCollapseIconStat, "fa fa-angle-down");

                String currentInputFieldContStat = inputFieldContainers.get(i).getAttribute("class");
                Assert.assertEquals(currentInputFieldContStat, "collapse show");

                expandCollapseIcon.get(i).click();
                //Explicit is not working in this cases, it is taking some time to change the class attribute to 'collapse' from 'collapse show'
                Thread.sleep(1000);

                String iconStatAfterClick = expandCollapseIcon.get(i).getAttribute("class");
                Assert.assertEquals(iconStatAfterClick, "fa fa-angle-right");

                String containerStatAfterClick = inputFieldContainers.get(i).getAttribute("class");
                Assert.assertEquals(containerStatAfterClick, "collapse");

            } else {
                Assert.assertEquals(currentExpCollapseIconStat, "fa fa-angle-right");

                String currentInputFieldContStat = inputFieldContainers.get(i).getAttribute("class");
                Assert.assertEquals(currentInputFieldContStat, "collapse");

                expandCollapseIcon.get(i).click();
                //wait.until(ExpectedConditions.attributeContains(inputFieldContainers.get(i),"class","show"));
                Thread.sleep(1000);

                String iconStatAfterClick = expandCollapseIcon.get(i).getAttribute("class");
                Assert.assertEquals(iconStatAfterClick, "fa fa-angle-down");

                String containerStatAfterClick = inputFieldContainers.get(i).getAttribute("class");
                Assert.assertEquals(containerStatAfterClick, "collapse show");

            }
            expandCollapseIcon.get(i).click();
            Thread.sleep(1000);
        }
    }

    public void verifyCheckboxLabelInUserAssociation() {
        String[] checkboxLabels = {"Culturalink User", "Location User"};
        for (int i = 0; i < 2; i++) {
            String actCheckboxLabel = checkboxLabelEle.get(i).getText();
            Assert.assertEquals(actCheckboxLabel, checkboxLabels[i]);
        }
    }

    public void verifyInputFieldLabelsInUserInfo() {
        String[] userInfoFieldLabelText = {"* First Name", "Middle Name", "* Last Name", "ID Number (Interpreter)"};
        for (int i = 0; i < userInfoFieldLabelText.length; i++) {
            String actUserInfoLabel = inputFieldLabels.get(i).getText();
            Assert.assertEquals(actUserInfoLabel, userInfoFieldLabelText[i]);
        }
    }

    public void verifyPlaceholderInUserInfo() {
        String[] userInfoPlaceholders = {"First Name", "Middle Initial", "Last Name", "ID Number (Interpreter)"};
        for (int i = 0; i < userInfoPlaceholders.length; i++) {
            String actUserInfoPlaceholder = inputFields.get(i).getAttribute("placeholder");
            Assert.assertEquals(actUserInfoPlaceholder, userInfoPlaceholders[i]);
        }
    }

    public void verifyMandatoryFieldsInUserInfo() {
        WebElement[] mandatoryFieldEle = {inputFields.get(0), inputFields.get(2)};
        for (int i = 0; i < mandatoryFieldEle.length; i++) {
            js = (JavascriptExecutor) driver;
            boolean isRequired = (Boolean) js.executeScript("return arguments[0].required;", mandatoryFieldEle);

            org.junit.Assert.assertTrue(isRequired);
        }
    }

    public void verifyInputFieldsInUserInfo() {
        for (int i = 0; i < 4; i++) {
            inputFields.get(i).sendKeys("Test");

            String inputFieldValue = inputFields.get(i).getAttribute("value");
            Assert.assertEquals(inputFieldValue, "Test");

        }
    }

    public void verifyFieldLabelsAndPlaceHoldersInContactInfo() {
        String[] fieldLabelTexts = {"* Home:", "Ext:", "Cell:", "Ext:", "Fax:", "Ext:", "Other:", "Ext:", "* Email:"};
        String[] placeHolderTexts = {"(555)555-5555", "123", "(555)555-5555", "123", "(555)555-5555", "123", "(555)555-5555", "123", "example@email.com"};
        ClUserTitle.get(2).click();

        for (int i = 4, j = 0; i < 13 && j < 9; i++, j++) {
            String actFieldLabels = inputFieldLabels.get(i).getText();
            String actPlaceHolderValues = inputFields.get(i).getAttribute("placeholder");
            Assert.assertEquals(actFieldLabels, fieldLabelTexts[j]);

            Assert.assertEquals(actPlaceHolderValues, placeHolderTexts[j]);
        }

    }

    public void verifyMandatoryFieldsInContactInfo() {
        WebElement[] requiredFields = {inputFields.get(4), inputFields.get(12)};
        for (WebElement requiredField : requiredFields) {

            try {
                js = (JavascriptExecutor) driver;
                boolean isRequired = (Boolean) js.executeScript("return arguments[0].required;", requiredField);
                Assert.assertTrue(isRequired);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void verifyInputFieldsFunctionalityInContactInfo() {
        js = (JavascriptExecutor) driver;
        for (int i = 4; i < 13; i++) {
            String fieldType = inputFields.get(i).getAttribute("type");
            if (fieldType.equals("tel")) {
                inputFields.get(i).sendKeys("1");
                js.executeScript("arguments[0].removeAttribute('pattern','value');", inputFields.get(i));

                inputFields.get(i).clear();
                inputFields.get(i).sendKeys("1234567890");

                String actFieldValues = inputFields.get(i).getAttribute("value");
                Assert.assertEquals(actFieldValues, "(123) 456-7890");

            } else {
                inputFields.get(i).clear();
                inputFields.get(i).sendKeys("1234");

                String actFieldValues = inputFields.get(i).getAttribute("value");
                Assert.assertEquals(actFieldValues, "1234");
            }
        }
    }

    public void verifyMandatoryFieldsInHomeAddress() {
        WebElement[] requiredFields = {inputFields.get(13), inputFields.get(16), stateSelect.get(0), inputFields.get(17)};
        for (WebElement requiredField : requiredFields) {
            js = (JavascriptExecutor) driver;
            boolean isRequired = (Boolean) js.executeScript("return arguments[0].required;", requiredField);
            Assert.assertTrue(isRequired);
        }
    }

    public void verifyInputFieldLabelsInHomeAddress() throws InterruptedException {
        String[] expInputFieldLabel = {"* Line 1:", "Line 2:", "Line 3:", "* City:", "* State:", "* Zip:"};
        ClUserTitle.get(3).click();
        Thread.sleep(1000);
        for (int i = 13, j = 0; i < 19 && j < expInputFieldLabel.length; i++, j++) {
            String actInputLabels = inputFieldLabels.get(i).getText();
            Assert.assertEquals(actInputLabels, expInputFieldLabel[j]);
        }
    }

    public void verifyPlaceholderValuesInHomeAddress() {
        String[] expPlaceHolders = {"Line 1", "Line 2", "Line 3", "City", "Zip"};
        for (int i = 13, j = 0; i < 18 && j < expPlaceHolders.length; i++, j++) {
            String actPlaceHolders = inputFields.get(i).getAttribute("placeholder");
            Assert.assertEquals(actPlaceHolders, expPlaceHolders[j]);
        }
    }

    public void verifyInputFieldFunctionalityInHomeAddress() {
        for (int i = 13; i < 18; i++) {

            inputFields.get(i).sendKeys("Test");
            String attriValue = inputFields.get(i).getAttribute("value");

            Assert.assertEquals(attriValue, "Test");
        }
        stateSelect.get(0).sendKeys("A");
    }

    public void verifyInputFieldLabelsInMailingAddress() throws InterruptedException {
        String[] expInputFieldLabel = {"Line 1:", "Line 2:", "Line 3:", "City:", "State:", "Zip:"};
        ClUserTitle.get(4).click();
        Thread.sleep(1000);
        for (int i = 19, j = 0; i < 25 && j < expInputFieldLabel.length; i++, j++) {
            String actInputLabels = inputFieldLabels.get(i).getText();
            Assert.assertEquals(actInputLabels, expInputFieldLabel[j]);
        }
    }

    public void verifyPlaceholderValuesInMailingAddress() {
        String[] expPlaceHolders = {"Line 1", "Line 2", "Line 3", "City", "Zip"};
        for (int i = 18, j = 0; i < 23 && j < expPlaceHolders.length; i++, j++) {
            String actPlaceHolders = inputFields.get(i).getAttribute("placeholder");
            Assert.assertEquals(actPlaceHolders, expPlaceHolders[j]);
        }
    }

    public void verifyInputFieldFunctionalityInMailingAddress() {
        for (int i = 18; i < 23; i++) {

            inputFields.get(i).sendKeys("Test");
            String attriValue = inputFields.get(i).getAttribute("value");

            Assert.assertEquals(attriValue, "Test");
        }
        stateSelect.get(1).sendKeys("A");
    }

    public void verifySameAsAboveCheckboxFunctionality() {
        String actCheckboxLabel = checkboxLabelEle.get(2).getText();
        Assert.assertEquals(actCheckboxLabel, "Same As Home");

        checkboxLabelEle.get(2).click();
        boolean hiddenInputFields = mailingAddressContainers.getAttribute("class").equals("hidden");
        Assert.assertTrue(hiddenInputFields);

        checkboxLabelEle.get(2).click();
        boolean visibleInputFields = mailingAddressContainers.getAttribute("class").equals("");
        Assert.assertTrue(visibleInputFields);
    }

    public void verifyRoleSelectionDropdownInRolesSection() {
        wait = new WebDriverWait(driver, 10);
        ClUserTitle.get(5).click();
        wait.until(ExpectedConditions.visibilityOf(selectRole));

        String[] dropdownListText = {"Administrator",
                "Interpreter", "Client Administrator-Sales Team",
                "Facility Administrator-Culturalink limited admin access",
                "Translator", "Language Services Coordinator",
                "Schedule Demo",
                "Test Role"};
        for (int i = 0; i < 8; i++) {
            selectRole.click();
            wait.until(ExpectedConditions.visibilityOfAllElements(roleAdmin));
            roleDropdownList.get(i).click();
            String selectedRoleText = selectedRoleLabel.getText();
            Assert.assertEquals(selectedRoleText, dropdownListText[i]);

            roleClearIcon.click();
            wait.until(ExpectedConditions.visibilityOf(selectRole));
        }
    }

    public void verifySelectAdminRoleFieldInRoles() {
        wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.visibilityOf(selectRole));
        String selectRolePlaceholder = selectRole.getText();
        Assert.assertEquals(selectRolePlaceholder, "Select Role");

        selectRole.click();
        wait.until(ExpectedConditions.visibilityOf(roleAdmin));

        roleAdmin.click();
        String selectedRole = selectedRoleLabel.getText();
        boolean clearIconVisibility = roleClearIcon.isDisplayed();
        Assert.assertEquals(selectedRole, "Administrator");
        Assert.assertTrue(clearIconVisibility);

        addRoleButton.click();
        wait.until(ExpectedConditions.visibilityOf(removeRoleButton));

        boolean permissionTableVisibility = permissionTable.isDisplayed();
        Assert.assertTrue(permissionTableVisibility);

        boolean interpreterInfoSectionVisible = ClUserTitle.get(6).isDisplayed();
        Assert.assertTrue(interpreterInfoSectionVisible);


    }

    public void verifyPermissionTableValuesAndCheckBoxes() {
        String[] permissionTableHeaderTexts = {"Permission Name", "Enabled", "Navigation Menu"};
        for (int i = 0; i < permissionTableHeader.size(); i++) {
            String actPermissionTableHeader = permissionTableHeader.get(i).getText();
            Assert.assertEquals(actPermissionTableHeader, permissionTableHeaderTexts[i]);
        }
        String[] expPermissionNames = {"Clients", "Dashboard", "Languages", "Reports", "Tools", "Translation Admin",
                "Users", "VRI", "OPI", "DT", "OSI", "Demo", "Scheduling Admin", "Analytics", "Web Calls", "Import"};

        List<String> actPermissionNames = new ArrayList<>();
        IntStream.range(0, 47 / 3 + 1).map(i -> i * 3).forEach((i) -> actPermissionNames.add(permissionTableValues.get(i).getText()));

        for (int j = 0; j < expPermissionNames.length; j++) {
            Assert.assertEquals(actPermissionNames.get(j), expPermissionNames[j]);
        }
    }

    public void verifyEnableAllPermissionForAdminUser() {
        wait = new WebDriverWait(driver, 5);
        action = new Actions(driver);
        IntStream.range(0, 31 / 2 + 1).map(i -> i * 2).forEach((i) -> {
            permissionCheckbox.get(i).click();

            String permissionEnabledStatus = permissionCheckbox.get(i).getText();
            String navigMenupermissionStatus = permissionCheckbox.get(i + 1).getText();

            Assert.assertEquals(permissionEnabledStatus, "Enabled");
            Assert.assertEquals(navigMenupermissionStatus, "Enabled");
        });
    }
}

