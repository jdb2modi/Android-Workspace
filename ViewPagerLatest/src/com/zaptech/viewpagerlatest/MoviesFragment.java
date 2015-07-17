package com.zaptech.viewpagerlatest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class MoviesFragment extends Fragment {
	Button btnGotoGames, btnGotoMovies, btnGotoTop;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_movies, container,
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

	public void init(View rootview) {
		btnGotoGames = (Button) rootview.findViewById(R.id.btnGoToGamesM);
		btnGotoMovies = (Button) rootview.findViewById(R.id.btnGoToMoviesM);
		btnGotoTop = (Button) rootview.findViewById(R.id.btnHomeM);

	}

}
