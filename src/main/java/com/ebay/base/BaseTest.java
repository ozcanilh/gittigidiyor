package com.ebay.base;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.ebay.common.Commons;


public class BaseTest extends Commons {

	/**
	 * Logger Object
	 */
	private static final Logger logger = LogManager.getLogger(BaseTest.class);

	/**
	 * WebDriver Object
	 */
	protected static WebDriver driver = null;
	DesiredCapabilities capabilities;
	
	/**
	 * This method will execute before each Test tag in testng.xml
	 * 
	 * @param browser Browser name as parameter. Should be defined in testng.xml
	 * @throws Exception
	 */
	@BeforeClass
	@Parameters("browser") /* For Docker @Parameters({"browser","remoteurl",})*/
	public void initalizeTests(@Optional("chrome") String browser) throws Exception {
		if (browser.equalsIgnoreCase("firefox")) {
			logger.info("Creating Firefox instance...");
			System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\geckodriver.exe"); // if you execute test on docker, not need to setProperty
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
			driver = new FirefoxDriver(capabilities);
		} else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe"); // if you execute test on docker, not need to setProperty
			capabilities = DesiredCapabilities.chrome();	
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			ChromeOptions options = new ChromeOptions();
		    options.addArguments("disable-infobars");
		     options.addArguments("--disable-extensions");
		     options.addArguments("--disable-notifications");
		     options.addArguments("--start-maximized");
		     options.addArguments("--disable-web-security");
		     options.addArguments("--no-proxy-server");
		     options.addArguments("--enable-automation");
		     options.addArguments("--disable-save-password-bubble");	
						
			Map<String, Object> prefs = new HashMap<String, Object>();
		     prefs.put("credentials_enable_service", false);
		     prefs.put("profile.password_manager_enabled", false);
		     options.setExperimentalOption("prefs", prefs);
			
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			// create chrome instance
			logger.info("Creating Chrome instance...");
			driver = new ChromeDriver(capabilities);
			
		} else if (browser.equalsIgnoreCase("ie")) {
			// set path to IE.exe
			System.setProperty("webdriver.ie.driver", "src\\main\\resources\\IEDriverServer.exe"); // if you execute test on docker, not need to setProperty
			// create IE instance
			logger.info("Creating IE instance...");
			driver = new InternetExplorerDriver();
		} else {
			// If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}
		// Set global timeout as 10 seconds
		//driver = new RemoteWebDriver(new URL(remoteurl), capabilities); for docker
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	 public void afterMethod(){
	        driver.quit();
	    }
	
	@AfterClass
	public void finalizeTests() {
		logger.trace("Finalizing tests.");
		driver.quit();
		
		
	}
	
	
}