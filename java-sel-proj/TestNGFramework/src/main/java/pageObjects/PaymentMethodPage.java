package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resusablities.AbstractComponents;

import java.util.List;

public class PaymentMethodPage extends AbstractComponents {
    WebDriver driver;
    public PaymentMethodPage(WebDriver driver){
        super(driver); //this will give information of driver to the AbstractComponents(parent class)
        this.driver = driver;
        //Page factory will know the driver by initElements
        //this was given inside the constructor is the constructor will be executed at first
        PageFactory.initElements(driver,this);//assigning driver to local driver(this- referred to that)
    }

    @FindBy(css = "[class='user__name mt-5']")
    WebElement emailValue;

    @FindBy(css = "[class='form-group'] input")
    WebElement writeCountry;

    @FindBy(css = "[class='ta-item list-group-item ng-star-inserted']")
    List<WebElement> selectCtry;

    @FindBy(css = "h1")
    WebElement orderPlacedText;
//    @FindBy(css = ".action__submit")
//    WebElement submitBtn;

   By submitBtn = By.cssSelector(".action__submit");



    public WebElement getMailValue(){
        return emailValue;
    }

    public void getCountry(String countryName){
    writeCountry.sendKeys(countryName);
    }

    public OrderPlacedPage selectCountry(String countryName){
        for(int i=0;i<selectCtry.size();i++){
            if(selectCtry.get(i).getText().equals(countryName)) {
                Actions actions = new Actions(driver);
                actions.moveToElement(selectCtry.get(i)).click().perform();
                break;
            }
        }
       clickFunctionUsingJavaScriptExecutor(submitBtn);
        //OrderPlacedPage placedPage = new OrderPlacedPage(driver);
        return new OrderPlacedPage(driver);
    }

    public WebElement getOrderPlacedText(){
        return orderPlacedText;
    }


}
