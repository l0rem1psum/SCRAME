package edu.university.assessments;

import java.io.Serializable;
import java.util.*;

public class Result implements Serializable{
	private HashMap<String, Examinable> examinableAssessments  = new HashMap<>();
	
	public Result() {}
	
	public double getTotal() {
		double totalMark = 0;
		for (Examinable e: this.examinableAssessments.values()) {
			totalMark += e.getMark();
		}
		return totalMark;
	}
	
	public void addComponent(String componentName, Examinable e) {
		this.examinableAssessments.put(componentName, e);
	}
}