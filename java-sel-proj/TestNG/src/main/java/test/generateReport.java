package test;//package test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class generateReport {
    ExtentReports reports;


    //paste Extent reports in maven repositiory and select the repository posted by apache and paste in pom.xml file
    // extent report was sensitive to dependency verion,
    //I am using jdk 24 and sdk 24 the compatible version is 5.0.9
    //If the version is not working fine try reducing the version and uninstall if using lambok or update to newer version
    @BeforeTest
    public void report(){
        ExtentSparkReporter exReport = new ExtentSparkReporter("test-output/ExtentReport.html");
        exReport.config().setReportName("Selenium Java First Report");
        exReport.config().setDocumentTitle("Java Selenium Report");

        reports = new ExtentReports();
        reports.attachReporter(exReport);
        reports.setSystemInfo("Tester","Sudharsanan Rajavelu");
    }

    @Test
    public void initialDemo(){
        reports.createTest("Initial Demo");
        System.setProperty("web-driver.chrome.driver", "C:\\Users\\S122\\Downloads\\courses\\java-selenium\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.rahulshettyacademy.com");
        System.out.println(driver.getTitle());
        driver.quit();
    }

    @AfterTest
    public void tearDown() {
        reports.flush();
    }
}

//import com.aventstack.extentreports.*;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import org.testng.annotations.*;
//
//public class generateReport {
//
//    ExtentReports extent;
//    ExtentTest test;
//
//    @BeforeSuite
//    public void setup() {
//        ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
//        extent = new ExtentReports();
//        extent.attachReporter(spark);
//    }
//
//    @Test
//    public void testPass() {
//        test = extent.createTest("Test Case 1 - Pass");
//        test.pass("This test passed!");
//    }
//
//    @Test
//    public void testFail() {
//        test = extent.createTest("Test Case 2 - Fail");
//        test.fail("This test failed!");
//    }
//
//    @AfterSuite
//    public void tearDown() {
//        extent.flush();
//    }
//}
//
