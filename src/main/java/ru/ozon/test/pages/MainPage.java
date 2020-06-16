package ru.ozon.test.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.ozon.test.config.TestSettings;

import static org.testng.Assert.assertEquals;
import static ru.ozon.test.config.Paths.*;

public class MainPage {
    WebDriver driver;
    WebDriverWait wait;
    WebElement cityElem;

    public MainPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    @Step("Login")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(LOGIN_XPATH)));
        driver.findElement(By.xpath(LOGIN_XPATH)).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ENTER_XPATH)));
        driver.findElement(By.xpath(ENTER_XPATH)).click();
    }
    @Step("Ввод номера")
    public void enterNumber () throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(PHONE_XPATH)));
        driver.findElement(By.xpath(PHONE_XPATH)).sendKeys(TestSettings.PHONE);
        driver.findElement(By.xpath(CODE_XPATH)).click();
        Thread.sleep(12000);
    }

    @Step("Проверка авторизации")
    public void checkAccountSettings() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SETTINGS_XPATH)));
        WebElement settings = driver.findElement(By.xpath(SETTINGS_XPATH));
        //Мы авторизовались, поэтому доступны настройки профиля
        assertEquals(settings.getAttribute("href"), TestSettings.URL + "/my/main");
    }

    @Step("Получить название города")
    public void clickCityLabel() {
        cityElem = driver.findElement(By.xpath(CITY_XPATH));
        cityElem.click();
    }

    @Step("Ввести название города")
    public void inputCity() {
        WebElement cityInput = driver.findElement(By.xpath(CITY_INPUT_XPATH));
        cityInput.click();
        cityInput.sendKeys(CITY);
        wait.until(ExpectedConditions.textToBe(By.xpath(CITY_MENU_XPATH), CITY_FULL));
        cityInput.sendKeys(Keys.DOWN);
        cityInput.sendKeys(Keys.ENTER);
    }

    @Step("Проверить новый город")
    public void checkNewCity() {
        wait.until(ExpectedConditions.textToBe(By.xpath(CITY_XPATH), CITY));
        cityElem = driver.findElement(By.xpath(CITY_XPATH));
        assertEquals(cityElem.getText(), CITY);
    }
}