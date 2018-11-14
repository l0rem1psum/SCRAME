package edu.university.assessments;
/**
 * Interface for examinable components since they all will have similar functions. This is for loose coupling. 
 */

public interface Examinable {
	public int getWeightage();
	public double getRawMark();
	public double getMark();
	public String getName();
}
