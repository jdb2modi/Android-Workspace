package com.zaptech.maptask;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.zaptech.maptask.Models.Model_Json;
import com.zaptech.maptask.utility.JSONParser;
import com.zaptech.maptask.utility.MapUtility;
import com.zaptech.taskstatus.R;

public class Activity_Home extends Activity implements OnClickListener {
	private ProgressDialog mProgress;
	private TextView mTxt_BatteryPercent;
	private TextView mTxt_BatteryDistance;
	private TextView mTxt_DateTime;
	private ImageButton mImgBtn_Referesh;
	private LocationManager mLocationManager;
	private Location mLocation;
	private GoogleMap mGoogleMap;
	private Model_Json mModelJson;
	private MapUtility mMapUtility;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();// Initializing variables
		new ParsingData().execute();// Calling AsyncTask

	}

	// Function to Initialize variables..
	public void init() {

		mModelJson = new Model_Json();
		mProgress = new ProgressDialog(Activity_Home.this);

		mTxt_BatteryPercent = (TextView) findViewById(R.id.txt_batteryStatus);
		mTxt_BatteryDistance = (TextView) findViewById(R.id.txt_batteryDistance);
		mTxt_DateTime = (TextView) findViewById(R.id.txt_dateTime);

		mImgBtn_Referesh = (ImageButton) findViewById(R.id.imgBtn_refresh);
		mImgBtn_Referesh.setOnClickListener(Activity_Home.this);

		mMapUtility = new MapUtility();

	}

	// Class to Parse the Main Json Stream..
	class ParsingData extends AsyncTask<Void, Void, Void> {
		String strStream;

		@Override
		protected void onPreExecute() {
			mProgress.setTitle(getResources()
					.getString(R.string.strDialogTitle));
			mProgress.setMessage(getResources().getString(
					R.string.strDialogMessage));
			mProgress.setCancelable(false);
			mProgress.show();
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {

			strStream = mMapUtility.GET(getResources().getString(
					R.string.strUrl));
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (mProgress != null) {
				if (mProgress.isShowing()) {
					mProgress.dismiss();
				}
			}
			super.onPostExecute(result);

			// Call to Parse Data..
			parseJson(strStream);

			// Initialize Map..
			try {
				initializeMap();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}// END of the Class ParsingData

	

	// Function to Parse Main Json Stream..
	public void parseJson(String str) {
		try {
			JSONObject objRootJson = new JSONObject(str);
			JSONObject objLocation = objRootJson.getJSONObject("location");
			JSONObject objBattery = objRootJson.getJSONObject("battery");

			String strDateTime = objRootJson.getString("datetime");
			String strLatitude = objLocation.getString("latitude");
			String strLongitude = objLocation.getString("longitude");
			String strCharge = objBattery.getString("charge");
			String strDistance = objBattery.getString("distance");

			mModelJson.setDatetime(strDateTime);
			mModelJson.setLatitude(strLatitude);
			mModelJson.setLongitude(strLongitude);
			mModelJson.setCharge(strCharge);
			mModelJson.setDistance(strDistance);

			setControls(mModelJson);

		} catch (JSONException e) {

			e.printStackTrace();
		}

	}

	// Function to set Values..
	protected void setControls(Model_Json mModelJson) {

		mTxt_BatteryPercent.setText(mTxt_BatteryPercent.getText().toString()
				+ " " + mModelJson.getCharge().toString());

		mTxt_BatteryDistance.setText(mTxt_BatteryDistance.getText().toString()
				+ " " + Integer.parseInt(mModelJson.getDistance()) / 1000);
		convertDate(mModelJson);

	}

	// Function to Convert date in dd/mm/yyyy HH:mm:ss format...
	protected void convertDate(Model_Json mModelJson) {

		String strDateTime = mModelJson.getDatetime();
		String[] Str_Format = strDateTime.split("T");
		mTxt_DateTime.setText(mTxt_DateTime.getText().toString() + " "
				+ strDateTime.format(Str_Format[0].trim()) + " , "
				+ strDateTime.format(Str_Format[1].trim()));

	}

	// Function to Restart Activity...
	protected void onRestart() {

		super.onRestart();
		Intent i = new Intent(Activity_Home.this, Activity_Home.class);
		startActivity(i);
		finish();

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imgBtn_refresh:
			onRestart();
			break;

		default:
			break;
		}

	}

	// To Initialize Map..
	protected void initializeMap() {
		if (mGoogleMap == null) {
			mGoogleMap = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.map)).getMap();
			if (mGoogleMap == null) {
				Toast.makeText(Activity_Home.this, "Sorry! Map Is Not Created",
						Toast.LENGTH_SHORT).show();
			}
			// Getting Current Location..
			mGoogleMap.setMyLocationEnabled(true);
			mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

			Criteria criteria = new Criteria();
			String bestProvider = mLocationManager.getBestProvider(criteria,
					true);
			mLocation = mLocationManager.getLastKnownLocation(bestProvider);

			// Adding Marker...

			mGoogleMap.addMarker(new MarkerOptions().position(
					new LatLng(Double.parseDouble(mModelJson.getLatitude()),
							Double.parseDouble(mModelJson.getLongitude())))
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.destination)));
			mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(
					mLocation.getLatitude(), mLocation.getLongitude())));

			/*
			 * // Drawing Line... Polyline line = mGoogleMap.addPolyline(new
			 * PolylineOptions() .add(new LatLng(mLocation.getLatitude(),
			 * mLocation .getLongitude()), new
			 * LatLng(Double.parseDouble(mModelJson .getLatitude()), Double
			 * .parseDouble(mModelJson.getLongitude())))
			 * .width(5).color(Color.RED));
			 */

			// Getting URL...
			String strURL = mMapUtility.makeURL(mLocation.getLatitude(),
					mLocation.getLongitude(),
					Double.parseDouble(mModelJson.getLatitude()),
					Double.parseDouble(mModelJson.getLongitude()));
			// Calling Asynctask for mapUrl..
			new ConnectAsyncTask(strURL).execute();
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

	// Function to calculate Distance between two Locations..
	public void calculateDistance() {
		double distance;
		Location source = new Location("source");
		source.setLatitude(mLocation.getLatitude());
		source.setLongitude(mLocation.getLongitude());
		Location destination = new Location("destination");
		destination.setLatitude(Double.parseDouble(mModelJson.getLatitude()));
		destination.setLongitude(Double.parseDouble(mModelJson.getLongitude()));
		distance = source.distanceTo(destination);
		Toast.makeText(Activity_Home.this, "Distance : " + distance,
				Toast.LENGTH_LONG).show();
	}

	public void autoUpdateLocation() {
		if (mLocation != null) {
			mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
					new LatLng(mLocation.getLatitude(), mLocation
							.getLongitude()), 13));

			CameraPosition cameraPosition = new CameraPosition.Builder()
					.target(new LatLng(mLocation.getLatitude(), mLocation
							.getLongitude())) // Sets the center of the map to
												// location user
					.zoom(17) // Sets the zoom
					.bearing(90) // Sets the orientation of the camera to east
					.tilt(40) // Sets the tilt of the camera to 30 degrees
					.build(); // Creates a CameraPosition from the builder
			mGoogleMap.animateCamera(CameraUpdateFactory
					.newCameraPosition(cameraPosition));

		}
	}

	/*
	 * private List<LatLng> decodePoly(String encoded) {
	 * 
	 * List<LatLng> poly = new ArrayList<LatLng>(); int index = 0, len =
	 * encoded.length(); int lat = 0, lng = 0;
	 * 
	 * while (index < len) { int b, shift = 0, result = 0; do { b =
	 * encoded.charAt(index++) - 63; result |= (b & 0x1f) << shift; shift += 5;
	 * } while (b >= 0x20); int dlat = ((result & 1) != 0 ? ~(result >> 1) :
	 * (result >> 1)); lat += dlat;
	 * 
	 * shift = 0; result = 0; do { b = encoded.charAt(index++) - 63; result |=
	 * (b & 0x1f) << shift; shift += 5; } while (b >= 0x20); int dlng = ((result
	 * & 1) != 0 ? ~(result >> 1) : (result >> 1)); lng += dlng;
	 * 
	 * LatLng p = new LatLng((((double) lat / 1E5)), (((double) lng / 1E5)));
	 * poly.add(p); }
	 * 
	 * return poly; }
	 */

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
				calculateDistance();
				//autoUpdateLocation();
			}
		}
	}

}
