package org.example;
import Testcomponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.pageobject.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
public class DependsonTests extends BaseTest
{
    @Test(groups = {"dataprodidingGroup"} , dataProvider = "username&password")
    public void runLoginTest(HashMap<String,String> map) throws  InterruptedException
    {
        loginPage.enterUserName(map.get("username"));
        loginPage.enterPassword(map.get("password"));
        CatalogPage catalogPage = loginPage.clickLogin();
        String productName ="ZARA COAT 3";
        catalogPage.clickAddtoCart(map.get("name"));
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

    @Test(dependsOnMethods = {"runLoginTest"})
    public void verifyOrderDetails() throws IOException
    {
        loginPage.enterUserName("maddy09@gmail.com");
        loginPage.enterPassword("Universe1234@");
        loginPage.clickLogin();
       OrderPage orderPage =  loginPage.setClickOderButton();
        String productName ="ZARA COAT 3";
        boolean findMatchc= orderPage.checkProductsThatOrdered(productName);
        Assert.assertTrue(findMatchc);
    }

//    @DataProvider(name = "username&password")
//    public Object[][] getData()
//    {
//        Object[][] obj = new Object[2][2];
//        obj[0][0] = "maddy08@gmail.com";
//        obj[0][1] = "Universe1234@";
//        obj[1][0] = "maddy09@gmail.com";
//        obj[1][1] = "Universe1234@";
//        return obj;
//    }



//    @DataProvider(name = "username&password")
//    public Object[][] getdata()
//    {
//        HashMap<String,String> map = new HashMap<>();
//        map.put("email","maddy08@gmail.com");
//        map.put("password","Universe1234@");
//        map.put("name","ZARA COAT 3");
//
//        HashMap<String,String> map2 = new HashMap<>();
//        map2.put("email","maddy09@gmail.com");
//        map2.put("password","Universe1234@");
//        map2.put("name","ZARA COAT 3");
//
//        return new Object[][] {{map},{map2}};
//    }



    @DataProvider(name = "username&password")
    public Object[][] getdata() throws IOException
    {
        List<HashMap<String, String>> mapList = getData(System.getProperty("user.dir") + "\\src\\test\\java\\data\\Productlist\\ProductList.json");

        Object[][] data = new Object[mapList.size()][1];
        for (int i = 0; i < mapList.size(); i++) {
            data[i][0] = mapList.get(i);
        }
        return data;
    }
}
