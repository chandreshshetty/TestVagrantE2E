package pages;

import static io.restassured.RestAssured.given;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

import config.UtilityMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class FetchWeatherDetailsApiPage {

	/*
	 * this method will fetch weather details through the API for specified city and
	 * then add it in the HashMap
	 */
	public HashMap<String, BigDecimal> fetchWeatherDeatilsAPI(String URI, String city, String APIKey) {

		RestAssured.baseURI = URI;

		String response = given().urlEncodingEnabled(false).queryParam("q", city).queryParam("appid", APIKey)
				.header("accept", "application/json").header("Content-Type", "application/json").when()
				.get("data/2.5/weather").then().assertThat().statusCode(200).extract().response().asString();

		// used JsonPath to read the API response
		UtilityMethods util = new UtilityMethods();
		JsonPath js = util.jsonPathFormatter(response);

		HashMap<String, BigDecimal> responseData = new HashMap<String, BigDecimal>();

		/*
		 * fetching required json data from the response and putting it in the HaspMap
		 */
		responseData.put("TemperatureAPI",
				new BigDecimal(js.getDouble("main.temp") - 273.00).setScale(2, RoundingMode.HALF_UP));
		responseData.put("HumidityAPI", new BigDecimal(js.getDouble("main.humidity")));

		return responseData;

	}

}
