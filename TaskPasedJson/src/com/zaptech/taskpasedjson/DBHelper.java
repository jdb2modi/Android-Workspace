package com.zaptech.taskpasedjson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import org.json.JSONObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {
	// public static final String DB_JSON = "JsonDB.db";
	public static final String TB_HomeItems = "TB_HomeItems";
	public static final String TB_HomeItemsImage = "TB_HomeItemsImage";
	public static final String TB_MenuItems = "TB_MenuItems";
	public static final String TB_NewsItems = "tbnewsitems";
	public static final String TB_NewsItems_Items = "TB_NewsItems_Items";
	public static final String TB_NewsImages = "TB_NewsImage";
	public static final String TB_HeadLine = "TB_Headline";
	public static final String TB_Description = "TB_Description";
	public static final String TB_DescriptionHtml = "TB_DescriptionHtml";

	// COLUMNS FOR TB_HOMEITEMS
	public static final String COL_HOMEITEM_INCLUDE_IMAGE_IN_LAYOUT = "IncludeImageInLayout";
	public static final String COL_HOMEITEM_INCLUDE_TITLE_IN_LAYOUT = "IncludetitleInLayout";
	public static final String COL_HOMEITEM_INCLUDE_TEXT_IN_LAYOUT = "IncludeTextInLayout";
	public static final String COL_HOMEITEM_IMAGE_POSITION = "ImagePosition";
	public static final String COL_HOMEITEM_TITLE_POSITION = "TitlePosition";
	public static final String COL_HOMEITEM_TEXT_POSITION = "TextPosition";
	public static final String COL_HOMEITEM_TITLE = "Title";
	public static final String COL_HOMEITEM_TEXT = "Text";
	public static final String COL_HOMEITEM_TEXT_HTML = "TextHtml";
	public static final String COL_HOMEITEM_ID = "HomeItemId";// (Primary key in
																// HomeItem and
																// Foreign key
																// in Image
																// table)
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

	// COLUMNS FOR TB_HOMEITEMS_IMAGE
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

	// COLUMNS FOR TB_MENU_ITEMNS...

	public static final String COL_MenuItemName = "item_name";

	// COLUMNS FOR TB_NEWS_ITEMS...
	public static final String COL_NEWSITEM_NewsId = "newsItemId";
	public static final String COL_NEWSITEM_Videos = "videos";
	public static final String COL_NEWSITEM_SortType = "sortType";
	public static final String COL_NEWSITEM_SharePointURL = "sharePointURL";
	public static final String COL_NEWSITEM_DisplayAsGantt = "displayAsGantt";
	public static final String COL_NEWSITEM_TabPosition = "tabPosition";
	public static final String COL_NEWSITEM_TabText = "tabText";
	public static final String COL_NEWSITEM_TabIcon = "tabIcon";
	public static final String COL_NEWSITEM_DateChanged = "dateChanged";
	public static final String COL_NEWSITEM_IsDirty = "isDirty";
	public static final String COL_NEWSITEM_TempUniqueUID = "tempUniqueUID";
	public static final String COL_NEWSITEM_Type = "type";
	public static final String COL_NEWSITEM_UseTabIcon = "useTabIcon";
	public static final String COL_NEWSITEM_SortPosition = "sortPosition";
	public static final String COL_NEWSITEM_Archived = "archived";
	public static final String COL_NEWSITEM_ListIcon = "listIcon";

	// COLUMNS FOR TB_NEWSITEMS_ITEMS...
	// public static final String COL_NEWSITEM_NewsId = "newsItemId";
	public static final String COL_NEWSITEM_ItemId = "newsItems_ItemId";
	public static final String COL_NEWSITEM_Items_url = "url";
	public static final String COL_NEWSITEM_Items_datePublished = "datePublished";
	public static final String COL_NEWSITEM_Items_dateChanged = "dateChanged";
	public static final String COL_NEWSITEM_Items_isDirty = "isDirty";
	public static final String COL_NEWSITEM_Items_eventFlag = "eventFlag";
	public static final String COL_NEWSITEM_Items_eventDate = "eventDate";
	public static final String COL_NEWSITEM_Items_publishToFacebook = "publishToFacebook";
	public static final String COL_NEWSITEM_Items_tempUniqueUid = "tempUniqueUid";
	public static final String COL_NEWSITEM_Items_eventDateFinish = "eventDateFinish";
	public static final String COL_NEWSITEM_Items_sortPosition = "sortPosition";
	public static final String COL_NEWSITEM_Items_archived = "archived";
	public static final String COL_NEWSITEM_Items_listIcon = "listIcon";
	public static final String COL_NEWSITEM_Items_sortType = "sortType";
	// public static final String COL_NEWSITEM_NewsId = "newsItemId";..AS ABOVE

	// COLUMNS FOR TB_NEWSImages...
	public static final String COL_NEWSIMAGE_newsImageId = "newsImageId";
	public static final String COL_NEWSIMAGE_width = "width";
	public static final String COL_NEWSIMAGE_height = "height";
	public static final String COL_NEWSIMAGE_originalName = "originalName";
	public static final String COL_NEWSIMAGE_locationLocal = "locationLocal";
	public static final String COL_NEWSIMAGE_type = "type";
	public static final String COL_NEWSIMAGE_baseUrl = "baseUrl";
	public static final String COL_NEWSIMAGE_mimeType = "mimeType";
	public static final String COL_NEWSIMAGE_base64Version = "base64Version";
	public static final String COL_NEWSIMAGE_isDirty = "isDirty";
	public static final String COL_NEWSIMAGE_archived = "archived";
	public static final String COL_NEWSIMAGE_name = "name";
	public static final String COL_NEWSIMAGE_newsItems_ItemId = "newsItems_ItemId";

	// COLUMNS FOR HEADLINES...
	public static final String COL_HEADLINE_theString = "theString";
	// COLUMNS FOR Description...
	public static final String COL_Description_theString = "theString";
	// COLUMNS FOR DescriptionHtml...
	public static final String COL_DESCRIPTION_HTML_theString = "theString";

	SQLiteDatabase sqlite;
	ContentValues contentValues;

	ArrayList<HomeItems> arrayListHomeItems;
	ArrayList<Model_MenuItems> arrayListMenuItems;
	ArrayList<Model_NewsItem> arrayListNewsItems;
	ArrayList<Model_NewsItem_Items> arrayListNewsItems_Items;
	ArrayList<Model_Headline> arrayListHeadLines;

	// CONCEPT ASSET...
	private static SQLiteDatabase myDataBase;
	private static Context myContext;
	public int count = 0;

	public DBHelper(Context context) {

		super(context, context.getResources().getString(R.string.DB_NAME),
				null, 1);
		DBHelper.myContext = context;

		try {
			createDataBase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
		myDataBase = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READWRITE);

	}

	@Override
	public synchronized void close() {

		if (myDataBase != null)
			myDataBase.close();

		super.close();

	}

	/*
	 * public DBHelper(Context context) { super(context, DB_JSON, null, 1);
	 * 
	 * }
	 */

	@Override
	public void onCreate(SQLiteDatabase db) {

		/*
		 * String CREATE_TB_HOMEITEMIMAGE = "CREATE TABLE IF NOT EXISTS " +
		 * TB_HomeItemsImage + " (" + COL_IMAGE_ID + " INTEGER," +
		 * COL_IMAGE_WIDTH + " INTEGER," + COL_IMAGE_HEIGHT + " INTEGER," +
		 * COL_IMAGE_ORIGINAL_NAME + " VARCHAR," + COL_IMAGE_LOCATION_LOCAL +
		 * " VARCHAR," + COL_IMAGE_TYPE + " INTEGER," + COL_IMAGE_BASE_URL +
		 * " VARCHAR," + COL_IMAGE_MIME_TYPE + " VARCHAR," +
		 * COL_IMAGE_BASE64_VERSION + " VARCHAR," + COL_IMAGE_IS_DIRTY +
		 * " VARCHAR," + COL_IMAGE_ARCHIVED + " VARCHAR," + COL_IMAGE_NAME +
		 * " VARCHAR," + COL_HOMEITEM_ID + " INTEGER REFERENCES " + TB_HomeItems
		 * + "(" + COL_HOMEITEM_ID + "));"; String CREATE_TB_HOME_ITEMS =
		 * "CREATE TABLE IF NOT EXISTS " + TB_HomeItems + " (" + COL_HOMEITEM_ID
		 * + " INTEGER PRIMARY KEY," + COL_HOMEITEM_INCLUDE_IMAGE_IN_LAYOUT +
		 * " VARCHAR," + COL_HOMEITEM_INCLUDE_TITLE_IN_LAYOUT + " VARCHAR," +
		 * COL_HOMEITEM_INCLUDE_TEXT_IN_LAYOUT + " VARCHAR," +
		 * COL_HOMEITEM_IMAGE_POSITION + " VARCHAR," +
		 * COL_HOMEITEM_TITLE_POSITION + " VARCHAR," +
		 * COL_HOMEITEM_TEXT_POSITION + " VARCHAR," + COL_HOMEITEM_TITLE +
		 * " VARCHAR," + COL_HOMEITEM_TEXT + " VARCHAR," +
		 * COL_HOMEITEM_TEXT_HTML + " VARCHAR," + COL_HOMEITEM_TAB_POSITION +
		 * " INTEGER," + COL_HOMEITEM_TAB_TEXT + " VARCHAR," +
		 * COL_HOMEITEM_TAB_ICON + " VARCHAR," + COL_HOMEITEM_DATE_CHANGED +
		 * " VARCHAR," + COL_HOMEITEM_IS_DIRTY + " VARCHAR," +
		 * COL_HOMEITEM_TEMP_UNIQUE_UID + " VARCHAR," + COL_HOMEITEM_TYPE +
		 * " INTEGER," + COL_HOMEITEM_USE_TAB_ICON + " VARCHAR," +
		 * COL_HOMEITEM_SORT_POSITION + " INTEGER," + COL_HOMEITEM_ARCHIVED +
		 * " VARCHAR," + COL_HOMEITEM_LIST_ICON + " VARCHAR);";
		 * 
		 * db.execSQL(CREATE_TB_HOME_ITEMS);
		 * db.execSQL(CREATE_TB_HOMEITEMIMAGE);COL_NEWSITEM_ItemId
		 */
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public SQLiteDatabase getDB() {
		sqlite = getWritableDatabase();
		return sqlite;
	}

	// ///////////////////////////////////INSERT//////////////////////////////////
	public void insertHomeItems(HomeItems model_HomeItems) {
		openDataBase();
		contentValues = new ContentValues();
		sqlite = getDB();
		contentValues.put(COL_HOMEITEM_ID, model_HomeItems.getHomeItem_id());
		contentValues.put(COL_HOMEITEM_INCLUDE_IMAGE_IN_LAYOUT,
				model_HomeItems.getHomeItem_includeImageInLayout());
		contentValues.put(COL_HOMEITEM_INCLUDE_TITLE_IN_LAYOUT,
				model_HomeItems.getHomeItem_includeTitleInLayout());
		contentValues.put(COL_HOMEITEM_INCLUDE_TEXT_IN_LAYOUT,
				model_HomeItems.getHomeItem_includeTextInLayout());
		contentValues.put(COL_HOMEITEM_IMAGE_POSITION,
				model_HomeItems.getHomeItem_imagePosition());
		contentValues.put(COL_HOMEITEM_TITLE_POSITION,
				model_HomeItems.getHomeItem_titlePosition());
		contentValues.put(COL_HOMEITEM_TEXT_POSITION,
				model_HomeItems.getHomeItem_textPosition());
		contentValues.put(COL_HOMEITEM_TITLE,
				model_HomeItems.getHomeItem_title());
		contentValues
				.put(COL_HOMEITEM_TEXT, model_HomeItems.getHomeItem_text());
		contentValues.put(COL_HOMEITEM_TEXT_HTML,
				model_HomeItems.getHomeItem_textHTML());
		contentValues.put(COL_HOMEITEM_TAB_TEXT,
				model_HomeItems.getHomeItem_tabText());
		contentValues.put(COL_HOMEITEM_TAB_ICON,
				model_HomeItems.getHomeItem_tabIcon());
		contentValues.put(COL_HOMEITEM_DATE_CHANGED,
				model_HomeItems.getHomeItem_dateChanged());
		contentValues.put(COL_HOMEITEM_IS_DIRTY,
				model_HomeItems.getHomeItem_isDirty());
		contentValues.put(COL_HOMEITEM_TEMP_UNIQUE_UID,
				model_HomeItems.getHomeItem_tempUniqueUID());
		contentValues
				.put(COL_HOMEITEM_TYPE, model_HomeItems.getHomeItem_Type());
		contentValues.put(COL_HOMEITEM_USE_TAB_ICON,
				model_HomeItems.getHomeItem_useTabIcon());
		contentValues.put(COL_HOMEITEM_SORT_POSITION,
				model_HomeItems.getHomeItem_sortPosition());
		contentValues.put(COL_HOMEITEM_ARCHIVED,
				model_HomeItems.getHomeItem_archived());
		contentValues.put(COL_HOMEITEM_LIST_ICON,
				model_HomeItems.getHomeItem_listIcon());
		sqlite.insert(TB_HomeItems, null, contentValues);
		sqlite.close();
		sqlite.releaseMemory();
		close();

	}

	public void insertHomeItemImage(HomeItemIMAGE model_HomeItemImage) {
		openDataBase();
		contentValues = new ContentValues();
		sqlite = getDB();
		contentValues.put(COL_IMAGE_ID,
				model_HomeItemImage.getHomeItemImage_Id());
		contentValues.put(COL_IMAGE_WIDTH,
				model_HomeItemImage.getHomeItemImage_Width());
		contentValues.put(COL_IMAGE_HEIGHT,
				model_HomeItemImage.getHomeItemImage_Height());
		contentValues.put(COL_IMAGE_ORIGINAL_NAME,
				model_HomeItemImage.getHomeItemImage_OriginalName());
		contentValues.put(COL_IMAGE_LOCATION_LOCAL,
				model_HomeItemImage.getHomeItemImage_LocationLocal());
		contentValues.put(COL_IMAGE_TYPE,
				model_HomeItemImage.getHomeItemImage_Type());
		contentValues.put(COL_IMAGE_BASE_URL,
				model_HomeItemImage.getHomeItemImage_BaseURL());
		contentValues.put(COL_IMAGE_MIME_TYPE,
				model_HomeItemImage.getHomeItemImage_mimeType());
		contentValues.put(COL_IMAGE_BASE64_VERSION,
				model_HomeItemImage.getHomeItemImage__Base64Version());
		contentValues.put(COL_IMAGE_IS_DIRTY,
				model_HomeItemImage.getHomeItemImage_IsDirty());
		contentValues.put(COL_IMAGE_ARCHIVED,
				model_HomeItemImage.getHomeItemImage_Archived());
		contentValues.put(COL_IMAGE_NAME,
				model_HomeItemImage.getHomeItemImage_Name());
		contentValues
				.put(COL_HOMEITEM_ID, model_HomeItemImage.getHomeItem_Id());
		sqlite.insert(TB_HomeItemsImage, null, contentValues);
		sqlite.close();
		sqlite.releaseMemory();
		close();
	}

	public void insertMenuItems(Model_MenuItems model_MenuItems) {
		openDataBase();
		contentValues = new ContentValues();
		sqlite = getDB();
		contentValues.put(COL_MenuItemName, model_MenuItems.getItem_name());
		sqlite.insert(TB_MenuItems, null, contentValues);
		sqlite.close();
		sqlite.releaseMemory();
		close();
	}

	public void insertNewsItems(Model_NewsItem model_NewsItem) {
		openDataBase();

		contentValues = new ContentValues();
		sqlite = getDB();
		contentValues.put(COL_NEWSITEM_NewsId, model_NewsItem.getId());
		contentValues.put(COL_NEWSITEM_Videos, model_NewsItem.getVideos());
		contentValues.put(COL_NEWSITEM_SortType, model_NewsItem.getSortType());
		contentValues.put(COL_NEWSITEM_SharePointURL,
				model_NewsItem.getSharePointURL());
		contentValues.put(COL_NEWSITEM_DisplayAsGantt,
				model_NewsItem.getDisplayAsGantt());
		contentValues.put(COL_NEWSITEM_TabPosition,
				model_NewsItem.getTabPosition());
		contentValues.put(COL_NEWSITEM_TabText, model_NewsItem.getTabText());
		contentValues.put(COL_NEWSITEM_TabIcon, model_NewsItem.getTabIcon());
		contentValues.put(COL_NEWSITEM_DateChanged,
				model_NewsItem.getDateChanged());
		contentValues.put(COL_NEWSITEM_IsDirty, model_NewsItem.getIsDirty());
		contentValues.put(COL_NEWSITEM_TempUniqueUID,
				model_NewsItem.getTempUniqueUID());
		contentValues.put(COL_NEWSITEM_Type, model_NewsItem.getType());
		contentValues.put(COL_NEWSITEM_UseTabIcon,
				model_NewsItem.getUseTabIcon());
		contentValues.put(COL_NEWSITEM_SortPosition,
				model_NewsItem.getSortPosition());
		contentValues.put(COL_NEWSITEM_Archived, model_NewsItem.getArchived());
		contentValues.put(COL_NEWSITEM_ListIcon, model_NewsItem.getListIcon());
		sqlite.insert(TB_NewsItems, null, contentValues);
		sqlite.close();
		sqlite.releaseMemory();
		close();
	}

	public void insertNewsItem_Items(Model_NewsItem_Items model_NewsItem_Items,
			Model_NewsItem obj_newsItem) {
		openDataBase();
		contentValues = new ContentValues();
		sqlite = getDB();
		contentValues.put(COL_NEWSITEM_ItemId,
				model_NewsItem_Items.getNewsItems_ItemId());

		contentValues
				.put(COL_NEWSITEM_Items_url, model_NewsItem_Items.getUrl());

		contentValues.put(COL_NEWSITEM_Items_datePublished,
				model_NewsItem_Items.getDatePublished());
		contentValues.put(COL_NEWSITEM_Items_dateChanged,
				model_NewsItem_Items.getDateChanged());
		contentValues.put(COL_NEWSITEM_Items_isDirty,
				model_NewsItem_Items.getIsDirty());
		contentValues.put(COL_NEWSITEM_Items_eventFlag,
				model_NewsItem_Items.getEventFlag());
		contentValues.put(COL_NEWSITEM_Items_eventDate,
				model_NewsItem_Items.getEventDate());
		contentValues.put(COL_NEWSITEM_Items_publishToFacebook,
				model_NewsItem_Items.getPublishToFacebook());
		contentValues.put(COL_NEWSITEM_Items_tempUniqueUid,
				model_NewsItem_Items.getTempUniqueUID());
		contentValues.put(COL_NEWSITEM_Items_eventDateFinish,
				model_NewsItem_Items.getEventDateFinish());
		contentValues.put(COL_NEWSITEM_Items_sortPosition,
				model_NewsItem_Items.getSortPosition());
		contentValues.put(COL_NEWSITEM_Items_archived,
				model_NewsItem_Items.getArchived());
		contentValues.put(COL_NEWSITEM_Items_listIcon,
				model_NewsItem_Items.getListIcon());
		contentValues.put(COL_NEWSITEM_NewsId, obj_newsItem.getId());

		sqlite.insert(TB_NewsItems_Items, null, contentValues);
		sqlite.close();
		sqlite.releaseMemory();
		close();
	}

	public void insertNewsImages(Model_NewsImage model_NewsImage,
			Model_NewsItem_Items model_NewsItem_Items) {
		openDataBase();
		contentValues = new ContentValues();
		sqlite = getDB();
		contentValues.put(COL_NEWSIMAGE_newsImageId,
				model_NewsImage.getNewsImageId());

		contentValues.put(COL_NEWSIMAGE_width, model_NewsImage.getWidth());
		contentValues.put(COL_NEWSIMAGE_height, model_NewsImage.getHeight());
		contentValues.put(COL_NEWSIMAGE_originalName,
				model_NewsImage.getOriginalName());
		contentValues.put(COL_NEWSIMAGE_locationLocal,
				model_NewsImage.getLocationLocal());
		contentValues.put(COL_NEWSIMAGE_type, model_NewsImage.getType());
		contentValues.put(COL_NEWSIMAGE_baseUrl, model_NewsImage.getBaseURL());
		contentValues
				.put(COL_NEWSIMAGE_mimeType, model_NewsImage.getMimeType());
		contentValues.put(COL_NEWSIMAGE_base64Version,
				model_NewsImage.getBase64Version());
		contentValues.put(COL_NEWSIMAGE_isDirty, model_NewsImage.getIsDirty());
		contentValues
				.put(COL_NEWSIMAGE_archived, model_NewsImage.getArchived());
		contentValues.put(COL_NEWSIMAGE_name, model_NewsImage.getName());
		contentValues.put(COL_NEWSIMAGE_newsItems_ItemId,
				model_NewsItem_Items.getNewsItems_ItemId());

		sqlite.insert(TB_NewsImages, null, contentValues);
		sqlite.close();
		sqlite.releaseMemory();
		close();

	}

	public void insertHeadlines(Model_Headline model_HeadLine,
			Model_NewsItem_Items model_newsItem_Items) {
		openDataBase();
		contentValues = new ContentValues();
		sqlite = getDB();

		contentValues
				.put(COL_HEADLINE_theString, model_HeadLine.getTheString());
		contentValues.put("newsItem_ItemId",
				model_newsItem_Items.getNewsItems_ItemId());
		sqlite.insert(TB_HeadLine, null, contentValues);
		sqlite.close();
		sqlite.releaseMemory();
		close();

	}

	public void insertDescription(Model_Description model_Description,
			Model_NewsItem_Items modelNewsItem_Items) {
		openDataBase();
		contentValues = new ContentValues();
		sqlite = getDB();

		contentValues.put(COL_Description_theString,
				model_Description.getTheString());
		contentValues.put("newsItem_ItemId",
				modelNewsItem_Items.getNewsItems_ItemId());
		sqlite.insert(TB_Description, null, contentValues);
		sqlite.close();
		sqlite.releaseMemory();
		close();
	}

	public void insertDescriptionHtml(
			Model_DescriptionHMTL modelDescriptionHMTL,
			Model_NewsItem_Items modelNewsItem_Items) {
		openDataBase();
		contentValues = new ContentValues();
		sqlite = getDB();

		contentValues.put(COL_DESCRIPTION_HTML_theString,
				modelDescriptionHMTL.getTheString());
		contentValues.put("newsItem_ItemId",
				modelNewsItem_Items.getNewsItems_ItemId());
		sqlite.insert(TB_DescriptionHtml, null, contentValues);
		sqlite.close();
		sqlite.releaseMemory();
		close();

	}

	// //////////////////////////INSERT COMPLETE///////////////////////////////

	//
	// //////////////////////////////DISPLAY//////////////////////////////
	public ArrayList<HomeItems> displayHomeItems() {
		arrayListHomeItems = new ArrayList<HomeItems>();
		openDataBase();
		sqlite = getDB();
		String getHomeItems = "SELECT * FROM " + TB_HomeItems;
		Cursor cursor = sqlite.rawQuery(getHomeItems, null);

		if (cursor != null) {
			if (cursor.moveToFirst()) {
				do {
					HomeItems model_HomeItems = new HomeItems();
					model_HomeItems.setHomeItem_id(cursor.getString(cursor
							.getColumnIndex(COL_HOMEITEM_ID)));
					model_HomeItems
							.setHomeItem_includeImageInLayout(cursor.getString(cursor
									.getColumnIndex(COL_HOMEITEM_INCLUDE_IMAGE_IN_LAYOUT)));
					model_HomeItems
							.setHomeItem_includeTitleInLayout(cursor.getString(cursor
									.getColumnIndex(COL_HOMEITEM_INCLUDE_TITLE_IN_LAYOUT)));
					model_HomeItems.setHomeItem_text(cursor.getString(cursor
							.getColumnIndex(COL_HOMEITEM_TEXT)));
					model_HomeItems
							.setHomeItem_imagePosition(cursor.getString(cursor
									.getColumnIndex(COL_HOMEITEM_IMAGE_POSITION)));
					model_HomeItems
							.setHomeItem_titlePosition(cursor.getString(cursor
									.getColumnIndex(COL_HOMEITEM_TITLE_POSITION)));
					model_HomeItems
							.setHomeItem_textPosition(cursor.getString(cursor
									.getColumnIndex(COL_HOMEITEM_TEXT_POSITION)));
					model_HomeItems.setHomeItem_title(cursor.getString(cursor
							.getColumnIndex(COL_HOMEITEM_TITLE)));
					model_HomeItems.setHomeItem_text(cursor.getString(cursor
							.getColumnIndex(COL_HOMEITEM_TEXT)));
					model_HomeItems.setHomeItem_textHTML(cursor
							.getString(cursor
									.getColumnIndex(COL_HOMEITEM_TEXT_HTML)));
					model_HomeItems
							.setHomeItem_tabPosition(cursor.getString(cursor
									.getColumnIndex(COL_HOMEITEM_TAB_POSITION)));
					model_HomeItems.setHomeItem_tabText(cursor.getString(cursor
							.getColumnIndex(COL_HOMEITEM_TAB_TEXT)));
					model_HomeItems.setHomeItem_tabIcon(cursor.getString(cursor
							.getColumnIndex(COL_HOMEITEM_TAB_ICON)));
					model_HomeItems
							.setHomeItem_dateChanged(cursor.getString(cursor
									.getColumnIndex(COL_HOMEITEM_DATE_CHANGED)));
					model_HomeItems.setHomeItem_isDirty(cursor.getString(cursor
							.getColumnIndex(COL_HOMEITEM_IS_DIRTY)));
					model_HomeItems
							.setHomeItem_tempUniqueUID(cursor.getString(cursor
									.getColumnIndex(COL_HOMEITEM_TEMP_UNIQUE_UID)));
					model_HomeItems.setHomeItem_Type(cursor.getString(cursor
							.getColumnIndex(COL_HOMEITEM_TYPE)));
					model_HomeItems.setHomeItem_tabIcon(cursor.getString(cursor
							.getColumnIndex(COL_HOMEITEM_USE_TAB_ICON)));
					model_HomeItems
							.setHomeItem_sortPosition(cursor.getString(cursor
									.getColumnIndex(COL_HOMEITEM_SORT_POSITION)));
					model_HomeItems.setHomeItem_archived(cursor
							.getString(cursor
									.getColumnIndex(COL_HOMEITEM_ARCHIVED)));
					model_HomeItems.setHomeItem_listIcon(cursor
							.getString(cursor
									.getColumnIndex(COL_HOMEITEM_LIST_ICON)));
					arrayListHomeItems.add(model_HomeItems);
				} while (cursor.moveToNext());

			}

		}
		sqlite.close();
		sqlite.releaseMemory();
		close();
		return arrayListHomeItems;
	}

	public ArrayList<Model_MenuItems> displayMenuItems() {
		arrayListMenuItems = new ArrayList<Model_MenuItems>();
		openDataBase();
		sqlite = getDB();
		String sql = "SELECT * FROM " + TB_MenuItems;
		Cursor cursor = sqlite.rawQuery(sql, null);
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				do {
					Model_MenuItems model_MenuItems = new Model_MenuItems();
					model_MenuItems.setItem_name(cursor.getString(cursor
							.getColumnIndex(COL_MenuItemName)));
					arrayListMenuItems.add(model_MenuItems);
				} while (cursor.moveToNext());
			}
		}
		close();
		sqlite.close();
		sqlite.releaseMemory();
		return arrayListMenuItems;
	}

	public ArrayList<Model_NewsItem> displayNewsItems() {
		arrayListNewsItems = new ArrayList<Model_NewsItem>();
		openDataBase();
		sqlite = getDB();
		String sql = "SELECT * FROM " + TB_NewsItems;
		Cursor cursor = sqlite.rawQuery(sql, null);
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				do {
					Model_NewsItem model_NewsItems = new Model_NewsItem();
					model_NewsItems.setId(cursor.getInt(cursor
							.getColumnIndex(COL_NEWSITEM_NewsId)));

					model_NewsItems.setTabText(cursor.getString(cursor
							.getColumnIndex(COL_NEWSITEM_TabText)));

					arrayListNewsItems.add(model_NewsItems);

				} while (cursor.moveToNext());
			}
		}
		close();
		sqlite.close();
		sqlite.releaseMemory();
		return arrayListNewsItems;
	}

	public ArrayList<Model_NewsItem_Items> getItems(int id) {
		openDataBase();
		sqlite = getDB();
		arrayListNewsItems_Items = new ArrayList<Model_NewsItem_Items>();

		Cursor cursor = sqlite.rawQuery("select * from " + TB_NewsItems_Items
				+ " where " + COL_NEWSITEM_NewsId + "='" + id + "'", null);

		if (cursor != null) {
			if (cursor.moveToFirst()) {
				do {
					Model_NewsItem_Items model_newsItem_Items = new Model_NewsItem_Items();
					model_newsItem_Items.setNewsItems_ItemId(Integer
							.parseInt(cursor.getString(cursor
									.getColumnIndex(COL_NEWSITEM_ItemId))));
					model_newsItem_Items.setUrl(cursor.getString(cursor
							.getColumnIndex(COL_NEWSITEM_Items_url)));
					model_newsItem_Items
							.setDatePublished(cursor.getString(cursor
									.getColumnIndex(COL_NEWSITEM_Items_datePublished)));
					model_newsItem_Items.setDateChanged(cursor.getString(cursor
							.getColumnIndex(COL_NEWSITEM_Items_dateChanged)));
					model_newsItem_Items.setIsDirty(cursor.getString(cursor
							.getColumnIndex(COL_NEWSITEM_Items_isDirty)));
					model_newsItem_Items.setEventFlag(cursor.getString(cursor
							.getColumnIndex(COL_NEWSITEM_Items_eventFlag)));
					model_newsItem_Items.setEventDate(cursor.getString(cursor
							.getColumnIndex(COL_NEWSITEM_Items_eventDate)));
					model_newsItem_Items
							.setPublishToFacebook(cursor.getString(cursor
									.getColumnIndex(COL_NEWSITEM_Items_publishToFacebook)));
					model_newsItem_Items
							.setTempUniqueUID(cursor.getString(cursor
									.getColumnIndex(COL_NEWSITEM_Items_tempUniqueUid)));
					model_newsItem_Items
							.setEventDateFinish(cursor.getString(cursor
									.getColumnIndex(COL_NEWSITEM_Items_eventDateFinish)));
					model_newsItem_Items
							.setSortPosition(cursor.getString(cursor
									.getColumnIndex(COL_NEWSITEM_Items_sortPosition)));
					model_newsItem_Items.setArchived(cursor.getString(cursor
							.getColumnIndex(COL_NEWSITEM_Items_archived)));
					model_newsItem_Items.setListIcon(cursor.getString(cursor
							.getColumnIndex(COL_NEWSITEM_Items_listIcon)));
					// //FOR HEADLINE
					Cursor cursor1 = myDataBase
							.rawQuery(
									"select * from "
											+ TB_HeadLine
											+ " where "
											+ "newsItem_ItemId"
											+ "='"
											+ cursor.getString(cursor
													.getColumnIndex(COL_NEWSITEM_ItemId))
											+ "'", null);

					if (cursor1 != null) {
						if (cursor1.moveToFirst()) {
							do {
								Model_Headline tempHeadLine = new Model_Headline();
								tempHeadLine
										.setTheString(cursor1.getString(cursor1
												.getColumnIndex(COL_HEADLINE_theString)));

								tempHeadLine
										.setNewsItem_ItemId(cursor.getInt(cursor
												.getColumnIndex(COL_NEWSITEM_ItemId)));
								model_newsItem_Items
										.setModel_headline(tempHeadLine);

							} while (cursor1.moveToNext());
						}
					}

					arrayListNewsItems_Items.add(model_newsItem_Items);
				} while (cursor.moveToNext());
			}
		}
		myDataBase.close();
		return arrayListNewsItems_Items;
	}

	public Model_NewsItem_Items getNewsDetails(int id) {
		openDataBase();
		ArrayList<Model_NewsItem_Items> tempList = new ArrayList<Model_NewsItem_Items>();
		Model_NewsItem_Items model_newsItem_Items = new Model_NewsItem_Items();
		Cursor cursor1 = myDataBase.rawQuery("select * from " + TB_HeadLine
				+ " where " + "newsItem_ItemId " + "='" + id + "'", null);
		if (cursor1.getCount() > 0) {
			if (cursor1 != null) {
				if (cursor1.moveToFirst()) {
					do {
						Model_Headline model_Headline = new Model_Headline();
						model_Headline.setTheString(cursor1.getString(cursor1
								.getColumnIndex(COL_HEADLINE_theString)));
						model_newsItem_Items.setModel_headline(model_Headline);

					} while (cursor1.moveToNext());
				}
			}
		}
		Cursor cursor2 = myDataBase.rawQuery("select * from " + TB_Description
				+ " where " + "newsItem_ItemId" + "='" + id + "'", null);
		if (cursor2.getCount() > 0) {
			if (cursor2 != null) {
				if (cursor2.moveToFirst()) {
					do {
						Model_Description model_description = new Model_Description();
						model_description
								.setTheString(cursor2.getString(cursor2
										.getColumnIndex(COL_Description_theString)));// //
						model_newsItem_Items
								.setModel_description(model_description);

					} while (cursor2.moveToNext());
				}
			}
		}
		Cursor cursor3 = myDataBase.rawQuery("select * from "
				+ TB_DescriptionHtml + " where " + "newsItem_ItemId" + "='"
				+ id + "'", null);
		if (cursor3.getCount() > 0) {
			if (cursor3 != null) {
				if (cursor3.moveToFirst()) {
					do {
						Model_DescriptionHMTL model_decriptionHtml = new Model_DescriptionHMTL();
						model_decriptionHtml
								.setTheString(cursor3.getString(cursor3
										.getColumnIndex(COL_DESCRIPTION_HTML_theString)));// ///
						model_newsItem_Items
								.setModel_descriptionHTML(model_decriptionHtml);

					} while (cursor3.moveToNext());
				}
			}
		}
		myDataBase.close();
		return model_newsItem_Items;
	}
	/*
	 * public Model_NewsItem_Items getDescription(){ return }
	 */

}
/*
 * public ArrayList<Model_Headline> displayHeadlines() { arrayListHeadLines=new
 * ArrayList<Model_Headline>(); openDataBase(); sqlite = getDB(); String sql =
 * "SELECT * FROM " + TB_HeadLine; Cursor cursor = sqlite.rawQuery(sql, null);
 * if (cursor != null) { if (cursor.moveToFirst()) { do { Model_Headline
 * model_headline=new Model_Headline();
 * model_NewsItems.setId(cursor.getInt(cursor
 * .getColumnIndex(COL_NEWSITEM_NewsId)));
 * 
 * model_NewsItems.setTabText(cursor.getString(cursor
 * .getColumnIndex(COL_NEWSITEM_TabText)));
 * 
 * arrayListNewsItems.add(model_NewsItems);
 * 
 * } while (cursor.moveToNext()); } } close(); sqlite.close();
 * sqlite.releaseMemory(); return arrayListNewsItems; }
 */
