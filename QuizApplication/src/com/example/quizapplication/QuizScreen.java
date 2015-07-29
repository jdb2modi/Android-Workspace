
package com.example.quizapplication;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import com.example.quizapplication.common.Global;
import com.example.quizapplication.model.ImageLoader;

public class QuizScreen extends Activity{

	private SharedPreferences preferences;
	public ImageLoader imageLoader;
	private TextView txtTimer,txtquestext,txtcurrentQues,txtUser,txtOpp;
	//TextView txtAns_1,txtAns_2,txtAns_3,txtAns_4;
	private ImageView Imgdusterline,ImgUserPhoto,ImgOppPhoto;
	private Global global;
	private Button btnAns_1,btnAns_2,btnAns_3,btnAns_4;
	private int i=0;
	private int currentQuestion=1,totalQuestion;
	private CountDownTimer cn,cnAnswer;
	private String Question,correct_Ans,actual_ans;
	String ans1,ans2,ans3,ans4;
	String[] option_name;
	int selectedOptions;
	private ArrayList<Integer> options_copy;
	private ArrayList<String> option_String;
	private LinearLayout ll;
	private int getSecs;
	private Animation animAnswer;
	private String Url = "http://122.170.97.189:81/iphonequiz/user_photos/";
	private SeekBar sb_Timer;
	private int milliSec;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.quizscreen);

		preferences = PreferenceManager
				.getDefaultSharedPreferences(QuizScreen.this);
		imageLoader = new ImageLoader(QuizScreen.this);
		txtTimer = (TextView) findViewById(R.id.txtstartforans);
		ll = (LinearLayout) findViewById(R.id.quizscreen_bodypart);
		ImgUserPhoto = (ImageView) findViewById(R.id.quizscreen_imgVw_user);
		ImgOppPhoto = (ImageView) findViewById(R.id.quizscreen_imgVw_opponent);
		txtUser = (TextView) findViewById(R.id.quizscreen_txt_name_user);
		txtOpp = (TextView)  findViewById(R.id.quizscreen_txt_name_opponent);
		txtquestext = (TextView) findViewById(R.id.quizscreen_txt_ask_question);
		txtcurrentQues = (TextView) findViewById(R.id.quizscreen_txt_totalQues);
		sb_Timer = (SeekBar) findViewById(R.id.seekBar_Question);
		global = (Global) getApplicationContext();
	
		option_String = new ArrayList<String>();
		options_copy = new ArrayList<Integer>();
		
		
		btnAns_1 = (Button) findViewById(R.id.quizscreen_op_1);
		btnAns_2 = (Button) findViewById(R.id.quizscreen_op_2);
		btnAns_3 = (Button) findViewById(R.id.quizscreen_op_3);
		btnAns_4 = (Button) findViewById(R.id.quizscreen_op_4);
		sb_Timer.setVisibility(ProgressBar.VISIBLE);
		String oppName = preferences.getString("u_name_pref", "");
		String oppPhoto = preferences.getString("opp_Pic_pref", "");

		//String userPhoto = preferences.getString(key, defValue)
		txtUser.setText("Me");
		txtOpp.setText(oppName);
		imageLoader.DisplayImage(oppPhoto,ImgUserPhoto);
		imageLoader.DisplayImage(Url + oppPhoto, ImgOppPhoto);
		txtquestext.setText(global.getSetQuestion().get(i).getQ_text());
		totalQuestion = global.getSetQuestion().size();

		
		
		
		displayQuestion();

		Log.e("OPtion",""+option_name);



	}
	public void OnClickHandler(View v) {
		switch (v.getId()) {
		case R.id.quizscreen_btn_remove_answer:
			Intent i_back = new Intent(QuizScreen.this, GameSummary.class);
			startActivity(i_back);
			break;
		case R.id.quizscreen_op_1:
			
			cnAnswer.cancel();
			sb_Timer.setProgress(milliSec);
			correct_Ans = btnAns_1.getText().toString();
			if(correct_Ans.equals(actual_ans))
			{
				btnAns_1.setBackgroundResource(R.drawable.custom_oval_g);
			}
			else
			{
				btnAns_1.setBackgroundResource(R.drawable.custom_oval_r);
			}

			i++;
			if(i<totalQuestion)
			{
				regularQustn();
			}
			else
			{
				cn.cancel();
				cnAnswer.cancel();
				Intent i_result = new Intent(QuizScreen.this,RoundResult.class);
				startActivity(i_result);
			}
			break;
		case R.id.quizscreen_op_2:
			
			cnAnswer.cancel();
			sb_Timer.setProgress(milliSec);
			correct_Ans = btnAns_2.getText().toString();
			if(correct_Ans.equals(actual_ans))
			{
				btnAns_2.setBackgroundResource(R.drawable.custom_oval_g);
			}
			else
			{
				btnAns_2.setBackgroundResource(R.drawable.custom_oval_r);
			}

			i++;
			if(i<totalQuestion)
			{
				regularQustn();
			}
			else
			{
				cn.cancel();
				cnAnswer.cancel();
				Intent i_result = new Intent(QuizScreen.this,GameSummary.class);
				startActivity(i_result);
			}
			break;
		case R.id.quizscreen_op_3:
		
			cnAnswer.cancel();
			sb_Timer.setProgress(milliSec);
			correct_Ans = btnAns_3.getText().toString();
			if(correct_Ans.equals(actual_ans))
			{
				btnAns_3.setBackgroundResource(R.drawable.custom_oval_g);
			}
			else
			{
				btnAns_3.setBackgroundResource(R.drawable.custom_oval_r);
			}

			i++;
			if(i<totalQuestion)
			{
				regularQustn();
			}
			else
			{
				cn.cancel();
				cnAnswer.cancel();
				Intent i_result = new Intent(QuizScreen.this,GameSummary.class);
				startActivity(i_result);
			}
			break;
		case R.id.quizscreen_op_4:
			
			cnAnswer.cancel();
			sb_Timer.setProgress(milliSec);

			correct_Ans = btnAns_4.getText().toString();
			if(correct_Ans.equals(actual_ans))
			{
				btnAns_4.setBackgroundResource(R.drawable.custom_oval_g);
			}
			else
			{
				btnAns_4.setBackgroundResource(R.drawable.custom_oval_r);
			}

			i++;
			if(i<totalQuestion)
			{

				regularQustn();
			}
			else
			{
				cn.cancel();
				cnAnswer.cancel();
				Intent i_result = new Intent(QuizScreen.this,GameSummary.class);
				startActivity(i_result);
			}
			break;
		case R.id.questnImage :

		case R.id.largeImage :
		

		}
	}


	public void displayQuestion()
	{
		/*imgLarge.setVisibility(View.VISIBLE);
		imgSmall.setVisibility(View.VISIBLE);

		if(i==2)
		{
			//slideUp = AnimationUtils.loadAnimation(QuestionList.this, R.anim.enlargeimage);
			imgLarge.setBackgroundResource(R.drawable.ic_launcher);
			imgLarge.bringToFront();
			//imgLarge.startAnimation(slideUp);

		}
		else
		{
			imgLarge.setVisibility(View.INVISIBLE);
			imgSmall.setVisibility(View.INVISIBLE);
		}*/
		cn =  new CountDownTimer(6000,1000) {

			@Override
			public void onTick(long millisUntilFinished) {



				ll.setVisibility(View.GONE);	
				if(i<totalQuestion)
				{
					milliSec=0;
					txtcurrentQues.setText( " " + currentQuestion + "/" + "5");
					Question = global.getSetQuestion().get(i).getQ_text();
					correct_Ans = global.getSetQuestion().get(i).getCorrect_ans();
					txtquestext.setText(Question);
					long milli = millisUntilFinished/1000;
					Log.e("Milli u r here for : ", milli + "," + i);
					txtTimer.setVisibility(View.VISIBLE);
					txtTimer.setText("" + milli);
					sb_Timer.setMax(10000);

				}
				else
				{
					cn.cancel();
					cnAnswer.cancel();

				}
			}

			@Override
			public void onFinish() {
				if(i<totalQuestion)
				{
					txtTimer.setVisibility(View.GONE);
					btnAns_1.setBackgroundResource(R.drawable.custom_oval_w);
					btnAns_2.setBackgroundResource(R.drawable.custom_oval_w);
					btnAns_3.setBackgroundResource(R.drawable.custom_oval_w);
					btnAns_4.setBackgroundResource(R.drawable.custom_oval_w);




					/*btnAns_1.setText(SpinGameCategory.arr_ques_details.get(i).getAns_text1());
				btnAns_2.setText(SpinGameCategory.arr_ques_details.get(i).getAns_text2());
				btnAns_3.setText(SpinGameCategory.arr_ques_details.get(i).getAns_text3());
				btnAns_4.setText(SpinGameCategory.arr_ques_details.get(i).getAns_text4());*/


					ans1 = global.getSetQuestion().get(i).getAns_text1();
					ans2 = global.getSetQuestion().get(i).getAns_text2();
					ans3 = global.getSetQuestion().get(i).getAns_text3();
					ans4 = global.getSetQuestion().get(i).getAns_text4();
					actual_ans = ans1;

					option_name = new String[] {ans1,ans2,ans3,ans4};
					for(int i =0;i<4;i++)
					{
						option_String.add(option_name[i]);
						Log.e("option_name : ", "" + option_name[i]);
					}

					Collections.shuffle(option_String);
					//Random random1 = new Random();
					//selectedOptions = random1.nextInt(option_name.length);



						

					/*while (options_copy.size() <= 4) {
						selectedOptions = random1.nextInt(15);
						if (options_copy.size() != 4) {
							if(selectedOptions< 4)
							{
								if (options_copy.size() == 0) {
									options_copy.add(selectedOptions);
								} else {
									for (int k = 0; k < options_copy.size(); k++) {

										if (!options_copy.contains(selectedOptions)) {

											options_copy.add(selectedOptions);
										}
									}
								}
							}
						}

						else {
							if (options_copy.size() == 4)

							{
								break;
							}
						}
					}
*/
					for(int i =0;i<4;i++)
					{

						//Log.e("Array Data : ", "" + options_copy.get(i) + "," + options_copy.size());
						Log.e("Array Data : ", "" + option_String.get(i) + "," + option_String.size());
					}


					/*btnAns_1.setText(option_name[options_copy.get(0)]);
					btnAns_2.setText(option_name[options_copy.get(1)]);
					btnAns_3.setText(option_name[options_copy.get(2)]);
					btnAns_4.setText(option_name[options_copy.get(3)]);*/
					btnAns_1.setText(option_String.get(0));
					btnAns_2.setText(option_String.get(1));
					btnAns_3.setText(option_String.get(2));
					btnAns_4.setText(option_String.get(3));

					//options_copy.clear();
					option_String.clear();	
					animAnswer = AnimationUtils.loadAnimation(QuizScreen.this, R.anim.in_from_right);
					ll.startAnimation(animAnswer);
					ll.setVisibility(View.VISIBLE);
					Log.e("U r here in first finish : ", "" + i);
					answerTenSec();
				}
			}

		};
		cn.start();	

	}

	public void answerTenSec()
	{

		try
		{

			cnAnswer =  new CountDownTimer(11000,1000){
				@Override
				public void onTick(long millisUntilFinished) {
					ll.setVisibility(View.VISIBLE);
					long milli = millisUntilFinished/1000;
					getSecs = (int) milli;
					milliSec +=1000;
					/*	Log.e("Milli u r here in next timer : ", milli + "," + i);
					txtTimer.setText("" + milli);*/
					sb_Timer.postDelayed(onEverySecond, 1000);

				}

				@Override
				public void onFinish() {

					//imgLarge.setVisibility(View.GONE);
					//imgSmall.setVisibility(View.GONE);
					milliSec=0;
					ll.setVisibility(View.INVISIBLE);
					i++;
					currentQuestion++;
					if(i<totalQuestion)
					{
						//txtTimer.setVisibility(View.VISIBLE);
						displayQuestion();
					}
					else
					{
						cn.cancel();
						cnAnswer.cancel();
						Intent i_result = new Intent(QuizScreen.this,GameSummary.class);
						startActivity(i_result);
					}

				}

			};
			cnAnswer.start();		

		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public void regularQustn()
	{
		Log.e("Clicked : ", "Ur Secs is :" + getSecs );
		cnAnswer.cancel();



		currentQuestion++;

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		displayQuestion();	
	}


	@Override
	public void onBackPressed() {
		super.onBackPressed();
		cn.cancel();
		cnAnswer.cancel();
	}
	
	
	private Runnable onEverySecond=new Runnable() {

		@Override
		public void run() {

			if(sb_Timer != null) {
				sb_Timer.setProgress(milliSec);
				sb_Timer.postDelayed(onEverySecond, 1000);
			}

		}
	};


}
