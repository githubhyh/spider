package com.hyh.test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.UUID;

import com.hyh.spider.entity.URLSource;
import com.hyh.spider.task.DownloadTask;
import com.hyh.spider.util.FileUtil;


public class Download {
	public static void main(String[] args) throws IOException {
		//download("E:\\MyResource\\img\\imgSrc.txt");
		List<String> readSource = FileUtil.readSource("E:\\MyResource\\img\\imgSrc.txt");
		URLSource source2 = URLSource.getInstance();
		for (String source : readSource) {
			source2.addImg(source);
		}
		
		new DownloadTask().run();
	}
	
	public static void download (String source) {
		File file = new File(source);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String data = "default";
			while ((data = reader.readLine()) != null) {
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
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
