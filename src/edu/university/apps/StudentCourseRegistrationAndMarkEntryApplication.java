package edu.university.apps;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;

import edu.university.assessments.ComponentType;
import edu.university.assessments.Examinable;
import edu.university.assessments.MainComponent;
import edu.university.assessments.Result;
import edu.university.assessments.Subcomponent;
import edu.university.courses.Course;
import edu.university.professors.Professor;
import edu.university.students.Student;

/**
 * Application class of the SCRAME system.
 */
public class StudentCourseRegistrationAndMarkEntryApplication implements Serializable {

	private static final long serialVersionUID = 8870011238997588030L;
	private HashMap<String, Course> courseList = new HashMap<>();
	private HashMap<String, Student> studentsRegistered = new HashMap<>();
	private HashMap<String, Professor> teachingProfessors = new HashMap<>();

	static final Scanner sc = new Scanner(System.in);

	/**
	 * This is the main method to start the Student Course Registration and Mark
	 * Entry Application (SCRAME).
	 * 
	 * The method first initialize a
	 * StudentCourseRegistrationAndMarkEntryApplication object using loadSystem()
	 * method. If the method return null object it means that there is no data or
	 * the data file has been corrupted. Otherwise, it will read from the data file
	 * and initialize system from there.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		StudentCourseRegistrationAndMarkEntryApplication SCRAME = loadSystem();
		if (SCRAME != null) {
			SCRAME.execute();
		} else {
			System.out.println("System File Not Found!");
			System.out.println("Initializing New System!");
			StudentCourseRegistrationAndMarkEntryApplication newSCRAME = new StudentCourseRegistrationAndMarkEntryApplication();
			newSCRAME.execute();
		}
	}

	/**
	 * The method is used to execute the Student Course Registration and Mark Entry
	 * Application (SCRAME)
	 * 
	 * @return Nothing.
	 */
	public void execute() {
		int option;
		do {
			System.out.println(
					"+------------------------------------------------------------------------------------------+");
			System.out.println(
					"|           Student Course Registration And Mark Entry Application (SCRAME) Menu           |");
			System.out.println(
					"+---+----------------------------------+----+----------------------------------------------+");
			System.out.println(
					"| 1 | Add a student                    |  6 | Enter course assessment components weightage |");
			System.out.println(
					"| 2 | Add a course                     |  7 | Enter coursework mark                        |");
			System.out.println(
					"| 3 | Register Student for a course    |  8 | Enter exam mark                              |");
			System.out.println(
					"| 4 | Check available slots in a class |  9 | Print course statistics                      |");
			System.out.println(
					"| 5 | Print student list               | 10 | Print student transcript                     |");
			System.out.println(
					"+---+----------------------------------+----+----------------------------------------------+");
			System.out.println(
					"|                                                         (0): Save the system  (-1): Exit |");
			System.out.println(
					"+------------------------------------------------------------------------------------------+");
			System.out.println("Please enter your option:");
			option = sc.nextInt();
			sc.nextLine();
			switch (option) {
			case 1:
				this.addStudent();
				this.printAllStudents();
				break;
			case 2:
				this.addCourse();
				this.printAllCourses();
				break;
			case 3:
				this.registerStudentForCourse();
				break;
			case 4:
				this.checkSlots();
				break;
			case 5:
				this.printStudentList();
				break;
			case 6:
				this.enterCourseWeightage();
				break;
			case 7:
				this.enterCourseworkMark();
				break;
			case 8:
				this.enterExamMark();
				break;
			case 9:
				this.printCourseStatistics();
				break;
			case 10:
				this.printStudentTranscript();
				break;
			case 0:
				this.saveSystem();
				break;
			case -1:
				System.out.println("Shutting Down SCRAME...");
				System.out.println("BYE!");
				System.exit(0);
			}
		} while (option != -1);
	}

