package org.example.Test;//package org.example.TestNG;

import TestComponents.BaseClass;
import TestComponents.Retry;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.MyCartPage;
import pageObjects.OrderPlacedPage;
import pageObjects.PaymentMethodPage;
import pageObjects.ProductCatalogue;

import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class ErrorValidationTest extends BaseClass {

    @Test
    public void product() throws IOException {

        //e com website link
        String username = "email07@example.com";
        String password = "Rahul@12";
        //selecting add to cart button in a card
        String productName = "ZARA COAT 3";
        String ctryName = "India";

        //login functionality
        //landpage object from BaseClass since we are using @BeforeMethod, it will execute the code at first
        //no necessity for defining the class in Test so we are creating variable for the Landpage globally and calling the variable
        landPage.loginApplication(username, password);
        Assert.assertEquals(landPage.getErrorMessage(), "ncorrect email or password.");
    }
    @Test(retryAnalyzer = Retry.class)//this was called from the retry class
    public void productValidation() throws IOException, InterruptedException {

        //e com website link
        String username = "email07@example.com";
        String password = "Rahul@123";
        //selecting add to cart button in a card
        String productName = "ZARA COAT 3";
        String ctryName = "India";

        //login functionality
        //landpage object from BaseClass since we are using @BeforeMethod, it will execute the code at first
        //no necessity for defining the class in Test so we are creating variable for the Landpage globally and calling the variable
        landPage.loginApplication(username, password);
        //Assert.assertEquals(landPage.getErrorMessage(), "Incorrect email or password.");
        //Products Page
        ProductCatalogue prodCat = new ProductCatalogue(driver);
        prodCat.getProductByName(productName);
        prodCat.addProductToCart(productName);

        //Add to Cart page
        MyCartPage myCart = new MyCartPage(driver);
        myCart.VerifyCart(productName);

        //Checkout Page
        PaymentMethodPage payMethodPage = new PaymentMethodPage(driver);
        System.out.println("Value of Email: "+payMethodPage.getMailValue().getText());
        payMethodPage.getCountry(ctryName);
        payMethodPage.selectCountry(ctryName);

        //Order Placed Page
        OrderPlacedPage placedPage = new OrderPlacedPage(driver);
        String thankYouVerify = payMethodPage.getOrderPlacedText().getText();
        System.out.println(thankYouVerify);
        Assert.assertTrue(thankYouVerify.equalsIgnoreCase("thankyou for the order."));

        driver.quit();

    }
}


