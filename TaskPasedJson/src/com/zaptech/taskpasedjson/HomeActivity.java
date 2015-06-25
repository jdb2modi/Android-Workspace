package com.zaptech.taskpasedjson;

/*Bitmap bitmap = BitmapFactory.decodeStream(is);
 ImageView imageView = (ImageView) findViewById(R.id.image_view);
 imageView.setImageBitmap(bitmap);*/
//http://80.93.28.24/json/autoexpress.json

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends Activity implements OnClickListener {
	ProgressDialog mProgress;
	TextView txt_Json, txt_welcome;
	ListView listview_MenuItems;
	DBHelper dbHelper;

	// Model Objects..
	JSON model_JSON;
	HomeItems model_HomeItems;
	HomeItemIMAGE model_HomeItemImage;
	Model_NewsItem model_NewsItem;
	Model_NewsItem_Items model_NewsItem_Items;
	Model_NewsImage model_NewsImage;
	Model_Headline model_HeadLine;
	Model_Description model_Description;
	Model_DescriptionHMTL model_DescriptionHTML;
	Model_MenuItems model_MenuItems;

	// FOR Sliding Drawer..
	private SlidingDrawer drawer;
	private Button handle;
	ArrayAdapter<String> adptDataItems;
	ArrayList<Model_NewsItem_Items> arraylist_NewsItems_Items;
	ArrayList<Model_NewsItem> arraylist_NewsItems;

	// BASE ADAPTER PURPOSE..
	LayoutInflater inflater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();
		new GetData().execute();
		manageDrawer();

		listview_MenuItems.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String strTemp = String.valueOf(dbHelper.arrayListMenuItems
						.get(position).item_name);
				if (strTemp.equals("homeItems")) {

					finish();
					Intent intent;
					intent = new Intent(HomeActivity.this,
							Activity_HomeItems.class);
					startActivity(intent);
				}
				if (strTemp.equals("newsItems")) {

					finish();
					Intent intent2;
					intent2 = new Intent(HomeActivity.this,
							Activity_NewsItems.class);
					startActivity(intent2);

				}

			}
		});
	}

	public void init() {
		txt_welcome = (TextView) findViewById(R.id.txt_welcome);
		mProgress = new ProgressDialog(HomeActivity.this);

		listview_MenuItems = (ListView) findViewById(R.id.listview_MenuItems);
		// MODEL
		model_JSON = new JSON();
		model_HomeItems = new HomeItems();
		model_HomeItemImage = new HomeItemIMAGE();
		model_NewsItem = new Model_NewsItem();
		model_NewsItem_Items = new Model_NewsItem_Items();
		model_NewsImage = new Model_NewsImage();
		model_HeadLine = new Model_Headline();
		model_Description = new Model_Description();
		model_DescriptionHTML = new Model_DescriptionHMTL();
		model_MenuItems = new Model_MenuItems();

		// Database object..
		dbHelper = new DBHelper(HomeActivity.this);
		// dbHelper.getWritableDatabase();

		// TO Handle Sliding Drawer
		handle = (Button) findViewById(R.id.handle);
		handle.setOnClickListener(this);
		drawer = (SlidingDrawer) findViewById(R.id.slidingDrawer);
		adptDataItems = new ArrayAdapter<String>(HomeActivity.this,
				android.R.layout.simple_list_item_1, getResources()
						.getStringArray(R.array.arrayDataItems));
		listview_MenuItems.setAdapter(adptDataItems);
		arraylist_NewsItems_Items = new ArrayList<Model_NewsItem_Items>();
		arraylist_NewsItems = new ArrayList<Model_NewsItem>();
	}

	// //////////////////////////ASYNKTASK///////////////////////////////////
	class GetData extends AsyncTask<Void, Void, Void> {
		String str;

		@Override
		protected void onPreExecute() {
			mProgress.setTitle("Stream Loader");
			mProgress.setMessage("Please wait, Loading Stream...");
			mProgress.setCancelable(false);
			mProgress.show();
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {
			str = GET("http://80.93.28.24/json/autoexpress.json");
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (mProgress.isShowing()) {
				mProgress.dismiss();
			}

			if (str != null) {
				JsonParsing(str);
				getJsonKeys(str);
			} else {
				Toast.makeText(HomeActivity.this,
						"ERROR : Stream did not initialized.",
						Toast.LENGTH_LONG).show();
			}
			new DisplayMenuItemAsync().execute();
			super.onPostExecute(result);
		}
	}

	// ////////////////////////ASYNKTASK_COMPLETES///////////////////////////////////
	public String GET(String str) {
		InputStream inputstream;
		String strConverted = "";

		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse httpResponse = httpClient.execute(new HttpGet(str));
			inputstream = httpResponse.getEntity().getContent();
			strConverted = convertToString(inputstream);

		} catch (ClientProtocolException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return strConverted;
	}

	public String convertToString(InputStream inputstream) {
		String result = "";
		String line;
		BufferedReader bf = new BufferedReader(new InputStreamReader(
				inputstream));
		try {
			while ((line = bf.readLine()) != null) {
				result += line;
			}
		} catch (IOException e) {

			e.printStackTrace();
		}

		return result;
	}

	public void JsonParsing(String str) {
		try {

			JSONObject JSON = new JSONObject(str);

			// FOR JSON MODEL...
			model_JSON.setStrAppMetaData(JSON.getString("AppMetaData"));
			model_JSON.setStrVersionNumber(JSON.getString("versionNumber"));
			model_JSON.setStrTabVersionNumber(JSON
					.getString("tABVersionNumber"));
			model_JSON.setStrWebServiceURL(JSON.getString("webServicesURL"));
			model_JSON.setStrAppName(JSON.getString("appName"));

			// FOR HOME ITEMS...
			JSONArray arrayhomeItems = JSON.getJSONArray("homeItems");

			for (int i = 0; i < arrayhomeItems.length(); i++) {
				JSONObject HomeItemsSub = arrayhomeItems.getJSONObject(i);
				model_HomeItems.setHomeItem_id(HomeItemsSub.getString("id"));
				model_HomeItems.setHomeItem_includeImageInLayout(HomeItemsSub
						.getString("includeImageInLayout"));
				model_HomeItems.setHomeItem_includeTitleInLayout(HomeItemsSub
						.getString("includeTitleInLayout"));
				model_HomeItems.setHomeItem_includeTextInLayout(HomeItemsSub
						.getString("includeTextInLayout"));
				model_HomeItems.setHomeItem_imagePosition(HomeItemsSub
						.getString("imagePosition"));
				model_HomeItems.setHomeItem_titlePosition(HomeItemsSub
						.getString("titlePosition"));
				model_HomeItems.setHomeItem_textPosition(HomeItemsSub
						.getString("textPosition"));
				model_HomeItems.setHomeItem_title(HomeItemsSub
						.getString("title"));
				model_HomeItems
						.setHomeItem_text(HomeItemsSub.getString("text"));
				model_HomeItems.setHomeItem_textHTML(HomeItemsSub
						.getString("textHTML"));
				model_HomeItems.setHomeItem_id(HomeItemsSub.getString("id"));
				model_HomeItems.setHomeItem_tabPosition(HomeItemsSub
						.getString("tabPosition"));
				model_HomeItems.setHomeItem_tabText(HomeItemsSub
						.getString("tabText"));
				model_HomeItems.setHomeItem_tabIcon(HomeItemsSub
						.getString("tabIcon"));
				model_HomeItems.setHomeItem_dateChanged(HomeItemsSub
						.getString("dateChanged"));
				model_HomeItems.setHomeItem_isDirty(HomeItemsSub
						.getString("isDirty"));
				model_HomeItems.setHomeItem_tempUniqueUID(HomeItemsSub
						.getString("tempUniqueUID"));
				model_HomeItems
						.setHomeItem_Type(HomeItemsSub.getString("type"));
				model_HomeItems.setHomeItem_useTabIcon(HomeItemsSub
						.getString("useTabIcon"));
				model_HomeItems.setHomeItem_sortPosition(HomeItemsSub
						.getString("sortPosition"));
				model_HomeItems.setHomeItem_archived(HomeItemsSub
						.getString("archived"));
				model_HomeItems.setHomeItem_listIcon(HomeItemsSub
						.getString("listIcon"));

				// FOR HOME ITEM : IMAGE OBJECT
				JSONObject JObjHomeItemImage = HomeItemsSub
						.getJSONObject("image");
				model_HomeItemImage.setHomeItemImage_Id(JObjHomeItemImage
						.getString("Id"));
				model_HomeItemImage.setHomeItemImage_Width(JObjHomeItemImage
						.getString("width"));
				model_HomeItemImage.setHomeItemImage_Height(JObjHomeItemImage
						.getString("height"));
				model_HomeItemImage
						.setHomeItemImage_OriginalName(JObjHomeItemImage
								.getString("originalName"));
				model_HomeItemImage
						.setHomeItemImage_LocationLocal(JObjHomeItemImage
								.getString("locationLocal"));
				model_HomeItemImage.setHomeItemImage_Type(JObjHomeItemImage
						.getString("type"));
				model_HomeItemImage.setHomeItemImage_BaseURL(JObjHomeItemImage
						.getString("baseURL"));
				model_HomeItemImage.setHomeItemImage_mimeType(JObjHomeItemImage
						.getString("mimeType"));
				model_HomeItemImage
						.setHomeItemImage__Base64Version(JObjHomeItemImage
								.getString("base64Version"));
				model_HomeItemImage.setHomeItemImage_IsDirty(JObjHomeItemImage
						.getString("isDirty"));
				model_HomeItemImage.setHomeItemImage_Archived(JObjHomeItemImage
						.getString("archived"));
				model_HomeItemImage.setHomeItemImage_Id(JObjHomeItemImage
						.getString("Id"));
				model_HomeItemImage
						.setHomeItem_Id(HomeItemsSub.getString("id"));
				model_HomeItemImage.setHomeItemImage_Name(JObjHomeItemImage
						.getString("name"));
				insertHomeItmes();
				insertHomeItemsImage();

			}

			// Parsing for NewsItem...

			JSONArray arrayNewsItems = JSON.getJSONArray("newsItems");

			System.err.println("+++++++++++++++" + arrayNewsItems.length());
			for (int j = 0; j < arrayNewsItems.length(); j++) {
				JSONObject jNewsItemSub = arrayNewsItems.getJSONObject(j);

				model_NewsItem.setVideos(jNewsItemSub.getString("videos"));

				System.err.println("----------------------------------"
						+ jNewsItemSub.getString("id"));

				model_NewsItem.setSortType(jNewsItemSub.getString("sortType"));

				model_NewsItem.setSharePointURL(jNewsItemSub
						.getString("SharepointUrl"));

				model_NewsItem.setDisplayAsGantt(jNewsItemSub
						.getString("DisplayAsGantt"));

				model_NewsItem.setId(Integer.parseInt(jNewsItemSub
						.getString("id")));

				model_NewsItem.setTabPosition(jNewsItemSub
						.getString("tabPosition"));

				/*
				 * System.err.println("/////////////////////////////////" +
				 * jNewsItemSub.getString("tabPosition"));
				 */
				model_NewsItem.setTabText(jNewsItemSub.getString("tabText"));
				model_NewsItem.setTabIcon(jNewsItemSub.getString("tabIcon"));
				model_NewsItem.setDateChanged(jNewsItemSub
						.getString("dateChanged"));
				model_NewsItem.setIsDirty(jNewsItemSub.getString("isDirty"));
				model_NewsItem.setTempUniqueUID(jNewsItemSub
						.getString("tempUniqueUID"));
				model_NewsItem.setType(jNewsItemSub.getString("type"));
				model_NewsItem.setUseTabIcon(jNewsItemSub
						.getString("useTabIcon"));
				model_NewsItem.setSortPosition(jNewsItemSub
						.getString("sortPosition"));
				model_NewsItem.setArchived(jNewsItemSub.getString("archived"));
				model_NewsItem.setListIcon(jNewsItemSub.getString("listIcon"));

				// INSERTING VALUES...
				insertNewsItems();

				// Parsing items in NewsITEM
				JSONArray arrayJItems = jNewsItemSub.optJSONArray("items");
				for (int k = 0; k < arrayJItems.length(); k++) {

					JSONObject jObjItems = arrayJItems.getJSONObject(k);
					model_NewsItem_Items.setNewsItems_ItemId(Integer
							.parseInt(jObjItems.getString("id")));
					model_NewsItem_Items.setNewsItemId(Integer
							.parseInt(jNewsItemSub.getString("id")));
					model_NewsItem_Items.setUrl(jObjItems.getString("url"));

					model_NewsItem_Items.setDatePublished(jObjItems
							.getString("datePublished"));
					model_NewsItem_Items.setDateChanged(jObjItems
							.getString("dateChanged"));
					model_NewsItem_Items.setIsDirty(jObjItems
							.getString("isDirty"));
					model_NewsItem_Items.setEventFlag(jObjItems
							.getString("eventFlag"));
					model_NewsItem_Items.setEventDate(jObjItems
							.getString("eventDate"));
					model_NewsItem_Items.setPublishToFacebook(jObjItems
							.getString("publishToFacebook"));
					model_NewsItem_Items.setTempUniqueUID(jObjItems
							.getString("tempUniqueUID"));
					model_NewsItem_Items.setEventDateFinish(jObjItems
							.getString("EventDateFinish"));
					model_NewsItem_Items.setSortPosition(jObjItems
							.getString("sortPosition"));
					model_NewsItem_Items.setArchived(jObjItems
							.getString("archived"));
					model_NewsItem_Items.setListIcon(jObjItems
							.getString("listIcon"));

					insertNewsItems_Items();
					// Parsing for NewsImage in NewsItems
					JSONObject jObjNewsImage = jObjItems
							.getJSONObject("newsImage");
					model_NewsImage.setWidth(Integer.parseInt(jObjNewsImage
							.getString("width")));
					model_NewsImage.setHeight(Integer.parseInt(jObjNewsImage
							.getString("height")));
					model_NewsImage.setOriginalName(jObjNewsImage
							.getString("originalName"));
					model_NewsImage.setLocationLocal(jObjNewsImage
							.getString("locationLocal"));
					model_NewsImage.setType(jObjNewsImage.getString("type"));
					model_NewsImage.setBaseURL(jObjNewsImage
							.getString("baseURL"));
					model_NewsImage.setMimeType(jObjNewsImage
							.getString("mimeType"));
					model_NewsImage.setBase64Version(jObjNewsImage
							.getString("base64Version"));
					model_NewsImage.setIsDirty(jObjNewsImage
							.getString("isDirty"));
					model_NewsImage.setArchived(jObjNewsImage
							.getString("archived"));
					model_NewsImage.setNewsImageId(Integer
							.parseInt(jObjNewsImage.getString("Id")));
					model_NewsImage.setName(jObjNewsImage.getString("name"));
					insertNewsImages();
					// /Parsing HeadLine...
					JSONObject jObjHeadLine = jObjItems
							.getJSONObject("headline");
					model_HeadLine.setTheString(jObjHeadLine
							.getString("theString"));
					model_NewsItem_Items.setModel_headline(model_HeadLine);
					insertHeadLine();

					// Parsing Description...
					JSONObject jObjDescription = jObjItems
							.getJSONObject("description");
					model_Description.setTheString(jObjDescription
							.getString("theString"));
					model_NewsItem_Items
							.setModel_description(model_Description);
					insertDescription();
					// Parsing DescriptionHTML
					JSONObject jObjDescriptionHTML = jObjItems
							.getJSONObject("descriptionHTML");
					model_DescriptionHTML.setTheString(Html.fromHtml(
							jObjDescriptionHTML.getString("theString"))
							.toString());
					model_NewsItem_Items
							.setModel_descriptionHTML(model_DescriptionHTML);
					insertDescriptionHTML();
				}

			}

		} catch (JSONException e) {

			e.printStackTrace();
		}

	}

	// ///////////////////INSERT PANEL HERE/////////////////////
	public void getJsonKeys(String str) {

		try {
			JSONObject JsonObj = new JSONObject(str);
			Iterator iterator = JsonObj.keys();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();

				model_MenuItems.setItem_name(key);
				dbHelper.insertMenuItems(model_MenuItems);

			}
		} catch (Exception e) {

		}

	}

	public void insertHomeItmes() {
		dbHelper.insertHomeItems(model_HomeItems);

	}

	public void insertHomeItemsImage() {
		dbHelper.insertHomeItemImage(model_HomeItemImage);

	}

	public void insertNewsItems() {
		dbHelper.insertNewsItems(model_NewsItem);

	}

	public void insertNewsItems_Items() {
		dbHelper.insertNewsItem_Items(model_NewsItem_Items, model_NewsItem);

	}

	public void insertNewsImages() {
		dbHelper.insertNewsImages(model_NewsImage, model_NewsItem_Items);

	}

	public void insertHeadLine() {
		dbHelper.insertHeadlines(model_HeadLine, model_NewsItem_Items);

	}

	public void insertDescription() {
		dbHelper.insertDescription(model_Description, model_NewsItem_Items);

	}

	public void insertDescriptionHTML() {
		dbHelper.insertDescriptionHtml(model_DescriptionHTML,
				model_NewsItem_Items);

	}

	// /////////////////////INSERT_PANEL_COMPLETE_HERE/////////////////////////

	// /////////////////////MANAGE_DRAWER_PANEL////////////////////
	public void manageDrawer() {
		drawer.setOnDrawerOpenListener(new OnDrawerOpenListener() {

			@Override
			public void onDrawerOpened() {
				handle.setText("-");

			}
		});
		drawer.setOnDrawerCloseListener(new OnDrawerCloseListener() {

			@Override
			public void onDrawerClosed() {
				handle.setText("+");

			}
		});

	}

	// /////////////////MANAGE_DRAWER_PANEL_COMPLETES////////////////////
	class DisplayMenuItems extends BaseAdapter {
		Context context;

		public DisplayMenuItems(Context context) {
			this.context = context;
		}

		@Override
		public int getCount() {

			return dbHelper.arrayListMenuItems.size();
		}

		@Override
		public Object getItem(int position) {

			return dbHelper.arrayListMenuItems.get(position);
		}

		@Override
		public long getItemId(int position) {

			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (inflater == null) {
				inflater = (LayoutInflater) context
						.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			}
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.custom_menuitems, null);
			}
			TextView txt_MenuItem = (TextView) convertView
					.findViewById(R.id.txt_MenuItem);
			txt_MenuItem.setText(dbHelper.arrayListMenuItems.get(position)
					.getItem_name().toString());
			return convertView;
		}

	}

	class DisplayMenuItemAsync extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			mProgress.setTitle("Menu Loader");
			mProgress.setMessage("Please wait, Loading Menu items...");
			mProgress.setCancelable(false);
			mProgress.show();
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {
			dbHelper.displayMenuItems();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (mProgress.isShowing()) {
				mProgress.dismiss();
			}
			listview_MenuItems.setAdapter(new DisplayMenuItems(
					HomeActivity.this));

			super.onPostExecute(result);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.handle:
			if (txt_welcome.getText().equals("+")) {
				txt_welcome.setText("");
			}
			if (txt_welcome.getText().equals("-")) {
				//txt_welcome.setVisibility(1);
			}
			break;

		default:
			break;
		}

	}

}

