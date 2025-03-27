package com.serenitydojo.playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimpleTestCase {
    @Test
    void checkTitle() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        page.navigate("https://github.com/quangkhaik62/java-playwright");
        String title = page.title();
        Assertions.assertTrue(title.contains("java-playwright"));
        System.out.println(title);

        browser.close();
        playwright.close();
    }

    @Test
    void interactwithelement() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://practicesoftwaretesting.com/");
        page.locator("#search-query").fill("Pliers");
        page.locator("button:has-text('Search')").click();
        int numberofproduct = page.locator(".card").count();
        Assertions.assertTrue(numberofproduct > 0);
        System.out.println(numberofproduct);

        browser.close();
        playwright.close();
    }
}
