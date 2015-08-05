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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DisplayNewsItem extends Activity implements OnItemClickListener {

	ProgressDialog progress_data;

	ModelNewsItem modelNewsItem;

	MyDatabaseHelper myData;

	NewsItem newsItem;

	NewsImage newsImage;

	NewsHeadLine newsHeadline;

	NewsDescription newsDescription;

	NewsDescriptionHTML newsDescriptionHTML;

	ListView list_Data;

	ArrayList<ModelNewsItem> main_List;

	customAdapter adapter;

	LayoutInflater inflater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_news_item);
		init();
		new parseJsonData().execute();
		list_Data.setOnItemClickListener(this);
	}

	private void init() {
		progress_data = new ProgressDialog(DisplayNewsItem.this);

		modelNewsItem = new ModelNewsItem();

		newsItem = new NewsItem();

		newsImage = new NewsImage();

		newsHeadline = new NewsHeadLine();

		newsDescription = new NewsDescription();

		newsDescriptionHTML = new NewsDescriptionHTML();
		myData = new MyDatabaseHelper(DisplayNewsItem.this);

		try {
			myData.createDataBase();
		} catch (IOException e) {
			e.printStackTrace();
		}

		list_Data = (ListView) findViewById(R.id.listViewTitle);

		main_List = new ArrayList<ModelNewsItem>();
	}

	class parseJsonData extends AsyncTask<Void, Void, Void> {

		String str_Get = "";

		@Override
		protected void onPreExecute() {
			progress_data.setTitle("Get Json String");
			progress_data.setMessage("Loading Data To Get The String");
			progress_data.setCancelable(false);
			progress_data.show();
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {
			str_Get = getString("http://80.93.28.24/json/autoexpress.json");
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (progress_data.isShowing()) {
				progress_data.dismiss();
			}
			parseString(str_Get);
			main_List = myData.displayNewsItem();
			adapter = new customAdapter(DisplayNewsItem.this, main_List);
			list_Data.setAdapter(adapter);
			super.onPostExecute(result);
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

			JSONArray mainNewsItem = new JSONArray(
					rootObj.getString("newsItems"));

			for (int i = 0; i < mainNewsItem.length(); i++) {

				JSONObject subNewsItem = new JSONObject(
						mainNewsItem.getString(i));
				modelNewsItem.setVideos(subNewsItem.getString("videos"));
				modelNewsItem.setSortType(subNewsItem.getString("sortType"));
				modelNewsItem.setSharePointURL(subNewsItem
						.getString("SharepointUrl"));
				modelNewsItem.setDisplayAsGantt(subNewsItem
						.getString("DisplayAsGantt"));
				modelNewsItem.setNewsItemId(Integer.parseInt(subNewsItem
						.getString("id")));
				modelNewsItem.setTabPosition(subNewsItem
						.getString("tabPosition"));
				modelNewsItem.setTabText(subNewsItem.getString("tabText"));
				modelNewsItem.setTabIcon(subNewsItem.getString("tabIcon"));
				modelNewsItem.setDateChanged(subNewsItem
						.getString("dateChanged"));
				modelNewsItem.setIsDirty(subNewsItem.getString("isDirty"));
				modelNewsItem.setTempUniqueUID(subNewsItem
						.getString("tempUniqueUID"));
				modelNewsItem.setType(subNewsItem.getString("type"));
				modelNewsItem
						.setUseTabIcon(subNewsItem.getString("useTabIcon"));
				modelNewsItem.setSortPosition(subNewsItem
						.getString("sortPosition"));
				modelNewsItem.setArchived(subNewsItem.getString("archived"));
				modelNewsItem.setListIcon(subNewsItem.getString("listIcon"));

				insertInMainNewsItem();

				JSONArray itemObj = new JSONArray(
						subNewsItem.getString("items"));

				for (int j = 0; j < itemObj.length(); j++) {
					JSONObject subItemObj = new JSONObject(itemObj.getString(j));
					newsItem.setNewsItemId(Integer.parseInt(subNewsItem
							.getString("id")));
					newsItem.setItemId(Integer.parseInt(subItemObj
							.getString("id")));
					newsItem.setURL(subItemObj.getString("url"));
					newsItem.setDatePublished(subItemObj
							.getString("datePublished"));
					newsItem.setDateChanged(subItemObj.getString("dateChanged"));
					newsItem.setIsDirty(subItemObj.getString("isDirty"));
					newsItem.setEventFlag(subItemObj.getString("eventFlag"));
					newsItem.setEventDate(subItemObj.getString("eventDate"));
					newsItem.setPublishToFacebook(subItemObj
							.getString("publishToFacebook"));
					newsItem.setTempUniqueUID(subItemObj
							.getString("tempUniqueUID"));
					newsItem.setEventDateFinish(subItemObj
							.getString("EventDateFinish"));
					newsItem.setSortPosition(subItemObj
							.getString("sortPosition"));
					newsItem.setArchived(subItemObj.getString("archived"));
					newsItem.setListIcon(subItemObj.getString("listIcon"));
					insertInNewsItem();

					JSONObject imageObj = new JSONObject(
							subItemObj.getString("newsImage"));
					newsImage.setItemId(Integer.parseInt(subItemObj
							.getString("id")));
					newsImage.setWidth(imageObj.getString("width"));
					newsImage.setHeight(imageObj.getString("height"));
					newsImage.setOriginalName(imageObj
							.getString("originalName"));
					newsImage.setLocationLocal(imageObj
							.getString("locationLocal"));
					newsImage.setType(imageObj.getString("type"));
					newsImage.setBaseURL(imageObj.getString("baseURL"));
					newsImage.setMimeType(imageObj.getString("mimeType"));
					newsImage.setBase64Version(imageObj
							.getString("base64Version"));
					newsImage.setIsDirty(imageObj.getString("isDirty"));
					newsImage.setArchived(imageObj.getString("archived"));
					newsImage.setImageId(Integer.parseInt(imageObj
							.getString("Id")));
					newsImage.setName(imageObj.getString("name"));
					insertInNewsImage();

					JSONObject headlineObj = new JSONObject(
							subItemObj.getString("headline"));
					newsHeadline.setItemId(Integer.parseInt(subItemObj
							.getString("id")));
					newsHeadline.setTheString(headlineObj
							.getString("theString"));
					insertInNewsHeadLine();

					JSONObject descriptionObj = new JSONObject(
							subItemObj.getString("description"));
					newsDescription.setItemId(Integer.parseInt(subItemObj
							.getString("id")));
					newsDescription.setTheString(descriptionObj
							.getString("theString"));
					insertInNewsDescription();

					JSONObject descriptionHTMLObj = new JSONObject(
							subItemObj.getString("descriptionHTML"));
					newsDescriptionHTML.setItemId(Integer.parseInt(subItemObj
							.getString("id")));
					newsDescriptionHTML.setTheString(descriptionHTMLObj
							.getString("theString"));
					insertInNewsDescriptionHTML();
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void insertInNewsDescriptionHTML() {
		myData.insertNewsDescriptionHTML(newsDescriptionHTML);
	}

	public void insertInNewsDescription() {
		myData.insertNewsDescription(newsDescription);
	}

	public void insertInNewsHeadLine() {
		myData.insertNewsHeadLine(newsHeadline);
	}

	public void insertInNewsImage() {
		myData.insertNewsImage(newsImage);
	}

	private void insertInNewsItem() {
		myData.insertNewsItem(newsItem);
	}

	public void insertInMainNewsItem() {
		myData.insertMainNewsItem(modelNewsItem);
	}

	class customAdapter extends BaseAdapter {

		Context context;

		ArrayList<ModelNewsItem> array_List;

		public customAdapter(Context context,
				ArrayList<ModelNewsItem> array_List) {
			this.context = context;
			this.array_List = array_List;
		}

		@Override
		public int getCount() {
			return array_List.size();
		}

		@Override
		public Object getItem(int position) {
			return array_List.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@SuppressLint("InflateParams")
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (inflater == null) {
				inflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			}
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.list_news_title, null);
			}
			TextView txt_Title = (TextView) convertView
					.findViewById(R.id.textViewTitle);
			TextView txt_Id = (TextView) convertView
					.findViewById(R.id.textViewId);
			txt_Title.setText(array_List.get(position).getTabText());
			txt_Id.setText(String.valueOf(array_List.get(position)
					.getNewsItemId()));
			return convertView;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		modelNewsItem = (ModelNewsItem) adapter.getItem(position);
		switch (position) {
		case 0:
			Intent first = new Intent(DisplayNewsItem.this,
					DisplayHeadLine.class);
			first.putExtra("id", modelNewsItem.getNewsItemId());
			startActivity(first);
			break;

		case 1:
			Intent second = new Intent(DisplayNewsItem.this,
					DisplayHeadLine.class);
			second.putExtra("id", modelNewsItem.getNewsItemId());
			startActivity(second);
			break;

		case 2:
			Intent third = new Intent(DisplayNewsItem.this,
					DisplayHeadLine.class);
			third.putExtra("id", modelNewsItem.getNewsItemId());
			startActivity(third);
			break;

		case 3:
			Intent fourth = new Intent(DisplayNewsItem.this,
					DisplayHeadLine.class);
			fourth.putExtra("id", modelNewsItem.getNewsItemId());
			startActivity(fourth);
			break;

		case 4:
			Intent fifth = new Intent(DisplayNewsItem.this,
					DisplayHeadLine.class);
			fifth.putExtra("id", modelNewsItem.getNewsItemId());
			startActivity(fifth);
			break;

		case 5:
			Intent sixth = new Intent(DisplayNewsItem.this,
					DisplayHeadLine.class);
			sixth.putExtra("id", modelNewsItem.getNewsItemId());
			startActivity(sixth);
			break;

		case 6:
			Intent seventh = new Intent(DisplayNewsItem.this,
					DisplayHeadLine.class);
			seventh.putExtra("id", modelNewsItem.getNewsItemId());
			startActivity(seventh);
			break;

		case 7:
			Intent eighth = new Intent(DisplayNewsItem.this,
					DisplayHeadLine.class);
			eighth.putExtra("id", modelNewsItem.getNewsItemId());
			startActivity(eighth);
			break;

		case 8:
			Intent nineth = new Intent(DisplayNewsItem.this,
					DisplayHeadLine.class);
			nineth.putExtra("id", modelNewsItem.getNewsItemId());
			startActivity(nineth);
			break;

		case 9:
			Intent tenth = new Intent(DisplayNewsItem.this,
					DisplayHeadLine.class);
			tenth.putExtra("id", modelNewsItem.getNewsItemId());
			startActivity(tenth);
			break;

		default:
			break;
		}
	}
}
