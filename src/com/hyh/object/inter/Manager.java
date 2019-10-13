package com.hyh.object.inter;

public class Manager extends Employee {
	private String level;
	
	public Manager() {
		super();
		System.out.println("m");
	}
	
	public Manager(String l) {
		super(l);
		this.level = l;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
}
