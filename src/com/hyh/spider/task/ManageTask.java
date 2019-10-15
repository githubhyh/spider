package com.hyh.spider.task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.hyh.spider.entity.URLSource;
import com.hyh.spider.util.FileUtil;
import com.hyh.spider.util.ThreadPoolUtil;

/**
 * @author hu.yuhao
 * <p>执行管理任务,何必纠结任务是否为空啊，传给后台，空就直接跳过嘛</p>
 * */
public class ManageTask {
	private final static ExecutorService pool;
	static {
		pool = Executors.newFixedThreadPool(4);
	}
	
	private static List<Runnable> tasks = new ArrayList<Runnable>();
	
	public static void submitTask(Runnable...task) {
		for (Runnable e : task) {
			tasks.add(e);
		}
	}
	
	public static void start() {
		Iterator<Runnable> iterator = tasks.iterator();
		while(iterator.hasNext()) {
			pool.submit(iterator.next());
			System.out.println("启动任务");
		}
		System.out.println("启动任务完成");
	}
	
	//shutdown时将内存资源写入文件保存，下一次启动继续执行
	public static void shutdown(String savePath) {
		URLSource source = URLSource.getInstance();
		//关闭步骤
		//第一步,停止产生新任务
		for (Runnable task:tasks) {
			((Task)task).stop();
		}
		pool.shutdownNow();
		ThreadPoolUtil.getInstance().shutdownNow();
		//第二步，等待所有线程执行完毕，保存资源列表
		List<String> htmlPaths = source.getHtmlPaths();
		String currentPath = source.getCurrentPath();
		if (htmlPaths==null || htmlPaths.size() == 0) {
			FileUtil.saveSource(currentPath, savePath);
		}else {
			FileUtil.saveSource(htmlPaths, savePath);
		}
		//第三步，关闭线程池
	}
}
