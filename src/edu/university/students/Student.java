package edu.university.students;

import edu.university.assessments.*;
import java.util.*;
import java.io.*;

import edu.university.courses.Course;

public class Student implements Serializable{
	private String studentName;
	private HashMap<Course, Result> coursesRegistered = new HashMap<>();
	private static final long serialVersionUID = 9L;

	public Student(String studentName) {
		this.studentName = studentName;
	}
	
	public boolean hasCourse(Course c) {	
		return this.coursesRegistered.get(c) != null ? true : false;
	}
	
	public boolean addCourse(Course course) {
		if (course == null) {
			return false;
		} else {
			this.coursesRegistered.put(course, new Result());
			return true;
		}
	}
	
	public String getStudentName() {
		return this.studentName;
	}
	
	public void updateResult(Course c, Result r) {
		this.coursesRegistered.put(c, r);
	}
	
	public Result getResultForCourse(Course c) {
		return this.coursesRegistered.get(c);
	}
}
