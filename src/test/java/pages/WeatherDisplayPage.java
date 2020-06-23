package pages;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BaseTest;
import config.ConfigReader;
import config.UtilityMethods;

public class WeatherDisplayPage extends BaseTest {

	Logger logger = Logger.getLogger(Log.class.getName());
	UtilityMethods util = new UtilityMethods();

	public WeatherDisplayPage() {
	}

	@FindBy(id = "searchBox")
	public WebElement citySearchBox;

	@FindBy(xpath = "//span[@class='heading']")
	public List<WebElement> weatherCompleteDetails;

	// this method used to enter city name in the Pin Your City Search Box
	public void searchForCity(String str) {
		try {
			util.waitForElementToDisplay(citySearchBox);
			citySearchBox.click();
			logger.info("Entered " + ConfigReader.city + " in the Pin Your City Search Box");
			citySearchBox.sendKeys(str);
		} catch (Exception e) {
			logger.error("Exception occued while entering text in the Pin Your City Text box", e);
		}
	}

	// this method used select the city after searching
	public void pinYourCity() throws InterruptedException {
		try {
			Thread.sleep(2000);
			String str = "#" + ConfigReader.city;
			WebElement element = getWebDriver().findElement(By.cssSelector(str));
			util.waitForElementToDisplay(element);
			logger.info("Clicking on the city checkbox after searching");
			// checking class attribute to check whether city checkbox already selected or
			// not
			if (element.getAttribute("class").isEmpty()) {
				element.click();
			}
		} catch (StaleElementReferenceException e) {
			logger.error("Search Result not displayed", e);
		} catch (Exception e) {
			logger.error("Exception", e);
		}
	}

	// method will validate temperature displayed in the Map after search
	public boolean validateWeatherDetailsRevealInMap() {
		String str = "//div[@title=" + "'" + ConfigReader.city + "'" + "]//div[@class='temperatureContainer']";
		WebElement element = getWebDriver().findElement(By.xpath(str));
		util.waitForElementToDisplay(element);
		logger.info("Validating whether weather details revealed in the Map or Not");
		return element.isDisplayed();
	}

	// method used to display complete weather details in the pop-up
	public void viewCityWeather() {
		String str = "//div[@title=" + "'" + ConfigReader.city + "'" + "]";
		WebElement element = getWebDriver().findElement(By.xpath(str));
		util.waitForElementToDisplay(element);
		element.click();
	}

	/*
	 * this method will fetch weather details from the Map for specified city and
	 * then add it in the HashMap
	 */
	public HashMap<String, BigDecimal> fetchCityWeatherDetailsFromNdtvPage() {
		HashMap<String, BigDecimal> weatherdetails = new HashMap<String, BigDecimal>();
		for (WebElement weather : weatherCompleteDetails) {
			if (weather.getText().contains("Humidity")) {
				String str = weather.getText().replace("Humidity: ", "");
				String str1 = str.replace("%", "");
				weatherdetails.put("HumidityUI", new BigDecimal(str1));
			} else if (weather.getText().contains("Temp in Degrees")) {
				String str1 = weather.getText().replace("Temp in Degrees: ", "");
				weatherdetails.put("TemperatureUI", new BigDecimal(str1));
			}
		}
		return weatherdetails;
	}

}
