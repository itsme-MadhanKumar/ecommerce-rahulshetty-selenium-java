package org.pageobject;
import abstractcomponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
public class CheckoutPage extends AbstractComponent
{
    WebDriver driver;
    WebDriverWait wait;
    public CheckoutPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        this.wait =wait;
        PageFactory.initElements(driver,this);
    }

    // actions.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")),"india").build().perform();
    //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
//driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
    @FindBy(xpath = "//input[@placeholder='Select Country']")
    WebElement actionToTypeIndia;

    @FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
    WebElement clickIndia ;

    @FindBy(css = ".action__submit")
    WebElement clickPlaceOrfer;

    By waitforPopDown = By.cssSelector(".ta-results");

    public void setActionToTypeIndia(Actions actions,String country)
    {
        actions.sendKeys(actionToTypeIndia,"india").build().perform();
    }

    public void setWaitforPopDown()
    {
        waitForTheElementoAppear(waitforPopDown);
    }
    public void clickforIndia()
    {
        clickIndia.click();
    }

    public ConformationPage clickPlaceOrder(Actions actions)
    {
        //actions.moveToElement(driver.findElement(By.cssSelector(".action__submit"))).build().perform();
        //        driver.findElement(By.cssSelector(".action__submit")).click();
        ActionTOMoveMouse(actions,clickPlaceOrfer);
        clickPlaceOrfer.click();
        return new ConformationPage(driver);
    }

}
