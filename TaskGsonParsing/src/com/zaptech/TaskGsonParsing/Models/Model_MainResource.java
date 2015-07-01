package com.zaptech.TaskGsonParsing.Models;

import java.util.List;

public class Model_MainResource {
	String success;
	List<Model_countryList> countryList;

	public List<Model_countryList> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<Model_countryList> countryList) {
		this.countryList = countryList;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

}
