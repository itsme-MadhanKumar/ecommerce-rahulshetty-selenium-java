package Testcomponents;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.resources.ExtendReportNg;
import java.io.IOException;

public class Listeners  extends BaseTest implements ITestListener
{
    ExtentTest test;
    public ExtentReports reports = ExtendReportNg.getReport();

    ThreadLocal<ExtentTest> threadLocal = new ThreadLocal<>();
    public void onTestStart(ITestResult result)
    {
        // initilize entry point for Extentreport!! -  reports.createTest(testname)
        test = reports.createTest(result.getMethod().getMethodName());
        threadLocal.set(test);
    }

    public void onTestSuccess(ITestResult result)
    {
       test =  test.log(Status.PASS,result.getMethod().getMethodName()+" - Test Passed");
        threadLocal.set(test);
    }

    public void onTestFailure(ITestResult result)
    {
        test.log(Status.FAIL,result.getMethod().getMethodName()+" - Test Failed");
        threadLocal.get().fail(result.getThrowable());
        // take Screen Shoot and Attach to html file
        try
        {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        String location;
        try
        {
            location = takeScreenShoot(result.getMethod().getMethodName(),driver);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        threadLocal.get().addScreenCaptureFromPath(location,result.getMethod().getMethodName());

    }
    public void onFinish(ITestContext context)
    {
        reports.flush();
    }
}
