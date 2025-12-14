package abstractcomponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
public class AbstractComponent
{
    WebDriver driver;
    WebDriverWait wait  = new WebDriverWait(driver,Duration.ofSeconds(5));
    public AbstractComponent(WebDriver driver)
    {
        this.driver = driver;
    }

    public void waitForTheElementoAppear(By locator)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public void waitForTheWebElementoAppear(WebElement element)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForInvisibilityofLocator(WebElement element) throws InterruptedException
    {
        Thread.sleep(2000L);
//        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void ActionTOMoveMouse(Actions actions,WebElement element)
    {
        actions.moveToElement(element).build().perform();
    }
}
