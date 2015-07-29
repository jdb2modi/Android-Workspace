package com.example.quizapplication;

import java.util.Timer;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;


public class QuestionList extends Activity {
	TextView txtTimer,txtques,txtquestext,txtcurrentQues;
	ImageView imgSmall,imgLarge;
	ViewFlipper vf;
	int i=1;
	CountDownTimer cn,cnAnswer;
	Timer timer;
	String Question;
	LinearLayout ll;
	int getSecs;
	Animation slideUp,Answer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listofquestion);
		txtTimer = (TextView) findViewById(R.id.txtstartforans);
		txtques = (TextView) findViewById(R.id.quizscreen_txt_question);
		txtquestext = (TextView) findViewById(R.id.quizscreen_txt_questionText);
		txtcurrentQues = (TextView) findViewById(R.id.quizscreen_txt_totalQues);
		//vf = (ViewFlipper) findViewById(R.id.vfmain);
		ll = (LinearLayout) findViewById(R.id.llAnswer);
		imgSmall = (ImageView) findViewById(R.id.questnImage);
		imgLarge = (ImageView) findViewById(R.id.largeImage);


		Question = "Hello World_" + i;
		txtquestext.setText(Question);
		displayQuestion();
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
					Log.e("Milli u r here in next timer : ", milli + "," + i);
					txtTimer.setText("" + milli);
				}

				@Override
				public void onFinish() {

					imgLarge.setVisibility(View.GONE);
					imgSmall.setVisibility(View.GONE);
					ll.setVisibility(View.INVISIBLE);
					i++;
					if(i<6)
					{
						txtTimer.setVisibility(View.VISIBLE);
						displayQuestion();
					}
					else
					{
						cn.cancel();
						cnAnswer.cancel();
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


	public void displayQuestion()
	{
		imgLarge.setVisibility(View.VISIBLE);
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
		}
		cn =  new CountDownTimer(6000,1000) {

			@Override
			public void onTick(long millisUntilFinished) {


			
				ll.setVisibility(View.GONE);	
				if(i<6)
				{
					txtcurrentQues.setText(i + "/" + "5");
					Question = "Hello World_" + i;
					txtquestext.setText(Question);
					long milli = millisUntilFinished/1000;
					Log.e("Milli u r here for : ", milli + "," + i);
					txtTimer.setVisibility(View.VISIBLE);
					txtTimer.setText("" + milli);
				}
				else
				{
					cn.cancel();
					cnAnswer.cancel();
				}
			}

			@Override
			public void onFinish() {
				if(i<6)
				{
				txtTimer.setVisibility(View.GONE);
				Answer = AnimationUtils.loadAnimation(QuestionList.this, R.anim.in_from_right);
				ll.startAnimation(Answer);
				ll.setVisibility(View.VISIBLE);
				Log.e("U r here in first finish : ", "" + i);
				answerTenSec();
				}
			}

		};
		cn.start();	

	}


	public void OnClickHandler(View v)
	{
		switch (v.getId()) {
		case R.id.txtAns1:
			Log.e("Clicked : ", "Ur Secs is :" + getSecs );
			cnAnswer.cancel();
			i++;
			displayQuestion();		
			break;
		case R.id.txtAns2:
			Log.e("Clicked : ", "Ur Secs is :" + getSecs );
			cnAnswer.cancel();
			i++;
			displayQuestion();		
			break;
		case R.id.txtAns3:
			Log.e("Clicked : ", "Ur Secs is :" + getSecs );
			cnAnswer.cancel();
			i++;
			displayQuestion();		
			break;
		case R.id.txtAns4:
			Log.e("Clicked : ", "Ur Secs is :" + getSecs );
			cnAnswer.cancel();
			i++;
			displayQuestion();		
			break;
		case R.id.questnImage :
			
			Log.e("U clicked Question image :", "U clicked Question image");
			imgLarge.setVisibility(View.VISIBLE);
			imgSmall.setVisibility(View.VISIBLE);
			//slideUp = AnimationUtils.loadAnimation(QuestionList.this, R.anim.enlargeimage);
			imgLarge.setBackgroundResource(R.drawable.ic_launcher);
			imgLarge.bringToFront();
			//imgLarge.startAnimation(slideUp);
		case R.id.largeImage :
			//slideUp = AnimationUtils.loadAnimation(QuestionList.this, R.anim.smallimage);
			imgSmall.setBackgroundResource(R.drawable.ic_launcher);
			//imgSmall.startAnimation(slideUp);
			imgLarge.setVisibility(View.GONE);


		default:
			break;
		}

	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		cn.cancel();
		cnAnswer.cancel();
	}

}
