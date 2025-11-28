package PageSuite8;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class BasicFeaturesPage {

    // Button selectors
    private final SelenideElement clickMeButton = $x("//button[contains(., 'Click me')]");
    private final SelenideElement vaadinButton = $("vaadin-button");
    private final SelenideElement anyVisibleButton = $x("//button[not(@style) or not(contains(@style, 'display: none'))]");

    // Better input selectors - specifically avoiding checkboxes
    private final SelenideElement textInput = $x("//input[@type='text']");
    private final SelenideElement anyTextInput = $x("//input[not(@type='checkbox') and not(@type='radio')]");
    private final SelenideElement inputByName = $x("//input[@placeholder]");
    private final SelenideElement vaadinTextField = $("vaadin-text-field");
    private final SelenideElement visibleInput = $x("//input[not(@type='hidden')]");

    public void clickBasicButton() {
        System.out.println("Looking for button to click...");

        sleep(2000);

        if (clickMeButton.exists()) {
            System.out.println("Found 'Click me' button");
            clickMeButton
                    .shouldBe(Condition.enabled, Duration.ofSeconds(5))
                    .click();
        } else if (vaadinButton.exists()) {
            System.out.println("Found vaadin-button");
            vaadinButton.click();
        } else if (anyVisibleButton.exists()) {
            System.out.println("Found visible button");
            anyVisibleButton.click();
        } else {
            debugCurrentPage();
            throw new RuntimeException("No button found to click");
        }

        sleep(1000);
        System.out.println("Button clicked successfully");
    }

    public void writeInBasicInput(String text) {
        System.out.println("Looking for text input field...");

        // Wait a bit for page to stabilize
        sleep(2000);

        // Try different text input selectors in order of preference
        if (textInput.exists() && textInput.isDisplayed()) {
            System.out.println("Found text input field");
            textInput
                    .shouldBe(Condition.visible, Duration.ofSeconds(5))
                    .setValue(text);
        } else if (vaadinTextField.exists()) {
            System.out.println("Found vaadin-text-field");
            vaadinTextField.$("input")
                    .shouldBe(Condition.visible, Duration.ofSeconds(5))
                    .setValue(text);
        } else if (inputByName.exists() && inputByName.isDisplayed()) {
            System.out.println("Found input with placeholder");
            inputByName
                    .shouldBe(Condition.visible, Duration.ofSeconds(5))
                    .setValue(text);
        } else if (anyTextInput.exists() && anyTextInput.isDisplayed()) {
            System.out.println("Found non-checkbox input");
            anyTextInput
                    .shouldBe(Condition.visible, Duration.ofSeconds(5))
                    .setValue(text);
        } else if (visibleInput.exists() && visibleInput.isDisplayed()) {
            System.out.println("Found visible input");
            visibleInput
                    .shouldBe(Condition.visible, Duration.ofSeconds(5))
                    .setValue(text);
        } else {
            debugCurrentPage();
            throw new RuntimeException("No suitable text input field found");
        }

        System.out.println("Text entered successfully: " + text);
    }

    public void verifyPageLoaded() {
        sleep(3000);
        System.out.println("Verifying Basic Features page is loaded...");

        String currentUrl = webdriver().object().getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);

        // Check if we have interactive elements
        boolean hasInteractiveElements = clickMeButton.exists() || textInput.exists() || anyVisibleButton.exists();

        if (!hasInteractiveElements) {
            debugCurrentPage();
            throw new RuntimeException("Basic Features page doesn't have expected interactive elements");
        }

        System.out.println("Basic Features page loaded successfully");
    }

    private void debugCurrentPage() {
        System.out.println("=== PAGE DEBUG INFO ===");
        System.out.println("URL: " + webdriver().object().getCurrentUrl());
        System.out.println("Title: " + title());

        System.out.println("All visible buttons:");
        $$("button").asFixedIterable().stream()
                .filter(btn -> btn.isDisplayed())
                .forEach(btn -> {
                    System.out.println(" - Button text: '" + btn.getText() + "'");
                });

        System.out.println("All visible inputs with details:");
        $$("input").asFixedIterable().stream()
                .filter(input -> input.isDisplayed())
                .forEach(input -> {
                    String type = input.getAttribute("type");
                    String placeholder = input.getAttribute("placeholder");
                    String id = input.getAttribute("id");
                    System.out.println(" - Input type: " + type + ", id: " + id + ", placeholder: " + placeholder);
                });

        System.out.println("Vaadin components:");
        $$("vaadin-*").forEach(comp -> {
            if (comp.isDisplayed()) {
                System.out.println(" - " + comp.getTagName());
            }
        });

        screenshot("page-debug");
    }
}