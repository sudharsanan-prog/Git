package test;

import org.checkerframework.checker.units.qual.A;
import org.testng.annotations.*;

public class dayThree {
    //Loan
    @Parameters({"URL","API"})
    @Test
    public void webLoginCarLoan(String url,String api){    //the parameter value will store in this url variable
        //selenium
        System.out.println("webLoginCarLoan");
        System.out.println(url);
        System.out.println(api);

    }

    @AfterSuite
    public void webLoginCarLoanMoneyLaundry(){
        System.out.println("I will execute After suit");
    }


    //this will execute first for this class alone
    @BeforeTest
    public void webLoginCarLoanWithMoney(){
        System.out.println("webLoginCarLoanWithMoney");
    }

    //this will execute last of this class alone
    @AfterTest
    public void mobileLoginCarLoan(){
       //Appium
        System.out.println("mobileLoginCarLoan");
    }

    @BeforeSuite
    public void LoginAPICarLoan(){
        //Rest API automation
        System.out.println("I will execute before suit");

    }
    @DataProvider(name = "loginData")
    public Object[][] getData() {
        return new Object[][] {   //for holding the data Object array is needed so we are creating an Object array
                {"username1", "password1"},//we are returning the values through the method
                {"username2", "password2"},
                {"username3", "password3"},
                {"username4", "password4"}
        };
    }
    @Test(dataProvider = "loginData")//creating a test and calling the dataprovider using the name
    public void testLogin(String username, String password) {//this will retrieve the data from the dataProvider
        System.out.println("Username: " + username + ", Password: " + password);
    }
}
