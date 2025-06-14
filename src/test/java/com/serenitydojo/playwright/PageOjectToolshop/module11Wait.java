package com.serenitydojo.playwright.PageOjectToolshop;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import java.util.List;

public class module11Wait extends PlaywrightTestcase{

    @Test
    void Waitfirst() {

        page.getByLabel("Sort").selectOption("Name (A - Z)");
//        page.waitForLoadState(LoadState.NETWORKIDLE);
        page.waitForSelector(".card-img-top");
        List<String> products = page.getByTestId("product-name").allInnerTexts().stream().toList();
        Assertions.assertThat(products).contains("Bolt Cutters");
    }

    @Test
    void Waitimplicit() {
        var filtergrinder = page.getByLabel("Grinder");
        filtergrinder.click();
        PlaywrightAssertions.assertThat(filtergrinder).isChecked();
        System.out.println("Grinder");
    }

    @Test
    void WaitwithSelector() {
        var filterHammer = page.getByLabel("Hammer");
        filterHammer.click();
//        PlaywrightAssertions.assertThat(filterHammer).isChecked();
        System.out.println("Hammer");

        List<String> ProductNames = page.getByTestId("product-name").allInnerTexts()
                .stream().toList();
        page.waitForSelector(".card-img-top", new Page.WaitForSelectorOptions().setTimeout(3000));
        Assertions.assertThat(ProductNames).contains("Claw Hammer");
    }

    @Test
    @DisplayName("appear & disappear 1")
    void waitAppear() {
        page.locator(".card-img-top[alt='Claw Hammer']").click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        page.locator("button[id='btn-add-to-cart']").click();

        PlaywrightAssertions.assertThat(page.getByRole(AriaRole.ALERT)).isVisible();
        PlaywrightAssertions.assertThat(page.getByRole(AriaRole.ALERT)).hasText("Product added to shopping cart.");

        page.waitForCondition(() -> page.getByRole(AriaRole.ALERT).isHidden());

    }

    @Test
    @DisplayName("appear & disappear 2")
    void waitTextcontentCart() {
        page.locator(".card-img-top[alt='Claw Hammer']").click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        page.locator("button[id='btn-add-to-cart']").click();

        page.waitForCondition(() -> page.getByTestId("cart-quantity").textContent().equals("1"));
    }

    @Test
    @DisplayName("API")
    void waitAPIrep(){

        page.waitForResponse("**/products?sort=name**", () -> page.getByLabel("Sort").selectOption("Name (A - Z)"));
        List<String> Productname = page.getByTestId("product-name").allInnerTexts();
        System.out.println("Name: " + Productname);


        page.waitForResponse("**/products?sort=price**", () -> page.getByLabel("Sort").selectOption("Price (High - Low)"));

        List<Double> PriceProduct = page.getByTestId("product-price")
                .allInnerTexts()
                .stream()
                .map( price -> Double.parseDouble(price.replace("$", "")))
                .toList();
        System.out.println("Price: " + PriceProduct);
        Assertions.assertThat(PriceProduct).isNotEmpty();



    }
}
