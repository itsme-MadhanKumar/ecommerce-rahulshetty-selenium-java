package utilities.resources;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
public class ExtendReportNg
{
    public static ExtentReports getReport()
    {
        // ExtendSpark report , Entendrepots
        String path = System.getProperty("user.dir")+"//reports/index.html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(path);

        sparkReporter.config().setReportName("Madhan");
        sparkReporter.config().setDocumentTitle("Web Allplication");
        sparkReporter.config().setTheme(Theme.DARK);

        ExtentReports reports = new ExtentReports();
        reports.attachReporter(sparkReporter);
        reports.setSystemInfo("Tester","Madhan Kumar ");
       return reports;
    }
}