package com.zaptech;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.Toast;

public class AutoCompleteAct extends Activity {
	AutoCompleteTextView act1;
	MultiAutoCompleteTextView mact1;
	RatingBar rb1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.auto_complete);
		
		Intent i=getIntent();
		
		act1=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
		mact1=(MultiAutoCompleteTextView)findViewById(R.id.multiAutoCompleteTextView1);
		rb1=(RatingBar)findViewById(R.id.ratingBar1);
		
		String[] city=getResources().getStringArray(R.array.arrycity);
		
		
		ArrayAdapter<String> adpt=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line,city);
		
		act1.setThreshold(1);
		act1.setAdapter(adpt);
		act1.setTextColor(Color.BLUE);
		
		mact1.setThreshold(1);
		mact1.setAdapter(adpt);
		mact1.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
		mact1.setTextColor(Color.BLUE);
		
		rb1.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
				// TODO Auto-generated method stub
				String strRating=String.valueOf(rb1.getRating());
				Toast.makeText(getApplicationContext(),"Rating"+strRating,Toast.LENGTH_LONG).show();
			}
		});
		
	}
}
