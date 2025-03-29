package com.serenitydojo.playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
@UsePlaywright
public class SimpleTestCase {
    /*Playwright playwright;
    Browser browser;
    Page page;
    @BeforeEach
    void startPage() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
    }
    @AfterEach
    void teardown() {
        browser.close();
        playwright.close();
    }*/
    @Test
    void checkTitle(Page page) {

        page.navigate("https://github.com/quangkhaik62/java-playwright");
        String title = page.title();
        Assertions.assertTrue(title.contains("java-playwright"));
        System.out.println(title);
    }

    @Test
    void interactwithelement(Page page) {
        page.navigate("https://practicesoftwaretesting.com/");
        page.locator("#search-query").fill("Pliers");
        page.locator("button:has-text('Search')").click();
        int numberofproduct = page.locator(".card").count();
        Assertions.assertTrue(numberofproduct > 0);
        System.out.println(numberofproduct);
    }
}
