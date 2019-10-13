package com.hyh.spider;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class HeadlessChrome {
	public static void main(String[] args) throws Exception {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless", "--disable-gpu");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("http://thz7.net/thread-2048099-1-1.html");
		Thread.sleep(1000);
		int num = 1;
		long count = 0;
		while (true) {
			Thread.sleep(5000);
            ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+(num * 1000)+")");
            long height = (long)((JavascriptExecutor)driver).executeScript("return document.body.scrollHeight;");
            if (height>count) {
            	count = height;
            }else {
            	break;
            }
            num++;
		}
		//String title = driver.getTitle();
		List<WebElement> elements = driver.findElements(By.cssSelector("img"));
		List<String> imgPath = new ArrayList<String>();
		String img = "default";
		for (int i = 0; i < elements.size(); i++) {
			if ((img = elements.get(i).getAttribute("file")) != null) {
				imgPath.add(img);
				System.out.println(img);
			}
		}
		driver.quit();
		//writeToFile(imgPath, "D:/huyuhao/spider/images/imgSrc.txt");
	}
	
	/**
	 * 将图片路径存储到本地文件
	 * */
	public static void writeToFile(List<String> data, String destFilePath) {
		File file = new File(destFilePath);
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
			for (String path:data) {
				bw.newLine();
				bw.write(path);
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
