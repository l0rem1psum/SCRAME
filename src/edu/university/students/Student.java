package edu.university.students;
import java.util.ArrayList;

import edu.university.courses.Course;

public class Student {
	private String studentName;
	private ArrayList<Course> coursesRegistered = new ArrayList<Course>();
	
	public Student(String studentName) {
		this.studentName = studentName;
	}
	
	public boolean hasCourse(Course c) {
		for (Course course: this.coursesRegistered) {
			if (course == c) {
				return true;
			}
		}
		return false;
	}
	
	public boolean addCourse(Course... courses) {
		if (courses == null) {
			return false; // No course added
		}
		for (Course c: courses) {
			coursesRegistered.add(c);
		}
		return true; // Added all course(s)
	}
	
	public String getStudentName() {
		return this.studentName;
	}
}
