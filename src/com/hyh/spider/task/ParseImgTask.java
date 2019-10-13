package com.hyh.spider.task;

import java.util.List;
import java.util.Set;

import com.hyh.spider.parse.ParseImage;
import com.hyh.spider.parse.htmlparse.Parse;
import com.hyh.spider.util.FileUtil;

/**
 * @author hu.yuhao
 * @version v1.0
 * a解析图片任务管理器
 * */
public class ParseImgTask implements Runnable {
	private ThreadLocal<List<String>> currentParam = new ThreadLocal<List<String>>();
	/**
	 * a构造函数初始化资源
	 * */
	public ParseImgTask(List<String> urls) {
		// TODO Auto-generated constructor stub
		this.currentParam.set(urls);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		parse(currentParam.get());
	}
	
	public void parse(List<String> urls) {
		for (String url : urls) {
			Parse parseImage = new ParseImage();
			Set<String> images = parseImage.parseImage(url);
			FileUtil.writeToFile(images, "E:\\MyResource\\img\\imgSrc.txt");
		}
	}

}
