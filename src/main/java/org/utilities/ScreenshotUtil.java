package org.utilities;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtil {


    public void takeSnap(WebDriver driver, WebElement element) throws Exception {
        // Take a screenshot of the current web page
        ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'", element);
        Allure.addAttachment("Any text", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        //Getting Date and Time Format
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
        String formattedDate=myDateObj.format(myFormatObj);
        System.out.println(formattedDate);
        FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir") + "/Screenshots/" + formattedDate + ".png"));
    }

}
