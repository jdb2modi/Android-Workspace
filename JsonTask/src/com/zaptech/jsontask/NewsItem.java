package com.zaptech.jsontask;

public class NewsItem {

	public int NewsItemId,ItemId;

	public String URL, datePublished, dateChanged, isDirty, eventFlag,
			eventDate, publishToFacebook, tempUniqueUID, eventDateFinish,
			sortPosition, archived, listIcon;

	public int getNewsItemId() {
		return NewsItemId;
	}

	public void setNewsItemId(int newsItemId) {
		NewsItemId = newsItemId;
	}

	public int getItemId() {
		return ItemId;
	}

	public void setItemId(int itemId) {
		ItemId = itemId;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getDatePublished() {
		return datePublished;
	}

	public void setDatePublished(String datePublished) {
		this.datePublished = datePublished;
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

	public String getEventFlag() {
		return eventFlag;
	}

	public void setEventFlag(String eventFlag) {
		this.eventFlag = eventFlag;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getPublishToFacebook() {
		return publishToFacebook;
	}

	public void setPublishToFacebook(String publishToFacebook) {
		this.publishToFacebook = publishToFacebook;
	}

	public String getTempUniqueUID() {
		return tempUniqueUID;
	}

	public void setTempUniqueUID(String tempUniqueUID) {
		this.tempUniqueUID = tempUniqueUID;
	}

	public String getEventDateFinish() {
		return eventDateFinish;
	}

	public void setEventDateFinish(String eventDateFinish) {
		this.eventDateFinish = eventDateFinish;
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

}
