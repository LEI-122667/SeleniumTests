package com.tests.selenium.seleniumtests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class NotificationTest {

    NotificationPage page = new NotificationPage();

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        Configuration.headless = false;
    }

    @BeforeEach
    public void openPage() {
        open(NotificationPage.URL);
    }

    @Test
    public void testNotificationAppears() {
        page.clickHereButton.click();

        // Verifica que a mensagem surgiu e contém um dos textos esperados
        page.message.shouldBe(visible)
                .should(matchText("Action (successful|unsuccessful|unsuccesful).*"));
    }

    @Test
    public void testPageTitle() {
        page.pageTitle.shouldHave(exactText("Notification Message"));
    }
}
