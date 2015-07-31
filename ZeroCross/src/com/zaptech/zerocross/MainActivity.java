package com.zaptech.zerocross;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	private ImageButton mImgBtn1;
	private ImageButton mImgBtn2;
	private ImageButton mImgBtn3;
	private ImageButton mImgBtn4;
	private ImageButton mImgBtn5;
	private ImageButton mImgBtn6;
	private ImageButton mImgBtn7;
	private ImageButton mImgBtn8;
	private ImageButton mImgBtn9;
	private Button mButtonReplay;
	private int flag1, flag2, flag3, flag4, flag5, flag6, flag7, flag8, flag9;
	private int lflag1, lflag2, lflag3, lflag4, lflag5, lflag6, lflag7, lflag8,
			lflag9;
	private int flagOver, flagDecideResult;
	private ArrayList<String> arrayListSelected;
	private String userSelection;
	private String cpuSelection;
	private RelativeLayout relMainBox;

	private Animation animRotate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();

	}

	public void init() {
		mImgBtn1 = (ImageButton) findViewById(R.id.imgBtnOne);
		mImgBtn1.setOnClickListener(this);
		mImgBtn2 = (ImageButton) findViewById(R.id.imgBtnTwo);
		mImgBtn2.setOnClickListener(this);
		mImgBtn3 = (ImageButton) findViewById(R.id.imgBtnThree);
		mImgBtn3.setOnClickListener(this);
		mImgBtn4 = (ImageButton) findViewById(R.id.imgBtnFour);
		mImgBtn4.setOnClickListener(this);
		mImgBtn5 = (ImageButton) findViewById(R.id.imgBtnFive);
		mImgBtn5.setOnClickListener(this);
		mImgBtn6 = (ImageButton) findViewById(R.id.imgBtnSix);
		mImgBtn6.setOnClickListener(this);
		mImgBtn7 = (ImageButton) findViewById(R.id.imgBtnSeven);
		mImgBtn7.setOnClickListener(this);
		mImgBtn8 = (ImageButton) findViewById(R.id.imgBtnEight);
		mImgBtn8.setOnClickListener(this);
		mImgBtn9 = (ImageButton) findViewById(R.id.imgBtnNine);
		mImgBtn9.setOnClickListener(this);
		arrayListSelected = new ArrayList<String>();

		arrayListSelected.add("1");
		arrayListSelected.add("2");
		arrayListSelected.add("3");
		arrayListSelected.add("4");
		arrayListSelected.add("5");
		arrayListSelected.add("6");
		arrayListSelected.add("7");
		arrayListSelected.add("8");
		arrayListSelected.add("9");

		mButtonReplay = (Button) findViewById(R.id.btnPlayAgain);
		mButtonReplay.setOnClickListener(this);

		relMainBox = (RelativeLayout) findViewById(R.id.rel_mainBox);
	}

	public void checkWin() {

		if (flag1 == 1 && flag2 == 2 && flag3 == 3) {
			enabled123();
			Toast.makeText(MainActivity.this, "You win..!", Toast.LENGTH_LONG)
					.show();
			flagOver = 1;
			flagDecideResult = 1;

		} else if (flag4 == 4 && flag5 == 5 && flag6 == 6) {
			enabled456();
			Toast.makeText(MainActivity.this, "You win..!", Toast.LENGTH_LONG)
					.show();
			flagOver = 1;
			flagDecideResult = 1;

		} else if (flag7 == 7 && flag8 == 8 && flag9 == 9) {
			enabled789();
			Toast.makeText(MainActivity.this, "You win..!", Toast.LENGTH_LONG)
					.show();
			flagOver = 1;
			flagDecideResult = 1;

		} else if (flag1 == 1 && flag4 == 4 && flag7 == 7) {
			enabled147();
			Toast.makeText(MainActivity.this, "You win..!", Toast.LENGTH_LONG)
					.show();
			flagOver = 1;
			flagDecideResult = 1;

		} else if (flag2 == 2 && flag5 == 5 && flag8 == 8) {
			enabled258();
			Toast.makeText(MainActivity.this, "You win..!", Toast.LENGTH_LONG)
					.show();
			flagOver = 1;
			flagDecideResult = 1;

		} else if (flag3 == 3 && flag6 == 6 && flag9 == 9) {
			enabled369();
			Toast.makeText(MainActivity.this, "You win..!", Toast.LENGTH_LONG)
					.show();
			flagOver = 1;
			flagDecideResult = 1;

		} else if (flag1 == 1 && flag5 == 5 && flag9 == 9) {
			enabled159();
			Toast.makeText(MainActivity.this, "You win..!", Toast.LENGTH_LONG)
					.show();
			flagOver = 1;
			flagDecideResult = 1;

		} else if (flag3 == 3 && flag5 == 5 && flag7 == 7) {
			enabled357();
			Toast.makeText(MainActivity.this, "You win..!", Toast.LENGTH_LONG)
					.show();
			flagOver = 1;
			flagDecideResult = 1;
		} else {

		}
	}

	public void checkLoose() {
		if (lflag1 == 1 && lflag2 == 2 && lflag3 == 3) {
			enabled123();
			Toast.makeText(MainActivity.this, "You loose..!", Toast.LENGTH_LONG)
					.show();
			flagOver = 1;
			flagDecideResult = 1;

		} else if (lflag4 == 4 && lflag5 == 5 && lflag6 == 6) {
			enabled456();
			Toast.makeText(MainActivity.this, "You loose..!", Toast.LENGTH_LONG)
					.show();
			flagOver = 1;
			flagDecideResult = 1;
		} else if (lflag7 == 7 && lflag8 == 8 && lflag9 == 9) {
			enabled789();
			Toast.makeText(MainActivity.this, "You loose..!", Toast.LENGTH_LONG)
					.show();
			flagOver = 1;
			flagDecideResult = 1;
		} else if (lflag1 == 1 && lflag4 == 4 && lflag7 == 7) {
			enabled147();
			Toast.makeText(MainActivity.this, "You loose..!", Toast.LENGTH_LONG)
					.show();
			flagOver = 1;
			flagDecideResult = 1;
		} else if (lflag2 == 2 && lflag5 == 5 && lflag8 == 8) {
			enabled258();
			Toast.makeText(MainActivity.this, "You loose..!", Toast.LENGTH_LONG)
					.show();
			flagOver = 1;
			flagDecideResult = 1;
		} else if (lflag3 == 3 && lflag6 == 6 && lflag9 == 9) {
			enabled369();
			Toast.makeText(MainActivity.this, "You loose..!", Toast.LENGTH_LONG)
					.show();
			flagOver = 1;
			flagDecideResult = 1;
		} else if (lflag1 == 1 && lflag5 == 5 && lflag9 == 9) {
			enabled159();
			Toast.makeText(MainActivity.this, "You loose..!", Toast.LENGTH_LONG)
					.show();
			flagOver = 1;
			flagDecideResult = 1;
		} else if (lflag3 == 3 && lflag5 == 5 && lflag7 == 7) {
			enabled357();
			Toast.makeText(MainActivity.this, "You loose..!", Toast.LENGTH_LONG)
					.show();
			flagOver = 1;
			flagDecideResult = 1;
		} else {

		}

	}

	public void enabled123() {
		mButtonReplay.setVisibility(View.VISIBLE);
		mImgBtn4.setEnabled(false);
		mImgBtn5.setEnabled(false);
		mImgBtn6.setEnabled(false);
		mImgBtn7.setEnabled(false);
		mImgBtn8.setEnabled(false);
		mImgBtn9.setEnabled(false);
	}

	public void enabled456() {
		mButtonReplay.setVisibility(View.VISIBLE);
		mImgBtn1.setEnabled(false);
		mImgBtn2.setEnabled(false);
		mImgBtn3.setEnabled(false);
		mImgBtn7.setEnabled(false);
		mImgBtn8.setEnabled(false);
		mImgBtn9.setEnabled(false);
	}

	public void enabled789() {
		mButtonReplay.setVisibility(View.VISIBLE);
		mImgBtn4.setEnabled(false);
		mImgBtn5.setEnabled(false);
		mImgBtn6.setEnabled(false);
		mImgBtn1.setEnabled(false);
		mImgBtn2.setEnabled(false);
		mImgBtn3.setEnabled(false);
	}

	public void enabled147() {
		mButtonReplay.setVisibility(View.VISIBLE);
		mImgBtn2.setEnabled(false);
		mImgBtn3.setEnabled(false);
		mImgBtn5.setEnabled(false);
		mImgBtn6.setEnabled(false);
		mImgBtn8.setEnabled(false);
		mImgBtn9.setEnabled(false);
	}

	public void enabled258() {
		mButtonReplay.setVisibility(View.VISIBLE);
		mImgBtn1.setEnabled(false);
		mImgBtn3.setEnabled(false);
		mImgBtn4.setEnabled(false);
		mImgBtn6.setEnabled(false);
		mImgBtn7.setEnabled(false);
		mImgBtn9.setEnabled(false);
	}

	public void enabled369() {
		mButtonReplay.setVisibility(View.VISIBLE);
		mImgBtn1.setEnabled(false);
		mImgBtn2.setEnabled(false);
		mImgBtn4.setEnabled(false);
		mImgBtn5.setEnabled(false);
		mImgBtn7.setEnabled(false);
		mImgBtn9.setEnabled(false);
	}

	public void enabled159() {
		mButtonReplay.setVisibility(View.VISIBLE);
		mImgBtn2.setEnabled(false);
		mImgBtn3.setEnabled(false);
		mImgBtn4.setEnabled(false);
		mImgBtn6.setEnabled(false);
		mImgBtn7.setEnabled(false);
		mImgBtn8.setEnabled(false);
	}

	public void enabled357() {
		mButtonReplay.setVisibility(View.VISIBLE);
		mImgBtn1.setEnabled(false);
		mImgBtn2.setEnabled(false);
		mImgBtn4.setEnabled(false);
		mImgBtn6.setEnabled(false);
		mImgBtn8.setEnabled(false);
		mImgBtn9.setEnabled(false);
	}

	public void cpuChoice() {

		if (mImgBtn1.isEnabled()) {
			mImgBtn1.setBackgroundResource(R.drawable.zero);
			animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
					R.anim.rotate);
			mImgBtn1.startAnimation(animRotate);
			mImgBtn1.setEnabled(false);
			lflag1 = 1;

			decideResult();

		} else if (mImgBtn2.isEnabled()) {
			mImgBtn2.setBackgroundResource(R.drawable.zero);
			animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
					R.anim.rotate);
			mImgBtn2.startAnimation(animRotate);
			mImgBtn2.setEnabled(false);
			lflag2 = 2;

			decideResult();
		} else if (mImgBtn3.isEnabled()) {
			mImgBtn3.setBackgroundResource(R.drawable.zero);
			animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
					R.anim.rotate);
			mImgBtn3.startAnimation(animRotate);
			mImgBtn3.setEnabled(false);

			lflag3 = 3;
			decideResult();
		} else if (mImgBtn4.isEnabled()) {
			mImgBtn4.setBackgroundResource(R.drawable.zero);
			animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
					R.anim.rotate);
			mImgBtn4.startAnimation(animRotate);
			mImgBtn4.setEnabled(false);
			lflag4 = 4;
			decideResult();
		} else if (mImgBtn5.isEnabled()) {
			mImgBtn5.setBackgroundResource(R.drawable.zero);
			animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
					R.anim.rotate);
			mImgBtn5.startAnimation(animRotate);
			mImgBtn5.setEnabled(false);
			lflag5 = 5;
			decideResult();
		} else if (mImgBtn6.isEnabled()) {
			mImgBtn6.setBackgroundResource(R.drawable.zero);
			animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
					R.anim.rotate);
			mImgBtn6.startAnimation(animRotate);
			mImgBtn6.setEnabled(false);
			lflag6 = 6;
			decideResult();
		} else if (mImgBtn7.isEnabled()) {
			mImgBtn7.setBackgroundResource(R.drawable.zero);
			animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
					R.anim.rotate);
			mImgBtn7.startAnimation(animRotate);
			mImgBtn7.setEnabled(false);
			lflag7 = 7;
			decideResult();
		} else if (mImgBtn8.isEnabled()) {
			mImgBtn8.setBackgroundResource(R.drawable.zero);
			animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
					R.anim.rotate);
			mImgBtn8.startAnimation(animRotate);
			mImgBtn8.setEnabled(false);
			lflag8 = 8;
			decideResult();
		} else if (mImgBtn9.isEnabled()) {
			mImgBtn9.setBackgroundResource(R.drawable.zero);
			animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
					R.anim.rotate);
			mImgBtn9.startAnimation(animRotate);
			mImgBtn9.setEnabled(false);
			lflag9 = 9;
			decideResult();
		} else {
			decideResult();
		}

	}

	public void cpuChoice2() {
		if (mImgBtn2.isEnabled()) {
			mImgBtn2.setBackgroundResource(R.drawable.zero);
			animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
					R.anim.rotate);
			mImgBtn2.startAnimation(animRotate);
			mImgBtn2.setEnabled(false);
			lflag2 = 2;
			decideResult();

		} else if (mImgBtn1.isEnabled()) {
			mImgBtn1.setBackgroundResource(R.drawable.zero);
			animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
					R.anim.rotate);
			mImgBtn1.startAnimation(animRotate);
			mImgBtn1.setEnabled(false);
			lflag1 = 1;
			decideResult();

		} else if (mImgBtn9.isEnabled()) {
			mImgBtn9.setBackgroundResource(R.drawable.zero);
			animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
					R.anim.rotate);
			mImgBtn9.startAnimation(animRotate);
			mImgBtn9.setEnabled(false);
			lflag9 = 9;
			decideResult();

		} else if (mImgBtn3.isEnabled()) {
			mImgBtn3.setBackgroundResource(R.drawable.zero);
			animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
					R.anim.rotate);
			mImgBtn3.startAnimation(animRotate);
			mImgBtn3.setEnabled(false);
			lflag3 = 3;
			decideResult();

		} else if (mImgBtn8.isEnabled()) {
			mImgBtn8.setBackgroundResource(R.drawable.zero);
			animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
					R.anim.rotate);
			mImgBtn8.startAnimation(animRotate);
			mImgBtn8.setEnabled(false);
			lflag8 = 8;
			decideResult();
		} else if (mImgBtn5.isEnabled()) {
			mImgBtn5.setBackgroundResource(R.drawable.zero);
			animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
					R.anim.rotate);
			mImgBtn5.startAnimation(animRotate);
			mImgBtn5.setEnabled(false);
			lflag5 = 5;
			decideResult();
		} else if (mImgBtn4.isEnabled()) {
			mImgBtn4.setBackgroundResource(R.drawable.zero);
			animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
					R.anim.rotate);
			mImgBtn4.startAnimation(animRotate);
			mImgBtn4.setEnabled(false);
			lflag4 = 4;
			decideResult();
		} else if (mImgBtn6.isEnabled()) {
			mImgBtn6.setBackgroundResource(R.drawable.zero);
			animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
					R.anim.rotate);
			mImgBtn6.startAnimation(animRotate);
			mImgBtn6.setEnabled(false);
			lflag6 = 6;
			decideResult();
		} else if (mImgBtn7.isEnabled()) {
			mImgBtn7.setBackgroundResource(R.drawable.zero);
			animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
					R.anim.rotate);
			mImgBtn7.startAnimation(animRotate);
			mImgBtn7.setEnabled(false);
			lflag7 = 7;
			decideResult();
		} else {
			decideResult();
		}

	}

	public void cpuChoice7() {
		if (mImgBtn7.isEnabled()) {
			mImgBtn7.setBackgroundResource(R.drawable.zero);
			animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
					R.anim.rotate);
			mImgBtn7.startAnimation(animRotate);
			mImgBtn7.setEnabled(false);
			lflag7 = 7;
			decideResult();
		} else if (mImgBtn6.isEnabled()) {
			mImgBtn6.setBackgroundResource(R.drawable.zero);
			animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
					R.anim.rotate);
			mImgBtn6.startAnimation(animRotate);
			mImgBtn6.setEnabled(false);
			lflag6 = 6;
			checkLoose();
		} else if (mImgBtn5.isEnabled()) {
			mImgBtn5.setBackgroundResource(R.drawable.zero);
			animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
					R.anim.rotate);
			mImgBtn5.startAnimation(animRotate);
			mImgBtn5.setEnabled(false);
			lflag5 = 5;
			decideResult();
		} else if (mImgBtn4.isEnabled()) {
			mImgBtn4.setBackgroundResource(R.drawable.zero);
			animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
					R.anim.rotate);
			mImgBtn4.startAnimation(animRotate);
			mImgBtn4.setEnabled(false);
			lflag4 = 4;
			decideResult();
		} else if (mImgBtn3.isEnabled()) {
			mImgBtn3.setBackgroundResource(R.drawable.zero);
			animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
					R.anim.rotate);
			mImgBtn3.startAnimation(animRotate);
			mImgBtn3.setEnabled(false);
			lflag3 = 3;
			decideResult();
		} else if (mImgBtn2.isEnabled()) {
			mImgBtn2.setBackgroundResource(R.drawable.zero);
			animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
					R.anim.rotate);
			mImgBtn2.startAnimation(animRotate);
			mImgBtn2.setEnabled(false);
			lflag2 = 2;
			decideResult();
		} else if (mImgBtn1.isEnabled()) {
			mImgBtn1.setBackgroundResource(R.drawable.zero);
			animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
					R.anim.rotate);
			mImgBtn1.startAnimation(animRotate);
			mImgBtn1.setEnabled(false);
			lflag1 = 1;
			decideResult();
		} else if (mImgBtn8.isEnabled()) {
			mImgBtn8.setBackgroundResource(R.drawable.zero);
			animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
					R.anim.rotate);
			mImgBtn8.startAnimation(animRotate);
			mImgBtn8.setEnabled(false);
			lflag8 = 8;
			decideResult();
		} else if (mImgBtn9.isEnabled()) {
			mImgBtn9.setBackgroundResource(R.drawable.zero);
			animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
					R.anim.rotate);
			mImgBtn9.startAnimation(animRotate);
			mImgBtn9.setEnabled(false);
			lflag9 = 9;
			decideResult();
		} else {
			decideResult();
		}

	}

	public void cpuChoice5() {
		if (mImgBtn5.isEnabled()) {
			mImgBtn5.setBackgroundResource(R.drawable.zero);
			animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
					R.anim.rotate);
			mImgBtn5.startAnimation(animRotate);
			mImgBtn5.setEnabled(false);
			lflag5 = 5;
			decideResult();
		} else if (mImgBtn6.isEnabled()) {
			mImgBtn6.setBackgroundResource(R.drawable.zero);
			animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
					R.anim.rotate);
			mImgBtn6.startAnimation(animRotate);
			mImgBtn6.setEnabled(false);
			lflag6 = 6;
			decideResult();
		} else if (mImgBtn1.isEnabled()) {
			mImgBtn1.setBackgroundResource(R.drawable.zero);
			animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
					R.anim.rotate);
			mImgBtn1.startAnimation(animRotate);
			mImgBtn1.setEnabled(false);
			lflag1 = 1;
			decideResult();
		} else if (mImgBtn7.isEnabled()) {
			mImgBtn7.setBackgroundResource(R.drawable.zero);
			animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
					R.anim.rotate);
			mImgBtn7.startAnimation(animRotate);
			mImgBtn7.setEnabled(false);
			lflag7 = 7;
			decideResult();
		} else if (mImgBtn2.isEnabled()) {
			mImgBtn2.setBackgroundResource(R.drawable.zero);
			animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
					R.anim.rotate);
			mImgBtn2.startAnimation(animRotate);
			mImgBtn2.setEnabled(false);
			lflag2 = 2;
			decideResult();
		} else if (mImgBtn8.isEnabled()) {
			mImgBtn8.setBackgroundResource(R.drawable.zero);
			animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
					R.anim.rotate);
			mImgBtn8.startAnimation(animRotate);
			mImgBtn8.setEnabled(false);
			lflag8 = 8;
			decideResult();
		} else if (mImgBtn4.isEnabled()) {
			mImgBtn4.setBackgroundResource(R.drawable.zero);
			animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
					R.anim.rotate);
			mImgBtn4.startAnimation(animRotate);
			mImgBtn4.setEnabled(false);
			lflag4 = 4;
			decideResult();
		} else if (mImgBtn3.isEnabled()) {
			mImgBtn3.setBackgroundResource(R.drawable.zero);
			animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
					R.anim.rotate);
			mImgBtn3.startAnimation(animRotate);
			mImgBtn3.setEnabled(false);
			lflag3 = 3;
			decideResult();
		} else if (mImgBtn9.isEnabled()) {
			mImgBtn9.setBackgroundResource(R.drawable.zero);
			animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
					R.anim.rotate);
			mImgBtn9.startAnimation(animRotate);
			mImgBtn9.setEnabled(false);
			lflag9 = 9;
			decideResult();
		} else {
			decideResult();
		}

	}

	public void decideCall() {
		Random r = new Random();
		int i1 = r.nextInt(10 - 1) + 1;
		if (i1 == 1 || i1 == 3 || i1 == 9) {
			cpuChoice2();
		}
		if (i1 == 2 || i1 == 4) {
			cpuChoice();
		}
		if (i1 == 5 || i1 == 7) {
			cpuChoice5();
		}
		if (i1 == 6 || i1 == 8) {
			cpuChoice7();
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imgBtnOne:
			mImgBtn1.setBackgroundResource(R.drawable.cross);
			mImgBtn1.setEnabled(false);
			flag1 = 1;
			decideResult();
			if (mImgBtn1.isEnabled() == false && mImgBtn3.isEnabled() == false
					&& mImgBtn2.isEnabled() == true) {
				mImgBtn2.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn2.startAnimation(animRotate);
				mImgBtn2.setEnabled(false);
				decideResult();
			} else if (mImgBtn1.isEnabled() == false
					&& mImgBtn2.isEnabled() == false
					&& mImgBtn3.isEnabled() == true) {
				mImgBtn3.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn3.startAnimation(animRotate);
				mImgBtn3.setEnabled(false);
				decideResult();
			} else if (mImgBtn1.isEnabled() == false
					&& mImgBtn7.isEnabled() == false
					&& mImgBtn4.isEnabled() == true) {
				mImgBtn4.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn4.startAnimation(animRotate);
				mImgBtn4.setEnabled(false);
				decideResult();
			} else if (mImgBtn1.isEnabled() == false
					&& mImgBtn9.isEnabled() == false
					&& mImgBtn5.isEnabled() == true) {
				mImgBtn5.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn5.startAnimation(animRotate);
				mImgBtn5.setEnabled(false);
				decideResult();
			} else if (mImgBtn1.isEnabled() == false
					&& mImgBtn4.isEnabled() == false
					&& mImgBtn7.isEnabled() == true) {
				mImgBtn7.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn7.startAnimation(animRotate);
				mImgBtn7.setEnabled(false);
				decideResult();
			} else if (mImgBtn1.isEnabled() == false
					&& mImgBtn5.isEnabled() == false
					&& mImgBtn9.isEnabled() == true) {
				mImgBtn9.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn9.startAnimation(animRotate);
				mImgBtn9.setEnabled(false);
				decideResult();
			} else {
				decideCall();
				decideResult();
			}

			break;
		case R.id.imgBtnTwo:
			mImgBtn2.setBackgroundResource((R.drawable.cross));
			mImgBtn2.setEnabled(false);
			flag2 = 2;

			decideResult();
			if (mImgBtn2.isEnabled() == false && mImgBtn3.isEnabled() == false
					&& mImgBtn1.isEnabled() == true) {
				mImgBtn1.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn1.startAnimation(animRotate);
				mImgBtn1.setEnabled(false);
				decideResult();
			} else if (mImgBtn2.isEnabled() == false
					&& mImgBtn8.isEnabled() == false
					&& mImgBtn5.isEnabled() == true) {
				mImgBtn5.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn5.startAnimation(animRotate);
				mImgBtn5.setEnabled(false);
				decideResult();
			} else if (mImgBtn1.isEnabled() == false
					&& mImgBtn2.isEnabled() == false
					&& mImgBtn3.isEnabled() == true) {
				mImgBtn3.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn3.startAnimation(animRotate);
				mImgBtn3.setEnabled(false);
				decideResult();
			} else if (mImgBtn5.isEnabled() == false
					&& mImgBtn2.isEnabled() == false
					&& mImgBtn8.isEnabled() == true) {
				mImgBtn8.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn8.startAnimation(animRotate);
				mImgBtn8.setEnabled(false);
				decideResult();
			} else {
				decideCall();
				decideResult();
			}

			break;
		case R.id.imgBtnThree:
			mImgBtn3.setBackgroundResource((R.drawable.cross));
			mImgBtn3.setEnabled(false);
			flag3 = 3;

			decideResult();
			if (mImgBtn2.isEnabled() == false && mImgBtn3.isEnabled() == false
					&& mImgBtn1.isEnabled() == true) {
				mImgBtn1.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn1.startAnimation(animRotate);
				mImgBtn1.setEnabled(false);
				decideResult();
			} else if (mImgBtn2.isEnabled() == false
					&& mImgBtn3.isEnabled() == false
					&& mImgBtn1.isEnabled() == true) {
				mImgBtn1.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn1.startAnimation(animRotate);
				mImgBtn1.setEnabled(false);
				decideResult();
			} else if (mImgBtn1.isEnabled() == false
					&& mImgBtn3.isEnabled() == false
					&& mImgBtn2.isEnabled() == true) {
				mImgBtn2.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn2.startAnimation(animRotate);
				mImgBtn2.setEnabled(false);
				decideResult();
			} else if (mImgBtn7.isEnabled() == false
					&& mImgBtn3.isEnabled() == false
					&& mImgBtn5.isEnabled() == true) {
				mImgBtn5.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn5.startAnimation(animRotate);
				mImgBtn5.setEnabled(false);
				decideResult();
			} else if (mImgBtn3.isEnabled() == false
					&& mImgBtn9.isEnabled() == false
					&& mImgBtn6.isEnabled() == true) {
				mImgBtn6.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn6.startAnimation(animRotate);
				mImgBtn6.setEnabled(false);
				decideResult();
			} else if (mImgBtn3.isEnabled() == false
					&& mImgBtn5.isEnabled() == false
					&& mImgBtn7.isEnabled() == true) {
				mImgBtn7.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn7.startAnimation(animRotate);
				mImgBtn7.setEnabled(false);
				decideResult();
			} else if (mImgBtn6.isEnabled() == false
					&& mImgBtn3.isEnabled() == false
					&& mImgBtn9.isEnabled() == true) {
				mImgBtn9.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn9.startAnimation(animRotate);
				mImgBtn9.setEnabled(false);
				decideResult();
			} else {

				decideResult();
				decideCall();
			}

			break;
		case R.id.imgBtnFour:
			mImgBtn4.setBackgroundResource((R.drawable.cross));
			mImgBtn4.setEnabled(false);

			flag4 = 4;

			decideResult();
			if (mImgBtn4.isEnabled() == false && mImgBtn7.isEnabled() == false
					&& mImgBtn1.isEnabled() == true) {
				mImgBtn1.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn1.startAnimation(animRotate);
				mImgBtn1.setEnabled(false);
				decideResult();
			} else if (mImgBtn4.isEnabled() == false
					&& mImgBtn6.isEnabled() == false
					&& mImgBtn5.isEnabled() == true) {
				mImgBtn5.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn5.startAnimation(animRotate);
				mImgBtn5.setEnabled(false);
				decideResult();
			} else if (flag4 == 4 && flag5 == 5 && flag6 != 6) {
				mImgBtn6.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn6.startAnimation(animRotate);
				mImgBtn6.setEnabled(false);
				decideResult();
			} else if (mImgBtn4.isEnabled() == false
					&& mImgBtn1.isEnabled() == false
					&& mImgBtn7.isEnabled() == true) {
				mImgBtn7.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn7.startAnimation(animRotate);
				mImgBtn7.setEnabled(false);
				decideResult();
			} else {
				decideCall();
				decideResult();
			}

			break;
		case R.id.imgBtnFive:
			mImgBtn5.setBackgroundResource((R.drawable.cross));
			mImgBtn5.setEnabled(false);
			flag5 = 5;

			decideResult();
			if (flag4 == 4 && flag5 == 5 && flag6 != 6) {
				mImgBtn6.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn6.startAnimation(animRotate);
				mImgBtn6.setEnabled(false);
				decideResult();
			} else if (mImgBtn5.isEnabled() == false
					&& mImgBtn9.isEnabled() == false
					&& mImgBtn1.isEnabled() == true) {
				mImgBtn1.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn1.startAnimation(animRotate);
				mImgBtn1.setEnabled(false);
				decideResult();
			} else if (mImgBtn5.isEnabled() == false
					&& mImgBtn8.isEnabled() == false
					&& mImgBtn2.isEnabled() == true) {
				mImgBtn2.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn2.startAnimation(animRotate);
				mImgBtn2.setEnabled(false);
				decideResult();
			} else if (mImgBtn5.isEnabled() == false
					&& mImgBtn6.isEnabled() == false
					&& mImgBtn4.isEnabled() == true) {
				mImgBtn4.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn4.startAnimation(animRotate);
				mImgBtn4.setEnabled(false);
				decideResult();
			} else if (mImgBtn5.isEnabled() == false
					&& mImgBtn3.isEnabled() == false
					&& mImgBtn7.isEnabled() == true) {
				mImgBtn7.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn7.startAnimation(animRotate);
				mImgBtn7.setEnabled(false);
				decideResult();
			} else if (mImgBtn5.isEnabled() == false
					&& mImgBtn7.isEnabled() == false
					&& mImgBtn3.isEnabled() == true) {
				mImgBtn3.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn3.startAnimation(animRotate);
				mImgBtn3.setEnabled(false);
				decideResult();
			} else if (mImgBtn5.isEnabled() == false
					&& mImgBtn2.isEnabled() == false
					&& mImgBtn8.isEnabled() == true) {
				mImgBtn8.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn8.startAnimation(animRotate);
				mImgBtn8.setEnabled(false);
				decideResult();
			} else if (mImgBtn1.isEnabled() == false
					&& mImgBtn5.isEnabled() == false
					&& mImgBtn9.isEnabled() == true) {
				mImgBtn9.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn9.startAnimation(animRotate);
				mImgBtn9.setEnabled(false);
				decideResult();
			} else {
				decideCall();
				decideResult();
			}

			break;
		case R.id.imgBtnSix:
			mImgBtn6.setBackgroundResource((R.drawable.cross));
			mImgBtn6.setEnabled(false);
			flag6 = 6;

			decideResult();
			if (mImgBtn4.isEnabled() == false && mImgBtn6.isEnabled() == false
					&& mImgBtn5.isEnabled() == true) {
				mImgBtn5.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn5.startAnimation(animRotate);
				mImgBtn5.setEnabled(false);
				decideResult();
			} else if (mImgBtn6.isEnabled() == false
					&& mImgBtn9.isEnabled() == false
					&& mImgBtn3.isEnabled() == true) {
				mImgBtn3.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn3.startAnimation(animRotate);
				mImgBtn3.setEnabled(false);
				decideResult();
			} else if (mImgBtn6.isEnabled() == false
					&& mImgBtn5.isEnabled() == false
					&& mImgBtn4.isEnabled() == true) {
				mImgBtn4.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn4.startAnimation(animRotate);
				mImgBtn4.setEnabled(false);
				decideResult();
			} else if (mImgBtn3.isEnabled() == false
					&& mImgBtn6.isEnabled() == false
					&& mImgBtn9.isEnabled() == true) {
				mImgBtn9.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn9.startAnimation(animRotate);
				mImgBtn9.setEnabled(false);
				decideResult();
			} else {
				decideCall();
				decideResult();
			}

			break;
		case R.id.imgBtnSeven:
			mImgBtn7.setBackgroundResource((R.drawable.cross));
			mImgBtn7.setEnabled(false);
			flag7 = 7;

			decideResult();
			if (mImgBtn4.isEnabled() == false && mImgBtn7.isEnabled() == false
					&& mImgBtn1.isEnabled() == true) {
				mImgBtn1.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn1.startAnimation(animRotate);
				mImgBtn1.setEnabled(false);
				decideResult();
			} else if (mImgBtn7.isEnabled() == false
					&& mImgBtn5.isEnabled() == false
					&& mImgBtn3.isEnabled() == true) {
				mImgBtn3.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn3.startAnimation(animRotate);
				mImgBtn3.setEnabled(false);
				decideResult();
			} else if (mImgBtn1.isEnabled() == false
					&& mImgBtn7.isEnabled() == false
					&& mImgBtn4.isEnabled() == true) {
				mImgBtn4.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn4.startAnimation(animRotate);
				mImgBtn4.setEnabled(false);
				decideResult();
			} else if (mImgBtn7.isEnabled() == false
					&& mImgBtn3.isEnabled() == false
					&& mImgBtn5.isEnabled() == true) {
				mImgBtn5.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn5.startAnimation(animRotate);
				mImgBtn5.setEnabled(false);
				decideResult();
			} else if (mImgBtn7.isEnabled() == false
					&& mImgBtn9.isEnabled() == false
					&& mImgBtn8.isEnabled() == true) {
				mImgBtn8.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn8.startAnimation(animRotate);
				mImgBtn8.setEnabled(false);
				decideResult();
			} else if (mImgBtn7.isEnabled() == false
					&& mImgBtn8.isEnabled() == false
					&& mImgBtn9.isEnabled() == true) {
				mImgBtn9.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn9.startAnimation(animRotate);
				mImgBtn9.setEnabled(false);
				decideResult();
			} else {
				decideCall();
				decideResult();
			}

			break;
		case R.id.imgBtnEight:
			mImgBtn8.setBackgroundResource((R.drawable.cross));
			mImgBtn8.setEnabled(false);
			flag8 = 8;

			decideResult();
			if (mImgBtn2.isEnabled() == false && mImgBtn8.isEnabled() == false
					&& mImgBtn5.isEnabled() == true) {
				mImgBtn5.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn5.startAnimation(animRotate);
				mImgBtn5.setEnabled(false);
				decideResult();
			} else if (mImgBtn5.isEnabled() == false
					&& mImgBtn8.isEnabled() == false
					&& mImgBtn2.isEnabled() == true) {
				mImgBtn2.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn2.startAnimation(animRotate);
				mImgBtn2.setEnabled(false);
				decideResult();
			} else if (mImgBtn9.isEnabled() == false
					&& mImgBtn8.isEnabled() == false
					&& mImgBtn7.isEnabled() == true) {
				mImgBtn7.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn7.startAnimation(animRotate);
				mImgBtn7.setEnabled(false);
				decideResult();
			} else if (mImgBtn7.isEnabled() == false
					&& mImgBtn8.isEnabled() == false
					&& mImgBtn9.isEnabled() == true) {
				mImgBtn9.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn9.startAnimation(animRotate);
				mImgBtn9.setEnabled(false);
				decideResult();
			} else {
				decideCall();
				decideResult();
			}

			break;
		case R.id.imgBtnNine:
			mImgBtn9.setBackgroundResource((R.drawable.cross));
			mImgBtn9.setEnabled(false);
			flag9 = 9;

			decideResult();
			if (mImgBtn5.isEnabled() == false && mImgBtn9.isEnabled() == false
					&& mImgBtn1.isEnabled() == true) {
				mImgBtn1.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn1.startAnimation(animRotate);
				mImgBtn1.setEnabled(false);
				decideResult();
			} else if (mImgBtn6.isEnabled() == false
					&& mImgBtn9.isEnabled() == false
					&& mImgBtn3.isEnabled() == true) {
				mImgBtn3.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn3.startAnimation(animRotate);
				mImgBtn3.setEnabled(false);
				decideResult();
			} else if (mImgBtn1.isEnabled() == false
					&& mImgBtn9.isEnabled() == false
					&& mImgBtn5.isEnabled() == true) {
				mImgBtn5.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn5.startAnimation(animRotate);
				mImgBtn5.setEnabled(false);
				decideResult();
			} else if (mImgBtn9.isEnabled() == false
					&& mImgBtn3.isEnabled() == false
					&& mImgBtn6.isEnabled() == true) {
				mImgBtn6.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn6.startAnimation(animRotate);
				mImgBtn6.setEnabled(false);
				decideResult();
			} else if (mImgBtn9.isEnabled() == false
					&& mImgBtn8.isEnabled() == false
					&& mImgBtn7.isEnabled() == true) {
				mImgBtn7.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn7.startAnimation(animRotate);
				mImgBtn7.setEnabled(false);
				decideResult();
			} else if (mImgBtn7.isEnabled() == false
					&& mImgBtn9.isEnabled() == false
					&& mImgBtn8.isEnabled() == true) {
				mImgBtn8.setBackgroundResource(R.drawable.zero);
				animRotate = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.rotate);
				mImgBtn8.startAnimation(animRotate);
				mImgBtn8.setEnabled(false);
				decideResult();
			} else {
				decideCall();
				decideResult();
			}

			break;
		case R.id.btnPlayAgain:
			finish();
			Intent intent = new Intent(MainActivity.this, MainActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}

	}

	public void checkForDraw() {
		if (mImgBtn1.isEnabled() == false && mImgBtn2.isEnabled() == false
				&& mImgBtn3.isEnabled() == false
				&& mImgBtn4.isEnabled() == false
				&& mImgBtn5.isEnabled() == false
				&& mImgBtn6.isEnabled() == false
				&& mImgBtn7.isEnabled() == false
				&& mImgBtn8.isEnabled() == false
				&& mImgBtn9.isEnabled() == false && flagOver != 1) {
			Toast.makeText(MainActivity.this, "Match has been Drawn..",
					Toast.LENGTH_LONG).show();
			mButtonReplay.setVisibility(View.VISIBLE);
			flagDecideResult = 1;

		} else {

		}
	}

	public void decideResult() {
		if (flagDecideResult != 1) {
			checkWin();
			checkLoose();
			checkForDraw();
		}

	}
}
