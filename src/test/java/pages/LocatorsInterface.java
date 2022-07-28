package pages;

import org.openqa.selenium.By;

import java.awt.*;

public interface LocatorsInterface {

    By linkKontrol = By.cssSelector("a");


    By emailInput = By.cssSelector("input#freemailLoginUsername");
    By passwordInput = By.id("freemailLoginPassword");
    By buttonLogin = By.xpath("//button[text()='Login']");



    By mailServiceWellcomeTitle = By.xpath("//span[text()='Logout']");


    By vornameInput = By.id("firstname-7d166885-fec4-44c0-bfef-bc50fb1e263a");
    By nachnameInput = By.id("lastname-7d166885-fec4-44c0-bfef-bc50fb1e263a");
    By unternehmenInput = By.id("company-7d166885-fec4-44c0-bfef-bc50fb1e263a");
    By bemerkungInput = By.id("message-7d166885-fec4-44c0-bfef-bc50fb1e263a");
    By telefonInput = By.id("phone-7d166885-fec4-44c0-bfef-bc50fb1e263a");

    By selectPosition= By.id("usu_job_seniority-7d166885-fec4-44c0-bfef-bc50fb1e263a");
    By selectDepartman= By.id("usu_job_department_role-7d166885-fec4-44c0-bfef-bc50fb1e263a");
    By selectCountry= By.id("aspera_country-7d166885-fec4-44c0-bfef-bc50fb1e263a");

By submitButton= By.cssSelector(" input.hs-button.primary.large");
 By errorMessage=By.cssSelector("label.hs-main-font-element");
 By socialMediaList=By.cssSelector("div.bottom__social>a");
}
