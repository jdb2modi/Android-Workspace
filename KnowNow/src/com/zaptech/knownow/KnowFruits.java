package com.zaptech.knownow;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class KnowFruits extends Activity implements OnClickListener,
		OnCheckedChangeListener {
	Button buttonVerifyFruits;
	ImageView imageviewFruit;
	RadioGroup rg;
	RadioButton rb;
	TextView tvFruitScore;
	int selected;
	Animation animRotate;
	Animation animAlpha;
	Animation animTranslate;
	Animation animScale;
	public int count = 0, score = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.knowfruits);
		init();
		provideAnimation();
		imageviewFruit.setBackgroundResource(R.drawable.apple);
	}

	public void init() {
		buttonVerifyFruits = (Button) findViewById(R.id.buttonVerifyFruit);
		buttonVerifyFruits.setOnClickListener(this);
		imageviewFruit = (ImageView) findViewById(R.id.imageviewFruits);
		tvFruitScore = (TextView) findViewById(R.id.textviewFruitScore);

		rg = (RadioGroup) findViewById(R.id.radiogroupFruits);
		int selected = rg.getCheckedRadioButtonId();
		rb = (RadioButton) findViewById(selected);

	}

	public void provideAnimation() {
		animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.rotate);
		imageviewFruit.startAnimation(animRotate);
		animAlpha = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.alpha);
		animTranslate = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.translate);
		animScale = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.scale);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonVerifyFruit:
			selected = rg.getCheckedRadioButtonId();
			rb = (RadioButton) findViewById(selected);
			switch (count) {

			case 0:
				imageviewFruit.startAnimation(animAlpha);
				selected = rg.getCheckedRadioButtonId();
				rb = (RadioButton) findViewById(selected);
				if (rb.getText().equals("Apple")) {

					imageviewFruit.setBackgroundResource(R.drawable.banana);
					score++;
					tvFruitScore.setText(String.valueOf(score));
					count++;
				} else {
					Toast.makeText(getApplicationContext(), "WRONG ANSWER",
							Toast.LENGTH_SHORT).show();
				}

				break;
			case 1:
				imageviewFruit.startAnimation(animRotate);
				selected = rg.getCheckedRadioButtonId();
				rb = (RadioButton) findViewById(selected);
				if (rb.getText().equals("Banana")) {

					imageviewFruit.setBackgroundResource(R.drawable.orange);
					score++;
					tvFruitScore.setText(String.valueOf(score));
					count++;
				} else {
					Toast.makeText(getApplicationContext(), "WRONG ANSWER",
							Toast.LENGTH_SHORT).show();
				}
				break;

			case 2:
				imageviewFruit.startAnimation(animScale);
				selected = rg.getCheckedRadioButtonId();
				rb = (RadioButton) findViewById(selected);
				if (rb.getText().equals("Orange")) {

					imageviewFruit.setBackgroundResource(R.drawable.grapes);
					count++;
					score++;
					tvFruitScore.setText(String.valueOf(score));
				} else {
					Toast.makeText(getApplicationContext(), "WRONG ANSWER",
							Toast.LENGTH_SHORT).show();
				}
				break;

			case 3:
				imageviewFruit.startAnimation(animTranslate);
				selected = rg.getCheckedRadioButtonId();
				rb = (RadioButton) findViewById(selected);
				if (rb.getText().equals("Grapes")) {

					imageviewFruit.setBackgroundResource(R.drawable.pineapple);
					count++;
					score++;
					tvFruitScore.setText(String.valueOf(score));
				} else {
					Toast.makeText(getApplicationContext(), "WRONG ANSWER",
							Toast.LENGTH_SHORT).show();
				}
				break;

			default:
				break;
			}
			break;

		default:
			break;
		}

	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub

	}
}
