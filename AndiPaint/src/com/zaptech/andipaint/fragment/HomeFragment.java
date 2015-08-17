package com.zaptech.andipaint.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.zaptech.andipaint.CanvasView;
import com.zaptech.andipaint.R;

public class HomeFragment extends Fragment implements OnClickListener {
	private CanvasView mcustomCanvas;
	private Button mbtnClearCanvas;

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

		mbtnClearCanvas = (Button) rootView.findViewById(R.id.btnClearCanvas);
		mbtnClearCanvas.setOnClickListener(this);

		mContent = (FrameLayout) rootView.findViewById(R.id.mContent);

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
