package com.ebay.search;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.ebay.base.page.BasePage;
import com.ebay.common.CustomWait;

import bsh.ParseException;

public class ProductSearch extends BasePage{

	public ProductSearch(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	private String productName;
	private String productPageNumber;
	private int productPriceTelevision;
	private int productPriceTelevisioninBasket;
	private int productPriceIphone;
	private int productsTotalPrice;
	private int productsTotalNewPrice;
	private String productPriceNumber;
	
	private enum productList {
		   MEDIA("televizyon"),
		   MOBILE("iphone");  
		   public String searchProductName;
		   private productList (String searchProductName) {
		        this.searchProductName = searchProductName;
		    }

		    public String toString() {
		        return searchProductName;
		    }
	}
	
	public enum pageNumber {
		   FIRST("1"),
		   SECOND("2"),  
		   THIRD("3"),
		   FOURTH("4"),
		   FIFTH("5");
		   public String paginationNumber;
		   private pageNumber (String paginationNumber) {
		        this.paginationNumber = paginationNumber;
		    }

		    public String toString() {
		        return paginationNumber;
		    }
	}
	
	/****Web Elements **/
	/** Input-Button-Dropdown-SidebarMenu */
	@FindBy(how = How.CSS, using = "input[id='search_word']") 
	private WebElement SEARCH_INPUT;
	
	@FindBy(how = How.CSS, using = "input[id='header_find_button']") 
	private WebElement SEARCH_BUTTON;
			
	private static String PAGINATION_PRODUCT_HREF = "a[href='/arama/?k=@productName&sf=@pageNumber']";
	
	@FindBy(how = How.CSS, using = "ul.products-container li:nth-child(5)") 
	private WebElement FIFTH_PRODUCT;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='col-center']/div[1]/div[1]/ul[2]/li[2]/ul/li[2]/div[3]/div[1]/strong")  /**Relative Xpath , not Absolute */
	private WebElement PRODUCT_PRICE;
	
	@FindBy(how = How.CSS, using = "div.total-price strong")  
	private WebElement PRODUCT_PRICE_IN_BASKET;
	
	@FindBy(how = How.CSS, using = "p[class='new-price']")  /**Relative Xpath , not Absolute */
	private WebElement TOTAL_PRODUCT_PRICE;
	
	@FindBy(how = How.CSS, using = "input[id='add-to-basket']") 
	private WebElement ADD_BASKET_BUTTON;
	
	@FindBy(how = How.CSS, using = "div[class='LightboxBCon']") 
	private WebElement FIXPACK_CONTAINER;
	
	private By CONTINUE_BASKET_BUTTON = By.cssSelector("a[id='ContinueToBasket']");

	@FindBy(how = How.CSS, using = "a[class='tooltip-close-button']") 
	private WebElement TOOLTIP_CLOSE_BUTTON;
	
	@FindBy(how = How.CSS, using = "div[id='wis-lightbox']") 
	private WebElement STAMP_LIGHTBOX;
	
	@FindBy(how = How.CSS, using = "button[type='button']") 
	private WebElement STAMP_CLOSE_BUTTON;
	
	@FindBy(how = How.CSS, using = "a[href='//www.gittigidiyor.com']") 
	private WebElement MAIN_PAGE;
	
	@FindBy(how = How.XPATH, using = "//div[3]/div[2]/div[3]/div/div[2]/div/a") 
	private WebElement DELETE_TELEVISION;
	
	@FindBy(how = How.CSS, using = ".btn-remove-item") 
	private WebElement DELETE_ALL_PRODUCT_IN_BASKET;
	
	/**** Functionality - GittiGidiyor **/
	/** Opens Login Page by Given URL */
	public ProductSearch getPage(String url) {
		logger.debug("Test Step: Navigate to login page");
		driver.get(url);
		return this;
	}

	
	public void clickMainPage() {
		MAIN_PAGE.click();
	}
	
	public boolean closeStamp() throws InterruptedException{
		
		if (STAMP_LIGHTBOX.isDisplayed()) {
			STAMP_CLOSE_BUTTON.click();
			logger.trace("Stamp is closed.");	
			Thread.sleep(2000);
			return true;		
		}
		else {
			return true;
		}
		
	}
	
	private void clickSearchInput() {
		CustomWait.waitUntilElementVisible(driver, SEARCH_INPUT);
		SEARCH_INPUT.click();
	}
	
	
	private void clickSearchButton() {
		CustomWait.waitUntilElementVisible(driver, SEARCH_BUTTON);
		SEARCH_BUTTON.click();
	}
	
	/**Search Products */
	
	private void searchProductName(String productCategoryName) {
		logger.trace("Searched Product is " + "" + productCategoryName);
		clickSearchInput();
		SEARCH_INPUT.sendKeys(productCategoryName);
		productName = productCategoryName;
		clickSearchButton();		
	}
	
	
	public void searchProduct1() {
		searchProductName(productList.MEDIA.toString());
	}
	
	
	public void searchProduct2() {
		searchProductName(productList.MOBILE.toString());
	}
	
	
	/** Click Page Number in Pagination 
	 * @return */
	private String clickProductPageNumber(String paginationProductPath) {
			
		logger.trace("Click " + productName + " " + "Pagination Number " + productPageNumber );
		driver.findElement(By.cssSelector(paginationProductPath)).click();
		return paginationProductPath;
		
	}
	
