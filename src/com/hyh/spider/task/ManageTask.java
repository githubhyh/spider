package com.hyh.spider.task;

/**
 * @author hu.yuhao
 * <p>执行管理任务,何必纠结任务是否为空啊，传给后台，空就直接跳过嘛</p>
 * */
public class ManageTask implements Runnable {
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			new ImagSourceTask().parse();
			new PathSourceTask().parse();
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
