package org.example;
import Testcomponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.pageobject.CartPage;
import org.pageobject.CatalogPage;
import org.pageobject.CheckoutPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class Test3 extends BaseTest
{
    @Test
    public void runTests() throws InterruptedException
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
    }
}
