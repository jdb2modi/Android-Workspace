package com.example.gpsnearbyplaces;

import android.graphics.Bitmap;


import java.util.ArrayList;

public class Constant {
	
	//92.168.15.164/RideBook/webservices/

	public static boolean searchEvent = false;
	public static boolean routesFromSearchOrFromNearby= false;
	
	public static String baseUrl = "http://zaptechphplab.com/ridebooks/webservices";
	//public static String baseUrl = "http://92.168.15.164/RideBook/webservices/";
	
	public static String AddressLocation="";
	public static boolean events = true;
	public static boolean routes = false;
	public static boolean rides = false;
	public static ArrayList<MyRoutesDetails> globalRoutesArrayList = new ArrayList<MyRoutesDetails>();
	public static int globalPosition = 0;
	public static String regId="";
	public static boolean firstHome = false;
	public static boolean editride=false;
	public static boolean editevent=false;
	public static boolean deleteEvent=false;
	public static boolean deleteEventFlag=false;
	
	public static boolean deleteRoute=false;
	
	public static boolean deleteRide=false;
	public static boolean calander = false;
			
	public static final String PAYPAL_APP_ID = "APP-80W284485P519543T";
	//public static final String PAYPAL_APP_ID = "APP-80W284485P519543T";
		
	
	public static ArrayList<RideModel> globalRideArrayList = new ArrayList<RideModel>();
	public static int rideGlobalPosition = 0;
	
	public static Bitmap bitmap;
	public static String selectedRide = "0";
	public static String RideNameTitle = "Bike";
    public static boolean formSetting = false;
    
    public static String loginID="";
     
    public static String userId="";
    public static boolean FromLocation= false;
	public static  boolean ToLocation= false;
	
	public static String rideCreateFromlat = "";
	public static String rideCreateFromlang = "";
	
	public static String rideCreateTolat = "";
	public static String rideCreateTolang = "";
   
    

    
    public static boolean eventCreate = false;
    public static boolean rideCreate=false;
    
    public static boolean fromFavoriteRouteDetail=false;
    
    public static boolean myevent = false;
    public static boolean myride = false;

    public static boolean eventfavorite = false;

    public static final String ADMOB_ID="ca-app-pub-9495241145820989/3857696155";
    
    public static final String APP_ID = "1436130873332199"; //RideApp
    //public static final String APP_ID = "303297789832313";//Ridebook
    //public static final String APP_ID = "561300287292507"; //F2S
   /* public static final String CONSUMER_KEY = "UYkB3ewwqnU5cxaXkkbIA";
	public static final String CONSUMER_SECRET= "xO5alWghXfF5dDMeJreTwryUWy4m5l5lIiOXOiRRjM";*/

    public static final String REQUEST_URL = "https://api.twitter.com/oauth/request_token";
    public static final String ACCESS_URL = " 	https://api.twitter.com/oauth/access_token";
    public static final String AUTHORIZE_URL = "https://api.twitter.com/oauth/authorize";

    public static final String OAUTH_CALLBACK_SCHEME = "x-oauthflow-twitter";
    public static final String OAUTH_CALLBACK_HOST = "callback";
    public static final String OAUTH_CALLBACK_URL = OAUTH_CALLBACK_SCHEME
	    + "://" + OAUTH_CALLBACK_HOST;
    
    /********Twitter********/
    /*public static final String CONSUMER_KEY = "cUvaDe81T9f354yWsqL2Q";
    public static final String CONSUMER_SECRET = "yZ1SBCSByjmTpqKnjy7SWawcWvzDsiQ6EfWWlCL2xg";
*/
    
    //public static final String APP_ID = "2055";
	public static final String AUTH_KEY = "bNEEymEjrnr7rTx";
	public static final String AUTH_SECRET = "kzFshC3sFH7OQcS";
    public static final String CONSUMER_KEY = "1NzqEQcuLKEeXdTZ7lHE2A";
	public static final String CONSUMER_SECRET = "uBvWxuPRSFPPctrTIbOOVKRaQaK5AiZvhxoma5pVYhM";
}
