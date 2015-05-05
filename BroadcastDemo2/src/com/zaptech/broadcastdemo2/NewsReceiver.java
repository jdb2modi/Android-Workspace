package com.zaptech.broadcastdemo2;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NewsReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		CharSequence seq = intent.getCharSequenceExtra("NEWS");
		Toast.makeText(context, "Breaking News : "+seq, Toast.LENGTH_LONG).show();
	}

}
