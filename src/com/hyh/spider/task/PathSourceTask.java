package com.hyh.spider.task;

import java.util.List;
import java.util.concurrent.ExecutorService;

import com.hyh.spider.entity.URLSource;
import com.hyh.spider.util.ThreadPoolUtil;

public class PathSourceTask extends Task implements Runnable {
	private URLSource source = URLSource.getInstance();
	private ExecutorService pool = ThreadPoolUtil.getInstance();
	private ThreadLocal<Boolean> flag = new ThreadLocal<Boolean>();
	private volatile boolean flag1 = true;
	public PathSourceTask() {
		// TODO Auto-generated constructor stub
		flag.set(Boolean.TRUE);
		System.out.println("PathSourceTask初始化"+flag.get());
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (flag1) {
			List<String> htmlSource = getHtmlSource();
			System.out.println(htmlSource);
			pool.submit(()->{new ParsePathTask(htmlSource).run();});
			System.out.println("执行资源解析........"+pool);
			try {
				Thread.sleep(160000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("资源解析睡眠进程中断，已经退出。。。");
			}
		}
	}
	
	public List<String> getHtmlSource() {
		return source.getHtmlPaths();
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		flag1 = false;
	}
}
