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
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends Activity {
	ProgressDialog mProgress;
	TextView txt_Json;
	ListView listParsedData;
	DBHelper dbHelper;

	// JSON Variables

	String strIncludeImageLayout, strIncludeTitleInLayout,
			strIncludeTextInLayout, strImagePosition, strTitlePosition,
			strTextPosition;
	ArrayList<JSON> arrayParsed = new ArrayList<JSON>();
	ArrayAdapter<JSON> adpt;

	// Model Objects..
	JSON model_JSON;
	HomeItems model_HomeItems;
	HomeItemIMAGE model_HomeItemImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();
		new GetData().execute();

	}

	public void init() {
		mProgress = new ProgressDialog(HomeActivity.this);
		txt_Json = (TextView) findViewById(R.id.txt_json);
		listParsedData = (ListView) findViewById(R.id.listParsedData);
		// MODEL
		model_JSON = new JSON();
		model_HomeItems = new HomeItems();
		model_HomeItemImage = new HomeItemIMAGE();

		// Database object..
		dbHelper = new DBHelper(HomeActivity.this);
		dbHelper.getWritableDatabase();

	}

	class GetData extends AsyncTask<Void, Void, Void> {
		String str;

		@Override
		protected void onPreExecute() {
			mProgress.setTitle("Stream Loder");
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
			txt_Json.setText(str);
			JsonParsing(str);

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
				model_HomeItemImage.setHomeItemImage_Name(JObjHomeItemImage
						.getString("name"));
				Toast.makeText(HomeActivity.this,
						model_HomeItemImage.getHomeItemImage_Name(),
						Toast.LENGTH_SHORT).show();
				insertHomeItmes();
				insertHomeItemsImage();
			}

		} catch (JSONException e) {

			e.printStackTrace();
		}
	}

	public void insertHomeItmes() {
		dbHelper.insertHomeItems(
				Integer.parseInt(model_HomeItems.getHomeItem_id()),
				model_HomeItems.getHomeItem_includeImageInLayout(),
				model_HomeItems.getHomeItem_includeTitleInLayout(),
				model_HomeItems.getHomeItem_includeTextInLayout(),
				model_HomeItems.getHomeItem_imagePosition(),
				model_HomeItems.getHomeItem_titlePosition(),
				model_HomeItems.getHomeItem_textPosition(),
				model_HomeItems.getHomeItem_title(),
				model_HomeItems.getHomeItem_text(),
				model_HomeItems.getHomeItem_textHTML(),
				Integer.parseInt(model_HomeItems.getHomeItem_tabPosition()),
				model_HomeItems.getHomeItem_tabText(),
				model_HomeItems.getHomeItem_tabIcon(),
				model_HomeItems.getHomeItem_dateChanged(),
				model_HomeItems.getHomeItem_isDirty(),
				model_HomeItems.getHomeItem_tempUniqueUID(),
				Integer.parseInt(model_HomeItems.getHomeItem_Type()),
				model_HomeItems.getHomeItem_useTabIcon(),
				Integer.parseInt(model_HomeItems.getHomeItem_sortPosition()),
				model_HomeItems.getHomeItem_archived(),
				model_HomeItems.getHomeItem_listIcon());
		Toast.makeText(HomeActivity.this, "Records Inserted..!",
				Toast.LENGTH_SHORT).show();
	}

	public void insertHomeItemsImage() {
		dbHelper.insertHomeItemImage(
				Integer.parseInt(model_HomeItemImage.getHomeItemImage_Id()),
				Integer.parseInt(model_HomeItemImage.getHomeItemImage_Width()),
				Integer.parseInt(model_HomeItemImage.getHomeItemImage_Height()),
				model_HomeItemImage.getHomeItemImage_OriginalName(),
				model_HomeItemImage.getHomeItemImage_LocationLocal(),
				model_HomeItemImage.getHomeItemImage_Type(),
				model_HomeItemImage.getHomeItemImage_BaseURL(),
				model_HomeItemImage.getHomeItemImage_mimeType(),
				model_HomeItemImage.getHomeItemImage__Base64Version(),
				model_HomeItemImage.getHomeItemImage_IsDirty(),
				model_HomeItemImage.getHomeItemImage_Archived(),
				model_HomeItemImage.getHomeItemImage_Name(),
				Integer.parseInt(model_HomeItems.getHomeItem_id()));
		Toast.makeText(HomeActivity.this, "Images Records Inserted..!",
				Toast.LENGTH_SHORT).show();
	}
}
