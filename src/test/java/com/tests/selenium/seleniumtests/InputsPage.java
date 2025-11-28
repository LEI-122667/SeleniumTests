package com.tests.selenium.seleniumtests;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class InputsPage {

    // Elementos da página de Inputs
    public SelenideElement numberInput = $("input[type='number']");
    public SelenideElement pageTitle = $("h3");

    // URL da página
    public static final String URL = "https://the-internet.herokuapp.com/inputs";
}
