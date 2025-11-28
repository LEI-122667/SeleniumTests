package com.tests.selenium.seleniumtests;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class NotificationPage {

    // Elementos
    public SelenideElement clickHereButton = $("a[href='/notification_message']");
    public SelenideElement message = $("#flash");
    public SelenideElement pageTitle = $("h3");

    // URL
    public static final String URL = "https://the-internet.herokuapp.com/notification_message_rendered";
}
