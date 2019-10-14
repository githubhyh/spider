package com.hyh.spider.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.hyh.spider.entity.Image;

public class Test {
	@SuppressWarnings({ "resource", "unchecked" })
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		List<String> list = new ArrayList<String>();
		list.add("http1");
		list.add("http2");
		Image image = new Image();
		image.setTitle("test");
		image.setImgs(list);
		
		Image image1 = new Image();
		image.setTitle("test1");
		image.setImgs(list);
		FileOutputStream fileOutputStream = new FileOutputStream(new File("D:\\huyuhao\\spider\\images\\obj.txt"));
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(image);
		objectOutputStream.writeObject(image1);
		objectOutputStream.close();
		
		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File("D:\\huyuhao\\spider\\images\\obj.txt")));
		List<Image> imgs = (List<Image>)objectInputStream.readObject();
		objectInputStream.close();
		System.out.println(imgs.size());
	}
}
