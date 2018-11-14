package edu.university.assessments;

import java.io.Serializable;
import java.util.HashMap;

/**
* 
 */
public class Result implements Serializable {

	private static final long serialVersionUID = -8325924104954052339L;
	private HashMap<ComponentType, Examinable> examinableAssessments = new HashMap<>();

	public Result() {
	}

	public double getTotalMark() {
		double totalMark = 0;
		for (Examinable e : this.examinableAssessments.values()) {
			totalMark += e.getMark();
		}
		return totalMark;
	}

	public double getExaminationMark() {
		return this.examinableAssessments.get(ComponentType.Examination).getMark();
	}

	public double getCourseworkMark() {
		return this.examinableAssessments.get(ComponentType.Coursework).getMark();
	}

	public void addComponent(ComponentType t, Examinable e) {
		this.examinableAssessments.put(t, e);
	}

	public void print(String courseName) {

		// TODO: Check whether the result has all components recorded

		System.out.printf("+--------------------------------+----------+--------------------+----------------+\n");
		System.out.printf("| %-10s                     | Raw Mark | Overall Percentage | Component Mark |\n",
				courseName);
		System.out.printf("+--------------------------------+----------+--------------------+----------------+\n");
		System.out.printf("| Examination (%2d%%)              |     %4.0f |                %2d%% |           %4.1f |\n",
				this.examinableAssessments.get(ComponentType.Examination).getWeightage(),
				this.examinableAssessments.get(ComponentType.Examination).getRawMark(),
				this.examinableAssessments.get(ComponentType.Examination).getWeightage(),
				this.examinableAssessments.get(ComponentType.Examination).getMark());
		if (!((MainComponent) this.examinableAssessments.get(ComponentType.Coursework)).hasSubcomponents()) {
			System.out.printf(
					"| Coursework (%2d%%)               |     %4.0f |                %2d%% |           %4.1f |\n",
					this.examinableAssessments.get(ComponentType.Coursework).getWeightage(),
					this.examinableAssessments.get(ComponentType.Coursework).getRawMark(),
					this.examinableAssessments.get(ComponentType.Coursework).getWeightage(),
					this.examinableAssessments.get(ComponentType.Coursework).getMark());

		} else {
			System.out.printf("| Coursework (%2d%%)               |          |                    |                |\n",
					this.examinableAssessments.get(ComponentType.Coursework).getWeightage());
			int i = 0;
			int len = ((MainComponent) this.examinableAssessments.get(ComponentType.Coursework)).getSubcomponents()
					.size();
			for (Subcomponent s : ((MainComponent) this.examinableAssessments.get(ComponentType.Coursework))
					.getSubcomponents()) {
				if (i != len - 1) {
					System.out.printf(
							"|     ├──  %-15s (%2d%%) |     %4.0f |               %3.0f%% |           %4.1f |\n",
							s.getName(), s.getWeightage(), s.getRawMark(),
							(double) s.getWeightage() / 100
									* this.examinableAssessments.get(ComponentType.Coursework).getWeightage(),
							s.getRawMark() * s.getWeightage() / 100
									* this.examinableAssessments.get(ComponentType.Coursework).getWeightage() / 100);
				} else {
					System.out.printf(
							"|     └──  %-15s (%2d%%) |     %4.0f |               %3.0f%% |           %4.1f |\n",
							s.getName(), s.getWeightage(), s.getRawMark(),
							(double) s.getWeightage() / 100
									* this.examinableAssessments.get(ComponentType.Coursework).getWeightage(),
							s.getRawMark() * s.getWeightage() / 100
									* this.examinableAssessments.get(ComponentType.Coursework).getWeightage() / 100);
				}
				i++;
			}
		}
		System.out.printf("+--------------------------------+----------+--------------------+----------------+\n");
		System.out.printf("|                                                                   Total:  %-5.1f |\n",
				this.getTotalMark());
		System.out.printf("+---------------------------------------------------------------------------------+\n");
	}
}