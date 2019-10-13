package com.hyh.spider.task;

import java.util.List;
import java.util.concurrent.ExecutorService;

import com.hyh.spider.entity.URLSource;
import com.hyh.spider.util.ThreadPoolUtil;

public class ImagSourceTask implements Runnable {
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

		List<String> imgSource = getImgSource();
		System.out.println(imgSource);
		System.out.println("执行图片解析........" + pool);
		pool.submit(()->{
			new ParseImgTask(imgSource).run();
		});
	}

	/**
	 * first 获取资源
	 */
	public List<String> getImgSource() {
		return source.getImgPaths();
	}
}
