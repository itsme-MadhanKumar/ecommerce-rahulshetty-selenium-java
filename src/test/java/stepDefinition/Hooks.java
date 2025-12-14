package stepDefinition;
import Testcomponents.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import java.io.IOException;
public class Hooks extends BaseTest
{

    @Before
    public void setUp() throws IOException
    {
        loginPage = new BaseTest().launchApplication();
    }

    @After
    public void setDown()
    {
        driver.close();
    }
}
