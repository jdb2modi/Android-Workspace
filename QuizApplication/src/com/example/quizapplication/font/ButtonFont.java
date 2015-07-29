package com.example.quizapplication.font;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

public class ButtonFont extends EditText {

	public ButtonFont(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public ButtonFont(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ButtonFont(Context context) {
		super(context);
	}

	public void setTypeface(Typeface tf, int style) {
		Typeface type = Typeface.createFromAsset(this.getContext().getAssets(),
				"Chalkduster.ttf");

		super.setTypeface(type);
	}

}