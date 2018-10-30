package edu.university.assessments;

public class Examination implements Examinable{
	private double examWeightage;
	private int rawExamScore;
	
	@Override
	public double getWeightage() {
		return this.examWeightage;
	}

	@Override
	public int getRawScore() {
		return this.rawExamScore;
	}

	@Override
	public double getScore() {
		return this.examWeightage * this.rawExamScore;
	}

	@Override
	public String getName() {
		return "Examination";
	}

}
