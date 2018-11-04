package edu.university.assessments;

import java.io.Serializable;

public class Subcomponent implements Examinable, Serializable {

	private static final long serialVersionUID = 3958562954203065378L;
	private String subcomponentName;
	private int subcomponentWeightage;
	private double subcomponentRawMark;

	public Subcomponent(String subcomponentName, int subcomponentWeightage, double subcomponentRawMark) {
		this.subcomponentName = subcomponentName;
		this.subcomponentWeightage = subcomponentWeightage;
		this.subcomponentRawMark = subcomponentRawMark;
	}

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
		return this.subcomponentRawMark * this.subcomponentWeightage / 100;
	}

	@Override
	public String getName() {
		return this.subcomponentName;
	}

}
