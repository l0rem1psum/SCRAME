package edu.university.courses;

import edu.university.students.Student;

public class Lecture extends CourseComponent {
	// This Lecture class assumes there is only one Lecture group as it is normally
	// the case.

	private static final long serialVersionUID = 3026535502854716051L;

	public Lecture(int numberOfVacancies) {
		super("Lecture", 1);
		this.listOfGroups.set(0, new Group(numberOfVacancies));
	}

	@Override
	public boolean registerStudent(Student s, int groupNumber) {
		return this.listOfGroups.get(0).registerStudent(s);
		// Since only 1 lecture group is assumed, students are always added to group 1
		// which is at index 0.
	}

	@Override
	public boolean haveVacancies() {
		return !this.listOfGroups.get(0).isFull();
	}

	@Override
	public void printStudentList() {
		System.out.println("+-----------------+---------------------------+");
		System.out.println("| Group           | Name                      |");
		System.out.println("+-----------------+---------------------------+");

		this.listOfGroups.get(0).printStudentList("Lecture");
	}
}
