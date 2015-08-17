package com.zaptech.myexpenditure2.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.zaptech.myexpenditure2.R;

public class FragmentExpenceHistory extends Fragment implements OnClickListener {
	private Button mbtnStartDate;
	private Button mbtnEndDate;
	private Button mbtnShowSpecificHistory;
	private Button mbtnShowAllHistory;

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView;
		rootView = inflater.inflate(R.layout.fragment_expencehistory,
				container, false);
		init(rootView);

		return rootView;
	}

	public void init(View rootView) {
		mbtnStartDate = (Button) rootView.findViewById(R.id.btn_startingDate);
		mbtnStartDate.setOnClickListener(this);
		mbtnEndDate = (Button) rootView.findViewById(R.id.btn_endingDate);
		mbtnEndDate.setOnClickListener(this);
		mbtnShowSpecificHistory = (Button) rootView
				.findViewById(R.id.btn_showSpecificHistory);
		mbtnShowSpecificHistory.setOnClickListener(this);
		mbtnShowAllHistory = (Button) rootView
				.findViewById(R.id.btn_showAllHistory);
		mbtnShowAllHistory.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_startingDate:

			break;
		case R.id.btn_endingDate:
			break;
		case R.id.btn_showSpecificHistory:
			Fragment fShowSpecificHistory = new FragmentViewExpenceHistory();
			FragmentTransaction ft = getFragmentManager().beginTransaction();
			ft.replace(R.id.main, fShowSpecificHistory);
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			ft.addToBackStack(null);
			ft.commit();
			break;
		case R.id.btn_showAllHistory:
			Fragment fShowAllHistory = new FragmentViewExpenceHistory();
			FragmentTransaction ft2 = getFragmentManager().beginTransaction();
			ft2.replace(R.id.main, fShowAllHistory);
			ft2.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			ft2.addToBackStack(null);
			ft2.commit();
			break;
		default:
			break;
		}
	}
}
