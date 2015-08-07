package com.zaptech.andipaint2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class HomeActivity extends Activity implements OnClickListener {
	private CanvasView mCanvasView;
	private ImageButton mImgBtnPencil;
	private ImageButton mImgBtnEraser;
	private ImageButton mImgBtnClearAll;
	private ImageButton mImgBtnShapes;
	private ImageButton mImgBtnColorPicker;
	private ImageButton mImgBtnSave;
	private ModelTool mModelTool;

	/**
	 * SharedPreferences saves data for particular click event of Tool's
	 * ImageButton Its values will be saved to draw corresponding items.
	 */
	private SharedPreferences mShared;
	public static final String MyPREFERENCES = "MyPrefs";
	public Editor editor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();

	}

	/**
	 * To Initialize necessary variables
	 */
	public void init() {

		mCanvasView = (CanvasView) findViewById(R.id.signature_canvas);
		mImgBtnPencil = (ImageButton) findViewById(R.id.imgBtnPencil);
		mImgBtnPencil.setOnClickListener(this);
		mImgBtnEraser = (ImageButton) findViewById(R.id.imgBtnEraser);
		mImgBtnEraser.setOnClickListener(this);
		mImgBtnClearAll = (ImageButton) findViewById(R.id.imgBtnClearAll);
		mImgBtnClearAll.setOnClickListener(this);
		mImgBtnShapes = (ImageButton) findViewById(R.id.imgBtnShapes);
		mImgBtnShapes.setOnClickListener(this);
		mImgBtnColorPicker = (ImageButton) findViewById(R.id.imgBtnColor);
		mImgBtnColorPicker.setOnClickListener(this);
		mImgBtnSave = (ImageButton) findViewById(R.id.imgBtnSave);
		mImgBtnSave.setOnClickListener(this);
		mModelTool = new ModelTool();

		mShared = getSharedPreferences(MyPREFERENCES, MODE_WORLD_READABLE
				| MODE_WORLD_WRITEABLE);
		editor = mShared.edit();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imgBtnPencil:
			editor.putString("TOOL", "pencil");
			editor.commit();
			Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
			startActivity(intent);
			break;
		case R.id.imgBtnEraser:
			editor.putString("TOOL", "eraser");
			editor.commit();
			break;
		case R.id.imgBtnClearAll:
			editor.putString("TOOL", "clearall");
			editor.commit();
			break;
		case R.id.imgBtnShapes:
			editor.putString("TOOL", "shapes");
			editor.commit();
			Intent intent2 = new Intent(HomeActivity.this, HomeActivity.class);
			startActivity(intent2);
			break;
		case R.id.imgBtnColor:
			editor.putString("TOOL", "color");
			editor.commit();
			break;
		case R.id.imgBtnSave:
			editor.putString("TOOL", "save");
			editor.commit();
			break;

		default:
			break;
		}

	}
}
