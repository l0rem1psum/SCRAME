package edu.university.assessments;
//package edu.university.results;
//
//import java.util.ArrayList;
//
//public class AssessmentComponent {
//	private String componentName;
//	private double componentWeightage;
//	private ArrayList<AssessmentComponent> subComponents = new ArrayList<>();
//	
//	public AssessmentComponent(String componentName, double componentWeightage){
//		this.componentName = componentName;
//		this.componentWeightage = componentWeightage;
//	}
//	
//	public boolean isValid() {
//		if (this.subComponents.isEmpty()) {
//			return Math.abs(this.componentWeightage - 100) < 1e-6;
//		} else {
//			
//		}
//	}
//	
//	public double getComponentWeightage() {
//		return this.componentWeightage;
//	}
//	
//	public double getOverallPercentage() {
//		if (this.subComponents.isEmpty()) {
//			return this.getComponentWeightage();
//		} else {
//			double overallPercentage = 0;
//			for (AssessmentComponent c: this.subComponents) {
//				overallPercentage += c.getComponentWeightage() * c.getOverallPercentage();
//			}
//			return overallPercentage;
//		}
//	}
//	
//	public String getComponentName(){
//		return this.componentName;
//	}
//	
//	public void addSubComponent(AssessmentComponent c) {
//		this.subComponents.add(c);
//	}
//
//}
