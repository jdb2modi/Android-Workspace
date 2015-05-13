package com.zaptech.texttospeech;

import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class HomeActivity extends Activity implements OnClickListener,
		TextToSpeech.OnInitListener {
	EditText edWrite;
	Button btnSpeak;
	TextToSpeech tts;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();
	}

	public void init() {
		edWrite = (EditText) findViewById(R.id.editText1);
		btnSpeak = (Button) findViewById(R.id.button1);
		btnSpeak.setOnClickListener(this);

		tts = new TextToSpeech(this, this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			speakOut();
			break;

		default:
			break;
		}

	}

	@Override
	public void onInit(int status) {
		if (status == TextToSpeech.SUCCESS) {

			int result = tts.setLanguage(Locale.GERMANY);

			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
				Log.e("TTS", "This Language is not supported");
			} else {
				btnSpeak.setEnabled(true);
				speakOut();
			}

		} else {
			Log.e("TTS", "Initilization Failed!");
		}

	}

	private void speakOut() {
		String text = edWrite.getText().toString();
		tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
	}

}