package com.hyh.spider;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

import com.hyh.spider.entity.URLSource;
import com.hyh.spider.task.ManageTask;
import com.hyh.spider.util.ThreadPoolUtil;

public class Test {
    public static void main(String[] args) throws IOException {
		URLSource source = URLSource.getInstance();
		init(source);
		ExecutorService pool = ThreadPoolUtil.getInstance();
		pool.submit(new ManageTask());	
    }
    
    public static void init(URLSource source) {
    	source.addPage("http://thz7.net/forum-42-1.html");
    	source.addPage("http://thz7.net/forum-221-1.html");
    	source.addPage("http://thz7.net/forum-56-1.html");
    	source.addPage("http://thz7.net/forum-307-1.html");
    }
}
