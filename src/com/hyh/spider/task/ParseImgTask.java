package com.hyh.spider.task;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyh.spider.entity.FileSource;
import com.hyh.spider.entity.Image;
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
		ObjectMapper mapper = new ObjectMapper();
		for (String url : urls) {
			try {
				Parse parseImage = new ParseImage();
				Image image = parseImage.parseImage(url);
				String valueAsString = mapper.writeValueAsString(image);
				//获取路径
				FileUtil.saveSource(valueAsString+"\n", FileSource.getImgFilePath(), true);
			}catch (Exception e) {
				// TODO: handle exception
				System.out.println("json转化错误！！！");
			}
		}
	}

}
