package com.zaptech.andipaint;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment implements OnClickListener {
	private CanvasView mcustomCanvas;
	private Button mbtnClearCanvas;

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
		mbtnClearCanvas = (Button) rootView.findViewById(R.id.btnClearCanvas);
		mbtnClearCanvas.setOnClickListener(this);
	}

	public void clearCanvas() {
		mcustomCanvas.clearCanvas();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnClearCanvas:
			clearCanvas();
			break;

		default:
			break;
		}

	}
}
