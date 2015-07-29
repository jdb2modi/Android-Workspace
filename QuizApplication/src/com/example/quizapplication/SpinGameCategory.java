package com.example.quizapplication;





import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import com.example.quizapplication.common.Common;
import com.example.quizapplication.common.Global;
import com.example.quizapplication.model.Question_Details;

public class SpinGameCategory extends Activity implements OnItemSelectedListener, OnItemClickListener{
	
	private ProgressDialog pd;
	private Common common;
	MediaPlayer mp;
	private String Opp_id,str,result,user_id;
	private SharedPreferences preferences;
	private  ArrayList<Question_Details> arr_ques_details;
	private Global global;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.spingamecategory);
		global = (Global) getApplicationContext();
		mp= MediaPlayer.create(SpinGameCategory.this, R.raw.button);
		common = new Common(this);
		common = new Common(SpinGameCategory.this);
		arr_ques_details = new ArrayList<Question_Details>();
		preferences = PreferenceManager
				.getDefaultSharedPreferences(SpinGameCategory.this);
		Opp_id = preferences.getString("opp_id_pref", "");
		user_id = preferences.getString("u_id_Pref", "");
		
		Log.e("oppoenet Id : ", Opp_id);
		Log.e("User Id : ", user_id);
		/*CircleLayout circleMenu = (CircleLayout)findViewById(R.id.spingame_imgVw_spincircle);
		circleMenu.setOnItemSelectedListener(SpinGameCategory.this);
		circleMenu.setOnItemClickListener(SpinGameCategory.this);*/
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View view, int position,long id)
	{
		
		switch (view.getId())
		{
		case R.id.spingame_imgVw_spincircle:
			break;
		}
	
		
	}

	

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		switch (view.getId())
		{
		case R.id.spingame_imgVw_spincircle:
			break;
		}
	
		
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void OnClickHandler(View v)
	{
		switch (v.getId()) {
		case R.id.spingame_btn_next:
			
			mp.start();
			if (common.checkInternetConnection(SpinGameCategory.this))
			{
			
			new GetQuestionList().execute();
			}
			else
			{
				Toast.makeText(getApplicationContext(), "Please check your Internet Connection first", Toast.LENGTH_SHORT).show(); 

			}
			
			if(result!=null)
			{
				
			}
			break;

		default:
			break;
		}
	}


	public class GetQuestionList extends AsyncTask<Void, Void, Void> {

		Question_Details q_details;
		ArrayList<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();

		protected void onPreExecute() {
			super.onPreExecute();
			pd = new ProgressDialog(SpinGameCategory.this);
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
				jObj.put("u_id", user_id);
				jObj.put("cat_id", "2");
				jObj.put("opp_id",Opp_id);
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
							Intent i_category = new Intent(SpinGameCategory.this,CategoryList.class);
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

}
