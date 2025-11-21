package com.tests.selenium.seleniumtests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.open;

public class DynamicLoadingTest {
    DynamicLoadingPage page = new DynamicLoadingPage();

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        Configuration.headless = false;
    }

    @BeforeEach
    public void setUp() {
        open("https://the-internet.herokuapp.com/dynamic_loading/1");
    }

    @Test
    public void testDynamicContent() {
        page.startButton.click();

        page.loadedText.shouldBe(Condition.hidden);

        page.loadedText.shouldHave(exactText(""));
    }
}
