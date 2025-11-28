package TestSuite8;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class VerifyBasicFeaturesTest {

    @Test
    public void verifyBasicFeaturesPage() {
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
        open("https://demo.vaadin.com/sampler/");

        // Navigate to Basic Features
        $x("//div[@class='gwt-HTML samplelink']//span[text()='Basic features']").click();
        sleep(5000);

        System.out.println("=== BASIC FEATURES PAGE CONTENT ===");
        System.out.println("URL: " + webdriver().object().getCurrentUrl());
        System.out.println("Title: " + title());

        System.out.println("Buttons found:");
        $$("button").forEach(btn -> {
            System.out.println(" - '" + btn.getText() + "'");
        });

        System.out.println("Inputs found:");
        $$("input").forEach(input -> {
            System.out.println(" - placeholder: '" + input.getAttribute("placeholder") + "'");
        });

        // Keep browser open for manual inspection
        sleep(30000);
    }
}