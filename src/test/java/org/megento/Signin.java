package org.megento;

import io.qameta.allure.Allure;
import lombok.Value;
import lombok.extern.java.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.utilities.Configuration;


@Log
public class Signin {
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
