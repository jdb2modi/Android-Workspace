package com.example.gpsnearbyplaces;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Application;

public class NearLocation extends Application{

	public static ArrayList<HashMap<String, ModelVenue>> listHashMap = new ArrayList<HashMap<String, ModelVenue>>();
	public static ArrayList<HashMap<String, ModelVenue>> searched_listHashMap = new ArrayList<HashMap<String, ModelVenue>>();
	public static boolean flagSearched=false;

	public static boolean isFlagSearched() {
		return flagSearched;
	}

	public static void setFlagSearched(boolean flagSearched) {
		NearLocation.flagSearched = flagSearched;
	}

	public static ArrayList<HashMap<String, ModelVenue>> getSearched_listHashMap() {
		return searched_listHashMap;
	}

	public static void setSearched_listHashMap(
			ArrayList<HashMap<String, ModelVenue>> searched_listHashMap) {
		NearLocation.searched_listHashMap = searched_listHashMap;
	}

	public static ArrayList<HashMap<String, ModelVenue>> getListHashMap() {
		return listHashMap;
	}

	public static void setListHashMap(
			ArrayList<HashMap<String, ModelVenue>> listHashMap) {
		NearLocation.listHashMap = listHashMap;
	}

}
