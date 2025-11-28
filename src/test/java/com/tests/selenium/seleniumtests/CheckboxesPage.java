package com.tests.selenium.seleniumtests;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CheckboxesPage {

    // Elementos da página Checkboxes
    public SelenideElement checkbox1 = $("input[type='checkbox']:nth-of-type(1)");
    public SelenideElement checkbox2 = $("input[type='checkbox']:nth-of-type(2)");
    public SelenideElement pageTitle = $("h3");

    // URL da página
    public static final String URL = "https://the-internet.herokuapp.com/checkboxes";
}
