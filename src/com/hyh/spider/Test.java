package com.hyh.spider;

import java.io.IOException;

import javax.swing.JOptionPane;

import com.hyh.spider.entity.URLSource;
import com.hyh.spider.task.ImagSourceTask;
import com.hyh.spider.task.ManageTask;
import com.hyh.spider.task.PathSourceTask;

/**
 * @author hu.yuhao
 * a资源初始化，文件路径初始化，任务启动器，关闭爬虫等等功能集于此类
 * */
public class Test {
    public static void main(String[] args) throws IOException {
    	URLSource.getInstance().init();
		ManageTask.submitTask(new PathSourceTask(), new ImagSourceTask());
		ManageTask.start();
		int dialog = JOptionPane.showConfirmDialog(null, "停止爬虫？？？", "提示", JOptionPane.YES_NO_OPTION);
		if (dialog == JOptionPane.YES_OPTION) {		
			ManageTask.shutdown();
		}
    }
}
