package com.hyh.spider;

import java.io.IOException;

import javax.swing.JOptionPane;

import com.hyh.spider.entity.URLSource;
import com.hyh.spider.task.ImagSourceTask;
import com.hyh.spider.task.ManageTask;
import com.hyh.spider.task.PathSourceTask;

public class Test {
    public static void main(String[] args) throws IOException {
		URLSource source = URLSource.getInstance();
		init(source);
		//ExecutorService pool = ThreadPoolUtil.getInstance();
		//pool.submit(new ManageTask());
//		Task task = new PathSourceTask();
//		task.run();
		ManageTask.submitTask(new PathSourceTask(), new ImagSourceTask());
		ManageTask.start();
		int dialog = JOptionPane.showConfirmDialog(null, "停止爬虫？？？", "提示", JOptionPane.YES_NO_OPTION);
		if (dialog == JOptionPane.YES_OPTION) {		
			ManageTask.shutdown("D:\\huyuhao\\spider\\images\\source.txt");
		}
    }
    
    public static void init(URLSource source) {
    	source.addPage("http:");
    }
}
