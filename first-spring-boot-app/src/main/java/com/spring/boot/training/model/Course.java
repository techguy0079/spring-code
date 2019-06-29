package com.spring.boot.training.model;

public class Course {

	private String name;
	private int chapterCount;
//	public Course() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
	
	public Course(String name, int chapterCount) {
		
		this.name = name;
		this.chapterCount = chapterCount;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getChapterCount() {
		return chapterCount;
	}
	public void setChapterCount(int chapterCount) {
		this.chapterCount = chapterCount;
	}
	
}
