package com.serenitydojo.playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import java.util.List;

public class module11Wait {
    public static Playwright playwright;
    public static Browser browser;
    public static BrowserContext browserContext;
    Page page;

    @BeforeAll
    public static void SetupBrowser(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
        );
        browserContext = browser.newContext(
                new Browser.NewContextOptions()
                        .setViewportSize(1920,1080)
        );
    }

    @BeforeEach
    void SetupPage(){
        page = browserContext.newPage();
        page.navigate("https://practicesoftwaretesting.com/");
        playwright.selectors().setTestIdAttribute("data-test");

    }

    @AfterAll
    public static void Teardown(){
        browser.close();
        playwright.close();
    }

    @Test
    void Waitfirst(){

        page.getByLabel("Sort").selectOption("Name (A - Z)");
//        page.waitForLoadState(LoadState.NETWORKIDLE);
        page.waitForSelector(".card-img-top");
        List<String> products = page.getByTestId("product-name").allInnerTexts().stream().toList();


        Assertions.assertThat(products).contains("Bolt Cutters");
    }
}
