package com.example.gpsnearbyplaces;

public class ModelVenue {

	public String id;
	public String name;
	public ModelLocation location;

	public ModelLocation getLocation() {
		return location;
	}

	public void setLocation(ModelLocation location) {
		this.location = location;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
