package com.serenitydojo.playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimpleTestCase {
    @Test
    void checkTitle() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch();
        Page page = browser.newPage();

        page.navigate("https://github.com/quangkhaik62/java-playwright");

        String title = page.title();

        Assertions.assertTrue(title.contains("java-playwright"));

        System.out.println(title);

        browser.close();
        playwright.close();
    }
}
