package test;

import org.testng.annotations.*;

public class dayFour {
    //Loan

    @Test(groups = {"Smoke"})
    public void webLoginCarLoan(){
        //selenium
        System.out.println("webLoginCarLoanday4");
    }

    @Test
    public void webLoginCarLoanMoneyLaundry(){
        System.out.println("webLoginCarLoanMoneyLaundryday4");
    }


    //this will execute first for this class alone
    @Test
    public void webLoginCarLoanWithMoney(){
        System.out.println("webLoginCarLoanWithMoneyday4");
    }

    //this will execute last of this class alone
    @Test(groups = {"Smoke"})
    public void mobileLoginCarLoan(){
       //Appium
        System.out.println("mobileLoginCarLoanday4");
    }

    @Test
    public void LoginAPICarLoan(){
        //Rest API automation
        System.out.println("LoginAPICarLoanday4");

    }
}
