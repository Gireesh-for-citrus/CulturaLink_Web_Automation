package Steps.Web;

import PageElements.PagesWithLocators;
import PageFactory.*;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.BeforeTest;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class CommonSteps {
    WebDriver driver = null;
    Properties Prop;
    Login login;
    navigationPanel navPanel;
    Dashboard dashboard;
    dashboardChartsGraphs chartsGraphs;
    Clients clients;
    AddNewLocation newLocation;
    AddNewUser adduser;


    @BeforeTest
    public static void loadLog4J() {
        String log4Jpath = System.getProperty("src/test/resources/Config/log4j2.properties");
        PropertyConfigurator.configure(log4Jpath);
    }

    @After
    public void closeBrowser() {
        driver.close();
    }

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

    @And("I verify the forgot password link redirects to One Login app")
    public void iVerifyTheForgotPasswordLinkRedirectsToOneLoginApp() {
        login = new Login(driver);
        login.verifyForgotPassword();
    }


    @Then("I check whether the validation is correct for valid and invalid login credentials")
    public void iCheckWhetherTheValidationIsCorrectForValidAndInvalidLoginCredentials() throws IOException {
        login.loginValidationCheck();
    }


    @Then("I Login to dashboard with valid credentials")
    public void iLoginToDashboardWithValidCredentials() throws IOException, InterruptedException {

        login = new Login(driver);
        login.loginWithValidCredentials();
        Thread.sleep(5000);

    }

    @And("I check whether the dashboard page is loaded")
    public void iCheckWhetherTheDashboardPageIsLoaded() {
        login.verifyDashboardLoaded();
    }

    @Then("I check the logo is displayed")
    public void iCheckTheLogoIsDisplayed() {
        try {
            navPanel = new navigationPanel(driver);
            navPanel.visibilityOfLogo();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @And("I check whether the Expand or Collapse button is working fine")
    public void iCheckWhetherTheExpandOrCollapseButtonIsWorkingFine() throws InterruptedException {
        navPanel = new navigationPanel(driver);
        navPanel.expandOrCollapseIcon();
        navPanel.headerLogoVisibility();
    }

    @And("I verify the notification button showing the notification popup")
    public void iVerifyTheNotificationButtonShowingTheNotificationPopup() {
        navPanel = new navigationPanel(driver);
        navPanel.verifyNotificationButton();
    }

    @Then("I verify the redirection URLs for other links on the Navigation panel")
    public void iVerifyTheRedirectionURLsForOtherLinksOnTheNavigationPanel() throws InterruptedException {
        navPanel = new navigationPanel(driver);
        navPanel.checkRedirection();
    }

    @And("I check whether the Edit profile picture popup displayed when clicking on it")
    public void iCheckWhetherTheEditProfilePicturePopupDisplayedWhenClickingOnIt() {
        navPanel = new navigationPanel(driver);
        navPanel.verifyEditProfilePopup();

    }

    @And("I verify the logout button is working fine")
    public void iVerifyTheLogoutButtonIsWorkingFine() {
        navPanel = new navigationPanel(driver);
        navPanel.verifyLogoutFunctionality();
    }

    @Then("I check the Filter Results button is displayed")
    public void iCheckTheFilterResultsButtonIsDisplayed() {
        dashboard = new Dashboard(driver);
        dashboard.visibilityOfFilter();
    }

    @And("I add one client to the filter and check whether the dashboard results displaying for the selected client")
    public void iAddOneClientToTheFilterAndCheckWhetherTheDashboardResultsDisplayingForTheSelectedClient() throws InterruptedException {
        dashboard = new Dashboard(driver);
        dashboard.filterResultsWithOneSearchValue();
    }

    @Then("I check whether the filter functionality is working fine with two filter values")
    public void iCheckWhetherTheFilterFunctionalityIsWorkingFineWithTwoFilterValues() throws InterruptedException {
        dashboard = new Dashboard(driver);
        dashboard.filterFunctionalityWithTwoFilterValue();

    }

    @And("I clear the applied filters from Dashboard")
    public void iClearTheAppliedFiltersFromDashboard() {
        dashboard = new Dashboard(driver);
        dashboard.clearFilter();
    }

    @Then("I verify the filter is applied for other pages")
    public void iVerifyTheFilterIsAppliedForOtherPages() {
        dashboard = new Dashboard(driver);
        dashboard.checkOtherPages();
    }

    @Then("I check the Filter by date functionality is displayed with required fields")
    public void iCheckTheFilterByDateFunctionalityIsDisplayedWithRequiredFields() {
        dashboard = new Dashboard(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        dashboard.filterByDate();
    }

    @Then("I select Portuguese language from the list and verify all charts are displayed the selected language")
    public void iSelectPortugueseLanguageFromTheListAndVerifyAllChartsAreDisplayedTheSelectedLanguage() {
        dashboard = new Dashboard(driver);
        dashboard.filterBySpecificLanguage();
    }

    @And("I add one user to filter and check whether the user is displayed on the filter section")
    public void iAddOneUserToFilterAndCheckWhetherTheUserIsDisplayedOnTheFilterSection() throws InterruptedException {
        dashboard = new Dashboard(driver);
        dashboard.filterByUser();
    }

    @Then("I Clear the user filter")
    public void iClearTheUserFilter() {
        dashboard = new Dashboard(driver);
        dashboard.clearUserFilter();
    }

    @And("I verify the start and end date in the date picker, the end date should be current date and start date should be previous month's date")
    public void iVerifyTheStartAndEndDateInTheDatePickerTheEndDateShouldBeCurrentDateAndStartDateShouldBePreviousMonthSDate() {
        dashboard = new Dashboard(driver);
        dashboard.verifyStartAndEndDate();
    }

    @Then("I click on Today button on the Start Date picker and check whether the current Date updated on Start Date calendar")
    public void iClickOnTodayButtonOnTheStartDatePickerAndCheckWhetherTheCurrentDateUpdatedOnStartDateCalendar() {
        dashboard = new Dashboard(driver);
        dashboard.verifyTodayButton();
    }

    @Then("I filter the dashboard using previous month")
    public void iFilterTheDashboardUsingPreviousMonth() throws InterruptedException {
        dashboard = new Dashboard(driver);
        dashboard.filterUsingPreviousMonth();

    }

    @Then("I filter the dashboard with next month and verify that it is not displaying the charts")
    public void iFilterTheDashboardWithNextMonthAndVerifyThatItIsNotDisplayingTheCharts() {
        dashboard = new Dashboard(driver);
        dashboard.filterUsingComingMonth();
    }

    @Then("I verify the Today Activity section displays the header and the inner elements")
    public void iVerifyTheTodayActivitySectionDisplaysTheHeaderAndTheInnerElements() {
        dashboard = new Dashboard(driver);
        dashboard.visibilityOfTodayActivity();
    }

    @And("I verify the Performance Statistics section displays the header and inner texts")
    public void iVerifyThePerformanceStatisticsSectionDisplaysTheHeaderAndInnerTexts() {
        dashboard = new Dashboard(driver);
        dashboard.verifyElementsInPerfStat();
    }

    @Then("I verify the required titles of each graphs and charts are displayed on the Dashboard")
    public void iVerifyTheRequiredTitlesOfEachGraphsAndChartsAreDisplayedOnTheDashboard() throws InterruptedException {
        chartsGraphs = new dashboardChartsGraphs(driver);
        chartsGraphs.verifyGraphsChartsTitles();
    }

    @And("I check whether the redirection URL of the right arrow on each graphs is set to reports page")
    public void iCheckWhetherTheRedirectionURLOfTheRightArrowOnEachGraphsIsSetToReportsPage() {
        chartsGraphs = new dashboardChartsGraphs(driver);
        chartsGraphs.verfiyRightArrowUrl();
    }

    @Then("I verify the elements in Highcharts Bar Series are displayed and verify the click functionality of legends values")
    public void iVerifyTheElementsInHighchartsBarSeriesAreDisplayedAndVerifyTheClickFunctionalityOfLegendsValues(DataTable barGraphs) throws InterruptedException {
        chartsGraphs = new dashboardChartsGraphs(driver);
        List<String> graphName = barGraphs.asList();

        for (String s : graphName) {
            switch (s) {
                case "Encounter Volume":
                    chartsGraphs.encounterVolumeVisibilityOfElement();
                    chartsGraphs.verifyMouseoverClickOnEnVolume();
                    break;
                case "Average_Client_Call_Rating":
                    chartsGraphs.avgClientCallRatingVisibilityOfElement();
                    //chartsGraphs.avgClientCallTooltipVisibility();
                    chartsGraphs.verifyClickOnLegendValues();
                    break;
                case "Language_Utilization":
                    chartsGraphs.langUtiVisibilityOfElements();
                    //chartsGraphs.langUtiTooltipVisibility();
                    chartsGraphs.verifyLangUtiLegendsClick();
                    break;
                case "Abandon_Rate":
                    chartsGraphs.abandRateVisibilityOfElements();
                    //chartsGraphs.abandRateTooltipVisibility();
                    chartsGraphs.verifyAbandRateLegendsClick();
                    break;
                case "Language_Duration":
                    chartsGraphs.langDurVisibilityOfElements();
                    //chartsGraphs.langDurTooltipVisibility();
                    chartsGraphs.verifyLangDurLegendsClick();
                    break;
                case "Volume By Duration":
                    chartsGraphs.verifyMouseoverClickVolByDuration();
                    break;
                case "Concurrent_Calls":
                    chartsGraphs.conCallsVisibilityOfElements();
                    //chartsGraphs.conCallsTooltipVisibility();
                    chartsGraphs.verifyConCallsLegendsClick();
                    break;
                case "Financial_Performance":
                    chartsGraphs.finPerfVisibilityOfElements();
                    //chartsGraphs.finPerfTooltipVisibility();
                    chartsGraphs.verifyFinPerfLegendsClick();
                    break;
                case "Interpreter_Pay":
                    chartsGraphs.interpreterPayVisibilityOfElements();
                    //chartsGraphs.interpreterPayTooltipVisibility();
                    chartsGraphs.verifyInterpreterPayLegendsClick();
                    break;
                case "Third Party Volume":
                    chartsGraphs.verifyMouseoverClickOnThirdPartyVol();
                    break;
                case "Third Party Duration":
                    chartsGraphs.thirdPartyDurVisibilityOfElements();
                    chartsGraphs.verifyThirdPartyDurLegendClick();
                    break;
            }
        }
    }

    @Then("I go to Clients page and verify that the required page is loaded")
    public void iGoToClientsPageAndVerifyThatTheRequiredPageIsLoaded() {
        clients = new Clients(driver);
        clients.verifyClientPageLoaded();
    }

    @And("I click on Tree View button and check whether it changes the client list behaviour from Table view to tree view")
    public void iClickOnTreeViewButtonAndCheckWhetherItChangesTheClientListBehaviourFromTableViewToTreeView() {
        clients.verifyTreeOrClientViewButton();
    }

    @Then("I click on Client View button and check whether it changes the client list behavior from Tree View to Table View")
    public void iClickOnClientViewButtonAndCheckWhetherItChangesTheClientListBehaviorFromTreeViewToTableView() {
        clients.verifyClientViewButton();
    }

    @And("I verify the search field functionality with an invalid search value")
    public void iVerifyTheSearchFieldFunctionalityWithAnInvalidSearchValue() {
        clients.verifySearchFunctionality();
    }

    @Then("I check the Add New Location button redirected to add new location page")
    public void iCheckTheAddNewLocationButtonRedirectedToAddNewLocationPage() {
        clients.verifyAddNewButtonRedirection();
    }

    @Then("I click on Client list header and verify the sort client list functionality")
    public void iClickOnClientListHeaderAndVerifyTheSortClientListFunctionality() {
        clients.verifySortClientListFunctionality();
    }

    @Then("I click on one client and check whether it redirected to proper URL")
    public void iClickOnOneClientAndCheckWhetherItRedirectedToProperURL() {
        clients.verifyRedirectionOfClientList();
    }

    @When("I click on page numbers and first and last icons, I verify whether it is displaying the proper list")
    public void iClickOnPageNumbersAndFirstAndLastIconsIVerifyWhetherItIsDisplayingTheProperList() {
        clients.verifyActivePages();
        clients.verifyFirstLastGrid();
        clients.verifyNextPreviousGrid();
    }

    @Then("I choose the Tree View and verify client page redirection")
    public void iChooseTheTreeViewAndVerifyClientPageRedirection() {
        clients.verifyClientPageRedirInTreeView();
    }

    @And("I verify the client page container title displayed is correct")
    public void iVerifyTheClientPageContainerTitleDisplayedIsCorrect() {
        newLocation = new AddNewLocation(driver);
        newLocation.verifyPageContainerTitle();

    }

    @Then("I verify the wizard header tabs are displayed and also verify the Basic Info tab is active state")
    public void iVerifyTheWizardHeaderTabsAreDisplayedAndAlsoVerifyTheBasicInfoTabIsActiveState() {
        newLocation = new AddNewLocation(driver);
        newLocation.verifyVisibilityOfWizardHeaderTabs();
    }

    @And("I navigate to Add New Location page")
    public void iNavigateToAddNewLocationPage() {
        newLocation = new AddNewLocation(driver);
        newLocation.navigateToNewLocation();
    }

    @And("I check whether the required content headers are visible in the Basic Info tab as expected")
    public void iCheckWhetherTheRequiredContentHeadersAreVisibleInTheBasicInfoTabAsExpected() {
        newLocation = new AddNewLocation(driver);
        newLocation.verifyContentHeaderInBasicInfo();
    }

    @Then("I verify each input text fields are displayed with proper labels and place holder values")
    public void iVerifyEachInputTextFieldsAreDisplayedWithProperLabelsAndPlaceHolderValues() {
        newLocation.verifyInputTextFieldsInBasicInfo();

    }

    @When("I click on Add another button I verify the new classification field is added to the page")
    public void iClickOnAddAnotherButtonIVerifyTheNewClassificationFieldIsAddedToThePage() {
        newLocation.verifyAddAnotherButton();
    }

    @Then("I check the Remove button on the classification section removes the input fields")
    public void iCheckTheRemoveButtonOnTheClassificationSectionRemovesTheInputFields() {
        newLocation.verifyRemoveButton();
    }

    @Then("I input some values to the input fields and verify whether they are accepting the values in these fields")
    public void iInputSomeValuesToTheInputFieldsAndVerifyWhetherTheyAreAcceptingTheValuesInTheseFields() throws IOException {
        newLocation.verifyInputFieldsAcceptingValues();
    }

    @And("I verify the input fields Name, Cart PIN, Classification Name are mandatory fields")
    public void iVerifyTheInputFieldsNameCartPINClassificationNameAreMandatoryFields() {
        newLocation.verifyMandatoryFieldsInBasicInfo();
    }

    @Then("I verified the check box labels are displayed in Location Information and Classification Name sections")
    public void iVerifiedTheCheckBoxLabelsAreDisplayedInLocationInformationAndClassificationNameSections() {
        newLocation.verifyCheckboxLabels();
    }

    @And("I enter the required values to the input fields and click on Next button and I verify it switched to Contact Info tab")
    public void iEnterTheRequiredValuesToTheInputFieldsAndClickOnNextButtonAndIVerifyItSwitchedToContactInfoTab() {
        newLocation.verifyNextButtonInBasicInfo();
    }

    @And("I verify the required titles in Contact Info tab is displayed")
    public void iVerifyTheRequiredTitlesInContactInfoTabIsDisplayed() throws IOException {
        newLocation.verifyCI_ContainerTitleAndContentHeader();
    }

    @And("I verify the input fields in Contact Info tab are working as expected")
    public void iVerifyTheInputFieldsInContactInfoTabAreWorkingAsExpected() {
        newLocation.veryInputFieldsInContactInfo();
        newLocation.verifyInputValueType();

    }

    @Then("I verify the Back and Next button functionality in Contact Info tab")
    public void iVerifyTheBackAndNextButtonFunctionalityInContactInfoTab() throws IOException {
        newLocation.verifyBackButtonInContactInfo();
        newLocation.verifyNextButtonInContactInfo();
    }

    @Then("I verify the input fields and its functionality in Business Info tab")
    public void iVerifyTheInputFieldsAndItsFunctionalityInBusinessInfoTab() {
        newLocation.verifyInputFieldsInBusinessAddressTab();
        newLocation.verifyMandatoryFieldsInBusinessAddressTab();
    }

    @And("I verify the Back and Next button functionality in Business Address tab")
    public void iVerifyTheBackAndNextButtonFunctionalityInBusinessAddressTab() {
        newLocation.verifyBackAndNextButtonInBusiAddressTab();
    }

    @Then("I verify the functionalities of check box, input fields, back button and Next button functionalities in Mailing address tab")
    public void iVerifyTheFunctionalitiesOfCheckBoxInputFieldsBackButtonAndNextButtonFunctionalitiesInMailingAddressTab() {
        newLocation.verifySameAsBusiCheckBoxInMailAddressTab();
        newLocation.verifyInputFieldsInMailingAddressTab();
        newLocation.verifyBackAndNextButtonInMailingAddressTab();
    }

    @And("I verify the checkboxes and list of permissions in Location Roles tab")
    public void iVerifyTheCheckboxesAndListOfPermissionsInLocationRolesTab() {
        newLocation.verifyCheckboxLabels_LR();
        newLocation.verifyListOfPermissionsInLR();
        newLocation.verifyBackAndNextButtonInLocationRolesTab();
    }

    @And("I verify the elements and fields in Smart Routing Tab are visible and their behavior")
    public void iVerifyTheElementsAndFieldsInSmartRoutingTabAreVisibleAndTheirBehavior() {
        newLocation.verifySmartRoutCheckboxAndInputField();

    }

    @When("I click on Smart Routing checkbox I verify whether the input field is accepting the values, also I verify the functionalities of Back and Next button")
    public void iClickOnSmartRoutingCheckboxIVerifyWhetherTheInputFieldIsAcceptingTheValuesAlsoIVerifyTheFunctionalitiesOfBackAndNextButton() {
        newLocation.verifyCheckboxFunctionalityInSmartRoute();
        newLocation.verifyBackAndNextButtonInSmartRoutingTab();
    }

    @Then("I move to Billing Profile tab and I verify the table values displayed")
    public void iMoveToBillingProfileTabAndIVerifyTheTableValuesDisplayed() {
        newLocation.verifyTableHeaderTextInBillingProfileTab();
        newLocation.verifyTableHeaderSortFunctionality();
    }

    @And("I click on edit and verify the Billing Profile popup and the input fields and their behaviors")
    public void iClickOnEditAndVerifyTheBillingProfilePopupAndTheInputFieldsAndTheirBehaviors() {
        newLocation.verifyVisibilityOfEditBillingProfile();

    }

    @Then("I verify the edit billing profile functionality with valid and invalid parameters")
    public void iVerifyTheEditBillingProfileFunctionalityWithValidAndInvalidParameters() {
        newLocation.verifyEditBillProfWithValidParameters();
        newLocation.verifyEditBillProfWithInvalidParameters();
        newLocation.verifyCancelAndCloseButtonFunctionalityInBillProfPopup();
        newLocation.verifyBackAndNextButtonInBillingProfileTab();
    }

    @And("I go to Billing Rates tab and verify the visibility of elements in it")
    public void iGoToBillingRatesTabAndVerifyTheVisibilityOfElementsInIt() {
        newLocation.verifyBillingRatesTabEle();
    }

    @Then("I click on Add Rate button and verify the elements and fields in Add Bill Rate popup")
    public void iClickOnAddRateButtonAndVerifyTheElementsAndFieldsInAddBillRatePopup() {
        newLocation.verifyAddBillingRatePopup();
        newLocation.verifyInputFieldType();
        newLocation.verifyAlertsForEmptyFieldsInBRPopup();
    }

    @Then("I verify the Add Bill rate functionality with valid parameters")
    public void iVerifyTheAddBillRateFunctionalityWithValidParameters() {
        newLocation.verifyAddBillRateFunctionality();
    }


    @And("I verify the Back and Next button functionality in Billing Rate tab")
    public void iVerifyTheBackAndNextButtonFunctionalityInBillingRateTab() {
        newLocation = new AddNewLocation(driver);
        newLocation.verifyBackAndNextButtonInBillingRateTab();

    }

    @Then("I go to Carts tab and verify all elements are properly visible")
    public void iGoToCartsTabAndVerifyAllElementsAreProperlyVisible() {
        newLocation = new AddNewLocation(driver);
        newLocation.verifyVisibilityOfElementsInCartsTab();
    }

    @And("I click on Add new cart button and verify all elements and its properties")
    public void iClickOnAddNewCartButtonAndVerifyAllElementsAndItsProperties() {
        newLocation = new AddNewLocation(driver);

        newLocation.verifyAddNewCartPopupTitle();
        newLocation.verifyCloseAndCancelButtonFunctionality();
        newLocation.verifyAddNewCartFieldLabels();
        newLocation.verifyPropertiesInAddNewCartDeliveryDateField();
        newLocation.verifyDatePickerValues();
        newLocation.verifyMonthSelectionInDatePicker();
        newLocation.pickDiffDeliveryDate();
        newLocation.verifyTodayButtonInDatePicker();
        newLocation.verifyAddNewCartCheckboxFunctionality();
    }

    @Then("I verify the add, edit and delete cart functionalities")
    public void iVerifyTheAddEditAndDeleteCartFunctionalities() {
        newLocation = new AddNewLocation(driver);
        newLocation.addNewCartForLease();
        newLocation.addNewCartForPurchase();
        newLocation.verifyEditCartFunctionality();
        newLocation.verifyDeleteCartFunctionality();

    }

    @And("Finally I verified the Add new client submit button functionalities")
    public void finallyIVerifiedTheAddNewClientSubmitButtonFunctionalities() throws IOException {
        newLocation = new AddNewLocation(driver);
        newLocation.addNewLocationSubmit();
    }

    @And("I navigate to Users page and verify that the required page is loaded")
    public void iNavigateToUsersPageAndVerifyThatTheRequiredPageIsLoaded() {
        Users users = new Users(driver);
        users.verifyUsersPageLoaded();

    }

    @Then("I verify the header sort functionality of users table")
    public void iVerifyTheHeaderSortFunctionalityOfUsersTable() {
        Users users = new Users(driver);
        users.verifySortFunctionalityOfTableHeader();
    }

    @Then("I verify the visibility of search field and add new user button")
    public void iVerifyTheVisibilityOfSearchFieldAndAddNewUserButton() {
        Users users = new Users(driver);
        users.verifyVisibilityOfSearchAndAddNewButton();
    }

    @And("I validate the functionality of search field with valid and invalid inputs")
    public void iValidateTheFunctionalityOfSearchFieldWithValidAndInvalidInputs() throws InterruptedException {
        Users users = new Users(driver);
        users.verifySearchFieldWithInvalidInput();
        users.verifySearchFieldWithValidInput();
    }

    @Then("I verify the add new user button redirects to new user creation page")
    public void iVerifyTheAddNewUserButtonRedirectsToNewUserCreationPage() {
        Users users = new Users(driver);
        users.verifyAddNewUserButtonRedirection();
    }

    @And("I search one user and verify link is redirected to user profile page")
    public void iSearchOneUserAndVerifyLinkIsRedirectedToUserProfilePage() {
        Users users = new Users(driver);
        users.verifyUserLinkRedirection();
    }

    @Then("I verify the functionality of grid control in users page")
    public void iVerifyTheFunctionalityOfGridControlInUsersPage() {
        Users users = new Users(driver);
        users.verifyActivePages();
        users.verifyFirstLastGrid();
        users.verifyNextPreviousGrid();
    }

    @Then("I navigate to Add new user page")
    public void iNavigateToAddNewUserPage() {
        adduser = new AddNewUser(driver);
        adduser.redirectToAddNewUserPage();
    }

    @Then("I verify whether the required titles are displayed for CulturaLink User")
    public void iVerifyWhetherTheRequiredTitlesAreDisplayedForCulturaLinkUser() {
        adduser = new AddNewUser(driver);
        adduser.verifyTitlesOfClUser();

    }

    @And("I verify the expand and collapse functionalities of input field titles in CulturaLink user")
    public void iVerifyTheExpandAndCollapseFunctionalitiesOfInputFieldTitlesInCulturaLinkUser() throws InterruptedException {
        adduser = new AddNewUser(driver);
        adduser.verifyExpandCollapseFunctionality();
    }

    @Then("I verify functionalities and properties of the checkbox and input fields under User Association and User Information")
    public void iVerifyFunctionalitiesAndPropertiesOfTheCheckboxAndInputFieldsUnderUserAssociationAndUserInformation() {
        adduser = new AddNewUser(driver);
        adduser.verifyCheckboxLabelInUserAssociation();
        adduser.verifyInputFieldLabelsInUserInfo();
        adduser.verifyPlaceholderInUserInfo();
        adduser.verifyMandatoryFieldsInUserInfo();
        adduser.verifyInputFieldsInUserInfo();
    }

    @And("I verify the properties of input field and add valid inputs to all fields in Contact Info section")
    public void iVerifyThePropertiesOfInputFieldAndAddValidInputsToAllFieldsInContactInfoSection() {
        adduser = new AddNewUser(driver);
        adduser.verifyFieldLabelsAndPlaceHoldersInContactInfo();
        adduser.verifyMandatoryFieldsInContactInfo();
        adduser.verifyInputFieldsFunctionalityInContactInfo();
    }

    @Then("I verify the functionalities and properties of input fields in Home address tab")
    public void iVerifyTheFunctionalitiesAndPropertiesOfInputFieldsInHomeAddressTab() throws InterruptedException {
        adduser = new AddNewUser(driver);
adduser.verifyMandatoryFieldsInHomeAddress();
adduser.verifyInputFieldLabelsInHomeAddress();
adduser.verifyPlaceholderValuesInHomeAddress();
adduser.verifyInputFieldFunctionalityInHomeAddress();
    }

    @Then("I go to mailing address section and verified the properties and functionalities of input fields and checkbox")
    public void iGoToMailingAddressSectionAndVerifiedThePropertiesAndFunctionalitiesOfInputFieldsAndCheckbox() throws InterruptedException {
        adduser = new AddNewUser(driver);
        adduser.verifyInputFieldLabelsInMailingAddress();
        adduser.verifyPlaceholderValuesInMailingAddress();
        adduser.verifyInputFieldFunctionalityInMailingAddress();
        adduser.verifySameAsAboveCheckboxFunctionality();
    }
    @And("I verify the Role selection in Roles section")
    public void iVerifyTheRoleSelectionInRolesSection() throws InterruptedException {
        adduser = new AddNewUser(driver);
        adduser.verifyRoleSelectionDropdownInRolesSection();
        adduser.verifySelectAdminRoleFieldInRoles();
        adduser.verifyPermissionTableValuesAndCheckBoxes();
    }
}
