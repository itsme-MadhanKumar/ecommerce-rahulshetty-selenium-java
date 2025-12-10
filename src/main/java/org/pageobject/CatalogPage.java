package org.pageobject;
import abstractcomponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
public class CatalogPage extends AbstractComponent
{
    WebDriver driver;
    public CatalogPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    //List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));


    //driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
    @FindBy(css = ".mb-3")
    List<WebElement> allProducts ;
    @FindBy(xpath = "div[class='card-body'] button:last-of-type")
    WebElement targetElement;

    @FindBy(css = ".ng-animating")
    WebElement gifInvisibility ;

    @FindBy(css = "[routerlink*='cart']")
    WebElement clickCartItems;


    //wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='toast-container']")));
    By productBy = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector("div[class='card-body'] button:last-of-type");
    By toastTimer = By.xpath("//div[@id='toast-container']");


    public List<WebElement> getProductsNames()
    {
        waitForTheElementoAppear(productBy);
        return allProducts;
    }
    public WebElement getTargetNameWebElement(String name)
    {
        return  getProductsNames().stream().filter(product -> product.findElement(By.xpath("//div//h5/b")).getText().equalsIgnoreCase(name)).findFirst().orElse(null);
    }
    public void clickAddtoCart(String productName) throws InterruptedException
    {
        getTargetNameWebElement(productName).findElement(addToCart).click();
        waitForTheElementoAppear(toastTimer);
        waitForInvisibilityofLocator(gifInvisibility);
    }

    public CartPage setClickCartItems()
    {
        clickCartItems.click();
        return new CartPage(driver);
    }
}
