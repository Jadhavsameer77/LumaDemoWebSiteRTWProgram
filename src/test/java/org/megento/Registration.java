package org.megento;

import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.utilities.Configuration;


public class Registration{

    WebDriver driver;
    Core co = new Core(driver);
    Configuration conf = new Configuration();


    @Test(priority = 1)
    public void navigateToSite()
    {
        driver = co.setup();
        driver.get(conf.getValues("url"));
        Allure.step("User Navigate to the url :: "+conf.getValues("url"));
    }

    @Test(priority = 2)
    public void registration() throws Exception
    {
        co.click(conf.getValues("createACTag"));
        co.input(conf.getValues("FirstName"), "amar");
        co.input(conf.getValues("LastName"), "deep");
        co.input(conf.getValues("ReEmail"), conf.getValues("Email"));
        co.input(conf.getValues("RePass"), conf.getValues("Pass"));
        co.input(conf.getValues("ConfPaass"), conf.getValues("Pass"));
        co.click(conf.getValues("ReButton"));
        Thread.sleep(2000);
        Allure.step("User Added information to the Registered Form");
    }

    @Test(priority = 3)
    public void closingBrowser()
    {
        co.teardown();
        Allure.step("Closing Session for Registration Page");
    }
}
