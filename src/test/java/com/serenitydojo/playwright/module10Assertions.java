package com.serenitydojo.playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.junit.jupiter.api.*;

public class module10Assertions {
    public static Playwright playwright;
    public static Browser browser;
    public static BrowserContext browserContext;
    public static Page page;
    String Toolshop = "https://practicesoftwaretesting.com/contact";

    @BeforeAll
    public static void SetupBrowser(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(1920,1080));
    }
    @BeforeEach
    public void SetupPage(){
        page = browserContext.newPage();
    }
    @AfterAll
    public static void Teardown(){
        browser.close();
        playwright.close();
    }

    @DisplayName("Assertions-test")
    @Test
    void Module10(){
        page.navigate(Toolshop);
        page.locator(".btnSubmit").click();
//        playwright.selectors().setTestIdAttribute("data-test");
//        PlaywrightAssertions.assertThat(page.getByTestId("first-name-error")).containsText("First name is required");
        PlaywrightAssertions.assertThat(page.locator("#subject_alert")).containsText("Subject is required");


    }



}
