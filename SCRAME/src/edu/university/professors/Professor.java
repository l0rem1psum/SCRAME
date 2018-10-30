package edu.university.professors;

import java.io.*;
import edu.university.courses.*;
import java.util.*;


public class Professor implements Serializable{
	private String name;
	ArrayList<Course> coursesInCharge = new ArrayList<>();
	private static final long serialVersionUID = 8L;
	
	public Professor(String name) {
		this.name = name;
		//this.coursesInCharge = null;
	}
	
	public void addCourse(Course c) {
		coursesInCharge.add(c);
	}
	
	public String getName() {
		return this.name;
	}
}
