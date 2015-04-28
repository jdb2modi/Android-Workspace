package com.zaptech.imageswitcher;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

public class MainActivity extends Activity {
	private ImageButton img,img2;
	private ImageSwitcher imageSwitcher;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		imageSwitcher.setFactory(new ViewFactory() {

			   @Override
			   public View makeView() {
			      ImageView myView = new ImageView(getApplicationContext());
			      myView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			      myView.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.
			      FILL_PARENT,LayoutParams.FILL_PARENT));
			      return myView;
			       }

			   });
	}

	public void init() {
		imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher1);
		img = (ImageButton) findViewById(R.id.imageButton1);
		img2 = (ImageButton) findViewById(R.id.imageButton2);


	}

	public void next(View view) {
		Toast.makeText(getApplicationContext(), "Next Image", Toast.LENGTH_LONG)
				.show();
		Animation in = AnimationUtils.loadAnimation(this,
				android.R.anim.slide_in_left);
		Animation out = AnimationUtils.loadAnimation(this,
				android.R.anim.slide_out_right);
		imageSwitcher.setInAnimation(in);
		imageSwitcher.setOutAnimation(out);
		imageSwitcher.setImageResource(R.drawable.ic_launcher);
	}

	public void previous(View view) {
		Toast.makeText(getApplicationContext(), "previous Image",
				Toast.LENGTH_LONG).show();
		Animation in = AnimationUtils.loadAnimation(this,
				android.R.anim.slide_out_right);
		Animation out = AnimationUtils.loadAnimation(this,
				android.R.anim.slide_in_left);
		imageSwitcher.setInAnimation(out);
		imageSwitcher.setOutAnimation(in);
		imageSwitcher.setImageResource(R.drawable.back);
	}

}
