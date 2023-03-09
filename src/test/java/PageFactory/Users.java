package PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

/* This class covers some functionalities in Users page. It includes functionalities of search field,
Add new location button and client list table */

public class Users {

    WebDriver driver;
    WebDriverWait wait = null;

    public Users(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "li[title='Users']")
    WebElement navLinkUsers;
    @FindBy(xpath = "//span[text()='Add New User']")
    WebElement addNewUserButton;
    @FindBy(css = "a[href='/users']")
    WebElement usersLink;
    @FindBy(css = "div[class='page-container__title']")
    WebElement usersPageHeader;
    @FindBy(how = How.CSS, using = "th.sortable")
    List<WebElement> usersTableHeader;
    @FindBy(css = "img[class='sort-icon']")
    WebElement sortIcon;
    @FindBy(css = "h5[class='grid-empty-state__title']")
    WebElement emptyGridAlert;
    @FindBy(css = "input[class='input form-control']")
    WebElement usersSearch;
    @FindBy(css = "div[class='Grid__table-wrapper']")
    WebElement gridUserTable;
    @FindBy(css = "div[class='Grid__controls-wrapper']")
    WebElement gridControl;
    @FindBy(css = "a[class='grid-link']")
    List<WebElement> usersListLinks;
    @FindBy(css = "input[placeholder='First Name']")
    WebElement usersFirstName;
    @FindBy(how = How.CSS, using = "span.page-number")
    List<WebElement> pageNumber;
    @FindBy(how = How.CSS, using = "span.fa-angle-double-left")
    WebElement firstPage;
    @FindBy(how = How.CSS, using = "span.fa-angle-double-right")
    WebElement lastPage;
    @FindBy(css = "span[class='fa fa-angle-left']")
    WebElement prevPage;
    @FindBy(css = "span[class='fa fa-angle-right']")
    WebElement nextPage;
    @FindBy(css = "span[class='active page-number']")
    WebElement activePageNumber;

    public void verifyUsersPageLoaded() {
        wait = new WebDriverWait(driver, 50);
        navLinkUsers.click();

        wait.until(ExpectedConditions.visibilityOf(addNewUserButton));

        String activeNavItemClass = usersLink.getAttribute("class");

        boolean activeClass = activeNavItemClass.contains("Sidebar__nav-wrapper__nav__nav-item__nav-link--active");
        Assert.assertTrue(activeClass);

        String expTitle = "CLIQ - Users";
        String expPageHeader = "Users";
        String actTitle = driver.getTitle();
        String actPageHeader = usersPageHeader.getText();

        Assert.assertEquals(actTitle, expTitle);
        Assert.assertEquals(actPageHeader, expPageHeader);
    }

    public void verifySortFunctionalityOfTableHeader() {
        wait = new WebDriverWait(driver, 50);
        wait.until(ExpectedConditions.visibilityOf(gridControl));

        for (WebElement webElement : usersTableHeader) {
            boolean headerClassContains = webElement.getAttribute("class").contains("sortable sorted");
            Assert.assertTrue(headerClassContains);

            webElement.click();

            boolean ascendingClass = webElement.getAttribute("class").contains("asc ");
            Assert.assertTrue(ascendingClass);

            String currentUrl = driver.getCurrentUrl();
            String baseUrl = currentUrl.replace("/users", "");

            String expAscSrcAttri = baseUrl + "/assets/img/sort-up@2x.e0fcbe83746bd87246013de5482f893b.png";
            Assert.assertEquals(sortIcon.getAttribute("src"), expAscSrcAttri);

            webElement.click();

            boolean descendingClass = webElement.getAttribute("class").contains("dec ");
            Assert.assertTrue(descendingClass);

            String expDecSrcAttri = baseUrl + "/assets/img/sort-down@2x.5b9168e44dbe76be3cc898bd534c575f.png";
            Assert.assertEquals(sortIcon.getAttribute("src"), expDecSrcAttri);
        }
    }

    public void verifyVisibilityOfSearchAndAddNewButton() {
        boolean searchFieldVisible = usersSearch.isDisplayed();
        boolean addNewUserButtonVisible = addNewUserButton.isDisplayed();

        Assert.assertTrue(searchFieldVisible);
        Assert.assertTrue(addNewUserButtonVisible);
    }

