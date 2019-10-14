package com.hyh.spider.entity;

import java.io.Serializable;
import java.util.List;

/**
 * a图片信息，包含一个相册集
 * */
public class Image implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1817460996219611876L;
	private String title;
	private List<String> imgs;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getImgs() {
		return imgs;
	}
	public void setImgs(List<String> imgs) {
		this.imgs = imgs;
	}
}
