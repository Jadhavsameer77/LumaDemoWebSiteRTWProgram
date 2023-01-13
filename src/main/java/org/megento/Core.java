package org.megento;


import io.qameta.allure.Allure;
import lombok.Getter;
import lombok.extern.java.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;


import java.time.Duration;

@Getter
@Log
public class Core {

    WebDriver driver;



    public Core(WebDriver driver)
    {
        this.driver=driver;
    }

    public WebDriver setup()
    {
        driver = new ChromeDriver();
        log.info("Driver Initialize successfully :: "+driver.getClass());
        driver.manage().window().maximize();
        return driver;
    }

    public void click(String xpath)
    {
        driver.findElement(By.xpath(xpath)).click();
        log.info("Click on button");
    }

    public void input(String xpath, String value)
    {
        driver.findElement(By.xpath(xpath)).sendKeys(value);
        log.info("Sending Values to the text field :: "+value);
    }

    public void teardown()
    {
        driver.quit();
        log.info("Session close successfully");
    }

    public void waiting(Long value) throws Exception
    {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(value));
        log.info("Adding Wait in Execution :: "+value.toString());
    }

    public void actionClick(WebElement element)
    {
        log.info("Performing moveOver Action on :: "+element.getText());
        Actions ac = new Actions(driver);
        ac.moveToElement(element).click().perform();

    }

    public void actionHover(WebElement element)
    {
        log.info("Performing moveOver Action on :: "+element.getText());
        Actions ac = new Actions(driver);
        ac.moveToElement(element).build().perform();
    }

    public WebElement getElement(String xpath)
    {
        WebElement element = driver.findElement(By.xpath(xpath));
        log.info("Sending WebElement for :: "+element.getText());
        return element;

    }

    public String getText(String xpath)
    {
        WebElement element = driver.findElement(By.xpath(xpath));
        log.info("Getting a text from an element :: "+element.getText());
        return element.getText();
    }

    public void validatingString(String expected, String actual) {
        Assert.assertEquals(actual,expected);
        log.info("Actual Value :: "+actual+" ,Expected Value :: "+expected);
    }

    public void scrollingWindowToBottom()
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void scrollingWindowToUp()
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,0)");
    }

    public void scrollTillElement(WebElement element)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void hardWait(Long val)throws Exception
    {
        Thread.sleep(val);
    }

    public void openInNewTab(WebElement element)
    {
        element.sendKeys(Keys.chord(Keys.CONTROL ,Keys.ENTER));
    }
}
