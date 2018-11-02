package edu.university.assessments;

import java.io.Serializable;
import java.util.*;

public class Result implements Serializable{
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
}