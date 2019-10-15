package com.hyh.spider.entity;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileSource {
	private static String currentHTMLSourceFilePath;
	private static String currentResourceFilePath;
	private static String imgFilePath;
	
	static {
		try {
			InputStream inputStream = new FileInputStream("./filesource.properties");
			Properties properties = new Properties();
			properties.load(inputStream);
			currentHTMLSourceFilePath = properties.getProperty("currentHTMLSourceFilePath");
			currentResourceFilePath = properties.getProperty("currentResourceFilePath");
			imgFilePath=properties.getProperty("imgFilePath");
		}catch (IOException e) {
			// TODO: handle exception
			System.out.println("资源文件加载错误！！！请确认后，重新启动");
			System.exit(1);
		}
		
	}
	public static String getCurrentHTMLSourceFilePath() {
		return currentHTMLSourceFilePath;
	}
	public static String getCurrentResourceFilePath() {
		return currentResourceFilePath;
	}
	public static String getImgFilePath() {
		return imgFilePath;
	}
}
