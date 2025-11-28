package TestSuite8;

import PageSuite8.BasicFeaturesPage;
import PageSuite8.SamplerHomePage;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class BasicFeaturesTest {

    @BeforeEach
    public void setup() {
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 15000;
        Configuration.pageLoadTimeout = 30000;
        Configuration.headless = false; // Keep visible for debugging
        open("https://demo.vaadin.com/sampler/");
    }

    @Test
    public void testBasicFeaturesNavigationAndInteraction() {
        System.out.println("=== STARTING BASIC FEATURES TEST ===");

        SamplerHomePage home = new SamplerHomePage();
        home.openBasicFeatures();

        BasicFeaturesPage basic = new BasicFeaturesPage();
        basic.verifyPageLoaded();

        System.out.println("=== TESTING BUTTON CLICK ===");
        basic.clickBasicButton();

        System.out.println("=== TESTING INPUT FIELD ===");
        String testText = "Hello Vaadin!";
        basic.writeInBasicInput(testText);

        // Take screenshot for verification
        screenshot("basic-features-test-completed");

        System.out.println("=== TEST COMPLETED SUCCESSFULLY ===");

        // Keep browser open to see results
        sleep(5000);
    }

    @Test
    public void quickNavigationTest() {
        System.out.println("Quick navigation test...");

        SamplerHomePage home = new SamplerHomePage();
        home.openBasicFeatures();

        System.out.println("Navigation successful!");
        sleep(3000);
    }
}