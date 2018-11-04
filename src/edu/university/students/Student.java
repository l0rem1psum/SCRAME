package edu.university.students;

import java.io.Serializable;
import java.util.HashMap;

import edu.university.assessments.Result;
import edu.university.courses.Course;

public class Student implements Serializable {

	private static final long serialVersionUID = -8006173904905537582L;
	private String studentName;
	private HashMap<Course, Result> coursesRegistered = new HashMap<>();

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

	public int getNumberOfCoursesRegistered() {
		return this.coursesRegistered.size();
	}

	public void updateResult(Course c, Result r) {
		this.coursesRegistered.put(c, r);
	}

	public Result getResultForCourse(Course c) {
		return this.coursesRegistered.get(c);
	}

	public HashMap<Course, Result> getCoursesRegistered() {
		return this.coursesRegistered;
	}
}
