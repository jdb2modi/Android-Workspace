package com.zaptech.gesturedetector2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class Activity_Home extends Activity {

	protected static final String TAG = "GestureDetector";
	private GestureDetector mGestureDetector;

	private ListView mList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();
		detectGesture();
		mList.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				detectGesture();
				return false;
			}
		});
		mList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				detectGesture();
				Toast.makeText(Activity_Home.this, "Clicked" + position,
						Toast.LENGTH_LONG).show();
			}
		});
		mList.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				detectGesture();

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {

			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		detectGesture();
		super.onResume();
	}

	private void init() {
		mList = (ListView) findViewById(R.id.list);
	}

	private void detectGesture() {

		mGestureDetector = new GestureDetector(this,

		new GestureDetector.SimpleOnGestureListener() {
			@Override
			public boolean onDoubleTap(MotionEvent e) {
				Log.i(TAG, "onDoubleTap");
				Toast.makeText(Activity_Home.this, "OnDoubleTap",
						Toast.LENGTH_SHORT).show();
				return true;
			}

			@Override
			public boolean onDoubleTapEvent(MotionEvent e) {
				Log.i(TAG, "onDoubleTapEvent");
				Toast.makeText(Activity_Home.this, "OnDoubleTapEvent",
						Toast.LENGTH_SHORT).show();
				return true;
			}

			@Override
			public boolean onDown(MotionEvent e) {
				Log.i(TAG, "onDown");
				Toast.makeText(Activity_Home.this, "OnDown", Toast.LENGTH_SHORT)
						.show();
				return true;
			}

			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2,
					float velocityX, float velocityY) {
				Log.i(TAG, "onFling");
				Toast.makeText(Activity_Home.this, "OnFling",
						Toast.LENGTH_SHORT).show();
				
				return true;
			}

			@Override
			public void onLongPress(MotionEvent e) {
				Log.i(TAG, "onLongPress");
				Toast.makeText(Activity_Home.this, "OnLongPress",
						Toast.LENGTH_SHORT).show();
			}

			@Override
			public boolean onScroll(MotionEvent e1, MotionEvent e2,
					float distanceX, float distanceY) {
				Log.i(TAG, "onScroll");
				Toast.makeText(Activity_Home.this, "OnScroll",
						Toast.LENGTH_SHORT).show();
				return true;
			}

			@Override
			public void onShowPress(MotionEvent e) {
				Log.i(TAG, "onShowPress");
				Toast.makeText(Activity_Home.this, "OnShowPress",
						Toast.LENGTH_SHORT).show();
			}

			@Override
			public boolean onSingleTapConfirmed(MotionEvent e) {
				Log.i(TAG, "onSingleTapConfirmed");
				Toast.makeText(Activity_Home.this, "OnSingleTapConfirmed",
						Toast.LENGTH_SHORT).show();
				return true;
			}

			@Override
			public boolean onSingleTapUp(MotionEvent e) {
				Log.i(TAG, "onSingleTapUp");
				Toast.makeText(Activity_Home.this, "OnSingleTapUp",
						Toast.LENGTH_SHORT).show();
				return true;
			}
		});

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (mGestureDetector != null) {
			return mGestureDetector.onTouchEvent(event);
		} else {
			return super.onTouchEvent(event);
		}

	}

}