	/**
	 * This method is used to add student to the studentsRegistered ArrayList.
	 * 
	 * @return false if student is already existing in the ArrayList, true if the
	 *         student object has been added to the studentsRegistered ArrayList.
	 */
	private boolean addStudent() {
		System.out.println("What is the name of the student?");
		String studentName = sc.nextLine();
		if (this.studentExists(studentName)) {
			System.out.println("This student has already been added!");
			return false;
		} else {
			Student newStudent = new Student(studentName);
			this.studentsRegistered.put(studentName, newStudent);
			return true;
		}
	}

	/**
	 * This method is used to add courses to the courseList ArrayList.
	 * 
	 * <p>
	 * The user will be first asked to enter name of the course. Followed by the
	 * name of the professor. Since there are in total 3 combination of courses:
	 * <ul>
	 * <li>Course with lectures only</li>
	 * <li>Course with both lectures and tutorials</li>
	 * <li>Course with lectures, tutorials and laboratories</li>
	 * </ul>
	 * The user will be asked whether the course have tutorials first. If one course
	 * does not have tutorials, it will definitely not have labs. Similarly, the
	 * user will also be asked whether a course have laboratories.
	 * 
	 * <p>
	 * For each of the tutorial and laboratory, the user will be asked the number of
	 * groups in tutorial and laboratory sessions and the corresponding number of
	 * people in each group. For lecture, we assume there is only one lecture group
	 * for each course, which makes logical sense.
	 * 
	 * @return true when course is successfully registered in the system, false when
	 *         the course has already been added.
	 */
	private boolean addCourse() {
		System.out.println("What is the name of the course?");
		String courseName = sc.nextLine();
		if (this.courseExists(courseName)) {
			System.out.println("This course has already been added!");
			return false;
		} else {
			System.out.println("What is the name of the Professor?");
			String profName = sc.nextLine();
			Professor p = getProfessor(profName);

			int lectureVacancies;
			boolean haveTutorials = false, haveLabs = false;
			int numberOfTutorialGroups, numberOfLabGroups;
			int slotsPerTutGroup, slotsPerLabGroup;
			Course c;

			do {
				System.out.println("How many vacancies are there for a lecture?");
				while (!sc.hasNextInt()) {
					System.out.println("That's not a valid number. Please enter again.");
					sc.next();
				}
				lectureVacancies = sc.nextInt();
				sc.nextLine();
			} while (lectureVacancies <= 0);

			System.out.println("Does this course have tutorials? (Type Y for yes, or any other character for no.)");
			char tutorialOption = sc.next().charAt(0);
			if (Character.toLowerCase(tutorialOption) == 'y') {
				haveTutorials = true;
			}
			sc.nextLine();
			if (!haveTutorials) {
				c = new Course(courseName, p, lectureVacancies);
				this.courseList.put(courseName, c);
				p.addCourse(c);
				return true;
			} else {
				do {
					System.out.println("How many tutorial groups does this course have?");
					while (!sc.hasNextInt()) {
						System.out.println("That's not a valid number. Please enter again.");
						sc.next();
					}
					numberOfTutorialGroups = sc.nextInt();
					sc.nextLine();
				} while (numberOfTutorialGroups <= 0);

				do {
					System.out.println("How many vacancies are there in one tutorial group?");
					while (!sc.hasNextInt()) {
						System.out.println("That's not a valid number. Please enter again.");
						sc.next();
					}
					slotsPerTutGroup = sc.nextInt();
					sc.nextLine();
				} while (slotsPerTutGroup <= 0);
			}

			System.out.println("Does this course have labs? (Type Y for yes, or any other character for no.)");
			char labOption = sc.next().charAt(0);
			if (Character.toLowerCase(labOption) == 'y') {
				haveLabs = true;
			} else {
				System.out.println();
			}
			if (!haveLabs) {
				c = new Course(courseName, p, lectureVacancies, numberOfTutorialGroups, slotsPerTutGroup);
				this.courseList.put(courseName, c);
				p.addCourse(c);
				return true;
			} else {
				do {
					System.out.println("How many lab groups does this course have?");
					while (!sc.hasNextInt()) {
						System.out.println("That's not a valid number. Please enter again!");
						sc.next();
					}
					numberOfLabGroups = sc.nextInt();
					sc.nextLine();
				} while (numberOfLabGroups <= 0);

				do {
					System.out.println("How many vacancies are there in one lab group?");
					while (!sc.hasNextInt()) {
						System.out.println("That's not a valid number. Please enter again.");
						sc.next();
					}
					slotsPerLabGroup = sc.nextInt();
					sc.nextLine();
				} while (slotsPerLabGroup <= 0);
				c = new Course(courseName, p, lectureVacancies, numberOfTutorialGroups, slotsPerTutGroup,
						numberOfLabGroups, slotsPerLabGroup);
				this.courseList.put(courseName, c);
				p.addCourse(c);
				return true;
			}
		}
	}

