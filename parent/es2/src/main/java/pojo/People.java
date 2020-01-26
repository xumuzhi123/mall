package pojo;

import java.io.Serializable;

public class People implements Serializable{
	int age;
	String name;
	public People(int age, String name) {
		super();
		this.age = age;
		this.name = name;
	}
	public People() {
	
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
