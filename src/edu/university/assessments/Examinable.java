package edu.university.assessments;

/**
 * Interface for examinable components.
 * 
 * The reason behind such interface is, every examinable component, be it main
 * component or subcomponent of a course must have the following:
 * <ul>
 * <li>The name of the component</li>
 * <li>A weight as of its parent component</li>
 * <li>A raw mark given by the professor</li>
 * <li>An overall mark (out of the maximum percentage of the component)</li>
 * </ul>
 * This is for loose coupling.
 */

public interface Examinable {
	/**
	 * This method is used to get the weight of an examinable component.
	 * 
	 * @return an integer representing the weight of an examinable component (out of
	 *         100).
	 */
	public int getWeightage();

	/**
	 * This method is used to get the raw mark of an exminable component.
	 * 
	 * When implementing this method, note that not every examinable component has a
	 * raw mark. For example, coursework that has subcomponents do not have a
	 * rawmark by itself.
	 * 
	 * @return a double type that is the raw mark (out of 100), as entered by the
	 *         professor for a particular component in the case of a component that
	 *         does not have subcomponents. And in the case of component having
	 *         subcomponents, this will return the sum of marks of all
	 *         subcomponents.
	 */
	public double getRawMark();

	/**
	 * This method is used to get the mark of an examinable components.
	 * 
	 * The implementation of this method needs to calculate the mark of the
	 * component as of the entire course.
	 * 
	 * @return a double type that is the mark of the component, as of the entire
	 *         course.
	 */
	public double getMark();

	/**
	 * This method is used to get the name of an examinable component.
	 * 
	 * @return the name of the examinable component.
	 */
	public String getName();
}