	/**
	 * This method is used to register a student in a course.
	 * 
	 * Based on whether a course has tutorials or labs, the method will prompt user
	 * to enter the group number the student want to register (for tutorial and
	 * laboratories) By default, a student will always be registered to the lecture
	 * group, since every course has a lecture (by assumption).
	 * 
	 * @return true if a student has successfully registered for a course, false if
	 *         there is
	 *         <ul>
	 *         <li>No registered students in the system</li>
	 *         <li>No courses recorded in the system</li>
	 *         <li>Student does not exist in the system</li>
	 *         <li>Student already has registered course</li>
	 *         <li>Course has no more vacancies</li>
	 */
	private boolean registerStudentForCourse() {
		if (this.studentsRegistered.size() == 0) {
			System.out.println(
					"Sorry! There are currently no student records in the system. Please add the student before register courses for him/her");
			return false;
		}
		if (this.courseList.size() == 0) {
			System.out.println(
					"Sorry! There are currently no courses available for registering. Please add courses first.");
			return false;
		}

		System.out.println("Please enter the name of the student for which you want to register course for:");
		String studentName = sc.nextLine();

		if (!this.studentExists(studentName)) {
			System.out.println(
					"The student you entered is currently not recorded in the system. Please add the student before registering courses for him/her.");
			return false;
		}

		Student s = this.getStudent(studentName);

		Course course = this.selectCourse();

		if (s.hasCourse(course)) {
			System.out.printf("Sorry! The student you entered has already registered course %s.\n",
					course.getCourseName());
			return false;
		}

		System.out.printf("The vacancies information of course %s is as follows:\n", course.getCourseName());
		Course.printCourseSlotsHead();
		course.printCourseSlots();

		if (!course.haveVacancies()) {
			System.out.println("Sorry! The course you want to add has no more vacancies!");
			return false;
		} else {
			if (course.getCourseComponents().size() == 1) {
				// The course has only lectures.
				course.registerStudent(s); // Register student to the course.
				s.addCourse(course); // Add course to student's list of courses.
				System.out.printf("The student %s has successfully been registered to the course %s (Lecture Only).\n",
						s.getStudentName(), course.getCourseName());
				System.out.println("The new vacancies information is as follows:");
				Course.printCourseSlotsHead();
				course.printCourseSlots();
				return true;
			} else {
				// The course has lecture and tutorials or all 3 types (i.e. lectures,
				// tutorials, labs)
				System.out.println("Please enter the tutorial group number that the student wants to register:");
				int tutGroup = sc.nextInt();
				sc.nextLine();
				while (course.getCourseComponents().get(1).getListOfGroups().get(tutGroup - 1).isFull()) {
					System.out.println("The selected tutorial group has no more vacancies. Please choose again!");
					tutGroup = sc.nextInt();
					sc.nextLine();
				}

				if (course.getCourseComponents().size() == 2) {
					// The course has only lecture and tutorials
					course.registerStudent(s, tutGroup);
					s.addCourse(course);
					System.out.printf(
							"The student %s has successfully been registered to the course %s with tutorial group %d.\n",
							s.getStudentName(), course.getCourseName(), tutGroup);
					System.out.println("The new vacancies information is as follows:");
					Course.printCourseSlotsHead();
					course.printCourseSlots();
					return true;
				} else {
					// The course has all 3 types.
					System.out.println("Please enter the lab group number that the student wants to register:");
					int labGroup = sc.nextInt();
					sc.nextLine();
					while (course.getCourseComponents().get(2).getListOfGroups().get(labGroup - 1).isFull()) {
						System.out.println("The selected lab group has no more vacancies. Please choose again!");
						labGroup = sc.nextInt();
						sc.nextLine();
					}
					course.registerStudent(s, tutGroup, labGroup);
					s.addCourse(course);
					System.out.printf(
							"The student %s has successfully been registered to the course %s with tutorial group %d and lab group %d.\n",
							s.getStudentName(), course.getCourseName(), tutGroup, labGroup);
					System.out.println("The new vacancies information is as follows:");
					Course.printCourseSlotsHead();
					course.printCourseSlots();
					return true;
				}
			}
		}
	}

