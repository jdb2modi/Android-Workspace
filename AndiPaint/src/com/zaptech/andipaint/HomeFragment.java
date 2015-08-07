package com.zaptech.andipaint;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

import com.flurry.android.FlurryAgent;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class HomeFragment extends Fragment implements OnClickListener {
	private CanvasView mcustomCanvas;
	private Button mbtnClearCanvas;
	private ImageView imgv;
	private String mstrData;
	private FrameLayout mContent;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_home, container,
				false);

		init(rootView);

		return rootView;
	}

	public void init(View rootView) {

		mcustomCanvas = (CanvasView) rootView
				.findViewById(R.id.signature_canvas);
		mcustomCanvas.setClickable(false);
		mcustomCanvas.setEnabled(false);
		mbtnClearCanvas = (Button) rootView.findViewById(R.id.btnClearCanvas);
		mbtnClearCanvas.setOnClickListener(this);

		mContent = (FrameLayout) rootView.findViewById(R.id.mContent);
		imgv = (ImageView) rootView.findViewById(R.id.img1);
	}

	public void clearCanvas() {
		mcustomCanvas.clearCanvas();

	}

	public void saveImage() {
		Bitmap bmap = BitmapFactory.decodeResource(
				getActivity().getResources(), R.drawable.ic_launcher);
		// imgv.buildDrawingCache();
		// Bitmap bmap = imgv.getDrawingCache();
		String root = Environment.getExternalStorageDirectory().toString();
		File myDir = new File(root + "/saved_images");
		myDir.mkdirs();
		Random generator = new Random();
		int n = 10000;
		n = generator.nextInt(n);
		String fname = "Image-" + n + ".jpg";
		File file = new File(myDir, fname);
		if (file.exists())
			file.delete();
		try {
			FileOutputStream out = new FileOutputStream(file);
			bmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
			out.flush();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnClearCanvas:
			clearCanvas();
			FlurryAgent.logEvent("View Page", true);
			saveImage();
			break;

		default:
			break;
		}

	}
}
