package edu.university.assessments;

import java.util.*;

public class MainComponent implements Examinable{

	private String componentName;
	private int rawMark;
	private int weightage;
	private ArrayList<Subcomponents> subcomponents = new ArrayList<>();
	
	@Override
	public int getWeightage() {
		return this.weightage;
	}

	@Override
	public double getRawMark() {
		return this.hasSubcomponents() ? this.getMark() : this.rawMark;
	}

	@Override
	public double getMark() {
		if (!this.hasSubcomponents()) {
			return (double)this.rawMark * (double)this.weightage / 100;
		} else {
			double mark = 0;
			for (Subcomponents s : this.subcomponents) {
				mark += s.getMark();
			}
			return mark;
		}
	}

	@Override
	public String getName() {
		return this.componentName; 
	}

	public boolean hasSubcomponents(){
		return !this.subcomponents.isEmpty();
	}
	
	public void addSubcomponent(Subcomponents e) {
		this.subcomponents.add(e);
	}
}
