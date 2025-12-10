package org.pageobject;
import abstractcomponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
public class CartPage extends AbstractComponent
{
    WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
    public CartPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    //List<WebElement> cartproducts = driver.findElements(By.xpath("//div[@class='cartSection']//h3"));

    //WebElement button = driver.findElement(By.xpath("//li[@class='totalRow']//button"));
    @FindBy(xpath = "//div[@class='cartSection']//h3")
    List<WebElement> cartAddedProducts;

    @FindBy(xpath="//li[@class='totalRow']//button")
    WebElement checkOutButton;

    By carttoAppear = By.xpath("//button[@class='btn btn-primary'][contains(text(),'Checkout')]");
    //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='btn btn-primary'][contains(text(),'Checkout')]")));

    public void visibilityOfTextInCartPage()
    {
        waitForTheElementoAppear(carttoAppear);
    }
    public List<WebElement> returnAllCartProducts()
    {
        return cartAddedProducts;
    }
//    boolean flag = cartproducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
    public boolean findAnyMatchInCartList(List<WebElement> cartproducts ,String productName)
    {
        return cartproducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
    }

    public void moveMouse(Actions actions)
    {
        ActionTOMoveMouse(actions,checkOutButton);
    }

    public CheckoutPage goTOcheckOut()
    {
        checkOutButton.click();
        return new CheckoutPage(driver);
    }
}
