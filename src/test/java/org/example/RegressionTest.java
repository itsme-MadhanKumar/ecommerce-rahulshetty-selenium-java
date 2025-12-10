package org.example;

import Testcomponents.BaseTest;
import Testcomponents.Retry;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.pageobject.CartPage;
import org.pageobject.CatalogPage;
import org.pageobject.CheckoutPage;
import org.pageobject.ConformationPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class RegressionTest extends BaseTest
{
    @Test
    public void login()
    {
        loginPage.enterUserName("maddy08@gmail.com");
        loginPage.enterPassword("Universe1234@");
        CatalogPage catalogPage = loginPage.clickLogin();
    }


    @Test(dependsOnMethods = "login",retryAnalyzer = Retry.class)
    public void addtocart() throws InterruptedException
    {
       // -------------------login page---------------
            loginPage.enterUserName("maddy08@gmail.com");
        loginPage.enterPassword("Universe1234@");
        CatalogPage catalogPage = loginPage.clickLogin();

//        -------------------Catalog Page--------------
        List<WebElement> products = catalogPage.getProductsNames();
        String productName ="ZARA COAT ";
        catalogPage.clickAddtoCart(productName);
        CartPage cartPage = catalogPage.setClickCartItems();
//        ---------------------Cart Page------------------------
        cartPage.visibilityOfTextInCartPage();
        List<WebElement> cartproducts = cartPage.returnAllCartProducts();
        boolean flag = cartPage.findAnyMatchInCartList(cartproducts,productName);
        Assert.assertTrue(flag);
        Actions actions = new Actions(driver);
        cartPage.moveMouse(actions);
        CheckoutPage checkoutPage = cartPage.goTOcheckOut();
    }

    @Test(dependsOnMethods = "addtocart")
    public void checkoutpage() throws InterruptedException
    {
      //  -------------------login page---------------
            loginPage.enterUserName("maddy08@gmail.com");
        loginPage.enterPassword("Universe1234@");
        CatalogPage catalogPage = loginPage.clickLogin();

//        -------------------Catalog Page--------------
        List<WebElement> products = catalogPage.getProductsNames();
        String productName ="ZARA COAT 3";
        catalogPage.clickAddtoCart(productName);
        CartPage cartPage = catalogPage.setClickCartItems();
//        ---------------------Cart Page------------------------
        cartPage.visibilityOfTextInCartPage();
        List<WebElement> cartproducts = cartPage.returnAllCartProducts();
        boolean flag = cartPage.findAnyMatchInCartList(cartproducts,productName);
        Assert.assertTrue(flag);
        Actions actions = new Actions(driver);
        cartPage.moveMouse(actions);
        CheckoutPage checkoutPage = cartPage.goTOcheckOut();

//        ----------------------------check out page---------------------------------
        String country = "india";
        checkoutPage.setActionToTypeIndia(actions,country);
        checkoutPage.setWaitforPopDown();
        checkoutPage.clickforIndia();
        ConformationPage conformationPage =checkoutPage.clickPlaceOrder(actions);
    }

    @Test(dependsOnMethods = "checkoutpage")
    public void conformationPage() throws InterruptedException
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
//        ---------------------Cart Page------------------------
        cartPage.visibilityOfTextInCartPage();
        List<WebElement> cartproducts = cartPage.returnAllCartProducts();
        boolean flag = cartPage.findAnyMatchInCartList(cartproducts,productName);
        Assert.assertTrue(flag);
        Actions actions = new Actions(driver);
        cartPage.moveMouse(actions);
        CheckoutPage checkoutPage = cartPage.goTOcheckOut();

//        ----------------------------check out page---------------------------------
        String country = "india";
        checkoutPage.setActionToTypeIndia(actions,country);
        checkoutPage.setWaitforPopDown();
        checkoutPage.clickforIndia();
        ConformationPage conformationPage =checkoutPage.clickPlaceOrder(actions);

        //--------------------------- conformation Page ------------------------------
        String conformation = conformationPage.getConformationMsg();
        Assert.assertEquals(conformation,"THANKYOU FOR THE ORDER.");
        System.out.println("Automation script run successfully");
    }
}
