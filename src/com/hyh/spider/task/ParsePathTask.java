package com.hyh.spider.task;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.hyh.spider.entity.URLSource;
import com.hyh.spider.util.WebDriverUtil;

/**
 * @author hu.yuhao
 * instruction	执行地址解析任务，将解析得到的结果（图片地址）放入集合
 * */
public class ParsePathTask implements Runnable {
	private ThreadLocal<List<String>> currentParam = new ThreadLocal<List<String>>();
	private URLSource source = URLSource.getInstance();;
	
	public ParsePathTask(List<String> temp) {
		this.currentParam.set(temp);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		executeParse(currentParam.get());
	}
	
	/**
	 * instruction 执行解析
	 * */
	public void executeParse(List<String> paths) {
		ChromeDriver driver = WebDriverUtil.getInstance();
		//遍历解析
		for (String url : paths) {
			System.out.println(url);
			parse(driver, url);
		}
		driver.quit();
	}
	
	public void parse(ChromeDriver driver, String url) {
		driver.get(url);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int num = 1;
		long count = 0;
		while (true) {
			try {
				//5秒钟下拉一次
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
		
		//加入集合	
		List<WebElement> elements = driver.findElements(By.cssSelector("a"));
		Set<String> urls = new HashSet<String>();
		//for循环去重加解析url
		String temp = "default";
		for (int i = 0; i < elements.size(); i++) {
			if ((temp = elements.get(i).getAttribute("href")) != null) {
				urls.add(temp);
			}
		}
		getUrl(urls, url);
	}
	
	/**
	 * instruction 正则匹配时就将结果加入集合
	 * */
	public void getUrl(Set<String> urls, String currentUrl) {
		String regexImg = "http://thz7.net/thread-.*-\\d+-\\d+.html";
		String regexNextPage = "http://thz7.net/forum.php\\?mod=forumdisplay&fid=\\d+&filter=&orderby=lastpost&&page=\\d+";
		Pattern compile = Pattern.compile(regexImg);
		Pattern compile1 = Pattern.compile(regexNextPage);
		for (String url : urls) {
			Matcher matcher = compile.matcher(url);
			if (matcher.find()) {
				source.addImg(matcher.group());
				System.out.println(url);
			}else {
				Matcher matcher1 = compile1.matcher(url);
				if (matcher1.find()) {
					String nextUrl = matcher1.group();
					if (!currentUrl.equals(nextUrl)) {
						source.addPage(matcher1.group());
						System.out.println(url);
					}
				}
			}
		}
	}
}
