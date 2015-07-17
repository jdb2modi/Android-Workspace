package com.zaptech.fragmentdatatransfer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InputFragment extends Fragment {
	EditText edName;
	Button btnSubmit;

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView;
		rootView = inflater.inflate(R.layout.fragment_input, container, false);
		init(rootView);

		btnSubmit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Fragment display = new DisplayFragment();
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.mainContent, display);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				ft.addToBackStack(null);
				ft.commit();
				Toast.makeText(getActivity().getApplicationContext(),
						"Clicked", 2500).show();
				Bundle bundle = new Bundle();
				bundle.putString("name", edName.getText().toString());
				display.setArguments(bundle);
				System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
						+ edName.getText().toString());
			}
		});

		return rootView;
	}

	public void init(View rootView) {
		edName = (EditText) rootView.findViewById(R.id.edName);
		btnSubmit = (Button) rootView.findViewById(R.id.btnSubmit);
	}
}
