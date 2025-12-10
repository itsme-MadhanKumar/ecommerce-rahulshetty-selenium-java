package org.example;
import Testcomponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.pageobject.CartPage;
import org.pageobject.CatalogPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class Test2 extends BaseTest
{

    @Test
    public void TestwithWrongCredentials()
    {

        loginPage.enterUserName("maddy09@gmai.com");
        loginPage.enterPassword("Universe1234@");
        CatalogPage catalogPage =  loginPage.clickLogin();
        Assert.assertEquals("Incorrec email or password.",loginPage.getErrorMessage());
    }

    @Test
    public void runTest() throws  InterruptedException
    {
//        -------------------login page---------------
        loginPage.enterUserName("maddy08@gmail.com");
        loginPage.enterPassword("Universe1234@");
        CatalogPage catalogPage = loginPage.clickLogin();

//        -------------------Catalog Page--------------
        List<WebElement> products = catalogPage.getProductsNames();
        String productName ="ZARA COAT 3";
        catalogPage.clickAddtoCart(productName);
        CartPage cartPage = catalogPage.setClickCartItems();
    }
}
