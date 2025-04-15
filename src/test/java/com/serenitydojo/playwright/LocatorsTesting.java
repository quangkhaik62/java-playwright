package com.serenitydojo.playwright;

import com.google.errorprone.annotations.Var;
import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.*;

import java.beans.Transient;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class LocatorsTesting {

    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext browserContext;
    static Page page;
    static String Toolshop = "https://practicesoftwaretesting.com";
    static String ContactOfShop = "https://practicesoftwaretesting.com/contact";


    @BeforeAll
    public static void setupbrowser(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setArgs(Arrays.asList("--start-fullscreen")));
        browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(1920,1080));
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
        page.navigate(Toolshop);
        page.getByAltText("Long Nose Pliers").click();
        PlaywrightAssertions.assertThat(page.getByText("MightyCraft Hardware")).isVisible();
    }

    @DisplayName("Text")
    @Test
    void elementbyText(){
        page.navigate(Toolshop);
        page.getByText("Combination Pliers").click();
        PlaywrightAssertions.assertThat(page.getByText("ForgeFlex Tools")).isVisible();
    }

    /*@DisplayName("Lable")
    @Test
    void elementbyLable(){
        page.navigate("https://practicetestautomation.com/practice-test-login/");
        page.getByLabel("Username").fill("student");
        page.getByLabel("Password").fill("Password123");
        //role button
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
        PlaywrightAssertions.assertThat(page.getByText("Congratulations student. You successfully logged in!")).isVisible();
    }
*/
    @DisplayName("Role")
    @Test
    void elementbyRoles(){
        page.navigate(Toolshop);
        page.getByLabel("Search").fill("Long");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Search")).click();
        PlaywrightAssertions.assertThat(page.getByText("Long Nose Pliers")).isVisible();
        page.waitForTimeout(3000);
    }

    @DisplayName("Test ID")
    @Test
    void elementByTestID(){
        page.navigate(Toolshop);
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Categories")).click();
        playwright.selectors().setTestIdAttribute("data-test");
        page.getByTestId("nav-special-tools").click();
        PlaywrightAssertions.assertThat(page.getByText("There are no products found")).isVisible();
        page.waitForTimeout(3000);
    }

    @DisplayName("CSS")
    @Test
    void elementByCSS(){
        page.navigate(Toolshop);
        page.locator("#search-query").fill("Hammer");
//        page.waitForTimeout(1000);
        page.locator("button[type='submit']").click();
//        page.waitForTimeout(2000);
        page.locator("img[alt='Thor Hammer']").click();
//        page.waitForTimeout(2000);
        page.locator("#btn-increase-quantity").click();
//        page.waitForTimeout(2000);
        PlaywrightAssertions.assertThat(page.locator("#quantity-input")).hasValue("2");
//        page.waitForTimeout(3000);
    }

    @Test
    void contactbutton() throws URISyntaxException {
        page.navigate(ContactOfShop);
        page.getByLabel("First name").fill("Kai");
        page.getByLabel("Last name").fill("Nguyen");
        page.getByLabel("Email address").fill("Kainguyen@aam1.com");
        var webmaster = page.locator("select[id='subject']");
        webmaster.selectOption("webmaster");
//        page.waitForTimeout(5000);
        var Messagefield = page.getByLabel("Message");
        Messagefield.fill("ipc@12IPCKCOI");
        Path uploadfile = Paths.get(ClassLoader.getSystemResource("testinput.txt").toURI());

        page.setInputFiles("#attachment", uploadfile);
        page.waitForTimeout(7000);


        
    }
}
