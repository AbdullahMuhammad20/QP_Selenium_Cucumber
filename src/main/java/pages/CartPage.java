package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends Base
{
    public String alertSuccessMessage;
    public CartPage(WebDriver driver)
    {
        super(driver);
    }

    private final By  placeOrder = By.xpath("//button[@type='button'][contains(.,'Place Order')]");
    private final By  orderName = By.id("name");
    private final By  orderCountry = By.id("country");
    private final By  orderCity = By.id("city");
    private final By  orderCredit = By.id("card");
    private final By  orderMonth = By.id("month");
    private final By  orderYear = By.id("year");
    private final By  purchaseButton = By.xpath("//button[@type='button'][contains(.,'Purchase')]");
    private final By  okButton = By.xpath("//button[@class='confirm btn btn-lg btn-primary'][contains(.,'OK')]");
    private final By  deleteButton = By.xpath("//a[@href='#'][contains(.,'Delete')]");

    public void deleteProduct()
    {
        waitingPresent(deleteButton);
        driver.findElement(deleteButton).click();
        driver.navigate().refresh();
    }

    public void proceedOrder(String name,String country,String city,String credit,String month,String year)
    {
        driver.findElement(placeOrder).click();
        // waiting until elements appear then start to enter values
        waitingPresent(orderName);
        driver.findElement(orderName).sendKeys(name);
        driver.findElement(orderCountry).sendKeys(country);
        driver.findElement(orderCity).sendKeys(city);
        driver.findElement(orderCredit).sendKeys(credit);
        driver.findElement(orderMonth).sendKeys(month);
        driver.findElement(orderYear).sendKeys(year);

        // Scroll down to can click on purchase button then click on it
        scrollDown();
        waitingPresent(purchaseButton);
        driver.findElement(purchaseButton).click();

        // waiting until confirmation popup is appears then click on OK button
        waitingPresent(okButton);
        driver.findElement(okButton).click();
    }

}
