package com.hyh.object.inter;

public class Employee {
	private String level;
	
	public Employee() {
		System.out.println("e");
	}
	
	public Employee(String level) {
		this.level = level;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	public void test1() {
		System.out.println("test1");
	}
	
	@SuppressWarnings("unused")
	private void test2() {
		System.out.println("private test2");
	}
	
	public static void test3() {
		System.out.println("static test3");
	}
}
