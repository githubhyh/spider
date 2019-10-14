package com.hyh.test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class MyThread extends Thread {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("i will shutdown");
	}
	
	@SuppressWarnings("unused")
	public void test() {
		ScheduledExecutorService newSingleThreadScheduledExecutor 
		= Executors.newSingleThreadScheduledExecutor();
	}
	
}
