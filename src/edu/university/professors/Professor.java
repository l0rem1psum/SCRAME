package edu.university.professors;

import java.io.Serializable;
import java.util.ArrayList;

import edu.university.courses.Course;

public class Professor implements Serializable {

	private static final long serialVersionUID = -4339808637498598313L;
	private String name;
	ArrayList<Course> coursesInCharge = new ArrayList<>();

	/**
	 * The constructor for a professor.
	 * 
	 * @param name The name of the professor.
	 */
	public Professor(String name) {
		this.name = name;
	}

	/**
	 * The method is used to add the course taught by a professor.
	 * 
	 * @param c The course that the professor is teaching.
	 */
	public void addCourse(Course c) {
		coursesInCharge.add(c);
	}

	/**
	 * This method is used to get the name of the professor.
	 * 
	 * @return the name of the professor.
	 */
	public String getName() {
		return this.name;
	}
}
