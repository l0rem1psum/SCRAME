package edu.university.professors;
import edu.university.courses.*;
import java.util.*;


public class Professor {
	private String name;
	ArrayList<Course> coursesInCharge = new ArrayList<>();
	
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
