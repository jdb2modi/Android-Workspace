package com.zaptech.jsonpro;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	TextView tv1;
	String strJson = "{\"employee\":{\"name\":\"Sachin\",\"Salary\":56000,\"Married\":true}}";
	String strJson2="{\"employee\":[{\"name\":\"Jaimin\"},{\"name\":\"Satyaraj\"}]}";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toast.makeText(getApplicationContext(), " callawd", 250).show();
		tv1 = (TextView) findViewById(R.id.tvDemo);

		
		try {
			JSONObject jsonObj = new JSONObject(strJson);
			JSONObject emp=jsonObj.getJSONObject("employee");
			String empname = emp.getString("name");
			String empSalary = emp.getString("Salary");
			String empStatus = emp.getString("Married");
			Toast.makeText(getApplicationContext(), ""+empname+empSalary+empStatus, 250).show();
			tv1.setText("Name : " + empname + "\n\nSalary : " + empSalary
					+ "\n\nStatus : " + empStatus);
			
			
			////
			tv1.setText("");
			JSONObject jobj2=new JSONObject(strJson2);
			JSONArray jarray=jobj2.optJSONArray("employee");
			for(int i=0;i<jarray.length();i++){
				JSONObject jsonsub=jarray.getJSONObject(i);
				tv1.setText(tv1.getText()+jsonsub.getString("name"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}
}
