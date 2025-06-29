package org.example.StepDefinition;

import TestComponents.BaseClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageObjects.LandingPage;
import pageObjects.MyCartPage;
import pageObjects.PaymentMethodPage;
import pageObjects.ProductCatalogue;

import java.io.IOException;

public class StepDefinitionImp extends BaseClass {

    public LandingPage landingPage;

    public String ctryName = "India";

    //@Given tag is used to describe the where to start.
    //Since there were no key value pairs dependency in this statement so we don't require any additional symbols
    @Given("I landed on eCommerce Page")
    public void I_landed_on_eCommerce_Page() throws IOException {
        //Launching the Application
        landingPage = launchApplication();
    }

    //for denoting the key value pairs we have to put "^-in start and $ in end"
    //for denoting strings we use (.+), while defining in method we use the arguments, it takes argument according to the statement
    //here after username (.+) is present, in method username is defined, it will match the first scenario with first argument
    @Given("^Logged in with username (.+) and Password (.+)")
    public void Logged_in_with_username_and_Password(String username, String password){
      landPage.loginApplication(username,password);

    }
    //
    @When("^I add (.+) from cart$")
    public void I_add_productName_from_cart(String productName) throws InterruptedException {
        ProductCatalogue prodCat = new ProductCatalogue(driver);
        prodCat.getProductByName(productName);
        prodCat.addProductToCart(productName);
    }

    @And("^Checkout (.+) and submit the order$")
    public void And_Checkout_productName_and_submit_order(String productName){
        //Add to Cart page
        MyCartPage myCart = new MyCartPage(driver);
        myCart.VerifyCart(productName);

        //Checkout Page
        PaymentMethodPage payMethodPage = new PaymentMethodPage(driver);
        System.out.println("Value of Email: "+payMethodPage.getMailValue().getText());
        payMethodPage.getCountry(ctryName);
        payMethodPage.selectCountry(ctryName);
    }

    @Then("{string} message is displayed")
    public void OrderPlaced_message_is_displayed(String string){
        //Order Placed Page
        PaymentMethodPage payMethodPage = new PaymentMethodPage(driver);
        String thankYouVerify = payMethodPage.getOrderPlacedText().getText();
        System.out.println(thankYouVerify);
        Assert.assertTrue(thankYouVerify.equalsIgnoreCase(string));
    }

    @Then("{string} message was displayed.")
    public void Incorrect_email_password_error_message(String string){
        //Order Placed Page
        Assert.assertEquals(landPage.getErrorMessage(), "Incorrect email or password.");
    }

}
