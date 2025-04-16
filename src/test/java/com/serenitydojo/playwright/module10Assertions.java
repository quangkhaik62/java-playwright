package com.serenitydojo.playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.junit.jupiter.api.*;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    void Module10() throws URISyntaxException {
        page.navigate(Toolshop);
        page.getByLabel("First name").fill("Kai");
        page.getByLabel("Last name").fill("Nguyen");
        page.getByLabel("Email address").fill("Kainguyen@aam1.com");
//        var webmaster = page.locator("select[id='subject']");
//        webmaster.selectOption("webmaster");
        var Messagefield = page.getByLabel("Message");
        Messagefield.fill("ipc@12IPCKCOI");
        Path uploadfile = Paths.get(ClassLoader.getSystemResource("testinput.txt").toURI());
        page.setInputFiles("#attachment", uploadfile);
        page.waitForTimeout(7000);
        page.locator(".btnSubmit").click();
//        playwright.selectors().setTestIdAttribute("data-test");
//        PlaywrightAssertions.assertThat(page.getByTestId("first-name-error")).containsText("First name is required");
        System.out.println("check assertions");
        PlaywrightAssertions.assertThat(page.locator("#subject_alert")).containsText("Subject is required");

    }



}