// ///////////////////////DISPLAY COMPLETE/////////////////////////

/*
 * public void insertHomeItems(int homeId, String includeImageInLayout, String
 * includeTitleInLayout, String includeTextInLayout, String imagePosition,
 * String titlePosition, String textPosition, String title, String text, String
 * textHtml, int tabPosition, String tabText, String tabIcon, String
 * dateChanged, String isDirty, String tempUniqueUID, int type, String
 * useTabIcon, int sortPosition, String archived, String listIcon) {
 * contentValues = getContentValues(); sqlite = getDB();
 * contentValues.put(COL_HOMEITEM_ID, homeId);
 * contentValues.put(COL_HOMEITEM_INCLUDE_IMAGE_IN_LAYOUT,
 * includeImageInLayout);
 * contentValues.put(COL_HOMEITEM_INCLUDE_TITLE_IN_LAYOUT,
 * includeTitleInLayout); contentValues.put(COL_HOMEITEM_INCLUDE_TEXT_IN_LAYOUT,
 * includeTextInLayout); contentValues.put(COL_HOMEITEM_IMAGE_POSITION,
 * imagePosition); contentValues.put(COL_HOMEITEM_TITLE_POSITION,
 * titlePosition); contentValues.put(COL_HOMEITEM_TEXT_POSITION, textPosition);
 * contentValues.put(COL_HOMEITEM_TITLE, title);
 * contentValues.put(COL_HOMEITEM_TEXT, text);
 * contentValues.put(COL_HOMEITEM_TEXT_HTML, textHtml);
 * contentValues.put(COL_HOMEITEM_TAB_TEXT, tabText);
 * contentValues.put(COL_HOMEITEM_TAB_ICON, tabIcon);
 * contentValues.put(COL_HOMEITEM_DATE_CHANGED, dateChanged);
 * contentValues.put(COL_HOMEITEM_IS_DIRTY, isDirty);
 * contentValues.put(COL_HOMEITEM_TEMP_UNIQUE_UID, tempUniqueUID);
 * contentValues.put(COL_HOMEITEM_TYPE, type);
 * contentValues.put(COL_HOMEITEM_USE_TAB_ICON, useTabIcon);
 * contentValues.put(COL_HOMEITEM_SORT_POSITION, sortPosition);
 * contentValues.put(COL_HOMEITEM_ARCHIVED, archived);
 * contentValues.put(COL_HOMEITEM_LIST_ICON, listIcon);
 * sqlite.insert(TB_HomeItems, null, contentValues); sqlite.close(); }
 */

