package edu.university.assessments;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Main component class to handle marks, weightage and adding subcomponents such
 * as labs, finals.
 */

public class MainComponent implements Examinable, Serializable {

	private static final long serialVersionUID = -527313197269275481L;
	private String componentName;
	private int rawMark;
	private int weightage;
	private ArrayList<Subcomponent> subcomponents = new ArrayList<>();

	/**
	 * Constructor to create MainComponent object based on parameters given. Creates
	 * tutorials for the course if they exist.
	 * 
	 * @param componentType
	 * @param weightage
	 */
	public MainComponent(ComponentType componentType, int weightage) {
		this.componentName = componentType.name();
		this.weightage = weightage;
	}

	/**
	 * Constructor to create MainComponent object based on parameters given. Creates
	 * tutorials for the course if they exist.
	 * 
	 * @param componentType
	 * @param weightage
	 * @param rawMark
	 */
	public MainComponent(ComponentType componentType, int weightage, int rawMark) {
		this.componentName = componentType.name();
		this.weightage = weightage;
		this.rawMark = rawMark;
	}

	/**
	 * Gets weightage of the component
	 * 
	 * @return weightage of component
	 */
	@Override
	public int getWeightage() {
		return this.weightage;
	}

	/**
	 * Ternary operation in this method to get the component's raw marks. If has
	 * subcomponents, calls getMark() and get the subcomponents marks instead.
	 * 
	 * @return subcomponent marks OR the component's raw marks.
	 */
	@Override
	public double getRawMark() {
		return this.hasSubcomponents() ? this.getMark() : this.rawMark;
	}

	/**
	 * Method to return marks depending on whether main component has subcomponent
	 * or not.
	 * 
	 * @return marks if doesnt have subcomponent, otherwise loops through each
	 *         subcomponent objects using an enhanced for loop and add the marks
	 *         accordingly to get the overall marks.
	 */
	@Override
	public double getMark() {
		if (!this.hasSubcomponents()) {
			return (double) this.rawMark * (double) this.weightage / 100;
		} else {
			double mark = 0;
			for (Subcomponent s : this.subcomponents) {
				mark += s.getMark();
			}
			return mark * this.weightage / 100;
		}
	}

	/**
	 * Gets the name of the component
	 * 
	 * @return component name
	 */
	@Override
	public String getName() {
		return this.componentName;
	}

	/**
	 * Method to check whether there are subcomponents or not.
	 * 
	 * @return false if no subcomponents, otherwise true if there are subcomponents.
	 */
	public boolean hasSubcomponents() {
		return !this.subcomponents.isEmpty();
	}

	/**
	 * Method to add subcomponents.
	 * 
	 * @param e
	 */
	public void addSubcomponent(Subcomponent e) {
		this.subcomponents.add(e);
	}

	/**
	 * Gets all subcomponents that exists
	 * 
	 * @return list of subcomponents
	 */
	public ArrayList<Subcomponent> getSubcomponents() {
		return this.subcomponents;
	}
}
