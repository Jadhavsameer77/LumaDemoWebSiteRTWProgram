package org.megento;

import io.qameta.allure.Allure;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.utilities.Configuration;
import lombok.extern.java.Log;
import org.utilities.ScreenshotUtil;

@Log
public class PurchasingItems {
    WebDriver driver;
    Core co = new Core(driver);
    WebElement element;
    Configuration conf = new Configuration();
    ScreenshotUtil sc = new ScreenshotUtil();



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
        sc.takeSnap(driver,element);
        co.hardWait(4000l);
        co.scrollTillElement(element);
        co.click(conf.getValues("WhatsNew"));
        Allure.step("User Accessing Whats New Tab");
        co.hardWait(3000l);
        element = co.getElement(conf.getValues("Product1"));
        co.hardWait(3000l);
        co.scrollTillElement(element);
        sc.takeSnap(driver,element);
        co.click(conf.getValues("Product1"));
        co.hardWait(3000l);
        Allure.step("Selecting Product From Whats New");
        element = co.getElement(conf.getValues("sizeOfProduct1"));
        sc.takeSnap(driver,element);
        co.click(conf.getValues("sizeOfProduct1"));
        co.hardWait(3000l);
        element = co.getElement(conf.getValues("colorOfProduct1"));
        sc.takeSnap(driver,element);
        co.click(conf.getValues("colorOfProduct1"));
        co.hardWait(3000l);
        co.click(conf.getValues("addToCartProduct1"));
        Allure.step("Product Added in cart");
        co.hardWait(3000l);
        String msg = co.getText(conf.getValues("product1Validation"));
        co.validatingString("shopping cart",msg);
        co.hardWait(3000l);
        element = co.getElement(conf.getValues("Gear"));
        sc.takeSnap(driver,element);
        co.actionClick(element);
        co.hardWait(3000l);
        element = co.getElement(conf.getValues("Watches"));
        sc.takeSnap(driver,element);
        co.openInNewTab(element);
        co.hardWait(5000l);
        System.out.println(driver.getTitle());
        co.winHandle();
        driver.switchTo().window(co.getChild());
        System.out.println(driver.getTitle());
        /*co.hardWait(5000l);
        element = co.getElement("filterPrice");
        co.actionClick(element);
        element = co.getElement("priceBracket");
        co.actionClick(element);
        co.hardWait(3000l);*/
    }

    @Test(priority = 4)
    public void addingCartProduct2() throws Exception {
        co.winHandle();
        driver.switchTo().window(co.getChild());
        co.hardWait(5000l);
        element = co.getElement(conf.getValues("filterPrice"));
        sc.takeSnap(driver,element);
        co.actionClick(element);
        element = co.getElement(conf.getValues("priceBracket"));
        sc.takeSnap(driver,element);
        co.actionClick(element);
        co.hardWait(3000l);
        element = co.getElement(conf.getValues("material"));
        sc.takeSnap(driver,element);
        co.actionClick(element);
        element = co.getElement(conf.getValues("materialRubber"));
        sc.takeSnap(driver,element);
        co.actionClick(element);
        co.hardWait(3000l);
        co.click(conf.getValues("watch"));
        co.click(conf.getValues("addToCartProduct2"));
        co.hardWait(3000l);
        Allure.step("Product added into cart");
        driver.close();
        driver.switchTo().window(co.getParent());
        co.hardWait(5000l);
    }

    @Test(priority = 5)
    public void clossingSession()
    {
        co.teardown();
        Allure.step("Session Closing for purchasing product");
    }


}
