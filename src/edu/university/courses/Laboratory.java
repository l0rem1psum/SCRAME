package edu.university.courses;

import edu.university.students.*;

public class Laboratory extends CourseComponent{
	public Laboratory(int numberOfGroups, int slotsPerGroup) {
		super("Laboratory", numberOfGroups);
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
		for (Group g: this.listOfGroups) {
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
			this.listOfGroups.get(i).printStudentList("Lab Grp " + String.valueOf(i + 1));
		}
	}
}