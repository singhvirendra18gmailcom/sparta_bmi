package com.bmi.sparta.models;

import org.springframework.stereotype.Component;

@Component
public class UserDTO {

	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String height;
	private String weight;
	private int age;

	public UserDTO() {
		super();
	}

	public UserDTO(String id, String name, String height, String weight, int age) {
		super();
		this.id = id;
		this.name = name;
		this.height = height;
		this.weight = weight;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
