package com.hyh.spider.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 记录当前html信息，当前信息，和下一页
 * */
public class PageInfo {
	private Set<String> currentUrl = new HashSet<String>();
	private String nextPage;

	public String getNextPage() {
		return nextPage;
	}

	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}

	public Set<String> getCurrentUrl() {
		return currentUrl;
	}

	public void setCurrentUrl(Set<String> currentUrl) {
		this.currentUrl = currentUrl;
	}
	
}
