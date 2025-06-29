package TestComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pageObjects.LandingPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseClass {

    public WebDriver driver;
    //Creating landing page here was after putting the @Before Method
    //Since the @Before method execute first then there is no need to define in the test case(code reduction)
    //so we are declaring the landing page object globally for the access of test case rather than method object calling
    public LandingPage landPage;


    public WebDriver initializeDriver() throws IOException {


        //getting browser data externally using properties
        Properties pro = new Properties();
        //we need "FileInputStream" to get the properties
        //System.getProperty is used to run the project in every computer without changing the path everytime
        //user.dir is used to give the path to the project and it is concat with the generic path.
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\GlobalData.properties");
        //FileInputStream fis = new FileInputStream("C:\\Users\\S122\\Downloads\\courses\\java-selenium\\java-sel-proj\\TestNGFramework\\src\\main\\java\\resources\\GlobalData.properties");
        pro.load(fis);

        //we put the if else statement using ternary operator
        //this takes the system input when we are giving from the terminal, if not it will take from the GlobalData.properties
        String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : pro.getProperty("browser");
        if (browserName.contains("chrome")) {
            //ChromeOptions perform multiple operation, we are using here for the running of browser in headless mode
            ChromeOptions options = new ChromeOptions();
            WebDriverManager.chromedriver().setup();//this will invoke the Chrome browser without using the local webDriver

            if (browserName.contains("headless")) {
                //this addArgumes which takes the headless mode along with the customized size
                //we are giving customized size, sometimes the browser may not behave correctly in headless mode, to overcome that we are using the customized dimensions
                //we can also give the customized size by below commented code which will take space so writing as compact for readability of the code
                // this.driver.manage().window().setSize(new Dimension(1920,1080));
                options.addArguments("--headless", "--window-size=1920,1080");
            }
            this.driver = new ChromeDriver(options);


        } else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();//this will invoke the Chrome browser without using the local webDriver
            this.driver = new EdgeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {

        driver = initializeDriver();
        landPage = new LandingPage(driver);
        landPage.goTo();
        return landPage;
    }

    @AfterMethod(alwaysRun = true)//this will trigger the method when we are running with groups method
    //since groups will run only the group so we are giving generically as always run
    public void tearDown() {
        driver.quit();
    }

    //this is used for the dataReader
    public List<HashMap<String, String>> getJsonDataToMap(String path) throws IOException {
        //reading the file from the source and giving the encoding format as second argument
        String jsonContent = FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);

        //Converting String to HashMap
        //For converting this we require Jackson DataBind dependency
        ObjectMapper mapper = new ObjectMapper();//this method is from the Jackson which provides classes for converting json to String
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
            //This will take the json in jsonContent and using TypeReference we are giving the 2d array to convert to Hashmap of string array and return as List
        });
        return data;
    }

    //Creating Screenshot Utils
    //since we are moving this from submitOrder to Baseclass driver has no ability
    //navigate to Listeners ->onfailed to continue
    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {

        //Taking Screenshot
        TakesScreenshot sc = (TakesScreenshot) driver;
        //Making screenshot output type
        File source = sc.getScreenshotAs(OutputType.FILE);
        //Giving path to the file to save in a designated folder(we are storing in the test-output\screenshot) -saving not completed
        String destinationPath = System.getProperty("user.dir") + "/test-output/screenshots/" + testCaseName + ".png";
        //File is necessary to make the output type recognizable
        File file = new File(destinationPath);
        //fileUtils make the recognizable file to the respective location to save
        FileUtils.copyFile(source, file);
        return destinationPath; // Return the actual file path
    }
}
