package info.androidhive.tabsswipe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MoviesFragment extends Fragment {
	Button btnMovies;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_movies, container,
				false);
		init(rootView);
		return rootView;
	}

	public void init(View rootview) {
		btnMovies = (Button) rootview.findViewById(R.id.buttonMovieList);

	}

}
