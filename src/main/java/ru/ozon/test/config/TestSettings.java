package ru.ozon.test.config;

import ru.ozon.test.listeners.WebDriverEventListener;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.util.Properties;

public abstract class TestSettings {
    public static EventFiringWebDriver driver;
    public static WebDriverWait wait;
    public static Properties properties = new Properties();
    public static final String URL = "https://www.ozon.ru";
    public static String PHONE = "89603496975";


    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
        driver = new EventFiringWebDriver(new ChromeDriver());
        driver.manage().window().maximize();
        WebDriverEventListener eventListener = new WebDriverEventListener();
        driver.register(eventListener);
        wait = new WebDriverWait(driver, 10);
        driver.get(URL);
    }

    @AfterMethod
    public void close() {
        driver.quit();
    }
}
