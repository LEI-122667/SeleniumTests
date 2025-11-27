package com.tests.selenium.seleniumtests;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class DynamicLoadingPage {
    public SelenideElement startButton = $("#start button");

    public SelenideElement loadedText = $("#finish h4");
}