	/**
	 * The method is used to print the slots of a course as specified by the user.
	 */
	private void checkSlots() {
		if (this.courseList.size() == 0) {
			System.out.println(
					"Sorry, there are currently no courses in the system. Please add courses before checking the slots.");
			return;
		}

		Course course = this.selectCourse();
		Course.printCourseSlotsHead();
		course.printCourseSlots();

	}

	/**
	 * The method is used to print the registered students of a course by lecture
	 * group, tutorial group or lab group.
	 */
	public void printStudentList() {
		if (this.courseList.size() == 0) {
			System.out.println("Sorry, there are currently no courses in the system. Please add courses first.");
			return;
		}

		Course course = this.selectCourse();

		System.out.println("Do you want to?");
		System.out.println("+---+------------------------------------+");
		System.out.println("| 1 |  Print students by lecture         |");
		System.out.println("| 2 |  Print students by tutorial groups |");
		System.out.println("| 3 |  print students by lab groups      |");
		System.out.println("+---+------------------------------------+");
		int option = sc.nextInt();
		sc.nextLine();
		while (option != 1 && option != 2 && option != 3) {
			System.out.println("Please check your option! Enter again:");
			option = sc.nextInt();
			sc.nextLine();
		}

		switch (option) {
		case (1):
			course.printStudents(0);
			break;
		case (2):
			if (course.getCourseComponents().size() == 1) {
				System.out.printf("Sorry! The course %s does not have tutorials!\n", course.getCourseName());
			} else {
				course.printStudents(1);
			}
			break;
		case (3):
			if (course.getCourseComponents().size() != 3) {
				System.out.printf("Sorry! The course %s does not have labs!\n", course.getCourseName());
			} else {
				course.printStudents(2);
			}
			break;
		}
	}

