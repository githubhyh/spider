package com.hyh.spider.parse.htmlparse;

import com.hyh.spider.entity.Image;

/**
 * @author hu.yuhao
 * instruction 解析接口
 * */
public interface Parse {
	public Image parseImage(String url);
	public void parseURL(String url);
}
