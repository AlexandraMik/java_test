package ru.ozon;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.ozon.test.config.TestSettings;
import ru.ozon.test.listeners.TestListener;
import ru.ozon.test.pages.ProductsPage;

@Listeners({TestListener.class})
public class ThirdTest extends TestSettings {
    @Test
    public void thirdTest() throws InterruptedException {
        ProductsPage productsPage = new ProductsPage(driver, wait);
        productsPage.openCatalog();
        productsPage.minPriceInput();
        productsPage.maxPriceInput();
        productsPage.priceCheck();
        productsPage.chooseCheap();
        productsPage.putJuicerInCart();
        productsPage.checkCartPrice();
    }
}
