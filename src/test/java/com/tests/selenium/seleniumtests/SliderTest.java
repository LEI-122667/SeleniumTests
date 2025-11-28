package com.tests.selenium.seleniumtests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SliderTest {

    SliderPage page = new SliderPage();

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        Configuration.headless = false;
    }

    @BeforeEach
    public void openPage() {
        open(SliderPage.URL);
    }

    @Test
    public void testMoveSliderTo4() {
        page.moveSliderTo(4.0);
        page.sliderValue.shouldHave(text("4"));
    }

    @Test
    public void testPageTitle() {
        page.pageTitle.shouldHave(exactText("Horizontal Slider"));
    }
}
