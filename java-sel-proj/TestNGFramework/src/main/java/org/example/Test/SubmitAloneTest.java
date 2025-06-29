package org.example.Test;//package org.example.TestNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import pageObjects.*;
import resusablities.AbstractComponents;

import java.time.Duration;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class SubmitAloneTest extends AbstractComponents {

    public SubmitAloneTest(WebDriver driver) {
        super(driver);
    }

    public static void main(String[] args) throws InterruptedException {


        WebDriverManager.chromedriver().setup();//this will invoke the Chrome browser without using the local webDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //e com website link


        String username = "email07@example.com";
        String password = "Rahul@123";
        //selecting add to cart button in a card
        String productName = "ZARA COAT 3";
        String ctryName = "India";


        //login functionality
        LandingPage landPage = new LandingPage(driver);
        landPage.goTo();
        landPage.loginApplication(username,password);


//        //Wait functionality
//        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

        //Product Catalogue
        //taking the whole card for a product
       //List<WebElement> productList = driver.findElements(By.className("mb-3"));
        ProductCatalogue prodCat = new ProductCatalogue(driver);
        List<WebElement> product = prodCat.getProducts();

       //this will take the first product in the products list using the findFirst method if not it will return null
//       WebElement pro = prodObj.getProducts().stream().filter(product->product.findElement(By.tagName("b"))
//               .getText().equals(ProductName)).findFirst().orElse(null);
//        assert pro != null;
//        pro.findElement(By.cssSelector(".card-body button:last-of-type")).click();//this class name contains last-of type means this will select the last button type in the container
        prodCat.getProductByName(productName);
        prodCat.addProductToCart(productName);
        //clicking on another add to cart button
        //un-interactable element is present in the website so need to wait for the next add to cart to click

        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        //invisiblity of the element will create performance issue, so we can put generically above the code to reduce the execution timing
        //wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
        //This will increase the performance
        //wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        //now we are using the routerlink to cart as a generic key
        //driver.findElement(By.cssSelector("[routerlink*=cart]")).click();

        //MyCartPage
        //Checking whether the product is added to the cart
        MyCartPage myCart = new MyCartPage(driver);
        myCart.VerifyCart(productName);

        //List<WebElement> cartProduct = driver.findElements(By.cssSelector("[class='cartSection'] h3"));
        // anyMatch- returns boolean value if the equalsIgnoreCase pass or fails
//        boolean productExistence = cartProduct.stream().anyMatch(prod->prod.getText().equalsIgnoreCase(productName));
//        Assert.assertTrue(productExistence);
//        System.out.println("The product is existed: "+productExistence);
        //Clicking on checkout button
        //driver.findElement(By.cssSelector(".totalRow button")).click();
//        WebElement checkout = driver.findElement(By.cssSelector(".totalRow button"));
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("arguments[0].click();", checkout);


        //Checkout Page
        PaymentMethodPage payMethodPage = new PaymentMethodPage(driver);
        System.out.println("Value of Email: "+payMethodPage.getMailValue().getText());
//        String value = driver.findElement(By.cssSelector("[class='user__name mt-5']")).getText();
//        System.out.println("E-mail value: "+value);
        //select country
        //driver.findElement(By.cssSelector("[class='form-group'] input")).sendKeys("Ind");
        payMethodPage.getCountry(ctryName);

//        List<WebElement>countryName = driver.findElements(By.cssSelector("[class='ta-item list-group-item ng-star-inserted']"));
//        for(int i=0;i<countryName.size();i++){
//            if(countryName.get(i).getText().equals("India")){
//
//                Actions actions = new Actions(driver);
//                actions.moveToElement(countryName.get(i)).click().perform();
//                break;
//
//            }
//        }
        payMethodPage.selectCountry(ctryName);


//        WebElement placeOrder = driver.findElement(By.cssSelector(".action__submit"));
//        js.executeScript("arguments[0].click();", placeOrder);
        //countryName.stream().filter(countrySel->countrySel.getText().equalsIgnoreCase("India"));


        //wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1")));
        String thankYouVerify = payMethodPage.getOrderPlacedText().getText();
        System.out.println(thankYouVerify);
//        String ThankYouVerify = driver.findElement(By.cssSelector("h1")).getText();
//        System.out.println("Thank you verification: "+ ThankYouVerifSubmitAloneTesty);
        Assert.assertTrue(thankYouVerify.equalsIgnoreCase("thankyou for the order."));

        driver.quit();
    }

}


