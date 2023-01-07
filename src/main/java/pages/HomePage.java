package pages;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class HomePage extends Base{

    public HomePage(WebDriver driver)
    {
        super(driver);
    }
    // Global Variable in Home Page
    WebDriverWait wait;
    public String alertSuccessMessage;

    // Get elements with ID locator to interact with it for the Registration popup
    private final By signupLink = By.id("signin2");
    private final By userName = By.id("sign-username");
    private final By password = By.id("sign-password");
    private final By signupButton = By.xpath("//button[contains(@onclick,'register()')]");


    // Get elements with ID locator to interact with it for the Login popup
    private final By loginLink = By.id("login2");
    private final By loginUserName = By.id("loginusername");
    private final By loginPassword = By.id("loginpassword");
    private final By loginButton = By.xpath("//button[@type='button'][contains(.,'Log in')]");
    private final By homeButton = By.xpath("//a[@class='nav-link'][contains(.,'Home (current)')]");
    // Get elements for logged-in user
    private final By loggedInUser = By.id("nameofuser");

    // Get elements to handle product get product select on of them
    private final By allProducts = By.className("hrefch");


    // Create public method to click on signup button
    public void clickOnSignUpLink()
    {
        this.driver.findElement(this.signupLink).click();
    }

    // Create methods to send values into fields
    public void setUserName(String UserName)
    {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(this.userName).sendKeys(UserName);
    }

    public void setUserPassword(String Password)
    {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(this.password).sendKeys(Password);
    }

    public void clickOnSignupButton()
    {
        driver.findElement(this.signupButton).click();
    }

    public void clickOnalertButton()
    {
        try
        {
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.accept();
            alertSuccessMessage = alert.getText();
            Assert.assertTrue(alert.getText().contains("Sign up successful."));
        }
        catch (Exception e)
        {
            System.out.println("Error while handling Exception for to handle an alert"+e.getMessage());
        }
    }

    public void makeUserLogin(String loginUserName,String LoginPassword)
    {
        driver.findElement(loginLink).click();
        waitingPresent(this.loginUserName);
        driver.findElement(this.loginUserName).sendKeys(loginUserName);
        driver.findElement(this.loginPassword).sendKeys(LoginPassword);
        driver.findElement(this.loginButton).click();
    }

    public String checkIfUserLoggedIn()
    {
        waitingPresent(this.loggedInUser);
        return  driver.findElement(this.loggedInUser).getText();
    }

    public void navigateToHomePage()
    {
        waitingPresent(homeButton);
        driver.findElement(homeButton).click();
    }

    public boolean productsAppear()
    {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        scrollDown();
        waitingPresent(allProducts);
        List<WebElement> products = driver.findElements(allProducts);
        return products.size()>1;
    }

    // Start to handle functions to get all products and make sure if list of products not an empty
    public void selectRandomProduct()
    {
        List<WebElement> products = driver.findElements(allProducts);
        int index = 0;
        if (products.size() > 1)
        {
            // Get random product from list between 1 and products variable size
            Random random = new Random();
            index = random.nextInt(products.size() - 1);
            for (int i=0;i<products.size();i++)
            {
                if (i==index)
                {
                    products.get(i).click();
                }
            }
        }
        else if (products.size() <1)
        {
            System.out.println("list of products is empty");
        }


    }


}
