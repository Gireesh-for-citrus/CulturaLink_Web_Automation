package PageFactory;

import org.openqa.selenium.*;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

/* This class covers the functionalities of charts in CLIQ Dashboard page. It includes, visibility of elements, Tooltip visibility,
and other functionalities of chart. Some elements does not has unique element locators, due to this some of the codes will not work. */
public class dashboardChartsGraphs {

    WebDriver driver;
    WebDriverWait wait;
    Actions action;

    @FindBy(css = "h1[class='headline header']")
    List<WebElement> dashboardTitles;
    @FindBy(xpath = "//*[local-name()='a']")
    List<WebElement> reportsRedirUrl;
    @FindBy(css = "tspan[class='highcharts-text-outline']")
    List<WebElement> highChartOutlineText;
    @FindBy(how = How.CSS, using = "g.highcharts-tooltip.highcharts-color-0")
    WebElement tooltipColor0;
    @FindBy(how = How.CSS, using = "g.highcharts-tooltiphighcharts-color-1")
    WebElement tooltipColor1;
    @FindBy(how = How.CSS, using = "g.highcharts-tooltip.highcharts-color-2")
    WebElement tooltipColor2;
    @FindBy(how = How.CSS, using = "path.highcharts-point")
    List<WebElement> pieChartSlices;
    @FindBy(how = How.CSS, using = "path.highcharts-data-label-connector")
    List<WebElement> pieChartLabelConnector;
    @FindBy(how = How.CSS, using = "g.highcharts-data-label")
    List<WebElement> pieChartLabelTexts;
    @FindBy(css = "g[class='highcharts-label highcharts-tooltip highcharts-color-undefined']")
    List<WebElement> tooltips;
    @FindBy(how = How.CSS, using = "g.highcharts-axis-labels")
    List<WebElement> axisLabels;
    @FindBy(how = How.CSS, using = "g.highcharts-legend-item")
    List<WebElement> chartLegends;
    @FindBy(how = How.CSS, using = "g.highcharts-bar-series")
    List<WebElement> barCharts;
    @FindBy(how = How.CSS, using = "g.highcharts-legend-item-hidden")
    WebElement disabledLegend;
    @FindBy(how = How.CSS, using = "text.highcharts-axis-title")
    List<WebElement> axisTitles;
    @FindBy(how = How.CSS, using = "g.highcharts-column-series")
    List<WebElement> highChartVertiGraph;
    @FindBy(how = How.CSS, using = "rect.highcharts-point")
    List<WebElement> allChartPoints;

