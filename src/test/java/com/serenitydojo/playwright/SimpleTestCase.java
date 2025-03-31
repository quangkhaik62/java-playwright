package com.serenitydojo.playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.junit.Options;
import com.microsoft.playwright.junit.OptionsFactory;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;

//@UsePlaywright(SimpleTestCase.MyOptions.class)
public class SimpleTestCase {
    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext browserContext;
    Page page;

    @BeforeAll
    public static void setupBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        browserContext = browser.newContext();
    }

    @BeforeEach
    public void startTest(){
        page = browserContext.newPage();
    }

    @AfterAll
    public static void teardown() {
        browser.close();
        playwright.close();
    }
    /*public static class MyOptions implements OptionsFactory {

        @Override
        public Options getOptions() {
            return new Options()
                    .setChannel("chrome")
                    .setHeadless(false)
                    .setLaunchOptions(
                            new BrowserType.LaunchOptions()
                                    .setArgs(Arrays.asList("--no-sandbox","--disable-extensions","--disable-gpu"))
                    );
        }
    }*/
    @Test
    void checkTitle() {

        page.navigate("https://github.com/quangkhaik62/java-playwright");
        String title = page.title();
        Assertions.assertTrue(title.contains("java-playwright"));
        System.out.println("Title is "+title);
    }

    @Test
    void interactwithelement() {
        page.navigate("https://practicesoftwaretesting.com/");
        page.locator("#search-query").fill("Pliers");
        page.locator("button:has-text('Search')").click();
        int numberofproduct = page.locator(".card").count();
        Assertions.assertTrue(numberofproduct > 0);
        System.out.println("Number of card is "+numberofproduct);
    }
}
