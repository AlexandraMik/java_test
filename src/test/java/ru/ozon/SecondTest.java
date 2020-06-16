package ru.ozon;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.ozon.test.config.TestSettings;
import ru.ozon.test.listeners.TestListener;
import ru.ozon.test.pages.MainPage;

@Listeners({TestListener.class})
public class SecondTest extends TestSettings{

    @Test
    public void secondTest() {
        MainPage mainPage = new MainPage(driver, wait);
        mainPage.clickCityLabel();
        mainPage.inputCity();
        mainPage.checkNewCity();
    }
}
