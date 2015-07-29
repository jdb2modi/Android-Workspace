package com.example.quizapplication;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import android.widget.ListView;
import android.widget.TextView;

import com.example.quizapplication.model.Model;

public class FindFriends extends Activity {

	TextView tv_invite, tv_play, tv_frndname, tv_chlngFrnd, tv_inviteFrnd,
			tv_button;
	Button btn_invite, btn_playing, btn_share;

	ListView listview;
	LayoutInflater inflater;
	ArrayList<Model> record;
	Model model;

	private boolean list_Invite;
	
	private GestureDetector mGestureDetector;

	// x and y coordinates within our side index
	private static float sideIndexX;
	private static float sideIndexY;

	// height of side index
	private int sideIndexHeight;

	// number of items in the side index
	private int indexListSize;

	// list with items for side index
	private ArrayList<Object[]> indexList = null;

	// an array with countries to display in the list

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.find_friends);

		mGestureDetector = new GestureDetector(this,
				new SideIndexGestureListener());
		
		tv_invite = (TextView) findViewById(R.id.findFrnd_tvInviteFrnd);
		tv_play = (TextView) findViewById(R.id.findFrnd_tvPlayingFrnd);
		btn_invite = (Button) findViewById(R.id.findFrnd_btn_Invite);
		btn_playing = (Button) findViewById(R.id.findFrnd_btn_Playing);
		btn_share = (Button) findViewById(R.id.findFrnd_btn_Share);

		listview = (ListView) findViewById(R.id.findFrnd_listVw);
		record = new ArrayList<Model>();
		inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		callDefaultList();
		
		model = new Model();
		model.setFrndName("Rutu");
		record.add(model);

		model = new Model();
		model.setFrndName("Dhara");
		record.add(model);

		model = new Model();
		model.setFrndName("Neha");
		record.add(model);

		model = new Model();
		model.setFrndName("Ravi");
		record.add(model);

		model = new Model();
		model.setFrndName("Shweta");
		record.add(model);

		model = new Model();
		model.setFrndName("Ashi");
		record.add(model);

		model = new Model();
		model.setFrndName("Bunny");
		record.add(model);

		model = new Model();
		model.setFrndName("Cat");
		record.add(model);

		model = new Model();
		model.setFrndName("Fenny");
		record.add(model);

		model = new Model();
		model.setFrndName("henu");
		record.add(model);

		model = new Model();
		model.setFrndName("Sohil");
		record.add(model);

		model = new Model();
		model.setFrndName("Tejas");
		record.add(model);

		model = new Model();
		model.setFrndName("yash");
		record.add(model);

		model = new Model();
		model.setFrndName("Charmee");
		record.add(model);

		model = new Model();
		model.setFrndName("Elephant");
		record.add(model);

		model = new Model();
		model.setFrndName("gauri");
		record.add(model);

		model = new Model();
		model.setFrndName("ice-cream");
		record.add(model);

		model = new Model();
		model.setFrndName("orange");
		record.add(model);

		model = new Model();
		model.setFrndName("deep");
		record.add(model);



	}
	
	private ArrayList<Object[]> createIndex(ArrayList<Model> strArr) {
		ArrayList<Object[]> tmpIndexList = new ArrayList<Object[]>();
		Object[] tmpIndexItem = null;

		int tmpPos = 0;
		String tmpLetter = "";
		String currentLetter = null;
		String strItem = null;

		Collections.sort(record, new CustomComparator());

		for (int j = 0; j < strArr.size(); j++) {
			strItem = strArr.get(j).getFrndName();
			
			currentLetter = strItem.substring(0, 1).toUpperCase();

			// every time new letters comes
			// save it to index list
			if (!currentLetter.equals(tmpLetter)) {
				tmpIndexItem = new Object[3];
				tmpIndexItem[0] = tmpLetter;
				tmpIndexItem[1] = tmpPos - 1;
				tmpIndexItem[2] = j - 1;

				tmpLetter = currentLetter;
				tmpPos = j + 1;

				tmpIndexList.add(tmpIndexItem);
			}
		}

		// save also last letter
		tmpIndexItem = new Object[3];
		tmpIndexItem[0] = tmpLetter;
		tmpIndexItem[1] = tmpPos - 1;
		tmpIndexItem[2] = strArr.size() - 1;
		tmpIndexList.add(tmpIndexItem);

		// and remove first temporary empty entry
		if (tmpIndexList != null && tmpIndexList.size() > 0) {
			tmpIndexList.remove(0);
		}

		return tmpIndexList;
	}

	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);

		final ListView listView = (ListView) findViewById(R.id.findFrnd_listVw);
		LinearLayout sideIndex = (LinearLayout) findViewById(R.id.sideIndex);
		sideIndexHeight = sideIndex.getHeight();
		sideIndex.removeAllViews();

		// TextView for every visible item
		TextView tmpTV = null;

		// Collections.sort(record);

		// we'll create the index list
		indexList = createIndex(record);

		// number of items in the index List
		indexListSize = indexList.size();

		// maximal number of item, which could be displayed
		int indexMaxSize = (int) Math.floor(sideIndex.getHeight() / 20);

		int tmpIndexListSize = indexListSize;

		// handling that case when indexListSize > indexMaxSize
		while (tmpIndexListSize > indexMaxSize) {
			tmpIndexListSize = tmpIndexListSize / 2;
		}

		// computing delta (only a part of items will be displayed to save a
		// place)
		double delta = indexListSize / tmpIndexListSize;

		String tmpLetter = null;
		Object[] tmpIndexItem = null;

		// show every m-th letter
		for (double i = 1; i <= indexListSize; i = i + delta) {
			tmpIndexItem = indexList.get((int) i - 1);
			tmpLetter = tmpIndexItem[0].toString();
			tmpTV = new TextView(this);
			tmpTV.setText(tmpLetter);

			tmpTV.setGravity(Gravity.CENTER);
			tmpTV.setTextSize(15);

			String myPassedColor = "#FFFFFF";
			tmpTV.setTextColor(Color.parseColor(myPassedColor));
			//tmpTV.setBackgroundColor(Color.parseColor("#000000"));
			
			LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1);
			tmpTV.setLayoutParams(params);
			
			sideIndex.addView(tmpTV);
		}

		// and set a touch listener for it
		sideIndex.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// now you know coordinates of touch
				sideIndexX = event.getX();
				sideIndexY = event.getY();

				// and can display a proper item it country list
				displayListItem();

				return false;
			}
		});
	}

	class SideIndexGestureListener extends
			GestureDetector.SimpleOnGestureListener {
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			// we know already coordinates of first touch
			// we know as well a scroll distance
			sideIndexX = sideIndexX - distanceX;
			sideIndexY = sideIndexY - distanceY;

			// when the user scrolls within our side index
			// we can show for every position in it a proper
			// item in the country list
			if (sideIndexX >= 0 && sideIndexY >= 0) {
				displayListItem();
			}

			return super.onScroll(e1, e2, distanceX, distanceY);
		}
	}

	public void displayListItem() {
		// compute number of pixels for every side index item
		double pixelPerIndexItem = (double) sideIndexHeight / indexListSize;

		// compute the item index for given event position belongs to
		int itemPosition = (int) (sideIndexY / pixelPerIndexItem);

		// compute minimal position for the item in the list
		int minPosition = (int) (itemPosition * pixelPerIndexItem);

		// get the item (we can do it since we know item index)
		Object[] indexItem = indexList.get(itemPosition);

		// and compute the proper item in the country list
		int indexMin = Integer.parseInt(indexItem[1].toString());
		int indexMax = Integer.parseInt(indexItem[2].toString());
		int indexDelta = Math.max(1, indexMax - indexMin);

		double pixelPerSubitem = pixelPerIndexItem / indexDelta;
		int subitemPosition = (int) (indexMin + (sideIndexY - minPosition)
				/ pixelPerSubitem);

		ListView listView = (ListView) findViewById(R.id.findFrnd_listVw);
		listView.setSelection(subitemPosition);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (mGestureDetector.onTouchEvent(event)) {
			return true;
		} else {
			return false;
		}
	}

	public void callDefaultList() {
		tv_invite.setVisibility(View.VISIBLE);
		tv_play.setVisibility(View.INVISIBLE);

		btn_invite.setTextColor(android.graphics.Color.WHITE);
		btn_playing.setTextColor(android.graphics.Color.BLACK);
		btn_share.setTextColor(android.graphics.Color.BLACK);

		btn_invite.setBackgroundResource(R.drawable.tab_green_bg);
		btn_playing.setBackgroundResource(R.drawable.tab_white_bg);
		btn_share.setBackgroundResource(R.drawable.tab_white_bg);

		listview.setAdapter(new listDetails());
		// listview.setAdapter(new
		// ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
		// COUNTRIES));

		list_Invite = true;
	}

	public void OnClickHandler(View v) {
		switch (v.getId()) {
		case R.id.findFrnd_btn_Invite:

			tv_invite.setVisibility(View.VISIBLE);
			tv_play.setVisibility(View.INVISIBLE);

			btn_invite.setTextColor(android.graphics.Color.WHITE);
			btn_playing.setTextColor(android.graphics.Color.BLACK);
			btn_share.setTextColor(android.graphics.Color.BLACK);

			btn_invite.setBackgroundResource(R.drawable.tab_green_bg);
			btn_playing.setBackgroundResource(R.drawable.tab_white_bg);
			btn_share.setBackgroundResource(R.drawable.tab_white_bg);

			listview.setAdapter(new listDetails());

			list_Invite = true;
			break;

		case R.id.findFrnd_btn_Playing:

			tv_invite.setVisibility(View.INVISIBLE);
			tv_play.setVisibility(View.VISIBLE);

			btn_playing.setTextColor(android.graphics.Color.WHITE);
			btn_share.setTextColor(android.graphics.Color.BLACK);
			btn_invite.setTextColor(android.graphics.Color.BLACK);

			btn_invite.setBackgroundResource(R.drawable.tab_white_bg);
			btn_playing.setBackgroundResource(R.drawable.tab_green_bg);
			btn_share.setBackgroundResource(R.drawable.tab_white_bg);

			listview.setAdapter(new listDetails());

			list_Invite = false;

			break;

		case R.id.findFrnd_btn_Share:

			tv_invite.setVisibility(View.INVISIBLE);
			tv_play.setVisibility(View.INVISIBLE);
			btn_share.setTextColor(android.graphics.Color.WHITE);
			btn_playing.setTextColor(android.graphics.Color.BLACK);
			btn_invite.setTextColor(android.graphics.Color.BLACK);
			btn_invite.setBackgroundResource(R.drawable.tab_white_bg);
			btn_playing.setBackgroundResource(R.drawable.tab_white_bg);
			btn_share.setBackgroundResource(R.drawable.tab_green_bg);

			break;

		case R.id.findFrnd_btn_back:
			finish();
			break;
		default:

			break;

		}

	}

	public class listDetails extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return record.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View view, ViewGroup arg2) {
			// TODO Auto-generated method stub

			view = inflater.inflate(R.layout.findfriend_list_row, null);

			tv_frndname = (TextView) view
					.findViewById(R.id.findFrnd_listRow_name);
			tv_button = (TextView) view
					.findViewById(R.id.findFrnd_listRow_btnChlng);
			tv_inviteFrnd = (TextView) view
					.findViewById(R.id.findFrnd_listRow_chlgTxtVW);

			if (list_Invite) {
				// tv_chlngFrnd,tv_inviteFrnd,tv_button

				tv_inviteFrnd.setText("Invite Now");
				tv_button.setText("Invite");
			} else {

				tv_inviteFrnd.setText("Challenge Now");
				tv_button.setText("Challenge");
			}

			tv_frndname.setText(record.get(position).getFrndName());

			return view;
		}

	}
}
