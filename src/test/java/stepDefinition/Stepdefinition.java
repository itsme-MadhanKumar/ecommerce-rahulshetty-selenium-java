package stepDefinition;
import Testcomponents.BaseTest;
import io.cucumber.java.en.*;
import org.openqa.selenium.interactions.Actions;
import org.pageobject.*;
import org.testng.Assert;
public class Stepdefinition extends BaseTest
{

    public CatalogPage catalogPage;
    public ConformationPage conformationPage;

    @Given("I landed on Rahulshetty ecommerce page")
    public void gettingStarted()
    {
        if(loginPage!=null)
        {
            System.out.println("Login page initiated");
        }
    }

    @Given("^login using username (.+) and password (.+)$")
    public void logged_in_username_password(String username, String password)
    {
        loginPage.enterUserName(username);
        loginPage.enterPassword(password);
        catalogPage = loginPage.clickLogin();
    }

    @When("^I add product (.+) to cart$")
    public void I_add_product_to_cart(String productname) throws InterruptedException
    {
        catalogPage.clickAddtoCart(productname);
    }

    @When("^Checkout (.+) and submit the order$")
    public void checkout_and_submit(String productname)
    {
        CartPage cartPage = catalogPage.setClickCartItems();
        Assert.assertTrue(
                cartPage.findAnyMatchInCartList(
                        cartPage.returnAllCartProducts(), productname
                )
        );
        Actions actions = new Actions(driver);
        cartPage.moveMouse(actions);
        CheckoutPage checkoutPage = cartPage.goTOcheckOut();
        checkoutPage.setActionToTypeIndia(new Actions(driver), "india");
        checkoutPage.setWaitforPopDown();
        checkoutPage.clickforIndia();
        conformationPage = checkoutPage.clickPlaceOrder(new Actions(driver));
    }

    @Then("{string} message is displayed on confirmation page")
    public void confirmation(String expectedMsg) {
        Assert.assertEquals(conformationPage.getConformationMsg(), expectedMsg);
    }



    @Then("{string} message is displayed on login page")
    public void message_is_displayed_on_confirmation_page(String expectedMessage)
    {
        String catchError = loginPage.getLoginError();
        Assert.assertEquals(catchError,"Incorrect email or password.");
//        driver.close();
    }
}


