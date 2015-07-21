package com.zaptech.mytoss;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_Home extends Activity implements OnClickListener {
	Button btn_Upload1, btn_Upload2, btn_Ready;
	ImageView img_Coin1, img_Coin2;
	TextView txtSelected;
	String str = "", str1, str2;
	// ///
	private static final int REQUEST_PICK_FILE = 1;

	private TextView filePath;
	private Button Browse;
	private File selectedFile;

	// ///
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();
		// ///

		// ///
	}

	public void init() {
		btn_Ready = (Button) findViewById(R.id.btn_ReadyToToss);
		btn_Ready.setOnClickListener(this);
		btn_Upload1 = (Button) findViewById(R.id.btn_UploadImage1);
		btn_Upload1.setOnClickListener(this);
		btn_Upload2 = (Button) findViewById(R.id.btn_UploadImage2);
		btn_Upload2.setOnClickListener(this);

		img_Coin1 = (ImageView) findViewById(R.id.img_Coin1);
		img_Coin2 = (ImageView) findViewById(R.id.img_Coin2);
		filePath = (TextView) findViewById(R.id.file_path);

		txtSelected = (TextView) findViewById(R.id.file_path);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_ReadyToToss:

			Intent intent = new Intent(Activity_Home.this, Activity_Toss.class);
			intent.putExtra("Path1", str1);
			intent.putExtra("Path2", str2);
			startActivity(intent);
			break;
		case R.id.btn_UploadImage1:
			str = "A";
			Intent intent2 = new Intent(this, FilePicker.class);
			startActivityForResult(intent2, REQUEST_PICK_FILE);

			break;
		case R.id.btn_UploadImage2:
			str = "B";
			Intent intent3 = new Intent(this, FilePicker.class);
			startActivityForResult(intent3, REQUEST_PICK_FILE);
			/*
			 * File imageFile2 = new File("/sdcard/Download/two.png");
			 * 
			 * Bitmap bitmap2 = BitmapFactory.decodeFile(imageFile2
			 * .getAbsolutePath()); img_Coin2.setImageBitmap(bitmap2);
			 */
			break;

		default:
			break;
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (resultCode == RESULT_OK) {

			switch (requestCode) {

			case REQUEST_PICK_FILE:

				if (data.hasExtra(FilePicker.EXTRA_FILE_PATH)) {

					selectedFile = new File(
							data.getStringExtra(FilePicker.EXTRA_FILE_PATH));
					filePath.setText(selectedFile.getPath());

					File imageFile = new File(filePath.getText().toString());
					System.err
							.println(">>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<"
									+ txtSelected.getText().toString());
					Bitmap bitmap = BitmapFactory.decodeFile(imageFile
							.getAbsolutePath());
					if (str.equals("A")) {
						img_Coin1.setImageBitmap(bitmap);
						
						str1 = filePath.getText().toString();
						
						System.err
								.println(">>>>>>>>>>>>>>>>>>>>>>>>img1" + str);
					} else {
						img_Coin2.setImageBitmap(bitmap);

						str2 = filePath.getText().toString();
//						str2 = String.valueOf(img_Coin2.getBackground());
						System.err
								.println(">>>>>>>>>>>>>>>>>>>>>>>>img2" + str);
					}

				}
				break;
			}
		}
	}
}
