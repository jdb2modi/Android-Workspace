package com.zaptech.imagesfromsdcard;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private Cursor cursor;
	private int columnIndex;
	private String imagePath;
	GridView sdcardImages;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sdcardImages = (GridView) findViewById(R.id.gridView1);
		loadimages();
		final ImageView display = (ImageView) findViewById(R.id.imgSelected);
		sdcardImages.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView parent, View v, int position,
					long id) {
				getPath(position);
				if (imagePath != null && imagePath != "") {
					File f = new File(imagePath);
					if (f.exists()) {
						Drawable d = Drawable.createFromPath(imagePath);
						display.setImageDrawable(d);

					}
				}

			}
		});

	}

	private void loadimages() {
		String[] projection = { MediaStore.Images.Thumbnails._ID };
		cursor = managedQuery(
				MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, projection,
				null, null, MediaStore.Images.Thumbnails.IMAGE_ID);
		columnIndex = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Thumbnails._ID);
		sdcardImages.setAdapter(new ImageAdapter(this));

	}

	private void getPath(int position) {
		String[] projection = { MediaStore.Images.Media.DATA };
		cursor = managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
				projection, null, null, null);
		columnIndex = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToPosition(position);
		imagePath = cursor.getString(columnIndex);

	}

	/**
	 * Adapter for our image files.
	 */
	private class ImageAdapter extends BaseAdapter {

		private Context context;

		public ImageAdapter(Context localContext) {
			context = localContext;
		}

		public int getCount() {
			return cursor.getCount();
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView picturesView;
			if (convertView == null) {
				picturesView = new ImageView(context);
				// Move cursor to current position
				cursor.moveToPosition(position);
				// Get the current value for the requested column
				int imageID = cursor.getInt(columnIndex);
				// Set the content of the image based on the provided URI
				picturesView.setImageURI(Uri.withAppendedPath(
						MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, ""
								+ imageID));
				picturesView.setScaleType(ImageView.ScaleType.FIT_CENTER);
				picturesView.setPadding(2, 2, 2, 2);
				picturesView
						.setLayoutParams(new GridView.LayoutParams(300, 300));
			} else {
				picturesView = (ImageView) convertView;
			}
			return picturesView;
		}
	}
}