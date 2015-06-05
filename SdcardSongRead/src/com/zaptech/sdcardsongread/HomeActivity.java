package com.zaptech.sdcardsongread;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class HomeActivity extends Activity {
	Button btnLoadSongs;
	ListView listSongs;
	ArrayList<String> msqlist;
	File listFile[], file;
	ArrayList<String> absolutepath;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();

		btnLoadSongs.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				msqlist.clear();
				
			
				walkdir(file);
			}
		});

	}

	public void init() {
		btnLoadSongs = (Button) findViewById(R.id.btnLoadSongs);
		listSongs = (ListView) findViewById(R.id.listSongs);
		msqlist = new ArrayList();
		
//		absolutepath = new ArrayList<String>();
		file = new File(Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/Download");

		Log.i("here file display or not", "++++++++++++++++" + file.listFiles());

	}

	public void walkdir(File dir) {
		Toast.makeText(getApplicationContext(),
				"dir : " + String.valueOf(dir).toString(), Toast.LENGTH_LONG)
				.show();
		String msqPattern = ".mp3";// Can include more strings for more
									// extensions and check it.

		File[] listFile = dir.listFiles();

		if (listFile != null) {
			for (int i = 0; i < listFile.length; i++) {

				if (listFile[i].getName().endsWith(msqPattern)) {
					msqlist.add(listFile[i].getName());
//					absolutepath.add(listFile[i].getAbsolutePath());
					Toast.makeText(getApplicationContext(),
							String.valueOf(listFile[i].getName()),
							Toast.LENGTH_LONG).show();
				}
			}
		} else {
			Toast.makeText(getApplicationContext(), "No Songs Found",
					Toast.LENGTH_LONG).show();
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1,
				msqlist);
		
		listSongs.setAdapter(adapter);
	}
}
