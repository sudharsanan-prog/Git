package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class ExtendReporterNg {

    //paste Extent reports in maven repositiory and select the repository posted by apache and paste in pom.xml file
    // extent report was sensitive to dependency verion,
    //I am using jdk 24 and sdk 24 the compatible version is 5.0.9
    //If the version is not working fine try reducing the version and uninstall if using lambok or update to newer version
    public static ExtentReports report(){
        ExtentSparkReporter exReport = new ExtentSparkReporter("test-output/ExtentReport.html");
        exReport.config().setReportName("Selenium Java First Report");
        exReport.config().setDocumentTitle("Java Selenium Report");

        ExtentReports reports = new ExtentReports();
        reports.attachReporter(exReport);
        reports.setSystemInfo("Tester","Sudharsanan Rajavelu");
        //reports.createTest("name of the method") //this cannot be used in all the methods since we have n number of test cases
        //for the above instance we are using Listeners
        return reports;
    }

}
