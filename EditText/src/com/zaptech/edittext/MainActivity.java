package com.zaptech.edittext;

import org.apache.commons.lang3.text.WordUtils;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MainActivity extends Activity {
	EditText ed1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		setCapitalizeTextWatcher(ed1);
	}

	public void init() {
		ed1 = (EditText) findViewById(R.id.ed1);
	}

	public static void setCapitalizeTextWatcher(final EditText editText) {
		final TextWatcher textWatcher = new TextWatcher() {

			int mStart = 0;

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				mStart = start + count;
			}

			@Override
			public void afterTextChanged(Editable s) {
				// Use WordUtils.capitalizeFully if you only want the first
				// letter of each word to be capitalized
				String capitalizedText = WordUtils.capitalize(editText
						.getText().toString());
				if (!capitalizedText.equals(editText.getText().toString())) {
					editText.addTextChangedListener(new TextWatcher() {
						@Override
						public void beforeTextChanged(CharSequence s,
								int start, int count, int after) {

						}

						@Override
						public void onTextChanged(CharSequence s, int start,
								int before, int count) {

						}

						@Override
						public void afterTextChanged(Editable s) {
							editText.setSelection(mStart);
							editText.removeTextChangedListener(this);
						}
					});
					editText.setText(capitalizedText);
				}
			}
		};

		editText.addTextChangedListener(textWatcher);
	}
}
