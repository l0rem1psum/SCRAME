package edu.university.courses;

import java.io.*;
import edu.university.students.*;
import edu.university.professors.*;
import java.util.*;

public class Course implements Serializable{
	private String courseName;
	private Professor courseCoordinator;
	private ArrayList<CourseComponent> courseComponents = new ArrayList<CourseComponent>();
	private HashMap<String, Integer> assessmentComponents = new HashMap<>();
	private HashMap<String, Integer> courseworkComponents = new HashMap<>();
	private static final long serialVersionUID = 2L;
	
	public Course(String courseName, Professor courseCoordinator, int lectureVacancies) {
		// Since all courses have lectures.
		// Before constructing the Course object, ask whether there is tutorials or labs
		// and if there is, how many tutorials/labs groups per course.
		this.courseName = courseName;
		this.courseCoordinator = courseCoordinator;
		
		CourseComponent lecture = new Lecture(lectureVacancies);
		courseComponents.add(lecture);
	}
	
	public Course(String courseName, Professor courseCoordinator, int lectureVacancies, int numberOfTutorialGroups, int slotsPerTutGroup) {
		this(courseName, courseCoordinator, lectureVacancies);
		
		CourseComponent tutorial = new Tutorial(numberOfTutorialGroups, slotsPerTutGroup);
		courseComponents.add(tutorial);

	}
	
	public Course(String courseName, Professor courseCoordinator, int lectureVacancies, int numberOfTutorialGroups, int slotsPerTutGroup, int numberOfLabGroups, int slotsPerLabGroup) {
		this(courseName, courseCoordinator, lectureVacancies, numberOfTutorialGroups, slotsPerTutGroup);

		CourseComponent lab = new Laboratory(numberOfLabGroups, slotsPerLabGroup);
		courseComponents.add(lab);
	}
	
	public void registerStudent(Student s) {
		this.courseComponents.get(0).registerStudent(s, 1); // The second argument can be any number since it is not used.
	}
	
	public void registerStudent(Student s, int tutGroup) {
		this.registerStudent(s);
		this.courseComponents.get(1).registerStudent(s, tutGroup);
	}
	
	public void registerStudent(Student s, int tutGroup, int labGroup) {
		this.registerStudent(s, tutGroup);
		this.courseComponents.get(2).registerStudent(s, labGroup);
	}
	
	public void printStudents(int option) {
		// index = 0 : print by lecture, index = 1 : print by tutorial, index = 2: print by lab
		this.courseComponents.get(option).printStudentList();
		
	}
	
	public boolean haveVacancies() {
		boolean bn = true;
		for (CourseComponent cc: this.courseComponents) {
			bn &= cc.haveVacancies();
		}
		return bn;
	}
	
	public boolean hasWeightageInfo() {
		return !this.assessmentComponents.isEmpty();
	}
	
	public boolean hasCourseworkSubcomponents() {
		return !this.courseworkComponents.isEmpty();
	}
	
	public HashMap<String, Integer> getAssessmentComponents(){
		return this.assessmentComponents;
	}
	
	public void setAssessmentComponents(HashMap<String, Integer> assessmentComponents) {
		this.assessmentComponents = assessmentComponents;
	}
	
	public HashMap<String, Integer> getCourseworkComponents(){
		return this.courseworkComponents;
	}
	
	public void setCourseworkSubcomponents(HashMap<String, Integer> courseworkComponents) {
		this.courseworkComponents = courseworkComponents;
	}
	
	public static void printCourseSlotsHead() {
		System.out.println("+-------------+-----------------+-----------+");
		System.out.println("| Course Name |      Group      | Vacancies |");
		System.out.println("|             |                 | /Slots    |");
		System.out.println("+-------------+-----------------+-----------+");
	}
	
	public void printCourseSlots() {
		System.out.printf("| %-11s | Lecture         | %4d/%-4d |\n", this.getCourseName(),
				this.getCourseComponents().get(0).getListOfGroups().get(0).getNumberOfVacancies(),
				this.getCourseComponents().get(0).getListOfGroups().get(0).getNumberOfSlots());
		if (this.getCourseComponents().size() == 1) {
			System.out.println("+-------------+-----------------+-----------+");
			return;
		}
		System.out.println("|             +-----------------+-----------+");
		
		if (this.getCourseComponents().size() >= 2) {
			// This means that the course has more than or equal to 2 components (i.e. lect
			// and tut or lect, tut and lab)
			int i = 1;
			for (Group group : this.getCourseComponents().get(1).getListOfGroups()) {
				System.out.printf("|             | Tutorial Grp %-2d | %4d/%-4d |\n", i,
						group.getNumberOfVacancies(), group.getNumberOfSlots());
				i++;
			}
			if (this.getCourseComponents().size() == 2) {
				System.out.println("+-------------+-----------------+-----------+");
				return;
			}
		}

		if (this.getCourseComponents().size() == 3) {
			// This means that the course has all 3 components
			System.out.println("|             +-----------------+-----------+");
			int i = 1;
			for (Group group : this.getCourseComponents().get(2).getListOfGroups()) {
				System.out.printf("|             | Lab Grp %-2d      | %4d/%-4d |\n", i,
						group.getNumberOfVacancies(), group.getNumberOfSlots());
				i++;
			}
			System.out.println("+-------------+-----------------+-----------+");
		}
	}
	
	public ArrayList<Student> getRegisteredStudents(){
		ArrayList<Student> registeredStudents = new ArrayList<>();
		for (int i = 0; 
				i < (this.courseComponents.get(0).getListOfGroups().get(0).getNumberOfSlots() - 
						this.courseComponents.get(0).getListOfGroups().get(0).getNumberOfVacancies());
				i++) {
			registeredStudents.add(this.courseComponents.get(0).getListOfGroups().get(0).getRegisteredStudents().get(0));
		}
		return registeredStudents;
	}
	
	public int getExamWeightage() {
		return this.assessmentComponents.get("Examination");
	}
	
	public int getCourseworkWeightage() {
		return this.assessmentComponents.get("Coursework");
	}
	
	public String getCourseName() {
		return this.courseName;
	}
	
	public String getCourseCoordinatorName() {
		return this.courseCoordinator.getName();
	}
	
	public ArrayList<CourseComponent> getCourseComponents(){
		return this.courseComponents;
	}
}