	/**
	 * This method is used to enter the assessment weightage of a course.
	 * 
	 * By assumption, all courses have 2 main assessment components: coursework and
	 * examination, and there are only 2 cases:
	 * <ul>
	 * <li>Coursework does not have subcomponents</li>
	 * <li>Coursework has 2 or more subcomponents</li>
	 * </ul>
	 * The user is first asked the weightage of examination, and coursework will
	 * naturally be 100 minus the weight of the examination. Then the user will be
	 * asked whether the coursework has/does not have subcomponents, and user will
	 * be prompt to under the weighage of each of the subcomponent.
	 */
	private void enterCourseWeightage() {
		Course course = this.selectCourse();
		HashMap<String, Integer> assessmentComponents = new HashMap<>();

		System.out.println("Please enter the exam weightage:");
		int examWeightage = sc.nextInt();
		sc.nextLine();
		while (examWeightage <= 0 | examWeightage >= 100) {
			System.out.println("Sorry! Please enter a valid exam weightage!");
			examWeightage = sc.nextInt();
			sc.nextLine();
		}
		assessmentComponents.put("Examination", examWeightage);
		assessmentComponents.put("Coursework", 100 - examWeightage);
		course.setAssessmentComponents(assessmentComponents);

		boolean hasSubcomponents;
		System.out.println("Does coursework have subcomponents? (true/false)");
		hasSubcomponents = sc.nextBoolean();
		sc.nextLine();
		if (hasSubcomponents) {
			int numberOfSubcomponents;
			int subcomponentWeightage;
			String subcomponentName;
			HashMap<String, Integer> courseworkComponents = new HashMap<>();
			;

			System.out.println("Please enter the number of subcomponents:");
			numberOfSubcomponents = sc.nextInt(); // Users may enter -1, 0, 1
			sc.nextLine();
			int weightageSum = 0;
			while (weightageSum != 100) {
				courseworkComponents = new HashMap<>();
				weightageSum = 0;
				for (int i = 0; i < numberOfSubcomponents; i++) {
					System.out.printf("What is the name of subcomponent %d?\n", i + 1);
					subcomponentName = sc.nextLine();
					System.out.printf("What is the weightage of %s (as a percentage of coursework)\n",
							subcomponentName);
					subcomponentWeightage = sc.nextInt(); // Users may enter negative values.
					sc.nextLine();
					courseworkComponents.put(subcomponentName, subcomponentWeightage);
				}

				for (Integer val : courseworkComponents.values()) {
					weightageSum += val;
				}

				if (weightageSum != 100) {
					System.out.println("Sorry, please check your input. Please enter again!");
				}
			}
			course.setCourseworkSubcomponents(courseworkComponents);
		}
	}

	/**
	 * The method is used to enter the coursework mark of a student.
	 * 
	 * The method will iterate through all the students registered for a course, and
	 * prompt user to enter the raw coursework mark if the coursework does not have
	 * subcomponent. In the case when coursework has subcomponents, the user will
	 * enter the raw subcomponent mark one by one for each of the registered
	 * student.
	 * 
	 * The method does not check whether a student has already has his coursework
	 * mark entered. So each time the method is called, it will reenter the
	 * coursework mark for all students. Such assumption is made since normally the
	 * mark will only be entered once, that is at the end of each semester.
	 */
	private void enterCourseworkMark() {
		if (this.studentsRegistered.size() == 0) {
			System.out.println(
					"Sorry! There are currently no student records in the system. Please add the student before entering exam mark for him/her");
			return;
		}
		if (this.courseList.size() == 0) {
			System.out.println(
					"Sorry! There are currently no courses available for registering. Please add courses first.");
			return;
		}

		Course course = this.selectCourse();
		if (course.hasWeightageInfo()) {
			if (course.getRegisteredStudents().size() == 0) {
				System.out.printf("Sorry! There are currently no student who has registered the course %s.\n",
						course.getCourseName());
			} else {
				for (Student s : course.getRegisteredStudents()) {
					Result r = s.getResultForCourse(course);
					if (!course.hasCourseworkSubcomponents()) {
						System.out.printf("What is the coursework mark for %s?\n", s.getStudentName()); // Users may
																										// enter invalid
																										// input
						int courseworkMark = sc.nextInt();
						sc.nextLine();
						Examinable coursework = new MainComponent(ComponentType.Coursework,
								course.getCourseworkWeightage(), courseworkMark);
						r.addComponent(ComponentType.Coursework, coursework);
						return;
					} else {
						MainComponent coursework = new MainComponent(ComponentType.Coursework,
								course.getCourseworkWeightage());
						for (HashMap.Entry<String, Integer> entry : course.getCourseworkComponents().entrySet()) {
							System.out.printf("What is the %s mark for %s?\n", entry.getKey(), s.getStudentName());
							int subcomponentMark = sc.nextInt();
							sc.nextLine();
							Subcomponent subcomponent = new Subcomponent(entry.getKey(), entry.getValue(),
									subcomponentMark);
							coursework.addSubcomponent(subcomponent);
						}
						r.addComponent(ComponentType.Coursework, coursework);
					}
				}
			}
		} else {
			System.out.println(
					"Sorry! This course currently does not have assessment component weightage information recorded in the system!");
			System.out.printf(
					"Please go to the main menu and enter the assessment component weightage information for %s.\n",
					course.getCourseName());
		}

	}

