//package edu.university.results;
//
//import java.util.*;
//
//public class Result extends AssessmentComponent{
//	private double componentScore;
//	
//	
//	
//	public double getResult(){
//		if (!this.hasSubComponents()) {
//			return this.componentWeightage * this.componentScore;
//		} else {
//			int result = 0;
//			for (Result c: this.subComponents) {
//				result += c.getResult();
//			}
//			return result;
//		}
//	}
//	
//	public boolean hasSubComponents() {
//		return !this.subComponents.isEmpty();
//	}
//	
//}
