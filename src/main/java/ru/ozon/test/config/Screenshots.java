package ru.ozon.test.config;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Screenshots {

    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] takeScreenshot(){
        return ((TakesScreenshot) TestSettings.driver).getScreenshotAs(OutputType.BYTES);
    }
}