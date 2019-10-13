package com.hyh.object;

import com.hyh.object.inter.Manager;

public class Test {
	public static void main(String[] args) {
		Manager manager1 = new Manager();
		Manager manager2 = new Manager();
		Pair<Manager> pair = new Pair<>(manager1, manager2);
		pair.getFirst();
		pair.setFirst(manager2);
		//pair.printInfo(arg);
		Manager.test3();
	}
}
