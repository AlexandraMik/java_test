package ru.ozon;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.ozon.test.config.TestSettings;
import ru.ozon.test.listeners.TestListener;
import ru.ozon.test.pages.MainPage;

@Listeners({TestListener.class})
public class FirstTest extends TestSettings {

    @Test
    public void firstTest() {
        MainPage mainPage = new MainPage(driver, wait);
        mainPage.clickLoginButton();
        try {
            mainPage.enterNumber();
        } catch (InterruptedException e) {}
        mainPage.checkAccountSettings();
    }
}