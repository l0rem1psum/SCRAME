package edu.university.courses;

import edu.university.students.Student;

public class Tutorial extends CourseComponent {

	private static final long serialVersionUID = -8786897404446090661L;

	public Tutorial(int numberOfGroups, int slotsPerGroup) {
		super("Tutorial", numberOfGroups);
		for (int i = 0; i < numberOfGroups; i++) {
			this.listOfGroups.set(i, new Group(slotsPerGroup));
		}
	}

	@Override
	public boolean registerStudent(Student s, int groupNumber) {
		return this.listOfGroups.get(groupNumber - 1).registerStudent(s);
	}

	@Override
	public boolean haveVacancies() {
		boolean bn = false;
		for (Group g : this.listOfGroups) {
			bn |= (!g.isFull());
		}
		return bn;
	}

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
