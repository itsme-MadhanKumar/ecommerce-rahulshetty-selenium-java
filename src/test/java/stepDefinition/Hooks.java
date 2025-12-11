package stepDefinition;

import Testcomponents.BaseTest;
import io.cucumber.java.Before;
import java.io.IOException;

public class Hooks extends BaseTest
{

    @Before
    public void setup() throws IOException
    {
        loginPage = launchApplication();  // IMPORTANT
    }
}
