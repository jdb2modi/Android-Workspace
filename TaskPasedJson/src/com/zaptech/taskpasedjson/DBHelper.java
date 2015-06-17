package com.zaptech.taskpasedjson;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	public static final String DB_JSON = "JsonDB.db";
	public static final String TB_HomeItems = "TB_HomeItems";
	public static final String TB_HomeItemsImage = "TB_HomeItemsImage";

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

	SQLiteDatabase sqlite;
	ContentValues contentValues;

	ArrayList<HomeItems> arrayListHomeItems;

	public DBHelper(Context context) {
		super(context, DB_JSON, null, 1);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		String CREATE_TB_HOMEITEMIMAGE = "CREATE TABLE IF NOT EXISTS "
				+ TB_HomeItemsImage + " (" + COL_IMAGE_ID + " INTEGER,"
				+ COL_IMAGE_WIDTH + " INTEGER," + COL_IMAGE_HEIGHT
				+ " INTEGER," + COL_IMAGE_ORIGINAL_NAME + " VARCHAR,"
				+ COL_IMAGE_LOCATION_LOCAL + " VARCHAR," + COL_IMAGE_TYPE
				+ " INTEGER," + COL_IMAGE_BASE_URL + " VARCHAR,"
				+ COL_IMAGE_MIME_TYPE + " VARCHAR," + COL_IMAGE_BASE64_VERSION
				+ " VARCHAR," + COL_IMAGE_IS_DIRTY + " VARCHAR,"
				+ COL_IMAGE_ARCHIVED + " VARCHAR," + COL_IMAGE_NAME
				+ " VARCHAR," + COL_HOMEITEM_ID + " INTEGER REFERENCES "
				+ TB_HomeItems + "(" + COL_HOMEITEM_ID + "));";
		String CREATE_TB_HOME_ITEMS = "CREATE TABLE IF NOT EXISTS "
				+ TB_HomeItems + " (" + COL_HOMEITEM_ID
				+ " INTEGER PRIMARY KEY,"
				+ COL_HOMEITEM_INCLUDE_IMAGE_IN_LAYOUT + " VARCHAR,"
				+ COL_HOMEITEM_INCLUDE_TITLE_IN_LAYOUT + " VARCHAR,"
				+ COL_HOMEITEM_INCLUDE_TEXT_IN_LAYOUT + " VARCHAR,"
				+ COL_HOMEITEM_IMAGE_POSITION + " VARCHAR,"
				+ COL_HOMEITEM_TITLE_POSITION + " VARCHAR,"
				+ COL_HOMEITEM_TEXT_POSITION + " VARCHAR," + COL_HOMEITEM_TITLE
				+ " VARCHAR," + COL_HOMEITEM_TEXT + " VARCHAR,"
				+ COL_HOMEITEM_TEXT_HTML + " VARCHAR,"
				+ COL_HOMEITEM_TAB_POSITION + " INTEGER,"
				+ COL_HOMEITEM_TAB_TEXT + " VARCHAR," + COL_HOMEITEM_TAB_ICON
				+ " VARCHAR," + COL_HOMEITEM_DATE_CHANGED + " VARCHAR,"
				+ COL_HOMEITEM_IS_DIRTY + " VARCHAR,"
				+ COL_HOMEITEM_TEMP_UNIQUE_UID + " VARCHAR,"
				+ COL_HOMEITEM_TYPE + " INTEGER," + COL_HOMEITEM_USE_TAB_ICON
				+ " VARCHAR," + COL_HOMEITEM_SORT_POSITION + " INTEGER,"
				+ COL_HOMEITEM_ARCHIVED + " VARCHAR," + COL_HOMEITEM_LIST_ICON
				+ " VARCHAR);";
		db.execSQL(CREATE_TB_HOME_ITEMS);
		db.execSQL(CREATE_TB_HOMEITEMIMAGE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public SQLiteDatabase getDB() {
		sqlite = getWritableDatabase();
		return sqlite;
	}

	public ContentValues getContentValues() {
		contentValues = new ContentValues();

		return contentValues;
	}

	public void insertHomeItems(HomeItems model_HomeItems) {
		contentValues = getContentValues();
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
	}

	public void insertHomeItemImage(HomeItemIMAGE model_HomeItemImage) {

		contentValues = getContentValues();
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
	}

	public ArrayList<HomeItems> displayHomeItems() {
		arrayListHomeItems = new ArrayList<HomeItems>();
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
		return arrayListHomeItems;
	}

	/*
	 * public void insertHomeItems(int homeId, String includeImageInLayout,
	 * String includeTitleInLayout, String includeTextInLayout, String
	 * imagePosition, String titlePosition, String textPosition, String title,
	 * String text, String textHtml, int tabPosition, String tabText, String
	 * tabIcon, String dateChanged, String isDirty, String tempUniqueUID, int
	 * type, String useTabIcon, int sortPosition, String archived, String
	 * listIcon) { contentValues = getContentValues(); sqlite = getDB();
	 * contentValues.put(COL_HOMEITEM_ID, homeId);
	 * contentValues.put(COL_HOMEITEM_INCLUDE_IMAGE_IN_LAYOUT,
	 * includeImageInLayout);
	 * contentValues.put(COL_HOMEITEM_INCLUDE_TITLE_IN_LAYOUT,
	 * includeTitleInLayout);
	 * contentValues.put(COL_HOMEITEM_INCLUDE_TEXT_IN_LAYOUT,
	 * includeTextInLayout); contentValues.put(COL_HOMEITEM_IMAGE_POSITION,
	 * imagePosition); contentValues.put(COL_HOMEITEM_TITLE_POSITION,
	 * titlePosition); contentValues.put(COL_HOMEITEM_TEXT_POSITION,
	 * textPosition); contentValues.put(COL_HOMEITEM_TITLE, title);
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
	 * public void insertHomeItemImage(int imageId, int width, int height,
	 * String originalName, String locationLocal, String imageType, String
	 * baseURL, String mimeType, String base64Version, String isDirty, String
	 * archive, String name, int homeItemId) { contentValues =
	 * getContentValues(); sqlite = getDB(); contentValues.put(COL_IMAGE_ID,
	 * imageId); contentValues.put(COL_IMAGE_WIDTH, width);
	 * contentValues.put(COL_IMAGE_HEIGHT, height);
	 * contentValues.put(COL_IMAGE_ORIGINAL_NAME, originalName);
	 * contentValues.put(COL_IMAGE_LOCATION_LOCAL, locationLocal);
	 * contentValues.put(COL_IMAGE_TYPE, imageType);
	 * contentValues.put(COL_IMAGE_BASE_URL, baseURL);
	 * contentValues.put(COL_IMAGE_MIME_TYPE, mimeType);
	 * contentValues.put(COL_IMAGE_BASE64_VERSION, base64Version);
	 * contentValues.put(COL_IMAGE_IS_DIRTY, isDirty);
	 * contentValues.put(COL_IMAGE_ARCHIVED, archive);
	 * contentValues.put(COL_IMAGE_NAME, name);
	 * contentValues.put(COL_HOMEITEM_ID, homeItemId);
	 * sqlite.insert(TB_HomeItemsImage, null, contentValues); sqlite.close(); }
	 */

}
