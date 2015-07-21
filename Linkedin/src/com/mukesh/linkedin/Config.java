package com.mukesh.linkedin;

public class Config {

	public static String LINKEDIN_CONSUMER_KEY = "75bvd2j7ng6zjx";
	public static String LINKEDIN_CONSUMER_SECRET = "G2ANsvTr3z58iGap";
	public static String scopeParams = "rw_nus+r_basicprofile";
	
	public static String OAUTH_CALLBACK_SCHEME = "x-oauthflow-linkedin";
	public static String OAUTH_CALLBACK_HOST = "callback";
	public static String OAUTH_CALLBACK_URL = OAUTH_CALLBACK_SCHEME + "://" + OAUTH_CALLBACK_HOST;
}
