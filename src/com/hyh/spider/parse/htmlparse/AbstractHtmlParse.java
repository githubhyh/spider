package com.hyh.spider.parse.htmlparse;

import org.openqa.selenium.WebDriver;

import com.hyh.spider.entity.Image;
import com.hyh.spider.util.WebDriverUtil;

public abstract class AbstractHtmlParse implements Parse {
	private final WebDriver driver = WebDriverUtil.getInstance();
	
	public abstract Image getImagURL(WebDriver driver, String url);

	@Override
	public Image parseImage(String url) {
		// TODO Auto-generated method stub
		return getImagURL(driver, url);
	}
	
}
