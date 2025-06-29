package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resusablities.AbstractComponents;


public class LandingPage extends AbstractComponents {

    WebDriver driver;
    public LandingPage(WebDriver driver){
        super(driver); //this will give information of driver to the AbstractComponents(parent class)
        this.driver = driver;
        //Page factory will know the driver by initElements
        //this was given inside the constructor is the constructor will be executed at first
        PageFactory.initElements(driver,this);//assigning driver to local driver(this- referred to that)
    }

  //WebElement email  = driver.findElement(By.cssSelector("input[id='userEmail']")).sendKeys("email07@example.com");
  //WebElement password = driver.findElement(By.cssSelector("input[id='userPassword']")).sendKeys("Rahul@123");

  //page factory
  @FindBy(css = "input[id='userEmail']")
  WebElement userEmail;
  @FindBy(css = "input[id='userPassword']")
  WebElement passWord;
  @FindBy(css = "input[id='login']")
  WebElement logiN;
  @FindBy(css = "[class*='flyInOut']")
  WebElement errorMessage;


    //action method
    public ProductCatalogue loginApplication(String email, String password){
       userEmail.sendKeys(email);
        passWord.sendKeys(password);
        logiN.click();
        return new ProductCatalogue(driver);
    }

    public String getErrorMessage(){
        waitForTheWebElementToAppear(errorMessage); //this will get the whole driver.findElement(By.css="");
         return errorMessage.getText();
    }

    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client");
    }


}


