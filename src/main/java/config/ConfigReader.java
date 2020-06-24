package config;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;

public class ConfigReader {

	Logger logger = Logger.getLogger(Log.class.getName());

	public static String url;
	public static String browser;
	public static String city;
	public static String uri;
	public static String api_key;
	public static String temp_var;
	public static String humidity_var;

	public static void readConfigData() throws Exception {
		ConfigReader conf = new ConfigReader();
		conf.readConfigFile();
	}

	@SuppressWarnings("static-access")
	public void readConfigFile() throws Exception {
		Properties prop = new Properties();
		File f = new File(System.getProperty("user.dir") + "\\src\\main\\java\\config\\globalConfig.properties");
		FileInputStream fis = new FileInputStream(f);
		prop.load(fis);

		// used getProperty to get value from cmd
		String city = System.getProperty("city");
		if (city != null) {
			this.city = city;
		} else {
			this.city = prop.getProperty("city_to_search");
		}

		// used getProperty to get value from cmd
		String browser = System.getProperty("browser");
		if (browser != null) {
			this.browser = browser;
		} else {
			this.browser = prop.getProperty("browser");
		}
		
		url = prop.getProperty("url");
		uri = prop.getProperty("api_uri");
		api_key = prop.getProperty("api_key");
		temp_var = prop.getProperty("temp_var_value");
		humidity_var = prop.getProperty("humidity_Var_value");
	}

}
