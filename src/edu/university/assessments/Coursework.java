package edu.university.assessments;

import java.util.*;

public class Coursework implements Examinable{
	ArrayList<Examinable> subcomponents = new ArrayList<>();
	
	
	public void addSubcomponents(Subcomponent subcomponent) {
		this.subcomponents.add(subcomponent);
	}
	
	@Override
	public double getWeightage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getScore() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRawScore() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		return "Coursework";
	}

}
