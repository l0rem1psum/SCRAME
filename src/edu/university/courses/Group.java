package edu.university.courses;

import java.io.*;
import edu.university.students.Student;

import java.util.*;

public class Group implements Serializable{

	private static final long serialVersionUID = -2445920414804040966L;
	private int numberOfSlots; 
	// Equal to the sum of number of vacancies plus the number of registered students.
	// Assuming this number is fixed and no changes will be made during the semester.
	private int numberOfVacancies;
	private List<Student> registeredStudents = null;
	
	public Group(int numberOfSlots) {
		this.numberOfSlots = numberOfSlots;
		this.numberOfVacancies = numberOfSlots;
		this.registeredStudents = Arrays.asList(new Student[numberOfSlots]);
		// Fix the length of the list of student to the number of slots during the creation of the object.
		// Cannot call add() method to add elements. Must use set(). Otherwise an exception will be thrown.
	}
	
	public boolean registerStudent(Student s) {
		if (this.isFull()) {
			return false;
		} else {
			this.registeredStudents.set(this.numberOfSlots - this.numberOfVacancies, s);
			this.numberOfVacancies--;
			// Assuming 12 slots and 12 vacancies, then the first student to be added is at index 12 - 12 = 0.
			return true;
		}
	}
	
	public void printStudentList(String groupName) {
		if (this.numberOfSlots == this.numberOfVacancies) {
			System.out.printf("| %-15s | %-25s |\n", groupName, new String("(No record)"));
		}
		for (int i = 0; i < this.numberOfSlots - this.numberOfVacancies; i++) {
			if (i == 0) {
				System.out.printf("| %-15s | %-25s |\n", groupName, this.registeredStudents.get(i).getStudentName());
			} else {
				System.out.printf("|                 | %-25s |\n", this.registeredStudents.get(i).getStudentName());
			}
		}
		System.out.println("+-----------------+---------------------------+");
	}
	
	public boolean isFull() {
		return this.numberOfVacancies == 0;
	}
	
	public int getNumberOfVacancies() {
		return this.numberOfVacancies;
	}
	
	public int getNumberOfSlots() {
		return this.numberOfSlots;
	}
	
	public List<Student> getRegisteredStudents(){
		return this.registeredStudents;
	}
}
