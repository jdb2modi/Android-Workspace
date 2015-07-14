package com.zaptech.mapfunctionality;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class Activity_Home extends Activity implements OnMapLongClickListener {
	private GoogleMap mGoogleMap;
	private LocationManager mLocationManager;
	private Location mLocation;
	private MapUtility mMapUtility;
	List<Address> mAddresses;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		initializeMap();
		getCurrentLocation();

	}

	private void initializeMap() {
		if (mGoogleMap == null) {
			// initializing Map...
			mGoogleMap = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.fragmentmap)).getMap();
			if (mGoogleMap == null) {
				Toast.makeText(Activity_Home.this, "Sorry! Map Is Not Created",
						Toast.LENGTH_SHORT).show();
			}

		}
		mMapUtility = new MapUtility();

	}

	private void getCurrentLocation() {
		mGoogleMap.setMyLocationEnabled(true);
		mGoogleMap.setOnMapLongClickListener(Activity_Home.this);
		mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		if (mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			mLocation = mLocationManager
					.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		} else {
			mLocation = mLocationManager
					.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		}
		mGoogleMap.getMyLocation();
		addMarkerToCurrentLocation();
	}

	private void addMarkerToCurrentLocation() {
		mGoogleMap.addMarker(new MarkerOptions().position(
				new LatLng(mLocation.getLatitude(), mLocation.getLongitude()))
				.title("Marker"));

	}

	@Override
	public void onMapLongClick(LatLng arg0) {

		mGoogleMap.clear();
		addMarkerToCurrentLocation();

		Geocoder geocoder = new Geocoder(Activity_Home.this);
		try {
			mAddresses = geocoder.getFromLocation(arg0.latitude,
					arg0.longitude, 1);

		} catch (IOException e) {
			e.printStackTrace();
		}
		// To Display Address Title...
		StringBuilder strReturnedAddress = new StringBuilder("Address:\n");
		if (mAddresses != null) {
			Address returnedAddress = mAddresses.get(0);

			for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {
				strReturnedAddress.append(returnedAddress.getAddressLine(i))
						.append("\n");
			}
		}
		// To change the Marker Position...
		mGoogleMap.addMarker(
				new MarkerOptions()
						.position(arg0)
						.icon(BitmapDescriptorFactory
								.fromResource(R.drawable.destination))
						.title(strReturnedAddress.toString())).showInfoWindow();
		String strURL = mMapUtility.makeURL(mLocation.getLatitude(),
				mLocation.getLongitude(), arg0.latitude, arg0.longitude);
		new ConnectAsyncTask(strURL).execute();

	}

	// /Class Json Parser
	public class JSONParser {
		InputStream is = null;
		JSONObject jObj = null;
		String json = "";

		// constructor
		public JSONParser() {
		}

		public String getJSONFromUrl(String url) {

			// Making HTTP request
			try {
				// defaultHttpClient
				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(url);

				HttpResponse httpResponse = httpClient.execute(httpPost);
				HttpEntity httpEntity = httpResponse.getEntity();
				is = httpEntity.getContent();

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(is, "iso-8859-1"), 8);
				StringBuilder sb = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}

				json = sb.toString();
				is.close();
			} catch (Exception e) {
				Log.e("Buffer Error", "Error converting result " + e.toString());
			}
			return json;

		}

	}

	// Class MapUtility
	public class MapUtility {

		public List<LatLng> decodePoly(String encoded) {

			List<LatLng> poly = new ArrayList<LatLng>();
			int index = 0, len = encoded.length();
			int lat = 0, lng = 0;

			while (index < len) {
				int b, shift = 0, result = 0;
				do {
					b = encoded.charAt(index++) - 63;
					result |= (b & 0x1f) << shift;
					shift += 5;
				} while (b >= 0x20);
				int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
				lat += dlat;

				shift = 0;
				result = 0;
				do {
					b = encoded.charAt(index++) - 63;
					result |= (b & 0x1f) << shift;
					shift += 5;
				} while (b >= 0x20);
				int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
				lng += dlng;

				LatLng p = new LatLng((((double) lat / 1E5)),
						(((double) lng / 1E5)));
				poly.add(p);
			}

			return poly;
		}

		// A function to Parse Json Stream..
		public String GET(String url) {
			InputStream inputStream = null;
			String result = "";
			try {
				HttpClient httpclient = new DefaultHttpClient();
				HttpResponse httpResponse = httpclient
						.execute(new HttpGet(url));
				inputStream = httpResponse.getEntity().getContent();
				if (inputStream != null)
					result = convertInputStreamToString(inputStream);
				else
					result = "Did not work!";

			} catch (Exception e) {
				Log.d("InputStream", e.getLocalizedMessage());
			}

			return result;
		}

		// convert Main inputstream to String
		private String convertInputStreamToString(InputStream inputStream)
				throws IOException {
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(inputStream));
			String line = "";
			String result = "";
			while ((line = bufferedReader.readLine()) != null)
				result += line;

			inputStream.close();
			return result;

		}

		// Drawing Path..
		public String makeURL(double sourcelat, double sourcelog,
				double destlat, double destlog) {
			StringBuilder urlString = new StringBuilder();
			urlString
					.append("http://maps.googleapis.com/maps/api/directions/json");
			urlString.append("?origin=");// from
			urlString.append(Double.toString(sourcelat));
			urlString.append(",");
			urlString.append(Double.toString(sourcelog));
			urlString.append("&destination=");// to
			urlString.append(Double.toString(destlat));
			urlString.append(",");
			urlString.append(Double.toString(destlog));
			urlString.append("&sensor=false&mode=driving&alternatives=true");
			return urlString.toString();
		}

	}

	// A Function to Draw a Path
	protected void drawPath(String result) {
		try {

			final JSONObject json = new JSONObject(result);
			JSONArray routeArray = json.getJSONArray("routes");
			JSONObject routes = routeArray.getJSONObject(0);
			JSONObject overviewPolylines = routes
					.getJSONObject("overview_polyline");
			String encodedString = overviewPolylines.getString("points");
			List<LatLng> list = mMapUtility.decodePoly(encodedString);

			for (int z = 0; z < list.size() - 1; z++) {
				LatLng src = list.get(z);
				LatLng dest = list.get(z + 1);
				Polyline line = mGoogleMap.addPolyline(new PolylineOptions()
						.add(new LatLng(src.latitude, src.longitude),
								new LatLng(dest.latitude, dest.longitude))
						.width(6).color(Color.RED).geodesic(true));
			}

		} catch (JSONException e) {

		}
	}

	// /FOR PATH...
	private class ConnectAsyncTask extends AsyncTask<Void, Void, String> {
		private ProgressDialog mProgressRoute;
		String url;

		ConnectAsyncTask(String urlPass) {
			url = urlPass;
		}

		@Override
		protected void onPreExecute() {

			super.onPreExecute();
			mProgressRoute = new ProgressDialog(Activity_Home.this);
			mProgressRoute.setTitle("Route Loader");
			mProgressRoute.setMessage("Fetching route, Please wait...");
			mProgressRoute.setIndeterminate(true);
			mProgressRoute.show();
		}

		@Override
		protected String doInBackground(Void... params) {
			JSONParser jParser = new JSONParser();
			String json = jParser.getJSONFromUrl(url);
			return json;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			if (mProgressRoute != null) {
				if (mProgressRoute.isShowing()) {
					mProgressRoute.dismiss();

				}
			}
			if (result != null) {
				drawPath(result);

				// autoUpdateLocation();
			}
		}
	}

}
