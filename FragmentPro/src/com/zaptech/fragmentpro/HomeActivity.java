package com.zaptech.fragmentpro;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Configuration config = getResources().getConfiguration();

		android.app.FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
Toast.makeText(getApplicationContext(), "mode="+config.orientation, Toast.LENGTH_LONG).show();
		/**
		 * Check the device orientation and act accordingly
		 */
		if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
			/**
			 * Landscape mode of the device
			 */
			FragmentLS ls_fragment = new FragmentLS();
			fragmentTransaction.replace(android.R.id.content, ls_fragment);
		} else {
			/**
			 * Portrait mode of the device
			 */
			FragmentPS pm_fragment = new FragmentPS();
			fragmentTransaction.replace(android.R.id.content, pm_fragment);
		}
		fragmentTransaction.commit();
	}

}