package stepDefinition;
import Testcomponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.interactions.Actions;
import org.pageobject.*;
import org.testng.Assert;
public class Stepdefinition extends BaseTest
{
    public CatalogPage catalogPage;
    public ConformationPage conformationPage;
    @Given("^login using username (.+) and password (.+)$")
    public void logged_in_username_password(String username, String password)
    {
        loginPage.enterUserName(username);
        loginPage.enterPassword(password);
        catalogPage = loginPage.clickLogin();
    }

    @When("^I add product (.+) to cart$")
    public void I_add_product_to_cart(String productname) {
        try {
            catalogPage.clickAddtoCart(productname);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @When("^Checkout (.+) and submit the order$")
    public void checkout_and_submit(String productname) {
        CartPage cartPage = catalogPage.setClickCartItems();
        cartPage.visibilityOfTextInCartPage();
        boolean flag = cartPage.findAnyMatchInCartList(cartPage.returnAllCartProducts(), productname);
        Assert.assertTrue(flag);

        CheckoutPage checkoutPage = cartPage.goTOcheckOut();
        checkoutPage.setActionToTypeIndia(new Actions(driver), "india");
        conformationPage = checkoutPage.clickPlaceOrder(new Actions(driver));
    }

    @Then("{string} message is displayed on confirmation page")
    public void confirmation(String expectedMsg) {
        Assert.assertEquals(conformationPage.getConformationMsg(), expectedMsg);
    }
}
