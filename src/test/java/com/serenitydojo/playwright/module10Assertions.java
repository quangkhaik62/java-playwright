package com.serenitydojo.playwright;

import com.microsoft.playwright.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import java.util.List;

public class module10Assertions {
    public static Playwright playwright;
    public static Browser browser;
    public static BrowserContext browserContext;
    public static Page page;
    String Toolshop = "https://practicesoftwaretesting.com";

    @BeforeAll
    public static void SetupBrowser(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(1920,1080));
    }
    @BeforeEach
    public void SetupPage(){

        page = browserContext.newPage();
        page.navigate(Toolshop);
        playwright.selectors().setTestIdAttribute("data-test");
        page.waitForCondition(() -> page.getByTestId("product-name").count() > 0);
    }
    @AfterAll
    public static void Teardown(){
        browser.close();
        playwright.close();
    }

    @DisplayName("Product Name")
    @Test
    void ProductPrice(){
        List<Double> prices = page.getByTestId("product-price")
                .allInnerTexts()
                .stream()
                .map(price -> Double.parseDouble(price.replace("$","")))
                .toList();
        prices.forEach(price -> System.out.println("Parsed price: $" + price));

        Assertions.assertThat(prices)
                .isNotEmpty()
                .allMatch(price -> price > 0);

        List<String> ProductName = page.getByTestId("product-name")
                .allInnerTexts()
                .stream()
                .toList();
        ProductName.forEach(Pname -> System.out.println("Product name: " + ProductName));

        Assertions.assertThat(ProductName)
                .isNotEmpty()





    }


}
