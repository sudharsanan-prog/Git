package test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class dayTwo {

    @AfterClass
    public void Book(){
        System.out.println("This is a Book");
    }

    @Test
    public void Book1(){
        System.out.println("This is a Book1");
    }

    @BeforeClass
    public void Book2(){
        System.out.println("This is a Book2");
    }
}
