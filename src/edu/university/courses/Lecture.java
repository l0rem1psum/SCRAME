package edu.university.courses;

import edu.university.students.Student;

/**
 * The Lecture class extends CourseComponent abstract class.
 */
public class Lecture extends CourseComponent {
	// This Lecture class assumes there is only one Lecture group as it is normally
	// the case.

	private static final long serialVersionUID = 3026535502854716051L;

	/**
	 * The constructor that creates a Lecture object.
	 * 
	 * @param numberOfVacancies Number of vacancies for a lecture group.
	 */
	public Lecture(int numberOfVacancies) {
		super("Lecture", 1);
		this.listOfGroups.set(0, new Group(numberOfVacancies));
	}

	/**
	 * This method is used to register a student under a lecture group.
	 * 
	 * By default, the student is registered to the group indexed 0, since the
	 * assumption is that there is only 1 lecture group.
	 * 
	 * @param s           The student object.
	 * @param groupNumber The index number of the lecture group which the student
	 *                    wants to register. This parameter is not used since there
	 *                    is only 1 lecture group by default.
	 * @return true upon successful registration, false otherwise.
	 */
	@Override
	public boolean registerStudent(Student s, int groupNumber) {
		return this.listOfGroups.get(0).registerStudent(s);
		// Since only 1 lecture group is assumed, students are always added to group 1
		// which is at index 0.
	}

	/**
	 * This method is used to check whether the lecture has vacancies.
	 * 
	 * @return true if the lecture has vacancies, false otherwise.
	 */
	@Override
	public boolean haveVacancies() {
		return !this.listOfGroups.get(0).isFull();
	}

	/**
	 * This method is used to print all students registered under the lecture.
	 */
	@Override
	public void printStudentList() {
		System.out.println("+-----------------+---------------------------+");
		System.out.println("| Group           | Name                      |");
		System.out.println("+-----------------+---------------------------+");

		this.listOfGroups.get(0).printStudentList("Lecture");
	}
}
