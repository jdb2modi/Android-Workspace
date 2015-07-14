package com.zaptech.multichoicegridview;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;

public class Activity_Home extends Activity {

	GridView mGrid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		loadApps();

		setContentView(R.layout.activity_home);
		mGrid = (GridView) findViewById(R.id.myGrid);
		mGrid.setAdapter(new AppsAdapter());
		mGrid.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE_MODAL);
		mGrid.setMultiChoiceModeListener(new MultiChoiceModeListener());
	}

	private List<ResolveInfo> mApps;

	private void loadApps() {
		Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
		mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

		mApps = getPackageManager().queryIntentActivities(mainIntent, 0);
	}

	public class AppsAdapter extends BaseAdapter {
		public AppsAdapter() {
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			CheckableLayout l;
			ImageView i;

			if (convertView == null) {
				i = new ImageView(Activity_Home.this);
				i.setScaleType(ImageView.ScaleType.FIT_CENTER);
				i.setLayoutParams(new ViewGroup.LayoutParams(50, 50));
				l = new CheckableLayout(Activity_Home.this);
				l.setLayoutParams(new GridView.LayoutParams(
						GridView.LayoutParams.WRAP_CONTENT,
						GridView.LayoutParams.WRAP_CONTENT));
				l.addView(i);
			} else {
				l = (CheckableLayout) convertView;
				i = (ImageView) l.getChildAt(0);
			}

			ResolveInfo info = mApps.get(position);
			i.setImageDrawable(info.activityInfo.loadIcon(getPackageManager()));

			return l;
		}

		public final int getCount() {
			return mApps.size();
		}

		public final Object getItem(int position) {
			return mApps.get(position);
		}

		public final long getItemId(int position) {
			return position;
		}
	}

	public class CheckableLayout extends FrameLayout implements Checkable {
		private boolean mChecked;

		public CheckableLayout(Context context) {
			super(context);
		}

		@SuppressWarnings("deprecation")
		public void setChecked(boolean checked) {
			mChecked = checked;
			setBackgroundDrawable(checked ? getResources().getDrawable(
					R.drawable.ic_launcher) : null);
		}

		public boolean isChecked() {
			return mChecked;
		}

		public void toggle() {
			setChecked(!mChecked);
		}

	}

	public class MultiChoiceModeListener implements
			GridView.MultiChoiceModeListener {
		public boolean onCreateActionMode(ActionMode mode, Menu menu) {
			mode.setTitle("Select Items");
			mode.setSubtitle("One item selected");
			return true;
		}

		public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
			return true;
		}

		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
			return true;
		}

		public void onDestroyActionMode(ActionMode mode) {
		}

		public void onItemCheckedStateChanged(ActionMode mode, int position,
				long id, boolean checked) {
			int selectCount = mGrid.getCheckedItemCount();
			switch (selectCount) {
			case 1:
				mode.setSubtitle("One item selected");
				break;
			default:
				mode.setSubtitle("" + selectCount + " items selected");
				break;
			}
		}

		@Override
		public boolean onCreateActionMode(android.view.ActionMode mode,
				Menu menu) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean onPrepareActionMode(android.view.ActionMode mode,
				Menu menu) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean onActionItemClicked(android.view.ActionMode mode,
				MenuItem item) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void onDestroyActionMode(android.view.ActionMode mode) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onItemCheckedStateChanged(android.view.ActionMode mode,
				int position, long id, boolean checked) {
			// TODO Auto-generated method stub
			
		}

	}
}
