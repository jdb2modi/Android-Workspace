package com.zaptech.jsontask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayhomeItem extends Activity implements OnItemClickListener {

	ListView listTitle;

	ProgressDialog progress_Data;

	RootModel rootModel;

	MyDatabaseHelper myData;

	HomeItems modelHomeItems;

	HomeItemIMAGE modelHomeImage;

	customAdapter adapter;

	public static LayoutInflater inflater;

	ArrayList<HomeItems> homeItems;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_displayhome_item);
		init();
		new parseJsonData().execute();
		listTitle.setClickable(true);
		listTitle.setOnItemClickListener(this);
	}

	public void init() {
		myData = new MyDatabaseHelper(DisplayhomeItem.this);

		try {
			myData.createDataBase();
		} catch (IOException e) {
			e.printStackTrace();
		}

		listTitle = (ListView) findViewById(R.id.listViewTitle);

		progress_Data = new ProgressDialog(DisplayhomeItem.this);

		rootModel = new RootModel();

		modelHomeItems = new HomeItems();

		modelHomeImage = new HomeItemIMAGE();
	}

	class parseJsonData extends AsyncTask<Void, Void, Void> {

		String str_Get = "";

		@Override
		protected void onPreExecute() {
			progress_Data.setTitle("Get Json String");
			progress_Data.setMessage("Loading Data To Get The String");
			progress_Data.setCancelable(false);
			progress_Data.show();
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {
			str_Get = getString("http://80.93.28.24/json/autoexpress.json");
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (progress_Data.isShowing()) {
				progress_Data.dismiss();
			}
			parseString(str_Get);
			homeItems = myData.displayHomeItem();
			adapter = new customAdapter(DisplayhomeItem.this, homeItems);
			listTitle.setAdapter(adapter);
			super.onPostExecute(result);
		}
	}

	@SuppressLint("InflateParams")
	class customAdapter extends BaseAdapter {

		Context context;

		ArrayList<HomeItems> homeItems;

		public customAdapter(Context context, ArrayList<HomeItems> homeItems) {
			this.context = context;
			this.homeItems = homeItems;
		}

		@Override
		public int getCount() {
			return homeItems.size();
		}

		@Override
		public Object getItem(int position) {
			return homeItems.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (inflater == null) {
				inflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			}
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.list_title, null);
			}
			TextView txt_Title = (TextView) convertView
					.findViewById(R.id.textViewTitle);
			TextView txt_Id = (TextView) convertView
					.findViewById(R.id.textViewId);
			txt_Title.setText(homeItems.get(position).getHomeItem_title());
			txt_Id.setText(homeItems.get(position).getHomeItem_id());
			return convertView;
		}

	}

	public String getString(String string) {
		String str_Result = "";
		InputStream inputStream = null;
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse httpResponse = httpClient.execute(new HttpGet(string));
			inputStream = httpResponse.getEntity().getContent();
			if (inputStream != null) {
				str_Result = convertInputStreamToString(inputStream);
			} else {
				str_Result = "String Not Found";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return str_Result;
	}

	public String convertInputStreamToString(InputStream inputStream) {
		String str_Line = "";
		String str_Result = "";
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(
				inputStream));
		try {
			while ((str_Line = bufferReader.readLine()) != null) {
				str_Result += str_Line;
				inputStream.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str_Result;
	}

	public void parseString(String str_Get) {
		try {
			JSONObject rootObj = new JSONObject(str_Get);
			rootModel.setStr_AppMetaData(rootObj.getString("AppMetaData"));

			JSONArray homeItems = new JSONArray(rootObj.getString("homeItems"));

			for (int i = 0; i < homeItems.length(); i++) {

				JSONObject subHomeItem = new JSONObject(homeItems.getString(i));
				modelHomeItems.setHomeItem_includeImageInLayout(subHomeItem
						.getString("includeImageInLayout"));
				modelHomeItems.setHomeItem_includeTitleInLayout(subHomeItem
						.getString("includeTitleInLayout"));
				modelHomeItems.setHomeItem_includeTextInLayout(subHomeItem
						.getString("includeTextInLayout"));
				modelHomeItems.setHomeItem_imagePosition(subHomeItem
						.getString("imagePosition"));
				modelHomeItems.setHomeItem_titlePosition(subHomeItem
						.getString("titlePosition"));
				modelHomeItems.setHomeItem_textPosition(subHomeItem
						.getString("textPosition"));
				modelHomeItems
						.setHomeItem_title(subHomeItem.getString("title"));
				modelHomeItems.setHomeItem_text(subHomeItem.getString("text"));
				modelHomeItems.setHomeItem_textHTML(Html.fromHtml(
						subHomeItem.getString("textHTML")).toString());
				modelHomeItems.setHomeItem_id(subHomeItem.getString("id"));
				modelHomeItems.setHomeItem_tabPosition(subHomeItem
						.getString("tabPosition"));
				modelHomeItems.setHomeItem_tabText(subHomeItem
						.getString("tabText"));
				modelHomeItems.setHomeItem_tabIcon(subHomeItem
						.getString("tabIcon"));
				modelHomeItems.setHomeItem_dateChanged(subHomeItem
						.getString("dateChanged"));
				modelHomeItems.setHomeItem_isDirty(subHomeItem
						.getString("isDirty"));
				modelHomeItems.setHomeItem_tempUniqueUID(subHomeItem
						.getString("tempUniqueUID"));
				modelHomeItems.setHomeItem_Type(subHomeItem.getString("type"));
				modelHomeItems.setHomeItem_useTabIcon(subHomeItem
						.getString("useTabIcon"));
				modelHomeItems.setHomeItem_sortPosition(subHomeItem
						.getString("sortPosition"));
				modelHomeItems.setHomeItem_archived(subHomeItem
						.getString("archived"));
				modelHomeItems.setHomeItem_listIcon(subHomeItem
						.getString("listIcon"));

				JSONObject subHomeImage = new JSONObject(
						subHomeItem.getString("image"));
				modelHomeImage.setHomeItemImage_Width(subHomeImage
						.getString("width"));
				modelHomeImage.setHomeItemImage_Height(subHomeImage
						.getString("height"));
				modelHomeImage.setHomeItemImage_OriginalName(subHomeImage
						.getString("originalName"));
				modelHomeImage.setHomeItemImage_LocationLocal(subHomeImage
						.getString("locationLocal"));
				modelHomeImage.setHomeItemImage_Type(subHomeImage
						.getString("type"));
				modelHomeImage.setHomeItemImage_BaseURL(subHomeImage
						.getString("baseURL"));
				modelHomeImage.setHomeItemImage_mimeType(subHomeImage
						.getString("mimeType"));
				modelHomeImage.setHomeItemImage__Base64Version(subHomeImage
						.getString("base64Version"));
				modelHomeImage.setHomeItemImage_IsDirty(subHomeImage
						.getString("isDirty"));
				modelHomeImage.setHomeItemImage_Archived(subHomeImage
						.getString("archived"));
				modelHomeImage
						.setHomeItemImage_Id(subHomeImage.getString("Id"));
				modelHomeImage.setHomeItemImage_Name(subHomeImage
						.getString("name"));
				modelHomeImage.setHomeItemImage_HomeItemId(subHomeItem
						.getString("id"));

				insertInHomeItem();
				insertInHomeItemImage();

			}
			Toast.makeText(DisplayhomeItem.this,
					"Record Inserted Successfully.......", Toast.LENGTH_SHORT)
					.show();
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	public void insertInHomeItemImage() {
		myData.insertHomeItemImage(modelHomeImage);
	}

	public void insertInHomeItem() {
		myData.insertHomeItems(modelHomeItems);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		modelHomeItems = (HomeItems) adapter.getItem(position);
		switch (position) {
		case 0:
			Intent first = new Intent(DisplayhomeItem.this,
					DisplayFirstTitle.class);
			first.putExtra("title", modelHomeItems.getHomeItem_title());
			first.putExtra("id", modelHomeItems.getHomeItem_id());
			startActivity(first);
			break;

		case 1:
			Intent second = new Intent(DisplayhomeItem.this,
					DisplayFirstTitle.class);
			second.putExtra("title", modelHomeItems.getHomeItem_title());
			second.putExtra("id", modelHomeItems.getHomeItem_id());
			startActivity(second);
			break;

		case 2:
			Intent third = new Intent(DisplayhomeItem.this,
					DisplayFirstTitle.class);
			third.putExtra("title", modelHomeItems.getHomeItem_title());
			third.putExtra("id", modelHomeItems.getHomeItem_id());
			startActivity(third);
			break;

		case 3:
			Intent fourth = new Intent(DisplayhomeItem.this,
					DisplayFirstTitle.class);
			fourth.putExtra("title", modelHomeItems.getHomeItem_title());
			fourth.putExtra("id", modelHomeItems.getHomeItem_id());
			startActivity(fourth);
			break;

		case 4:
			Intent fifth = new Intent(DisplayhomeItem.this,
					DisplayFirstTitle.class);
			fifth.putExtra("title", modelHomeItems.getHomeItem_title());
			fifth.putExtra("id", modelHomeItems.getHomeItem_id());
			startActivity(fifth);
			break;

		case 5:
			Intent sixth = new Intent(DisplayhomeItem.this,
					DisplayFirstTitle.class);
			sixth.putExtra("title", modelHomeItems.getHomeItem_title());
			sixth.putExtra("id", modelHomeItems.getHomeItem_id());
			startActivity(sixth);
			break;

		default:
			break;
		}
	}
}
