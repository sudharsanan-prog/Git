package org.example.Test;//package org.example.TestNG;

import TestComponents.BaseClass;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class SubmitOrderTest extends BaseClass {
    String ctryName = "India";
    @Test(dataProvider = "loginData",groups = {"ErrorHandling"})
    public void product(HashMap<String,String> input) throws IOException {
        //login functionality
        //landpage object from BaseClass since we are using @BeforeMethod, it will execute the code at first
        //no necessity for defining the class in Test so we are creating variable for the Landpage globally and calling the variable
        landPage.loginApplication(input.get("userName"), input.get("password"));
        //Assert.assertEquals(landPage.getErrorMessage(), "Incorrect email or password.");
    }
    @Test(dataProvider = "loginData",groups = "ErrorHandling") //can also add multiple groups
    public void productValidation(HashMap<String,String> input) throws IOException, InterruptedException {

        //e com website link


        //login functionality
        //landpage object from BaseClass since we are using @BeforeMethod, it will execute the code at first
        //no necessity for defining the class in Test so we are creating variable for the Landpage globally and calling the variable
        ProductCatalogue prodCat = landPage.loginApplication(input.get("userName"), input.get("password"));//this was used to call the hashmap key so we are passing username and pass in hashmap and calling using key.
        //Assert.assertEquals(landPage.getErrorMessage(), "Incorrect email or password.");
        //Products Page
        //ProductCatalogue prodCat = new ProductCatalogue(driver);
        prodCat.getProductByName(input.get("productName"));
        prodCat.addProductToCart(input.get("productName"));

        //Add to Cart page
        MyCartPage myCart = new MyCartPage(driver);
        myCart.VerifyCart(input.get("productName"));

        //Checkout Page
        PaymentMethodPage payMethodPage = new PaymentMethodPage(driver);
        System.out.println("Value of Email: "+payMethodPage.getMailValue().getText());
        payMethodPage.getCountry(ctryName);
        payMethodPage.selectCountry(ctryName);

        //Order Placed Page
        String thankYouVerify = payMethodPage.getOrderPlacedText().getText();
        System.out.println(thankYouVerify);
        Assert.assertTrue(thankYouVerify.equalsIgnoreCase("thankyou for the order."));

        driver.quit();

    }

    //Creating another validation code to verify whether the product was ordered
    //so we have to go to the order history and check, for that the above test should be executed
    //so we are using dependsOnMethod to make the above test to be executed and after this should be.
    @Test(dataProvider = "loginData",dependsOnMethods = {"productValidation"}) //this will wait until the "productValidation" is executed
    public void orderHistory(HashMap<String,String> input){
        ProductCatalogue productcataloguePage = landPage.loginApplication(input.get("userName"),input.get("password"));
        OrderPage orderPage = productcataloguePage.GetOrderHistoryPage();
        Assert.assertTrue(orderPage.ValidateOrderByName(input.get("productName")));
    }



//providing all data into the test case
    @DataProvider(name = "loginData")
    public Object[][] getData() throws IOException {
//        //DataProvider accepts hashmap, we can use hashmap to provide the hashmap to
//        HashMap<Object,Object> hash = new HashMap<>();
//        hash.put("userName",username);
//        hash.put("Password",password);
//        hash.put("product", productName);
//        HashMap<Object,Object> hash1 = new HashMap<>();
//        hash1.put("userName","shetty@gmail.com");
//        hash1.put("Password","Iamking@000");
//        hash1.put("product", "ADIDAS ORIGINAL");

        //getting the Json from external file
        List<HashMap<String,String>> hash = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\main\\java\\org\\example\\Data\\purchaseOrder.json");

        return new Object[][]{
                {hash.get(0)},{hash.get(1)} //we can also put loop to run the file
//            {hash},{hash1}
//                //{"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"}//reference for how it is before hashmap
        };
    }
}


