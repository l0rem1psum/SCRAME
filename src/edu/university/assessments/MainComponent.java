package edu.university.assessments;

import java.io.Serializable;
import java.util.*;

public class MainComponent implements Examinable, Serializable{

	private String componentName;
	private int rawMark;
	private int weightage;
	private ArrayList<Subcomponent> subcomponents = new ArrayList<>();
	
	public MainComponent(String componentName, int weightage) {
		this.componentName = componentName;
		this.weightage = weightage;
	}
	
	public MainComponent(String componentName, int weightage, int rawMark) {
		this.componentName = componentName;
		this.weightage = weightage;
		this.rawMark = rawMark;
	}
	
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
			for (Subcomponent s : this.subcomponents) {
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
	
	public void addSubcomponent(Subcomponent e) {
		this.subcomponents.add(e);
	}
	
	public ArrayList<Subcomponent> getSubcomponents(){
		return this.subcomponents;
	}
}
