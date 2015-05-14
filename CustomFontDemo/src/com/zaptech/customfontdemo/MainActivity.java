package com.zaptech.customfontdemo;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TextureView;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView txtDemo, txtDemo2, txtDemo3, txtDemo4, txtDemo5, txtDemo6,
			txtDemo7;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		setTypeFace();
	}

	public void init() {
		txtDemo = (TextView) findViewById(R.id.txtDemo);
		txtDemo2 = (TextView) findViewById(R.id.txtDemo2);
		txtDemo3 = (TextView) findViewById(R.id.txtDemo3);
		txtDemo4 = (TextView) findViewById(R.id.txtDemo4);
		txtDemo5 = (TextView) findViewById(R.id.txtDemo5);
		txtDemo6 = (TextView) findViewById(R.id.txtDemo6);
		txtDemo7 = (TextView) findViewById(R.id.txtDemo7);
	}

	public void setTypeFace() {
		Typeface tyFace = Typeface.createFromAsset(getAssets(),
				"fonts/androidnation.ttf");
		Typeface tyFace2 = Typeface.createFromAsset(getAssets(),
				"fonts/alba.ttf");
		Typeface tyFace3 = Typeface.createFromAsset(getAssets(),
				"fonts/Wedgie Regular.ttf");
		Typeface tyFace4 = Typeface.createFromAsset(getAssets(),
				"fonts/BroadcastTitling.ttf");
		Typeface tyFace5 = Typeface.createFromAsset(getAssets(),
				"fonts/Cantate Beveled.otf");
		Typeface tyFace6 = Typeface.createFromAsset(getAssets(),
				"fonts/From Cartoon Blocks.ttf");
		Typeface tyFace7 = Typeface.createFromAsset(getAssets(),
				"fonts/Montague.ttf");
		txtDemo.setTypeface(tyFace);
		txtDemo2.setTypeface(tyFace2);
		txtDemo3.setTypeface(tyFace3);
		txtDemo4.setTypeface(tyFace4);
		txtDemo5.setTypeface(tyFace5);
		txtDemo6.setTypeface(tyFace6);
		txtDemo7.setTypeface(tyFace7);

	}
}
