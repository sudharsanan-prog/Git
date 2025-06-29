package TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

//retry class is from the IRetryAnalyzer which is used to rerun the application once it is failed
public class Retry implements IRetryAnalyzer {

    int count = 0;
    int maxTry=1;

    @Override
    public  boolean retry(ITestResult result){
        if(count<maxTry){
            count++;
            return true;
        }
        return false;
    }

}
