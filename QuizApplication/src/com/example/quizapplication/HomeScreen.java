package com.example.quizapplication;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.quizapplication.SpinGameCategory.GetQuestionList;
import com.example.quizapplication.common.Common;
import com.example.quizapplication.common.Global;
import com.example.quizapplication.model.ImageLoader;
import com.example.quizapplication.model.Model;
import com.example.quizapplication.model.Question_Details;

public class HomeScreen extends Activity {

	//ImageButton imgBtn_slider;
	//@+id/homeScreen_imgbtn_Slider
	TextView tv_username, tv_opponentname, tv_userscore, tv_opponentscore, tv_status;
	ListView listview;
	private long totalmilisec;
 
	private TextView txthr1,txthr2,txtmin1,txtmin2,txtsec1,txtsec2,txtday1,txtday2;
	private ViewFlipper vf;
	Global global;
	private ImageButton ImgNext,Imprev;
	String strHr,strMin,strSec,strDay,u_id,result,str,opp_photo,u_status,cat_ID,opp_ID;   
	
	RelativeLayout weekly_score,tournamentScre;
	LayoutInflater inflater;
	SharedPreferences prefrences;

	ArrayList<Model> friend_list;
	ArrayList<Question_Details> arr_ques_details;
	
	View v1;
	Model model;
	
	ImageView img_opp;
	
	ProgressDialog pd;
	Common common;

	final Context context = this;
	
