package com.serenitydojo.playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import com.sun.jdi.connect.Connector;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import java.util.Comparator;
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
                .allMatch(price -> price > 0)
                .allSatisfy(price -> Assertions.assertThat(price)
                        .isGreaterThan(0.0)
                        .isLessThan(1000.0));

        List<String> ProductName = page.getByTestId("product-name")
                .allInnerTexts()
                .stream()
                .toList();
        ProductName.forEach(Pname -> System.out.println("Product name: " + ProductName));

        Assertions.assertThat(ProductName)
                .isNotEmpty();
    }


    @Test
    void SortProductAtoZ(){
        page.getByLabel("Sort").selectOption("Name (A - Z)");
        page.waitForLoadState(LoadState.NETWORKIDLE);

        List<String> productNamesAtoZ = page.getByTestId("product-name").allTextContents();
        System.out.println("Product Names: "+ productNamesAtoZ);

        Assertions.assertThat(productNamesAtoZ).isSortedAccordingTo(String.CASE_INSENSITIVE_ORDER);
        System.out.println("Sort A to Z");
    }

    @Test
    void SortProductZtoA(){
        page.getByLabel("Sort").selectOption("Name (Z - A)");
        page.waitForLoadState(LoadState.NETWORKIDLE);

        List<String> productNamesZtoA = page.getByTestId("product-name").allTextContents();
        System.out.println("Product Names: "+ productNamesZtoA);

        Assertions.assertThat(productNamesZtoA).isSortedAccordingTo(Comparator.reverseOrder());
        System.out.println("Sort Z to A");
    }

    @Test
    void SortPriceHightoLow(){
        page.getByLabel("Sort").selectOption("Price (High - Low)");
        page.waitForLoadState(LoadState.NETWORKIDLE);

        List<Double> PriceHightoLow = page.getByTestId("product-price")
                .allInnerTexts()
                .stream().map(price -> Double.parseDouble(price.replace("$", "")))
                .toList();
        System.out.println("Product Prices: "+ PriceHightoLow);

        Assertions.assertThat(PriceHightoLow).isSortedAccordingTo(Comparator.reverseOrder());
        System.out.println("High to Low");
    }

    @Test
    void SortPriceLowtoHigh(){
        page.getByLabel("Sort").selectOption("Price (Low - High)");
        page.waitForLoadState(LoadState.NETWORKIDLE);

        List<Double> PriceLowtoHigh = page.getByTestId("product-price")
                .allInnerTexts()
                .stream()
                .map(price -> Double.parseDouble(price.replace("$", "")))
                .toList();
        System.out.println("Prices Low to High: " + PriceLowtoHigh);

        Assertions.assertThat(PriceLowtoHigh)
                .isSorted();
        System.out.println("Low to High");
    }


}