    public void verifySearchFieldWithInvalidInput() throws InterruptedException {
        wait = new WebDriverWait(driver,30);
        String searchPlaceholder = usersSearch.getAttribute("placeholder");
        Assert.assertEquals(searchPlaceholder, "Enter Search Term");

        usersSearch.sendKeys("abcd");

        Thread.sleep(5000);

        String noResultsText = emptyGridAlert.getText();
        Assert.assertEquals(noResultsText, "There are currently no results");

    }

    public void verifySearchFieldWithValidInput() {
        wait = new WebDriverWait(driver, 60);

        usersSearch.clear();
        usersSearch.sendKeys("Test Client");

        wait.until(ExpectedConditions.visibilityOf(gridUserTable));
        boolean userListVisibility = gridUserTable.isDisplayed();
        Assert.assertTrue(userListVisibility);

        boolean userLinkTextContains = usersListLinks.get(0).getText().contains("Test Client");
        Assert.assertTrue(userLinkTextContains);

    }

    public void verifyAddNewUserButtonRedirection() {
        wait = new WebDriverWait(driver, 50);

        String currentUrl = driver.getCurrentUrl();
        String baseUrl = currentUrl.replace("/users", "");

        addNewUserButton.click();
        wait.until(ExpectedConditions.textToBePresentInElement(usersPageHeader, "New User"));

        String currentURL = driver.getCurrentUrl();
        String expUrl = baseUrl + "/users/new";

        String actualTitle = driver.getTitle();

        Assert.assertEquals(currentURL, expUrl);
        Assert.assertEquals(actualTitle,"CLIQ - Users Add");

        driver.navigate().back();
        Assert.assertEquals(driver.getCurrentUrl(), baseUrl + "/users");

    }

    public void verifyUserLinkRedirection() {
        wait = new WebDriverWait(driver, 50);

        String currentUrl = driver.getCurrentUrl();
        String baseUrl = currentUrl.replace("/users", "");

        usersSearch.clear();
        usersSearch.sendKeys("All ");

        wait.until(ExpectedConditions.visibilityOf(gridControl));

        usersListLinks.get(0).click();

        wait.until(ExpectedConditions.attributeToBeNotEmpty(usersFirstName,"value"));

        String currentURL = driver.getCurrentUrl();
        String expUrl = baseUrl + "/user/48";

        Assert.assertEquals(currentURL, expUrl);

        String actFirstName = usersFirstName.getAttribute("value");
        Assert.assertEquals(actFirstName, "All");

        driver.navigate().back();
        wait.until(ExpectedConditions.visibilityOf(addNewUserButton));
    }

    public void verifyActivePages() {
        for (WebElement webElement : pageNumber) {
            webElement.click();

            String activePage = webElement.getAttribute("class");
            boolean pageIsActive = activePage.contains("active");

            Assert.assertTrue(pageIsActive);
        }
    }

    public void verifyFirstLastGrid() {
        firstPage.click();

        String pageOneClass = pageNumber.get(0).getAttribute("class");
        boolean activePageOne = pageOneClass.contains("active");
        Assert.assertTrue(activePageOne);

        lastPage.click();

        int lastPageNo = pageNumber.size() - 1;
        String pageLastClass = pageNumber.get(lastPageNo).getAttribute("class");
        boolean activeLastPage = pageLastClass.contains("active");
        Assert.assertTrue(activeLastPage);


    }

    public void verifyNextPreviousGrid() {

        int currentActivePage = Integer.parseInt(activePageNumber.getText());
        int lastPage = pageNumber.size();

        if (currentActivePage == lastPage) {
            prevPage.click();
            Assert.assertEquals(Integer.parseInt(activePageNumber.getText()), lastPage - 1);

            nextPage.click();
            Assert.assertEquals(Integer.parseInt(activePageNumber.getText()), lastPage);

        } else if (currentActivePage == 1) {
            nextPage.click();
            Assert.assertEquals(Integer.parseInt(activePageNumber.getText()), 2);

            prevPage.click();
            Assert.assertEquals(Integer.parseInt(activePageNumber.getText()), 1);

        }
    }
}
