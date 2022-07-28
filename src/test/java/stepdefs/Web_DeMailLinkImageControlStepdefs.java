package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LocatorsInterface;
import utils.BaseClass;

import java.awt.*;

import static pages.InformationClass.*;



public class Web_DeMailLinkImageControlStepdefs extends BaseClass implements LocatorsInterface {
    @Given("User should be able to navigate to home Page")
    public void userShouldBeAbleToNavigateToHomePage() throws InterruptedException, AWTException {
        open(webMailurl);


    }@When("All links in the home page should be properly defined")
    public void allLinksInTheHomePageShouldBeProperlyDefined() {

        verifyLinks(linkKontrol);
    }

    @Then("All image in the home page should be properly defined")
    public void allImageInTheHomePageShouldBeProperlyDefined() {
        selenium_broken_images(url);
    }


}
