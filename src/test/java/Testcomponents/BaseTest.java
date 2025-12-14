package Testcomponents;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.pageobject.LoginPage;
import org.testng.annotations.BeforeMethod;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
public class BaseTest
{
    public static WebDriver driver;
    public  static LoginPage loginPage;


    //1. Initilize webDriver
    //2. get data from Jsona
    //3. launch application
    //4. take screen shoot
    public WebDriver initilizeDriver() throws IOException
    {
        // create a properties class
        // create a inputStrem call and with location paramerter
        // load the properties class useing inputStema
        Properties properties = new Properties();
//        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"D:\\Intellij project location\\Selenium\\src\\main\\resources\\GlobalData.properties");
        FileInputStream fileInputStream = new FileInputStream(
                System.getProperty("user.dir") + "//src//main//resources//GlobalData.properties"
        );

        properties.load(fileInputStream);

        String browser= System.getProperty("browser")!=null ? System.getProperty("browser") : properties.getProperty("browser");
//        String browser =properties.getProperty("browser");

        if(browser.equalsIgnoreCase("chrome"))
        {
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
        }
        else if (browser.equalsIgnoreCase("edge"))
        {
             driver = new EdgeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

    public List<HashMap<String,String>> getData(String filepath) throws IOException
    {
        // Step 1 : convert json to string
        String file =  FileUtils.readFileToString(new File(filepath),
                StandardCharsets.UTF_8);

        // Step 2 : string to Jackson data binder
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String>> list =  mapper.readValue(file, new TypeReference<>(){});
        return list;
    }


    @BeforeMethod(alwaysRun = true)
    public LoginPage launchApplication() throws IOException
    {
        driver = initilizeDriver();
        loginPage = new LoginPage(driver);
        loginPage.goTo("https://rahulshettyacademy.com/client");
        return loginPage;
    }

    public String takeScreenShoot(String testcaseName, WebDriver driver) throws IOException
    {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        // Save screenshot inside reports/screenshots folder
        String folderPath = System.getProperty("user.dir") + "/reports/screenshots/";
        File directory = new File(folderPath);
        if (!directory.exists())
        {
            directory.mkdirs();
        }

        String fileName = testcaseName + "_" + System.currentTimeMillis() + ".png";
        String fullPath = folderPath + fileName;

        FileUtils.copyFile(source, new File(fullPath));

        // Return relative path for HTML
        return "screenshots/" + fileName;
    }

//    @AfterSuite
//    public void closeDriver()
//    {
//        driver.close();
//    }
}
