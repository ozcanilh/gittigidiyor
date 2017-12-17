package com.ebay.test.search;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ebay.base.BaseTest;
import com.ebay.search.ProductSearch;
import com.ebay.search.ProductSearch.pageNumber;

/**
 * Unit test for simple App.
 */
public class ProductSearchTest extends BaseTest{
   
	/**
	 * Logger Object
	 */
	private static final Logger logger = LogManager.getLogger(BaseTest.class);
	
	/**
	 * SearchProduct Object
	 */	
	public ProductSearch onProductSearch;
	
	@BeforeClass
	public void testSetUp() throws Exception {
		
		logger.info("STARTING TEST: Browser is opening..");
		onProductSearch = new ProductSearch(driver);		
		onProductSearch.getPage(PRODUCTION_URL);
		onProductSearch.closeStamp();
	
	} 
	
	/**Search 'Televizyon' */
	@Test (priority = 1)
	public void searchProduct1() {	 
		 logger.trace("Test Step: Search 'Televizyon'");
		 onProductSearch.searchProduct1();	 			
	 }
	
	/**Select Fifth product of second page 
	 * @throws Exception */
	@Test (priority = 2)
	public void selectFifthProductofSecondPage() throws Exception {	 
		 logger.trace("Test Step: Select Fifth product of second page");
		 onProductSearch.findProductPaginationNumber(pageNumber.SECOND.toString());	 	
		 onProductSearch.selectFifthProduct();
	 }
	
	/**Get Price of Product - Television 
	 * @throws Exception */
	@Test (priority = 3)
	public void getPriceProductTelevision() throws Exception {	 
		 logger.trace("Test Step: Get Price of Product of Television");
		 onProductSearch.findProductPrice();
		 onProductSearch.getTelevisionProductPrice();	 
	 }
	
	/**Add to Basket - Television
	 * @throws Exception */
	@Test (priority = 4)
	public void addToBasketWithoutFixpackTelevision() throws Exception {	 
		 logger.trace("Test Step: Add to Basket without Fixpack");
		 onProductSearch.AddtoBasket();
		 
	 }
	
	/**Get Price of Product in Basket - Television
	 * @throws Exception */
	@Test (priority = 5)
	public void getPriceProductinBasketTelevision() throws Exception {	 
		 logger.trace("Test Step: Get Price of Product in Basket");
		 onProductSearch.getProductPriceTelevisioninBasket();	 
	 }
	
	/**Check product price - Television 
	 * @throws Exception */
	@Test (priority = 6)
	public void compareProductPriceTelevision() throws Exception {	 
		 logger.trace("Test Step: Check Product price with previous page price ");
		 Assert.assertTrue(onProductSearch.compareProductPrice());
		 
	 }
	
	/**Search 'iphone' */
	@Test (priority = 7)
	public void searchProduct2() {	 
		 logger.trace("Test Step: Search 'Televizyon'");
		 onProductSearch.clickMainPage();
		 onProductSearch.searchProduct2();	 			
	 }
	
	/**Select Fifth product of first page */
	@Test (priority = 8)
	public void selectFifthProductofFirstPage() {	 
		 logger.trace("Test Step: Select Fifth product of first page");	
		 onProductSearch.selectFifthProduct(); 			
	 }
	
	/**Get Price of Product - iphone 
	 * @throws Exception */
	@Test (priority = 9)
	public void getPriceProductIphone() throws Exception {	 
		 logger.trace("Test Step: Get Price of Product of Iphone");
		 onProductSearch.findProductPrice();
		 onProductSearch.getIphoneProductPrice();	 
	 }
	
	/**Add to Basket - iphone
	 * @throws Exception */
	@Test (priority = 10)
	public void addToBasketWithoutFixpackIphone() throws Exception {	 
		 logger.trace("Test Step: Add to Basket without Fixpack");
		 onProductSearch.AddtoBasket();	 
	 }
	
	/**Check Total Number of Price - iphone + television
	 * @throws Exception */
	@Test (priority = 11)
	public void checkTotalNumberofPriceIphoneTelevision() throws Exception {	 
		 logger.trace("Test Step: Check Total Number of Price iphone + television");
		 onProductSearch.findTotalNumberofProductsPrice();
		 onProductSearch.getTotalNumberofProductsPrice();
		 onProductSearch.controlTotalNumberofPrice();		 
	 }
	
	/**Delete television in Basket
	 * @throws Exception */
	@Test (priority = 12)
	public void deleteTelevisionInBasket() throws Exception {	 
		 logger.trace("Test Step: Delete Television in basket  iphone - television");
		 onProductSearch.deleteTelevisioninBasket();		 
	 }
	
	/**control Total Number of Price After Delete Television
	 * @throws Exception */
	@Test (priority = 13)
	public void controlTotalNumberofPriceAfterDeleteTelevision() throws Exception {	 
		 logger.trace("Test Step: control Total Number of Price After Delete Television");
		 onProductSearch.findTotalNumberofNewProductsPrice();		
		 onProductSearch.getTotalNumberofNewProductsPrice();
		 onProductSearch.controlTotalNumberofPriceAfterDeleteTelevision();
	 }
	
	/**Delete All Basket
	 * @throws Exception */
	@Test (priority = 14)
	public void deleteAllBasket() throws Exception {	 
		 logger.trace("Test Step: Delete All Basket");
		 onProductSearch.deleteAllBaseket();		
	 }
	
	
	
}
