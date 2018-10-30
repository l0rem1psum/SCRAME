package edu.university.assessments;

public class Subcomponent implements Examinable{
	private String subcomponentName;
	private double subcomponentWeightage;
	private int subcomponentRawScore;
	
	@Override
	public double getWeightage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRawScore() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getScore() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		return this.subcomponentName;
	}

}
