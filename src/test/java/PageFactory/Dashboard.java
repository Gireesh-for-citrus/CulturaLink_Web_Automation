package PageFactory;

import Utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import java.util.List;
import java.util.concurrent.TimeUnit;


/* This class covers some functionalities in CLIQ Dashboard page. It includes Filter Results functionality, Language Filter,
 User filter, Date range filter and visibility of elements in Today's Activity and Performance Statistics section */

public class Dashboard {

    WebDriver driver;
    WebDriverWait wait;
    public static Logger logger;


    @FindBy(css = "div[class='FilterDropdown mr-1']")
    static WebElement filterResults;
    @FindBy(css = "input[class='form-control']")
    WebElement filterSearchField;
    @FindBy(css = "span[class='fa fa-plus fa-2x color-black']")
    WebElement btn_addFilterValue;
    @FindBy(css = "span[class='text text__primary']")
    WebElement btn_ApplyFilter;
    @FindBy(css = "li[class='SearchRichResultsItem']")
    List<WebElement> filterSearchResults;
    @FindBy(css = "h1[class='headline page-container__title']")
    WebElement filterHeader;
    @FindBy(css = "strong[class='secondary']")
    List<WebElement> filterStatus;
    @FindBy(css = "div[class='FilterDropdown__content__button__text']")
    WebElement clearFilter;
    @FindBy(css = "div[class='Sidebar__nav-wrapper__nav__nav-item__nav-link__label']")
    List<WebElement> navPages;
    @FindBy(css = "div[class='label']")
    List<WebElement> filterContainer;
    @FindBy(css = "div[class='date-picker d-flex grid-item']")
    WebElement filterByDate;
    @FindBy(css = "select[class='input']")
    WebElement dropDown_languageList;
    @FindBy(css = "g[class='highcharts-axis-labels highcharts-xaxis-labels ']")
    WebElement chartAxisValue;
    @FindBy(css = "span[class='primary-text-style']")
    WebElement btn_userSelect;
    @FindBy(css = "div[class='modal undefined']")
    WebElement selectUserPopup;
    @FindBy(css = "h2[class='modal-title']")
    WebElement selectUserPopupHeader;
    @FindBy(css = "input[class='input form-control']")
    WebElement searchUserInputField;
    @FindBy(css = "a[class='grid-link']")
    WebElement filterUser;
    @FindBy(css = "span[class='primary']")
    WebElement clearUser;
    @FindBy(css = "input[placeholder='Choose Start Date']")
    WebElement filterStartDate;
    @FindBy(css = "input[placeholder='Choose End Date']")
    WebElement filterEndDate;
    @FindBy(css = "span[class='current-date']")
    List<WebElement> monthYearDatePicker;
    @FindBy(css = "td[class='current-day']")
    List<WebElement> todayDatePicker;
    @FindBy(css = "button[class='dtp-button primary mt-5']")
    List<WebElement> btn_TodayDatePicker;
    @FindBy(css = "span[class='fa fa-chevron-left']")
    List<WebElement> datePickerLeftArrow;
    @FindBy(css = "span[class='fa fa-chevron-right']")
    List<WebElement> datePickerRightArrow;
    @FindBy(css = "div[class='DateTimePicker__wrapper']")
    List<WebElement> datePicker;
    @FindBy(css = "input[class='dtp-addon input form-control']")
    List<WebElement> startEndDatePicker;
    @FindBy(css = "div[class='statistic-value']")
    WebElement resultPerStatistics;
    @FindBy(css = "div[class='SectionWrapper__content chart']")
    WebElement noDataCharts;
    @FindBy(css = "h1[class='headline header']")
    List<WebElement> dashboardTitles;
    @FindBy(css = "div[class='SectionWrapper__content today-activity']")
    WebElement todayActivity;
    @FindBy(css = "div[class='header__name__today-activity']")
    List<WebElement> todayActivityHeaders;
    @FindBy(css = "div[class='description']")
    List<WebElement> todayActDescription;
    @FindBy(css = "div[class='grid-item container-justified-space-between performance-statistics']")
    WebElement perfStatistics;
    @FindBy(css = "div[class='header__name__performance-statistics']")
    List<WebElement> perfStatisticsHeaders;
    @FindBy(css = "div[class='statistic-label']")
    List<WebElement> perfStatisticsLabels;
    @FindBy(how = How.CSS, using = "div.sub-container")
    List<WebElement> performanceStatContent;