    public dashboardChartsGraphs(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void verifyGraphsChartsTitles() throws InterruptedException {
        Thread.sleep(20000);
        String[] graphChartsTitles = {"Today's Activity", "Performance Statistics", "Encounter Volume", "Average Client Call Rating",
                "Language Utilization", "Abandon Rate", "Language Duration", "Volume By Duration", "Concurrent Calls", "Financial Performance",
                "Interpreter Pay", "Third Party Volume", "Third Party Duration"};
        for (int i = 0; i < dashboardTitles.size(); i++) {
            Assert.assertEquals(graphChartsTitles[i], dashboardTitles.get(i).getText());
        }
    }

    public void verfiyRightArrowUrl() {
        String currentUrl = driver.getCurrentUrl();
        String baseUrl = currentUrl.replace("/dashboard", "");
        String expectedURL = baseUrl + "/reports";
        for (int i = 16; i < reportsRedirUrl.size(); i++) {
            Assert.assertEquals(expectedURL, reportsRedirUrl.get(i).getAttribute("href"));
        }
    }

    public void encounterVolumeVisibilityOfElement() throws InterruptedException {
        action = new Actions(driver);
        wait = new WebDriverWait(driver, 80);
        Dimension d = new Dimension(1280, 720);

        WebElement[] tooltipEle = new WebElement[]{tooltipColor0, tooltipColor1, tooltipColor2};

        driver.manage().window().setSize(d);
        driver.navigate().refresh();
        Thread.sleep(40000);
        //wait.until(ExpectedConditions.visibilityOfAllElements(allCharts));
        //wait.until(ExpectedConditions.visibilityOf(todaysActivity));
        String[] outLineText = {"OSI", "OPI", "VRI"};
        for (int i = 0; i < 3; i++) {
            Assert.assertEquals(highChartOutlineText.get(i).getText(), outLineText[i]);

//            action.moveToElement(pieChartSlices.get(i)).perform();
//            boolean tooltipVisibility = tooltipEle[i].isDisplayed();
//            Assert.assertTrue(tooltipVisibility);

        }
        Thread.sleep(5000);
    }

    public void verifyMouseoverClickOnEnVolume() {

        action = new Actions(driver);
        for (int i = 0; i < 3; i++) {
            action.moveToElement(pieChartSlices.get(i)).perform();
            String mouseoverClass = pieChartSlices.get(i).getAttribute("class");
            mouseoverClass.contains("highcharts-point-hover");


            action.click(pieChartSlices.get(i)).build().perform();
            String clickExpandClass = pieChartSlices.get(i).getAttribute("class");
            clickExpandClass.contains("highcharts-point-select");

        }
    }

    public void avgClientCallRatingVisibilityOfElement() {
        WebElement[] clientRatingEle = new WebElement[]{axisLabels.get(0), axisLabels.get(1), chartLegends.get(0), chartLegends.get(1), chartLegends.get(2)};

        for (WebElement webElement : clientRatingEle) {
            boolean visibilityClientRatingEle = webElement.isDisplayed();
            Assert.assertTrue(visibilityClientRatingEle);
        }
    }

    public void avgClientCallTooltipVisibility() {
        action = new Actions(driver);
        for (int i = 5; i < 9; i++) {
            action.moveToElement(allChartPoints.get(i)).perform();
            boolean avgCallRateTooltipVisibility = tooltips.get(0).isDisplayed();
            Assert.assertTrue(avgCallRateTooltipVisibility);
        }
    }

    public void verifyClickOnLegendValues() {

        WebElement[] barGraphs = new WebElement[]{barCharts.get(0), barCharts.get(2), barCharts.get(4)};

        for (int i = 0; i < 3; i++) {
            chartLegends.get(i).click();
            String legendsAttriVisibility = disabledLegend.getAttribute("class");
            String barGraphAttriVisibility = barGraphs[i].getAttribute("Visibility");

            legendsAttriVisibility.contains("highcharts-legend-item-hidden");

            Assert.assertEquals(barGraphAttriVisibility, "hidden");

            disabledLegend.click();
        }
    }

    public void langUtiVisibilityOfElements() {
        WebElement[] langUtiEle = new WebElement[]{axisLabels.get(2), axisLabels.get(3), chartLegends.get(3), chartLegends.get(4), chartLegends.get(5)};
        String langUtiAxisTitle = axisTitles.get(0).getText();
        Assert.assertEquals(langUtiAxisTitle, "Language By Volume");

        for (WebElement webElement : langUtiEle) {
            boolean langUtiEleVisibility = webElement.isDisplayed();
            Assert.assertTrue(langUtiEleVisibility);
        }
    }

    public void langUtiTooltipVisibility() {
        action = new Actions(driver);
        for (int i = 23; i < 28; i++) {
            action.moveToElement(allChartPoints.get(i)).perform();
            boolean langUtiTooltipVisibility = tooltips.get(1).isDisplayed();
            Assert.assertTrue(langUtiTooltipVisibility);
        }
    }

    public void verifyLangUtiLegendsClick() {
        WebElement[] langUtiBarGraphEle = new WebElement[]{highChartVertiGraph.get(0), highChartVertiGraph.get(2), highChartVertiGraph.get(4)};
        WebElement[] langUtiLegends = new WebElement[]{chartLegends.get(3), chartLegends.get(4), chartLegends.get(5)};

        for (int i = 0; i < langUtiLegends.length; i++) {
            langUtiLegends[i].click();
            String legendAttriClass = disabledLegend.getAttribute("class");
            String barGraphEleVisibility = langUtiBarGraphEle[i].getAttribute("Visibility");

            legendAttriClass.contains("highcharts-legend-item-hidden");

            Assert.assertEquals(barGraphEleVisibility, "hidden");

            disabledLegend.click();
        }
    }

    public void abandRateVisibilityOfElements() {
        WebElement[] abandRateGraphEle = new WebElement[]{axisLabels.get(4), axisLabels.get(5), chartLegends.get(6), chartLegends.get(7), chartLegends.get(8)};
        String abandRateAxisTitle = axisTitles.get(1).getText();
        Assert.assertEquals(abandRateAxisTitle, "Abandon Rate %");

        for (WebElement webElement : abandRateGraphEle) {
            boolean langUtiEleVisibility = webElement.isDisplayed();
            Assert.assertTrue(langUtiEleVisibility);
        }
    }

    public void abandRateTooltipVisibility() {
        action = new Actions(driver);
        for (int i = 41; i < 46; i++) {
            action.moveToElement(allChartPoints.get(i)).perform();
            boolean abandRateTooltipVisibility = tooltips.get(2).isDisplayed();
            Assert.assertTrue(abandRateTooltipVisibility);
        }
    }

    public void verifyAbandRateLegendsClick() {
        WebElement[] abandRateBarGraphEle = new WebElement[]{highChartVertiGraph.get(9), highChartVertiGraph.get(11), highChartVertiGraph.get(13)};
        WebElement[] abandRateLegends = new WebElement[]{chartLegends.get(6), chartLegends.get(7), chartLegends.get(8)};

        for (int i = 0; i < abandRateLegends.length; i++) {
            abandRateLegends[i].click();
            String legendAttriClass = disabledLegend.getAttribute("class");
            String barGraphEleVisibility = abandRateBarGraphEle[i].getAttribute("Visibility");

            legendAttriClass.contains("highcharts-legend-item-hidden");

            Assert.assertEquals(barGraphEleVisibility, "hidden");

            disabledLegend.click();
        }
    }

    public void langDurVisibilityOfElements() {
        WebElement[] langDurGraphEle = new WebElement[]{axisLabels.get(6), axisLabels.get(7), chartLegends.get(9), chartLegends.get(10), chartLegends.get(11)};
        String langDurAxisTitle = axisTitles.get(2).getText();
        Assert.assertEquals(langDurAxisTitle, "Minutes");

        for (WebElement webElement : langDurGraphEle) {
            boolean langDurEleVisibility = webElement.isDisplayed();
            Assert.assertTrue(langDurEleVisibility);
        }
    }

    public void langDurTooltipVisibility() {
        action = new Actions(driver);
        for (int i = 59; i < 64; i++) {
            action.moveToElement(allChartPoints.get(i)).perform();
            boolean langDurTooltipVisibility = tooltips.get(3).isDisplayed();
            Assert.assertTrue(langDurTooltipVisibility);
        }
    }

    public void verifyLangDurLegendsClick() {
        WebElement[] langDurBarGraphEle = new WebElement[]{highChartVertiGraph.get(18), highChartVertiGraph.get(20), highChartVertiGraph.get(22)};
        WebElement[] langDurLegends = new WebElement[]{chartLegends.get(9), chartLegends.get(10), chartLegends.get(11)};

        for (int i = 0; i < langDurLegends.length; i++) {
            langDurLegends[i].click();
            String legendAttriClass = disabledLegend.getAttribute("class");
            String barGraphEleVisibility = langDurBarGraphEle[i].getAttribute("Visibility");

            legendAttriClass.contains("highcharts-legend-item-hidden");

            Assert.assertEquals(barGraphEleVisibility, "hidden");

            disabledLegend.click();
        }
    }

    public void conCallsVisibilityOfElements() {
        WebElement[] conCallsGraphEle = new WebElement[]{axisLabels.get(8), axisLabels.get(9), chartLegends.get(12), chartLegends.get(13), chartLegends.get(14)};
        String conCallsAxisTitle = axisTitles.get(3).getText();
        Assert.assertEquals(conCallsAxisTitle, "Number of calls");

        for (WebElement webElement : conCallsGraphEle) {
            boolean conCallsEleVisibility = webElement.isDisplayed();
            Assert.assertTrue(conCallsEleVisibility);
        }
    }

    public void conCallsTooltipVisibility() {
        action = new Actions(driver);
        for (int i = 77; i < 82; i++) {
            action.moveToElement(allChartPoints.get(i)).perform();
            boolean conCallsTooltipVisibility = tooltips.get(4).isDisplayed();
            Assert.assertTrue(conCallsTooltipVisibility);
        }
    }

    public void verifyConCallsLegendsClick() {
        WebElement[] conCallsBarGraphEle = new WebElement[]{highChartVertiGraph.get(27), highChartVertiGraph.get(29), highChartVertiGraph.get(31)};
        WebElement[] conCallsLegends = new WebElement[]{chartLegends.get(12), chartLegends.get(13), chartLegends.get(14)};

        for (int i = 0; i < conCallsLegends.length; i++) {
            conCallsLegends[i].click();
            String legendAttriClass = disabledLegend.getAttribute("class");
            String barGraphEleVisibility = conCallsBarGraphEle[i].getAttribute("Visibility");

            legendAttriClass.contains("highcharts-legend-item-hidden");

            Assert.assertEquals(barGraphEleVisibility, "hidden");

            disabledLegend.click();
        }
    }

    public void finPerfVisibilityOfElements() {
        WebElement[] finPerfGraphEle = new WebElement[]{axisLabels.get(10), axisLabels.get(11), chartLegends.get(15), chartLegends.get(16), chartLegends.get(17)};
        String finPerfAxisTitle = axisTitles.get(4).getText();
        Assert.assertEquals(finPerfAxisTitle, "Total Spend");

        for (WebElement webElement : finPerfGraphEle) {
            boolean finPerfEleVisibility = webElement.isDisplayed();
            Assert.assertTrue(finPerfEleVisibility);
        }
    }

    public void finPerfTooltipVisibility() {
        action = new Actions(driver);
        wait = new WebDriverWait(driver, 5);
        for (int i = 91; i < 95; i++) {
            action.moveToElement(allChartPoints.get(i)).perform();
            wait.until(ExpectedConditions.visibilityOf(tooltips.get(5)));
            boolean finPerfTooltipVisibility = tooltips.get(5).isDisplayed();
            Assert.assertTrue(finPerfTooltipVisibility);
        }
    }

    public void verifyFinPerfLegendsClick() {
        WebElement[] finPerfBarGraphEle = new WebElement[]{barCharts.get(10), barCharts.get(11), barCharts.get(13)};
        WebElement[] finPerfLegends = new WebElement[]{chartLegends.get(15), chartLegends.get(16), chartLegends.get(17)};

        for (int i = 0; i < finPerfLegends.length; i++) {
            finPerfLegends[i].click();
            String legendAttriClass = disabledLegend.getAttribute("class");
            String barGraphEleVisibility = finPerfBarGraphEle[i].getAttribute("Visibility");

            legendAttriClass.contains("highcharts-legend-item-hidden");

            Assert.assertEquals(barGraphEleVisibility, "hidden");

            disabledLegend.click();
        }
    }

    public void interpreterPayVisibilityOfElements() {
        WebElement[] interpreterPayGraphEle = new WebElement[]{axisLabels.get(12), axisLabels.get(13), chartLegends.get(18), chartLegends.get(19), chartLegends.get(20)};

        for (WebElement webElement : interpreterPayGraphEle) {
            boolean interpreterPayEleVisibility = webElement.isDisplayed();
            Assert.assertTrue(interpreterPayEleVisibility);
        }
    }

    public void interpreterPayTooltipVisibility() {
        action = new Actions(driver);
        for (int i = 113; i < 118; i++) {
            action.moveToElement(allChartPoints.get(i)).perform();
            boolean interpreterPayTooltipVisibility = tooltips.get(6).isDisplayed();
            Assert.assertTrue(interpreterPayTooltipVisibility);
        }
    }

    public void verifyInterpreterPayLegendsClick() {
        WebElement[] interpreterPayBarGraphEle = new WebElement[]{barCharts.get(18), barCharts.get(20), barCharts.get(22)};
        WebElement[] interpreterPayLegends = new WebElement[]{chartLegends.get(18), chartLegends.get(19), chartLegends.get(20)};

        for (int i = 0; i < interpreterPayLegends.length; i++) {
            interpreterPayLegends[i].click();
            String legendAttriClass = disabledLegend.getAttribute("class");
            String barGraphEleVisibility = interpreterPayBarGraphEle[i].getAttribute("Visibility");

            legendAttriClass.contains("highcharts-legend-item-hidden");

            Assert.assertEquals(barGraphEleVisibility, "hidden");

            disabledLegend.click();
        }
    }

    public void thirdPartyDurVisibilityOfElements() {
        WebElement[] thirdPartyDurGraphEle = new WebElement[]{axisLabels.get(14), axisLabels.get(15), chartLegends.get(21)};

        String thirdPartyDurAxisTitle = axisTitles.get(5).getText();
        Assert.assertEquals(thirdPartyDurAxisTitle, "Minutes");

        for (WebElement webElement : thirdPartyDurGraphEle) {
            boolean thirdPartyDurEleVisibility = webElement.isDisplayed();
            Assert.assertTrue(thirdPartyDurEleVisibility);
        }
    }

    public void verifyThirdPartyDurLegendClick() {

        chartLegends.get(21).click();
        String legendAttriClass = disabledLegend.getAttribute("class");
        String barGraphEleVisibility = highChartVertiGraph.get(36).getAttribute("Visibility");

        legendAttriClass.contains("highcharts-legend-item-hidden");

        Assert.assertEquals(barGraphEleVisibility, "hidden");

        disabledLegend.click();
    }


    public void verifyMouseoverClickVolByDuration() {

        action = new Actions(driver);

        for (int i = 3; i < 8; i++) {

            boolean pieChartLabelConVisibility = pieChartLabelConnector.get(i).isDisplayed();
            boolean pieChartLabelVisibility = pieChartLabelTexts.get(i).isDisplayed();

            Assert.assertTrue(pieChartLabelConVisibility);
            Assert.assertTrue(pieChartLabelVisibility);

            action.moveToElement(pieChartSlices.get(i)).perform();
            String mouseoverClass = pieChartSlices.get(i).getAttribute("class");
            mouseoverClass.contains("highcharts-point-hover");

            action.click(pieChartSlices.get(i)).build().perform();
            String clickExpandClass = pieChartSlices.get(i).getAttribute("class");
            clickExpandClass.contains("highcharts-point-select");

        }
    }

    public void verifyMouseoverClickOnThirdPartyVol() {

        action = new Actions(driver);
        for (int i = 7; i < 12; i++) {

            boolean pieChartLabelConVisibility = pieChartLabelConnector.get(i).isDisplayed();
            boolean pieChartLabelVisibility = pieChartLabelTexts.get(i).isDisplayed();

            Assert.assertTrue(pieChartLabelConVisibility);
            Assert.assertTrue(pieChartLabelVisibility);

            action.moveToElement(pieChartSlices.get(i)).perform();
            String mouseoverClass = pieChartSlices.get(i).getAttribute("class");
            mouseoverClass.contains("highcharts-point-hover");


            action.click(pieChartSlices.get(i)).build().perform();
            String clickExpandClass = pieChartSlices.get(i).getAttribute("class");
            clickExpandClass.contains("highcharts-point-select");

        }
    }

}
