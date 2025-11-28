package com.tests.selenium.seleniumtests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class InputsTest {

    InputsPage page = new InputsPage();

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        Configuration.headless = false;
    }

    @BeforeEach
    public void setUp() {
        open(InputsPage.URL);
    }

    @Test
    public void testTypingNumbers() {
        // Verifica que o input está visível
        page.numberInput.shouldBe(visible);

        // Digita um número
        page.numberInput.setValue("12345");

        // Valida o valor digitado
        page.numberInput.shouldHave(value("12345"));
    }

    @Test
    public void testClearingInput() {
        page.numberInput.setValue("999");
        page.numberInput.clear();
        page.numberInput.shouldHave(value(""));
    }

    @Test
    public void testPageTitle() {
        // Verifica o título da página ("Inputs")
        page.pageTitle.shouldHave(exactText("Inputs"));
    }
}
