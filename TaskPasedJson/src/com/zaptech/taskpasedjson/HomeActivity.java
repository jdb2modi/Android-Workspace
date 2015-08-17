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
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zaptech.taskpasedjson.database.DBHelper;
import com.zaptech.taskpasedjson.models.HomeItemIMAGE;
import com.zaptech.taskpasedjson.models.HomeItems;
import com.zaptech.taskpasedjson.models.JSON;
import com.zaptech.taskpasedjson.models.Model_Description;
import com.zaptech.taskpasedjson.models.Model_DescriptionHMTL;
import com.zaptech.taskpasedjson.models.Model_Headline;
import com.zaptech.taskpasedjson.models.Model_MenuItems;
import com.zaptech.taskpasedjson.models.Model_NewsImage;
import com.zaptech.taskpasedjson.models.Model_NewsItem;
import com.zaptech.taskpasedjson.models.Model_NewsItem_Items;

public class HomeActivity extends Activity implements OnClickListener,
		OnItemClickListener {
	ProgressDialog mProgress;
	TextView txt_Json, txt_welcome;
	ListView drawer;
	DBHelper dbHelper;
	SharedPreferences shred;
	DrawerLayout mydrawer;// This is that
	ImageView imgBtnList;
	ArrayList<Model_MenuItems> list_MenuItems;

	int parsed = 0;

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

	private Button handle;

	// ArrayLists
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

		if (shred.contains("PARSED")) {
			Editor editor = shred.edit();
			editor.putInt("PARSED", 1);
			editor.commit();

		} else {
			new GetData().execute();
		}

	}

	public void init() {

		drawer = (ListView) findViewById(R.id.listviewMenuItem);// This is that
		mydrawer = (DrawerLayout) findViewById(R.id.drawer_layout);// This is
																	// that
		imgBtnList = (ImageView) findViewById(R.id.imgBtn_list);// This is that
		imgBtnList.setOnClickListener(this);// This is that
		drawer.setOnItemClickListener(HomeActivity.this);// This is that
		list_MenuItems = new ArrayList<Model_MenuItems>();

		mProgress = new ProgressDialog(HomeActivity.this);

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

		adptDataItems = new ArrayAdapter<String>(HomeActivity.this,
				android.R.layout.simple_list_item_1, getResources()
						.getStringArray(R.array.arrayDataItems));

		arraylist_NewsItems_Items = new ArrayList<Model_NewsItem_Items>();
		arraylist_NewsItems = new ArrayList<Model_NewsItem>();

		shred = getSharedPreferences("Parsed", MODE_PRIVATE);// CHECK FOR
																// PARSING DONE
	}

	// //////////////////ASYNKTASK TO GET JSON STREAM///////////////////////////
	class GetData extends AsyncTask<Void, Void, Void> {
		String str;

		@Override
		protected void onPreExecute() {

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
				// /IF Parsing completes

				JsonParsing(str);
				getJsonKeys(str);
				list_MenuItems = dbHelper.displayMenuItems();
				drawer.setAdapter(new MenuItem_Adapter(HomeActivity.this));

			} else {
				Toast.makeText(HomeActivity.this,
						"ERROR : Stream did not initialized.",
						Toast.LENGTH_LONG).show();
			}

			super.onPostExecute(result);
		}
	}

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

	// ////PARSING is going to be DONE HERE...
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

					// /INSERTING...
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

					// INSERTING...
					insertNewsImages();

					// /Parsing HeadLine...
					JSONObject jObjHeadLine = jObjItems
							.getJSONObject("headline");
					model_HeadLine.setTheString(jObjHeadLine
							.getString("theString"));
					model_NewsItem_Items.setModel_headline(model_HeadLine);

					// INSERTING...
					insertHeadLine();

					// Parsing Description...
					JSONObject jObjDescription = jObjItems
							.getJSONObject("description");
					model_Description.setTheString(jObjDescription
							.getString("theString"));
					model_NewsItem_Items
							.setModel_description(model_Description);

					// INSERTING..
					insertDescription();

					// Parsing DescriptionHTML
					JSONObject jObjDescriptionHTML = jObjItems
							.getJSONObject("descriptionHTML");
					model_DescriptionHTML.setTheString(Html.fromHtml(
							jObjDescriptionHTML.getString("theString"))
							.toString());
					model_NewsItem_Items
							.setModel_descriptionHTML(model_DescriptionHTML);
					// INSERTING..
					insertDescriptionHTML();
				}

			}

		} catch (JSONException e) {

			e.printStackTrace();
		}
		parsed = 1;
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

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		String selected = list_MenuItems.get(position).getItem_name();

		if (selected.equalsIgnoreCase("homeItems")) {
			Intent intent_home = new Intent(HomeActivity.this,
					Activity_HomeItems.class);
			startActivity(intent_home);
		} else if (selected.equalsIgnoreCase("newsItems")) {
			Intent intent_login = new Intent(HomeActivity.this,
					Activity_NewsItems.class);
			startActivity(intent_login);
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imgBtn_list:// ////////THIS IS THAT
			mydrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

			if (mydrawer.isDrawerOpen(drawer)) {
				Animation animation1 = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.clockwise);

				imgBtnList.startAnimation(animation1);

				mydrawer.closeDrawer(drawer);
				imgBtnList.setBackgroundResource(R.drawable.ic_list);

			} else {
				Animation animation2 = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.clockwise);
				imgBtnList.startAnimation(animation2);
				mydrawer.openDrawer(drawer);
				imgBtnList.setBackgroundResource(R.drawable.img_back);
			}

			break;

		default:
			break;
		}

	}

	// /THIS IS THAT
	public class MenuItem_Adapter extends BaseAdapter {
		Context context;

		public MenuItem_Adapter(Context context) {
			this.context = context;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list_MenuItems.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list_MenuItems.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub

			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.custom_menuitems, null);

			TextView tv_MenuItem = (TextView) convertView
					.findViewById(R.id.txt_MenuItem);

			tv_MenuItem.setText(""
					+ list_MenuItems.get(position).getItem_name());

			return convertView;
		}

	}
}
