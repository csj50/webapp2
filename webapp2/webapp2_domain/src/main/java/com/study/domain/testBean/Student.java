package com.study.domain.testBean;

public class Student {
	private String name;
	private int age;

	public void sayHello() {
		System.out.println("student say hello." + " name=" + name + " age=" + age);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
