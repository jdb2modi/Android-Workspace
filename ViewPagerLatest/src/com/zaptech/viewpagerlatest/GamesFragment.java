package com.zaptech.viewpagerlatest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GamesFragment extends Fragment {

	Button btnGotoGames, btnGotoMovies, btnGotoTop;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_games, container,
				false);

		init(rootView);
		btnGotoGames.setOnClickListener(new OnClickListener() {

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
		btnGotoMovies.setOnClickListener(new OnClickListener() {

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
		btnGotoTop.setOnClickListener(new OnClickListener() {

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

	// txt1 = (TextView) rootView.findViewById(R.id.txt

	private void init(View rootView) {
		btnGotoGames = (Button) rootView.findViewById(R.id.btnGoToGamesG);
		btnGotoMovies = (Button) rootView.findViewById(R.id.btnGoToMoviesG);
		btnGotoTop = (Button) rootView.findViewById(R.id.btnHomeG);

	}
}
