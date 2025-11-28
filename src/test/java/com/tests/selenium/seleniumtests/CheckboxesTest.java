package com.tests.selenium.seleniumtests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CheckboxesTest {

    CheckboxesPage page = new CheckboxesPage();

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        Configuration.headless = false;
    }

    @BeforeEach
    public void openPage() {
        open(CheckboxesPage.URL);
    }

    @Test
    public void testCheckbox1Toggle() {
        // checkbox1 começa desmarcado
        page.checkbox1.shouldNotBe(checked);

        // marca
        page.checkbox1.click();
        page.checkbox1.shouldBe(checked);

        // desmarca
        page.checkbox1.click();
        page.checkbox1.shouldNotBe(checked);
    }

    @Test
    public void testCheckbox2StartsChecked() {
        // checkbox2 começa marcado
        page.checkbox2.shouldBe(checked);

        // desmarca
        page.checkbox2.click();
        page.checkbox2.shouldNotBe(checked);

        // marca de novo
        page.checkbox2.click();
        page.checkbox2.shouldBe(checked);
    }

    @Test
    public void testPageTitle() {
        page.pageTitle.shouldHave(exactText("Checkboxes"));
    }
}
