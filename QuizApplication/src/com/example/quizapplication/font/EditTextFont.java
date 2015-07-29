package com.example.quizapplication.font;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

public class EditTextFont extends EditText {

	public EditTextFont(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public EditTextFont(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public EditTextFont(Context context) {
		super(context);
	}

	public void setTypeface(Typeface tf, int style) {
		Typeface type = Typeface.createFromAsset(this.getContext().getAssets(),
				"Chalkduster.ttf");

		super.setTypeface(type);
	}

}
