package pages;

import org.openqa.selenium.By;

import java.awt.*;

public interface LocatorsInterface {

    By linkKontrol = By.cssSelector("a");

    By emailInput = By.cssSelector("input#freemailLoginUsername");
    By passwordInput = By.id("freemailLoginPassword");
    By buttonLogin = By.xpath("//button[text()='Login']");

    By mailServiceWellcomeTitle = By.xpath("//span[text()='Logout']");


}
