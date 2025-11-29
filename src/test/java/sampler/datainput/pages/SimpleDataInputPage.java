package sampler.datainput.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class SimpleDataInputPage {

    public void navigateToTextFieldDirectly() {
        open("https://demo.vaadin.com/sampler/#ui/datainput/textfield");

        $("input").shouldBe(visible);
    }

    public void writeText(String text) {
        $("input").setValue(text);
    }

    public String getValue() {
        return $("input").getValue();
    }
}