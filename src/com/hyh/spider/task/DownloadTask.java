package com.hyh.spider.task;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hu.yuhao
 * @version 1.0
 * <p>执行下载任务</p>
 * <p>从redis中获取下载链接</p>
 * */
public class DownloadTask implements Runnable {
	//线程不安全
	private List<String> paths = new ArrayList<String>();

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			//空了就去获取资源
			if (paths.isEmpty()) break;
			
		}
	}
	
	public void download(String url) {
		
	}
	
	public List<String> getSources() {return null;}
}
