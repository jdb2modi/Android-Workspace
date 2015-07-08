package com.zaptech.facebookintegration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.AsyncFacebookRunner.RequestListener;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;

public class Activity_Home extends Activity implements OnClickListener {

	private Button mBtnFbLogin;
	private Button mBtnPostToWall;
	private Button mBtnLogout;
	private Button mBtnShowProfileInfo;
	private Button mBtnShowTokens;

	// Your Facebook APP ID
	private static String sAPP_ID = "1458405367811906"; // Replace your App ID
														// here
	// Instance of Facebook Class
	private Facebook mFacebook;
	private AsyncFacebookRunner mAsyncRunner;
	String FILENAME = "AndroidSSO_data";
	private SharedPreferences mPrefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();
	}

	public void init() {
		mFacebook = new Facebook(sAPP_ID);
		mAsyncRunner = new AsyncFacebookRunner(mFacebook);
		mBtnFbLogin = (Button) findViewById(R.id.btn_Login);
		mBtnFbLogin.setOnClickListener(this);
		mBtnPostToWall = (Button) findViewById(R.id.btn_PostToWall);
		mBtnPostToWall.setOnClickListener(this);
		mBtnLogout = (Button) findViewById(R.id.btn_Logout);
		mBtnLogout.setOnClickListener(this);
		mBtnShowProfileInfo = (Button) findViewById(R.id.btn_ShowProfileInfo);
		mBtnShowProfileInfo.setOnClickListener(this);
		mBtnShowTokens = (Button) findViewById(R.id.btn_ShowTokens);
		mBtnShowTokens.setOnClickListener(this);

	}

	public void showTokens() {
		Toast.makeText(Activity_Home.this,
				"Access Token:: " + mFacebook.getAccessToken(),
				Toast.LENGTH_LONG).show();
	}

	public void loginToFacebook() {

		mPrefs = getPreferences(MODE_PRIVATE);
		String access_token = mPrefs.getString("access_token", null);
		long expires = mPrefs.getLong("access_expires", 0);

		System.err.println("+++++++++++++++++++++++++++++++++++++++++++>>>>>>>"
				+ access_token);

		if (access_token != null) {
			mFacebook.setAccessToken(access_token);
		}

		if (expires != 0) {
			mFacebook.setAccessExpires(expires);
		}

		if (!mFacebook.isSessionValid()) {
			mFacebook.authorize(this,
					new String[] { "email", "public_profile" },
					new DialogListener() {

						@Override
						public void onCancel() {
							// Function to handle cancel event
						}

						@Override
						public void onComplete(Bundle values) {
							// Function to handle complete event
							// Edit Preferences and update facebook acess_token
							SharedPreferences.Editor editor = mPrefs.edit();
							editor.putString("access_token",
									mFacebook.getAccessToken());
							editor.putLong("access_expires",
									mFacebook.getAccessExpires());
							editor.commit();
							mBtnFbLogin.setVisibility(View.INVISIBLE);
							mBtnPostToWall.setVisibility(View.VISIBLE);
							mBtnShowProfileInfo.setVisibility(View.VISIBLE);
							mBtnShowTokens.setVisibility(View.VISIBLE);
							mBtnLogout.setVisibility(View.VISIBLE);
						}

						@Override
						public void onError(DialogError error) {
							// Function to handle error

						}

						@Override
						public void onFacebookError(FacebookError fberror) {
							// Function to handle Facebook errors

						}

					});

		}
	}

	public void postToWall() {
		// post on user's wall.
		mFacebook.dialog(this, "feed", new DialogListener() {

			@Override
			public void onFacebookError(FacebookError e) {
			}

			@Override
			public void onError(DialogError e) {
			}

			@Override
			public void onComplete(Bundle values) {
			}

			@Override
			public void onCancel() {
			}
		});

	}

	public void getProfileInformation() {
		mAsyncRunner.request("me", new RequestListener() {
			@Override
			public void onComplete(String response, Object state) {
				Log.d("Profile", response);
				String json = response;
				try {
					JSONObject profile = new JSONObject(json);
					// getting name of the user
					final String name = profile.getString("name");
					// getting email of the user
					final String email = profile.getString("email");

					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							Toast.makeText(getApplicationContext(),
									"Name: " + name + "\nEmail: " + email,
									Toast.LENGTH_LONG).show();
						}

					});

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onIOException(IOException e, Object state) {
			}

			@Override
			public void onFileNotFoundException(FileNotFoundException e,
					Object state) {
			}

			@Override
			public void onMalformedURLException(MalformedURLException e,
					Object state) {
			}

			@Override
			public void onFacebookError(FacebookError e, Object state) {
			}
		});
	}

	public void logoutFromFacebook(Context ctx) {
		final ProgressDialog dialog;
		dialog = new ProgressDialog(Activity_Home.this);
		dialog.setTitle("Loading");
		dialog.show();

		CookieSyncManager cookieSyncMngr = CookieSyncManager
				.createInstance(ctx);
		cookieSyncMngr.startSync();
		CookieManager cookieManager = CookieManager.getInstance();
		cookieManager.removeAllCookie();
		cookieManager.removeSessionCookie();
		cookieSyncMngr.stopSync();
		try {
			// Session.initializeStaticContext(this.getApplicationContext());
			AsyncFacebookRunner mAsyncRunner = new AsyncFacebookRunner(
					mFacebook);
			mAsyncRunner.logout(Activity_Home.this,
					new AsyncFacebookRunner.RequestListener() {

						@Override
						public void onMalformedURLException(
								MalformedURLException e, Object state) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onIOException(IOException e, Object state) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onFileNotFoundException(
								FileNotFoundException e, Object state) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onFacebookError(FacebookError e,
								Object state) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onComplete(String response, Object state) {
							// TODO Auto-generated method stub

							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									// TODO Auto-generated method stub
									Log.d("Logout from profile",
											"Logout from profile");

									SharedPreferences.Editor editor = mPrefs
											.edit();

									editor.putString("access_token", "");
									editor.putLong("access_expires", 0);
									editor.commit();
									mBtnLogout.setVisibility(View.INVISIBLE);
									mBtnFbLogin.setVisibility(View.VISIBLE);
									mBtnPostToWall
											.setVisibility(View.INVISIBLE);
									mBtnShowProfileInfo
											.setVisibility(View.INVISIBLE);
									mBtnShowTokens
											.setVisibility(View.INVISIBLE);

									if (dialog.isShowing()) {
										dialog.dismiss();
									}

								}
							});

						}
					});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_Login:

			loginToFacebook();
			break;
		case R.id.btn_PostToWall:

			postToWall();

			break;
		case R.id.btn_Logout:

			logoutFromFacebook(Activity_Home.this);
			break;
		case R.id.btn_ShowProfileInfo:
			getProfileInformation();
			break;
		case R.id.btn_ShowTokens:
			showTokens();
			break;
		default:
			break;
		}

	}
}