	public String findProductPaginationNumber(String pageNumber){
		String[][] replacements = {{"@productName", productName}, 
									{"@pageNumber", pageNumber}};
		
		productPageNumber =pageNumber;
		//loop over the array and replace		
		for(String[] replacement: replacements) {			
		PAGINATION_PRODUCT_HREF = PAGINATION_PRODUCT_HREF.replace(replacement[0], replacement[1]);				
		}	
		
		return clickProductPageNumber(PAGINATION_PRODUCT_HREF);
					
		}
	
	/** Select Fifth product */ 
		
	public void selectFifthProduct() {
		CustomWait.waitUntilElementVisible(driver, FIFTH_PRODUCT);
		FIFTH_PRODUCT.click();
	}
	
	
	
	/** Get-Change Product Price 
	 * @throws ParseException */
	private String changeProductPrice(String productPriceText) throws ParseException {	
		
		productPriceNumber = productPriceText.substring(0,
				productPriceText.length() - 3);
		
		String[][] replacements = {{",", ""}, 
								   {".", ""}};

		for(String[] replacement: replacements) {			
			productPriceNumber = productPriceNumber.replace(replacement[0], replacement[1]);	
			}
		return productPriceNumber;
	}
	
	public String findProductPrice() throws ParseException {
		TOOLTIP_CLOSE_BUTTON.click();
		CustomWait.waitUntilElementVisible(driver, PRODUCT_PRICE);
		String productPriceText = PRODUCT_PRICE.getText().toString();
		return changeProductPrice(productPriceText);
	}
	
	public void getTelevisionProductPrice() {
		productPriceTelevision = Integer.parseInt(productPriceNumber);
		logger.trace("Television Product Price is " + productPriceTelevision );
	}
	
	public void getIphoneProductPrice() {
		productPriceIphone = Integer.parseInt(productPriceNumber);
		logger.trace("Iphone Product Price is " + productPriceIphone );
	}
	
	/** Add to Basket  
	 * @return */
	public void AddtoBasket() {
		clickAddtoBasket();
		clickContinueBasket();
	
	}
	
	public void clickAddtoBasket() {
		CustomWait.waitUntilElementClickable(driver, ADD_BASKET_BUTTON);
		ADD_BASKET_BUTTON.click();	
	}
	
	public void clickContinueBasket() {	
		CustomWait.waitUntilElementVisible(driver, FIXPACK_CONTAINER);
		driver.findElement(CONTINUE_BASKET_BUTTON).click();
	}
	
	/**  Check Product price with previous page price 
	 * @throws ParseException */
	public void getProductPriceTelevisioninBasket()  {
		CustomWait.waitUntilElementVisible(driver, PRODUCT_PRICE_IN_BASKET);
		String productPriceText = PRODUCT_PRICE_IN_BASKET.getText().toString();		
		String productPriceNumber = productPriceText.substring(0,
				productPriceText.length() - 3);
		
		String[][] replacements = {{",", ""}, 
								   {".", ""}};

		for(String[] replacement: replacements) {			
			productPriceNumber = productPriceNumber.replace(replacement[0], replacement[1]);	
			}	
		productPriceTelevisioninBasket = Integer.parseInt(productPriceNumber);
		logger.trace("Product Price in Basket " + productPriceTelevisioninBasket);
	}
	
	public boolean compareProductPrice(){
		
		if (productPriceTelevision == productPriceTelevisioninBasket) {
			logger.trace("Prices are same. Basket Price: " +productPriceTelevisioninBasket +" Product Price "+productPriceTelevision );
			return true;
		} else {
			logger.trace("Prices are not same. Basket Price: " +productPriceTelevisioninBasket +" Product Price "+productPriceTelevision );
			return false;
		}
	
	}
			
	/**Control Total Number of Price - iphone + television 
	 * @return 
	 * @throws ParseException */	
	public String findTotalNumberofProductsPrice() throws ParseException{
		String productPriceText = TOTAL_PRODUCT_PRICE.getText().toString();
		return changeProductPrice(productPriceText);		
	}
	
	public void getTotalNumberofProductsPrice() {
		productsTotalPrice = Integer.parseInt(productPriceNumber);
		logger.trace("Product Total Price is Iphone + Television  " + productsTotalPrice );
	}
	
	public boolean controlTotalNumberofPrice(){
		
		int totalPrice = productPriceTelevisioninBasket + productPriceIphone;
		if (productsTotalPrice == totalPrice ) {	
			logger.trace("Total Price is Basket  " + totalPrice);
			return true;
		}else {
			logger.trace("Total Price is Basket  " + totalPrice );
			return false;
		}
	}
	
	/** Delete Television in Basket */
	public void deleteTelevisioninBasket(){		
		DELETE_TELEVISION.click();
	}
	
	/**Control Total Price After Delete Television */
	public String findTotalNumberofNewProductsPrice() throws ParseException{
		String productPriceText = TOTAL_PRODUCT_PRICE.getText().toString();
		return changeProductPrice(productPriceText);		
	}
	
	public void getTotalNumberofNewProductsPrice() {
		productsTotalNewPrice = Integer.parseInt(productPriceNumber);
	}
	
	public boolean controlTotalNumberofPriceAfterDeleteTelevision(){
		
		int totalPrice = productsTotalPrice - productPriceTelevision;
		if (productsTotalNewPrice == totalPrice ) {	
			logger.trace("Total Price after delete Television  " + totalPrice);
			return true;
		}else {
			logger.trace("Total Price after delete Television  " + totalPrice );
			return false;
		}
	
	
	}
	
	/** Delete All Basket */
	public void deleteAllBaseket(){
		DELETE_ALL_PRODUCT_IN_BASKET.click();
	}
}
	
	









