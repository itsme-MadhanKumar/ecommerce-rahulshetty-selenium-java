package stepDefinition;

import Testcomponents.BaseTest;
import io.cucumber.java.en.Given;
import org.pageobject.LoginPage;

import java.io.IOException;

public class Stepdefinition extends BaseTest
{
    public LoginPage loginPage;
    @Given("I landed on Rahulshetty ecommerce page")
    public void I_landed_on_Rahulshetty_ecommerce_page() throws IOException
    {
       loginPage= launchApplication();
    }

    @Given("^login using username (.+) and passowrd (.+)$")
    public void logged_in_username_password(String username,String passowrd)
    {
        loginPage.enterUserName(username);
        loginPage.enterPassword(passowrd);
    }

}