	/**
	 * The method is used to enter the examination mark of a student.
	 * 
	 * The method will iterate through all the students registered for a course, and
	 * prompt user to enter the raw examination mark for a student.
	 * 
	 * Similar to the enterCourseworkMark() method, this method does not check
	 * whether a student has already has his/her mark recorded. This is based on the
	 * assumption that examination will only be entered once, that is at the end of
	 * each semester.
	 */
	private void enterExamMark() {
		if (this.studentsRegistered.size() == 0) {
			System.out.println(
					"Sorry! There are currently no student records in the system. Please add the student before entering exam mark for him/her");
			return;
		}
		if (this.courseList.size() == 0) {
			System.out.println(
					"Sorry! There are currently no courses available for entering mark. Please add courses first.");
			return;
		}

		Course course = this.selectCourse();
		if (course.hasWeightageInfo()) {
			if (course.getRegisteredStudents().size() == 0) {
				System.out.printf("Sorry! There are currently no student who has registered the course %s.\n",
						course.getCourseName());
			} else {

				for (Student s : course.getRegisteredStudents()) {
					Result r = s.getResultForCourse(course);
					System.out.printf("What is the examination mark for %s?\n", s.getStudentName()); // Users may
																										// provide
																										// invalid input
					int examMark = sc.nextInt();
					sc.nextLine();
					Examinable exam = new MainComponent(ComponentType.Examination, course.getExamWeightage(), examMark);
					r.addComponent(ComponentType.Examination, exam);
				}
			}
		} else {
			System.out.println(
					"Sorry! This course currently does not have assessment component weightage information recorded in the system!");
			System.out.printf(
					"Please go to the main menu and enter the assessment component weightage information for %s.\n",
					course.getCourseName());
		}
	}

