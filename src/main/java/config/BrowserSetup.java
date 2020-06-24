package config;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import base.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserSetup extends BaseTest {

	RemoteWebDriver driver = null;
	Logger logger = Logger.getLogger(Log.class.getName());

	public void InitializeBrowser(String type) throws MalformedURLException {
		try {
			switch (type.toLowerCase()) {
			case "chrome":
				logger.info("-----browser invoking-----");
				System.setProperty("webdriver.chrome.silentOutput", "true");
				// setting up chrome driver exe through Webdriver manager
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				BaseTest.setWebDriverLocal(driver);
				break;
			case "firefox":
				logger.info("-----browser invoking-----");
				// setting up firefox driver exe through Webdriver manager
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				BaseTest.setWebDriverLocal(driver);
				break;
			default:
				logger.error("Please configure your browser in the globalConfig file");
			}
		} catch (NullPointerException ne) {
			// TODO Auto-generated catch block
			logger.error("Please configure your browser in the globalConfig file", ne);
		} catch (Exception e) {
			logger.error("Please configure your browser in the globalConfig file", e);
		}
	}

	@BeforeMethod
	public void browserSetup() throws Exception {
		// method used to read properties file values
		ConfigReader.readConfigData();
		System.out.println(ConfigReader.browser);
		InitializeBrowser(ConfigReader.browser);
		getWebDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		getWebDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		PropertyConfigurator.configure("log4j.properties");
		logger.info("-----maximizing the browser window-----");
		BaseTest.getWebDriver().manage().window().maximize();
		logger.info("Navigated to NDTV Website");
		BaseTest.getWebDriver().get(ConfigReader.url);
	}

	@AfterMethod
	public void tearDown() {
		logger.info("-----closing down browser window-----");
		BaseTest.getWebDriver().quit();
	}

}
