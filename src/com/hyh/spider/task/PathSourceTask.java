package com.hyh.spider.task;

import java.util.List;
import java.util.concurrent.ExecutorService;

import com.hyh.spider.entity.URLSource;
import com.hyh.spider.util.ThreadPoolUtil;

public class PathSourceTask implements Runnable {
	private URLSource source = URLSource.getInstance();
	private ExecutorService pool = ThreadPoolUtil.getInstance();
	
	public void parse() {
		List<String> htmlSource = getHtmlSource();
		System.out.println(htmlSource);
		System.out.println("执行资源解析........"+pool);
		pool.submit(()->{new ParsePathTask(htmlSource).run();});
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		List<String> htmlSource = getHtmlSource();
		System.out.println(htmlSource);
		System.out.println("执行资源解析........"+pool);
		pool.submit(()->{new ParsePathTask(htmlSource).run();});
	}
	
	public List<String> getHtmlSource() {
		return source.getHtmlPaths();
	}
}
