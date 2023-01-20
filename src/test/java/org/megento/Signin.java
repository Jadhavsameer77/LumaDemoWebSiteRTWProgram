package org.megento;

import io.qameta.allure.*;
import lombok.Value;
import lombok.extern.java.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.utilities.Configuration;
import org.utilities.ScreenshotUtil;

import java.net.URL;

@Feature("Login user in webpage")
@Log
public class Signin{
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

    @Description("User signin in portal with valid url")
    @Test(priority = 2)
    public void signin() throws Exception
    {
        co.click(conf.getValues("Signin"));
        co.input(conf.getValues("SEmail"), conf.getValues("Email"));
        co.input(conf.getValues("SPass"), conf.getValues("Pass"));
        co.click(conf.getValues("SButton"));
        Thread.sleep(5000);
        element= co.getElement(conf.getValues("WelcomeB"));
        co.actionClick(element);
        element= co.getElement(conf.getValues("LogoutB"));
        co.actionClick(element);
        Thread.sleep(8000);
        Allure.step("Adding Valid Credentials to login Megento Page");
    }

    @Test(priority = 3)
    public void invalidEmailformat() throws Exception
    {
        co.click(conf.getValues("Signin"));
        co.input(conf.getValues("SEmail"),"bkbdsk");
        co.input(conf.getValues("SPass"),"Qwerty12345!@#2121");
        co.click(conf.getValues("SButton"));
        Allure.step("Adding Invalid Email Format");
        Thread.sleep(5000);
        String text = co.getText(conf.getValues("EmailValidation"));
        element = co.getElement(conf.getValues("EmailValidation"));
        sc.takeSnap(driver,element);
        co.validatingString(conf.getValues("EmailErrMsg"), text);
        Allure.label("Validating String :: "+conf.getValues("EmailErrMsg"),"www.google.co.in?q=invalidMail");
    }

    @Test(priority = 4)
    public void invalidCreds() throws Exception
    {
        co.click(conf.getValues("Signin"));
        co.input(conf.getValues("SEmail"),"bkbdsk@test.com");
        co.input(conf.getValues("SPass"),"Qwerty12345!@#2121");
        co.click(conf.getValues("SButton"));
        Allure.step("Passing Invalid Email Id");
        Thread.sleep(5000);
        String text = co.getText(conf.getValues("HeaderErr"));
        element = co.getElement(conf.getValues("HeaderErr"));
        sc.takeSnap(driver,element);
        co.validatingString(conf.getValues("HeaderErrMsg"), text);
        Allure.label("Validating String :: "+conf.getValues("EmailErrMsg"),"www.google.co.in?q=invalidMail");
    }

    @Test(priority = 5)
    public void browserClose()
    {
        co.teardown();
        Allure.step("Closing Session for Signing Window");
    }
}
