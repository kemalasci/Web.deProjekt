package utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class BaseClass {

    protected WebDriver driver;
    private WebDriverWait wait;
    Robot robot;
    public static String status = "passed";

    private void setUpDriver() {
        driver = Driver.getDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().deleteAllCookies();
        if (Config.maximized) {
            driver.manage().window().maximize();
        }


    }


    public BaseClass() {
        setUpDriver();
    }

    public void open(String url) {

        driver.get(url);
    }

    public void scrollToElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void sendKeysTo(By locator, String text) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
    }

    public void waitUntilVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void hower(By locator) {
        Actions action = new Actions(driver);
        WebElement we = driver.findElement(locator);
        action.moveToElement(we).build().perform();
    }


    public void sendKeysTo(WebElement element, String text) {
        waitUntilVisible(element);
        element.sendKeys(text);

    }

    public void clickTo(By locator) {

        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();

    }

    public void clickTo(WebElement element) {

        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void waitForVisibilityOf(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * @param locator
     * @return
     */
    public List<WebElement> getListOf(By locator) {
        return driver.findElements(locator);
    }


    public int getRandom(int min, int max) {

        return min + new Random().nextInt(max - min);
    }

    public void select(By locator, String selectByText) {
        Select objSelect = new Select(driver.findElement(locator));
        objSelect.selectByValue(selectByText);
    }

    public void scroolTo(By locator) {
        WebElement element = driver.findElement(locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();


    }


    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void switcToWindow() {
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
    }  public void switcToFrame(WebElement element) {

        driver.switchTo().frame(element);
    }

    public void verifyElementContainsText(By locator, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        System.out.println(driver.findElement(locator).getText());
        Assert.assertTrue(driver.findElement(locator).getText().toLowerCase().contains(text.toLowerCase()));
    }


    public void RobotClass(int a) throws AWTException, InterruptedException {
        Robot robot;
        robot = new Robot();
        for (int i = 0; i < (a); i++) {
            Thread.sleep(500);

            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);

        }


    }

    public void clearWithRobotClassSendKeys(By locator, int a, String text) throws AWTException {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();

        for (int i = 0; i < (a); i++) {

            robot = new Robot();

            robot.keyPress(KeyEvent.VK_BACK_SPACE);
            robot.keyRelease(KeyEvent.VK_BACK_SPACE);

        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
    }

    public void robotClass() {


        for (int i = 0; i < (1); i++) {

            robot = null;
            try {
                robot = new Robot();
            } catch (AWTException e) {
                e.printStackTrace();
            }

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);


        }


    }


    public void verifyLinks(By locator) {

        List<WebElement> links = driver.findElements(locator);

        System.out.println("Total links are " + links.size());

        for (int i = 0; i < links.size(); i++) {
            WebElement element = links.get(i);
            String url = element.getAttribute("href");
            verifyLink(url);
        }


    }

    public void verifyLink(String urlLink) {

        try {
            URL link = new URL(urlLink);
            HttpURLConnection httpConn = (HttpURLConnection) link.openConnection();
            httpConn.setConnectTimeout(2000);
            httpConn.connect();
            System.out.println(httpConn.getResponseCode() + " - " + urlLink + " - " + httpConn.getResponseMessage());
        } catch (Exception e) {
            System.out.println(urlLink + " - " + e.getMessage());
        }
    }

    public void selenium_broken_images(String URL) {
        Integer iBrokenImageCount = 0;

        driver.navigate().to(URL);
        driver.manage().window().maximize();

        try {
            iBrokenImageCount = 0;
            List<WebElement> image_list = driver.findElements(By.tagName("img"));
            /* Print the total number of images on the page */
            System.out.println("The page under test has " + image_list.size() + " images");
            for (WebElement img : image_list) {
                if (img != null) {
                    HttpClient client = HttpClientBuilder.create().build();
                    HttpGet request = new HttpGet(img.getAttribute("src"));
                    HttpResponse response = client.execute(request);
                    /* For valid images, the HttpStatus will be 200 */
                    if (response.getStatusLine().getStatusCode() != 200) {
                        System.out.println(img.getAttribute("outerHTML") + " is broken.");
                        iBrokenImageCount++;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            status = "failed";
            System.out.println(e.getMessage());
        }
        status = "passed";
        System.out.println("The page " + URL + " has " + iBrokenImageCount + " broken images");
    }
    public void navigateToHomePage() {

        String homePageIds = driver.getWindowHandle();



        Set<String> windowIDs = driver.getWindowHandles();

        for (String ID : windowIDs) {
            if (ID.equals(homePageIds))break;











        }
        driver.switchTo().window(homePageIds);



    }
    public void close() {
        Driver.quitDriver();
    }

    @AfterSuite
    public void afterClass() {
        close();
    }

}





