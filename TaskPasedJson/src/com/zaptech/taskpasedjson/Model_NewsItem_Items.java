package com.zaptech.taskpasedjson;

public class Model_NewsItem_Items {
	int id;
	String url, datePublished, dateChanged, isDirty, eventFlag, eventDate,
			publishToFacebook, tempUniqueUID, eventDateFinish, sortPosition,
			archived, listIcon;

	Model_NewsImage newsImgge;
	Model_Headline headline;
	Model_Description description;
	Model_DescriptionHMTL descriptionHTML;
	
	
	
	public Model_NewsImage getNewsImgge() {
		return newsImgge;
	}

	public void setNewsImgge(Model_NewsImage newsImgge) {
		this.newsImgge = newsImgge;
	}

	public Model_Headline getHeadline() {
		return headline;
	}

	public void setHeadline(Model_Headline headline) {
		this.headline = headline;
	}

	public Model_Description getDescription() {
		return description;
	}

	public void setDescription(Model_Description description) {
		this.description = description;
	}

	public Model_DescriptionHMTL getDescriptionHTML() {
		return descriptionHTML;
	}

	public void setDescriptionHTML(Model_DescriptionHMTL descriptionHTML) {
		this.descriptionHTML = descriptionHTML;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
