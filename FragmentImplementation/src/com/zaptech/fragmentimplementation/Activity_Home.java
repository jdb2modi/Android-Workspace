package com.zaptech.fragmentimplementation;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;

public class Activity_Home extends FragmentActivity implements OnClickListener {
	FrameLayout container;
	FragmentManager myFragmentManager;
	Fragment1 myFragment1;
	Fragment2 myFragment2;
	Fragment3 myFragment3;
	Button button1;
	Button button2;
	Button button3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.maincontainer, new Fragment1()).commit();
		}
	}

	public void init() {

		myFragment1 = new Fragment1();
		myFragment2 = new Fragment2();
		myFragment3 = new Fragment3();
		container = (FrameLayout) findViewById(R.id.maincontainer);
		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(this);
		button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(this);
		button3 = (Button) findViewById(R.id.button3);
		button3.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			
			getFragmentManager().beginTransaction()
					.replace(R.id.maincontainer, new Fragment1()).commit();
			 Bundle bundle1 = new Bundle();
		     bundle1.putString("MSG1", "Replace MyFragment1");
		     myFragment1.setArguments(bundle1);
			/*
			 * Fragment1 fragment1 = (Fragment1) myFragmentManager
			 * .findFragmentByTag("Fragment1");
			 * 
			 * FragmentTransaction fragmentTransaction1 = myFragmentManager
			 * .beginTransaction();
			 * fragmentTransaction1.replace(R.id.maincontainer, myFragment1,
			 * "Fragment1"); fragmentTransactions1.commit();
			 */
			break;
		case R.id.button2:

			getFragmentManager().beginTransaction()
					.replace(R.id.maincontainer, new Fragment2()).commit();
			 Bundle bundle2 = new Bundle();
		     bundle2.putString("MSG2", "Replace MyFragment1");
		     myFragment2.setArguments(bundle2);
			/*
			 * Fragment2 fragment2 = (Fragment2) myFragmentManager
			 * .findFragmentByTag("Fragment2");
			 * 
			 * FragmentTransaction fragmentTransaction2 = myFragmentManager
			 * .beginTransaction();
			 * fragmentTransaction2.replace(R.id.maincontainer, myFragment2,
			 * "Fragment2"); fragmentTransaction2.commit();
			 */
			break;
		case R.id.button3:

			getFragmentManager().beginTransaction()
					.replace(R.id.maincontainer, new Fragment3()).commit();
			 Bundle bundle3 = new Bundle();
		     bundle3.putString("MSG3", "Replace MyFragment1");
		     myFragment3.setArguments(bundle3);
			/*
			 * Fragment3 fragment3 = (Fragment3) myFragmentManager
			 * .findFragmentByTag("Fragment3");
			 * 
			 * FragmentTransaction fragmentTransaction3 = myFragmentManager
			 * .beginTransaction();
			 * fragmentTransaction.replace(R.id.maincontainer, myFragment1,
			 * "Fragment1"); fragmentTransaction.commit();
			 */
			break;

		default:
			break;
		}

	}
}
