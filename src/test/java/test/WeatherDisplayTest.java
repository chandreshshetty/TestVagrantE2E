package test;

import java.math.BigDecimal;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import config.BrowserSetup;
import config.ConfigReader;
import config.UtilityMethods;
import pages.FetchWeatherDetailsApiPage;
import pages.NdtvHomePage;
import pages.WeatherDisplayPage;

public class WeatherDisplayTest extends BrowserSetup {

	Logger logger = Logger.getLogger(WeatherDisplayTest.class);

	@Test
	public void checkWeatherVarianceForTemperature() throws InterruptedException {

		NdtvHomePage homePage = new NdtvHomePage();
		FetchWeatherDetailsApiPage fetchWeatherApi = new FetchWeatherDetailsApiPage();
		UtilityMethods util = new UtilityMethods();
		HashMap<String, BigDecimal> map = new HashMap<String, BigDecimal>();

		logger.info("Navigating to Weather page from Ndtv Home Page");
		WeatherDisplayPage weatherPage = homePage.navigateToWeatherMenu();
		logger.info("Searching for " + ConfigReader.city + " city in the Pin Your City search box");
		weatherPage.searchForCity(ConfigReader.city);
		logger.info("Click on the checkbox for the selected city");
		weatherPage.pinYourCity();
		logger.info("Validate that selecting any city on the map itself reveals the weather details");
		Assert.assertTrue(weatherPage.validateWeatherDetailsRevealInMap(),
				"Weather details not revealed in Map after selecting the city");
		logger.info("Clicking on city name to view complete weather details");
		weatherPage.viewCityWeather();
		logger.info("fetch all required weather details from map and adding it in hashmap");
		map.putAll(weatherPage.fetchCityWeatherDetailsFromNdtvPage());
		logger.info("fetch all required weather details through API and adding it in hashmap");
		map.putAll(fetchWeatherApi.fetchWeatherDeatilsAPI(ConfigReader.uri, ConfigReader.city, ConfigReader.api_key));
		System.out.println(map);
		logger.info("validate temperature in the Map and from API with variance value");
		Assert.assertTrue(
				util.compareWeather(map.get("TemperatureUI"), map.get("TemperatureAPI"),
						new BigDecimal(ConfigReader.temp_var)),
				"Temperature defined is not in the specified range" + "\n Temperature from NDTV Website: "
						+ map.get("TemperatureUI") + " and Temperature through the API is: "
						+ map.get("TemperatureAPI"));

	}

	@Test
	public void checkWeatherVarianceForHumidity() throws InterruptedException {
		NdtvHomePage homePage = new NdtvHomePage();
		FetchWeatherDetailsApiPage fetchWeatherApi = new FetchWeatherDetailsApiPage();
		UtilityMethods util = new UtilityMethods();
		HashMap<String, BigDecimal> map = new HashMap<String, BigDecimal>();

		logger.info("Navigating to Weather from Ndtv Home Page");
		WeatherDisplayPage weatherPage = homePage.navigateToWeatherMenu();
		logger.info("Searching city in the Pin Your City search box");
		weatherPage.searchForCity(ConfigReader.city);
		logger.info("check the checkbox for the selected city");
		weatherPage.pinYourCity();
		logger.info("Validate that selecting any city on the map itself reveals the weather details");
		Assert.assertTrue(weatherPage.validateWeatherDetailsRevealInMap(),
				"Weather details not revealed in Map after selecting the city");
		logger.info("click on city in the map");
		weatherPage.viewCityWeather();
		logger.info("fetch all required weather details from map and adding it in hashmap");
		map.putAll(weatherPage.fetchCityWeatherDetailsFromNdtvPage());
		logger.info("fetch all required weather details through API and adding it in hashmap");
		map.putAll(fetchWeatherApi.fetchWeatherDeatilsAPI(ConfigReader.uri, ConfigReader.city, ConfigReader.api_key));
		System.out.println(map);
		logger.info("validate temperature in the Map and from API with variance value");
		Assert.assertTrue(
				util.compareWeather(map.get("HumidityUI"), map.get("HumidityAPI"),
						new BigDecimal(ConfigReader.humidity_var)),
				"Humidity defined is not in the specified range" + "\n Humidity from NDTV Website: "
						+ map.get("HumidityUI") + " and Humidity through the API is: " + map.get("HumidityAPI"));

	}

}
