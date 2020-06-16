package ru.ozon.test.listeners;

import ru.ozon.test.config.Screenshots;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class WebDriverEventListener extends AbstractWebDriverEventListener {

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        Screenshots.takeScreenshot();
    }

    @Override
    public void afterChangeValueOf(WebElement element,
                                   WebDriver driver,
                                   java.lang.CharSequence[] keysToSend){
        Screenshots.takeScreenshot();
    }
}