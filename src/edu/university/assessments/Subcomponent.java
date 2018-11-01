package edu.university.assessments;



public class Subcomponent implements Examinable{

	private String subcomponentName;
	private int subcomponentWeightage;
	private double subcomponentRawMark;
	
	@Override
	public int getWeightage() {
		return this.subcomponentWeightage;
	}

	@Override
	public double getRawMark() {
		return this.subcomponentRawMark;
	}

	@Override
	public double getMark() {
		return (double)this.subcomponentRawMark * (double)this.subcomponentWeightage / 100;
	}

	@Override
	public String getName() {
		return this.subcomponentName;
	}

}
