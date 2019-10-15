package com.hyh.spider.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * a文件工具类，存储，读取等
 * */
public class FileUtil {
	/**
	 * 将图片路径存储到本地文件
	 * */
	public static void writeToFile(Collection<? extends String> data, String destFilePath) {
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
	
	public static List<String> readSource(String sourcePath) {
		List<String> paths = new ArrayList<String>();
		File file = new File(sourcePath);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String data = "default";
			while ((data = reader.readLine()) != null) {
				paths.add(data);
			}
			reader.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return paths;
	}
	
	public static void saveSource(String source, String destPath) {
		File file = new File(destPath);
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file, false));
			outputStream.write(source.getBytes());
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void saveSource(List<String> sources, String destPath) {
		writeToFile(sources, destPath);
	}
}
