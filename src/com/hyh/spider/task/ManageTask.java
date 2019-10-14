package com.hyh.spider.task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.hyh.spider.util.ThreadPoolUtil;

/**
 * @author hu.yuhao
 * <p>执行管理任务,何必纠结任务是否为空啊，传给后台，空就直接跳过嘛</p>
 * */
public class ManageTask implements Runnable {
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
		while (iterator.hasNext()) {
			pool.submit(iterator.next());
		}
	}
	
	public static void shutdown() {
		pool.shutdown();
		ThreadPoolUtil.getInstance().shutdown();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
