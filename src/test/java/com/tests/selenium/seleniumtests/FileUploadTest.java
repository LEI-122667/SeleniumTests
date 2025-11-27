package com.tests.selenium.seleniumtests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.jupiter.api.Assertions.*;

public class FileUploadTest {

    FileUploadPage fileUploadPage = new FileUploadPage();
    private File testFile;

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        Configuration.timeout = 10000;
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        // Criar um arquivo de teste temporário
        try {
            Path tempFile = Files.createTempFile("test_upload", ".txt");
            Files.write(tempFile, "Este é um arquivo de teste para upload".getBytes());
            testFile = tempFile.toFile();
            System.out.println("Arquivo de teste criado: " + testFile.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException("Erro ao criar arquivo de teste", e);
        }
    }

    @AfterEach
    public void tearDown() {
        // Limpar arquivo temporário
        if (testFile != null && testFile.exists()) {
            testFile.delete();
        }
    }

    @Test
    public void testFileUploadSuccess() {
        // Navegar para a página de upload
        open(FileUploadPage.URL);
        System.out.println("Página carregada: " + title());

        // Verificar que estamos na página correta
        $("h3").shouldHave(text("File Uploader"));

        // Fazer upload do arquivo
        fileUploadPage.fileInput.uploadFile(testFile);
        System.out.println("Arquivo selecionado: " + testFile.getName());

        fileUploadPage.uploadButton.click();
        System.out.println("Botão de upload clicado");

        // Verificar se o upload foi bem-sucedido
        fileUploadPage.successMessage.shouldHave(text("File Uploaded!"));
        fileUploadPage.uploadedFiles.shouldHave(text(testFile.getName()));

        System.out.println("Upload realizado com sucesso!");
        System.out.println("URL atual: " + url());
        System.out.println("Título da página: " + title());
    }

    @Test
    public void testFileUploadWithoutFile() {
        // Navegar para a página de upload
        open(FileUploadPage.URL);

        // Verificar que estamos na página correta
        $("h3").shouldHave(text("File Uploader"));

        // Tentar fazer upload sem selecionar arquivo
        fileUploadPage.uploadButton.click();

        // Verificar que permanece na mesma página (upload falhou)
        // O site pode mostrar uma mensagem de erro ou permanecer na mesma página
        System.out.println("Tentativa de upload sem arquivo - URL: " + url());

        // Verificação mais flexível - pode permanecer na mesma página OU mostrar erro
        boolean stayedOnUploadPage = url().equals(FileUploadPage.URL);
        boolean showsErrorMessage = $("body").getText().contains("error") ||
                $("body").getText().contains("select");

        assertTrue(stayedOnUploadPage || showsErrorMessage,
                "Should either stay on upload page or show error message");
    }

    @Test
    public void testUploadDifferentFileTypes() {
        // Testar com diferentes tipos de arquivo
        String[] fileTypes = {".txt", ".jpg", ".png"};

        for (String extension : fileTypes) {
            File typeTestFile;
            try {
                Path tempFile = Files.createTempFile("test_file", extension);
                Files.write(tempFile, ("Conteúdo do arquivo " + extension).getBytes());
                typeTestFile = tempFile.toFile();
            } catch (IOException e) {
                continue; // Pular se não conseguir criar o arquivo
            }

            try {
                open(FileUploadPage.URL);

                fileUploadPage.fileInput.uploadFile(typeTestFile);
                fileUploadPage.uploadButton.click();

                // Verificar upload bem-sucedido
                fileUploadPage.successMessage.shouldHave(text("File Uploaded!"));
                fileUploadPage.uploadedFiles.shouldHave(text(typeTestFile.getName()));

                System.out.println("Upload de " + extension + " bem-sucedido!");

            } finally {
                if (typeTestFile != null && typeTestFile.exists()) {
                    typeTestFile.delete();
                }
            }
        }
    }

    @Test
    public void testUploadMultipleTimes() {
        // Testar upload múltiplo do mesmo arquivo
        open(FileUploadPage.URL);

        // Primeiro upload
        fileUploadPage.fileInput.uploadFile(testFile);
        fileUploadPage.uploadButton.click();

        fileUploadPage.successMessage.shouldHave(text("File Uploaded!"));
        fileUploadPage.uploadedFiles.shouldHave(text(testFile.getName()));

        System.out.println("Primeiro upload realizado");

        // Voltar para fazer segundo upload
        open(FileUploadPage.URL);

        // Segundo upload
        fileUploadPage.fileInput.uploadFile(testFile);
        fileUploadPage.uploadButton.click();

        fileUploadPage.successMessage.shouldHave(text("File Uploaded!"));
        fileUploadPage.uploadedFiles.shouldHave(text(testFile.getName()));

        System.out.println("Segundo upload realizado com sucesso!");
    }
}