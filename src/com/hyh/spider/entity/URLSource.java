package com.hyh.spider.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.hyh.spider.util.FileUtil;
import com.hyh.spider.util.URLSourceUtil;

/**
 * @author hu.yuhao URL资源存储类，互斥访问，线程安全
 * <p>synchronized非公平竞争锁（缘分锁）
 */
public class URLSource {
	/**
	 * <p>使用线程不安全集合，自定义同步块(不推荐使用)</p>
	 */
	private Set<String> imgPaths = new HashSet<String>();
	private Set<String> htmlPaths = new HashSet<String>();
	private String currentPath = new String();
	
	/**
	 * <p>定义每个线程获取资源数量，4条记录</p>
	 * */
	private final static int DEFAULT_URL_NUMBER = 3;
	
	/**
	 * instauction 网页解析较慢，资源定位2
	 * */
	private final static int DEFAULT_HTML_NUMBER = 2;

	private volatile static URLSource source;
	private URLSource() {}
	public static URLSource getInstance() {
		if (source == null) {
			synchronized (URLSourceUtil.class) {
				if (source == null) {
					source = new URLSource();
				}
			}
		}
		return source;
	}
	
	/**
	 * <p>一次获取二十条数据</p>
	 * */
	public List<String> getImgPaths() {
		// 调用处检查返回结果
		if (imgPaths.isEmpty()) {
			return null;
		}
		// 访问资源
		List<String> paths = new ArrayList<String>();
		synchronized (imgPaths) {
			int i = 0;
			Iterator<String> iterator = imgPaths.iterator();
			while (iterator.hasNext()) {
				String url = iterator.next();
				paths.add(url);
				iterator.remove();
				i++;
				if (i == DEFAULT_URL_NUMBER) {
					break;
				}
			}
		}
		return paths;
	}

	public void setImgPaths(Set<String> imgPaths) {
		this.imgPaths = imgPaths;
	}

	/**
	 * <p>一次20条，只少不多
	 * */
	public List<String> getHtmlPaths() {
		// 调用处检查返回结果
		if (htmlPaths.isEmpty()) {
			return null;
		}
		// 访问资源
		List<String> paths = new ArrayList<String>();
		synchronized (htmlPaths) {
			int i = 0;
			Iterator<String> iterator = htmlPaths.iterator();
			while (iterator.hasNext()) {
				String url = iterator.next();
				paths.add(url);
				iterator.remove();
				i++;
				if (i == DEFAULT_HTML_NUMBER) {
					break;
				}
			}
		}
		return paths;
	}

	public void setHtmlPaths(Set<String> htmlPaths) {
		this.htmlPaths = htmlPaths;
	}
	
	
	/**
	 * <p>添加数据，是否成功不重要，故不检验，返回void
	 * */
	public synchronized void addImg(String url) {
		imgPaths.add(url);
	}
	
	public synchronized void addPage(String url) {
		htmlPaths.add(url);
		setCurrentPath(url);
	}
	
	public synchronized void addImg(Set<String> urls) {
		for (String url : urls) {
			imgPaths.add(url);
		}
	}
	public String getCurrentPath() {
		return currentPath;
	}
	public void setCurrentPath(String currentPath) {
		this.currentPath = currentPath;
	}
	
	public void init() {
		System.out.println("系统资源初始化......");
		String currentHTMLSourceFilePath = FileSource.getCurrentHTMLSourceFilePath();
    	String currentResourceFilePath = FileSource.getCurrentResourceFilePath();
    	Set<String> htmls = FileUtil.readSourceAsSet(currentHTMLSourceFilePath);
    	Set<String> imgs = FileUtil.readSourceAsSet(currentResourceFilePath);
    	if (htmls.isEmpty() || htmls == null) {
    		System.out.println("没有资源网页初始化，请检查......");
    	}else {
    		setImgPaths(imgs);
    	}
    	if (htmls.isEmpty() || htmls == null) {
    		System.out.println("目标资源进行空初始化......");
    	}else {
    		setHtmlPaths(htmls);
    	}	
	}
}
