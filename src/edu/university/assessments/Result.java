package edu.university.assessments;

import java.io.Serializable;
import java.util.*;

public class Result implements Serializable{

	private static final long serialVersionUID = -8325924104954052339L;
	private HashMap<String, Examinable> examinableAssessments  = new HashMap<>();
	
	public Result() {}
	
	public double getTotalMark() {
		double totalMark = 0;
		for (Examinable e: this.examinableAssessments.values()) {
			totalMark += e.getMark();
		}
		return totalMark;
	}
	
	public double getExaminationMark() {
		return this.examinableAssessments.get("Examination").getMark();
	}
	
	public double getCourseworkMark() {
		return this.examinableAssessments.get("Coursework").getMark();
	}
	
	public void addComponent(String componentName, Examinable e) {
		this.examinableAssessments.put(componentName, e);
	}
	
	public void print(String courseName) {
		
		// TODO: Check whether the result has all components recorded
		
		/*
		+--------------------------------+----------+--------------------+----------------+
		| CZ2002                         | Raw Mark | Overall Percentage | Component Mark |
		+--------------------------------+----------+--------------------+----------------+
		| Examination (50%)              |      100 |                50% |             50 |
		| Coursework (50%)               |          |                    |                |
		|     ├── Assignment (70%)       |       90 |                35% |           31.5 |
		|     └── Presentation (30%)     |      100 |                15% |             15 |
		+--------------------------------+----------+--------------------+----------------+
		|                                                                     Total: 96.5 |
		+---------------------------------------------------------------------------------+ 
		*/
		System.out.printf("+--------------------------------+----------+--------------------+----------------+\n");
		System.out.printf("| %-10s                     | Raw Mark | Overall Percentage | Component Mark |\n", courseName);
		System.out.printf("+--------------------------------+----------+--------------------+----------------+\n");
		System.out.printf("| Examination (%2d%%)              |     %4.0f |                %2d%% |           %4.1f |\n", this.examinableAssessments.get("Examination").getWeightage(), this.examinableAssessments.get("Examination").getRawMark(), this.examinableAssessments.get("Examination").getWeightage(), this.examinableAssessments.get("Examination").getMark());
		if (!((MainComponent)this.examinableAssessments.get("Coursework")).hasSubcomponents()) {
			System.out.printf("| Coursework (%2d%%)               |     %4.0f |                %2d%% |           %4.1f |\n", this.examinableAssessments.get("Coursework").getWeightage(), this.examinableAssessments.get("Coursework").getRawMark(), this.examinableAssessments.get("Coursework").getWeightage(), this.examinableAssessments.get("Coursework").getMark());
			
		} else {
			System.out.printf("| Coursework (%2d%%)               |          |                    |                |\n", this.examinableAssessments.get("Coursework").getWeightage());
			int i = 0;
			int len = ((MainComponent)this.examinableAssessments.get("Coursework")).getSubcomponents().size();
			for (Subcomponent s: ((MainComponent)this.examinableAssessments.get("Coursework")).getSubcomponents()) {
				if (i != len - 1) {
					System.out.printf("|     ├──  %-15s (%2d%%) |     %4.0f |               %3.0f%% |           %4.1f |\n", s.getName(), s.getWeightage(), s.getRawMark(), (double) s.getWeightage() / 100 * this.examinableAssessments.get("Coursework").getWeightage(), s.getRawMark() * (double) s.getWeightage() / 100 * this.examinableAssessments.get("Coursework").getWeightage() / 100);
				} else {
					System.out.printf("|     └──  %-15s (%2d%%) |     %4.0f |               %3.0f%% |           %4.1f |\n", s.getName(), s.getWeightage(), s.getRawMark(), (double) s.getWeightage() / 100 * this.examinableAssessments.get("Coursework").getWeightage(), s.getRawMark() * (double) s.getWeightage() / 100 * this.examinableAssessments.get("Coursework").getWeightage() / 100);
				}
				i++;
			}
		}
		System.out.printf("+--------------------------------+----------+--------------------+----------------+\n");
		System.out.printf("|                                                                   Total:  %-5.1f |\n", this.getTotalMark());
		System.out.printf("+---------------------------------------------------------------------------------+\n");
	}
}