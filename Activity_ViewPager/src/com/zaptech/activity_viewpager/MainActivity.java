package com.zaptech.activity_viewpager;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewPager viewPager;
		MyPagerAdapter myPagerAdapter;
		viewPager = (ViewPager) findViewById(R.id.myviewpager);
		myPagerAdapter = new MyPagerAdapter();
		viewPager.setAdapter(myPagerAdapter);
	}

	private class MyPagerAdapter extends PagerAdapter {

		int NumberOfPages = 5;

		/*int[] res = { R.layout.l1, R.layout.l2, R.layout.l3, R.layout.l4,
				R.layout.l5 };*/
		
		  int[] res = { android.R.drawable.ic_dialog_alert,
		  android.R.drawable.ic_menu_camera,
		  android.R.drawable.ic_menu_compass,
		  android.R.drawable.ic_menu_directions,
		  android.R.drawable.ic_menu_gallery };
		 
		int[] backgroundcolor = { 0xFF101010, 0xFF202020, 0xFF303030,
				0xFF404040, 0xFF505050 };

		@Override
		public int getCount() {
			return res.length;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {

			TextView textView = new TextView(MainActivity.this);
			textView.setTextColor(Color.WHITE);
			textView.setTextSize(30);
			textView.setTypeface(Typeface.DEFAULT_BOLD);
			textView.setText(String.valueOf(position));

			ImageView imageView = new ImageView(MainActivity.this);
			imageView.setImageResource(res[position]);
			LayoutParams imageParams = new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

			imageView.setLayoutParams(imageParams);

			LinearLayout layout = new LinearLayout(MainActivity.this);
			layout.setOrientation(LinearLayout.VERTICAL);
			LayoutParams layoutParams = new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			layout.setBackgroundColor(backgroundcolor[position]);
			layout.setLayoutParams(layoutParams);
			layout.addView(textView);
			layout.addView(imageView);

			final int page = position;
			layout.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Toast.makeText(MainActivity.this,
							"Page " + page + " clicked", Toast.LENGTH_LONG)
							.show();
				}
			});

			container.addView(layout);
			return layout;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((LinearLayout) object);
		}

	}
}
