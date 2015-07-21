package com.example.gpsnearbyplaces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;


public class SearchHotelActivity extends Activity {
	private Double addressLat;
	private Double addressLng;
	private Double currentLat;
	private Double currentLng;
	GPSTracker gpsTracker;
	private String strLocation = "";
	private ModelResponse response;
	private ModelLocation location;
	private ModelVenue venue;

	private TextView txtAddress;
	private ListView listview;
	private String strTy;
TextView textView1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hotel);
		textView1=(TextView)findViewById(R.id.textView1);
		
		init();
		
		Intent intent = getIntent();
		strTy = intent.getStringExtra("type");
		textView1.setText("Near By "+strTy);
		new GetLocation().execute();
	}

	public void init() {

		gpsTracker = new GPSTracker(SearchHotelActivity.this);
		currentLat = gpsTracker.getLatitude();
		currentLng = gpsTracker.getLongitude();
		listview = (ListView) findViewById(R.id.listView1);

	}

	public class GetLocation extends AsyncTask<Void, Void, Void> {
		ProgressDialog pd;
		String result;

		protected void onPreExecute() {
			super.onPreExecute();
			pd = new ProgressDialog(SearchHotelActivity.this);
			// pd.setTitle(getString(R.string.app_name));
			pd.setCancelable(false);
			pd.setMessage("Loading.....");
			pd.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			try {
				String url = "https://api.foursquare.com/v2/venues/search?"
						+ "+intent=match+&ll="
						+ currentLat
						+ ","
						+ currentLng
						+ "+&query="+strTy
						+ strLocation
						+ ""
						+ "+&client_id=RMYWTFPJJX35F4HRGVSAH2FVJD4YUKG3TSC2MOAQW5KWMOHW"
						+ "+&client_secret=WYHCOWKM3PL4IQL1L5BTNAXKVN33PLJTRHGSCD4BSDPISLE2"
						+ "+&v=20131111";

				/*
				 * String url=
				 * "https://api.foursquare.com/v2/venues/search?intent=match&ll=40.7,-74%20&query="
				 * +strLocation+
				 * "client_id=RMYWTFPJJX35F4HRGVSAH2FVJD4YUKG3TSC2MOAQW5KWMOHW%20&client_secret=WYHCOWKM3PL4IQL1L5BTNAXKVN33PLJTRHGSCD4BSDPISLE2%20"
				 * + "&v=20131126";
				 */
				Log.d("URL", url);
				// HTTP GET METHOD
				HttpClient client = new DefaultHttpClient();
				HttpUriRequest request = new HttpGet(url);
				HttpResponse response = client.execute(request);

				result = convertToString(response.getEntity().getContent());
				Log.e("result", result);

			} catch (Exception e) {
				if (pd != null && pd.isShowing())
					pd.dismiss();
				result = "";
				e.printStackTrace();
			}

			return null;
		}

		protected void onPostExecute(Void result1) {
			if (pd != null && pd.isShowing())
				pd.dismiss();

			Geocoder geoCoder = new Geocoder(SearchHotelActivity.this,
					Locale.getDefault());

			try {
				List<Address> addresses = geoCoder.getFromLocationName(
						strLocation, 1);
				if (addresses.size() > 0) {
					addressLat = addresses.get(0).getLatitude();
					addressLng = addresses.get(0).getLongitude();

					String data = "Street Address: "
							+ addresses.get(0).getAddressLine(0) + "" + "\n"
							+ addresses.get(0).getSubLocality() + "\n"
							+ "City: " + addresses.get(0).getLocality() + "\n"
							+ "State: " + addresses.get(0).getAdminArea()
							+ "\n" + "Zip: " + addresses.get(0).getPostalCode();

					data = data.replace("null", " ");

					Constant.AddressLocation = data;

					Log.i("Address", "**********" + Constant.AddressLocation);
					txtAddress.setText(data);
				}

			} catch (IOException e) { // TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				Gson gson = new Gson();
				ResMain resMain = gson.fromJson(result, ResMain.class);
				response = resMain.getResponse();

				if (response != null) {
					int size = response.getVenues().size();

					Log.d("size", "" + size + "," + "11111");
					// listHashMap.clear();
					NearLocation.getListHashMap().clear();
					for (ModelVenue venue : response.getVenues()) {

						HashMap<String, ModelVenue> temp = new HashMap<String, ModelVenue>();
						temp.put("venue", venue);
						// listHashMap.add(temp);
						NearLocation.getListHashMap().add(temp);

						/*
						 * Log.d("Venue name", ">>>" + venue.getName());
						 * Log.d("Venue Lat", ">>>" +
						 * venue.getLocation().getLat()); Log.d("Venue Lng",
						 * ">>>" + venue.getLocation().getLng());
						 */
					}
					NearLocation.setFlagSearched(false);

					listview.setAdapter(new CustomAdapter(NearLocation
							.getListHashMap()));

				} else {
					Toast.makeText(SearchHotelActivity.this, "Response Null",
							Toast.LENGTH_SHORT).show();
				}

				Log.i("response", "" + response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public static String convertToString(InputStream inputStream)
			throws IOException {
		if (inputStream != null) {
			Log.d("convertToString", "3");
			Writer writer = new StringWriter();

			char[] buffer = new char[1024];
			try {
				Reader reader = new BufferedReader(new InputStreamReader(
						inputStream, "UTF-8"), 1024);
				int n;
				while ((n = reader.read(buffer)) != -1) {
					writer.write(buffer, 0, n);
				}
				Log.d("convertToString", "4 >>>>>>> " + writer.toString());
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				inputStream.close();
			}
			return writer.toString();
		} else {
			return "";
		}
	}

	class CustomAdapter extends BaseAdapter {

		ArrayList<HashMap<String, ModelVenue>> list;

		public CustomAdapter(ArrayList<HashMap<String, ModelVenue>> list_) {
			// TODO Auto-generated constructor stub
			list = list_;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int pos, View convetView, ViewGroup arg2) {
			// TODO Auto-generated method stub
			View view = convetView;
			if (view == null) {
				view = getLayoutInflater().inflate(R.layout.list_row, null);
			}
			final ModelVenue venue = list.get(pos).get("venue");
			TextView name = (TextView) view.findViewById(R.id.name);
			name.setText("" + venue.getName());

			String locname=""+venue.getName();
			Log.d("trhis is the",""+locname);
			
			
			return view;
		}

	}
}