package com.hyh.spider.parse.load;

import org.openqa.selenium.WebDriver;

/**
 * instruction 网页相关资源加载器
 * */
public abstract class AbstractHtmlLoader implements Load {
	public abstract WebDriver loadHtml (WebDriver driver, String url);
}
