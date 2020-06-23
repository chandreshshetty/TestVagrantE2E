package config;

import java.math.BigDecimal;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BaseTest;
import io.restassured.path.json.JsonPath;

public class UtilityMethods extends BaseTest {

	Logger logger = Logger.getLogger(Log.class.getName());

	public boolean compareWeather(BigDecimal bd1, BigDecimal bd2, BigDecimal end) {
		BigDecimal start = new BigDecimal(0);
		// converted BigDecimal value to positive
		BigDecimal diffValue = compareValue(bd1, bd2).abs();
		// validating whether given value is in the given range
		if (diffValue.compareTo(start) > 0 && diffValue.compareTo(end) < 0) {
			return true;
		}
		return false;
	}

	public BigDecimal compareValue(BigDecimal bd1, BigDecimal bd2) {
		logger.info("variance value after comparing is: " + bd1.subtract(bd2));
		return bd1.subtract(bd2);
	}

	public void waitForElementToDisplay(WebElement element) {
		try {
			logger.info("waiting for specified element to display");
			WebDriverWait wait = new WebDriverWait(getWebDriver(), 20);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (TimeoutException e) {
			logger.error("Element " + element + " not displayed after waiting for 20 seconds", e);
		} catch (Exception e) {
			logger.error("Exception", e);
		}
	}

	// this method is used to read entire API response
	public JsonPath jsonPathFormatter(String response) {
		JsonPath js = new JsonPath(response);
		return js;
	}

}
