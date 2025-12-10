package org.pageobject;
import abstractcomponents.AbstractComponent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
public class LoginPage extends AbstractComponent
{
    WebDriver driver;

    WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));

    public LoginPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    //.ng-tns-c4-5.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error

    @FindBy(css = "[class*='flyInOut']")
    WebElement errorMessage ;

    @FindBy(id = "userEmail")
    WebElement username;


    @FindBy(id="userPassword")
    WebElement password;


    @FindBy(id = "login")
    WebElement login;

    @FindBy(css = "[routerlink$='/dashboard/myorders']")
    WebElement clickOderButton;



    public void enterUserName(String userName)
    {
        username.sendKeys(userName);
    }

    public void enterPassword(String userpassowrd)
    {
        password.sendKeys(userpassowrd);
    }

    public CatalogPage clickLogin()
    {
        login.click();
        return new CatalogPage(driver);
    }

    public void goTo(String url)
    {
        driver.get(url);
    }

    public String getErrorMessage()
    {
        waitForTheWebElementoAppear(errorMessage);
        return errorMessage.getText();
    }
    public OrderPage setClickOderButton()
    {

        OrderPage orderPage = new OrderPage(driver);
        clickOderButton.click();
        return orderPage;
    }
}
