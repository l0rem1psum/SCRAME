package edu.university.assessments;

import java.io.Serializable;

public class Subcomponent implements Examinable, Serializable {

	private static final long serialVersionUID = 3958562954203065378L;
	private String subcomponentName;
	private int subcomponentWeightage;
	private double subcomponentRawMark;

	/**
	 * The constructor of a subcomponent object.
	 * 
	 * @param subcomponentName      The name of the subcomponent.
	 * @param subcomponentWeightage The weight of a subcomponent.
	 * @param subcomponentRawMark   The raw mark of a subcomponent.
	 */
	public Subcomponent(String subcomponentName, int subcomponentWeightage, double subcomponentRawMark) {
		this.subcomponentName = subcomponentName;
		this.subcomponentWeightage = subcomponentWeightage;
		this.subcomponentRawMark = subcomponentRawMark;
	}

	/**
	 * This method is used to get the weight of a subcomponent, as a percentage of
	 * the main component.
	 * 
	 * @return the weight of a subcomponent, as a percentage of the main component.
	 */
	@Override
	public int getWeightage() {
		return this.subcomponentWeightage;
	}

	/**
	 * This method is used to get the raw mark of the subcomponent.
	 * 
	 * @return the raw mark of the subcomponent.
	 */
	@Override
	public double getRawMark() {
		return this.subcomponentRawMark;
	}

	/**
	 * This method is used to get the mark of the subcomponent.
	 * 
	 * @return the mark (product of weight and raw mark) of the subcomponent.
	 */
	@Override
	public double getMark() {
		return this.subcomponentRawMark * this.subcomponentWeightage / 100;
	}

	/**
	 * This method is used to get the name of subcomponent.
	 * 
	 * @return the name of the subcomponent.
	 */
	@Override
	public String getName() {
		return this.subcomponentName;
	}

}
