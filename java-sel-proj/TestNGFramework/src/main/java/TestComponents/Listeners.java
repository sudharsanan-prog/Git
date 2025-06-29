package TestComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import resources.ExtendReporterNg;

import java.io.IOException;

public class Listeners extends BaseClass implements ITestListener {
    ExtentTest test;  //this is used to log the result
    ExtentReports extent = ExtendReporterNg.report(); //it is returning extentReport in ExtentReporterNG class
    ThreadLocal<ExtentTest> threatLocal = new ThreadLocal<>(); //threadLocal which will create a unique id for the running testcases.
    //this unique will make parallel run test cases run without any disruption with the execution.

    @Override
    public void onTestStart(ITestResult result) { //ITestResult will hold the information of the execution and that will be retrieved to produce report
        test = extent.createTest(result.getMethod().getMethodName());//this will call the create test method in all the running test methods with its name
        //test will hold the connecting execution status
        threatLocal.set(test);//this will create a unique test id, according to this we are giving the same to all the Listeners
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // not implemented
        //test.log(Status.PASS,"Test Passed"); //this will log the status along with the comment mentioned while passing
        threatLocal.get().log(Status.PASS, "Test Passed"); //this will log the status along with the comment mentioned while passing
    }

    @Override
    public void onTestFailure(ITestResult result) {
        //this will print the error in the report as same as the console
        //test.fail(result.getThrowable()); commented because of extendThread
        threatLocal.get().fail(result.getThrowable());//this will run according to the id created while running which doesn't make conflict.
        //importing baseClass files
        BaseClass baseClass = new BaseClass();

        try {
            //this will retrieve the driver from the execution result, since driver mentioned in the baseclass doesn't have idea where driver comes from
            //so we are taking the driver information from the execution of class result
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e1) {
            e1.fillInStackTrace();
        }

        try {
            //This will get the screenshot method from the baseclass and providing the information to the html report to recognize the screenshot.
            baseClass.getScreenshot(result.getMethod().getMethodName(), driver);
            String relativePath = "screenshots/" + result.getMethod().getMethodName() + ".png"; // e.g., product.png

            /*
this will print the screenshot failure when relative path is not found and fall near the failed step
            test.fail("Screenshot of failure",
                    MediaEntityBuilder.createScreenCaptureFromPath(relativePath).build());
*/

            threatLocal.get().fail("Screenshot of failure",
                    MediaEntityBuilder.createScreenCaptureFromPath(relativePath).build());


        } catch (IOException e) {
            e.fillInStackTrace();
        }


    }

//    @Override
//    public void onTestFailure(ITestResult result) {
//       // test.log(Status.FAIL,"Test Failed");
//        test.fail(result.getThrowable());//this will print the error in the report while failing
//
//        BaseClass baseClass = new BaseClass();
//        String filePath;
//
//        //continuing from BaseClass
//        //the result parameter must have the knowledge about the driver so we are using that driver knowledge to the screenshot functionality
//        //By this driver getting class level we can get the knowledge about the driver
//        //this will be asked in interview
//        try {
//            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
//        } catch (Exception e1) {
//            e1.printStackTrace();
//        }
//        //class driver to Listeners
//        try {
//            filePath =  baseClass.getScreenshot(result.getMethod().getMethodName(),driver);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        //Screenshot
//        test.addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
//
//    }

    @Override
    public void onFinish(ITestContext context) {
        // flush will come
        extent.flush();
    }
}
