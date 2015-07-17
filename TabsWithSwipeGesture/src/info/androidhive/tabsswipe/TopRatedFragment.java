package info.androidhive.tabsswipe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class TopRatedFragment extends Fragment {
	TextView txtTopRated;
	Button btnGoToGames;
	Button btnGOTOMovies;
	Button btnGOTOTop;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_top_rated,
				container, false);
		init(rootView);
		btnGoToGames.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Fragment games = new GamesFragment();
				FragmentTransaction ft = getActivity()
						.getSupportFragmentManager().beginTransaction();
				ft.replace(R.id.mainContent, games);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				ft.addToBackStack(null);
				ft.commit();
			
				
			}
		});
		btnGOTOMovies.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Fragment movies = new MoviesFragment();
				FragmentTransaction ft = getActivity()
						.getSupportFragmentManager().beginTransaction();
				ft.replace(R.id.mainContent, movies);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				ft.addToBackStack(null);
				ft.commit();
				
			}
		});
		btnGOTOTop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Fragment top = new TopRatedFragment();
				FragmentTransaction ft = getActivity()
						.getSupportFragmentManager().beginTransaction();
				ft.replace(R.id.mainContent, top);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				ft.addToBackStack(null);
				ft.commit();
				
			}
		});
		return rootView;
	}

	public void init(View rootView) {
		txtTopRated = (TextView) rootView.findViewById(R.id.txtTopRated);
		btnGoToGames = (Button) rootView.findViewById(R.id.btnGoToGames);
		btnGOTOMovies = (Button) rootView.findViewById(R.id.btnGoToMovies);
		btnGOTOTop = (Button) rootView.findViewById(R.id.btnHome);
	}

}
