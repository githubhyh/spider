package com.hyh.spider.parse;

import org.openqa.selenium.WebDriver;

import com.hyh.spider.entity.Image;
import com.hyh.spider.parse.htmlparse.Parse;
import com.hyh.spider.util.WebDriverUtil;

public abstract class AbstractURLParse implements Parse {
	private final WebDriver driver = WebDriverUtil.getInstance();
	public abstract void parsePath(WebDriver driver, String path);

	@Override
	public void parseURL(String url) {
		// TODO Auto-generated method stub
		parsePath(driver, url);
	}

	@Override
	public Image parseImage(String url) {
		// TODO Auto-generated method stub
		return null;
	}
}
