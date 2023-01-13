package org.megento;

import io.qameta.allure.Allure;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.utilities.Configuration;
import lombok.extern.java.Log;

@Log
public class PurchasingItems {
    WebDriver driver;
    Core co = new Core(driver);
    WebElement element;
    Configuration conf = new Configuration();



    @Test(priority = 1)
    public void openBrowser()
    {
        driver= co.setup();
        driver.get(conf.getValues("url"));
        log.info("Megento Website invoke");
        Allure.step("User Navigate to the url :: "+conf.getValues("url"));
    }

    @Test(priority = 2)
    public void signin() throws Exception
    {
        co.click(conf.getValues("Signin"));
        co.input(conf.getValues("SEmail"), conf.getValues("Email"));
        co.input(conf.getValues("SPass"), conf.getValues("Pass"));
        co.click(conf.getValues("SButton"));
        /*Thread.sleep(5000);
        element= co.getElement(conf.getValues("WelcomeB"));
        co.actionClick(element);
        element= co.getElement(conf.getValues("LogoutB"));
        co.actionClick(element);*/
        Thread.sleep(8000);
        Allure.step("Adding Valid Credentials to login Megento Page");
    }

    @Test(priority = 3)
    public void addingCart()throws Exception
    {
        co.scrollingWindowToBottom();
        element = co.getElement(conf.getValues("WhatsNew"));
        co.hardWait(4000l);
        co.scrollTillElement(element);
        co.click(conf.getValues("WhatsNew"));
        co.hardWait(3000l);
        element = co.getElement(conf.getValues("Product1"));
        co.hardWait(3000l);
        co.scrollTillElement(element);
        co.click(conf.getValues("Product1"));
        co.hardWait(3000l);
        element = co.getElement(conf.getValues("Gear"));
        co.actionClick(element);
        co.hardWait(3000l);
        element = co.getElement(conf.getValues("Watches"));
        co.openInNewTab(element);
        co.hardWait(5000l);
    }

    @Test(priority = 4)
    public void clossingSession()
    {
        co.teardown();
    }


}
