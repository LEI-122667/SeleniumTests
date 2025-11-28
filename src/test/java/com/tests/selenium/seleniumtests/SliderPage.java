package com.tests.selenium.seleniumtests;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;

public class SliderPage {

    // Elementos
    public SelenideElement slider = $("input[type='range']");
    public SelenideElement sliderValue = $("#range");
    public SelenideElement pageTitle = $("h3");

    // URL
    public static final String URL = "https://the-internet.herokuapp.com/horizontal_slider";

    public void moveSliderTo(double target) {
        double current = Double.parseDouble(sliderValue.getText());

        while (current < target) {
            slider.sendKeys(Keys.ARROW_RIGHT);
            current = Double.parseDouble(sliderValue.getText());
        }

        while (current > target) {
            slider.sendKeys(Keys.ARROW_LEFT);
            current = Double.parseDouble(sliderValue.getText());
        }
    }
}