	/**
	 * This method is used to print the statistics of a particular course.
	 * 
	 * The method will only be able to run if all the students registered under a
	 * course have their marks entered.
	 */
	private void printCourseStatistics() {
		// Check whether there is course/whether the course has weightage
		// information/whether students have marks.
		if (this.studentsRegistered.size() == 0) {
			System.out.println(
					"Sorry! There are currently no student records in the system. Please add the student before entering exam mark for him/her");
			return;
		}
		if (this.courseList.size() == 0) {
			System.out.println("Sorry! There are currently no courses in the system. Please add courses first.");
			return;
		}

		Course course = this.selectCourse();
		int studentNumber = course.getRegisteredStudents().size();

		if (studentNumber == 0) {
			System.out.println("Sorry, no one has registered this course yet!");
		}

		double examMark = 0;
		double courseworkMark = 0;
		double totalMark = 0;
		double highestMark = course.getRegisteredStudents().get(0).getResultForCourse(course).getTotalMark();
		double lowestMark = course.getRegisteredStudents().get(0).getResultForCourse(course).getTotalMark();

		try {
			for (Student s : course.getRegisteredStudents()) {
				Result r = s.getResultForCourse(course);

				if (r.getTotalMark() > highestMark) {
					highestMark = r.getTotalMark();
				}

				if (r.getTotalMark() < lowestMark) {
					lowestMark = r.getTotalMark();
				}

				examMark += r.getExaminationMark();
				courseworkMark += r.getCourseworkMark();
				totalMark += r.getTotalMark();
			}
		} catch (NullPointerException e) {
			System.out.println(
					"Some students do not have a mark. Please make sure every student have a mark for both examination and coursework.");
			return;
		}

		System.out.printf("Statistical summary of course %s:\n", course.getCourseName());
		System.out.println("+-------------------------------------------------+---------+");
		System.out.printf("| Number of students registered for the course    | %7d |\n", studentNumber);
		System.out.println("+-------------------------------------------------+---------+");
		System.out.printf("| Examination grade percentage (%2d%%)              | %6.2f%% |\n",
				course.getExamWeightage(), examMark / studentNumber);
		System.out.println("+-------------------------------------------------+---------+");
		System.out.printf("| Coursework grade percentage (%2d%%)               | %6.2f%% |\n",
				course.getCourseworkWeightage(), courseworkMark / studentNumber);
		System.out.println("+-------------------------------------------------+---------+");
		System.out.printf("| Overall grade percentage                        | %6.2f%% |\n", totalMark / studentNumber);
		System.out.println("+-------------------------------------------------+---------+");
		System.out.printf("| Highest overall grade                           | %6.2f%% |\n", highestMark);
		System.out.println("+-------------------------------------------------+---------+");
		System.out.printf("| Lowest overall grade                            | %6.2f%% |\n", lowestMark);
		System.out.println("+-------------------------------------------------+---------+");
	}

	/**
	 * This method is used to print the transcript of a selected student.
	 * 
	 * For a student, this method will print all the courses he has registered and
	 * those that have all the marks entered.
	 */
	private void printStudentTranscript() {
		if (this.studentsRegistered.size() == 0) {
			System.out.println(
					"Sorry! There are currently no student records in the system. Please add the student before entering exam mark for him/her");
			return;
		}
		Student student = this.selectStudent();
		if (student.getNumberOfCoursesRegistered() == 0) {
			System.out.println("Sorry! The student %s has not registered any course.");
			return;
		}
		for (HashMap.Entry<Course, Result> entry : student.getCoursesRegistered().entrySet()) {
			entry.getValue().print(entry.getKey().getCourseName());
		}
	}

	/**
	 * This method is used to check whether a particular student is registered in
	 * the system.
	 * 
	 * @param studentName This is the name of student.
	 * @return true if the student is registered and false otherwise.
	 */
	private boolean studentExists(String studentName) {
		return this.studentsRegistered.get(studentName) != null;
	}

	/**
	 * This method is used to check whether a particular course is registered in the
	 * system.
	 * 
	 * @param courseName This is the name of the course.
	 * @return true if the course exists and false otherwise.
	 */
	private boolean courseExists(String courseName) {
		return this.courseList.get(courseName) != null;
	}

	/**
	 * This method is used to get access the professor object in/not in the system.
	 * 
	 * @param profName This is the name of the professor.
	 * @return Professor object if the name of professor matches any of the record
	 *         registered in the system. Otherwise, a new Professor (with name
	 *         profName, the parameter of method) will be created in the method and
	 *         returne.
	 */
	private Professor getProfessor(String profName) {
		Professor prof = this.teachingProfessors.get(profName);
		if (prof != null) {
			return prof;
		} else {
			// In case the named Professor is not in the current list of professors,
			// create a new professor object and store it in the list.
			prof = new Professor(profName);
			this.teachingProfessors.put(profName, prof);
			return prof;
		}
	}

	/**
	 * This method is used to access the student that is already registered in the
	 * system.
	 * 
	 * This method access the student that is already registered. This means caution
	 * must be exercised when using the method, since it will return null if name of
	 * the student does not match any of the record. To select a student (safer),
	 * use the selectStudent() method.
	 * 
	 * @param studentName This is the name of the student.
	 * @return Student (those who are already registered in the system) object.
	 */
	private Student getStudent(String studentName) {
		return this.studentsRegistered.get(studentName);
	}