    public Dashboard(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        logger = Logger.getLogger(Dashboard.class.getName().getClass());
    }


    public void visibilityOfFilter() {
        try {
            filterResults.isDisplayed();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    public void filterResultsWithOneSearchValue() throws InterruptedException {
        wait = new WebDriverWait(driver, 50);

        wait.until(ExpectedConditions.visibilityOfAllElements(performanceStatContent));
        filterResults.click();
        String filterVal = "Test Client";
        filterSearchField.sendKeys(filterVal);
        Thread.sleep(10000);
        wait.until(ExpectedConditions.textToBePresentInElement(filterSearchResults.get(0), "Test Client1"));

        filterSearchResults.get(0).click();
        btn_addFilterValue.click();
        btn_ApplyFilter.click();

        wait.until(ExpectedConditions.visibilityOf(filterHeader));
        String actHeader = filterHeader.getText();
        String expFilterStatus = "Enabled (1)";
        String actFilterStatus = filterStatus.get(0).getText();
        Assert.assertEquals(actHeader, "Test Client1");

        Assert.assertEquals(expFilterStatus, actFilterStatus);

    }

    public void filterFunctionalityWithTwoFilterValue() throws InterruptedException {
        String filterVal = "Test Client";

        filterResults.click();
        filterSearchField.sendKeys(filterVal);
        Thread.sleep(10000);
        filterSearchResults.get(1).click();

        btn_addFilterValue.click();
        btn_ApplyFilter.click();
        Thread.sleep(15000);
        String actualUpdatedHeader = filterHeader.getText();
        String expectedHeader = "Test Client1, Test Client1 - Test 124";
        String expUpdatedFilterStatus = "Enabled (2)";
        String actFilterStatus = filterStatus.get(0).getText();
        Assert.assertEquals(actualUpdatedHeader, expectedHeader);

        Assert.assertEquals(expUpdatedFilterStatus, actFilterStatus);

    }

    public void checkOtherPages() {
        String[] expectedTitles = {"CLIQ - Translation Documents", "CLIQ - Translation Admin Dashboard", "CLIQ - Reports"};
        for (int i = 6, j = 0; i < 9 && j < 3; i++, j++) {
            navPages.get(i).click();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            String navPageTitle = driver.getTitle();
            String expUpdatedFilterStatus = "Enabled (2)";
            String actualFilterStatus = filterStatus.get(0).getText();
            if (navPageTitle.equals(expectedTitles[j])) {
                filterResults.isDisplayed();
                Assert.assertEquals(expUpdatedFilterStatus, actualFilterStatus);
            } else {
                logger.info("Filter functionality is not working....");
            }
        }

    }

    public void clearFilter() {
        navPages.get(0).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        filterResults.click();
        clearFilter.click();
        btn_ApplyFilter.click();
        String actualFilterStatus = filterStatus.get(0).getText();
        Assert.assertEquals(actualFilterStatus, "Disabled");

    }

    public void filterByDate() {
        try {
            if (filterByDate.isDisplayed()) {
                for (int i = 0; i < 4; i++) {

                    String[] containerLabels = {"Language", "User", "Start Date", "End Date"};
                    filterContainer.get(i).isDisplayed();
                    String actualContainerLabel = filterContainer.get(i).getText();
                    Assert.assertEquals(actualContainerLabel, containerLabels[i]);

                }

            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    public void filterBySpecificLanguage() {
        wait = new WebDriverWait(driver, 50);

        Select select = new Select(dropDown_languageList);
        select.selectByValue("Spanish");

        wait.until(ExpectedConditions.visibilityOf(chartAxisValue));

        String actualAxisValue = chartAxisValue.getText();
        Assert.assertEquals(actualAxisValue, "Spanish");
        select.selectByIndex(0);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    public void filterByUser() throws InterruptedException {

        btn_userSelect.click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        if (selectUserPopup.isDisplayed()) {
            wait = new WebDriverWait(driver, 120);
            String actualPopupHeader = selectUserPopupHeader.getText();
            String expectedPopupHeader = "Select User";
            Assert.assertEquals(actualPopupHeader, expectedPopupHeader);
            searchUserInputField.sendKeys("gkrish");
            wait.until(ExpectedConditions.textToBePresentInElement(filterUser, "Gireesh K K"));
            filterUser.click();
            wait.until(ExpectedConditions.visibilityOf(filterStatus.get(1)));
            driver.getPageSource().contains("Clear user");
            String actualFilterUser = filterStatus.get(1).getText();
            String expectedFilterUser = "Gireesh K K";
            Assert.assertEquals(actualFilterUser, expectedFilterUser);
            Thread.sleep(5000);


        } else {
            logger.error("Popup is not displayed");
        }

    }

    public void clearUserFilter() {
        clearUser.click();
    }

    public void verifyStartAndEndDate() {

        String actualStartDate = filterStartDate.getAttribute("value");
        String actualEndDate = filterEndDate.getAttribute("value");

        String expectedStartDate = Utils.aMonthAgoTodayDate();
        String expectedEndDate = Utils.today();

        Assert.assertEquals(expectedStartDate, actualStartDate);
        Assert.assertEquals(expectedEndDate, actualEndDate);

        String expectedStartMonthYear = Utils.previousMonthYear();
        String expectedEndMonthYear = Utils.currentMonthYear();

        filterStartDate.click();
        String actualFilterStartMonth = monthYearDatePicker.get(0).getText();

        Assert.assertEquals(expectedStartMonthYear.toUpperCase(), actualFilterStartMonth);

        filterEndDate.click();
        String actualFilterEndMonthYear = monthYearDatePicker.get(1).getText();

        Assert.assertEquals(expectedEndMonthYear.toUpperCase(), actualFilterEndMonthYear);

        String expFilterStartDay = Utils.aMonthAgoToday();
        String expFilterEndDay = Utils.todayDate();

        filterStartDate.click();
        String actFilterStartDay = todayDatePicker.get(0).getText();

        Assert.assertEquals(actFilterStartDay, expFilterStartDay);

        filterEndDate.click();
        String actFilterEndDay = todayDatePicker.get(1).getText();

        Assert.assertEquals(actFilterEndDay, expFilterEndDay);

    }

    public void verifyTodayButton() {
        String expFilterStartDate = Utils.today();

        filterStartDate.click();
        btn_TodayDatePicker.get(0).click();

        String actDateUpdated = filterStartDate.getAttribute("value");

        Assert.assertEquals(actDateUpdated, expFilterStartDate);
    }

    public void filterUsingPreviousMonth() throws InterruptedException {
        String[] startEndDate = {Utils.previousMonthStartDate(), Utils.previousMonthEndDate()};
        for (int i = 0; i < 2; i++) {
            startEndDatePicker.get(i).click();
            Thread.sleep(3000);
            datePickerLeftArrow.get(i).click();
            Thread.sleep(2000);

            List<WebElement> rows = datePicker.get(i).findElements(By.tagName("tr"));
            for (WebElement row : rows) {

                List<WebElement> cols = row.findElements(By.tagName("td"));
                aa:
                for (int k = 0; k < cols.size(); k++) {

                    String calDate = cols.get(k).getText();
                    while (calDate.equals(startEndDate[i])) {
                        if (cols.get(k).getAttribute("class").equals("prev-month")) {
                            k++;

                        } else {
                            cols.get(k).click();
                            break aa;
                        }
                    }
                }
            }
        }
        String actUpdatedFilterStartDate = filterStartDate.getAttribute("value");
        String actUpdatedFilterEndDate = filterEndDate.getAttribute("value");

        Assert.assertEquals(Utils.previousMonthStarts(), actUpdatedFilterStartDate);
        Assert.assertEquals(Utils.previousMonthEnds(), actUpdatedFilterEndDate);
    }

    public void filterUsingComingMonth() {
        String[] startEndDate = {Utils.nextMonthStartDate(), Utils.nextMonthEndDate()};
        for (int i = 0; i < 2; i++) {
            startEndDatePicker.get(i).click();

            while (true) {
                if (monthYearDatePicker.get(i).getText().equals(Utils.nextMonthYear().toUpperCase())) {
                    break;
                } else
                    datePickerRightArrow.get(i).click();
            }

            List<WebElement> rows = datePicker.get(i).findElements(By.tagName("tr"));
            for (WebElement row : rows) {

                List<WebElement> cols = row.findElements(By.tagName("td"));
                for (int k = 0; k < cols.size(); k++) {

                    String calDate = cols.get(k).getText();
                    if (cols.get(k).getAttribute("class").equals("prev-month")) {
                        k++;

                    } else if (calDate.equals(startEndDate[i])) {
                        cols.get(k).click();
                        break;
                    }
                }
            }
        }
        wait = new WebDriverWait(driver, 50);

        String actualUpdatedFilterStartDate = filterStartDate.getAttribute("value");
        String actualUpdatedFilterEndDate = filterEndDate.getAttribute("value");

        Assert.assertEquals(Utils.nextMonthStarts(), actualUpdatedFilterStartDate);
        Assert.assertEquals(Utils.nextMonthEnds(), actualUpdatedFilterEndDate);

        wait.until(ExpectedConditions.textToBePresentInElement(resultPerStatistics, "0"));
        Assert.assertEquals(resultPerStatistics.getText(), "0");

        wait.until(ExpectedConditions.visibilityOf(noDataCharts));
        Assert.assertEquals(noDataCharts.getText(), "No data for selected filters");
    }

    public void visibilityOfTodayActivity() {
        String[] expTodayActHeaders = {"Onsite (OSI)", "Onsite (OSI)", "Over The Phone (OPI)", "Video Remote (VRI)", "Document Translation (DT)"};
        String[] expTodayActDesc = {"Scheduled Appointments", "Cancelled Appointments", "Calls Completed", "Video Sessions Completed", "Translations in Queue"};

        driver.navigate().refresh();
        wait = new WebDriverWait(driver, 50);
        wait.until(ExpectedConditions.visibilityOf(todayActivity));

        Assert.assertEquals(dashboardTitles.get(0).getText(), "Today's Activity");
        todayActivity.isDisplayed();

        for (int i = 0; i < 5; i++) {
            Assert.assertEquals(todayActivityHeaders.get(i).getText(), expTodayActHeaders[i]);
            Assert.assertEquals(todayActDescription.get(i).getText(), expTodayActDesc[i]);
        }
    }

    public void verifyElementsInPerfStat() {
        wait = new WebDriverWait(driver, 50);

        String[] expPerfStatHeaders = {"Onsite (OSI)", "Over The Phone (OPI)", "Video Remote (VRI)", "Document Translation (DT)"};
        String[] expPerfStatLabels = {"Total Encounters",
                "Daily Average", "Cancelled", "Average Assignment Time", "Total Minutes", "Average Duration", "Calls Offered", "Calls Answered", "Calls Abandoned",
                "Total Minutes", "Average Connection Time", "Average Duration", "Calls Offered", "Calls Answered", "Calls Abandoned", "Total Documents",
                "Translations In Queue", "Translations Completed"};

        wait.until(ExpectedConditions.visibilityOf(perfStatistics));
        perfStatistics.isDisplayed();
        Assert.assertEquals(dashboardTitles.get(1).getText(), "Performance Statistics");

        for (int i = 0, j = 0; i < 4 && j < 18; i++, j++) {
            Assert.assertEquals(perfStatisticsHeaders.get(i).getText(), expPerfStatHeaders[i]);
            Assert.assertEquals(perfStatisticsLabels.get(j).getText(), expPerfStatLabels[j]);
        }
    }
}





