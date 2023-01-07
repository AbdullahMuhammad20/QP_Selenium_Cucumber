package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.CartPage;
import pages.HomePage;
import pages.ProductDetails;


public class Step {
    WebDriver driver;
    HomePage home;
    ProductDetails productDetails;
    CartPage cart;
    String usernameWelcome;

    @Given("user open the chrome browser")
    public void user_open_the_chrome_browser()
    {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
    }

    @When("user open the URL {string}")
    public void user_open_the_URL(String url)
    {
        driver.get(url);
    }

    @When("user has signup and login with username as {string} and password as {string}")
    public void user_has_signup_and_login_with_username_and_password(String name, String password)
    {
        home=new HomePage(driver);
        home.clickOnSignUpLink();
        home.setUserName(name);
        home.setUserPassword(password);
        home.clickOnSignupButton();
        home.clickOnalertButton();
        home.makeUserLogin(name,password);
        usernameWelcome = home.checkIfUserLoggedIn();
        Assert.assertTrue(usernameWelcome.contains("Welcome " + name));
    }

    @When("user has see Category has items")
    public void user_has_see_Category_has_items()
    {
        home = new HomePage(driver);
        Assert.assertTrue(home.productsAppear());
    }

    @When("user has add random item to cart")
    public void user_has_add_random_item_to_cart()
    {
        home=new HomePage(driver);
        home.selectRandomProduct();
        // Add product to cart
        productDetails = new ProductDetails(driver);
        productDetails.addProduct();
        productDetails.navigateToCart();
    }

    @When("user has delete item from cart")
    public void user_has_delete_item_from_cart()
    {
        // Delete item
        cart = new CartPage(driver);
        cart.deleteProduct();
        // Go back to home page again
        home=new HomePage(driver);
        home.navigateToHomePage();
        home.selectRandomProduct();
    }

    @Then("user has make checkout with random item")
    public void user_has_make_checkout_with_random_item()
    {
        // Add product to cart
        productDetails = new ProductDetails(driver);
        productDetails.addProduct();
        productDetails.navigateToCart();
        // Proceed Order
        cart = new CartPage(driver);
        cart.proceedOrder("Test Name","Test Country","Test City","Test Credit","Test Month","Test Year");
        driver.quit();
    }
}