	/**
	 * This method is used to access the course that is already registered in the
	 * system.
	 * 
	 * This method access the course that is already registered. This means caution
	 * must be exercised when using the method, since it will return null if the
	 * coursename does not match any of the record. To select a course (safer), use
	 * the selectCourse() method.
	 * 
	 * @param courseName This is the name of the course.
	 * @return Course (those who are already registered in the system) object.
	 */
	private Course getCourse(String courseName) {
		return this.courseList.get(courseName);
	}

	/**
	 * This method is used to select course that is already registered in the
	 * system.
	 * 
	 * @return Course (those who are already registered in the system) object with
	 *         name specified by the caller of the method.
	 */
	private Course selectCourse() {
		System.out.println("Please choose the course listed below:");
		this.printAllCourses();

		String courseName = sc.nextLine();
		while (!this.courseExists(courseName)) {
			System.out.println(
					"Please make sure you only enter the course name that is already registered in the system!");
			System.out.println("Please choose the course listed below:");
			this.printAllCourses();
			courseName = sc.nextLine();
		}

		return this.getCourse(courseName);
	}

	/**
	 * This method is used to select student who is already registered in the
	 * system.
	 * 
	 * @return Student (those who is already registered in the system) object with
	 *         name specified by the caller of the method.
	 */
	private Student selectStudent() {
		System.out.println("Please enter the student name:");
		String studentName = sc.nextLine();
		while (!this.studentExists(studentName)) {
			System.out.println(
					"Please make sure you only enter the student name that is already registered in the system!");
			System.out.println("Please enter the student name:");
			studentName = sc.nextLine();
		}

		return this.getStudent(studentName);
	}

	/**
	 * This method is used to print all the courses registered in the system with
	 * their respective course coordinator.
	 */
	private void printAllCourses() {
		System.out.println("+-------------+--------------------+");
		System.out.println("| Course Name | Course Coordinator |");
		System.out.println("+-------------+--------------------+");
		for (Course c : this.courseList.values()) {
			System.out.printf("| %-11s | %-18s |\n", c.getCourseName(), c.getCourseCoordinatorName());
		}
		System.out.println("+-------------+--------------------+");
	}

	/**
	 * This method is used to print all the students registered in the system.
	 */
	private void printAllStudents() {
		System.out.println("+----------------------------------+");
		System.out.println("|           Student Name           |");
		System.out.println("+----------------------------------+");
		for (Student s : this.studentsRegistered.values()) {
			System.out.printf("| %-32s |\n", s.getStudentName());
		}
		System.out.println("+----------------------------------+");
	}

	/**
	 * The method is used to serialize the state of the system.
	 * 
	 * To be more specific, the method serializes the
	 * StudentCourseRegistrationAndMarkEntryApplication object.
	 */
	private void saveSystem() {
		try {
			FileOutputStream fileOut = new FileOutputStream("res/Sys.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
			System.out.println("Saved!");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	/**
	 * The method is used to deserialize the state of the system.
	 * 
	 * @return a StudentCourseRegistrationAndMarkEntryApplication object that is
	 *         saved during the previous execution of the program, null if there is
	 *         an IOException of ClassNotFoundException. This happens when the
	 *         system binary file is corrupted or the structure of the program has
	 *         been modified.
	 */
	private static StudentCourseRegistrationAndMarkEntryApplication loadSystem() {
		StudentCourseRegistrationAndMarkEntryApplication s = null;
		try {
			FileInputStream fileIn = new FileInputStream("res/Sys.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			s = (StudentCourseRegistrationAndMarkEntryApplication) in.readObject();
			in.close();
			fileIn.close();
			return s;
		} catch (IOException i) {
			i.printStackTrace();
			return s;
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return s;
		}
	}

}
