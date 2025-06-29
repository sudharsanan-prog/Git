package test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class dayFive {

    @Test(dependsOnMethods = {"Book1","Books"})//this will execute after the Book1 method and will be executed at last
    public void Book(){
        System.out.println("This is a Book");
    }

    @Test(timeOut = 4000)//this will wait for 40 seconds if any output is not attaining within 10 seconds.
    public void Book1(){
        System.out.println("This is a Book1");
    }

    @Test
    public void Books(){
        System.out.println("There are books");
    }


    @Test(enabled = false)
    public void Book2(){
        System.out.println("This is a Book2");
    }
}
