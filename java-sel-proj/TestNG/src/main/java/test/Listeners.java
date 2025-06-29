package test;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {
    //Have to give idea to the testNG xml file where the Listeners is located.
    //It will be declared
    @Override
    public void onTestSuccess(ITestResult result){//this will execute after the test was passed, this was used mostly in reporting
       // System.out.println("Test was success");
    }
    @Override
    public void onTestFailure(ITestResult result){ //this will execute after the test case got failed
        System.out.println("The test was failed"+result.getName());
    }
}
