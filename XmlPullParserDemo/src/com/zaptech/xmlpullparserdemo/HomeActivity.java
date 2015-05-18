package com.zaptech.xmlpullparserdemo;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class HomeActivity extends Activity {
	private String url1 = "http://api.openweathermap.org/data/2.5/weather?q=";
	private String url2 = "&mode=xml";
	private EditText location, country, temperature, humidity, pressure;
	private HandleXML obj;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		location = (EditText) findViewById(R.id.editText1);
		country = (EditText) findViewById(R.id.editText2);
		temperature = (EditText) findViewById(R.id.editText3);
		humidity = (EditText) findViewById(R.id.editText4);
		pressure = (EditText) findViewById(R.id.editText5);
	}

	public void open(View view) {
		String url = location.getText().toString();
		String finalUrl = url1 + url + url2;
		country.setText(finalUrl);
		obj = new HandleXML(finalUrl);
		obj.fetchXML();
		while (obj.parsingComplete)
			;
		country.setText(obj.getCountry());
		temperature.setText(obj.getTemperature());
		humidity.setText(obj.getHumidity());
		pressure.setText(obj.getPressure());

	}
 class HandleXML {

		   private String country = "county";
		   private String temperature = "temperature";
		   private String humidity = "humidity";
		   private String pressure = "pressure";
		   private String urlString = null;
		   private XmlPullParserFactory xmlFactoryObject;
		   public volatile boolean parsingComplete = true;
		   public HandleXML(String url){
		      this.urlString = url;
		   }
		   public String getCountry(){
		      return country;
		   }
		   public String getTemperature(){
		      return temperature;
		   }
		   public String getHumidity(){
		      return humidity;
		   }
		   public String getPressure(){
		      return pressure;
		   }

		   public void parseXMLAndStoreIt(XmlPullParser myParser) {
		      int event;
		      String text=null;
		      try {
		         event = myParser.getEventType();
		         while (event != XmlPullParser.END_DOCUMENT) {
		            String name=myParser.getName();
		            switch (event){
		               case XmlPullParser.START_TAG:
		               break;
		               case XmlPullParser.TEXT:
		               text = myParser.getText();
		               break;

		               case XmlPullParser.END_TAG:
		                  if(name.equals("country")){
		                     country = text;
		                  }
		                  else if(name.equals("humidity")){ 	
		                     humidity = myParser.getAttributeValue(null,"value");
		                  }
		                  else if(name.equals("pressure")){
		                     pressure = myParser.getAttributeValue(null,"value");
		                  }
		                  else if(name.equals("temperature")){
		                     temperature = myParser.getAttributeValue(null,"value");
		                  }
		                  else{
		                  }
		                  break;
		                  }		 
		                  event = myParser.next(); 

		              }
		                 parsingComplete = false;
		      } catch (Exception e) {
		         e.printStackTrace();
		      }

		   }
		   public void fetchXML(){
		      Thread thread = new Thread(new Runnable(){
		         @Override
		         public void run() {
		            try {
		               URL url = new URL(urlString);
		               HttpURLConnection conn = (HttpURLConnection) 
		               url.openConnection();
		                  conn.setReadTimeout(10000 /* milliseconds */);
		                  conn.setConnectTimeout(15000 /* milliseconds */);
		                  conn.setRequestMethod("GET");
		                  conn.setDoInput(true);
		                  conn.connect();
		            InputStream stream = conn.getInputStream();

		            xmlFactoryObject = XmlPullParserFactory.newInstance();
		            XmlPullParser myparser = xmlFactoryObject.newPullParser();

		            myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES
		            , false);
		            myparser.setInput(stream, null);
		            parseXMLAndStoreIt(myparser);
		            stream.close();
		            } catch (Exception e) {
		               e.printStackTrace();
		            }
		        }
		    });

		    thread.start(); 


		   }
 }
}
