package PageSuite8;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class SamplerHomePage {

    // Correct selector for Basic Features navigation
    private final SelenideElement basicFeaturesLink = $x("//div[@class='gwt-HTML samplelink']//span[text()='Basic features']");
    private final SelenideElement basicFeaturesDiv = $x("//div[@class='gwt-HTML samplelink' and contains(., 'Basic features')]");

    public void openBasicFeatures() {
        System.out.println("Opening Basic Features section...");

        // Wait for page to load
        sleep(3000);

        // Click the Basic Features link
        if (basicFeaturesLink.exists()) {
            System.out.println("Found Basic features text, clicking parent div");
            basicFeaturesLink.click();
        } else if (basicFeaturesDiv.exists()) {
            System.out.println("Found Basic features div, clicking it");
            basicFeaturesDiv.click();
        } else {
            System.out.println("Basic Features element not found. Available sample links:");
            $$(".samplelink .title").forEach(title -> {
                System.out.println(" - " + title.getText());
            });
            throw new RuntimeException("Basic Features element not found");
        }

        // Wait for navigation to complete
        sleep(5000);
        System.out.println("Navigation to Basic Features completed");
    }
}