package edu.university.students;

import java.io.Serializable;
import java.util.HashMap;

import edu.university.assessments.Result;
import edu.university.courses.Course;

public class Student implements Serializable {

	private static final long serialVersionUID = -8006173904905537582L;
	private String studentName;
	private HashMap<Course, Result> coursesRegistered = new HashMap<>();

	/**
	 * The constructor for a Student object.
	 * 
	 * @param studentName The name of the student.
	 */
	public Student(String studentName) {
		this.studentName = studentName;
	}

	/**
	 * This method check whether a student has registered for a course.
	 * 
	 * @param c The course for which one wants to check whether a student has
	 *          registered.
	 * @return true if a students has registered for a course, false otherwise.
	 */
	public boolean hasCourse(Course c) {
		return this.coursesRegistered.get(c) != null ? true : false;
	}

	/**
	 * This method is used to add a course to a student's registered course list.
	 * 
	 * @param course The course for which a student wants to register.
	 * @return true if a course is successfully added, false if the course is null
	 */
	public boolean addCourse(Course course) {
		if (course == null) {
			return false;
		} else {
			this.coursesRegistered.put(course, new Result());
			return true;
		}
	}

	/**
	 * This method is used to get the name of the student.
	 * 
	 * @return the name of the student.
	 */
	public String getStudentName() {
		return this.studentName;
	}

	/**
	 * This method is used to get the number of courses a student has registered.
	 * 
	 * @return the number of courses the student has registered.
	 */
	public int getNumberOfCoursesRegistered() {
		return this.coursesRegistered.size();
	}

	/**
	 * This method is used to update the result of a student.
	 * 
	 * @param c The course whose result will be updated.
	 * @param r The result of the course that needs to be updated.
	 */
	public void updateResult(Course c, Result r) {
		this.coursesRegistered.put(c, r);
	}

	/**
	 * This method is used to get the result of a course registered by the student.
	 * 
	 * @param c The course object
	 * @return The result associated with the course
	 */
	public Result getResultForCourse(Course c) {
		return this.coursesRegistered.get(c);
	}

	/**
	 * This method is used to get all the courses registered by a student.
	 * 
	 * @return a hashmap of all courses registered by a student.
	 */
	public HashMap<Course, Result> getCoursesRegistered() {
		return this.coursesRegistered;
	}
}
