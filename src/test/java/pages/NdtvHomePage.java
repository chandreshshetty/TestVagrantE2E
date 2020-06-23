package pages;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BaseTest;
import config.UtilityMethods;

public class NdtvHomePage extends BaseTest {

	UtilityMethods util = new UtilityMethods();
	Logger logger = Logger.getLogger(Log.class.getName());

	public NdtvHomePage() {
	}

	@FindBy(css = ".topnavmore")
	public WebElement subNavigationMenu;

	@FindBy(xpath = "//a[contains(text(),'WEATHER')]")
	public WebElement weatherSubNavigationBtn;

	@FindBy(xpath = "//a[contains(text(),'No Thanks')]")
	public WebElement closeAlertPopup;

	// this method used to navigate weather page
	public WeatherDisplayPage navigateToWeatherMenu() {
		try {
			closeAlertPopup();
			util.waitForElementToDisplay(subNavigationMenu);
			subNavigationMenu.click();
			util.waitForElementToDisplay(weatherSubNavigationBtn);
			weatherSubNavigationBtn.click();
			return new WeatherDisplayPage();
		} catch (ElementClickInterceptedException e) {
			logger.error("Element click intercepted by other element", e);
		} catch (Exception e) {
			logger.error("Exception", e);
		}
		return null;
	}

	// this method used to close alerts
	public void closeAlertPopup() {
		/*
		 * added sleep as Alerts are getting displayed when we visit website for the
		 * first time
		 */
		try {
			Thread.sleep(5000);
			if (closeAlertPopup.isDisplayed()) {
				util.waitForElementToDisplay(closeAlertPopup);
				closeAlertPopup.click();
			}
		} catch (ElementClickInterceptedException e) {
			logger.error("Element click intercepted by other element", e);
		} catch (Exception e) {
			logger.error("Exception", e);
		}
	}

}
