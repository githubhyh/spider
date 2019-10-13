package com.hyh.spider.parse;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.hyh.spider.parse.htmlparse.AbstractHtmlParse;

/**
 * @author hu.yuhao
 * @version v1.0
 * <p>解析图片资源，返回图片资源地址
 * instruction 多线程执行，没有限制driver数量，每次使用后记得关闭（后期考虑使用集合约束，形成driver池）
 * instruction 多线程情况下，产生垃圾进程，解决办法：通过driver.quit()退出；手动管理Chromedriver服务，在程序退出时，杀死所有相关进程
 * instruction 将driver放在抽象层，通过父类调用
 * */
public class ParseImage extends AbstractHtmlParse {
	
	/**
	 * instruction 如果参数有一个为空，则抛出null异常
	 * */
	public Set<String> getImagURL(WebDriver driver, String url){
		if (driver == null||url == null) {
			try {
				throw new NullPointerException("驱动或url为空。。。");
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}		
		}
		WebDriver page = LoadHtml.loadHtmlByPullPage(driver, url);
		System.out.println("parseimage:"+page.getTitle());
		List<WebElement> elements = page.findElements(By.cssSelector("img"));
		String result = "default";
		Set<String> urls = new HashSet<String>();
		
		//img+file能够唯一确定图片资源
		for (int i = 0; i < elements.size(); i++) {
			if ((result = elements.get(i).getAttribute("file")) != null) {
				urls.add(result);
			}
		}
		driver.quit();
		return urls;
	}
}
