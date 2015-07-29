package com.example.quizapplication.common;

import java.util.ArrayList;

import com.example.quizapplication.model.Question_Details;


import android.app.Application;

public class Global extends Application{
	
	public ArrayList<Question_Details> setQuestion= new ArrayList<Question_Details>();

	public ArrayList<Question_Details> getSetQuestion() {
		return setQuestion;
	}

	public void setSetQuestion(ArrayList<Question_Details> setQuestion) {
		this.setQuestion = setQuestion;
	}

}
