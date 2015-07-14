package com.zaptech.twitterintegration;

import java.io.InputStream;

import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_Home extends Activity implements OnClickListener {

	/* Shared preference keys */
	private static final String PREF_NAME = "sample_twitter_pref";
	private static final String PREF_KEY_OAUTH_TOKEN = "oauth_token";
	private static final String PREF_KEY_OAUTH_SECRET = "oauth_token_secret";
	private static final String PREF_KEY_TWITTER_LOGIN = "is_twitter_loggedin";
	private static final String PREF_USER_NAME = "twitter_user_name";

	/* Any number for uniquely distinguish your request */
	public static final int WEBVIEW_REQUEST_CODE = 100;

	private ProgressDialog mPDialog;

	private static Twitter sTwitter;
	private static RequestToken sRequestToken;

	private static SharedPreferences sSharedPreferences;

	private EditText mShareEditText;
	private TextView mUserName;
	private View mLoginLayout;
	private View mShareLayout;

	private String mConsumerKey = null;
	private String mConsumerSecret = null;
	private String mCallbackUrl = null;
	private String mOAuthVerifier = null;
	boolean isLoggedIn;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		/* initializing twitter parameters from string.xml */
		initTwitterConfigs();
		enableStrictMode();
		initControls();
		checkTwitterKeys();
		/* Initialize application preferences */
		sSharedPreferences = getSharedPreferences(PREF_NAME, 0);

		isLoggedIn = sSharedPreferences.getBoolean(PREF_KEY_TWITTER_LOGIN,
				false);

		/* if already logged in, then hide login layout and show share layout */
		if (isLoggedIn) {
			mLoginLayout.setVisibility(View.GONE);
			mShareLayout.setVisibility(View.VISIBLE);

			String username = sSharedPreferences.getString(PREF_USER_NAME, "");
			mUserName.setText(getResources().getString(R.string.hello)
					+ username);

		} else {
			mLoginLayout.setVisibility(View.VISIBLE);
			mShareLayout.setVisibility(View.GONE);

			Uri uri = getIntent().getData();

			if (uri != null && uri.toString().startsWith(mCallbackUrl)) {

				String verifier = uri.getQueryParameter(mOAuthVerifier);

				try {

					/* Getting oAuth authentication token */
					AccessToken accessToken = sTwitter.getOAuthAccessToken(
							sRequestToken, verifier);

					/* Getting user id form access token */
					long userID = accessToken.getUserId();
					final User user = sTwitter.showUser(userID);
					final String username = user.getName();

					/* save updated token */
					saveTwitterInfo(accessToken);

					mLoginLayout.setVisibility(View.GONE);
					mShareLayout.setVisibility(View.VISIBLE);
					mUserName.setText(getString(R.string.hello) + username);

				} catch (Exception e) {
					Log.e("Failed to login Twitter!!", e.getMessage());
				}
			}

		}
	}

	private void initControls() {
		mLoginLayout = (RelativeLayout) findViewById(R.id.login_layout);
		mShareLayout = (LinearLayout) findViewById(R.id.share_layout);
		mShareEditText = (EditText) findViewById(R.id.share_text);
		mUserName = (TextView) findViewById(R.id.user_name);

		/* register button click listeners */
		findViewById(R.id.btn_login).setOnClickListener(this);
		findViewById(R.id.btn_share).setOnClickListener(this);
		findViewById(R.id.btn_logout).setOnClickListener(this);
	}

	private void checkTwitterKeys() {
		/* Check if required twitter keys are set */
		if (TextUtils.isEmpty(mConsumerKey)
				|| TextUtils.isEmpty(mConsumerSecret)) {
			Toast.makeText(this, "Twitter key and secret not configured",
					Toast.LENGTH_SHORT).show();
			return;
		}

	}

	private void enableStrictMode() {
		/* Enabling strict mode */
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
	}

	/**
	 * Saving user information, after user is authenticated for the first time.
	 * You don't need to show user to login, until user has a valid access toen
	 */
	private void saveTwitterInfo(AccessToken accessToken) {

		long userID = accessToken.getUserId();

		User user;
		try {
			user = sTwitter.showUser(userID);

			String username = user.getName();

			/* Storing oAuth tokens to shared preferences */
			Editor e = sSharedPreferences.edit();
			e.putString(PREF_KEY_OAUTH_TOKEN, accessToken.getToken());
			e.putString(PREF_KEY_OAUTH_SECRET, accessToken.getTokenSecret());
			e.putBoolean(PREF_KEY_TWITTER_LOGIN, true);
			e.putString(PREF_USER_NAME, username);
			e.commit();

		} catch (TwitterException e1) {
			e1.printStackTrace();
		}

	}

	/* Reading twitter essential configuration parameters from strings.xml */
	private void initTwitterConfigs() {
		mConsumerKey = getString(R.string.twitter_consumer_key);
		mConsumerSecret = getString(R.string.twitter_consumer_secret);
		mCallbackUrl = getString(R.string.twitter_callback);
		mOAuthVerifier = getString(R.string.twitter_oauth_verifier);
	}

	private void loginToTwitter() {
		boolean isLoggedIn = sSharedPreferences.getBoolean(
				PREF_KEY_TWITTER_LOGIN, false);
		Toast.makeText(getApplicationContext(), ">>>>>ISLOGGEDIN" + isLoggedIn,
				Toast.LENGTH_LONG).show();
		if (!isLoggedIn) {
			final ConfigurationBuilder builder = new ConfigurationBuilder();
			builder.setOAuthConsumerKey(mConsumerKey);
			builder.setOAuthConsumerSecret(mConsumerSecret);

			final Configuration configuration = builder.build();
			final TwitterFactory factory = new TwitterFactory(configuration);
			sTwitter = factory.getInstance();

			try {
				sRequestToken = sTwitter.getOAuthRequestToken(mCallbackUrl);

				/**
				 * Loading twitter login page on webview for authorization Once
				 * authorized, results are received at onActivityResult
				 * */
				final Intent intent = new Intent(this, Activity_WebView.class);
				intent.putExtra(Activity_WebView.EXTRA_URL,
						sRequestToken.getAuthenticationURL());
				startActivityForResult(intent, WEBVIEW_REQUEST_CODE);

			} catch (TwitterException e) {
				e.printStackTrace();
			}
		} else {

			mLoginLayout.setVisibility(View.GONE);
			mShareLayout.setVisibility(View.VISIBLE);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (resultCode == Activity.RESULT_OK) {
			String verifier = data.getExtras().getString(mOAuthVerifier);
			try {
				AccessToken accessToken = sTwitter.getOAuthAccessToken(
						sRequestToken, verifier);

				long userID = accessToken.getUserId();
				final User user = sTwitter.showUser(userID);
				String username = user.getName();

				saveTwitterInfo(accessToken);

				mLoginLayout.setVisibility(View.GONE);
				mShareLayout.setVisibility(View.VISIBLE);
				mUserName.setText(Activity_Home.this.getResources().getString(
						R.string.hello)
						+ username);

			} catch (Exception e) {
				Log.e("Twitter Login Failed", e.getMessage());
			}
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_login:
			loginToTwitter();
			break;
		case R.id.btn_share:
			final String status = mShareEditText.getText().toString();

			if (status.trim().length() > 0) {
				new updateTwitterStatus().execute(status);
			} else {
				Toast.makeText(this, "Message is empty!!", Toast.LENGTH_SHORT)
						.show();
			}
			break;
		case R.id.btn_logout:

			saveTwitterInfo(null);
			

			break;
		}

	}

	class updateTwitterStatus extends AsyncTask<String, String, Void> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			mPDialog = new ProgressDialog(Activity_Home.this);
			mPDialog.setMessage("Posting to twitter...");
			mPDialog.setIndeterminate(false);
			mPDialog.setCancelable(false);
			mPDialog.show();
		}

		protected Void doInBackground(String... args) {

			String status = args[0];
			try {
				ConfigurationBuilder builder = new ConfigurationBuilder();
				builder.setOAuthConsumerKey(mConsumerKey);
				builder.setOAuthConsumerSecret(mConsumerSecret);

				// Access Token
				String access_token = sSharedPreferences.getString(
						PREF_KEY_OAUTH_TOKEN, "");
				// Access Token Secret
				String access_token_secret = sSharedPreferences.getString(
						PREF_KEY_OAUTH_SECRET, "");

				AccessToken accessToken = new AccessToken(access_token,
						access_token_secret);
				Twitter twitter = new TwitterFactory(builder.build())
						.getInstance(accessToken);

				// Update status
				StatusUpdate statusUpdate = new StatusUpdate(status);
				InputStream is = getResources().openRawResource(
						R.drawable.ic_launcher);
				statusUpdate.setMedia("test.jpg", is);

				twitter4j.Status response = twitter.updateStatus(statusUpdate);

				Log.d("Status", response.getText());

			} catch (TwitterException e) {
				Log.d("Failed to post!", e.getMessage());
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {

			/* Dismiss the progress dialog after sharing */
			mPDialog.dismiss();

			Toast.makeText(Activity_Home.this, "Posted to Twitter!",
					Toast.LENGTH_SHORT).show();

			// Clearing EditText field
			mShareEditText.setText("");
		}

	}
}
