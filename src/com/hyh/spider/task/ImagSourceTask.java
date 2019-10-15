package com.hyh.spider.task;

import java.util.List;
import java.util.concurrent.ExecutorService;

import com.hyh.spider.entity.URLSource;
import com.hyh.spider.util.ThreadPoolUtil;

public class ImagSourceTask extends Task implements Runnable {
	private URLSource source = URLSource.getInstance();
	private ExecutorService pool = ThreadPoolUtil.getInstance();
	private ThreadLocal<Boolean> flag = new ThreadLocal<Boolean>();
	private volatile boolean flag1 = true;
	public ImagSourceTask() {
		// TODO Auto-generated constructor stub
		flag.set(Boolean.TRUE);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (flag1) {
			//if (flag.get()) break;
			List<String> imgSource = getImgSource();
			System.out.println(imgSource);
			pool.submit(()->{
				new ParseImgTask(imgSource).run();
			});
			System.out.println("执行图片解析........" + pool);
			try {
				Thread.sleep(50000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("图片解析睡眠进程中断，已经退出。。。");
			}
		}
	}

	/**
	 * first 获取资源
	 */
	public List<String> getImgSource() {
		return source.getImgPaths();
	}
	
	public void stop() {
		flag1 = false;
	}
}
