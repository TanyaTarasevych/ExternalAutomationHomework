package homework;

import homework.locators.HomeworkTestLocators;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;

public class HomeworkTest {
    private WebDriver driver;
    @Before
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown(){
       driver.close(); // закрывает активную таб
       // driver.quit(); // закрывает программу - убивает драйвер
    }

    @Test
    public void openSiteTest(){
        driver.get("http://automationpractice.com");

        String expectedURL = "http://automationpractice.com";
        String actualURL = driver.getCurrentUrl();

        Assert.assertThat("This is not " + expectedURL, actualURL, containsString(expectedURL));
    }

    @Test
    public void verifyThatCategoryDressedIsOpened(){
        driver.navigate().to("http://automationpractice.com");

        WebElement categoryAnchor = driver.findElement(By.xpath(HomeworkTestLocators.DRESSES_CATEGORY_ANCHOR));
        categoryAnchor.click();

        WebElement title = driver.findElement(By.xpath(HomeworkTestLocators.DRESSES_CATEGORY_TITLE_WITH_DRESSES));

        Assert.assertThat("Category page not loaded", driver.getCurrentUrl(), is("http://automationpractice.com/index.php?id_category=8&controller=category"));
        Assert.assertThat("Title is not visible", title.isDisplayed(), is(true));
    }

    @Test
    public void verifyThatGoodsAddsToCart(){
        driver.navigate().to("http://automationpractice.com/index.php?id_category=8&controller=category");
        WebElement productContainer = driver.findElement(By.xpath(HomeworkTestLocators.FIRST_PRODUCT_CONTAINER));

        Actions builder = new Actions(driver);
        builder.moveToElement(productContainer).perform(); // Trigger mouseover event

        WebElement addToCartAnchor = driver.findElement(By.xpath(HomeworkTestLocators.FIRST_PRODUCT_ADD_TO_CART_ANCHOR));
        addToCartAnchor.click();

        try { // Wait for animation
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement proceedToCheckoutButton = driver.findElement(By.xpath(HomeworkTestLocators.PROCEED_TO_CHECKOUT));
        proceedToCheckoutButton.click();

        List<WebElement> cartElements = driver.findElements( By.xpath(HomeworkTestLocators.EXPECTED_PRODUCT_IN_CART));

        Assert.assertThat("Too many products in the cart", cartElements.size(), is(1));
        Assert.assertThat("Product is not in cart", cartElements.get(0).isDisplayed(), is(true));
    }
}
