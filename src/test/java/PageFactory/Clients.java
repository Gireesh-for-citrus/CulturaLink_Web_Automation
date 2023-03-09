package PageFactory;


import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.List;

/* This class covers some functionalities in Clients page. It includes functionalities of search filed,
Add new location button and client list table */

public class Clients {

    WebDriver driver;

    WebDriverWait wait;

    @FindBy(css = "button[class='button backgroundColor color border button button__tree-view']")
    WebElement treeViewButton;
    @FindBy(css = "div[class='input-container search-container']")
    WebElement clientSearchField;
    @FindBy(css = "a[href='/clients']")
    WebElement navItemClientsSelect;
    @FindBy(css = "li[title='Clients']")
    WebElement navItemClients;
    @FindBy(css = "div[class='page-container__title']")
    WebElement clientPageHeader;
    @FindBy(css = "span[class='text text__tree-view']")
    WebElement treeOrClientViewButton;
    @FindBy(css = "div[class='Tree__wrapper']")
    WebElement treeViewList;
    @FindBy(css = "div[class='ClientsDashboard__wrapper']")
    WebElement clientViewList;
    @FindBy(how = How.CSS, using = "th.string-sort")
    WebElement clientListHeader;
    @FindBy(css = "input[class='input form-control']")
    WebElement clientSearch;
    @FindBy(css = "div[class='no-results']")
    WebElement noResults;
    @FindBy(css = "div[class='SectionWrapper__content clients-grid']")
    WebElement clientTable;
    @FindBy(how = How.CSS, using = "button.button__primary")
    WebElement addNewLocationButton;
    @FindBy(css = "div[class='Wizard__item']")
    WebElement addNewLocInputFields;
    @FindBy(css = "img[class='sort-icon']")
    WebElement sortIcon;
    @FindBy(how = How.CSS, using = "span.page-number")
    List<WebElement> pageNumber;
    @FindBy(linkText = "HCA Healthcare")
    WebElement HCA_Healthcare;
    @FindBy(css = "div[class='basic-info__name mb-1']")
    WebElement clientNameHeader;
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

    public Clients(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void verifyClientPageLoaded() {
        wait = new WebDriverWait(driver, 50);
        navItemClients.click();

        wait.until(ExpectedConditions.visibilityOf(treeViewButton));

        String activeNavItemClass = navItemClientsSelect.getAttribute("class");

        boolean activeClass = activeNavItemClass.contains("Sidebar__nav-wrapper__nav__nav-item__nav-link--active");
        Assert.assertTrue(activeClass);

        String expTitle = "CLIQ - Client Level Container";
        String expPageHeader = "Clients";
        String actTitle = driver.getTitle();
        String actPageHeader = clientPageHeader.getText();

        Assert.assertEquals(actTitle, expTitle);
        Assert.assertEquals(actPageHeader, expPageHeader);


    }

    public void verifyTreeOrClientViewButton() {
        String treeButtonText = treeOrClientViewButton.getText();
        Assert.assertEquals(treeButtonText, "TREE VIEW");

        treeViewButton.click();

        Assert.assertEquals(treeOrClientViewButton.getText(), "CLIENT VIEW");

        boolean treeViewVisibility = treeViewList.isDisplayed();
        Assert.assertTrue(treeViewVisibility);

    }

    public void verifyClientViewButton() {
        wait = new WebDriverWait(driver, 50);

        treeViewButton.click();

        wait.until(ExpectedConditions.visibilityOf(clientListHeader));

        boolean clientViewListVisibility = clientViewList.isDisplayed();
        Assert.assertTrue(clientViewListVisibility);

        boolean searchVisibility = clientSearchField.isDisplayed();
        Assert.assertTrue(searchVisibility);

        boolean listHeaderVisibility = clientListHeader.isDisplayed();
        Assert.assertTrue(listHeaderVisibility);

        String listHeaderText = clientListHeader.getText();
        Assert.assertEquals(listHeaderText, "CLIENT NAME");

    }

    public void verifySearchFunctionality() {
        String searchPlaceholder = clientSearch.getAttribute("placeholder");
        Assert.assertEquals(searchPlaceholder, "Search...");

        clientSearch.sendKeys("123");

        String noResultsText = noResults.getText();
        Assert.assertEquals(noResultsText, "No results with the name:\n" +
                "\"123\"");

        clientSearch.clear();
        boolean clientListVisibility = clientTable.isDisplayed();
        Assert.assertTrue(clientListVisibility);


    }

    public void verifyAddNewButtonRedirection() {
        wait = new WebDriverWait(driver, 50);

        String currentUrl = driver.getCurrentUrl();
        String baseUrl = currentUrl.replace("/clients", "");

        addNewLocationButton.click();
        wait.until(ExpectedConditions.visibilityOf(addNewLocInputFields));

        String currentURL = driver.getCurrentUrl();
        String addNewLocURL = baseUrl + "/clients/new";

        Assert.assertEquals(currentURL, addNewLocURL);

        driver.navigate().back();
        Assert.assertEquals(driver.getCurrentUrl(), baseUrl + "/clients");

    }

    public void verifySortClientListFunctionality() {
        String clientListHeaderClass = clientListHeader.getAttribute("class");
        Assert.assertEquals(clientListHeaderClass, "sortable sorted string-sort");

        clientListHeader.click();

        Assert.assertEquals(clientListHeader.getAttribute("class"), "asc sortable sorted string-sort");

        String currentUrl = driver.getCurrentUrl();
        String baseUrl = currentUrl.replace("/clients", "");

        String expAscSrcAttri = baseUrl + "/assets/img/sort-up@2x.e0fcbe83746bd87246013de5482f893b.png";
        Assert.assertEquals(sortIcon.getAttribute("src"), expAscSrcAttri);

        clientListHeader.click();

        Assert.assertEquals(clientListHeader.getAttribute("class"), "dec sortable sorted string-sort");

        String expDecSrcAttri = baseUrl + "/assets/img/sort-down@2x.5b9168e44dbe76be3cc898bd534c575f.png";
        Assert.assertEquals(sortIcon.getAttribute("src"), expDecSrcAttri);

    }

    public void verifyRedirectionOfClientList() {
        wait = new WebDriverWait(driver, 50);

        for (int i = 0; i < pageNumber.size(); i++) {
            pageNumber.get(i).click();
            try {
                HCA_Healthcare.click();
                Assert.assertEquals(clientNameHeader.getText(), "HCA Healthcare");
                driver.navigate().back();
                wait.until(ExpectedConditions.visibilityOf(treeViewButton));

            } catch (NoSuchElementException e) {
                i++;
            }
        }

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

    public void verifyClientPageRedirInTreeView() {
        wait = new WebDriverWait(driver, 10);
        treeOrClientViewButton.click();
        wait.until(ExpectedConditions.visibilityOf(HCA_Healthcare));
        HCA_Healthcare.click();
        Assert.assertEquals(clientNameHeader.getText(), "HCA Healthcare");
        driver.navigate().back();
        wait.until(ExpectedConditions.visibilityOf(treeViewButton));

    }

}


