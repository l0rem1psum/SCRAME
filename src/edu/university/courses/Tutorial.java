package edu.university.courses;

import edu.university.students.Student;

public class Tutorial extends CourseComponent {

	private static final long serialVersionUID = -8786897404446090661L;

	/**
	 * Constructor to create a Tutorial object.
	 * 
	 * @param numberOfGroups number of groups of a tutorial
	 * @param slotsPerGroup  number of total slots per tutorial group
	 */
	public Tutorial(int numberOfGroups, int slotsPerGroup) {
		super("Tutorial", numberOfGroups);
		for (int i = 0; i < numberOfGroups; i++) {
			this.listOfGroups.set(i, new Group(slotsPerGroup));
		}
	}

	/**
	 * This method is used to register a student under a tutorial group.
	 * 
	 * @param s           The student object.
	 * @param groupNumber The index number of the tutorial group which the student
	 *                    wants to register.
	 * @return true upon successful registration, false otherwise.
	 */
	@Override
	public boolean registerStudent(Student s, int groupNumber) {
		return this.listOfGroups.get(groupNumber - 1).registerStudent(s);
	}

	/**
	 * This method is used to check whether the tutorial groups have vacancies.
	 * 
	 * @return true if the any of the tutorial group has vacancies, false if none of
	 *         the group has vacancies.
	 */
	@Override
	public boolean haveVacancies() {
		boolean bn = false;
		for (Group g : this.listOfGroups) {
			bn |= (!g.isFull());
		}
		return bn;
	}

	/**
	 * This method is used to print all students registered under the each of the
	 * tutorial group.
	 */
	@Override
	public void printStudentList() {
		System.out.println("+-----------------+---------------------------+");
		System.out.println("| Group           | Name                      |");
		System.out.println("+-----------------+---------------------------+");
		for (int i = 0; i < this.getNumberOfGroups(); i++) {
			this.listOfGroups.get(i).printStudentList("Tutorial Grp " + String.valueOf(i + 1));
		}
	}
}
