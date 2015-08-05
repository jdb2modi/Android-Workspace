package com.zaptech.jsontask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDatabaseHelper extends SQLiteOpenHelper {

	ArrayList<HomeItems> listHomeItems = new ArrayList<HomeItems>();

	ArrayList<ModelMenuItem> listMenuItems = new ArrayList<ModelMenuItem>();

	ArrayList<ModelNewsItem> listNewsItems = new ArrayList<ModelNewsItem>();

	ArrayList<NewsHeadLine> listNewsHeadLine = new ArrayList<NewsHeadLine>();

	ArrayList<NewsDescriptionHTML> listNewsDescriptionHTML = new ArrayList<NewsDescriptionHTML>();

	public static final int DATABASE_VERSION = 1;

	public static final String DATABASE_NAME = "HomeItems";

	public static final String TABLE_HOME = "HomeData";

	public static final String TABLE_IMAGE = "HomeImageData";

	public static final String TABLE_MENU = "MenuItem";

	public static final String TABLE_MAINNEWSITEM = "MainNewsItem";

	public static final String TABLE_NEWSITEM = "NewsItem";

	public static final String TABLE_NEWSIMAGE = "NewsImage";

	public static final String TABLE_NEWSHEADLINE = "NewsHeadline";

	public static final String TABLE_NEWSDESCRIPTION = "NewsDescription";

	public static final String TABLE_NEWSDESCRIPTIONHTML = "NewsDescriptionHTML";

	public static final String COL_HOMEITEM_INCLUDE_IMAGE_IN_LAYOUT = "IncludeImageInLayout";
	public static final String COL_HOMEITEM_INCLUDE_TITLE_IN_LAYOUT = "IncludetitleInLayout";
	public static final String COL_HOMEITEM_INCLUDE_TEXT_IN_LAYOUT = "IncludeTextInLayout";
	public static final String COL_HOMEITEM_IMAGE_POSITION = "ImagePosition";
	public static final String COL_HOMEITEM_TITLE_POSITION = "TitlePosition";
	public static final String COL_HOMEITEM_TEXT_POSITION = "TextPosition";
	public static final String COL_HOMEITEM_TITLE = "Title";
	public static final String COL_HOMEITEM_TEXT = "Text";
	public static final String COL_HOMEITEM_TEXT_HTML = "TextHtml";
	public static final String COL_HOMEITEM_ID = "HomeItemId";

	public static final String COL_HOMEITEM_TAB_POSITION = "TabPosition";
	public static final String COL_HOMEITEM_TAB_TEXT = "TabText";
	public static final String COL_HOMEITEM_TAB_ICON = "TabIcon";
	public static final String COL_HOMEITEM_DATE_CHANGED = "DateChanged";
	public static final String COL_HOMEITEM_IS_DIRTY = "IsDirty";
	public static final String COL_HOMEITEM_TEMP_UNIQUE_UID = "TempUniqueId";
	public static final String COL_HOMEITEM_TYPE = "Type";
	public static final String COL_HOMEITEM_USE_TAB_ICON = "UseTabIcon";
	public static final String COL_HOMEITEM_SORT_POSITION = "SortPosition";
	public static final String COL_HOMEITEM_ARCHIVED = "Archived";
	public static final String COL_HOMEITEM_LIST_ICON = "ListIcon";

	public static final String COL_IMAGE_WIDTH = "Width";
	public static final String COL_IMAGE_HEIGHT = "Height";
	public static final String COL_IMAGE_ORIGINAL_NAME = "OriginalName";
	public static final String COL_IMAGE_LOCATION_LOCAL = "LocationLocal";
	public static final String COL_IMAGE_TYPE = "ImageType";
	public static final String COL_IMAGE_BASE_URL = "BaseUrl";
	public static final String COL_IMAGE_MIME_TYPE = "MimeType";
	public static final String COL_IMAGE_BASE64_VERSION = "Base64Version";
	public static final String COL_IMAGE_IS_DIRTY = "IsDirty";
	public static final String COL_IMAGE_ARCHIVED = "Archived";
	public static final String COL_IMAGE_ID = "ImageId";
	public static final String COL_IMAGE_NAME = "Name";

	public static final String COL_MENUITEM_ITEM_NAME = "Item_Name";

	public static final String COL_MAINNEWSITEM_VIDEOS = "Videos";
	public static final String COL_MAINNEWSITEM_SORTTYPE = "SortType";
	public static final String COL_MAINNEWSITEM_SHAREPOINTURL = "SharePointURL";
	public static final String COL_MAINNEWSITEM_DISPLAYASGANTT = "DisplayAsGantt";
	public static final String COL_MAINNEWSITEM_NEWSITEMID = "NewsItemId";
	public static final String COL_MAINNEWSITEM_TABPOSITION = "TabPosition";
	public static final String COL_MAINNEWSITEM_TABTEXT = "TabText";
	public static final String COL_MAINNEWSITEM_TABICON = "TabIcon";
	public static final String COL_MAINNEWSITEM_DATECHANGED = "DateChanged";
	public static final String COL_MAINNEWSITEM_ISDIRTY = "IsDirty";
	public static final String COL_MAINNEWSITEM_TEMPUNIQUEUID = "TempUniqueUID";
	public static final String COL_MAINNEWSITEM_TYPE = "Type";
	public static final String COL_MAINNEWSITEM_USETABICON = "UseTabIcon";
	public static final String COL_MAINNEWSITEM_SORTPOSITION = "SortPosition";
	public static final String COL_MAINNEWSITEM_ARCHIVED = "Archived";
	public static final String COL_MAINNEWSITEM_LISTICON = "ListIcon";

	public static final String COL_NEWSITEM_NEWSITEMID = "NewsItemId";
	public static final String COL_NEWSITEM_ITEMID = "ItemId";
	public static final String COL_NEWSITEM_URL = "URL";
	public static final String COL_NEWSITEM_DATEPUBLISHED = "DatePublished";
	public static final String COL_NEWSITEM_DATECHANGED = "DateChanged";
	public static final String COL_NEWSITEM_ISDIRTY = "IsDirty";
	public static final String COL_NEWSITEM_EVENTFLAG = "EventFlag";
	public static final String COL_NEWSITEM_EVENTDATE = "EventDate";
	public static final String COL_NEWSITEM_PUBLISHTOFACEBOOK = "PublishToFacebook";
	public static final String COL_NEWSITEM_TEMPUNIQUEUID = "TempUniqueUID";
	public static final String COL_NEWSITEM_EVENTDATEFINISH = "EventDateFinish";
	public static final String COL_NEWSITEM_SORTPOSITION = "SortPosition";
	public static final String COL_NEWSITEM_ARCHIVED = "Archived";
	public static final String COL_NEWSITEM_LISTICON = "ListIcon";

	public static final String COL_NEWSIMAGE_ITEMID = "ItemId";
	public static final String COL_NEWSIMAGE_WIDTH = "Width";
	public static final String COL_NEWSIMAGE_HEIGHT = "Height";
	public static final String COL_NEWSIMAGE_ORIGINALNAME = "OriginalName";
	public static final String COL_NEWSIMAGE_LOCATIONLOCAL = "LocationLocal";
	public static final String COL_NEWSIMAGE_TYPE = "Type";
	public static final String COL_NEWSIMAGE_BASEURL = "BaseURL";
	public static final String COL_NEWSIMAGE_MIMETYPE = "MimeType";
	public static final String COL_NEWSIMAGE_BASE64VERSION = "Base64Version";
	public static final String COL_NEWSIMAGE_ISDIRTY = "IsDirty";
	public static final String COL_NEWSIMAGE_ARCHIVED = "Archived";
	public static final String COL_NEWSIMAGE_IMAGEID = "ImageId";
	public static final String COL_NEWSIMAGE_NAME = "Name";

	public static final String COL_NEWSHEADLINE_ITEMID = "ItemId";
	public static final String COL_NEWSHEADLINE_THESTRING = "TheString";

	public static final String COL_NEWSDESCRIPTION_ITEMID = "ItemId";
	public static final String COL_NEWSDESCRIPTION_THESTRING = "TheString";

	public static final String COL_NEWSDESCRIPTIONHTML_ITEMID = "ItemId";
	public static final String COL_NEWSDESCRIPTIONHTML_THESTRING = "TheString";

	// /FOR ASSET CONCEPT
	private static SQLiteDatabase myDataBase;
	private static Context myContext;
	public int count = 0;

	public MyDatabaseHelper(Context context) {

		super(context, context.getResources().getString(R.string.DB_NAME),
				null, 1);

		MyDatabaseHelper.myContext = context;
		try {
			createDataBase();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ---Create the database---
	public void createDataBase() throws IOException {

		// ---Check whether database is already created or not---
		boolean dbExist = checkDataBase();

		if (!dbExist) {
			this.getReadableDatabase();
			try {
				// ---If not created then copy the database---
				copyDataBase();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// --- Check whether database already created or not---
	private boolean checkDataBase() {
		try {
			String myPath = myContext.getString(R.string.DB_PATH)
					+ myContext.getString(R.string.DB_NAME);
			File f = new File(myPath);
			if (f.exists())
				return true;
			else
				return false;
		} catch (SQLiteException e) {
			e.printStackTrace();
			return false;
		}
	}

	// --- Copy the database to the output stream---
	private void copyDataBase() throws IOException {

		InputStream myInput = myContext.getAssets().open(
				myContext.getString(R.string.DB_NAME));

		String outFileName = myContext.getString(R.string.DB_PATH)
				+ myContext.getString(R.string.DB_NAME);

		OutputStream myOutput = new FileOutputStream(outFileName);

		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}

		myOutput.flush();
		myOutput.close();
		myInput.close();

	}

	public static void openDataBase() throws SQLException {

		// --- Open the database---
		String myPath = myContext.getString(R.string.DB_PATH)
				+ myContext.getString(R.string.DB_NAME);

		Log.i("hello path print here", "+++++++++++++" + myPath);
		myDataBase = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READWRITE);

	}

	@Override
	public synchronized void close() {

		if (myDataBase != null)
			myDataBase.close();

		super.close();

	}

	// /ASSET COMPLETE

	@Override
	public void onCreate(SQLiteDatabase db) {
	}

	public void insertHomeItems(HomeItems homeItems) {
		openDataBase();
		SQLiteDatabase myDb = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COL_HOMEITEM_INCLUDE_IMAGE_IN_LAYOUT,
				homeItems.getHomeItem_includeImageInLayout());
		values.put(COL_HOMEITEM_INCLUDE_TITLE_IN_LAYOUT,
				homeItems.getHomeItem_includeTitleInLayout());
		values.put(COL_HOMEITEM_INCLUDE_TEXT_IN_LAYOUT,
				homeItems.getHomeItem_includeTextInLayout());
		values.put(COL_HOMEITEM_IMAGE_POSITION,
				homeItems.getHomeItem_imagePosition());
		values.put(COL_HOMEITEM_TITLE_POSITION,
				homeItems.getHomeItem_titlePosition());
		values.put(COL_HOMEITEM_TEXT_POSITION,
				homeItems.getHomeItem_textPosition());
		values.put(COL_HOMEITEM_TITLE, homeItems.getHomeItem_title());
		values.put(COL_HOMEITEM_TEXT, homeItems.getHomeItem_text());
		values.put(COL_HOMEITEM_TEXT_HTML, homeItems.getHomeItem_textHTML());
		values.put(COL_HOMEITEM_ID, homeItems.getHomeItem_id());
		values.put(COL_HOMEITEM_TAB_POSITION,
				homeItems.getHomeItem_tabPosition());
		values.put(COL_HOMEITEM_TAB_TEXT, homeItems.getHomeItem_tabText());
		values.put(COL_HOMEITEM_TAB_ICON, homeItems.getHomeItem_tabIcon());
		values.put(COL_HOMEITEM_DATE_CHANGED,
				homeItems.getHomeItem_dateChanged());
		values.put(COL_HOMEITEM_IS_DIRTY, homeItems.getHomeItem_isDirty());
		values.put(COL_HOMEITEM_TEMP_UNIQUE_UID,
				homeItems.getHomeItem_tempUniqueUID());
		values.put(COL_HOMEITEM_TYPE, homeItems.getHomeItem_Type());
		values.put(COL_HOMEITEM_USE_TAB_ICON,
				homeItems.getHomeItem_useTabIcon());
		values.put(COL_HOMEITEM_SORT_POSITION,
				homeItems.getHomeItem_sortPosition());
		values.put(COL_HOMEITEM_ARCHIVED, homeItems.getHomeItem_archived());
		values.put(COL_HOMEITEM_LIST_ICON, homeItems.getHomeItem_listIcon());
		myDb.insert(TABLE_HOME, null, values);
		myDb.close();
		close();
	}

	public void insertHomeItemImage(HomeItemIMAGE homeItemImage) {
		openDataBase();
		SQLiteDatabase myDb = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COL_IMAGE_ID, homeItemImage.getHomeItemImage_Id());
		values.put(COL_IMAGE_WIDTH, homeItemImage.getHomeItemImage_Width());
		values.put(COL_IMAGE_HEIGHT, homeItemImage.getHomeItemImage_Height());
		values.put(COL_IMAGE_ORIGINAL_NAME,
				homeItemImage.getHomeItemImage_OriginalName());
		values.put(COL_IMAGE_LOCATION_LOCAL,
				homeItemImage.getHomeItemImage_LocationLocal());
		values.put(COL_IMAGE_TYPE, homeItemImage.getHomeItemImage_Type());
		values.put(COL_IMAGE_BASE_URL, homeItemImage.getHomeItemImage_BaseURL());
		values.put(COL_IMAGE_MIME_TYPE,
				homeItemImage.getHomeItemImage_mimeType());
		values.put(COL_IMAGE_BASE64_VERSION,
				homeItemImage.getHomeItemImage__Base64Version());
		values.put(COL_IMAGE_IS_DIRTY, homeItemImage.getHomeItemImage_IsDirty());
		values.put(COL_IMAGE_ARCHIVED,
				homeItemImage.getHomeItemImage_Archived());
		values.put(COL_IMAGE_NAME, homeItemImage.getHomeItemImage_Name());
		values.put(COL_HOMEITEM_ID, homeItemImage.getHomeItemImage_HomeItemId());
		myDb.insert(TABLE_IMAGE, null, values);
		myDb.close();
		close();
	}

	public void insertMenuItem(ModelMenuItem modelMenuItem) {
		openDataBase();
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COL_MENUITEM_ITEM_NAME, modelMenuItem.getItemName());
		db.insert(TABLE_MENU, null, values);
		close();
	}

	public void insertMainNewsItem(ModelNewsItem modelNewsItem) {
		openDataBase();
		SQLiteDatabase myDb = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COL_MAINNEWSITEM_VIDEOS, modelNewsItem.getVideos());
		values.put(COL_MAINNEWSITEM_SORTTYPE, modelNewsItem.getSortType());
		values.put(COL_MAINNEWSITEM_SHAREPOINTURL,
				modelNewsItem.getSharePointURL());
		values.put(COL_MAINNEWSITEM_DISPLAYASGANTT,
				modelNewsItem.getDisplayAsGantt());
		values.put(COL_MAINNEWSITEM_NEWSITEMID, modelNewsItem.getNewsItemId());
		values.put(COL_MAINNEWSITEM_TABPOSITION, modelNewsItem.getTabPosition());
		values.put(COL_MAINNEWSITEM_TABTEXT, modelNewsItem.getTabText());
		values.put(COL_MAINNEWSITEM_TABICON, modelNewsItem.getTabIcon());
		values.put(COL_MAINNEWSITEM_DATECHANGED, modelNewsItem.getDateChanged());
		values.put(COL_MAINNEWSITEM_ISDIRTY, modelNewsItem.isDirty);
		values.put(COL_MAINNEWSITEM_TEMPUNIQUEUID,
				modelNewsItem.getTempUniqueUID());
		values.put(COL_MAINNEWSITEM_TYPE, modelNewsItem.getType());
		values.put(COL_MAINNEWSITEM_USETABICON, modelNewsItem.getUseTabIcon());
		values.put(COL_MAINNEWSITEM_SORTPOSITION,
				modelNewsItem.getSortPosition());
		values.put(COL_MAINNEWSITEM_ARCHIVED, modelNewsItem.getArchived());
		values.put(COL_MAINNEWSITEM_LISTICON, modelNewsItem.getListIcon());
		myDb.insert(TABLE_MAINNEWSITEM, null, values);
		close();
	}

	public void insertNewsItem(NewsItem newsItem) {
		openDataBase();
		SQLiteDatabase myDb = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COL_NEWSITEM_NEWSITEMID, newsItem.getNewsItemId());
		values.put(COL_NEWSITEM_ITEMID, newsItem.getItemId());
		values.put(COL_NEWSITEM_URL, newsItem.getURL());
		values.put(COL_NEWSITEM_DATEPUBLISHED, newsItem.getDatePublished());
		values.put(COL_NEWSITEM_DATECHANGED, newsItem.getDateChanged());
		values.put(COL_NEWSITEM_ISDIRTY, newsItem.getIsDirty());
		values.put(COL_NEWSITEM_EVENTFLAG, newsItem.getEventFlag());
		values.put(COL_NEWSITEM_EVENTDATE, newsItem.getEventDate());
		values.put(COL_NEWSITEM_PUBLISHTOFACEBOOK,
				newsItem.getPublishToFacebook());
		values.put(COL_NEWSITEM_TEMPUNIQUEUID, newsItem.getTempUniqueUID());
		values.put(COL_NEWSITEM_EVENTDATEFINISH, newsItem.getEventDateFinish());
		values.put(COL_NEWSITEM_SORTPOSITION, newsItem.getSortPosition());
		values.put(COL_NEWSITEM_ARCHIVED, newsItem.getArchived());
		values.put(COL_NEWSITEM_LISTICON, newsItem.getListIcon());
		myDb.insert(TABLE_NEWSITEM, null, values);
		close();
	}

	public void insertNewsImage(NewsImage newsImage) {
		openDataBase();
		SQLiteDatabase myDb = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COL_NEWSIMAGE_ITEMID, newsImage.getItemId());
		values.put(COL_NEWSIMAGE_WIDTH, newsImage.getWidth());
		values.put(COL_NEWSIMAGE_HEIGHT, newsImage.getHeight());
		values.put(COL_NEWSIMAGE_ORIGINALNAME, newsImage.getOriginalName());
		values.put(COL_NEWSIMAGE_LOCATIONLOCAL, newsImage.getLocationLocal());
		values.put(COL_NEWSIMAGE_TYPE, newsImage.getType());
		values.put(COL_NEWSIMAGE_BASEURL, newsImage.getBaseURL());
		values.put(COL_NEWSIMAGE_MIMETYPE, newsImage.getMimeType());
		values.put(COL_NEWSIMAGE_BASE64VERSION, newsImage.getBase64Version());
		values.put(COL_NEWSIMAGE_ISDIRTY, newsImage.getIsDirty());
		values.put(COL_NEWSIMAGE_ARCHIVED, newsImage.getArchived());
		values.put(COL_NEWSIMAGE_IMAGEID, newsImage.getImageId());
		values.put(COL_NEWSIMAGE_NAME, newsImage.getName());
		myDb.insert(TABLE_NEWSIMAGE, null, values);
		close();
	}

	public void insertNewsHeadLine(NewsHeadLine newsHeadline) {
		openDataBase();
		SQLiteDatabase myDb = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COL_NEWSHEADLINE_ITEMID, newsHeadline.getItemId());
		values.put(COL_NEWSHEADLINE_THESTRING, newsHeadline.getTheString());
		myDb.insert(TABLE_NEWSHEADLINE, null, values);
		close();
	}

	public void insertNewsDescription(NewsDescription newsDescription) {
		openDataBase();
		SQLiteDatabase myDb = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COL_NEWSDESCRIPTION_ITEMID, newsDescription.getItemId());
		values.put(COL_NEWSDESCRIPTION_THESTRING,
				newsDescription.getTheString());
		myDb.insert(TABLE_NEWSDESCRIPTION, null, values);
		close();
	}

	public void insertNewsDescriptionHTML(
			NewsDescriptionHTML newsDescriptionHTML) {
		openDataBase();
		SQLiteDatabase myDb = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COL_NEWSDESCRIPTIONHTML_ITEMID,
				newsDescriptionHTML.getItemId());
		values.put(COL_NEWSDESCRIPTIONHTML_THESTRING,
				newsDescriptionHTML.getTheString());
		myDb.insert(TABLE_NEWSDESCRIPTIONHTML, null, values);
		close();
	}

	public SQLiteDatabase getDb() {
		return getWritableDatabase();
	}

	public ArrayList<HomeItems> displayHomeItem(String id) {
		openDataBase();
		listHomeItems.clear();
		Cursor fetchHomeItems = getDb().rawQuery(
				"SELECT " + COL_HOMEITEM_TITLE + "," + COL_HOMEITEM_TEXT + ","
						+ COL_HOMEITEM_TEXT_HTML + " FROM " + TABLE_HOME
						+ " WHERE " + COL_HOMEITEM_ID + " = " + id, null);
		if (fetchHomeItems.moveToFirst()) {
			do {
				HomeItems homeItems = new HomeItems();
				homeItems.setHomeItem_title(fetchHomeItems
						.getString(fetchHomeItems
								.getColumnIndex(COL_HOMEITEM_TITLE)));
				homeItems.setHomeItem_text(fetchHomeItems
						.getString(fetchHomeItems
								.getColumnIndex(COL_HOMEITEM_TEXT)));
				homeItems.setHomeItem_textHTML(fetchHomeItems
						.getString(fetchHomeItems
								.getColumnIndex(COL_HOMEITEM_TEXT_HTML)));
				listHomeItems.add(homeItems);
			} while (fetchHomeItems.moveToNext());
		}
		close();
		return listHomeItems;
	}

	public ArrayList<HomeItems> displayHomeItem() {
		openDataBase();
		listHomeItems.clear();
		Cursor fetchHomeItems = getDb().rawQuery(
				"SELECT " + COL_HOMEITEM_TITLE + "," + COL_HOMEITEM_ID
						+ " FROM " + TABLE_HOME, null);
		if (fetchHomeItems.moveToFirst()) {
			do {
				HomeItems homeItems = new HomeItems();
				homeItems.setHomeItem_title(fetchHomeItems
						.getString(fetchHomeItems
								.getColumnIndex(COL_HOMEITEM_TITLE)));
				homeItems.setHomeItem_id(fetchHomeItems
						.getString(fetchHomeItems
								.getColumnIndex(COL_HOMEITEM_ID)));
				listHomeItems.add(homeItems);
			} while (fetchHomeItems.moveToNext());
		}
		close();
		return listHomeItems;
	}

	public ArrayList<ModelMenuItem> displayMenuItem() {
		openDataBase();
		listMenuItems.clear();
		Cursor fetchHomeItems = getDb().rawQuery(
				"SELECT " + COL_MENUITEM_ITEM_NAME + " FROM " + TABLE_MENU,
				null);
		if (fetchHomeItems.moveToFirst()) {
			do {
				ModelMenuItem modelMenuItem = new ModelMenuItem();
				modelMenuItem.setItemName(fetchHomeItems
						.getString(fetchHomeItems
								.getColumnIndex(COL_MENUITEM_ITEM_NAME)));
				listMenuItems.add(modelMenuItem);
			} while (fetchHomeItems.moveToNext());
		}
		close();
		return listMenuItems;
	}

	public ArrayList<ModelNewsItem> displayNewsItem() {
		openDataBase();
		listNewsItems.clear();
		Cursor fetchNewsItems = getDb().rawQuery(
				"SELECT " + COL_MAINNEWSITEM_TABTEXT + ","
						+ COL_MAINNEWSITEM_NEWSITEMID + " FROM "
						+ TABLE_MAINNEWSITEM, null);
		if (fetchNewsItems.moveToFirst()) {
			do {
				ModelNewsItem modelNewsItem = new ModelNewsItem();
				modelNewsItem.setTabText(fetchNewsItems
						.getString(fetchNewsItems
								.getColumnIndex(COL_MAINNEWSITEM_TABTEXT)));
				modelNewsItem.setNewsItemId(fetchNewsItems
						.getInt(fetchNewsItems
								.getColumnIndex(COL_MAINNEWSITEM_NEWSITEMID)));
				listNewsItems.add(modelNewsItem);
			} while (fetchNewsItems.moveToNext());
		}
		close();
		return listNewsItems;
	}

	public ArrayList<NewsHeadLine> displayHeadLine(int id) {
		openDataBase();
		listNewsHeadLine.clear();
		Cursor fetchHeadLine = getDb().rawQuery(
				"SELECT A." + COL_NEWSHEADLINE_THESTRING + ",A."
						+ COL_NEWSHEADLINE_ITEMID + " FROM "
						+ TABLE_NEWSHEADLINE + " A, " + TABLE_NEWSITEM + " B"
						+ " WHERE " + "A" + "." + COL_NEWSITEM_ITEMID + " = "
						+ "B" + "." + COL_NEWSHEADLINE_ITEMID + " AND B."
						+ COL_NEWSITEM_NEWSITEMID + " = " + id, null);
		if (fetchHeadLine.moveToFirst()) {
			do {
				NewsHeadLine newsHeadLine = new NewsHeadLine();
				newsHeadLine.setTheString(fetchHeadLine.getString(fetchHeadLine
						.getColumnIndex(COL_NEWSHEADLINE_THESTRING)));
				newsHeadLine.setItemId(fetchHeadLine.getInt(fetchHeadLine
						.getColumnIndex(COL_NEWSHEADLINE_ITEMID)));
				listNewsHeadLine.add(newsHeadLine);
			} while (fetchHeadLine.moveToNext());
		}
		close();
		return listNewsHeadLine;
	}

	public ArrayList<NewsDescriptionHTML> displayDescriptionHTML(int id) {
		openDataBase();
		listNewsDescriptionHTML.clear();
		Cursor fetchDescriptionHTML = getDb().rawQuery(
				"SELECT A." + COL_NEWSDESCRIPTIONHTML_THESTRING + " FROM "
						+ TABLE_NEWSDESCRIPTIONHTML + " A, "
						+ TABLE_NEWSHEADLINE + " B" + " WHERE " + "A" + "."
						+ COL_NEWSDESCRIPTIONHTML_ITEMID + " = " + id, null);
		if (fetchDescriptionHTML.moveToFirst()) {
			do {
				NewsDescriptionHTML newsDescriptionHTML = new NewsDescriptionHTML();
				newsDescriptionHTML
						.setTheString(fetchDescriptionHTML.getString(fetchDescriptionHTML
								.getColumnIndex(COL_NEWSDESCRIPTIONHTML_THESTRING)));
				listNewsDescriptionHTML.add(newsDescriptionHTML);
			} while (fetchDescriptionHTML.moveToNext());
		}
		close();
		return listNewsDescriptionHTML;
	}

	public void displayDescriptionHTML() {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
