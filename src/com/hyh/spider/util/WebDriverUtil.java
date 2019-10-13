package com.hyh.spider.util;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * instruction 每次来都给新的driver
 * */
public class WebDriverUtil {
	
	private WebDriverUtil() {}
	
	public static ChromeDriver getInstance(String...param) {
		ChromeOptions options = new ChromeOptions();
		options.addArguments(param);
		ChromeDriver driver = new ChromeDriver(options);
		return driver;
	}
	
	public static ChromeDriver getInstance() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless", "--disable-gpu");
		ChromeDriver driver = new ChromeDriver(options);
		return driver;
	}
}
