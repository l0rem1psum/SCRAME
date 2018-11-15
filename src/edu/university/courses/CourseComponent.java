package edu.university.courses;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import edu.university.students.Student;

/**
 * The abstract class of course component provides methods for managing
 * students/vacancies etc.
 */
public abstract class CourseComponent implements Serializable {

	private static final long serialVersionUID = -4703095934839336500L;
	private String componentName;
	private int numberOfGroups; // How many lectures/tutorials/labs groups for one course.
	List<Group> listOfGroups = null;

	/**
	 * The constructor for CourseComponent.
	 * 
	 * @param componentName  Name of the component (e.g. lecture)
	 * @param numberOfGroups Number of groups of a single course component.
	 */
	public CourseComponent(String componentName, int numberOfGroups) {
		this.componentName = componentName;
		this.numberOfGroups = numberOfGroups;
		this.listOfGroups = Arrays.asList(new Group[numberOfGroups]);
	}

	/**
	 * The method is used to register a student for a course component.
	 * 
	 * @param s           The student object.
	 * @param groupNumber The group number that a student wants to register.
	 * @return true upon successful registration, false otherwise.
	 */
	public abstract boolean registerStudent(Student s, int groupNumber);

	/**
	 * This method is used to check whether a course component still has vacancies
	 * for student to register.
	 * 
	 * @return true if there are vacancies available for registration, false
	 *         otherwise.
	 */
	public abstract boolean haveVacancies();

	/**
	 * This method is used to print all students registered under a course
	 * component, seperated by group.
	 */
	public abstract void printStudentList();

	/**
	 * This method is used to get the name of the course component.
	 * 
	 * @return the name of the course component.
	 */
	public String getName() {
		return this.componentName;
	}

	/**
	 * This method is used to get the number of groups for the current course
	 * component.
	 * 
	 * @return the number of groups for the course component.
	 */
	public int getNumberOfGroups() {
		return this.numberOfGroups;
	}

	/**
	 * This method is used to get the list groups for the current course component.
	 * 
	 * @return the list of groups for the course component.
	 */
	public List<Group> getListOfGroups() {
		return this.listOfGroups;
	}
}
