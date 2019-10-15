package com.hyh.spider.task;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

import com.hyh.spider.entity.URLSource;
import com.hyh.spider.util.ThreadPoolUtil;

/**
 * @author hu.yuhao
 * @version 1.0
 * <p>执行下载任务</p>
 * <p>从redis中获取下载链接</p>
 * */
public class DownloadTask implements Runnable {
	//线程不安全
	private URLSource source = URLSource.getInstance();
	private ExecutorService pool = ThreadPoolUtil.getInstance();

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			//空了就去获取资源
			List<String> imgPaths = source.getImgPaths();
			pool.submit(()->{
				download(imgPaths);
			});
			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void download(List<String> urls) {
		System.out.println(pool);
		for (String data : urls) {
			try {
				URL url = new URL(data);
				URLConnection urlConnection = url.openConnection();
				urlConnection.setDoInput(true);
				urlConnection.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
				InputStream inputStream = urlConnection.getInputStream();
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				File temp = new File("E:\\MyResource\\img\\imgs\\" + uuid + ".jpg");
				DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(temp));
				byte[] buffer = new byte[4096];
				int count = 0;
				while ((count = inputStream.read(buffer)) > 0) {
					outputStream.write(buffer, 0, count);
				}
				outputStream.flush();
				outputStream.close();
				inputStream.close();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	public List<String> getSources() {return null;}
}
