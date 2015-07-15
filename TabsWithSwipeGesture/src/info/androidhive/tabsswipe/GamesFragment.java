package info.androidhive.tabsswipe;

import info.androidhive.tabsswipe.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class GamesFragment extends Fragment {

	private TextView txt1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_games, container,
				false);

		init(rootView);

		return rootView;
	}

	// txt1 = (TextView) rootView.findViewById(R.id.txt

	private void init(View rootView) {
		// TODO Auto-generated method stub
		txt1 = (TextView) rootView.findViewById(R.id.txtGames);
	}
}
