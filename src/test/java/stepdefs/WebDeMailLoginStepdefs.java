package stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import pages.LocatorsInterface;
import utils.BaseClass;

import java.awt.*;

import static pages.InformationClass.*;

public class WebDeMailLoginStepdefs extends BaseClass implements LocatorsInterface {
    @Given("User should be able to navigate to homePage")
    public void userShouldBeAbleToNavigateToHomePage() throws InterruptedException, AWTException {

        open(url);

        RobotClass(2);
        robotClass();
        RobotClass(2);
        robotClass();
        navigateToHomePage();



    }

    @When("The user should be able to fill the mail login form")
    public void theUserShouldBeAbleToFillTheMailLoginForm() throws InterruptedException, AWTException {


        clickTo(emailInput);
         sendKeysTo(emailInput,mailLoginInformationen);
         sendKeysTo(passwordInput,mailLoginPasword);
    }

    @Then("The user should be able to click the mail login button")
    public void theUserShouldBeAbleToClickTheMailLoginButton() {
        clickTo(buttonLogin);
    }

    @And("User should  be able to login successfully")
    public void userShouldBeAbleToLoginSuccessfully() {

        verifyElementContainsText(mailServiceWellcomeTitle,"Logout");
    }
}
