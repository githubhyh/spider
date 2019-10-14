package com.hyh.spider.task;

import java.util.List;
import java.util.concurrent.ExecutorService;

import com.hyh.spider.entity.URLSource;
import com.hyh.spider.util.ThreadPoolUtil;

public class ImagSourceTask extends Task implements Runnable {
	private URLSource source = URLSource.getInstance();
	private ExecutorService pool = ThreadPoolUtil.getInstance();
	
	public void parse() {
		List<String> imgSource = getImgSource();
		System.out.println(imgSource);
		System.out.println("执行图片解析........" + pool);
		pool.submit(()->{
			new ParseImgTask(imgSource).run();
		});
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			List<String> imgSource = getImgSource();
			System.out.println(imgSource);
			System.out.println("执行图片解析........" + pool);
			pool.submit(()->{
				new ParseImgTask(imgSource).run();
			});
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * first 获取资源
	 */
	public List<String> getImgSource() {
		return source.getImgPaths();
	}
}
