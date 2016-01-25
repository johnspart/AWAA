package org.awaa.feature.utils;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class Utils {

	private static Utils utils = null;
	private WebDriver driver = null;

	private Utils() {
		super();
	}

	public WebDriver getWebDriver() {
		if (this.driver == null) {
			FirefoxProfile fp = new FirefoxProfile();
			fp.setPreference("webdriver.load.strategy", "unstable");
			driver = new FirefoxDriver(fp);
		}
		return this.driver;
	}

	public static Utils getInstance() {

		if (utils == null) {
			String valorDriver = System.getProperty("webdriver.gecko.driver");
			if (valorDriver == null) {
				System.setProperty("webdriver.gecko.driver", "drivers/wires.exe");
			}
			utils = new Utils();
		}
		return utils;

	}
}
