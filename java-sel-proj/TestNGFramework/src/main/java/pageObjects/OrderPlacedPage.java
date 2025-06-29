package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resusablities.AbstractComponents;

public class OrderPlacedPage extends AbstractComponents {
    WebDriver driver;
    public OrderPlacedPage(WebDriver driver){
        super(driver); //this will give information of driver to the AbstractComponents(parent class)
        this.driver = driver;
        //Page factory will know the driver by initElements
        //this was given inside the constructor is the constructor will be executed at first
        PageFactory.initElements(driver,this);//assigning driver to local driver(this- referred to that)
    }








}
