package com.serenitydojo.playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Module11ActiveITzone {
    public static Playwright playwright;
    public static Browser browser;
    public static BrowserContext brcontext;
    Page page;

    @BeforeAll
    public static void setupPlaywright(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setArgs(Arrays.asList("--no-sandbox","--disable-extensions","--disable-gpu")));
        brcontext = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(2560,1600));
    }

    @BeforeEach
    void setupPage(){
        page = brcontext.newPage();
        page.navigate("https://demo.activeitzone.com/ecommerce/handle-demo-login");
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    @AfterAll
    public static void Teardown(){
        brcontext.close();
        browser.close();
        playwright.close();
    }

    @DisplayName("Login Customer account")
    @Test
    void logincustomeraccount(){
        page.getByText("Login as Customer").click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        page.locator("button[onclick='autoFillCustomer()']").click();
        page.waitForTimeout(3000);

    }
}
