package com.example.quizapplication;

import java.io.FileNotFoundException;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ProfileSetting extends Activity {
	Dialog myDialog, dialogCamera;
	Button btnCamera, btnGallery;
	ImageView imgVw_userPhoto;
	private static final int CAMERA_REQUEST = 1888;
	private static int RESULT_LOAD_IMAGE = 1;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.profilesetting);

	}

	public void OnClickHandler(View v) {
		switch (v.getId()) {
		case R.id.profileSet_btn_back:
			Intent i_back = new Intent(ProfileSetting.this,
					AccountSetting.class);
			startActivity(i_back);
			break;

		case R.id.profileSet_btn_share_UserName:
			myDialog = new Dialog(ProfileSetting.this);
			myDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			myDialog.setContentView(R.layout.sharewith_dialog);
			// myDialog.setCanceledOnTouchOutside(false);
			myDialog.getWindow().setGravity(Gravity.BOTTOM);
			myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
			myDialog.setCancelable(false);
			myDialog.show();

			Button button = (Button) myDialog
					.findViewById(R.id.sharewith_btn_cancel);
			button.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					myDialog.dismiss();
				}
			});

			myDialog.show();
			break;

		case R.id.profileSet_btn_Adv_setting:
			Intent i_advset = new Intent(ProfileSetting.this,
					AdvancedSetting.class);
			startActivity(i_advset);
			break;
		case R.id.profileSet_btn_chngPswd:
			Intent i_changpswd = new Intent(ProfileSetting.this,
					ChangePassword.class);
			startActivity(i_changpswd);
			break;
		case R.id.profileSet_imgVw_userPhoto:

			dialogCamera = new Dialog(ProfileSetting.this);
			dialogCamera.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialogCamera.setContentView(R.layout.camera_dialog);
			// dialogCancel.setCanceledOnTouchOutside(false);
			dialogCamera.getWindow().setGravity(Gravity.BOTTOM);

			dialogCamera.getWindow()
					.setBackgroundDrawable(new ColorDrawable(0));

			dialogCamera.setCancelable(true);
			dialogCamera.show();

			Button btncancel = (Button) dialogCamera
					.findViewById(R.id.camera_btn_cancel);
			btnCamera = (Button) dialogCamera
					.findViewById(R.id.camera_btn_camera);
			btnGallery = (Button) dialogCamera
					.findViewById(R.id.Camera_btn_gallery);
			btncancel.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					dialogCamera.dismiss();
				}
			});
			btnCamera.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent cameraIntent = new Intent(
							android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
					startActivityForResult(cameraIntent, CAMERA_REQUEST);

					Toast.makeText(getApplicationContext(), "Click Camera",
							Toast.LENGTH_SHORT).show();

				}
			});
			btnGallery.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i = new Intent(
							Intent.ACTION_PICK,
							android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

					startActivityForResult(i, RESULT_LOAD_IMAGE);

					Toast.makeText(getApplicationContext(), "Click Gallery",
							Toast.LENGTH_SHORT).show();
				}
			});

			dialogCamera.show();
			break;

		}

	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
			Bitmap photo = (Bitmap) data.getExtras().get("data");
			imgVw_userPhoto.setImageBitmap(photo);
			dialogCamera.dismiss();
		}

		if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK
				&& null != data) {
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };

			Cursor cursor = getContentResolver().query(selectedImage,
					filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			cursor.close();

			// imageview.setImageBitmap(BitmapFactory.decodeFile(picturePath));

			try {
				imgVw_userPhoto.setImageBitmap(decodeUri(selectedImage));
				dialogCamera.dismiss();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private Bitmap decodeUri(Uri selectedImage) throws FileNotFoundException {
		BitmapFactory.Options o = new BitmapFactory.Options();
		o.inJustDecodeBounds = true;
		BitmapFactory.decodeStream(
				getContentResolver().openInputStream(selectedImage), null, o);

		final int REQUIRED_SIZE = 100;

		int width_tmp = o.outWidth, height_tmp = o.outHeight;
		int scale = 1;
		while (true) {
			if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE) {
				break;
			}
			width_tmp /= 2;
			height_tmp /= 2;
			scale *= 2;
		}

		BitmapFactory.Options o2 = new BitmapFactory.Options();
		o2.inSampleSize = scale;
		return BitmapFactory.decodeStream(
				getContentResolver().openInputStream(selectedImage), null, o2);
	}

}
