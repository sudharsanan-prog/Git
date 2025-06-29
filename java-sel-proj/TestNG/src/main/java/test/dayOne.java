package test;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class dayOne {

    @BeforeMethod
    public void webLoginCarMoneyBefore(){
        System.out.println("I will execute before all the @Test methods in this class.");


    }
    @AfterMethod
    public void afterMethod(){
        System.out.println("I will execute after all the @Test methods in this class.");
    }
    @Test
    public void Demo(){
        System.out.println("Hello");
        Assert.fail();
    }

    @Test
    public void newDemo(){
        System.out.println("World");
    }

}
