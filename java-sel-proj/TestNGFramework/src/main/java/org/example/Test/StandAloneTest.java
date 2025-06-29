package org.example.Test;//package org.example.TestNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class StandAloneTest {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();//this will invoke the Chrome browser without using the local webDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //e com website link
        driver.get("https://rahulshettyacademy.com/client");

        //login functionality
        driver.findElement(By.cssSelector("input[id='userEmail']")).sendKeys("email07@example.com");
        driver.findElement(By.cssSelector("input[id='userPassword']")).sendKeys("Rahul@123");
        driver.findElement(By.cssSelector("input[id='login']")).click();

        //Wait functionality
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

        //selecting add to cart button in a card
        String ProductName = "ZARA COAT 3";
        //taking the whole card for a product
       List<WebElement> productList = driver.findElements(By.className("mb-3"));
       //this will take the first product in the products list using the findFirst method if not it will return null
       WebElement pro = productList.stream().filter(product->product.findElement(By.tagName("b"))
               .getText().equals(ProductName)).findFirst().orElse(null);
        assert pro != null;
        pro.findElement(By.cssSelector(".card-body button:last-of-type")).click();//this class name contains last-of type means this will select the last button type in the container

        //clicking on another add to cart button
        //un-interactable element is present in the website so need to wait for the next add to cart to click

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        //invisiblity of the element will create performance issue, so we can put generically above the code to reduce the execution timing
        //wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
        //This will increase the performance
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        //now we are using the routerlink to cart as a generic key
        driver.findElement(By.cssSelector("[routerlink*=cart]")).click();

        //Checking whether the product is added to the cart
        List<WebElement> cartProduct = driver.findElements(By.cssSelector("[class='cartSection'] h3"));
        // anyMatch- returns boolean value if the equalsIgnoreCase pass or fails
        boolean productExistence = cartProduct.stream().anyMatch(product->product.getText().equalsIgnoreCase(ProductName));
        Assert.assertTrue(productExistence);
        System.out.println("The product is existed: "+productExistence);
        //Clicking on checkout button
        //driver.findElement(By.cssSelector(".totalRow button")).click();
        WebElement checkout = driver.findElement(By.cssSelector(".totalRow button"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", checkout);


        //Checkout Page
        String value = driver.findElement(By.cssSelector("[class='user__name mt-5']")).getText();
        System.out.println("E-mail value: "+value);
        //select country
        driver.findElement(By.cssSelector("[class='form-group'] input")).sendKeys("Ind");

        List<WebElement>countryName = driver.findElements(By.cssSelector("[class='ta-item list-group-item ng-star-inserted']"));
        for(int i=0;i<countryName.size();i++){
            if(countryName.get(i).getText().equals("India")){

                Actions actions = new Actions(driver);
                actions.moveToElement(countryName.get(i)).click().perform();
                break;

            }
        }
        WebElement placeOrder = driver.findElement(By.cssSelector(".action__submit"));
        js.executeScript("arguments[0].click();", placeOrder);
        //countryName.stream().filter(countrySel->countrySel.getText().equalsIgnoreCase("India"));


        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1")));
        String ThankYouVerify = driver.findElement(By.cssSelector("h1")).getText();
        System.out.println("Thank you verification: "+ ThankYouVerify);
        Assert.assertTrue(ThankYouVerify.equalsIgnoreCase("thankyou for the order."));

        driver.quit();
    }
}


