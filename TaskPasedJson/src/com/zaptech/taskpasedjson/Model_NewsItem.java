package com.zaptech.taskpasedjson;

import java.util.List;

public class Model_NewsItem {
	String videos, sortType, sharePointURL, displayAsGantt, tabPosition,
			tabText, tabIcon, dateChanged, isDirty, tempUniqueUID, type,
			useTabIcon, sortPosition, archived, listIcon;
	int id;
	
	List<Model_NewsItem_Items> items;
	
	
	

	public List<Model_NewsItem_Items> getItems() {
		return items;
	}

	public void setItems(List<Model_NewsItem_Items> items) {
		this.items = items;
	}

	public String getVideos() {
		return videos;
	}

	public void setVideos(String videos) {
		this.videos = videos;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public String getSharePointURL() {
		return sharePointURL;
	}

	public void setSharePointURL(String sharePointURL) {
		this.sharePointURL = sharePointURL;
	}

	public String getDisplayAsGantt() {
		return displayAsGantt;
	}

	public void setDisplayAsGantt(String displayAsGantt) {
		this.displayAsGantt = displayAsGantt;
	}

	public String getTabPosition() {
		return tabPosition;
	}

	public void setTabPosition(String tabPosition) {
		this.tabPosition = tabPosition;
	}

	public String getTabText() {
		return tabText;
	}

	public void setTabText(String tabText) {
		this.tabText = tabText;
	}

	public String getTabIcon() {
		return tabIcon;
	}

	public void setTabIcon(String tabIcon) {
		this.tabIcon = tabIcon;
	}

	public String getDateChanged() {
		return dateChanged;
	}

	public void setDateChanged(String dateChanged) {
		this.dateChanged = dateChanged;
	}

	public String getIsDirty() {
		return isDirty;
	}

	public void setIsDirty(String isDirty) {
		this.isDirty = isDirty;
	}

	public String getTempUniqueUID() {
		return tempUniqueUID;
	}

	public void setTempUniqueUID(String tempUniqueUID) {
		this.tempUniqueUID = tempUniqueUID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUseTabIcon() {
		return useTabIcon;
	}

	public void setUseTabIcon(String useTabIcon) {
		this.useTabIcon = useTabIcon;
	}

	public String getSortPosition() {
		return sortPosition;
	}

	public void setSortPosition(String sortPosition) {
		this.sortPosition = sortPosition;
	}

	public String getArchived() {
		return archived;
	}

	public void setArchived(String archived) {
		this.archived = archived;
	}

	public String getListIcon() {
		return listIcon;
	}

	public void setListIcon(String listIcon) {
		this.listIcon = listIcon;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
