package com.hyh.spider;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.hyh.spider.entity.PageInfo;

public class WebUtil {
	@SuppressWarnings("unused")
	private static ThreadLocal<Set<String>> urls = new ThreadLocal<Set<String>>();
	private final static String DEFAULT_URL = "http://thz7.net/forum-42-1.html";

	public static void getHref(String path) {
		if (path == null || "".equals(path))
			path = DEFAULT_URL;
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless", "--disable-gpu");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get(path);
		try {
			Thread.sleep(1000);
			int num = 1;
			long count = 0;
			while (true) {
				Thread.sleep(5000);
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + (num * 1000) + ")");
				long height = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight;");
				if (height > count) {
					count = height;
				} else {
					break;
				}
				num++;
			}
			String href = "default";
			List<WebElement> elements = driver.findElements(By.cssSelector("a"));
			for (int i = 0; i < elements.size(); i++) {
				if ((href = elements.get(i).getAttribute("href")) != null) {
					// imgPath.add(img);
					System.out.println(href);
				}
			}
			driver.close();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// http://thz7.net/thread-2048107-1-1.html
	public static void pareseThreadUrl(String source) {
		File file = new File(source);
		Set<String> urls = new HashSet<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String data = "default";
			while ((data = reader.readLine()) != null) {
				String regex = "http://thz7.net/thread-.*-1-1.html";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(data);
				while (matcher.find()) {
					// System.out.println(matcher.group());
					urls.add(matcher.group());
				}
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(urls.size());
	}
	
	public static Set<String> pareseThreadUrl(List<String> source) {
		Set<String> urls = new HashSet<String>();
		for (String data:source) {
			String regex = "http://thz7.net/thread-.*-1-1.html";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(data);
			while (matcher.find()) {
				// System.out.println(matcher.group());
				urls.add(matcher.group());
			}
		}
		return urls;
	}
	
	public static String pareseNextUrl(List<String> source) {
		String url = "default";
		for (String data:source) {
			String regex = "http://thz7.net/forum.php\\?mod=forumdisplay&fid=\\d+&filter=&orderby=lastpost&&page=\\d+";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(data);
			if (matcher.find()) {
				url = matcher.group();
				break;
			}
		}
		return url;
	}

	/**
	 * 应当开启多线程进行下载
	 * 下载任务单独执行，主要考虑网络带宽问题 
	 */
	public static void downloadImg(String source) {
		File file = new File(source);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String data = "default";
			while ((data = reader.readLine()) != null) {
				URL url = new URL(data);
				HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
				connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
				InputStream inputStream = connection.getInputStream();
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				File temp = new File("E:\\MyResource\\img\\imgs\\" + uuid + ".jpg");
				DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(temp));
				byte[] buffer = new byte[4096];
				int count = 0;
				while ((count = inputStream.read(buffer)) > 0) {
					outputStream.write(buffer, 0, count);
				}
				outputStream.flush();
				outputStream.close();
				inputStream.close();
				connection.disconnect();
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void nextPage(String source) {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless", "--disable-gpu");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get(source);
		try {
			Thread.sleep(1000);
			int num = 1;
			long count = 0;
			while (true) {
				Thread.sleep(5000);
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + (num * 1000) + ")");
				long height = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight;");
				if (height > count) {
					count = height;
				} else {
					break;
				}
				num++;
			}
			String href = "default";
			List<WebElement> elements = driver.findElements(By.cssSelector("a"));
			for (int i = 0; i < elements.size(); i++) {
				if ((href = elements.get(i).getAttribute("href")) != null) {
					// imgPath.add(img);
					System.out.println(href);
				}
			}
			driver.close();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static PageInfo getCurrentPage(String url) {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless", "--disable-gpu");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get(url);
		PageInfo pageInfo = new PageInfo();
		try {
			Thread.sleep(1000);
			int num = 1;
			long count = 0;
			while (true) {
				Thread.sleep(2000);
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + (num * 1000) + ")");
				long height = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight;");
				if (height > count) {
					count = height;
				} else {
					break;
				}
				num++;
			}
			String href = "default";
			List<String> hrefs = new ArrayList<String>();
			List<WebElement> elements = driver.findElements(By.cssSelector("a"));
			for (int i = 0; i < elements.size(); i++) {
				if ((href = elements.get(i).getAttribute("href")) != null) {
					// imgPath.add(img);
					hrefs.add(href);
				}
			}
			driver.close();
			
			Set<String> threadUrl = pareseThreadUrl(hrefs);
			String nextUrl = pareseNextUrl(hrefs);
			if ("default".equals(nextUrl))return null;
			pageInfo.setCurrentUrl(threadUrl);
			pageInfo.setNextPage(nextUrl);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pageInfo;
	}
}
