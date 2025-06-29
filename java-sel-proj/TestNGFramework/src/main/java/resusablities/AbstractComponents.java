package resusablities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pageObjects.OrderPage;

import java.time.Duration;
import java.util.List;

public class AbstractComponents {

     WebDriver driver;
    public AbstractComponents(WebDriver driver){
        this.driver=driver;
    }


    @FindBy(css = "[routerlink*='myorders']")
    WebElement myOrders;



    public OrderPage GetOrderHistoryPage(){
        myOrders.click();
        return new OrderPage(driver);
    }



    public void waitForTheElementToAppear(By findBy) {
        //Wait functionality
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }
    public void waitForTheWebElementToAppear(WebElement findBy) {
        //Wait functionality
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(findBy));
    }

    public void waitForTheElementToDisappear(WebElement findByDriver) {
        //Wait functionality
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(findByDriver));
    }

    public void waitForTheElementToBeClickable(WebElement findByDriver) {
        //Wait functionality
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(findByDriver));
    }

    public boolean textMatchUserIgnoreCaseBoolean(List<WebElement>listObject, String productText){
        return listObject.stream().anyMatch(prod->prod.getText().equalsIgnoreCase(productText));
    }

    public void clickFunctionUsingJavaScriptExecutor(By findBy){
        WebElement clickFunExecutor = driver.findElement(findBy);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", clickFunExecutor);

    }

}