/*
 * public void insertHomeItemImage(int imageId, int width, int height, String
 * originalName, String locationLocal, String imageType, String baseURL, String
 * mimeType, String base64Version, String isDirty, String archive, String name,
 * int homeItemId) { contentValues = getContentValues(); sqlite = getDB();
 * contentValues.put(COL_IMAGE_ID, imageId); contentValues.put(COL_IMAGE_WIDTH,
 * width); contentValues.put(COL_IMAGE_HEIGHT, height);
 * contentValues.put(COL_IMAGE_ORIGINAL_NAME, originalName);
 * contentValues.put(COL_IMAGE_LOCATION_LOCAL, locationLocal);
 * contentValues.put(COL_IMAGE_TYPE, imageType);
 * contentValues.put(COL_IMAGE_BASE_URL, baseURL);
 * contentValues.put(COL_IMAGE_MIME_TYPE, mimeType);
 * contentValues.put(COL_IMAGE_BASE64_VERSION, base64Version);
 * contentValues.put(COL_IMAGE_IS_DIRTY, isDirty);
 * contentValues.put(COL_IMAGE_ARCHIVED, archive);
 * contentValues.put(COL_IMAGE_NAME, name); contentValues.put(COL_HOMEITEM_ID,
 * homeItemId); sqlite.insert(TB_HomeItemsImage, null, contentValues);
 * sqlite.close(); }
 */

