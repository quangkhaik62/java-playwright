package com.serenitydojo.playwright;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class module9forms {
    static Playwright playwright;
    static Browser browser;
    static BrowserContext browserContext;
    static Page page;
    String Toolshop = "https://practicesoftwaretesting.com/";
    String ToolshopContact = "https://practicesoftwaretesting.com/contact";

    @BeforeAll
    public static void setupBrowser(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        browserContext = browser.newContext();
    }
    @BeforeEach
    public void OpenPage(){
        page = browserContext.newPage();
    }

    @AfterAll
    public static void CloseALL(){
        browser.close();
        playwright.close();
    }

    @Test
    void contactbutton() throws URISyntaxException {
        page.navigate(ToolshopContact);
        page.getByLabel("First name").fill("Kai");
        page.getByLabel("Last name").fill("Nguyen");
        page.getByLabel("Email address").fill("Kainguyen@aam1.com");
        var webmaster = page.locator("select[id='subject']");
        webmaster.selectOption("webmaster");
        var Messagefield = page.getByLabel("Message");
        Messagefield.fill("ipc@12IPCKCOI");
        Path uploadfile = Paths.get(ClassLoader.getSystemResource("testinput.txt").toURI());
        page.setInputFiles("#attachment", uploadfile);
        page.waitForTimeout(7000);
    }
}
