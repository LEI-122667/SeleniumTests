package com.tests.selenium.seleniumtests;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class FileUploadPage {

    // Elementos da página de File Upload do The Internet Heroku App
    public SelenideElement fileInput = $("#file-upload");
    public SelenideElement uploadButton = $("#file-submit");
    public SelenideElement uploadedFiles = $("#uploaded-files");
    public SelenideElement dragDropArea = $(".dz-clickable"); // Área de drag & drop
    public SelenideElement successMessage = $("h3"); // O título muda para "File Uploaded!"

    // URL da página
    public static final String URL = "https://the-internet.herokuapp.com/upload";
}