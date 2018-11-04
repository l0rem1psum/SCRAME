package edu.university.professors;

import java.io.Serializable;
import java.util.ArrayList;

import edu.university.courses.Course;

public class Professor implements Serializable {

	private static final long serialVersionUID = -4339808637498598313L;
	private String name;
	ArrayList<Course> coursesInCharge = new ArrayList<>();

	public Professor(String name) {
		this.name = name;
		// this.coursesInCharge = null;
	}

	public void addCourse(Course c) {
		coursesInCharge.add(c);
	}

	public String getName() {
		return this.name;
	}
}
