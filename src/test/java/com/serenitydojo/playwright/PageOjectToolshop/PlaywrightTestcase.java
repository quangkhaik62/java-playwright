package com.serenitydojo.playwright.PageOjectToolshop;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;

public abstract class PlaywrightTestcase {
    public static Playwright playwright;
    public static Browser browser;
    public static BrowserContext browserContext;
    Page page;

    @BeforeAll
    static void setupPlaywright(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)
                .setArgs(Arrays.asList("--no-sandbox", "--disable-extensions", "--disable-gpu")));
        browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(1920,1080));
    }

    @BeforeEach
    void setupPage(){
        page = browserContext.newPage();
        page.navigate("https://practicesoftwaretesting.com/");
        page.waitForLoadState(
                LoadState.NETWORKIDLE
        );
        playwright.selectors().setTestIdAttribute("data-test");
    }

//    @AfterEach
//    void teardownBrowser(){
//        browserContext.close();
//    }

    @AfterAll
    static void teardown(){
        browser.close();
        playwright.close();
    }
}
