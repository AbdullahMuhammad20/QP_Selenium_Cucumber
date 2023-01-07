package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductDetails extends Base
{
    public String alertSuccessMessage;

    public ProductDetails(WebDriver driver)
    {
        super(driver);
    }

    // Get elements
    private final By btnAddProd = By.xpath("//a[@href='#'][contains(.,'Add to cart')]");
    private final By cartLink = By.xpath("//a[@class='nav-link'][contains(.,'Cart')]");
    public void addProduct()
    {
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        waitingPresent(btnAddProd);

        // add product to cart
        driver.findElement(btnAddProd).click();

        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        alertSuccessMessage = alert.getText();
        alert.accept();

    }
    public void navigateToCart()
    {
        waitingPresent(cartLink);
        driver.findElement(cartLink).click();
    }
}
