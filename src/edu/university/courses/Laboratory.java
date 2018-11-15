package edu.university.courses;

import edu.university.students.Student;

public class Laboratory extends CourseComponent {

	private static final long serialVersionUID = 363149913616301192L;

	/**
	 * Constructor to create a Laboratory object.
	 * 
	 * @param numberOfGroups number of groups of a lab
	 * @param slotsPerGroup  number of total slots per lab group
	 */
	public Laboratory(int numberOfGroups, int slotsPerGroup) {
		super("Laboratory", numberOfGroups);
		for (int i = 0; i < numberOfGroups; i++) {
			this.listOfGroups.set(i, new Group(slotsPerGroup));
		}
	}

	/**
	 * This method is used to register a student under a lab group.
	 * 
	 * @param s           The student object.
	 * @param groupNumber The index number of the lab group which the student
	 *                    wants to register.
	 * @return true upon successful registration, false otherwise.
	 */
	@Override
	public boolean registerStudent(Student s, int groupNumber) {
		return this.listOfGroups.get(groupNumber - 1).registerStudent(s);
	}

	/**
	 * This method is used to check whether the lab groups have vacancies.
	 * 
	 * @return true if the any of the lab group has vacancies, false if none of
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
	 * lab group.
	 */
	@Override
	public void printStudentList() {
		System.out.println("+-----------------+---------------------------+");
		System.out.println("| Group           | Name                      |");
		System.out.println("+-----------------+---------------------------+");
		for (int i = 0; i < this.getNumberOfGroups(); i++) {
			this.listOfGroups.get(i).printStudentList("Lab Grp " + String.valueOf(i + 1));
		}
	}
}
