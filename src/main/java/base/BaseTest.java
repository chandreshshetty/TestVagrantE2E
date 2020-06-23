package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BaseTest {

	// created thread for each execution to avoid script failure if it is using any
	// common variable value
	private static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<WebDriver>();

	public static WebDriver getWebDriver() {
		return webDriverThreadLocal.get();
	}

	public static void setWebDriverLocal(WebDriver driverThreadLocal) {
		webDriverThreadLocal.set(driverThreadLocal);
	}

	// used this constructor to initialize page factory elements
	public BaseTest() {
		PageFactory.initElements(getWebDriver(), this);
	}

}
