package com.ebay.base.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ebay.common.Commons;

public class BasePage extends Commons{
	
	/**
	 * Logger Object
	 */
	public static final Logger logger = LogManager.getLogger(BasePage.class);

	/**
	 * WebDriver Object
	 */
	public WebDriver driver;
	public WebElement myDynamicElement;
	public Boolean myElement;

	/**
	 * WebDriverWait Object
	 */
	public WebDriverWait wait;
	
	/**
	 * Constructor
	 * 
	 * @param driver WebDriver Object
	 */
	public BasePage(WebDriver driver) {
		this.driver = driver;
		logger.info("Initializing Page Objects...");
		PageFactory.initElements(driver, this);
		wait=new WebDriverWait(driver,60);
	
	}

}
