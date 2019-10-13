package com.hyh.spider.parse.htmlparse;

import java.util.Set;

import org.openqa.selenium.WebDriver;

import com.hyh.spider.util.WebDriverUtil;

public abstract class AbstractHtmlParse implements Parse {
	private final WebDriver driver = WebDriverUtil.getInstance();
	
	public abstract Set<String> getImagURL(WebDriver driver, String url);

	@Override
	public Set<String> parseImage(String url) {
		// TODO Auto-generated method stub
		return getImagURL(driver, url);
	}
	
}
