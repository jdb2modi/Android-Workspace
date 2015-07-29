package com.example.quizapplication.font;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class TextViewFont extends TextView {

	public TextViewFont(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public TextViewFont(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public TextViewFont(Context context) {
		super(context);
	}

	public void setTypeface(Typeface tf, int style) {
		Typeface type = Typeface.createFromAsset(this.getContext().getAssets(),
				"Chalkduster.ttf");

		super.setTypeface(type);
	}

}
