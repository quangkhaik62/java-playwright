package com.serenitydojo.playwright;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class module10Assertions {
    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;

    @BeforeAll
    public void SetupBrowser(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        browserContext = browser.newContext();
    }
    @BeforeEach
    public void SetupPage(){
        page = browserContext.newPage();
    }
    @AfterAll
    public void Teardown(){
        browser.close();
        playwright.close();
    }

}
