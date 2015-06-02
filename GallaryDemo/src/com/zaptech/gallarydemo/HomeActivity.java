package com.zaptech.gallarydemo;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

public class HomeActivity extends Activity {
	Gallery mGallary;
	Integer[] imgIds = { R.drawable.two, R.drawable.three, R.drawable.four,
			R.drawable.five, R.drawable.six, R.drawable.seven,
			R.drawable.eight, R.drawable.nine };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		init();
	}

	public void init() {

		mGallary = (Gallery) findViewById(R.id.gallery1);
		mGallary.setAdapter(new ImageAdapter(this));
		mGallary.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getBaseContext(),
						"pic" + (position + 1) + " selected",
						Toast.LENGTH_SHORT).show();
				// display the images selected
				ImageView imageView = (ImageView) findViewById(R.id.image1);
				imageView.setImageResource(imgIds[position]);
			}
		});
	}

	public class ImageAdapter extends BaseAdapter {
		Context mContext;
		private int itemBackground;

		public ImageAdapter(Context mContext) {
			this.mContext = mContext;
			TypedArray a = obtainStyledAttributes(R.styleable.MyGallery);
			itemBackground = a.getResourceId(
					R.styleable.MyGallery_android_galleryItemBackground, 0);
			a.recycle();
		}

		@Override
		public int getCount() {

			return imgIds.length;
		}

		@Override
		public Object getItem(int position) {

			return position;
		}

		@Override
		public long getItemId(int position) {

			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView = new ImageView(mContext);
			imageView.setImageResource(imgIds[position]);
			imageView.setLayoutParams(new Gallery.LayoutParams(100, 100));
			imageView.setBackgroundResource(itemBackground);
			return imageView;

		}

	}
}
