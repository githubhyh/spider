package com.hyh.spider.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author hu.yuhao
 * <p>获取线程池</p>
 * */
public class ThreadPoolUtil {
	private final static int THREAD_NUMBER = 16;
	private volatile static ExecutorService pool;
	private ThreadPoolUtil() {}
	
	public static ExecutorService getInstance() {
		if (pool == null) {
			synchronized (ThreadPoolUtil.class) {
				if (pool == null) {
					pool = Executors.newFixedThreadPool(THREAD_NUMBER);
				}
			}
		}
		return pool;
	}
}
