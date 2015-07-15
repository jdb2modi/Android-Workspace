package info.androidhive.tabsswipe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TopRatedFragment extends Fragment {
	TextView txtTopRated;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_top_rated,
				container, false);
		init(rootView);
		return rootView;
	}

	public void init(View rootView) {
		txtTopRated = (TextView) rootView.findViewById(R.id.txtTopRated);
	}

}
