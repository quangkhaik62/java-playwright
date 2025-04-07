package com.serenitydojo.playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.*;

import java.beans.Transient;
import java.util.Arrays;

public class LocatorsTesting {

    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext browserContext;
    Page page;

    @BeforeAll
    public static void setupbrowser(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setArgs(Arrays.asList("--start-fullscreen")));
        browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
    }

    @BeforeEach
    public void startbrowser(){
        page = browserContext.newPage();
    }

    @AfterAll
    public static void Teardown(){
        browser.close();
        playwright.close();
    }

    @DisplayName("Alt Text")
    @Test
    void elementbyAltText() {
        page.navigate("https://practicesoftwaretesting.com/");
        page.getByAltText("Long Nose Pliers").click();
        PlaywrightAssertions.assertThat(page.getByText("MightyCraft Hardware")).isVisible();
    }

    @DisplayName("Text")
    @Test
    void elementbyText(){
        page.navigate("https://practicesoftwaretesting.com/");
        page.getByText("Combination Pliers").click();
        PlaywrightAssertions.assertThat(page.getByText("ForgeFlex Tools")).isVisible();
    }

    @DisplayName("Lable")
    @Test
    void elementbyLable(){
        page.navigate("https://practicetestautomation.com/practice-test-login/");
        page.getByLabel("Username").fill("student");
        page.getByLabel("Password").fill("Password123");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
        PlaywrightAssertions.assertThat(page.getByText("Congratulations student. You successfully logged in!")).isVisible();
    }
}