	public ImageLoader imageLoader;
	String Url = "http://122.170.97.189:81/iphonequiz/user_photos/";

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.homescreen);
		
		listview = (ListView) findViewById(R.id.homeScreen_listVw);
		
		
		friend_list = new ArrayList<Model>();
		pd = new ProgressDialog(HomeScreen.this);
		
		weekly_score = (RelativeLayout) findViewById(R.id.homeScreen_relative_week_ScoreCard);
		tournamentScre = (RelativeLayout) findViewById(R.id.homeScreen_relative_ScoreCard);
		//inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater = LayoutInflater.from(context);
		
		prefrences = PreferenceManager.getDefaultSharedPreferences(this);
		common = new Common(this);
		arr_ques_details = new ArrayList<Question_Details>();
		global = (Global) getApplicationContext();
		imageLoader = new ImageLoader(HomeScreen.this);
		//opp_photo = prefrences.getString("opp_Pic_pref", "");
		
		
		u_id = prefrences.getString("u_id_Pref", "");
		
		v1 = inflater.inflate(R.layout.homescreen_bottom_btns, null);
		
		vf = (ViewFlipper) findViewById(R.id.ViewFlipper);
		ImgNext = (ImageButton) findViewById(R.id.homeScreen_imgbtn_SliderL);
		Imprev =  (ImageButton)  findViewById(R.id.homeScreen_imgbtn_Slider);
		txthr1 = (TextView) findViewById(R.id.homeScreen_tv_time_HR_1);
		txthr2 = (TextView) findViewById(R.id.homeScreen_tv_time_HR_2);
		txtmin1 = (TextView) findViewById(R.id.homeScreen_tv_time_Min_1);
		txtmin2 = (TextView) findViewById(R.id.homeScreen_tv_time_Min_2);
		txtsec1 = (TextView) findViewById(R.id.homeScreen_tv_time_Sec_1);
		txtsec2 = (TextView) findViewById(R.id.homeScreen_tv_time_Sec_2);
		txtday1 = (TextView)findViewById(R.id.homeScreen_tv_time_Day_1);
		txtday2 = (TextView)findViewById(R.id.homeScreen_tv_time_Day_2);
		
		//listview.addFooterView();
		//homeScreen_linear_Bottom
		
		listview.addFooterView(v1);
		
		listview.setAdapter(new listDetails());
		
		new GetFriendList().execute();
		
	
		
		totalmilisec = 604800000;
		CountDownTimer cn = new CountDownTimer(totalmilisec,1000) {
			@SuppressWarnings("static-access")
			@Override
			public void onTick(long millisUntilFinished) {
				
				
				long milli = millisUntilFinished;
				long hour = ((milli / (1000*60*60)) % 24);
				long  min = ((milli / (1000*60)) % 60);
				long sec = (milli / 1000) % 60;
				long day = milli / (1000*60*60*24);

				strSec  = strSec.valueOf(sec);
			
				strMin = strMin.valueOf(min);
				
				strHr = strHr.valueOf(hour);
				strDay = strDay.valueOf(day);
				

				String sec1 = strSec.substring(0,1);
				String sec2 = strSec.substring(1);

				String min1 = strMin.substring(0,1);
				String min2 = strMin.substring(1);

				String Hr1 = strHr.substring(0,1);
				String Hr2 = strHr.substring(1);
				
				String dy1 = strDay.substring(0,1);
				String dy2 = strDay.substring(1);



				if(sec<10)
				{
					txtsec1.setText("0");
					txtsec2.setText(sec1);
				}
				else
				{
					txtsec1.setText(sec1);
					txtsec2.setText(sec2);

				}
				if(min < 10)
				{
					txtmin1.setText("0");
					txtmin2.setText(min1);
				}
				else
				{
					txtmin1.setText(min1);
					txtmin2.setText(min2);
				}

				if(hour<10)
				{
					txthr1.setText("0");
					txthr2.setText(Hr1);
				}
				else
				{
					txthr1.setText(Hr1);
					txthr2.setText(Hr2);
				}
				if(day<10)
				{
					txtday1.setText("0");
					txtday2.setText(dy1);
				}
				else
				{
					txtday1.setText(dy1);
					txtday2.setText(dy2);
				}

			}
			@Override
			public void onFinish() {


			}
		};
		cn.start();
	}
	
	
	
	public void OnClickHandler(View v) {
		switch (v.getId()) {
		case R.id.homeScreen_btn_creategame:

			Intent i = new Intent(HomeScreen.this, CreateGame.class);
			startActivity(i);
			break;

		case R.id.homeScreen_imgbtn_topSetting:

			Intent i_setting = new Intent(HomeScreen.this, AccountSetting.class);
			startActivity(i_setting);
			break;
			
		case R.id.homeScreen_btn_findFrnds:

			Intent i_frnds = new Intent(HomeScreen.this, FindFriends.class);
			startActivity(i_frnds);
			break;
			
		case R.id.homeScreen_btn_bottom_creategame:
			
			Intent i_go = new Intent(HomeScreen.this, CreateGame.class);
			startActivity(i_go);
			break;
			

		case R.id.homeScreen_imgbtn_Slider:

			vf.setInAnimation(this, R.anim.in_from_right);
			vf.setOutAnimation(this, R.anim.out_to_left);
			vf.showNext();
			vf.getInAnimation().setAnimationListener(new Animation.AnimationListener() {
				public void onAnimationStart(Animation animation) {
					ImgNext.setVisibility(View.INVISIBLE);
				}
				public void onAnimationRepeat(Animation animation) {}
				public void onAnimationEnd(Animation animation) {
					ImgNext.setVisibility(View.VISIBLE);
				}
			});
			break;
			
		case R.id.homeScreen_imgbtn_SliderL:

			vf.setInAnimation(this, R.anim.in_from_left);
			vf.setOutAnimation(this, R.anim.out_to_right);
			vf.showPrevious();
			vf.getInAnimation().setAnimationListener(new Animation.AnimationListener() {
				public void onAnimationStart(Animation animation) {
					Imprev.setVisibility(View.INVISIBLE);

				}
				public void onAnimationRepeat(Animation animation) {}
				public void onAnimationEnd(Animation animation) {
					Imprev.setVisibility(View.VISIBLE);
				}
			});
			break;
			
		case R.id.homeScreen_btn_shop:
			
			Intent i_shop = new Intent(HomeScreen.this, Shop.class);
			startActivity(i_shop);
			break;
			
		case R.id.homescreen_listrow_state:
			
			/*if(u_status.equals("Accept"))
			{
				new GetQuestionList().execute();
				
			}
			break;*/
		default:

			break;

		}

	}
	
	public class listDetails extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			Log.e("Array Size in listDetails: ", "" + friend_list.size());
			return friend_list.size();
			
			
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
			
		
			view = inflater.inflate(R.layout.homescreen_list_row, null);

			
			tv_opponentname = (TextView)view.findViewById(R.id.homescreen_listrow_opponent_name);
			
			tv_opponentscore = (TextView)view.findViewById(R.id.homescreen_listrow_opponent_score);
			tv_status = (TextView)view.findViewById(R.id.homescreen_listrow_state);
			
			img_opp = (ImageView)view.findViewById(R.id.homescreen_listrow_imgV);
		
			String pic = friend_list.get(position).getOpp_photo();
			
			
			
			tv_opponentname.setText(friend_list.get(position).getOpponentname());
			tv_opponentscore.setText(friend_list.get(position).getOpponentscore());
			tv_status.setText(friend_list.get(position).getU_status());
			cat_ID = friend_list.get(position).getCat_ID();
			
			u_status = tv_status.getText().toString();
			opp_ID = friend_list.get(position).getOpp_ID();		
					
			imageLoader.DisplayImage(Url + pic, img_opp);
			
			if(u_status.equals("Accept"))
			{
				tv_status.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (common.checkInternetConnection(HomeScreen.this))
					{
					new GetQuestionList().execute();
					}
				}
			});
			}
			
			return view;
		}

	}
	
	
	public class GetQuestionList extends AsyncTask<Void, Void, Void> {

		Question_Details q_details;
		ArrayList<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();

		protected void onPreExecute() {
			super.onPreExecute();
			pd = new ProgressDialog(HomeScreen.this);
			pd.setTitle(getString(R.string.app_name));
			pd.setCancelable(false);
			pd.setMessage("Loading.....");
			pd.show();
	
		}

		@Override
		protected Void doInBackground(Void... params) {
			try {

				JSONObject jObj = new JSONObject();
				jObj.put("ModMethod", "getQuestionList");
				jObj.put("u_id", "2");
				jObj.put("cat_id",cat_ID);
				jObj.put("opp_id",opp_ID);
				jObj.put("u_diamond", "");
				
			
				nameValuePair.add(new BasicNameValuePair("data", jObj
						.toString()));
				result = common.call(nameValuePair,"http://122.170.97.189:81/iphonequiz/webservice/userClass.php");
				Log.e("Response : ", result);
			} catch (Exception e) {
				e.printStackTrace();
				Log.e("U R in Error part :", "Here Comes the error in background");
			}

			return null;
		}

		protected void onPostExecute(Void result1) {
			if (pd != null && pd.isShowing())
				pd.dismiss();


		try {

				if (result != null) {

			

					JSONObject jsonObject = new JSONObject(result);
					str = "" + jsonObject.getInt("code");
					if (str != null && str.equals("1")) {
						Log.e("Success : ", "Done");
						JSONArray jsonmsgarr = jsonObject.getJSONArray("output");
						for (int i = 0; i < jsonmsgarr.length(); i++) {
							JSONObject jsonarrObject = jsonmsgarr.getJSONObject(i);
							
							Log.e("User_id", jsonarrObject.optString("u_id"));
							Log.e("fb_id", jsonarrObject.optString("fb_id"));
							
							q_details = new Question_Details();
							q_details.setTbl_id(jsonarrObject.optString("tbl_id"));
							q_details.setQ_id(jsonarrObject.optString("q_id"));
							q_details.setQ_type(jsonarrObject.optString("q_type"));
							q_details.setQ_image(jsonarrObject.optString("q_image"));
							q_details.setQ_text(jsonarrObject.optString("q_description"));
							q_details.setAns_text1(jsonarrObject.optString("ans_text1"));
							q_details.setAns_text2(jsonarrObject.optString("ans_text2"));
							q_details.setAns_text3(jsonarrObject.optString("ans_text3"));
							q_details.setAns_text4(jsonarrObject.optString("ans_text4"));
							q_details.setCorrect_ans(jsonarrObject.optString("ans_correct"));
							q_details.setOpp_score(jsonarrObject.optString("opp_score"));
							q_details.setOpp_time(jsonarrObject.optString("opp_time_elapsed"));
							q_details.setU_Type(jsonarrObject.optString("u_type"));
							arr_ques_details.add(q_details);
							global.setSetQuestion(arr_ques_details);							
							Intent i_category = new Intent(HomeScreen.this,CategoryList.class);
							startActivity(i_category);


						}
					}
					else
					{
						
					}

					Log.e("Array Size : ", "" + arr_ques_details.size());
					Log.e("Array Size in global : ", "" + global.getSetQuestion().size());

				}
			} 
			catch (JSONException e) {
				e.printStackTrace();
				Log.e("U R in Error part :", "Here Comes the error in Post Execute ");
			}
			super.onPostExecute(result1);
		}
	}	
	
	
	public class GetFriendList extends AsyncTask<Void, Void, Void> {

		//Question_Details q_details;
		Model model_friend_list;
		
		ArrayList<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();

		protected void onPreExecute() {
			super.onPreExecute();
			pd = new ProgressDialog(HomeScreen.this);
			pd.setTitle(getString(R.string.app_name));
			pd.setCancelable(false);
			pd.setMessage("Loading.....");
			pd.show();
	
		}

		@Override
		protected Void doInBackground(Void... params) {
			try {

				JSONObject jObj = new JSONObject();
				jObj.put("ModMethod", "getFriendList");
				//jObj.put("u_id", u_id);
				jObj.put("u_id", "2");
			

				nameValuePair.add(new BasicNameValuePair("data", jObj
						.toString()));
				result = common.call(nameValuePair,"http://122.170.97.189:81/iphonequiz/webservice/userClass.php");
				Log.e("Response : ", result);
			} catch (Exception e) {
				e.printStackTrace();
				Log.e("U R in Error part :", "Here Comes the error in background");
			}

			return null;
		}

		protected void onPostExecute(Void result1) {
			if (pd != null && pd.isShowing())
				pd.dismiss();


		try {

				if (result != null) {

				
					JSONObject jsonObject = new JSONObject(result);
					str = "" + jsonObject.getInt("code");
					if (str != null && str.equals("1")) {
					
						JSONArray jsonmsgarr = jsonObject.getJSONArray("output");
						for (int i = 0; i < jsonmsgarr.length(); i++) {
							JSONObject jsonarrObject = jsonmsgarr.getJSONObject(i);
							
							model_friend_list = new Model();
													
							
							model_friend_list.setOpponentname(jsonarrObject.optString("u_name"));
							model_friend_list.setOpponentscore(jsonarrObject.optString("u_score"));
							model_friend_list.setU_status(jsonarrObject.optString("u_status"));
							model_friend_list.setOpp_photo(jsonarrObject.optString("u_photo")); 
							model_friend_list.setCat_ID(jsonarrObject.optString("cat_id"));
							model_friend_list.setOpp_ID(jsonarrObject.optString("u_id"));
														
							friend_list.add(model_friend_list);
													
							

						}
					}
					else
					{
						
					}

					Log.e("Array Size : ", "" + friend_list.size());
					
				}
			} 
			catch (JSONException e) {
				e.printStackTrace();
				Log.e("U R in Error part :", "Here Comes the error in Post Execute ");
			}
			super.onPostExecute(result1);
		}
	}	


}
