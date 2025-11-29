package sampler.datainput.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.junit5.AllureJunit5;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import sampler.datainput.pages.SimpleDataInputPage;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(AllureJunit5.class)
public class DataInputTest {

    private SimpleDataInputPage dataInputPage;

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        Configuration.timeout = 15000;
        Configuration.browser = "chrome";
        SelenideLogger.addListener("allure", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));
    }

    @BeforeEach
    public void setUp() {
        dataInputPage = new SimpleDataInputPage();

        dataInputPage.navigateToTextFieldDirectly();
    }

    @Test
    public void testDataInputComponent() {
        String text = "Selenide Test";

        dataInputPage.writeText(text);

        assertEquals(text, dataInputPage.getValue());
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}