/*
 * public void insertHomeItmes() { dbHelper.insertHomeItems(
 * Integer.parseInt(model_HomeItems.getHomeItem_id()),
 * model_HomeItems.getHomeItem_includeImageInLayout(),
 * model_HomeItems.getHomeItem_includeTitleInLayout(),
 * model_HomeItems.getHomeItem_includeTextInLayout(),
 * model_HomeItems.getHomeItem_imagePosition(),
 * model_HomeItems.getHomeItem_titlePosition(),
 * model_HomeItems.getHomeItem_textPosition(),
 * model_HomeItems.getHomeItem_title(), model_HomeItems.getHomeItem_text(),
 * model_HomeItems.getHomeItem_textHTML(),
 * Integer.parseInt(model_HomeItems.getHomeItem_tabPosition()),
 * model_HomeItems.getHomeItem_tabText(), model_HomeItems.getHomeItem_tabIcon(),
 * model_HomeItems.getHomeItem_dateChanged(),
 * model_HomeItems.getHomeItem_isDirty(),
 * model_HomeItems.getHomeItem_tempUniqueUID(),
 * Integer.parseInt(model_HomeItems.getHomeItem_Type()),
 * model_HomeItems.getHomeItem_useTabIcon(),
 * Integer.parseInt(model_HomeItems.getHomeItem_sortPosition()),
 * model_HomeItems.getHomeItem_archived(),
 * model_HomeItems.getHomeItem_listIcon()); Toast.makeText(HomeActivity.this,
 * "Records Inserted..!", Toast.LENGTH_SHORT).show(); }
 */

/*
 * public void insertHomeItemsImage() { dbHelper.insertHomeItemImage(
 * Integer.parseInt(model_HomeItemImage.getHomeItemImage_Id()),
 * Integer.parseInt(model_HomeItemImage.getHomeItemImage_Width()),
 * Integer.parseInt(model_HomeItemImage.getHomeItemImage_Height()),
 * model_HomeItemImage.getHomeItemImage_OriginalName(),
 * model_HomeItemImage.getHomeItemImage_LocationLocal(),
 * model_HomeItemImage.getHomeItemImage_Type(),
 * model_HomeItemImage.getHomeItemImage_BaseURL(),
 * model_HomeItemImage.getHomeItemImage_mimeType(),
 * model_HomeItemImage.getHomeItemImage__Base64Version(),
 * model_HomeItemImage.getHomeItemImage_IsDirty(),
 * model_HomeItemImage.getHomeItemImage_Archived(),
 * model_HomeItemImage.getHomeItemImage_Name(),
 * Integer.parseInt(model_HomeItems.getHomeItem_id()));
 * Toast.makeText(HomeActivity.this, "Images Records Inserted..!",
 * Toast.LENGTH_SHORT).show(); }
 */

