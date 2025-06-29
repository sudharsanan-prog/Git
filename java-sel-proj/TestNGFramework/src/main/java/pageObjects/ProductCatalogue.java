    package pageObjects;

    import org.openqa.selenium.By;
    import org.openqa.selenium.JavascriptExecutor;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.WebElement;
    import org.openqa.selenium.support.FindBy;
    import org.openqa.selenium.support.PageFactory;
    import org.openqa.selenium.support.ui.ExpectedConditions;
    import resusablities.AbstractComponents;

    import java.util.List;



    public class ProductCatalogue extends AbstractComponents {


        WebDriver driver;
        public ProductCatalogue(WebDriver driver){
            super(driver);
            this.driver = driver;
            //Page factory will know the driver by initElements
            //this was given inside the constructor is the constructor will be executed at first
            PageFactory.initElements(driver,this);//assigning driver to local driver(this- referred to that)
        }

      //page factory
      @FindBy(className = "mb-3")
        List<WebElement> productList;  //LIST will identify the findElements property

      @FindBy(css = "#toast-container")
              WebElement toastCont;
      @FindBy(css = "[routerlink*=cart]")
              WebElement cart;


    //this will be passed as parameters in methods
        By productsBy = By.cssSelector(".mb-3");
        By addToCart = By.cssSelector(".card-body button:last-of-type");
        By toastContainer = By.cssSelector("#toast-container");

        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));


        //action method
        public List<WebElement> getProducts(){
            waitForTheElementToAppear(productsBy);
            return productList;
        }

        public WebElement getProductByName(String productName){
            return getProducts().stream().filter(product->product.findElement(By.tagName("b"))
                    .getText().equals(productName)).findFirst().orElse(null);
        }
           //this class name contains last-of type means this will select the last button type in the container

//        public void addProductToCart(String productName) throws InterruptedException {
////
//
//            WebElement prod = getProductByName(productName);
//
//            ((JavascriptExecutor) driver).executeScript("document.body.style.zoom = 50%;");
//            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", prod.findElement(addToCart));
//            //wait.until(ExpectedConditions.elementToBeClickable(prod.findElement(addToCart)));
//            waitForTheElementToBeClickable(prod.findElement(addToCart));
//            prod.findElement(addToCart).click();
//
//            waitForTheElementToAppear(toastContainer);
//            //wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
//            waitForTheElementToDisappear(toastCont);
//
//           // ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", prod.findElement((By) cart));
//            waitForTheElementToBeClickable(cart);
//            cart.click();
//
//
//        }

public void addProductToCart(String productName) throws InterruptedException {
    WebElement product = getProductByName(productName);

    // Zoom out for better visibility
    ((JavascriptExecutor) driver).executeScript("document.body.style.zoom = '50%'");

    // Scroll to the 'Add to Cart' button
    WebElement addToCartButton = product.findElement(addToCart);
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartButton);

    // Wait and click 'Add to Cart'
    waitForTheElementToBeClickable(addToCartButton);
    addToCartButton.click();

    // Wait for toast notification to appear and disappear
    waitForTheElementToAppear(toastContainer);
    waitForTheElementToDisappear(toastCont);

    // Click on the cart
   // ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cart);
    waitForTheElementToBeClickable(cart);
    cart.click();
}



        public void goTo(){
            driver.get("https://rahulshettyacademy.com/client");
        }

    }

