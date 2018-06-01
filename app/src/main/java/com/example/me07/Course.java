package com.example.me07;

public class Course {
	private String teaid;
	private String name;
	private String cou;
	public Course() {
		super();
	}
	public Course(String id, String name) {
		super();
		this.teaid = id;
		this.name = name;
	}
	public String getId() {
		return teaid;
	}
	public void setId(String id) {
		this.teaid = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCou() {
		return cou;
	}
	public void setCou(String cou) {
		this.cou = cou;
	}
	

}
