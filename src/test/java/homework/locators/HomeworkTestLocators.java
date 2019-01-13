package homework.locators;

public class HomeworkTestLocators {
    public static final String DRESSES_CATEGORY_ANCHOR = "//div[@id='block_top_menu']/ul/li/a[@title='Dresses']";
    public static final String DRESSES_CATEGORY_TITLE_WITH_DRESSES = "//div[@id='categories_block_left']/h2[@class='title_block']";
    public static final String FIRST_PRODUCT_CONTAINER = "//ul[contains(@class, 'product_list grid row')]/li/div[@class='product-container']";
    public static final String FIRST_PRODUCT_ADD_TO_CART_ANCHOR = "//ul[contains(@class, 'product_list grid row')]/li//a[@title='Add to cart']";
    public static final String PROCEED_TO_CHECKOUT = "//div[@class='button-container']/a[@title='Proceed to checkout']";
    public static final String EXPECTED_PRODUCT_IN_CART = "//tbody/tr";
}
