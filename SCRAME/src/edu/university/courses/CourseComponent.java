package edu.university.courses;
import edu.university.students.*;
import java.util.*;

public abstract class CourseComponent {
	private String componentName;
	private int numberOfGroups; // How many lectures/tutorials/labs groups for one course.
	List<Group> listOfGroups = null;
	
	public CourseComponent(String componentName, int numberOfGroups){
		this.componentName = componentName;
		this.numberOfGroups = numberOfGroups;
		this.listOfGroups = Arrays.asList(new Group[numberOfGroups]);
	}
	
	public abstract boolean registerStudent(Student s, int groupNumber);
	
	public abstract boolean haveVacancies();
	
	public abstract void printStudentList();
	
	public String getName() {
		return this.componentName;
	}
	
	public int getNumberOfGroups() {
		return this.numberOfGroups;
	}
	
	public List<Group> getListOfGroups(){
		return this.listOfGroups;
	}
}
