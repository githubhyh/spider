package com.hyh.spider.parse;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.hyh.spider.parse.load.AbstractHtmlLoader;

/**
 * @author hu.yuhao
 * @version v1.0
 * instruction 网页加载类，目前网站基本是动态加载，且有些资源加载方式不唯一，故此封装HTML加载类
 * instruction 能够根据不同网页类型顺利实现动态资源加载
 * */
public class LoadHtml extends AbstractHtmlLoader {
	/**
	 * <p>以工具类的方式实现该类</p>
	 * */
	
	
	/**
	 * instruction 特定动态加载方法，通过下拉页面加载动态资源
	 * @exception 网页加载超时异常
	 * @author hu.yuhao
	 * @param tempDriver 后台运行的浏览器，网页将在此driver上打开
	 * @param url 需要解析的地址
	 * @return WebDriver 返回一个解析好的页面回去，包含了已经加载好的页面信息和内容
	 * */
	public static WebDriver loadHtmlByPullPage(WebDriver driver, String url) {
		System.out.println("loader:"+driver+"\n"+url);
		driver.get(url);
		int num = 1;
		long count = 0;
		while (true) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("网页加载被终止，退出进程......");
			}
            ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+(num * 1000)+")");
            long height = (long)((JavascriptExecutor)driver).executeScript("return document.body.scrollHeight;");
            if (height>count) {
            	count = height;
            }else {
            	break;
            }
            num++;
		}
		return driver;
	}

	@Override
	public WebDriver loadHtml(WebDriver driver, String url) {
		// TODO Auto-generated method stub
		return null;
	}
}
