package com.zaptech.wallpapermanager;

import java.io.IOException;

import android.app.Activity;
import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ImageView mImgWallPaper;
	private Button mBtnWallPaper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();

		onClick();

	}

	public void init() {
		mImgWallPaper = (ImageView) findViewById(R.id.imgViewSetWallPaper);
		
		mImgWallPaper.setTag("ic_launcher_web");

		mBtnWallPaper = (Button) findViewById(R.id.btnSetWallPaper);
	}

	public void onClick() {
		mBtnWallPaper.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setWallPaper();
			}
		});
	}

	public void setWallPaper() {

		int icon = getResources()
				.getIdentifier(mImgWallPaper.getTag().toString(), "drawable",
						getPackageName());

		Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), icon);
		WallpaperManager myWallpaperManager = WallpaperManager
				.getInstance(getApplicationContext());

		try {
			myWallpaperManager.setBitmap(mBitmap);
			Toast.makeText(MainActivity.this, "Wallpaper set",
					Toast.LENGTH_SHORT).show();
		} catch (IOException e) {
			Toast.makeText(MainActivity.this, "Error setting wallpaper",
					Toast.LENGTH_SHORT).show();
		}
	}
}
