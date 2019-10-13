package com.hyh.spider.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

/**
 * a文件工具类，存储，读取等
 * */
public class FileUtil {
	/**
	 * 将图片路径存储到本地文件
	 * */
	public static void writeToFile(Set<String> data, String destFilePath) {
		File file = new File(destFilePath);
		try {
			if (!file.exists()) {
				//还要创建文件路径，目前假设路径正确
				file.createNewFile();
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
			for (String path:data) {
				bw.newLine();
				bw.write(path);
				//System.out.println(path);
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
