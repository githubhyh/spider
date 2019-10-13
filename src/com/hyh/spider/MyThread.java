package com.hyh.spider;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class MyThread extends Thread {
	private WebDriver driver = null;
	private int sec = 0;
	public MyThread(WebDriver driver, int sec) {
		this.driver = driver;
		this.sec = sec;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(sec*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		((JavascriptExecutor)driver).executeScript("window.stop();");
	}
	
}
