package com.example.gpsnearbyplaces;

import com.google.gson.annotations.SerializedName;


public class ResMain {

	@SerializedName("response")
	ModelResponse response;
	
	public ModelResponse getResponse() {
		return response;
	}
	
	public void setResponse(ModelResponse response) {
		this.response = response;
	}
	
}
