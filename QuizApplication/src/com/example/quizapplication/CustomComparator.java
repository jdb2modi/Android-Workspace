package com.example.quizapplication;

import java.text.Collator;
import java.util.Comparator;

import com.example.quizapplication.model.Model;

public class CustomComparator implements Comparator<Model> {

	
	
	 Collator myCollator = Collator.getInstance();
	public int compare(Model o1, Model o2) {
		return myCollator.compare(o1.getFrndName().toLowerCase(), o2.getFrndName()
				.toLowerCase());
	}


	
}
