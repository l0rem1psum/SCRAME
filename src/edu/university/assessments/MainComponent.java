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
	 * The constructor for an assessment component that has subcomponents.
	 * 
	 * This constructor is used when a main assessment component (e.g. coursework)
	 * that has subcomponents. In such case, the component will not have a raw mark
	 * of iteself
	 * 
	 * @param componentType Name of the main component. Expect an enum type of
	 *                      ComponentType.
	 * @param weightage     Weight of the component as of its parent component.
	 */
	public MainComponent(ComponentType componentType, int weightage) {
		this.componentName = componentType.name();
		this.weightage = weightage;
	}

	/**
	 * The constructor for an assessment component that does not have subcomponents.
	 * 
	 * This constructor is used when a main assessment component (e.g. examination)
	 * that does not have subcomponents. In such case, the component will have a raw
	 * mark of itself.
	 * 
	 * @param componentType Name of the main component. Expect an enum type of
	 *                      ComponentType.
	 * @param weightage     Weight of the component as of its partent component.
	 * @param rawMark       The raw mark of the assessment component.
	 */
	public MainComponent(ComponentType componentType, int weightage, int rawMark) {
		this.componentName = componentType.name();
		this.weightage = weightage;
		this.rawMark = rawMark;
	}

	/**
	 * Gets weightage of the component.
	 * 
	 * @return weightage of component.
	 */
	@Override
	public int getWeightage() {
		return this.weightage;
	}

	/**
	 * Ternary operation in this method to get the component's raw marks.
	 * 
	 * If the main component has subcomponents, calls getMark() and get the sum of
	 * all subcomponents marks instead.
	 * 
	 * @return raw mark if it dosen't have subcomponents and sum of subcomponent
	 *         marks if it has subcomponents.
	 */
	@Override
	public double getRawMark() {
		return this.hasSubcomponents() ? this.getMark() : this.rawMark;
	}

	/**
	 * Method to return marks depending on whether main component has subcomponent
	 * or not.
	 * 
	 * @return marks if the main componnet doesn't have subcomponents, otherwise
	 *         loops through each subcomponent objects using an enhanced for loop
	 *         and add the marks accordingly to get the overall marks.
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
	 * This method is used to get the name of the main component.
	 * 
	 * @return component This is the name of the main component.
	 */
	@Override
	public String getName() {
		return this.componentName;
	}

	/**
	 * Method to check whether there are subcomponents or not.
	 * 
	 * @return false if no subcomponents, otherwise if there are subcomponents.
	 */
	public boolean hasSubcomponents() {
		return !this.subcomponents.isEmpty();
	}

	/**
	 * The method is used to add subcomponents.
	 * 
	 * @param e This is the Subcomponent to be added.
	 */
	public void addSubcomponent(Subcomponent e) {
		this.subcomponents.add(e);
	}

	/**
	 * Gets all subcomponents that exists.
	 * 
	 * @return list of subcomponents.
	 */
	public ArrayList<Subcomponent> getSubcomponents() {
		return this.subcomponents;
	}
}
