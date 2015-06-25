package com.zaptech.taskpasedjson;

public class Model_NewsItem_Items {
	int newsItems_ItemId, newsItemId;

	String url, datePublished, dateChanged, isDirty, eventFlag, eventDate,
			publishToFacebook, tempUniqueUID, eventDateFinish, sortPosition,
			archived, listIcon;

	Model_NewsImage model_newsImgge;
	Model_Headline model_headline;
	Model_Description model_description;
	Model_DescriptionHMTL model_descriptionHTML;

	public int getNewsItemId() {
		return newsItemId;
	}

	public void setNewsItemId(int newsItemId) {
		this.newsItemId = newsItemId;
	}

	public int getNewsItems_ItemId() {
		return newsItems_ItemId;
	}

	public void setNewsItems_ItemId(int newsItems_ItemId) {
		this.newsItems_ItemId = newsItems_ItemId;
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

	public Model_NewsImage getModel_newsImgge() {
		return model_newsImgge;
	}

	public void setModel_newsImgge(Model_NewsImage model_newsImgge) {
		this.model_newsImgge = model_newsImgge;
	}

	public Model_Headline getModel_headline() {
		return model_headline;
	}

	public void setModel_headline(Model_Headline model_headline) {
		this.model_headline = model_headline;
	}

	public Model_Description getModel_description() {
		return model_description;
	}

	public void setModel_description(Model_Description model_description) {
		this.model_description = model_description;
	}

	public Model_DescriptionHMTL getModel_descriptionHTML() {
		return model_descriptionHTML;
	}

	public void setModel_descriptionHTML(
			Model_DescriptionHMTL model_descriptionHTML) {
		this.model_descriptionHTML = model_descriptionHTML;
	}

}
