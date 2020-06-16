package ru.ozon.test.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ozon.test.config.Paths;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ProductsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public ProductsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }
    @Step("Перейти в соковыжималки")
    public void openCatalog() {
        driver.get(Paths.URL_PRODUCTS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Paths.MIN_PRICE_INPUT_XPATH)));
    }

    @Step("Установить минимальную цену")
    public void minPriceInput() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Paths.MIN_PRICE_INPUT_XPATH)));
        WebElement element = driver.findElement(By.xpath(Paths.MIN_PRICE_INPUT_XPATH));
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys("3000");
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("[class=\"dots dots-blue\"]")));
        }
        catch(NoSuchElementException e) {}
        element.sendKeys(Keys.ENTER);
    }

    @Step("Установить максимальную цену")
    public void maxPriceInput() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Paths.MAX_PRICE_INPUT_XPATH)));
        closeCookie();
        WebElement element = driver.findElement(By.xpath(Paths.MAX_PRICE_INPUT_XPATH));
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys("4000");
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("[class=\"dots dots-blue\"]")));
        }
        catch(NoSuchElementException e) {}
        element.sendKeys(Keys.ENTER);
        Thread.sleep(2000);

    }
    @Step("Проверить диапазон цены")
    public void priceCheck() {
        closeCookie();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Paths.PRODUCTS_COST_XPATH)));
        WebElement element = driver.findElement(By.xpath(Paths.PRODUCTS_COST_XPATH));
        assertEquals(element.getText(), "Цена: от 3 000 до 4 000");
    }

    @Step("Отсортировать по цене")
    public void chooseCheap() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[role=\"combobox\"]")));
        WebElement element = driver.findElement(By.cssSelector("[role=\"combobox\"]"));
        element.click();
        element.sendKeys(Keys.ARROW_DOWN);
        element.sendKeys(Keys.ARROW_DOWN);
        element.sendKeys(Keys.ENTER);
    }
    @Step("Выбрать самую дешевую и добавить в корзину")
    public void putJuicerInCart() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Paths.INCART_XPATH)));
        WebElement firstJuicerCart = driver.findElement(By.xpath(Paths.INCART_XPATH));
        firstJuicerCart.click();
        WebElement cart = driver.findElement(By.cssSelector(Paths.CART_CSS));
        cart.click();
    }
    @Step("Проверить цену в корзине")
    public void checkCartPrice() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Paths.CART_XPATH)));
        String priceStr = driver.findElement(By.xpath(Paths.PRICE_FOR_ONE)).getText();
        priceStr = priceStr.substring(0, priceStr.length() - 2);
        priceStr = priceStr.replace(" ", "");
        double priceDouble = Double.parseDouble(priceStr);
        for (int i = 1; i < 5; i++) {
            driver.findElement(By.xpath(Paths.PLUS_XPATH)).sendKeys(Keys.ARROW_DOWN);
        }
        driver.findElement(By.xpath(Paths.PLUS_XPATH)).sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        String totalPriceStr = driver.findElement(By.xpath(Paths.FULL_PRICE)).getText();
        totalPriceStr = totalPriceStr.substring(0, totalPriceStr.length() - 2);
        totalPriceStr = totalPriceStr.replace(" ", "");
        double totalPriceDouble = Double.parseDouble(totalPriceStr);
        // Сравнение цены на сайте, с ориентировочной по вычислениям
        assertTrue(totalPriceDouble == 5 * priceDouble);
    }
    @Step("Установить минимальную мощность")
    public void minPowerInput() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Paths.MIN_POWER_INPUT_XPATH)));
        WebElement element = driver.findElement(By.xpath(Paths.MIN_POWER_INPUT_XPATH));
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys("1000");
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("[class=\"dots dots-blue\"]")));
        }
        catch(NoSuchElementException e) {}
        element.sendKeys(Keys.ENTER);
    }
    @Step("Проверить минимальную мощность")
    public void minPowerCheck() {
        closeCookie();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Paths.PRODUCTS_POWER_XPATH)));
        WebElement element = driver.findElement(By.xpath(Paths.PRODUCTS_POWER_XPATH));
        assertEquals(element.getText(), "Мощность, Вт: от 1 000 до 1 500");
    }
    private void closeCookie() {
        try {
            WebElement element = driver.findElement(By.xpath(Paths.COOKIE_XPATH));
            element.click();
        }
        catch(Exception e) {}
    }
}
